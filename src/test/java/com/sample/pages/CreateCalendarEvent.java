package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateCalendarEvent {

    public CreateCalendarEvent() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "[data-name='recurrence-repeat']")
    private WebElement repeatBox;

    //TODO: was not able to locate this element
//    @FindBy(css = "[name^=temp-validation-name]")
    @FindBy(css = "[data-related-field='interval']")
    private WebElement repeatIntervalBox;

    @FindBy(css = "[data-name='recurrence-daily'] .recurrence-subview-control__item:nth-of-type(1) [type='radio']")
    private WebElement dailyRepeatRadioButton;

    @FindBy(css = ".alert-info.control-group.recurrence-summary > .controls  span")
    private WebElement summaryText;

    @FindBy(css = ".action-button.btn")
    private WebElement saveAndCloseButton;

    @FindBy(css = "a[data-action='cancel']")
    private WebElement cancelButton;

    @FindBy(css = "#oro_calendar_event_form_title-uid-63587e733d2ae")
    private WebElement titleBox;

    @FindBy(css = "[id^='temp-validation-name']")
    private WebElement repeatErrorMsg;

    @FindBy(css = "#tinymce")
    private WebElement descriptionBox;

    @FindBy(css = "#tinymce p")
    private WebElement descriptionParagraph;

    @FindBy(css = "[id^='oro_calendar_event_form_description-uid']")
    private WebElement iframe;


    public void verifyDefaultRepeatInterval() {
        repeatBox.click();
        Driver.sleep(1);
        assertTrue(dailyRepeatRadioButton.isSelected());
//        assertEquals(Integer.parseInt(repeatIntervalBox.getAttribute("value")), 1);
        System.out.println(summaryText.getText());
        assertEquals(Integer.parseInt(summaryText.getText().substring(12, 13)), 1);
        cancelButton.click();
        Driver.sleep(1);
    }

    public void verifyRepeatIntervalCantBeEmpty() {
        repeatBox.click();
        Driver.sleep(1);
        assertTrue(dailyRepeatRadioButton.isSelected());
        repeatIntervalBox.sendKeys(Keys.BACK_SPACE);
        Driver.blankClick();
        assertTrue(repeatErrorMsg.isDisplayed());
        Driver.captureScreen();
        cancelButton.click();
        Driver.sleep(1);
    }

    public void verifyRepeatIntervalWithInvalidNumbers() {
        repeatBox.click();
        Driver.sleep(1);
        assertTrue(dailyRepeatRadioButton.isSelected());
        repeatIntervalBox.sendKeys(Keys.BACK_SPACE + "-2");
        Driver.blankClick();
        assertTrue(repeatErrorMsg.isDisplayed());
        Driver.captureScreen();
        repeatIntervalBox.clear();
        repeatIntervalBox.sendKeys("134");
        Driver.blankClick();
        assertTrue(repeatErrorMsg.isDisplayed());
        Driver.captureScreen();
        cancelButton.click();
        Driver.sleep(1);
    }

    public void typeDescription() {
        Driver.sleep(2);
        Driver.getDriver().switchTo().frame(iframe);
        String testText = "Description box being tested";
        descriptionBox.sendKeys(testText);
        assertEquals(descriptionParagraph.getText(), testText);
        Driver.captureScreen();
        Driver.getDriver().switchTo().defaultContent();
        cancelButton.click();
        Driver.sleep(1);
    }

}
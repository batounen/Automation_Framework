package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HowToUsePinbar {

    Home home = new Home();

    public HowToUsePinbar() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".clearfix > h3")
    private WebElement howToUsePinbarHeader;

    @FindBy(css = "div[class='container-fluid'] > div :nth-child(2)")
    private WebElement guideText;

    @FindBy(css = "div[class='container-fluid'] > div :nth-child(3)")
    private WebElement guideImage;

    @FindBy(css = "[title='Mark/unmark the page as favorite']")
    private WebElement starIcon;

    @FindBy(css = "[title='Pin/unpin the page']")
    private WebElement pinIcon;

    public void captureLinktext() {
        Driver.captureHighlighted(home.getPinbarLink());
        Driver.sleep(1);
    }

    public void capturePinIcon() {
        Driver.captureHighlighted(pinIcon);
        Driver.sleep(1);
    }

    public void guides_test() {
        if (home.getPinbarLink().isDisplayed()) {
            home.getPinbarLink().click();
            Driver.waitForTitle(Driver.getProperty("pinbar"), 10);
            assertTrue(howToUsePinbarHeader.isDisplayed());
            assertTrue(guideText.isDisplayed());
            assertTrue(guideImage.isDisplayed());
        }
    }

    public void pinIcon_test() {
        assertTrue(pinIcon.isDisplayed());

        Driver.captureHighlighted(pinIcon);
        Driver.sleep(1);

        pinIcon.click();

        Driver.captureHighlighted(pinIcon);
        Driver.sleep(1);

        String classAttributeAfterClick = Driver.getDriver().findElement(By.cssSelector(".top-action-box :nth-child(2)")).getAttribute("class");

        assertEquals(classAttributeAfterClick, Driver.getProperty("pinAttribute"));

        pinIcon.click();
    }
}
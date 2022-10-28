package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalendarEvents {

    public CalendarEvents() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".btn-primary")
    private WebElement createCalendarEventButton;

    public WebElement getCreateCalendarEventButton() {
        return createCalendarEventButton;
    }

    public void clickCreateCalendarEventButton() {
        createCalendarEventButton.click();
        Driver.waitForTitle(Driver.getProperty("createCalendarEvent"), 10);
        Driver.sleep(2);
    }

}
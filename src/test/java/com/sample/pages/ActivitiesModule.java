package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActivitiesModule {

    public ActivitiesModule() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "li[data-route='oro_call_index'] a")
    private WebElement calls;

    @FindBy(css = "li[data-route='oro_calendar_event_index'] a")
    private WebElement calendarEvents;

    public WebElement getCalendarEvents() {
        return calendarEvents;
    }

    public void access_activities_module(String moduleName) {
        Home home = new Home();
        home.hover_to_module(Driver.getProperty("activities"));
        switch (moduleName) {
            case "Calls":
                Driver.captureHighlighted(calls);
                calls.click();
                Driver.sleep(2);
                break;
            case "Calendar Events":
                Driver.captureHighlighted(calendarEvents);
                calendarEvents.click();
                Driver.sleep(2);
                break;
        }
    }
}
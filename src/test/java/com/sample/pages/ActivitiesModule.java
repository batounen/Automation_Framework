package com.sample.pages;

import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActivitiesModule extends TestBase {

    public ActivitiesModule() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "li[data-route='oro_call_index'] a")
    private WebElement call;

    @FindBy(css = "li[data-route='oro_calendar_event_index'] a")
    private WebElement calendarEvent;

    public void access_activities_module(String moduleName) {
        Home home = new Home();
        home.hover_to_module(Driver.getProperty("activities"));
        switch (moduleName) {
            case "Calls":
                Driver.captureHighlighted(call);
                call.click();
                Driver.sleep(2);
//                Driver.waitUntilClickable(calls.getLogCallButton(), 10);
                break;
            case "Calendar Events":
                Driver.captureHighlighted(calendarEvent);
                calendarEvent.click();
                Driver.sleep(2);
//                Driver.waitUntilClickable(calendarEvents.getCreateCalendarEventButton(), 10);
                break;
        }
    }
}
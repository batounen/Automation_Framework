package com.sample.tests.us77;

import com.sample.pages.*;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class US77 extends TestBase {

    /*
    As a user, I want to create a recurring (repetitive) calendar event
        AC #1: user should see the number “1” by default.
        AC #2: user should see an error message “This value should not be blank.” when the Calendar event repeat field is cleared(delete the number 1).
     */

    @DataProvider(name = "dp")
    public Object[][] dp() {
        return Driver.readXLSX();
    }

    @Test(priority = 1, dataProvider = "dp")
    public void verify_default_repeat_interval_test(String username, String password) {
        login = new Login();
        home = new Home();
        activitiesModule = new ActivitiesModule();
        calendarEvents = new CalendarEvents();
        createCalendarEvent = new CreateCalendarEvent();

        login.login_positive(username, password);
        activitiesModule.access_activities_module(Driver.getProperty("calendarEvents"));
        calendarEvents.clickCreateCalendarEventButton();
        createCalendarEvent.verifyDefaultRepeatInterval();
        home.logout();
    }

    @Test(priority = 2, dataProvider = "dp")
    public void verify_repeat_interval_cantBe_empty_test(String username, String password) {
        login = new Login();
        home = new Home();
        activitiesModule = new ActivitiesModule();
        calendarEvents = new CalendarEvents();
        createCalendarEvent = new CreateCalendarEvent();

        login.login_positive(username, password);
        activitiesModule.access_activities_module(Driver.getProperty("calendarEvents"));
        calendarEvents.clickCreateCalendarEventButton();
        createCalendarEvent.verifyRepeatIntervalCantBeEmpty();
        home.logout();
    }

}
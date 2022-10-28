package com.sample.tests.us78;

import com.sample.pages.*;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class US78 extends TestBase {

    /*
    As a user, I should see error messages when I enter an invalid integer into the calendar repeat day input box.
        AC #1: users see error messages for entering invalid integers.
        If enters less than 1 —> user should see “The value have not to be less than 1.”
        If enters more than 99 —> user should see “The value have not to be more than 99.”
     */

    @DataProvider(name = "dp")
    public Object[][] dp() {
        return Driver.readXLSX();
    }

    @Test(dataProvider = "dp")
    public void verify_default_repeat_interval_test(String username, String password) {
        login = new Login();
        home = new Home();
        activitiesModule = new ActivitiesModule();
        calendarEvents = new CalendarEvents();
        createCalendarEvent = new CreateCalendarEvent();

        login.login_positive(username, password);
        activitiesModule.access_activities_module(Driver.getProperty("calendarEvents"));
        calendarEvents.clickCreateCalendarEventButton();
        createCalendarEvent.verifyRepeatIntervalWithInvalidNumbers();
        home.logout();
    }

}
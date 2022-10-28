package com.sample.tests.us79;

import com.sample.pages.*;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class US79 extends TestBase {

    /*
    As a user, I want to write the “Description” when I create a calendar event. (Iframe)
    AC1: users should be able to write messages in the Description field on the calendar event page.
     */

    @DataProvider(name = "dp")
    public Object[][] dp() {
        return Driver.readXLSX();
    }

    @Test(dataProvider = "dp")
    public void type_description_test(String username, String password) {
        login = new Login();
        home = new Home();
        activitiesModule = new ActivitiesModule();
        calendarEvents = new CalendarEvents();
        createCalendarEvent = new CreateCalendarEvent();

        login.login_positive(username, password);
        activitiesModule.access_activities_module(Driver.getProperty("calendarEvents"));
        calendarEvents.clickCreateCalendarEventButton();
        createCalendarEvent.typeDescription();
        home.logout();
    }

}
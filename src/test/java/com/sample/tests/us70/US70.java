package com.sample.tests.us70;

import com.sample.pages.Home;
import com.sample.pages.Login;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class US70 extends TestBase {

    /*
    As a user, I should be accessing all the main modules of the app.
        AC #1: store and sales managers should view 8 module names.
        Expected module names: Dashboards, Fleet, Customers, Sales, Activities, Marketing, Reports & Segments, System

        AC #2: drivers should view 4 module names
        Expected module names: Fleet, Customers, Activities, System
     */

    @DataProvider(name = "dp")
    public Object[][] dp() {
        List<String> usernames = Driver.readCSV(0);
        Object[][] testData = new Object[usernames.size()][2];
        for (int i = 0; i < usernames.size(); i++) {
            testData[i][0] = usernames.get(i);
            testData[i][1] = Driver.getProperty("validPassword");
        }
        return testData;
    }

    @Test(dataProvider = "dp")
    public void verify_visibility_allModules(String username, String password) {
        login = new Login();
        home = new Home();
        login.login_positive(username, password);

        if (username.startsWith("user")) {
            home.verify_visibility_allModules("driver");
        } else if (username.startsWith("s")) {
            home.verify_visibility_allModules("manager");
        }
        Driver.waitUntilClickable(home.getGetHelp(), 10);
        Driver.captureHighlighted(home.getAllModuleBar());
        home.logout();
    }
}
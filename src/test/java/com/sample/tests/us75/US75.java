package com.sample.tests.us75;

import com.sample.pages.FleetModule;
import com.sample.pages.Home;
import com.sample.pages.Login;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class US75 extends TestBase {

    /*
    As a user, I want to view edit car info icons from the Vehicle page
    AC #1:  users should see “view, edit, delete” when they hover the mouse to the three dots “...”
     */

    @DataProvider(name = "dp")
    public Object[][] dp() {
        return Driver.readXLSX();
    }

    @Test(dataProvider = "dp")
    public void vehicle_edit_buttons_test(String username, String password) {
        home = new Home();
        login = new Login();
        fleetModule = new FleetModule();

        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicles"));
        fleetModule.vehicle_info_edit_verification_test();
        home.logout();
    }


}
package com.sample.tests.us73;

import com.sample.pages.FleetModule;
import com.sample.pages.Home;
import com.sample.pages.Login;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class US73 extends TestBase {

    /*
    As a user, I want to access to Vehicle contracts page
    AC1: Store managers and Sales managers access the Vehicle contracts page.
    Expected title: All - Vehicle Contract - Entities - System - Car - Entities - System.
    AC2: Drivers should NOT able to access the Vehicle contracts page, the app should display “You do not have permission to perform this action.”
     */

    @DataProvider(name = "dp1")
    public Object[][] dp1() {
        return Driver.readXLSX(Driver.getProperty("excelFilePath_MD"), "manager");
    }

    @DataProvider(name = "dp2")
    public Object[][] dp2() {
        return Driver.readXLSX(Driver.getProperty("excelFilePath_MD"), "driver");
    }

    @Test(dataProvider = "dp1", priority = 1)
    public void vehicle_access_managers_test(String username, String password) {
        login = new Login();
        home = new Home();
        fleetModule = new FleetModule();

        login.login_positive(username, password);
        fleetModule.vehicle_contracts_access_test(username);
        home.logout();
    }

    @Test(dataProvider = "dp2", priority = 2)
    public void vehicle_access_drivers_test(String username, String password) {
        login = new Login();
        home = new Home();
        fleetModule = new FleetModule();

        login.login_positive(username, password);
        fleetModule.vehicle_contracts_access_test(username);
        home.logout();
    }
}
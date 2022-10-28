package com.sample.tests.us74;

import com.sample.pages.FleetModule;
import com.sample.pages.Home;
import com.sample.pages.Login;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class US74 extends TestBase {

    /*
    As a user, I want to view columns on the Vehicle models page.(web-table)
    AC #1: The store manager and sales manager should see 10 columns on the Vehicle Model page.
    Expected Column names:
    MODEL NAME, MAKE, CAN BE REQUESTED, CVVI, CO2 FEE (/MONTH), COST (DEPRECIATED), TOTAL COST (DEPRECIATED), CO2 EMISSIONS, FUEL TYPE, VENDORS
    AC #2: Drivers should not able to access the Vehicle Model page, users should see “You do not have permission to perform this action.”
     */

    @DataProvider(name = "dp1")
    public Object[][] dp1() {
        return Driver.readXLSX(Driver.getProperty("excelFilePath_MD"), "manager");
    }

    // Driver access test failed manually. Thus, no automation script was written
    @DataProvider(name = "dp2")
    public Object[][] dp2() {
        return Driver.readXLSX(Driver.getProperty("excelFilePath_MD"), "driver");
    }

    @Test(dataProvider = "dp1")
    public void vehicle_models_columns_manager_test(String username, String password) {
        login = new Login();
        home = new Home();
        fleetModule = new FleetModule();

        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicleModel"));

//        Driver.captureScreen();

        Set<String> expectedColumnTitles = new LinkedHashSet<>(Arrays.asList("MODEL NAME", "MAKE", "CAN BE REQUESTED", "CVVI", "CO2 FEE (/MONTH)", "COST (DEPRECIATED)", "TOTAL COST (DEPRECIATED)", "CO2 EMISSIONS", "FUEL TYPE", "VENDORS"));

        fleetModule.vehicle_model_page_columns_verification_test(expectedColumnTitles);

        home.logout();
    }
}
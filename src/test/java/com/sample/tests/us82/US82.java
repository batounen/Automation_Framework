package com.sample.tests.us82;

import com.sample.pages.FleetModule;
import com.sample.pages.Home;
import com.sample.pages.Login;
import com.sample.pages.VehihcleCosts;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class US82 extends TestBase {

    /*
    As a user, I want to manage filters on the Vehicle Costs page. (Web-table and checkbox)
        AC #1: Users should see three columns on the Vehicle Costs page.
                   Expected Column names:
                   TYPE, TOTAL PRICE, DATE
        AC #2: users check the first checkbox to check all the Vehicle Costs
     */

    @DataProvider(name = "dp")
    public Object[][] dp() {
        return Driver.readXLSX();
    }

    @Test(dataProvider = "dp", priority = 1)
    public void vehicle_cost_filters_test(String username, String password) {
        login = new Login();
        home = new Home();
        fleetModule = new FleetModule();
        vehihcleCosts = new VehihcleCosts();

        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicleCosts"));
        vehihcleCosts.verifyTableColumns();
//        Driver.captureScreen();
        home.logout();
    }

    @Test(dataProvider = "dp", priority = 2)
    public void vehicle_cost_checkAllBox_test(String username, String password) {
        if (username.startsWith("s")) {
            login = new Login();
            home = new Home();
            fleetModule = new FleetModule();
            vehihcleCosts = new VehihcleCosts();

            login.login_positive(username, password);
            fleetModule.access_fleet_module(Driver.getProperty("vehicleCosts"));
            vehihcleCosts.verifyCheckAllVehicleBox();
//        Driver.captureScreen();
            home.logout();
        }
    }
}
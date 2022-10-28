package com.sample.tests.us80;

import com.sample.pages.FleetModule;
import com.sample.pages.Home;
import com.sample.pages.Login;
import com.sample.pages.VehicleOdometer;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class US80 extends TestBase {

    /*
    As a user, I want to view car odometer info on the Vehicles Odometers page.
        AC1: Store and Sales managers should see an error message “You do not have permission to perform this action.” when they click the “Vehicle Odometer” module.
        AC2: Drivers should see the default page as 1.
        AC3: Divers should see the View Per Page is 25 by default.
     */

    @DataProvider(name = "dpManager")
    public Object[][] dp1() {
        return Driver.readXLSX("manager");
    }

    @DataProvider(name = "dpDriver")
    public Object[][] dp2() {
        return Driver.readXLSX("driver");
    }

    @Test(dataProvider = "dpManager", priority = 1)
    public void vehicle_odometer_access_manager_test(String username, String password) {
        login = new Login();
        home = new Home();
        fleetModule = new FleetModule();
        vehicleOdometer = new VehicleOdometer();

        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicleOdometer"));
        vehicleOdometer.verify_error_msg();
//        Driver.captureScreen();
        home.logout();
    }

    @Test(dataProvider = "dpDriver", priority = 2)
    public void vehicle_odometer_default_page_driver_test(String username, String password) {
        login = new Login();
        home = new Home();
        fleetModule = new FleetModule();
        vehicleOdometer = new VehicleOdometer();

        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicleOdometer"));
        vehicleOdometer.verify_default_page_test();
        Driver.sleep(1);
//        Driver.captureScreen();
        home.logout();
    }

    @Test(dataProvider = "dpDriver", priority = 3)
    public void vehicle_odometer_access_driver_test(String username, String password) {
        login = new Login();
        home = new Home();
        fleetModule = new FleetModule();
        vehicleOdometer = new VehicleOdometer();

        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicleOdometer"));
        vehicleOdometer.verify_default_view_per_page_test();
        Driver.sleep(1);
//        Driver.captureScreen();
        home.logout();
    }

}
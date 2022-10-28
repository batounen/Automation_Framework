package com.sample.tests.us76;

import com.sample.pages.FleetModule;
import com.sample.pages.Home;
import com.sample.pages.Login;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class US76 extends TestBase {

    /*
    As a user, I should be to select any vehicle from the Vehicle page(web-table)
        AC #1:  once the user launched to the Vehicle page, all the checkboxes should be unchecked
        AC #2:  user check the first checkbox to check all the cars
        AC #3:  users can select any car
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
    public void all_vehicles_unchecked_manager_test(String username, String password) {
        home = new Home();
        login = new Login();
        fleetModule = new FleetModule();
        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicles"));
//        Driver.captureScreen();
        fleetModule.all_vehicle_checkboxes_unchecked_verification_test();
        home.logout();
    }

    @Test(dataProvider = "dp1", priority = 2)
    public void select_all_vehicles_manager_test(String username, String password) {
        home = new Home();
        login = new Login();
        fleetModule = new FleetModule();
        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicles"));
        fleetModule.selectAllBox_verification_test();
        home.logout();
    }

    @Test(dataProvider = "dp1", priority = 3)
    public void select_any_vehicle_manager_test(String username, String password) {
        home = new Home();
        login = new Login();
        fleetModule = new FleetModule();
        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicles"));
        fleetModule.each_vehicle_select_verification_test();
        home.logout();
    }

    @Test(dataProvider = "dp2", priority = 4)
    public void all_vehicles_unchecked_driver_test(String username, String password) {
        home = new Home();
        login = new Login();
        fleetModule = new FleetModule();
        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicles"));
//        Driver.captureScreen();
        fleetModule.all_vehicle_checkboxes_unchecked_verification_test();
        home.logout();
    }

    @Test(dataProvider = "dp2", priority = 5)
    public void select_all_vehicles_driver_test(String username, String password) {
        home = new Home();
        login = new Login();
        fleetModule = new FleetModule();
        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicles"));
        fleetModule.selectAllBox_verification_test();
        home.logout();
    }

    @Test(dataProvider = "dp2", priority = 6)
    public void select_any_vehicle_driver_test(String username, String password) {
        home = new Home();
        login = new Login();
        fleetModule = new FleetModule();
        login.login_positive(username, password);
        fleetModule.access_fleet_module(Driver.getProperty("vehicles"));
        fleetModule.each_vehicle_select_verification_test();
        home.logout();
    }
}
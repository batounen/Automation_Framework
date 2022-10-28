package com.sample.tests.us81;

import com.sample.pages.CustomerAccounts;
import com.sample.pages.Home;
import com.sample.pages.Login;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class US81 extends TestBase {

    /*
    As a user, I want to filter customers’ info on the Account page
    AC1: users should see 8 filter items on the Accounts page
    Expected filter names:  Account Name, Contact Name, Contact Email, Contact Phone, Owner,  Business Unit, Created At, Updated At
     */

    @DataProvider(name = "dp")
    public Object[][] dp() {
        return Driver.readXLSX();
    }

    @Test(dataProvider = "dp")
    public void verify_customer_account_filters_test(String username, String password) {
        login = new Login();
        home = new Home();
        customerAccounts = new CustomerAccounts();

        login.login_positive(username, password);
        customerAccounts.access_customers_module(Driver.getProperty("accounts"));
        customerAccounts.verify_account_filters();
        Driver.sleep(1);
//        Driver.captureScreen();
        home.logout();
    }
}
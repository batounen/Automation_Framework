package com.sample.tests.us72;

import com.sample.pages.Home;
import com.sample.pages.HowToUsePinbar;
import com.sample.pages.Login;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.*;


public class US72 extends TestBase {

    // As a user, I want to learn how to use the pinbar

    @DataProvider(name = "dp")
    public Object[][] dp() {
        return Driver.readXLSX();
    }

    @Test(dataProvider = "dp")
    public void testCase1(String userName, String password) {
        login = new Login();
        home = new Home();
        pinbar = new HowToUsePinbar();

        login.login_positive(userName, password);
        pinbar.captureLinktext();
        pinbar.guides_test();
        pinbar.pinIcon_test();
        home.logout();
    }
}
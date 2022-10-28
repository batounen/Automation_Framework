package com.sample.tests.us71;

import com.sample.pages.Home;
import com.sample.pages.Login;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.*;

public class US71 extends TestBase {

    /*
    As a user, I should be able to access the Oroinc Documentation page.
    AC #1: users access the Oronic Documentation page by clicking the question icon. The page URL: https://doc.oroinc.com/
     */

    @DataProvider(name = "dp")
    public Object[][] dataProvider() {
        return Driver.readXLSX();
    }

    @Test(dataProvider = "dp")
    public void oroinc_doc_test(String username, String password) {
        login = new Login();
        home = new Home();

        login.login_positive(username, password);
        home.getHelp_button_test();
        home.logout();
    }

}
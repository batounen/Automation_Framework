package com.sample.tests.us83;

import com.sample.pages.Home;
import com.sample.pages.Login;
import com.sample.pages.Marketing;
import com.sample.pages.MarketingCampaigns;
import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class US83 extends TestBase {

    /*
    As a user, I want to manage filters on the Marketing page.
    AC #1: Store and sales managers should see all the five filter options are checked by default.
    AC #2: any amount of boxes should be unchecked. (can check only 1, or multiple)
     */

    @DataProvider(name = "dp")
    public Object[][] dp() {
        return Driver.readXLSX(Driver.getProperty("excelFilePath_MD"), "manager");
    }

    @Test(dataProvider = "dp", priority = 1)
    public void campaign_filters_test(String username, String password) {
        login = new Login();
        home = new Home();
        marketingCampaigns = new MarketingCampaigns();
        marketing = new Marketing();

        login.login_positive(username, password);
        marketing.access_marketing_module(Driver.getProperty("campaigns"));
        marketingCampaigns.verifyFilterCheckboxes();
        home.logout();
    }

}
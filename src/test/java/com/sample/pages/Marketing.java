package com.sample.pages;

import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Marketing extends TestBase {

    public Marketing() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "a[href='/campaign/'] span")
    private WebElement campaignsModule;

    @FindBy(css = "a[href='/campaign/email/'] span")
    private WebElement emailCampaignsModule;

    public void access_marketing_module(String moduleName) {
        home = new Home();
        home.hover_to_module(Driver.getProperty("marketing"));
        switch (moduleName) {
            case "Campaigns":
                Driver.captureHighlighted(campaignsModule);
                campaignsModule.click();
                Driver.sleep(2);
                break;
            case "Email Campaigns":
                Driver.captureHighlighted(emailCampaignsModule);
                emailCampaignsModule.click();
                Driver.sleep(2);
                break;
        }
    }
}
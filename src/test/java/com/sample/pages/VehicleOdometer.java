package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class VehicleOdometer {

    public VehicleOdometer() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = ".message")
    private WebElement errorMsg;

    @FindBy(css = ".input-widget")
    private WebElement currentPageNumber;

    @FindBy(css = ".btn-group button[data-toggle='dropdown']")
    private WebElement viewPerPage;

    public void verify_error_msg() {
        assertTrue(errorMsg.isDisplayed());
    }

    public void verify_default_page_test() {
        assertEquals(currentPageNumber.getAttribute("value"), Driver.getProperty("defaultPage"), "Current page not matching!");
    }

    public void verify_default_view_per_page_test() {
        assertEquals(Integer.parseInt(viewPerPage.getText()), 25, "Default view per page page not matching!");
    }
}
package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class CustomerAccounts {

    public CustomerAccounts() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "a[href='/account'] span")
    private WebElement accountsModule;

    @FindBy(css = "a[href='/contact'] span")
    private WebElement contactsModule;

    @FindAll(@FindBy(css = ".grid-header-cell__label"))
    private List<WebElement> accountFilters;

    public void access_customers_module(String moduleName) {
        Home home = new Home();
        home.hover_to_module(Driver.getProperty("customers"));
        switch (moduleName) {
            case "Accounts":
                Driver.captureHighlighted(accountsModule);
                accountsModule.click();
                Driver.sleep(2);
                break;
            case "Contacts":
                Driver.captureHighlighted(contactsModule);
                contactsModule.click();
                Driver.sleep(2);
                break;
        }
    }

    public void verify_account_filters() {
        Set<String> actualFilterNames = new LinkedHashSet<>();
        for (WebElement eachFilter : accountFilters) {
            if (!eachFilter.getText().isBlank()) {
                actualFilterNames.add(eachFilter.getText());
            }
        }
        Set<String> expectedFilterNames = new LinkedHashSet<>();
        String[] arr = Driver.getProperty("expectedFilters").split(",");
        for (String each : arr) {
            if (!each.isBlank()) {
                expectedFilterNames.add(each.strip().toUpperCase());
            }
        }
        assertTrue(actualFilterNames.containsAll(expectedFilterNames), "Filter names are not matching!");
    }
}
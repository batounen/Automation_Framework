package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class MarketingCampaigns extends Marketing {

    Marketing marketing;

    public MarketingCampaigns() {
//        marketing = new Marketing();
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "a[title='Filters']")
    private WebElement filterIcon;

    @FindBy(css = ".add-filter-button")
    private WebElement manageFilters;

    @FindAll(@FindBy(css = ".filter-container .filter-default-value"))
    private List<WebElement> campaignFilters;

    @FindAll(@FindBy(css = "[name='multiselect_0']"))
    private List<WebElement> campaignFilterCheckboxes;

    @FindBy(css = ".ui-multiselect-menu.ui-corner-all.select-filter-widget.dropdown-menu")
    private WebElement checkBoxArea;

    public void verifyFilterCheckboxes() {
        Driver.sleep(1);
        if (!filterIcon.getAttribute("class").endsWith("pressed")) {
            filterIcon.click();
        }
        manageFilters.click();
        for (WebElement eachBox : campaignFilterCheckboxes) {
            assertTrue(eachBox.isSelected());
        }
        Driver.captureHighlighted(checkBoxArea);
    }
}
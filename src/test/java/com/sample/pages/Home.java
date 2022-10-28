package com.sample.pages;

import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Home extends TestBase {

    public Home() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "div[class='pin-bar-empty'] a")
    private WebElement pinbarLink;

    @FindBy(css = "#main-menu")
    private WebElement allModuleBar;

    @FindAll(@FindBy(css = ".title-level-1"))
    private List<WebElement> actualModules;

    @FindBy(css = "li:nth-of-type(1) > .unclickable > .title.title-level-1")
    private WebElement dashboardsModule;

    @FindBy(css = "li:nth-of-type(2) > .unclickable > .title.title-level-1")
    private WebElement FleetModule;

    @FindBy(css = "li:nth-of-type(3) > .unclickable > .title.title-level-1")
    private WebElement CustomersModule;

    @FindBy(css = "li:nth-of-type(4) > .unclickable > .title.title-level-1")
    private WebElement SalesModule;

    @FindBy(css = "li:nth-of-type(5) > .unclickable > .title.title-level-1")
    private WebElement ActivitiesModule;

    @FindBy(css = "li:nth-of-type(6) > .unclickable > .title.title-level-1")
    private WebElement MarketingModule;

    @FindBy(css = "li:nth-of-type(7) > .unclickable > .title.title-level-1")
    private WebElement ReportsAndSegmentsModule;

    @FindBy(css = "li:nth-of-type(8) > .unclickable > .title.title-level-1")
    private WebElement SystemModule;

    @FindBy(css = ".fa-question-circle")
    private WebElement getHelp;

    @FindBy(css = ".fa-share-square")
    private WebElement shortcutsArrow;

    @FindBy(css = ".action-wrapper a[href='/contact']")
    private WebElement viewAll;

    @FindBy(css = "#user-menu")
    private WebElement userInfo;

    @FindBy(css = "[role] .no-hash")
    private WebElement logoutButton;

    public WebElement getGetHelp() {
        return getHelp;
    }

    public WebElement getViewAll() {
        return viewAll;
    }

    public WebElement getShortcutsArrow() {
        return shortcutsArrow;
    }

    public void verify_visibility_allModules(String userType) {
        List<String> driverModules = new ArrayList<>(Arrays.asList("Fleet", "Customers", "Activities", "System"));
        List<String> managerModules = new ArrayList<>(Arrays.asList("Dashboards", "Fleet", "Customers", "Sales", "Activities", "Marketing", "Reports & Segments", "System"));

        List<String> actualModuleNames = new ArrayList<>();
        for (WebElement actualModule : actualModules) {
            actualModuleNames.add(actualModule.getText());
        }

        if (userType.equalsIgnoreCase("manager")) {
            assertEquals(actualModuleNames, managerModules);
        } else if (userType.equalsIgnoreCase("driver")) {
            assertEquals(actualModuleNames, driverModules);
        }
    }

    public WebElement getAllModuleBar() {
        return allModuleBar;
    }

    public WebElement getPinbarLink() {
        return pinbarLink;
    }

    public void getHelp_button_test() {
        assertTrue(getHelp.isDisplayed());
        Driver.captureHighlighted(getHelp);
        getHelp.click();
        String mainWindowHandle = Driver.getDriver().getWindowHandle();
        for (String eachHandle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(eachHandle);
            if (Driver.getDriver().getTitle().equals("Welcome to Oro Documentation")) {
                assertEquals(Driver.getDriver().getTitle(), "Welcome to Oro Documentation");
            }
        }
        Driver.captureScreen();
        Driver.getDriver().close();
        Driver.getDriver().switchTo().window(mainWindowHandle);
    }

    public void hover_to_module(String moduleName) {
        for (WebElement eachModule : actualModules) {
            if (eachModule.getText().equals(moduleName)) {
                Actions actions = new Actions(Driver.getDriver());
                actions.moveToElement(eachModule).perform();
            }
        }
    }

    public void logout() {
//        login = new Login();
//        Driver.waitUntilClickable(userInfo, 10);
        userInfo.click();
//        Driver.waitUntilClickable(logoutButton, 10);
        logoutButton.click();
//        Driver.waitUntilClickable(login.getLoginButton(), 10);
        Driver.sleep(2);
    }
}
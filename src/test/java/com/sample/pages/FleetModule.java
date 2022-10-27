package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

public class FleetModule {

    public FleetModule() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "li:nth-of-type(2) > .unclickable > .title.title-level-1")
    public WebElement fleetModule;

    @FindBy(css = "a[href='entity/Extend_Entity_Carreservation'] > .title-level-2")
    private WebElement vehicles;

    @FindBy(css = "a[href='/entity/Extend_Entity_VehiclesOdometer'] > .title-level-2")
    private WebElement vehicleOdometer;

    @FindBy(css = "a[href='/entity/Extend_Entity_VehicleCosts'] > .title-level-2")
    private WebElement vehicleCosts;

    @FindBy(css = "a[href='/entity/Extend_Entity_VehicleContract'] > .title-level-2")
    private WebElement vehicleContracts;

    @FindBy(css = "a[href='/entity/Extend_Entity_VehicleFuelLogs'] > .title-level-2")
    private WebElement vehiclesFuelLogs;

    @FindBy(css = "a[href='entity/Extend_Entity_VehicleServicesLogs'] > .title-level-2")
    private WebElement vehicleServicesLogs;

    @FindBy(css = "a[href='/entity/Extend_Entity_VehiclesModel'] > .title-level-2")
    private WebElement vehiclesModel;

    @FindBy(css = "div:nth-of-type(2) > .message")
    private WebElement accessDeniedMsg;

    @FindAll(@FindBy(css = "span.grid-header-cell__label"))
    private List<WebElement> actualModuleNames;

    @FindAll(@FindBy (css = "td div div a[data-toggle='dropdown'].dropdown-toggle"))
    private List<WebElement> threeDotsToEditVehicleInfo;

    @FindBy(xpath = "/html/body/ul/li/ul//a[@title='View']")
    private WebElement viewIcon;

    @FindBy(xpath = "//body/ul/li/ul//a[@title='Edit']/i[@class='fa-pencil-square-o hide-text']")
    private WebElement editIcon;

    @FindBy(xpath = "//body/ul/li/ul//a[@title='Delete']/i[@class='fa-trash-o hide-text']")
    private WebElement deleteIcon;

    @FindAll(@FindBy(css = "input[tabindex='-1'][type='checkbox']"))
    private List<WebElement> allVehicleCheckboxes;

    @FindBy(css = ".dropdown-toggle > input[type='checkbox']")
    private WebElement checkAllBox;

    public void access_fleet_module(String moduleName) {
        Home home = new Home();
        home.hover_to_module(Driver.getProperty("fleet"));
        switch (moduleName) {
            case "Vehicles":
                Driver.captureHighlighted(vehicles);
                vehicles.click();
                break;
            case "Vehicle Odometer":
                Driver.captureHighlighted(vehicleOdometer);
                vehicleOdometer.click();
                break;
            case "Vehicle Costs":
                Driver.captureHighlighted(vehicleCosts);
                vehicleCosts.click();
                break;
            case "Vehicle Contracts":
                Driver.captureHighlighted(vehicleContracts);
                vehicleContracts.click();
                break;
            case "Vehicles Fuel Logs":
                Driver.captureHighlighted(vehiclesFuelLogs);
                vehiclesFuelLogs.click();
                break;
            case "Vehicle Services Logs":
                Driver.captureHighlighted(vehicleServicesLogs);
                vehicleServicesLogs.click();
                break;
            case "Vehicles Model":
                Driver.captureHighlighted(vehiclesModel);
                vehiclesModel.click();
                break;
        }
        Driver.sleep(2);
    }

    public void vehicle_contracts_access_test(String username) {
        FleetModule fleetModule = new FleetModule();
        if (username.startsWith("salesmanager") || username.startsWith("storemanager")) {
            fleetModule.access_fleet_module(Driver.getProperty("vehicleContract"));
            Driver.sleep(5);
            Driver.captureScreen();
            assertEquals(Driver.getDriver().getTitle(), Driver.getProperty("vehicleContracts"), "Title did not match!");
        } else if (username.startsWith("user")) {
            fleetModule.access_fleet_module(Driver.getProperty("vehicleContract"));
            Driver.sleep(5);
            Driver.captureHighlighted(accessDeniedMsg);
            assertTrue(accessDeniedMsg.isDisplayed());
        }
    }

    public void vehicle_model_page_columns_verification_test(Set<String> expectedColumnTitles) {
        Set<String> actualColumnNames = new LinkedHashSet<>();
        for (WebElement actualColumnName : actualModuleNames) {
            if (!actualColumnName.getText().isBlank()) {
                actualColumnNames.add(actualColumnName.getText());
            }
        }
        System.out.println(actualColumnNames);
        assertEquals(actualColumnNames, expectedColumnTitles, "Column Titles did not match!");
    }

    public void vehicle_info_edit_verification_test() {
        for (WebElement each : threeDotsToEditVehicleInfo) {
            Actions action = new Actions(Driver.getDriver());
            action.moveToElement(each).perform();
            assertTrue(viewIcon.isDisplayed());
            assertTrue(editIcon.isDisplayed());
            assertTrue(deleteIcon.isDisplayed());
        }
    }

    public void all_vehicle_checkboxes_unchecked_verification_test() {
        for (WebElement eachBox : allVehicleCheckboxes) {
            assertFalse(eachBox.isSelected());
        }
    }

    public void selectAllBox_verification_test() {
        Driver.captureHighlighted(checkAllBox);
        checkAllBox.click();
        Driver.captureScreen();
        for (WebElement eachBox : allVehicleCheckboxes) {
            assertTrue(eachBox.isSelected());
        }
    }

    public void each_vehicle_select_verification_test() {
        for (WebElement eachBox : allVehicleCheckboxes) {
            assertFalse(eachBox.isSelected());
            eachBox.click();
            assertTrue(eachBox.isSelected());
            eachBox.click();
            assertFalse(eachBox.isSelected());
        }
    }

}
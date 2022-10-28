package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class VehihcleCosts {

    public VehihcleCosts() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindAll(@FindBy(css = ".grid-header-cell__label"))
    private List<WebElement> tableColumns;

    @FindBy(css = ".grid-container .dropdown-toggle [type='checkbox']")
    private WebElement checkAllBox;

    @FindAll(@FindBy(css = "[data-role='select-row-cell']"))
    private List<WebElement> checkBoxes;

    public void verifyTableColumns() {
        Set<String> expectedColumnNames = new LinkedHashSet<>();
        for (String eachColumn : Driver.getProperty("expectedColumnNames").split(",")) {
            if (!eachColumn.isBlank()) {
                expectedColumnNames.add(eachColumn.strip());
            }
        }
        Set<String> actualColumnNames = new LinkedHashSet<>();
        for (WebElement eachColumn : tableColumns) {
            if (!eachColumn.getText().isBlank())
                actualColumnNames.add(eachColumn.getText());
        }
        assertTrue(actualColumnNames.containsAll(expectedColumnNames));
    }

    public void verifyCheckAllVehicleBox() {
        checkAllBox.click();
        for (WebElement checkBox : checkBoxes) {
            assertTrue(checkBox.isSelected());
        }
        Driver.captureScreen();
        checkAllBox.click();
        for (WebElement checkBox : checkBoxes) {
            assertFalse(checkBox.isSelected());
        }
    }

}
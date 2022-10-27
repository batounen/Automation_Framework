package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.support.PageFactory;

public class VehicleContracts {

    public VehicleContracts() {
        PageFactory.initElements(Driver.getDriver(), this);
    }





}
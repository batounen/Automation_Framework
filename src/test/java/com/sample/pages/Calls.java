package com.sample.pages;

import com.sample.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Calls {

    public Calls() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "a[title='Log call']")
    private WebElement logCallButton;

    public WebElement getLogCallButton() {
        return logCallButton;
    }
}
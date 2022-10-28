package com.sample.pages;

import com.sample.utils.Driver;
import com.sample.utils.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertEquals;

public class Login extends TestBase {

    public Login() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "#prependedInput")
    private WebElement usernameBox;

    @FindBy(css = "#prependedInput2")
    private WebElement passwordBox;

    @FindBy(css = ".custom-checkbox__text")
    private WebElement rememberMe;

    @FindBy(css = "a[href='/user/reset-request']")
    private WebElement forgotPassword;

    @FindBy(css = "#_submit")
    private WebElement loginButton;

    @FindBy(css = ".login-copyright")
    private WebElement copyrightText;

    @FindBy(css = ".alert-error div")
    private WebElement errorMsg;

    public WebElement getLoginButton() {
        return loginButton;
    }

    public void login_positive(String username, String password) {
        home = new Home();
        Driver.getDriver().get(Driver.getProperty("env"));
        if (Driver.verifyTitle(Driver.getProperty("login"))) {
            usernameBox.sendKeys(username);
            passwordBox.sendKeys(password);
            loginButton.click();
//            Driver.waitUntilClickable(home.getGetHelp(), 10);
            Driver.sleep(5);
            assertEquals(Driver.getDriver().getTitle(), Driver.getProperty("homepage"));
        } else {
            System.out.println("Incorrect Login Page detected!");
        }
    }

    public void login_negative(String userName, String password) {
        Driver.getDriver().get(Driver.getProperty("env"));
        if (Driver.verifyTitle(Driver.getProperty("login"))) {
            usernameBox.sendKeys(userName);
            passwordBox.sendKeys(password);
            loginButton.click();
            Driver.waitUntilVisible(errorMsg, 10);
            assertEquals(errorMsg.getText(), "Invalid user name or password.");
        } else {
            System.out.println("Incorrect Login Page detected!");
        }
    }

}
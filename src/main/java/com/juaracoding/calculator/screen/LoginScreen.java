package com.juaracoding.calculator.screen;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;


public class LoginScreen {
    private AndroidDriver driver;
    private By username = AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Username\"]");
    private By password = AppiumBy.xpath("//android.widget.EditText[@content-desc=\"test-Password\"]");
    private By button = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-LOGIN\"]");
    private By errorMessage = AppiumBy.xpath("//android.widget.TextView[@text=\"Username and password do not match any user in this service.\"]");

    public LoginScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        driver.findElement(this.username).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(this.password).sendKeys(password);
    }

    public void click() {
        driver.findElement(button).click();
    }

    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        click();
    }

    public void login() {
        login("standard_user", "secret_sauce");
    }

    public String getMessage() {
        return driver.findElement(errorMessage).getText();
    }
}


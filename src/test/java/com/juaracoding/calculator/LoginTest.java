package com.juaracoding.calculator;

import com.juaracoding.calculator.screen.InventoryScreen;
import com.juaracoding.calculator.screen.LoginScreen;
import com.juaracoding.calculator.utils.DriverUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class LoginTest {
    @Test(enabled = false)
    public void TC0001() throws MalformedURLException {
        DriverUtil driverUtil = new DriverUtil();
        LoginScreen loginScreen = new LoginScreen(driverUtil.getDriver());
        InventoryScreen inventoryScreen = new InventoryScreen(driverUtil.getDriver());

        loginScreen.login("standard_user", "secret_sauce");

        String actual = inventoryScreen.getTitle();
        String expected = "PRODUCTS";

        Assert.assertEquals(actual, expected);

        driverUtil.quitApp();
    }

    @Test()
    public void TC0002() throws MalformedURLException {
        DriverUtil driverUtil = new DriverUtil();
        LoginScreen loginScreen = new LoginScreen(driverUtil.getDriver());

        loginScreen.login("invalid_username", "secret_sauce");

        String actual = loginScreen.getMessage();
        String expected = "Username and password do not match any user in this service.";
        Assert.assertEquals(actual, expected);

        driverUtil.quitApp();
    }

    @Test(enabled = false)
    public void TC0003() throws MalformedURLException {
        DriverUtil driverUtil = new DriverUtil();
        LoginScreen loginScreen = new LoginScreen(driverUtil.getDriver());

        loginScreen.login("standard_user", "invalid_password");

        String actual = loginScreen.getMessage();
        String expected = "Username and password do not match any user in this service.";

        Assert.assertEquals(actual, expected);

        driverUtil.quitApp();
    }

    @Test(enabled = false)
    public void TC0004() throws MalformedURLException {
        DriverUtil driverUtil = new DriverUtil();
        LoginScreen loginScreen = new LoginScreen(driverUtil.getDriver());

        loginScreen.login("", "");

        String actual = loginScreen.getMessage();
        String expected = "Username is required";

        Assert.assertEquals(actual, expected);

        driverUtil.quitApp();
    }

}

package com.juaracoding.calculator;

import com.juaracoding.calculator.component.HeaderComponent;
import com.juaracoding.calculator.screen.InventoryScreen;
import com.juaracoding.calculator.screen.LoginScreen;
import com.juaracoding.calculator.utils.DragPositionUtil;
import com.juaracoding.calculator.utils.DriverUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.net.MalformedURLException;

public class InventoryTest {

    @Test(enabled = false)
    public void TC0007() throws MalformedURLException {
        DriverUtil driverUtil = new DriverUtil();
        LoginScreen loginScreen = new LoginScreen(driverUtil.getDriver());
        InventoryScreen inventoryScreen = new InventoryScreen(driverUtil.getDriver());
        // Pre condition (sebelum melakukan test)
        loginScreen.login();

        int actual = inventoryScreen.getTotalProduct();
        inventoryScreen.scrollDown(3);
        actual += inventoryScreen.getTotalProduct();

        int expected = 6;

        Assert.assertEquals(actual, expected);

        driverUtil.quitApp();
    }

    @Test(enabled = false)
    public void TC0008() throws MalformedURLException {
        DriverUtil driverUtil = new DriverUtil();
        HeaderComponent headerComponent = new HeaderComponent(driverUtil.getDriver());
        LoginScreen loginScreen = new LoginScreen(driverUtil.getDriver());

        InventoryScreen inventoryScreen = new InventoryScreen(driverUtil.getDriver(), headerComponent);

        // Pre condition (sebelum melakukan test)
        loginScreen.login();

        inventoryScreen.drag(new DragPositionUtil(200, 100));

        String expected = "1";
        String actual = inventoryScreen.getTotalCart();
        Assert.assertEquals(actual, expected);
        driverUtil.quitApp();
    }

    //zoom in
    @Test(enabled = true)
    public void TC0009() throws MalformedURLException, InterruptedException {
        DriverUtil driverUtil = new DriverUtil();
        HeaderComponent headerComponent = new HeaderComponent(driverUtil.getDriver());
        LoginScreen loginScreen = new LoginScreen(driverUtil.getDriver());

        InventoryScreen inventoryScreen = new InventoryScreen(driverUtil.getDriver(), headerComponent);

        // Pre condition (sebelum melakukan test)
        loginScreen.login();

        inventoryScreen.tapItem();
        inventoryScreen.pinchOut();

        int actualWidth = inventoryScreen.getTestItemWidth();
        System.out.println(actualWidth);
        int expectedWidth = 948;

        Assert.assertEquals(actualWidth, expectedWidth, "Width tidak sesuai");
    }
}

package com.juaracoding.calculator;
import java.time.Duration;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AppTest {
    AndroidDriver driver;
    DesiredCapabilities capabilities;

    @BeforeClass
    public void setup() throws MalformedURLException {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "emulator-5556");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "com.google.android.calculator");
        capabilities.setCapability("appium:appActivity", "com.android.calculator2.Calculator");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.hideKeyboard();
    }

    @Test
    public void test() {
        WebElement buttonClearElement = driver.findElement(AppiumBy.id("com.google.android.calculator:id/clr"));
        buttonClearElement.click();

        WebElement button5 = driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_5"));
        button5.click();

        WebElement buttonPlus = driver.findElement(AppiumBy.id("com.google.android.calculator:id/op_add"));
        buttonPlus.click();

        WebElement button9 = driver.findElement(AppiumBy.id("com.google.android.calculator:id/digit_9"));
        button9.click();

        WebElement buttonEq = driver.findElement(AppiumBy.id("com.google.android.calculator:id/eq"));
        buttonEq.click();

        String actual = driver.findElement(AppiumBy.id("com.google.android.calculator:id/result_final")).getText();
        String expected = "14";

        Assert.assertEquals(actual, expected);

    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }
}
package com.juaracoding.calculator.screen;

import com.juaracoding.calculator.component.HeaderComponent;
import com.juaracoding.calculator.utils.DragPositionUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.awt.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InventoryScreen {
    private AndroidDriver driver;
    private By header = AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]");
    private By productCards = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Item\"]");
    private By scrollView = AppiumBy.xpath("//android.widget.ScrollView[@content-desc=\"test-PRODUCTS\"]");
    private By buttonDrag = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-Drag Handle\"])[1]");
    private By itemSelect = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]/android.view.ViewGroup/android.widget.ImageView");
    private By testItem = AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-Image Container\"]/android.widget.ImageView");

    private HeaderComponent headerComponent;

    public InventoryScreen(AndroidDriver driver, HeaderComponent headerComponent) {
        this.driver = driver;
        this.headerComponent = headerComponent;
    }

    public InventoryScreen(AndroidDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(header).getText();
    }

    public int getTotalProduct() {
        return driver.findElements(productCards).size();
    }

    public String getTotalCart() {
        return driver.findElement(headerComponent.getTotalByOne()).getText();
    }

    public int getTestItemWidth() {
        return driver.findElement(testItem).getSize().getWidth();
    }

    public int getTestItemHeight(){
        return driver.findElement(testItem).getSize().getHeight();
    }

    public void scrollDown(double percent) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("elementId", driver.findElement(scrollView));
        params.put("direction", "down");
        params.put("percent", percent);
        params.put("speed", 1000);

        js.executeScript("mobile: scrollGesture", params);
    }

    public void drag(DragPositionUtil dragPositionUtil) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<String, Object>();

        int x = driver.findElement(scrollView).getLocation().getX() + dragPositionUtil.getCustomX();
        int y = driver.findElement(scrollView).getLocation().getY() - dragPositionUtil.getCustomY();

        params.put("elementId", driver.findElement(buttonDrag));
        params.put("endX", x);
        params.put("endY", y);
        params.put("speed", 5000);

        js.executeScript("mobile: dragGesture", params);

    }

    public void tapItem(){
        driver.findElement(itemSelect).click();
    }

    public void pinchOut() throws InterruptedException {
        Thread.sleep(1000);
        // koordinat awal (kiri dan kanan dalam bounds)
        int startX1 = 350; // jari kiri
        int startY1 = 1100;

        int startX2 = 700; // jari kanan
        int startY2 = 1100;

        // koordinat akhir (keluar lebih jauh ke kiri dan kanan)
        int endX1 = 50;   // geser lebih kiri
        int endY1 = 1100;

        int endX2 = 1000;  // geser lebih kanan
        int endY2 = 1100;

        // pointer untuk jari kiri
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence seq1 = new Sequence(finger1, 1);
        seq1.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX1, startY1));
        seq1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        seq1.addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX1, endY1));
        seq1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // pointer untuk jari kanan
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence seq2 = new Sequence(finger2, 1);
        seq2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX2, startY2));
        seq2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        seq2.addAction(finger2.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX2, endY2));
        seq2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(seq1, seq2));
    }

}

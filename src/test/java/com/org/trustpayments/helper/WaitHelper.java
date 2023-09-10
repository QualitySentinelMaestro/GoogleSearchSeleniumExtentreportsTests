package com.org.trustpayments.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitHelper {


    static WebDriverWait wait;

    /**
     * This is ImplicitWait method
     * @param waitTimeSeconds
     *
     */
    public static void implicitWait(WebDriver driver, int waitTimeSeconds)
    {
        driver.manage().timeouts().implicitlyWait(waitTimeSeconds, TimeUnit.SECONDS);
    }

    public static WebElement waitUntilVisible(WebDriver driver ,WebElement elt, int waitTimeSeconds) {
        try {
            WebElement element = null;
            wait = new WebDriverWait(driver, waitTimeSeconds);
            element = wait.until(ExpectedConditions.visibilityOf(elt));
            System.out.println("text coming on screen to be visible is  : " + element.getText());
            return element;
        } catch (Exception e) {
            return null;
        }

    }


}

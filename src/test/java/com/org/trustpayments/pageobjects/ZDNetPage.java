package com.org.trustpayments.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZDNetPage {

    private final WebDriver driver;


    public ZDNetPage(WebDriver driver) {
        this.driver = driver;

    }



    public String getURL() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }
}

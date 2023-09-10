package com.org.trustpayments.pageobjects;

// PageObjectFactory.java
import org.openqa.selenium.WebDriver;

public class PageObjectFactory {
    private WebDriver driver;

    public PageObjectFactory(WebDriver driver) {
        this.driver = driver;
    }

    public GoogleSearchPage createGoogleSearchPage() {
        return new GoogleSearchPage(driver);
    }

    public ZDNetPage createZDNetPage() {
        return new ZDNetPage(driver);
    }

    public AWSLandingPage createAWSLandingPage() {
        return new AWSLandingPage(driver);
    }
}
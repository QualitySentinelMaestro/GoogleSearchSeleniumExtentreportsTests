/*
package com.org.trustpayments.stepdefinitions;

import com.org.trustpayments.common.CommonConstants;
import com.org.trustpayments.enums.Browsers;
import com.org.trustpayments.pageobjects.PageObjectFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.logging.Logger;

public class HooksSteps {

    @Getter
    @Setter
    private  WebDriver driver;

    @Getter
    @Setter
    private PageObjectFactory pageFactory;

    private static final Logger LOGGER = Logger.getLogger(HooksSteps.class.getName());
    private Scenario scenario;

    @Before
    public void setup(Scenario scenario) throws Exception {
        this.scenario = scenario;
        driver = createWebDriver(CommonConstants.BROWSER);
        pageFactory = new PageObjectFactory(driver);
    }

    public WebDriver createWebDriver(String browser) throws Exception {
        Browsers enumBrowserVal = Browsers.valueOf(browser.toUpperCase());
        try {
            switch (enumBrowserVal) {
                case CHROME:
                    String projectRoot = System.getProperty("user.dir");
                    String chromeDriverPath = projectRoot + "/Drivers/chromedriver";
                    String chromeBinPath = projectRoot + "/ChromeBin/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing";
                    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                    ChromeOptions options = new ChromeOptions();
                    options.setBinary(chromeBinPath);
                    options.addArguments("--start-maximized");

                    return new ChromeDriver(options);

                default:
                    throw new Exception("Driver not Found: " + browser);

            }
        } catch (Exception e) {
            throw e;
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
           // driver.quit();
        }
    }
}*/

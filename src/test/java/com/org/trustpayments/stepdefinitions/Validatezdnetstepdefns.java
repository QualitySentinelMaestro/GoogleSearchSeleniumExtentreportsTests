package com.org.trustpayments.stepdefinitions;


import com.org.trustpayments.common.CommonConstants;
import com.org.trustpayments.common.CommonUtility;
import com.org.trustpayments.helper.WaitHelper;
import com.org.trustpayments.pageobjects.AWSLandingPage;
import com.org.trustpayments.pageobjects.GoogleSearchPage;
import com.org.trustpayments.pageobjects.PageObjectFactory;
import com.org.trustpayments.pageobjects.ZDNetPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validatezdnetstepdefns
{

    private static final Logger logger = Logger.getLogger(Validatezdnetstepdefns.class.getName());
    private Scenario scenario;
    private WebDriver driver;
    private WebDriverWait wait;
    private PageObjectFactory pageFactory;
    private ZDNetPage zdNetPage;

    @Before
    public void setUp(Scenario scenario) {
        try {
            this.scenario = scenario ;
            System.out.println("**Execution of Feature File Started**");
            System.out.println("Executing Scenario:" + scenario.getName());
            System.out.println(scenario.getClass());
            CommonUtility.createFolders();
            System.setProperty("webdriver.chrome.driver", CommonConstants.chromeDriverPath);
            ChromeOptions options = new ChromeOptions();
            options.setBinary(CommonConstants.chromeBinPath);
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 20);
            pageFactory = new PageObjectFactory(driver);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during setup.", e);
        }
    }

    @Given("User launches the Browser")
    public void userLaunchesTheBrowser() {
       logger.info("User Launching the Browser and Commencing his search");
    }

    @When("User navigates to Google {string}")
    public void userNavigatesToGoogle(String googleSiteLinkText) {
        driver.get(googleSiteLinkText);
        logger.info("User Launching the Browser and navigating to Google");
        WaitHelper.implicitWait(driver,CommonConstants.WAITSECONDS);
    }

    @And("User searches for the following cloud details {string}")
    public void userSearchesForTheFollowingCloudDetails(String searchPhrase) {
        pageFactory.createGoogleSearchPage().performSearch(searchPhrase);
        logger.info("User Launched the Google and searching for list of cloud providers");
        WaitHelper.implicitWait(driver,CommonConstants.WAITSECONDS);

    }

    @And("User clicks on the ZDNet Link {string}")
    public void userClicksOnTheZDNetLink(String linkText) {

        pageFactory.createGoogleSearchPage().clickLink(linkText);
        WaitHelper.implicitWait(driver,CommonConstants.WAITSECONDS);


    }

    @Then("User should be on a new tab and the list of Cloud Providers should be displayed")
    public void userShouldBeOnANewTabAndTheListOfCloudProvidersShouldBeDisplayed() {
        List<WebElement> cloudProvidersList = driver.findElements(By.tagName("a"));
        boolean awsFound = false;
        boolean azureFound = false;
        boolean googleCloudFound = false;

        for (WebElement providerElement : cloudProvidersList) {
            String providerName = providerElement.getAttribute("href");
            System.out.println(providerName);
            if (providerName == null || providerName.isEmpty()) {
                System.out.println("No Elements found.");
            }
            else {
                if (providerName.contains(CommonConstants.AMAZON)) {
                    awsFound = true;
                } else if (providerName.contains(CommonConstants.AZURE)) {
                    azureFound = true;
                } else if (providerName.contains(CommonConstants.GOOGLE)) {
                    googleCloudFound = true;
                }
            }
        }

        // Validate that all three cloud providers are listed on the zdnet.com page
        if (awsFound && azureFound && googleCloudFound) {
            System.out.println("All three cloud providers are listed on the zdnet.com page.");
        } else {
            System.out.println("One or more cloud providers are missing from the zdnet.com page.");
        }

        //WebElement viewAWSButton = driver.findElement(By.xpath("//div[contains(@class, 'g-text-xxsmall-bold') and text()='Amazon Web Services']/following::div[2]/span"));

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @And("User should click the Amazon URL to see their services.")
    public void userShouldClickTheAmazonURLToSeeTheirServices() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement viewAWSButton = driver.findElement(By.xpath("//div[contains(@class, 'g-text-xxsmall-bold') and text()='Amazon Web Services']/following::div[2]"));
        viewAWSButton.click();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


    }

    @And("User should see the following text on their Amazon Website")
    public void userShouldSeeTheFollowingTextOnTheirAmazonWebsite() {
        // Verify that a new tab has been opened in the same web browser
        String currentWindowHandle = driver.getWindowHandle();
        boolean newTabOpened = false;

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindowHandle)) {
                driver.switchTo().window(windowHandle);
                newTabOpened = true;
                break;
            }
        }

        if (newTabOpened) {
            System.out.println("A new tab has been opened in the same web browser.");
        } else {
            System.out.println("A new tab has not been opened.");
        }

        // Validate the new tab URL is "https://aws.amazon.com/what-isaws/"
        String expectedURL = " https://aws.amazon.com/what-is-aws/?tag=zd-buy-button-20&ascsubtag=665483ddae6941ad8b9e54cfa1a36e9d%7C7d6e5a56-c060-49d5-b3b7-31a5cdc6dbb5%7Cdtp";

        if (driver.getCurrentUrl().equals(expectedURL)) {
            System.out.println("The new tab URL is correct: " + expectedURL);
        } else {
            System.out.println("The new tab URL is incorrect: " + driver.getCurrentUrl());
        }

        // Validate the landing page displays the words "Cloud computing with AWS"
        String expectedText = "Cloud computing with AWS";
        if (driver.getPageSource().contains(expectedText)) {
            System.out.println("The landing page displays the words: " + expectedText);
        } else {
            System.out.println("The landing page does not display the words: " + expectedText);
        }
    }


    @After
    public void tearDown() throws IOException {

        String fileName = scenario.getName();
        System.out.println("Finished Scenario:" + scenario.getName());
        logger.info("Finished Scenario:" + scenario.getName());

        if (scenario.isFailed()) {
            CommonUtility.takeFailedScreenshot(driver, fileName);
        } else {
            CommonUtility.takeScreenshot(driver ,fileName);
        }
    }

}

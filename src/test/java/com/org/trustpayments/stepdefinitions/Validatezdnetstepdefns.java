
/*******************************************************************************************
 *  This is a Stepdefinition class which has all the matching stepdefinition implementation
 *  for all the steps in the  feature file and validates the ZDNet Website and it's
 *  page Contents
 *
 *  @author Lakshmi Ay
 *********************************************************************************************/


package com.org.trustpayments.stepdefinitions;

import com.org.trustpayments.common.CommonConstants;
import com.org.trustpayments.common.CommonUtility;
import com.org.trustpayments.enums.Browsers;
import com.org.trustpayments.helper.WaitHelper;
import com.org.trustpayments.pageobjects.PageObjectFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Validatezdnetstepdefns {

    private static final Logger logger = Logger.getLogger(Validatezdnetstepdefns.class.getName());
    private PageObjectFactory pageFactory;
    private WebDriver driver;
    private Scenario scenario;
    private String browser;

    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        initEnv();

    }

    @Given("User launches the Browser")
    public void userLaunchesTheBrowser() {
        logger.info("User Launching the Browser and Commencing his search");
    }

    @When("User navigates to Google {string}")
    public void userNavigatesToGoogle(String googleSiteLinkText) {
        driver.get(googleSiteLinkText);
        logger.info("User Launching the Browser and navigating to Google");
        WaitHelper.implicitWait(driver, CommonConstants.WAITSECONDS);
    }

    @And("User searches for the following cloud details {string}")
    public void userSearchesForTheFollowingCloudDetails(String searchPhrase) {

        pageFactory.getGoogleSearchPage().performSearch(searchPhrase);
        logger.info("User Launched the Google and searching for list of cloud providers");
        WaitHelper.implicitWait(driver, CommonConstants.WAITSECONDS);

    }

    @And("User clicks on the ZDNet Link {string}")
    public void userClicksOnTheZDNetLink(String linkText) {

        pageFactory.getGoogleSearchPage().clickLink(linkText);
        WaitHelper.implicitWait(driver, CommonConstants.WAITSECONDS);


    }

    @Then("User should be on a new tab and the list of Cloud Providers should be displayed")
    public void userShouldBeOnANewTabAndTheListOfCloudProvidersShouldBeDisplayed() {

        List<WebElement> cloudProvidersList = pageFactory.getZDNetPage().getCloudProvidersList();
        pageFactory.getZDNetPage().validateCloudProviderList(cloudProvidersList);

    }

    @And("User should click the Amazon URL to see their services.")
    public void userShouldClickTheAmazonURLToSeeTheirServices() {

        WaitHelper.implicitWait(driver, CommonConstants.WAITSECONDS);
        pageFactory.getZDNetPage().navigateAWSPage();

    }

    @And("User should see the following text on their Amazon Website")
    public void userShouldSeeTheFollowingTextOnTheirAmazonWebsite(DataTable expectedAWSData) {
        for (Map<Object, Object> expAWS : expectedAWSData.asMaps(String.class, String.class)) {
            String expectedPageSrc = (String) expAWS.get("Expected Text");
            String expectedPageUrl = (String) expAWS.get("Expected URL");

            pageFactory.getAwsLandingPage().switchToNewTab(expectedPageUrl);
            pageFactory.getAwsLandingPage().validateAwsLandingPage(expectedPageSrc);


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
            CommonUtility.takeScreenshot(driver, fileName);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    public void initEnv() {

        try {
            logger.info("**Execution of Feature File Started**");
            logger.info("Executing Scenario:" + scenario.getName());
            CommonUtility.createFolders();
            browser = CommonUtility.browsertype;
            createWebdriver();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error occurred during setup.", e);
        }

    }

    public void createWebdriver() throws Exception {

        driver = null;
        Browsers enumBrowserVal = Browsers.valueOf(browser.toUpperCase());

        try {
            switch (enumBrowserVal) {
                case CHROME:
                    System.setProperty("webdriver.chrome.driver", CommonConstants.chromeDriverPath);
                    ChromeOptions options = new ChromeOptions();
                    options.setBinary(CommonConstants.chromeBinPath);
                    options.addArguments("--start-maximized");
                    driver = new ChromeDriver(options);
                    pageFactory = new PageObjectFactory(driver);
                    break;

                case FIREFOX:
                    System.setProperty("webdriver.gecko.driver", CommonConstants.firefoxDriverPath);
                    FirefoxOptions opt = new FirefoxOptions();
                    opt.setBinary(CommonConstants.firefoxBinPath);
                    opt.addArguments("--start-maximized");
                    driver = new FirefoxDriver(opt);
                    pageFactory = new PageObjectFactory(driver);
                    break;


                default:
                    throw new Exception("Driver not Found: " + browser);

            }
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();

        } catch (Exception e) {
            logger.info(e.getMessage());
            throw e;
        }

    }

}

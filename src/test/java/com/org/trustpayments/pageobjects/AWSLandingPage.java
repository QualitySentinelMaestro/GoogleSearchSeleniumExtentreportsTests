package com.org.trustpayments.pageobjects;
/**
 * This is the page object class for AWS Website home page
 */

import com.org.trustpayments.helper.JavascriptHelper;
import com.org.trustpayments.helper.WaitHelper;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

public class AWSLandingPage {

    private final WebDriver driver;
    private static final Logger logger = Logger.getLogger(AWSLandingPage.class.getName());

    @FindBy(how = How.ID, using = "Cloud_computing_with_AWS")
    public WebElement cloudComputeAWSelt;


    public AWSLandingPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void switchToNewTab(String expPageUrl) {
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

            WaitHelper.implicitWait(driver, 20);
            Assert.assertTrue(driver.getCurrentUrl().contains(expPageUrl));

            logger.info("A new tab has been opened in the same web browser.");
        } else {
            logger.info("A new tab has not been opened.");
        }

    }

    public void validateAwsLandingPage(String expectedPagesrc) {

        WaitHelper.implicitWait(driver, 20);
        Assert.assertTrue(driver.getPageSource().contains(expectedPagesrc));

        WebElement cloudComputeAWSelement = WaitHelper.clickWhenReady(driver, cloudComputeAWSelt, 10);
        Assert.assertTrue(cloudComputeAWSelement.isDisplayed());
        if (cloudComputeAWSelement.isDisplayed()) {
            JavascriptHelper.flash(driver, cloudComputeAWSelement);
            cloudComputeAWSelement.click();
        }
    }

}

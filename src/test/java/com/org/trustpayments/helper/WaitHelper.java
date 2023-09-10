/***********************************************************************************************************************************
 * This is a helper class which has all Wait methods which can be used on webelements on the page.
 * @author : Lakshmi Ay
 ***********************************************************************************************************************************************************************************/

package com.org.trustpayments.helper;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class WaitHelper {

    private static final Logger logger = Logger.getLogger(WaitHelper.class.getName());
    static WebDriverWait wait;


    public static void implicitWait(WebDriver driver, int waitTimeSeconds) {
        driver.manage().timeouts().implicitlyWait(waitTimeSeconds, TimeUnit.SECONDS);
    }

    public static void pageloadWait(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public static WebElement waitUntilVisible(WebDriver driver, WebElement elt, int waitTimeSeconds) {
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

    public static WebElement clickWhenReady(WebDriver driver, WebElement elt, int waitTimeSeconds) {
        try {
            WebElement element = null;
            wait = new WebDriverWait(driver, waitTimeSeconds);
            element = wait.until(ExpectedConditions.elementToBeClickable(elt));
            System.out.println("text coming on screen to be clicked  is : " + element.getText());

            return element;
        } catch (Exception e) {
            return null;
        }

    }

    public static void simpleWait(WebDriver driver, int waitTimeSeconds) {

        try {
            driver.manage().timeouts().implicitlyWait(waitTimeSeconds, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(driver, waitTimeSeconds);
            ExpectedCondition<WebElement> ec = ExpectedConditions.visibilityOfElementLocated(By.id("invalid_id"));
            wait.until(ec);

        } catch (TimeoutException ex) {
            logger.info("Exception Occured:" + ex.getMessage());

        } catch (UnhandledAlertException uae) {
            logger.info("Exception Occured:" + uae.getMessage());
        }

    }


}

/***********************************************************************************************************************************
 * This is a helper class which has all javascript methods which can be used on webelements on the page.
 * @author : Lakshmi Ay
 ***********************************************************************************************************************************************************************************/

package com.org.trustpayments.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class JavascriptHelper {
    JavascriptExecutor js;

    public Object executeScript(WebDriver driver, String script) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        return exe.executeScript(script);
    }

    public static void flash(WebDriver driver, WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        String bgcolor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 15; i++) {
            changeColor(driver, "rgb(0,255,0)", element);//1  //Highlight Element 10 times
            changeColor(driver, bgcolor, element);//2 // Then move back to original colour
        }
    }

    public static void changeColor(WebDriver driver, String color, WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
    }

    public static void scrollAndClick(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebElement secondPageLink = driver.findElement(By.xpath("//a[@aria-label='Page 2']"));
        secondPageLink.click();

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        boolean zdnetFound = false;
        while (!zdnetFound) {
            List<WebElement> searchResults = driver.findElements(By.tagName("a"));
            for (WebElement result : searchResults) {
                String hrefValue = result.getAttribute("href");
                if (hrefValue == null || hrefValue.isEmpty()) {
                    System.out.println("Anchor element does not have a valid href attribute.");
                } else {
                    if (result.getAttribute("href").contains("zdnet.com")) {
                        zdnetFound = true;
                        result.click();
                        break;
                    }
                }
            }

            if (!zdnetFound) {
                // Scroll to load more results
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                jsExecutor.executeScript("window.scrollBy(0, 500);");
            }
        }


    }

}

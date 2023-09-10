package com.org.trustpayments.pageobjects;
/**
 * This is the Pageobject fcatory class to instatntiate all the
 * Page Objects
 * @author Lakshmi
 *
 */

import lombok.Data;
import org.openqa.selenium.WebDriver;

@Data
public class PageObjectFactory {


     private WebDriver driver;
     GoogleSearchPage googleSearchPage;
     ZDNetPage zDNetPage;
     AWSLandingPage awsLandingPage;


    public PageObjectFactory(WebDriver driver) {
        this.driver = driver;
        googleSearchPage = new GoogleSearchPage(driver);
        zDNetPage = new ZDNetPage(driver);
        awsLandingPage = new AWSLandingPage(driver);

    }


}
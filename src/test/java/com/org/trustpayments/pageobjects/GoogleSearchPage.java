package com.org.trustpayments.pageobjects;
/**
 * This is the page object class for Google Website Search page
 */

import com.org.trustpayments.helper.WaitHelper;
import com.org.trustpayments.helper.JavascriptHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.util.logging.Logger;


public class GoogleSearchPage {

    private final WebDriver driver;
    private static final Logger LOGGER = Logger.getLogger(GoogleSearchPage.class.getName());

    @FindBy(how = How.NAME, using = "q")
    public WebElement searchBoxLocatorelt;

    public GoogleSearchPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public void performSearch(String searchPhrase) {

        WebElement searchBoxLocatorelement = WaitHelper.waitUntilVisible(driver ,searchBoxLocatorelt, 5);
        assert searchBoxLocatorelement != null;
        JavascriptHelper.flash(driver ,searchBoxLocatorelement);
        LOGGER.info("Searching for the list of cloud providers");
        searchBoxLocatorelt.clear();
        searchBoxLocatorelt.sendKeys(searchPhrase);
        searchBoxLocatorelt.submit();
    }

    public void clickLink(String linkText) {

        LOGGER.info("Clicking and navigating to Zdnet Link");
        JavascriptHelper.scrollAndClick(driver);

    }
}
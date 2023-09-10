package com.org.trustpayments.pageobjects;

import com.org.trustpayments.common.CommonConstants;
import com.org.trustpayments.helper.JavascriptHelper;
import com.org.trustpayments.helper.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

public class ZDNetPage {

    private final WebDriver driver;
    private static final Logger logger = Logger.getLogger(ZDNetPage.class.getName());


    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'g-text-xxsmall-bold') and text()='Amazon Web Services']")
    public WebElement clickAwsButtonElt;

    @FindBy(how = How.XPATH, using = "//a[@title='ZDNET']")
    public WebElement zdnetLogoElt;

    public ZDNetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    public List<WebElement> getCloudProvidersList()
    {

        WaitHelper.simpleWait(driver,2);
        WebElement zdnetLogoElement = WaitHelper.clickWhenReady(driver,zdnetLogoElt,20) ;
        assert zdnetLogoElement != null;
        JavascriptHelper.flash(driver, zdnetLogoElement);


        List<WebElement> cloudProvidersList = driver.findElements(By.tagName("a"));
        for (WebElement providerElement : cloudProvidersList) {
            String providerName = providerElement.getAttribute("href");
            System.out.println(providerName);}
        return  cloudProvidersList ;
    }


    public void validateCloudProviderList(List<WebElement> cloudProvidersList )
    {
        HashMap<String, Boolean> cloudProvidersMap = new HashMap<>();
        cloudProvidersMap.put(CommonConstants.AMAZON, false);
        cloudProvidersMap.put(CommonConstants.AZURE, false);
        cloudProvidersMap.put(CommonConstants.GOOGLE, false);

        cloudProvidersList.stream()
                .map(providerElement -> providerElement.getAttribute("href"))
                .filter(providerName -> providerName != null && !providerName.isEmpty())
                .forEach(providerName -> {
                    cloudProvidersMap.keySet().stream()
                            .filter(provider -> providerName.contains(provider))
                            .findFirst()
                            .ifPresent(provider -> cloudProvidersMap.put(provider, true));
                });

        // After the loop,  access the boolean flags for each cloud provider.
        boolean awsFound = cloudProvidersMap.get(CommonConstants.AMAZON);
        boolean azureFound = cloudProvidersMap.get(CommonConstants.AZURE);
        boolean googleCloudFound = cloudProvidersMap.get(CommonConstants.GOOGLE);

        /** Validate that all three cloud providers are listed on the zdnet.com page**/


        if (awsFound && azureFound && googleCloudFound) {

            logger.info("All three cloud providers are listed on the zdnet.com page.");

        } else {
            logger.info("One or more cloud providers are missing from the zdnet.com page.");
        }

    }

    public void navigateAWSPage() {

        WaitHelper.simpleWait(driver,5);
        WebElement viewAWSButton  = WaitHelper.clickWhenReady(driver,clickAwsButtonElt,20) ;
        assert viewAWSButton != null;
        viewAWSButton.click();
        WaitHelper.simpleWait(driver,5);

    }

}

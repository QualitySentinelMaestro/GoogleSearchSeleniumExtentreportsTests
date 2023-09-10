package com.org.trustpayments.testrunners;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to  feature files
        glue = "com.org.trustpayments.stepdefinitions", // Package name where step definitions are located
        plugin = { "pretty", "json:target/cucumberreport.json" ,"pretty",
                "html:target/site/cucumber-pretty/cucumberreport.html",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        dryRun = false,
        tags = "@ValidateZDNet"
)
public class TestRunner {

}



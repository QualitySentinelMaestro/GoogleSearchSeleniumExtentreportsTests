package com.org.trustpayments.testrunners;


import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to your feature files
        glue = "com.org.trustpayments.stepdefinitions", // Package name where your step definitions are located
        plugin = { "pretty", "html:target/site/cucumber-pretty/cucumberreport.html"},

        monochrome = true,
        dryRun = false,
        tags = "@sample"
)
public class TestRunner {

    }



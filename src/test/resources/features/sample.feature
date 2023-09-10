Feature: Feature file to validate the ZDNet Page

  @sample
  Scenario Outline: Navigate to Google and Validate ZDNet Page and their page contents

    Given User launches the Browser
    When User navigates to Google "<GWebsite>"
    And User searches for the following cloud details "<SearchPhrase>"
    And User clicks on the ZDNet Link "<ZDNetURL>"
    Then User should be on a new tab and the list of Cloud Providers should be displayed
    And User should click the Amazon URL to see their services.
    And User should see the following text on their Amazon Website

    Examples:
      | GWebsite                  | SearchPhrase            |   ZDNetURL        |
      | https://www.google.co.uk  | list of cloud platforms |    ZDNet.com      |
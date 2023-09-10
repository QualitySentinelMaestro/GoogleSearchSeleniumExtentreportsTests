Feature: Feature file to validate the ZDNet Page

  @ZDNet
  Scenario Outline: Navigate to Google and Validate ZDNet Page and their page contents

    Given User launches the Browser
    When User navigates to Google "<GWebsite>"
    And User searches for the following cloud details "<SearchPhrase>"
    And User clicks on the ZDNet Link "<ZDNetURL>"
    Then User should be on a new tab and the list of Cloud Providers should be displayed
    And User should click the Amazon URL to see their services.
      | Expected URL                                                                                                                                     |
      | https://aws.amazon.com/what-is-aws/?tag=zd-buy-button-20&ascsubtag=665483ddae6941ad8b9e54cfa1a36e9d%7C7d6e5a56-c060-49d5-b3b7-31a5cdc6dbb5%7Cdtp |
    And User should see the following text on their Amazon Website
      | Expected Text   |
      | Cloud computing with AWS |

    Examples:
      | GWebsite                  | SearchPhrase            |   ZDNetURL        |
      | https://www.google.co.uk  | list of cloud platforms |    ZDNet.com      |
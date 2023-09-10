/*********************************************************************************************************************************************************************************
 * @author : Lakshmi Ay
 * This is the class which contains all the common constants required for the tests.
 ***********************************************************************************************************************************************************************************/

package com.org.trustpayments.common;

public class CommonConstants {
    public final static String SCREEN_SHOTS_BASE_PATH = "screenshots";
    public final static String PASSED_METHODS_PATH = "passedmethods";
    public final static String FAILED_METHODS_PATH = "failedmethods";

    public final static String projectRoot = System.getProperty("user.dir");

    public final static String chromeDriverPath = projectRoot + "/Drivers/chromedriver";
    public final static String chromeBinPath = projectRoot + "/ChromeBin/Google Chrome for Testing.app/Contents/MacOS/Google Chrome for Testing";

    public final static String firefoxDriverPath = projectRoot + "/Drivers/geckodriver";
    public final static String firefoxBinPath = projectRoot + "/FirefoxBin/firefox.app/Contents/MacOS/firefox";

    public final static int WAITSECONDS = 20;
    public final static String AMAZON = "https://aws.amazon.com";
    public final static String AZURE = "http://azure.microsoft.com";
    public final static String GOOGLE = "https://cloud.google.com/";


}

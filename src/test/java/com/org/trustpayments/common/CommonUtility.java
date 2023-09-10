/*********************************************************************************************************************************************************************************
 * @author : Lakshmi Ay
 * This is the Common utility classes which contains common methods to take screenshots and other util methods.
 ***********************************************************************************************************************************************************************************/

package com.org.trustpayments.common;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class CommonUtility {
    public static Date date;
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-SSS");
    public static String path = CommonConstants.SCREEN_SHOTS_BASE_PATH + "\\" + CommonConstants.PASSED_METHODS_PATH + "\\";
    public static String failedpath = CommonConstants.SCREEN_SHOTS_BASE_PATH + "\\" + CommonConstants.FAILED_METHODS_PATH + "\\";
    public static String newScreenShotFileName;
    public static String newFailedScreenShotFileName;
    public static String screenShotFileNameWithTimeStamp;
    public static String browsertype = "firefox";


    public static void createFolders() throws IOException {

        File passedFolder = new File(path);
        if (!passedFolder.exists()) {
            System.out.println("Folder doesnt exist. Create new folder for " + CommonConstants.PASSED_METHODS_PATH);
            passedFolder.mkdir();
        }

        File failedFolder = new File(failedpath);
        if (!failedFolder.exists()) {
            System.out.println("Folder doesnt exist. Create new folder for " + CommonConstants.FAILED_METHODS_PATH);
            failedFolder.mkdir();
        }

    }


    public static void takeScreenshot(WebDriver driver, String screenshotName) throws IOException {
        System.out.println(screenshotName);
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        date = new Date();
        screenShotFileNameWithTimeStamp = dateFormat.format(date) + ".png";
        System.out.println(screenShotFileNameWithTimeStamp);

        newScreenShotFileName = path + screenshotName + "_" + screenShotFileNameWithTimeStamp;
        System.out.println(newScreenShotFileName);

        FileUtils.copyFile(scrFile, new File(newScreenShotFileName));

    }

    public static void takeFailedScreenshot(WebDriver driver, String screenshotName) throws IOException {

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        date = new Date();
        screenShotFileNameWithTimeStamp = dateFormat.format(date) + ".png";
        System.out.println(screenShotFileNameWithTimeStamp);

        newFailedScreenShotFileName = failedpath + screenshotName + "_" + screenShotFileNameWithTimeStamp;

        FileUtils.copyFile(scrFile, new File(newFailedScreenShotFileName));

    }


    public static String getScreenshotPath(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, we could see a folder "FailedTestsScreenshots"
        // under src folder
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
                + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }


}


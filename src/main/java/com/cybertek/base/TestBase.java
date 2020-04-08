package com.cybertek.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cybertek.utilities.BrowserUtils;
import com.cybertek.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected ExtentReports report;
    protected ExtentHtmlReporter htmlReport;
    protected ExtentTest logger;

    @BeforeMethod
    public void setUpMethod(){
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 10);

    }

    @AfterMethod
    public void driverClose(ITestResult result) throws InterruptedException, IOException {
        // IF FAILS TAKE SCREENSHOT
        if(result.getStatus() == ITestResult.FAILURE){
            // record the name of the failed test
            logger.fail(result.getName());
            // take screenshot and return location of the screenshot
            String screenshot = BrowserUtils.getScreenshot(result.getName());
            logger.addScreenCaptureFromPath(screenshot);
            // capture the exception
            logger.fail(result.getThrowable());
        }else if(result.getStatus() == ITestResult.SKIP){
            // sometime tests are skipped
            logger.skip("Test Skipped: "+ result.getName());
        }
        // CLOSE DRIVER
        Thread.sleep(2000);
        driver.close();
    }
}


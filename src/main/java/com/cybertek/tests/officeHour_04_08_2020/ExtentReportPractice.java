package com.cybertek.tests.officeHour_04_08_2020;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cybertek.base.TestBase;
import com.cybertek.utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtentReportPractice extends TestBase {

    @BeforeMethod
    public void setUp(){
        report = new ExtentReports();
        String path = System.getProperty("user.dir")+"\\test-output\\report.html";
        htmlReport = new ExtentHtmlReporter(path);
        report.attachReporter(htmlReport);
        report.setSystemInfo("browser", ConfigurationReader.getProperty("browser")); //setting some general system info
    }

    @Test
    public void test1(){
        logger = report.createTest("Login to a website");
        logger.info("Navigating to URL");
        driver.get(ConfigurationReader.getProperty("url"));

        WebElement user = driver.findElement(By.id("prependedInput"));
        WebElement password = driver.findElement(By.id("prependedInput2"));
        WebElement submit = driver.findElement(By.id("_submit"));

        logger.info("Enter Username");
        user.sendKeys(ConfigurationReader.getProperty("username"));
        logger.info("Enter Password");
        password.sendKeys(ConfigurationReader.getProperty("password"));
        logger.info("Click submit button");
        submit.click();
        logger.info("Verify title");
        Assert.assertEquals(driver.getTitle(), "ABC");
        logger.pass("PASS: login successful");
    }

    @AfterTest
    public void flushReport(){
        report.flush(); // clear existing report and generate new one
    }

    /*
        suite - set of tests
        regression suite - all tests that  cover all functionalities
            client A regression suite
            client B regression suite
        smoke suite - few main test

     */



}

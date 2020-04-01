package com.cybertek.tests.officeHour_03_31_2020;
import com.cybertek.base.TestBase;
import com.cybertek.utilities.ConfigurationReader;
import com.cybertek.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class WaitPractice extends TestBase {

    /*
        http://qa3.vytrack.com
        salesmanager110
        UserUser123
     */

    /*
        Implicit Wait - set it 1 time and it will work for every findElement method
                        -NoSuchElementException

       Thread.sleep - not Selenium wait! Thread - java class, sleep() method of Thread class
                        stops the execution of java program
                        -We never want to use this method in our tests

        Explicit wait - we have to declare every time before the iteraction with element
                        Expected Condition we are looking for

        Singelton - it helps us to make sure we have only 1 instance of driver object at a time
     */

    @Test
    public void testWait(){
        driver.get(ConfigurationReader.getProperty("url"));

        webDriverWait.until(ExpectedConditions.titleIs("Login"));

        WebElement user = driver.findElement(By.id("prependedInput"));
        WebElement password = driver.findElement(By.id("prependedInput2"));
        WebElement submit = driver.findElement(By.id("_submit"));

        user.sendKeys(ConfigurationReader.getProperty("username"));
        password.sendKeys(ConfigurationReader.getProperty("password"));
        submit.click();

        WebElement account = driver.findElement(By.xpath("//span[.='Accounts']/following-sibling::span/following-sibling::a/parent::li"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(account));

        account.click();




    }


}


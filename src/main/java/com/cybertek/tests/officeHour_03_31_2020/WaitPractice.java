package com.cybertek.tests.officeHour_03_31_2020;
import com.cybertek.base.TestBase;
import com.cybertek.utilities.ConfigurationReader;
import com.cybertek.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.text.Utilities;
import java.util.List;
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

        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingoverlay")));

        WebElement account = driver.findElement(By.xpath("//span[.='Accounts']/following-sibling::a"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(account));

        account.click();

        /*
                table
                    tbody
                        thead - header
                        tr - table row
                        td - table data

                        print all owner names
         */

        List <WebElement> owners = driver.findElements(By.xpath("//table/tbody/tr/td[6]"));
        for (WebElement each :
                owners) {
            System.out.println(each.getText());
        }

        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title='Filters']")));
        driver.findElement(By.xpath("//a[@title='Filters']")).click();
        driver.findElement(By.xpath("//div[contains(text(), 'Owner')]")).click();

        String searchFor = "cyber tek";

        WebElement filterInput = driver.findElement(By.id("s2id_autogen2"));
        filterInput.sendKeys(searchFor);

        driver.findElement(By.xpath("//span[.='"+searchFor+"']")).click(); ////span[.='cyber tek']
        driver.findElement(By.xpath("(//button[.='Update'])[1]")).click();

        List <WebElement> results = driver.findElements(By.xpath("//table/tbody/tr/td[6]"));

        for (WebElement each :
                results) {
            Assert.assertEquals(each.getText(), searchFor);
        }





    }


}


package org.ibs.appline;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FirtsTest {

    WebDriver driver;
    @Before
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }



    @Test
    public void firstTest() {


        driver.get("https://www.sberbank.ru/ru/person");
        WebElement btnCookie =  driver.findElement(By.xpath("//span[@class='dk-sbol-button__text dk-sbol-button__text_size_sm']"));
        btnCookie.click();
        WebElement btnRegion = driver.findElement(By.xpath("//button[@class='kitt-region__accept']"));
        btnRegion.click();
        WebElement mainMenuIns = driver.findElement(By.xpath("//a[text()='Страхование']"));
        mainMenuIns.click();
        WebElement subMenuIns = driver.findElement(By.xpath("//a[text()='Все страховые программы']"));
        subMenuIns.click();
        wait(2000);
        String parentHandle = driver.getWindowHandle();
        for(String childHandle : driver.getWindowHandles()){
            if (!childHandle.equals(parentHandle)){
                driver.close();
                driver.switchTo().window(childHandle);
            }
        }
        wait(2000);

        WebElement travelIns = driver.findElement(By.xpath("//h4[text()='Страхование путешественников']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(travelIns);
        actions.perform();
        wait(2000);
        travelIns.click();
        parentHandle = driver.getWindowHandle();
        for(String childHandle : driver.getWindowHandles()){
            if (!childHandle.equals(parentHandle)){
                driver.close();
                driver.switchTo().window(childHandle);
            }
        }
        wait(2000);
        WebElement titleTravelInsPage = driver.findElement(By.xpath("//h1/span"));

        Assert.assertEquals("Заголовок не найден","Страхование путешественников", titleTravelInsPage.getText());

        WebElement checkOnSite = driver.findElement(By.xpath("//a[@data-testid='Link']"));
        actions.moveToElement(checkOnSite);
        actions.perform();
        wait(2000);
        checkOnSite.click();
        parentHandle = driver.getWindowHandle();
        for(String childHandle : driver.getWindowHandles()){
            if (!childHandle.equals(parentHandle)){
                driver.close();
                driver.switchTo().window(childHandle);
            }
        }


        wait(5000);


    }

    @After
    public void afterTest() {
        driver.quit();
    }

    public void wait (int mls) {
        try {
            Thread.sleep(mls);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




}

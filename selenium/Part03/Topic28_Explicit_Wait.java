package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic28_Explicit_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void BeforeClass() {

        driver.get("https://automationfc.github.io/dynamic-loading/");


    }
    @Test
    public void TC_01_Equals_5_Second() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        //Wait cho loading icon biến mất trong vòng x giây
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        //Wait cho hello world text xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#loading")));

        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");



    }

    @Test
    public void TC_02_Less_Than_5_Second() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        //Wait cho loading icon biến mất trong vòng x giây
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");


    }
    @Test
    public void TC_02_Greater_Than_5_Second() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        //Wait cho loading icon biến mất trong vòng x giây
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");


    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInsecons(long timeInsecons) {
        try {
            Thread.sleep(timeInsecons * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
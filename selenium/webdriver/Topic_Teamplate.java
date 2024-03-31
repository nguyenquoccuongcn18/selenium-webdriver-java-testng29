package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic_Teamplate {
    WebDriver driver;

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }
    @Test
    public void TC_01_Element() {

    }

    @Test
    public void TC_02_Logo() {

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
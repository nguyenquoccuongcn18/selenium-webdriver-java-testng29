package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;


public class Topic29_Wait_Mix_Implicit_Explicit {
    WebDriver driver;
    WebDriverWait explicitWait;


    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Only_Implicit_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");

        //Khi vào tìm element thì tìm thấy ngay
        //Ko cần chờ hết timeout
        driver.findElement(By.cssSelector("#input#email"));
    }

    @Test
    public void TC_02_Only_Implicit_Not_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.facebook.com/");

        //Khi vào tìm element sẽ ko tìm thấy
        //polling mỗi nữa giây tìm lại 1 lần
        //hết timeout fail testcase->throw
        driver.findElement(By.cssSelector("#input#auto"));

    }
    @Test
    public void TC_03_Only_Explicit_Found() {
        driver.get("https://www.facebook.com/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }
    @Test
    public void TC_04_Only_Explicit_Not_Found_Param_By() {
        driver.get("https://www.facebook.com/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#auto")));
    }
    @Test
    public void TC_05_Only_Explicit_Not_Found_Param_WebElement() {
        driver.get("https://www.facebook.com/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //visibilityOf
        //visibilityOfElementLocated
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#auto"))));
    }
    @Test
    public void TC_05_Mix_Implicit_Explicit_Found() {
        driver.get("https://www.facebook.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.cssSelector("input#email"));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

    }
    @Test
    public void TC_06_Mix_Implicit_Explicit_No_Found() {
        //Implicit chạy trước tầm 0.5s sau đó Explicit chạy sau
        //implicit chạy trước tìm element
        driver.get("https://www.facebook.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("Start time :" + getTimdate());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
        } catch (Exception e) {
            System.out.println("End time :" + getTimdate());
            e.printStackTrace();
        }
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
    public String getTimdate() {
        Date date = new Date();
            return date.toString();
    }
}
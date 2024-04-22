package Part03;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic27_Wait_01_Explicit_01_Knowledge {
    WebDriver driver;
    WebDriverWait explicitWait; //Khai báo chưa khởi tạo

    @BeforeClass   //Precondition - Khởi tạo dữ liệu/ data test /page class/variable
    public void BeforeClass() {

        driver = new ChromeDriver();
        //Khởi tạo 1 ExplicitWait có tổng thời gian là 10s và polling là 0.5s mặc định
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Khởi tạo 1 ExplicitWait có tổng thời gian là 10s và polling là 0.3s tự set
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10),Duration.ofMillis(300));
    }
    @Test
    public void TC_01_Element() {
        driver.get("https://www.facebook.com/");
        //Chờ cho 1 Alert Presence trong HTML/DOM trước khi thao tác lên
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        //Chờ cho Element không còn ở trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        //Chờ cho Element có ở trong DOM (ko quan tâm có trên UI ko)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //Chờ cho 1 List Element có ở trong DOM (ko quan tâm có trên UI ko)
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));
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
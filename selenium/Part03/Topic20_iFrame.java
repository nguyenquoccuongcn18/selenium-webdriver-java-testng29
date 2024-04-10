package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic20_iFrame {
    WebDriver driver;

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Element() {
        //Trang A domain : formsite.com
        //lúc này chưa click vào cái chưa iframe
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        //Click vào thì trong element xuất hiện iframe
        //Chứa iframe trang B
        //Từ A vào B
        driver.switchTo().frame("frame-one85593366");
        driver.findElement(By.cssSelector("")).click();
        //Nếu từ B vào C thì switchto tiếp

        //Nếu từ C quay lại B (dùng khi trong iframe có tiếp 1 iframe)
        driver.switchTo().parentFrame();

        //Đang ở B

        //Từ B quay lại A
        driver.switchTo().defaultContent();



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
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class Topic04_Slenium_Xpath {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.msedge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
        } else {
            System.setProperty("webdriver.msedge.driver", projectPath + "/browserDrivers/msedgedriver");

        }

        //driver = new FirefoxDriver();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //driver.get("http://live.techpanda.org/index.php");
    }



    @Test
    public void TC_02_Run_On_Chrome() {
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        driver.quit();
    }

    @Test
    public void TC_01_Url() {
        driver.get("http://live.techpanda.org/index.php");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        // Tương đối a[@title='My Account'] - Tuyệt đối a[contains(@title,'My Account')]
        //input[starts-with(@data-spm-anchor-id,'a2o42.login_signup')]
        //dùng hàm gettext để get ra đoạn text  'Samsung Galaxy was added to your shopping cart.'
        String succesMessText = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
        Assert.assertEquals(succesMessText,"Samsung Galaxy was added to your shopping cart.");
    }

    @Test
    public void TC_02_Logo() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Part2_topic11_Xpath {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String  username , pasword;

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
        driver.get(" https://demo.guru99.com/");
    }


    @Test
    public void TC_01_() {
        //truy cập https://demo.guru99.com/
        //Nhập email bất kì
        //click button submit
        //get user - password -> Lưu vào 1 biến

         //username = driver.findElement(By.xpath("//td[text()=/'User ID :/']/following-sibling::td")).getText();
         //pasword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
    }

    @Test
    public void TC_02_login() {
        //truy cập trang login
        //nhập user-password ở màn hình đăng kí
        //driver.findElement(By.name("")).sendKeys(username);
        //driver.findElement(By.name("")).sendKeys(pasword);

        //click login

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
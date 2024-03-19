package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


//https://docs.google.com/document/d/1G0QsiVDI5KfhNGKxE6a03bhn6KpvgDh69BTx2ttCO94/edit
public class Topic05_WebBrowser_WebElement_Exercise01 {

    WebDriver driver;

    @BeforeClass
    public void BeforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Page_Url() throws InterruptedException {
        //step01: truy cập http://live.techpanda.org/

        driver.get("http://live.techpanda.org/");

        //Click My account tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInsecons(3);

        //verify xem đúng link này không
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        //click
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInsecons(3);
    }

    @Test
    public void TC_02_Page_Title() {
        driver.get("http://live.techpanda.org/");

        //Click My account tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInsecons(3);

        Assert.assertEquals(driver.getTitle(),"Customer Login");

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInsecons(3);

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");


    }

    @Test
    public void TC_03_Page_Navigation() {

        driver.get("http://live.techpanda.org/");

        //Click My account tại footer
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        sleepInsecons(3);

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInsecons(3);

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

        driver.navigate().back();
        sleepInsecons(2);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

        driver.navigate().forward();
        sleepInsecons(2);

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }
    @Test
    public void TC_04_Page_Source() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
        sleepInsecons(3);

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInsecons(2);

        //ktra xem đúng cái title k
        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));


    }



    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
    //sau khi chờ thì throw trycache ra hàm sleepInsecons
    public void sleepInsecons(long timeInsecons)  {
        try {
            Thread.sleep(timeInsecons * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}


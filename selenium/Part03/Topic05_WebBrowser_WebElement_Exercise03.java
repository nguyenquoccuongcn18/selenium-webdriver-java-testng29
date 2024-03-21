package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic05_WebBrowser_WebElement_Exercise03 {
    WebDriver driver;

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("http://live.techpanda.org/");
    }
    @Test
    public void TC_01_Emty_User_Password() {
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='content fieldset']//p[@class='required']")).isDisplayed());
    }

    @Test
    public void TC_02_invalid_Email() {
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='content fieldset']//p[@class='required']")).isDisplayed());

        driver.findElement(By.xpath("//div[@class='input-box']//input[@name='login[username]']")).sendKeys("123456@123.1223");
        driver.findElement(By.xpath("//div[@class='input-box']//input[@name='login[password]']")).sendKeys("123456");
        driver.findElement(By.xpath("//div[@class='buttons-set']//button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),("Please enter a valid email address. For example johndoe@domain.com."));

    }

    @Test
    public void TC_03_succes() {
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        driver.findElement(By.cssSelector("input#firstname")).sendKeys();
        driver.findElement(By.cssSelector("input#lastname")).sendKeys();
        driver.findElement(By.cssSelector("input#email_address")).sendKeys();
        driver.findElement(By.cssSelector("input#password")).sendKeys();
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys();
        driver.findElement(By.cssSelector("button[title='Register']")).click();


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
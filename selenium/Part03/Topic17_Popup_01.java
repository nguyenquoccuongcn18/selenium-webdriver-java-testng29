package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic17_Popup_01 {
    WebDriver driver;

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Fixed_Popup_in_DOM_1() {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();

        By LoginPopup = By.xpath("//div[@class='modal fade in']//div[@class='modal-dialog']//div[@class='modal-content']");
        By LoginPopup1 = By.cssSelector("div[id='modal-login-v1'][style]>div");
        //Ktra login popup đang hiển thị
        Assert.assertTrue(driver.findElement(LoginPopup1).isDisplayed());

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#account-input")).sendKeys("automatìonc");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] input#password-input")).sendKeys("automatìonc");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.btn-login-v1")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Tài khoản không tồn tại!']")).getText(),"Tài khoản không tồn tại!");
        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        Assert.assertFalse(driver.findElement(LoginPopup1).isDisplayed());


    }

    @Test
    public void TC_02_Fixed_Popup_Not_in_DOM() {
        driver.get("https://tiki.vn/");
        By ButtonLoginTiKi = By.cssSelector("div[data-view-id='header_header_account_container']");
        By Login = By.cssSelector("div[class='styles__Left-sc-2hr4xa-1 iwneWf']");
        By ClickEmail = By.cssSelector("p.login-with-email");
        By ClickDangNhap = By.xpath("//button[text()='Đăng nhập']");

        driver.findElement(ButtonLoginTiKi).click();
        //ktra hiển thị
        Assert.assertTrue(driver.findElement(Login).isDisplayed());

        driver.findElement(ClickEmail).click();
        driver.findElement(ClickDangNhap).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).getText(),"Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();





    }

    @Test
    public void TC_02_Fixed_Popup_Not_in_DOM_2() {
        driver.get("https://www.facebook.com/");

        driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
        sleepInsecons(5);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mbs _52lq fsl fwb fcb']")).isDisplayed());
        sleepInsecons(5);
        driver.findElement(By.xpath("//img[@class='_8idr img']")).click();



        // do không sleep sẽ lỗi vì chưa load được để verify tắt popup
        //Nếu tắt popup sẽ mất element nên sẽ dùng assertEquals - .sizr(),0
        sleepInsecons(5);
        Assert.assertEquals(driver.findElements(By.xpath("//div[@class='mbs _52lq fsl fwb fcb']")).size(),0);


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
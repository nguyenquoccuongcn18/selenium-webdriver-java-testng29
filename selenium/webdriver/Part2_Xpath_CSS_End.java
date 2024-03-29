package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class Part2_Xpath_CSS_End {
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
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }


    @Test
    public void Register_01_Empty_Data() {
        //driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.id("txtFirstname")).clear();
        driver.findElement(By.id("txtEmail")).clear();
        driver.findElement(By.id("txtCEmail")).clear();
        driver.findElement(By.id("txtPassword")).clear();
        driver.findElement(By.id("txtCPassword")).clear();
        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.xpath("//button[text()='ĐĂNG KÝ' and @type='submit']")).click();

        //Có thể dùng này nhưng không tối ưu
        //String EmailAddressErrorMessage = driver.findElement(By.id("Vui lòng nhập họ tên")).getText();
        //Assert.assertEquals(EmailAddressErrorMessage, "Vui lòng nhập họ tên");
        //Cách này tối ưu hơn  // Verify
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");

    }

    @Test
    public void Register_02_Invalid_Email_Address() {
        //action nhập sai định dạng email
        driver.findElement(By.id("txtFirstname")).sendKeys("Dat Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("Dat Nguyen@gmail.@");
        driver.findElement(By.id("txtCEmail")).sendKeys("Dat Nguyen@gmail");
        driver.findElement(By.id("txtPassword")).sendKeys("1234a");
        driver.findElement(By.id("txtCPassword")).sendKeys("11112");
        driver.findElement(By.id("txtPhone")).sendKeys("000000000");

        //verify
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }
    @Test
    public void Register_03_Incorrect_Confirm_Email() {
        //Nhập lại email sai
        driver.findElement(By.id("txtFirstname")).sendKeys("Dat Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("DatNguyen@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("Dat Nguyen@gmail");
        driver.findElement(By.id("txtPassword")).sendKeys("1234a");
        driver.findElement(By.id("txtCPassword")).sendKeys("11112");
        driver.findElement(By.id("txtPhone")).sendKeys("000000000");

        //Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }
    @Test
    public void Register_04_Invalid_Password() {
        //nhập sai mât khau
        driver.findElement(By.id("txtFirstname")).sendKeys("Dat Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("DatNguyen@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("DatNguyen@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("1234a");
        driver.findElement(By.id("txtCPassword")).sendKeys("1234a");
        driver.findElement(By.id("txtPhone")).sendKeys("000000000");
        //
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");


    }
    @Test
    public void Register_05_Invalid_Confirm_Password() {
        driver.findElement(By.id("txtFirstname")).sendKeys("Dat Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("DatNguyen@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("DatNguyen@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123478a");
        driver.findElement(By.id("txtCPassword")).sendKeys("11112a");
        driver.findElement(By.id("txtPhone")).sendKeys("000000000");
        //
        //Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
    }
    @Test
    public void Register_06_Invalid_Phone_Number() {
        driver.findElement(By.id("txtFirstname")).sendKeys("Dat Nguyen");
        driver.findElement(By.id("txtEmail")).sendKeys("DatNguyen@gmail.com");
        driver.findElement(By.id("txtCEmail")).sendKeys("DatNguyen@gmail.com");
        driver.findElement(By.id("txtPassword")).sendKeys("123478a");
        driver.findElement(By.id("txtCPassword")).sendKeys("123478a");
        driver.findElement(By.id("txtPhone")).sendKeys("000000000");
        //

        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
        driver.findElement(By.id("txtPhone")).clear();

        driver.findElement(By.id("txtPhone")).sendKeys("092");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        driver.findElement(By.id("txtPhone")).clear();
        driver.findElement(By.id("txtPhone")).sendKeys("09555555555555555555555555555555");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
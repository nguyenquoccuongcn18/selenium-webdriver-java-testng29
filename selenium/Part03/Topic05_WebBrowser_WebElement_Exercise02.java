package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic05_WebBrowser_WebElement_Exercise02 {
    WebDriver driver;

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Verify_isDisplay() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()){
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Testing");
            System.out.println("Email Text box isDisplay");

        }else
            System.out.println("Is not Display");


        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()){
            driver.findElement(By.cssSelector("input#under_18")).sendKeys("Automation Testing");
            System.out.println("Email Text box isDisplay");

        }else
            System.out.println("Is not Display");

        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Testing");
            System.out.println("Email Text box isDisplay");

        }else
            System.out.println("Is not Display");

        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
            System.out.println("Name: User5");

        }else
            System.out.println("Name: User5 Is not Display");


        //Mong đợi hiển thị là dùng true
        //Assert.assertTrue(driver.findElement(By.cssSelector("input#mail")).isDisplayed());
        //Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isDisplayed());
        //Assert.assertTrue(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed());

        //Không mong đợi hiển thị dùng Flase
        //Assert.assertFalse(driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed());



    }

    @Test
    public void TC_02_Verify_isDisiable() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.cssSelector("input#mail")).isEnabled()) {
            System.out.println("Email Text box isDisplay");
        } else {
            System.out.println("Is not Display");
        }

        if (driver.findElement(By.cssSelector("input#under_18")).isEnabled()) {
            System.out.println("Email Text box isDisplay");

        } else {
            System.out.println("Is not Display");
        }
        if (driver.findElement(By.cssSelector("textarea#edu")).isEnabled()){
            System.out.println("Email Text box isDisplay");

        }else{
            System.out.println("Is not Display");}
    //Nếu enable thì trả ra enable ngược lại Disiable
        if (driver.findElement(By.cssSelector("input#check-disbaled")).isEnabled()){
            System.out.println("enable");

        }else {
            System.out.println("Disiable");
        }
    }


    @Test
    public void TC_03_Verify_isSelect() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //click under18
        driver.findElement(By.cssSelector("input#under_18")).click();
        driver.findElement(By.cssSelector("input#java")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#java")).isSelected());

        driver.findElement(By.cssSelector("input#java")).click();
        sleepInsecons(2);
        Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());
        Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());

    }

    @Test
    public void TC_04_Verify_isSelect_isDisplay_isDisiable() {
        driver.get("https://login.mailchimp.com/signup/");

        driver.findElement(By.cssSelector("input#email")).sendKeys("CuongTest");
        driver.findElement(By.cssSelector("input#new_username")).click();
        //Nhập số
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("0001");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Nhập chữ
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("aaaa");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Nhập chữ đủ 8 kí tự
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("aaaabghgjy");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());



        //Nhập chữ hoa
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Aaaabghgjy");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Nhập kí tự đặc biệt đủ 8 kí tự
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("FFFSss#");

        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //Nhập kí tự đặc biệt đủ 8 kí tự viết hoa.
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Addd$sfbd2143245");

        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());



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

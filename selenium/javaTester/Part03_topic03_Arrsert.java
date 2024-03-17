package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Part03_topic03_Arrsert {
    WebDriver driver;
    ChromeDriver Chrome;
    @Test
    public void verifyTestNG(){

        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        //Trong JAVA có nhiêu thư viện để verify dữ liệu
        //Testing Famework (Unit/Intergration/UI Auto)
        //Junit4/TestNG/Junit5/Hamcrest/ArrsertJ/...


        //Kiểu boolean (True/false)
        //khi mong muốn đkien trả về là ĐÚNG thì dùng assertTrue để verify
        Assert.assertTrue(driver.getPageSource().contains("Facebook giúp bạn kết nối và chia sẻ"));

        //khi mong muốn đkien trả về là SAI thì dùng assertFail để verify
        Assert.assertFalse(driver.getPageSource().contains("Nhanh chóng và dễ dàng."));

        //Các hàm trả về kiểu dữ liệu là boolean
        //Quy tắc: bắt đầu với tiền tố isXXX
        //WebElement
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select(driver.findElement(By.id(""))).isMultiple();

        //Mong đợi 1 điều kiện nó giống thực tế
        //Actual = Expect
        Assert.assertEquals(driver.findElement(By.id("")).getText(), "ừedwè ");



        //Unit test ko làm
        Object name = null;
        Assert.assertNull(name);

        name = "Testing";
        Assert.assertNotNull(name);
    }
}

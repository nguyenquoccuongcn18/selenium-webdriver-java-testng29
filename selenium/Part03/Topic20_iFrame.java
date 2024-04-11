package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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
    public void TC_02_Iframe_Formsite() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();


        //Check iframe hiển thị
        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIframe.isDisplayed());

        //Dùng webelement trong tương lai thay đổi code đỡ maitain
        driver.switchTo().frame(formIframe);

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Junior");

        //Thao tác ra lại trang A
        driver.switchTo().defaultContent();

        //Thao tác 1 element bên ngoài iframe Trang A
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();

        driver.findElement(By.cssSelector("a[id='link-login-google'] span[class='auth-card__social-text']")).click();
        sleepInsecons(3);

    }
    @Test
    public void TC_02_Frame_HDFCBank() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        driver.switchTo().frame("login_page");

        driver.findElement(By.cssSelector("input.form-control")).sendKeys("Ronaldo");


        driver.findElement(By.cssSelector("a.login-btn")).click();
        driver.findElement(By.cssSelector("a.loginBtn")).click();



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
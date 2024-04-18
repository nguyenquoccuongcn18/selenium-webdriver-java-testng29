package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic25_Wait_01_Element_Status {
    WebDriver driver;
    By ReconfirmEmailTextBox = By.cssSelector("input[name='reg_email_confirmation__']");
    By ReconfirmEmailTextBoxInvisible = By.cssSelector("input[name='reg_email__']");

    WebDriverWait explicitWait ;
    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Visible() {
        //Case này ver trước của Fb.com sẽ làm được còn hiện tại nó invisible
        By ReconfirmEmailTextBoxInvisible = By.cssSelector("input[name='reg_email__']");
        //Nên sẽ dùng ReconfirmEmailTextBoxInvisible

        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInsecons(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("Cuong123@gmail.com");

        //ĐK 1 : Có xuất hiện trên UI và có trong HTML
        //Tại đúng thời điểm này step confirm email texbox đang visible/display
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(ReconfirmEmailTextBoxInvisible));

        //Ktra 1 element đang hiển thị
        Assert.assertTrue(driver.findElement(ReconfirmEmailTextBoxInvisible).isDisplayed());
    }

    @Test
    public void TC_02_InVisible_in_DOM() {
        //Đk 2: Ko xuất hiện trên UI mà vẫn có trong HTML
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();

        //Tại đúng thời điểm này step confirm email texbox đang visible/display
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(ReconfirmEmailTextBoxInvisible));

        //Ktra 1 element đang hiển thị
        Assert.assertTrue(driver.findElement(ReconfirmEmailTextBoxInvisible).isDisplayed());

        //ĐK3: Element ko xuất hiện trên UI và cũng ko có trong HTML

    }
    @Test
    public void TC_02_InVisible_Not_in_DOM() {
        //ĐK3: Element ko xuất hiện trên UI và cũng ko có trong HTML
        driver.get("https://www.facebook.com/");
        sleepInsecons(3);

        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInsecons(3);

        driver.findElement(By.xpath("div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        //Tại đúng thời điểm này step confirm email texbox đang visible/display
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(ReconfirmEmailTextBoxInvisible));

        //Ktra 1 element đang hiển thị
        Assert.assertTrue(driver.findElement(ReconfirmEmailTextBoxInvisible).isDisplayed());

        //ĐK3: Element ko xuất hiện trên UI và cũng ko có trong HTML

    }
    @Test
    public void TC_03_Presence() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInsecons(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("Cuong123@gmail.com");

        //ĐK 1 : Có xuất hiện trên UI và có trong HTML
        //Tại đúng thời điểm này step confirm email texbox đang visible/display
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(ReconfirmEmailTextBoxInvisible));


        //Đk 2: Ko xuất hiện trên UI mà vẫn có trong HTML
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();

        explicitWait.until(ExpectedConditions.presenceOfElementLocated(ReconfirmEmailTextBoxInvisible));


    }
    @Test
    public void TC_04_Staleness() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInsecons(3);

        //Tại thời điểm này có element xuất hiện
        WebElement ReconfirmEmail = driver.findElement(ReconfirmEmailTextBox);

        //click vào popup đóng lại
        driver.findElement(By.xpath("div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();

        //ĐK3: Element ko xuất hiện trên UI và cũng ko có trong HTML
        explicitWait.until(ExpectedConditions.stalenessOf(ReconfirmEmail));



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
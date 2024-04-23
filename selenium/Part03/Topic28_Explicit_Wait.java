package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;


public class Topic28_Explicit_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String File2k = "2k.jpg";
    String File4k = "5k.jpg";
    String File5k = "5k.jpg";

    String FilePath2k = projectPath + File.separator + "UploadFile" + File.separator + File2k ;
    String FilePath4k = projectPath + File.separator + "UploadFile" + File.separator + File4k ;
    String FilePath5k = projectPath + File.separator + "UploadFile" + File.separator + File5k ;

    @BeforeClass
    public void BeforeClass() {




    }
    @Test
    public void TC_01_Equals_5_Second() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        //Wait cho loading icon biến mất trong vòng x giây
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        //Wait cho hello world text xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#loading")));

        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");



    }

    @Test
    public void TC_02_Less_Than_5_Second() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        //Wait cho loading icon biến mất trong vòng x giây
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");


    }
    @Test
    public void TC_02_Greater_Than_5_Second() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start>button")).click();

        //Wait cho loading icon biến mất trong vòng x giây
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");


    }
    @Test
    public void TC_01_AjaxLoading() {
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));


        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");



        driver.findElement(By.xpath("//a[text()='12']")).click();

        //Wait cho loading icon biến mất trong vòng x giây
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1']>div.raDiv")));

       // Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(),"Hello World!");

        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"Friday, April 12, 2024");



    }
    @Test
    public void TC_02_AjaxUploadFile() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(50));

        driver.get("https://gofile.io/welcome");

        //Verify + Assert icon biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.spinner-border"))));

        //Viết gọn lại vừa Wait sau đó tìm element và click
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("button.btn-secondary")))).click();

        //Wait Alll số nhiều + Verify
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border")))));

        //import File
        By ElementFile = By.cssSelector("input[type='file']");
        driver.findElement(ElementFile).sendKeys(FilePath2k + "\n" + FilePath4k + "\n" + FilePath5k );

        //Wait Alll số nhiều
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.spinner-border"))));

        //Wait cho progress bar biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress")))));

        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink")))).click();

        //Assert.assertTrue(driver.findElement(By.xpath("//span[@id='filesContentInfoPublic']")).isDisplayed());


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
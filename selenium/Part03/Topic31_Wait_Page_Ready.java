package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;


public class Topic31_Wait_Page_Ready {
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
    public void TC_01_AjaxLoading() {
        driver = new ChromeDriver();
        driver.get("https://admin-demo.nopcommerce.com");
        driver.manage().window().maximize();


        driver.findElement(By.cssSelector("input#Email")).clear();
        driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
        driver.findElement(By.cssSelector("input#Password")).clear();
        driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.login-button")).click();

        Assert.assertTrue(isPageLoadedSuccess());

      driver.findElement(By.xpath("//i[contains(@class, 'fa-user')]/following-sibling::p")).click();
        driver.findElement(By.xpath("//ul[contains(@style, 'display: block; ')]//i[contains(@class, 'fa-dot-circle')]/following-sibling::p[contains(string(), 'Customers')]")).click();
        Assert.assertTrue(isPageLoadedSuccess());


       driver.findElement(By.xpath("//i[contains(@class, 'fa-book')]/following-sibling::p")).click();
       driver.findElement(By.xpath("//ul[contains(@style, 'display: block; ')]//i[contains(@class, 'fa-dot-circle')]/following-sibling::p[contains(string(), 'Products')]")).click();
           Assert.assertTrue(isPageLoadedSuccess());





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



    //Hàm wait cho nó ready JS
    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(50));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;


        //Điều kiện 1
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
        //Điều kiện 2
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        return explicitWait.until(jQueryLoad);
    }
}
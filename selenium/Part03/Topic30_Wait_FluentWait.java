package Part03;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;


public class Topic30_Wait_FluentWait {
    WebDriver driver;

    private long fullTimeoutInSeconds = 30 ;
    private long pollingTimoutInSeconds = 300 ;


    FluentWait <WebDriver> fluentWait ;
    FluentWait <WebElement> FluentElement ;
    FluentWait <String> FluentString ;
    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();

        //Time - default polling time 0.5s
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Time - default polling time 0.3s (300s)
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));
        fluentWait =new FluentWait<WebDriver>(driver);
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Element() {
        //Khoi tạo
        driver.get("https://www.facebook.com/");

        fluentWait = new FluentWait<WebDriver>(driver);

        FluentElement = new FluentWait<WebElement>(driver.findElement(By.cssSelector(" ")));

        FluentString = new FluentWait<String>("Hi");


        //Settings

        //Tổng time
        fluentWait.withTimeout(Duration.ofSeconds(10));

        //Polling Time
        fluentWait.pollingEvery(Duration.ofMillis(300));

        //Ignore exception
        fluentWait.ignoring(NoSuchElementException.class);

        //Ignore timeoutException
        fluentWait.ignoring(TimeoutException.class);

        //Đkien ktra
        fluentWait.until(new Function<WebDriver, Object>() {
            @Override
            public Object apply(WebDriver webDriver) {
                return null;
            }
        });

        fluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("")).getText();
            }
        });
        //
        fluentWait.withTimeout(Duration.ofSeconds(10))
                    .pollingEvery(Duration.ofMillis(300))
                    .ignoring(NoSuchElementException.class)
                    .until(new Function<WebDriver, Boolean>() {
                  @Override
                     public Boolean apply(WebDriver webDriver) {
                             return webDriver.findElement(By.cssSelector("")).isDisplayed();
                  }
              });



    }

    @Test
    public void TC_02_Logo() {
        //Viết gọi lại dùng hàm waitandFindElement
        driver.get("https://automationfc.github.io/dynamic-loading/");
        waitandFindElement(By.cssSelector("div#start>button")).click();

        String helloText = waitandFindElement(By.xpath("//div[@id='finish']/h4")).getText();
        Assert.assertEquals(helloText,"Hello World!");

    }    @Test
    public void TC_03_Logo() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
       WebElement countdownTime = driver.findElement(By.cssSelector("javascript_countdown_time"));

       FluentElement = new FluentWait<WebElement>(countdownTime);

        fluentWait.withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        FluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement Element) {
                String text = Element.getText();
                System.out.println(text);
                return text.endsWith("00");
            }
        });

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
// Hàm fluent Wait
    public WebElement waitandFindElement (By locator){
        FluentWait <WebDriver> fluentDriver = new FluentWait<WebDriver>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

       return fluentDriver.until(new Function<WebDriver, WebElement>() {

          @Override
           public WebElement apply(WebDriver webDriver) {
               return webDriver.findElement(locator);
           }

       });
    }
}
package Part03;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic12_Handle_Alert {
    WebDriver driver;
    WebDriverWait explicitWait;
    By resultText = By.cssSelector("p#result");

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Acept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();



        //Chờ cho alert present
        //Nếu trong tgian chờ mà xuất hiện thì tự switch vào
        //nếu hết tgian mà chưa xuất hiện thì mới fail
        Alert alert =  explicitWait.until(ExpectedConditions.alertIsPresent());
       // Alert alert= driver.switchTo().alert();

        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        //Khi acept cancel rồi alert sẽ mất nên verify trước
        alert.accept();
        sleepInsecons(2);

       Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");



    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alert =  explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        //Nhấn cancle
        alert.dismiss();
        sleepInsecons(2);
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked: Cancel");


    }
    @Test
    public void TC_03_Prompt_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInsecons(5);

        Alert alert =  explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        String text ="cuongtetstne";
        alert.sendKeys(text);
        sleepInsecons(5);
        //Nhấn OK
        alert.accept();
        Assert.assertEquals(driver.findElement(resultText).getText(),"You entered: " + text);

    }
    @Test
    public void TC_04_Authentication_Alert() {
        //Thư viện alert không sử dụng được cho Authen Alert được  vì tính bảo mật
        //Chrome Dev tool protocol (CDP) /chrome Edge chỉ 2 này dc support

        //Cách 1 truyền thẳng user pass vào url
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

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

//NOTE
//        driver.switchTo().alert();
//        driver.switchTo().frame(1);
//        driver.switchTo().window("");


        //Cancle alert
//        void dismiss();

        //Accept alert
//        void accept();

        //Get text trong alert ra
//        String getText();

        //Nhập text vào alert
//        void sendKeys(String keysToSend);


}
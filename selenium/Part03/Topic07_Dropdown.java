package Part03;

import javaTester.Random_01;
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
import java.util.List;
import java.util.Random;


public class Topic07_Dropdown {
    WebDriver driver;
    String firstname = "Trung" , lastname="Kelvin" , emailaddress="getEmailaddress",Company="MC" , password="11111111" ;

    String day="15", month="October" , year="1998";

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");
    }
    @Test
    public void TC_01_Element() {

        driver.findElement(By.xpath("//div[@class='header-links']//a[text()='Register']")).click();

        driver.findElement(By.cssSelector("input#gender-male")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstname);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastname);

        //Hàm select// code chưa tối ưu
        //Select day =new Select(driver.findElement(By.name("DateOfBirthDay")));
        //day.deselectByVisibleText("15");



        //code ngắn gọn hơn
        //new Select(driver.findElement(By.name("DateOfBirthDay"))).deselectByVisibleText("15");
        //new Select(driver.findElement(By.name("DateOfBirthMonth"))).deselectByVisibleText("October");
        //new Select(driver.findElement(By.name("DateOfBirthYear"))).deselectByVisibleText("1998");

        //tạo ra 1 hàm để dùng lại
        Select  day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText(this.day);


            //verify fropdown là single ko phải muti
        Assert.assertFalse(day.isMultiple());

            //verify SLuong 32 item
        List<WebElement> dayOptions = day.getOptions();
        Assert.assertEquals(dayOptions.size(), 32);



        Select  month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText("October");

        Select  year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByVisibleText("1998");


        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailaddress);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(Company);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        driver.findElement(By.cssSelector("button#register-button")).click();


        //verify dropdown xem có đúng không
       // new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText();

        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),day);



    }

    @Test
    public void TC_02_Logo() {

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

    public String getEmailaddress() {
        Random_01 rand = new Random_01();
        System.out.println(rand.NextInt(0));
        return "RandomCuongTest" + new Random().nextInt(9999) + "Automation";

    }


}
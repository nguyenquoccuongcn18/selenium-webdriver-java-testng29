package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_Selenium_Locator  {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.Chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.Chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/register");
    }

    //Note
    //Selenium ver: 1x / 2x / 3x
    // 8 loại Locator
    // Selenium Locator = HTML Attribute
    // id/Class/Name = Trùng với 3 Attribute cua html
    //LinkText/Partical Linktext : html link thẻ (a)
    // Taqname: html taqname
    //CSS/Xpath

    //Selenium v4.0 - GUI(Graphic  user interface)
    //Class - relative Locator
    // above/bellow/near / leftof /rightof


    //UI testing
    //Funtion UI // Tinh nang truoc Giao dien sau

    //UI
    //Font/Size/Color/Postion/Location/Resolution/Reponsive..

    //TestNG order (0-9 A-Z)
    //C:la class  M :là method I: interface R:record A: Annotation F:Final
    @Test
    public void TC_01_ID() {
        //Tìm element có id là FirstName
        driver.findElement(By.id("FirstName")).sendKeys("Cuong");

    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));
    }
    @Test
    public void TC_03_TagName() {
        //tagname tìm giống nhau số nhiều
        driver.findElement(By.tagName("input"));

    }

    @Test
    public void TC_05_Link_Text() {
        //Link text lấy nguyên cái text còn Partical_Link thì chỉ lấy 1 phần
        // Độ chính xác cao = toàn bộ
        driver.findElement(By.linkText("Shipping & returns"));
    }
    @Test
    public void TC_06_Partical_Link() {
        // Độ chính xác tương đối = 1 phần (đầu / giữa /cuối)
        driver.findElement(By.partialLinkText("vendor account"));

    }
    @Test
    public void TC_07_CSS() {
        //Css với id
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));


        //Css với class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));


        //Css với name
        driver.findElement(By.cssSelector("input[name='FirstName']"));


        //Css với Tagname
        driver.findElement(By.cssSelector("input"));

        //Css với Link
        driver.findElement(By.cssSelector("a[href='/newproducts']"));

        //Css với Partical_Link
        //lấy đầu
        driver.findElement(By.cssSelector("a[href*='newproducts']"));
        //lấy giữa
        //driver.findElement(By.cssSelector("a[href^='newproducts']"));
        //lấy cuối
        //driver.findElement(By.cssSelector("a[href$='newproducts']"));
    }
    @Test
    public void TC_08_Xpath() {

        //Xpath với id
        driver.findElement(By.xpath("//input[@id='FirstName']"));


        //Xpath với class
        driver.findElement(By.xpath("//div[@class='page-title']"));



        //Xpath với name
        driver.findElement(By.xpath("//input[@name='FirstName']"));


        //Xpath với Tagname
        driver.findElement(By.xpath("//input"));

        //Xpath với Link
        driver.findElement(By.xpath("//a[@href='/newproducts']"));
        driver.findElement(By.xpath("//a[text()='Blog']"));

        //Xpath với Partical_Link
        driver.findElement(By.xpath("//a[contains(@href,'newproducts')]"));
        driver.findElement(By.xpath("//a[contains(text(),'New products')]"));

    }

    @AfterClass
    public void afterClass() {
       //Tắt trình duyệt
        driver.quit();
    }
}
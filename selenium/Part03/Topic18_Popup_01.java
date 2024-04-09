package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic18_Popup_01 {
    WebDriver driver;

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Random_PopUp_NotIn_DOM() {
        driver.get("https://www.javacodegeeks.com/");
        By PopUp = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
        By ClosePopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none']) div.lepopup-element-html-content>a");
        By TextSearch = By.cssSelector("input#search-input");
        By ClickSearch = By.xpath("//button[@id='search-submit']//span[@class='tie-icon-search tie-search-icon']");
       // By TextNotinDOM = By.xpath("//div[@class='lepopup-element-html-content' and text()='Do you want to know how to develop your skillset to become a']");
        //Nếu hiển thị nhảy vào close nó đi
        sleepInsecons(5);
        if (driver.findElements(PopUp).size() > 0 && driver.findElements(PopUp).get(0).isDisplayed()){
            System.out.println("Chưa Hiển thị POPUP");
            driver.findElement(ClosePopup).click();
            sleepInsecons(3);
        }else {
            System.out.println("Không hiển thị");
        }
        //Không hiển thị thì qua step tiếp theo search
            driver.findElement(ClosePopup).click();
            sleepInsecons(3);
        // Search
            driver.findElement(TextSearch).sendKeys("Agile Testing Explained");
        //click nút search
            driver.findElement(ClickSearch).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='post-title']//a[text()='Agile Testing Explained']")).getText(),"Agile Testing Explained");


    }

    @Test
    public void TC_02_Random_PopUp_In_DOM() {
        driver.get("https://vnk.edu.vn/");
        sleepInsecons(30);
        By PopUpInDOM = By.cssSelector("div.tve-leads-conversion-object");
//div.tve_ea_thrive_leads_form_close
        if(driver.findElement(PopUpInDOM).isDisplayed()){
            driver.findElement(By.cssSelector("svg.tcb-icon")).click();
            sleepInsecons(2);
        }else {
            System.out.println("Ko hiển thị");
        }


    }
    //Step nào thao tác màn hình HOME mới gọi cái này
    public WebElement findElement (By Locator)
   {
       if (driver.findElement(By.cssSelector("div.tve-leads-conversion-object")).isDisplayed())
       {
        driver.findElement(By.cssSelector("svg.tcb-icon")).click();

    }
       return driver.findElement(Locator);
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
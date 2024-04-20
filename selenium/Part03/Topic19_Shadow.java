package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic19_Shadow {
    WebDriver driver;


    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        
    }
    @Test
    public void TC_01_Shadow() {
        driver.get("https://automationfc.github.io/shadow-dom/");
        //Tìm element đầu tiên trước shadow-root
        //driver đại diện DOM bên ngoài
        WebElement ShadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));
        //Get cái element vừa tìm được
        //ShadowRootConText đại diện cho shadow DOM bên trong
        SearchContext ShadowRootConText = ShadowHostElement.getShadowRoot();

        String someText = ShadowRootConText.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println("someText");
        Assert.assertEquals(someText,"some text");

        WebElement ShadowCheckBox = ShadowRootConText.findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(ShadowCheckBox.isSelected());

        // muốn tìm tiếp theo cứ xem nó cóa shadow mới không thì tiếp tục tìm rồi get ra
        //lấy ShadowRootConText để đi tìm tiếp cái shadow thứ 2
        WebElement NestedShadowHostElement = ShadowRootConText.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext NestedShadowRootConText = NestedShadowHostElement.getShadowRoot();


        String NestedShadow = NestedShadowRootConText.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        Assert.assertEquals(NestedShadow,"nested text");



    }

    @Test
    public void TC_02_Logo() {
        driver.get("https://skills.kynaenglish.vn/");
        sleepInsecons(3);
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
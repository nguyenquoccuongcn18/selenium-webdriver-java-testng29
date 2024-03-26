package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic08_Handle_Custom_Dropdown {
    WebDriver driver;

   //Wait đến khi nào tìm được dữ liệu không cần sleep (wait tường minh có trạng thái cụ thể cho element)
    //Visible//invisible//number//clickable....
    WebDriverWait explicitWait;

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

    }
    @Test
    public void TC_01_Element() {
        // 1.Clip 1 thẻ để xổ ra hết item
        driver.findElement(By.xpath("//span[@id='number-button']")).click();
        sleepInsecons(3);

        // 1.1 nó xổ ra hết item
        //1.2 nó chỉ xổ ra 1 phần và load thêm
            //chờ cho nó xổ ra hết các item
                //visiable //có trên ui//có trong html
                //presence //chỉ cần có trong html ko cần ui
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));

        List<WebElement> AllItems =driver.findElements(By.cssSelector("ul#number-menu div"));
            //lưu trữ 19 cái item
        //dùng for each cho số nhiều
        for (WebElement item : AllItems){
            String textItem = item.getText();
            System.out.println("Text item" + textItem);
            //nếu như =8  click vào thoát vòng lặp
            if (textItem.equals("18")){
                item.click();
                break;
            }
        }
        //1.3 nếu như item cần chọn có thì click vào
        //1.4 nếu như item phải cần scroll mới chọn đc (angular,react..)
        //1.5 Trc khi click cần ktra item đó đúng khong vaà click



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


}
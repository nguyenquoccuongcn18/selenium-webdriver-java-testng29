package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;


public class Topic26_Wait_Find_Element {
    WebDriver driver;
    WebDriverWait explicitWait ;
    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        //ImplicitWait
        //Set Implicit selenium Ver4.X trở lên
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Set Implicit selenium Ver3.X trở xuống
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));


    }
    @Test
    public void TC_01_FindElement() {
        driver.get("https://www.facebook.com/");

        //Case 1 : Element được tìm thấy chỉ có 1
        //Sẽ không cần chờ hết TimeOut
        //Tìm thấy sẽ trả về 1 WebElement
        //Qua step tiếp theo

        System.out.println("Start datetimenow" + getDateTimeNow());
        driver.findElement(By.cssSelector("input#email"));
        System.out.println("Finish datetimenow" + getDateTimeNow());


        //Case 2 : Element được tìm thấy nhưng có nhiều hơn 1
        //sẽ ko cần chờ hết timeout
        //lấy cái element đầu tiên dù có n node

        driver.findElement(By.cssSelector("input[type='text'],[type=password]")).sendKeys("cuong@gmail.com");

        //Case 3 :Element không được tìm thấy
        //Chờ hết timeout 10s
        //Trong thời gian chờ 10s thì cứ mỗi nữa giây sẽ tìm lại
        //Nếu tìm thấy element thì trả về element rồi qua step tiếp theo
        // Tìm lại k thấy thì fail và throw Exception
        //Các step còn lại k chạy nữa
        driver.findElement(By.cssSelector("input[name='reg_email__ ']"));



    }

    @Test
    public void TC_02_FindElements() {
        List<WebElement> elementList;

        //Case 1 : Element được tìm thấy chỉ có 1
        //ko cần chờ hết timeout 10s
        //Trả về 1 list element chứa đúng 1 element
        System.out.println("Start datetimenow" + getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input#email"));
        System.out.println("List have:" + elementList.size());
        System.out.println("Finish datetimenow" + getDateTimeNow());

        //Case 2 : Element được tìm thấy nhưng có nhiều hơn 1
        //ko cần chờ hết timeout 10s
        //Trả về 1 list element chứa nhiều element
        System.out.println("Start datetimenow" + getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input[type='text'],[type=password]"));
        System.out.println("List have:" + elementList.size());
        System.out.println("Finish datetimenow" + getDateTimeNow());

        //Case 3 :Element không được tìm thấy
        //Chờ hết timeouts 10s
        //Mỗi nữa giây tìm lại 1 lan
        //Nếu trong tgian tìm lại mà thấy element thì cũng trả về List chứa các element đó
        //Nếu hết tgian tìm lại mà ko thấy thì trả về list rỗng và lo đánh fail testcase
        //Qua step tiep theo
        System.out.println("Start datetimenow" + getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input[name='reg_email__ ']"));
        System.out.println("List have:" + elementList.size());
        System.out.println("Finish datetimenow" + getDateTimeNow());





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

    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();

    }
}
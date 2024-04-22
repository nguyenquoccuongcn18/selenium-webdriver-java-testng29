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
import java.util.regex.Pattern;


public class Topic27_Wait_01_Explicit_01_Knowledge {
    WebDriver driver;
    WebDriverWait explicitWait; //Khai báo chưa khởi tạo

    @BeforeClass   //Precondition - Khởi tạo dữ liệu/ data test /page class/variable
    public void BeforeClass() {

        driver = new ChromeDriver();
        //Khởi tạo 1 ExplicitWait có tổng thời gian là 10s và polling là 0.5s mặc định
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Khởi tạo 1 ExplicitWait có tổng thời gian là 10s và polling là 0.3s tự set
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10),Duration.ofMillis(300));
    }
    @Test
    public void TC_01_Element() {
        driver.get("https://www.facebook.com/");
        //Chờ cho 1 Alert Presence trong HTML/DOM trước khi thao tác lên
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        //Chờ cho Element không còn ở trong DOM
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        //Chờ cho Element có ở trong DOM (ko quan tâm có trên UI ko)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //Chờ cho 1 List Element có ở trong DOM (ko quan tâm có trên UI ko)
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));

        //Chờ cho 1 List element có trong DOM
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector(""),By.cssSelector(" ")));

        //Chờ cho 1-n element được hiển thị trên UI
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")),driver.findElement(By.cssSelector(""))));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.cssSelector("")),driver.findElement(By.cssSelector("")), driver.findElement(By.cssSelector(""))));

        //Chờ cho element được phép click : link/button/checkbox/radio..
        explicitWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(""))));
        //Get title trước sau đó get dữliêu
        explicitWait.until(ExpectedConditions.titleIs("document.title"));
        driver.getTitle();

        // kết hợp nhiều đkien
        explicitWait.until(ExpectedConditions.and(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))
        ));

        explicitWait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("")),
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(""))
        ));

        //Chờ cho 1 element có attribute chứa giá trị mong đợi
        //Tương đối
        explicitWait.until(ExpectedConditions.attributeContains(By.cssSelector("#input#search"),"palahocode","Sear.."));

        //Tuyệt đối
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector("#input#search"),"palahocode","Search "));
        //có attribute khác null
        explicitWait.until(ExpectedConditions.attributeToBeNotEmpty(driver.findElement(By.cssSelector("#input#search")),"palahocode"));


        explicitWait.until(ExpectedConditions.domAttributeToBe(driver.findElement(By.cssSelector("#input#search")),"palahocode","Search "));
        explicitWait.until(ExpectedConditions.domPropertyToBe(driver.findElement(By.cssSelector("#input#search")),"palahocode","Search "));

        //Chờ cho 1 element Selected thành công
        //Checkbox/Radio/Dropdown...
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));

        //Chờ 1 element được selected rồi
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),true));

        //Chờ 1 element chưa được selected
        explicitWait.until(ExpectedConditions.elementSelectionStateToBe(By.cssSelector(""),false));

        //explicitWait.until(ExpectedConditions)

        //Name or ID
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(""));

        //Index
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(1));
        //By or Element
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("")));
        explicitWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector(""))));

        //chờ cho 1 element biến mất ko hiển thị trên UI
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        //Cho 1 cái đoạn code JS cần trả về giá trị
        explicitWait.until(ExpectedConditions.jsReturnsValue("return agument[0].validationMess"));

        //Chờ cho 1 đoạn code Js được thực thi k ném ra ngoại lệ nào hết
        //ko throw lỗi : True
        //Có throw lỗi : false
        explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.innerText"));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.innerText")));
        Assert.assertFalse(explicitWait.until(ExpectedConditions.javaScriptThrowsNoExceptions("document.innerText")));

        //Chờ cho SL Element là 1 con số cố định
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(" "),10));

        //Chờ window tab mở ra xem bao nhiêu tab /window
        explicitWait.until(ExpectedConditions.numberOfWindowsToBe(3));

        //Đoạn text tuyệt đối
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.title"),"Mobile"));

        //Text của pattern
        Pattern pattern = Pattern.compile("This is root Mobile",Pattern.CASE_INSENSITIVE);
        explicitWait.until(ExpectedConditions.textMatches(By.cssSelector("div.title"),pattern));

        //Bắt buộc text này có trong DOM HTML
        explicitWait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div.category"), "Mobile"));


        //url tuyet doi
        explicitWait.until(ExpectedConditions.urlToBe("fb.com"));
        //url tương đối
        explicitWait.until(ExpectedConditions.urlContains("fb.com"));
        //Regex chuỗi kí tự
        explicitWait.until(ExpectedConditions.urlMatches("[abc^]"));

        //Chờ cho 1 đkien mà element này nó bị update trạng thái - load lại HTML
        explicitWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("")))));

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
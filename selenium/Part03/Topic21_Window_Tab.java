package Part03;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;


public class Topic21_Window_Tab {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExecutor;


    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();


    }
    @Test
    public void TC_01_Tab_switch_1Tab() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //Lấy id của tab hiện tại
        String BasicFormid = driver.getWindowHandle();
        System.out.println("ID tab đầu tiên " + BasicFormid);

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        SwitchToTabWindowID(BasicFormid);

        //Mở ra được tab Google

            driver.findElement(By.cssSelector("textarea[title='Tìm kiếm']")).sendKeys("selenium");
            sleepInsecons(2);

        String GoogleFormid = driver.getWindowHandle();
        SwitchToTabWindowID(GoogleFormid);


        System.out.println(driver.getTitle());
    }


    @Test
    public void TC_02_SwitchtoTitle() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        //Switch bằng title để qua google / console -> .document.title
        SwitchTitleToTabWindowID("Google");
        driver.findElement(By.cssSelector("textarea[title='Tìm kiếm']")).sendKeys("selenium");
        sleepInsecons(2);

        //Quay về lại trang basicform
        SwitchTitleToTabWindowID("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

        //Switch qua FB
        SwitchTitleToTabWindowID("Facebook - log in or sign up");
        driver.findElement(By.cssSelector("input#email")).sendKeys("Cuongne@gmail");
        sleepInsecons(2);

        //Quay về lại trang basicform
        SwitchTitleToTabWindowID("Selenium WebDriver");


    }
    @Test
    public void TC_03_techpanda(){
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[@normalize-space()='Mobile']")).click();

        driver.findElement(By.xpath("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();
        sleepInsecons(2);

        //switch qua window

        SwitchTitleToTabWindowID("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"COMPARE PRODUCTS");
        sleepInsecons(2);

        //Nếu mở window phải thêm 1 step quay về nếu ko driver vẫn ở kia
        SwitchTitleToTabWindowID("Mobile");
        driver.findElement(By.cssSelector("input#search")).sendKeys("iphone");

    }
    @Test
    public void TC_04_SeleniumVer04_TabWindow(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        System.out.println("Driver BasicForm = " + driver.toString());
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();

        //New 1 tab mới 1 window mới
        WebDriver FaceBookDriver = driver.switchTo().newWindow(WindowType.TAB);
        FaceBookDriver.get("https://www.google.com/");
        System.out.println("Driver Google = " + driver.toString());
        driver.findElement(By.cssSelector("textarea[title='Tìm kiếm']")).sendKeys("selenium");
        sleepInsecons(2);

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

        // Khác giữa ver 3 và 4
        //ver 4 sẽ chủ động mở ra trước cái link nhưng vẫn phải switch về như ver 3
        //FaceBookDriver.get("https://www.google.com/");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    //Hàm SwitchToTabWindowID dùng cho 1 tab swicht qua 1 tab
    public void SwitchToTabWindowID(String ExpectedID){

        //Lấy tất cả tab / windơ id đang có
        Set<String> allIDTab = driver.getWindowHandles();

        //Dùng vòng lặp chạy qua từng id
        for (String id : allIDTab){
            //Nếu như 1 id nào khác với  parentid thì switch vào
            if(!id.equals(ExpectedID)){
                driver.switchTo().window(id);
                //Thoát khỏi vòng lặp k cần ktra các giá trị còn lại nếu có
                break;

            }
        }
    }

    //Hàm SwitchTitleToTabWindowID dùng switch nhiều tab window bằng title
    public void SwitchTitleToTabWindowID(String ExpectedTitle){

        //Lấy tất cả tab / windơ id đang có
        Set<String> allIDTabs = driver.getWindowHandles();

        //Dùng vòng lặp chạy qua từng id
        for (String id : allIDTabs){

            //Switch từng id trước
            driver.switchTo().window(id);

            //LẤy ra từng title truyền vào để switch
            String actualTitle = driver.getTitle();

            //Lấy ra title của tab window hiện tại
            if(actualTitle.equals(ExpectedTitle)){
                break;
            }
        }
    }

    //Nếu mở nhiều tab mà muốn đóng lại chỉ giữ thằng tab đầu tiên thì dùng hàm này
    public void closeAllWindowNotParent (String parentID ){
        Set<String> allIDTabs = driver.getWindowHandles();

        for (String id : allIDTabs){
            //Nếu như 1 id nào khác với  parentid thì switch vào
            if(!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();

            }
        }
        driver.switchTo().window(parentID);

    }
    public void sleepInsecons(long timeInsecons) {
        try {
            Thread.sleep(timeInsecons * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }


    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInsecons(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }
}
package Part03;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic04_WebElement_Command {
    WebDriver driver;

@BeforeClass
public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }
    @Test
    public void TC_01_Element() {
        //XTML Element: Textbox/TextArea/Dropdow/checkbox
        //Tìm trả về 1 Element

        driver.findElement(By.id(""));//**
        driver.findElement(By.id("")).clear(); //**
        driver.findElement(By.id("")).click(); //Dùng để click lên Element //**
        //Tìm từ node cha vào node con có 2 cách
        driver.findElement(By.id("validate")).findElement(By.id("name"));
        driver.findElement(By.id("validate")).findElement(By.cssSelector("name"));

        driver.findElement(By.cssSelector("form#validate input#name "));

        //Trả về nhiều Elements khớp với đkien
        List<WebElement> Test01  = driver.findElements(By.cssSelector(""));

        //Tìm và lưu nó vào 1 biến Element (chưa tương tác)
        //Khi dùng biến này 2 lần trở lên mới khai báo biến
        WebElement fullname = driver.findElement(By.id(""));

        //Dùng xóa dữ liệu trong 1 field cho phép nhập
        //hàm này thường đc dùng trước hàm sendkeys
        fullname.clear();

        //Dùng nhập dữ liệu
        fullname.sendKeys("Cuong");//**

        //
        fullname.getAttribute("value");

        //Dùng để verify 1 checkbox/radio/dropdown đã chọn hay chưa //nếu bắt đầu is trả về kiểu boolean(T/F)
        Assert.assertTrue(driver.findElement(By.id(" ")).isSelected());//**
        Assert.assertFalse(driver.findElement(By.id(" ")).isSelected());

        //Dropdown (default/custom)
        Select select = new Select(driver.findElement(By.id(" ")));

        //Dùng để verify 1 element có hiển thị ko
        Assert.assertTrue(driver.findElement(By.id(" ")).isDisplayed());//**
        Assert.assertFalse(driver.findElement(By.id(" ")).isDisplayed());//**

        //Dùng để verify 1 element có thao tác lên đc ko (ko phải read only)
        Assert.assertTrue(driver.findElement(By.id(" ")).isEnabled());//*
        Assert.assertFalse(driver.findElement(By.id(" ")).isEnabled());//*

        //HTML Element
        //title="Fistname" type="Text"  // muốn lấy ra Firtname truyền vào title or text truyền vào type
        driver.findElement(By.cssSelector("form#validate input#name ")).getAttribute("title");//*
        driver.findElement(By.cssSelector("form#validate input#name ")).getAttribute("type");

        //F12->tab Accesibility/Properties trong element
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute(" check");
        driver.findElement(By.id("")).getDomProperty(" check1");

        //Lấy element so với độ phân giải màn hình
        Point nameText = driver.findElement(By.id("")).getLocation();
        nameText.getX();
        nameText.getY();

        //Location + size


        //Element Style -> lây mã màu ..... font/size/color
        driver.findElement(By.id("")).getCssValue("background-color");//*

        //Location + sizesize của text box dài rộng cao bao nhiêu
        Rectangle sizeTexBox = driver.findElement(By.id("")).getRect();
            //Location
        Point nameText1 = driver.findElement(By.id("")).getLocation();
            //Size
        //Dimension nameTextboxRect = null;
       // Dimension nameText2 = nameTextboxRect.getDimension();

        //Shadow element  (JavascriptExcutor)
        driver.findElement(By.id("")).getShadowRoot();

        //
        driver.findElement(By.id("")).getSize();

        //Từ id/className/name //css/xpath có thể truy ra ngược lại taqname html
        driver.findElement(By.id("firtname")).getTagName(); //input
        driver.findElement(By.cssSelector("firtname")).getTagName();//input
        driver.findElement(By.className("firtname")).getTagName();//p
        driver.findElement(By.xpath("firtname")).getTagName();//ul

        //Element A -> đầu ra của nó là taqname của element A
        String fromListTag = driver.findElement(By.xpath("//*[@class='from-list']")).getTagName();
        //ul
        //Đầu vào của element B sẽ nhận taqname Của  element A làm tham số
        driver.findElement(By.xpath("//div[@class='from-list1']/preceding-siblling::" + fromListTag )).getTagName();




        //
        driver.findElement(By.cssSelector("firtname.ittest01")).getText();//**
        //IT test 01

        //Chụp ảnh bị lỗi và lưu dưới dạng nào
        driver.findElement(By.cssSelector("firtname.ittest01")).getScreenshotAs(OutputType.BASE64);//*
        driver.findElement(By.cssSelector("firtname.ittest01")).getScreenshotAs(OutputType.BYTES);
        driver.findElement(By.cssSelector("firtname.ittest01")).getScreenshotAs(OutputType.FILE);



        //element nào là thẻ form or nằm trong thẻ form
        //hành vi giống phím enter
        //register/login/search
        driver.findElement(By.id("firtname")).submit();//**


        //**
        //getText , getCssValue,getAttribute,click,clear,sendkeys,isSelected,isDisplayed,isEnabled


    }

    @Test
    public void TC_02_Logo() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
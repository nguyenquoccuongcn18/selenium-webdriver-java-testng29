package Part03;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic02_WebBrowser_Commands {
    //Các câu lệnh thao tac với browers
    // .Driver
    WebDriver driver;

    ChromeDriver Chrome;

    //Các câu lệnh thao tác với element
    // .element
    WebElement element;
    //** thường xuyên sử dụng
    //*  ít sử dụng


    public void beforeclass(){
        //M:method C:class i:interface E:Enum A:astract class @:annotation
        //Muốn dùng đựược thì phải khởi tạo
        //Nếu không khởi tạo sẽ gặp lỗi : NullPointerException
         driver = new ChromeDriver();//**

         //selenium ver 3/2/1
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //implicitlyWait ảnh hưởng 2 hàm findElements ,findElement

        //selenium ver 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));//**

        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }
    @Test
    public void TC_01() throws MalformedURLException {
        //findelement:Tìm element trả về 1 element - findelements:Tìm element trả về nhiều element
        //get:mở 1 cái URL
        //close:đóng tab, trình duyệt
        //getCurrentUrl: mở 1 URL
        //getWindowHandle : mở 1 của sổ - getWindowHandles:mở nhiều
        //manage - navigate - switchTo
    //void: không trả về gì - khác void lấy dữ liệu từ brower

    //Mở ra 1 URL bất kì
        //Set trực tiếp vào
        driver.get("https://www.facebook.com/");
        //in ra ID của tab
        System.out.println("Window / TabID=" + driver.getWindowHandle());
        //Khai báo biến rồi gán vào sau
        //nếu nhƯ tẠo bIẾN dÙng 1lan thÌ khÔng nÊn tẠo bIẾN
        //String homepageURL = "https://www.facebook.com/";
        //driver.get(homepageURL);

    //Đóng tab đang thao tác
        driver.close();//*

    //Đóng tất cả các tab
        driver.quit();//**


    //Nó sẽ đi tìm với loại By nào trả về element nếu như được tìm thấy
    //không tìm thấy: thì fail tại step này - throw Exception
        driver.findElement(By.id("email"));
        WebElement emailne = driver.findElement(By.id("fff"));//**


    //Nó sẽ đi tìm với loại By nào trả về element nếu như được tìm thấy List Element
    //khong tìm thấy - không bị fail - trả về 1 list rỗng 0 element
     List<WebElement> emailnee =driver.findElements(By.id("email"));//**
        emailnee.get(1).click();   // lay index ra để dùng click vì findElements số nhiều không dùng được

    //Lấy ra URL của màn hình hiện tại
    driver.getCurrentUrl();//*
    //Lấy ra page source html/css/js của màn hình hiện tại
    //dùng để verify
    driver.getPageSource();
    //Assert.assertTrue(driver.getPageSource().contains("yuyyujffsd");


    //Lấy title của page hiện tại
        driver.getTitle();

    //Lấy ra ID của cửa sổ or tab hiện tại
    driver.getWindowHandle();//*
    driver.getWindowHandles();//*

    //Cookie - get cookies - Fameword
    driver.manage().getCookies();//*
    //get ra những log ở devtool - Fameword
    driver.manage().logs().get(LogType.DRIVER);//*
    //timeout apply cho việc tìm element
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));//**

    //chờ cho page load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    //Set trước khi dùng với thư viện JAVAscriptExcution
        // inject trước 1 đoạn code JS vào element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
    //selenium 4 mới có getpageLoadTimeout,getscriptTimeout,getimplicitlyWait

    //khi chạy full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();//**
        driver.manage().window().minimize();

    //Test reponsive -> get ra cái size
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().window().getSize();

    //Set brower ở vị trí nào so với độ phân giải
        driver.manage().window().setPosition(new Point(605,533));
        driver.manage().window().getPosition();

    //Dùng để chuyển hướng điều hướng trang web
        driver.navigate().back();//bấm trở lại
        driver.navigate().refresh();//F5 lại
        driver.navigate().forward();//bấm tới trên trang web có mũi tên
        //Thao tác với history của web (back/foward)
        driver.navigate().to("fb.com");
        driver.navigate().to(new URL("fb.com"));
        driver.get("fb.com");

    //Lấy ra id của cửa sổ hiện tại//*
    //Handle window tab
        String homepage = driver.getWindowHandle();
        driver.switchTo().window(homepage);



    //Alert/window/frame(iframe) //*
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("fds");
     //switch/handle/
     //indẽ/id/element
        driver.switchTo().frame(0);
        driver.switchTo().frame("45615156");
        driver.switchTo().frame(driver.findElement(By.id("")));
    //switch to về html chứa frame trước đó
    driver.switchTo().defaultContent();
    //Từ frame trong ra frame ngòi chứa nó
    driver.switchTo().parentFrame();

    }
    @Test
    public void Notes(){
        //**
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();//**
        driver.get("");

        driver.findElement(By.id("email"));
        driver.findElement(By.xpath("//input[@id='checkbox']"));

        driver.quit();

    }

    @AfterClass

    public void afterClass() {
        driver.quit();
    }
}

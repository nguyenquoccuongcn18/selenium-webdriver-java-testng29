package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic10_11_Button_Radio_Checkbox {
    WebDriver driver;


    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");

        WebElement RegButton = driver.findElement(By.cssSelector("input.egov-button"));
        //Verify button bị disiable khi chưa click
        Assert.assertFalse(RegButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInsecons(3);

        //Verify button đã đc enable sau khi click checkbox
        Assert.assertTrue(RegButton.isEnabled());

        //Lấy mã màu của button
        String getBackgroundColorCss = RegButton.getCssValue("background-color");
        System.out.println("background-color" + " - " + getBackgroundColorCss.toLowerCase());

//Convert từ String sang RGB sau đó RGB sang Hexa
        //Convert String từ RGB - Color / - background-colorrgba(239, 90, 0, 1) -Cam
        Color loginButtonBackgroundColour = Color.fromString(getBackgroundColorCss);

        //Convert kiểu Hexa
        String RegColorButtonBackgroundHexa = loginButtonBackgroundColour.asHex();
        System.out.println("background-color" + " - " + RegColorButtonBackgroundHexa.toLowerCase());
        Assert.assertEquals(RegColorButtonBackgroundHexa,"#ef5a00");

        //Assert.assertEquals(getBackgroundColorCss);
    }

    @Test
    public void TC_02_Button() {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-item.popup-login-tab-login")).click();

        WebElement LoginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        Assert.assertFalse(LoginButton.isEnabled());
        //chạy thử xem ra màu gì để verify
        System.out.println(LoginButton.getCssValue("background-color"));

        Assert.assertEquals(Color.fromString(LoginButton.getCssValue("background-color")).asHex().toLowerCase(),"#000000");
        System.out.println("backgroundclor" + LoginButton);

        //nhập email và password
        String UsernameLogin="cuong0123@gmail.com",Pass="123444";
        driver.findElement(By.cssSelector("input#login_username")).sendKeys(UsernameLogin);
        driver.findElement(By.cssSelector("input#login_password")).sendKeys(Pass);
        Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

        WebElement LoginUserPass = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        System.out.println(LoginUserPass.getCssValue("background-color"));

        System.out.println("background" + "-" + LoginUserPass);

        Assert.assertEquals(Color.fromString(LoginUserPass.getCssValue("background-color")).asHex().toLowerCase(),"#c92127");

    }

    @Test
    public void TC_03_CheckBox(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By dualZoneCheckBox = By.xpath("//input[@id='eq3']");
        By TrueCheckBox = By.xpath("//input[@id='eq1']");
        sleepInsecons(5);

        //case 1 : Nếu web mở ra check box chưa được chọn
        CheckBoxElement(dualZoneCheckBox);

        //case 2 : Nếu web mở ra checkbox đã được chọn
        UnCheckBoxElement(TrueCheckBox);

        Assert.assertFalse(driver.findElement(By.xpath("//input[@id='eq3']")).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//input[@id='eq1']")).isSelected());

    }
    @Test
    public void TC_04_Radio(){
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By TwoPetrolRadio = By.xpath("//input[@id='engine3']");
        By NoTwoPetrolRadio = By.xpath("//input[@id='engine6']");
        //Click chọn 1 trong 2
        CheckBoxElement(TwoPetrolRadio);

        Assert.assertFalse(driver.findElement(TwoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(NoTwoPetrolRadio).isSelected());

        CheckBoxElement(NoTwoPetrolRadio);

        Assert.assertFalse(driver.findElement(TwoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(NoTwoPetrolRadio).isSelected());


    }
    @Test
    public void test1(){
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        driver.findElement(By.cssSelector("//input[@id='engine3']")).click();
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    //Hàm checkBox và không checkBox
    public void CheckBoxElement(By Xpath){
        //Nếu như element chưa đc chọn thì click
        if (driver.findElement(Xpath).isSelected()){//true mới vào thân hàm if
            driver.findElement(Xpath).click();
        }
        sleepInsecons(3);
    }

    public void UnCheckBoxElement(By Xpath){
        //Nếu như element đã đc chọn thì click lần nữa cho thành bỏ chọn
        if (!driver.findElement(Xpath).isSelected()){//true mới vào thân hàm if
            driver.findElement(Xpath).click();
        }
        sleepInsecons(3);
    }

    public void sleepInsecons(long timeInsecons) {
        try {
            Thread.sleep(timeInsecons * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
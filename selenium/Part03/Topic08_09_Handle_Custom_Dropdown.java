package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic08_09_Handle_Custom_Dropdown {
    WebDriver driver;

   //Wait đến khi nào tìm được dữ liệu không cần sleep (wait tường minh có trạng thái cụ thể cho element)
    //Visible//invisible//number//clickable....
    WebDriverWait explicitWait;

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
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


//1.3 nếu như item cần chọn có thì click vào
// dùng for each cho số nhiều
        for (WebElement item : AllItems){
            String textItem = item.getText();
            System.out.println("Text item" + textItem);
            //nếu như =8  click vào thoát vòng lặp
            if (textItem.equals("18")){
                item.click();
                break;//thoát vòng lặp
            }
        }

        //1.4 nếu như item phải cần scroll mới chọn đc (angular,react..)
        //1.5 Trc khi click cần ktra item đó đúng khong vaà click



    }

    @Test
    public void TC_02_Jquery() {
        driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
        ////Thay vì viết như trên mình tạo ra 1 hàm và sử dụng lại
        SelectItemDropdown("//span[@id='number-button']" ,"ul#number-menu div", "16");
        sleepInsecons(3);

        driver.navigate().refresh();

        SelectItemDropdown("//span[@id='number-button']" ,"ul#number-menu div", "18");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"18");


    }

    @Test
    public void TC_03_React(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        SelectItemDropdown("i.dropdown.icon","span.text","Matt");
        sleepInsecons(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Matt");

    }

    public void TC_04_VueJS(){
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        SelectItemDropdown("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
        sleepInsecons(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");

    }

    @Test void TC_05_Editable(){
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        SelectItemEditableDropdown("input.search","div.item span","Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Belgium");



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

    //Những dữ liệu dùng để truyền vào gọi là tham số
    //khi làm cho dự án khác với hành vi khác thì cần sửa lại hàm cho đúng với hành vi của dự án đó
    public void SelectItemDropdown (String parrenCSS, String ChilrenCSS ,String ItemTextExpected){
        driver.findElement(By.cssSelector(parrenCSS)).click();//"//span[@id='number-button']"
        sleepInsecons(3);

       // explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(ChilrenCSS)));//"ul#number-menu div"
        //List<WebElement> AllItems =driver.findElements(By.cssSelector(ChilrenCSS));//"ul#number-menu div"
            //Tối ưu code vừa Wait vừa tìm Element
        List<WebElement> AllItems =explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(ChilrenCSS)));;
            for (WebElement item : AllItems){
            if (item.getText().equals(ItemTextExpected)){
                item.click();
                break;
            }
        }

    }




    public void SelectItemEditableDropdown (String parrenCSS, String ChilrenCSS ,String ItemTextExpected){
        driver.findElement(By.cssSelector(parrenCSS)).clear();//"//span[@id='number-button']"
        driver.findElement(By.cssSelector(parrenCSS)).sendKeys(ItemTextExpected);
        sleepInsecons(3);

        // explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(ChilrenCSS)));//"ul#number-menu div"
        //List<WebElement> AllItems =driver.findElements(By.cssSelector(ChilrenCSS));//"ul#number-menu div"
        //Tối ưu code vừa Wait vừa tìm Element
        List<WebElement> AllItems =explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(ChilrenCSS)));;
        for (WebElement item : AllItems){
            if (item.getText().equals(ItemTextExpected)){
                item.click();
                break;
            }
        }

    }


}
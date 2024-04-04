package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Topic15_User_Interaction_II {
    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        //Nó đang giả lập hành vi của chuột / bàn phím khi đang chạy ko sử dụng thiết bị này
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }
    @Test
    public void TC_01_Tooltip_Hover() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        actions.moveToElement(ageTextbox).perform();
        sleepInsecons(3);


        //Có thể dùng set timeout để bật debug
        // console-- setTimeout(() => {debugger;}, 3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Logo() {
        driver.get("http://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main'] "))).perform();

        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName'] "))).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(),"Kids Home Bath");


    }

    @Test

    public void TC_03_Fahasa() {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();

        actions.moveToElement(driver.findElement(By.xpath("//span[@class='menu-title' and text()='Đồ Chơi'] "))).perform();


        actions.click(driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[normalize-space()='Board Game']"))).perform();


        Assert.assertEquals(driver.findElement(By.cssSelector("ol li strong")).getText(),"BOARD GAME");


    }

    @Test

    public void TC_0_ClickandHove() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumber.size(), 20);

        //Chọn block ngang dọc
        // chọn 1->15
        actions.clickAndHold(allNumber.get(0))//click lên số 1 và giữ chuột
                .pause(2000)//dừng lại 2s rồi nhả chuột
                .moveToElement(allNumber.get(14))//di chuột trái đến số 15
                .pause(2000)//dừng lại 2s rồi nhả chuột
                .release()//nhả chuột trái
                .perform();//excute action

        //Nhấn ctrl xuống chưa nhả ra
        //actions.keyDown(Keys.DOWN).perform();
        sleepInsecons(2);

        List<String> allNumberTextExpect = new ArrayList<>();
        allNumberTextExpect.add("1");
        allNumberTextExpect.add("2");
        allNumberTextExpect.add("3");
        allNumberTextExpect.add("5");
        allNumberTextExpect.add("6");
        allNumberTextExpect.add("7");
        allNumberTextExpect.add("9");
        allNumberTextExpect.add("10");
        allNumberTextExpect.add("11");
        allNumberTextExpect.add("13");
        allNumberTextExpect.add("14");
        allNumberTextExpect.add("15");

        //Tổng các số đã chọn
        List<WebElement> allnumberselect = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allnumberselect.size(), 12);

        List<String> allnumbertextactual =new ArrayList<>();

        for (WebElement element : allnumberselect){
                allnumbertextactual.add(element.getText());

        }
        Assert.assertEquals(allNumberTextExpect, allnumbertextactual);




    }
    @Test
    public void TC_05_ClickRandom(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allNumber = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumber.size(), 20);
        actions.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(12)).release().perform();

        actions.keyDown(Keys.CONTROL).perform();
        actions.click(allNumber.get(3))
                .click(allNumber.get(11))
                .click(allNumber.get(7))
                .pause(2000)
                .keyUp(Keys.CONTROL).perform();


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
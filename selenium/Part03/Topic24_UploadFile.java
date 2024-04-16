package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;


public class Topic24_UploadFile {
    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    String File2k = "2k.jpg";
    String File4k = "5k.jpg";
    String File5k = "5k.jpg";

    String FilePath2k = projectPath + File.separator + "UploadFile" + File.separator + File2k ;
    String FilePath4k = projectPath + File.separator + "UploadFile" + File.separator + File4k ;
    String FilePath5k = projectPath + File.separator + "UploadFile" + File.separator + File5k ;




    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_Upload_1_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        //File nằm ở đâu thư mục nào
        //Máy khác có chạy đựợc ko // phải lấy được đường dẫn tương đối
        By ElementFile = By.cssSelector("input[type='file']");

        driver.findElement(ElementFile).sendKeys(FilePath2k);
        sleepInsecons(1);
        driver.findElement(ElementFile).sendKeys(FilePath4k);
        sleepInsecons(1);
        driver.findElement(ElementFile).sendKeys(FilePath5k);
        sleepInsecons(1);

        //Veryfile uploadfile success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ File2k +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ File4k +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ File5k +"']")).isDisplayed());


        List<WebElement> StartButton = driver.findElements(By.cssSelector("td>button.start"));

        //classic for
   //     for (int i = 0; i < StartButton.size(); i++) {
   //         StartButton.get(i).click();
    //        sleepInsecons(3);
    //    }

        //for each -> gọn hơn
            for(WebElement button : StartButton){
                    button.click();
                    sleepInsecons(3);

            }

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ File2k +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ File4k +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='"+ File5k +"']")).isDisplayed());

    }

    @Test
    public void TC_02_Upload_Multiple() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By ElementFile = By.cssSelector("input[type='file']");

        driver.findElement(ElementFile).sendKeys(FilePath2k + "\n" + FilePath4k + "\n" + FilePath5k );
        sleepInsecons(3);

        //Veryfile uploadfile success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ File2k +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ File4k +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+ File5k +"']")).isDisplayed());

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
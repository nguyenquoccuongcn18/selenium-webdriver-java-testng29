package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic03_Selenium_Relative_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
        } else {
            System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");
    }


    @Test
    public void TC_01_Url() {
        //Login button
        By LoginButtonBy = By.cssSelector("button-loginbutton");
        WebElement LoginbuttonbtElement = driver.findElement(By.cssSelector("button.login-button"));

         // above nằm trên Loginbuttonby login button
        RelativeLocator.with(By.tagName("label")).above(LoginButtonBy);
        //Element
        RelativeLocator.with(By.tagName("label")).above(LoginbuttonbtElement);

        //Remmemberme checkbox
        By RememberMeCheckBoxBy = By.id("RememberMe");

        // label - nằm trên LoginButtonBy và bên phải RememberMeCheckBoxBy là  Remember me?
        WebElement RememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                .above(LoginButtonBy)
                .toRightOf(RememberMeCheckBoxBy));
        System.out.println(RememberMeTextElement.getText());

    }

    @Test
    public void TC_02_Logo() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
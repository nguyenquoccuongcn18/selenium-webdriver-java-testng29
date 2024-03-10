package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
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
            System.setProperty("webdriver.msedge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
        } else {
            System.setProperty("webdriver.msedge.driver", projectPath + "/browserDrivers/msedgedriver");
        }

        //driver = new FirefoxDriver();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2Fregister");
    }


    @Test
    public void TC_01_Url() {
        //Login button
        By loginButtonBy = By.cssSelector("button.login-button");
        WebElement LoginButtonbtElement = driver.findElement(By.cssSelector("button.login-button"));

         // above nằm trên Loginbuttonby login button
        RelativeLocator.with(By.tagName("label")).above(loginButtonBy);
        //Element
        RelativeLocator.with(By.tagName("label")).above(LoginButtonbtElement);


        //Forgot Password Link 'Nằm bên trái'
        WebElement forgotPasswordLink = driver.findElement(By.cssSelector("span.forgot-password"));

        //Remmemberme checkbox 'nằm bên phải'
        By RememberMeCheckBoxBy = By.id("RememberMe");

        //Password texbox 'nằm dưới below'
        By Password = By.id("Password");


        // label - nằm trên LoginButtonBy và bên phải RememberMeCheckBoxBy là  Remember me? và nằm bên trái forgotpassword
        WebElement RememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy)                                   //nằm bên trên
                .toRightOf(RememberMeCheckBoxBy)                        //nằm bên phải
                .toLeftOf(forgotPasswordLink)                           //nằm bên trái
                .below(Password)                                        // nằm dưới
                .near(forgotPasswordLink))                              // near nằm bên cạnh
                ;
        // in ra được text mà mình muốn tìm
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
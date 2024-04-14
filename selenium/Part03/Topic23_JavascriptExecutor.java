package Part03;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic23_JavascriptExecutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void BeforeClass() {

        driver = new ChromeDriver();
        jsExecutor =(JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //dùng timeout cho JE
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        driver.manage().window().maximize();

    }
    @Test
    public void TC_01_JavascriptExecutor() {
       // driver.get("https://www.facebook.com/");
       // jsExecutor.executeScript("window.location ='http://live.techpanda.org/'");
        executeForBrowser("window.location = 'http://live.techpanda.org/'");

        //Get domain
        String TechpandaDomain = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(TechpandaDomain,"live.techpanda.org");

        //Get URL
        String TechpandaUrl = (String) executeForBrowser("return document.URL;");
        Assert.assertEquals(TechpandaUrl,"http://live.techpanda.org/");

        //Click
        //highlight
        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        //click add compare
        hightlightElement("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]");
        clickToElementByJS("//li[3]//div[1]//div[3]//ul[1]//li[2]//a[1]");
        Assert.assertTrue(getInnerText().contains("The product Sony Xperia has been added to comparison list."));

        //
        hightlightElement("//a[text()='Customer Service']");
        clickToElementByJS("//a[text()='Customer Service']");
        String TechpandaTitle = (String) executeForBrowser("return document.title;");
        Assert.assertEquals(TechpandaTitle,"Customer Service");

        scrollToBottomPage();
        //
        hightlightElement("//input[@id='newsletter']");
        sendkeyToElementByJS("//input[@id='newsletter']","cuong@gmail.com");

        hightlightElement("//button[@type='submit']//span//span[text()='Subscribe']");
        clickToElementByJS("//button[@type='submit']//span//span[text()='Subscribe']");
        Assert.assertTrue(getInnerText().contains("There was a problem with the subscription: This email address is already assigned to another user."));

        //
        navigateToUrlByJS("https://www.facebook.com/");
        Assert.assertEquals(executeForBrowser("return document.domain;"),"facebook.com");







    }

    @Test
    public void TC_02_Logo() {
        driver.get("https://sieuthimaymocthietbi.com/account/register");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();


        // var element =$x("//input[@id='lastName']")[0];
        //element.validationmessage
        //Dùng này ở console để lấy dc message

        Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"),"Please fill out this field.");
        driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Automation");
        driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

    }



    // Hàm JE sẵn có
    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
        sleepInsecons(3);
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInsecons(2);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
        sleepInsecons(3);
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
        jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public String getAttributeInDOM(String locator, String attributeName) {
        return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript(
                "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        return status;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
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
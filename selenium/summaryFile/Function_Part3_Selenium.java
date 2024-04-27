package summaryFile;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Function_Part3_Selenium {
    WebDriver driver;
    WebDriverWait explicitWait;
    Actions actions;
    JavascriptExecutor jsExecutor;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    // WebBrowser Commands
    @Test
    public void WebBrowser_Commands() {
        driver.get("");
        driver.close();
        driver.quit();

        WebElement emailAddressTextbox = driver.findElement(By.id("email"));

        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        checkboxes.get(1).click();

        driver.getCurrentUrl();

        driver.getWindowHandle();

        driver.getWindowHandles();

        driver.manage().getCookies();

        driver.manage().logs().get(LogType.DRIVER);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.manage().window().maximize();

        // Alert/ Window (Tab)/ Frame (iFrame) //*
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");

        // Lấy ra ID của cửa sổ/ tab hiện tại //*
        // Handle Window/ Tab
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        // Switch/ handle frame (iframe) //*
        // Index/ ID (name)/ Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("12332432");
        driver.switchTo().frame(driver.findElement(By.id("")));
    }

    // WebElement Commands
    @Test
    public void WebElement_Commands() {
        driver.findElement(By.id("")).clear();

        driver.findElement(By.id("")).sendKeys("");

        driver.findElement(By.id("")).click();

        Assert.assertTrue(driver.findElement(By.id("")).isSelected());

        Assert.assertTrue(driver.findElement(By.id("")).isDisplayed());

        Assert.assertTrue(driver.findElement(By.id("")).isEnabled());

        driver.findElement(By.id("")).getAttribute("title");

        driver.findElement(By.id("")).getCssValue("background-color");

        driver.findElement(By.cssSelector("address.copyright")).getText();

        driver.findElement(By.cssSelector("address.copyright")).getScreenshotAs(OutputType.BASE64);
    }

    // Textbox Textarea
    @Test
    public void Textbox_TextArea() {
        driver.findElement(By.cssSelector("input#pass")).clear();

        driver.findElement(By.cssSelector("input#email")).sendKeys("ac@gmail.com");

        driver.findElement(By.cssSelector("button#send2")).click();
    }

    // Default Dropdowm - Custom Dropdown
    @Test
    public void Dropdown() {
        // Default Dropdown
        String day = "6", month = "July", year = "2000";
        Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
        dayDropdown.selectByVisibleText(day);

        // Verify dropdown này là Single (ko phải Multiple)
        Assert.assertFalse(dayDropdown.isMultiple());

        new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
        new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

        // Custom Dropdown
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");

        selectItemInEditableDropdown("input.search", "div.item span", "Algeria");

    }

    // Button
    @Test
    public void Button() {
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));

        // Verify login button is disable
        Assert.assertFalse(loginButton.isEnabled());

        // Verify login button = background
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toLowerCase(), "#000000");
    }

    // Checkbox - Radio
    @Test
    public void Checkbox_Radio() {
        // Checkbox
        By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        checkToElement(rearSideCheckbox);
        checkToElement(dualZoneCheckbox);

        Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        unCheckToElement(rearSideCheckbox);
        unCheckToElement(dualZoneCheckbox);

        Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

        // Radio
        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/input");

        checkToElement(twoPetrolRadio);

        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

        checkToElement(twoDieselRadio);

        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());

        // Custom Radio
        By registerRadio = By.xpath("//div[text() ='Đăng ký cho người thân']/preceding-sibling::div/input");

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(registerRadio));

        Assert.assertTrue(driver.findElement(registerRadio).isSelected());
    }

    // Alert
    @Test
    public void Alert() {
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // Accept alert
        alert.accept();

        // Cancel alert
        alert.dismiss();
    }

    // Actions
    @Test
    public void Actions() {
        // Hover
        actions.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();

        // Click
        actions.click();

        // Click and Hold
        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        actions.clickAndHold(allNumbers.get(0)) // Click lên ô số 1 và giữ chuột
                .pause(2000) // Dừng lại 2s
                .moveToElement(allNumbers.get(14)) // Di chuột trái đến số 15
                .pause(2000) // Dừng lại 2s
                .release() // Nhả chuột trái ra
                .perform(); // Execute tất cả các action trên

        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        // Double click
        actions.doubleClick().perform();

        // Right click
        actions.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
    }

    // Popup
    @Test
    public void Popup() {
        // Fixed popup in DOM
        driver.findElement(By.cssSelector("button.login_")).click();
        By loginPopup = By.cssSelector("div[id='modal-login-v1'][style]>div");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("div[id='modal-login-v1'][style] button.close")).click();
        Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());

        // Fixed popup not in DOM
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(driver.findElements(By.cssSelector("div.ReactModal__Content")).size(),0);



        // Random popup in DOM
        By marketingPopup = By.cssSelector("div.tve-leads-conversion-object");

        if(driver.findElement(marketingPopup).isDisplayed()) {
            driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close")).click();
            System.out.println("Popup hiển thị");
        } else {
            System.out.println("Popup ko hiển thị");
        }

        // Random popup not in DOM
        if (driver.findElements(marketingPopup).size() > 0 && driver.findElements(marketingPopup).get(0).isDisplayed()) {
            System.out.println("Popup hiển thị");

            int heightBrowser = driver.manage().window().getSize().getHeight();
            System.out.println("Browser height = " + heightBrowser);

            if(heightBrowser < 1920){
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));
            } else {
                driver.findElement(By.cssSelector("button#clode-popup")).click();
            }
        }

        // Shadow DOM
        driver.get("https://automationfc.github.io/shadow-dom");

        // driver = đại diện cho cái Real DOM (DOM bên ngoài)
        WebElement shadowHostElement = driver.findElement(By.cssSelector("div#shadow_host"));

        // shadowRootContext = đại diện cho cái shadow DOM 1 (DOM bên trong)
        SearchContext shadowRootContext = shadowHostElement.getShadowRoot();

        String someText = shadowRootContext.findElement(By.cssSelector("span#shadow_content>span")).getText();
        System.out.println(someText);
        Assert.assertEquals(someText,"some text");

        WebElement checkboxShadow = shadowRootContext.findElement(By.cssSelector("input[type='checkbox']"));
        Assert.assertFalse(checkboxShadow.isSelected());

        List<WebElement> allInput = shadowRootContext.findElements(By.cssSelector("input"));
        System.out.println(allInput.size());

        // nestedShadowHostElement = đại diện cho cái nested shadow DOM 2 (nằm trong shadow DOM 1)
        WebElement nestedShadowHostElement = shadowRootContext.findElement(By.cssSelector("div#nested_shadow_host"));
        SearchContext nestedShadowRootContext = nestedShadowHostElement.getShadowRoot();

        String nestedText = nestedShadowRootContext.findElement(By.cssSelector("div#nested_shadow_content>div")).getText();
        Assert.assertEquals(nestedText,"nested text");
    }

    // Frame - iFrame
    @Test
    public void Frame_IFrame() {
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));

        // Switch về trang Default/ Parent chứa iframe
        driver.switchTo().defaultContent();
    }

    // Window - Tab
    @Test
    public void Window_Tab() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }
    public void selectItemInEditableDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected); // "span#number-button"
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss))); // "ul#number-menu div"
        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }
    public void checkToElement(By byXpath) {
        // Nếu như element chưa được chọn thì mới click
        if(!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
        }
    }
    public void unCheckToElement(By byXpath) {
        // Nếu như element được chọn rồi thì vào click lần nữa cho thành bỏ chọn
        if(driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
        }
    }
    public void switchToWindowByID (String expectedID) {
        // Lấy ra ID của tất cả window/ tab đang có
        Set<String> allIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua từng ID trong Set ở trên
        for (String id : allIDs) {
            // Nếu như 1 ID nào mà khác với expectedID thì switch vào
            if (!id.equals(expectedID)) {
                driver.switchTo().window(id);
                // Thoát khỏi vòng lặp ko cần kiểm tra các giá trị còn lại (nếu có)
                break;
            }
        }
    }
    public void switchToWindowByTitle (String expectedTitle) {
        // Lấy ra ID của tất cả window/ tab đang có
        Set<String> allIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua Set ID ở trên
        for (String id : allIDs) {
            // Cho switch vào từng id
            driver.switchTo().window(id);

            // Lấy ra title của window/ tab hiện tại
            String actualTitle = driver.getTitle();

            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }
    public void closeAllWindowWithoutParent (String parentID) {
        Set<String> allIDs = driver.getWindowHandles();

        for (String id : allIDs) {
            if (!id.equals(parentID)) {
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }
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
    }
    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }
    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
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
}
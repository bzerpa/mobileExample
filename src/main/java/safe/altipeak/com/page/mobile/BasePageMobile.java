package safe.altipeak.com.page.mobile;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import altipeak.com.interfaces.BasePageInterface;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import javassist.NotFoundException;


class BasePageMobile implements BasePageInterface {

    public AppiumDriver<MobileElement> driver;
    public WebDriverWait wait;

    public BasePageMobile(AppiumDriver<MobileElement> driver) throws InterruptedException {
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    protected void click(WebElement element) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
        try {
            element.click();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void back() throws InterruptedException {
        driver.navigate().back();
    }

    String getText(WebElement element) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    String getValue(WebElement element) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute("value");
    }

    int getSize(List<WebElement> elements) throws InterruptedException {
        if (!elements.isEmpty()) {
            wait.until(ExpectedConditions.visibilityOf(elements.get(0)));
            return elements.size();
        }
        return 0;
    }

    void clearText(WebElement element) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
    }

    boolean selectByText(List<WebElement> elements, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        for (WebElement webElement : elements) {
            if (webElement.getText().equalsIgnoreCase(text)) {
                click(webElement);
                return true;
            }
        }
        return false;
    }

    boolean isEnableElement(WebElement element) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.isEnabled();
    }

    boolean isClickableElement(WebElement element) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.getAttribute("clickable").contains("true");
    }

    boolean isVisibleElement(WebElement element) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return (element.getAttribute("visible").toString().equalsIgnoreCase("true")) ? true : false;
    }

    boolean isDisplayedElement(WebElement element) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.isDisplayed();
    }

    public String sliderValueToOnOff(String value) {
        return (value.equalsIgnoreCase("1")) ? "ON" : "OFF";
    }

    void assertText(WebElement element, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertEquals("The text " + text + " is not equals to " + element.getText(), element.getText(), text);
    }

    void assertTextByAttribute(WebElement element, String attribute, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertEquals("The text " + text + " is not equals to " + element.getAttribute(attribute).toString(), element.getAttribute(attribute).toString(), text);
    }

    void assertTextToast(WebElement element, String text) throws InterruptedException {
        Assert.assertEquals("The text " + text + " is not equals to " + element.getText(), element.getAttribute("name"), text);
    }

    void assertNumbersNotEquals(int actual, int expected) {
        Assert.assertNotSame("The values [" + actual + "] and [" + expected + "] are equals.", actual, expected);
    }

    void assertListTam(int actual, List<WebElement> element) {
        Assert.assertEquals("The values [" + actual + "] and [" + element.size() + "] are not equals." , element.size(), actual);
    }

    void assertNumbersEquals(int actual, int expected) {
        Assert.assertEquals("The values [" + actual + "] and [" + expected + "] are not equals.", actual, expected);
    }

    void assertTextNotEquals(String actual, String expected) {
        Assert.assertNotSame( "The values [" + actual + "] and [" + expected + "] are equals.", actual, expected);
    }

    void assertTextNotPresentInList(List<String> elems, String text) throws InterruptedException {
        for(String result: elems) {
            assertTextNotEquals(result, text);
        }
    }

    void assertTextValue(WebElement element, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
        Assert.assertEquals("The text " + text + " is not equals to " + element.getAttribute("value"), element.getAttribute("value"), text);
    }

    boolean isTextValue(WebElement element, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
        return (element.getAttribute("value").equalsIgnoreCase(text)) ? true : false;
    }

    boolean isTextInTheList(List<WebElement> usersInformationList, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(usersInformationList.get(2)));
        for (WebElement webElement : usersInformationList) {
            if (webElement.getAttribute("label").contentEquals(text)) {
                return true;
            }
        }
        return false;
    }

    boolean isTextInTheListByText(List<WebElement> usersInformationList, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(usersInformationList.get(0)));
        for (WebElement webElement : usersInformationList) {
            if (webElement.getText().contentEquals(text)) {
                return true;
            }
        }
        return false;
    }

    boolean isTextContainsInTheList(List<WebElement> usersInformationList, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(usersInformationList.get(0)));
        for (WebElement webElement : usersInformationList) {
            if (webElement.getAttribute("label").contains(text)) {
                return true;
            }
        }
        return false;
    }



    boolean isTextContainsInTheListByText(List<WebElement> usersInformationList, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(usersInformationList.get(0)));
        for (WebElement webElement : usersInformationList) {
            if (webElement.getText().contains(text)) {
                return true;
            }
        }
        return false;
    }

    boolean selectByTextContains(WebElement element, String text) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(element));
        if (element.getText().contains(text)) {
            return true;
        }
        return false;
    }

    protected void sendKey(WebElement element, String text) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.sendKeys(text);
    }

    public void hideAndroidKeyboard() throws InterruptedException {
        driver.hideKeyboard();

    }

    protected void assertElementPresentDisabled(WebElement element) {
        Assert.assertTrue("Excepted to be false, now i get " + element.getAttribute("enabled") , element.getAttribute("enabled").equalsIgnoreCase("true"));
    }

    public void longPress(WebElement elem) throws InterruptedException {
        TouchAction action = new TouchAction(driver).longPress(longPressOptions().withElement(element(elem)).withDuration(Duration.ofMillis(10000))).release().perform();;
        Thread.sleep(3000);
    }

    private BufferedImage generateImage( MobileElement element, File screenshot) throws IOException {
        BufferedImage fullImage = ImageIO.read(screenshot);
        Point imageLocation = element.getLocation();
        int qrCodeImageWidth = element.getSize().getWidth();
        int qrCodeImageHeight = element.getSize().getHeight();
        int pointXPosition = imageLocation.getX();
        int pointYPosition = imageLocation.getY();
        BufferedImage qrCodeImage = fullImage.getSubimage(pointXPosition, pointYPosition, qrCodeImageWidth, qrCodeImageHeight);
        ImageIO.write(qrCodeImage, "png", screenshot);
        return qrCodeImage;
    }

    public void readQRCode() throws IOException, NotFoundException, Throwable {
        MobileElement qrCodeElement = driver.findElement(By.id("com.eliasnogueira.qr_code:id/qrcode"));
        File screenshot = driver.getScreenshotAs(OutputType.FILE);

        String content = decodeQRCode(generateImage(qrCodeElement, screenshot));
        System.out.println("content = " + content);
    }

    private static String decodeQRCode(BufferedImage qrCodeImage) throws NotFoundException, com.google.zxing.NotFoundException {
        LuminanceSource source = new BufferedImageLuminanceSource(qrCodeImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }

    public void swipeElem(WebElement elem, WebElement elem2) throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver).press(point(elem.getLocation().getX(), elem.getLocation().getY())).waitAction(waitOptions(Duration.ofMillis(2000))).moveTo(point(elem2.getLocation().getX(), elem2.getLocation().getY())).release().perform();
    }

}

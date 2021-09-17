package safe.altipeak.com.page.mobile;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSFindBys;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PinCode extends BasePageMobile {
    public PinCode(AppiumDriver<MobileElement> driver) throws InterruptedException {
        super(driver);
    }
    
    @iOSFindBys(value = {@iOSBy(className = "XCUIElementTypeStaticText")})
    @AndroidFindBys(value = {@AndroidBy(xpath = "//android.widget.Button")})
    private List<WebElement> pinNumber;    
    
    @iOSFindBy(accessibility = "pin-title")
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='pin-title']")
    private WebElement title;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeAlert/**/XCUIElementTypeStaticText[1]")
    @AndroidFindBy(xpath = "(//android.widget.TextView)[1]")
    private WebElement alertTitle;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeAlert/**/XCUIElementTypeStaticText[2]")
    @AndroidFindBy(id = "android:id/message")
    private WebElement alertMessage;

    @iOSFindBy(accessibility = "OK")
    @AndroidFindBy(id = "android:id/button2")
    private WebElement alertButtonOk;
    
    public void enterCodeNumber(String c1, String c2, String c3, String c4) throws InterruptedException {
    	click(driver.findElementByAccessibilityId(c1));
    	click(driver.findElementByAccessibilityId(c2));
    	click(driver.findElementByAccessibilityId(c3));
    	click(driver.findElementByAccessibilityId(c4));
    }
    
    public void tapCodeNumber(String code) throws InterruptedException {
	
	    int number = Integer.parseInt(code);
	    int[] digits = new int[code.length()];
	    int i = digits.length - 1;
	    while (number > 0) {
	        digits[i] = number%10;
	        number = number / 10;
	        i--;
	    }

	    for(int j=0; j < digits.length; j++){
	    	click(driver.findElementByAccessibilityId(Integer.toString(digits[j])));
	     }
    }
    
    public void assertTitle(String value) throws InterruptedException {
    	assertText(title, value);
    }
    
    public void eraseCode() throws InterruptedException {
    	selectByText(pinNumber, "✕");
    }
    
    public void assertAlertMessage(String value) throws InterruptedException {
    	Thread.sleep(1000);
    	assertText(alertMessage, value);
    }
    
    public void assertAlertTitle(String value) throws InterruptedException {
    	Thread.sleep(1000);
    	assertText(alertTitle, value);
    }
    
    public void tapAlertOk() throws InterruptedException {
    	click(alertButtonOk);
    }
    
}

package safe.altipeak.com.page.mobile;

import org.openqa.selenium.WebElement;

import altipeak.com.common.ConfigDictionary;
import altipeak.com.common.Utility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Register extends BasePageMobile {

	public Register(AppiumDriver<MobileElement> driver) throws InterruptedException {
		super(driver);
	}
	
	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeNavigationBar/XCUIElementTypeOther")
	@AndroidFindBy(xpath = "(//android.widget.TextView)[1]")
    private WebElement title;

    @iOSFindBy(xpath = "//XCUIElementTypeTextField[@name='register-sever-url']")
	@AndroidFindBy(xpath ="//android.widget.EditText[@content-desc='register-sever-url']")
    private WebElement urlField;

    @iOSXCUITFindBy(accessibility = "register-user-name")
	@AndroidFindBy(accessibility = "register-user-name")
    private WebElement userIdField;

    @iOSXCUITFindBy(accessibility = "register-password")
	@AndroidFindBy(accessibility = "register-password")
    private WebElement passwordField;
	
    @iOSFindBy(accessibility = "register-button")
	@AndroidFindBy(accessibility = "register-button")
    private WebElement clickToRegisterLink;
	
    @iOSFindBy(xpath = "(//XCUIElementTypeButton[@name='scanqr'])[1]")
	@AndroidFindBy(xpath = "(//android.widget.Button)[2]")
    private WebElement qrCodeButton;

    @iOSFindBy(xpath = "(//XCUIElementTypeImage)[2]")
	@AndroidFindBy(xpath = "(//android.widget.ImageView)[1]")
    private WebElement safewalkIcon;
	
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar//XCUIElementTypeButton")
	@AndroidFindBy(xpath = "//android.widget.ImageButton")
    private WebElement backButton;

    @iOSFindBy(accessibility = "scan-image-button")
	@AndroidFindBy(xpath = "(//android.widget.TextView)[2]")    
    private WebElement tapToScanLabel;
    
	public void clearUrlField() throws InterruptedException {
		clearText(urlField);
	}
	
	public void clearUserIdField() throws InterruptedException {
		clearText(userIdField);
	}
	
	public void clearPasswordField() throws InterruptedException {
		clearText(passwordField);
	}
	
	public void fillUrlField(String value) throws InterruptedException {
		if(ConfigDictionary.getInstance().getCustomizationProperties().getSafewalkUrlEditable().equalsIgnoreCase("true")) {
			if(ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Utility.Device.IOS.getName())) {
				sendKey(urlField, value);
			}
			else {
				sendKey(urlField, "https://" + value);
			}
		}
	}

	public void fillUserIdField(String value) throws InterruptedException {
		sendKey(userIdField, value);
	}
	
	public void fillPasswordField(String value) throws InterruptedException {
		sendKey(passwordField, value);
	}
	
	public void tapOnRegister() throws InterruptedException {
		click(clickToRegisterLink);
	}
	
	public void tapOnScanQRCode() throws InterruptedException {
		click(qrCodeButton);
	}
	

	public void assertTapToScanLabelDisplayed() throws InterruptedException {
		assertElementPresentDisabled(tapToScanLabel);
	}
	
	public void assertTitle(String value) throws InterruptedException {
		assertText(title, value);
	}
	
	
}

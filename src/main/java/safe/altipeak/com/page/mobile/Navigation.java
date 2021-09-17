package safe.altipeak.com.page.mobile;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;

import altipeak.com.common.ConfigDictionary;
import altipeak.com.common.Utility.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class Navigation extends BasePageMobile {

	public Navigation(AppiumDriver<MobileElement> driver) throws InterruptedException {
		super(driver);
	}

	@iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeAlert/**/XCUIElementTypeStaticText[1]")
	@AndroidFindBy(xpath = "(//android.widget.TextView)[1]")
	private WebElement alertTitle;

    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeAlert/**/XCUIElementTypeStaticText[2]")	
	@AndroidFindBy(id = "android:id/message")
	private WebElement alertMessage;

//    @iOSFindBy(xpath = "//XCUIElementTypeAlert//XCUIElementTypeButton")
    @iOSXCUITFindBy(iOSClassChain = "**/XCUIElementTypeAlert/**/XCUIElementTypeButton")	
    @AndroidFindBy(id = "android:id/button2")
	private WebElement alertButtonOk;

    @iOSFindBy(xpath = "  ")
	@AndroidFindBy(id = "toolbar")
	private WebElement toolBar;
  
    @iOSFindBy(xpath  = "(//XCUIElementTypeButton)[2]")    
    private MobileElement allowNotificationsBtn;
    
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Don’t Allow']")    
    private WebElement doNoAllowNotificationsBtn;
    
    @iOSFindBy(xpath="//XCUIElementTypeNavigationBar//XCUIElementTypeStaticText")
    private WebElement navigationBarTitle;
    
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar//XCUIElementTypeButton")
	@AndroidFindBy(xpath = "//android.widget.ImageButton")
    private WebElement navigationBarBackButton;
    
	@AndroidFindBy(id = "com.android.systemui:id/recents_close_all_button")
    private WebElement closeALlAndroidButton;
	
	@iOSFindBy(accessibility = "NotificationCell")
	private WebElement pushNotification;

	@iOSFindBy(xpath = "(//XCUIElementTypeButton)[1]")
	@AndroidFindBy(accessibility = "Menu")	
	private WebElement settingIcon;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement understandTheRiskButton;
    
	public void assertAlertMessage(String value) throws InterruptedException {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())){
			assertText(alertMessage, value);
		}
		else {
			assertText(alertMessage, value);			
		}
	}

	public void assertAlertMessageRefreshingToken(String value) throws InterruptedException {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())){
			assertText(alertMessage, "Are you sure you want to renew the push notification token for the following authenticator?\n" + "\n" + value);
		}
		else {
			assertText(alertMessage, value);			
		}
	}
	
	public void assertAlertMessageDeletingToken(String value) throws InterruptedException {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())){
			assertText(alertMessage, "Do you want to delete the following token?\n" + "\n" + value);
		}
		else {
			assertText(alertMessage, value);			
		}
	}
	
	public void assertAlertTitle(String value) throws InterruptedException {
		assertText(alertTitle, value);
	}

	public void tapAlertOk() throws InterruptedException {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())){
			driver.switchTo().alert().accept();
		}
		else {
			click(alertButtonOk);
		}
	}
	
	public WebElement getAlertOK() throws InterruptedException {
		return alertButtonOk;
	}
	
	public void tapAllowNotifications() throws InterruptedException {
		click(allowNotificationsBtn);
	}
	
	public void tapDoNotAllowNotifications() throws InterruptedException {
		click(doNoAllowNotificationsBtn);
	}
	
	public void tapNavBarBackBtn() throws InterruptedException {
		click(navigationBarBackButton);
	}
	
	public void tapOnSettings() throws InterruptedException {
		click(settingIcon);
	}
	
	public void assertPageTitle(String value) throws InterruptedException {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())){
			assertText(driver.findElement(By.xpath("//XCUIElementTypeNavigationBar//XCUIElementTypeOther")), value);
		}
		else
			assertText(toolBar.findElement(By.className("android.widget.TextView")), value);
	}
	
	@SuppressWarnings("rawtypes")
	public void openPushNotification(String title) throws InterruptedException {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase("iOS")) {
			((EnhancedIOSDriver<MobileElement>) driver).lockDevice();
			((EnhancedIOSDriver<MobileElement>) driver).unlockDevice();
        	((EnhancedIOSDriver<MobileElement>) driver).label("Cel unlocked");
		}
		else {
			((AndroidDriver) driver).openNotifications();
			List<MobileElement> allnotifications = driver.findElements(By.id("android:id/title"));
			for (MobileElement webElement : allnotifications) {
				if(webElement.getText().equalsIgnoreCase(title)){
					webElement.click();
					break;
				}
			}		

		}
	}
	
	public void closeApp() {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())){
			((IOSDriver<MobileElement>)driver).terminateApp(ConfigDictionary.getInstance().getCustomizationProperties().getBundeId());			
		}
		else {
			((AndroidDriver<MobileElement>)driver).terminateApp(ConfigDictionary.getInstance().getCustomizationProperties().getBundeId());			
		}
	}
	
	public void waitUntilLock() throws InterruptedException {
		Thread.sleep(240000);
	}
	
	public void openApp() {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName()))
			((EnhancedIOSDriver)driver).launchApp();
		else
			((EnhancedAndroidDriver) driver).launchApp();
	}
	
	public void tapOnRecentApp(){
		((AndroidDriver<MobileElement>)driver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
	}

	public void tapHome() {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())){
			driver.runAppInBackground(Duration.ofSeconds(10));
			((EnhancedAndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.HOME));
			((IOSDriver<MobileElement>)driver).terminateApp(ConfigDictionary.getInstance().getCustomizationProperties().getBundeId());
		}
		else {
			driver.runAppInBackground(Duration.ofSeconds(-1));
		}
	}

	public void tapCloseAllAndroid() throws InterruptedException {
		click(closeALlAndroidButton);
	}
	
	public void killApp() throws InterruptedException {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())){
			((IOSDriver<MobileElement>)driver).closeApp();			
		}
		else {
			((AndroidDriver<MobileElement>)driver).closeApp();			
		}
    }
	
	public void navigatePushNotification() throws InterruptedException {
		click(pushNotification);
	}
	
	public void acceptAlert() throws InterruptedException{
    	driver.switchTo().alert().accept();
	}
	
	public void acceptCertificateRisks() throws InterruptedException {
		click(understandTheRiskButton);
	}
	
	public WebElement getSslCertButton() throws InterruptedException {
		return this.understandTheRiskButton;
	}

	public String getAppState() {
			return driver.queryAppState(ConfigDictionary.getInstance().getCustomizationProperties().getBundeId()).toString();			
	}


}


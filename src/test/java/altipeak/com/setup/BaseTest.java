package altipeak.com.setup;


import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.SAXException;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;
import com.microsoft.appcenter.appium.Factory;

import altipeak.com.common.ConfigDictionary;
import altipeak.com.common.Translations;
import altipeak.com.common.Utility.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import safe.altipeak.com.page.mobile.Navigation;



public class BaseTest extends Utils {
	
	protected String user = ConfigDictionary.getInstance().getCustomizationProperties().getUserName();
	protected String password = ConfigDictionary.getInstance().getCustomizationProperties().getPassword();
	protected String serverURL = ConfigDictionary.getInstance().getCustomizationProperties().getPredefinedUrl();
	protected String deeplinkServerURL = ConfigDictionary.getInstance().getCustomizationProperties().getDeeplinkUrl();
	protected String wrongUrl = ConfigDictionary.getInstance().getCustomizationProperties().getWrongUrl();
	protected String wrongUser = ConfigDictionary.getInstance().getCustomizationProperties().getWrongUser();
	protected String wrongPassword = ConfigDictionary.getInstance().getCustomizationProperties().getWrongPassword();
	public final String DEEPLINK_REGISTRATION_TYPE = "deeplinkRegType";
	protected List<Translations> translations;


	@Rule
	public TestWatcher watcher = Factory.createWatcher();	
	protected WebDriverWait wait;

	public AppiumDriver<MobileElement> getDriver() {
		return driver;
	}

	@Before
	public void setUp() throws InterruptedException, XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		translations = getTranslationsFromCustomization();
		DeviceSetup.prepareDevice();
		wait = new WebDriverWait(driver, 30);
		driver.resetApp();
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())) {
			Navigation navigation = new Navigation(driver);
			navigation.acceptAlert();
		}
	}

	@After
	public void tearDown() {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())) {
			((EnhancedIOSDriver<MobileElement>) driver).label("Stopping app");
			driver.closeApp();
			driver.removeApp(ConfigDictionary.getInstance().getCustomizationProperties().getBundeId());
			driver.quit();
		}
		else {
			((EnhancedAndroidDriver<MobileElement>) driver).label("Stopping app");
			driver.quit();
		}
	}
}

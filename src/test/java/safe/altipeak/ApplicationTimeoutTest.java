package safe.altipeak;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

import com.microsoft.appcenter.appium.Factory;

import altipeak.com.common.ConfigDictionary;
import altipeak.com.setup.BaseTest;
import safe.altipeak.com.page.mobile.Navigation;
import safe.altipeak.com.page.mobile.PinCode;
import safe.altipeak.com.page.mobile.Settings;

public class ApplicationTimeoutTest extends BaseTest {

	@Rule
    public TestWatcher watcher = Factory.createWatcher();
	
	public ApplicationTimeoutTest() {
		// TODO Auto-generated constructor stub
	}
	
	PinCode pinPage;
	Navigation navigation;
	Settings settingsPage;		
	String code;		

	
	@Before
	public void prepareTimooutTest() throws InterruptedException, IOException {
		pinPage = new PinCode(driver);
		navigation = new Navigation(driver);
		settingsPage = new Settings(driver);		
		code = getRandomNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength()));		

	}
	
	@Test
	public void timeoutWithOpenedApp() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();
        navigation.tapNavBarBackBtn();
        navigation.tapOnSettings();
        settingsPage.tapOnAbout();
        Thread.sleep(240010);
        pinPage.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getCustomPinWarn(), translations));
        navigation.tapAlertOk();
		takeScreenshot("4 minutes timeout with opened app");
		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getPinAuthMsg(), translations));
	}
	
	@Test
	public void timeoutWithAppClosed() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();
        navigation.tapNavBarBackBtn();
        navigation.tapOnSettings();
        settingsPage.tapOnAbout();
        navigation.tapHome();       
        Thread.sleep(240010);
        navigation.openApp();
        pinPage.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getWarningTitle(), translations));
        pinPage.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getCustomPinWarn(), translations));
        navigation.tapAlertOk();
		takeScreenshot("4 minutes timeout with opened app");
		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getPinAuthMsg(), translations));
	}

}

package safe.altipeak;

import static org.junit.Assume.assumeTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

import com.microsoft.appcenter.appium.Factory;

import altipeak.com.api.Association;
import altipeak.com.common.ConfigDictionary;
import altipeak.com.common.Utility.Device_Type;
import altipeak.com.setup.BaseTest;
import safe.altipeak.com.page.mobile.About;
import safe.altipeak.com.page.mobile.Navigation;
import safe.altipeak.com.page.mobile.PinCode;
import safe.altipeak.com.page.mobile.Register;
import safe.altipeak.com.page.mobile.RegisteredInstances;
import safe.altipeak.com.page.mobile.SafewalkFastAuth;
import safe.altipeak.com.page.mobile.Settings;

public class TokenRefreshTest extends BaseTest {

		
	Association association = new Association();
	
	PinCode pinPage;
	Navigation navigation;
	Register registerPage;
	SafewalkFastAuth homePage;
	RegisteredInstances registeredLicensesPage;
	About aboutPage;	
	Settings settingsPage;

	String code;		

	@Rule
	public TestWatcher watcher = Factory.createWatcher();

	@Before
	public void clearAssociations() throws Exception {
		assumeTrue(ConfigDictionary.getInstance().getCustomizationProperties().getOtpSupport().equalsIgnoreCase("true"));
		association.clearAssociation(user);
		association.associate(Device_Type.ASYMM.getName());
		association.associate(Device_Type.ASYMM.getName());       
		association.associate(Device_Type.ASYMM.getName());       
        association.associate(Device_Type.SIGN.getName());
        association.associate(Device_Type.SIGN.getName());
       
		pinPage = new PinCode(driver);
		navigation = new Navigation(driver);
		registerPage = new Register(driver);
		homePage = new SafewalkFastAuth(driver);
		registeredLicensesPage = new RegisteredInstances(driver);
		code = getRandomNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength()));		
		aboutPage = new About(driver);		
		settingsPage = new Settings(driver);
	}
	
	@Test
	public void refreshTokenFromMainPage() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
        homePage.tapOnRegisteredLicenses();
        String serialNumber = registeredLicensesPage.getSerialNumber(0);
        registeredLicensesPage.selectLicense(0);
        registeredLicensesPage.tapResetRegToken();
        navigation.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getWarnTitle(), translations));
        navigation.assertAlertMessageRefreshingToken(serialNumber);
        registeredLicensesPage.tapAcceptInPopup();
        navigation.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getFcmTokenUpdate(), translations));
		takeScreenshot("Token refreshed correctly from Registered Instances Page");
	}

	@Test
	public void refreshTokenFromAboutPage() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
        navigation.tapOnSettings();
        settingsPage.tapOnAbout();
        aboutPage.tapOnRegisteredInstances();
        String serialNumber = registeredLicensesPage.getSerialNumber(0);
        registeredLicensesPage.selectLicense(0);
        registeredLicensesPage.tapResetRegToken();
        navigation.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getWarnTitle(), translations));
        navigation.assertAlertMessageRefreshingToken(serialNumber);
        registeredLicensesPage.tapAcceptInPopup();
        navigation.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getFcmTokenUpdate(), translations));
		takeScreenshot("Token refreshed correctly from About Page");
	}	
}

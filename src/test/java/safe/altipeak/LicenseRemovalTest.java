package safe.altipeak;

import static org.junit.Assume.assumeTrue;

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

public class LicenseRemovalTest extends BaseTest {

	private String user = ConfigDictionary.getInstance().getCustomizationProperties().getUserName();
    private String password = ConfigDictionary.getInstance().getCustomizationProperties().getPassword();
	private String serverURL = ConfigDictionary.getInstance().getCustomizationProperties().getPredefinedUrl();
	Association association = new Association();

	PinCode pinPage;
	Navigation navigation;
	Register registerPage;
	SafewalkFastAuth homePage;
	RegisteredInstances registeredLicensesPage;
	Settings settingsPage;
	About aboutPage;		

	String code;

	@Rule
	public TestWatcher watcher = Factory.createWatcher();

	@Before
	public void clearAssociations() throws Exception {
		assumeTrue(ConfigDictionary.getInstance().getCustomizationProperties().getOtpSupport().equalsIgnoreCase("true"));
		association.clearAssociation(user);
        association.associate(Device_Type.SIGN.getName());       
        association.associate(Device_Type.SIGN.getName());       
        association.associate(Device_Type.SIGN.getName());   
        
		pinPage = new PinCode(driver);
		navigation = new Navigation(driver);
		registerPage = new Register(driver);
		homePage = new SafewalkFastAuth(driver);
		registeredLicensesPage = new RegisteredInstances(driver);
		settingsPage = new Settings(driver);
		aboutPage = new About(driver);		
		code = getRandomNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength()));

	}
	
	@Test
	public void removeLicenseFromMainPage() throws InterruptedException {
		PinCode pinPage = new PinCode(driver);
		Navigation navigation = new Navigation(driver);
		Register registerPage = new Register(driver);
		SafewalkFastAuth homePage = new SafewalkFastAuth(driver);
		RegisteredInstances registeredLicensesPage = new RegisteredInstances(driver);
		String code = getRandomNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength()));
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
        homePage.tapOnRegisteredLicenses();
        String serialNumberToDelete = registeredLicensesPage.getSerialNumber(0);
        System.out.println(serialNumberToDelete);
        registeredLicensesPage.selectLicense(0);
        registeredLicensesPage.tapDelete();
        navigation.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getWarnTitle(), translations));
        navigation.assertAlertMessageDeletingToken(serialNumberToDelete);
        registeredLicensesPage.removeLicense();
        navigation.tapNavBarBackBtn();
        homePage.tapOnRegisteredLicenses();
        registeredLicensesPage.assertSerialNotPresentInList(serialNumberToDelete);
		takeScreenshot("License removed: " + serialNumberToDelete);        
	}
	
	@Test
	public void removeLicenseFromAboutPage() throws InterruptedException {
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
        String serialNumberToDelete = registeredLicensesPage.getSerialNumber(0);
        registeredLicensesPage.selectLicense(0);
        registeredLicensesPage.tapDelete();
        navigation.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getWarnTitle(), translations));
        navigation.assertAlertMessageDeletingToken(serialNumberToDelete);
        registeredLicensesPage.removeLicense();
        navigation.tapNavBarBackBtn();
        aboutPage.tapOnRegisteredInstances();        
        registeredLicensesPage.assertSerialNotPresentInList(serialNumberToDelete);
	}


}

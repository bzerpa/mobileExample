package safe.altipeak;

import java.io.IOException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

import com.microsoft.appcenter.appium.Factory;

import altipeak.com.api.Association;
import altipeak.com.api.Deeplink;
import altipeak.com.common.ConfigDictionary;
import altipeak.com.setup.BaseTest;
import safe.altipeak.com.page.mobile.Navigation;
import safe.altipeak.com.page.mobile.PinCode;
import safe.altipeak.com.page.mobile.Register;

public class ApplicationPinLoginTest extends BaseTest {

	Association association = new Association();
	Deeplink deeplinkReg = new Deeplink();

	@Rule
    public TestWatcher watcher = Factory.createWatcher();
	
	@Before
	public void clearAssociations() throws Exception {
		association.clearAssociation(user);
	}

	@Test
	public void applicationPinLoginTwoAttempts() throws InterruptedException, IOException {
		PinCode pinPage = new PinCode(driver);
		Navigation navigation = new Navigation(driver);
		Register registerPage = new Register(driver);
		String code = getRandomNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength()));
		String wrongCode = generateIncreasingNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength()));
        int attempts = Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getMaximumPinAttempts());
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();
        navigation.killApp();
        navigation.openApp();
        pinPage.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getWarnTitle(), translations));
        pinPage.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getCustomPinWarn(), translations));
        navigation.tapAlertOk();
        pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getPinAuthMsg(), translations));
		pinPage.tapCodeNumber(wrongCode); 
		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrorMessage(), translations) + " " + (attempts-1));
		pinPage.tapCodeNumber(wrongCode); 
		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrorMessage(), translations) + " " + (attempts-2));
		pinPage.tapCodeNumber(code); 
		registerPage.assertTapToScanLabelDisplayed();
        System.out.println(code);
	}

	@Test
	public void applicationPinAfterFourMinutes() throws InterruptedException, IOException {
		PinCode pinPage = new PinCode(driver);
		Navigation navigation = new Navigation(driver);
		Register registerPage = new Register(driver);
        int attempts = Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getMaximumPinAttempts());
        int finalCode = Integer.parseInt(getRandomNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength())));
		int wrongCodeNumber = finalCode++;
		String code = Integer.toString(finalCode);
		String wrongNumber = Integer.toString(wrongCodeNumber);
		Integer.toString(wrongCodeNumber);
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();      
		navigation.closeApp();
        Thread.sleep(240001);
        navigation.openApp();
        pinPage.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getCustomPinWarn(), translations));
		takeScreenshot("Four minutes block");
        navigation.tapAlertOk();
        pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getPinAuthMsg(), translations));
		pinPage.tapCodeNumber(wrongNumber); 
		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrorMessage(), translations) + " " + (attempts-1));
		pinPage.tapCodeNumber(wrongNumber); 
		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrorMessage(), translations) + " " + (attempts-2));
		pinPage.tapCodeNumber(code);
		navigation.closeApp();
        navigation.openApp();
        for (int i=0; i<attempts-1 ; i++) {
    		pinPage.tapCodeNumber(wrongNumber); 
    		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrorMessage(), translations) + " " +Integer.toString(attempts-(i+1)));
        }
        pinPage.tapCodeNumber(code);
		registerPage.assertTapToScanLabelDisplayed();
        navigation.closeApp();
        navigation.openApp();
        for (int i=0; i<attempts ; i++) {
    		pinPage.tapCodeNumber(wrongNumber); 
    		if(attempts-(i+1)!=0) 
        		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrorMessage(), translations) + " " + Integer.toString(attempts-(i+1)));
    		else {
    			pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getMaxAttempts(), translations));

    		}
        }
	}
}

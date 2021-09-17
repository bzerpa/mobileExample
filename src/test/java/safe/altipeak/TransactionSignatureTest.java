package safe.altipeak;

import static org.junit.Assume.assumeTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import altipeak.com.api.Association;
import altipeak.com.api.TransactionSignature;
import altipeak.com.common.ConfigDictionary;
import altipeak.com.common.Utility.Device_Type;
import altipeak.com.setup.BaseTest;
import junit.framework.Assert;
import safe.altipeak.com.page.mobile.Navigation;
import safe.altipeak.com.page.mobile.PinCode;
import safe.altipeak.com.page.mobile.Register;
import safe.altipeak.com.page.mobile.TransactionSignaturePage;

public class TransactionSignatureTest extends BaseTest {
	
	PinCode pinPage;
	Navigation navigation;
	Register registerPage;
	TransactionSignaturePage transactionPage;
	
	private String hash = ConfigDictionary.getInstance().getRepoKeys().getHash();
	private String data = ConfigDictionary.getInstance().getRepoKeys().getHashData();
	private String newTransaction = ConfigDictionary.getInstance().getRepoKeys().getNewTransactionMsg();
	private String code;
	
	Association association = new Association();
	TransactionSignature transaction = new TransactionSignature();
	
	@Before
	public void clearAssociations() throws Exception {
		assumeTrue(ConfigDictionary.getInstance().getCustomizationProperties().getOtpSupport().equalsIgnoreCase("true"));
		association.clearAssociation(user);
		association.associate(Device_Type.SIGN.getName());		
		pinPage = new PinCode(driver);
		navigation = new Navigation(driver);
		registerPage = new Register(driver);
		transactionPage = new TransactionSignaturePage(driver);		
		code = getRandomNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength()));
	}

	@Test 
	public void acceptTransactionSignatureTest() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();   
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
        Assert.assertEquals(200, transaction.pushTransactionSignature(user, hash, data, transactionPage.getAcceptButton()));
		takeScreenshot("Push auth accepted");
	}

	@Test 
	public void revokeTransactionSignatureTest() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();   
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
        transaction.pushTransactionSignature(user, hash, data, transactionPage.getRevokeButton());
        navigation.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getTransactionDeniedMsg(), translations));        
        navigation.tapAlertOk();
		takeScreenshot("Transaction denied");
	}
		
	@Test 
	public void acceptTransactionSignatureTestNavigatingByPushNotification() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();        
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
        navigation.tapHome();
        Assert.assertEquals(200, transaction.pushTransactionSignatureByPushNotification(user, hash, data, transactionPage.getAcceptButton(), driver, newTransaction, navigation.getAlertOK(), code));
        takeScreenshot("Transaction accepted by push notification");
	}
		
	@Test 
	public void revokeTransactionSignatureTestNavigatingByPushNotification() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();        
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
		navigation.tapHome();
		Assert.assertEquals(200, transaction.pushTransactionSignatureByPushNotification(user, hash, data, transactionPage.getRevokeButton(), driver, newTransaction, navigation.getAlertOK(), code));
        navigation.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getTransactionDeniedMsg(), translations));
        navigation.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getTransactionTitle(), translations));
	}

	@Test 
	public void acceptTransactionSignatureTestNavigatingByPushNotificationButExpired() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();        
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
		navigation.tapHome();
        transaction.pushTransactionSignatureByPushNotificationExpired(user, hash, data, transactionPage.getAcceptButton(), driver, newTransaction, navigation.getAlertOK(), code);
        takeScreenshot("Open push notification but auth expired");
        navigation.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrSignFailed(), translations));		
	}
	
	@Test 
	public void revokeTransactionSignatureTestNavigatingByPushNotificationButExpired() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();        
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
		navigation.closeApp();
        transaction.pushTransactionSignatureByPushNotificationExpired(user, hash, data, transactionPage.getRevokeButton(), driver, newTransaction, navigation.getAlertOK(), code);
        navigation.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrSignFailed(), translations));		
        takeScreenshot("Open push notification but auth expired");
	}


	@Test 
	public void acceptWhenExpiredTransactionSignatureTest() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();        
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
        transaction.pushTransactionSignatureExpired(user, hash, data, transactionPage.getAcceptButton());
        takeScreenshot("Tap on Push Notification but expired");
        navigation.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrSignFailed(), translations));		
	}
	
	@Test 
	public void revokeWhenExpiredTransactionSignatureTest() throws InterruptedException, IOException {
		pinPage.tapCodeNumber(code);
		pinPage.tapCodeNumber(code);
        navigation.tapAlertOk();        
    	registerPage.fillUrlField(serverURL);
        registerPage.fillUserIdField(user);
        registerPage.fillPasswordField(password);
        registerPage.tapOnRegister();        	
        navigation.tapAlertOk();
        transaction.pushTransactionSignatureExpired(user, hash, data, transactionPage.getRevokeButton());
        takeScreenshot("Tap on Push Notification but expired");
        navigation.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrSignFailed(), translations));		
	}


}

package altipeak.com.common;

import java.util.Properties;

@SuppressWarnings("serial")
public class CustomizationProperties extends Properties {

	private final String API_BASE = "baseUrl";
	private final String REGISTRATION_TOKEN = "registrationToken";
	private final String AUTHENTICATION_TOKEN=	"authenticationToken";
    private final String USER_NAME = "userName";
    private final String PASSWORD = "userPassword";
    private final String PREDEFINED_URL = "serverUrl";
    private final String CERTIFICATE_CHECK = "certificateCheck";
    private final String SAFEWALK_URL_EDITABLE = "safewalkUrlEditable";
    private final String MAXIMUM_PIN_ATTEMPTS = "maximumPinAttempts";
	private final String PIN_LENGTH = "pinLength";
	private final String OTP_SUPPORT = "otpSupport";
	private final String PUSH_SIGNATURE_SUPPORT = "pushSignatureSupport";
	private final String PUSH_AUTHENTICATION_SUPPORT = "pushAuthenticationSupport";
	private final String QR_AUTHENTICATION_SUPPORT = "qrAuthenticationSupport";
	private final String DEEPLINK_AUTHENTICATION_SUPPORT = "deepLinkAuthenticationSupport";
	private final String DEEPLINK_REGISTRATION_SUPPORT = "deepLinkRegistrationSupport";
	private final String REGISTRATION_FROM_ADD_BUTTON = "registrationFromAddButton";
	private final String QR_CODE_FOR_OTP_REGISTRATION =  "qrCodeForOtpRegistration";
	private final String DELETE_KEYS = "deleteKeys";
	private final String DEFAULT_LANGUAGE = "defaultLanguage";
	private final String DEEPLINK_URL = "deeplinkUrl";
    private final String WRONG_USER = "wrongUser";
    private final String WRONG_PASSWORD = "wrongPassword";
    private final String WRONG_URL = "wrongUrl";
    private final String OPERATING_SYSTEM = "operatingSystem";
    private final String APPLICATION_DIRECTORY = "applicationDirectory";
    private final String APPLICATION_FILE = "applicationFile";
    private final String PLATFORM_VERSION = "platformVersion";
    private final String DEVICE_NAME = "deviceName";
    private final String UDID = "udid";
    private final String AUTOMATION_TEST = "automationTest";
    private final String AUTO_ACCEPT_ALERTS = "autoAcceptAlerts";
    private final String NO_RESET = "noReset";
    private final String NEW_COMMAND_TIMEOUT = "newCommandTimeout";
    private final String APPIUM_SERVER_URL = "appiumServerURL";
    private final String BROWSER = "browser";
    private final String APPLICATION_URL = "applicationURL";
    private final String FULL_RESET = "fullReset";
    private final String CLEAR_SYSTEM_FILES = "clearSystemFiles";
    private final String BUNDLE_ID = "bundleId";
    private final String REGISTRATION_TYPE = "registrationType";
    private final String CUSTOMIZATION_NAME = "customizationName";
    private final String UNIVERSAL_LINK = "universalLink";
    private final String CUSTOM_SKIN = "customSkin";
    private final String DEEPLINK_REG_URL = "deeplinkRegistrationUrl";
    private final String NEW_SIGN_UI = "newSignUi";

    public String getConfigValue(String key) {
        String property = this.getProperty(key);
        if (property == null) {
            System.out.println("Sorry, unable to find " + key);
        }
        return property;
    }

	public CustomizationProperties() {
		// TODO Auto-generated constructor stub
	}
	
    public String getApplicationDirectory() {
        return getConfigValue(APPLICATION_DIRECTORY);
    }

    public String getApplicationFile() {
        return getConfigValue(APPLICATION_FILE);
    }

    public String getPlatformVersion() {
        return getConfigValue(PLATFORM_VERSION);
    }

    public String getDeviceName() {
        return getConfigValue(DEVICE_NAME);
    }

    public String getUDID() {
        return getConfigValue(UDID);
    }

    public String getAutomationTest() {
        return getConfigValue(AUTOMATION_TEST);
    }

    public String getAutoAcceptAlerts() {
        return getConfigValue(AUTO_ACCEPT_ALERTS);
    }

    public String getNoReset() {
        return getConfigValue(NO_RESET);
    }

    public String getFullReset() {
    	return getConfigValue(FULL_RESET);
    }
    
    public String getNewCommandTimeout() {
        return getConfigValue(NEW_COMMAND_TIMEOUT);
    }

    public String getAppiumServerURL() {
        return getConfigValue(APPIUM_SERVER_URL);
    }

    public String getBrowser() {
        return getConfigValue(BROWSER);
    }

    public String getApplicationURL() {
        return getConfigValue(APPLICATION_URL);
    }
    
    public String getClearSystemFiles() {
    	return getConfigValue(CLEAR_SYSTEM_FILES);
    }
    
    public String getBundeId() {
    	return getConfigValue(BUNDLE_ID);
    }


    public String getUserName() {
        return getConfigValue(USER_NAME);
    }

    public String getPassword() {
    	return getConfigValue(PASSWORD);
    }
    
    public String getPredefinedUrl() {
    	return getConfigValue(PREDEFINED_URL);
    }
    
    public String getCertificateCheck() {
    	return getConfigValue(CERTIFICATE_CHECK);
    }
    
    public String getSafewalkUrlEditable() {
    	return getConfigValue(SAFEWALK_URL_EDITABLE);
    }
    
    public String getMaximumPinAttempts() {
    	return getConfigValue(MAXIMUM_PIN_ATTEMPTS);
    }
    
    public String getPinLength() {
    	return getConfigValue(PIN_LENGTH);
    }
    
    public String getOtpSupport() {
    	return getConfigValue(OTP_SUPPORT);
    }
    
    public String getPushSignatureSupport() {
    	return getConfigValue(PUSH_SIGNATURE_SUPPORT);
    }
    
    public String getPushAuthenticationSupport() {
    	return getConfigValue(PUSH_AUTHENTICATION_SUPPORT);
    }
    
    public String getQrAuthenticationSupport() {
    	return getConfigValue(QR_AUTHENTICATION_SUPPORT);
    }
    
    public String getDeeplinkAuthenticationSupport() {
    	return getConfigValue(DEEPLINK_AUTHENTICATION_SUPPORT);
    }
    
    public String getDeeplinkRegistrationSupport() {
    	return getConfigValue(DEEPLINK_REGISTRATION_SUPPORT);
    }
    
    public String getRegistrationFromAddButton() {
    	return getConfigValue(REGISTRATION_FROM_ADD_BUTTON);
    }
    
    public String getQrCodeForOtpRegistration() {
    	return getConfigValue(QR_CODE_FOR_OTP_REGISTRATION);
    }
    
    public String getDeleteKeys() {
    	return getConfigValue(DELETE_KEYS);
    }
    
    public String getDefaultLanguage() {
    	return getConfigValue(DEFAULT_LANGUAGE);
    }

    public String getDeeplinkUrl() {
    	return getConfigValue(DEEPLINK_URL);
    }

    public String getOperatingSystem() {
        return getConfigValue(OPERATING_SYSTEM);
    }
    
    public String getWrongUser() {
    	return getConfigValue(WRONG_USER);
    }
    
    public String getWrongPassword() {
    	return getConfigValue(WRONG_PASSWORD);
    }
    
    public String getWrongUrl() {
    	return getConfigValue(WRONG_URL);
    }
    
    public String getBaseUrl() {
    	return getConfigValue(API_BASE);
    }
    
    public String getRegistrationToken() {
    	return getConfigValue(REGISTRATION_TOKEN);
    }
    
    public String getAuthenticationToken() {
    	return getConfigValue(AUTHENTICATION_TOKEN);
    }
    
    public String getRegistrarionType() {
    	return getConfigValue(REGISTRATION_TYPE);
    }
    
    public String getCustomizationName() {
    	return getConfigValue(CUSTOMIZATION_NAME);
    }
    
    public String getCustomSkin() {
    	return getConfigValue(CUSTOM_SKIN);
    }
    
    public String getUniversalLink() {
    	return getConfigValue(UNIVERSAL_LINK);
    }
    
    public String getDeeplinkRegistrationUrl() {
    	return getConfigValue(DEEPLINK_REG_URL);
    }
    
    public String getNewSignUi() {
    	return getConfigValue(NEW_SIGN_UI);
    }

}

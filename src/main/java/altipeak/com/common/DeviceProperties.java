package altipeak.com.common;

import java.util.Properties;

@SuppressWarnings("serial")
public class DeviceProperties extends Properties {

    // Constants in device properties file
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

    
    public DeviceProperties() {

    }

    public String getConfigValue(String key) {
        String property = this.getProperty(key);
        if (property == null) {
            System.out.println("Sorry, unable to find " + key);
        }
        return property;
    }

    public String getOperatingSystem() {
        return getConfigValue(OPERATING_SYSTEM);
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
}

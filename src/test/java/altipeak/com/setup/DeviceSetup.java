package altipeak.com.setup;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;
import com.microsoft.appcenter.appium.Factory;

import altipeak.com.common.ConfigDictionary;
import altipeak.com.common.Utility.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class DeviceSetup extends BaseTest {

	@Rule
    public TestWatcher watcher = Factory.createWatcher();

    static AppiumDriver<MobileElement> prepareDevice() throws MalformedURLException {
    	
        File appDir = new File(ConfigDictionary.getInstance().getCustomizationProperties().getApplicationDirectory());
        File app = new File(appDir, ConfigDictionary.getInstance().getCustomizationProperties().getApplicationFile());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, ConfigDictionary.getInstance().getCustomizationProperties().getPlatformVersion());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ConfigDictionary.getInstance().getCustomizationProperties().getDeviceName());
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, ConfigDictionary.getInstance().getCustomizationProperties().getAutomationTest());
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, ConfigDictionary.getInstance().getCustomizationProperties().getFullReset());
        capabilities.setCapability(MobileCapabilityType.NO_RESET, ConfigDictionary.getInstance().getCustomizationProperties().getNoReset());
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, ConfigDictionary.getInstance().getCustomizationProperties().getClearSystemFiles());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, ConfigDictionary.getInstance().getCustomizationProperties().getNewCommandTimeout());
        if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())) {
        	capabilities.setCapability(MobileCapabilityType.UDID, ConfigDictionary.getInstance().getCustomizationProperties().getUDID());  	
        	driver = Factory.createIOSDriver(new URL(ConfigDictionary.getInstance().getCustomizationProperties().getAppiumServerURL()), capabilities);
        	return ((EnhancedIOSDriver<MobileElement>) driver);
        } 
        else {
        	driver = Factory.createAndroidDriver(new URL(ConfigDictionary.getInstance().getCustomizationProperties().getAppiumServerURL()), capabilities);
        	return ((EnhancedAndroidDriver<MobileElement>) driver);
        }
    }    

}

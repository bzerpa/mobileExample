package safe.altipeak.com.page.mobile;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;

import altipeak.com.common.ConfigDictionary;
import altipeak.com.common.Utility;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSFindBys;

public class SafewalkFastAuth extends BasePageMobile {
	
	public SafewalkFastAuth(AppiumDriver<MobileElement> driver) throws InterruptedException {
		super(driver);
	}
	
    @iOSFindBys(value = {@iOSBy(xpath = "//XCUIElementTypeStaticText[@name='friendly-name']")})
    @AndroidFindBys(value = {@AndroidBy(xpath = "//android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView")})
    private List<WebElement> serialNumber;
    
    @iOSFindBys(value = {@iOSBy(accessibility = "formatted-code")})
    @AndroidFindBys(value = {@AndroidBy(xpath = "//android.widget.TextView[@content-desc='formatted-code']")})
	private List<WebElement> totpCode;
    
    
    @iOSFindBy(accessibility = "registered-instances-button")
    @AndroidFindBy(accessibility = "registered-instances-button")
    private WebElement registeredLicenses;

	@iOSFindBy(accessibility = "Add")
	@AndroidFindBy(accessibility = "Add")
	private WebElement addBtn;
	
	
	public void tapOnRegisteredLicenses() throws InterruptedException {
		click(registeredLicenses);
	}
	
	public String getSerialNumber(int index) throws InterruptedException {
		return serialNumber.get(index).getText();
	}
	
	public void tapAddBtn() throws InterruptedException {
		click(addBtn);
	}
	
	public void assertRegisteredInstances(String value) throws InterruptedException {
		assertEquals(registeredLicenses.getText().substring(0, 1), value);
	}
	
	public String getRegisteredInstances() throws InterruptedException {
		return registeredLicenses.getText().substring(0, 1);
	}
	
	public void assertRegisteredInstancesNotEquals(String value) throws InterruptedException {
		assertTextNotEquals(registeredLicenses.getText().substring(0, 1), value);
	}
	
	public String getTotpCode(int index) throws InterruptedException {
		if(ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Utility.Device.IOS.getName()))
			return getValue(totpCode.get(index));
		else
			return getText(totpCode.get(index));
	}

}

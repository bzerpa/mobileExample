package safe.altipeak.com.page.mobile;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;

public class SafeAccess extends BasePageMobile {

	public SafeAccess(AppiumDriver<MobileElement> driver) throws InterruptedException {
		super(driver);
	}
	
	@AndroidFindBy(xpath =  "//android.widget.EditText[@text='Password / Code']")
    private WebElement passwordField;

	@AndroidFindBy(xpath =  "//android.widget.EditText[@text='User ID']")
    private WebElement userID;

	@AndroidFindBy(xpath =  "//android.widget.Button[@text='CLICK TO REGISTER']")
	private WebElement clickToRegister;

    @AndroidFindBys(value = {@AndroidBy(xpath = "//android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView")})
    private List<WebElement> serialNumber;
    
    @AndroidFindBys(value = {@AndroidBy(xpath = "//android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView")})
	private List<WebElement> totpCode;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text, 'registered license')]")        
    private WebElement registeredLicenses;

    @AndroidFindBys(value = {@AndroidBy(xpath = "//android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView")})
	private List<WebElement> registeredLicenseType;
	
    @AndroidFindBys(value = {@AndroidBy(xpath = "//android.widget.LinearLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView")})
	private List<WebElement> registeredLicenseName;
	
	public void fillPassword(String value) throws InterruptedException {
		sendKey(passwordField, value);
	}
	
	public void fillUserID(String value) throws InterruptedException {
		sendKey(userID, value);
	}
	
	public void tapOnRegisteredLicenses() throws InterruptedException {
		click(registeredLicenses);
	}

	public void tapOnClickToRegister() throws InterruptedException {
		click(clickToRegister);
	}
	
	public void assertExistingLicences(int existingLicences) throws InterruptedException {
		assertListTam(existingLicences, totpCode);
	}

	
}

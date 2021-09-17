package safe.altipeak.com.page.mobile;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSFindBys;

public class RegisteredInstances extends BasePageMobile {
	
	public RegisteredInstances(AppiumDriver<MobileElement> driver) throws InterruptedException {
		super(driver);
	}
	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Delete']")
	@AndroidFindBy(xpath="(//android.widget.TextView)[1]")
	private WebElement deleteBtn;
	
	@iOSFindBys(value = @iOSBy(xpath ="//XCUIElementTypeStaticText[@name='str']"))
    @AndroidFindBys(value = {@AndroidBy(xpath = "(//android.widget.TextView[@content-desc='str'])")})
    private List<WebElement> serialNumber;
    
	@iOSFindBys(value = @iOSBy(xpath ="//XCUIElementTypeStaticText[@name='type']"))
	@AndroidFindBys(value = {@AndroidBy(xpath = "(//android.widget.TextView[@content-desc='type'])")})
    private List<WebElement> licenseName;
    
	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Delete']")
    @AndroidFindBy(id="android:id/button1")
	private WebElement deleteBtnInPopup;

	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Accept']")
    @AndroidFindBy(id="android:id/button1")
	private WebElement acceptBtnInPopup;

    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Cancel']")
    @AndroidFindBy(id="android:id/button3")
	private WebElement cancelBtnInPopup;

    @AndroidFindBy(xpath = "//android.support.v7.widget.LinearLayoutCompat/android.widget.ImageView")
    private WebElement settingsIcon;
    
	@iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Renew push notification token']")
    @AndroidFindBy(xpath ="(//android.widget.TextView)[2]")
    private WebElement resetRegToken;
    
    public String getLicenseName(int index) throws InterruptedException {
    	return licenseName.get(index).getText();
    }
    
    public String getSerialNumber(int index) throws InterruptedException {
    	String result = serialNumber.get(index).getText().split("\\(")[1];
        return result.replace(result.substring(result.length()-1), "");
    }
    
    public void assertSerialNumber(int index, String value) throws InterruptedException {
    	assertText(serialNumber.get(index), value);
    }
    
    public void selectLicense(int index) throws InterruptedException {
        	click(serialNumber.get(index));
    }

    public void tapDelete() throws InterruptedException {
    	click(deleteBtn);
    }
    
    public List<String> getAllSerialNumbers() throws InterruptedException {
    	List<String> result = new ArrayList<String>();
    	List<MobileElement> serialNum = driver.findElementsByXPath("(//android.widget.TextView[@content-desc='str'])");
    	String serial;
    	for (WebElement item : serialNum) {
    		serial = item.getText().split("\\(")[1];
    		result.add(serial.replace(serial.substring(serial.length()-1), ""));
    	    System.out.println(item.getText());
    	}
    	return result;
    }

    public void removeLicense() throws InterruptedException {
    	click(deleteBtnInPopup);
    }
    
    public void cancelLicenseRemoval() throws InterruptedException {
    	click(cancelBtnInPopup);
    }
    
    public void tapSettingsIcon() throws InterruptedException {
    	click(settingsIcon);
    }
    
    public void tapResetRegToken() throws InterruptedException {
    	click(resetRegToken);
    }
    
    public void tapAcceptInPopup() throws InterruptedException {
    	click(acceptBtnInPopup);
    }
    
    public void assertSerialNotPresentInList(String value) throws InterruptedException {
    	
    	assertTextNotPresentInList(getAllSerialNumbers(), value);
    }

    public void assertExistingLicences(int existingLicences) throws InterruptedException {
		assertListTam(existingLicences, licenseName);
	}
    
    public void swipeRight(int index) throws InterruptedException {
		swipeElem(serialNumber.get(index), licenseName.get(index));
	}
}

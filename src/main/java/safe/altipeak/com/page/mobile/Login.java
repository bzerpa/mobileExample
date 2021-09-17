package safe.altipeak.com.page.mobile;

import org.openqa.selenium.WebElement;

import altipeak.com.common.ConfigDictionary;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Login extends BasePageMobile {

	public Login(AppiumDriver<MobileElement> driver) throws InterruptedException {
		super(driver);
	}

    @iOSFindBy(accessibility = "sign-button")
	@AndroidFindBy(accessibility = "sign-button")
   	private WebElement acceptButton;

    @iOSFindBy(xpath = "(//XCUIElementTypeButton)[1]")
    @AndroidFindBy(xpath = "(//android.widget.Button)[1]")
    private WebElement acceptButtonInNewUi;
    
    @iOSFindBy(accessibility = "reject-button")
	@AndroidFindBy(accessibility = "reject-button")
	private WebElement denyButton;

    @iOSFindBy(xpath = "(//XCUIElementTypeButton)[2]")
    @AndroidFindBy(xpath = "(//android.widget.Button)[2]")
    private WebElement denyButtonInNewUi;

    public void tapOnAccept() throws InterruptedException {
    	if(ConfigDictionary.getInstance().getCustomizationProperties().getNewSignUi().equalsIgnoreCase("true"))
    		click(acceptButtonInNewUi);
    	else
        	click(acceptButton);    		
    }
    
    public void tapOnDeny() throws InterruptedException {
    	if(ConfigDictionary.getInstance().getCustomizationProperties().getNewSignUi().equalsIgnoreCase("true"))
    		click(denyButtonInNewUi);
    	else
    		click(denyButton);
    }
    
    public WebElement getDenyButton() throws InterruptedException {
    	if(ConfigDictionary.getInstance().getCustomizationProperties().getNewSignUi().equalsIgnoreCase("true"))
    		return denyButtonInNewUi;
    	else
    		return denyButton;
    }
    
    public WebElement getAcceptButton() throws InterruptedException {
    	if(ConfigDictionary.getInstance().getCustomizationProperties().getNewSignUi().equalsIgnoreCase("true"))
    		return acceptButtonInNewUi;
    	else
    		return acceptButton;
    }
    
}

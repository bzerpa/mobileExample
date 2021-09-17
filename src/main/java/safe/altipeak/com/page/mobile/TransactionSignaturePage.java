package safe.altipeak.com.page.mobile;

import org.openqa.selenium.WebElement;

import altipeak.com.common.ConfigDictionary;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class TransactionSignaturePage extends BasePageMobile {

	public TransactionSignaturePage(AppiumDriver<MobileElement> driver) throws InterruptedException {
        super(driver);
	}
	
    @iOSFindBy(xpath = "(//XCUIElementTypeButton)[1]")
	@AndroidFindBy(accessibility = "sign-button")
   	private WebElement acceptButton;

    @iOSXCUITFindBy(accessibility = "reject-button")
	@AndroidFindBy(accessibility = "reject-button")
	private WebElement denyButton;

    @iOSFindBy(xpath = "(//XCUIElementTypeButton)[1]")
    @AndroidFindBy(xpath = "(//android.widget.Button)[1]")
    private WebElement acceptButtonInNewUi;
    
    @iOSFindBy(xpath = "(//XCUIElementTypeButton)[2]")
    @AndroidFindBy(xpath = "(//android.widget.Button)[2]")
    private WebElement denyButtonInNewUi;

    public void tapOnAccept() throws InterruptedException {
    	if(ConfigDictionary.getInstance().getCustomizationProperties().getNewSignUi().equalsIgnoreCase("true"))
    		click(acceptButtonInNewUi);
    	else
        	click(acceptButton);    		
    }
    
    public WebElement getAcceptButton() throws InterruptedException {
    	if(ConfigDictionary.getInstance().getCustomizationProperties().getNewSignUi().equalsIgnoreCase("true"))
    		return acceptButtonInNewUi;
    	else
    		return acceptButton;
    }
    
    public void tapOnRevoke() throws InterruptedException {
    	if(ConfigDictionary.getInstance().getCustomizationProperties().getNewSignUi().equalsIgnoreCase("true"))
    		click(denyButtonInNewUi);
    	else
    		click(denyButton);
    }
    
    public WebElement getRevokeButton() throws InterruptedException {
    	if(ConfigDictionary.getInstance().getCustomizationProperties().getNewSignUi().equalsIgnoreCase("true"))
    		return denyButtonInNewUi;
    	else
    		return denyButton;
    }
    


}

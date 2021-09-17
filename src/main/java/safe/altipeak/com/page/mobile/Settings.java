package safe.altipeak.com.page.mobile;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Settings extends BasePageMobile {

	public Settings(AppiumDriver<MobileElement> driver) throws InterruptedException {
		super(driver);
	}
	
    @iOSFindBy(accessibility = "settings-pin-button")
	@AndroidFindBy(xpath = "(//android.widget.Button)[1]")
    private WebElement changePin;

	@iOSFindBy(accessibility = "settings-faq-button")
	@AndroidFindBy(accessibility = "(//android.widget.Button)[2]")
	private WebElement FAQ;
	
	@iOSFindBy(accessibility = "settings-about-button")
	@AndroidFindBy(accessibility = "settings-about-button")
	private WebElement about;
	
	public void tapOnChangePin() throws InterruptedException {
		click(changePin);
	}
	
	
	public void tapOnFAQ() throws InterruptedException {
		click(FAQ);
	}
	
	public void tapOnAbout() throws InterruptedException {
		click(about);
	}
	
	
}

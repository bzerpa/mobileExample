package safe.altipeak.com.page.mobile;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSFindBy;

public class Passcode extends BasePageMobile {

	public Passcode(AppiumDriver<MobileElement> driver) throws InterruptedException {
		super(driver);
	}
	
	@iOSFindBy(id = "alert-title")
	private WebElement alertTitle;

	@iOSFindBy(id = "Passcode field")
	private WebElement passcodeField;
	
	public void fillPasscode(String value) throws InterruptedException {
		sendKey(passcodeField, value);
	}
	
	public void clickDone() throws InterruptedException {
		click(driver.findElement(By.name("done")));
	}
	
}

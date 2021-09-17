package safe.altipeak.com.page.mobile;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class About extends BasePageMobile {
	
	public About(AppiumDriver<MobileElement> driver) throws InterruptedException {
		super(driver);
	}
	
    @iOSFindBy(accessibility = "about-registered-instances")
	@AndroidFindBy(accessibility = "about-registered-instances")
    private WebElement registeredInstancesNumber;
    
    
    public void tapOnRegisteredInstances() throws InterruptedException  {
    	click(registeredInstancesNumber);
    }
    
    public void assertRegisteredInstances(String value) throws InterruptedException {
    	assertText(registeredInstancesNumber, value);
    }
    

}

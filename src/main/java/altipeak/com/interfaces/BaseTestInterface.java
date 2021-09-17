package altipeak.com.interfaces;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public interface BaseTestInterface {

    public AppiumDriver<MobileElement> getDriver();

    public String getTestFailedMsg();

    public void setTestFailedMsg(String testFailedMsg);

    public void setUp() throws MalformedURLException, InterruptedException, FileNotFoundException;

    public void tearDown();

}

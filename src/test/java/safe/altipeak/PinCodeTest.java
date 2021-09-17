package safe.altipeak;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import altipeak.com.common.ConfigDictionary;
import altipeak.com.setup.BaseTest;
import safe.altipeak.com.page.mobile.PinCode;

public class PinCodeTest extends BaseTest {

	PinCode pinPage;
	String increasingCode;
	String descendingCode;
	String repetitiveCode;
	int finalCode;
	int wrongCode;

	@Before
	public void preparePinCodeTest() throws Exception {
		pinPage = new PinCode(driver);
		increasingCode = generateIncreasingNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength()));
		descendingCode = generateDescendingNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength()));
		repetitiveCode = generateRepetitiveCode(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength()), 3);
		finalCode = Integer.parseInt(getRandomNumber(Integer.parseInt(ConfigDictionary.getInstance().getCustomizationProperties().getPinLength())));
		wrongCode = finalCode++;

	}
	@Test
	public void pinCodeTestCases() throws InterruptedException, ClientProtocolException, IOException, XPathExpressionException, ParserConfigurationException, SAXException {
		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getMessagePinDesired(), translations));
		pinPage.tapCodeNumber(increasingCode);
		pinPage.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().pinErrorTitle(), translations));
		pinPage.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrorTrivialPin(), translations));
		takeScreenshot("Increasing basic code");
		pinPage.tapAlertOk();
		pinPage.tapCodeNumber(descendingCode);
		pinPage.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().pinErrorTitle(), translations));
		pinPage.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrorTrivialPin(), translations));
		takeScreenshot("Descending basic code");
		pinPage.tapAlertOk();
		pinPage.tapCodeNumber(repetitiveCode);
		pinPage.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().pinErrorTitle(), translations));
		pinPage.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrorTrivialPin(), translations));
		takeScreenshot("Repetitive basic code");
		pinPage.tapAlertOk();
		pinPage.tapCodeNumber(Integer.toString(finalCode));
		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getConfirmPinMessage(), translations));
		pinPage.tapCodeNumber(Integer.toString(wrongCode));
		pinPage.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().pinErrorTitle(), translations));
		pinPage.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getErrorDiffPinKey(), translations));
		takeScreenshot("Wrong pin code");
		pinPage.tapAlertOk();
		pinPage.tapCodeNumber(Integer.toString(finalCode));
		pinPage.assertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().getConfirmPinMessage(), translations));
		pinPage.tapCodeNumber(Integer.toString(finalCode));
		pinPage.assertAlertTitle(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().pinSuccessTitleKey(), translations));
		pinPage.assertAlertMessage(getMessageByGivenValue(ConfigDictionary.getInstance().getRepoKeys().pinSuccessMessageKey(), translations));
		takeScreenshot("Success code");
	}

}

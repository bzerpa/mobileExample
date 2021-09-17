package altipeak.com.setup;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.binary.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;

import altipeak.com.common.ConfigDictionary;
import altipeak.com.common.Translations;
import altipeak.com.common.Utility;
import altipeak.com.common.Utility.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.connection.ConnectionStateBuilder;

public class Utils {
	
	public static AppiumDriver<MobileElement> driver;
	public final String LANGUAGE_PROPERTY = "language";
	String baseCustomizationUrl = "repoURL";
	String personalToken = "setPersonalGithubToken";
	
	public void takeScreenshot(String label) {
		if (ConfigDictionary.getInstance().getCustomizationProperties().getOperatingSystem().equalsIgnoreCase(Device.IOS.getName())) {
			((EnhancedIOSDriver<MobileElement>) driver).label(label);
		}
		else {
			((EnhancedAndroidDriver<MobileElement>) driver).label(label);
		}
	}

	public int getRandomInt() {
		Random random = new Random();
		return random.nextInt(10);
	}

	public static String getRandomNumber(int digCount) {
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(digCount);
		for(int i=0; i < digCount; i++)
			sb.append((char)('0' + rnd.nextInt(10)));
		return sb.toString();
	}

	public String generateIncreasingNumber(int digitLength) {

		int result = 0;
		for( int temp=1; temp < digitLength+1; temp++){
			result*=10;
			result+=temp;
		}
		return Integer.toString(result);
	}
	public String generateRepetitiveCode(int digitLength, int value) {
		int result = 0;
		for( int temp=1; temp < digitLength+1; temp++){
			result*=10;
			result+=value;
		}
		return Integer.toString(result);
	}

	public String generateDescendingNumber(int digitLength) {

		int result = 0;
		for( int temp=1; temp < digitLength+1; temp++){
			result*=10;
			result+=temp;
		}

		int reversed = 0;
		while(result != 0) {
			int digit = result % 10;
			reversed = reversed * 10 + digit;
			result /= 10;
		}
		return Integer.toString(reversed);
	}

	//no hay un standard para iOS
	public void setOfflineMode() {

		try {
			((EnhancedAndroidDriver<MobileElement>) driver).setConnection(new ConnectionStateBuilder().withWiFiDisabled().build());
			System.out.println("Switching OFF the connection : " + ((EnhancedAndroidDriver<MobileElement>) driver).getConnection());
		} catch (Exception e) {
			System.out.println("Connection could not be switch OFF");
		}
	}

	public List<Translations> getTranslationsFromCustomization() throws InterruptedException, XPathExpressionException, MalformedURLException, SAXException, IOException, ParserConfigurationException {
		String customizationName = ConfigDictionary.getInstance().getCustomizationProperties().getCustomizationName();
		String languageSubName = Utility.getLanguageSubNameForCustomization(customizationName, System.getProperty(LANGUAGE_PROPERTY));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(setTranslationProperties(personalToken, customizationName, languageSubName));
			NodeList nodeList = doc.getElementsByTagName("data");
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("(//data[@name])");
			NodeList nl = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			List<Translations> empList = new ArrayList<Translations>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node currentItem = nl.item(i);
				empList.add(getTranslations(nodeList.item(i), currentItem.getAttributes().getNamedItem("name").getNodeValue()));
			}
			return empList;
		}catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	private static Translations getTranslations(Node node, String value) {
		Translations emp = new Translations();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			emp.setName(getTagValue("value", element));
			emp.setDataName(value);
		}
		return emp;
	}

	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	public static String getMessageByGivenValue(String value, List<Translations> translations) throws InterruptedException {
		try {
			for (Translations tr : translations) {
				if(tr.getData().equalsIgnoreCase(value)) {
					return tr.getName();
				}
			}
			return null;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public InputStream setTranslationProperties(String personalToken, String customizationName, String languageSubName) {
		try {
			URL myURL = new URL(baseCustomizationUrl + customizationName + "/Translations/AppResources." + languageSubName + ".resx");
			URLConnection connection = myURL.openConnection();
			personalToken = personalToken + ":x-oauth-basic";
			String authString = "Basic " + Base64.encodeBase64String(personalToken.getBytes());
			connection.setRequestProperty("Authorization", authString);
			InputStream crunchifyInStream = connection.getInputStream();
			return crunchifyInStream;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

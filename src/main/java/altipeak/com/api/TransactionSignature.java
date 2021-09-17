package altipeak.com.api;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.openqa.selenium.WebElement;

import altipeak.com.common.ConfigDictionary;
import altipeak.com.common.TestThread;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class TransactionSignature extends ApiBase {

	public String baseUrl = ConfigDictionary.getInstance().getCustomizationProperties().getBaseUrl();
	public String authToken = ConfigDictionary.getInstance().getCustomizationProperties().getAuthenticationToken();

	TestThread multi = new TestThread();

	public int pushTransactionSignature(String user, String hash, String data, WebElement elem) throws IOException, InterruptedException {
		disableSslVerification();
		String urlParameters  = "username=" + user + "&hash=" + hash + "&data=" + data;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (baseUrl + "/auth/push_signature/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", authToken);
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}
		multi.runMultithread(con, elem);
        System.out.print("Transaction signature: " +con.getResponseCode());
        return con.getResponseCode();
	}	
	
	public void pushTransactionSignatureExpired(String user, String hash, String data, WebElement elem) throws IOException, InterruptedException {
		disableSslVerification();
		String urlParameters  = "username=" + user + "&hash=" + hash + "&data=" + data;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (baseUrl + "/auth/push_signature/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", authToken);
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}
		multi.runMultithreadToExpire(con, elem);
        System.out.print("Transaction signature: " +con.getResponseCode());
	}	
	
	public int pushTransactionSignatureByPushNotification(String user, String hash, String data, WebElement elem, AppiumDriver<MobileElement> driver, String title, WebElement alertOK, String pinCode) throws IOException, InterruptedException {
		disableSslVerification();
		String urlParameters  = "username=" + user + "&hash=" + hash + "&data=" + data;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (baseUrl + "/auth/push_signature/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", authToken);
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}
		multi.runMultithreadToOpenPushNotification(con, elem, driver, title, alertOK, pinCode);
        System.out.print("Transaction signature: " +con.getResponseCode());
        return con.getResponseCode();
	}	
	
	public void pushTransactionSignatureByPushNotificationExpired(String user, String hash, String data, WebElement elem, AppiumDriver<MobileElement> driver, String title, WebElement alertOK, String pinCode) throws IOException, InterruptedException {
		disableSslVerification();
		String urlParameters  = "username=" + user + "&hash=" + hash + "&data=" + data;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (baseUrl + "/auth/push_signature/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", authToken);
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}
		multi.runMultithreadToOpenPushNotificationExpired(con, elem, driver, title, alertOK, pinCode);
        System.out.print("Transaction signature: " +con.getResponseCode());
	}	
}

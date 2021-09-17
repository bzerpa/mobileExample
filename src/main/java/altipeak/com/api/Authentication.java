package altipeak.com.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.openqa.selenium.WebElement;

import altipeak.com.common.ConfigDictionary;
import altipeak.com.common.TestThread;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Authentication extends ApiBase {

	TestThread multi = new TestThread();
	
	public int totpAuth(String user, String totpCode) throws IOException {
		disableSslVerification();
		String urlParameters  = "username=" + user + "&password=" + totpCode;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (baseUrl + "/auth/authenticate/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", ConfigDictionary.getInstance().getCustomizationProperties().getAuthenticationToken());
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}
		catch(Exception e){
		}
		try(Reader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))){
	        //for (int c; (c = in.read()) >= 0;)
	        //System.out.print((char)c);
		}
		catch(Exception e){				
		}
	    return con.getResponseCode();   
	}
	
	public int authentication(String user, String password, WebElement elem) throws IOException {
		disableSslVerification();
		String urlParameters  = "username=" + user + "&password=" + password;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (ConfigDictionary.getInstance().getCustomizationProperties().getBaseUrl() + "/auth/authenticate/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", ConfigDictionary.getInstance().getCustomizationProperties().getAuthenticationToken());
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}  
		multi.runMultithread(con, elem);
	    System.out.print("Authentication " +con.getResponseCode());
	    return con.getResponseCode();
	}	
	
	public int authenticationExpired(String user, String password, WebElement elem) throws IOException, InterruptedException {
		disableSslVerification();
		String urlParameters  = "username=" + user + "&password=" + password;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (ConfigDictionary.getInstance().getCustomizationProperties().getBaseUrl() + "/auth/authenticate/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", ConfigDictionary.getInstance().getCustomizationProperties().getAuthenticationToken());
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}
		multi.runMultithreadToExpire(con, elem);
	    System.out.print("Multithread Authentication " +con.getResponseCode());
	    return con.getResponseCode();
	}
	
	public int authenticationByPushNotification(String user, String password, WebElement elem, AppiumDriver<MobileElement> driver, String title, WebElement alertOK, String pinCode) throws IOException, InterruptedException {
		disableSslVerification();
		String urlParameters  = "username=" + user + "&password=" + password;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (ConfigDictionary.getInstance().getCustomizationProperties().getBaseUrl() + "/auth/authenticate/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", ConfigDictionary.getInstance().getCustomizationProperties().getAuthenticationToken());
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}
		multi.runMultithreadToOpenPushNotification(con, elem, driver, title, alertOK, pinCode);
	    System.out.print("Authentication " +con.getResponseCode());
	    return con.getResponseCode();
	}
	
	public void authenticationByPushNotificationExpired(String user, String password, WebElement elem, AppiumDriver<MobileElement> driver, String title, WebElement alertOK, String pinCode) throws IOException, InterruptedException {
		disableSslVerification();
		String urlParameters  = "username=" + user + "&password=" + password;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (ConfigDictionary.getInstance().getCustomizationProperties().getBaseUrl() + "/auth/authenticate/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", ConfigDictionary.getInstance().getCustomizationProperties().getAuthenticationToken());
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}
        multi.runMultithreadToOpenPushNotificationExpired(con, elem, driver, title, alertOK, pinCode);
	    System.out.print("Multithread Authentication " +con.getResponseCode());
	}
	    
	public void authenticationTest(String user, String password) throws IOException {
		disableSslVerification();
		String urlParameters  = "username=" + user + "&password=" + password;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (ConfigDictionary.getInstance().getCustomizationProperties().getBaseUrl() + "/auth/authenticate/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", ConfigDictionary.getInstance().getCustomizationProperties().getAuthenticationToken());
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}
		multi.runMultithreadTest(con);
	    System.out.print("Authentication " +con.getResponseCode());	
	}	
}

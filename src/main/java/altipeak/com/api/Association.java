package altipeak.com.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import altipeak.com.common.ConfigDictionary;

public class Association extends ApiBase {


	public void associate(String deviceType) throws IOException {
		disableSslVerification();
		String urlParameters  = "device_type=" + deviceType;
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (baseUrl + "/admin/user/"+ user +"/devices/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		ConfigDictionary.getInstance().getCustomizationProperties().getBaseUrl();
		con.setRequestProperty("Authorization", registrationToken);
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			wr.write( postData );
		}
		StringBuilder sb = new StringBuilder();

		try(BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))){
			String output;
			while ((output = in.readLine()) != null) {
				sb.append(output);
			}
		}
		System.out.println("Association: " + con.getResponseMessage());
	}

	public void clearAssociation(String user) throws IOException {
		disableSslVerification();
		URL url = new URL (baseUrl + "/admin/user/"+ user +"/devices/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("DELETE");
		con.setRequestProperty("Authorization", registrationToken);

		try(Reader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))){
		}
		System.out.println("Clear assoc: " + con.getResponseMessage());
	}


}

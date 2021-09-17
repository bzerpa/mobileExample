package altipeak.com.api;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Deeplink extends ApiBase {

	public void deeplinkRegistration(String user) throws IOException {
		disableSslVerification();
		String urlParameters  = "send_registration_token=false";
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL ("https://148.251.223.25:8443/api/v1/admin/user/"+ user +"/registrationtoken/");
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");		
		con.setRequestProperty("Authorization", "Bearer 9c1a9f0317c9d91720104fa1e7c37e3fa7115007");		
		con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty( "Content-Length", Integer.toString(postDataLength));
		con.setUseCaches( false );
		con.setDoOutput(true);		
		try( DataOutputStream wr = new DataOutputStream( con.getOutputStream())) {
			   wr.write( postData );
		}
		System.out.println("Deeplink: " + con.getResponseMessage());	
	}	
	
}


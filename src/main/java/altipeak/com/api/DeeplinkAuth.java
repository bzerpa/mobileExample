package altipeak.com.api;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import altipeak.com.common.ConfigDictionary;

public class DeeplinkAuth extends ApiBase {

	public String deeplinkAuthentication() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
		disableSslVerification();
		String urlParameters  = "send_registration_token=false";
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		URL url = new URL (baseUrl + "/auth/session_key/");
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
		
		BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
		StringBuilder sb = new StringBuilder();
		String output;
		while ((output = br.readLine()) != null) {
		  sb.append(output);
		}

		if(con.getResponseCode()==200) {
			return sb.toString().split("\\{")[1].split("\\}")[0].split("\\:")[1].split("\\,")[0].split("\\;")[1].split("\\&")[0];
		}
		else {
			return null;			
		}

	}	
}

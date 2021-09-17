package altipeak.com.common;

import java.util.Properties;

@SuppressWarnings("serial")
public class ApiProperties extends Properties {

    private final String BASE_URL = "baseUrl";
    private final String REGISTRATION_URL = "registrationToken";
    private final String AUTHENTICATION_URL = "authenticationToken";
    private final String SERVER_URL = "serverUrl";
    private final String DEEPLINK_TOKEN = "deeplinkToken";
    

    public String getConfigValue(String key) {
        String property = this.getProperty(key);
        if (property == null) {
            System.out.println("Sorry, unable to find " + key);
        }
        return property;
    }

    public String getBaseUrl() {
        return getConfigValue(BASE_URL);
    }
    
    public String getRegistrationToken(){
    	return getConfigValue(REGISTRATION_URL);
    }
    
    public String getAuthenticationToken(){
    	return getConfigValue(AUTHENTICATION_URL);
    }

    public String getServerUrl(){
    	return getConfigValue(SERVER_URL);
    }
    
    public String getDeeplinkToken(){
    	return getConfigValue(DEEPLINK_TOKEN);
    }
}

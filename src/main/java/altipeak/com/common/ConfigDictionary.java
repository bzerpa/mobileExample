package altipeak.com.common;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.codec.binary.Base64;

public class ConfigDictionary {

    public final String FILE_SEPARATOR_PROPERTY = "file.separator";
    public final String DEVICE_PROPERTY = "device";
    public final String USER_PROPERTY = "users";
    public final String API_PROPERTY = "api";
    public final String CUSTOMIZATION_PROPERTY = "customization";
    public final String LANGUAGE_PROPERTY = "language";
    public final String DEEPLINK_REGISTRATION_TYPE = "deeplinkRegType";


    String baseCustomizationUrl = "https://raw.githubusercontent.com/altipeak/safewalk-fast-auth-tests/master/src/main/resources/customization/";
    String personalToken = "a578f631b0c82774c90f942362cd7c582f5e12a3";
    private CustomizationProperties customizationProperties;

    private static ConfigDictionary instance = null;

    public static synchronized ConfigDictionary getInstance() {
        if (instance == null) {
            instance = new ConfigDictionary();
        }
        return instance;
    }

    private ConfigDictionary() {
        try {
            customizationProperties = new CustomizationProperties();
            String customization = System.getProperty(CUSTOMIZATION_PROPERTY);
            String fileCustomizationName = customization + "-config.properties";
            setCustomizationProperties(personalToken, fileCustomizationName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomizationProperties getCustomizationProperties() {
        return customizationProperties;
    }

    public void setCustomizationProperties(String personalToken, String customizationName) {
        try {
            URL myURL = new URL(baseCustomizationUrl + customizationName);
            URLConnection connection = myURL.openConnection();
            personalToken = personalToken + ":x-oauth-basic";
            String authString = "Basic " + Base64.encodeBase64String(personalToken.getBytes());
            connection.setRequestProperty("Authorization", authString);
            InputStream crunchifyInStream = connection.getInputStream();
            customizationProperties.load(crunchifyInStream);
            crunchifyInStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package altipeak.com.common;

import java.util.Properties;

@SuppressWarnings("serial")
public class UsersProperties extends Properties {

    // Constants in users properties file

    private final String USER_NAME = "userName";
    private final String PASSWORD = "password";

    public UsersProperties() {

    }

    public String getConfigValue(String key) {
        String property = this.getProperty(key);
        if (property == null) {
            System.out.println("Sorry, unable to find " + key);
        }

        return property;
    }

    public String getUserName() {
        return getConfigValue(USER_NAME);
    }

    public String getPassword() {
    	return getConfigValue(PASSWORD);
    }

}


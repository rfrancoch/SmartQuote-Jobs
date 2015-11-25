package main.java.com.pernix.smartquote.utils;

import main.java.com.pernix.smartquote.services.MySqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private Properties properties;
    private final Logger logger = LoggerFactory.getLogger(MySqlService.class);

    public PropertiesUtil() {
        initializeProperties();
    }

    public void initializeProperties(){
        properties = new Properties();

        try {
            //ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            properties.load( ClassLoader.getSystemResourceAsStream("email.properties"));
        } catch (IOException e) {
            logger.error("IOException at initializeProperties in PropertiesUtil " + e.getMessage());
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public String getEmailProtocol(){
        return properties.getProperty("smartquote.email.protocol");
    }

    public String getEmailUsername(){
        return properties.getProperty("smartquote.email.user");
    }

    public String getEmailPassword(){
        return properties.getProperty("smartquote.email.password");
    }

    public String getEmailServer(){
        return properties.getProperty("smartquote.email.server");
    }
}


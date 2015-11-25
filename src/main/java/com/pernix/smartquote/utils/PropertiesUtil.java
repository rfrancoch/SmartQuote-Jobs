package main.java.com.pernix.smartquote.utils;

import main.java.com.pernix.smartquote.services.MySqlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Properties;
import static main.java.com.pernix.smartquote.constants.PropertiesUtilConstant.*;

public class PropertiesUtil {

    private Properties properties;
    private final Logger logger = LoggerFactory.getLogger(MySqlService.class);

    public PropertiesUtil() {
        initializeProperties();
    }

    public void initializeProperties(){
        properties = new Properties();

        try {
            properties.load( ClassLoader.getSystemResourceAsStream(PROPERTIES));
        } catch (IOException e) {
            logger.error("IOException at initializeProperties in PropertiesUtil " + e.getMessage());
        }
    }

    public Properties getProperties() {
        return this.properties;
    }

    public String getEmailProtocol(){
        return properties.getProperty(PROTOCOL);
    }

    public String getEmailUsername(){
        return properties.getProperty(USER);
    }

    public String getEmailPassword(){
        return properties.getProperty(PASSWORD);
    }

    public String getEmailServer(){
        return properties.getProperty(SERVER);
    }
}


package main.java.com.pernix.smartquote.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;

public class ResourceManager {
    private final static Logger logger = LoggerFactory.getLogger(ResourceManager.class);


    public static InputStream getResourceAsInputStream(String resourceName){
        InputStream inputStreamFile = ClassLoader.getSystemResourceAsStream(resourceName);
        return inputStreamFile;
    }

    public static URL getResourceUrl(String resourceName){
        return ClassLoader.getSystemResource(resourceName);
    }

    public static String getFileAsString(String filename) {
        InputStream inputStream = getResourceAsInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String result = "";
        String line;
        try {
            while ((line = br.readLine()) != null) {
                result = result.concat(line+"\r\n");
            }
            br.close();
        } catch (IOException e) {
            logger.error("IOException at getFileAsString in ResourceManager " + e.getMessage());
        }
        return result;
    }
}

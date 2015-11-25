package main.java.com.pernix.smartquote.resources;

import java.io.*;
import java.net.URL;

public class ResourceManager {

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
            e.printStackTrace();
        }
        return result;
    }
}

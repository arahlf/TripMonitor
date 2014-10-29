package com.arahlf.tripmonitor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    
    public static Properties loadProperties(String propertiesFilePath) throws IOException {
        InputStream inputStream = new FileInputStream(propertiesFilePath);
        Properties properties = new Properties();
        
        try {
            properties.load(inputStream);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException dontCare) {
            }
        }
        
        return properties;
    }
}

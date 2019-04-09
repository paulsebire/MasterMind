package com.ocr.paul;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * this class only contains the loader of the config file
 * its attribute is the path of config.properties
 */
public class Config {
    String path;

    public Config(String path) {
        this.path = path;
    }

    /**
     * this method load the file config.properties as properties in order to read (in main)
     * the different values of variable.
     * @return an instance of the properties class
     */
    public Properties load(){
        Properties properties = new Properties();
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(path);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}

package com.ocr.paul;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    String path;

    public Config(String path) {
        this.path = path;
    }

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

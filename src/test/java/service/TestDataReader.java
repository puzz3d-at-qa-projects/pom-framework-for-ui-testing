package service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("env"));

    public static String getTestData(String key){
        return resourceBundle.getString(key);
    }
}

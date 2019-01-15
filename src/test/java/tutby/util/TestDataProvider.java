package tutby.util;

import framework.PropertyReader;
import framework.utils.CSVUtils;
import framework.utils.SQLUtils;
import framework.utils.XMLUtils;

import java.util.Properties;

public class TestDataProvider {
    private static TestDataProvider instance;
    private static Properties properties;
    private final String DATA_PROVIDER = PropertyReader.getPropertyOrDefault("dataProvider", "MYSQL").toUpperCase();
    private static final String FILE_PATH = PropertyReader.getProperty("dataStorageFilePath");
    private static final String FILE_NAME = PropertyReader.getProperty("dataStorageFileName");

    private TestDataProvider() {
        properties = new Properties();
        initTestData();
    }

    private void initTestData() {
        switch (DATA_PROVIDER) {
            case "XML":
                properties = XMLUtils.readData(FILE_PATH, FILE_NAME);
                break;
            case "CSV":
                properties = CSVUtils.getProperties(FILE_PATH, FILE_NAME);
                break;
            case "MYSQL":
                properties = SQLUtils.getProperties();
                break;
        }
    }

    public static String getProperty(String propertyName) {
        if (instance == null) {
            instance = new TestDataProvider();
        }
        return properties.containsKey(propertyName) ? properties.getProperty(propertyName) : "";
    }
}

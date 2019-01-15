package framework.utils;

import framework.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class XMLUtils {
    private static Log log = Log.getInstance();

    public static Properties readData(final String FILE_PATH, final String FILE_NAME) {
        File file = new File(String.format("%s%s", FILE_PATH, FILE_NAME));
        Properties properties = new Properties();
        try (FileInputStream fileInput = new FileInputStream(file)) {
            properties.loadFromXML(fileInput);
        } catch (IOException e) {
            log.error("loc.err.xml");
        }
        return properties;
    }
}

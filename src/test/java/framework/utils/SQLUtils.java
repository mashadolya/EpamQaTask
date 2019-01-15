package framework.utils;

import framework.Log;

import java.sql.ResultSet;
import java.util.Properties;

public class SQLUtils {
    private static Log log = Log.getInstance();
    private static final String JDBC_DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String QUERY = "select Name, Value from property";
    private static final String PROP_KEY = "Name";
    private static final String PROP_VALUE = "Value";

    public static Properties getProperties() {
        Properties properties = new Properties();
        DBConnectFactory dbConnectFactory = new DBConnectFactory(JDBC_DRIVER_NAME);
        ResultSet rs = dbConnectFactory.getResultSet(QUERY);
        fillProperties(rs, properties, dbConnectFactory);
        return properties;
    }

    private static void fillProperties(ResultSet resultSet, Properties properties, DBConnectFactory dbConnectFactory) {
        try {
            while (resultSet.next()) {
                properties.put(resultSet.getString(PROP_KEY), resultSet.getString(PROP_VALUE));
            }
        } catch (Exception ex) {
            log.error("loc.err.db.no.rs");
        } finally {
            dbConnectFactory.closeConnection();
        }
    }
}

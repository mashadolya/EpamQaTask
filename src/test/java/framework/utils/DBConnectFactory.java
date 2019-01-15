package framework.utils;

import framework.Log;
import framework.PropertyReader;

import java.sql.*;

public class DBConnectFactory {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private Log log = Log.getInstance();
    private final String URL_DB = "urlDB";
    private final String USER_DB = "userDB";
    private final String PASSWORD_DB = "passwordDB";

    public DBConnectFactory(String jdbcDriverName) {
        String url = PropertyReader.getProperty(URL_DB);
        String user = PropertyReader.getProperty(USER_DB);
        String password = PropertyReader.getProperty(PASSWORD_DB);
        try {
            Class.forName(jdbcDriverName);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            log.error("loc.err.db.con");
        }
    }

    public ResultSet getResultSet(String query) {
        resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            log.error("loc.err.db.no.rs");
        }
        return resultSet;
    }

    public void closeConnection() {
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            log.error("loc.err.db.con.close");
        }
    }
}

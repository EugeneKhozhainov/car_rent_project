package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtils {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String hostName = "localhost";
            String dbName = "car_rent";
            String userName = "root";
            String password = "root";

            String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useSSL=false";

            Connection connection = DriverManager.getConnection(connectionURL, userName, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

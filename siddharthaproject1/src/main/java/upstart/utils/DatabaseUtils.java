package upstart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/customer_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";  // Update this according to your MySQL setup

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("Database connected: " + (connection != null));  // Ensure connection is not null
        return connection;
    }
}


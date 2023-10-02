package jm.task.core.jdbc.util;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Log
public class Util {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/jdbc_task";
    private static final String USER_NAME = "PatesM";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
            log.info("Connection DONE!\n");
        } catch (SQLException e) {
            log.severe("Connection ERROR!\n" + e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
            log.info("Connection CLOSED!\n");
        } catch (SQLException e) {
            log.severe("Connection ERROR!\n" + e.getMessage());
        }
    }
}

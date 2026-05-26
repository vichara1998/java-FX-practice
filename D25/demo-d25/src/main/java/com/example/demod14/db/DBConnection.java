package com.example.demod14.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Assign to instance variable (FIXED)
        this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javafx_phone",
                "root",
                "9801"
        );
    }

    public static DBConnection getDBConnection() throws SQLException, ClassNotFoundException {
        if (dbConnection == null) {
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }

    // Getter method (ADDED)
    public Connection getConnection() {
        return connection;
    }
}
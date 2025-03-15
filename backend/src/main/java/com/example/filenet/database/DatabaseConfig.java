package com.example.filenet.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simulates FileNet Database Storage.
 * Supports H2 (for testing), PostgreSQL, or MySQL.
 */
public class DatabaseConfig {
    private static final String JDBC_URL = "jdbc:h2:mem:filenetdb";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, "sa", "");
    }
}
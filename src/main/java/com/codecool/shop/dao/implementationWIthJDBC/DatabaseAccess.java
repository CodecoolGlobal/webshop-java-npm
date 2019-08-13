package com.codecool.shop.dao.implementationWIthJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract class DatabaseAccess {
    private static final String DATABASE = "jdbc:postgresql://localhost:5432/webshop";
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    DATABASE,
                    DB_USER,
                    DB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("ERROR: Connection error.");
            e.printStackTrace();
        }
        return null;
    }
}


package com.mycompany.modul8.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL
            = "jdbc:mysql://localhost:3306/alexander_cipta_karya";

    private static final String USER = "root";

    private static final String PASSWORD = "Khilmaa*2103";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

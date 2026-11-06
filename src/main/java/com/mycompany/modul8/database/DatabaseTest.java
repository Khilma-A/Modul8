package com.mycompany.modul8.database;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseTest {

    public static void main(String[] args) {

        try (Connection connection = DatabaseConnection.getConnection()) {

            if (connection != null && connection.isValid(2)) {
                System.out.println("Koneksi database berhasil!");
                System.out.println("Database: alexander_cipta_karya");
                System.out.println("Status: Terhubung");
            }

        } catch (SQLException e) {
            System.out.println("Koneksi database gagal!");
            System.out.println("Pesan error: " + e.getMessage());
        }
    }
}

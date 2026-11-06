package com.mycompany.modul8.model;

import com.mycompany.modul8.database.DatabaseConnection;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkOrderModel {

    public List<WorkOrder> getAllWorkOrders() throws SQLException {

        List<WorkOrder> workOrders = new ArrayList<>();

        String sql = "SELECT * FROM work_order ORDER BY id DESC";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                WorkOrder workOrder = new WorkOrder(
                        result.getInt("id"),
                        result.getString("nama_pekerjaan"),
                        result.getString("pemohon"),
                        result.getDate("tanggal").toLocalDate(),
                        result.getString("deskripsi"),
                        result.getBigDecimal("harga"),
                        result.getString("status")
                );

                workOrders.add(workOrder);
            }
        }

        return workOrders;
    }

    public boolean addWorkOrder(WorkOrder workOrder) throws SQLException {

        String sql = "INSERT INTO work_order "
                + "(nama_pekerjaan, pemohon, tanggal, deskripsi, harga, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, workOrder.getNamaPekerjaan());
            statement.setString(2, workOrder.getPemohon());
            statement.setDate(3, java.sql.Date.valueOf(workOrder.getTanggal()));
            statement.setString(4, workOrder.getDeskripsi());

            BigDecimal harga = workOrder.getHarga() == null
                    ? BigDecimal.ZERO : workOrder.getHarga();

            statement.setBigDecimal(5, harga);
            statement.setString(6, workOrder.getStatus());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean updateWorkOrder(WorkOrder workOrder) throws SQLException {

        String sql = "UPDATE work_order SET "
                + "nama_pekerjaan = ?, "
                + "pemohon = ?, "
                + "tanggal = ?, "
                + "deskripsi = ?, "
                + "harga = ?, "
                + "status = ? "
                + "WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, workOrder.getNamaPekerjaan());
            statement.setString(2, workOrder.getPemohon());
            statement.setDate(3, java.sql.Date.valueOf(workOrder.getTanggal()));
            statement.setString(4, workOrder.getDeskripsi());

            BigDecimal harga = workOrder.getHarga() == null
                    ? BigDecimal.ZERO : workOrder.getHarga();

            statement.setBigDecimal(5, harga);
            statement.setString(6, workOrder.getStatus());
            statement.setInt(7, workOrder.getId());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean deleteWorkOrder(int id) throws SQLException {

        String sql = "DELETE FROM work_order WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        }
    }
}

package com.mycompany.modul8.model;

import com.mycompany.modul8.database.DatabaseConnection;
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
                        result.getString("nomor_wo"),
                        result.getString("nama_pekerjaan"),
                        result.getString("pemohon"),
                        result.getDate("tanggal").toLocalDate(),
                        result.getString("deskripsi"),
                        result.getString("status")
                );

                workOrders.add(workOrder);
            }
        }

        return workOrders;
    }

    public boolean addWorkOrder(WorkOrder workOrder) throws SQLException {

        String sql = "INSERT INTO work_order "
                + "(nomor_wo, nama_pekerjaan, pemohon, tanggal, "
                + "deskripsi, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, workOrder.getNomorWO());
            statement.setString(2, workOrder.getNamaPekerjaan());
            statement.setString(3, workOrder.getPemohon());
            statement.setDate(4,
                    java.sql.Date.valueOf(workOrder.getTanggal()));
            statement.setString(5, workOrder.getDeskripsi());
            statement.setString(7, workOrder.getStatus());

            return statement.executeUpdate() > 0;
        }
    }
}

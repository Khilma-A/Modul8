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
}

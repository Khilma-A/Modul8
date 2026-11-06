package com.mycompany.modul8.view;

import com.mycompany.modul8.controller.WorkOrderController;
import com.mycompany.modul8.model.WorkOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class WorkOrderView extends JPanel {

    private final WorkOrderController controller;
    private final DefaultTableModel tableModel;
    private final JTable workOrderTable;

    public WorkOrderView() {
        controller = new WorkOrderController();

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Daftar Work Order");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        String[] columns = {
            "ID", "Nomor WO", "Nama Pekerjaan",
            "Pemohon", "Tanggal", "Prioritas", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        workOrderTable = new JTable(tableModel);
        workOrderTable.setRowHeight(28);
        workOrderTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        JScrollPane scrollPane = new JScrollPane(workOrderTable);

        JButton refreshButton = new JButton("Muat Ulang Data");
        refreshButton.addActionListener(e -> loadWorkOrders());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(refreshButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadWorkOrders();
    }

    public void loadWorkOrders() {
        tableModel.setRowCount(0);

        try {
            List<WorkOrder> workOrders = controller.getAllWorkOrders();

            for (WorkOrder workOrder : workOrders) {
                Object[] row = {
                    workOrder.getId(),
                    workOrder.getNomorWO(),
                    workOrder.getNamaPekerjaan(),
                    workOrder.getPemohon(),
                    workOrder.getTanggal(),
                    workOrder.getStatus()
                };

                tableModel.addRow(row);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal memuat data Work Order: " + e.getMessage(),
                    "Kesalahan Database",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

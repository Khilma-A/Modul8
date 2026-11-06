package com.mycompany.modul8.view;

import com.mycompany.modul8.controller.WorkOrderController;
import com.mycompany.modul8.model.WorkOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
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
            "Pemohon", "Tanggal", "Deskripsi", "Status"
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

        JButton addButton = new JButton("Tambah Work Order");
        addButton.addActionListener(e -> showAddForm());

        JButton editButton = new JButton("Edit Work Order");
        editButton.addActionListener(e -> showEditForm());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);

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
                    workOrder.getDeskripsi(),
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

    private void showAddForm() {
        JTextField nomorWOField = new JTextField();
        JTextField namaPekerjaanField = new JTextField();
        JTextField pemohonField = new JTextField();
        JTextField tanggalField = new JTextField();
        JTextArea deskripsiArea = new JTextArea(3, 20);

        JComboBox<String> statusBox = new JComboBox<>(
                new String[]{"Pending", "Diproses", "Selesai"}
        );

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));

        formPanel.add(new JLabel("Nomor WO:"));
        formPanel.add(nomorWOField);

        formPanel.add(new JLabel("Nama Pekerjaan:"));
        formPanel.add(namaPekerjaanField);

        formPanel.add(new JLabel("Pemohon:"));
        formPanel.add(pemohonField);

        formPanel.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        formPanel.add(tanggalField);

        formPanel.add(new JLabel("Deskripsi:"));
        formPanel.add(new JScrollPane(deskripsiArea));

        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(
                this,
                formPanel,
                "Tambah Work Order",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                String nomorWO = nomorWOField.getText().trim();
                String namaPekerjaan = namaPekerjaanField.getText().trim();
                String pemohon = pemohonField.getText().trim();
                String tanggalText = tanggalField.getText().trim();
                String deskripsi = deskripsiArea.getText().trim();

                if (nomorWO.isEmpty() || namaPekerjaan.isEmpty()
                        || pemohon.isEmpty() || tanggalText.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Nomor WO, nama pekerjaan, pemohon, dan tanggal wajib diisi."
                    );
                    return;
                }

                LocalDate tanggal = LocalDate.parse(tanggalText);

                WorkOrder workOrder = new WorkOrder(
                        0,
                        nomorWO,
                        namaPekerjaan,
                        pemohon,
                        tanggal,
                        deskripsi,
                        statusBox.getSelectedItem().toString()
                );

                boolean success = controller.addWorkOrder(workOrder);

                if (success) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Work Order berhasil ditambahkan."
                    );
                    loadWorkOrders();
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Work Order gagal ditambahkan."
                    );
                }

            } catch (java.time.format.DateTimeParseException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Format tanggal tidak valid. Gunakan YYYY-MM-DD."
                );
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Gagal menyimpan Work Order: " + e.getMessage(),
                        "Kesalahan Database",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void showEditForm() {
        int selectedRow = workOrderTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data Work Order yang ingin diubah terlebih dahulu."
            );
            return;
        }

        int id = Integer.parseInt(
                tableModel.getValueAt(selectedRow, 0).toString()
        );

        JTextField nomorWOField = new JTextField(
                tableModel.getValueAt(selectedRow, 1).toString()
        );

        JTextField namaPekerjaanField = new JTextField(
                tableModel.getValueAt(selectedRow, 2).toString()
        );

        JTextField pemohonField = new JTextField(
                tableModel.getValueAt(selectedRow, 3).toString()
        );

        JTextField tanggalField = new JTextField(
                tableModel.getValueAt(selectedRow, 4).toString()
        );

        JTextArea deskripsiArea = new JTextArea(
                tableModel.getValueAt(selectedRow, 5).toString(), 3, 20
        );

        JComboBox<String> statusBox = new JComboBox<>(
                new String[]{"Pending", "Diproses", "Selesai"}
        );

        statusBox.setSelectedItem(
                tableModel.getValueAt(selectedRow, 7).toString()
        );

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));

        formPanel.add(new JLabel("Nomor WO:"));
        formPanel.add(nomorWOField);

        formPanel.add(new JLabel("Nama Pekerjaan:"));
        formPanel.add(namaPekerjaanField);

        formPanel.add(new JLabel("Pemohon:"));
        formPanel.add(pemohonField);

        formPanel.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        formPanel.add(tanggalField);

        formPanel.add(new JLabel("Deskripsi:"));
        formPanel.add(new JScrollPane(deskripsiArea));

        formPanel.add(new JLabel("Status:"));
        formPanel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(
                this,
                formPanel,
                "Edit Work Order",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                String nomorWO = nomorWOField.getText().trim();
                String namaPekerjaan = namaPekerjaanField.getText().trim();
                String pemohon = pemohonField.getText().trim();
                String tanggalText = tanggalField.getText().trim();
                String deskripsi = deskripsiArea.getText().trim();

                if (nomorWO.isEmpty() || namaPekerjaan.isEmpty()
                        || pemohon.isEmpty() || tanggalText.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Semua kolom wajib diisi kecuali deskripsi."
                    );
                    return;
                }

                LocalDate tanggal = LocalDate.parse(tanggalText);

                WorkOrder workOrder = new WorkOrder(
                        id,
                        nomorWO,
                        namaPekerjaan,
                        pemohon,
                        tanggal,
                        deskripsi,
                        statusBox.getSelectedItem().toString()
                );

                boolean success = controller.updateWorkOrder(workOrder);

                if (success) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Work Order berhasil diperbarui."
                    );
                    loadWorkOrders();
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Work Order gagal diperbarui."
                    );
                }

            } catch (java.time.format.DateTimeParseException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Format tanggal tidak valid. Gunakan YYYY-MM-DD."
                );
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Gagal memperbarui Work Order: " + e.getMessage(),
                        "Kesalahan Database",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}

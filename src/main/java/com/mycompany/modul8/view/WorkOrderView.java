package com.mycompany.modul8.view;

import com.mycompany.modul8.controller.WorkOrderController;
import com.mycompany.modul8.model.WorkOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
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
            "ID", "Nama Pekerjaan", "Pemohon",
            "Tanggal", "Deskripsi", "Harga (Rp)", "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        workOrderTable = new JTable(tableModel);
        workOrderTable.setRowHeight(32);
        workOrderTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );
        workOrderTable.setAutoCreateRowSorter(true);
        workOrderTable.setFillsViewportHeight(true);

        workOrderTable.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 12)
        );
        workOrderTable.getTableHeader().setReorderingAllowed(false);

        workOrderTable.getColumnModel().getColumn(0).setPreferredWidth(40);
        workOrderTable.getColumnModel().getColumn(1).setPreferredWidth(180);
        workOrderTable.getColumnModel().getColumn(2).setPreferredWidth(140);
        workOrderTable.getColumnModel().getColumn(3).setPreferredWidth(100);
        workOrderTable.getColumnModel().getColumn(4).setPreferredWidth(220);
        workOrderTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        workOrderTable.getColumnModel().getColumn(6).setPreferredWidth(100);

        JScrollPane scrollPane = new JScrollPane(workOrderTable);

        JButton addButton = new JButton("Tambah Work Order");
        addButton.addActionListener(e -> showAddForm());

        JButton editButton = new JButton("Edit Work Order");
        editButton.addActionListener(e -> showEditForm());

        JButton deleteButton = new JButton("Hapus Work Order");
        deleteButton.addActionListener(e -> deleteWorkOrder());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

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
                    workOrder.getNamaPekerjaan(),
                    workOrder.getPemohon(),
                    workOrder.getTanggal(),
                    workOrder.getDeskripsi(),
                    workOrder.getHarga(),
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
        JTextField namaPekerjaanField = new JTextField();
        JTextField pemohonField = new JTextField();
        JTextField tanggalField = new JTextField();
        JTextArea deskripsiArea = new JTextArea(3, 20);

        JTextField hargaField = new JTextField("0.00");
        hargaField.setEditable(false);

        JComboBox<String> statusBox = new JComboBox<>(
                new String[]{"Pending", "Diproses", "Selesai"}
        );

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));

        formPanel.add(new JLabel("Nama Pekerjaan:"));
        formPanel.add(namaPekerjaanField);

        formPanel.add(new JLabel("Pemohon:"));
        formPanel.add(pemohonField);

        formPanel.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        formPanel.add(tanggalField);

        formPanel.add(new JLabel("Deskripsi:"));
        formPanel.add(new JScrollPane(deskripsiArea));

        formPanel.add(new JLabel("Harga (Rp):"));
        formPanel.add(hargaField);

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
                String namaPekerjaan = namaPekerjaanField.getText().trim();
                String pemohon = pemohonField.getText().trim();
                String tanggalText = tanggalField.getText().trim();
                String deskripsi = deskripsiArea.getText().trim();

                if (namaPekerjaan.isEmpty()
                        || pemohon.isEmpty()
                        || tanggalText.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Nama pekerjaan, pemohon, dan tanggal wajib diisi."
                    );
                    return;
                }

                LocalDate tanggal = LocalDate.parse(tanggalText);

                WorkOrder workOrder = new WorkOrder(
                        0,
                        namaPekerjaan,
                        pemohon,
                        tanggal,
                        deskripsi,
                        BigDecimal.ZERO,
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

        // Mengubah indeks baris tampilan menjadi indeks data tabel.
        int modelRow = workOrderTable.convertRowIndexToModel(selectedRow);

        int id = Integer.parseInt(
                tableModel.getValueAt(modelRow, 0).toString()
        );

        JTextField namaPekerjaanField = new JTextField(
                tableModel.getValueAt(modelRow, 1).toString()
        );

        JTextField pemohonField = new JTextField(
                tableModel.getValueAt(modelRow, 2).toString()
        );

        JTextField tanggalField = new JTextField(
                tableModel.getValueAt(modelRow, 3).toString()
        );

        JTextArea deskripsiArea = new JTextArea(
                tableModel.getValueAt(modelRow, 4).toString(), 3, 20
        );

        BigDecimal hargaLama = (BigDecimal) tableModel.getValueAt(modelRow, 5);

        JTextField hargaField = new JTextField(
                hargaLama.toPlainString()
        );

        JComboBox<String> statusBox = new JComboBox<>(
                new String[]{"Pending", "Diproses", "Selesai"}
        );

        String statusLama = tableModel.getValueAt(modelRow, 6).toString();
        statusBox.setSelectedItem(statusLama);

        // Harga hanya bisa diedit ketika status Diproses.
        hargaField.setEditable("Diproses".equals(statusLama));

        statusBox.addActionListener(e -> {
            boolean dapatEdit = "Diproses".equals(
                    statusBox.getSelectedItem()
            );

            hargaField.setEditable(dapatEdit);

            if (!dapatEdit) {
                hargaField.setText(hargaLama.toPlainString());
            }
        });

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));

        formPanel.add(new JLabel("Nama Pekerjaan:"));
        formPanel.add(namaPekerjaanField);

        formPanel.add(new JLabel("Pemohon:"));
        formPanel.add(pemohonField);

        formPanel.add(new JLabel("Tanggal (YYYY-MM-DD):"));
        formPanel.add(tanggalField);

        formPanel.add(new JLabel("Deskripsi:"));
        formPanel.add(new JScrollPane(deskripsiArea));

        formPanel.add(new JLabel("Harga (Rp):"));
        formPanel.add(hargaField);

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
                String namaPekerjaan = namaPekerjaanField.getText().trim();
                String pemohon = pemohonField.getText().trim();
                String tanggalText = tanggalField.getText().trim();
                String deskripsi = deskripsiArea.getText().trim();
                String status = statusBox.getSelectedItem().toString();

                if (namaPekerjaan.isEmpty()
                        || pemohon.isEmpty()
                        || tanggalText.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Nama pekerjaan, pemohon, dan tanggal wajib diisi."
                    );
                    return;
                }

                BigDecimal harga;

                if ("Diproses".equals(status)) {
                    String hargaText = hargaField.getText().trim()
                            .replace(",", ".");

                    harga = new BigDecimal(hargaText);

                    if (harga.compareTo(BigDecimal.ZERO) < 0) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Harga tidak boleh negatif."
                        );
                        return;
                    }
                } else {
                    harga = hargaLama;
                }

                LocalDate tanggal = LocalDate.parse(tanggalText);

                WorkOrder workOrder = new WorkOrder(
                        id,
                        namaPekerjaan,
                        pemohon,
                        tanggal,
                        deskripsi,
                        harga,
                        status
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

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Format harga tidak valid. Contoh: 5000000 atau 5000000.00"
                );
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

    private void deleteWorkOrder() {
        int selectedRow = workOrderTable.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Pilih data Work Order yang ingin dihapus terlebih dahulu."
            );
            return;
        }

        int modelRow = workOrderTable.convertRowIndexToModel(selectedRow);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Apakah kamu yakin ingin menghapus Work Order ini?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                int id = Integer.parseInt(
                        tableModel.getValueAt(modelRow, 0).toString()
                );

                boolean success = controller.deleteWorkOrder(id);

                if (success) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Work Order berhasil dihapus."
                    );
                    loadWorkOrders();
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Work Order gagal dihapus."
                    );
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Gagal menghapus Work Order: " + e.getMessage(),
                        "Kesalahan Database",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}

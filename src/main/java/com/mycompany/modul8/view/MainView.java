package com.mycompany.modul8.view;

import javax.swing.*;
import java.awt.*;
import com.mycompany.modul8.view.WorkOrderView;

public class MainView extends JFrame {

    public MainView() {
        setTitle("Sistem Informasi PT Alexander Cipta Karya");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 60, 90));
        headerPanel.setLayout(new GridLayout(2, 1));
        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(25, 30, 25, 30)
        );

        JLabel titleLabel = new JLabel(
                "PT ALEXANDER CIPTA KARYA"
        );
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        JLabel subtitleLabel = new JLabel(
                "Sistem Informasi Pengelolaan Work Order"
        );
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setLayout(new GridBagLayout());

        JLabel welcomeLabel = new JLabel(
                "Selamat Datang di Sistem Informasi"
        );
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel descriptionLabel = new JLabel(
                "Kelola data Work Order perusahaan melalui aplikasi ini."
        );
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(Color.WHITE);
        welcomePanel.setLayout(new GridLayout(2, 1, 0, 10));

        welcomePanel.add(welcomeLabel);
        welcomePanel.add(descriptionLabel);

        JButton workOrderButton = new JButton("Lihat Daftar Work Order");
        workOrderButton.setFont(new Font("Arial", Font.BOLD, 14));
        workOrderButton.setPreferredSize(new Dimension(230, 40));

        workOrderButton.addActionListener(e -> {
            getContentPane().removeAll();
            add(new WorkOrderView());
            revalidate();
            repaint();
        });

        welcomePanel.add(workOrderButton);
        contentPanel.add(welcomePanel);

        JLabel footerLabel = new JLabel(
                "PT Alexander Cipta Karya"
        );
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        footerLabel.setBorder(
                BorderFactory.createEmptyBorder(15, 0, 15, 0)
        );

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.setVisible(true);
        });
    }
}

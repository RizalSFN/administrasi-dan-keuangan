package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class AdminDashboard extends JFrame {

    private User user;

    private JPanel contentPanel;
    private CardLayout cardLayout;
    private List<JButton> sidebarButtons = new ArrayList<>();

    public AdminDashboard(User user) {
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        setTitle("Administrasi dan Keuangan - Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // panel utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(33, 150, 243));

        // logo
        JLabel logo = new JLabel("LOGO SEKOLAH", SwingConstants.CENTER);
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 18));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        sidebar.add(logo);

        // menu sidebar
        JButton btnDashboard = createSidebarButton("Dashboard");
        JButton btnTransaksi = createSidebarButton("Pencatatan Pemasukan & Pengeluaran Siswa");
        JButton btnPembayaran = createSidebarButton("Pembayaran Siswa");
        JButton btnLaporan = createSidebarButton("Laporan Uang Otomatis");
        JButton btnNotifikasi = createSidebarButton("Notifikasi");

        sidebar.add(btnDashboard);
        sidebar.add(btnTransaksi);
        sidebar.add(btnPembayaran);
        sidebar.add(btnLaporan);
        sidebar.add(btnNotifikasi);

        sidebar.add(Box.createVerticalGlue());

        // tombol logout
        JButton btnLogout = new JButton("Logout");
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.setFocusPainted(false);
        btnLogout.setBackground(new Color(244, 67, 54)); // merah
        btnLogout.setForeground(Color.WHITE);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(btnLogout);
        sidebar.add(Box.createVerticalStrut(20));

        // top bar
        JPanel topbar = new JPanel(new BorderLayout());
        topbar.setBackground(new Color(3, 169, 244)); // biru muda
        JLabel akunLabel = new JLabel("Login sebagai: " + user.getUsername() + "   ");
        akunLabel.setForeground(Color.WHITE);
        akunLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        akunLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topbar.add(akunLabel, BorderLayout.EAST);
        topbar.setPreferredSize(new Dimension(0, 50));

        // content
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(Color.WHITE);

        // panel dashboard
        JPanel dashboardPanel = createDashboardPanel();
        contentPanel.add(dashboardPanel, "Dashboard");
        contentPanel.add(new JLabel("Pencatatan Pemasukan & Pengeluaran Siswa"), "Transaksi");
        contentPanel.add(new JLabel("Pembayaran Siswa"), "Pembayaran");
        contentPanel.add(new JLabel("Laporan Uang Otomatis"), "Laporan");
        contentPanel.add(new JLabel("Notifikasi"), "Notifikasi");

        // add ke main panel
        mainPanel.add(sidebar, BorderLayout.WEST);
        mainPanel.add(topbar, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);

        // aksi menu
        btnDashboard.addActionListener(e -> setActiveMenu("Dashboard", btnDashboard));
        btnTransaksi.addActionListener(e -> setActiveMenu("Transaksi", btnTransaksi));
        btnPembayaran.addActionListener(e -> setActiveMenu("Pembayaran", btnLaporan));
        btnLaporan.addActionListener(e -> setActiveMenu("Laporan", btnNotifikasi));
        btnNotifikasi.addActionListener(e -> setActiveMenu("Notifikasi", btnLogout));
        btnLogout.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin logout?", "Logout", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new Login().setVisible(true);
            }
        });

        setVisible(true);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setBackground(new Color(33, 150, 243));
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        return button;
    }

    private void setActiveMenu(String cardName, JButton activeButton) {
        // Ganti panel
        cardLayout.show(contentPanel, cardName);

        // Reset semua tombol ke warna default
        for (JButton btn : sidebarButtons) {
            btn.setBackground(new Color(33, 150, 243));
        }

        // Highlight tombol aktif
        activeButton.setBackground(new Color(25, 118, 210)); 
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3, 20, 20));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel card1 = createCard("Jumlah Siswa", "250");
        JPanel card2 = createCard("Siswa dengan Tunggakan", "30");
        JPanel card3 = createCard("Siswa Lunas", "220");

        panel.add(card1);
        panel.add(card2);
        panel.add(card3);

        return panel;
    }

    private JPanel createCard(String title, String value) {
        JPanel card = new JPanel();
        card.setBackground(new Color(3, 169, 244));
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel valueLabel = new JLabel(value);
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 36));
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.SOUTH);

        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard(null));
    }
}

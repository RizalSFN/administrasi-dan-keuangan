/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;

import controller.LoginController;
import model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author RIZAL
 */
public class Login extends JFrame {

    private JPanel panelLeft, panelRight;
    private JLabel labelLogo, labelTitle, labelUsername, labelPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public Login() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Administrasi dan Keuangan - Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // Panel luar
        JPanel mainPanel = new JPanel(new GridBagLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Container tengah
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/logo-app.jpeg"));
        Image image = logoIcon.getImage();
        Image scaled = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        logoIcon = new ImageIcon(scaled);
        JLabel labelLogo = new JLabel(logoIcon);
        labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title
        JLabel labelTitle = new JLabel("Administrasi dan Keuangan");
        labelTitle.setFont(new Font("Arial", Font.PLAIN, 16));
        labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Instansi
        JLabel instansiTitle = new JLabel("SMK Tadika Mesra");
        instansiTitle.setFont(new Font("Arial", Font.PLAIN, 16));
        instansiTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Form username
        JLabel labelUsername = new JLabel("Username:");
        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(200, 30));

        // Form password
        JLabel labelPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(200, 30));

        // Tombol login
        JButton btnLogin = new JButton("Login");
        btnLogin.setAlignmentX(CENTER_ALIGNMENT);

        // Panel form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(labelUsername, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(txtUsername, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(labelPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(txtPassword, gbc);

        // Tambah ke container
        container.add(Box.createVerticalStrut(20));
        container.add(labelLogo);
        container.add(Box.createVerticalStrut(20));
        container.add(labelTitle);
        container.add(Box.createVerticalStrut(5));
        container.add(instansiTitle);
        container.add(Box.createVerticalStrut(20));
        container.add(formPanel);
        container.add(Box.createVerticalStrut(20));
        container.add(btnLogin);
        container.add(Box.createVerticalStrut(20));

        // Tambah ke main panel (center)
        mainPanel.add(container, new GridBagConstraints());

        // Dummy aksi tombol
        btnLogin.addActionListener(e -> loginAction());

        setVisible(true);
    }

    private void loginAction() {
        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        LoginController loginController = new LoginController();
        User user = loginController.authenticate(username, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login berhasil sebagai " + user.getRole());

            if (user.getRole().equalsIgnoreCase("admin")) {
                new AdminDashboard(user).setVisible(true);
            } else if (user.getRole().equalsIgnoreCase("siswa")) {
                new SiswaDashboard(user).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Login gagal, invalid akun!");
            }

            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password salah!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}

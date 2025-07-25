/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.Admin;

import view.Admin.Cashflow.CashflowPanel;
import view.Admin.Notification.NotificationPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.User;
import view.Admin.Laporan.ArusKasPanel;
import view.Admin.Laporan.LabaRugiPanel;
import view.Admin.Laporan.LaporanPanel;
import view.Admin.Laporan.NeracaPanel;
import view.Admin.Notification.NotificationCategoryPanel;
import view.Admin.Notification.NotificationDataPanel;
import view.Admin.Pembayaran.FormInvoicePanel;
import view.Admin.Pembayaran.PembayaranInvoicePanel;
import view.Admin.Pembayaran.PembayaranPanel;
import view.Admin.Pembayaran.PembayaranPaymentPanel;
import view.Login;

/**
 *
 * @author RIZAL
 */
public class AdminDashboard extends javax.swing.JFrame {

    private User user;

    private String activeMenu = "Dashboard";
    private PembayaranInvoicePanel pembayaranInvoicePanel;

    /**
     * Creates new form AdminDashboard
     */
    public AdminDashboard() {
        initComponents();
    }

    public AdminDashboard(User user) {
        initComponents();
        this.user = user;

        setLocationRelativeTo(null);
        setTitle("Administrasi dan Keuangan - Dashboard");

//        panelContent.removeAll();
        DashboardPanel dashboard = new DashboardPanel(user);
        NotificationPanel notificationPanel = new NotificationPanel(this);
        NotificationCategoryPanel notificationCategoryPanel = new NotificationCategoryPanel(this);
        NotificationDataPanel notificationDataPanel = new NotificationDataPanel(this);
        PembayaranPanel pembayaranPanel = new PembayaranPanel(this);
        pembayaranInvoicePanel = new PembayaranInvoicePanel(this);
        FormInvoicePanel formInvoicePanel = new FormInvoicePanel(this);
        PembayaranPaymentPanel pembayaranPaymentPanel = new PembayaranPaymentPanel(this);
        LaporanPanel laporanPanel = new LaporanPanel(this);
        ArusKasPanel arusKasPanel = new ArusKasPanel(this);
        LabaRugiPanel labaRugiPanel = new LabaRugiPanel(this);
        NeracaPanel neracaPanel = new NeracaPanel(this);

        pembayaranInvoicePanel = new PembayaranInvoicePanel(this);
        panelContent.add(pembayaranInvoicePanel, "PembayaranInvoice");

        panelContent.add(dashboard, "Dashboard");
        panelContent.add(notificationPanel, "Notifikasi");
        panelContent.add(notificationCategoryPanel, "NotificationCategory");
        panelContent.add(notificationDataPanel, "NotificationData");
        panelContent.add(pembayaranPanel, "Pembayaran");
        panelContent.add(pembayaranInvoicePanel, "PembayaranInvoice");
        panelContent.add(formInvoicePanel, "FormInvoice");
        panelContent.add(pembayaranPaymentPanel, "PembayaranPayment");
        panelContent.add(laporanPanel, "Laporan");
        panelContent.add(arusKasPanel, "ArusKas");
        panelContent.add(labaRugiPanel, "LabaRugi");
        panelContent.add(neracaPanel, "Neraca");

        CardLayout cl = (CardLayout) panelContent.getLayout();
        cl.show(panelContent, "Dashboard");

//        panelContent.revalidate();
//        panelContent.repaint();
    }

    public JPanel getPanelContent() {
        return panelContent;
    }

    public PembayaranInvoicePanel getPembayaranInvoicePanel() {
        return pembayaranInvoicePanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelMenuDashboard = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        panelMenuCashflow = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelMenuLaporan = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panelMenuNotifikasi = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        panelMenuPembayaran = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelContent = new javax.swing.JPanel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user-icon.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Admin");

        panelMenuDashboard.setBackground(new java.awt.Color(204, 204, 204));
        panelMenuDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMenuDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMenuDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMenuDashboardMouseExited(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Dashboard");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home-icon.png"))); // NOI18N

        javax.swing.GroupLayout panelMenuDashboardLayout = new javax.swing.GroupLayout(panelMenuDashboard);
        panelMenuDashboard.setLayout(panelMenuDashboardLayout);
        panelMenuDashboardLayout.setHorizontalGroup(
            panelMenuDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuDashboardLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMenuDashboardLayout.setVerticalGroup(
            panelMenuDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuDashboardLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelMenuDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Menu");

        logoutButton.setBackground(new java.awt.Color(255, 0, 51));
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setText("LOGOUT");
        logoutButton.setToolTipText("");
        logoutButton.setBorder(null);
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButtonMouseExited(evt);
            }
        });
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        panelMenuCashflow.setBackground(new java.awt.Color(255, 255, 255));
        panelMenuCashflow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMenuCashflowMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMenuCashflowMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMenuCashflowMouseExited(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Cashflow");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money-icon.png"))); // NOI18N

        javax.swing.GroupLayout panelMenuCashflowLayout = new javax.swing.GroupLayout(panelMenuCashflow);
        panelMenuCashflow.setLayout(panelMenuCashflowLayout);
        panelMenuCashflowLayout.setHorizontalGroup(
            panelMenuCashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuCashflowLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMenuCashflowLayout.setVerticalGroup(
            panelMenuCashflowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuCashflowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(10, Short.MAX_VALUE))
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelMenuLaporan.setBackground(new java.awt.Color(255, 255, 255));
        panelMenuLaporan.setForeground(new java.awt.Color(0, 0, 0));
        panelMenuLaporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMenuLaporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMenuLaporanMouseExited(evt);
            }
        });

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/notebook-icon.png"))); // NOI18N

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Laporan");

        javax.swing.GroupLayout panelMenuLaporanLayout = new javax.swing.GroupLayout(panelMenuLaporan);
        panelMenuLaporan.setLayout(panelMenuLaporanLayout);
        panelMenuLaporanLayout.setHorizontalGroup(
            panelMenuLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLaporanLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMenuLaporanLayout.setVerticalGroup(
            panelMenuLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLaporanLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel13)
                .addContainerGap(8, Short.MAX_VALUE))
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelMenuNotifikasi.setBackground(new java.awt.Color(255, 255, 255));
        panelMenuNotifikasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMenuNotifikasiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMenuNotifikasiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMenuNotifikasiMouseExited(evt);
            }
        });

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/notification-icon.png"))); // NOI18N

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Notifikasi");

        javax.swing.GroupLayout panelMenuNotifikasiLayout = new javax.swing.GroupLayout(panelMenuNotifikasi);
        panelMenuNotifikasi.setLayout(panelMenuNotifikasiLayout);
        panelMenuNotifikasiLayout.setHorizontalGroup(
            panelMenuNotifikasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuNotifikasiLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMenuNotifikasiLayout.setVerticalGroup(
            panelMenuNotifikasiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuNotifikasiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addContainerGap(12, Short.MAX_VALUE))
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelMenuPembayaran.setBackground(new java.awt.Color(255, 255, 255));
        panelMenuPembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMenuPembayaranMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMenuPembayaranMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMenuPembayaranMouseExited(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Pembayaran");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/student-icon.png"))); // NOI18N

        javax.swing.GroupLayout panelMenuPembayaranLayout = new javax.swing.GroupLayout(panelMenuPembayaran);
        panelMenuPembayaran.setLayout(panelMenuPembayaranLayout);
        panelMenuPembayaranLayout.setHorizontalGroup(
            panelMenuPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuPembayaranLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMenuPembayaranLayout.setVerticalGroup(
            panelMenuPembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenuDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMenuCashflow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMenuLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMenuNotifikasi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
            .addComponent(panelMenuPembayaran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelMenuDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMenuPembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelMenuCashflow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMenuLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMenuNotifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        panelContent.setMinimumSize(new java.awt.Dimension(822, 521));
        panelContent.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelMenuCashflowMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuCashflowMouseEntered
        if (activeMenu != "Cashflow") {
            panelMenuCashflow.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_panelMenuCashflowMouseEntered

    private void panelMenuCashflowMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuCashflowMouseExited
        if (activeMenu != "Cashflow") {
            panelMenuCashflow.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_panelMenuCashflowMouseExited

    private void panelMenuCashflowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuCashflowMouseClicked
        CashflowPanel cashflow = new CashflowPanel(user);
        panelContent.add(cashflow, "Cashflow");

        activeMenu = "Cashflow";
        panelMenuDashboard.setBackground(new Color(255, 255, 255));
        panelMenuLaporan.setBackground(new Color(255, 255, 255));
        panelMenuNotifikasi.setBackground(new Color(255, 255, 255));
        panelMenuCashflow.setBackground(new Color(204, 204, 204));
        panelMenuPembayaran.setBackground(new Color(255, 255, 255));

        CardLayout cl = (CardLayout) panelContent.getLayout();
        cl.show(panelContent, "Cashflow");
    }//GEN-LAST:event_panelMenuCashflowMouseClicked

    private void panelMenuLaporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuLaporanMouseEntered
        if (activeMenu != "Laporan") {
            panelMenuLaporan.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_panelMenuLaporanMouseEntered

    private void panelMenuLaporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuLaporanMouseExited
        if (activeMenu != "Laporan") {
            panelMenuLaporan.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_panelMenuLaporanMouseExited

    private void panelMenuNotifikasiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuNotifikasiMouseEntered
        if (activeMenu != "Notifikasi") {
            panelMenuNotifikasi.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_panelMenuNotifikasiMouseEntered

    private void panelMenuNotifikasiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuNotifikasiMouseExited
        if (activeMenu != "Notifikasi") {
            panelMenuNotifikasi.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_panelMenuNotifikasiMouseExited

    private void logoutButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButtonMouseEntered
        logoutButton.setBackground(new Color(204, 0, 0));
    }//GEN-LAST:event_logoutButtonMouseEntered

    private void logoutButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButtonMouseExited
        logoutButton.setBackground(new Color(255, 0, 51));
    }//GEN-LAST:event_logoutButtonMouseExited

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this,
                "Yakin mau logout?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            new Login().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void panelMenuDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuDashboardMouseEntered
        if (activeMenu != "Dashboard") {
            panelMenuDashboard.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_panelMenuDashboardMouseEntered

    private void panelMenuDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuDashboardMouseExited
        if (activeMenu != "Dashboard") {
            panelMenuDashboard.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_panelMenuDashboardMouseExited

    private void panelMenuDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuDashboardMouseClicked
        activeMenu = "Dashboard";
        panelMenuCashflow.setBackground(new Color(255, 255, 255));
        panelMenuLaporan.setBackground(new Color(255, 255, 255));
        panelMenuNotifikasi.setBackground(new Color(255, 255, 255));
        panelMenuDashboard.setBackground(new Color(204, 204, 204));
        panelMenuPembayaran.setBackground(new Color(255, 255, 255));

        CardLayout cl = (CardLayout) panelContent.getLayout();
        cl.show(panelContent, "Dashboard");
    }//GEN-LAST:event_panelMenuDashboardMouseClicked

    private void panelMenuPembayaranMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuPembayaranMouseEntered
        if (activeMenu != "Pembayaran") {
            panelMenuPembayaran.setBackground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_panelMenuPembayaranMouseEntered

    private void panelMenuPembayaranMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuPembayaranMouseExited
        if (activeMenu != "Pembayaran") {
            panelMenuPembayaran.setBackground(new Color(255, 255, 255));
        }
    }//GEN-LAST:event_panelMenuPembayaranMouseExited

    private void panelMenuNotifikasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuNotifikasiMouseClicked
        activeMenu = "Notifikasi";
        panelMenuCashflow.setBackground(new Color(255, 255, 255));
        panelMenuLaporan.setBackground(new Color(255, 255, 255));
        panelMenuNotifikasi.setBackground(new Color(204, 204, 204));
        panelMenuDashboard.setBackground(new Color(255, 255, 255));
        panelMenuPembayaran.setBackground(new Color(255, 255, 255));

        CardLayout cl = (CardLayout) panelContent.getLayout();
        cl.show(panelContent, "Notifikasi");
    }//GEN-LAST:event_panelMenuNotifikasiMouseClicked

    private void panelMenuPembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMenuPembayaranMouseClicked
        activeMenu = "Pembayaran";
        panelMenuCashflow.setBackground(new Color(255, 255, 255));
        panelMenuLaporan.setBackground(new Color(255, 255, 255));
        panelMenuNotifikasi.setBackground(new Color(255, 255, 255));
        panelMenuDashboard.setBackground(new Color(255, 255, 255));
        panelMenuPembayaran.setBackground(new Color(204, 204, 204));

        CardLayout cl = (CardLayout) panelContent.getLayout();
        cl.show(panelContent, "Pembayaran");
    }//GEN-LAST:event_panelMenuPembayaranMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel panelContent;
    private javax.swing.JPanel panelMenuCashflow;
    private javax.swing.JPanel panelMenuDashboard;
    private javax.swing.JPanel panelMenuLaporan;
    private javax.swing.JPanel panelMenuNotifikasi;
    private javax.swing.JPanel panelMenuPembayaran;
    // End of variables declaration//GEN-END:variables
}

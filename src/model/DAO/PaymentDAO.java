package model.DAO;

import model.Payment;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private Connection conn;

    public PaymentDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewPayment(Payment payment) {
        String sql = "INSERT INTO payment (invoice_id, bukti_pembayaran, tanggal_bayar, jumlah_bayar, jenis_pembayaran, status_verifikasi) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, payment.getInvoiceId());
            stmt.setString(2, payment.getBuktiPembayaran());
            stmt.setDate(3, Date.valueOf(payment.getTanggalBayar()));
            stmt.setFloat(4, payment.getJumlahBayar());
            stmt.setString(5, payment.getJenisPembayaran());
            stmt.setString(6, payment.getStatusVerifikasi());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Payment findByInvoiceId(int invoice_id) {
        Payment payment = null;

        try {
            String sql = "SELECT * FROM payment WHERE invoice_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, invoice_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setInvoiceId(rs.getInt("invoice_id"));
                payment.setBuktiPembayaran(rs.getString("bukti_pembayaran"));
                payment.setTanggalBayar(rs.getDate("tanggal_bayar").toLocalDate());
                payment.setJumlahBayar(rs.getFloat("jumlah_bayar"));
                payment.setJenisPembayaran(rs.getString("jenis_pembayaran"));
                payment.setStatusVerifikasi(rs.getString("status_verifikasi"));
                payment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payment;

    }

    public Payment findByJenisPembayaran(String jenis_pembayaran) {
        Payment payment = null;

        try {
            String sql = "SELECT * FROM payment WHERE jenis_pembayaran = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, jenis_pembayaran);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setInvoiceId(rs.getInt("invoice_id"));
                payment.setBuktiPembayaran(rs.getString("bukti_pembayaran"));
                payment.setTanggalBayar(rs.getDate("tanggal_bayar").toLocalDate());
                payment.setJumlahBayar(rs.getFloat("jumlah_bayar"));
                payment.setJenisPembayaran(rs.getString("jenis_pembayaran"));
                payment.setStatusVerifikasi(rs.getString("status_verifikasi"));
                payment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payment;
    }

    public Payment findByStatusVerifikasi(String status_verifikasi) {
        Payment payment = null;

        try {
            String sql = "SELECT * FROM payment WHERE status_verifikasi = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status_verifikasi);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setInvoiceId(rs.getInt("invoice_id"));
                payment.setBuktiPembayaran(rs.getString("bukti_pembayaran"));
                payment.setTanggalBayar(rs.getDate("tanggal_bayar").toLocalDate());
                payment.setJumlahBayar(rs.getFloat("jumlah_bayar"));
                payment.setJenisPembayaran(rs.getString("jenis_pembayaran"));
                payment.setStatusVerifikasi(rs.getString("status_verifikasi"));
                payment.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payment;
    }

    public boolean updatePayment(Payment payment) {
        StringBuilder sql = new StringBuilder("UPDATE payment SET ");
        List<Object> params = new ArrayList<>();

        if (payment.getInvoiceId() != 0) {
            sql.append("invoice_id = ?, ");
            params.add(payment.getInvoiceId());
        }

        if (payment.getBuktiPembayaran() != null && !payment.getBuktiPembayaran().isEmpty()) {
            sql.append("bukti_pembayaran = ?, ");
            params.add(payment.getBuktiPembayaran());
        }

        if (payment.getTanggalBayar() != null) {
            sql.append("tanggal_bayar = ?, ");
            params.add(payment.getTanggalBayar());
        }

        if (payment.getJumlahBayar() != 0) {
            sql.append("jumlah_bayar = ?, ");
            params.add(payment.getJumlahBayar());
        }

        if (payment.getJenisPembayaran() != null && !payment.getJenisPembayaran().isEmpty()) {
            sql.append("jenis_pembayaran = ?, ");
            params.add(payment.getJenisPembayaran());
        }

        if (payment.getStatusVerifikasi() != null && !payment.getStatusVerifikasi().isEmpty()) {
            sql.append("status_verifikasi = ?, ");
            params.add(payment.getStatusVerifikasi());
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(payment.getId());

        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            for (int i = 1; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

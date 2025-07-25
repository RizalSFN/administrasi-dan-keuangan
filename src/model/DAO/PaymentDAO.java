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

    public int insertNewPayment(Payment payment) {
        String sql = "INSERT INTO payment (invoice_id, bukti_pembayaran, tanggal_bayar, jumlah_bayar, jenis_pembayaran, status_verifikasi) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, payment.getInvoiceId());
            stmt.setString(2, payment.getBuktiPembayaran());
            stmt.setDate(3, Date.valueOf(payment.getTanggalBayar()));
            stmt.setFloat(4, payment.getJumlahBayar());
            stmt.setString(5, payment.getJenisPembayaran());
            stmt.setString(6, payment.getStatusVerifikasi());

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
                generatedKeys.close();
            }
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedId;
    }

    public List<Payment> getPayments(String statusVerifikasi, String invoiceIdSearch) {
        List<Payment> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM payment WHERE 1=1";

            if (statusVerifikasi != null && !statusVerifikasi.equalsIgnoreCase("Semua")) {
                sql += " AND status_verifikasi = ?";
            }
            if (invoiceIdSearch != null && !invoiceIdSearch.trim().isEmpty()) {
                sql += " AND invoice_id LIKE ?";
            }

            PreparedStatement stmt = conn.prepareStatement(sql);

            int idx = 1;
            if (statusVerifikasi != null && !statusVerifikasi.equalsIgnoreCase("Semua")) {
                stmt.setString(idx++, statusVerifikasi);
            }
            if (invoiceIdSearch != null && !invoiceIdSearch.trim().isEmpty()) {
                stmt.setString(idx++, "%" + invoiceIdSearch + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setInvoiceId(rs.getInt("invoice_id"));
                payment.setBuktiPembayaran(rs.getString("bukti_pembayaran"));
                payment.setTanggalBayar(rs.getDate("tanggal_bayar").toLocalDate());
                payment.setJumlahBayar(rs.getFloat("jumlah_bayar"));
                payment.setJenisPembayaran(rs.getString("jenis_pembayaran"));
                payment.setStatusVerifikasi(rs.getString("status_verifikasi"));
                // Tambahkan kolom lain kalau ada
                result.add(payment);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
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

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

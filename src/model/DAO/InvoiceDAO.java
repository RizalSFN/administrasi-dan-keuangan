package model.DAO;

import model.Invoice;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvoiceDAO {

    private Connection conn;

    public InvoiceDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewInvoice(Invoice invoice) {
        String sql = "INSERT INTO invoice (student_id, jumlah, tanggal_jatuh_tempo, status) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, invoice.getStudentId());
            stmt.setFloat(2, invoice.getJumlah());
            stmt.setDate(3, Date.valueOf(invoice.getTanggalJatuhTempo()));
            stmt.setString(4, invoice.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String, Object>> getAllInvoiceWithStudent(String status, String nisn) {
        List<Map<String, Object>> result = new ArrayList<>();

        try {
            String sql = "SELECT i.id, s.nama_lengkap AS student_name, s.nisn, i.jumlah, i.tanggal_jatuh_tempo, i.status "
                    + "FROM invoice i "
                    + "JOIN student s ON i.student_id = s.id "
                    + "WHERE 1=1 "; 

            if (status != null && !status.trim().isEmpty() && !status.equalsIgnoreCase("Semua")) {
                sql += " AND i.status = ?";
            }

            if (nisn != null && !nisn.trim().isEmpty()) {
                sql += " AND s.nisn LIKE ?";
            }

            PreparedStatement stmt = conn.prepareStatement(sql);

            int index = 1;
            if (status != null && !status.trim().isEmpty() && !status.equalsIgnoreCase("Semua")) {
                stmt.setString(index++, status);
            }

            if (nisn != null && !nisn.trim().isEmpty()) {
                stmt.setString(index++, "%" + nisn + "%");
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("student_name", rs.getString("student_name"));
                row.put("nisn", rs.getString("nisn"));
                row.put("jumlah", rs.getFloat("jumlah"));
                row.put("tanggal_jatuh_tempo", rs.getDate("tanggal_jatuh_tempo").toString());
                row.put("status", rs.getString("status"));
                result.add(row);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int findStudentIdByNisn(String nisn) {
        int studentId = -1;

        try {
            String sql = "SELECT id FROM student WHERE nisn = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nisn);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                studentId = rs.getInt("id");
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentId;
    }

    public int findById(int id) {
        int invoice_id = -1;
        String sql = "SELECT student_id FROM invoice WHERE id = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                invoice_id = rs.getInt("student_id");
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return invoice_id;
    }

    public Invoice findByStudentId(int student_id) {
        Invoice invoice = null;

        try {
            String sql = "SELECT * FROM invoice WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setStudentId(rs.getInt("student_id"));
                invoice.setJumlah(rs.getFloat("jumlah"));
                invoice.setTanggalJatuhTempo(rs.getDate("tanggal_jatuh_tempo").toLocalDate());
                invoice.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return invoice;

    }

    public Invoice findByStatus(String status) {
        Invoice invoice = null;

        try {
            String sql = "SELECT * FROM invoice WHERE status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setStudentId(rs.getInt("student_id"));
                invoice.setJumlah(rs.getFloat("jumlah"));
                invoice.setTanggalJatuhTempo(rs.getDate("tanggal_jatuh_tempo").toLocalDate());
                invoice.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return invoice;

    }

    public boolean updateInvoice(Invoice invoice) {
        StringBuilder sql = new StringBuilder("UPDATE invoice SET ");
        List<Object> params = new ArrayList<>();

        if (invoice.getStudentId() != 0) {
            sql.append("student_id = ?, ");
            params.add(invoice.getStudentId());
        }

        if (invoice.getJumlah() != 0) {
            sql.append("jumlah = ?, ");
            params.add(invoice.getJumlah());
        }

        if (invoice.getTanggalJatuhTempo() != null) {
            sql.append("tanggal_jatuh_tempo = ?, ");
            params.add(invoice.getTanggalJatuhTempo());
        }

        if (invoice.getStatus() != null && !invoice.getStatus().isEmpty()) {
            sql.append("status = ?, ");
            params.add(invoice.getStatus());
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(invoice.getId());

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

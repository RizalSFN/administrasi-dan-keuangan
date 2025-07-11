package model.DAO;

import model.SchoolExpense;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SchoolExpenseDAO {
    private Connection conn;

    public SchoolExpenseDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewSchoolExpense(SchoolExpense schoolExpense) {
        String sql = "INSERT INTO school_expense (category_id, jumlah, tanggal_pengeluaran, bukti_transaksi, keterangan, created_by) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, schoolExpense.getCategoryId());
            stmt.setFloat(2, schoolExpense.getJumlah());
            stmt.setDate(3, Date.valueOf(schoolExpense.getTanggalPengeluaran()));
            stmt.setString(4, schoolExpense.getBuktiTransaksi());
            stmt.setString(5, schoolExpense.getKeterangan());
            stmt.setInt(6, schoolExpense.getCreatedBy());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SchoolExpense findByCategoryId(int category_id) {
        SchoolExpense schoolExpense = null;

        try {
            String sql = "SELECT * FROM school_expense WHERE category_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, category_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                schoolExpense = new SchoolExpense();
                schoolExpense.setId(rs.getInt("id"));
                schoolExpense.setCategoryId(rs.getInt("category_id"));
                schoolExpense.setjumlah(rs.getFloat("jumlah"));
                schoolExpense.setTanggalPengeluaran(rs.getDate("tanggal_pengeluaran").toLocalDate());
                schoolExpense.setBuktiTransaksi(rs.getString("bukti_transaksi"));
                schoolExpense.setKeterangan(rs.getString("keterangan"));
                schoolExpense.setCreatedBy(rs.getInt("created_by"));
                schoolExpense.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schoolExpense;
    }

    public SchoolExpense findByTanggalPengeluaran(Date tanggal_pengeluaran) {
        SchoolExpense schoolExpense = null;

        try {
            String sql = "SELECT * FROM school_expense WHERE tanggal_pengeluaran = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, tanggal_pengeluaran);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                schoolExpense = new SchoolExpense();
                schoolExpense.setId(rs.getInt("id"));
                schoolExpense.setCategoryId(rs.getInt("category_id"));
                schoolExpense.setjumlah(rs.getFloat("jumlah"));
                schoolExpense.setTanggalPengeluaran(rs.getDate("tanggal_pengeluaran").toLocalDate());
                schoolExpense.setBuktiTransaksi(rs.getString("bukti_transaksi"));
                schoolExpense.setKeterangan(rs.getString("keterangan"));
                schoolExpense.setCreatedBy(rs.getInt("created_by"));
                schoolExpense.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schoolExpense;
    }

    public boolean updateSchoolExpense(SchoolExpense schoolExpense) {
        StringBuilder sql = new StringBuilder("UPDATE school_expense SET ");
        List<Object> params = new ArrayList<>();

        if (schoolExpense.getCategoryId() != 0) {
            sql.append("category_id = ?, ");
            params.add(schoolExpense.getCategoryId());
        }

        if (schoolExpense.getJumlah() != 0) {
            sql.append("jumlah = ?, ");
            params.add(schoolExpense.getJumlah());
        }

        if (schoolExpense.getTanggalPengeluaran() != null) {
            sql.append("tanggal_pengeluaran = ?, ");
            params.add(schoolExpense.getTanggalPengeluaran());
        }

        if (schoolExpense.getBuktiTransaksi() != null && !schoolExpense.getBuktiTransaksi().isEmpty()) {
            sql.append("bukti_transaksi = ?, ");
            params.add(schoolExpense.getBuktiTransaksi());
        }

        if (schoolExpense.getKeterangan() != null && !schoolExpense.getKeterangan().isEmpty()) {
            sql.append("keterangan = ?, ");
            params.add(schoolExpense.getKeterangan());
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(schoolExpense.getId());

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

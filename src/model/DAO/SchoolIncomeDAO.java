package model.DAO;

import model.SchoolIncome;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SchoolIncomeDAO {
    private Connection conn;

    public SchoolIncomeDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewSchoolIncome(SchoolIncome schoolIncome) {
        String sql = "INSERT INTO school_income (source_id, jumlah, tanggal_pemasukan, keterangan, created_by) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, schoolIncome.getSourceId());
            stmt.setFloat(2, schoolIncome.getJumlah());
            stmt.setDate(3, Date.valueOf(schoolIncome.getTanggalPemasukan()));
            stmt.setString(4, schoolIncome.getKeterangan());
            stmt.setInt(5, schoolIncome.getCreatedBy());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SchoolIncome findBySourceId(int source_id) {
        SchoolIncome schoolIncome = null;

        try {
            String sql = "SELECT * FROM school_income WHERE source_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, source_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                schoolIncome = new SchoolIncome();
                schoolIncome.setId(rs.getInt("id"));
                schoolIncome.setSourceId(rs.getInt("source_id"));
                schoolIncome.setJumlah(rs.getFloat("jumlah"));
                schoolIncome.setTanggalPemasukan(rs.getDate("tanggal_pemasukan").toLocalDate());
                schoolIncome.setKeterangan(rs.getString("keterangan"));
                schoolIncome.setCreatedBy(rs.getInt("created_by"));
                schoolIncome.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schoolIncome;
    }

    public SchoolIncome findByTanggalPemasukan(Date tanggal_pemasukan) {
        SchoolIncome schoolIncome = null;

        try {
            String sql = "SELECT * FROM school_income WHERE tanggal_pemasukan = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, tanggal_pemasukan);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                schoolIncome = new SchoolIncome();
                schoolIncome.setId(rs.getInt("id"));
                schoolIncome.setSourceId(rs.getInt("source_id"));
                schoolIncome.setJumlah(rs.getFloat("jumlah"));
                schoolIncome.setTanggalPemasukan(rs.getDate("tanggal_pemasukan").toLocalDate());
                schoolIncome.setKeterangan(rs.getString("keterangan"));
                schoolIncome.setCreatedBy(rs.getInt("created_by"));
                schoolIncome.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schoolIncome;
    }

    public boolean updateSchoolIncome(SchoolIncome schoolIncome) {
        StringBuilder sql = new StringBuilder("UPDATE school_income SET ");
        List<Object> params = new ArrayList<>();

        if (schoolIncome.getSourceId() != 0) {
            sql.append("source_id = ?, ");
            params.add(schoolIncome.getSourceId());
        }

        if (schoolIncome.getJumlah() != 0) {
            sql.append("jumlah = ?, ");
            params.add(schoolIncome.getJumlah());
        }

        if (schoolIncome.getTanggalPemasukan() != null) {
            sql.append("tanggal_pemasukan = ?, ");
            params.add(schoolIncome.getTanggalPemasukan());
        }

        if (schoolIncome.getKeterangan() != null && !schoolIncome.getKeterangan().isEmpty()) {
            sql.append("keterangan = ?, ");
            params.add(schoolIncome.getKeterangan());
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(schoolIncome.getId());

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

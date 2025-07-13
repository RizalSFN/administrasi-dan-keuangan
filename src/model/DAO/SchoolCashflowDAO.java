package model.DAO;

import model.SchoolCashflow;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SchoolCashflowDAO {
    private Connection conn;

    public SchoolCashflowDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewSchoolCashflow(SchoolCashflow schoolCashflow) {
        String sql = "INSERT INTO school_cahflow (tipe, income_id, expense_id, jumlah, tanggal, saldo_awal, saldo_akhir, keterangan) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, schoolCashflow.getTipe());

            if (schoolCashflow.getIncomeId() != 0) {
                stmt.setInt(2, schoolCashflow.getIncomeId());
            } else {
                stmt.setInt(3, schoolCashflow.getExpenseId());
            }

            stmt.setFloat(4, schoolCashflow.getJumlah());
            stmt.setDate(5, Date.valueOf(schoolCashflow.getTanggal()));
            stmt.setFloat(6, schoolCashflow.getSaldoAwal());
            stmt.setFloat(7, schoolCashflow.getSaldoAkhir());
            stmt.setString(8, schoolCashflow.getKeterangan());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SchoolCashflow findByTipe(String tipe) {
        SchoolCashflow schoolCashflow = null;

        try {
            String sql = "SELECT * FROM school_cashflow WHERE tipe = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, tipe);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                schoolCashflow = new SchoolCashflow();
                schoolCashflow.setId(rs.getInt("id"));
                schoolCashflow.setTipe(rs.getString("tipe"));
                schoolCashflow.setIncomeId(rs.getInt("income_id"));
                schoolCashflow.setExpenseId(rs.getInt("expense_id"));
                schoolCashflow.setJumlah(rs.getFloat("jumlah"));
                schoolCashflow.setTanggal(rs.getDate("tanggal").toLocalDate());
                schoolCashflow.setSaldoAwal(rs.getFloat("saldo_awal"));
                schoolCashflow.setSaldoAkhir(rs.getFloat("saldo_akhir"));
                schoolCashflow.setKeterangan(rs.getString("keterangan"));
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return schoolCashflow;
    }

    public SchoolCashflow findByTanggal(Date tanggal) {
        SchoolCashflow schoolCashflow = null;

        try {
            String sql = "SELECT * FROM school_cashflow WHERE tanggal = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, tanggal);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                schoolCashflow = new SchoolCashflow();
                schoolCashflow.setId(rs.getInt("id"));
                schoolCashflow.setTipe(rs.getString("tipe"));
                schoolCashflow.setIncomeId(rs.getInt("income_id"));
                schoolCashflow.setExpenseId(rs.getInt("expense_id"));
                schoolCashflow.setJumlah(rs.getFloat("jumlah"));
                schoolCashflow.setTanggal(rs.getDate("tanggal").toLocalDate());
                schoolCashflow.setSaldoAwal(rs.getFloat("saldo_awal"));
                schoolCashflow.setSaldoAkhir(rs.getFloat("saldo_akhir"));
                schoolCashflow.setKeterangan(rs.getString("keterangan"));
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return schoolCashflow;
    }
}

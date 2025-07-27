package model.DAO;

import config.DatabaseConnection;
import java.math.BigDecimal;
import model.SchoolCashflow;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import view.Admin.Cashflow.CashflowPanel;

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

    public List<SchoolCashflow> getCashFlowBetween(Date tglAwal, Date tglAkhir) {
        List<SchoolCashflow> list = new ArrayList<>();
        String sql = "SELECT tanggal, keterangan, tipe, jumlah, saldo_akhir "
                + "FROM school_cashflow WHERE tanggal BETWEEN ? AND ? "
                + "ORDER BY tanggal ASC, id ASC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(tglAwal.getTime()));
            ps.setDate(2, new java.sql.Date(tglAkhir.getTime()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SchoolCashflow cf = new SchoolCashflow();
                cf.setTanggal(rs.getDate("tanggal").toLocalDate());
                cf.setKeterangan(rs.getString("keterangan"));
                cf.setTipe(rs.getString("tipe"));
                cf.setJumlah(rs.getFloat("jumlah"));
                cf.setSaldoAkhir(rs.getFloat("saldo_akhir"));
                list.add(cf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static BigDecimal getTotalPemasukan(Connection conn, String tglAwal, String tglAkhir) throws Exception {
        String sql = "SELECT SUM(jumlah) FROM school_income WHERE tanggal_pemasukan BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tglAwal);
            ps.setString(2, tglAkhir);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getBigDecimal(1) != null) {
                return rs.getBigDecimal(1);
            }
        }
        return BigDecimal.ZERO;
    }

    public static BigDecimal getTotalPengeluaran(Connection conn, String tglAwal, String tglAkhir) throws Exception {
        String sql = "SELECT SUM(jumlah) FROM school_expense WHERE tanggal_pengeluaran BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tglAwal);
            ps.setString(2, tglAkhir);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getBigDecimal(1) != null) {
                return rs.getBigDecimal(1);
            }
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getSaldoKas(String tanggal) throws SQLException {
        String sql = "SELECT saldo_akhir FROM school_cashflow WHERE tanggal <= ? ORDER BY tanggal DESC, id DESC LIMIT 1";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tanggal);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
        }
    }

    public BigDecimal getTotalPiutang(String tanggal) throws SQLException {
        String sql = "SELECT SUM(jumlah) FROM invoice WHERE status = 'belum lunas' AND tanggal_jatuh_tempo <= ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tanggal);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
        }
    }

    public BigDecimal getTotalPemasukan(String tanggal) throws SQLException {
        String sql = "SELECT SUM(jumlah) FROM school_income WHERE tanggal_pemasukan <= ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tanggal);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
        }
    }

    public BigDecimal getTotalPengeluaran(String tanggal) throws SQLException {
        String sql = "SELECT SUM(jumlah) FROM school_expense WHERE tanggal_pengeluaran <= ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tanggal);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getBigDecimal(1) != null ? rs.getBigDecimal(1) : BigDecimal.ZERO;
        }
    }
}

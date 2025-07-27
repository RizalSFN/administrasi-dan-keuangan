package model.DAO;

import model.TransactionHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryDAO {

    private Connection conn;

    public TransactionHistoryDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertHistory(TransactionHistory history) {
        String sql = "INSERT INTO transaction_history (student_id, payment_id, keterangan, waktu_catat) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, history.getStudentId());
            stmt.setInt(2, history.getPaymentId());
            stmt.setString(3, history.getKeterangan());
            stmt.setTimestamp(4, Timestamp.valueOf(history.getWaktuCatat()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<TransactionHistory> getAllHistories() {
        List<TransactionHistory> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction_history ORDER BY waktu_catat DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TransactionHistory> getHistoriesByStudentId(int studentId) {
        List<TransactionHistory> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction_history WHERE student_id = ? ORDER BY waktu_catat DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<TransactionHistory> getHistoriesByPaymentId(int paymentId) {
        List<TransactionHistory> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction_history WHERE payment_id = ? ORDER BY waktu_catat DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, paymentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSet(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private TransactionHistory mapResultSet(ResultSet rs) throws SQLException {
        TransactionHistory th = new TransactionHistory();
        th.setId(rs.getInt("id"));
        th.setStudentId(rs.getInt("student_id"));
        th.setPaymentId(rs.getInt("payment_id"));
        th.setKeterangan(rs.getString("keterangan"));
        th.setWaktuCatat(rs.getTimestamp("waktu_catat").toLocalDateTime());
        return th;
    }
}

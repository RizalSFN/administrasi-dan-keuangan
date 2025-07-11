package model.DAO;

import model.TransactionHistory;
import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryDAO {
    private Connection conn;

    public TransactionHistoryDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewHistory(TransactionHistory transactionHistory) {
        String sql = "INSERT INTO transaction_history (student_id, payment_id, keterangan) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, transactionHistory.getStudentId());
            stmt.setInt(2, transactionHistory.getPaymentId());
            stmt.setString(3, transactionHistory.getKeterangan());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public TransactionHistory findByStudentId(int student_id) {
        TransactionHistory transactionHistory = null;

        try {
            String sql = "SELECT * FROM transaction_history WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                transactionHistory = new TransactionHistory();
                transactionHistory.setId(rs.getInt("id"));
                transactionHistory.setStudentId(rs.getInt("student_id"));
                transactionHistory.setPaymentId(rs.getInt("payment_id"));
                transactionHistory.setKeterangan(rs.getString("keterangan"));
                transactionHistory.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactionHistory;
    }

    public TransactionHistory findByPaymentId(int payment_id) {
        TransactionHistory transactionHistory = null;

        try {
            String sql = "SELECT * FROM transaction_history WHERE payment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, payment_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                transactionHistory = new TransactionHistory();
                transactionHistory.setId(rs.getInt("id"));
                transactionHistory.setStudentId(rs.getInt("student_id"));
                transactionHistory.setPaymentId(rs.getInt("payment_id"));
                transactionHistory.setKeterangan(rs.getString("keterangan"));
                transactionHistory.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return transactionHistory;
    }
}

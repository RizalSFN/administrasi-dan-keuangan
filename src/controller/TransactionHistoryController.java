package controller;

import model.TransactionHistory;
import model.DAO.TransactionHistoryDAO;
import java.sql.Connection;
import config.DatabaseConnection;
import java.util.List;

public class TransactionHistoryController {

    private TransactionHistoryDAO transactionHistoryDAO;

    public TransactionHistoryController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            transactionHistoryDAO = new TransactionHistoryDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addHistory(TransactionHistory history) {
    return transactionHistoryDAO.insertHistory(history);
}

    public List<TransactionHistory> getAllHistories() {
        return transactionHistoryDAO.getAllHistories();
    }

    public List<TransactionHistory> getByStudentId(int studentId) {
        return transactionHistoryDAO.getHistoriesByStudentId(studentId);
    }

    public List<TransactionHistory> getByPaymentId(int paymentId) {
        return transactionHistoryDAO.getHistoriesByPaymentId(paymentId);
    }
}

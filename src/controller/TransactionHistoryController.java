package controller;

import model.TransactionHistory;
import model.DAO.TransactionHistoryDAO;
import java.sql.Connection;
import config.DatabaseConnection;

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

    public boolean createTransactionHistory(int studentId, int paymentId, String keterangan) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setStudentId(studentId);
        transactionHistory.setPaymentId(paymentId);
        transactionHistory.setKeterangan(keterangan);

        return transactionHistoryDAO.insertNewTransactionHistory(transactionHistory);
    }

    public TransactionHistory getTransactionHistoryByStudentId(int studentId) {
        return transactionHistoryDAO.findByStudentId(studentId);
    }

    public TransactionHistory getTransactionHistoryByPaymentId(int paymentId) {
        return transactionHistoryDAO.findByPaymentId(paymentId);
    }
}

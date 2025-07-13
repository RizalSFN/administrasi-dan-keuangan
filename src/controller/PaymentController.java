package controller;

import config.DatabaseConnection;
import model.Payment;
import model.TransactionHistory;
import model.DAO.InvoiceDAO;
import model.DAO.PaymentDAO;
import model.DAO.TransactionHistoryDAO;

import java.sql.Connection;
import java.time.LocalDate;

public class PaymentController {
    private PaymentDAO paymentDAO;
    private InvoiceDAO invoiceDAO;
    private TransactionHistoryDAO transactionHistoryDAO;

    public PaymentController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            paymentDAO = new PaymentDAO(conn);
            invoiceDAO = new InvoiceDAO(conn);
            transactionHistoryDAO = new TransactionHistoryDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean cashPayment(int invoice_id, float jummlah_bayar) {
        Payment payment = new Payment();
        payment.setInvoiceId(invoice_id);
        payment.setBuktiPembayaran(null);
        payment.setTanggalBayar(LocalDate.now());
        payment.setJumlahBayar(jummlah_bayar);
        payment.setJenisPembayaran("tunai");
        payment.setStatusVerifikasi("menunggu");

        int paymentId = paymentDAO.insertNewPayment(payment);

        if (paymentId > 0) {
            int studentId = invoiceDAO.findById(invoice_id);

            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setStudentId(studentId);
            transactionHistory.setPaymentId(paymentId);
            transactionHistory.setKeterangan("Pembayaran tunai SPP");

            return transactionHistoryDAO.insertNewTransactionHistory(transactionHistory);
        }

        return false;
    }

    public boolean cashlessPayment(int invoice_id, float jumlah_bayar, String bukti_pembayaran) {
        Payment payment = new Payment();
        payment.setInvoiceId(invoice_id);
        payment.setBuktiPembayaran(bukti_pembayaran);
        payment.setTanggalBayar(LocalDate.now());
        payment.setJumlahBayar(jumlah_bayar);
        payment.setJenisPembayaran("non tunai");
        payment.setStatusVerifikasi("menunggu");

        int paymentId = paymentDAO.insertNewPayment(payment);

        if (paymentId > 0) {
            int studentId = invoiceDAO.findById(invoice_id);

            TransactionHistory transactionHistory = new TransactionHistory();
            transactionHistory.setStudentId(studentId);
            transactionHistory.setPaymentId(paymentId);
            transactionHistory.setKeterangan("Pembayaran non tunai SPP");

            return transactionHistoryDAO.insertNewTransactionHistory(transactionHistory);
        }

        return false;
    }

    public boolean updatePayment(Payment payment) {
        return paymentDAO.updatePayment(payment);
    }

    public Payment getPaymentByInvoiceId(int invoice_id) {
        return paymentDAO.findByInvoiceId(invoice_id);
    }

    public Payment getPaymentByJenisPembayaran(String jenis_pembayaran) {
        return paymentDAO.findByJenisPembayaran(jenis_pembayaran);
    }

    public Payment getPaymentByStatusVerifikasi(String status) {
        return paymentDAO.findByStatusVerifikasi(status);
    }
}

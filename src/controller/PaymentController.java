package controller;

import config.DatabaseConnection;
import model.Payment;
import model.TransactionHistory;
import model.DAO.InvoiceDAO;
import model.DAO.PaymentDAO;
import model.DAO.TransactionHistoryDAO;
import utils.EmailSender;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class PaymentController {

    private PaymentDAO paymentDAO;
    private InvoiceDAO invoiceDAO;
    private TransactionHistoryDAO transactionHistoryDAO;
    private InvoiceController invoiceController;

    public PaymentController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            paymentDAO = new PaymentDAO(conn);
            invoiceDAO = new InvoiceDAO(conn);
            transactionHistoryDAO = new TransactionHistoryDAO(conn);
            invoiceController = new InvoiceController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getPayments(String statusVerifikasi, String invoiceId) {
        return paymentDAO.getPayments(statusVerifikasi, invoiceId);
    }

    public boolean createPayment(Payment payment) {
        int paymentId = paymentDAO.insertNewPayment(payment);
        if (paymentId > 0) {
            return invoiceController.updateInvoice(payment.getInvoiceId(), "lunas");
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

    public boolean updatePaymentStatus(int paymentId, String newStatus) {
        Payment payment = new Payment();
        payment.setId(paymentId);
        payment.setStatusVerifikasi(newStatus);

        return paymentDAO.updatePayment(payment);
    }

    public void sendPaymentReminder(String studentEmail, String namaSiswa, String invoiceInfo) {

        String emailSubject = "Pengingat Pembayaran SPP";

        String emailBody = "Yth. Orang Tua/Wali dari " + namaSiswa + ", \n\n"
                + "Berikut info tagihan:\n"
                + invoiceInfo + "\n\n"
                + "Terima kasih.";

        EmailSender.sendEmail(studentEmail, emailSubject, emailBody);
    }
}

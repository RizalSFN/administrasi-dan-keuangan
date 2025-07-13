package controller;

import config.DatabaseConnection;
import model.Payment;
import model.DAO.PaymentDAO;
import java.sql.Connection;
import java.time.LocalDate;

public class PaymentController {
    private PaymentDAO paymentDAO;

    public PaymentController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            paymentDAO = new PaymentDAO(conn);
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

        return paymentDAO.insertNewPayment(payment);
    }

    public boolean cashlessPayment(int invoice_id, float jumlah_bayar, String bukti_pembayaran) {
        Payment payment = new Payment();
        payment.setInvoiceId(invoice_id);
        payment.setBuktiPembayaran(bukti_pembayaran);
        payment.setTanggalBayar(LocalDate.now());
        payment.setJumlahBayar(jumlah_bayar);
        payment.setJenisPembayaran("non tunai");
        payment.setStatusVerifikasi("menunggu");

        return paymentDAO.insertNewPayment(payment);
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

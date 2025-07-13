package controller;

import config.DatabaseConnection;
import java.sql.Connection;
import java.time.LocalDate;
import model.Invoice;
import model.DAO.InvoiceDAO;

public class InvoiceController {
    private InvoiceDAO invoiceDAO;

    public InvoiceController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            invoiceDAO = new InvoiceDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createInvoice(int student_id, float jumlah) {
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        LocalDate tanggalJatuhTempo = nextMonth.withDayOfMonth(nextMonth.lengthOfMonth());

        Invoice invoice = new Invoice();
        invoice.setStudentId(student_id);
        invoice.setJumlah(jumlah);
        invoice.setTanggalJatuhTempo(tanggalJatuhTempo);
        invoice.setStatus("belum lunas");

        return invoiceDAO.insertNewInvoice(invoice);
    }

    public boolean updateInvoice(Invoice invoice) {
        return invoiceDAO.updateInvoice(invoice);
    }

    public Invoice getInvoiceByStudentId(int student_id) {
        return invoiceDAO.findByStudentId(student_id);
    }

    public Invoice getInvoiceByStatus(String status) {
        return invoiceDAO.findByStatus(status);
    }
}

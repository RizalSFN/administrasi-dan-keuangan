package controller;

import config.DatabaseConnection;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import model.Invoice;
import model.DAO.InvoiceDAO;
import model.DAO.StudentDAO;

public class InvoiceController {

    private InvoiceDAO invoiceDAO;
    private StudentDAO studentDAO;

    public InvoiceController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            invoiceDAO = new InvoiceDAO(conn);
            studentDAO = new StudentDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createInvoiceByNisn(String nisn, float jumlah, LocalDate tanggalJatuhTempo) {
        int studentId = studentDAO.findStudentIdByNisn(nisn);

        if (studentId == -1) {
            System.out.println("Siswa dengan NISN tidak ditemukan.");
            return false;
        }

        Invoice invoice = new Invoice();
        invoice.setStudentId(studentId);
        invoice.setJumlah(jumlah);
        invoice.setTanggalJatuhTempo(tanggalJatuhTempo);
        invoice.setStatus("belum lunas");

        return invoiceDAO.insertNewInvoice(invoice);
    }

    public List<Map<String, Object>> getAllInvoices(String status, String nisn) {
        return invoiceDAO.getAllInvoiceWithStudent(status, nisn);
    }

    public List<Invoice> getFilteredInvoices(String status, String nisn) {
        return invoiceDAO.getFilteredInvoices(status, nisn);
    }

    public boolean updateInvoice(int invoiceId, String newStatus) {
        Invoice invoice = new Invoice();
        invoice.setId(invoiceId);
        invoice.setStatus(newStatus);
        return invoiceDAO.updateInvoice(invoice);
    }

    public int getStudentIdByInvoiceId(int invoiceId) {
        return invoiceDAO.getStudentIdByInvoiceId(invoiceId);
    }

    public Invoice getInvoiceById(int id) {
        return invoiceDAO.findInvoiceById(id);
    }

    public Invoice getInvoiceByStudentId(int student_id) {
        return invoiceDAO.findByStudentId(student_id);
    }

    public Invoice getInvoiceByStatus(String status) {
        return invoiceDAO.findByStatus(status);
    }
}

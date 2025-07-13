package controller;

import model.SchoolExpense;
import model.DAO.SchoolExpenseDAO;
import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

public class SchoolExpenseController {
    private SchoolExpenseDAO schoolExpenseDAO;

    public SchoolExpenseController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            schoolExpenseDAO = new SchoolExpenseDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createSchoolExpense(int categoryId, float jumlah, String buktiTransaksi, String keterangan,
            int created_by) {
        SchoolExpense schoolExpense = new SchoolExpense();
        schoolExpense.setCategoryId(categoryId);
        schoolExpense.setjumlah(jumlah);
        schoolExpense.setTanggalPengeluaran(LocalDate.now());
        schoolExpense.setBuktiTransaksi(buktiTransaksi);
        schoolExpense.setKeterangan(keterangan);
        schoolExpense.setCreatedBy(created_by);

        return schoolExpenseDAO.insertNewSchoolExpense(schoolExpense);
    }

    public SchoolExpense getSchoolExpenseByCategoryId(int categoryId) {
        return schoolExpenseDAO.findByCategoryId(categoryId);
    }

    public SchoolExpense getSchoolExpenseByTanggalPengeluaran(Date tanggalPengeluaran) {
        return schoolExpenseDAO.findByTanggalPengeluaran(tanggalPengeluaran);
    }

    public boolean updateSchoolExpense(SchoolExpense schoolExpense) {
        return schoolExpenseDAO.updateSchoolExpense(schoolExpense);
    }
}

package controller;

import model.SchoolCashflow;
import model.DAO.SchoolCashflowDAO;
import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;

public class SchoolCashflowController {
    private SchoolCashflowDAO schoolCashflowDAO;

    public SchoolCashflowController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            schoolCashflowDAO = new SchoolCashflowDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createSchoolCashflow(String tipe, int incomeId, int expenseId, float jumlah, Date tanggal,
            float saldoAwal, float saldoAkhir, String keterangan) {
        SchoolCashflow schoolCashflow = new SchoolCashflow();
        schoolCashflow.setTipe(tipe);
        schoolCashflow.setIncomeId(incomeId);
        schoolCashflow.setExpenseId(expenseId);
        schoolCashflow.setJumlah(jumlah);
        schoolCashflow.setTanggal(tanggal.toLocalDate());
        schoolCashflow.setSaldoAwal(saldoAkhir);
        schoolCashflow.setSaldoAkhir(saldoAkhir);
        schoolCashflow.setKeterangan(keterangan);

        return schoolCashflowDAO.insertNewSchoolCashflow(schoolCashflow);
    }

    public SchoolCashflow getSchoolCashflowByTipe(String tipe) {
        return schoolCashflowDAO.findByTipe(tipe);
    }

    public SchoolCashflow getSchoolCashflowByTanggal(Date tanggal) {
        return schoolCashflowDAO.findByTanggal(tanggal);
    }
}

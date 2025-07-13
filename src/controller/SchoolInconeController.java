package controller;

import model.SchoolIncome;
import model.DAO.SchoolIncomeDAO;
import config.DatabaseConnection;
import java.sql.Connection;
import java.time.LocalDate;

public class SchoolInconeController {
    private SchoolIncomeDAO schoolIncomeDAO;

    public SchoolInconeController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            schoolIncomeDAO = new SchoolIncomeDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createSchoolIncome(int sourceId, float jumlah, String buktiTransaksi, String keterangan, int created_by) {
        SchoolIncome schoolIncome = new SchoolIncome();
        schoolIncome.setSourceId(sourceId);
        schoolIncome.setJumlah(jumlah);
        schoolIncome.setTanggalPemasukan(LocalDate.now());
        schoolIncome.setBuktiTransaksi(buktiTransaksi); 
        schoolIncome.setKeterangan(keterangan);
        schoolIncome.setCreatedBy(created_by);

        return schoolIncomeDAO.insertNewSchoolIncome(schoolIncome);
    }

    public 
}

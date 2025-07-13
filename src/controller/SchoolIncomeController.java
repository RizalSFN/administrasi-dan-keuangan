package controller;

import model.SchoolIncome;
import model.DAO.SchoolIncomeDAO;
import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;

public class SchoolIncomeController {
    private SchoolIncomeDAO schoolIncomeDAO;

    public SchoolIncomeController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            schoolIncomeDAO = new SchoolIncomeDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createSchoolIncome(int sourceId, float jumlah, String buktiTransaksi, String keterangan,
            int created_by) {
        SchoolIncome schoolIncome = new SchoolIncome();
        schoolIncome.setSourceId(sourceId);
        schoolIncome.setJumlah(jumlah);
        schoolIncome.setTanggalPemasukan(LocalDate.now());
        schoolIncome.setBuktiTransaksi(buktiTransaksi);
        schoolIncome.setKeterangan(keterangan);
        schoolIncome.setCreatedBy(created_by);

        return schoolIncomeDAO.insertNewSchoolIncome(schoolIncome);
    }

    public boolean updateSchoolIncome(SchoolIncome schoolIncome) {
        return schoolIncomeDAO.updateSchoolIncome(schoolIncome);
    }

    public SchoolIncome getSchoolIncomeBySourceId(int sourceId) {
        return schoolIncomeDAO.findBySourceId(sourceId);
    }

    public SchoolIncome getSchoolIncomeByTanggalPemasukan(Date tanggalPemasukan) {
        return schoolIncomeDAO.findByTanggalPemasukan(tanggalPemasukan);
    }
}

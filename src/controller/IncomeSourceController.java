package controller;

import model.IncomeSource;
import model.DAO.IncomeSourceDAO;
import config.DatabaseConnection;
import java.sql.Connection;

public class IncomeSourceController {
    private IncomeSourceDAO incomeSourceDAO;

    public IncomeSourceController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            incomeSourceDAO = new IncomeSourceDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createIncomeSource(String nama, String deskripsi) {
        IncomeSource incomeSource = new IncomeSource();
        incomeSource.setNama(nama);
        incomeSource.setDeskripsi(deskripsi);
        incomeSource.setStatus("aktif");

        return incomeSourceDAO.insertNewIncomeSource(incomeSource);
    }

    public boolean updateIncomeSource(IncomeSource incomeSource) {
        return incomeSourceDAO.updateIncomeSource(incomeSource);
    }

    public IncomeSource getIncomeSourceByStatus(String status) {
        return incomeSourceDAO.findByStatus(status);
    }
}

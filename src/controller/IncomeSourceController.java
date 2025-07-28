package controller;

import model.IncomeSource;
import model.DAO.IncomeSourceDAO;
import config.DatabaseConnection;
import java.sql.Connection;
import java.util.List;

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
    
    public List<IncomeSource> getAllKategoriPemasukan() {
        return incomeSourceDAO.getAllKategoriPemasukan();
    }

    public boolean createIncomeSource(IncomeSource incomeSource) {
        return incomeSourceDAO.insertNewIncomeSource(incomeSource);
    }

    public boolean updateIncomeSource(IncomeSource incomeSource) {
        return incomeSourceDAO.updateIncomeSource(incomeSource);
    }

    public IncomeSource getIncomeSourceByStatus(String status) {
        return incomeSourceDAO.findByStatus(status);
    }
}

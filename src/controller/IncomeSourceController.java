package controller;

import model.IncomeSource;
import model.DAO.IncomeSourceDAO;
import config.DatabaseConnection;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Map<String, Integer> kategoriMap = new HashMap<>();

    public List<String> getAllKategoriNames() {
        List<IncomeSource> list = incomeSourceDAO.getAllIncomeSources();
        List<String> namaList = new ArrayList<>();
        kategoriMap.clear();

        for (IncomeSource i : list) {
            namaList.add(i.getNama());
            kategoriMap.put(i.getNama(), i.getId());
        }

        return namaList;
    }

    public boolean createIncomeSource(IncomeSource incomeSource) {
        return incomeSourceDAO.insertNewIncomeSource(incomeSource);
    }

    public int getIdByNama(String nama) {
        return incomeSourceDAO.getIdByName(nama);
    }

    public boolean updateIncomeSource(IncomeSource incomeSource) {
        return incomeSourceDAO.updateIncomeSource(incomeSource);
    }

    public IncomeSource getIncomeSourceByStatus(String status) {
        return incomeSourceDAO.findByStatus(status);
    }
}

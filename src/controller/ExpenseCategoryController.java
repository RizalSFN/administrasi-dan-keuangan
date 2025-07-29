package controller;

import model.ExpenseCategory;
import model.DAO.ExpenseCategoryDAO;
import java.sql.Connection;
import config.DatabaseConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseCategoryController {

    private ExpenseCategoryDAO expenseCategoryDAO;

    public ExpenseCategoryController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            expenseCategoryDAO = new ExpenseCategoryDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryDAO.insertNewExpenseCategory(expenseCategory);
    }

    private Map<String, Integer> kategoriMap = new HashMap<>();

    public List<String> getAllKategoriNames() {
        List<ExpenseCategory> list = expenseCategoryDAO.getAllKategoriPengeluaran();
        List<String> namaList = new ArrayList<>();
        kategoriMap.clear();

        for (ExpenseCategory i : list) {
            namaList.add(i.getNama());
            kategoriMap.put(i.getNama(), i.getId());
        }

        return namaList;
    }

    public List<ExpenseCategory> getAllKategoriPengeluaran() {
        return expenseCategoryDAO.getAllKategoriPengeluaran();
    }

    public int getIdByNama(String nama) {
        return expenseCategoryDAO.getIdByName(nama);
    }

    public ExpenseCategory getExpenseCategoryByStatus(String status) {
        return expenseCategoryDAO.findByStatus(status);
    }

    public boolean updateExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryDAO.updateExpenseCategory(expenseCategory);
    }
}

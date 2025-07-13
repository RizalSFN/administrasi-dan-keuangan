package controller;

import model.ExpenseCategory;
import model.DAO.ExpenseCategoryDAO;
import java.sql.Connection;
import config.DatabaseConnection;

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

    public boolean createExpenseCategory(String nama, String deskripsi) {
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setNama(nama);
        expenseCategory.setDeskripsi(deskripsi);
        expenseCategory.setStatus("aktif");

        return expenseCategoryDAO.insertNewExpenseCategory(expenseCategory);
    }

    public ExpenseCategory getExpenseCategoryByStatus(String status) {
        return expenseCategoryDAO.findByStatus(status);
    }

    public boolean updateExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryDAO.updateExpenseCategory(expenseCategory);
    }
}

package controller;

import model.ExpenseCategory;
import model.DAO.ExpenseCategoryDAO;
import java.sql.Connection;
import config.DatabaseConnection;
import java.util.List;

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
    
    public List<ExpenseCategory> getAllKategoriPengeluaran() {
        return expenseCategoryDAO.getAllKategoriPengeluaran();
    }

    public ExpenseCategory getExpenseCategoryByStatus(String status) {
        return expenseCategoryDAO.findByStatus(status);
    }

    public boolean updateExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryDAO.updateExpenseCategory(expenseCategory);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.DAO.SchoolExpenseDAO;
import model.SchoolExpense;

/**
 *
 * @author RIZAL
 */
public class SchoolExpenseController {

    private SchoolExpenseDAO dao;

    public SchoolExpenseController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            this.dao = new SchoolExpenseDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tambahExpense(SchoolExpense expense) {
        return dao.insertExpense(expense);
    }

    public List<SchoolExpense> tampilkanSemuaExpense() {
        return dao.getAllExpense();
    }

    public int getLastInsertedExpenseId() {
        return dao.getLastInsertedId();
    }
}

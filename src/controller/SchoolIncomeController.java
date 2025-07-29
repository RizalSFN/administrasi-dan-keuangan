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
import model.DAO.SchoolIncomeDAO;
import model.SchoolIncome;

/**
 *
 * @author RIZAL
 */
public class SchoolIncomeController {

    private SchoolIncomeDAO dao;

    public SchoolIncomeController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            this.dao = new SchoolIncomeDAO(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean tambahIncome(SchoolIncome income) {
        return dao.insertIncome(income);
    }

    public List<SchoolIncome> tampilkanSemuaIncome() {
        return dao.getAllIncome();
    }
}

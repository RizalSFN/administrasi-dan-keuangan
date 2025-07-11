/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.DatabaseConnection;
import java.sql.Connection;
import model.User;
import model.DAO.UserDAO;
import utils.SecurityUtils;
import java.sql.SQLException;

/**
 *
 * @author RIZAL
 */
public class LoginController {

    private UserDAO userDAO;

    public LoginController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            if (conn == null) {
                throw new RuntimeException("Database connection failed.");
            }
            userDAO = new UserDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection error: " + e.getMessage());
        }
    }

    public User authenticate(String username, String password) {
        User user = userDAO.findByUsername(username);

        boolean passwordMatch = SecurityUtils.checkPassword(password, user.getPassword());

        if (user != null && username.equals(user.getUsername()) && passwordMatch) {
            return user;
        }

        return null;
    }

    public User getUserAktif(String status) {
        return userDAO.findByStatus(status);
    }
}

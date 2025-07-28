/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import model.DAO.UserDAO;
import model.User;

/**
 *
 * @author RIZAL
 */
public class UserController {

    private UserDAO userDAO;

    public UserController(Connection conn) {
        this.userDAO = new UserDAO(conn);
    }

    public boolean createUser(User user) {
        return userDAO.insertNewUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
}

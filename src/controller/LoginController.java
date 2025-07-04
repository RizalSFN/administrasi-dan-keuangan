/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.User;
import model.DAO.UserDAO;

/**
 *
 * @author RIZAL
 */
public class LoginController {
    private UserDAO userDAO;
    
    public LoginController() {
        userDAO = new UserDAO();
    }
    
    public User authenticate(String username, String password) {
        User user = userDAO.findByUsername(username);
        
        if (user != null && password.equals(user.getPassword()) && username.equals(user.getUsername())) {
            return user;
        }
        return null;
    }
    
    public User getUserAktif(String status) {
        return userDAO.findByStatus(status);
    }
}

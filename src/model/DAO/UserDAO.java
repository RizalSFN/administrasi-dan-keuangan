/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.User;
import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author RIZAL
 */
public class UserDAO {
    public User findByUsername(String username) {
        User user = null;
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM user WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
                user.setNomorTelepon(rs.getString("nomor_telepon"));
                user.setStatus(rs.getString("status"));
                
                Timestamp ts = rs.getTimestamp("created_at");
                user.setCreatedAt(ts.toLocalDateTime());
            } 
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    public User findByStatus(String status) {
        User user = null;
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM user WHERE status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setEmail(rs.getString("email"));
                user.setNomorTelepon(rs.getString("nomor_telepon"));
                user.setStatus(rs.getString("status"));
                
                Timestamp ts = rs.getTimestamp("created_at");
                user.setCreatedAt(ts.toLocalDateTime());
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return user;
    }
}

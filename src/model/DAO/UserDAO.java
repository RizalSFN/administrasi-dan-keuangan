/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.User;
import utils.SecurityUtils;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RIZAL
 */
public class UserDAO {

    private Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewUser(User user) {
        String sql = "INSERT INTO user (username, password, role, email, nomor_telepon) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            String hashedPassword = SecurityUtils.hashPassword(user.getPassword());

            stmt.setString(1, user.getUsername());
            stmt.setString(2, hashedPassword);
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getNomorTelepon());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public User findByUsername(String username) {
        User user = null;

        try {
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
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
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
                user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean updateUser(User user) {
        StringBuilder sql = new StringBuilder("UPDATE user SET ");
        List<Object> params = new ArrayList<>();

        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            sql.append("username = ?, ");
            params.add(user.getUsername());
        }

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            sql.append("password = ?, ");
            params.add(SecurityUtils.hashPassword(user.getPassword()));
        }

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            sql.append("email = ?, ");
            params.add(user.getEmail());
        }

        if (user.getNomorTelepon() != null && !user.getNomorTelepon().isEmpty()) {
            sql.append("nomor_telepon = ?, ");
            params.add(user.getNomorTelepon());
        }

        if (user.getStatus() != null && !user.getStatus().isEmpty()) {
            sql.append("status = ?, ");
            params.add(user.getStatus());
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(user.getId());

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

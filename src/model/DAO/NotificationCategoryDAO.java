package model.DAO;

import model.NotificationCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotificationCategoryDAO {
    private Connection conn;

    public NotificationCategoryDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewNotificationCategory(NotificationCategory notificationCategory) {
        String sql = "INSERT INTO notification_category (nama) VALUES (?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, notificationCategory.getNama());
//            stmt.setString(2, notificationCategory.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public NotificationCategory findByStatus(String status) {
        NotificationCategory notificationCategory = null;

        try {
            String sql = "SELECT * FROM notification_category WHERE status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                notificationCategory = new NotificationCategory();
                notificationCategory.setId(rs.getInt("id"));
                notificationCategory.setNama(rs.getString("nama"));
                notificationCategory.setStatus(rs.getString("status"));
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notificationCategory;
    }

    public boolean updateNotificationCategory(NotificationCategory notificationCategory) {
        StringBuilder sql = new StringBuilder("UPDATE notification_category SET ");
        List<Object> params = new ArrayList<>();

        if (notificationCategory.getNama() != null && !notificationCategory.getNama().isEmpty()) {
            sql.append("nama = ?, ");
            params.add(notificationCategory.getNama());
        }

        if (notificationCategory.getStatus() != null && !notificationCategory.getStatus().isEmpty()) {
            sql.append("status = ?, ");
            params.add(notificationCategory.getStatus());
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(notificationCategory.getId());

        try {
            PreparedStatement stmt = conn.prepareStatement(sql.toString());

            for (int i = 1; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

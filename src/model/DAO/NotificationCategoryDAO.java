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

    public List<NotificationCategory> getAllNotificationCategories() {
        List<NotificationCategory> list = new ArrayList<>();
        String sql = "SELECT id, nama, status FROM notification_category";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                NotificationCategory nc = new NotificationCategory();
                nc.setId(rs.getInt("id"));
                nc.setNama(rs.getString("nama"));
                nc.setStatus(rs.getString("status"));
                list.add(nc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<NotificationCategory> getFilteredNotificationCategories(String status, String nama) {
        List<NotificationCategory> list = new ArrayList<>();
        String sql = "SELECT id, nama, status FROM notification_category WHERE 1=1";

        if (!status.equalsIgnoreCase("Semua")) {
            sql += " AND status = ?";
        }
        if (nama != null && !nama.trim().isEmpty()) {
            sql += " AND nama LIKE ?";
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            int index = 1;
            if (!status.equalsIgnoreCase("Semua")) {
                stmt.setString(index++, status.toLowerCase()); // asumsi status disimpan lowercase
            }
            if (nama != null && !nama.trim().isEmpty()) {
                stmt.setString(index++, "%" + nama + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NotificationCategory nc = new NotificationCategory();
                nc.setId(rs.getInt("id"));
                nc.setNama(rs.getString("nama"));
                nc.setStatus(rs.getString("status"));
                list.add(nc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
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

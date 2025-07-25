package model.DAO;

import model.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    private Connection conn;

    public NotificationDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insertNewNotification(Notification notification) {
        String sql = "INSERT INTO notification (notification_category_id, student_id, invoice_id, title, body, destination, send_at) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, notification.getId());
            stmt.setInt(2, notification.getStudentId());
            stmt.setInt(3, notification.getInvoiceId());
            stmt.setString(4, notification.getTitle());
            stmt.setString(5, notification.getBody());
            stmt.setString(6, notification.getDestination());
            stmt.setTimestamp(7, Timestamp.valueOf(notification.getSendAt()));
            // stmt.setString(8, notification.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Notification> getNotifications(String status, String studentName) {
        List<Notification> list = new ArrayList<>();

        String sql = "SELECT n.*, s.nama_lengkap as student_name FROM notification n "
                + "JOIN student s ON n.student_id = s.id WHERE 1=1";

        if (status != null && !status.equalsIgnoreCase("Semua")) {
            sql += " AND n.status = ?";
        }
        if (studentName != null && !studentName.trim().isEmpty()) {
            sql += " AND s.nama LIKE ?";
        }

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            int index = 1;
            if (status != null && !status.equalsIgnoreCase("Semua")) {
                stmt.setString(index++, status);
            }
            if (studentName != null && !studentName.trim().isEmpty()) {
                stmt.setString(index++, "%" + studentName + "%");
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Notification n = new Notification();
                n.setId(rs.getInt("id"));
                n.setNotificationCategoryId(rs.getInt("notification_category_id"));
                n.setStudentId(rs.getInt("student_id"));
                n.setInvoiceId(rs.getInt("invoice_id"));
                n.setTitle(rs.getString("title"));
                n.setBody(rs.getString("body"));
                n.setDestination(rs.getString("destination"));
                n.setSendAt(rs.getTimestamp("send_at").toLocalDateTime());
                n.setStatus(rs.getString("status"));
                n.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                n.setStudentName(rs.getString("student_name")); 
                list.add(n);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Notification findByStudentId(int student_id) {
        Notification notification = null;

        try {
            String sql = "SELECT * FROM notification WHERE student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                notification = new Notification();
                notification.setId(rs.getInt("notification_category_id"));
                notification.setStudentId(rs.getInt("student_id"));
                notification.setInvoiceId(rs.getInt("invoice_id"));
                notification.setTitle(rs.getString("title"));
                notification.setBody(rs.getString("body"));
                notification.setDestination(rs.getString("destination"));
                notification.setSendAt(rs.getTimestamp("send_at").toLocalDateTime());
                notification.setStatus(rs.getString("status"));
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notification;
    }

    public Notification findByInvoiceId(int invoice_id) {
        Notification notification = null;

        try {
            String sql = "SELECT * FROM notification WHERE invoice_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, invoice_id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                notification = new Notification();
                notification.setId(rs.getInt("notification_category_id"));
                notification.setStudentId(rs.getInt("student_id"));
                notification.setInvoiceId(rs.getInt("invoice_id"));
                notification.setTitle(rs.getString("title"));
                notification.setBody(rs.getString("body"));
                notification.setDestination(rs.getString("destination"));
                notification.setSendAt(rs.getTimestamp("send_at").toLocalDateTime());
                notification.setStatus(rs.getString("status"));
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notification;
    }

    public Notification findByStatus(String status) {
        Notification notification = null;

        try {
            String sql = "SELECT * FROM notification WHERE status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                notification = new Notification();
                notification.setId(rs.getInt("notification_category_id"));
                notification.setStudentId(rs.getInt("student_id"));
                notification.setInvoiceId(rs.getInt("invoice_id"));
                notification.setTitle(rs.getString("title"));
                notification.setBody(rs.getString("body"));
                notification.setDestination(rs.getString("destination"));
                notification.setSendAt(rs.getTimestamp("send_at").toLocalDateTime());
                notification.setStatus(rs.getString("status"));
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return notification;
    }

    public int findByJumlahStatus(String status) {
        int totalCount = 0;

        try {
            String sql = "SELECT COUNT(*) FROM notification WHERE status = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalCount;
    }

    public boolean updateNotification(Notification notification) {
        StringBuilder sql = new StringBuilder("UPDATE notification SET ");
        List<Object> params = new ArrayList<>();

        if (notification.getNotificationCategoryId() != 0) {
            sql.append("notification_category_id = ?, ");
            params.add(notification.getNotificationCategoryId());
        }

        if (notification.getStudentId() != 0) {
            sql.append("student_id = ?, ");
            params.add(notification.getStudentId());
        }

        if (notification.getInvoiceId() != 0) {
            sql.append("invoice_id = ?, ");
            params.add(notification.getInvoiceId());
        }

        if (notification.getTitle() != null && !notification.getTitle().isEmpty()) {
            sql.append("title = ?, ");
            params.add(notification.getTitle());
        }

        if (notification.getBody() != null && !notification.getBody().isEmpty()) {
            sql.append("body = ?, ");
            params.add(notification.getBody());
        }

        if (notification.getDestination() != null && !notification.getDestination().isEmpty()) {
            sql.append("destination = ?, ");
            params.add(notification.getDestination());
        }

        if (notification.getStatus() != null && !notification.getStatus().isEmpty()) {
            sql.append("status = ?, ");
            params.add(notification.getStatus());
        }

        if (params.isEmpty()) {
            return false;
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE id = ?");
        params.add(notification.getId());

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

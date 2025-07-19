package controller;

import model.Notification;
import model.DAO.NotificationDAO;
import config.DatabaseConnection;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

public class NotificationController {

    private NotificationDAO notificationDAO;

    public NotificationController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            notificationDAO = new NotificationDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createNotification(int notificationCategoryId, int studentId, int invoiceId, String title, String body, String destination, String status) {
        Notification notification = new Notification();
        notification.setNotificationCategoryId(notificationCategoryId);
        notification.setStudentId(studentId);
        notification.setInvoiceId(invoiceId);
        notification.setTitle(title);
        notification.setBody(body);
        notification.setDestination(destination);
        notification.setSendAt(LocalDateTime.now());
        notification.setStatus(status);

        return notificationDAO.insertNewNotification(notification);
    }

    public List<Notification> getNotifications(String status, String studentName) {
        return notificationDAO.getNotifications(status, studentName);
    }

    public Notification getNotificationByStudentId(int studentId) {
        return notificationDAO.findByStudentId(studentId);
    }

    public Notification getNotificationByInvoiceId(int invoiceId) {
        return notificationDAO.findByInvoiceId(invoiceId);

    }

    public Notification getNotificationByStatus(String status) {
        return notificationDAO.findByStatus(status);
    }

    public int getTotalNotificationByStatus(String status) {
        return notificationDAO.findByJumlahStatus(status);
    }
    
    public boolean updateNotification(Notification notification) {
        return notificationDAO.updateNotification(notification);
    }
}

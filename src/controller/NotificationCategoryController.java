package controller;

import model.NotificationCategory;
import model.DAO.NotificationCategoryDAO;
import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;

public class NotificationCategoryController {
    private NotificationCategoryDAO notificationCategoryDAO;

    public NotificationCategoryController() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            notificationCategoryDAO = new NotificationCategoryDAO(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createNotificationCategory(String nama) {
        NotificationCategory notificationCategory = new NotificationCategory();
        notificationCategory.setNama(nama);
        notificationCategory.setStatus("aktif");

        return notificationCategoryDAO.insertNewNotificationCategory(notificationCategory);
    }

    public NotificationCategory getNotificationCategoryByStatus(String status) {
        return notificationCategoryDAO.findByStatus(status);
    }

    public boolean updateNotificationCategory(NotificationCategory notificationCategory) {
        return notificationCategoryDAO.updateNotificationCategory(notificationCategory);
    }
}

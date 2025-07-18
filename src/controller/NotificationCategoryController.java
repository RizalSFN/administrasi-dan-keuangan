package controller;

import model.NotificationCategory;
import model.DAO.NotificationCategoryDAO;
import config.DatabaseConnection;
import java.sql.Connection;
import java.util.List;

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

        return notificationCategoryDAO.insertNewNotificationCategory(notificationCategory);
    }

    public List<NotificationCategory> getAllNotificationCategories() {
        return notificationCategoryDAO.getAllNotificationCategories();
    }

    public List<NotificationCategory> getFilteredNotificationCategories(String status, String nama) {
        return notificationCategoryDAO.getFilteredNotificationCategories(status, nama);
    }

    public boolean updateNotificationCategory(NotificationCategory notificationCategory) {
        return notificationCategoryDAO.updateNotificationCategory(notificationCategory);
    }
}

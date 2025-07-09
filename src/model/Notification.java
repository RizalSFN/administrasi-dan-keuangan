package model;

import java.time.LocalDateTime;

public class Notification {
    private int id;
    private int notification_category_id;
    private int student_id;
    private int invoice_id;
    private String title;
    private String body;
    private String destination;
    private LocalDateTime send_at;
    private String status;
    private LocalDateTime created_at;

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNotificationCategoryId() {
        return notification_category_id;
    }

    public void setNotificationCategoryId(int notification_category_id) {
        this.notification_category_id = notification_category_id;
    }

    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    public int getInvoiceId() {
        return invoice_id;
    }

    public void setInvoiceId(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getSendAt() {
        return send_at;
    }

    public void setSendAt(LocalDateTime send_at) {
        this.send_at = send_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author RIZAL
 */
public class TransactionHistory {
    private int id;
    private int student_id;
    private int payment_id;
    private String keterangan;
    private LocalDateTime created_at;
    
    public TransactionHistory() {}
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getStudentId() {
        return student_id;
    }
    
    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }
    
    public int getPaymentId() {
        return payment_id;
    }
    
    public void setPaymentId(int payment_id) {
        this.payment_id = payment_id;
    }
    
    public String getKeterangan() {
        return keterangan;
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    public LocalDateTime getCreatedAt() {
        return created_at;
    }
    
    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}

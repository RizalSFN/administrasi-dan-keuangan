/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 *
 * @author RIZAL
 */
public class Invoice {
    private int id;
    private int student_id;
    private String studentName;
    private String studentNisn;
    private float jumlah;
    private LocalDate tanggal_jatuh_tempo;
    private String status;
    private LocalDateTime created_at;
    
    public Invoice() {}
    
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
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getStudentNisn() {
        return studentNisn;
    }
    
    public void setStudentNisn(String studentNisn) {
        this.studentNisn = studentNisn;
    }
    
    public float getJumlah() {
        return jumlah;
    }
    
    public void setJumlah(float jumlah) {
        this.jumlah = jumlah;
    }
    
    public LocalDate getTanggalJatuhTempo() {
        return tanggal_jatuh_tempo;
    }
    
    public void setTanggalJatuhTempo(LocalDate tanggal_jatuh_tempo) {
        this.tanggal_jatuh_tempo = tanggal_jatuh_tempo;
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

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
public class Student {
    private int id;
    private int user_id;
    private String nama_lengkap;
    private String nisn;
    private String kelas;
    private LocalDateTime created_at;
    
    public Student() {}
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUserId() {
        return user_id;
    }
    
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
    
    public String getNamaLengkap() {
        return nama_lengkap;
    }
    
    public void setNamaLengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }
    
    public String getNisn() {
        return nisn;
    }
    
    public void setNisn(String nisn) {
        this.nisn = nisn;
    }
    
    public String getKelas() {
        return kelas;
    }
    
    public void setKelas(String kelas) {
        this.kelas = kelas;
    }
    
    public LocalDateTime getCreatedAt() {
        return created_at;
    }
    
    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}

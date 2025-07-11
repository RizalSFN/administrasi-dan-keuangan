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
public class SchoolIncome {
    private int id;
    private int source_id;
    private float jumlah;
    private LocalDate tanggal_pemasukan;
    private String bukti_transaksi;
    private String keterangan;
    private int created_by;
    private LocalDateTime created_at;

    public SchoolIncome() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSourceId() {
        return source_id;
    }

    public void setSourceId(int source_id) {
        this.source_id = source_id;
    }

    public float getJumlah() {
        return jumlah;
    }

    public void setJumlah(float jumlah) {
        this.jumlah = jumlah;
    }

    public LocalDate getTanggalPemasukan() {
        return tanggal_pemasukan;
    }

    public void setTanggalPemasukan(LocalDate tanggal_pemasukan) {
        this.tanggal_pemasukan = tanggal_pemasukan;
    }

    public String getBuktiTransaksi() {
        return bukti_transaksi;
    }

    public void setBuktiTransaksi(String bukti_transaksi) {
        this.bukti_transaksi = bukti_transaksi;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public int getCreatedBy() {
        return created_by;
    }

    public void setCreatedBy(int created_by) {
        this.created_by = created_by;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}

package model;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class SchoolExpense {
    private int id;
    private int category_id;
    private float jumlah;
    private LocalDate tanggal_pengeluaran;
    private String bukti_transaksi;
    private String keterangan;
    private int created_by;
    private LocalDateTime created_at;

    public SchoolExpense() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return category_id;
    }

    public void setCategoryId(int category_id) {
        this.category_id = category_id;
    }

    public float getJumlah() {
        return jumlah;
    }

    public void setjumlah(float jumlah) {
        this.jumlah = jumlah;
    }

    public LocalDate getTanggalPengeluaran() {
        return tanggal_pengeluaran;
    }

    public void setTanggalPengeluaran(LocalDate tanggal_pengeluaran) {
        this.tanggal_pengeluaran = tanggal_pengeluaran;
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

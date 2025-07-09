package model;

import java.time.LocalDateTime;
import java.time.LocalDate;

public class SchoolCashflow {
    private int id;
    private String tipe;
    private int income_id;
    private int expense_id;
    private float jumlah;
    private LocalDate tanggal;
    private float saldo_awal;
    private float saldo_akhir;
    private String keterangan;
    private LocalDateTime created_at;

    public SchoolCashflow() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public int getIncomeId() {
        return income_id;
    }

    public void setIncomeId(int income_id) {
        this.income_id = income_id;
    }

    public int getExpenseId() {
        return expense_id;
    }

    public void setExpenseId(int expense_id) {
        this.expense_id = expense_id;
    }

    public float getJumlah() {
        return jumlah;
    }

    public void setJumlah(float jumlah) {
        this.jumlah = jumlah;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public float getSaldoAwal() {
        return saldo_awal;
    }

    public void setSaldoAwal(float saldo_awal) {
        this.saldo_awal = saldo_awal;
    }

    public float getSaldoAkhir() {
        return saldo_akhir;
    }

    public void setSaldoAkhir(float saldo_akhir) {
        this.saldo_akhir = saldo_akhir;
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

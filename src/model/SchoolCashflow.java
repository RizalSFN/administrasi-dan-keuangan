package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class SchoolCashflow {
    private int id;
    private String tipe;
    private int income_id;
    private int expense_id;
    private BigDecimal jumlah;
    private LocalDate tanggal;
    private BigDecimal saldo_awal;
    private BigDecimal saldo_akhir;
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

    public BigDecimal getJumlah() {
        return jumlah;
    }

    public void setJumlah(BigDecimal jumlah) {
        this.jumlah = jumlah;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public BigDecimal getSaldoAwal() {
        return saldo_awal;
    }

    public void setSaldoAwal(BigDecimal saldo_awal) {
        this.saldo_awal = saldo_awal;
    }

    public BigDecimal getSaldoAkhir() {
        return saldo_akhir;
    }

    public void setSaldoAkhir(BigDecimal saldo_akhir) {
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

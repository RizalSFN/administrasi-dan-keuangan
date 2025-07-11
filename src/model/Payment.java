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
public class Payment {
    private int id;
    private int invoice_id;
    private String bukti_pembayaran;
    private LocalDate tanggal_bayar;
    private float jumlah_bayar;
    private String jenis_pembayaran;
    private String status_verifikasi;
    private LocalDateTime created_at;

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoice_id;
    }

    public void setInvoiceId(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getBuktiPembayaran() {
        return bukti_pembayaran;
    }

    public void setBuktiPembayaran(String bukti_pembayaran) {
        this.bukti_pembayaran = bukti_pembayaran;
    }

    public LocalDate getTanggalBayar() {
        return tanggal_bayar;
    }

    public void setTanggalBayar(LocalDate tanggal_bayar) {
        this.tanggal_bayar = tanggal_bayar;
    }

    public float getJumlahBayar() {
        return jumlah_bayar;
    }

    public void setJumlahBayar(float jumlah_bayar) {
        this.jumlah_bayar = jumlah_bayar;
    }

    public String getJenisPembayaran() {
        return jenis_pembayaran;
    }

    public void setJenisPembayaran(String jenis_pembayaran) {
        this.jenis_pembayaran = jenis_pembayaran;
    }

    public String getStatusVerifikasi() {
        return status_verifikasi;
    }

    public void setStatusVerifikasi(String status_verifikasi) {
        this.status_verifikasi = status_verifikasi;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}

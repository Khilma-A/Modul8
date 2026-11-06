package com.mycompany.modul8.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WorkOrder {

    private int id;
    private String namaPekerjaan;
    private String pemohon;
    private LocalDate tanggal;
    private String deskripsi;
    private BigDecimal harga;
    private String status;

    public WorkOrder(int id, String namaPekerjaan,
            String pemohon, LocalDate tanggal,
            String deskripsi, BigDecimal harga, String status) {

        this.id = id;
        this.namaPekerjaan = namaPekerjaan;
        this.pemohon = pemohon;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaPekerjaan() {
        return namaPekerjaan;
    }

    public void setNamaPekerjaan(String namaPekerjaan) {
        this.namaPekerjaan = namaPekerjaan;
    }

    public String getPemohon() {
        return pemohon;
    }

    public void setPemohon(String pemohon) {
        this.pemohon = pemohon;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public BigDecimal getHarga() {
        return harga;
    }

    public void setHarga(BigDecimal harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

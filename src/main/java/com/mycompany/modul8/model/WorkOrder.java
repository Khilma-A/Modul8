package com.mycompany.modul8.model;

import java.time.LocalDate;

public class WorkOrder {

    private int id;
    private String nomorWO;
    private String namaPekerjaan;
    private String pemohon;
    private LocalDate tanggal;
    private String deskripsi;
    private String prioritas;
    private String status;

    public WorkOrder(int id, String nomorWO, String namaPekerjaan,
            String pemohon, LocalDate tanggal, String deskripsi,
            String prioritas, String status) {

        this.id = id;
        this.nomorWO = nomorWO;
        this.namaPekerjaan = namaPekerjaan;
        this.pemohon = pemohon;
        this.tanggal = tanggal;
        this.deskripsi = deskripsi;
        this.prioritas = prioritas;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomorWO() {
        return nomorWO;
    }

    public void setNomorWO(String nomorWO) {
        this.nomorWO = nomorWO;
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

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

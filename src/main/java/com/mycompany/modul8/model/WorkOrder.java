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
}

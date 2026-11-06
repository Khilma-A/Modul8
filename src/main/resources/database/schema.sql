CREATE DATABASE IF NOT EXISTS alexander_cipta_karya;

USE alexander_cipta_karya;

CREATE TABLE IF NOT EXISTS work_order (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama_pekerjaan VARCHAR(100) NOT NULL,
    pemohon VARCHAR(100) NOT NULL,
    tanggal DATE NOT NULL,
    deskripsi TEXT,
    harga DECIMAL(15,2) NOT NULL DEFAULT 0.00,
    status VARCHAR(30) NOT NULL DEFAULT 'Pending'
);
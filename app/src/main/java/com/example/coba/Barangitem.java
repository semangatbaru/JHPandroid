package com.example.coba;

import java.io.Serializable;

public class Barangitem implements Serializable {
    String namabarang, harga, satuan, stok, kategori, gambar, deskripsi;
    public Barangitem(String namabarang, String harga, String satuan, String stok, String kategori, String gambar, String deskripsi)
    {
        this.namabarang = namabarang;
        this.harga= harga;
        this.satuan= satuan;
        this.stok= stok;
        this.kategori= kategori;
        this.gambar= gambar;
        this.deskripsi= deskripsi;

    }
    public String getNamabarang() {
        return namabarang;
    }
    public String getHarga() {
        return harga;
    }
    public String getSatuan() {
        return satuan;
    }
    public String getStok(){
        return stok;
    }
    public String getKategori() {
        return kategori;
    }
    public String getGambar(){
        return gambar;
    }
    public String getDeskripsi() {
        return deskripsi;
    }


}

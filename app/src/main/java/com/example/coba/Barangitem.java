package com.example.coba;

import java.io.Serializable;

public class Barangitem implements Serializable {
    String no, namabarang, harga, satuan, stok, kategori, gambar, deskripsi;
    public Barangitem(String no, String namabarang, String harga, String satuan, String stok, String kategori, String gambar, String deskripsi)
    {
        this.no = no;
        this.namabarang = namabarang;
        this.harga= harga;
        this.satuan= satuan;
        this.stok= stok;
        this.kategori= kategori;
        this.gambar= gambar;
        this.deskripsi= deskripsi;

    }
}
    public String getNo() {
        return no;
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

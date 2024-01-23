/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.sun.jdi.connect.spi.Connection;
import static connection.mysqlconnect.ConnectDb;
import javafx.collections.ObservableList;

/**
 *
 * @author BENEDICT TAMBUNAN
 */
public class Barang {
    public String kode, nama, kategori;
    public int harga, stok;
//    private final String kode_barang;
    
    public void setKode(String kode){
        this.kode = kode;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public void setHarga(int harga){
        this.harga = harga;
    }
    
    public void setKategori(String kategori){
        this.kategori = kategori;
    }
    
    public void setStok(int stok){
        this.stok = stok;
    }
  
    public String getKode(){
        return kode;
    }
    
    public String getNama(){
        return nama;
    }
    
    public int getHarga(){
        return harga;
    }
    
    public int getStok(){
        return stok;
    }
    
    public String getKategori(){
        return kategori;
    }
    
    public Barang(String kode_barang, String nama_barang, String kategori, int harga, int stok) {
        this.kode = kode_barang;
        this.nama = nama_barang;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }
}


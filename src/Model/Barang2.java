/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import com.sun.jdi.connect.spi.Connection;
import static connection.mysqlconnect.ConnectDb;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author BENEDICT TAMBUNAN
 */
public class Barang2 {
    private String kode, nama, kategori;
    private int no,harga, jlh;
//    private final String kode_barang;


    public int getNo(){
        return no;
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
    
    public int getJlh(){
        return jlh;
    }
    
    public String getKategori(){
        return kategori;
    }
    
    public void setNo(int no){
        this.no = no;
    }
    
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
    
    public void setStok(int jlh){
        this.jlh = jlh;
    }
  
    
    
    public Barang2(int no,String kode_barang, String nama_barang, String kategori, int harga, int jlh) {
        this.no = no;
        this.kode = kode_barang;
        this.nama = nama_barang;
        this.kategori = kategori;
        this.harga = harga;
        this.jlh = jlh;
    }
}


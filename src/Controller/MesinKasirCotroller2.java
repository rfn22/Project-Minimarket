package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
import Model.Barang;
import Model.Barang2;
import Ui.ProjectPbo3;
import connection.connect_db;
import static connection.mysqlconnect.ConnectDb;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static javax.swing.UIManager.getString;

/**
 *
 * @author BENEDICT TAMBUNAN
 */
public class MesinKasirCotroller2 implements Initializable {

    int i = 1;
    
    
     
    @FXML
    private TableColumn<Barang2, Integer> notable;

    @FXML
    private Label label;

    @FXML
    private TableColumn<Barang2, String> namatable;

    @FXML
    private TableColumn<Barang2, String> kategoritable;

    @FXML
    private Button proses;

    @FXML
    private Button hapus;

    @FXML
    private TableColumn<Barang2, Integer> jumlahtable;

    @FXML
    private TextField txtjlh;
    
    @FXML
    private TextField uang;

    @FXML
    private TextField txtkode;


    @FXML
    private TableColumn<Barang2, Integer> hargatable;

    @FXML
    private TableColumn< Barang2, String> kodetable;

    @FXML
    private TableView<Barang2> table;

    @FXML
    private Button simpan;
    
    @FXML
    private Label total;
    @FXML
    private Label total1;

    ObservableList<Barang2> listM;
    
    int j = 0;
    int x = 1;
    int hargatotal = 0;
    int index = -1;

    @FXML
    void getSelected(MouseEvent event) {
        index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        
    }
    
     public ObservableList<Barang2> getDatabarang(){
        Connection conn = ConnectDb();
        ObservableList<Barang2> list = FXCollections.observableArrayList();
        int jumlah = Integer.parseInt(txtjlh.getText());
        String kode = txtkode.getText();
        String nama = null;
        String kategori = null;
        int harga = 0;
        int lharga[] = null;
        int sum = 0;
        int count = 0;
        int id = 0;
        try {
//            String query = "SELECT * from barang where kode_barang = '" + kode + "'";
            PreparedStatement ps = conn.prepareStatement("SELECT * from barang where kode_barang = '" + kode + "'");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
            id = Integer.parseInt(rs.getString("id"));
            harga = Integer.parseInt(rs.getString("harga"));
            hargatotal = harga * jumlah;
            nama = rs.getString("nama_barang");
            kategori = rs.getString("kategori");
                list.add(
//                        new Barang2(rs.getString("kode_barang"), rs.getString("nama_barang"), rs.getString("kategori"), Integer.parseInt(rs.getString("harga")), Integer.parseInt(rs.getString("stok")))
                        new Barang2(x++, kode, nama, kategori, hargatotal, jumlah)
                );
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    @FXML
    public void proses(ActionEvent event) throws SQLException {
        Connection con = connect_db.getDBConnection();
        PreparedStatement stmt = null;
        PreparedStatement ps = null;
//
        int jumlah = Integer.parseInt(txtjlh.getText());
//        int uangKembalian = Integer.parseInt(uang.getText());
        String kode = txtkode.getText();
        String nama = null;
        String kategori = null;
        int harga = 0;
        int lharga[] = null;
        int sum = 0;
        int count = 0;
        int id = 0;
        
        
//
        String query = "SELECT * from barang where kode_barang = '" + kode + "'";
        stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery(query);
//        
        while (rs.next()) {
//        j++;
////            harga = Integer.parseInt(rs.getString("harga"));
            id = Integer.parseInt(rs.getString("id"));
            harga = Integer.parseInt(rs.getString("harga"));
            hargatotal = harga * jumlah;
            nama = rs.getString("nama_barang");
            kategori = rs.getString("kategori");
//            
////            System.out.println("No :"+x);
//            System.out.println("Kode Barang :"+kode);
//            System.out.println("Nama Barang :"+nama);
//            System.out.println("Kategori :"+kategori);
//            System.out.println("Harga :"+harga);
//            System.out.println("Jumlah Dibeli :"+jumlah);
//            System.out.println("Total harga:"+hargatotal);
//            System.out.println("Total yang harus di bayar adalah:");
//            System.out.println("====================================");

            String xString = Integer.toString(x);

        }
                notable.setCellValueFactory(new PropertyValueFactory<>("no"));
                kodetable.setCellValueFactory(new PropertyValueFactory<>("kode"));
                namatable.setCellValueFactory(new PropertyValueFactory<>("nama"));
                kategoritable.setCellValueFactory(new PropertyValueFactory<>("kategori"));
                hargatable.setCellValueFactory(new PropertyValueFactory<>("harga"));
                jumlahtable.setCellValueFactory(new PropertyValueFactory<>("jlh"));
        
        listM = getDatabarang();
//        for(int i=0;i<observableList.size();i++){
            table.setItems(listM);
            
//        }
total.setText(""+hargatotal);
        
    }
    
    @FXML
    void uang(ActionEvent event) {
        int uangK = Integer.parseInt(uang.getText());
        if(uangK < hargatotal){
            JOptionPane.showMessageDialog(null, "Uang tidak cukup");
        }else{
        int kalkulasi = uangK - hargatotal;
        total1.setText(""+kalkulasi);
    }
    }

    @FXML
    void save(ActionEvent event) throws SQLException {

        int id = 0;

        Connection con = connect_db.getDBConnection();
        PreparedStatement ps = null;
        PreparedStatement stmt = null;
        int jumlah = Integer.parseInt(txtjlh.getText());
        String query = "SELECT * from barang where kode_barang = '" + txtkode.getText() + "'";
        stmt = con.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery(query);
        while (resultSet.next()) {
            id = Integer.parseInt(resultSet.getString("id"));
        }

        String sql = "INSERT INTO transaksi (id_barang, jumlah) VALUES('" + id + "','" + jumlah + "')";
        ps = con.prepareStatement(sql);
        ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Berhasil di simpan");
        con.close();
    }

    @FXML
    void hapus(ActionEvent event) {

    }

    @FXML
    private Hyperlink home;

    @FXML
    private Hyperlink barang;

    @FXML
    private Hyperlink logout;

    @FXML
    public void home(ActionEvent event) throws IOException {
//        p.changeScene("/Ui/mesin_kasir.fxml");
        Parent root = FXMLLoader.load(getClass().getResource("/Ui/mesin_kasir.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void barang(ActionEvent event) throws IOException {
//        p.changeScene("/Ui/daftar_barang.fxml");
        Parent root = FXMLLoader.load(getClass().getResource("/Ui/Barang.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}

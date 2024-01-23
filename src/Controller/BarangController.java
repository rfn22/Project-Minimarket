/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author BENEDICT TAMBUNAN
 */
import Model.Barang;
import com.mysql.cj.xdevapi.Statement;
import connection.connect_db;
import connection.mysqlconnect;
import static connection.mysqlconnect.ConnectDb;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;


public class BarangController implements Initializable {
    

    @FXML
    private TableView<Barang> table_barang;

    @FXML
    private TableColumn<Barang, String> kode_barang;

    @FXML
    private TableColumn<Barang, String> nama_barang;

    @FXML
    private TableColumn<Barang, Integer> harga;

    @FXML
    private TableColumn<Barang, Integer> stok;

    @FXML
    private TableColumn<Barang, String> kategori;
    
     @FXML
    private TextField txt_namaBarang;

    @FXML
    private TextField txt_harga;

    @FXML
    private TextField txt_stok;

    @FXML
    private TextField txt_kategori;
        
    @FXML
    private TextField txt_kodeBarang;
    
    @FXML
    private TextField filterField;
    
       
    ObservableList<Barang> listM;
    ObservableList<Barang> dataList;
    
   
    
    int index = -1;
    
    Connection conn =null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
     public static ObservableList<Barang> getDatabarang(){
        Connection conn = ConnectDb();
        ObservableList<Barang> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from barang");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){   
                list.add(new Barang(rs.getString("kode_barang"), rs.getString("nama_barang"), rs.getString("kategori"), Integer.parseInt(rs.getString("harga")), Integer.parseInt(rs.getString("stok"))));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public void Add_barang () throws SQLException{
        int count = 0;
        conn = mysqlconnect.ConnectDb();
        String sql = "insert into barang (kode_barang,nama_barang,harga,stok,kategori)values(?,?,?,?,? )";

            PreparedStatement stmt=null;
            Connection con = connect_db.getDBConnection();
            
            con.setAutoCommit(false);
            String query = "SELECT COUNT(*) FROM barang AS count where kode_barang = '"+txt_kodeBarang.getText()+"'";
            stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
            count = resultSet.getInt(1);
    }
            if(count > 0){
                JOptionPane.showMessageDialog(null, "Kode Barang Sudah Ada");
                return;
            }
            
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_kodeBarang.getText());
            pst.setString(2, txt_namaBarang.getText());
            pst.setString(3, txt_harga.getText());
            pst.setString(4, txt_stok.getText());
            pst.setString(5, txt_kategori.getText());
            pst.execute();
            
            txt_kodeBarang.setText("");
            txt_namaBarang.setText("");
            txt_harga.setText("");
            txt_stok.setText("");
            txt_kategori.setText("");
            
            JOptionPane.showMessageDialog(null, "Berhasil Tambah Barang");
            UpdateTable();
            search_barang();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
   
    @FXML
    void getSelected (MouseEvent event){
    index = table_barang.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    txt_kodeBarang.setText(kode_barang.getCellData(index).toString());
    txt_namaBarang.setText(nama_barang.getCellData(index).toString());
    txt_harga.setText(harga.getCellData(index).toString());
    txt_stok.setText(stok.getCellData(index).toString());
    txt_kategori.setText(kategori.getCellData(index).toString());
    
    }

    public void Edit (){   
        try {
            conn = mysqlconnect.ConnectDb();
            String value1 = txt_kodeBarang.getText();
            String value2 = txt_namaBarang.getText();
            String value3 = txt_harga.getText();
            String value4 = txt_stok.getText();
            String value5 = txt_kategori.getText();
            String sql = "update barang set nama_barang= '"+value2+"',harga= '"+
                    value3+"',stok= '"+value4+"',kategori= '"+value5+"' where kode_barang='"+value1+"' ";
            pst= conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil di Ubah");
            UpdateTable();
            search_barang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void Delete(){
    conn = mysqlconnect.ConnectDb();
    String sql = "delete from barang where kode_barang = ?";
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, txt_kodeBarang.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Berhasil di Hapus");
            UpdateTable();
            search_barang();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    }

    
    public void UpdateTable(){
        kode_barang.setCellValueFactory(new PropertyValueFactory<Barang,String>("kode"));
        nama_barang.setCellValueFactory(new PropertyValueFactory<Barang,String>("nama"));
        harga.setCellValueFactory(new PropertyValueFactory<Barang,Integer>("harga"));
        stok.setCellValueFactory(new PropertyValueFactory<Barang,Integer>("stok"));
        kategori.setCellValueFactory(new PropertyValueFactory<Barang,String>("kategori"));
        
        listM = getDatabarang();
        table_barang.setItems(listM);
    }
    
    

     @FXML
    void search_barang() {          
        kode_barang.setCellValueFactory(new PropertyValueFactory<Barang,String>("kode"));
        nama_barang.setCellValueFactory(new PropertyValueFactory<Barang,String>("nama"));
        harga.setCellValueFactory(new PropertyValueFactory<Barang,Integer>("harga"));
        stok.setCellValueFactory(new PropertyValueFactory<Barang,Integer>("stok"));
        kategori.setCellValueFactory(new PropertyValueFactory<Barang,String>("kategori"));
        
        dataList = getDatabarang();
        table_barang.setItems(dataList);
        FilteredList<Barang> filteredData = new FilteredList<>(dataList, b -> true);  
 filterField.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(data -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (data.getNama().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; 
    }else if (data.getKode().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; 
    }else if (data.getKategori().toLowerCase().indexOf(lowerCaseFilter)!=-1)
         return true;
     return false;
   });
  });  
  SortedList<Barang> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(table_barang.comparatorProperty());  
  table_barang.setItems(sortedData);      
    }
    
    @FXML
    public void kembali(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Ui/mesin_kasir.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    UpdateTable();
    search_barang();
    } 
}

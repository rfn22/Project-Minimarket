package Controller;

import Ui.ProjectPbo3;
import Ui.ProjectPbo3;
import com.sun.tools.javac.Main;
import connection.connect_db;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class LoginController {
    

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Button btnlogin;
    
     @FXML
    private Label notif;
     
    private Scene scene;

     @FXML
    public void login(ActionEvent event) throws IOException, SQLException {
        int count = 0;
        Connection con = connect_db.getDBConnection();
        PreparedStatement stmt=null;
        PreparedStatement ps=null;
       
            String query = "SELECT COUNT(*) FROM admin AS count where password = '"+txtpassword.getText()+"'";
            stmt = con.prepareStatement(query);
            ResultSet resultSet = stmt.executeQuery(query);
            while(resultSet.next()){
            count = resultSet.getInt(1);
                }
            if(count > 0){
        Parent root = FXMLLoader.load(getClass().getResource("/Ui/mesin_kasir.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
            
        }else if(txtpassword.getText().isEmpty()){
            notif.setText("Masukkan password");
        }else{     
            notif.setText("Password Salah");
        

}
}
}
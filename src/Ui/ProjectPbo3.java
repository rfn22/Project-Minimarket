/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package Ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import Controller.MesinKasirController;
import Controller.LoginController;
import java.io.IOException;
import java.util.Objects;
import javafx.scene.Node;
import javafx.stage.Window;
import javax.swing.Action;
import javafx.event.ActionEvent;

/**
 *
 * @author BENEDICT TAMBUNAN
 */
public class ProjectPbo3 extends Application {

    private static Stage stage;
    private Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

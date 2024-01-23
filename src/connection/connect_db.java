/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BENEDICT TAMBUNAN
 */
public class connect_db {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost/project_minimarket";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    

    public static Connection getDBConnection() throws SQLException {
        Connection connection = null;
        
        try{
            Class.forName(DB_DRIVER);
        }catch(ClassNotFoundException exception){
            System.out.println("Driver tidak ditemukan " + exception);
        }
        try{
            connection  = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return connection;
        }catch(SQLException exception){
            System.out.println("Koneksi Gagal " + exception);
        }
        return connection;
    }
}

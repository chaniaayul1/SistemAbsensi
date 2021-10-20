/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smkcendekia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class koneksi {
    Connection con;
    Statement stm;
    
    public void config(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/smkcendekia","root","");
            //con=DriverManager.getConnection("jdbc:mysql://192.168.0.104/smkcendekia","cendekia","smkcendekia");
            //con=DriverManager.getConnection("jdbc:mysql://192.168.0.100/smkcendekia","cendekia","smkcendekia");
            stm = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal ");
        }
    }
}

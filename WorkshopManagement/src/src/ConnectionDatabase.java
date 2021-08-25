/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;
import java.sql.Connection;
import java.sql.DriverManager;
//import javax.swing.JOptionPane;
//import java.sql.Statement;

/**
 *
 * @author CHRIS
 */
public class ConnectionDatabase {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/cendekia", "root", "");
            System.out.println("Success to connect database");
            return connect;
        } catch (Exception ex) {
            System.out.println("Failed to connect database");
            return null;
        }
    }
}

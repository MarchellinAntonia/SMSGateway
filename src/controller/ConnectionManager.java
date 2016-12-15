/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author chellin
 */
public class ConnectionManager {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/sms?autoReconnect=true&useSSL=false";
    private String username = "chellin";
    private String password = "Kosongin@474";
    public Connection con;

    public Connection logOn() {
        try {
            Class.forName(driver).newInstance();
            //Buat object connection
            con = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occoured when LogIn" + e);
        }
        return con;
    }

    public void LogOff() {
        //tutup koneksi
        try {
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occoured when LogIn" + e);
        }
    }

    public void connect() {
        try {
            con = logOn();
        } catch (Exception e) {
            System.out.println("Error occoured when connecting to database");
        }
    }

    public void disconnect() {
        try {
            LogOff();
        } catch (Exception e) {
            System.out.println("Error occoured when connecting to database");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.ConnectionManager;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Messages;

/**
 *
 * @author chellin
 */
public class SMSCommand {
    //buat ambil semua data
    public static ArrayList<Messages> getAllInbox() throws SQLException {
        ArrayList<Messages> list = new ArrayList<>();
        ConnectionManager conn = new ConnectionManager();
        conn.connect();

        Statement stm = conn.con.createStatement();
        ResultSet rs = stm.executeQuery("Select * from inbox");

        while (rs.next()) {
            Messages msg = new Messages(rs.getString("ID"), rs.getString("ReceivingDateTime"), rs.getString("SMSCNumber"), rs.getString("SenderNumber"), rs.getString("TextDecoded"), "0");
            list.add(msg);
        }
        conn.disconnect();
        return list;
    }
    
    public static ArrayList<Messages> getAllOutbox() throws SQLException {
        ArrayList<Messages> list = new ArrayList<>();
        ConnectionManager conn = new ConnectionManager();
        conn.connect();

        Statement stm = conn.con.createStatement();
        ResultSet rs = stm.executeQuery("Select * from outbox");

        while (rs.next()) {
            Messages msg = new Messages(rs.getString("ID"), rs.getString("SendingDateTime"), rs.getString("DestinationNumber"), rs.getString("SenderID"), rs.getString("TextDecoded"), "0");
            list.add(msg);
        }
        conn.disconnect();
        return list;
    }

    public static void insertOutbox(Messages msg) throws SQLException {
        ConnectionManager conn = new ConnectionManager();
        conn.connect();
        
        System.out.println("masuk insert outbox");
        
        String query = "insert into outbox(DestinationNumber, TextDecoded, creatorID)VALUES(?,?,?)";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setString(1, msg.getRecipient());
        stmt.setString(2, msg.getMessages());
        stmt.setString(3, "Gammu");
        stmt.executeUpdate();
        conn.disconnect();
    }

    public void deleteInboxById(String id) {
        ConnectionManager conn = new ConnectionManager();
        conn.connect();

        Statement stm;
        try {
            stm = conn.con.createStatement();
            stm.executeQuery("DELETE from inbox WHERE ID ="+id);

            conn.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(SMSCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deleteOutboxById(String id) {
        ConnectionManager conn = new ConnectionManager();
        conn.connect();

        Statement stm;
        try {
            stm = conn.con.createStatement();
            stm.executeQuery("DELETE from outbox WHERE ID ="+id);

            conn.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(SMSCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    public void deleteCustomerByName(String name) {
//
//        ConnectionManager conn = new ConnectionManager();
//        conn.connect();
//
//        Statement stm;
//        try {
//            stm = conn.con.createStatement();
//            stm.executeQuery("DELETE from customer WHERE customer_name ="+name);
//
//            conn.disconnect();
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseCRUD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    public Messages getCustomerById(String id) {
//        Messages cust = new Messages();
//        ConnectionManager conn = new ConnectionManager();
//        conn.connect();
//
//        Statement stm;
//        try {
//            stm = conn.con.createStatement();
//            ResultSet rs = stm.executeQuery("Select * from customer WHERE customer_id ="+id);
//
//            while (rs.next()) {
//                cust = new Messages(rs.getString("customer_id"), rs.getString("customer_name"), rs.getString("customer_idcard"), rs.getString("customer_username"), rs.getString("customer_password"), rs.getString("customer_address"),rs.getString("customer_address"), "", null);
//            }
//            conn.disconnect();
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseCRUD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return cust;
//
//    }
//
//    public Messages getCustomerByName(String name) {
//        Messages cust = new Messages();
//        ConnectionManager conn = new ConnectionManager();
//        conn.connect();
//
//        Statement stm;
//        try {
//            stm = conn.con.createStatement();
//            ResultSet rs = stm.executeQuery("Select * from customer WHERE customer_name ="+name);
//
//            while (rs.next()) {
//                cust = new Messages(rs.getString("customer_id"), rs.getString("customer_name"), rs.getString("customer_idcard"), rs.getString("customer_username"), rs.getString("customer_password"), rs.getString("customer_address"),rs.getString("customer_address"), "", null);
//            }
//            conn.disconnect();
//        } catch (SQLException ex) {
//            Logger.getLogger(DatabaseCRUD.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        return cust;
//
//    }
//    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Messages;

/**
 *
 * @author chellin
 */
public class DatabaseCRUD {

    public ArrayList<Messages> getAllInbox() throws SQLException {
        ArrayList<Messages> list = new ArrayList<>();
        ConnectionManager conn = new ConnectionManager();
        conn.connect();

        Statement stm = conn.con.createStatement();
        ResultSet rs = stm.executeQuery("Select * from inbox");

        while (rs.next()) {
            Messages mhs = new Messages(rs.getString("inbox_id"), rs.getString("inbox_message"), rs.getString("inbox_status"));
            list.add(mhs);
        }
        conn.disconnect();
        return list;
    }
    public ArrayList<Messages> getAllOutbox() throws SQLException {
        ArrayList<Messages> list = new ArrayList<>();
        ConnectionManager conn = new ConnectionManager();
        conn.connect();

        Statement stm = conn.con.createStatement();
        ResultSet rs = stm.executeQuery("Select * from outbox");

        while (rs.next()) {
            Messages mhs = new Messages(rs.getString("outbox_id"), rs.getString("outbox_message"), rs.getString("outbox_status"));
            list.add(mhs);
        }
        conn.disconnect();
        return list;
    }

    public void insertInbox(Messages msg) throws SQLException {
        ConnectionManager conn = new ConnectionManager();
        conn.connect();
        
        String query = "insert into inbox value (?,?,?)";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setString(1, msg.getId());
        stmt.setString(2, msg.getMessages());
        stmt.setString(3, msg.getStatus());
        stmt.executeUpdate();
        conn.disconnect();
    }
    
    public void insertOutbox(Messages msg) throws SQLException {
        ConnectionManager conn = new ConnectionManager();
        conn.connect();
        
        String query = "insert into outbox value (?,?,?)";
        PreparedStatement stmt = conn.con.prepareStatement(query);
        stmt.setString(1, msg.getId());
        stmt.setString(2, msg.getMessages());
        stmt.setString(3, msg.getStatus());
        stmt.executeUpdate();
        conn.disconnect();
    }

    public void deleteInboxById(String id) {
        ConnectionManager conn = new ConnectionManager();
        conn.connect();

        Statement stm;
        try {
            stm = conn.con.createStatement();
            stm.executeQuery("DELETE from inbox WHERE inbox_id ="+id);

            conn.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deleteOutboxById(String id) {
        ConnectionManager conn = new ConnectionManager();
        conn.connect();

        Statement stm;
        try {
            stm = conn.con.createStatement();
            stm.executeQuery("DELETE from outbox WHERE outbox_id ="+id);

            conn.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author axis
 */
public class SQLHandler {

    /**
     *
     * @param query
     * @return ResultSet
     * @throws SQLException
     */
    public static ResultSet queryer(String query) throws SQLException {
        Connection conn = MySQLConnector.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public static ResultSet queryer(String query, PreparedStatement ps) throws SQLException {
        Connection conn = MySQLConnector.getConnection();
        ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    
    /**
     *
     * @param tablename
     * @param where
     * @return
     * @throws SQLException
     */
    public static ResultSet queryer(String tablename, String where, int is) throws SQLException {
        String query    =  "SELECT * FROM household WHERE headoffamily=?";//"SELECT * FROM ? WHERE ?=?;";
        Connection conn = MySQLConnector.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
//        ps.setString(1, tablename);
//        ps.setString(2, where);
        ps.setInt(1, is);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    
    public static ResultSet queryer(String tablename, String where, String is) throws SQLException {
        String query    =  "SELECT * FROM household WHERE headoffamily=?";//"SELECT * FROM ? WHERE ?=?;";
        Connection conn = MySQLConnector.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
//        ps.setString(1, tablename);
//        ps.setString(2, where);
        ps.setString(1, is);
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    
    /**
     *
     * @param query
     * @param columnNames
     * @throws SQLException
     */
    public static void updater(String query, String[] columnNames) throws SQLException {
        Connection conn = MySQLConnector.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate(query, columnNames);
    }
    
    /**
     *
     * @param query
     * @throws SQLException
     */
    public static void updater(String query) throws SQLException {
        Connection conn = MySQLConnector.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.executeUpdate(query);
    }
    
    /**
     *
     * @param query
     * @param name
     * @return Result
     * @throws SQLException
     */
    public static ResultSet nameQueryer(String query, String name) throws SQLException {
        
        Connection conn = MySQLConnector.getConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    public static String idtoNAME(int id) {
        try { //returns concatenated name String  format:Fname Fname Mname Last Name

            String query = "SELECT lname, fname, mname FROM person WHERE id = ?";
            PreparedStatement ps;
            ps = MySQLConnector.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) { //invocation error daw kay walay rs.next ==== nag error pod sauna sa tiles sa home page
                String last = rs.getString(1);
                String first = rs.getString(2);
                String middle = rs.getString(3);
                String NAME = first + " " + middle + " " + last;
                return NAME;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}

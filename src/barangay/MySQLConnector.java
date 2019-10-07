/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author axis
 */
public class MySQLConnector {
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/aplaya?serverTimezone=Asia/Manila";
    private static final String USER = "root";
    private static final String PASS = "Quelly119";
    
    /**
     *
     * @return Returns the database connection.
     */
    public static Connection getConnection(){
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return conn;
    }
}

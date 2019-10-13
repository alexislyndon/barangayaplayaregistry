/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay.UserAuth;

import db.MySQLConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author axis
 */
public class User {
    
    private static String current;
    private static int staffID;
    
    /**
     *
     * @param user
     */
    public User(String user) {
        current = user;
    }
    
    /**
     *
     * @return
     */
    public static String getUser() {
        return current;
    }
    
    /**
     *
     * @param user
     */
    public static void setUser(String user) throws SQLException {
        current = user;
        setStaffID();
    }
    
    private static void setStaffID() throws SQLException {
        if(current.length()==0){
            staffID = 5; //5 is = axis the coder
            System.out.println("Default user axis logged in.");
        } else {
            String q = "select * FROM brgystaff inner join users on brgystaff.username=users.username where users.username = ?";
            PreparedStatement ps = MySQLConnector.getConnection().prepareStatement(q);
            ps.setString(1, current);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                staffID = rs.getInt("staff_ID");
                System.out.println("User " + current + "with staffID " + staffID + " successfully logged in.");
            }
        }
    }
    
    public static int getStaff() {
        return staffID;
    }
}

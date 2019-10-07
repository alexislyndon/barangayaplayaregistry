/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barangay.UserAuth;

/**
 *
 * @author axis
 */
public class User {
    
    private static String current;
    
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
    public static void setUser(String user) {
        current = user;
    }
}

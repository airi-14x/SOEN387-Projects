/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public class Session {
    
    public static String hashPassword(String password) {
        String hashedPassword = null;
        
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            md.update(password.getBytes());
            
            byte[] bytes = md.digest();
            
            StringBuilder sb = new StringBuilder();
            
            for(int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            hashedPassword = sb.toString();
        }
        catch(NoSuchAlgorithmException e) {
        }
        return hashedPassword;
    }
    
    public String getCurrentUser() {
        return null;
    }
    
    public boolean isUserLoggedIn() {
        return false;
    }
    
    public boolean login(String userId, String password){
        return false;
    }
    
    public boolean logout() {
        return false; 
    }
    
    public static void main(String[] args) {
        String passwordTest = "thisIsATest123";
        System.out.print(Session.hashPassword(passwordTest));
    }
}

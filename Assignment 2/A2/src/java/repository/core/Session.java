/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public class Session {
    
    private String currentUser = null;
    
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
        return currentUser;
    }
    
    public boolean isUserLoggedIn() {
        if (currentUser == null) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public boolean login(String userId, String password){
        return false;
    }
    
    public boolean logout() {
        this.currentUser = null;
        return true;
    }
    
    public static void createJsonObject() throws IOException {
        //User 1
        JSONObject userInfo1 = new JSONObject();
        userInfo1.put("userId", "Jasmine");
        userInfo1.put("password", hashPassword("Test123"));
        
        JSONObject user1 = new JSONObject();
        user1.put("user", userInfo1);
        
        //User 2
        JSONObject userInfo2 = new JSONObject();
        userInfo2.put("userId", "Airi");
        userInfo2.put("password", hashPassword("anotherPassword123"));
        
        JSONObject user2 = new JSONObject();
        user2.put("user", userInfo2);
        
        //User 3
        JSONObject userInfo3 = new JSONObject();
        userInfo3.put("userId", "Bob");
        userInfo3.put("password", hashPassword("bobPswd456"));
        
        JSONObject user3 = new JSONObject();
        user3.put("user", userInfo3);
        
        
        try (FileWriter file = new FileWriter("./users.json")) {
            file.write(user1.toJSONString());
            file.write(user2.toJSONString());
            file.write(user3.toJSONString());
            
            System.out.println("Users successfully written to JSON file.");
        }
    }
    
    public static void main(String[] args) throws IOException {
        String passwordTest = "thisIsATest123";
        System.out.println(hashPassword(passwordTest));
        
        createJsonObject();
    }
}

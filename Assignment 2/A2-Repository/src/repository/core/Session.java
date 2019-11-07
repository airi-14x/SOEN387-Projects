/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public class Session {

    private JSONObject currentUser = null;

    public String hashPassword(String password) {
        String hashedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(password.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            hashedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return hashedPassword;
    }

    public String getCurrentUser() {
        return currentUser.toString();
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }


    public String login(String uId, String uPassword) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Properties config = new Properties();
        String result = "";
        try {

            //(FileReader reader = new FileReader("./users.json"))
            InputStream input = new FileInputStream("config.properties");

            config.load(input);
            //System.out.println(config.getProperty("path"));
            String path = config.getProperty("path"); //GET "path"'s value
            FileReader reader = new FileReader(path);

            Object usr = parser.parse(reader);
            JSONArray users = (JSONArray) usr;

            for (Object obj : users) {
                JSONObject user = (JSONObject) obj;
                JSONObject userObject = (JSONObject) user.get("user");

                String userId = (String) userObject.get("userId");

                String password = (String) userObject.get("password");

                if (userId.equals(uId)) {
                    String psw = hashPassword(uPassword);

                    if (psw.equals(password)) {
                        this.currentUser = (JSONObject) obj;
                        result = "SUCCESS";
                    }
                    else {
                        result = "FAIL";
                    }
                }
            }
        } catch (IOException | ParseException e) {

        }
        return result;
    }

    public boolean logout() {
        this.currentUser = null;
        return true;
    }

    public void createJsonObject() throws IOException {
        //User 1
        JSONObject userInfo1 = new JSONObject();
        userInfo1.put("userId", "Jasmine");
        userInfo1.put("password", hashPassword("test123"));

        JSONObject user1 = new JSONObject();
        user1.put("user", userInfo1);

        //User 2
        JSONObject userInfo2 = new JSONObject();
        userInfo2.put("userId", "Airi");
        userInfo2.put("password", hashPassword("test456"));

        JSONObject user2 = new JSONObject();
        user2.put("user", userInfo2);

        //User 3
        JSONObject userInfo3 = new JSONObject();
        userInfo3.put("userId", "Bob");
        userInfo3.put("password", hashPassword("test789"));

        JSONObject user3 = new JSONObject();
        user3.put("user", userInfo3);

        JSONArray userList = new JSONArray();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        try (FileWriter file = new FileWriter("./users.json")) {
            file.write(userList.toJSONString());

            System.out.println("Users successfully written to JSON file.");
        }
    }
    public static void main(String[] args) throws IOException, ParseException {
        Session session = new Session();
        System.out.println(session.login("Jasmine", "test123"));
        
       
    }
}
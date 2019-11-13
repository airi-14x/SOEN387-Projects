/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public class Session {

    private static JSONObject currentUser = null;

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
    
    public void setCurrentUser(JSONObject user) {
        this.currentUser = user;
    }

    public static String getCurrentUser() {
        return currentUser.toString();
    }
    
    public static String getUserName() {
        return (String) currentUser.get("userId");
    }

    public boolean isUserLoggedIn() {
        return currentUser != null;
    }

    private static JSONObject getUser(JSONObject user) {
        JSONObject currentUser = new JSONObject();
        JSONObject userObject = (JSONObject) user.get("user");

        String userName = (String) userObject.get("userId");

        String password = (String) userObject.get("password");

        String[] userString = {"", ""};
        userString[0] = userName;
        userString[1] = password;

        currentUser.put("userId", userString[0]);
        currentUser.put("password", userString[1]);

        System.out.println(Arrays.toString(userString));

        return currentUser;
    }

    public Boolean login(String uId, String uPassword) {
        JSONParser parser = new JSONParser();
        //Properties config = new Properties();
        Boolean result = false;
        ArrayList<JSONObject> userList = new ArrayList<>();
        try {

            //FileReader reader = new FileReader("./users.json");
            //InputStream input = new FileInputStream("config.properties");
            //config.load(input);
            //System.out.println(config.getProperty("path"));
            //String path = config.getProperty("path"); //GET "path"'s value
            //Object usr = parser.parse(path);
            FileReader reader = new FileReader("./users.json");

            Object usr = parser.parse(reader);
            JSONArray users = (JSONArray) usr;

            for (int i = 0; i < users.size(); i++) {
                userList.add(getUser((JSONObject) users.get(i)));
            }
            System.out.println(userList);

            for (JSONObject o : userList) {
                String userPassword = (String) o.get("password");
                if (o.get("userId").equals(uId)) {
                    String pw = hashPassword(uPassword);
                    if (pw.equals(userPassword)) {
                        this.currentUser = o;
                        System.out.println(currentUser.get("userId"));
                        System.out.println(currentUser.get("password"));
                        result = true;
                    } else {
                        result = false;
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

    void createJsonObject() {
        //User 1
        JSONObject userInfo1 = new JSONObject();
        userInfo1.put("userId", "Jasmine");
        userInfo1.put("password", hashPassword("test123"));

        JSONObject user1 = new JSONObject();
        user1.put("user", userInfo1);

        //User 2
        JSONObject userInfo2 = new JSONObject();
        userInfo2.put("userId", "Airi");
        userInfo2.put("password", hashPassword("test123"));

        JSONObject user2 = new JSONObject();
        user2.put("user", userInfo2);

        //User 3
        JSONObject userInfo3 = new JSONObject();
        userInfo3.put("userId", "Bob");
        userInfo3.put("password", hashPassword("test123"));

        JSONObject user3 = new JSONObject();
        user3.put("user", userInfo3);

        //Add employees to list
        JSONArray userList = new JSONArray();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        //Write JSON file
        try (FileWriter file = new FileWriter("./users.json")) {

            file.write(userList.toJSONString());
            file.flush();

        } catch (IOException e) {
        }
    }

    public static void main(String[] args) throws IOException, ParseException {
        Session session = new Session();
        System.out.println(session.login("Jasmine", "test123"));

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.sql.*;

/**
 *
 * @author jasminelatendresse
 */
public class Database {
    private static Database instance;
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet resultSet = null;
    private String user = "root";
    private String pwd = "root1234";
    
    private Database(){
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookRepo", user, pwd);
            stmt = conn.createStatement();
        }
        catch(SQLException e){
        }
    }
    
    public ResultSet execute(String query){
        try{
            resultSet = stmt.executeQuery(query);
        }
        catch(SQLException e){
        }
        return resultSet;
    }
    
    public static Database getInstance(){
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }
    
    public void disconnect(){
        try{
            conn.close();
        }
        catch(SQLException e){
        }
    }
    
    public static void main(String[] args) throws SQLException{
        
    }
    
}

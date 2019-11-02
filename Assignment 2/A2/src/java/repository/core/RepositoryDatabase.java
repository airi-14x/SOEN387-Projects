/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Airi
 */
public class RepositoryDatabase {

    private static RepositoryDatabase database_instance; // SINGLETON
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet result_set = null;

    private String user = "root";
    private String pass = "root1234";

    private RepositoryDatabase() {

        // 1. Get a connection to database
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookRepo?serverTimezone=UTC", user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // SINGLETON
    public static RepositoryDatabase getInstance() {
        if (database_instance == null) {
            database_instance = new RepositoryDatabase();
        }
        return database_instance;
    }

    public Statement createStatement() {

        try {
            // 2. Create a statement
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement;
    }

    public ResultSet executeQuery(String query) {
        try {
            // 3. Execute SQL query
            result_set = statement.executeQuery("select * from book");
            // 4. Process the result set
            while (result_set.next()) {
                System.out.println(result_set.getString("id") + ", "
                        + result_set.getString("title"));
                System.out.println(result_set.getString("image_mime"));
                System.out.println(result_set.getBlob("image_data"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result_set;
    }
    
    public int executeUpdate(String query) {
        int update = 0;
        
        try {
            update = statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return update;
    }

    public void cleanup() {

        try {
            if (result_set != null) {
                result_set.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws SQLException {
        RepositoryDatabase database = new RepositoryDatabase();
        database.createStatement();
        //database.executeQuery();
        database.cleanup();
    }

}

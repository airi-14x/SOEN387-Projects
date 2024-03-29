/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

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
public class BookRepositoryDatabase {

    private static BookRepositoryDatabase database_instance; // SINGLETON
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet result_set = null;

    private String user = "root";
    private String pass = "root1234";

    private BookRepositoryDatabase() {

        // 1. Get a connection to database
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookRepo?serverTimezone=UTC", user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BookRepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnectionInstance() {
        return connection;
    }

    // SINGLETON
    public static BookRepositoryDatabase getInstance() {
        if (database_instance == null) {
            database_instance = new BookRepositoryDatabase();
            System.out.println("DAL.BookRepositoryDatabase - Instance is created!");
        }
        return database_instance;
    }

    public Statement createStatement() {

        try {
            // 2. Create a statement
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statement;
    }

    public ResultSet executeQuery(String query) {
        try {
            createStatement();
            result_set = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result_set;
    }

    public int executeUpdate(String query) {
        int update = 0;
        try {
            createStatement();
            update = statement.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(BookRepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

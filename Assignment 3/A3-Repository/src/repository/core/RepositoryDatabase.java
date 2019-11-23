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
import java.util.ArrayList;
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
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookRepo?serverTimezone=UTC", user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnectionInstance() {
        return connection;
    }

    // SINGLETON
    public static RepositoryDatabase getInstance() {
        if (database_instance == null) {
            database_instance = new RepositoryDatabase();
            System.out.println("RepositoryDatabase - Instance is created!");
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
            createStatement();
            result_set = statement.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result_set;
    }

    public int executeUpdate(String query) {
        int update = 0;
        try {
            createStatement();
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

    public static void main(String[] args) throws SQLException, RepositoryException {
        RepositoryDatabase database = new RepositoryDatabase();
        database.createStatement();

        Session session = null;
        session = new Session();
        session.login("Jasmine", "test123");

        BookRepository b1 = BookRepository.getInstance();
        System.out.println();
        System.out.println("DROP TABLE");
        System.out.println();
        b1.dropBookTable();
        System.out.println("CREATE TABLE");
        System.out.println();

        b1.createBookTable(session);
        ArrayList<Book> books;

        CoverImage cover1 = new CoverImage();
        cover1.setMimeType("image/jpeg");
        cover1.setImagePath("./endofownership_photo_final.jpeg");

        Author author = new Author("Aurelius", "Marcus");
        Book book1 = new Book("Meditations", "Written in Greek, without any intention of publication, by the only Roman emperor", "0140449337", author,
                "Penguin Classic", "England", cover1);

        CoverImage cover2 = new CoverImage();
        cover2.setMimeType("image/png");
        cover2.setImagePath("./panda.png");

        Author author2 = new Author("Epictetus", "Unknown");
        Book book2 = new Book("Discourses, Fragments, Handbook", "About things that are within our power and those that are not.", "0199595186",
                author2, "Oxford University Press", "England", cover2);

        Author author3 = new Author("Kishimi", "Ichiro");
        CoverImage cover3 = new CoverImage();
        cover3.setMimeType("image/png");
        cover3.setImagePath("./redpanda.png");
        Book book3 = new Book("Courage to be Happy", "The Courage to be Happy is a profound insight into the way we should live our lives that has already sold more than one million copies in Japan.", "1911630210", author3, "Allen & Unwin", "London, England", cover3);

        System.out.println("Adding book 1 to database");
        b1.addNewBook(session, book1);
        //System.out.println(b1.addNewBook(session, book1));
        System.out.println("Adding book 2 to database");
        b1.addNewBook(session, book2);
        //System.out.println(b1.addNewBook(session, book2));
        System.out.println("Adding book 3 to database");
        b1.addNewBook(session, book3);
        //System.out.println(b1.addNewBook(session, book3));
        
        System.out.println();
        System.out.println("Book ArrayList: ");
        books = b1.listAllBooks(session);
        for (Book book : books) {
            System.out.println(book);
        }
    }

}

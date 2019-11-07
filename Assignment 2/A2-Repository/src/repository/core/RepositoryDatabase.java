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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookRepo?serverTimezone=UTC", user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /*public static void main(String[] args) throws SQLException {
        RepositoryDatabase database = new RepositoryDatabase();
        database.createStatement();
        //database.executeUpdate("UPDATE book SET last_name = 'Hello2', first_name = 'Hello' WHERE (id = '2')");
        //database.executeUpdate("UPDATE `BookRepo`.`book` SET `image_data` = LOAD_FILE('src/java/repository/database/endofownership_photo_final.jpeg') WHERE (`id` = '2')");
        //database.executeUpdate("UPDATE `BookRepo`.`book` SET `image_data` = LOAD_FILE('endofownership_photo_final.jpeg') WHERE (`id` = '2');");
        //database.executeQuery("SELECT * FROM book");

        BookRepository b1 = BookRepository.getInstance();
        System.out.println();
        System.out.println("DROP TABLE");
        System.out.println();
        b1.dropBookTable();
        System.out.println("CREATE TABLE");
        System.out.println();
        b1.createBookTable();
        ArrayList<Book> books;

        Author author = new Author("Aurelius", "Marcus");
        Book book1 = new Book("Meditations", "Written in Greek, without any intention of publication, by the only Roman emperor", "0140449337", author,
                "Penguin Classic", "England");

        System.out.println("BEFORE adding Book 1:");
        books = b1.listAllBooks();
        System.out.println(b1.addNewBook(book1)); // GET ID

        Author author2 = new Author("Epictetus", "Unknown");
        Book book2 = new Book("Discourses, Fragments, Handbook", "About things that are within our power and those that are not.", "0199595186",
                author2, "Oxford University Press", "England");

        System.out.println();
        System.out.println("BEFORE adding Book 2:");
        books = b1.listAllBooks();
        System.out.println(b1.addNewBook(book2)); // GET ID

        Author author3 = new Author("Kishimi", "Ichiro");
        Book book3 = new Book("Courage to be Happy", "The Courage to be Happy is a profound insight into the way we should live our lives that has already sold more than one million copies in Japan.", "1911630210", author3, "Allen & Unwin", "London, England");

        System.out.println();
        System.out.println("BEFORE adding Book 3:");
        books = b1.listAllBooks();
        System.out.println(b1.addNewBook(book3)); // Get ID

        Author author4 = new Author("Laurent", "Deversa");
        System.out.println();
        System.out.println("BEFORE:");
        books = b1.listAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
        b1.updateBookInfo(2, "Margin", "1232", author4); // UPDATE

        System.out.println();
        System.out.println("AFTER:");
        books = b1.listAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println();
        System.out.println("Delete one book:");
        b1.deleteBook(3);

        books = b1.listAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }

        //b1.deleteAllBooks();
        System.out.println(b1.addNewBook(book3)); // Get ID

        System.out.println();
        System.out.println("After - Add another book:");
        books = b1.listAllBooks();
        System.out.println(books);

        System.out.println();
        System.out.println("GetBookInfo with ID 4");
        System.out.println(b1.getBookInfo(4));

        System.out.println();
        System.out.println("GetBookInfo with ISBN \"0140449337\"");
        System.out.println(b1.getBookInfo("0140449337"));

        // /Users/Airi/Documents/SOEN387-Projects-and-Labs/Assignment\ 2/A2/tmp/endofownership_photo_final.jpeg
        //database.executeUpdate("UPDATE `BookRepo`.`book` SET `image_data` = LOAD_FILE('/Users/Airi/Documents/SOEN387-Projects-and-Labs/Assignment\\ 2/A2/tmp/endofownership_photo_final.jpeg') WHERE (`id` = '2')");
        database.executeUpdate("INSERT INTO `book`(`title`,`image_mime`,`image_data`) VALUES('The End of Ownership', 'image/jpeg', LOAD_FILE('/Users/Airi/Documents/SOEN387-Projects-and-Labs/Assignment\\ 2/A2/tmp/endofownership_photo_final.jpeg')) ");
        System.out.println();
        System.out.println("Book ArrayList: ");
        books = b1.listAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
        //database.cleanup();
    }*/

}
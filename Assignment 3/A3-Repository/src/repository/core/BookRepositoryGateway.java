/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import DAL.BookRepositoryDatabase;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Airi
 */
// Singleton
public class BookRepositoryGateway {

    private BookRepositoryDatabase repositoryDatabaseConnection;
    private static BookRepositoryGateway instance;

    private BookRepositoryGateway() {
        repositoryDatabaseConnection = BookRepositoryDatabase.getInstance();
        System.out.println("Gateway Connection " + repositoryDatabaseConnection);
    }

    public static synchronized BookRepositoryGateway getInstance() {
        if (instance == null) {
            instance = new BookRepositoryGateway();
            System.out.println("Business Core: BookRepositoryGateway - Instance is Created!");
            System.out.println("Instance " + instance);
        }
        return instance;
    }

    public ResultSet listAllBooks() {
        ResultSet resultSet = repositoryDatabaseConnection.executeQuery("SELECT * FROM book");
        return resultSet;
    }

    public ResultSet getBookInfo(int id) {
        ResultSet resultSet = repositoryDatabaseConnection.executeQuery("SELECT * FROM book WHERE id=" + id);
        return resultSet;
    }

    public ResultSet getBookInfo(String isbn) {
        ResultSet resultSet = repositoryDatabaseConnection.executeQuery("SELECT * FROM book WHERE isbn=" + isbn);
        return resultSet;
    }

    public void addNewBook(Book book) {
        repositoryDatabaseConnection.executeUpdate("INSERT INTO book(title, description,isbn, first_name, last_name, publisher_company, address) VALUES(\"" + book.getTitle() + "\",\""
                + book.getDescription() + "\",\"" + book.getISBN() + "\", \"" + book.getAuthor().getFirstName() + "\", \""
                + book.getAuthor().getLastName() + "\", \"" + book.getPublisherCompany() + "\", \"" + book.getPublisherAddress() + "\")");
    }

    public void updateBookInfo(int id, String title, String description, Author author) {
        String statement = "UPDATE book SET title = '" + title + "', description = '"
                + description + "', last_name = '" + author.getLastName() + "', first_name = '"
                + author.getFirstName() + "' WHERE id = '" + id + "';";
        System.out.println("STATEMENT: " + statement);
        repositoryDatabaseConnection.executeUpdate(statement);
    }

    public void setBookCoverImage(File image, String mimeType, int id) {
        FileInputStream input = null;
        String updateSQL = "UPDATE book SET image_data = ?, image_mime = ? WHERE id=?";

        try {
            input = new FileInputStream(image);
            PreparedStatement pstmt = repositoryDatabaseConnection.getConnectionInstance().prepareStatement(updateSQL);
            pstmt.setBinaryStream(1, input);
            pstmt.setString(2, mimeType);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookRepositoryGateway.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteBook(int id) {
        repositoryDatabaseConnection.executeUpdate("DELETE FROM book WHERE id=" + id);
    }

    public void deleteAllBooks() {
        repositoryDatabaseConnection.executeUpdate("DELETE FROM book");
    }

    public void createBookTable() {
        try {
            repositoryDatabaseConnection.executeUpdate("CREATE TABLE `book`(\n"
                    + "	`id` INT  NOT NULL AUTO_INCREMENT,\n"
                    + "    `title` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `description` VARCHAR(256) DEFAULT NULL,\n"
                    + "    `isbn` VARCHAR(64) DEFAULT NULL UNIQUE,\n"
                    + "    `last_name` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `first_name` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `publisher_company` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `address` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `image_mime` VARCHAR(256) DEFAULT NULL,\n"
                    + "    `image_data` BLOB,\n"
                    + "    PRIMARY KEY(`id`)\n"
                    + ")AUTO_INCREMENT=1;");
        } catch (Exception e) {

        }
    }

    public void dropBookTable() {
        try {
            repositoryDatabaseConnection.executeUpdate("DROP TABLE book");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

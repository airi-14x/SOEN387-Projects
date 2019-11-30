/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

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
            System.out.println("DAL BookRepositoryGateway - Instance is Created!");
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

    
    public void addNewBook(String title, String description, String isbn, String authorFirstName, String authorLastName, String publisherCompany, String publisherAddress, byte[] image, String mimeType) {
        String query = "INSERT INTO Book(title, description,isbn, first_name, last_name, publisher_company, address, image_data, image_mime) values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = repositoryDatabaseConnection.getConnectionInstance().prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, isbn);
            statement.setString(4, authorFirstName);
            statement.setString(5, authorLastName);
            statement.setString(6, publisherCompany);
            statement.setString(7, publisherAddress);
            statement.setBytes(8, image);
            statement.setString(9, mimeType);

            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(BookRepositoryGateway.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateBookInfo(int id, String title, String description, String authorFirstName, String authorLastName, byte[] image, String mimeType) {
        String query = "UPDATE book SET title=?, description=?, first_name=?, last_name=?, image_data=?, image_mime=? WHERE id=" + id;
        
        try {
            PreparedStatement statement = repositoryDatabaseConnection.getConnectionInstance().prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, description);
            statement.setString(3, authorFirstName);
            statement.setString(4, authorLastName);
            statement.setBytes(5, image);
            statement.setString(6, mimeType);
            
            statement.executeUpdate();
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

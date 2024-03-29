/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public class BookRepository implements IBookRepository {

    private ArrayList<Book> books;
    private RepositoryDatabase repositoryDatabaseConnection;
    private static BookRepository instance = null;

    private BookRepository() {
        repositoryDatabaseConnection = RepositoryDatabase.getInstance();
        System.out.println("Connection " + repositoryDatabaseConnection);
        books = new ArrayList<Book>();

    }

    public static synchronized BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
            System.out.println("BookRepository - Instance is Created!");
            System.out.println("Instance " + instance);
        }
        return instance;
    }

    @Override
    public ArrayList<Book> listAllBooks(Session session) throws RepositoryException {

        try {
            resetBooks(session);
            ResultSet resultSet = repositoryDatabaseConnection.executeQuery("SELECT * FROM book");
            if(resultSet == null) {
                throw new RepositoryException("No Books to display at the moment");
            }
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String isbn = resultSet.getString("isbn");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String publisherCompany = resultSet.getString("publisher_company");
                String address = resultSet.getString("address");
                String mimeType = resultSet.getString("image_mime");
                Blob imageData = resultSet.getBlob("image_data");

                //System.out.println("mimeType " + mimeType);
                //System.out.println("imageData " + imageData);
                //Book book = new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address);
                Book book = new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData));
                book.setId(id); // Set ID of book to match Database ID
                books.add(book);

                //books.add(new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData)));
            }

        } catch (SQLException e) {
        }
        return books; // Updated ArrayList of Book
    }

    @Override
    public Book getBookInfo(Session session, int id) {
        Book result = new Book();

        try {
            ResultSet resultSet = repositoryDatabaseConnection.executeQuery("SELECT * FROM book WHERE id=" + id);

            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String isbn = resultSet.getString("isbn");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String publisherCompany = resultSet.getString("publisher_company");
                String address = resultSet.getString("address");
                String mimeType = resultSet.getString("image_mime");
                Blob imageData = resultSet.getBlob("image_data");

                //System.out.println("mimeType " + mimeType);
                //System.out.println("imageData " + imageData);
                result = new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData));
                //result = new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address);
                result.setId(bookId); // Set ID of book to match Database ID
            }
        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public Book getBookInfo(Session session, String isbn) {
        Book result = new Book();

        try {
            ResultSet resultSet = repositoryDatabaseConnection.executeQuery("SELECT * FROM book WHERE isbn=" + isbn);
            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String bookIsbn = resultSet.getString("isbn");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String publisherCompany = resultSet.getString("publisher_company");
                String address = resultSet.getString("address");
                String mimeType = resultSet.getString("image_mime");
                Blob imageData = resultSet.getBlob("image_data");

                result = new Book(title, description, bookIsbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData));
                //result = new Book(title, description, bookIsbn, new Author(firstName, lastName), publisherCompany, address);
                result.setId(bookId); // Set ID of book to match Database ID

            }
        } catch (SQLException e) {
        }
        return result;
    }

    /**
     *
     * @param session
     * @param book
     * @return
     * @throws RepositoryException
     */
    @Override
    public int addNewBook(Session session, Book book) throws RepositoryException {
        String user = (String) Session.getCurrentUser();
        if (null == user) {
            throw new RepositoryException("You must be logged in to do this operation.");
        }

        repositoryDatabaseConnection.executeUpdate("INSERT INTO book(title, description,isbn, first_name, last_name, publisher_company, address) VALUES(\"" + book.getTitle() + "\",\""
                + book.getDescription() + "\",\"" + book.getISBN() + "\", \"" + book.getAuthor().getFirstName() + "\", \""
                + book.getAuthor().getLastName() + "\", \"" + book.getPublisherCompany() + "\", \"" + book.getPublisherAddress() + "\")");

        /*repositoryDatabaseConnection.executeUpdate("INSERT INTO book(id, title, description, isbn, last_name, first_name, publisher_company, address, mime_type, image_data) "
                + "VALUES(\"" + book.getId() + "\", \"" + book.getTitle() + "\", \"" + book.getDescription() + "\", \"" + book.getISBN() + "\",\""
                + book.getAuthor().getFirstName() + "\", \"" + book.getAuthor().getLastName() + "\", \"" + book.getPublisherCompany() + "\", \"" + book.getPublisherAddress() + "\", \"mime_type" + "\", \"image_data"
        );*/
        book.autoIncrement();

        //System.out.print("Book Current ID" + book.getId());
        if (book.getCover() != null) {
            if (book.getCover().getImagePath() != null) {
                File file = new File(book.getCover().getImagePath());
                setBookCoverImage(session, file, book.getCover().getMimeType(), book.getId());
            }

        }

        System.out.println("");

        books.add(book);
        //System.out.println(books);
        return book.getId(); //Return: Should be ID
    }

    @Override
    public void updateBookInfo(Session session, int id, String title, String description, Author author) throws RepositoryException {
        String user = (String) Session.getCurrentUser();
        if (null == user) {
            throw new RepositoryException("You must be logged in to do this operation.");
        }

        String statement = "UPDATE book SET title = '" + title + "', description = '"
                + description + "', last_name = '" + author.getLastName() + "', first_name = '"
                + author.getFirstName() + "' WHERE id = '" + id + "';";
        System.out.println("STATEMENT: " + statement);
        repositoryDatabaseConnection.executeUpdate(statement);

        // ID begins at index 1. But books:Arraylist starts at index 0.
        books.get(id - 1).setTitle(title);
        books.get(id - 1).setDescription(description);
        books.get(id - 1).setAuthor(author);
    }
    
    

    public void setBookCoverImage(Session session, File image, String mimeType, int id) throws RepositoryException {
        FileInputStream input = null;
        String updateSQL = "UPDATE book SET image_data = ?, image_mime = ? WHERE id=?";
        String user = (String) Session.getCurrentUser();
        if (null == user) {
            throw new RepositoryException("You must be logged in to do this operation.");
        }
        try {
            input = new FileInputStream(image);
            PreparedStatement pstmt = repositoryDatabaseConnection.getConnectionInstance().prepareStatement(updateSQL);
            pstmt.setBinaryStream(1, input);
            pstmt.setString(2, mimeType);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteBook(Session session, int id) throws RepositoryException {

        String user = (String) Session.getCurrentUser();
        if (null == user) {
            throw new RepositoryException("You must be logged in to do this operation.");
        }

        ResultSet rs = repositoryDatabaseConnection.executeQuery("SELECT * FROM book WHERE id=" + id);
        if (rs == null) {
            throw new RepositoryException("Book not found in the database");
        }

        try {
            repositoryDatabaseConnection.executeUpdate("DELETE FROM book WHERE id=" + id);
        } catch (Exception e) {
        }
    }

    public void deleteAllBooks(Session session) {
        try {
            repositoryDatabaseConnection.executeUpdate("DELETE FROM book");
            Book.resetCount(); //Re-initialise ID assigning counter
        } catch (Exception e) {
        }
    }

    public void resetBooks(Session session) {
        books.removeAll(books);
    }

    public void createBookTable(Session session) {
        try {
            repositoryDatabaseConnection.executeUpdate("CREATE TABLE `book`(\n"
                    + "	`id` INT  NOT NULL AUTO_INCREMENT,\n"
                    + "    `title` VARCHAR(64) DEFAULT NULL,\n"
                    + "    `description` VARCHAR(256) DEFAULT NULL,\n"
                    + "    `isbn` VARCHAR(64) DEFAULT NULL,\n"
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
            Book.resetCount(); //Re-initialise ID assigning counter
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Book getBookInfo(Session session, Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < books.size(); i++) {
            output += "Title: " + books.get(i).getTitle() + ", Description: " + books.get(i).getDescription() + ", ISBN: " + books.get(i).getISBN()
                    + ", Author: " + books.get(i).getAuthor() + ", Publisher Company: " + books.get(i).getPublisherCompany() + ", Publisher Address: "
                    + books.get(i).getPublisherAddress() + "\n";
        }
        return output;
    }
}

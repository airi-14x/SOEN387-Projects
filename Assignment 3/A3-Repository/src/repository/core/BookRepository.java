/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import DAL.BookRepositoryGateway;
import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public class BookRepository implements IBookRepository {

    private ArrayList<Book> books;
    private BookRepositoryGateway respositoryDatabaseGatewayConnection;
    private static BookRepository instance = null;

    private BookRepository() {
        respositoryDatabaseGatewayConnection = BookRepositoryGateway.getInstance();
        System.out.println("Connection " + respositoryDatabaseGatewayConnection);
        books = new ArrayList<Book>();

    }

    public static synchronized BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
            System.out.println("Business Core: BookRepository - Instance is Created!");
        }
        return instance;
    }

    @Override
    public ArrayList<Book> listAllBooks(Session session) throws BookRepositoryException {

        try {
            resetBooks(session);
            ResultSet resultSet = respositoryDatabaseGatewayConnection.listAllBooks();

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
                byte[] imageData = resultSet.getBytes("image_data");

                Book book = new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData));
                book.setId(id); // Set ID of book to match Database ID
                books.add(book);
            }

        } catch (SQLException e) {
        }
        return books; // Updated ArrayList of Book
    }

    @Override
    public Book getBookInfo(Session session, int id) throws BookRepositoryException {
        Book result = null;

        try {
            ResultSet resultSet = respositoryDatabaseGatewayConnection.getBookInfo(id);

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
                byte[] imageData = resultSet.getBytes("image_data");

                result = new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData));
                result.setId(bookId); // Set ID of book to match Database ID
            }

            if (result == null) {
                throw new BookRepositoryException("Book not found in database");
            }

        } catch (SQLException e) {
        }
        return result;
    }

    @Override
    public Book getBookInfo(Session session, String isbn) throws BookRepositoryException {
        Book result = null;
        try {
            ResultSet resultSet = respositoryDatabaseGatewayConnection.getBookInfo(isbn);

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
                byte[] imageData = resultSet.getBytes("image_data");

                result = new Book(title, description, bookIsbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData));
                result.setId(bookId); // Set ID of book to match Database ID

                if (result == null) {
                    throw new BookRepositoryException("Book not found in database");
                }
            }
        } catch (SQLException e) {
        }
        return result;
    }
    
    @Override
    public int addNewBook(Session session, Book book) throws BookRepositoryException {
        String user = (String) Session.getCurrentUser();
        if (null == user) {
            throw new BookRepositoryException("You must be logged in to do this operation.");
        }
        String title = book.getTitle();
        String description = book.getDescription();
        String isbn = book.getISBN();
        String authorFirstName = book.getAuthor().getFirstName();
        String authorLastName = book.getAuthor().getLastName();
        String publisherCompany = book.getPublisherCompany();
        String publisherAddress = book.getPublisherAddress();
        byte[] image = book.getCover().getImageData();
        String mimeType = book.getCover().getMimeType();
        
        respositoryDatabaseGatewayConnection.addNewBook(title, description, isbn, authorFirstName, authorLastName, publisherCompany, publisherAddress, image, mimeType);
      
        book.autoIncrement();
        books.add(book);
        return book.getId();
    }

    @Override
    public void updateBookInfo(Session session, int id, String title, String description, Author author, CoverImage image) throws BookRepositoryException {
        String user = (String) Session.getCurrentUser();
        if (null == user) {
            throw new BookRepositoryException("You must be logged in to do this operation.");
        }
        
        String authorFirstName = author.getFirstName();
        String authorLastName = author.getLastName();
        byte[] imageData = image.getImageData();
        String mimeType = image.getMimeType();
        
        respositoryDatabaseGatewayConnection.updateBookInfo(id, title, description, authorFirstName, authorLastName, imageData, mimeType);
        
        for (Book book : books) {
            if (book.getId() == id) {
                book.setTitle(title);
                book.setDescription(description);
                book.setAuthor(author);
                book.setCover(image);
            }
        }
    }


    @Override
    public void deleteBook(Session session, int id) throws BookRepositoryException {

        String user = (String) Session.getCurrentUser();
        if (null == user) {
            throw new BookRepositoryException("You must be logged in to do this operation.");
        }
        try {
            ResultSet rs = respositoryDatabaseGatewayConnection.getBookInfo(id);
            if (!rs.next()) {
                throw new BookRepositoryException("Book not found in the database");
            }

            respositoryDatabaseGatewayConnection.deleteBook(id);
        } catch (Exception e) {
        }
    }

    public void deleteAllBooks(Session session) {
        try {
            respositoryDatabaseGatewayConnection.deleteAllBooks();
            Book.resetCount(); //Re-initialise ID assigning counter
        } catch (Exception e) {
        }
    }

    public void resetBooks(Session session) {
        books.removeAll(books);
    }

    public void createBookTable(Session session) {
        respositoryDatabaseGatewayConnection.createBookTable();
    }

    public void dropBookTable() {
        respositoryDatabaseGatewayConnection.dropBookTable();
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

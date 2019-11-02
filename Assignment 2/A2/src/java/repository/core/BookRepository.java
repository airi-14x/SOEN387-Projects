/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public class BookRepository implements IBookRepository {

    private ArrayList<Book> books;
    private RepositoryDatabase connection;
    private static BookRepository instance = null;

    private BookRepository() {
        connection = RepositoryDatabase.getInstance();
        books = new ArrayList<Book>();

    }

    public static synchronized BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    @Override
    public ArrayList<Book> listAllBooks() {
        try {
            ResultSet resultSet = connection.executeQuery("SELECT * FROM book");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String isbn = resultSet.getString("isbn");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String publisherCompany = resultSet.getString("publisher_company");
                String address = resultSet.getString("address");
                String mimeType = resultSet.getString("mime_type");
                Blob imageData = resultSet.getBlob("image_data");

                books.add(new Book(id, title, description, isbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book getBookInfo(int id) {
        Book result = new Book();

        try {
            ResultSet resultSet = connection.executeQuery("SELECT * FROM book WHERE id=" + id);

            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String isbn = resultSet.getString("isbn");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String publisherCompany = resultSet.getString("publisher_company");
                String address = resultSet.getString("address");
                String mimeType = resultSet.getString("mime_type");
                Blob imageData = resultSet.getBlob("image_data");

                result = new Book(bookId, title, description, isbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Book getBookInfo(String isbn) {
        Book result = new Book();

        try {
            ResultSet resultSet = connection.executeQuery("SELECT * FROM book WHERE isbn=" + isbn);

            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String bookIsbn = resultSet.getString("isbn");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String publisherCompany = resultSet.getString("publisher_company");
                String address = resultSet.getString("address");
                String mimeType = resultSet.getString("mime_type");
                Blob imageData = resultSet.getBlob("image_data");

                result = new Book(bookId, title, description, bookIsbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addNewBook(Book book) {
        connection.executeUpdate("INSERT INTO book(id, title, description, isbn, last_name, first_name, publisher_company, address, mime_type, image_data) "
                + "VALUES(\"" + book.getId() + "\", \"" + book.getTitle() + "\", \"" + book.getDescription() + "\", \"" + book.getISBN() + "\",\""
                + book.getAuthor().getFirstName() + "\", \"" + book.getAuthor().getLastName() + "\", \"" + book.getPublisherCompany() + "\", \"" + book.getPublisherAddress() + "\", \"mime_type" + "\", \"image_data"
        );
        return book.getId(); //Return: Should be ID
    }

    @Override
    public void updateBookInfo(int id, String title, String description, Author author) {
        // USE ID T
    }

    @Override
    public void setBookCoverImage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBook(int id) {
        try {
            connection.executeUpdate("DELETE FROM book WHERE id=" + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAllBooks() {
        try {
            connection.executeUpdate("DELETE FROM book");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

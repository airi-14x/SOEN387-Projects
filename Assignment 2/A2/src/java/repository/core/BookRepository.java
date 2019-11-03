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
        System.out.println("Connection " + connection);
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
    public ArrayList<Book> listAllBooks() {
        try {
            ResultSet resultSet = connection.executeQuery("SELECT * FROM book");

            resetBooks(); // Reset Arraylist
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String isbn = resultSet.getString("isbn");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                String publisherCompany = resultSet.getString("publisher_company");
                String address = resultSet.getString("address");
                //String mimeType = resultSet.getString("mime_type");
                //Blob imageData = resultSet.getBlob("image_data");
                Book book = new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address);
                book.setId(id); // Set ID of book to match Database ID
                books.add(book);

                //books.add(new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books; // Updated ArrayList of Book
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
                //String mimeType = resultSet.getString("mime_type");
                //Blob imageData = resultSet.getBlob("image_data");

                result = new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address);
                result.setId(bookId); // Set ID of book to match Database ID
                //result = new Book(title, description, isbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData));
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
                //String mimeType = resultSet.getString("mime_type");
                //Blob imageData = resultSet.getBlob("image_data");

                result = new Book(title, description, bookIsbn, new Author(firstName, lastName), publisherCompany, address);
                result.setId(bookId); // Set ID of book to match Database ID
                //result = new Book(title, description, bookIsbn, new Author(firstName, lastName), publisherCompany, address, new CoverImage(mimeType, imageData));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int addNewBook(Book book) {

        connection.executeUpdate("INSERT INTO book(title, description,isbn, first_name, last_name, publisher_company, address) VALUES(\"" + book.getTitle() + "\",\""
                + book.getDescription() + "\",\"" + book.getISBN() + "\", \"" + book.getAuthor().getFirstName() + "\", \""
                + book.getAuthor().getLastName() + "\", \"" + book.getPublisherCompany() + "\", \"" + book.getPublisherAddress() + "\")");

        /*connection.executeUpdate("INSERT INTO book(id, title, description, isbn, last_name, first_name, publisher_company, address, mime_type, image_data) "
                + "VALUES(\"" + book.getId() + "\", \"" + book.getTitle() + "\", \"" + book.getDescription() + "\", \"" + book.getISBN() + "\",\""
                + book.getAuthor().getFirstName() + "\", \"" + book.getAuthor().getLastName() + "\", \"" + book.getPublisherCompany() + "\", \"" + book.getPublisherAddress() + "\", \"mime_type" + "\", \"image_data"
        );*/
        book.autoIncrement();
        books.add(book);
        return book.getId(); //Return: Should be ID
    }

    @Override
    public void updateBookInfo(int id, String title, String description, Author author) {
        String statement = "UPDATE book SET title = '" + title + "', description = '"
                + description + "', last_name = '" + author.getLastName() + "', first_name = '"
                + author.getFirstName() + "' WHERE id = '" + id + "';";
        System.out.println("STATEMENT: " + statement);
        connection.executeUpdate(statement);

        // ID begins at index 1. But books:Arraylist starts at index 0.
        books.get(id - 1).setTitle(title);
        books.get(id - 1).setDescription(description);
        books.get(id - 1).setAuthor(author);
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
            Book.resetCount(); //Re-initialise ID assigning counter
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetBooks() {
        books.removeAll(books);
    }

    public void createBookTable() {
        try {
            connection.executeUpdate("CREATE TABLE `book`(\n"
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
            connection.executeUpdate("DROP TABLE book");
            Book.resetCount(); //Re-initialise ID assigning counter
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

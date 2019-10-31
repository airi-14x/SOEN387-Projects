/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.util.ArrayList;

/**
 *
 * @author Airi
 */
public class BookRepository implements IBookRepository {

    private ArrayList<Book> books; //? Need to go into database to fetch it?
    private Book book;
    private RepositoryDatabase connection;

    private static BookRepository instance = null;

    private BookRepository() {
        connection = RepositoryDatabase.getInstance();
        books = new ArrayList<Book>();
    }

    public static synchronized IBookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    // private IMAGE-type(Choose one??) cover;
    public ArrayList<Book> listAllBooks() {
        return books;
    }

    //** Need Method to return matching book with id**//
    public Book getBookInfoID(int book_id) {
        return book; // WIP - Implement method in book class
    }

    public Book getBookInfoISBN(String book_isbn) {
        return book; // WIP - Implement method to match ISBN with book class
    }

    public int addBook(Book book) {
        // Add book to database?
        // Return ID
        return book.getId();
    }

    public void updateBookInfo(int book_id, String title, String description, Author author) {
        // Method to match book with book parameters(title,description,isbn...)?
        // Need to validate!
        // Probably not an Author object. If Author info, need to format it into object.
        // Not cover though.

    }

    // Reset and Set function. Need to pick out image type!
    public void setBookCoverImage() {

    }

    public void deleteBook(int id) {
        // Match id with book to delete!
    }

    public void deleteAllBooks() {
        // Drop Database
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.util.List;

/**
 *
 * @author Airi
 */
public class Repository implements IBookRepository {

    // NEED TO IMPLEMENT SINGLETON pattern
    // WIP
    private int id; //AUTO-GENERATED??
    private String title;
    private String description;
    private String isbn;

    private String author_first_name;
    private String author_last_name;
    private String publisher_company;
    private String publisher_address;

    private List<Book> books; //? Need to go into database to fetch it?
    private Book book;

    // private IMAGE-type(Choose one??) cover;
    public List<Book> listAllBooks() {
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
        return id;
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

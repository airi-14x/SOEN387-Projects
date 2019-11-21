/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Airi
 */
public class Book {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int id = count.get(); //AUTO-INCREMENT
    private String title;
    private String description;
    private String isbn;
    private Author author;
    private String publisherCompany;
    private String publisherAddress;
    private CoverImage cover;

    public Book() {

    }

    public Book(String title, String description, String isbn, Author author, String publisherCompany, String publisherAddress) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.author = author;
        this.publisherCompany = publisherCompany;
        this.publisherAddress = publisherAddress;
    }

    public Book(String title, String description, String isbn, Author author, String publisherCompany, String publisherAddress, CoverImage cover) {
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.author = author;
        this.publisherCompany = publisherCompany;
        this.publisherAddress = publisherAddress;
        this.cover = cover;
    }

    // Called from: addNewBook()
    public void autoIncrement() {
        id = count.incrementAndGet();
    }

    // Called from: deleteAllBooks()
    public static void resetCount() {
        count.set(0);
    }

    // Used to match bookID with databaseID --> Called when a new Book is created.
    public void setId(int databaseID) {
        id = databaseID;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPublisherCompany() {
        return publisherCompany;
    }

    public void setPublisherCompany(String publisherCompany) {
        this.publisherCompany = publisherCompany;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    public CoverImage getCover() {
        return cover;
    }

    public void setCover(CoverImage cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", description=" + description + ", isbn=" + isbn + ", author=" + author + ", publisherCompany=" + publisherCompany + ", publisherAddress=" + publisherAddress + ", cover=" + cover + '}';
    }

}

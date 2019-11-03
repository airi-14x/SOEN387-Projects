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

    private static final AtomicInteger count = new AtomicInteger(0); // Need to Initialise with #books in DB
    private int id = count.get();
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
        //this.id = id;
        //System.out.println("Count:");
        //System.out.println(count);
        //id = count.incrementAndGet(); // AUTO-INCREMENT
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.author = author;
        this.publisherCompany = publisherCompany;
        this.publisherAddress = publisherAddress;
    }

    public Book(String title, String description, String isbn, Author author, String publisherCompany, String publisherAddress, CoverImage cover) {
        //this.id = id;
        //id = count.incrementAndGet(); // AUTO-INCREMENT
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.author = author;
        this.publisherCompany = publisherCompany;
        this.publisherAddress = publisherAddress;
        this.cover = cover;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void autoIncrement() {
        id = count.incrementAndGet();
    }

    public void setCount(int databaseID) {
        if (databaseID > count.get()) {
            count.set(databaseID);
        }
    }

    public void resetCount() {
        count.set(0);
    }

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
        return "Book{" + "id=" + id + ", isbn=" + isbn + ", description=" + description + ", author=" + author + ", publisher_company=" + publisherCompany + ", publisher_address=" + publisherAddress + ", cover=" + cover + '}' + "\n";
    }

}

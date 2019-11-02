/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

/**
 *
 * @author Airi
 */
public class Book {

    private int id;
    private String title;
    private String isbn;
    private String description;
    private Author author;
    private String publisherCompany;
    private String publisherAddress;
    private CoverImage cover;

    public Book() {
    }

    public Book(int id, String title, String isbn, String description, Author author, String publisherCompany, String publisherAddress, CoverImage cover) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.description = description;
        this.author = author;
        this.publisherCompany = publisherCompany;
        this.publisherAddress = publisherAddress;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Book{" + "id=" + id + ", isbn=" + isbn + ", description=" + description + ", author=" + author + ", publisher_company=" + publisherCompany + ", publisher_address=" + publisherAddress + ", cover=" + cover + '}';
    }

}

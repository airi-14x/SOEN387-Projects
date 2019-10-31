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
    private String isbn;
    private String description;
    private Author author;
    private String publisher_company;
    private String publisher_address;
    private CoverImage cover;

    public Book() {
    }

    public Book(int id, String isbn, String description, Author author, String publisher_company, String publisher_address, CoverImage cover) {
        this.id = id;
        this.isbn = isbn;
        this.description = description;
        this.author = author;
        this.publisher_company = publisher_company;
        this.publisher_address = publisher_address;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPublisher_company() {
        return publisher_company;
    }

    public void setPublisher_company(String publisher_company) {
        this.publisher_company = publisher_company;
    }

    public String getPublisher_address() {
        return publisher_address;
    }

    public void setPublisher_address(String publisher_address) {
        this.publisher_address = publisher_address;
    }

    public CoverImage getCover() {
        return cover;
    }

    public void setCover(CoverImage cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", isbn=" + isbn + ", description=" + description + ", author=" + author + ", publisher_company=" + publisher_company + ", publisher_address=" + publisher_address + ", cover=" + cover + '}';
    }

}

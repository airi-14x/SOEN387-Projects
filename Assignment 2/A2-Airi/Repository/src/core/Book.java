/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

/**
 *
 * @author Airi
 */
public class Book {

    private int id;
    private String isbn;
    private String description;
    private Author author;
    private CoverImage cover;

    public Book() {
    }

    public Book(int id, String isbn, String description, Author author, CoverImage cover) {
        this.id = id;
        this.isbn = isbn;
        this.description = description;
        this.author = author;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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

    public CoverImage getCover() {
        return cover;
    }

    public void setCover(CoverImage cover) {
        this.cover = cover;
    }

    //** Need Method to return matching book with id**//
}

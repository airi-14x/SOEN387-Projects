/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

/**
 *
 * @author jasminelatendresse
 */
public class Book {
    private int id;
    private String title;
    private String description; 
    private String isbn; 
    private Author author;
    //private Publisher publishier;
    private CoverImage coverImage; 
    
    public Book(int id, String title, String description, String isbn, Author author, CoverImage coverImage ) {
        this.id = id; 
        this.title = title;
        this.description = description;
        this.isbn = isbn;
        this.author = author;
        this.coverImage = coverImage;
    }
    
    //Getters
    public int getId(){
        return this.id;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public String getISBN(){
        return this.isbn;
    }
    
    public Author getAuthor(){
        return this.author;
    }
    
    //Setters
    public void setId(int id){
        this.id = id;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setISBN(String isbn){
        this.isbn = isbn;
    }
    
    public void setAuthor(Author author){
        this.author = author; 
    }
    
    @Override
    public String toString(){
        return "Book{" + "id=" + id +", title=" + title + ", description=" + description + ", isbn=" + isbn + ", author=" + author.toString() + "}";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.util.ArrayList;

/**
 *
 * @author jasminelatendresse
 */
public interface IBookRepository {
    ArrayList<Book> listAllBooks();
    Book getBookInfo(int id);
    Book getBookInfo(String isbn);
    int addNewBook(Book book);
    void updateBookInfo(int id, String title, String description, Author author);
    void setBookCoverImage();
    void deleteBook(int id);
    
}

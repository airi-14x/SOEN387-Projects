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
public interface IBookRepository {

    List<Book> listAllBooks();

    Book getBookInfoID(int book_id);

    Book getBookInfoISBN(String book_isbn);

    int addBook(Book book);

    void updateBookInfo(int book_id, String title, String description, Author author);

    void setBookCoverImage(); // Reset and Set function. Need to pick out image type!

    void deleteBook(int id);

    void deleteAllBooks();

}

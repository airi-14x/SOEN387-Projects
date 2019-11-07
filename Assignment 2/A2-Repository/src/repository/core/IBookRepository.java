package repository.core;

import java.util.ArrayList;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public interface IBookRepository {

    ArrayList<Book> listAllBooks(Session session);

    Book getBookInfo(Session session, int id);

    Book getBookInfo(Session session, String isbn);

    int addNewBook(Session session, Book book);

    void updateBookInfo(Session session, int id, String title, String description, Author author);

    void setBookCoverImage(Session session); // Reset and Set function. Need to pick out image type!

    void deleteBook(Session session, int id);

}

package repository.core;

import java.util.ArrayList;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public interface IBookRepository {

    ArrayList<Book> listAllBooks();

    Book getBookInfo(int id);

    Book getBookInfo(String isbn);

    int addNewBook(Book book);

    void updateBookInfo(int id, String title, String description, Author author);

    void setBookCoverImage(); // Reset and Set function. Need to pick out image type!

    void deleteBook(int id);

}

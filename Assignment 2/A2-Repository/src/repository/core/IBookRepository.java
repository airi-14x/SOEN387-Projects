package repository.core;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public interface IBookRepository {

    ArrayList<Book> listAllBooks(Session session);

    Book getBookInfo(Session session, int id);

    Book getBookInfo(Session session, String isbn);

    int addNewBook(Session session, Book book) throws RepositoryException;

    void updateBookInfo(Session session, int id, String title, String description, Author author) throws RepositoryException;

    //void setBookCoverImage(Session session) throws RepositoryException; // Reset and Set function. Need to pick out image type!
    void setBookCoverImage(Session session, File image, String mimeType, int id) throws RepositoryException; // Reset and Set function. Need to pick out image type!

    void deleteBook(Session session, int id) throws RepositoryException;

}

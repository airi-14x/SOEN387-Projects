package repository.core;

import java.io.File;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public interface IBookRepository {

    ArrayList<Book> listAllBooks(Session session) throws BookRepositoryException;

    Book getBookInfo(Session session, int id) throws BookRepositoryException;

    Book getBookInfo(Session session, String isbn) throws BookRepositoryException;

    int addNewBook(Session session, Book book) throws BookRepositoryException;

    void updateBookInfo(Session session, int id, String title, String description, Author author) throws BookRepositoryException;

    //void setBookCoverImage(Session session) throws BookRepositoryException; // Reset and Set function. Need to pick out image type!
    void setBookCoverImage(Session session, File image, String mimeType, int id) throws BookRepositoryException; // Reset and Set function. Need to pick out image type!

    void deleteBook(Session session, int id) throws BookRepositoryException;

}

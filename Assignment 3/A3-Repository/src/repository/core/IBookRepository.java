package repository.core;

import java.io.File;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

/**
 *
 * @author Jasmine Latendresse and Airi Chow
 */
public interface IBookRepository {

    ArrayList<Book> listAllBooks(Session session) throws BookRepositoryGatewayException;

    Book getBookInfo(Session session, int id) throws BookRepositoryGatewayException;

    Book getBookInfo(Session session, String isbn) throws BookRepositoryGatewayException;

    int addNewBook(Session session, Book book) throws BookRepositoryGatewayException;

    void updateBookInfo(Session session, int id, String title, String description, Author author) throws BookRepositoryGatewayException;

    //void setBookCoverImage(Session session) throws BookRepositoryGatewayException; // Reset and Set function. Need to pick out image type!
    void setBookCoverImage(Session session, File image, String mimeType, int id) throws BookRepositoryGatewayException; // Reset and Set function. Need to pick out image type!

    void deleteBook(Session session, int id) throws BookRepositoryGatewayException;

}

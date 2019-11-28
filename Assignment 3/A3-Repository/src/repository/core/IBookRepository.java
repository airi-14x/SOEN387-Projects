package repository.core;

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

    void updateBookInfo(Session session, int id, String title, String description, Author author, CoverImage image) throws BookRepositoryException;

    void deleteBook(Session session, int id) throws BookRepositoryException;

}

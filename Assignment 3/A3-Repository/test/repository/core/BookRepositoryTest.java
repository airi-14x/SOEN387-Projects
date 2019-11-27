/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import DAL.BookRepositoryDatabase;
import java.io.File;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author jasminelatendresse
 */
public class BookRepositoryTest {

    static BookRepository bookRepository = null;
    static ArrayList<Book> allBooksInDB;
    static Book book1;
    static Book book2;
    static Session session;
    static JSONObject user;
    static BookRepositoryDatabase repoDb = null;
    public BookRepositoryTest() {
    }

    @BeforeClass
    public static void setUp() throws BookRepositoryException {
        bookRepository = BookRepository.getInstance();
        repoDb = BookRepositoryDatabase.getInstance();
       

        session = new Session();
        user = new JSONObject();
        user.put("uId", "Jasmine");
        user.put("password", "test123");
        session.setCurrentUser(user);

        allBooksInDB = bookRepository.listAllBooks(session);
        book1 = allBooksInDB.get(0);
        book2 = allBooksInDB.get(1);


        System.out.println(allBooksInDB.toString());
        System.out.println(book1.toString());
        System.out.println(book2.toString());

    }

    @AfterClass
    public static void tearDown() throws BookRepositoryException {
        bookRepository = null;
        repoDb = null;
        book1 = null;
        book2 = null;
        session = null;
        user = null;
    }

    @Test
    public void listAllBooksTest() throws BookRepositoryException {
        System.out.println("Testing listAllBooks");

        ArrayList<Book> books = bookRepository.listAllBooks(session);

        assertNotNull(books);
    }

    @Test
    public void getBookInfoTest() throws BookRepositoryException {
        System.out.println("Testing getBookInfo by book id");

        Book resultBook = bookRepository.getBookInfo(session, book1.getId());

        assertNotNull(resultBook);

        assertEquals(resultBook.getDescription(), book1.getDescription());
        assertEquals(resultBook.getTitle(), book1.getTitle());
        assertEquals(resultBook.getISBN(), book1.getISBN());
        assertEquals(resultBook.getAuthor().toString(), book1.getAuthor().toString());
        assertEquals(resultBook.getPublisherCompany(), book1.getPublisherCompany());
        assertEquals(resultBook.getPublisherAddress(), book1.getPublisherAddress());
        assertNotNull(resultBook.getCover());
        assertNotNull(resultBook.getCover().getMimeType());
        assertNotNull(resultBook.getCover().getImageData());

        System.out.println("Testing getBookInfo by book isbn");

        Book resultBook2 = bookRepository.getBookInfo(session, book1.getISBN());

        assertNotNull(resultBook2);

        assertEquals(resultBook2.getDescription(), book1.getDescription());
        assertEquals(resultBook2.getTitle(), book1.getTitle());
        assertEquals(resultBook2.getISBN(), book1.getISBN());
        assertEquals(resultBook2.getAuthor().toString(), book1.getAuthor().toString());
        assertEquals(resultBook2.getPublisherCompany(), book1.getPublisherCompany());
        assertEquals(resultBook2.getPublisherAddress(), book1.getPublisherAddress());
        assertNotNull(resultBook2.getCover());
        assertNotNull(resultBook2.getCover().getMimeType());
        assertNotNull(resultBook2.getCover().getImageData());

    }

    @Test
    public void addNewBookTest() throws BookRepositoryException {
        System.out.println("Testing addNewBook");
        Book book = new Book("Title", "Description", "ISBN", new Author("First Name", "Last Name"), "Publisher Company", "Publisher Address", new CoverImage("image/png", "some_image".getBytes()));
        bookRepository.addNewBook(session, book);

        assertFalse(book.getId() == 0);
        assertNotNull(bookRepository);
    }

    @Test
    public void updateBookInfoTest() throws BookRepositoryException {
        System.out.println("Testing updateBookInfo");

        Author author = new Author("New", "Name");
        CoverImage cover = new CoverImage("image/png", "some_image".getBytes());
        bookRepository.updateBookInfo(session, book2.getId(), "New Title", "New Description", author, cover);
        System.out.println(book1.getTitle());
        System.out.println(book2.getTitle());

        assertEquals("New Title", book2.getTitle());
        assertEquals("New Description", book2.getDescription());
        assertEquals(author.toString(), book2.getAuthor().toString());
    }

    
//    @Test
    public void deleteBookTest() throws BookRepositoryException {
        System.out.println("Testing deleteBook");

        bookRepository.deleteBook(session, book1.getId());

        assertNull(book1);

    }

    @Test
    public void deleteAllBooksTest() throws BookRepositoryException {
        System.out.println("Testing deleteAllBooks");

        int initialSize = allBooksInDB.size();

        bookRepository.deleteAllBooks(session);

        ArrayList<Book> result = bookRepository.listAllBooks(session);

        int sizeAfterDelete = result.size();

        assertFalse(initialSize == sizeAfterDelete);
        assertEquals(0, sizeAfterDelete);
    }

}

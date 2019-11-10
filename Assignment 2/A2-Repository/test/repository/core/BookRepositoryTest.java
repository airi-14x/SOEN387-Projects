/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
    static RepositoryDatabase repoDb = null;

    public BookRepositoryTest() {
    }

    @BeforeClass
    public static void setUp() throws RepositoryException {
        bookRepository = BookRepository.getInstance();
        repoDb = RepositoryDatabase.getInstance();

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
    public static void tearDown() throws RepositoryException {
        bookRepository = null;
        repoDb = null;
        book1 = null;
        book2 = null;
        session = null;
        user = null;
    }

    @Test
    public void listAllBooksTest() throws RepositoryException {
        System.out.println("Testing listAllBooks");

        ArrayList<Book> books = bookRepository.listAllBooks(session);

        assertNotNull(books);
    }

    @Test
    public void getBookInfoTest() {
        System.out.println("Testing getBookInfo by book id");

        Book resultBook = bookRepository.getBookInfo(session, book1.getId());

        assertNotNull(resultBook);

        assertEquals(resultBook.getDescription(), book1.getDescription());
        assertEquals(resultBook.getTitle(), book1.getTitle());
        assertEquals(resultBook.getISBN(), book1.getISBN());
        assertEquals(resultBook.getAuthor().toString(), book1.getAuthor().toString());
        assertEquals(resultBook.getPublisherCompany(), book1.getPublisherCompany());
        assertEquals(resultBook.getPublisherAddress(), book1.getPublisherAddress());

        System.out.println("Testing getBookInfo by book isbn");

        Book resultBook2 = bookRepository.getBookInfo(session, book1.getISBN());

        assertNotNull(resultBook2);

        assertEquals(resultBook2.getDescription(), book1.getDescription());
        assertEquals(resultBook2.getTitle(), book1.getTitle());
        assertEquals(resultBook2.getISBN(), book1.getISBN());
        assertEquals(resultBook2.getAuthor().toString(), book1.getAuthor().toString());
        assertEquals(resultBook2.getPublisherCompany(), book1.getPublisherCompany());
        assertEquals(resultBook2.getPublisherAddress(), book1.getPublisherAddress());

    }


    @Test
    public void addNewBookTest() throws RepositoryException {
        System.out.println("Testing addNewBook");
        Book book = new Book("Title", "Description", "ISBN", new Author("First Name", "Last Name"), "Publisher Company", "Publisher Address");
        bookRepository.addNewBook(session, book);

        assertFalse(book.getId() == 0);
        assertNotNull(bookRepository);
    }

    @Test
    public void updateBookInfoTest() throws RepositoryException {
        System.out.println("Testing updateBookInfo");
        
        Author author = new Author("New", "Name");
        
        bookRepository.updateBookInfo(session, book2.getId(), "New Title", "New Description", author);
        System.out.println(book1.getTitle());
        System.out.println(book2.getTitle());
        
        assertEquals("New Title", book2.getTitle());
        assertEquals("New Description", book2.getDescription());
        assertEquals(author.toString(), book2.getAuthor().toString());
    }
    
    @Test
    public void deleteBookTest() throws RepositoryException {
        System.out.println("Testing deleteBook");

        bookRepository.deleteBook(session, book1.getId());

        assertNull(bookRepository.getBookInfo(session, book1.getId()).getTitle());
        assertNull(bookRepository.getBookInfo(session, book1.getId()).getDescription());
        assertNull(bookRepository.getBookInfo(session, book1.getId()).getISBN());
        assertNull(bookRepository.getBookInfo(session, book1.getId()).getAuthor());
        assertNull(bookRepository.getBookInfo(session, book1.getId()).getPublisherCompany());
        assertNull(bookRepository.getBookInfo(session, book1.getId()).getPublisherAddress());

    }

    @Test
    public void deleteAllBooksTest() {
        System.out.println("Testing deleteAllBooks");

        int initialSize = allBooksInDB.size();

        bookRepository.deleteAllBooks(session);

        ArrayList<Book> result = bookRepository.listAllBooks(session);

        int sizeAfterDelete = result.size();

        assertFalse(initialSize == sizeAfterDelete);
        assertEquals(0, sizeAfterDelete);
    }
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.core;

import com.mysql.cj.jdbc.Blob;
import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jasminelatendresse
 */
public class BookRepositoryTest {
    BookRepository bookRepository = null;
    Book book1;
    Book book2; 
    Author author; 
    Session session;
    JSONObject user;
    
    public BookRepositoryTest() {
    }
    
    @Before
    public void setUp() throws RepositoryException {
        bookRepository = BookRepository.getInstance();
        
        author = new Author("First", "Last");
        
        book1 = new Book("Title1", "Description1", "ISBN1", author, "Publisher Company1", "Publisher Address1");
        book2 = new Book("Title2", "Description2", "ISBN2", author, "Publisher Company2", "Publisher Address2");
        
        session = new Session();
        user = new JSONObject();
        user.put("uId", "Jasmine");
        user.put("password", "test123");
        session.setCurrentUser(user);
        
        bookRepository.addNewBook(session, book1);
        bookRepository.addNewBook(session, book2);
        
    }
    
    @After
    public void tearDown() throws RepositoryException {
        bookRepository.resetBooks(session);
        bookRepository.deleteAllBooks(session);
        bookRepository = null;
        author = null;
        book1 = null;
        book2 = null;
        session = null;
        user = null;  
    }
    
    //@Test
    public void listAllBooksTest() throws RepositoryException{
        ArrayList<Book> books = bookRepository.listAllBooks(session);

        assertNotNull(books); 
    }
    
    @Test
    public void getBookInfoTest() {
        Book resultBook = bookRepository.getBookInfo(session, book1.getId());
        
        assertNotNull(resultBook);
       
        assertEquals(resultBook.getDescription(), book1.getDescription());
        assertEquals(resultBook.getTitle(), book1.getTitle());
        assertEquals(resultBook.getISBN(), book1.getISBN());
        assertEquals(resultBook.getAuthor().toString(), book1.getAuthor().toString());
        assertEquals(resultBook.getPublisherCompany(), book1.getPublisherCompany());
        assertEquals(resultBook.getPublisherAddress(), book1.getPublisherAddress());
        
    }

    
}

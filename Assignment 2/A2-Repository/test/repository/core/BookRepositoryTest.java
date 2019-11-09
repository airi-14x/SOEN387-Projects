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
    Session session;
    JSONObject user;
    
    public BookRepositoryTest() {
    }
    
    @Before
    public void setUp() throws RepositoryException {
        bookRepository = BookRepository.getInstance();
        
        session = new Session();
        user = new JSONObject();
        user.put("uId", "Jasmine");
        user.put("password", "test123");
        session.setCurrentUser(user);
        
        ArrayList<Book> allBooksInDB = bookRepository.listAllBooks(session);
        book1 = allBooksInDB.get(0);
        book2 = allBooksInDB.get(2);
        
        
    }
    
    @After
    public void tearDown() throws RepositoryException {
        bookRepository = null;
        author = null;
        book1 = null;
        book2 = null;
        session = null;
        user = null;  
    }
    
    //@Test
    public void listAllBooksTest() throws RepositoryException{
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
    
    
}

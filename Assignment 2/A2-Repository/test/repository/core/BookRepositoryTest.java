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
    Book book; 
    Author author; 
    Session session;
    JSONObject user;
    
    public BookRepositoryTest() {
    }
    
    @Before
    public void setUp() throws RepositoryException {
        bookRepository = BookRepository.getInstance();
        
        author = new Author("First", "Last");
        
        book = new Book("Title", "Description", "ISBN", author, "Publisher Company", "Publisher Address");
        book.setId(1);
        
        session = new Session();
        user = new JSONObject();
        user.put("uId", "Jasmine");
        user.put("password", "test123");
        session.setCurrentUser(user);
        
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void listAllBooksTest() throws RepositoryException{
        bookRepository.addNewBook(session, book);
        ArrayList<Book> books = bookRepository.listAllBooks(session);

        assertNotNull(books); 
    }

    
}

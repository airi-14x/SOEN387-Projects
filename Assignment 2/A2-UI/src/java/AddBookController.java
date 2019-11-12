/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.core.Author;
import repository.core.Book;
import repository.core.BookRepository;
import repository.core.RepositoryException;
import repository.core.Session;

/**
 *
 * @author jasminelatendresse
 */
@WebServlet("/AddBookController")
public class AddBookController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>AddBookController Controller</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Login Controller at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        doPost(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String title = request.getParameter("title");
        String isbn = request.getParameter("isbn");
        String description = request.getParameter("description");
        String fName = request.getParameter("fname");
        String lName = request.getParameter("lName");
        String publisherName = request.getParameter("pName");
        String publisherAddress = request.getParameter("paddress");

        BookRepository bookRepo = BookRepository.getInstance();
        //ArrayList<Book> books = bookRepo.listAllBooks(new Session());

        Author author = new Author(fName, lName);
        Book book = new Book(title, description, isbn, author, publisherName, publisherAddress);
        //Book book = new Book(title, isbn, description, author, publisherName, publisherAddress, new CoverImage());
        try {
            bookRepo.addNewBook(new Session(), book);
        } catch (RepositoryException ex) {
            Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //request.setAttribute("books", books);
        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

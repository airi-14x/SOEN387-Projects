/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import repository.core.Author;
import repository.core.Book;
import repository.core.BookRepository;
import repository.core.BookRepositoryException;
import repository.core.CoverImage;
import repository.core.Session;

/**
 *
 * @author jasminelatendresse
 */
@WebServlet("/AddBookController")
@MultipartConfig
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
        String lName = request.getParameter("lname");
        String publisherName = request.getParameter("pname");
        String publisherAddress = request.getParameter("paddress");

        BookRepository bookRepo = BookRepository.getInstance();

        InputStream input = null;
        Part filePart = request.getPart("image");
        String fileType = "";
        if (filePart != null) {
            fileType = filePart.getContentType();
            input = filePart.getInputStream();
            System.out.println(fileType);
        }

        Author author = new Author(fName, lName);
        CoverImage cover = new CoverImage(fileType, input);

        Book book = new Book(title, description, isbn, author, publisherName, publisherAddress);
        book.setCover(cover);
        try {
            bookRepo.addNewBook2(new Session(), book);
        } catch (BookRepositoryException ex) {
            Logger.getLogger(AddBookController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Book ISBN must be unique");
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            response.sendRedirect("error.jsp");

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

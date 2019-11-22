

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mysql.cj.jdbc.Blob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.core.Book;
import repository.core.BookRepository;
import repository.core.RepositoryException;
import repository.core.Session;

/**
 *
 * @author Airi
 */
@WebServlet("/BookViewController")
public class BookViewController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DisplayServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DisplayServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        doPost(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookRepository bookRepo = BookRepository.getInstance();
        Book resultBook = null;
        request.setAttribute("error", " ");

        if (!request.getParameter("viewBookID").equals("")) {
            String bookID = (String) request.getParameter("viewBookID");
            try {
                resultBook = bookRepo.getBookInfo(new Session(), Integer.parseInt(bookID));
            } catch (RepositoryException e) {
                Logger.getLogger(BookViewController.class.getName()).log(Level.SEVERE, null, e);
                request.setAttribute("error", "No book found in the dabatase with id = " + request.getParameter("viewBookID"));
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Book ID must be an integer.");
            } catch (NullPointerException e) {
                 request.setAttribute("error", "No book found in the dabatase with id = " + request.getParameter("viewBookID"));
            }
            
            int resultBookID = 0;
            try {
               resultBookID =  resultBook.getId();
            } catch (NullPointerException e) {
                request.setAttribute("error", "No book found in the dabatase with id = " + request.getParameter("viewBookID"));
            }
            if (resultBookID != 0) {
                request.setAttribute("book", resultBook);
            }
            else {
                request.setAttribute("error", "No book found in the dabatase with id = " + request.getParameter("viewBookID"));
            }

        } else if (!request.getParameter("ISBN").equals("")) {
            try {
                resultBook = bookRepo.getBookInfo(new Session(), request.getParameter("ISBN"));
            } catch (RepositoryException | NullPointerException e) {
                Logger.getLogger(BookViewController.class.getName()).log(Level.SEVERE, null, e);
                request.setAttribute("error", "No book found in the database with ISBN = " + request.getParameter("ISBN"));
            }

            request.setAttribute("book", resultBook);

        } else {
            request.setAttribute("error", "Please enter ID or ISBN!");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/bookView.jsp");
        rd.forward(request, response);
        //getServletContext().getRequestDispatcher("/bookView.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
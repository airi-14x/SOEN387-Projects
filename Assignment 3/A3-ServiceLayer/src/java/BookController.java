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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import repository.core.Book;
import repository.core.BookRepository;
import repository.core.BookRepositoryException;
import repository.core.Session;

/**
 *
 * @author Airi
 */
public class BookController extends HttpServlet {

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
            out.println("<title>Servlet BookServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        String bookID = (String) request.getParameter("viewBookID");

        JSONObject jbook = null;
        JSONObject jerror = new JSONObject();
        if (!request.getParameter("viewBookID").equals("")) {
            try {
                resultBook = bookRepo.getBookInfo(new Session(), Integer.parseInt(bookID));
                jbook = new JSONObject();
                jbook.put("id", resultBook.getId());
                jbook.put("title", resultBook.getTitle());
                jbook.put("description", resultBook.getDescription());
                jbook.put("isbn", resultBook.getISBN());
                jbook.put("author", resultBook.getAuthor());
                jbook.put("publisher-company", resultBook.getPublisherCompany());
                jbook.put("publisher-address", resultBook.getPublisherAddress());

                if (resultBook.getCover().getImageData() == null) {
                    jbook.put("has-image", "no");
                } else {
                    jbook.put("has-image", "yes");
                }

            } catch (BookRepositoryException ex) {
                Logger.getLogger(BookController.class.getName()).log(Level.SEVERE, null, ex);
                String errorMessage = "Sorry there's no book in the database with id = " + bookID;
                jerror.put("error", errorMessage);
                request.setAttribute("error", jerror);
            } catch (NumberFormatException e) {
                String errorMessage = "Book ID must be an integer.";
                jerror.put("error", errorMessage);
                request.setAttribute("error", jerror);
            }
            request.setAttribute("books", jbook);
        } else {
            String errorMessage = "Please enter ID!";
            jerror.put("error", errorMessage);
            request.setAttribute("error", jerror);
        }

        request.setAttribute("results", "Results");
        RequestDispatcher rd = request.getRequestDispatcher("/displayBooks.jsp");
        rd.forward(request, response);

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

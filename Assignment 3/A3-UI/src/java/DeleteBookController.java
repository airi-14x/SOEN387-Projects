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
import repository.core.BookRepository;
import repository.core.BookRepositoryException;
import repository.core.Session;

/**
 *
 * @author Airi
 */
@WebServlet("/DeleteBookController")
public class DeleteBookController extends HttpServlet {

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
            out.println("<title>Servlet DeleteBookController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteBookController at " + request.getContextPath() + "</h1>");
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

        if (request.getParameter("deleteBookID").equals("") && !request.getParameter("delete").equals("deleteAll")) {
            request.setAttribute("errorMessage", "Please enter a book ID in order to delete a book.");
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            response.sendRedirect("error.jsp");

        } else if (request.getParameter("deleteBookID").equals("") && request.getParameter("delete").equals("deleteAll")) {
            bookRepo.deleteAllBooks(new Session());
        } 
        else if (request.getParameter("delete").equals("deleteBook") && !(request.getParameter("deleteBookID").equals(""))) {
            String bookID = (String) request.getParameter("deleteBookID");
            int bookIDtoInt = 0;
            
            try {
                bookIDtoInt = Integer.parseInt(bookID);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "The book ID must be an integer");
                RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
                response.sendRedirect("error.jsp");
            }
            
            try {
                bookRepo.getBookInfo(new Session(), bookIDtoInt);
            } catch (BookRepositoryException e) {
                request.setAttribute("errorMessage", "Book with id " + bookIDtoInt + " not found in the database.");
                RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
                response.sendRedirect("error.jsp");
            }
            
            try {
                bookRepo.deleteBook(new Session(), bookIDtoInt);

            } catch (Exception ex) {
                Logger.getLogger(DeleteBookController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("errorMessage", "Book with id " + bookID + " could not be found in the database.");
                RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
                response.sendRedirect("error.jsp");
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
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

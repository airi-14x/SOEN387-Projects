
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import repository.core.Book;
import repository.core.BookRepository;
import repository.core.BookRepositoryException;
import repository.core.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jasminelatendresse
 */
@WebServlet("/BookViewNoCoverController")
public class BookViewNoCoverController extends HttpServlet{
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
        HttpSession session = request.getSession();
        Session currentSession = (Session) session.getAttribute("currentSession");

        if (currentSession.isUserLoggedIn()) {
            BookRepository bookRepo = BookRepository.getInstance();
            Book resultBook = null;
            request.setAttribute("error", " ");
            String bookID = (String) request.getParameter("viewBookID");
            if (!request.getParameter("viewBookID").equals("")) {
                try {
                    resultBook = bookRepo.getBookInfo(currentSession, Integer.parseInt(bookID));
                } catch (BookRepositoryException ex) {
                    Logger.getLogger(BookViewController.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("error", "Sorry there's no book in the database with id = " + bookID);
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Book ID must be an integer.");
                }

                request.setAttribute("book", resultBook);

            } 
            RequestDispatcher rd = request.getRequestDispatcher("/bookViewNoCover.jsp");
            rd.forward(request, response);
            //getServletContext().getRequestDispatcher("/bookView.jsp").forward(request, response);
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", "You need to be logged in to do this operation.");
            rd.forward(request, response);
        }
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

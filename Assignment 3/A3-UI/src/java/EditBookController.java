/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import repository.core.Author;
import repository.core.BookRepository;
import repository.core.BookRepositoryException;
import repository.core.CoverImage;
import repository.core.Session;

/**
 *
 * @author Airi
 */
@WebServlet("EditController")
@MultipartConfig
public class EditBookController extends HttpServlet {

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
        processRequest(request, response);
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
        HttpSession session = request.getSession();
        Session currentSession = (Session) session.getAttribute("currentSession");

        if (currentSession.isUserLoggedIn()) {

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String fName = request.getParameter("fname");
            String lName = request.getParameter("lname");
            String[] params = {title, description, fName, lName};

            InputStream input = null;
            Part filePart = request.getPart("image");
            String fileType = "";
            if (filePart != null) {
                fileType = filePart.getContentType();
                input = filePart.getInputStream();
                System.out.println(fileType);
            }

            Author author = new Author(fName, lName);
            CoverImage cover = null;
            BookRepository bookRepo = BookRepository.getInstance();

            try {
                cover = new CoverImage(fileType, input);
                bookRepo.updateBookInfo(currentSession, Integer.parseInt(request.getParameter("id")), title, description, author, cover);
            } catch (BookRepositoryException ex) {
                Logger.getLogger(EditBookController.class.getName()).log(Level.SEVERE, null, ex);
                request.setAttribute("errorMessage", "An error occurred");
                RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
                response.sendRedirect("error.jsp");
            } catch (Exception e) {
                request.setAttribute("errorMessage", "An error occurred");
                RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
                response.sendRedirect("error.jsp");
            }
            //Form validation
            for (String param : params) {
                if (param.equals("") || cover == null) {
                    request.setAttribute("errorMessage", "Empty fields");
                    RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                    rd.forward(request, response);
                    response.sendRedirect("error.jsp");
                    break;
                }
            }

            RequestDispatcher rd = request.getRequestDispatcher("BookViewController?viewBookID=" + request.getParameter("id"));
            rd.forward(request, response);

        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            request.setAttribute("errorMessage", "You need to be logged in to do this operation.");
            rd.forward(request, response);
        }

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

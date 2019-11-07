/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import repository.core.Session;

/**
 *
 * @author jasminelatendresse
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login Controller</title>");
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
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        Session session = new Session();
        Boolean login = session.login(userName, password);

        if (login) {
            request.setAttribute("username", userName);
            RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
            rd.forward(request, response);
            response.sendRedirect("home.jsp");
            
        } else {
            request.setAttribute("errorMessage", "Login failed");
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            response.sendRedirect("error.jsp");
<<<<<<< HEAD
        }
=======
>>>>>>> 6d33a6e3b6a421d62950c78678002e85c386917f

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

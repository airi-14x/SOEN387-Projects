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
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();

        if (session.getAttribute("currentSession") == null) {
            Session currentSession = new Session();
            session.setAttribute("currentSession", currentSession);
            RequestDispatcher disp = request.getRequestDispatcher("/login.jsp");
            disp.forward(request, response);

        } else {
            if (request.getMethod().equals("GET")) {
                Session currentSession = (Session) session.getAttribute("currentSession");
                if (currentSession.isUserLoggedIn()) {
                    response.sendRedirect("home");
                } else {
                    session.setAttribute("currentSession", null);
                    response.sendRedirect("LoginController");
                }
            }
            if (request.getMethod().equals("POST")) {
                String userName = request.getParameter("username");
                String password = request.getParameter("password");

                Session currentSession = (Session) session.getAttribute("currentSession");
                if (currentSession.isUserLoggedIn()) {
                    RequestDispatcher disp = request.getRequestDispatcher("/home.jsp");
                    disp.forward(request, response);
                } else {
                    Boolean isSuccess = currentSession.login(userName, password);

                    if (isSuccess) {
                        request.setAttribute("username", userName);
                        RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
                        rd.forward(request, response);
                        response.sendRedirect("home.jsp");
                    } else {
                        request.setAttribute("errorMessage", "Login failed");
                        RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
                        rd.forward(request, response);
                        response.sendRedirect("error.jsp");
                    }
                }

            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository.ui;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.parser.ParseException;
import repository.core.LoginBean;
import repository.core.Session;

/**
 *
 * @author jasminelatendresse
 */
@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class loginController extends HttpServlet {

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
        
        LoginBean loginBean = new LoginBean();
        
        loginBean.setUserName(userName);
        loginBean.setPassword(password);
        
        PrintWriter out = response.getWriter();
    
        try {
            boolean login = Session.login(userName, password);
            
            if(login) {
                request.setAttribute("username", userName);
                request.getRequestDispatcher("/login.jsp");  
                out.print("Success");
            }
            
            else {
                request.setAttribute("errMessage", login);
                request.getRequestDispatcher("/error.jsp");
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            doPost(request, response);
    }
}

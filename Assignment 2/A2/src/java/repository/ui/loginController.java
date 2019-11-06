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
public class loginController extends HttpServlet {
    public loginController() {
        
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
    
        try {
            boolean login = Session.login(userName, password);
            
            if(login) {
                request.setAttribute("username", userName);
                request.getRequestDispatcher("/home.jsp");  
            }
            
            else {
                request.setAttribute("errMessage", login);
                request.getRequestDispatcher("/error.jsp");
            }
            
        } catch (ParseException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

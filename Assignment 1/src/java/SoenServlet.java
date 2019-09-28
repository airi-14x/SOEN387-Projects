/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *
 * @author jasminelatendresse
 */
public class SoenServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String title = "Soen 387 Assignment 1";
        String requestMethod = request.getMethod();
        String queryString = "";
        String[] arrayOfQueryString;
        
        try{
            arrayOfQueryString = URLDecoder.decode(request.getQueryString(), "UTF-8").split("&");
            for(String a : arrayOfQueryString){
                queryString += a;
            }
        }
        catch(NullPointerException e){
            queryString = "No query string found";
        }
        String docType =
            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
        
        out.println(docType +
            "<html>\n" +
            "<head><title>" + title + "</title></head>\n"+
            "<body bgcolor = \"#f0f0f0\">\n" +
            "<h1 align = \"center\">" + title + "</h1>\n" +
            "<h3> Request Method: " +  requestMethod + "</h3>\n" +
            "<h3> Query String: " +  queryString + "</h3>\n" +
            "<table width = \"100%\" border = \"1\" align = \"center\">\n" +
            "<tr bgcolor = \"#949494\">\n" +
            "<th>Header Name</th><th>Header Value(s)</th>\n"+
            "</tr>\n"
        );
       
        Enumeration headerNames = request.getHeaderNames();
    
        while(headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            out.print("<tr><td>" + paramName + "</td>\n");
            String paramValue = request.getHeader(paramName);
            out.println("<td> " + paramValue + "</td></tr>\n");
        }
        
        Enumeration queryParams = request.getParameterNames();
        
        out.println("<tr bgcolor = \"#949494\"><th>Query String</th><th bgcolor = \"#949494\">Parameter Values</th></tr>\n");
        
        while(queryParams.hasMoreElements()) {
            String paramName = (String) queryParams.nextElement();
            out.print("<tr><td>" + paramName + "</td> \n");
            String paramValue = request.getParameter(paramName);
            out.println("<td>" + paramValue + "</td></tr>\n");
        }
        out.println("</table>\n</body></html>");
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

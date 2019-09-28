/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Airi
 */
public class HelloWorldServlet extends HttpServlet {

    String current_format = "";

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
            //localhost:8080/A1-Airi/HelloWorldServlet?format=text&param1=val1&param2=val2
            out.println("<!DOCTYPE xhtml>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelloWorldServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HelloWorldServlet at " + request.getContextPath() + "</h1>");
            out.println("<h4>Request Method: " + request.getMethod() + "</h4>");
            out.println("<h4>Request Headers: </h4>");
            out.println("<h4>Host: " + request.getHeader("host") + "</h4>");
            out.println("<h4>Connection: " + request.getHeader("connection") + "</h4>");
            out.println("<h4>User-Agent: " + request.getHeader("user-agent") + "</h4>");
            out.println("<h4>Accept: " + request.getHeader("accept") + "</h4>");
            out.println("<h4>Query String: </h4>");

            if (request.getParameter("format") == null) {
                current_format = "html";
                out.println("<h4>format: " + current_format + "</h4>");
                out.println("");
                out.println("Response:");
                response.setStatus(200);
                out.println(request.getProtocol());
                out.println(response.getStatus() + " OK");

            } else if (!request.getParameter("format").equals("text") && !request.getParameter("format").equals("html")
                    && !request.getParameter("format").equals("xml")) {
                out.println("");
                response.setStatus(400); //Bad Request
                out.println("<h4> Response: " + request.getProtocol() + " " + response.getStatus()
                        + " Bad Request - Syntax error in Request</h4>");
                //out.println("<h4>format: " + "ERROR PAGE NEEDED TO BE DISPLAYED!" + "</h4>");
            } else {
                current_format = request.getParameter("format");
                out.println("<h4>format: " + current_format + "</h4>");
                out.println("");
                out.println("Response:");
                response.setStatus(200);
                out.println(request.getProtocol());
                out.println(response.getStatus() + " OK");
            }
            //out.println(request.getQueryString());
            //Enumeration<String> request2 = request.getParameterNames();

            current_format = "";
            out.println("</body>");
            out.println("</xhtml>");
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

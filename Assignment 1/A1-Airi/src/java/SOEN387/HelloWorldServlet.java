package SOEN387;

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
            //localhost:8080/A1-Airi/HelloWorldServlet?format=text&param1=val1&param2=val2
            //localhost:8080/A1-Airi/HelloWorldServlet?format=text&param1=１３２&param2=val2
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HelloWorldServlet</title>");
            out.println("<style>");
            out.println("table { border-collapse: collapse; }");
            out.println("th, td { border: 1px solid black; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HelloWorldServlet at " + request.getContextPath() + "</h1>");

            if (request.getParameter("format") == null) {
                current_format = "html";
                response.setStatus(200);

            } else if (!request.getParameter("format").equals("text") && !request.getParameter("format").equals("html")
                    && !request.getParameter("format").equals("xml")) {
                response.setStatus(400); //Bad Request
                out.println("<h4> Response: " + request.getProtocol() + " " + response.getStatus()
                        + " Bad Request - Syntax error in Request</h4>");
            } else {
                current_format = request.getParameter("format");
                response.setStatus(200);
            }

            if (response.getStatus() == 200 && current_format.equals("text")) {

                out.println("<h4>Request Method: " + request.getMethod() + "</h4>");
                out.println("<h4>Request Headers: </h4>");
                out.println("<h4> - Host: " + request.getHeader("host") + "</h4>");
                out.println("<h4> - Connection: " + request.getHeader("connection") + "</h4>");
                out.println("<h4> - User-Agent: " + request.getHeader("user-agent") + "</h4>");
                out.println("<h4> - Accept: " + request.getHeader("accept") + "</h4>");
                out.println("<h4> - Query String: </h4>");

                String[] query_strings = URLDecoder.decode(request.getQueryString(), "UTF-8").split("&");

                int index = 0;
                for (String temp_str : query_strings) {
                    temp_str = temp_str.replace("=", ": ");
                    query_strings[index] = temp_str;
                    index++;
                    out.println("<h4>&nbsp &nbsp &nbsp &nbsp" + temp_str + "</h4>");
                }
            }

            //localhost:8080/A1-Airi/HelloWorldServlet?format=html&param1=愛理&param2=val2
            if (response.getStatus() == 200 && current_format.equals("html")) {
                response.setContentType("text/html;charset=UTF-8");
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>&nbspRequest Method:&nbsp&nbsp");
                out.println("</th>");
                out.println("<td>" + "&nbsp" + request.getMethod());
                out.println("&nbsp</td>");
                out.println("</tr>");
                out.println("</table>");

                out.println("&nbsp");

                out.println("<table>");
                out.println("<tr>");
                out.println("<th>&nbspRequest Headers:" + "&nbsp</th>");
                out.println("</tr>");

                out.println("<tr>");
                out.println("<td>&nbspHost</td>");
                out.println("<td>&nbsp" + request.getHeader("host") + "&nbsp</td>");
                out.println("</tr>");

                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>&nbspConnection</td>");
                out.println("<td>&nbsp" + request.getHeader("connection") + "&nbsp</td>");
                out.println("</tr>");

                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>&nbspUser-Agent</td>");
                out.println("<td>&nbsp" + request.getHeader("user-agent") + "&nbsp</td>");
                out.println("</tr>");

                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>&nbspAccept</td>");
                out.println("<td>&nbsp" + request.getHeader("accept") + "&nbsp</td>");
                out.println("</tr>");

                out.println("</table>");

                out.println("&nbsp");

                out.println("<table>");
                out.println("<tr>");
                out.println("<th>&nbspQuery String:" + "&nbsp</th>");
                out.println("</tr>");

                String[] query_strings = URLDecoder.decode(request.getQueryString(), "UTF-8").split("&");
                for (String temp_str : query_strings) {
                    String[] temp_query_string_pair = new String[2]; //Storing 1 key-value
                    temp_str = temp_str.replace("=", " ");
                    temp_query_string_pair = temp_str.split(" ");
                    if (temp_query_string_pair.length == 2) {   // Ignore malformed string
                        out.println("<tr>");
                        out.println("<td>&nbsp" + temp_query_string_pair[0] + "&nbsp</td>");
                        out.println("<td>&nbsp" + temp_query_string_pair[1] + "&nbsp</td>");
                        out.println("</tr>");
                    }
                }

                out.println("</table>");
            }

            //localhost:8080/A1-Airi/HelloWorldServlet?format=xml&param1=１３２&param2=val2
            if (response.getStatus() == 200 && current_format.equals("xml")) {
                response.setContentType("text/xml;charset=UTF-8");
                out.println("<response>");
                out.println("<request-method>" + request.getMethod() + "</request-method>");
                out.println("<request-headers>");
                out.println("<header name =\"Host\">" + request.getHeader("host") + "</header>");
                out.println("<header name =\"Connection\">" + request.getHeader("connection") + "</header>");
                out.println("<header name =\"User-Agent\">" + request.getHeader("user-agent") + "</header>");
                out.println("<header name =\"Accept\">" + request.getHeader("accept") + "</header>");
                out.println("</request-headers>");
                out.println("<query-string>");

                String[] query_strings = URLDecoder.decode(request.getQueryString(), "UTF-8").split("&");
                for (String temp_str : query_strings) {
                    String[] temp_query_string_pair = new String[2]; //Storing 1 key-value
                    temp_str = temp_str.replace("=", " ");
                    temp_query_string_pair = temp_str.split(" ");
                    if (temp_query_string_pair.length == 2) {   // Ignore malformed string
                        out.println("<" + temp_query_string_pair[0] + ">" + temp_query_string_pair[1]
                                + "</" + temp_query_string_pair[0] + ">");
                    }
                }
                out.println("</query-string>");
                out.println("</response>");
            }

            current_format = "";
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

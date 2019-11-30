<%-- 
    Document   : editBook
    Created on : 2019-11-30, 16:58:56
    Author     : jasminelatendresse
--%>

<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="repository.core.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book Page</title>
        <link href="style/addBookCSS.css" rel="stylesheet" type="text/css">
    </head>
    <%
        try {
            Session currentSession = (Session) session.getAttribute("currentSession");
            if (!currentSession.isUserLoggedIn()) {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("login.jsp");
        }

        if (!request.getParameterMap().containsKey("bookID")) {
            request.setAttribute("errorMessage", "Cannot access this page at the moment");
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            response.sendRedirect("error.jsp");
        }


    %>
    <body>
        <h1>Edit book</h1>
        <div>
            <form action="EditBookController" method="POST" enctype="multipart/form-data">
                <input type="text" value="<%=request.getParameter("bookID")%>" name="id" readonly><br>
                Title <input type="text" name="title"><br>
                Description <input type="text" name="description"><br>
                Author First Name <input type="text" name="fname"><br>
                Author Last Name <input type="text" name="lname"><br>
                Cover Image <input type="file" name="image"/><br>
                <input type="submit" value="Submit" name="submit"/>
            </form>
        </div>
    </body>
</html>

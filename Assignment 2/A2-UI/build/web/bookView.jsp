<%-- 
    Document   : viewBook
    Created on : 6 nov. 2019, 08:40:53
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="repository.core.Book"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        String userName = (String) request.getAttribute("username");
        if (null == userName) {
            response.sendRedirect("login.jsp");
        }
    %>
    <body>
        <h1>List of books</h1>
        ${books}
    </body>
</html>
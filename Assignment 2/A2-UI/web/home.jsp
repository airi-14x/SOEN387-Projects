<%-- 
    Document   : homepage
    Created on : 6 nov. 2019, 08:38:56
    Author     : Airi & Jasmine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="repository.core.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <%
        String userName = (String) request.getAttribute("username");
        if (null == userName) {
            response.sendRedirect("login.jsp");
        }
    %>
    <body>
        <h1>Welcome to the Book Store!</h1>
        <a href="bookView">View books here</a><br>
        <a href="addBook.jsp">Add a book</a><br>
        <form action="LogoutConroller" method="POST">
            <input type="submit" value="Logout"/>
        </form><br>
        <%=request.getAttribute("username")%>
    </body>
</html>

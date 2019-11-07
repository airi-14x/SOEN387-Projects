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
        <!--<a href="bookView.jsp">View books here</a><br>-->
        <a href="addBook.jsp">Add a book</a><br>
<<<<<<< HEAD
=======

        <form action="DeleteBookController" method="DELETE">Provide bookID to delete: <input type="text" name="bookID">
        <button name="deleteBook" value="submit">Submit</button><br>
        Delete All <button name="deleteAll" value="submit">Submit</button></form>
        
>>>>>>> 6d33a6e3b6a421d62950c78678002e85c386917f
        <form action="LogoutController" method="POST">
            <input type="submit" value="Logout"/>
        </form><br>
        <p>Welcome <%=request.getAttribute("username")%> ! </p>
    </body>
</html>

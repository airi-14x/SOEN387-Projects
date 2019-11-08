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
        <link href="style/homeCSS.css" rel="stylesheet" type="text/css">
    </head>
    <%
        String userName = (String) request.getAttribute("username");
        if (null == userName) {
            response.sendRedirect("login.jsp");
        }
    %>
    
    <body>
        <h1>Welcome to the Book Store <%=request.getAttribute("username")%> !</h1>
        <div> 
            <form action="DisplayAllController" method="GET">
                 View All Books <input type="submit" name="displayAll" value="Submit"/><br>
            </form>
            
            <form action="BookViewController" method="GET">
                View Book with ID:<input type="text" name="bookID">
                <input type="submit" name="viewBook" value="Submit"/><br>
                View Book with ISBN:<input type="text" name="ISBN">
                <input type="submit" name="viewBook" value="Submit"/><br>
            </form>
            
            <a href="addBook.jsp">Add/Update a book</a><br>

            <form action="DeleteBookController" method="GET">
                Delete book with bookID:<input type="text" name="bookID" value = 0>
                <input type="submit" name="deleteBook" value="Submit"/><br>
                Delete all: 
                <input type="submit" name="deleteAll" value="Submit"/>
            </form>
        </div>
        <form action="LogoutController" method="POST">
            <input type="submit" value="Logout"/>
        </form><br>
    </body>
</html>

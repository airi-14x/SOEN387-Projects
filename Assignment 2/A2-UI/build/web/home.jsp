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
            <!--<a href="bookView.jsp">View books here</a><br>-->
            <a href="addBook.jsp">Add a book</a><br>

            <form action="DeleteBookController" method="DELETE">Provide bookID to delete: <input type="text" name="bookID">
                <input type="submit" name="deleteBook" value="Submit"/><br>
                Delete All <input type="submit" name="deleteAll" value="Submit"/>
            </form>
        </div>
        <form action="LogoutController" method="POST">
            <input type="submit" value="Logout"/>
        </form><br>
    </body>
</html>

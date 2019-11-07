<%-- 
    Document   : UpdateBook
    Created on : 6 nov. 2019, 09:23:30
    Author     : Airi
--%>

<%@page import="repository.core.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <%
        String user = (String) Session.getCurrentUser();
        if (null == user) {
            response.sendRedirect("login.jsp");
        }
    %>
    <body>
        <h1>Add a book</h1>
        <form action="AddBookController" method="POST">
            Title <input type="text" name="title"><br>
            ISBN <input type="text" name="isbn"><br>
            Description <input type="text" name="description"><br>
            Author First Name <input type="text" name="fname"><br>
            Author Last Name <input type="text" name="lname"><br>
            Publisher <input type="text" name="pname"><br>
            Publisher Address <input type="text" name="paddress"><br>
            <button value="submit" name="submit">Submit<br/>
        </form>
    </body>
</html>

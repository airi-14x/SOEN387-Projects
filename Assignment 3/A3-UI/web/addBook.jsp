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
        <title>Add Book Page</title>
        <link href="style/addBookCSS.css" rel="stylesheet" type="text/css">
    </head>
     <%
        String user = (String) Session.getCurrentUser();
        if (null == user) {
            response.sendRedirect("login.jsp");
        }
    %>
    <body>
        <h1>Add a book</h1>
        <div>
            <form action="AddBookController" method="POST" enctype="multipart/form-data">
                Title <input type="text" name="title"><br>
                Description <input type="text" name="description"><br>
                ISBN <input type="text" name="isbn"><br>
                Author First Name <input type="text" name="fname"><br>
                Author Last Name <input type="text" name="lname"><br>
                Publisher <input type="text" name="pname"><br>
                Publisher Address <input type="text" name="paddress"><br>
                Cover Image <input type="file" name="image"/><br>
                <input type="submit" value="Submit" name="submit"/>
            </form>
        </div>
        
        <h1>Update a book</h1>
        <div>
            <form action="UpdateBookController" method="POST">
                ID <input type="text" name="id"><br>
                Title <input type="text" name="title"><br>
                Description <input type="text" name="description"><br>
                Author First Name <input type="text" name="fname"><br>
                Author Last Name <input type="text" name="lname"><br>
                <input type="submit" value="Submit" name="submit"/>
            </form>
        </div>
    </body>
</html>

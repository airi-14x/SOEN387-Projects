<%-- 
    Document   : displayAll
    Created on : 8 nov. 2019, 10:58:17
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
        <h1> Display All Books in Repository Database: </h1><br>
        ${books}
    </body>
</html>

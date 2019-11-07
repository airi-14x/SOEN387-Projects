<%-- 
    Document   : updateBook
    Created on : 2019-11-06, 19:26:55
    Author     : jasminelatendresse
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
        <h1>Hello World!</h1>
    </body>
</html>

<%-- 
    Document   : error
    Created on : 6 nov. 2019, 09:15:43
    Author     : Airi & Jasmine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1><%=request.getAttribute("errorMessage")%></h1>
    </body>
</html>

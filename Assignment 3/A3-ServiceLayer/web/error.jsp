<%-- 
    Document   : error
    Created on : 30 nov. 2019, 14:51:04
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <link href="style/errorCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1><%=request.getAttribute("errorMessage")%></h1>
    </body>
</html>

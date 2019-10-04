<%-- 
    Document   : HomePage
    Created on : 4 oct. 2019, 15:42:24
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE htmlPUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Home Page</title>
    </head>
    <body bgcolor="blue">
        <h1>Welcome User</h1>
        <h2><b><%=request.getParameter("username")%></b></h2>
    </body>
</html>

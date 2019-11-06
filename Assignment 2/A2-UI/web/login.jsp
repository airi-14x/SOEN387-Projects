<%-- 
    Document   : login
    Created on : 6 nov. 2019, 08:40:18
    Author     : Airi & Jasmine
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="repository.core.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="LoginController" method="POST">
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="Login"/>
        </form>
    </body>
</html>

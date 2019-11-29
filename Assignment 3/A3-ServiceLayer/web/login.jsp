<%-- 
    Document   : login
    Created on : 29 nov. 2019, 12:36:57
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="style/loginCSS.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <h1>Login</h1>
        <div>
            <form action="LoginController" method="POST">
                Username: <input type="text" name="username"><br>
                Password: <input type="password" name="password"><br>
                <input type="submit" value="Login"/>
            </form>
        </div>
    </body>
</html>

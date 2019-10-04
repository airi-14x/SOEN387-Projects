<%-- 
    Document   : MyFirstJSP
    Created on : 4 oct. 2019, 15:38:17
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="HomePage.jsp" method="GET">
            Please enter your name:<br>
            <input type="text" name="username">
            <br/>
        <input type="submit" value="Submit"/>
        </form>
    </body>
</html>

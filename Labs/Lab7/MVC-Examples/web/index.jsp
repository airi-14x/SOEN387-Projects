<%-- 
    Document   : index
    Created on : 31 oct. 2019, 17:15:11
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- VIEW -->
<html>
    <head>
        <title>Registration Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="MyControllerServlet" method="POST">
            Name: <input type="text" name="username"><br>
            Email: <input type="text" name="email"><br>
            <br />
            <input type="submit" value="Submit"/>
        </form>
    </body>
</html>

<%-- 
    Document   : Calculator
    Created on : 4 nov. 2019, 18:19:56
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculator</title>
    </head>
    <body>
        <h2>Calculator</h2>
        <form method="post" action="calculator">
            <input type="text" name="number1" class="a">
            <select name="operator" class="a">
                <option selected>+</option>
                <option>-</option>
                <option>*</option>
                <option>/</option>
            </select>
            <input type="text" name="number2">
            <input type="submit" value="=">
            
        </form>
    </body>
</html>
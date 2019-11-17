<%-- 
    Document   : jQueryExample5
    Created on : 17 nov. 2019, 13:20:49
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>jQuery Example 5</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"> 
        </script>
        <script>
        $(document).ready(function(){
           $("button").dblclick(function(){
            $(this).hide();
           });
        });
        </script>
    </head>
    <body>
        <button>If you double-click on me, I will disappear.</button><br><br>
        <button>Click me away!</button><br><br>
        <button>Click me too!</button><br><br>
    </body>
</html>


<%-- 
    Document   : jQueryExample3
    Created on : 17 nov. 2019, 11:06:41
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>jQuery Example 2</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"> 
        </script>
        <script>
        $(document).ready(function(){
           $("button").click(function(){
             $("p").toggle();  
           });
        });
        </script>
    </head>
    <body>
        <button>It's a Toggle Button</button>
        <p>Welcome to tutorial class!</p>
        <p>We are learning jQuery in SOEN 387.</p>
    </body>
</html>
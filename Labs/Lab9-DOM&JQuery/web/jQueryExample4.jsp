<%-- 
    Document   : jQueryExample4
    Created on : 17 nov. 2019, 13:13:04
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>jQuery Example 4</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"> 
        </script>
        <script>
        $(document).ready(function(){
           $("button").click(function(){
            $("div").animate({left:'250px'});
           });
        });
        </script>
    </head>
    <body>
        <button>Start Animation</button>
        <p>HTML elements have a static position by default, and cannot be moved.
        To manipulate the position, we are firstly setting the CSS position property
        of the element to relative, fixed, or absolute!</p>
        <div style="background:#98bf21;height:100px;width:100px;position:absolute;"></div>
    </body>
</html>

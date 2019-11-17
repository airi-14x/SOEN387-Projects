<%-- 
    Document   : jQueryExample2
    Created on : 17 nov. 2019, 10:53:46
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
           $("#hide").click(function(){
             $("p").hide();
           });
           $("#show").click(function(){
             $("p").show();
           });
        });
        </script>
    </head>
    <body>
        <p>If you click the "Hide" button, I will disappear.</p>
        <button id="hide">Hide</button>
        <button id="show">Show</button>
    </body>
</html>

<%-- 
    Document   : Example4
    Created on : 16 nov. 2019, 11:28:14
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Example 4</title>
    </head>
    <body>
        <h2>Lets learn EventListener</h2>
        <p>This example uses the addEventListener() method to attach
        a click event to a button.</p>
        <button id='myBtn'>Try it</button>
        
        <script>
            document.getElementById("myBtn").addEventListener("click",
            function(){
                alert("Tutorial for SOEN 387!");
            });
        </script>
    </body>
</html>

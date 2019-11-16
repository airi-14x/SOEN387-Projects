<%-- 
    Document   : Example1
    Created on : 16 nov. 2019, 09:52:20
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Example 1</title>
    </head>
    <body>
        <h2>DOM Method</h2>
        <p id="name"></p>
        
        <script>
            document.getElementById("name").innerHTML = "Setting inner text for paragraph\n\
        with id=name";
        </script>
        
        <p id="name2"></p>
        
        <script>
            var x = document.getElementsByTagName("h2");
            document.getElementById("name2").innerHTML = 
                    "The text in first paragraph (index 0) is: " + x[0].innerHTML;
        </script>
    </body>
</html>

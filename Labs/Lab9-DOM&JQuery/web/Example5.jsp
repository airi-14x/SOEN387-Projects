<%-- 
    Document   : Example5
    Created on : 16 nov. 2019, 11:39:18
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Example 5</title>
    </head>
    <body>
        <h1 id='id01'>Learning Navigation in DOM</h1>
        <p id='id02'></p>
        
        <script>
            document.getElementById("id02").innerHTML=
                    document.getElementById("id01").firstChild.nodeValue;
        </script>
    </body>
</html>

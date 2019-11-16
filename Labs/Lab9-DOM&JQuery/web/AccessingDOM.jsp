<%-- 
    Document   : AccessingDOM
    Created on : 16 nov. 2019, 11:43:58
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accessing DOM</title>
        <script>
            // Run this function when the document is loaded
            window.onload = function(){
              // Create a couple of elements in an otherwise empty HTML page
              var heading = document.createElement("h2");
              var heading_text = document.createTextNode("We are adding text in DOM!");
              heading.appendChild(heading_text);
              document.body.appendChild(heading);
            }
            
        </script>
    </head>
    <body>
       
    </body>
</html>

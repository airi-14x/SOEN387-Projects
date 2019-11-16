<%-- 
    Document   : Exampl2
    Created on : 16 nov. 2019, 10:27:15
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Example 2</title>
    </head>
    <body>
        <p>Click the button to display the date.</p>
        <button id="myBtn">The time is?</button>
        <!-- <button onclick="displayDate()" id="myBtn">The time is?</button> -->
        <script>
            function displayDate(){
                document.getElementById("demo").innerHTML = Date();
            }
            document.getElementById("myBtn").addEventListener("click",displayDate);
            element.addEventListener("click", function(){alert("Hello World!");});
        </script>
        <p id="demo"></p>
    </body>
</html>

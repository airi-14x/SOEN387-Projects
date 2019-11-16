<%-- 
    Document   : Example7
    Created on : 16 nov. 2019, 11:54:25
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Example 7</title>
        
        <style>
           #container{
              width: 400px;
              height: 400px;
              position: relative;
              background: yellow;
           }
           #animate{
               width: 50px;
               height: 50px;
               position: absolute;
               background-color: red;
           }
        </style>
    </head>
    <body>
        <p><button onclick="myMove()">Click Me</button></p>
        <div id='container'>
            <div id="animate"></div>
        </div>
        
        <script>
            function myMove(){
                var elem = document.getElementById("animate");
                var pos = 0;
                var id = setInterval(frame,5);
                function frame(){
                    if(pos == 350){
                        clearInterval(id);
                    }
                    else{
                        pos++;
                        elem.style.top = pos + "px";
                        elem.style.left = pos + "px";
                    }
                }
            }
        </script>
    </body>
</html>

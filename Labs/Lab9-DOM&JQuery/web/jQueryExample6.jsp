<%-- 
    Document   : jQueryExample6
    Created on : 17 nov. 2019, 13:31:57
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>jQuery Example 6</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"> 
        </script>
        <h1>Learning jQuery dropdown box</h1>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#isSelect").click(function(){
                   alert($('#country').val()); 
                });
                $("#selectChina").click(function(){
                   $("#country").val("China"); 
                });
                $("#selectMalaysia").click(function(){
                   $("#country").val("Malaysia"); 
                });
            });
        </script>
    </head>
    <body>
        <select id="country">
            <option value="None">--Select--</option>
            <option value="China">China</option>
            <option value="Malaysia">Malaysia</option>
        </select>
        
        <input type="button" value="Display Selected" id="isSelect">
        <input type="button" value="Select China" id="selectChina">
        <input type="button" value="Select Malaysia" id="selectMalaysia">
    </body>
</html>

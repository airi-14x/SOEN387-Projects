<%-- 
    Document   : first
    Created on : 4 oct. 2019, 15:49:52
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Example</title>
    </head>
    <body>
        <div>
        <h3>Please select a file to Upload in Server</h3>
        <form action="upload" method="post" enctype="multipart/form-data">
            <input type="file" name="file" /><input type="submit" value="upload"/>
        </form>
        </div>
    </body>
</html>

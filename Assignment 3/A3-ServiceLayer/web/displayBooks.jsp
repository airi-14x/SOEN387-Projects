<%-- 
    Document   : displayBooks
    Created on : 28 nov. 2019, 16:18:35
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        ${books}
        
         <form action="BooksServlet" method="GET">
                View All Books <input type="submit" name="displayAll" value="Submit"/><br>
         </form>
    </body>
</html>

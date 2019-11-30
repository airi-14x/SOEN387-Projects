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
        <title>Books</title>
        <link href="style/displayBooksCSS.css" rel="stylesheet" type="text/css">
    </head>
    
    <jsp:useBean id="currentUser" scope="session" class="repository.core.Session" />
    <%
        Cookie ck[] = request.getCookies();
        String username = null;
        
        if (currentUser.isUserLoggedIn() == false)
        {
           response.sendRedirect("login.jsp");
        }
        
        else if (currentUser.isUserLoggedIn())
        {
            
            for(int i = 0; i < ck.length; i++)
            {
                if(currentUser.getUserName().equals(ck[i].getValue()))
                     username = ck[i].getValue();
            }
            
            if (username == null)
                response.sendRedirect("login.jsp"); 
            
        }
        
    %>
    
    <body>
        
        <h1>Welcome to the Books <%= username %> !</h1>
        <div class="submit-actions">
         <form action="BooksController" method="GET">
                View All Books: <input type="submit" name="books" value="displayAll"/><br>
         </form>
        
         <form action="BooksController" method="GET">
                View Book with ID:<input type="text" name="viewBookID">
                <input type="submit" name="books" value="displayBook"/><br>
         </form>
        </div>
        
        <div class="results">
            <div class="results-header">${results}</div>
            <div class="error">${error}</div>
        ${books}
        </div>
        
        <form action="LogoutController" method="POST">
            <input type="submit" value="Logout"/>
        </form><br>
    </body>
</html>

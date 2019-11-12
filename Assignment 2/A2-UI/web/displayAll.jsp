<%-- 
    Document   : displayAll
    Created on : 8 nov. 2019, 10:58:17
    Author     : Airi
--%>

<%@page import="repository.core.Book"%>
<%@page import="repository.core.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
        String user = (String) Session.getCurrentUser();
        if (null == user) {
            response.sendRedirect("login.jsp");
        }
    %>
    <body>
        <h1> Display All Books in Repository Database: </h1><br>
        
        <table border="1px solid black;">
            <tr>
                <td>ID</td>
                <td>Title</td>
                <td>Description</td>
                <td>ISBN</td>
                <td>Author</td>
                <td>Publisher Company</td>
                <td>Publisher Address</td>
                <td>Book Cover</td>
            </tr>
            <c:forEach items="${books}" var="book">
                <tr>
                    <td>${book.id}</td>
                    <td>${book.title}</td>
                    <td>${book.description}</td>
                    <td>${book.getISBN()}</td>
                    <td>${book.author}</td>
                    <td>${book.publisherCompany}</td>
                    <td>${book.publisherAddress}</td>
                    <td>${book.cover}</td>
                </tr>
            </c:forEach>  
            
        </table>
        
    </body>
</html>

<%-- 
    Document   : viewBook
    Created on : 6 nov. 2019, 08:40:53
    Author     : Airi
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="repository.core.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="repository.core.Book"%>

<jsp:useBean id="currentUser" scope="session" class="repository.core.Session" />

    <%
        String user = currentUser.getCurrentUser();
        if (null == user) {
            response.sendRedirect("login.jsp");
        }

    %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Book</title>
    </head>
    
    <body>
        <div>
            <table border="1px solid black;">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Description</th>
                    <th>ISBN</th>
                    <th>Author</th>
                    <th>Publisher Company</th>
                    <th>Publisher Address</th>
                    <th>Book Cover</th>
                </tr>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td><input type="text" value="${book.id}" name="bookId" readonly/></td>
                        <td>${book.title}</td>
                        <td>${book.description}</td>
                        <td>${book.getISBN()}</td>
                        <td>${book.author}</td>
                        <td>${book.publisherCompany}</td>
                        <td>${book.publisherAddress}</td>
                        <td><form action="ImageController" method="GET"><input type="submit" value="View Cover" name="viewCover"/></form></td>
                    </tr>
                </c:forEach>   
            </table>
        </div>
    </body>
</html>
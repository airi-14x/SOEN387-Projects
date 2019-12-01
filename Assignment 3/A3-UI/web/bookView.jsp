<%-- 
    Document   : viewBook
    Created on : 6 nov. 2019, 08:40:53
    Author     : Airi
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="repository.core.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="repository.core.Book"%>

<%
    try {
        Session currentSession = (Session) session.getAttribute("currentSession");
        if (!currentSession.isUserLoggedIn()) {
            response.sendRedirect("login.jsp");
        }
    } catch (Exception e) {
        response.sendRedirect("login.jsp");
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Book</title>
    </head>
    <jsp:useBean id = "book" class = "repository.core.Book" scope = "session"/>
    <body>
        <div><a href="BookViewNoCoverController?viewBookID=<jsp:getProperty name = "book" property = "id"/>">View book without cover</a></div>
        <div>
            <h2>Book details - With cover</h2>
            <%=request.getAttribute("error")%>
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
                <tr>
                <form action="ImageController" method="GET">
                    <td><input type="text" value="<jsp:getProperty name = "book" property = "id"/>" name="bookId" readonly/></td>
                    <td><jsp:getProperty name = "book" property = "title"/></td>
                    <td><jsp:getProperty name = "book" property = "description"/></td>
                    <td><jsp:getProperty name = "book" property = "ISBN"/></td>
                    <td><jsp:getProperty name = "book" property = "author"/></td>
                    <td><jsp:getProperty name = "book" property = "publisherCompany"/></td>
                    <td><jsp:getProperty name = "book" property = "publisherAddress"/></td>
                    <td><input type="submit" value="View Cover" name="viewCover"/></td>
                    <td><a href="./editBook.jsp?bookID=<jsp:getProperty name = "book" property = "id"/>">Edit book</a></td>
                </form>
                </tr>
            </table>
        </div>
    </body>
</html>
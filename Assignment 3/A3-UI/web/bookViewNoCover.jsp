<%-- 
    Document   : bookViewNoCover
    Created on : 2019-11-30, 11:24:33
    Author     : jasminelatendresse
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
    } catch(Exception e) {
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
        <div><a href="BookViewController?viewBookID=<jsp:getProperty name = "book" property = "id"/>">View book with cover</a></div>
        <div>
            <h2>Book details - No cover</h2>
            <%=request.getAttribute("error")%>
            <jsp:useBean id = "book" class = "repository.core.Book" scope = "session"/>
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
                    <td><a href="./editBook.jsp?bookID=<jsp:getProperty name = "book" property = "id"/>">Edit book</a></td>
                </form>
                </tr>
            </table>
        </div>
        <a href="./home.jsp">Back to homepage</a>
    </body>
</html>

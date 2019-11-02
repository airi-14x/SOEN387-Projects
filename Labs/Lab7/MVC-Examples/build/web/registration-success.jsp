<%-- 
    Document   : registration-success
    Created on : 31 oct. 2019, 16:55:13
    Author     : Airi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id = "bean" class = "com.registration.LoginBean">
<% out.print("You are successfully registered! "
        + "Welcome, " + bean.getName()); %>
</jsp:useBean>
<%-- 
    Document   : AttributeTest
    Created on : Sep 4, 2008, 12:27:24 AM
    Author     : Rakesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%@ taglib prefix="rock" uri="Attribute_ClassicStyle" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> Classic Tags </h1> <br>
         <rock:attribute_ClassicStyle movieList='${movieList}' >
             Name is: ${m.name} <br>
             Genre is: ${m.genre} <br>
         </rock:attribute_ClassicStyle>
         <h1> HELLO WORLD </h1> 
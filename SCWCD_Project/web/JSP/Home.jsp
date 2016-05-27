<%-- 
    Document   : Home
    Created on : Aug 27, 2008, 11:57:44 PM
    Author     : Rakesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% sample.Person person = new sample.Person();
           person.setName("Rakesh");
        application.setAttribute("person", person); %>
        <a href="Next.jsp"> Click Here </a>
    </body>
</html>

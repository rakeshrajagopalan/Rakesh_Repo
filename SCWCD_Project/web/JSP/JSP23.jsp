<%-- 
    Document   : JSP23
    Created on : Sep 1, 2008, 9:27:08 PM
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
        <% sample.Person p = new sample.Person();
           request.setAttribute("person", p); 
           RequestDispatcher disp = request.getRequestDispatcher("JSP24.jsp");
           disp.forward(request, response); %>
    </body>
</html>

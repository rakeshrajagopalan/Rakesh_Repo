<%-- 
    Document   : Generic_Page
    Created on : Sep 12, 2008, 12:41:41 AM
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
        <% pageContext.setAttribute("Apple", "Boy"); 
        RequestDispatcher rd = request.getRequestDispatcher("JSP2.jsp");
           rd.forward(request, response); %>
    </body>
</html>

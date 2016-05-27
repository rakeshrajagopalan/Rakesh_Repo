<%-- 
    Document   : JSP21
    Created on : Sep 1, 2008, 9:11:26 AM
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
        <% request.setAttribute("username", "Rakesh"); 
           RequestDispatcher disp = request.getRequestDispatcher("JSP22.jsp");
           disp.forward(request, response); %>
    </body>
</html>

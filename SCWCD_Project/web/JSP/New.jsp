<%-- 
    Document   : New
    Created on : Sep 9, 2008, 9:04:10 AM
    Author     : Rakesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String s = "Apple,Boy,Cat";
          request.setAttribute("s",s); 
          RequestDispatcher disp = request.getRequestDispatcher("Test.jsp");
          disp.forward(request, response); %>
    </body>
</html>

<%-- 
    Document   : JSP11
    Created on : Aug 30, 2008, 12:44:04 PM
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
        <% actioncheck.Person person = new actioncheck.Employee(); 
        person.setName("Raku");
        request.setAttribute("person", person);
        RequestDispatcher disp = request.getRequestDispatcher("JSP12.jsp");
        disp.forward(request, response);
        %>
    </body>
</html>

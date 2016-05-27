<%-- 
    Document   : JSPOne
    Created on : Aug 28, 2008, 10:02:24 PM
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
        session.setAttribute("person", person);
        RequestDispatcher disp = request.getRequestDispatcher("JSP2.jsp");
        disp.forward(request, response);
        %>
        <a href="JSP2.jsp"> Click Here </a>
    </body>
</html>

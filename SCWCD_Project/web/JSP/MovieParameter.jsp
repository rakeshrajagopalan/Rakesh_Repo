<%-- 
    Document   : MovieParameter
    Created on : Sep 4, 2008, 12:29:58 AM
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
        <% sample.Movie m1 = new sample.Movie();
            java.util.ArrayList al = new java.util.ArrayList();
            m1.setName("Minnalae");
            m1.setGenre("Romance");
            al.add(m1);
            m1 = new sample.Movie();
            m1.setName("Yaar");
            m1.setGenre("Horror");
            al.add(m1);
            request.setAttribute("movieList", al);
            RequestDispatcher disp = request.getRequestDispatcher("AttributeTest.jsp");
            disp.forward(request, response);
        %>
    </body>
</html>

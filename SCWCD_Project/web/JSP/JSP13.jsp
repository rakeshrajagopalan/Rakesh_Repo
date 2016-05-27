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
        <% java.util.HashMap map = new java.util.HashMap();
        map.put(3,"Three");
        java.util.ArrayList list = new java.util.ArrayList();
        list.add("Apple");
        list.add("Boy");
        list.add("Cat");
        list.add("Dog");
        list.add("Eagle");
        request.setAttribute("map", map);
        request.setAttribute("list",list);
        RequestDispatcher disp = request.getRequestDispatcher("JSP14.jsp");
        disp.forward(request, response);
        %>
        <a href="JSP2.jsp"> Click Here </a>
    </body>
</html>

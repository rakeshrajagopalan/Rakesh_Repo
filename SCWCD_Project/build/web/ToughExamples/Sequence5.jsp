<%-- 
    Document   : Sequence5
    Created on : Sep 20, 2008, 9:35:05 PM
    Author     : Rakesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        The age is: <%= session.getAttribute("age") %>
    </body>
</html>

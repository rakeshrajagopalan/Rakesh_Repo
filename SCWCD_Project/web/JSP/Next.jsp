<%-- 
    Document   : Next
    Created on : Aug 28, 2008, 12:00:11 AM
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
        <jsp:useBean id="person" class="sample.Person" scope="application" />
    <%--    <jsp:setProperty name="person" property="name" value="Fred" /> --%>
        Person Created is: <jsp:getProperty name="person" property="name" />
    </body>
</html>

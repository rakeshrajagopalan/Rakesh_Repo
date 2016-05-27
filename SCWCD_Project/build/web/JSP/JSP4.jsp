<%-- 
    Document   : JSPFour
    Created on : Aug 28, 2008, 10:36:35 PM
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
        <jsp:useBean id="person" class="sample.Person" >
            <jsp:setProperty name="person" property="name" param="name" />
            <jsp:setProperty name="person" property="empID" param="eID" />
        </jsp:useBean>
        The name is: <jsp:getProperty name="person" property="name" />
        The ID is: <jsp:getProperty name="person" property="empID" />
    </body>
</html>

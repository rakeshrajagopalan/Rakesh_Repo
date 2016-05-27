<%-- 
    Document   : RakuNestTest
    Created on : Sep 7, 2008, 12:05:59 AM
    Author     : Rakesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%@ taglib prefix="rak" uri="Menu"%>
   <%@ taglib prefix="rock" uri="MenuItem"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <rak:menu> 
        <rock:menuItem item="Dogs" />
        <rock:menuItem item="Cats" />
        <rock:menuItem item="Horses" />
        </rak:menu>
    </body>
</html>

<%-- 
    Document   : Rakesh
    Created on : Sep 3, 2008, 12:27:18 AM
    Author     : Rakesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%@ taglib prefix="if" uri="Rakesh" %>
   <%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <if:advice>
            <jsp:attribute name='user'>Raku</jsp:attribute>
        </if:advice> 
        <br> If Test:
        <c:if test="${1 eq 1}" var="tester" >
            Hi
            ${tester}
        </c:if>
    </body>
</html>

<%-- 
    Document   : JSP19
    Created on : Aug 31, 2008, 9:49:15 PM
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
        <c:forEach var="next" items="${alpha}" varStatus="cnt" >
       <%--     <c:out value='${next}' />  --%>
             Loop Count: ${cnt.count} <br>   
             ${next} <br>
        </c:forEach>
    </body>
</html>

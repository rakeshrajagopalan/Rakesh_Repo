<%-- 
    Document   : JSP10
    Created on : Aug 30, 2008, 12:32:12 PM
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
        <c:if test="${hello eq 'Lord' }" >
            Hello World
        </c:if>
        <c:out value="God is great" />
        <c:set var='Test' value='Jai Sri Ram' />
        The value is: ${Test}
        <c:choose>
            <c:when test="${user eq 'Rakesh'}" >
                Inside When:   ${user eq null}
            </c:when>
        </c:choose>   
        <br> C:FORTOKENS
        <c:forTokens items='(A B C)-(D E F)' delims='()' var='item'>
            <c:out value='${item}'/><br>
        </c:forTokens>
    </body>
</html>

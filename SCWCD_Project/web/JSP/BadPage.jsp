<%-- 
    Document   : BadPage
    Created on : Sep 1, 2008, 11:48:12 PM
    Author     : Rakesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%@ page errorPage="ErrorPage.jsp" %> 
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
       <%-- <c:catch> --%>
        <% int x= 10/0; %>
       <%-- </c:catch> --%>
        Error caught!
    </body>
</html>

<%-- 
    Document   : JSP18
    Created on : Aug 31, 2008, 9:45:35 PM
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
        <table>
            <c:forEach var="next" items="${alpha}" >
                <tr>
                    <td> ${next} </td> 
                </tr>    
            </c:forEach>  
        </table>
    </body>
</html>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%@ page import="java.util.*" isThreadSafe="true" isErrorPage="true" isELIgnored="true" errorPage="/1.jsp" %>
    
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="/Sequence4.do">
            
            The name is: <%= session.getAttribute("name") %>
            <input type="text" name="age"> <br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>

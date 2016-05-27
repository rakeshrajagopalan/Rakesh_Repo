<%-- 
    Document   : JSPA
    Created on : Sep 4, 2008, 10:52:50 PM
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
        All's fine so far..<br>
        Going to include Page B.<br>
        <jsp:include page="JSPB.jsp"/>
        Returned from the include call, back to Page A.<br>
    </body>
</html>

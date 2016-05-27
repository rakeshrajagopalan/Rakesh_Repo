<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%@ taglib prefix="rock" uri="Parent" %>
   <%@ taglib prefix="raku" uri="Child" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <rock:parent name="Rakesh"> 
        <raku:child> Hi </raku:child> 
        <raku:child> Hello </raku:child>
        </rock:parent>
    </body>
</html>

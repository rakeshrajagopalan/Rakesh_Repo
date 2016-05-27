<%-- 
    Document   : ToyTester
    Created on : Sep 21, 2008, 12:30:22 PM
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
        The person ${person.name}'s dog ${person.dog.name}'s toys are:
        1. ${person['dog']['toys']['0']["name"]}
        2. ${person.dog.toys[1].name}
    </body>
</html>

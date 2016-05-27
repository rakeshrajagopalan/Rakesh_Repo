

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <br> The complete list is: ${alphabets}
        <br> Using '.': ${alphabets.Apple}
        <br> Using '[]': ${alphabets["Apple"]} 
        <br> ${alphabets["Boy"]}
        <br> Test: ${alphabets["Cat"]}
        <br> Nested Attribute: ${alphabets[Dog]}
    </body>
</html>

<%-- 
    Document   : FileMover
    Created on : Feb 16, 2008, 5:12:16 PM
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
    <form name="filemover" action="MoveController.do" method="post">
        <body>
            <center><h2>File Mover</h2></center><br><br><br>
            <table align="center">
                <tr>
                    <td>Source Dir Path</td>
                    <td><input type="text" name="sourcedir" value=""></td>
                </tr>
                <tr>
                    <td>Target Dir Path</td>
                    <td><input type="text" name="targetdir" value=""></td>
                </tr>
            </table><br><br><br>
            <center><input type="submit" name="submitbutton" value="Move Files"></center>
        </body>
    </form>
</html>

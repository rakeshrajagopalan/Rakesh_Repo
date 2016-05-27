<%-- 
    Document   : JSPB
    Created on : Sep 4, 2008, 10:53:04 PM
    Author     : Rakesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

Inside Page B.<br>
Going to call the exception. <br>
<% throw new javax.servlet.jsp.SkipPageException(); %>


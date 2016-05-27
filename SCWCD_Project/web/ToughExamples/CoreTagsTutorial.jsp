<%-- 
    Document   : CoreTagsTutorial
    Created on : Sep 21, 2008, 2:10:22 PM
    Author     : Rakesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:out default="" escapeXml="" value="" /> <%-- Attribute value is madatory --%>
        <c:forEach var="hi" items='' begin="" end='' step="" varStatus="" > </c:forEach> <%-- No attribute is madatory --%>
        <c:forTokens var='hi' items='' delims='' begin="" step='' end='' varStatus=""></c:forTokens> <%-- Attributes items and delims are mandatory --%>
        <c:if test="" var='hi' scope='session'> </c:if> <%-- Attribute test is mandatory --%>
        <c:choose> 
            <c:when test=""> 
            </c:when> 
            <c:otherwise> 
            </c:otherwise>
        </c:choose> <%-- Attribute test is mandatory --%>
        <%-- Legal uses of c:set --%> 1. <c:set />
        2. <c:set var="hi" />
        3. <c:set var='hi' value='' scope="session" />
        4. <c:set var='hi' value='' />
        5. <c:set target="" property="" />
        6. <c:set target="" property="" value=""> </c:set> <%-- No attribute is mandatory --%>
        <c:remove var='hi' scope="session" /> <%-- Attribute var is mandatory --%>
        <%-- Legal uses of c:import --%>
        <%-- NOTE: Attributes varReader and var cannot go with each other --%>
        <%-- NOTE: Attributes scope and var must go with each other --%>
        1. <c:import url="" charEncoding="" context="" varReader="hello">  </c:import> 
        2. <c:import url='' charEncoding="" context="" scope="application" var="hi" /> <%-- Attribute url is mandatory --%>
        <%-- NOTE: c:param can be used in the body of only c:import, c:url and c:redirect --%>
        <c:param name='' value=''/> <%-- Attribute name is mandatory --%> 
        <c:url var="hi" context="" value='' scope='session' /> <%-- No attributes are mandatory --%>
        <c:catch var='hi' /> <%-- No attributes are mandatory --%>
        <c:redirect url="" context=''> </c:redirect> <%-- No attributes are mandatory --%>
    </body>
</html>

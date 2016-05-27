<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page import="chapter7.Counter;" />
<html>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1">
		<title>Counter</title>
	</head>
	<body>
		<%! Counter ctr = new Counter(); %>
		<h1>
			The visit count is:
		</h1>
		<% ctr.deserialize(); %>
		<%= ctr.getCount() %>
		<% ctr.serialize(); %>
	</body>
</html>

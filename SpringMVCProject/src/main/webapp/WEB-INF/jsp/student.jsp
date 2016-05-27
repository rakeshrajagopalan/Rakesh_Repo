<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Spring MVC Form Handling</title>
</head>
<body>

	<h2>Student Information</h2>
	<form:form method="POST" action="/SpringMVC/addStudent">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="address">Adddress</form:label></td>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<td><form:label path="className">Class Name</form:label></td>
				<td><form:input path="className" /></td>
			</tr>
			<tr>
				<td><form:label path="section">Section</form:label></td>
				<td><form:input path="section" /></td>
			</tr>
			<tr>
				<td><form:label path="emergencyContact">Emergency Contact</form:label></td>
				<td><form:input path="emergencyContact" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Submit" /></td>
			</tr>
		</table>
	</form:form>
</body>
<script>
	validate() {
		show("Hello World");
	}	
</script>
</html>
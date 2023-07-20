<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:forEach items="${users}" var="user">
		<pre>
			<tr>
				<td>User ID: <c:out value="${user.id()}" /></td>
				<td>User Name: <c:out value="${user.name()}" /></td>
				<td>User Email: <c:out value="${user.email()}" /></td>
				<td>User profile: <c:out value="${user.profile()}" /></td>
				<td>User type: <c:out value="${user.type()}" /></td>
				<td>User phone: <c:out value="${user.phone()}" /></td>
				<td>User address: <c:out value="${user.address()}" /></td>
				<td>User dob: <c:out value="${user.dob()}" /></td>
				<td>User createdUserId: <c:out value="${user.createdUserId()}" /></td>
				<td>User updatedUserId: <c:out value="${user.updatedUserId()}" /></td>
				<td>User deletedUserId: <c:out value="${user.deletedUserId()}" /></td>
			</tr>		
		</pre>
		<br>
	</c:forEach>
</body>
</html>
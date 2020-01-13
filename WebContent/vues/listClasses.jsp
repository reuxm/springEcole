<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prof</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${liste}" var="c">
				<tr>
					<td><c:out value="${c.id}" /></td>
					<td><c:out value="${c.nom}" /></td>
					<td><c:out value="${c.prof.nom}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>
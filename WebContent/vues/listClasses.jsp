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
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prof</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${liste}" var="c">
				<tr>
					<td><a href="./editClasse/${ c.id }"><c:out value="${c.id}" /></a></td>
					<td><c:out value="${c.nom}" /></td>
					<td><c:out value="${c.prof.nom}" /></td>
					<td>
						<form action="./classes" method="post">
							<input hidden="true" name="id" value="${c.id}" />
							<button type="submit">Supprimer</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="./editClasse">Nouvelle</a>
</body>
</html>
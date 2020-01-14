<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Ecole - Profs</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Date de naissance</th>
				<th>Adresse</th>
				<th>Sexe</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${liste}" var="p">
				<tr>
					<td><a href="./editProf/${ p.id }"><c:out value="${p.id}" /></a></td>
					<td><c:out value="${p.nom}" /></td>
					<td><c:out value="${p.prenom}" /></td>
					<td><c:out value="${fn:split(p.dateNaissance, ' ')[0]}" /></td>
					<td><c:out value="${p.adresse}" /></td>
					<td><c:out value="${p.sexe}" /></td>
					<td>
						<form action="./profs" method="post">
							<input hidden="true" name="id" value="${p.id}" />
							<button type="submit">Supprimer</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="./editProf">Nouveau</a>
</body>
</html>
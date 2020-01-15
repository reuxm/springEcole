<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Ecole - Eleves</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>Nom</th>
				<th>Prenom</th>
				<th>Classe</th>
				<th>Date de naissance</th>
				<th>Adresse</th>
				<th>Sexe</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${liste}" var="e">
				<tr>
					<td><a href="./editEleve/${ e.id }"><c:out value="${e.id}" /></a></td>
					<td><c:out value="${e.nom}" /></td>
					<td><c:out value="${e.prenom}" /></td>
					<td><c:out value="${e.classe.nom}" /></td>
					<td><c:out value="${fn:split(e.dateNaissance, ' ')[0]}" /></td>
					<td><c:out value="${e.adresse}" /></td>
					<td><c:out value="${e.sexe}" /></td>
					<td>
						<form action="./eleves" method="post">
							<input hidden="true" name="id" value="${e.id}" />
							<button type="submit">Supprimer</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="./editEleve">Nouveau</a>
</body>
</html>
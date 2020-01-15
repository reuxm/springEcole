<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edition - Eleve</title>
</head>
<body>
	<h2>Gestion d'eleve</h2>
	
	<form action="${ fill_id==-1 ? 'commitEleveEdit' : '../commitEleveEdit' }" method="post">
	<input type="text" name="id" value="${fill_id}" hidden="true" readonly>
	<input type="text" name="nom" value="${fill_nom}" placeholder="Nom">
	<input type="text" name="prenom" value="${fill_prenom}" placeholder="Prenom">
	<select name="classe">
		<c:forEach items="${ classes }" var="c">
		
			<c:choose>
			    <c:when test="${c.nom == fill_classe}">
					<option value="${c.id}" selected>
			    </c:when>    
			    <c:otherwise>
					<option value="${c.id}">
			    </c:otherwise>
			</c:choose>
			<%-- <option ...> print par le choose --%>
				<c:out value="${c.nom}"/>
			</option>
		</c:forEach>
	</select>
	<input type="date" name="birth" value="${fill_birth}" placeholder="Date de naissance">
	<input type="text" name="adresse" value="${fill_adresse}" placeholder="Adresse">
	<select name="sexe">
		<c:forEach items="${ sexes }" var="s">
		
			<c:choose>
			    <c:when test="${s == fill_sexe}">
					<option value="${s}" selected>
			    </c:when>    
			    <c:otherwise>
					<option value="${s}">
			    </c:otherwise>
			</c:choose>
			<%-- <option ...> print par le choose --%>
				<c:out value="${s}"/>
			</option>
		</c:forEach>
	</select>
	<button type="submit">Valider</button>
	</form>
	
</body>
</html>
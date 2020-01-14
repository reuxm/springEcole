<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edition - Prof</title>
</head>
<body>
	<h2>Gestion de classes</h2>
	
	<form action="${ fill_id==-1 ? 'commitProfEdit' : '../commitProfEdit' }" method="post">
	<input type="text" name="id" value="${fill_id}" hidden="true" readonly>
	<input type="text" name="nom" value="${fill_nom}" placeholder="Nom">
	<input type="text" name="prenom" value="${fill_prenom}" placeholder="Prenom">
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
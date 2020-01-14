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
	<h2>Gestion de classes</h2>
	
	<form action="${ fill_id==-1 ? 'commitClasseEdit' : '../commitClasseEdit' }" method="post">
	<input type="text" name="id" value="${fill_id}" hidden="true" readonly>
	<input type="text" name="nom" value="${fill_nom}" placeholder="Nom">
	<select name="prof">
		<c:forEach items="${ profs }" var="p">
		
			<c:choose>
			    <c:when test="${p.nom == fill_prof}">
					<option value="${p.id}" selected>
			    </c:when>    
			    <c:otherwise>
					<option value="${p.id}">
			    </c:otherwise>
			</c:choose>
			<%-- <option ...> print par le choose --%>
				<c:out value="${p.nom}"/>
			</option>
		</c:forEach>
	</select>
	<button type="submit">Valider</button>
	</form>
	
</body>
</html>
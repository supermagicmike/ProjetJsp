<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--
    La servlet fait : session.setAttribute("customer", customer)
    La JSP récupère cette valeur dans ${customer}
--%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>You are connected</title>
	</head>
	<body>
		<h1>Bienvenue ${userName}</h1>
                <c:forEach var="purshase" items="${purshaseList}">
                    <tr>
                            <td>${purshase.orderNum}</td>
                            <td>${purshase.productId}</td>
                            <td>${purshase.quantity}</td>
                            <td>${purshase.shippingCost}</td>
                            <td>${purshase.salesDate}</td>
                            <td>${purshase.description}</td>
                    </tr>	  		    
                </c:forEach>
		Vous avez maintenant accès aux fichiers dans le répertoire 
		"<a href="<c:url value="protected/protectedPage2.html"/>">protected</a>".<br>

		<form action="<c:url value="/"/>" method="POST"> 
			<input type='submit' name='action' value='logout'>
		</form>
		<hr/>
		<h3>Il y a actuellement ${applicationScope.numberConnected} utilisateurs connectés</h3>
	</body>
</html>

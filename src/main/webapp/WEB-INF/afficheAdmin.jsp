<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        Vous avez maintenant accès aux fichiers dans le répertoire admin
        "<a href="<c:url value="protected/admin.jsp"/>">protected</a>".<br>

        <form action="<c:url value="/"/>" method="POST"> 
            <input type='submit' name='action' value='logout'>
        </form>
        <hr/>
        <h3>Il y a actuellement ${applicationScope.numberConnected} utilisateurs connectés</h3>

        <table>
            <th>Product_Code</th>
            <th>CA</th>
                <c:forEach items="${ affaires }" var="affaire" varStatus="status">
                <tr>
                    <td>${affaire.key}</td>
                    <td>${affaire.value}</td>
                </tr>
            </c:forEach>
         </table>

            <form method="post" action="<c:url value="AdminController"/>">
                <p>
                    <input type="text" name="dateDeb"/>
                    <input type="text" name="dateFin"/>

                    <input type="submit" name="action" value="DATE"/>

                </p>
            </form>
                <p>Chiffre affaire par année : ${ CA }</p>
                
            <form method="post" action="<c:url value="AdminController"/>">
                <p>
                    <!-- une balise select ou input ne peut pas être imbriquée directement dans form -->
                    <input type="text" name="Deb"/>
                    <input type="text" name="Fin"/>

                    <input type="submit" name="action" value="CUSTO"/>

                </p>
            </form>
                <c:forEach items="${ CAClient }" var="ca" varStatus="status">
                <tr>
                    <td>${ca.key}</td>
                    <td>${ca.value}</td>
                </tr>
            </c:forEach>

       
    </body>
</html>

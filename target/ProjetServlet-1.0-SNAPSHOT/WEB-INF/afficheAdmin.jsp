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
            <form method="post" action="<c:url value="AdminController"/>">
                <p>
                    <input type="text" name="produitDeb"/>
                    <input type="text" name="produitFin"/>
                    <input type="submit" name="action" value="PRODUCT"/>
                </p>
            </form>
            <th>CA_produit</th>
                <c:forEach items="${ CAProduit }" var="produit" varStatus="status">
                <tr>
                    <td>${produit.key}</td>
                    <td>${produit.value}</td>
                </tr>
            </c:forEach>
         </table>

            <form method="post" action="<c:url value="AdminController"/>">
                <p>
                    <input type="text" name="etatDeb"/>
                    <input type="text" name="etatFin"/>

                    <input type="submit" name="action" value="ETAT"/>

                </p>
            </form>
            <c:forEach items="${ CAEtat }" var="etat" varStatus="status">
                <tr>
                    <td>${etat.key}</td>
                    <td>${etat.value}</td>
                </tr>
            </c:forEach>
                
            <form method="post" action="<c:url value="AdminController"/>">
                <p>
                    <!-- une balise select ou input ne peut pas être imbriquée directement dans form -->
                    <input type="text" name="custoDeb"/>
                    <input type="text" name="custoFin"/>

                    <input type="submit" name="action" value="CUSTO"/>

                </p>
            </form>
                <c:forEach items="${ CAClient }" var="ca" varStatus="status">
                <tr>
                    <td>${ca.key}</td>
                    <td>${ca.value}</td>
                </tr>
            </c:forEach>
            <form method="post" action="<c:url value="AdminController"/>">
                <p>
                    <input type="submit" name="action" value="admin_product"/>
                </p>
                                <p>
                    <input type="submit" name="action" value="admincust"/>
                </p>
            </form>

       <!--mettre script c for each mleh pour boucler et mettre dans tableau js merci chatoune -->
    </body>
</html>

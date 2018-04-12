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
        <form action="<c:url value="LoginController"/>" method="POST"> 
            <input type='submit' name='action' value='logout'>
        </form>
        <table>
            <tr>
                <th>OrderNum</th>
                <th>productId</th> 
                <th>shippingCost</th>
            </tr>
            
            <c:forEach items="${ purchases }" var="purchase" varStatus="status">
            <tr>
                <td>${purchase.orderNum}</td>
                <td>${purchase.productId}</td> 
                <td>${purchase.shippingCost}</td>
                <td><a href="CustomerController?action=DELETE&code=${purchase.orderNum}">delete</a></td>
            </tr>
            </c:forEach>
            <tr>
            <form method="post"  action="<c:url value="CustomerController"/>">
                    <table>
                    <td><input type="text" name="orderNum" /></td>
                    <td><input type="text" name="productId" /></td>
                    <td><input type="text" name="quantity" /></td>
                    <td><input type="text" name="salesDate" /></td>
                    <input type='submit' name='action' value='ADD'>
                    </table>
                </form>
            </tr>
        </table>
    </body>
</html>

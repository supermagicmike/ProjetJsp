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
                <th>Product</th>
                <th>Quantity</th>
                <th>Freight Company</th>
                <th>Order Num</th>
                <th>Product Id</th> 
                <th>Sales Date</th>
                <th>Shipping Date</th>                
                <th>Shipping Cost</th>
                <th>Total Price</th>
            </tr>
            
            <c:forEach items="${ purchases }" var="purchase" varStatus="status">
            <tr>
                <td>${purchase.description}</td>
                <td>${purchase.quantity}</td>
                <td>${purchase.freightCompany}</td>
                <td>${purchase.orderNum}</td>                
                <td>${purchase.productId}</td>                
                <td>${purchase.salesDate}</td>
                <td>${purchase.shippingDate}</td>               
                <td>${purchase.shippingCost}</td>
                <td>${purchase.totalCost}</td>
                <td><a href="CustomerController?action=DELETE&code=${purchase.orderNum}">delete</a></td>
            </tr>
            </c:forEach>
            <tr>
                <form method='GET' action="<c:url value="CustomerController" />">
                    <td><input type="text" name="OrderNum"/></td>
                    <td><input type="text" name="ProdductId"/></td>
                    <td><input type="text" name="ShippingCost"/></td>
                    <td><input type="text" name="Quantity"/></td>
                    <td><input type="text" name="freightCompany"/></td>
                    <td><input type="hidden" name="action" value="ADD"><input type="submit" name="submit" value="test"></td>
                </form>
            </tr>
        </table>
    </body>
</html>

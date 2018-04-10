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
                <form method='POST' action="<c:url value="CustomerController" />">
                    <td>
                        <select name="Description">
                            <c:forEach var="d" items="${Descritpions}"  varStatus="status">
                                <option value="${d}">${d}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" name="Quantity"/></td>
                    <td>
                        <select name="Companies">
                            <c:forEach var="c" items="${Companies}"  varStatus="status">
                                <option value="${c}">${c}</option>
                            </c:forEach>
                        </select>
                    </td>  
                    <td>
                        <select name="Companies">
                            <option value="0">Free delivery</option>
                            <option value="5">Normal delivery</option>
                            <option value="15">Fast delivery</option>
                        </select>
                    </td>
                    <td><input type="hidden" name="action" value="ADD"><input type="submit" name="submit" value="Ajouter"></td>
                </form>
            </tr>
        </table>
    </body>
</html>

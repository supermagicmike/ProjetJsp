<%-- 
    Document   : customer
    Created on : 16-Apr-2018, 1:44:00 PM
    Author     : micka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
        <link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

        <title>Paper Dashboard by Creative Tim</title>

        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
        <meta name="viewport" content="width=device-width" />


        <!-- Bootstrap core CSS     -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />

        <!-- Animation library for notifications   -->
        <link href="assets/css/animate.min.css" rel="stylesheet"/>

        <!--  Paper Dashboard core CSS    -->
        <link href="assets/css/paper-dashboard.css" rel="stylesheet"/>

        <!--  CSS for Demo Purpose, don't include it in your project     -->
        <link href="assets/css/demo.css" rel="stylesheet" />

        <!--  Fonts and icons     -->
        <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
        <link href="assets/css/themify-icons.css" rel="stylesheet">

    </head>
    <body>

        <div class="wrapper">
            <div class="sidebar" data-background-color="white" data-active-color="danger">

                <!--
                            Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
                            Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
                -->

                <div class="sidebar-wrapper">
                    <div class="logo">
                        <a href="http://www.creative-tim.com" class="simple-text">
                             ${userName}
                        </a>
                    </div>

                    <ul class="nav">
                        <li class="active">
                            <a href="dashboard.html">
                                <i class="ti-view-list-alt"></i>
                                <p>Commandes</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="main-panel">
                <nav class="navbar navbar-default">
                    <div class="container-fluid">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar bar1"></span>
                                <span class="icon-bar bar2"></span>
                                <span class="icon-bar bar3"></span>
                            </button>
                            <a class="navbar-brand" href="#">User Profile</a>
                        </div>
                        <div class="collapse navbar-collapse">
                            <ul class="nav navbar-nav navbar-right">
                                <li>
                                    <a href="LoginController?action=logout">
                                        <i class="ti-user"></i>
                                        <p>Log out</p>
                                    </a>
                                </li>
                            </ul>

                        </div>
                    </div>
                </nav>


                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-4 col-md-3"></div>
                            <div class="col-lg-4 col-md-6">
                                <div class="card card-user">
                                    <div class="image">
                                        <img src="assets/img/background.jpg" alt="..."/>
                                    </div>
                                    <div class="content">
                                        <div class="author">
                                            <img class="avatar border-white" src="assets/img/faces/face-2.jpg" alt="..."/>
                                            <h4 class="title"> ${userName}<br />
                                                <a href="#"><small>@ ${userName}</small></a>
                                            </h4>
                                        </div>
                                        <p class="description text-center">
                                            "I like the way you work it <br>
                                            No diggity <br>
                                            I wanna bag it up"
                                        </p>
                                    </div>
                                    <hr>
                                    <div class="text-center">
                                        <div class="row">
                                            <div class="col-md-3 col-md-offset-1">
                                                <h5>12<br /><small>Files</small></h5>
                                            </div>
                                            <div class="col-md-4">
                                                <h5>2GB<br /><small>Used</small></h5>
                                            </div>
                                            <div class="col-md-3">
                                                <h5>24,6$<br /><small>Spent</small></h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4 col-md-3"></div>
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="header">
                                        <h4 class="title">Striped Table</h4>
                                        <p class="category">Here is a subtitle for this table</p>
                                    </div>
                                    <div class="content table-responsive table-full-width">
                                        <table class="table table-striped">
                                            <thead>
                                            <th>Product</th>
                                            <th>Quantity</th>
                                            <th>Freight Company</th>
                                            <th>Shipping Cost</th>
                                            <th>Order Num</th>
                                            <th>Product Id</th> 
                                            <th>Sales Date</th>
                                            <th>Shipping Date</th>                
                                            <th>Total Price</th>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${ purchases }" var="purchase" varStatus="status">
                                                    <tr>
                                                        <c:if test="${(first_time == true) || (edit == false) || ((edit == true) && (numero_edit != purchase.orderNum))}">
                                                            <td>${purchase.description}</td>
                                                            <td>${purchase.quantity}</td>
                                                            <td>${purchase.freightCompany}</td>
                                                            <td>${purchase.shippingCost}</td>
                                                            <td>${purchase.orderNum}</td>                
                                                            <td>${purchase.productId}</td>                
                                                            <td>${purchase.salesDate}</td>
                                                            <td>${purchase.shippingDate}</td>               
                                                            <td>${purchase.totalCost}</td>
                                                            <td><a href="CustomerController?action=EDIT&code=${purchase.orderNum}">edit</a></td>
                                                            <td><a href="CustomerController?action=DELETE&code=${purchase.orderNum}">delete</a></td>    
                                                        </c:if>

                                                        <c:if test="${(edit == true) && (numero_edit == purchase.orderNum)}">
                                                    <form method='POST' action="<c:url value="CustomerController" />">
                                                        <td>${purchase.description}</td>
                                                        <td><input type="text" name="editQuantity" value="${purchase.quantity}" required/></td>
                                                        <td>
                                                            <select name="editFreightCompany">
                                                                <c:forEach var="c" items="${Companies}"  varStatus="status">
                                                                    <option value="${c}">${c}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>  
                                                        <td>
                                                            <select name="editShippingCost">
                                                                <option value="0">Free delivery</option>
                                                                <option value="5">Normal delivery</option>
                                                                <option value="15">Fast delivery</option>
                                                            </select>
                                                        </td>
                                                        <td>${purchase.orderNum}<input type="hidden" name="num_edit" value="${purchase.orderNum}"></td>              
                                                        <td>${purchase.productId}</td>                
                                                        <td>${purchase.salesDate}</td>
                                                        <td>${purchase.shippingDate}</td>               
                                                        <td>${purchase.totalCost}</td>
                                                        <td><input type="hidden" name="action" value="VALIDEDIT"><input type="submit" name="submit" value="valid"></td>
                                                        <td><a href="CustomerController?action=ANNUL&code=${purchase.orderNum}">annul</a></td>
                                                        <td><a href="CustomerController?action=DELETE&code=${purchase.orderNum}">delete</a></td>  
                                                    </form>
                                                </c:if>
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
                                                <td><input type="text" name="Quantity" required/></td>
                                                <td>
                                                    <select name="freightCompany">
                                                        <c:forEach var="c" items="${Companies}"  varStatus="status">
                                                            <option value="${c}">${c}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>  
                                                <td>
                                                    <select name="ShippingCost">
                                                        <option value="0">Free delivery</option>
                                                        <option value="5">Normal delivery</option>
                                                        <option value="15">Fast delivery</option>
                                                    </select>
                                                </td>
                                                <td><input type="hidden" name="action" value="ADD"><input type="submit" name="submit" value="Ajouter"></td>
                                            </form>
                                            </tr>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <footer class="footer">
                    <div class="container-fluid">
                        <nav class="pull-left">

                        </nav>
                        <div class="copyright pull-right">
                            &copy; <script>document.write(new Date().getFullYear())</script>, made with <i class="fa fa-heart heart"></i> by <a href="http://www.creative-tim.com">Creative Tim</a>
                        </div>
                    </div>
                </footer>

            </div>
        </div>


    </body>

    <!--   Core JS Files   -->
    <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

    <!--  Checkbox, Radio & Switch Plugins -->
    <script src="assets/js/bootstrap-checkbox-radio.js"></script>

    <!--  Charts Plugin -->
    <script src="assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="assets/js/bootstrap-notify.js"></script>

    <!--  Google Maps Plugin    -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
    <script src="assets/js/paper-dashboard.js"></script>

    <!-- Paper Dashboard DEMO methods, don't include it in your project! -->
    <script src="assets/js/demo.js"></script>
</html>

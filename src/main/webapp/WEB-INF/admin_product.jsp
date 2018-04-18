<%-- 
    Document   : admincust
    Created on : 16-Apr-2018, 3:23:55 PM
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
        <link href="css/jquery-ui.min.css" rel="stylesheet" />


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
                        <a href="" class="simple-text">
                            ${userName}
                        </a>
                    </div>

                    <ul class="nav">
                        <li class="active">
                            <a href="AdminController?action=admin_product">
                                <i class="ti-view-list-alt"></i>
                                <p>Produits</p>
                            </a>
                        </li>
                        <li>
                            <a href="AdminController?action=admin_state">
                                <i class="ti-map"></i>
                                <p>Etats</p>
                            </a>
                        </li>
                        <li>
                            <a href="AdminController?action=admin_customer">
                                <i class="ti-user"></i>
                                <p>Customers</p>
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
                            <a class="navbar-brand" href="#">Dashboard</a>
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
                        </div>
                        <div class="row">

                            <div class="col-md-12">
                                <div class="card">
                                    <div class="header">
                                        <h4 class="title">Produits</h4>
                                        <p class="category">CA par Produits</p>
                                    </div>
                                    <div class="row">
                                        <div class ="col-md-4"></div>
                                        <div class ="col-md-4">
                                    <form method="post" action="<c:url value="AdminController"/>">

                                        <span data-feather="calendar"></span>
                                        <input type="text" id="debut"name="produitDeb" class="form-control" placeholder="date debut"></input>


                                        <span data-feather="calendar"></span>
                                        <input type="text" id="fin" name="produitFin" class="form-control" placeholder="date fin"></input>

                                        <input type="submit" name="action" value="PRODUCT" class="btn btn-primary btn-sm"></input>

                                    </form>
                                     </div>
                                        <div class ="col-md-4"></div>
                                    <div class="content">
                                        <canvas class="my-4" id="myChart" width="900" height="380"></canvas>
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
                            &copy; <script>document.write(new Date().getFullYear())</script>, made with <i class="fa fa-heart heart"></i> by Billy, Théo, Micka </a>
                        </div>
                    </div>
                </footer>

            </div>
        </div>


    </body>

    <!--   Core JS Files   -->
    <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/jquery-ui.min.js"></script>
    <script src="js/custom.js"></script>

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

   <!-- <script type="text/javascript">
                                $(document).ready(function () {

                                    demo.initChartist();

                                    $.notify({
                                        icon: 'ti-gift',
                                        message: "Bonjour <b>Superuser</b> - accès aux CA ."

                                    }, {
                                        type: 'success',
                                        timer: 4000
                                    });

                                });
    </script>-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
    <script>
                                var key = [];
                                var value = [];
        <c:forEach items="${ CAProduit}" var="produit" varStatus="status">
                                key.push("${produit.key}");
                                value.push(${produit.value});

        </c:forEach>

                                var ctx = document.getElementById("myChart");
                                var myChart = new Chart(ctx, {
                                    type: 'line',
                                    data: {
                                        labels: key,
                                        datasets: [{
                                                data: value,
                                                lineTension: 0,
                                                backgroundColor: 'transparent',
                                                borderColor: '#007bff',
                                                borderWidth: 4,
                                                pointBackgroundColor: '#007bff'
                                            }]
                                    },
                                    options: {
                                        scales: {
                                            yAxes: [{
                                                    ticks: {
                                                        beginAtZero: false
                                                    }
                                                }]
                                        },
                                        legend: {
                                            display: false,
                                        }
                                    }
                                });
    </script>
</html>

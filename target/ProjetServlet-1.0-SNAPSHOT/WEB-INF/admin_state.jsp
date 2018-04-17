<%-- 
    Document   : admin_state
    Created on : 12-Apr-2018, 5:05:30 AM
    Author     : micka
--%>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="">

    <title>Admin Page</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/jquery-ui.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">
  </head>

  <body>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">Admin</a>
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="LoginController?action=logout">Logout</a>
        </li>
      </ul>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link" href="AdminController?action=admin_product">
                  <span data-feather="shopping-cart"></span>
                  Products
                </a>
              </li>
			  <li class="nav-item">
                <a class="nav-link active" href="AdminController?action=admin_state">
                  <span data-feather="file"></span>
                  Etat
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="AdminController?action=admin_customer">
                  <span data-feather="users"></span>
                  Customers
                </a>
              </li>
            </ul>
          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
            <h1 class="h2">Dashboard</h1>
            <div class="btn-toolbar mb-2 mb-md-0">
              <div class="btn-group mr-2">
              </div>
                <form method="post" action="<c:url value="AdminController"/>">
              
                <span data-feather="calendar"></span>
				<input type="text" id="debut"name="etatDeb"></input>
      
			  
			     <span data-feather="calendar"></span>
			<input type="text" id="fin" name="etatFin"></input>
                        
                        <input type="submit" name="action" value="ETAT"></input>
             
		</form>	  
            </div>
          </div>
          <canvas class="my-4" id="myChart" width="900" height="380"></canvas>
        </main>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script>
	</script>
    <script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/custom.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- Icons -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>

    <!-- Graphs -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.1/Chart.min.js"></script>
    <script>
      var key =[];
      var value=[];
      <c:forEach items="${ CAEtat }" var="etat" varStatus="status">
      key.push("${etat.key}");
      value.push(${etat.value});

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
  </body>
</html>


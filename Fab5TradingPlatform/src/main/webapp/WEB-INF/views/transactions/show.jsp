<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">

	<title>

	</title>

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

	<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'>

	<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'>
	</script>

	<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'>
	</script>
	<link rel="stylesheet" href="./fontawesome/css/all.css">

	<style>
		body {
			background-image: url("9.jpeg");
			background-repeat: no-repeat;
			background-size: cover;
			background-position: center;
			font-weight: bold;
			margin: 20px;
		}

		#topheader .navbar-nav li>a {
			text-transform: capitalize;
			color: black;
			-webkit-transition: background-color .2s, color .2s;
			transition: background-color .2s, color .2s;
		}

		#topheader .navbar-nav li>a:hover,
		#topheader .navbar-nav li>a:focus {

			background-color: hsl(180, 88%, 29%);
			color: white;
			transition: 0.6s ease;
		}

		#topheader .navbar-nav li.active>a {
			background-color: #045858;
			color: white;
		}
		
		table,
		td,
		th {
			text-align: center;
			
			margin-left: auto;
			margin-right: auto;
		}
		
		.table1 {
			border-collapse: collapse;
			width: 90%;
			
			text-align: center;
			margin-left: auto;
			margin-right: auto;
			padding: 50px;
		}
		
		.table1 th,
		.table1 td {
			text-align: center;
			
			margin-left: auto;
			margin-right: auto;
			
		}
		
		.table2{
			border-collapse: collapse;
			width: 40%;
			border: 2px solid black;
			text-align: center;
			margin-left: auto;
			margin-right: auto;
			padding: 50px;
		}
		
		#table3{
			border-collapse: collapse;
			width: 90%;
			border: 2px solid black;
			text-align: center;
			margin-left: auto;
			margin-right: auto;
			padding: 50px;
		}
		
		#table3 th{
			background-color: rgb(222, 242, 248);
		}
		
		#table3 th, td{
			border: 2px solid black;
			padding: 4px;
		}
		
		caption {
			background-color: rgb(222, 242, 248);
			border-left: 2px solid black;
			border-right: 2px solid black;
			border-top: 2px solid black;
			text-align: center;

		}
		
	</style>
</head>

<body>
	<div id="topheader">
		<div class="container-fluid">
			<nav class="navbar navbar-expand-lg bg-info">

				<div class="navbar-header">
					<a class="navbar-brand" href="#" style="color: rgb(1, 49, 43);">FAB5 Trading</a>
				</div>

				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right" class="fa-ul">
						<li><a href="/"><i class="fas fa-home"></i> HOME</a></li>
						<li><a href="/trade/search?userId=${requestScope.userId}"><i class="fas fa-shopping-cart"></i> BUY/SELL</a></li>
						<li class="active"><a href="/transactions/show?userId=${requestScope.userId}"><i class="fas fa-shopping-cart"></i> ORDER HISTORY</a></li> 
						<li><a href="/portfolio/display?userId=${requestScope.userId}"><i class="fas fa-chart-line"></i> PORTFOLIO</a></li>
						<li><a href="/user/logout"><i class="fa fa-user" aria-hidden="true"></i> LOGOUT</a></li>
					</ul>
				</div>
		</div>
		</nav>

	</div>

	<script>
		$('#topheader .navbar-nav a').on('click',
			function () {
				$('#topheader .navbar-nav').find('li.active')
					.removeClass('active');
				$(this).parent('li').addClass('active');
			}); 
	</script>
	<br>
	<br>
	<div>
    <center><h1><p>Transactions</p></h1></center>
	<br>
	<br>
		
	<table id="table3">
		<tr>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;Symbol&nbsp;&nbsp;&nbsp;&nbsp;</th>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Order Date&nbsp;&nbsp;&nbsp;&nbsp;</th>
			<th>&nbsp;&nbsp;&nbsp;Order Time&nbsp;&nbsp;&nbsp;</th>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;Shares&nbsp;&nbsp;&nbsp;&nbsp;</th>
			<th>&nbsp;&nbsp;&nbsp;&nbsp;Price&nbsp;&nbsp;&nbsp;&nbsp;</th>
			<th>&nbsp;Total Cost&nbsp;</th>
			<th>&nbsp;&nbsp;&nbsp;Order Type&nbsp;&nbsp;&nbsp;</th>
		</tr>
		<c:forEach var="order" items="${requestScope.orders}">
		<tr>
			<td>${order.apiUniqueName}</td>
			<td>${order.company}</td>
			<td>${order.orderDate}</td>
			<td>${order.orderTime}</td>
			<td>${order.quantity}</td>
			<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value = "${order.price}" /></td>
			<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value = "${order.cost}" /></td>
			<td>${order.type}</td>
		</tr>
		</c:forEach>
	</table>

	
</div>
</body>

</html>
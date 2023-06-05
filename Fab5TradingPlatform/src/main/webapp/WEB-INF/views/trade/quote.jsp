<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	<style>
	    <link rel="stylesheet" href= "https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css"> 
      
    <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css'> 
      
    <script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js'> 
    </script> 
      
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js'> 
    </script> 
    <link href="<c:url value="/css/all.css" />" rel="stylesheet">

	<style>

body {
			background-image : url("/images/9.jpeg");
			background-repeat: no-repeat;
			background-size: cover;
			background-position: center;
            font-weight: bold;
            margin: 20px; 
        } 
        #topheader .navbar-nav li > a { 
            text-transform: capitalize; 
            color :black; 
            -webkit-transition: background-color .2s, color .2s; 
            transition: background-color .2s, color .2s; 
        } 
        #topheader .navbar-nav li > a:hover,  
        #topheader .navbar-nav li > a:focus {
             
            background-color:hsl(180, 88%, 29%); 
            color: white; 
            transition: 0.6s ease;
        } 
        #topheader .navbar-nav li.active > a { 
            background-color: #045858; 
            color: white; 
        }
	
		table,
		td,
		th {
			border: 2px solid black;
		}

		#table1 {
			background-color: rgb(222,242,248);
			border-collapse: collapse;
			width: 50%;
			text-align: center;
			margin-left: auto;
			margin-right: auto;
		}

		#table1 tr,
		#table1 th {
			padding: 50px;
		}

		#table2 {
			background-color: white;
			border-collapse: collapse;
			width: 60%;
			text-align: center;
			margin-left: auto;
			margin-right: auto;
		}

		#table3 {
			background-color: white;
			border-collapse: collapse;
			width: 60%;
			margin-left: auto;
			margin-right: auto;
		}

		#div1 {
			text-align: center;
			margin-left: auto;
			margin-right: auto;
		}
		
			#table4 {
			background-color: rgb(222,242,248);
			border-collapse: collapse;
			width: 50%;
			text-align: center;
			margin-left: auto;
			margin-right: auto;
		}
		#table4 tr,
		#table4 td {
			border-color: rgb(222,242,248);
		}
	</style>
</head>

<body>
<div id="topheader"> 
      <div class="container-fluid">       
        <nav class="navbar navbar-expand-lg bg-info"> 
            
             <div class="navbar-header"> 
                    <a class="navbar-brand" href="#"
                    style="color: rgb(1, 49, 43);">FAB5 Trading</a>   
                </div> 
                  
                <div class="collapse navbar-collapse"
                        id="bs-example-navbar-collapse-1"> 
                    <ul class="nav navbar-nav navbar-right" class="fa-ul">  
                      <li><a href="/"><i class="fas fa-home"></i> HOME</a></li> 
                      <li class="active"><a href="/trade/search?userId=${requestScope.userId}"><i class="fas fa-shopping-cart"></i> BUY/SELL</a></li> 
                      <li><a href="#page3"><i class="fas fa-chart-line"></i> PORTFOLIO</a></li>
                      <li><a href="/user/login"><i class="fa fa-user" aria-hidden="true"></i> LOG OUT</a></li>
                      </ul>   
                </div> 
            </div> 
        </nav> 
      
    </div> 
  
    <script> 
        $( '#topheader .navbar-nav a' ).on('click',  
                    function () { 
            $( '#topheader .navbar-nav' ).find( 'li.active' ) 
            .removeClass( 'active' ); 
            $( this ).parent( 'li' ).addClass( 'active' ); 
        }); 
    </script>

	<br>
	<table id="table1">
		<tr>
			<th>
				<div class="panel panel-default">
					<div class="panel-body " align="center">${requestScope.orderquote.company.apiUniqueName}</div>
				</div>
				<br>
				<br>
				<table id="table2">
					<tr>
						<td>&nbsp;&nbsp; Bid &nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;Orders</td>
						<td>&nbsp;&nbsp; Qty &nbsp;&nbsp;&nbsp;</td>
						<td>&nbsp;&nbsp;Offer&nbsp;</td>
						<td>&nbsp;Orders&nbsp;</td>
						<td>&nbsp;&nbsp;Qty &nbsp;&nbsp;&nbsp;</td>
					</tr>
					<tr>
						<td colspan=3>
						<c:forEach var="bid" items="${requestScope.orderquote.depth.buy}">
						<table id="table2" width="100%">
						<tr>
						<td>
							<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${bid.price}" />
						</td>
						<td>${bid.orders}</td>
						<td>${bid.quantity}</td>
						</tr>
						</table>
						</c:forEach>
						</td>

						<td colspan=3>
						<c:forEach var="offer" items="${requestScope.orderquote.depth.sell}">
						<table id="table2" width="100%">
						<tr>
						<td>
							<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${offer.price}" />
						</td>
						<td>${offer.orders}</td>
						<td>${offer.quantity}</td>
						</tr>
						</table>
						</c:forEach>
						</td>
						</tr>
				</table>
				<br>
				<br>
				<table id="table3">
					<tr>
						<td>&nbsp;Open</td>
						<td>&nbsp;
							<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${requestScope.orderquote.ohlc.open}" />
						</td>
						<td>&nbsp;High</td>
						<td>&nbsp;
							<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${requestScope.orderquote.ohlc.high}" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;Low</td>
						<td>&nbsp;
							<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${requestScope.orderquote.ohlc.low}" />
						</td>
						<td>&nbsp;Prev Close</td>
						<td>&nbsp;
							<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${requestScope.orderquote.ohlc.close}" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;Volume</td>
						<td>&nbsp;
							<fmt:formatNumber maxFractionDigits="0" value="${requestScope.orderquote.volumeTradedToday}" />
						</td>
						<td>&nbsp;Avg Price</td>
						<td>&nbsp;
							<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${requestScope.orderquote.averagePrice}" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;LTQ</td>
						<td>&nbsp;
							<fmt:formatNumber maxFractionDigits="0" value="${requestScope.orderquote.lastTradedQuantity}" />
						</td>
						<td>&nbsp;LTT</td>
						<td>&nbsp;
						<fmt:formatDate pattern = "dd-MM-yyyy HH:mm:ss" value = "${requestScope.orderquote.lastTradedTime}" />
						</td>
					</tr>
					<tr>
						<td>&nbsp;Lower Circuit</td>
						<td>&nbsp;
							<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${requestScope.orderquote.lowerCircuitLimit}" />
						</td>
						<td>&nbsp;Upper Circuit</td>
						<td>&nbsp;
							<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${requestScope.orderquote.upperCircuitLimit}" />
						</td>
					</tr>
				</table>
				<br>
				<br>
				<table id="table4">
					<tr>
					<td>
					<form method="post" action="/trade/buyquote">
						<input type="hidden" name="id" value="${requestScope.id}">
						<input type="hidden" name="userId" value="${requestScope.userId}">
						<input type="hidden" name="apiUniqueName" value="${requestScope.orderquote.company.apiUniqueName}">
						<input type="hidden" name="price" value="${requestScope.orderquote.lastPrice}">
						<!--<input type="submit" value="Buy" >-->
						<button type="button" class="btn btn-default" onclick="this.form.submit();">Buy</button>
					</form>
					</td>
					<td>
					<form method="post" action="/trade/sellquote">
						<input type="hidden" name="id" value="${requestScope.id}">
						<input type="hidden" name="userId" value="${requestScope.userId}">
						<input type="hidden" name="apiUniqueName" value="${requestScope.orderquote.company.apiUniqueName}">
						<input type="hidden" name="price" value="${requestScope.orderquote.lastPrice}">
						<!--<input type="submit" value="Sell" >-->
						<button type="button" class="btn btn-default" onclick="this.form.submit();">Sell</button>
					</form>
					</td>
					</tr>
				</table>

	</th>
	</tr>
	</table>
</body>

</html>
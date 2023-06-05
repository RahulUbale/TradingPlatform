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
			text-align: center;

		}

		table {
			border-collapse: collapse;
			width: 50%;
			text-align: center;
			margin-left: auto;
			margin-right: auto;
		}

		table th,
		table td {
			padding: 20px;
		}

		#div1 {
			background-color: rgb(222,242,248);
			border: 2px solid black;
			width: 50%;
			text-align: center;
			margin-left: auto;
			margin-right: auto;
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
	<div id="div1">
		<br>
		<br>
		<br>
		<form action="/trade/sell" method="post">
		<input type="hidden" name="id" value="${requestScope.id}">
		<input type="hidden" name="userId" value="${requestScope.userId}">
		<input type="hidden" name="company" value="${requestScope.orderquote.company.name}">
		<input type="hidden" name="ticker" value="${requestScope.orderquote.company.ticker}">
		<input type="hidden" name="apiUniqueName" value="${requestScope.orderquote.company.apiUniqueName}">		
		<table>
			<tr>
				<th colspan="2">
					${requestScope.response}
				</th>
			</tr>
			<tr>
				<th>
					${requestScope.orderquote.company.apiUniqueName}
				</th>
				<th>
					<fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${requestScope.orderquote.lastPrice}" />
				</th>
			</tr>
			<tr>
				<td>
					Quantity
				</td>
				<td>
					<input type="text" name="quantity">
				</td>
			</tr>
			<tr>
				<td>
					Price
				</td>
				<td>
					<input type="number"name="price" >
				</td>
			</tr>
			<tr>
				<td>
					<button type="button" class="btn btn-default">Cancel</button>
				</td>
				<td>
					<button type="button" class="btn btn-default" onclick="this.form.submit();">Sell</button>
				</td>
			</tr>
		</table>
		</form>
		<br>
		<br>
		<br>
	</div>
</body>
</html>
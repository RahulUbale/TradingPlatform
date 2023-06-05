<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html> 
<html lang="en" > 
<head> 
    <meta charset="UTF-8"> 
      
    <title> 
        
    </title> 
      
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
			background-color: white;
			border-collapse: collapse;
			width: 40%;
			border: 2px solid black;
			text-align: center;
			margin-left: auto;
			margin-right: auto;
		}
		
		caption {
			background-color: rgb(222,242,248);
			border-left: 2px solid black;
			border-right: 2px solid black;
			border-top: 2px solid black;
			text-align:center;
			
		}
		
		.size {
			background-color: rgb(222,242,248);
			width: 50%;
			text-align: center;
			margin-left: auto;
			margin-right: auto;
		}
		
		.news {
			background-color: rgb(222,242,248);
			width: 50%;
			text-align: left;
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
                      <li class="active"><a href="/"><i class="fas fa-home"></i> HOME</a></li> 
                      <li><a href="/trade/search?userId=${requestScope.userId}"><i class="fas fa-shopping-cart"></i> BUY/SELL</a></li> 
                      <li><a href="/transactions/show?userId=${requestScope.userId}"><i class="fas fa-shopping-cart"></i> ORDER HISTORY</a></li> 
                      <li><a href="/portfolio/display?userId=${requestScope.userId}"><i class="fas fa-chart-line"></i> PORTFOLIO</a></li>
                      <li> 
	                      <c:if test="${requestScope.userId != null}">
	                      <a href="/user/logout"><i class="fa fa-user" aria-hidden="true"></i> LOG OUT </a></li></c:if>
	                      <c:if test="${requestScope.userId == null}">
	                      <a href="/user/login"><i class="fa fa-user" aria-hidden="true"></i> SIGN IN | SIGN UP </a></li>
	                      </c:if>
                      
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

	<div>
		<br>
		<table>
				<caption>Today's Market</caption>			
			<tr>
				<th></th>
				<th>Price</th>
				<th>Change</th>
				<th>%Change</th>
			</tr>
			<c:forEach var="quote" items="${requestScope.bean.marketChangeData}">
			<tr>
				<th>${quote.company}</th>
				<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value = "${quote.price}" /></td>
				<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value = "${quote.change}"/></td>
				<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${quote.changePercentage}"/></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<br>
<div>
		<table>
				<caption>Bottom 5 Stocks</caption>
			<tr>
				<th></th>
				<th>Price</th>
				<th>Change</th>
				<th>% Loss</th>
			</tr>
			<c:forEach var="quote" items="${requestScope.bean.movers.bottomFive}">
			<tr>
				<th>${quote.company}</th>
				<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value = "${quote.price}" /></td>
				<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value = "${quote.change}"/></td>
				<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${quote.changePercentage}"/></td>
			</tr>
			</c:forEach>		</table>
</div>
<br>
<br>
<div>
		<table>
				<caption>Top 5 Stocks</caption>
			<tr>
				<th></th>
				<th>Price</th>
				<th>Change</th>
				<th>% Gain</th>
			</tr>
			<c:forEach var="quote" items="${requestScope.bean.movers.topFive}">
			<tr>
				<th>${quote.company}</th>
				<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value = "${quote.price}" /></td>
				<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value = "${quote.change}"/></td>
				<td><fmt:formatNumber maxFractionDigits="2" minFractionDigits="2" value="${quote.changePercentage}"/></td>
			</tr>
			</c:forEach>
		</table>
</div>
	<br>
	<br>	
	<div class="panel panel-default size">
		<div class="panel-body align="center">Latest News</div>
	</div>
	<div class="news">
			<c:forEach var="news" items="${requestScope.bean.newslist}">
				<a href="${news.url}" target="_blank">${news.title}</a>
				<br><br><br>
			</c:forEach>
	</div>
	<br>
	<br>
	<div class="panel panel-default size">
		<div class="panel-body align="center">Youtube Video on Investing</div>		
	</div>
	<div class="size">
			<iframe width="500px" height="350px" src="https://www.youtube.com/embed/p7HKvqRI_Bo" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
	</div>
</body> 
  
</html>
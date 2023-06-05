<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <link href="<c:url value="/resources/css/all.css" />" rel="stylesheet">

	<style>
	
       body {
			background-image : url("/resources/images/9.jpeg");
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
			text-align: center;

		}

		table {
			background-color: rgb(222,242,248);
			border-collapse: collapse;
			width: 50%;
			text-align: center;
			margin-left: auto;
			margin-right: auto;
		}

		table th,
		table td {
			padding: 50px;
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
                      <li><a href="/transactions/show?userId=${requestScope.userId}"><i class="fas fa-shopping-cart"></i> ORDER HISTORY</a></li>  
                      <li><a href="/portfolio/display?userId=${requestScope.userId}"><i class="fas fa-chart-line"></i> PORTFOLIO</a></li>
                      <li><a href="/user/logout"><i class="fa fa-user" aria-hidden="true"></i> LOG OUT</a></li>
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


	<br><br>
	<br>
	<form method="post">
	<input type="hidden" name="userId" value="${requestScope.userId}" />
	<table>
	
		<tr>
			<th>${requestScope.message}
				<input type="search" name="enteredName">
				<button type="button" class="btn btn-default" onclick='this.form.submit();'>
					<span class="glyphicon glyphicon-search"></span>
				</button>
				<br>
				<br>
				<br>
				<h3>Search Company</h3>
			</th>
		</tr>
	</table>
	</form>
</body>
</html>
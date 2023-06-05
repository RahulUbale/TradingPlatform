<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>

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
    </style>


    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        * {
            box-sizing: border-box;
        }

        table,
		td,
		th {
            text-align: center;	
            border: black;
            
        }
        
        
        input[type=text] {
            width: 100%;
            padding: 15px;
            margin: 5px 0 22px 0;
            display: inline-block;
            border: none;
            background: white;
        }


        input[type=text]:focus {
            background-color: #ddd;
            outline: none;
        }


        button {
            background-color: #304ce7;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 150%;
            opacity: 0.9;
        }

        button:hover {
            opacity: 1;
        }

        .signupbtn {
            float: center;
            width: 50%;
        }

        .container {
            padding: 16px;
        }


        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color:white;
            padding-top: 50px;
        }


        .modal-content {
            background-color: rgb(222,242,248);
            margin: 5% auto 5% auto;
            border: 1px solid #888;
            width: 40%;
        }


        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
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
                        <li><a href="#page2"><i class="fas fa-shopping-cart"></i> BUY/SELL</a></li>
                        <li><a href="#page3"><i class="fas fa-chart-line"></i> PORTFOLIO</a></li>
                        <li class="active"><a href="#page5"><i class="fa fa-user" aria-hidden="true"></i> SIGN UP</a></li>
                    </ul>
                </div>
        </div>
        </nav>

    </div>

        <div class="container">
		<form:form modelAttribute="user" method="post" class="modal-content">            
        <table border='0' width='90%' cellpadding='0' cellspacing='0' >
                <tr>
                    <td><label for="name"><b> Name </b></label></td>
                    <td> <form:input path="name"/></td>
                </tr>
                <tr>
                    <td><label for="email"><b> Email </b></label></td>
                    <td><form:input path="email"/></td>
                </tr>
                <tr>
                    <td ><label for="phn"><b> Phone </b></label></td>
                    <td> <form:input path="phone"/></td>
                </tr>

                <tr>
                    <td align="center" colspan="2">

                            <button type="submit" class="signupbtn" onclick="this.form.submit();">Sign Up</button>
                        
                    </td>
                </tr>
		</table>
		</form:form>
    </div>

</body>

</html>
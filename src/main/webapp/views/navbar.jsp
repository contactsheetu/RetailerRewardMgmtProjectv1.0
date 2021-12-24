<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>

	<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
			
	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,400i,500,500i,700&display=swap" rel="stylesheet"> 
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
</head>


	<nav class="navbar navbar-inverse">
		<div class="container">
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/rewardcenter">Home</a></li>
					<li><a href="/rewardcenter/getUserList">Customers</a></li>
					<li class="dropdown">
					<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">Reward Points<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/rewardcenter/getMonthlyRewardPoints">Monthly</a></li>
							<li><a href="/rewardcenter/getAggregateRewardPoints">Aggregate</a></li>							
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>


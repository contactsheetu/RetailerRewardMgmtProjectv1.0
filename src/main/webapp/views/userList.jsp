<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer List</title>

<c:import url="navbar.jsp" />

</head>
<body>

	<div class="container">

		<div class="starter-template top_heading ">
			<h2>Customer Details</h2>
		</div>

	<table class="table table-striped">
		<tr>
			<th>Customer ID</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Date of Joining</th>
			<th>Payment Details</th>
			<th colspan=4 align="center">Quarterly Reward Points</th>			
		</tr>
		<c:forEach var="customer" items="${userList}">
			<tr>
				<td><c:out value="${customer.ID}" /></td>
				<td><c:out value="${customer.firstName}"/></td>
				<td><c:out value="${customer.lastName}"/></td>
				<td><c:out value="${customer.doJ}" /></td>
				<td>
						<form action="getPaymentDetails/${customer.ID}" method="GET">
							<input class="btn" type="submit" value="View Payment Details">
						</form>
				</td>
				<td>
					<form action="getQuarterlyRewardPoints/${customer.ID}/1" method="GET">
						<input class="btn" type="submit" value="Q1, 2019">
					</form>
				</td>
				<td>
					<form action="getQuarterlyRewardPoints/${customer.ID}/2" method="GET">
						<input class="btn" type="submit" value="Q2, 2019">
					</form>
				</td>				
				<td>
					<form action="getQuarterlyRewardPoints/${customer.ID}/3" method="GET">
						<input class="btn" type="submit" value="Q3, 2019">
					</form>
				</td>
				<td>
					<form action="getQuarterlyRewardPoints/${customer.ID}/4" method="GET">
						<input class="btn" type="submit" value="Q4, 2019">
					</form>
				</td>				
			</tr>
		</c:forEach>
	</table>

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


</body>
</html>
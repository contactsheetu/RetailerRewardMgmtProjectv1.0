<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggregate Reward Points</title>
<c:import url="navbar.jsp" />
</head>
<body>
	
<div class="container">
	    <div class="starter-template top_heading">
			<h2>Aggregate Reward Points</h2>
		</div>
      <div class="row">
	    <div class="col-lg-12">
			<table class="table table-bordered rewardTable">
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th class="txt_right">Total Amount</th>
					<th class="txt_right">Total Reward Points</th>
				</tr>
				<c:forEach var="aggregateRewardPoint" items="${aggregateRewardPoints}">
					<tr>
						<td><c:out value="${aggregateRewardPoint.user.firstName}" /></td>
						<td><c:out value="${aggregateRewardPoint.user.lastName}" /></td>
						<td class="txt_right"><c:out value="${aggregateRewardPoint.totalAmount}" /></td>
						<td class="txt_right"><c:out value="${aggregateRewardPoint.totalRewardPoints}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	  </div>
</div>	


 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
				
</body>
</html>
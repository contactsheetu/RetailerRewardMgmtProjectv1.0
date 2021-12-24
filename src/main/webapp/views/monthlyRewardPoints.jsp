<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Monthly Reward Points</title>

<c:import url="navbar.jsp" />
	
</head>
<body>
	<div class="container">
	    <div class="starter-template top_heading ">
			<h2>Monthly Reward Points</h2>
		</div>
      <div class="row">
	    <div class="col-lg-12">
			<table class="table table-bordered monthly_rewardTable">
				<c:forEach var="monthlyRewardPoint" items="${monthlyRewardPoints}">
					<tr>
						<td colspan=3 class="colr_name"><strong>Name: </strong>
						<strong><c:out value="${monthlyRewardPoint.user.lastName}" />, <c:out value="${monthlyRewardPoint.user.firstName}"/></strong></td>				
					</tr>
					<tr>
						<th>Month</th>
						<th class="txt_right">Total Amount</th>
						<th class="txt_right">Reward Points</th>
					</tr>
					<c:forEach var="rewardPoint" items="${monthlyRewardPoint.rewardPoints}">
						<tr>
							<td><c:out value="${rewardPoint.rewardMonth}" /></td>
							<td class="txt_right"><c:out value="${rewardPoint.amount}" /></td>
							<td class="txt_right"><c:out value="${rewardPoint.rewardPoints}" /></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan=3 class="txt_right"><strong>Total: </strong>
						<strong><c:out value="${monthlyRewardPoint.totalRewardPoints}" /></strong></td>							
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
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
			<h2>Quarterly Reward Points</h2>
			<p>Name - <c:out value="${userDetail.firstName}" /> <c:out value="${userDetail.lastName}" /></p>
	        <p>Date of joining - <c:out value="${userDetail.doJ}"/></p>

		</div>
      <div class="row">
	    <div class="col-lg-12">
			<table class="table table-bordered rewardTable">
				<tr>
					<th>Month</th>
					<th class="txt_right">Total Amount</th>
					<th class="txt_right">Reward Points</th>
				</tr>
				<c:forEach var="quarterlyRewardPoint" items="${quarterlyRewardPoints}">
					<tr>
						<td><c:out value="${quarterlyRewardPoint.rewardMonth}" /></td>
						<td class="txt_right"><c:out value="${quarterlyRewardPoint.amount}" /></td>
						<td class="txt_right"><c:out value="${quarterlyRewardPoint.rewardPoints}" /></td>
					</tr>
				</c:forEach>
				<tr><td colspan=3 align="right"><b>Total:&nbsp;&nbsp;&nbsp;</b><c:out value="${totalRewardPoints}" /></td>
			</table>
		</div>
	  </div>
</div>	
	
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</body>
</html>
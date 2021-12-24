<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payment Details</title>
<c:import url="navbar.jsp" />
</head>
<body>

	
<div class="container">
	    <div class="starter-template top_heading ">
			<h2>Payment Details</h2>
			<p>Name - <c:out value="${userDetail.firstName}" /> <c:out value="${userDetail.lastName}" /></p>
	        <p>Date of joining - <c:out value="${userDetail.doJ}"/></p>
		</div>
      <div class="row">
	    <div class="col-lg-12">
			<table class="table table-bordered rewardTable">
				<tr>
					<th>Payment Date</th>
					<th>Payment Description</th>	
					<th class="txt_right">Payment Amount</th>		
				</tr>
				<c:forEach var="payment" items="${paymentDetails}">
					<tr>
						<td><c:out value="${payment.paymentDate}" /></td>
						<td><c:out value="${payment.description}" /></td>
						<td class="txt_right"><c:out value="${payment.amount}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	  </div>
</div>	

</body>
</html>
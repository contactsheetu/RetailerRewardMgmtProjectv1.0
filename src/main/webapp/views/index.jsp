<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">


<c:import url="navbar.jsp" />


<body>

	<div class="container">
      <div class="row">
	    <div class="col-lg-12">
			<div class="starter-template home_top">
				<h1>Spring Boot Web JSP Example</h1>
				<h2>${welcomeMessage}</h2>
			</div>
		</div>
	  </div>
	</div>
	
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome to login JSP file madafucka!</h1>
		<div class="container">
			<div class="loginForm">
				<h3><strong>Login to Tidder</strong></h3>
				<c:choose>
					<c:when test="${not empty error}">
			  			<div class="alert alert-danger">
			 				Your login was unsuccessful. <br /> Caused by:
							${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
					    </div>
					</c:when>
					<c:when test="${not empty param.accCreated}">
						<div class="alert alert-success">
							Your account was created successfully. You can now log in. <br />
						</div>
					</c:when>
					<c:when test="${not empty param.accNotCreated}">
						<div class="alert alert-danger">
							Something went wrong during account creation. Try again. <br />
						</div>
					</c:when>
				</c:choose>
				<div class="credentials">
					<h4>Login:</h4>
					<form action="login" name="f" method="POST">
						<div class="form-group">
							<label for="login">Email:</label>
							<input type="text" class="form-control" id="email" placeholder="Enter email" name="email" >
						</div>
						<div>
							<label for="pwd">Password: </label>
							<input type="password" class="form-control" id="password" placeholder="Enter password" name="password"> 
						</div>
						<button type="submit">Submit</button>
					</form>
				</div>
				<div class="create_account">
					No account yet? <br /> <a href="/tidder/createAccount.html">Create a new one!</a>
				</div>
			</div>
		</div>
</body>
</html>
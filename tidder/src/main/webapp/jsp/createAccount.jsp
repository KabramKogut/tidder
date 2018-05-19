<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Tidder new account</title>
</head>

<body>
	<div class="container">
		<div class="createForm">
			<h3>
				<strong>Create Account</strong>
			</h3>
			<div>
				<c:choose>
					<c:when test="${not empty error}">
						<div class="alert alert-danger">
							Your account creation was unsuccessful. <br /> 
							You already created account with this email.
						</div>
					</c:when>
				</c:choose>
				<form:form modelAttribute="userProfile">
					<table>
						<tr>
							<td>Email:</td>
							<td><form:input path="email" class="form-control" id="email"
									placeholder="Enter email" /></td>
							<td><form:errors path="email" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Password:</td>
							<td><form:input type="password" path="password"
									class="form-control" id="password" placeholder="Enter password" /></td>
							<td><form:errors path="password" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Name:</td>
							<td><form:input path="name" class="form-control" id="name"
									placeholder="Enter name" /></td>
							<td><form:errors path="name" cssClass="error" /></td>
						</tr>
						<tr>
							<td>Lastname:</td>
							<td><form:input path="lastname" class="form-control"
									id="lastname" placeholder="Enter lastname" /></td>
							<td><form:errors path="lastname" cssClass="error" /></td>
						</tr>
					</table>
					<button type="submit">Submit</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Invitation</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrapValidator.js"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
		<script type="text/javascript" src="https://www.google.com/recaptcha/api/challenge?k=6LdlHOsSAAAAAM8ypy8W2KXvgMtY2dFsiQT3HVq-"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/captchaScript.js"></script>
	</head>
	<body>
		<div class="formContainer">
			<div class="formHeader">
				<span>Social</span><span>Survey</span>
			</div>
			<div class="registerStep1Wrapper" id="registrationStep1">
				<div class="formContentHeader">
					Sign Up To Start Your Journey
				</div>
				<form role="form" id="registerForm" method="post" action="./corporateinvite.do">
					<div class="form-group">
						<input id="regStep1FirstName" name="firstName"  class="form-control formInput"  type="text" placeholder="First Name">
					</div>
					<div class="form-group">
						<input id="regStep1LastName" name="lastName" class="form-control formInput" type="text" placeholder="Last Name">
					</div>
					<div class="form-group">
						<input id="regStep1EmailId" name="emailId" class="form-control formInput" type="email" placeholder="Email ID">
					</div>
					<div class="form-group">
        				<div class="col-sm-9 captchaContainer">
            				<div id="recaptcha"></div>
        				</div>
    				</div>
					 <button type="submit" class="formButton">Submit</button>
				</form>
			</div>
		</div>
	</body>
</html>
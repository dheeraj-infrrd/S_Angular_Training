<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="label.resetpassword.key" /></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-common.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-resp.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/rangeslider.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-common-1.1.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style-resp-1.1.css">
</head>

<body>
<div class="hdr-wrapper">
	<div class="container hdr-container clearfix">
		<div class="float-left hdr-logo"></div>
		<div class="float-right clearfix hdr-btns-wrapper">
			<div class="float-left hdr-log-btn hdr-log-reg-btn"><spring:message code="label.signin.key" /></div>
			<div class="float-left hdr-reg-btn hdr-log-reg-btn"><spring:message code="label.joinus.key" /></div>
		</div>
	</div>
</div>
<div class="hm-header-main-wrapper">
	<div class="container">
		<div class="hm-header-row hm-header-row-main clearfix">
			<div class="float-left hm-header-row-left text-center"><spring:message code="label.resetpassword.key" /></div>
		</div>
	</div>
</div>

<div class="prof-main-content-wrapper margin-top-25 margin-bottom-25">
	<div class="container reg_panel_container">
		<div class="reg_header"><spring:message code="label.resetpassword.key" /></div>
		
		<form id="reset-pwd-form" method="POST" action="./setnewpassword.do">
			<div class="reg_form_wrapper_2">
				<div class="reg_form_row clearfix">
					<div class="float-left rfr_lbl"><spring:message code="label.emailid.key"/></div>
					<div class="float-left rfr_txt">
						<div class="rfr_icn icn-mail"></div>
						<input class="rfr_txt_fld" id="login-user-id" data-non-empty="true" data-email="true"
							name="emailId" value="${emailId}" placeholder='<spring:message code="label.emailid.key"/>'>
					</div>
				</div>
				<div class="reg_form_row clearfix">
					<div class="float-left rfr_lbl"><spring:message code="label.password.key" /></div>
					<div class="float-left rfr_txt">
						<div class="rfr_icn icn-password"></div>
						<input type="password" class="rfr_txt_fld" id="login-pwd" data-non-empty="true"
							name="password" placeholder='<spring:message code="label.password.key" />'>
					</div>
					<div class="float-left rfr_txt">
						<div class="rfr_icn icn-confirm-password"></div>
						<input type="password" class="rfr_txt_fld" id="login-cnf-pwd" data-non-empty="true"
							name="confirmPassword" placeholder='<spring:message code="label.confirmpassword.key" />'>
					</div>
				</div>
				
				<div class="reg_form_row clearfix">
					<div class="reg_btn" id="reset-pwd-submit"><spring:message code="label.submit.key" /></div>
				</div>
			</div>
			<input type="hidden" value="${param.q}" name="q">
			<input type="hidden" value="${message}" data-status="${status}" name="message" id="message"/>
		</form>
		
	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script>
var isResetFormValid;
$(document).ready(function(){
	isResetFormValid = false;
	
	if ($('#message').val() != "") {
		if ($('#message').attr('data-status') == 'SUCCESS_MESSAGE') {
			showInfo($('#message').val());
		} else {
			showError($('#message').val());
		}
	}
	
	function submitResetPasswordForm() {
		if(validateResetPasswordForm('reset-pwd-form')){
			console.log("submitting reset password form");
			$('#reset-pwd-form').submit();
		}
	}
	
	$('#reset-pwd-submit').click(function(e){
		submitResetPasswordForm();
	});
	
	$('input').keypress(function(e){
		// detect enter
		if (e.which==13){
			e.preventDefault();
			submitResetPasswordForm();
		}
	});
	
	$('#login-user-id').blur(function() {
		validateEmailId(this.id);
	});
	
	$('#login-pwd').blur(function() {
		validatePassword(this.id);
	});
	
	$('#login-cnf-pwd').blur(function() {
		validateConfirmPassword('login-pwd', this.id);
	});
	
	function validateResetPasswordForm(id) {
		var isFocussed = false;
		isResetFormValid = true;
		var isSmallScreen = false;
		if($(window).width()<768){
			isSmallScreen = true;
		}
		if(!validateEmailId('login-user-id')){
			isResetFormValid = false;
			if(!isFocussed){
				$('#login-user-id').focus();
				isFocussed=true;
			}
			if(isSmallScreen){
				return isResetFormValid;
			}
		}
		if(!validatePassword('login-pwd')){
			isResetFormValid = false;
			if(!isFocussed){
				$('#login-pwd').focus();
				isFocussed=true;
			}
			if(isSmallScreen){
				return isResetFormValid;
			}
		}
		if(!validateConfirmPassword('login-pwd', 'login-cnf-pwd')){
			isResetFormValid = false;
			if(!isFocussed){
				$('#login-cnf-pwd').focus();
				isFocussed=true;
			}
			if(isSmallScreen){
				return isResetFormValid;
			}
		}
		return isResetFormValid;
	}
});
</script>

</body>
</html>
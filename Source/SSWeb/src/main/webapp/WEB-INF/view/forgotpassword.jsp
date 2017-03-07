<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="label.title.forgotpassword.key" /></title>
	<link rel="stylesheet" href="${initParam.resourcesPath}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="${initParam.resourcesPath}/resources/css/style.css">
	<link rel="stylesheet" href="${initParam.resourcesPath}/resources/css/style-common.css">
	<link rel="stylesheet" href="${initParam.resourcesPath}/resources/css/style-resp.css">
	<link rel="stylesheet" href="${initParam.resourcesPath}/resources/css/style-common-1.1.css">
	<link rel="stylesheet" href="${initParam.resourcesPath}/resources/css/style-resp-1.1.css">
</head>

<body>
	<div id="toast-container" class="toast-container">
	   <span id="overlay-toast" class="overlay-toast"></span>
    </div>
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
				<div class="hm-header-row-left text-center lgn-adj"><spring:message code="label.title.forgotpassword.key" /></div>
			</div>
		</div>
	</div>
	
	<div class="prof-main-content-wrapper margin-top-25 margin-bottom-25">
		<div class="container reg_panel_container">
			<div class="reg_header"><spring:message code="label.forgotpassword.key" /></div>
			
			<form id="forgot-pwd-form" method="POST" action="./sendresetpasswordlink.do">
				<div id="forgot-pwd-div" class="reg_form_wrapper_1">
					<div class="reg_form_row clearfix">
						<div class="float-left rfr_lbl rfr_lbl_width-98"><spring:message code="label.emailid.key"/></div>
						<div class="float-left rfr_txt">
							<div class="rfr_icn icn-mail"></div>
							<div class="rfr_txt_fld">
								<input type="email" class="rfr_input_fld" id="login-user-id" data-non-empty="true" data-email=""
									name="emailId" placeholder='<spring:message code="label.emailid.key"/>'>
							</div>
						</div>
					</div>
					
					<div class="reg_form_row clearfix">
						<div class="reg_btn" id="forgot-pwd-submit"><spring:message code="label.submit.key" /></div>
					</div>
				</div>
				<input type="hidden" value="${message}" data-status="${status}" name="message" id="message"/>
			</form>
			
		</div>
	</div>

<script src="${initParam.resourcesPath}/resources/js/jquery-2.1.1.min.js"></script>
<script src="${initParam.resourcesPath}/resources/js/script.js"></script>
<script>
$(document).ready(function() {
	initializeForgotPasswordPage();
});
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" var="user" />

<div class="hm-header-main-wrapper">
	<div class="container">
		<div class="hm-header-row clearfix">
			<div class="float-left hm-header-row-left">
				<spring:message code="label.title.emailsettings.key" />
			</div>
		</div>
	</div>
</div>
<div class="hm-main-content-wrapper margin-top-25 margin-bottom-25">
	<div class="container">

		<!-- Starting code for Mail text -->
		<div class="um-top-container" style="padding-bottom: 0">
			<form id="mail-body-settings-form">
				<input type="hidden" name="mailcategory" id="mailcategory">
				
				<ul class="accordion">

        <li class="col-lg-12 col-md-12 col-sm-12 col-xs-12 "><a href="#" class="email-category">Dynamic Fields Legend</a>
        <div class="email-content" style="">
         <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 float-left">
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 float-left" >
                        <div class="legend-header">Field</div>
						<div class="legend-wrapper"><span class="legend">[BaseUrl] </span></div>
						<div class="legend-wrapper"><span class="legend">[LogoUrl] </span></div>
						<div class="legend-wrapper"><span class="legend">[Link] </span></div>
						<div class="legend-wrapper"><span class="legend">[Name] </span></div>
						<div class="legend-wrapper"><span class="legend">[FirstName]</span></div>
						<div class="legend-wrapper"><span class="legend">[AgentName] </span></div>
						<div class="legend-wrapper"><span class="legend">[AgentSignature]</span></div> 
						<div class="legend-wrapper"><span class="legend">[RecipientEmail]</span> </div>
						<div class="legend-wrapper"><span class="legend">[SenderEmail] </span> </div>
						<div class="legend-wrapper"><span class="legend">[CompanyName] </span></div>
						<div class="legend-wrapper"><span class="legend">[InitiatedDate]</span></div>
						<div class="legend-wrapper"><span class="legend">[CurrentYear]</span></div>
						<div class="legend-wrapper"><span class="legend">[FullAddress]</span> </div>
						<div class="legend-wrapper"><span class="legend">[CompanyDisclaimer]</span></div>
						<div class="legend-wrapper"><span class="legend">[AgentDisclaimer] </span></div>
                        <div class="legend-wrapper"><span class="legend">[AgentLicense]</span></div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
					    <div class="legend-header">Description</div>
					    <div class="legend-wrapper">Url of the SocialSurvey</div>
						<div class="legend-wrapper"> Url of the SocialSurvey Logo</div>
						 <div class="legend-wrapper">Url of the survey</div>
						 <div class="legend-wrapper">Customer Name</div>
						 <div class="legend-wrapper">Customer First Name</div>
						 <div class="legend-wrapper">User Name</div>
						 <div class="legend-wrapper">User's Signature</div>
						 <div class="legend-wrapper">Receipient's Email</div>
						 <div class="legend-wrapper">Sender's Email</div>
						 <div class="legend-wrapper">Company Name</div>
						 <div class="legend-wrapper">Survey Initiated On</div>
						 <div class="legend-wrapper">Current Year</div>
						 <div class="legend-wrapper">Address of SocialSurvey</div>
						 <div class="legend-wrapper">Company's disclaimer</div>
						 <div class="legend-wrapper">User's disclaimer</div>
                         <div class="legend-wrapper">User's licenses</div>
						<br />
						<br />
					</div>
			</div>
			
			  <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 float-left">
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 float-left" >
                        <div class="legend-header">Field</div>
						<div class="legend-wrapper"><span class="legend">[company_facebook_link]</span> </div>
						<div class="legend-wrapper"><span class="legend">[company_twitter_link] </span></div>
						<div class="legend-wrapper"><span class="legend">[company_linkedin_link]</span> </div>
						<div class="legend-wrapper"><span class="legend">[company_google_plus_link]</span> </div>
						<div class="legend-wrapper"><span class="legend">[company_google_review_link]</span></div>
						<div class="legend-wrapper"><span class="legend">[company_yelp_link]</span> </div>
						<div class="legend-wrapper"><span class="legend">[company_zillow_link]</span></div> 
						<div class="legend-wrapper"><span class="legend">[company_lending_tree_link]</span> </div>
						<div class="legend-wrapper"><span class="legend">[company_realtor_com_link]</span>  </div>
						<div class="legend-wrapper"><span class="legend">[facebook_link]</span> </div>
						<div class="legend-wrapper"><span class="legend">[twitter_link]</span></div>
						<div class="legend-wrapper"><span class="legend">[linkedin_link]</span></div>
						<div class="legend-wrapper"><span class="legend">[google_plus_link]</span> </div>
						<div class="legend-wrapper"><span class="legend">[google_review_link]</span> </div>
						<div class="legend-wrapper"><span class="legend">[yelp_link]</span></div>
						<div class="legend-wrapper"><span class="legend">[zillow_link]</span> </div>
                        <div class="legend-wrapper"><span class="legend">[lending_tree_link]</span></div>
                        <div class="legend-wrapper"><span class="legend">[realtor_com_link] </span> </div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
					<div class="legend-header">Description</div>
					    <div class="legend-wrapper">Company Facebook Link</div>
						<div class="legend-wrapper">Company Twitter Link</div>
						<div class="legend-wrapper">Company LinkedIn Link</div>
						<div class="legend-wrapper">Company Google+ Link</div>
						<div class="legend-wrapper">Company Google Business Rate & Review Link</div>
						<div class="legend-wrapper">Company Yelp Link</div>
						<div class="legend-wrapper">Company Zillow Link</div>
						<div class="legend-wrapper">Company Lending Tree Link</div>
						<div class="legend-wrapper">Company Realtor.com Profile Link</div>
						<div class="legend-wrapper">Facebook Link</div>
						<div class="legend-wrapper">Twitter Link</div>
						<div class="legend-wrapper">LinkedIn Link</div>
						<div class="legend-wrapper">Google+ Link</div>
						<div class="legend-wrapper">Google Business Rate & Review Link</div>
						<div class="legend-wrapper">Yelp Link</div>
						<div class="legend-wrapper">Zillow Link</div>
						<div class="legend-wrapper">Lending Tree Link</div>
						<div class="legend-wrapper">Realtor.com Profile Link</div>
					</div>
			</div>
					<div>Note: Only <span class="legend">[AgentName] </span>is allowed in Mail Subject</div>
			
					
				</div>
				
</li>

        <li class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
        <a href="#"  class="email-category">Initial Survey Request</a>
        <div class="email-content" style="">
<div>
	<div class="clearfix st-bottom-wrapper margin-top-50">
					
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-participation-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-participation-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-participation-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-participation-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-participation-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text">Edit</div>
								<div class="float-left settings-btn-text margin-left-20">Save</div>
								<div class="float-left settings-btn-text margin-left-20">Reset</div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.mailer.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="survey-mailcontent-subject" name="survey-mailcontent-subject"
								class="st-subject-input" value="${surveymailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="survey-participation-mailcontent" name="survey-participation-mailcontent"
							class="st-header-txt-input">${surveymailbody}</textarea>
					</div>
				</div>
				
</div>
</div></li>

        <li class="col-lg-12 col-md-12 col-sm-12 col-xs-12" ><a href="#" class="email-category">Incomplete Survey Reminder</a><div class="email-content" style="">


				<!-- survey reminder mail -->
				<div class="clearfix st-bottom-wrapper margin-top-50">
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.reminder.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-participation-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-participation-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-participation-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-participation-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-participation-reminder-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text"><spring:message code="label.edit.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.save.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.reset.key" /></div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.reminder.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="survey-mailreminder-subject" name="survey-mailreminder-subject"
								class="st-subject-input" value="${surveyremindermailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="survey-participation-reminder-mailcontent" name="survey-participation-reminder-mailcontent"
							class="st-header-txt-input">${surveyremindermailbody}</textarea>
					</div>
				</div>
				
				<!--  survey reminder interval checkbox and time -->
				<!-- set the mail body details -->
				<c:if test="${cannonicalusersettings.companySettings != null && cannonicalusersettings.companySettings.survey_settings!= null}">
					<c:set var="reminderinterval" value="${cannonicalusersettings.companySettings.survey_settings.survey_reminder_interval_in_days}" />
					<c:set var="isreminderdisabled" value="${cannonicalusersettings.companySettings.survey_settings.isReminderDisabled}"/>				
				</c:if>
				
				<div class="clearfix st-bottom-wrapper st-reminder-wrapper">
					<div class="float-left"><spring:message code="label.reminder.interval.key" /></div>
					<div class="clearfix float-left">
						<div class="float-left st-input-reminder">
						<c:choose>
						 <c:when test="${isreminderdisabled == false}">
						 <input class="st-rating-input" name="reminder-interval"
								id="reminder-interval" value="${reminderinterval}">
						 </c:when>
						 <c:otherwise>
						  <input class="st-rating-input" name="reminder-interval"
								id="reminder-interval" value="${reminderinterval}" disabled>
						 </c:otherwise>
						 </c:choose>
							<div id="reminder-interval-error" class="hm-item-err-2"></div>
						</div>
						<div class="float-left"><spring:message code="label.days.key" /></div>
					</div>
					<div class="clearfix st-check-main float-left">
						<div class="float-left st-check-wrapper">
							<input type="hidden" name="reminder-needed-hidden" id="reminder-needed-hidden" value="${isreminderdisabled}">
							<c:choose>
							<c:when test="${isreminderdisabled == false}">
								<div id="st-reminder-on" class="st-checkbox st-checkbox-on hide"></div>
								<div id="st-reminder-off" class="st-checkbox st-checkbox-off"></div>
							</c:when>
							<c:otherwise>
								<div id="st-reminder-on" class="st-checkbox st-checkbox-on"></div>
								<div id="st-reminder-off" class="st-checkbox st-checkbox-off hide"></div>
							</c:otherwise>
							</c:choose>
							
						</div>
						<div class="float-left st-check-txt-OR"><spring:message code="label.noreminder.key" /></div>
					</div>
				</div>
</div></li>

        <li class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><a href="#" class="email-category">Survey Completed</a><div class="email-content" style="">

<div class="clearfix st-bottom-wrapper margin-top-50">
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.survey.completion.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-survey-completion-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-survey-completion-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-survey-completion-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-survey-completion-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-survey-completion-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text"><spring:message code="label.edit.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.save.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.reset.key" /></div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.survey.completion.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="survey-completion-subject" name="survey-completion-subject"
								class="st-subject-input" value="${surveycompletionmailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="survey-completion-mailcontent" name="survey-completion-mailcontent"
							class="st-header-txt-input">${surveycompletionmailbody}</textarea>
					</div>
				</div>
</div></li>
 		<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><a href="#" class="email-category">Survey Completed (Unpleasant)</a><div class="email-content" style="">
<!-- survey completion unpleasent mail -->
				
				<div class="clearfix st-bottom-wrapper margin-top-50">
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.survey.completion.unpleasant.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-survey-completion-unpleasant-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-survey-completion-unpleasant-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-survey-completion-unpleasant-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-survey-completion-unpleasant-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-survey-completion-unpleasant-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text"><spring:message code="label.edit.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.save.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.reset.key" /></div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.survey.completion.unpleasant.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="survey-completion-unpleasant-subject" name="survey-completion-unpleasant-subject"
								class="st-subject-input" value="${surveycompletionunpleasantmailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="survey-completion-unpleasant-mailcontent" name="survey-completion-unpleasant-mailcontent"
							class="st-header-txt-input">${surveycompletionunpleasantmailbody}</textarea>
					</div>
				</div>
</div></li>
		<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><a href="#" class="email-category">Social Post Reminder</a><div class="email-content" style="">

<!-- social post reminder mail -->
				<div class="clearfix st-bottom-wrapper margin-top-50">
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.socialpost.reminder.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-social-post-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-social-post-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-social-post-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-social-post-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-social-post-reminder-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text"><spring:message code="label.edit.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.save.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.reset.key" /></div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.socialpost.reminder.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="social-post-reminder-subject" name="social-post-reminder-subject"
								class="st-subject-input" value="${socialpostremindermailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="social-post-reminder-mailcontent" name="social-post-reminder-mailcontent"
							class="st-header-txt-input">${socialpostremindermailbody}</textarea>
					</div>
				</div>
				
				<!-- set the variables -->
				<c:if test="${cannonicalusersettings.companySettings != null && cannonicalusersettings.companySettings.survey_settings!= null}">
					<c:set var="postreminderinterval" value="${cannonicalusersettings.companySettings.survey_settings.social_post_reminder_interval_in_days}" />
					<c:set var="ispostreminderdisabled" value="${cannonicalusersettings.companySettings.survey_settings.isSocialPostReminderDisabled}"/>
				</c:if>
				
				<!-- social post reminder interval checkbox and time -->
				<div class="clearfix st-bottom-wrapper st-reminder-wrapper">
					<div class="float-left"><spring:message code="label.post.reminder.interval.key" /></div>
					<div class="clearfix float-left">
						<div class="float-left st-input-reminder">
						<c:choose>
						 <c:when test="${ispostreminderdisabled == false}">
						 <input class="st-rating-input" name="post-reminder-interval"
								id="post-reminder-interval" value="${postreminderinterval}">
						 </c:when>
						 <c:otherwise>
						 <input class="st-rating-input" name="post-reminder-interval"
								id="post-reminder-interval" value="${postreminderinterval}" disabled>
						 </c:otherwise>
						 </c:choose>
							<div id="post-reminder-interval-error" class="hm-item-err-2"></div>
						</div>
						<div class="float-left"><spring:message code="label.days.key" /></div>
					</div>
					<div class="clearfix st-check-main float-left">
						<div class="float-left st-check-wrapper">
							<input type="hidden" name="post-reminder-needed-hidden" id="post-reminder-needed-hidden" value="${ispostreminderdisabled}">
							<c:choose>
							<c:when test="${ispostreminderdisabled == false}">
								<div id="post-reminder-on" class="st-checkbox st-checkbox-on hide"></div>
								<div id="post-reminder-off" class="st-checkbox st-checkbox-off"></div>
							</c:when>
							<c:otherwise>
								<div id="post-reminder-on" class="st-checkbox st-checkbox-on"></div>
								<div id="post-reminder-off" class="st-checkbox st-checkbox-off hide"></div>
							</c:otherwise>
							</c:choose>
						</div>
						<div class="float-left st-check-txt-OR"><spring:message code="label.post.noreminder.key" /></div>
					</div>
				</div>
</div></li>
		<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12"><a href="#" class="email-category">Retake Survey Request</a><div class="email-content" style="">

<!-- incomplete survey reminder mail -->
				<div class="clearfix st-bottom-wrapper margin-top-50">
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.restart.survey.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-incomplete-survey-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-incomplete-survey-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-incomplete-survey-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-incomplete-survey-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-incomplete-survey-reminder-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text"><spring:message code="label.edit.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.save.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.reset.key" /></div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.restart.survey.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="incomplete-survey-mailreminder-subject" name="incomplete-survey-mailreminder-subject"
								class="st-subject-input" value="${restartsurveymailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="incomplete-survey-reminder-mailcontent" name="incomplete-survey-reminder-mailcontent"
							class="st-header-txt-input">${restartsurveymailbody}</textarea>
					</div>
				</div>
				
</div></li>
</ul>
				<%-- <div class="clearfix st-bottom-wrapper margin-top-50">
					 <div class="clearfix">Legends: <br />
						&nbsp&nbsp&nbsp&nbsp[BaseUrl] : Url of the SocialSurvey,
						&nbsp&nbsp&nbsp&nbsp[LogoUrl] : Url of the SocialSurvey Logo,
						&nbsp&nbsp&nbsp&nbsp[Link] : Url of the survey,
						<br />
						&nbsp&nbsp&nbsp&nbsp[Name] : Customer Name,
						&nbsp&nbsp&nbsp&nbsp[FirstName] : Customer First Name,
						&nbsp&nbsp&nbsp&nbsp[AgentName] : User Name,
						&nbsp&nbsp&nbsp&nbsp[AgentSignature] : User's Signature,
						<br />
						&nbsp&nbsp&nbsp&nbsp[RecipientEmail] : Receipient's Email,
						&nbsp&nbsp&nbsp&nbsp[SenderEmail] : Sender's Email,
						&nbsp&nbsp&nbsp&nbsp[CompanyName] : Company Name,
						<br />
						&nbsp&nbsp&nbsp&nbsp[InitiatedDate] : Survey Initiated On,
						&nbsp&nbsp&nbsp&nbsp[CurrentYear] : Current Year,
						&nbsp&nbsp&nbsp&nbsp[FullAddress] : Address of SocialSurvey,
						<br />
						&nbsp&nbsp&nbsp&nbsp[CompanyDisclaimer] : Company's disclaimer,
						&nbsp&nbsp&nbsp&nbsp[AgentDisclaimer] : User's disclaimer,
                        &nbsp&nbsp&nbsp&nbsp[AgentLicense] : User's licenses,
						<br />

						&nbsp&nbsp&nbsp&nbsp[facebook_link] : Facebook Social Link,
						&nbsp&nbsp&nbsp&nbsp[twitter_link] : Twitter Social Link,
                        &nbsp&nbsp&nbsp&nbsp[linkedin_link] : LinkedIn Social Link,
						<br />
						&nbsp&nbsp&nbsp&nbsp[google_plus_link] : Google Plus Social Link,
						&nbsp&nbsp&nbsp&nbsp[google_review_link] : Google Business Review Link,
                        &nbsp&nbsp&nbsp&nbsp[yelp_link] : Yelp Social Link,
						<br />
						&nbsp&nbsp&nbsp&nbsp[zillow_link] : Zillow Social Link,
						&nbsp&nbsp&nbsp&nbsp[lending_tree_link] : Lending Tree Social Link,
                        &nbsp&nbsp&nbsp&nbsp[realtor_com_link] : Realtor Social Link,
						<br />
						&nbsp&nbsp&nbsp&nbsp[company_facebook_link] : Company's Facebook Social Link,
						&nbsp&nbsp&nbsp&nbsp[company_twitter_link] : Company's Twitter Social Link,
                        &nbsp&nbsp&nbsp&nbsp[company_linkedin_link] : Company's LinkedIn Social Link,
						<br />
						&nbsp&nbsp&nbsp&nbsp[company_google_plus_link] : Company's Google Plus Link,
						&nbsp&nbsp&nbsp&nbsp[company_google_review_link] : Company's Google Business Review Link,
                        &nbsp&nbsp&nbsp&nbsp[company_yelp_link] : Company's Yelp Social Link,
						<br />
						&nbsp&nbsp&nbsp&nbsp[company_zillow_link] : Company's Zillow Social Link,
						&nbsp&nbsp&nbsp&nbsp[company_lending_tree_link] : Company's Lending Tree Social Link,
                        &nbsp&nbsp&nbsp&nbsp[company_realtor_com_link] : Company's Realtor Social Link
						<br />
						<br />
						Note: Only [AgentName] is allowed in Mail Subject
					</div> 
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-participation-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-participation-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-participation-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-participation-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-participation-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text">Edit</div>
								<div class="float-left settings-btn-text margin-left-20">Save</div>
								<div class="float-left settings-btn-text margin-left-20">Reset</div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.mailer.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="survey-mailcontent-subject" name="survey-mailcontent-subject"
								class="st-subject-input" value="${surveymailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="survey-participation-mailcontent" name="survey-participation-mailcontent"
							class="st-header-txt-input">${surveymailbody}</textarea>
					</div>
				</div>
				 --%>
				<%-- 
				<!-- survey reminder mail -->
				<div class="clearfix st-bottom-wrapper margin-top-50">
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.reminder.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-participation-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-participation-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-participation-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-participation-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-participation-reminder-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text"><spring:message code="label.edit.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.save.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.reset.key" /></div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.reminder.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="survey-mailreminder-subject" name="survey-mailreminder-subject"
								class="st-subject-input" value="${surveyremindermailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="survey-participation-reminder-mailcontent" name="survey-participation-reminder-mailcontent"
							class="st-header-txt-input">${surveyremindermailbody}</textarea>
					</div>
				</div>
				
				<!--  survey reminder interval checkbox and time -->
				<!-- set the mail body details -->
				<c:if test="${cannonicalusersettings.companySettings != null && cannonicalusersettings.companySettings.survey_settings!= null}">
					<c:set var="reminderinterval" value="${cannonicalusersettings.companySettings.survey_settings.survey_reminder_interval_in_days}" />
					<c:set var="isreminderdisabled" value="${cannonicalusersettings.companySettings.survey_settings.isReminderDisabled}"/>				
				</c:if>
				
				<div class="clearfix st-bottom-wrapper st-reminder-wrapper">
					<div class="float-left"><spring:message code="label.reminder.interval.key" /></div>
					<div class="clearfix float-left">
						<div class="float-left st-input-reminder">
						<c:choose>
						 <c:when test="${isreminderdisabled == false}">
						 <input class="st-rating-input" name="reminder-interval"
								id="reminder-interval" value="${reminderinterval}">
						 </c:when>
						 <c:otherwise>
						  <input class="st-rating-input" name="reminder-interval"
								id="reminder-interval" value="${reminderinterval}" disabled>
						 </c:otherwise>
						 </c:choose>
							<div id="reminder-interval-error" class="hm-item-err-2"></div>
						</div>
						<div class="float-left"><spring:message code="label.days.key" /></div>
					</div>
					<div class="clearfix st-check-main float-left">
						<div class="float-left st-check-wrapper">
							<input type="hidden" name="reminder-needed-hidden" id="reminder-needed-hidden" value="${isreminderdisabled}">
							<c:choose>
							<c:when test="${isreminderdisabled == false}">
								<div id="st-reminder-on" class="st-checkbox st-checkbox-on hide"></div>
								<div id="st-reminder-off" class="st-checkbox st-checkbox-off"></div>
							</c:when>
							<c:otherwise>
								<div id="st-reminder-on" class="st-checkbox st-checkbox-on"></div>
								<div id="st-reminder-off" class="st-checkbox st-checkbox-off hide"></div>
							</c:otherwise>
							</c:choose>
							
						</div>
						<div class="float-left st-check-txt-OR"><spring:message code="label.noreminder.key" /></div>
					</div>
				</div> --%>
				
				<!-- survey completion mail -->
				
				<%-- <div class="clearfix st-bottom-wrapper margin-top-50">
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.survey.completion.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-survey-completion-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-survey-completion-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-survey-completion-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-survey-completion-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-survey-completion-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text"><spring:message code="label.edit.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.save.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.reset.key" /></div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.survey.completion.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="survey-completion-subject" name="survey-completion-subject"
								class="st-subject-input" value="${surveycompletionmailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="survey-completion-mailcontent" name="survey-completion-mailcontent"
							class="st-header-txt-input">${surveycompletionmailbody}</textarea>
					</div>
				</div> --%>
				
				<%-- <!-- survey completion unpleasent mail -->
				
				<div class="clearfix st-bottom-wrapper margin-top-50">
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.survey.completion.unpleasant.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-survey-completion-unpleasant-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-survey-completion-unpleasant-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-survey-completion-unpleasant-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-survey-completion-unpleasant-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-survey-completion-unpleasant-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text"><spring:message code="label.edit.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.save.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.reset.key" /></div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.survey.completion.unpleasant.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="survey-completion-unpleasant-subject" name="survey-completion-unpleasant-subject"
								class="st-subject-input" value="${surveycompletionunpleasantmailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="survey-completion-unpleasant-mailcontent" name="survey-completion-unpleasant-mailcontent"
							class="st-header-txt-input">${surveycompletionunpleasantmailbody}</textarea>
					</div>
				</div> --%>
				
				<%-- <!-- social post reminder mail -->
				<div class="clearfix st-bottom-wrapper margin-top-50">
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.socialpost.reminder.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-social-post-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-social-post-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-social-post-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-social-post-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-social-post-reminder-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text"><spring:message code="label.edit.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.save.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.reset.key" /></div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.socialpost.reminder.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="social-post-reminder-subject" name="social-post-reminder-subject"
								class="st-subject-input" value="${socialpostremindermailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="social-post-reminder-mailcontent" name="social-post-reminder-mailcontent"
							class="st-header-txt-input">${socialpostremindermailbody}</textarea>
					</div>
				</div>
				
				<!-- set the variables -->
				<c:if test="${cannonicalusersettings.companySettings != null && cannonicalusersettings.companySettings.survey_settings!= null}">
					<c:set var="postreminderinterval" value="${cannonicalusersettings.companySettings.survey_settings.social_post_reminder_interval_in_days}" />
					<c:set var="ispostreminderdisabled" value="${cannonicalusersettings.companySettings.survey_settings.isSocialPostReminderDisabled}"/>
				</c:if>
				
				<!-- social post reminder interval checkbox and time -->
				<div class="clearfix st-bottom-wrapper st-reminder-wrapper">
					<div class="float-left"><spring:message code="label.post.reminder.interval.key" /></div>
					<div class="clearfix float-left">
						<div class="float-left st-input-reminder">
						<c:choose>
						 <c:when test="${ispostreminderdisabled == false}">
						 <input class="st-rating-input" name="post-reminder-interval"
								id="post-reminder-interval" value="${postreminderinterval}">
						 </c:when>
						 <c:otherwise>
						 <input class="st-rating-input" name="post-reminder-interval"
								id="post-reminder-interval" value="${postreminderinterval}" disabled>
						 </c:otherwise>
						 </c:choose>
							<div id="post-reminder-interval-error" class="hm-item-err-2"></div>
						</div>
						<div class="float-left"><spring:message code="label.days.key" /></div>
					</div>
					<div class="clearfix st-check-main float-left">
						<div class="float-left st-check-wrapper">
							<input type="hidden" name="post-reminder-needed-hidden" id="post-reminder-needed-hidden" value="${ispostreminderdisabled}">
							<c:choose>
							<c:when test="${ispostreminderdisabled == false}">
								<div id="post-reminder-on" class="st-checkbox st-checkbox-on hide"></div>
								<div id="post-reminder-off" class="st-checkbox st-checkbox-off"></div>
							</c:when>
							<c:otherwise>
								<div id="post-reminder-on" class="st-checkbox st-checkbox-on"></div>
								<div id="post-reminder-off" class="st-checkbox st-checkbox-off hide"></div>
							</c:otherwise>
							</c:choose>
						</div>
						<div class="float-left st-check-txt-OR"><spring:message code="label.post.noreminder.key" /></div>
					</div>
				</div> --%>
				
				<%-- <!-- incomplete survey reminder mail -->
				<div class="clearfix st-bottom-wrapper margin-top-50">
					<div class="st-header-txt-lft-rt clearfix margin-top-25">
						<div class="float-left st-header-txt-lft"><spring:message code="label.header.restart.survey.mailer.content.key" /></div>
						<div class="float-right clearfix st-header-txt-rt">
							<div class="clearfix">
								<div id="edit-incomplete-survey-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-pen cursor-pointer icn-pen-blue"></div>
								<div id="edit-incomplete-survey-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn icn-pen hide"></div>
		
								<div id="save-incomplete-survey-reminder-mail-content"
									class="float-left st-header-txt-rt-icn icn-blue-tick margin-left-20 cursor-pointer hide"></div>
								<div id="save-incomplete-survey-reminder-mail-content-disabled"
									class="float-left st-header-txt-rt-icn margin-left-20 icn-grey-tick"></div>
									
								<div id="revert-incomplete-survey-reminder-mail"
									class="float-left st-header-txt-rt-icn margin-left-20 cursor-pointer icn-blue-ellipse"></div>
							</div>
							<div class="clearfix">
								<div class="float-left settings-btn-text"><spring:message code="label.edit.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.save.key" /></div>
								<div class="float-left settings-btn-text margin-left-20"><spring:message code="label.reset.key" /></div>
							</div>
						</div>
					</div>
					<div class="st-subject-cont clearfix">
						<div class="st-subject-label float-left"><spring:message code="label.subject.restart.survey.text" /></div>
						<div class="st-subject-input-cont float-left">
							<input type="text" id="incomplete-survey-mailreminder-subject" name="incomplete-survey-mailreminder-subject"
								class="st-subject-input" value="${restartsurveymailsubject}" readonly>
						</div>
					</div>
					<div class="st-header-txt-wrapper">
						<textarea id="incomplete-survey-reminder-mailcontent" name="incomplete-survey-reminder-mailcontent"
							class="st-header-txt-input">${restartsurveymailbody}</textarea>
					</div>
				</div>
				 --%>
			</form>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		hideOverlay();
		$(document).attr("title", "Email Settings");
		
		try {
			$('#survey-participation-mailcontent').ckeditor();
			$('#survey-participation-mailcontent').ckeditorGet().config.readOnly = true;
			
			$('#survey-participation-reminder-mailcontent').ckeditor();
			$('#survey-participation-reminder-mailcontent').ckeditorGet().config.readOnly = true;
			
			$('#survey-completion-mailcontent').ckeditor();
			$('#survey-completion-mailcontent').ckeditorGet().config.readOnly = true;
			
			$('#survey-completion-unpleasant-mailcontent').ckeditor();
			$('#survey-completion-unpleasant-mailcontent').ckeditorGet().config.readOnly = true;
			
			$('#social-post-reminder-mailcontent').ckeditor();
			$('#social-post-reminder-mailcontent').ckeditorGet().config.readOnly = true;
			
			$('#incomplete-survey-reminder-mailcontent').ckeditor();
			$('#incomplete-survey-reminder-mailcontent').ckeditorGet().config.readOnly = true;
		} catch(e) {
		}
		
		$('#edit-participation-mail-content').click(function() {
			try {
				$('#survey-participation-mailcontent').ckeditorGet().setReadOnly(false);
			} catch(e) {
			}
			$('#survey-mailcontent-subject').attr("readonly", false);
			
			$('#save-participation-mail-content').show();
			$('#save-participation-mail-content-disabled').hide();
			
			$('#edit-participation-mail-content-disabled').show();
			$(this).hide();
		});
		$('#save-participation-mail-content').click(function() {
			$('#mailcategory').val('participationmail');
			updateMailContent("mail-body-settings-form",'#save-participation-mail-content');
			
			try {
				$('#survey-participation-mailcontent').ckeditorGet().setReadOnly(true);
			} catch(e) {
			}
			$('#survey-mailcontent-subject').attr("readonly", true);
			
			$(this).hide();
			$('#save-participation-mail-content-disabled').show();

			$('#edit-participation-mail-content').show();
			$('#edit-participation-mail-content-disabled').hide();
		});
		$('#revert-participation-mail').click(function(e) {
			e.stopPropagation();
			$('#overlay-main').show();
			$('#overlay-continue').show();
			$('#overlay-continue').html("Reset");
			$('#overlay-cancel').html("Cancel");
			$('#overlay-header').html("Reset Mail Template");
			$('#overlay-text').html("Are you sure you want to reset the mail template ?");
			$('#overlay-continue').attr("onclick", "revertMailContent('participationmail','#revert-participation-mail');");
			
		});
		
		//survey reminder mail
		$('#edit-participation-reminder-mail-content').click(function() {
			try {
				$('#survey-participation-reminder-mailcontent').ckeditorGet().setReadOnly(false);
			} catch(e) {
			}
			$('#survey-mailreminder-subject').attr("readonly", false);
			
			$('#save-participation-reminder-mail-content').show();
			$('#save-participation-reminder-mail-content-disabled').hide();
			
			$('#edit-participation-reminder-mail-content-disabled').show();
			$(this).hide();
		});
		$('#save-participation-reminder-mail-content').click(function() {
			$('#mailcategory').val('participationremindermail');
			updateMailContent("mail-body-settings-form",'#save-participation-reminder-mail-content');
			
			try { 
				$('#survey-participation-reminder-mailcontent').ckeditorGet().setReadOnly(true);
			} catch(e) {
			}
			$('#survey-mailreminder-subject').attr("readonly", true);
			
			$(this).hide();
			$('#save-participation-reminder-mail-content-disabled').show();

			$('#edit-participation-reminder-mail-content').show();
			$('#edit-participation-reminder-mail-content-disabled').hide();
		});
		$('#revert-participation-reminder-mail').click(function(e) {
			e.stopPropagation();
			$('#overlay-main').show();
			$('#overlay-continue').show();
			$('#overlay-continue').html("Reset");
			$('#overlay-cancel').html("Cancel");
			$('#overlay-header').html("Reset Mail Template");
			$('#overlay-text').html("Are you sure you want to reset the mail template ?");
			$('#overlay-continue').attr("onclick", "revertMailContent('participationremindermail','#revert-participation-reminder-mail');");
		});
		
		//for survey completion mail
		$('#edit-survey-completion-mail-content').click(function() {
			try {
				$('#survey-completion-mailcontent').ckeditorGet().setReadOnly(false);
			} catch(e) {
			}
			$('#survey-completion-subject').attr("readonly", false);
			
			$('#save-survey-completion-mail-content').show();
			$('#save-survey-completion-mail-content-disabled').hide();
			
			$('#edit-survey-completion-mail-content-disabled').show();
			$(this).hide();
		});
		$('#save-survey-completion-mail-content').click(function() {
			$('#mailcategory').val('surveycompletionmail');
			updateMailContent("mail-body-settings-form",'#save-survey-completion-mail-content');
			
			try {
				$('#survey-completion-mailcontent').ckeditorGet().setReadOnly(true);
			} catch(e) {
			}
			$('#survey-completion-subject').attr("readonly", true);
			
			$(this).hide();
			$('#save-survey-completion-mail-content-disabled').show();

			$('#edit-survey-completion-mail-content').show();
			$('#edit-survey-completion-mail-content-disabled').hide();
		});
		$('#revert-survey-completion-mail').click(function(e) {
			e.stopPropagation();
			$('#overlay-main').show();
			$('#overlay-continue').show();
			$('#overlay-continue').html("Reset");
			$('#overlay-cancel').html("Cancel");
			$('#overlay-header').html("Reset Mail Template");
			$('#overlay-text').html("Are you sure you want to reset the mail template ?");
			$('#overlay-continue').attr("onclick", "revertMailContent('surveycompletionmail','');");
		});
		
		//for survey completion unpleasant mail
		$('#edit-survey-completion-unpleasant-mail-content').click(function() {
			try {
				$('#survey-completion-unpleasant-mailcontent').ckeditorGet().setReadOnly(false);
			} catch(e) {
			}
			$('#survey-completion-unpleasant-subject').attr("readonly", false);
			
			$('#save-survey-completion-unpleasant-mail-content').show();
			$('#save-survey-completion-unpleasant-mail-content-disabled').hide();
			
			$('#edit-survey-completion-unpleasant-mail-content-disabled').show();
			$(this).hide();
		});
		$('#save-survey-completion-unpleasant-mail-content').click(function() {
			$('#mailcategory').val('surveycompletionunpleasantmail');
			updateMailContent("mail-body-settings-form",'#save-survey-completion-unpleasant-mail-content');
			
			try {
				$('#survey-completion-unpleasant-mailcontent').ckeditorGet().setReadOnly(true);
			} catch(e) {
			}
			$('#survey-completion-unpleasant-subject').attr("readonly", true);
			
			$(this).hide();
			$('#save-survey-completion-unpleasant-mail-content-disabled').show();

			$('#edit-survey-completion-unpleasant-mail-content').show();
			$('#edit-survey-completion-unpleasant-mail-content-disabled').hide();
		});
		$('#revert-survey-completion-unpleasant-mail').click(function(e) {
			e.stopPropagation();
			$('#overlay-main').show();
			$('#overlay-continue').show();
			$('#overlay-continue').html("Reset");
			$('#overlay-cancel').html("Cancel");
			$('#overlay-header').html("Reset Mail Template");
			$('#overlay-text').html("Are you sure you want to reset the mail template ?");
			$('#overlay-continue').attr("onclick", "revertMailContent('surveycompletionunpleasantmail','#revert-survey-completion-unpleasant-mail');");
		});
		
		//social post reminder mail
		$('#edit-social-post-reminder-mail-content').click(function() {
			try {
				$('#social-post-reminder-mailcontent').ckeditorGet().setReadOnly(false);
			} catch(e) {
			}
			$('#social-post-reminder-subject').attr("readonly", false);
			
			$('#save-social-post-reminder-mail-content').show();
			$('#save-social-post-reminder-mail-content-disabled').hide();
			
			$('#edit-social-post-reminder-mail-content-disabled').show();
			$(this).hide();
		});
		$('#save-social-post-reminder-mail-content').click(function() {
			$('#mailcategory').val('socialpostremindermail');
			updateMailContent("mail-body-settings-form",'#save-social-post-reminder-mail-content');
			
			try { 
				$('#social-post-reminder-mailcontent').ckeditorGet().setReadOnly(true);
			} catch(e) {
			}
			$('#social-post-reminder-subject').attr("readonly", true);
			
			$(this).hide();
			$('#save-social-post-reminder-mail-content-disabled').show();

			$('#edit-social-post-reminder-mail-content').show();
			$('#edit-social-post-reminder-mail-content-disabled').hide();
		});
		$('#revert-social-post-reminder-mail').click(function(e) {
			e.stopPropagation();
			$('#overlay-main').show();
			$('#overlay-continue').show();
			$('#overlay-continue').html("Reset");
			$('#overlay-cancel').html("Cancel");
			$('#overlay-header').html("Reset Mail Template");
			$('#overlay-text').html("Are you sure you want to reset the mail template ?");
			$('#overlay-continue').attr("onclick", "revertMailContent('socialpostremindermail','#revert-social-post-reminder-mail');");
		});
		
		//incomplete survey reminder mail
		$('#edit-incomplete-survey-reminder-mail-content').click(function() {
			try {
				$('#incomplete-survey-reminder-mailcontent').ckeditorGet().setReadOnly(false);
			} catch(e) {
			}
			$('#incomplete-survey-mailreminder-subject').attr("readonly", false);
			
			$('#save-incomplete-survey-reminder-mail-content').show();
			$('#save-incomplete-survey-reminder-mail-content-disabled').hide();
			
			$('#edit-incomplete-survey-reminder-mail-content-disabled').show();
			$(this).hide();
		});
		$('#save-incomplete-survey-reminder-mail-content').click(function() {
			$('#mailcategory').val('restartsurveymail');
			updateMailContent("mail-body-settings-form",'#save-incomplete-survey-reminder-mail-content');
			
			try { 
				$('#incomplete-survey-reminder-mailcontent').ckeditorGet().setReadOnly(true);
			} catch(e) {
			}
			$('#incomplete-survey-mailreminder-subject').attr("readonly", true);
			
			$(this).hide();
			$('#save-incomplete-survey-reminder-mail-content-disabled').show();

			$('#edit-incomplete-survey-reminder-mail-content').show();
			$('#edit-incomplete-survey-reminder-mail-content-disabled').hide();
		});
		$('#revert-incomplete-survey-reminder-mail').click(function(e) {
			e.stopPropagation();
			$('#overlay-main').show();
			$('#overlay-continue').show();
			$('#overlay-continue').html("Reset");
			$('#overlay-cancel').html("Cancel");
			$('#overlay-header').html("Reset Mail Template");
			$('#overlay-text').html("Are you sure you want to reset the mail template ?");
			$('#overlay-continue').attr("onclick", "revertMailContent('restartsurveymail','#revert-incomplete-survey-reminder-mail');");
		});
		
		
		//for survey reminder
		$('#reminder-interval').change(function() {
			$('#mailcategory').val('reminder-interval');
			if(validateReminderInterval('reminder-interval')) {
				updateReminderSettings("mail-body-settings-form");
			}
		});

		$('#st-reminder-on').click(function() {
			$('#mailcategory').val('reminder-needed');
			
			$('#reminder-needed-hidden').val('false');
			$('#st-reminder-off').show();
			$(this).hide();

			$('#reminder-interval').removeAttr("disabled");
			updateReminderSettings("mail-body-settings-form");
		});
		$('#st-reminder-off').click(function() {
			$('#mailcategory').val('reminder-needed');

			$('#reminder-needed-hidden').val('true');
			$('#st-reminder-on').show();
			$(this).hide();
			
			$('#reminder-interval').attr("disabled", true);
			updateReminderSettings("mail-body-settings-form");
		});
		
		//for social post reminder
		$('#post-reminder-interval').change(function() {
			$('#mailcategory').val('post-reminder-interval');
			if(validateReminderInterval('post-reminder-interval')) {
				updateReminderSettings("mail-body-settings-form");
			}
		});

		$('#post-reminder-on').click(function() {
			$('#mailcategory').val('post-reminder-needed');
			
			$('#post-reminder-needed-hidden').val('false');
			$('#post-reminder-off').show();
			$(this).hide();

			$('#post-reminder-interval').removeAttr("disabled");
			updateReminderSettings("mail-body-settings-form");
		});
		$('#post-reminder-off').click(function() {
			$('#mailcategory').val('post-reminder-needed');

			$('#post-reminder-needed-hidden').val('true');
			$('#post-reminder-on').show();
			$(this).hide();
			
			$('#post-reminder-interval').attr("disabled", true);
			updateReminderSettings("mail-body-settings-form");
		});

	});
</script>
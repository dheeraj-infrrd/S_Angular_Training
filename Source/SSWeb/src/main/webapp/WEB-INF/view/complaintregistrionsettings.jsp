<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${not empty complaintRegSettings }">
	<c:set value="${complaintRegSettings}" var="complaintRegSettings"></c:set>
</c:if>
<div class="hm-header-main-wrapper">
	<div class="container">
		<div class="hm-header-row clearfix">
			<div class="float-left hm-header-row-left">
				<spring:message code="label.title.complaintregsettings.key" />
			</div>
		</div>
	</div>
</div>

<div id="toast-container" class="toast-container">
	<span id="overlay-toast" class="overlay-toast"></span>
</div>


<div class="prof-main-content-wrapper margin-top-25 margin-bottom-25">
	<div class="container">
		<div class="complaint-cont">
			<form id="comp-reg-form" method="post">
				<!-- Mail Id Input -->
				<div class="bd-hr-form-item clearfix">
					<div class="float-left bd-frm-left"><spring:message code="label.complaintreg.mail.text" /></div>
					<div class="float-left bd-frm-right"><input class="bd-frm-rt-txt" type="text" name="mailId"
						value="${complaintRegSettings.mailId}"></div>
				</div>
				<div class="coml-checkbox-cont clearfix">
					<input type="checkbox" name="enabled" value="enable" class="hide">
					<div class="float-left">
						<div id="compl-checkbox" class="bd-check-img bd-check-img-checked float-right compl-checkbox"></div>
					</div>
					<div class="float-left compl-box-txt"><spring:message code="label.complaintreg.trigger.text" /></div>
					<!-- set the min rating -->
				</div>
				<div class="mood-text">
					<spring:message	code="label.complaintreg.rating.text" />
				</div>
				<div class="clearfix">
					<div class="float-left">
						<input type="text" name="rating" id="comp-rating-post"
							class="st-item-row-txt cursor-pointer dd-arrow-dn"
							autocomplete="off" value="${complaintRegSettings.rating}">
						<div class="st-dd-wrapper hide" id="st-dd-wrapper-min-post"></div>
					</div>
				</div>				
				<div class="mood-text">
					<spring:message code="label.complaintreg.or.text" />
				</div>
	
				<!-- Mood selection -->
				<div class="mood-text">
					<spring:message code="label.complaintreg.mood.text" />
				</div>
				<div class="clearfix">
					<div class="sq-smile-icn-container compl-input-cont opacity-red" data-mood="unpleasant">
						<div id="sad-smile" class="sq-smile-icn-wrapper sq-sad-smile"></div>
						<div class="sq-smile-icn-text sq-smile-sad-text float-left">
							<spring:message code="label.smile.sad.text" />
						</div>
					</div>
					
					<div class="mood-text">
						<spring:message code="label.complaintreg.or.text" />
					</div>
				
					<div class="sq-smile-icn-container compl-input-cont opacity-red" data-mood="ok">
						<div id="neutral-smile" class="sq-smile-icn-wrapper sq-neutral-smile"></div>
						<div class="sq-smile-icn-text sq-smile-neutral-text float-left">
							<spring:message code="label.smile.neutral.text" />
						</div>
					</div>
				</div>
				<input type="hidden" id="survey-mood" name="mood"/> 
				<div id="comp-reg-form-submit" class="bd-btn-save cursor-pointer">Save</div>
			</form>
		</div>
	</div>
</div>


<!-- window to display reviews flagged as under resolution  
<div>
	<div>
		<spring:message code="label.complaintres.review.text" />
	</div>
	<div><spring:message code="label.complaintres.reviewdesc.text" /></div>
	
	<c:if test="${not empty complaintRegSettings.mailId }">
		<div><spring:message code="label.complaintres.criteria.text" /></div>
		<div><spring:message code="label.complaintreg.rating.text" /> : ${complaintRegSettings.rating}</div>
		<div><spring:message code="label.complaintreg.mood.text" /> : ${complaintRegSettings.mood}</div>
	</c:if>
	<div id="sur-under-res-list">
		<!-- Javascript will display reviews under Complaint resolution 
	</div>
</div>
-->

<script>
	$(document).ready(function() {
		autoAppendRatingDropdown('#st-dd-wrapper-min-post',
				"st-dd-item st-dd-item-min-post", 5, 0, 0.5);
		$('#comp-rating-post').off('click');
		$('#comp-rating-post').on('click', function() {
			$('#st-dd-wrapper-min-post').slideToggle(200);
		});
		$('.sq-smile-icn-container').off('click');
		$('.sq-smile-icn-container').on('click', function() {
			var mood = $(this).attr("data-mood");
			var currentMood = $('#survey-mood').val();
			
			//check for toggle state
			
			//set the mood
			if($(this).hasClass('opacity-red')) {
				$('#survey-mood').val(mood);
				if(mood.toLowerCase() == "ok") {
					$('.sq-smile-icn-container').removeClass('opacity-red');
				} else if (mood.toLowerCase() == "unpleasant") {
					$(this).removeClass('opacity-red');
				}
			} else {
				$('#survey-mood').val('');
				
				if(mood.toLowerCase() == "ok") {
					$('.sq-smile-icn-container').addClass('opacity-red');
				} else if (mood.toLowerCase() == "unpleasant") {
					if(currentMood == "ok") {
						$('.sq-smile-icn-container[data-mood="ok"]').addClass('opacity-red');
						$('#survey-mood').val(mood);
					} else if(currentMood == "unpleasant"){
						$(this).addClass('opacity-red');
					}
				}
			}
		});
		
	});
</script>
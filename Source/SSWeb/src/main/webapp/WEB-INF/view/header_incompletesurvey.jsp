<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:if test="${not empty incompleteSurveys}">
	<c:forEach var="survey" items="${incompleteSurveys}">
		<c:choose>
			<c:when test="${not empty survey.customerLastName}">
				<c:set value="${survey.customerFirstName} ${survey.customerLastName}" var="customerName"></c:set>
			</c:when>
			<c:otherwise>
				<c:set value="${survey.customerFirstName}" var="customerName"></c:set>
			</c:otherwise>
		</c:choose>
		<c:set var="customerNameParts" value="${fn:split(customerName, ' ')}"></c:set>
		<div class="dash-lp-item clearfix" data-sur-iden="${survey.surveyPreIntitiationId}" 
			data-iden="sur-pre-${survey.surveyPreIntitiationId}">
			<div class="float-left sur-icn-checkbox sb-q-chk-no"></div>
			<div class="float-left dash-lp-txt">
				<div><spring:message code="label.customername.key"/>
				 <span class="text-capitalize">- ${customerNameParts[0]} 
				 <c:if test="${fn:length(customerNameParts) > 1}">
				 	${fn:substring(customerNameParts[1], 0, 1)}
				 </c:if>
				 </span>
				 </div>
				<div><spring:message code="label.custemail.key"/> - ${survey.customerEmailId}</div>
				<div><spring:message code="label.agentname.key"/> - ${survey.agentName}</div>
				<div><spring:message code="label.remindercount.key"/> - ${survey.reminderCounts}</div>
				<div class="font-11 opensanslight date-inc-sur" data-value='<fmt:formatDate value="${survey.modifiedOn}" pattern="yyyy-MM-dd-H-mm-s-S"/>'>
				</div>
			</div>
			<%-- <div class="float-right dash-icn-close cursor-pointer"
				title="Cancel Survey Reminder"
				onclick="removeIncompleteSurveyRequest(${survey.surveyPreIntitiationId })"></div> --%>
			<div title="Resend Survey"
				data-surveypreinitiationid="${survey.surveyPreIntitiationId }"
				data-custname="${survey.customerFirstName} ${survey.customerLastName}"
				data-agentid="${survey.agentId}"
				data-agentname="${survey.agentName}"
				data-custemail="${survey.customerEmailId}"
				class="float-right dash-lp-rt-img cursor-pointer hide"></div>
		</div>
	</c:forEach>
</c:if>
<script>
	$(document).ready(function() {
		$('.opensanslight.date-inc-sur').each(function(index, currentElement) {
			var dateStr = $(this).attr('data-value');
			$(this).html(getDateStrToUTC(dateStr));
		});
		
		$('.sur-icn-checkbox').each(function(index, currentElement) {
			var selectedSurveys = $('#icn-sur-popup-cont').data('selected-survey');
			if (selectedSurveys == undefined) {
				return;
			} else {
				var surveyId = $(this).closest('.dash-lp-item').attr('data-sur-iden');
				var index = selectedSurveys.indexOf(surveyId);
				if (index > -1) {
					$(this).addClass('sb-q-chk-yes').removeClass('sb-q-chk-no');
				}
			}
		});
		
		$('.sur-icn-checkbox').on('click',function(e){
			var selectedSurveys = $('#icn-sur-popup-cont').data('selected-survey');
			if (selectedSurveys == undefined) {
				selectedSurveys = new Array();
			}
			var surveyId = $(this).closest('.dash-lp-item').attr('data-sur-iden');
			var index = selectedSurveys.indexOf(surveyId);
			if ($(this).hasClass('sb-q-chk-no')){
				$(this).addClass('sb-q-chk-yes').removeClass('sb-q-chk-no');
				if (index < 0){
					selectedSurveys.push(surveyId);
				}
			} else {
				$(this).addClass('sb-q-chk-no').removeClass('sb-q-chk-yes');
				if (index > -1) {
					selectedSurveys.splice(index, 1);
				}
			}
			
			$('#icn-sur-popup-cont').data('selected-survey', selectedSurveys);
			if (selectedSurveys != undefined && selectedSurveys.length > 0 ) {
				$('#del-mult-sur-icn').addClass('mult-sur-icn-active');
				$('#resend-mult-sur-icn').addClass('mult-sur-icn-active');
			} else {
				$('#del-mult-sur-icn').removeClass('mult-sur-icn-active');
				$('#resend-mult-sur-icn').removeClass('mult-sur-icn-active');
			}
		});
	});
</script>
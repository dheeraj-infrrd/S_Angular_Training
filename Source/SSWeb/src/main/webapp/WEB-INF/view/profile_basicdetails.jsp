<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:set value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal}" var="user" />
<c:if test="${not empty profile && not empty profile.contact_details}">
	<c:set value="${profile.contact_details}" var="contactdetail"></c:set>
	<c:set value="${profile.vertical}" var="companyvertical"></c:set>
	<c:set value="${profile.lockSettings}" var="lock"></c:set>
</c:if>
<div class="float-left lp-edit-wrapper clearfix float-left">
						<c:choose>
							<c:when	test="${parentLock.isDisplayNameLocked && not user.agent}">
								<input id="prof-name" class="prof-name prof-name-txt prof-edditable" value="${contactdetail.name}" readonly>
								<div id="prof-name-lock" data-state="locked" data-control="parent" class="lp-edit-locks float-left lp-edit-locks-locked"></div>
							</c:when>
							<c:when	test="${parentLock.isDisplayNameLocked && user.agent}">
								<input id="prof-name" class="prof-name prof-name-txt prof-edditable" value="${contactdetail.name}" readonly>
								<div id="prof-name-lock" data-state="locked" data-control="parent" class="hide lp-edit-locks float-left lp-edit-locks-locked"></div>
							</c:when>
							<c:when	test="${not parentLock.isDisplayNameLocked && user.agent}">
								<input id="prof-name" class="prof-name prof-name-txt prof-edditable" value="${contactdetail.name}">
								<div id="prof-name-lock" data-state="unlocked" data-control="user" class="hide lp-edit-locks float-left"></div>
							</c:when>
							<c:when	test="${not parentLock.isDisplayNameLocked && lock.isDisplayNameLocked && not user.agent}">
								<input id="prof-name" class="prof-name prof-name-txt prof-edditable" value="${contactdetail.name}">
								<div id="prof-name-lock" data-state="unlocked" data-control="user" class="lp-edit-locks float-left lp-edit-locks-locked"></div>
							</c:when>
							<c:when	test="${not parentLock.isDisplayNameLocked && not lock.isDisplayNameLocked && not user.agent}">
								<input id="prof-name" class="prof-name prof-name-txt prof-edditable" value="${contactdetail.name}">
								<div id="prof-name-lock" data-state="unlocked" data-control="user" class="lp-edit-locks float-left"></div>
							</c:when>
						</c:choose>
					</div>
					<div class="prof-address">
						<div class="prof-addline1 prof-edditable">${profile.vertical}</div>
						
						<input id="prof-title" class="prof-addline2 prof-edditable" value="${profile.contact_details.title}" placeholder='<spring:message code="label.profiletitle.placeholder.key"/>'>
						<div id="prof-title-lock" data-state="unlocked" data-control="user" class="hide lp-edit-locks float-left"></div>
					</div>
					<div id="prof-rating-review-count" class="prof-rating clearfix">
						<div class="st-rating-wrapper maring-0 clearfix float-left" id="rating-avg-comp">
							<div class="rating-star icn-full-star"></div>
							<div class="rating-star icn-full-star"></div>
							<div class="rating-star icn-no-star"></div>
							<div class="rating-star icn-no-star"></div>
							<div class="rating-star icn-no-star"></div>
						</div>
						<div class="float-left review-count-left" id="prof-company-review-count"></div>
					</div>
					
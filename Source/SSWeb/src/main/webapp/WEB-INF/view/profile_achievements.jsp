<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:if test="${not empty profileSettings && not empty profileSettings.achievements}">
	<c:set value="${profileSettings.achievements}" var="achievements"></c:set>
</c:if>

<c:choose>
	<c:when test="${not empty achievements}">
		<c:forEach items="${achievements}" var="achievement">
			<input class="lp-ach-row lp-row clearfix prof-edditable-sin-agent" value="${achievement.achievement}">
			<div class="float-right lp-ach-item-img" data-type="achievement"></div>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div><spring:message code="label.achievement.empty.key"></spring:message></div>
	</c:otherwise>
</c:choose>
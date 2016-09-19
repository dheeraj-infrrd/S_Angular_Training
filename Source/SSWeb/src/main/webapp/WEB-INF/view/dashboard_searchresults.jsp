<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty results}">
	<c:forEach var="result" items="${results}">
		<c:choose>
			<c:when test="${searchColumn == 'regionName'}">
				<div class="dsh-res-display" data-attr="${result.regionId}">${result.regionName}</div>
			</c:when>
			<c:when test="${searchColumn == 'branchName'}">
				<div class="dsh-res-display" data-attr="${result.branchId}">${result.branchName}</div>
			</c:when>
			<c:when test="${searchColumn == 'displayName'}">
				<div class="dsh-res-display" data-attr="${result.userId}">${result.displayName}</div>
			</c:when>
			<c:when test="${searchColumn == 'company'}">
			    <c:choose>
                    <c:when test="${result.status == 0 || result.status == 11}">
				        <div class="dsh-res-display" data-attr="${result.companyId}">${result.company} - inactive</div>
                     </c:when>
                    <c:otherwise>
                        <div class="dsh-res-display" data-attr="${result.companyId}">${result.company}</div>
                    </c:otherwise>
			    </c:choose>
			</c:when>
		</c:choose>
	</c:forEach>
</c:if>

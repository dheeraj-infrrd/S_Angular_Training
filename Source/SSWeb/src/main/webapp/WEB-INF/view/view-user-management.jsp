<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><spring:message code="label.title.registeruser.key"/></title>
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
                <div class="float-left hm-header-row-left text-center">User Management</div>
            </div>
        </div>
    </div>

    <div class="container v-um-container">
        <div class="v-um-header clearfix">
            <div class="v-um-hdr-left float-left">Browse Users</div>
            <div class="v-um-hdr-right float-right">
                <input class="v-um-inp" placeholder="Search User">
            </div>
        </div>
        <div class="v-um-tbl-wrapper">
            <table class="v-um-tbl">
                <tr class="u-tbl-header">
                    <td class="v-tbl-uname">Username</td>
                    <td class="v-tbl-email">Email Address</td>
                    <td class="v-tbl-rgn-adm text-center">Region</br/>Admin</td>
                    <td class="v-tbl-of-adm text-center">Office<br/>Admin</td>
                    <td class="v-tbl-ln-of text-center">Loan<br/>Officer</td>
                    <td class="v-tbl-mail"></td>
                    <td class="v-tbl-online"></td>
                    <td class="v-tbl-rem"></td>
                    <td class="v-tbl-edit"></td>
                </tr>
                <tr class="u-tbl-row">
                    <td class="v-tbl-uname">Annalisa Detrick</td>
                    <td class="v-tbl-email">annalisa@detrick.com</td>
                    <td class="v-tbl-rgn-adm v-tbl-icn v-icn-tick"></td>
                    <td class="v-tbl-of-adm v-tbl-icn v-icn-tick"></td>
                    <td class="v-tbl-ln-of v-tbl-icn v-icn-tick"></td>
                    <td class="v-tbl-mail v-tbl-icn v-icn-fmail"></td>
                    <td class="v-tbl-online v-tbl-icn v-icn-onl v-icn-onl-off"></td>
                    <td class="v-tbl-rem v-tbl-icn v-icn-rem-user"></td>
                    <td class="v-tbl-edit v-tbl-icn v-icn-edit-user"></td>
                </tr>
                <tr class="u-tbl-row">
                    <td class="v-tbl-uname">Annalisa Detrick</td>
                    <td class="v-tbl-email">annalisa@detrick.com</td>
                    <td class="v-tbl-rgn-adm v-tbl-icn v-icn-tick"></td>
                    <td class="v-tbl-of-adm v-tbl-icn v-icn-tick"></td>
                    <td class="v-tbl-ln-of v-tbl-icn v-icn-tick"></td>
                    <td class="v-tbl-mail v-tbl-icn v-icn-fmail"></td>
                    <td class="v-tbl-online v-tbl-icn v-icn-ofl v-icn-onl-off"></td>
                    <td class="v-tbl-rem v-tbl-icn v-icn-rem-user"></td>
                    <td class="v-tbl-edit v-tbl-icn v-icn-edit-user"></td>
                </tr>
<!--
                <tr class="u-tbl-row u-tbl-row-sel">
                        
                </tr>
-->
            </table>
        </div>
    </div>


<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script>
    $(document).ready(function() {
        
        $(document).on('click','.v-tbl-icn',function(e){
            e.stopPropagation();
        });
        
        $(document).on('click','.v-ed-txt-dd',function(){
            $(this).next('.v-ed-dd-wrapper').slideToggle(200);
        });
        
        $(document).on('click','.v-ed-dd-item',function(e){
            e.stopPropagation();
            $(this).parent().prev('.v-ed-txt-dd').val($(this).html());
            $(this).parent().slideToggle(200);
        });
        
        $(document).on('click','.u-tbl-row',function(){
            if($(this).hasClass('u-tbl-row-sel')){
                $(this).removeClass('u-tbl-row-sel');
                $(this).next('.u-tbl-row').hide();
            }else{
                var editRow = $('<tr class="u-tbl-row u-tbl-row-sel">');
                $(this).after(editRow);
                $(this).addClass('u-tbl-row-sel');
            }
        });
        
    });
</script>

</body>
</html>
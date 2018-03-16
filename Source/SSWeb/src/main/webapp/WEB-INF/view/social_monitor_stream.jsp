<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div id="bulk-options-popup" class="hide bulk-action-popup">
	<button type="button" class="close bulk-options-dismiss" id="dismiss-bulk-options">&times;</button>
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 bulk-action-edit-container">
			<div class="bulk-action-hdr">Bulk Actions</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div id="bulk-send-mail-post" class="col-lg-3 col-md-3 col-sm-3 col-xs-3 stream-post-mail-note stream-post-mail-note-active">Send Email</div>
				<div id="bulk-private-note-post" class="col-lg-3 col-md-3 col-sm-3 col-xs-3 stream-post-mail-note">Private Note</div>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<textarea class="form-control stream-post-textbox" rows="3" id="bulk-edit-txt-box" placeholder="Send an email message to offending user or take a private note"></textarea>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 stream-actions-btn-container">
			<div id="bulk-macro-dropdown" class="col-lg-5 col-md-5 col-sm-5 col-xs-5 macro-dropdown">
				<img src="${initParam.resourcesPath}/resources/images/flash.png" class="macro-dropdown-icn">
				 Apply Macro 
				<img id="bulk-mac-chevron-down" src="${initParam.resourcesPath}/resources/images/chevron-down.png" class="macro-dropdown-chevron">
				<img id="bulk-mac-chevron-up" src="${initParam.resourcesPath}/resources/images/chevron-up.png" class="hide macro-dropdown-chevron">
			</div>
			<div id="bulk-edit-unflag" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 stream-actions-btn stream-action-unflag bulk-act-btn">
				Unflag
			</div>
			<div id="bulk-edit-flag" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 stream-actions-btn stream-action-flag bulk-act-btn">
				Flag
			</div>
			<div id="bulk-edit-esc" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 stream-actions-btn stream-action-esc bulk-act-btn">
				Escalate
			</div>
			<div id="bulk-edit-res" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 stream-actions-btn stream-action-res bulk-act-btn">
				Resolve
			</div>
			<div id="bulk-edit-sub" class="col-lg-2 col-md-2 col-sm-2 col-xs-2 stream-actions-btn stream-action-submit bulk-act-btn">
				Submit
			</div>
			<div id="bulk-macro-options" class="hide float-left macro-options">
					
			</div>
			</div>		
		</div>
</div>

<div class="hm-header-main-wrapper hm-hdr-bord-bot soc-mon-hdr">
	
	<div class="container">
		<div class="hm-header-row clearfix">
			<div class="float-left hm-header-row-left hr-dsh-adj-lft soc-mon-hdr-txt">
				<spring:message code="label.social.monitor.key" /> - <spring:message code="label.social.monitor.stream.key" />
			</div>
			<div class="float-right hm-header-right text-center soc-mon-btn" onclick="javascript:showMainContent('./showsocialmonitorpage.do')">
					<spring:message code="label.edit.monitors.key" />
			</div>
			<div class="float-right hm-header-right text-center soc-mon-btn" onclick="">
					<spring:message code="label.social.monitor.reports.key" />
			</div>
			<div class="float-right hm-header-right text-center soc-mon-btn" onclick="javascript:showMainContent('./showsocialmonitormacropage.do')">
					<spring:message code="label.edit.macros.key" />
			</div>
		</div>
	</div>
</div>

<div class="hm-header-main-wrapper hm-hdr-bord-bot soc-mon-sub-hdr">
	<div class="container">
		<div class="hm-header-row clearfix">
			<div class="v-um-hdr-right v-um-hdr-search float-right soc-mon-search-bar">
				<input id="search-post" class="v-um-inp soc-mon-inp" placeholder="<spring:message code="label.social.monitor.search.key" />">
				<span id="soc-mon-stream-search-icn" class="um-search-icn"></span>
				<div id="soc-mon-stream-search-clr" class="um-clear-input-icn hide" title="clear"></div>
			</div>
			<div class="hm-header-left text-center float-left" style="margin-top: 12px;">
				<div id="stream-usr-selection" class="dash-btn-dl-sd-admin report-selector soc-mon-dropdown" >
					<jsp:include page="social_monitor_stream_user_dropdown.jsp"></jsp:include>
				</div>
				<div id="stream-seg-selection" class="dash-btn-dl-sd-admin report-selector soc-mon-dropdown" >
					<jsp:include page="social_monitor_stream_segment_dropdown.jsp"></jsp:include>	
				</div>
				<div id="stream-feed-selection" class="dash-btn-dl-sd-admin report-selector soc-mon-dropdown" >
					<jsp:include page="social_monitor_stream_feed_dropdown.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</div>
</div>

<input type="hidden" id="selected-post-ids" data-post-ids='[]'>
<div class="dash-wrapper-main">
	<div class="dash-container container">
		<div id="stream-tabs" class="clearfix" data-flagged=false data-status="none">
			<div id="soc-mon-stream-tab" class="soc-mon-tab soc-mon-stream-active" data-disabled=true><img id="stream-inactive" src="${initParam.resourcesPath}/resources/images/stream-gray.png"  class="hide soc-mon-icns"><img id="stream-active" src="${initParam.resourcesPath}/resources/images/stream-blue.png" class="soc-mon-icns">Stream</div>
			<div id="soc-mon-alerts-tab" class="soc-mon-tab" data-disabled=false><img id="alert-inactive" src="${initParam.resourcesPath}/resources/images/flag-gray.png" class="soc-mon-icns"><img id="alert-active" src="${initParam.resourcesPath}/resources/images/flag-yellow.png" class="hide soc-mon-icns">Alerts</div>
			<div id="soc-mon-escalated-tab" class="soc-mon-tab" data-disabled=false><img id="esc-inactive" src="${initParam.resourcesPath}/resources/images/escalated-gray.png" class="soc-mon-icns"><img id="esc-active" src="${initParam.resourcesPath}/resources/images/escalated-orange.png" class="hide soc-mon-icns">Escalations</div>
			<div id="soc-mon-resolved-tab" class="soc-mon-tab" data-disabled=false><img id="res-inactive" src="${initParam.resourcesPath}/resources/images/verified-gray.png" class="soc-mon-icns"><img id="res-active" src="${initParam.resourcesPath}/resources/images/verified-green.png" class="hide soc-mon-icns">Resolutions</div>
		</div>
	</div>
</div>

<form id="macro-form-apply">
	<input type="hidden" id="macro-form-post-id" class="macro-form-post-id" name="macro-form-post-id" value="">
	<input type="hidden" id="macro-form-flagged" class="macro-form-flagged" name="macro-form-flagged" value="false">
	<input type="hidden" id="macro-form-status" name="macro-form-status" class="macro-form-status" value="NEW">
	<input type="hidden" id="macro-form-text-act-type" class="macro-form-text-act-type" name="macro-form-text-act-type" value="SEND_EMAIL">
	<input type="hidden" id="macro-form-macro-id" class="macro-form-macro-id" name="macro-form-macro-id" value="">
	<input type="hidden" id="macro-form-text" class="macro-form-text" name="macro-form-text" value="">
</form>

<form id="bulk-actions-apply">
	<input type="hidden" id="form-post-id" class="form-post-id" name="form-post-id" value="">
	<input type="hidden" id="form-flagged" class="form-flagged" name="form-flagged" value="false">
	<input type="hidden" id="form-status" name="form-status" class="form-status" value="NEW">
	<input type="hidden" id="form-text-act-type" class="form-text-act-type" name="form-text-act-type" value="SEND_EMAIL">
	<input type="hidden" id="form-macro-id" class="form-macro-id" name="form-macro-id" value="">
	<input type="hidden" id=form-post-textbox class="form-post-textbox" name="form-post-textbox" value="">
</form>

<div id="soc-mon-stream" class="dash-wrapper-main">
	<div id="stream-dash" class="hide" ></div>
	<div class="dash-container container">
		<div class="hm-header-main-wrapper hm-hdr-bord-bot soc-mon-sub-hdr">
			<div class="container" style="width:100%">
				<div class="hm-header-row clearfix">
					<div class="hm-header-left text-center float-left">
						<img id="stream-unchecked" src="${initParam.resourcesPath}/resources/images/check-no.png"  class="float-left stream-checkbox">
						<img id="stream-checked" src="${initParam.resourcesPath}/resources/images/check-yes.png"  class="hide float-left stream-checkbox">
						<div id="stream-bulk-actions" class="float-left stream-bulk-actions">
							<div class="bulk-actions-select">Bulk Actions <img src="${initParam.resourcesPath}/resources/images/chevron-down.png" id="chevron-down" class="float-right bulk-actions-dropdown-img"><img id="chevron-up" src="${initParam.resourcesPath}/resources/images/chevron-up.png" class="hide float-right bulk-actions-dropdown-img"></div>
							<div id="stream-bulk-action-options" class="hide float-left bulk-actions-options">
								<div id="stream-bulk-edit" class="bulk-option">Edit</div>
								<div id="stream-bulk-unflag" class="bulk-option">Unflag</div>
								<div id="stream-bulk-flag" class="bulk-option">Flag</div>
								<div id="stream-bulk-esc" class="bulk-option">Escalate</div>
								<div id="stream-bulk-res" class="bulk-option">Resolve</div>	
							</div>
						</div>
					</div>
					<div id="stream-pagination" class="float-right soc-mon-pagination" data-startIndex=0 data-count=0>
						<div class="soc-mon-pag-text"><span id="stream-item-count" class="soc-mon-bold-text">7</span> items</div>
						<div id="stream-start-page" class="soc-mon-pag"><img src="${initParam.resourcesPath}/resources/images/chevron-double-left.png" class="soc-mon-pag-icn"></div>
						<div id="stream-start-page-active" class="hide soc-mon-pag-active"><img src="${initParam.resourcesPath}/resources/images/chevron-double-left-blue.png" class="soc-mon-pag-icn"></div>
						<div id="stream-prev-page" class="soc-mon-pag"><img src="${initParam.resourcesPath}/resources/images/chevron-left.png"  class="soc-mon-pag-icn"></div>
						<div id="stream-prev-page-active" class="soc-mon-pag-active hide"><img src="${initParam.resourcesPath}/resources/images/chevron-left-blue.png" class="soc-mon-pag-icn"></div>
						<div class="soc-mon-pag-text"><span id="stream-page-no" class="soc-mon-bold-text">1</span> of <span id="stream-page-count" class="soc-mon-bold-text">1</span></div>
						<div id="stream-next-page" class="soc-mon-pag"><img src="${initParam.resourcesPath}/resources/images/chevron-right.png" class="soc-mon-pag-icn"></div>
						<div id="stream-next-page-active" class="soc-mon-pag-active hide"><img src="${initParam.resourcesPath}/resources/images/chevron-right-blue.png" class="soc-mon-pag-icn"></div>
						<div id="stream-end-page" class="soc-mon-pag"><img src="${initParam.resourcesPath}/resources/images/chevron-double-right.png" class="soc-mon-pag-icn"></div>
						<div id="stream-end-page-active" class="soc-mon-pag-active hide"><img src="${initParam.resourcesPath}/resources/images/chevron-double-right-blue.png" class="soc-mon-pag-icn"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div  id="empty-stream" class="dash-container container">
		<span class="incomplete-trans-span stream-alert">No posts found for the stream</span>
	</div>
	<div  id="stream-posts" class="dash-container container">
		<div class="dash-stats-wrapper bord-bot-dc clearfix stream-container"  >
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 soc-mon-post-container bottom-padding-stream">
					<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
						<img id="stream-unchecked-1" src="${initParam.resourcesPath}/resources/images/check-no.png" class="float-left stream-checkbox soc-mon-post-checkbox">
						<img id="stream-checked-1" src="${initParam.resourcesPath}/resources/images/check-yes.png" class="hide float-left stream-checkbox soc-mon-post-checkbox">
						<img id="stream-unflagged-icn-1" src="${initParam.resourcesPath}/resources/images/flag-gray.png" class="hide float-left soc-mon-post-icn">
						<img id="stream-flagged-icn-1" src="${initParam.resourcesPath}/resources/images/flag-yellow.png" class="hide float-left soc-mon-post-icn">
						<img id="stream-esc-icn-1" src="${initParam.resourcesPath}/resources/images/escalated-orange.png" class="hide float-left soc-mon-post-icn">
						<img id="stream-res-icn" src="${initParam.resourcesPath}/resources/images/verified-green.png" class="float-left soc-mon-post-icn">
					</div>
					<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 soc-mon-post-details">
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 float-left soc-mon-post-prof-pic-div">
							<img id="stream-res-icn" src="https://s3-us-west-1.amazonaws.com/agent-survey/dev/userprofilepics/d-f02172c39500454b75eee46f93c5cdc08cc2b2c396cd24325e9fc6436b8bd0e3b5eb57d493124683566073755b907e0e665734b42b3b03ae47073bcb61f5b3a1.jpg" class="float-left soc-mon-prof-pic">
							<img id="stream-res-icn" src="${initParam.resourcesPath}/resources/images/ss-icon-small-facebook.png" class=" float-left soc-mon-prof-pic-media">
						</div>
						<div class="col-lg-7 col-md-7 col-sm-7 col-xs-7 float-left" >
							<div class="row soc-mon-user-name">
								Loren Bronczyk
							</div>
							<div class="row soc-mon-post-media">
								Facebook Business Page
							</div>
							<div class="row soc-mon-post-date">
								November 23 at 9:01am
							</div>
						</div>
						<div class="col-lg-3 col-md-2 col-sm-2 col-xs-2 float-left soc-mon-post-dup">
							<img id="stream-unflagged-icn" src="${initParam.resourcesPath}/resources/images/duplicates.png" class="float-left soc-mon-post-dup-icn">
							<div class="soc-mon-post-dup-num float-left">Manage 15 duplicates</div>
						</div>
					</div>
					<div id="stream-post-text-1" class="col-lg-10 col-md-10 col-sm-10 col-xs-10 stream-post-text float-right">
						The best vacations are true departures from everyday life. To accomplish that feeling, however, you need to leave your cares behind. Here are some tips to help you do that whenever you leave your home unoccupied for  several days. #ThursdayThought 
					</div>
					<div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 float-right stream-post-pic-div" >
						<img id="stream-post-pic-1" src="${initParam.resourcesPath}/resources/images/soc-mon-img-1.png" class="float-left stream-post-pic">
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-6 bottom-padding-stream">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div id="send-mail-post-1" class="col-lg-3 col-md-3 col-sm-3 col-xs-3 stream-post-mail-note stream-post-mail-note-active">Send Email</div>
						<div id="private-note-post-1" class="col-lg-3 col-md-3 col-sm-3 col-xs-3 stream-post-mail-note">Private Note</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<textarea class="form-control stream-post-textbox" rows="3" id="post-1-textbox" placeholder="Send an email message to offending user or take a private note"></textarea>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 stream-actions-btn-container">
						<div id="stream-macro-small-1" class="hide col-lg-5 col-md-5 col-sm-5 col-xs-5 macro-dropdown">
							<img src="${initParam.resourcesPath}/resources/images/flash.png" class="macro-dropdown-icn">
						 	 Apply Macro 
							<img id="mac-chevron-down-small-1" src="${initParam.resourcesPath}/resources/images/chevron-down.png" class="macro-dropdown-chevron">
							<img id="mac-chevron-up-small-1" src="${initParam.resourcesPath}/resources/images/chevron-up.png" class="hide macro-dropdown-chevron">
						</div>
						<div id="stream-macro-1" class="col-lg-6 col-md-6 col-sm-6 col-xs-6 macro-dropdown">
							<img src="${initParam.resourcesPath}/resources/images/flash.png" class="macro-dropdown-icn">
							 Apply Macro 
							<img id="mac-chevron-down-1" src="${initParam.resourcesPath}/resources/images/chevron-down.png" class="macro-dropdown-chevron">
							<img id="mac-chevron-up-1" src="${initParam.resourcesPath}/resources/images/chevron-up.png" class="hide macro-dropdown-chevron">
						</div>
						<div class="hide col-lg-2 col-md-2 col-sm-2 col-xs-2 stream-actions-btn stream-action-unflag">
							Unflag
						</div>
						<div class="hide col-lg-2 col-md-2 col-sm-2 col-xs-2 stream-actions-btn stream-action-flag">
							Flag
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 stream-actions-btn stream-action-esc">
							Escalate
						</div>
						<div class="hide col-lg-2 col-md-2 col-sm-2 col-xs-2 stream-actions-btn stream-action-res">
							Resolve
						</div>
						<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 stream-actions-btn stream-action-submit">
							Submit
						</div>
						<div id="macro-options-1" class="hide float-left macro-options">
								<div id="macro-1" class="macro-opt">Post has questionable language - Flag</div>
								<div id="macro-2" class="macro-opt">Request post must be Removed - Escalate</div>
								<div id="macro-3" class="macro-opt">Confirm post Removed - Resolve</div>
						</div>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 float-left stream-action-container">
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 float-left stream-action-icn">
								<img id="" src="${initParam.resourcesPath}/resources/images/verified-green.png" class="float-left">
							</div>
							<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 float-left">
								<div class="row stream-action-date">
									Dec 7, 2017 7:59AM
								</div>
								<div class="row stream-action-text">
									Post was <span class="stream-action-text-imp">Resolved</span> by <span class="stream-action-text-imp">New American Funding Admin</span>
								</div>
							</div>	
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 float-left stream-action-container">
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 float-left stream-action-icn">
							</div>
							<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 float-left">
								<div class="row stream-action-date">
									Dec 9, 2017 7:59AM - New American Funding Admin
								</div>
								<div class="row stream-action-text">
									I have verified that the post was removed
								</div>
							</div>	
						</div>	
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 float-left stream-action-container stream-action-mail">
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 float-left stream-action-icn">
								<img id="" src="${initParam.resourcesPath}/resources/images/email-18px.png" class="float-left">
							</div>
							<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 float-left">
								<div class="row stream-action-date">
									Dec 7, 2017 7:59AM - New American Funding Admin
								</div>
								<div class="row stream-action-text">
									This post must be taken down immediatly. Please reply to confirm once it has been removed.
								</div>
							</div>	
						</div>
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 float-left stream-action-container">
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 float-left stream-action-icn">
								<img id="" src="${initParam.resourcesPath}/resources/images/escalated-gray.png" class="float-left">
							</div>
							<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 float-left">
								<div class="row stream-action-date">
									Dec 7, 2017 7:59AM
								</div>
								<div class="row stream-action-text">
									Post was <span class="stream-action-text-imp">Escalated</span>
								</div>
							</div>	
						</div>	
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 float-left stream-action-container">
							<div class="col-lg-1 col-md-1 col-sm-1 col-xs-1 float-left stream-action-icn">
								<img id="" src="${initParam.resourcesPath}/resources/images/flag-gray.png" class="float-left">
							</div>
							<div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 float-left">
								<div class="row stream-action-date">
									Dec 7, 2017 7:59AM
								</div>
								<div class="row stream-action-text">
									Post was <span class="stream-action-text-imp">Flagged</span> for matching <span class="stream-action-text-imp">best</span>
								</div>
							</div>	
						</div>		
					</div>
				</div>
			</div>
	</div>
</div>
<script>
/* var  is_dashboard_loaded = window.is_dashboard_loaded; */
$(document).ready(function() {
	$(document).attr("title", "Social Monitor");
	getMacrosForStream();
	getSegmentsByCompanyId();
	getStreamPosts(0,'none',false);
});
</script>
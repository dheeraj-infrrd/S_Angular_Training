<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div id="welcome-popup-invite" class="welcome-popup-wrapper">
	<div class="welcome-popup-hdr-wrapper clearfix">
		<div class="float-left wc-hdr-txt"><spring:message code="label.sendsurvey.key" /></div>
		<div class="float-right popup-close-icn wc-skip-btn wc-final-skip"></div>
	</div>
	<div class="welcome-popup-body-wrapper clearfix">
		<div class="wc-popup-body-hdr"><spring:message code="label.happyreviews.key" /></div>
		<div id="wc-review-table" class="wc-popup-body-cont wc-review-table-cont">
			<div id="wc-review-table-inner" class="wc-review-table">
				<div class="wc-review-tr wc-review-hdr clearfix">
					<div class="wc-review-th1 float-left"><spring:message code="label.agentname.key" /></div>
					<div class="wc-review-th2 float-left"><spring:message code="label.custname.key" /></div>
					<div class="wc-review-th3 float-left"><spring:message code="label.emailid.key" /></div>
					<div class="wc-review-th4 float-left"></div>
				</div>
				<div class="wc-review-tr clearfix">
					<div class="wc-review-tc1 float-left pos-relative"><input data-name="agent-name" class="wc-review-input wc-review-agentname"></div>
					<div class="wc-review-tc2 float-left"><input class="wc-review-input wc-review-custname"></div>
					<div class="wc-review-tc3 float-left"><input class="wc-review-input wc-review-email"></div>
					<div class="wc-review-tc4 float-left"><div class="wc-review-rmv-icn hide"></div></div>
				</div>
				<div class="wc-review-tr clearfix">
					<div class="wc-review-tc1 float-left pos-relative"><input data-name="agent-name" class="wc-review-input wc-review-agentname"></div>
					<div class="wc-review-tc2 float-left"><input class="wc-review-input wc-review-custname"></div>
					<div class="wc-review-tc3 float-left"><input class="wc-review-input wc-review-email"></div>
					<div class="wc-review-tc4 float-left"><div class="wc-review-rmv-icn"></div></div>
				</div>
				<div class="wc-review-tr clearfix">
					<div class="wc-review-tc1 float-left pos-relative"><input data-name="agent-name" class="wc-review-input wc-review-agentname"></div>
					<div class="wc-review-tc2 float-left"><input class="wc-review-input wc-review-custname"></div>
					<div class="wc-review-tc3 float-left"><input class="wc-review-input wc-review-email"></div>
					<div class="wc-review-tc4 float-left"><div class="wc-review-rmv-icn"></div></div>
				</div>
			</div>
		</div>
	</div>
	<div class="wc-btn-row clearfix">
		<div class="wc-btn-col float-left">
			<div id="wc-skip-send-survey" class="wc-skip-btn float-right wc-final-skip"><spring:message code="label.skip.key" /></div>
		</div>
		<div class="wc-btn-col float-left">
			<div id="wc-send-survey" class="wc-sub-send-btn float-left wc-final-submit"><spring:message code="label.send.key" /></div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function(){
		$('#wc-review-table').perfectScrollbar();
		$('#wc-review-table').perfectScrollbar('update');
		
		$('input[data-name="agent-name"]').bind('click',function(e){
			e.stopPropagation();
			$('.agent-dropdown-cont').remove();
			fillAgents(this);
		});
		debugger;
		function fillAgents(element){
			var columnName = '${columnName}';
			var columnValue = '${columnValue}';
			var payload = {
				"columnName" : columnName,
				"columnValue" : columnValue
			};
			var success = false;
			$.ajax({
				url : './fetchagentsforadmin.do',
				type : "GET",
				data : payload,
				dataType : 'JSON',
				async : true,
				success : function(){
					success = true;
				},
				complete: function(data){
					if(success){
						appendAgentDropDown(data.responseJSON, element, columnName);
					}
				},
				error : function(e) {
					redirectErrorpage();
				}
			});
		}
		
		function appendAgentDropDown(data, element, columnName) {
			var htmlData = '<div class="agent-dropdown-wrapper">';
			htmlData += '<div class="agent-dropdown-cont">';
			for(var index=0; index<data.length;index++){
				htmlData += '<div column-name="' + columnName + '" attr="' + data[index].userId + '" class="agent-dropdown-item">' + data[index].displayName + '</div>';
			}
			htmlData += '</div>';
			htmlData += '<div>';
			$(element).parent().append(htmlData);
		}
		
		$(document).on('click','.agent-dropdown-item',function(e){
			e.stopPropagation();
			$(this).parent().parent().parent().find('input[data-name="agent-name"]').val($(this).text());
			$(this).parent().parent().parent().find('input[data-name="agent-name"]').attr('agent-id', $(this).attr('attr'));
			$(this).parent().parent().parent().find('input[data-name="agent-name"]').attr('column-name', $(this).attr('column-name'));
			$('.agent-dropdown-cont').remove();
		})
		
		$(document).click(function(){
			$('.agent-dropdown-cont').remove();
		});
	});
</script>
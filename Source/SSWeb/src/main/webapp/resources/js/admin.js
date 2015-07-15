//Contains js functions for realtech admin


$(document).on('click','#dsh-ind-report-dwn-btn',function(e){
	var startDate = $('#dsh-start-date').val();
	var endDate = $("#dsh-end-date").val();
	var idenVal = $('#report-sel').attr('data-idenVal');
	var selectedProf = $('#report-sel').attr('data-iden');
	if(idenVal == undefined || idenVal == "") {
		return;
	}
	window.location.href = "./downloaddashboardcompletesurvey.do?columnName="
			+ selectedProf + "&columnValue=" + idenVal + "&startDate=" + startDate + "&endDate=" + endDate;
});

$(document).on('click','#dsh-admin-report-dwn-btn',function(){
	var selectedValue = $('#download-survey-reports').val();
	var startDate = $('#dsh-start-date').val();
	var endDate = $("#dsh-end-date").val();
	var key = parseInt(selectedValue);
	var idenVal = $('#report-sel').attr('data-idenVal');
	var selectedProf = $('#report-sel').attr('data-iden');
	switch (key) {
	case 0:
		console.log("complete-survey");
		window.location.href = "/downloaddashboardcompletesurvey.do?columnName="+selectedProf+"&columnValue="+idenVal+"&startDate="+startDate+"&endDate="+endDate;
		break;
	case 1:
		console.log("loan-officer-ranking");
		window.location.href = "/downloadagentrankingreport.do?columnName="+selectedProf+"&columnValue="+idenVal+"&startDate="+startDate+"&endDate="+endDate;
		break;
	case 2:
		console.log("customer-survey");
		window.location.href = "/downloadcustomersurveyresults.do?columnName="+selectedProf+"&columnValue="+idenVal+"&startDate="+startDate+"&endDate="+endDate;
		break;
	case 3:
		console.log("social-monitor");
		window.location.href = "/downloaddashboardsocialmonitor.do?columnName="+selectedProf+"&columnValue="+idenVal+"&startDate="+startDate+"&endDate="+endDate;
		break;
	default:
		break;
	}
});

$(document).on('keyup','#hr-comp-sel',function(e){
	if(e.which == 13) {
		var key = $(this).val();
		searchAndDisplayCompanies(key);
	}
});

$(document).on('click','#hr-comp-icn',function(e){
	var key = $('#hr-comp-sel').val();
	searchAndDisplayCompanies(key);
});

function searchAndDisplayCompanies(key) {
	callAjaxGET("/fetchcompaniesbykey.do?searchKey="+key, function(data) {
		$('#admin-com-list').html(data);
	}, true);
}

function searchAdminCompanies (element) {
	var payload = {
		"searchColumn" : "company",
		"searchKey" : $(element).val()
	};
	

	callAjaxGetWithPayloadData("./findregionbranchorindividual.do", function(data) {
		
		$('#hr-comp-res').html(data).show();
		
		$('.dsh-res-display').click(function() {
			var value = $(this).data('attr');
			columnName = "companyId";
			$('#hr-comp-sel').val($(this).html());
			$('#hr-comp-sel').attr("data-iden", value);
			$('.dsh-res-display').hide();
			$('#hr-comp-res').hide();
			
			//showSelectedCompanyHierarchy(value);
		});
	}, payload, true);
}

function showSelectedCompanyHierarchy(companyId) {
	callAjaxGET("/companyhierarchy.do?companyId="+companyId, function(data) {
		$('.comp-hr-cont[data-iden="'+companyId+'"]').html(data).show();
		bindAdminRegionListClicks();
	    $('.v-tbl-icn').click(function(e){
	        e.stopPropagation();
	    });
	    bindAdminBranchListClicks();
	    bindUserEditClicks();
	}, true);
}

function bindAdminRegionListClicks() {
	$(".region-row").click(function(e){
		var regionId = $(this).attr("data-regionid");
		if($(this).attr('clicked') == "false"){
			var companyId = $(this).closest('.comp-hr-cont').attr('data-iden');
			fetchAdminHierarchyViewBranches(regionId, companyId);
			 $(this).attr('clicked','true');
		}
		else {
			$("tr[class*='sel-r"+regionId+"'").html("").hide();
            $(this).attr('clicked','false');
		}
	});
	$(".region-edit-icn").click(function(e){
		e.stopPropagation();
		var regionId = $(this).attr("data-regionid");
		if($(this).attr('clicked') == "false"){
			showRegionEdit(regionId);
			$(this).attr('clicked','true');
		}
		else {
			hideRegionEdit(regionId);
			$(this).attr('clicked','false');
		}		
	});
	$(".region-del-icn").unbind('click');
	$(".region-del-icn").click(function(e){
		var regionId = $(this).attr("data-regionid");
		deleteRegionPopup(regionId);
	});
}

function bindAdminBranchListClicks(){
	$(".branch-edit-icn").unbind('click');
	$(".branch-edit-icn").click(function(e){
		e.stopPropagation();
		var branchId = $(this).attr("data-branchid");
		if($(this).attr('clicked') == "false"){
			showBranchEdit(branchId);
			$(this).attr('clicked','true');
		}
		else {
			hideBranchEdit(branchId);
			$(this).attr('clicked','false');
		}		
	});
	$(".branch-row").unbind('click');
	$(".branch-row").click(function(e){
		e.stopPropagation();
		var branchId = $(this).attr("data-branchid");
		var regionId = $(this).attr("data-regionid");
		if($(this).attr('clicked') == "false"){
			var companyId = $(this).closest('.comp-hr-cont').attr('data-iden');
			fetchAdminUsersForBranch(branchId,regionId, companyId);
			 $(this).attr('clicked','true');
		}
		else {
			$('.user-row-'+branchId).html("").hide(); 
            $(this).attr('clicked','false');
		}
	});
	$(".branch-del-icn").unbind('click');
	$(".branch-del-icn").click(function(e){
		var branchId = $(this).attr("data-branchid");
		deleteBranchPopup(branchId);
	});
}

function fetchAdminHierarchyViewBranches(regionId, companyId) {
	var url = "./fetchhierarchyviewbranchesforadmin.do?regionId="+regionId + "&companyId="+companyId;
	callAjaxGET(url, function(data) {
		paintAdminHierarchyViewBranches(data,regionId);
	}, true);
}

function paintAdminHierarchyViewBranches(data,regionId) {
	$("#td-region-edit-"+regionId).parent(".tr-region-edit").after(data);
	$("#tr-region-"+regionId).slideDown(200);
	$(".tr-region-edit").slideUp(200);
	bindUserEditClicks();
	bindAdminBranchListClicks();
}

function fetchAdminUsersForBranch(branchId,regionId, companyId) {
	var url="./fetchbranchusersforadmin.do?branchId="+branchId+"&regionId="+regionId + "&companyId="+companyId;
	callAjaxGET(url, function(data) {
		paintAdminUsersFromBranch(data,branchId);
	}, true);
}

function paintAdminUsersFromBranch(data,branchId,regionId) {
	$("#td-branch-edit-"+branchId).parent(".tr-branch-edit").after(data);
	$("#tr-branch-"+branchId).slideDown(200);
	$(".tr-branch-edit").slideUp(200);
	bindUserEditClicks();
}

$(document).on('click','.comp-row',function(e){
	var element = this;
	var companyId = $(element).attr('data-iden');
	if($(element).attr('clicked') == "false"){
		showSelectedCompanyHierarchy(companyId);
		 $(element).attr('clicked','true');
	}
	else {
		$('.comp-hr-cont[data-iden="'+companyId+'"]').html('').hide();
        $(element).attr('clicked','false');
	}
});
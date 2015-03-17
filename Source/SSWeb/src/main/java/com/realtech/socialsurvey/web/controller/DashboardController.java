package com.realtech.socialsurvey.web.controller;

// JIRA SS-137 : by RM-05 : BOC

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.realtech.socialsurvey.core.commons.CommonConstants;
import com.realtech.socialsurvey.core.entities.AgentSettings;
import com.realtech.socialsurvey.core.entities.OrganizationUnitSettings;
import com.realtech.socialsurvey.core.entities.SurveyDetails;
import com.realtech.socialsurvey.core.entities.User;
import com.realtech.socialsurvey.core.entities.UserProfile;
import com.realtech.socialsurvey.core.entities.UserSettings;
import com.realtech.socialsurvey.core.exception.InvalidInputException;
import com.realtech.socialsurvey.core.exception.NonFatalException;
import com.realtech.socialsurvey.core.services.mail.EmailServices;
import com.realtech.socialsurvey.core.services.organizationmanagement.DashboardService;
import com.realtech.socialsurvey.core.services.organizationmanagement.ProfileManagementService;
import com.realtech.socialsurvey.core.services.search.SolrSearchService;

@Controller
public class DashboardController {

	private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);

	@Autowired
	private SessionHelper sessionHelper;

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private ProfileManagementService profileManagementService;

	@Autowired
	private SolrSearchService solrSearchService;
	
	@Autowired
	private EmailServices emailServices;
	
	@Value("${ENABLE_KAFKA}")
	private String enableKafka;

	@ResponseBody
	@RequestMapping(value = "/surveycount")
	public String getSurveyCount(Model model, HttpServletRequest request) {
		LOG.info("Method to get count of all, completed and clicked surveys, getSurveyCount() started.");
		Map<String, Object> surveyCount = new HashMap<String, Object>();
		// try {
		User user = sessionHelper.getCurrentUser();
		String columnName = request.getParameter("columnName");
		long columnValue = 0;
		if (columnName.equalsIgnoreCase(CommonConstants.COMPANY_ID_COLUMN)) {
			columnValue = user.getCompany().getCompanyId();
		}
		else if (columnName.equalsIgnoreCase(CommonConstants.AGENT_ID_COLUMN)) {
			columnValue = user.getUserId();
		}
		else {
			try {
				columnValue = Long.parseLong(request.getParameter("columnValue"));
			}
			catch (NumberFormatException e) {
				LOG.error("NumberFormatException caught in getSurveyCountForCompany() while converting columnValue for regionId/branchId/agentId.");
				throw e;
			}
		}
		int numberOfDays = -1;
		try {
			if (request.getParameter("numberOfDays") != null) {
				numberOfDays = Integer.parseInt(request.getParameter("numberOfDays"));
			}
		}
		catch (NumberFormatException e) {
			LOG.error("NumberFormatException caught in getSurveyCountForCompany() while converting numberOfDays.");
			throw e;
		}
		long allSurveyCount = dashboardService.getAllSurveyCountForPastNdays(columnName, columnValue, numberOfDays);
		surveyCount.put("allSurveySent", allSurveyCount);
		surveyCount.put("completedSurvey", dashboardService.getCompletedSurveyCountForPastNdays(columnName, columnValue, numberOfDays));
		surveyCount.put("clickedSurvey", dashboardService.getClickedSurveyCountForPastNdays(columnName, columnValue, numberOfDays));
		surveyCount.put("socialPosts", dashboardService.getSocialPostsForPastNdays(columnName, columnValue, numberOfDays));

		/*
		 * } catch (NonFatalException e) { }
		 */

		LOG.info("Method to get count of surveys sent in entire company, getSurveyCountForCompany() finished.");
		return new Gson().toJson(surveyCount);
	}

	@ResponseBody
	@RequestMapping(value = "/profiledetails")
	public String getProfileDetails(Model model, HttpServletRequest request) {
		LOG.info("Method to get profile of company, region, branch, agent getProfileDetails() started.");
		Map<String, Object> profileDetails = new HashMap<>();
		User user = sessionHelper.getCurrentUser();
		String columnName = request.getParameter("columnName");
		long columnValue = 0;
		if (columnName.equalsIgnoreCase(CommonConstants.COMPANY_ID_COLUMN)) {
			columnValue = user.getCompany().getCompanyId();
			profileDetails.put("name", user.getCompany().getCompany());
			profileDetails.put("title", getTitle(request, columnName, columnValue, user));
		}
		else if (columnName.equalsIgnoreCase(CommonConstants.AGENT_ID_COLUMN)) {
			columnValue = user.getUserId();
			profileDetails.put("name", user.getFirstName() + " " + user.getLastName());
			profileDetails.put("company", user.getCompany().getCompany());
		}
		else {
			try {
				columnValue = Long.parseLong(request.getParameter("columnValue"));
			}
			catch (NumberFormatException e) {
				LOG.error("NumberFormatException caught in getProfileDetails() while converting columnValue for regionId/branchId/agentId.");
				throw e;
			}
		}
		int numberOfDays = 30;
		try {
			if (request.getParameter("numberOfDays") != null) {
				numberOfDays = Integer.parseInt(request.getParameter("numberOfDays"));
			}
		}
		catch (NumberFormatException e) {
			LOG.error("NumberFormatException caught in getProfileDetails() while converting numberOfDays.");
			throw e;
		}
		if (columnName.equalsIgnoreCase(CommonConstants.AGENT_ID_COLUMN)) {
			// columnValue contains branchId, the agent belongs to.(Here only)
			profileDetails.put("title", getTitle(request, columnName, columnValue, user));
		}

		int surveyScore = (int) Math.round(dashboardService.getSurveyScore(columnName, columnValue, numberOfDays));
		int sentSurveyCount = (int) dashboardService.getAllSurveyCountForPastNdays(columnName, columnValue, numberOfDays);
		int socialPostsCount = (int) dashboardService.getSocialPostsForPastNdays(columnName, columnValue, numberOfDays);
		UserSettings userSettings = (UserSettings) request.getSession(false).getAttribute(CommonConstants.CANONICAL_USERSETTINGS_IN_SESSION);
		int profileCompleteness = dashboardService.getProfileCompletionPercentage(user, columnName, columnValue, userSettings);

		profileDetails.put("socialScore", surveyScore);
		profileDetails.put("surveyCount", sentSurveyCount);
		profileDetails.put("socialPosts", socialPostsCount);
		profileDetails.put("profileCompleteness", profileCompleteness);
		profileDetails.put("badges", dashboardService.getBadges(surveyScore, sentSurveyCount, socialPostsCount, profileCompleteness));
		LOG.info("Method to get profile of company, region, branch, agent getProfileDetails() finished.");
		return new Gson().toJson(profileDetails);
	}

	@ResponseBody
	@RequestMapping(value = "/fetchdashboardreviews")
	public String getReviews(Model model, HttpServletRequest request) {
		LOG.info("Method to get reviews of company, region, branch, agent getReviews() started.");
		List<SurveyDetails> surveyDetails = new ArrayList<>();
		try {
			String columnName = request.getParameter("columnName");
			String startIndexStr = request.getParameter("startIndex");
			String batchSizeStr = request.getParameter("batchSize");
			int startIndex = Integer.parseInt(startIndexStr);
			int batchSize = Integer.parseInt(batchSizeStr);
			if (columnName == null || columnName.isEmpty()) {
				LOG.error("Invalid value (null/empty) passed for profile level.");
				throw new InvalidInputException("Invalid value (null/empty) passed for profile level.");
			}
			String profileLevel = getProfileLevel(columnName);
			long iden = 0;

			User user = sessionHelper.getCurrentUser();
			if (profileLevel.equals(CommonConstants.PROFILE_LEVEL_COMPANY)) {
				iden = user.getCompany().getCompanyId();
			}
			else if (profileLevel.equals(CommonConstants.PROFILE_LEVEL_INDIVIDUAL)) {
				iden = user.getUserId();
			}
			else {
				String columnValue = request.getParameter("columnValue");
				if (columnValue != null && !columnValue.isEmpty()) {
					try {
						iden = Long.parseLong(columnValue);
					}
					catch (NumberFormatException e) {
						LOG.error("NumberFormatException caught while parsing columnValue in getReviews(). Nested exception is ", e);
						throw e;
					}
				}
			}
			try {
				surveyDetails = profileManagementService.getReviews(iden, 0, 0, startIndex, batchSize, profileLevel);
			}
			catch (InvalidInputException e) {
				LOG.error("InvalidInputException caught in getReviews() while fetching reviews. Nested exception is ", e);
				throw e;
			}
		}
		catch (NonFatalException e) {
			LOG.error("Non fatal exception caught in getReviews() while fetching reviews. Nested exception is ", e);
			return new Gson().toJson(e.getMessage());
		}
		LOG.info("Method to get reviews of company, region, branch, agent getReviews() finished.");
		return new Gson().toJson(surveyDetails);
	}

	@ResponseBody
	@RequestMapping(value = "/fetchdashboardreviewCount")
	public String getReviewCount(Model model, HttpServletRequest request) {
		LOG.info("Method to get reviews count getReviewCount() started.");
		long reviewCount = 0;
		try {
			String columnName = request.getParameter("columnName");
			if (columnName == null || columnName.isEmpty()) {
				LOG.error("Invalid value (null/empty) passed for profile level.");
				throw new InvalidInputException("Invalid value (null/empty) passed for profile level.");
			}
			String profileLevel = getProfileLevel(columnName);
			long iden = 0;

			User user = sessionHelper.getCurrentUser();
			if (profileLevel.equals(CommonConstants.PROFILE_LEVEL_COMPANY)) {
				iden = user.getCompany().getCompanyId();
			}
			else if (profileLevel.equals(CommonConstants.PROFILE_LEVEL_INDIVIDUAL)) {
				iden = user.getUserId();
			}
			else {
				String columnValue = request.getParameter("columnValue");
				if (columnValue == null || columnValue.isEmpty()) {
					LOG.error("Null or empty value passed for Region/BranchId. Please pass valid value.");
					throw new InvalidInputException("Null or empty value passed for Region/BranchId. Please pass valid value.");
				}
				try {
					iden = Long.parseLong(columnValue);
				}
				catch (NumberFormatException e) {
					LOG.error("NumberFormatException caught while parsing columnValue in getReviews(). Nested exception is ", e);
					throw e;
				}

			}
			// Calling service method to count number of reviews stored in database.
			reviewCount = profileManagementService.getReviewsCount(iden, -1, -1, profileLevel);
		}
		catch (NonFatalException e) {
			LOG.error("Non fatal exception caught in getReviewCount() while fetching reviews count. Nested exception is ", e);
			return new Gson().toJson(e.getMessage());
		}
		LOG.info("Method to get reviews count getReviewCount() finished.");
		return new Gson().toJson(reviewCount);
	}

	@ResponseBody
	@RequestMapping(value = "/fetchName")
	public String getName(Model model, HttpServletRequest request) {
		LOG.info("Method to get name to display in review section getName() started.");
		String name = "";
		try {
			String columnName = request.getParameter("columnName");
			if (columnName == null || columnName.isEmpty()) {
				LOG.error("Invalid value (null/empty) passed for profile level.");
				throw new InvalidInputException("Invalid value (null/empty) passed for profile level.");
			}
			long id = 0;
			User user = sessionHelper.getCurrentUser();
			if (columnName.equals(CommonConstants.COMPANY_ID_COLUMN)) {
				return new Gson().toJson(user.getCompany().getCompany());
			}
			else if (columnName.equals(CommonConstants.AGENT_ID_COLUMN)) {
				return new Gson().toJson(user.getFirstName() + " " + user.getLastName());
			}
			else {
				String columnValue = request.getParameter("columnValue");
				if (columnValue != null && !columnValue.isEmpty()) {
					try {
						id = Long.parseLong(columnValue);
					}
					catch (NumberFormatException e) {
						LOG.error("NumberFormatException caught while parsing columnValue in getReviews(). Nested exception is ", e);
						throw e;
					}
				}
				if (columnName.equalsIgnoreCase(CommonConstants.BRANCH_ID_COLUMN))
					name = solrSearchService.searchBranchNameById(id);
				else if (columnName.equalsIgnoreCase(CommonConstants.REGION_ID_COLUMN))
					name = solrSearchService.searchRegionById(id);
			}
		}
		catch (NonFatalException e) {
			LOG.error("Non fatal exception caught in getReviewCount() while fetching reviews count. Nested exception is ", e);
			return new Gson().toJson(e.getMessage());
		}
		LOG.info("Method to get name to display in review section getName() finished.");
		return name;
	}

	@ResponseBody
	@RequestMapping(value = "/fetchdashboardincompletesurvey")
	public String getIncompleteSurvey(Model model, HttpServletRequest request) {
		LOG.info("Method to get reviews of company, region, branch, agent getReviews() started.");
		List<SurveyDetails> surveyDetails = new ArrayList<>();
		try {
			String columnName = request.getParameter("columnName");
			String startIndexStr = request.getParameter("startIndex");
			String batchSizeStr = request.getParameter("batchSize");
			int startIndex = Integer.parseInt(startIndexStr);
			int batchSize = Integer.parseInt(batchSizeStr);
			if (columnName == null || columnName.isEmpty()) {
				LOG.error("Invalid value (null/empty) passed for profile level.");
				throw new InvalidInputException("Invalid value (null/empty) passed for profile level.");
			}
			String profileLevel = getProfileLevel(columnName);
			long iden = 0;

			User user = sessionHelper.getCurrentUser();
			if (profileLevel.equals(CommonConstants.PROFILE_LEVEL_COMPANY)) {
				iden = user.getCompany().getCompanyId();
			}
			else if (profileLevel.equals(CommonConstants.PROFILE_LEVEL_INDIVIDUAL)) {
				iden = user.getUserId();
			}
			else {
				String columnValue = request.getParameter("columnValue");
				if (columnValue != null && !columnValue.isEmpty()) {
					try {
						iden = Long.parseLong(columnValue);
					}
					catch (NumberFormatException e) {
						LOG.error("NumberFormatException caught while parsing columnValue in getReviews(). Nested exception is ", e);
						throw e;
					}
				}
			}
			try {
				surveyDetails = profileManagementService.getIncompleteSurvey(iden, 0, 0, startIndex, batchSize, profileLevel);
			}
			catch (InvalidInputException e) {
				LOG.error("InvalidInputException caught in getReviews() while fetching reviews. Nested exception is ", e);
				throw e;
			}
		}
		catch (NonFatalException e) {
			LOG.error("Non fatal exception caught in getReviews() while fetching reviews. Nested exception is ", e);
			return new Gson().toJson(e.getMessage());
		}
		LOG.info("Method to get reviews of company, region, branch, agent getReviews() finished.");
		return new Gson().toJson(surveyDetails);
	}

	@ResponseBody
	@RequestMapping(value = "/findregionbranchorindividual")
	public String getRegionBranchOrAgent(Model model, HttpServletRequest request) {
		LOG.info("Method to get list of regions, branches, agents getRegionBranchOrAgent() started.");
		String result = "";
		long regionOrBranchId = 0;
		try {
			String searchColumn = request.getParameter("searchColumn");
			String searchKey = request.getParameter("searchKey");
			String columnName = request.getParameter("columnName");
			String columnValueStr = request.getParameter("columnValue");
			if (searchColumn == null || searchColumn.isEmpty()) {
				LOG.error("Invalid value (null/empty) passed for search criteria.");
				throw new InvalidInputException("Invalid value (null/empty) passed for search criteria.");
			}
			if (columnName == null || columnName.isEmpty()) {
				LOG.error("Invalid value (null/empty) passed for profile level.");
				throw new InvalidInputException("Invalid value (null/empty) passed for profile level.");
			}
			if (columnValueStr == null || columnValueStr.isEmpty()) {
				LOG.error("Invalid value (null/empty) passed for Region/branch Id.");
				throw new InvalidInputException("Invalid value (null/empty) passed for Region/branch Id.");
			}

			if (searchKey == null) {
				searchKey = "";
			}

			User user = sessionHelper.getCurrentUser();
			if(columnName.equalsIgnoreCase(CommonConstants.COMPANY_ID_COLUMN)){
				try {
					result = solrSearchService.searchBranchRegionOrAgentByName(searchColumn, searchKey, columnName, user.getCompany().getCompanyId());
				}
				catch (InvalidInputException e) {
					LOG.error("InvalidInputException caught in getRegionBranchOrAgent() while fetching details. Nested exception is ", e);
					throw e;
				}
			}
			else if(columnName.equalsIgnoreCase(CommonConstants.REGION_ID_COLUMN)){
				try {
					regionOrBranchId = Long.parseLong(columnValueStr);
					result = solrSearchService.searchBranchRegionOrAgentByName(searchColumn, searchKey, columnName, regionOrBranchId);
				}
				catch(NumberFormatException e){
					LOG.error("NumberFormatException caught in getRegionBranchOrAgent() while fetching details. Nested exception is ", e);
					throw e;
				}
				catch (InvalidInputException e) {
					LOG.error("InvalidInputException caught in getRegionBranchOrAgent() while fetching details. Nested exception is ", e);
					throw e;
				}
			}
			else if(columnName.equalsIgnoreCase(CommonConstants.BRANCH_ID_COLUMN)){
				try {
					result = solrSearchService.searchBranchRegionOrAgentByName(searchColumn, searchKey, columnName, regionOrBranchId);
				}
				catch (InvalidInputException e) {
					LOG.error("InvalidInputException caught in getRegionBranchOrAgent() while fetching details. Nested exception is ", e);
					throw e;
				}
			}
		}
		catch (NonFatalException e) {
			LOG.error("Non fatal exception caught in getReviews() while fetching reviews. Nested exception is ", e);
			return new Gson().toJson(e.getMessage());
		}
		LOG.info("Method to get list of regions, branches, agents getRegionBranchOrAgent() finished.");
		return result;
	}
	
	@RequestMapping(value = "/sendsurveyremindermail")
	public void sendReminderMailForSurvey(Model model, HttpServletRequest request) {
		LOG.info("Method to send email to remind customer for survey sendReminderMailForSurvey() started.");
		try {
			String customerName = request.getParameter("customerName");
			String customerEmail = request.getParameter("customerEmail");
			String agentName = request.getParameter("agentName");
			if (customerName == null || customerName.isEmpty()) {
				LOG.error("Invalid value (null/empty) passed for customerName.");
				throw new InvalidInputException("Invalid value (null/empty) passed for customerName.");
			}
			if (customerEmail == null || customerEmail.isEmpty()) {
				LOG.error("Invalid value (null/empty) passed for customerEmail.");
				throw new InvalidInputException("Invalid value (null/empty) passed for customerEmail.");
			}
			try {
				if(enableKafka.equals(CommonConstants.YES)){
					emailServices.queueSurveyReminderMail(customerEmail, customerName, agentName);
				}else{
					emailServices.sendSurveyReminderMail(customerEmail, customerName, agentName);
				}
			}
			catch (InvalidInputException e) {
				LOG.error("Exception occurred while trying to send survey reminder mail to : " + customerEmail);
				throw e;
			}
		}catch(Exception e){
			LOG.error("Exception caught in sendReminderMailForSurvey() while sending mail. Nested exception is ",e);
		}
		LOG.info("Method to send email to remind customer for survey sendReminderMailForSurvey() finished.");
	}
	
	// Method to return title for logged in user.
	// It returns title for various fields e.g. company, region, branch and agent.
	private String getTitle(HttpServletRequest request, String field, long value, User user) {
		LOG.debug("Method to find title for " + field + " started.");
		String title = "";
		try {
			UserSettings userSettings = (UserSettings) request.getSession(false).getAttribute(CommonConstants.CANONICAL_USERSETTINGS_IN_SESSION);
			switch (field) {
				case CommonConstants.COMPANY_ID_COLUMN:
					title = userSettings.getCompanySettings().getContact_details().getTitle();
					break;
				case CommonConstants.REGION_ID_COLUMN:
					OrganizationUnitSettings regionSettings = userSettings.getRegionSettings().get(value);
					title = regionSettings.getContact_details().getTitle();
					break;
				case CommonConstants.BRANCH_ID_COLUMN:
					OrganizationUnitSettings branchSettings = userSettings.getBranchSettings().get(value);
					title = branchSettings.getContact_details().getTitle();
					break;
				case CommonConstants.AGENT_ID_COLUMN:
					for (UserProfile userProfile : user.getUserProfiles()) {
						if (userProfile.getBranchId() == value) {
							AgentSettings agentSettings = userSettings.getAgentSettings().get(user.getUserId());
							if (agentSettings != null)
								title = agentSettings.getContact_details().getTitle();
						}
					}
					break;
				default:
					LOG.error("Invalid value " + field + " passed for field. It should be either of " + CommonConstants.COMPANY_ID_COLUMN + "/"
							+ CommonConstants.REGION_ID_COLUMN + "/" + CommonConstants.BRANCH_ID_COLUMN + "/" + CommonConstants.AGENT_ID_COLUMN);
			}
		}
		catch (NullPointerException e) {
			LOG.error("Null Pointer exception caught in getProfileDetails() while fetching designation of agent. Nested exception is ", e);
		}
		LOG.debug("Method to find title for " + field + " finished.");
		return title;
	}

	private String getProfileLevel(String columnName) {
		LOG.debug("Method to return profile level based upon column to be quried started.");
		String profileLevel = "";
		switch (columnName) {
			case CommonConstants.COMPANY_ID_COLUMN:
				profileLevel = CommonConstants.PROFILE_LEVEL_COMPANY;
				break;
			case CommonConstants.REGION_ID_COLUMN:
				profileLevel = CommonConstants.PROFILE_LEVEL_REGION;
				break;
			case CommonConstants.BRANCH_ID_COLUMN:
				profileLevel = CommonConstants.PROFILE_LEVEL_BRANCH;
				break;
			case CommonConstants.AGENT_ID_COLUMN:
				profileLevel = CommonConstants.PROFILE_LEVEL_INDIVIDUAL;
				break;
		}
		LOG.debug("Method to return profile level based upon column to be quried finished.");
		return profileLevel;
	}
}
// JIRA SS-137 : by RM-05 : EOC
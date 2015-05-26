package com.realtech.socialsurvey.core.starter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import com.realtech.socialsurvey.core.commons.CommonConstants;
import com.realtech.socialsurvey.core.entities.AgentSettings;
import com.realtech.socialsurvey.core.entities.Company;
import com.realtech.socialsurvey.core.entities.OrganizationUnitSettings;
import com.realtech.socialsurvey.core.entities.SurveyDetails;
import com.realtech.socialsurvey.core.exception.InvalidInputException;
import com.realtech.socialsurvey.core.services.generator.URLGenerator;
import com.realtech.socialsurvey.core.services.mail.EmailServices;
import com.realtech.socialsurvey.core.services.mail.UndeliveredEmailException;
import com.realtech.socialsurvey.core.services.organizationmanagement.OrganizationManagementService;
import com.realtech.socialsurvey.core.services.organizationmanagement.UserManagementService;
import com.realtech.socialsurvey.core.services.social.SocialManagementService;
import com.realtech.socialsurvey.core.services.surveybuilder.SurveyHandler;

public class IncompleteSocialPostReminderSender extends QuartzJobBean {

	private URLGenerator urlGenerator;

	private UserManagementService userManagementService;

	private SurveyHandler surveyHandler;

	private EmailServices emailServices;

	private OrganizationManagementService organizationManagementService;

	private SocialManagementService socialManagementService;

	public static final Logger LOG = LoggerFactory.getLogger(IncompleteSocialPostReminderSender.class);

	private static List<String> socialSites = new ArrayList<>();

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) {
		LOG.info("Executing IncompleteSocialPostReminderSender");
		initializeDependencies(jobExecutionContext.getMergedJobDataMap());
		populateSocialSites();
		// IncompleteSocialPostReminderSender sender = new IncompleteSocialPostReminderSender();
		StringBuilder links = new StringBuilder();
		Set<String> socialPosts;
		AgentSettings agentSettings = null;
		for (Company company : organizationManagementService.getAllCompanies()) {
			List<SurveyDetails> incompleteSocialPostCustomers = surveyHandler.getIncompleteSocialPostSurveys(company.getCompanyId());
			for (SurveyDetails survey : incompleteSocialPostCustomers) {
				// To fetch settings of Agent and admins in the hierarchy
				Set<String> socialSitesWithSettings = new HashSet<>();
				try {
					 socialSitesWithSettings = getSocialSitesWithSettingsConfigured(survey.getAgentId());
				}
				catch (InvalidInputException e) {
					LOG.error("InvalidInputException caught in executeInternal() for SocialpostReminderMail");
					continue;
				}

				if (survey.getSharedOn() == null)
					socialPosts = new HashSet<>();
				else
					socialPosts = new HashSet<String>(survey.getSharedOn());
				links = new StringBuilder();
				for (String site : getRemainingSites(socialPosts, socialSitesWithSettings)) {
					try {
						links.append("<br/>For ").append(site).append(" : ").append("<a href="+generateQueryParams(survey, site)+">Click here</a>");
						agentSettings = userManagementService.getUserSettings(survey.getAgentId());
					}
					catch (InvalidInputException e) {
						LOG.error("InvalidInputException occured while generating URL for " + site + ". Nested exception is ", e);
						continue;
					}
				}
				// Send email to complete social post for survey to each customer.
				try {
					String title = "";
					String phoneNo = "";
					String companyName = "";
					if(agentSettings!=null && agentSettings.getContact_details() !=null){
						if(agentSettings.getContact_details().getTitle() != null){
							title = agentSettings.getContact_details().getTitle();
						}
						if(agentSettings.getContact_details().getContact_numbers() != null && agentSettings.getContact_details().getContact_numbers().getWork() != null){
							phoneNo = agentSettings.getContact_details().getContact_numbers().getWork();
						}
						if(company.getCompany()!=null){
							companyName = company.getCompany();
						}
					}
					emailServices.sendSocialPostReminderMail(survey.getCustomerEmail(), phoneNo, title, companyName,
							survey.getCustomerFirstName() + " " + survey.getCustomerLastName(), survey.getAgentName(), links.toString());
					surveyHandler.updateReminderCountForSocialPosts(survey.getAgentId(), survey.getCustomerEmail());
				}
				catch (InvalidInputException | UndeliveredEmailException e) {
					LOG.error(
							"Exception caught in IncompleteSurveyReminderSender.main while trying to send reminder mail to "
									+ survey.getCustomerFirstName() + " for completion of survey. Nested exception is ", e);
					continue;
				}
			}
		}
	}

	private void initializeDependencies(JobDataMap jobMap) {
		urlGenerator = (URLGenerator) jobMap.get("urlGenerator");
		surveyHandler = (SurveyHandler) jobMap.get("surveyHandler");
		emailServices = (EmailServices) jobMap.get("emailServices");
		userManagementService = (UserManagementService) jobMap.get("userManagementService");
		socialManagementService = (SocialManagementService) jobMap.get("socialManagementService");
		organizationManagementService = (OrganizationManagementService) jobMap.get("organizationManagementService");

	}

	private static void populateSocialSites() {
		socialSites.add(CommonConstants.FACEBOOK_SOCIAL_SITE);
		socialSites.add(CommonConstants.TWITTER_SOCIAL_SITE);
		socialSites.add(CommonConstants.YELP_SOCIAL_SITE);
		socialSites.add(CommonConstants.GOOGLE_SOCIAL_SITE);
		socialSites.add(CommonConstants.LINKEDIN_SOCIAL_SITE);
	}

	private String generateQueryParams(SurveyDetails survey, String socialSite) throws InvalidInputException {
		LOG.debug("Method to generate URL parameters for Facebook, generateUrlParamsForFacebook() started.");
		Map<String, String> params = new HashMap<>();
		String subUrl = "rest/survey/";
		switch (socialSite) {
			case "facebook":
				subUrl += "posttofacebook";
				break;
			case "twitter":
				subUrl += "posttotwitter";
				break;
			case "linkedin":
				subUrl += "posttolinkedin";
				break;
			case "yelp":
				subUrl += "getyelplinkrest";
				break;
			case "google":
				subUrl += "getgooglepluslinkrest";
				break;
		}

		AgentSettings agentSettings = userManagementService.getUserSettings(survey.getAgentId());
		params.put("agentName", survey.getAgentName());
		params.put("agentProfileLink", surveyHandler.getApplicationBaseUrl() + "rest/profile/" + agentSettings.getProfileUrl());
		params.put("firstName", survey.getCustomerFirstName());
		params.put("lastName", survey.getCustomerLastName());
		params.put("agentId", survey.getAgentId() + "");
		params.put("rating", survey.getScore() + "");
		params.put("customerEmail", survey.getCustomerEmail());
		params.put("feedback", survey.getReview());
		LOG.debug("Method to generate URL parameters for Facebook, generateUrlParamsForFacebook() finished.");
		return urlGenerator.generateUrl(params, surveyHandler.getApplicationBaseUrl() + subUrl);
	}

	private static Set<String> getRemainingSites(Set<String> sharedOn, Set<String> socialSitesWithSettings) {
		Set<String> allElems = new HashSet<String>(socialSites);
		allElems.removeAll(sharedOn);
		allElems.retainAll(socialSitesWithSettings);
		return allElems;
	}

	private Set<String> getSocialSitesWithSettingsConfigured(long agentId) throws InvalidInputException {
		LOG.debug("Method to get settings of agent and admins in the hierarchy getSocialSitesWithSettingsConfigured() started.");
		OrganizationUnitSettings agentSettings = userManagementService.getUserSettings(agentId);
		List<OrganizationUnitSettings> hierarchySettings = socialManagementService.getSettingsForBranchesAndRegionsInHierarchy(agentId);
		List<OrganizationUnitSettings> settings;
		if(hierarchySettings!=null){
			settings = new ArrayList<>(hierarchySettings);
		}
		else{
			settings = new ArrayList<>();
		}
		Set<String> socialSitesWithSettings = new HashSet<>();
		
		// Enabling Google+ and Yelp only if agent has configured it.
		if (agentSettings != null){
			settings.add(agentSettings);
			if(agentSettings.getSocialMediaTokens().getGoogleToken() != null){
				socialSitesWithSettings.add("google");
			}
			if(agentSettings.getSocialMediaTokens().getYelpToken() != null){
				socialSitesWithSettings.add("yelp");
			}
		}
		
		// Enabling Facebook / Linkedin / Twitter if agent or anybody in the hierarchy has configured in settings.
		for (OrganizationUnitSettings setting : settings) {
			if (setting.getSocialMediaTokens() != null) {
				if (setting.getSocialMediaTokens() != null && setting.getSocialMediaTokens().getFacebookToken() != null) {
					socialSitesWithSettings.add("facebook");
				}
				if (setting.getSocialMediaTokens().getTwitterToken() != null) {
					socialSitesWithSettings.add("twitter");
				}
				if(setting.getSocialMediaTokens().getLinkedInToken() != null){
					socialSitesWithSettings.add("linkedin");
				}
			}
		}
		LOG.debug("Method getSocialSitesWithSettingsConfigured() finished");
		return socialSitesWithSettings;
	}
}

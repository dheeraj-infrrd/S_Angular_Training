package com.realtech.socialsurvey.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.realtech.socialsurvey.core.commons.CommonConstants;
import com.realtech.socialsurvey.core.commons.EmailTemplateConstants;
import com.realtech.socialsurvey.core.commons.UserProfileComparator;
import com.realtech.socialsurvey.core.entities.AbridgedUserProfile;
import com.realtech.socialsurvey.core.entities.FileContentReplacements;
import com.realtech.socialsurvey.core.entities.MailContent;
import com.realtech.socialsurvey.core.entities.MailContentSettings;
import com.realtech.socialsurvey.core.entities.User;
import com.realtech.socialsurvey.core.entities.UserProfile;
import com.realtech.socialsurvey.core.entities.UserSettings;
import com.realtech.socialsurvey.core.enums.AccountType;
import com.realtech.socialsurvey.core.exception.InvalidInputException;
import com.realtech.socialsurvey.core.exception.NoRecordsFetchedException;
import com.realtech.socialsurvey.core.exception.NonFatalException;
import com.realtech.socialsurvey.core.services.organizationmanagement.UserManagementService;
import com.realtech.socialsurvey.core.utils.EmailFormatHelper;
import com.realtech.socialsurvey.core.utils.EncryptionHelper;
import com.realtech.socialsurvey.core.utils.FileOperations;
import com.realtech.socialsurvey.core.utils.PropertyFileReader;
import com.realtech.socialsurvey.web.security.UserAuthProvider;

/**
 * Manipulates the values in session
 */
@Component
public class SessionHelper {

	private static final Logger LOG = LoggerFactory.getLogger(SessionHelper.class);

	@Autowired
	private FileOperations fileOperations;

	@Autowired
	private UserManagementService userManagementService;

	@Autowired
	private PropertyFileReader propertyFileReader;

	@Autowired
	private UserAuthProvider userAuthProvider;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private EncryptionHelper encryptionHelper;

	@Autowired
	private EmailFormatHelper emailFormatHelper;

	@Value("${PARAM_ORDER_TAKE_SURVEY_REMINDER}")
	String paramOrderTakeSurveyReminder;

	@Transactional
	public void getCanonicalSettings(HttpSession session) throws InvalidInputException, NoRecordsFetchedException {
		LOG.info("Getting canonical settings");
		User user = getCurrentUser();
		AccountType accountType = (AccountType) session.getAttribute(CommonConstants.ACCOUNT_TYPE_IN_SESSION);
		LOG.info("Getting settings for " + user.toString() + " for account type " + accountType);
		UserSettings userSettings = userManagementService.getCanonicalUserSettings(user, accountType);
		session.setAttribute(CommonConstants.CANONICAL_USERSETTINGS_IN_SESSION, userSettings);
		LOG.info("Set the settings in session");
	}

	public void setSettingVariablesInSession(HttpSession session) {
		LOG.info("Settings related session values being set.");
		UserSettings userSettings = (UserSettings) session.getAttribute(CommonConstants.CANONICAL_USERSETTINGS_IN_SESSION);
		if (session.getAttribute(CommonConstants.CANONICAL_USERSETTINGS_IN_SESSION) != null) {
			// setting the logo name
			setLogo(session, userSettings);
			setProfileImage(session, userSettings);
			// check for the mail content
			setMailContent(session, userSettings);
			// set the highest role from the user's profiles
			setHighestRole(session, getCurrentUser());
		}
	}

	// JIRA SS-97 by RM-06 : BOC
	public void setLogoInSession(HttpSession session, UserSettings userSettings) {
		LOG.info("Setting logo in session");
		setLogo(session, userSettings);
		LOG.info("Logo successfully updated in session");
	}

	public void setProfileImageInSession(HttpSession session, UserSettings userSettings) {
		LOG.info("Setting logo in session");
		setProfileImage(session, userSettings);
		LOG.info("Logo successfully updated in session");
	}
	// JIRA SS-97 by RM-06 : EOC

	private void setLogo(HttpSession session, UserSettings userSettings) {
		LOG.debug("Setting logo name in the session");
		// check if company has a logo
		if (userSettings.getCompanySettings().getLogo() != null) {
			LOG.debug("Settings logo image from company settings");
			String logoUrl = userSettings.getCompanySettings().getLogo();
			session.setAttribute(CommonConstants.LOGO_DISPLAY_IN_SESSION, logoUrl);
		}
		else {
			LOG.debug("Could not find logo settings in company. Checking in lower heirarchy.");
			// TODO: Check the lower level hierarchy for logo
		}
	}

	private void setProfileImage(HttpSession session, UserSettings userSettings) {
		LOG.debug("Setting profile image name in the session");
		// check if company has a logo
		if (userSettings.getCompanySettings().getProfileImageUrl() != null) {
			LOG.debug("Settings profile image from company settings");
			String imageUrl = userSettings.getCompanySettings().getProfileImageUrl();
			session.setAttribute(CommonConstants.IMAGE_DISPLAY_IN_SESSION, imageUrl);
		}
		else {
			LOG.debug("Could not find profile image settings in company. Checking in lower heirarchy.");
			// TODO: Check the lower level hierarchy for logo
		}
	}

	private void setMailContent(HttpSession session, UserSettings userSettings) {
		LOG.debug("Setting mail content in the session");
		String body = null;
		FileContentReplacements replacements = new FileContentReplacements();
		replacements.setFileName(EmailTemplateConstants.EMAIL_TEMPLATES_FOLDER + EmailTemplateConstants.SURVEY_PARTICIPATION_MAIL_BODY);

		if (userSettings.getCompanySettings().getMail_content() == null) {
			LOG.debug("Setting default survey participation mail body.");

			try {
				List<String> paramOrder = new ArrayList<String>(Arrays.asList(paramOrderTakeSurveyReminder.split(",")));
				body = fileOperations.replaceFileContents(replacements);
				body = emailFormatHelper.replaceEmailBodyWithParams(body, paramOrder);
				
				session.setAttribute(CommonConstants.SURVEY_PARTICIPATION_MAIL_BODY_IN_SESSION, body);
				session.setAttribute(CommonConstants.SURVEY_PARTICIPATION_REMINDER_MAIL_BODY_IN_SESSION, body);
			}
			catch (InvalidInputException e) {
				LOG.warn("Could not set mail content for survey participation");
			}
		}
		else {
			LOG.debug("Company already has mail body settings. Hence, setting the same");

			MailContentSettings mailSettings = userSettings.getCompanySettings().getMail_content();
			if (userSettings.getCompanySettings().getMail_content().getTake_survey_mail() != null) {
				MailContent mailContent = mailSettings.getTake_survey_mail();
				String mailBody = emailFormatHelper.replaceEmailBodyWithParams(mailContent.getMail_body(), mailContent.getParam_order());
				
				mailSettings.getTake_survey_mail().setMail_body(mailBody);
				session.setAttribute(CommonConstants.SURVEY_PARTICIPATION_MAIL_BODY_IN_SESSION, mailBody);
			}
			else {
				try {
					List<String> paramOrder = new ArrayList<String>(Arrays.asList(paramOrderTakeSurveyReminder.split(",")));
					body = fileOperations.replaceFileContents(replacements);
					body = emailFormatHelper.replaceEmailBodyWithParams(body, paramOrder);
					
					session.setAttribute(CommonConstants.SURVEY_PARTICIPATION_MAIL_BODY_IN_SESSION, body);
				}
				catch (InvalidInputException e) {
					LOG.warn("Could not set mail content for survey participation");
				}
			}

			if (userSettings.getCompanySettings().getMail_content().getTake_survey_reminder_mail() != null) {
				MailContent mailContent = mailSettings.getTake_survey_reminder_mail();
				String mailBody = emailFormatHelper.replaceEmailBodyWithParams(mailContent.getMail_body(), mailContent.getParam_order());
				
				mailSettings.getTake_survey_reminder_mail().setMail_body(mailBody);
				session.setAttribute(CommonConstants.SURVEY_PARTICIPATION_REMINDER_MAIL_BODY_IN_SESSION, mailBody);
			}
			else {
				try {
					List<String> paramOrder = new ArrayList<String>(Arrays.asList(paramOrderTakeSurveyReminder.split(",")));
					body = fileOperations.replaceFileContents(replacements);
					body = emailFormatHelper.replaceEmailBodyWithParams(body, paramOrder);
					
					session.setAttribute(CommonConstants.SURVEY_PARTICIPATION_REMINDER_MAIL_BODY_IN_SESSION, body);
				}
				catch (InvalidInputException e) {
					LOG.warn("Could not set mail content for survey participation reminder");
				}
			}
		}
	}

	private void setHighestRole(HttpSession session, User user) {
		LOG.debug("Checking the highest role");
		List<UserProfile> userProfiles = user.getUserProfiles();
		if (userProfiles != null) {
			// sort the user profiles
			Collections.sort(userProfiles, new UserProfileComparator());
			// get the first one. that one will be the highest
			session.setAttribute(CommonConstants.HIGHEST_ROLE_ID_IN_SESSION, userProfiles.get(0).getProfilesMaster().getProfileId());
		}
	}

	/**
	 * Method to add new user into Principal
	 * 
	 * @param emailId
	 * @param password
	 * @return
	 */
	public void loginOnRegistration(String username, String password) {
		LOG.debug("Adding newly registered user to session");
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
			userAuthProvider.authenticate(auth);

			if (auth.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			}

			if (getCurrentUser() == null) {
				throw new NullPointerException();
			}
		}
		catch (Exception e) {
			SecurityContextHolder.getContext().setAuthentication(null);
			LOG.error("Problem authenticating user" + username, e);
		}
	}

	/**
	 * Method to get active user from Principal
	 * 
	 * @return User
	 */
	public User getCurrentUser() {
		final Object sessionUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = null;
		if (sessionUser instanceof User) {
			user = (User) sessionUser;
		}
		return user;
	}
	
	/**
	 * Method to update user profiles in session
	 * @throws NonFatalException 
	 */
	@SuppressWarnings("unchecked")
	public void updateProcessedUserProfiles(HttpSession session, User user) throws NonFatalException {
		LOG.info("Method updateProcessedUserProfiles() called from SessionHelper");

		UserProfile selectedProfile = (UserProfile) session.getAttribute(CommonConstants.USER_PROFILE);
		AccountType accountType = (AccountType) session.getAttribute(CommonConstants.ACCOUNT_TYPE_IN_SESSION);
		Map<Long, AbridgedUserProfile> profileAbridgedMap = (Map<Long, AbridgedUserProfile>) session.getAttribute(CommonConstants.USER_PROFILE_LIST);
		Map<Long, UserProfile> profileMap = (Map<Long, UserProfile>) session.getAttribute(CommonConstants.USER_PROFILE_MAP);
		
		if (profileMap == null) {
			profileMap = new HashMap<Long, UserProfile>();
		}

		List<UserProfile> profiles = userManagementService.getAllUserProfilesForUser(user);
		if (selectedProfile == null) {
			selectedProfile = profiles.get(CommonConstants.INITIAL_INDEX);
			for (UserProfile profile : profiles) {
				if (profile.getProfilesMaster().getProfileId() == CommonConstants.PROFILES_MASTER_AGENT_PROFILE_ID) {
					selectedProfile = profile;
					break;
				}
			}
			session.setAttribute(CommonConstants.USER_PROFILE, selectedProfile);
		}

		// updating session with aggregated user profiles, if not set
		profileAbridgedMap = userManagementService.processedUserProfiles(user, accountType, profileMap, profiles);
		if (profileAbridgedMap.size() > 0) {
			session.setAttribute(CommonConstants.USER_PROFILE_LIST, profileAbridgedMap);
			session.setAttribute(CommonConstants.PROFILE_NAME_COLUMN, profileAbridgedMap.get(selectedProfile.getUserProfileId()).getUserProfileName());
		}
		session.setAttribute(CommonConstants.USER_PROFILE_MAP, profileMap);

		LOG.info("Method updateProcessedUserProfiles() finished from SessionHelper");
	}
}
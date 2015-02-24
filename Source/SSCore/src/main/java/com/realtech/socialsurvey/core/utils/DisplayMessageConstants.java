package com.realtech.socialsurvey.core.utils;

// SS-14 By RM02 BOC

/**
 * Class containing variables holding the messages to be displayed in the application
 */
public final class DisplayMessageConstants {

	private DisplayMessageConstants() {}

	// error message constants
	public static final String INVALID_FIRSTNAME = "INVALID_FIRSTNAME";
	public static final String INVALID_LASTNAME = "INVALID_LASTNAME";
	public static final String INVALID_EMAILID = "INVALID_EMAILID";
	public static final String INVALID_CAPTCHA = "INVALID_CAPTCHA";
	public static final String REGISTRATION_INVITE_GENERAL_ERROR = "REGISTRATION_INVITE_GENERAL_ERROR";
	public static final String GENERAL_ERROR = "GENERAL_ERROR";
	public static final String INVALID_USERNAME = "INVALID_USERNAME";
	public static final String INVALID_PASSWORD = "INVALID_PASSWORD";
	public static final String PASSWORDS_MISMATCH = "PASSWORDS_MISMATCH";
	public static final String REGISTRATION_GENERAL_ERROR = "REGISTRATION_GENERAL_ERROR";
	public static final String INVALID_REGISTRATION_URL = "INVALID_REGISTRATION_URL";
	public static final String INVALID_COMPANY_NAME = "INVALID_COMPANY_NAME";
	public static final String INVALID_ADDRESS = "INVALID_ADDRESS";
	public static final String INVALID_ZIPCODE = "INVALID_ZIPCODE";
	public static final String INVALID_COMPANY_PHONEN0 = "INVALID_COMPANY_PHONEN0";
	public static final String INVALID_REGISTRATION_INVITE = "INVALID_REGISTRATION_INVITE";
	public static final String USERNAME_ALREADY_TAKEN = "USERNAME_ALREADY_TAKEN";
	public static final String EMAILID_ALREADY_TAKEN = "EMAILID_ALREADY_TAKEN";
	public static final String INVALID_USER_CREDENTIALS = "INVALID_USER_CREDENTIALS";
	public static final String INVALID_USER = "INVALID_USER";
	public static final String USER_INACTIVE = "USER_INACTIVE";
	public static final String COMPANY_INACTIVE = "COMPANY_INACTIVE";
	public static final String USER_NOT_PRESENT = "USER_NOT_PRESENT";
	public static final String SUBSCRIPTION_UNSUCCESSFUL = "USER_SUBSCRIPTION_UNSUCCESSFUL";
	public static final String INVALID_REGION_NAME = "INVALID_REGION_NAME";
	public static final String INVALID_REGION_ADDRESS = "INVALID_REGION_ADDRESS";
	public static final String INVALID_BRANCH_NAME = "INVALID_BRANCH_NAME";
	public static final String INVALID_BRANCH_ADDRESS = "INVALID_BRANCH_ADDRESS";
	public static final String INVALID_VERIFICATION_URL = "INVALID_VERIFICATION_URL";
	public static final String INVALID_LOGO_FORMAT = "INVALID_LOGO_FORMAT";
	public static final String INVALID_LOGO_DIMENSIONS = "INVALID_LOGO_DIMENSIONS";
	public static final String INVALID_LOGO_SIZE = "INVALID_LOGO_SIZE";
	public static final String INVALID_LOGO_FILE = "INVALID_LOGO_FILE";
	public static final String MAX_USERS_LIMIT_REACHED = "MAX_USERS_LIMIT_REACHED";
	public static final String USER_MANAGEMENT_NOT_AUTHORIZED = "USER_MANAGEMENT_NOT_AUTHORIZED";
	public static final String BRANCH_ASSIGNING_NOT_AUTHORIZED = "BRANCH_ASSIGNING_NOT_AUTHORIZED";
	public static final String NO_USER_IN_SESSION = "NO_USER_IN_SESSION";
	public static final String USER_SEARCH_SUCCESSFUL = "USER_SEARCH_SUCCESSFUL";
	public static final String SESSION_EXPIRED = "SESSION_EXPIRED";
	public static final String INVALID_COUNTRY = "INVALID_COUNTRY";
	public static final String INVALID_VERTICAL = "INVALID_VERTICAL";

	public static final String INVALID_REGION_SELECTED = "INVALID_REGION_SELECTED";
	public static final String INVALID_SURVEY_REMINDER_INTERVAL = "INVALID_SURVEY_REMINDER_INTERVAL";
	public static final String SUBSCRIPTION_UPGRADE_UNSUCCESSFUL = "SUBSCRIPTION_UPGRADE_UNSUCCESSFUL";
	public static final String SUBSCRIPTION_PAST_DUE = "SUBSCRIPTION_PAST_DUE";
	public static final String INVALID_CURRENT_PASSWORD = "INVALID_CURRENT_PASSWORD";
	public static final String INVALID_NEW_PASSWORD = "INVALID_NEW_PASSWORD";
	public static final String INVALID_CONFIRM_NEW_PASSWORD = "INVALID_CONFIRM_NEW_PASSWORD";
	public static final String PAYMENT_GATEWAY_EXCEPTION = "PAYMENT_GATEWAY_EXCEPTION";
	public static final String CARD_UPDATE_UNSUCCESSFUL = "CARD_UPDATE_UNSUCCESSFUL";
	public static final String CREDIT_CARD_INVALID = "CREDIT_CARD_INVALID";
	public static final String INVALID_SURVEY_ANSWER = "INVALID_SURVEY_ANSWER";
	public static final String NO_SURVEY_FOUND = "NO_SURVEY_FOUND";
	
	// success message constants
	public static final String REGISTRATION_INVITE_SUCCESSFUL = "REGISTRATION_INVITE_SUCCESSFUL";
	public static final String USER_REGISTRATION_SUCCESSFUL = "USER_REGISTRATION_SUCCESSFUL";
	public static final String ACCOUNT_TYPE_SELECTION_SUCCESSFUL = "ACCOUNT_TYPE_SELECTION_SUCCESSFUL";
	public static final String PASSWORD_RESET_LINK_SUCCESSFUL = "PASSWORD_RESET_LINK_SUCCESSFUL";
	public static final String PASSWORD_CHANGE_SUCCESSFUL = "PASSWORD_CHANGE_SUCCESSFUL";
	public static final String USER_LOGIN_SUCCESSFUL = "USER_LOGIN_SUCCESSFUL";
	public static final String SUBSCRIPTION_SUCCESSFUL = "USER_SUBSCRIPTION_SUCCESSFUL";
	public static final String BRANCH_DELETE_SUCCESSFUL = "BRANCH_DELETE_SUCCESSFUL";
	public static final String REGION_DELETE_SUCCESSFUL = "REGION_DELETE_SUCCESSFUL";
	public static final String EMAIL_VERIFICATION_SUCCESSFUL = "EMAIL_VERIFICATION_SUCCESSFUL";
	public static final String USER_DELETE_SUCCESSFUL = "USER_DELETE_SUCCESSFUL";
	public static final String USER_STATUS_UPDATE_SUCCESSFUL = "USER_STATUS_UPDATE_SUCCESSFUL";
	public static final String BRANCH_UNASSIGN_SUCCESSFUL = "BRANCH_UNASSIGN_SUCCESSFUL";
	public static final String BRANCH_ASSIGN_SUCCESSFUL = "BRANCH_ASSIGN_SUCCESSFUL";
	public static final String BRANCH_ADDITION_SUCCESSFUL = "BRANCH_ADDITION_SUCCESSFUL";
	public static final String REGION_ADDTION_SUCCESSFUL = "REGION_ADDTION_SUCCESSFUL";
	public static final String REGION_UPDATION_SUCCESSFUL = "REGION_UPDATION_SUCCESSFUL";
	public static final String BRANCH_UPDATION_SUCCESSFUL = "BRANCH_UPDATION_SUCCESSFUL";
	public static final String ENCOMPASS_CONNECTION_SUCCESSFUL = "ENCOMPASS_CONNECTION_SUCCESSFUL";
	public static final String ENCOMPASS_DATA_UPDATE_SUCCESSFUL = "ENCOMPASS_DATA_UPDATE_SUCCESSFUL";
	public static final String SURVEY_PARTICIPATION_MAILBODY_UPDATE_SUCCESSFUL = "SURVEY_PARTICIPATION_MAILBODY_UPDATE_SUCCESSFUL";
	public static final String SURVEY_PARTICIPATION_REMINDERMAILBODY_UPDATE_SUCCESSFUL = "SURVEY_PARTICIPATION_REMINDERMAILBODY_UPDATE_SUCCESSFUL";
	public static final String SURVEY_AUTO_POST_SCORE_UPDATE_SUCCESSFUL = "SURVEY_AUTO_POST_SCORE_UPDATE_SUCCESSFUL";
	public static final String SURVEY_MIN_POST_SCORE_UPDATE_SUCCESSFUL = "SURVEY_MIN_POST_SCORE_UPDATE_SUCCESSFUL";
	public static final String SURVEY_REMINDER_INTERVAL_UPDATE_SUCCESSFUL = "SURVEY_REMINDER_INTERVAL_UPDATE_SUCCESSFUL";
	public static final String SURVEY_REMINDER_ENABLED_UPDATE_SUCCESSFUL = "SURVEY_REMINDER_ENABLED_UPDATE_SUCCESSFUL";
	public static final String LOCATION_SETTINGS_UPDATE_SUCCESSFUL = "LOCATION_SETTINGS_UPDATE_SUCCESSFUL";
	public static final String ACCOUNT_SETTINGS_UPDATE_SUCCESSFUL = "ACCOUNT_SETTINGS_UPDATE_SUCCESSFUL";
	public static final String USER_LOGOUT_SUCCESSFUL = "USER_LOGOUT_SUCCESSFUL";
	public static final String TO_INDIVIDUAL_SUBSCRIPTION_UPGRADE_SUCCESSFUL = "TO_INDIVIDUAL_SUBSCRIPTION_UPGRADE_SUCCESSFUL";
	public static final String TO_TEAM_SUBSCRIPTION_UPGRADE_SUCCESSFUL = "TO_TEAM_SUBSCRIPTION_UPGRADE_SUCCESSFUL";
	public static final String TO_COMPANY_SUBSCRIPTION_UPGRADE_SUCCESSFUL = "TO_COMPANY_SUBSCRIPTION_UPGRADE_SUCCESSFUL";
	public static final String TO_ENTERPRISE_SUBSCRIPTION_UPGRADE_SUCCESSFUL = "TO_ENTERPRISE_SUBSCRIPTION_UPGRADE_SUCCESSFUL";
	public static final String CARD_UPDATE_SUCCESSFUL = "CARD_UPDATE_SUCCESSFUL";
	public static final String SUBSCRIPTION_UPGRADE_SUCCESSFUL = "SUBSCRIPTION_UPGRADE_SUCCESSFUL";
	public static final String ASSOCIATION_UPDATE_SUCCESSFUL = "ASSOCIATION_UPDATE_SUCCESSFUL";
	public static final String ACHIEVEMENT_UPDATE_SUCCESSFUL = "ACHIEVEMENT_UPDATE_SUCCESSFUL";
	public static final String PROFILE_ADDRESSES_UPDATE_SUCCESSFUL = "PROFILE_ADDRESSES_UPDATE_SUCCESSFUL";
	public static final String LICENSES_UPDATE_SUCCESSFUL = "LICENSES_UPDATE_SUCCESSFUL";
	public static final String ABOUT_ME_DETAILS_UPDATE_SUCCESSFUL = "ABOUT_ME_DETAILS_UPDATE_SUCCESSFUL";
	public static final String MAIL_IDS_UPDATE_SUCCESSFUL = "MAIL_IDS_UPDATE_SUCCESSFUL";
	public static final String CONTACT_NUMBERS_UPDATE_SUCCESSFUL = "CONTACT_NUMBERS_UPDATE_SUCCESSFUL";
	public static final String WEB_ADDRESSES_UPDATE_SUCCESSFUL = "WEB_ADDRESSES_UPDATE_SUCCESSFUL";
	public static final String LOGO_UPLOAD_SUCCESSFUL = "LOGO_UPLOAD_SUCCESSFUL";
	
	public static final String SURVEY_CREATION_SUCCESSFUL = "SURVEY_CREATION_SUCCESSFUL";
	public static final String SURVEY_ALREADY_EXISTS = "SURVEY_ALREADY_EXISTS";
	public static final String SURVEY_QUESTION_MAPPING_SUCCESSFUL = "SURVEY_QUESTION_MAPPING_SUCCESSFUL";
	public static final String SURVEY_QUESTION_MODIFY_SUCCESSFUL = "SURVEY_QUESTION_MODIFY_SUCCESSFUL";
	public static final String SURVEY_QUESTION_DISABLE_SUCCESSFUL = "SURVEY_QUESTION_DISABLE_SUCCESSFUL";
	public static final String SURVEY_QUESTIONS_DISABLE_SUCCESSFUL = "SURVEY_QUESTIONS_DISABLE_SUCCESSFUL";
	public static final String SURVEY_QUESTION_REORDER_SUCCESSFUL = "SURVEY_QUESTION_REORDER_SUCCESSFUL";
	public static final String SURVEY_COMPANY_DISABLE_SUCCESSFUL = "SURVEY_COMPANY_DISABLE_SUCCESSFUL";
	public static final String SURVEY_TEMPLATE_CLONE_SUCCESSFUL = "SURVEY_TEMPLATE_CLONE_SUCCESSFUL";
}

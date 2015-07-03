package com.realtech.socialsurvey.core.commons;

/**
 * Holds application level constants
 */
public interface CommonConstants
{

    /**
     * Property file constants
     */
    public static final String CONFIG_PROPERTIES_FILE = "config.properties";
    public static final String MESSAGE_PROPERTIES_FILE = "displaymessage.properties";
    public static final String SENDGRID_SENDER_USERNAME = "SENDGRID_SENDER_USERNAME";
    public static final String SENDGRID_SENDER_NAME = "SENDGRID_SENDER_NAME";
    public static final String SENDGRID_SENDER_PASSWORD = "SENDGRID_SENDER_PASSWORD";

    /**
     * Default constants
     */
    // default company id for application. if any entity is linked to this id, then its an orphan
    // entity
    public static final long DEFAULT_COMPANY_ID = 1;
    public static final String DEFAULT_BRANCH_NAME = "Default Branch";
    public static final String DEFAULT_REGION_NAME = "Default Region";
    public static final String DEFAULT_ADDRESS = "Default Address";
    public static final long DEFAULT_REGION_ID = 0;
    public static final long DEFAULT_BRANCH_ID = 0;
    public static final long DEFAULT_AGENT_ID = 0;
    public static final String DEFAULT_SOURCE_APPLICATION = "AP";

    /**
     * Profile master constants
     */
    public static final int PROFILES_MASTER_COMPANY_ADMIN_PROFILE_ID = 1;
    public static final int PROFILES_MASTER_REGION_ADMIN_PROFILE_ID = 2;
    public static final int PROFILES_MASTER_BRANCH_ADMIN_PROFILE_ID = 3;
    public static final int PROFILES_MASTER_AGENT_PROFILE_ID = 4;
    public static final int PROFILES_MASTER_NO_PROFILE_ID = 10;

    /**
     * Accounts master constants
     */
    public static final int ACCOUNTS_MASTER_FREE = 5;
    public static final int ACCOUNTS_MASTER_INDIVIDUAL = 1;
    public static final int ACCOUNTS_MASTER_TEAM = 2;
    public static final int ACCOUNTS_MASTER_COMPANY = 3;
    public static final int ACCOUNTS_MASTER_ENTERPRISE = 4;

    /**
     * Verticals master constants
     */
    public static final int VERTICALS_MASTER_CUSTOM = -1;
    public static final int VERTICALS_MASTER_BANKING = 1;
    public static final int VERTICALS_MASTER_MORTGAGE = 2;
    public static final int VERTICALS_MASTER_REALTOR = 3;

    /**
     * Profile completion stages constants and form action constants, store the url mappings
     */
    public static final String ADD_COMPANY_STAGE = "addcompanyinformation.do";
    public static final String ADD_ACCOUNT_TYPE_STAGE = "addaccounttype.do";
    public static final String RESET_PASSWORD = "resetpassword.do";
    public static final String MANUAL_REGISTRATION = "invitetoregister.do";
    public static final String DASHBOARD_STAGE = "dashboard.do";
    public static final String PROFILE_STAGES_COMPLETE = "complete";
    public static final String REQUEST_MAPPING_EMAIL_EDIT_VERIFICATION = "emailverification.do";
    public static final String REQUEST_MAPPING_SHOW_REGISTRATION = "showregistrationpage.do";
    public static final String REQUEST_MAPPING_MAIL_VERIFICATION = "verification.do";
    public static final String SHOW_COMPLETE_REGISTRATION_PAGE = "showcompleteregistrationpage.do";
    public static final String PRE_PROCESSING_BEFORE_LOGIN_STAGE = "defaultbrandandregioncreation.do";
    public static final String START_SURVEY = "rest/survey/start";

    /**
     * Status constants
     */
    public static final int ONE = 1;
    public static final int STATUS_ACTIVE = 1;
    public static final int STATUS_INACTIVE = 0;
    public static final int STATUS_SURVEY_TEMPLATE = 2;
    public static final int STATUS_NOT_VERIFIED = 2;
    public static final int STATUS_UNDER_PROCESSING = 2;
    public static final int STATUS_ACCOUNT_DISABLED = 2;
    public static final int STATUS_COMPANY_DISABLED = 2;
    public static final int STATUS_TEMPORARILY_INACTIVE = 3;
    public static final int PROCESS_COMPLETE = 1;
    public static final int PROCESS_NOT_STARTED = 0;
    public static final int IS_DEFAULT_BY_SYSTEM_YES = 1;
    public static final int IS_DEFAULT_BY_SYSTEM_NO = 0;
    public static final int YES = 1;
    public static final int NO = 0;
    public static final int SANDBOX_MODE_TRUE = 1;
    public static final int IS_OWNER = 1;
    public static final int IS_NOT_OWNER = 0;
    public static final int SUBSCRIPTION_DUE = 1;
    public static final int SUBSCRIPTION_NOT_DUE = 0;
    public static final String IS_ASSIGN_ADMIN = "YES";
    public static final String IS_UNASSIGN_ADMIN = "NO";
    public static final int EMPTY_LIST = 0;
    public static final int MAX_DEFAULT_REGIONS = 1;
    public static final int MAX_DEFAULT_BRANCHES = 1;
    public static final double MIN_RATING_SCORE = 0;
    public static final double MAX_RATING_SCORE = 5;
    public static final String YES_STRING = "Y";
    public static final String NO_STRING = "N";
    public static final int SURVEY_STATUS_PRE_INITIATED = 1;
    public static final int SURVEY_STATUS_INITIATED = 2;
    public static final int STATUS_SURVEYPREINITIATION_NOT_PROCESSED = 2;
    public static final int STATUS_SURVEYPREINITIATION_PROCESSED = 3;

    /**
     * Hibernate entities and column name constants
     */
    public static final String USER_INVITE_INVITATION_PARAMETERS_COLUMN = "invitationParameters";
    public static final String STATUS_COLUMN = "status";
    public static final String INVITATION_EMAIL_ID_COLUMN = "invitationEmailId";
    public static final String USER_LOGIN_NAME_COLUMN = "loginName";
    public static final String USER_INVITE_INVITATION_VALID_UNTIL_COLUMN = "invitationValidUntil";
    public static final String USER_COLUMN = "user";
    public static final String PROFILE_MASTER_COLUMN = "profilesMaster";
    public static final String AUTO_PAYMENT_MODE = "A";
    public static final int INITIAL_PAYMENT_RETRIES = 0;
    public static final String PAYMENT_GATEWAY = "Braintree";
    public static final String FREE_ACCOUNT = "Free Account";
    public static final String IS_DEFAULT_BY_SYSTEM = "isDefaultBySystem";
    public static final String COMPANY_COLUMN = "company";
    public static final String COMPANY_ID_COLUMN = "companyId";
    public static final String IS_OWNER_COLUMN = "isOwner";
    public static final String LICENSE_DETAIL_COLUMN = "licenseDetail";
    public static final String REGION_COLUMN = "region";
    public static final String REGION_ID_COLUMN = "regionId";
    public static final String PROFILE_NAME_COLUMN = "profileName";
    public static final String BRANCH_ID_COLUMN = "branchId";
    public static final String BRANCH_NAME_COLUMN = "branch";
    public static final String SUBSCRIPTION_ID_COLUMN = "subscriptionId";
    public static final String REGION_NAME_COLUMN = "region";
    public static final String SURVEY_QUESTION_COLUMN = "surveyQuestion";
    public static final String SURVEY_COLUMN = "survey";
    public static final String SURVEY_COMPANY_COLUMN = "company";
    public static final String SURVEY_QUESTION_ORDER_COLUMN = "questionOrder";
    public static final String SURVEY_IS_RATING_QUESTION_COLUMN = "isRatingQuestion";
    public static final String VERTICALS_MASTER_NAME_COLUMN = "verticalName";
    public static final String FEED_SOURCE_COLUMN = "feedSource";
    public static final String PASSWORD_COLUMN = "loginPassword";
    public static final String API_SECRET_COLUMN = "apiSecret";
    public static final String API_KEY_COLUMN = "apiKey";
    public static final String SURVEY_SOURCE_KEY_COLUMN = "surveySource";
    public static final String SURVEY_SOURCE_ID_COLUMN = "surveySourceId";

    /**
     * Mongo entities and column name constants
     */
    public static final String AGENT_ID_COLUMN = "agentId";
    public static final String CUSTOMER_EMAIL_COLUMN = "customerEmail";
    public static final String CREATED_ON = "createdOn";
    public static final String UPDATED_ON = "updatedOn";
    public static final String MODIFIED_ON_COLUMN = "modifiedOn";
    public static final String SCORE_COLUMN = "score";
    public static final String SHARED_ON_COLUMN = "sharedOn";
    public static final String INITIATED_BY_COLUMN = "initiated By";
    public static final String STAGE_COLUMN = "stage";
    public static final String REMINDER_COUNT_COLUMN = "reminderCount";
    public static final String MOOD_COLUMN = "mood";
    public static final String SURVEY_CLICKED_COLUMN = "surveyClicked";
    public static final String IS_ABUSIVE_COLUMN = "isAbusive";
    public static final String DEFAULT_MONGO_ID_COLUMN = "_id";
    public static final String LAST_REMINDER_FOR_INCOMPLETE_SURVEY = "lastReminderForIncompleteSurvey";
    public static final String REMINDERS_FOR_INCOMPLETE_SURVEYS = "remindersForIncompleteSurveys";
    public static final String LAST_REMINDER_FOR_SOCIAL_POST = "lastReminderForSocialPost";
    public static final String REMINDERS_FOR_SOCIAL_POSTS = "remindersForSocialPosts";
    public static final String REVIEW_COUNT_MONGO = "reviewCount";
    public static final String EDITABLE_SURVEY_COLUMN = "editable";

    /**
     * Constants to be used in code for referencing variables(i.e in maps or session attributes)
     */
    public static final String ACCOUNT_TYPE_IN_SESSION = "accounttype";
    public static final String CANONICAL_USERSETTINGS_IN_SESSION = "cannonicalusersettings";
    public static final String COMPANY_NAME = "companyName";
    public static final String ADDRESS = "address";
    public static final String ZIPCODE = "zipCode";
    public static final String COMPANY_CONTACT_NUMBER = "companyContactNo";
    public static final String COMPANY = "company";
    public static final String EMAIL_ID = "emailId";
    public static final String CURRENT_TIMESTAMP = "currentTimestamp";
    public static final String ACCOUNT_CRETOR_EMAIL_ID = "creatorEmailId"; //used for registration via invite
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    public static final String USER_ID = "userId";
    public static final String JOB_PARAMETER_NAME = "date";
    public static final String LOGO_DISPLAY_IN_SESSION = "displaylogo";
    public static final String IMAGE_DISPLAY_IN_SESSION = "displayimage";
    public static final String SURVEY_PARTICIPATION_MAIL_BODY_IN_SESSION = "surveymailbody";
    public static final String SURVEY_PARTICIPATION_MAIL_SUBJECT_IN_SESSION = "surveymailsubject";
    public static final String SURVEY_PARTICIPATION_REMINDER_MAIL_BODY_IN_SESSION = "surveyremindermailbody";
    public static final String SURVEY_PARTICIPATION_REMINDER_MAIL_SUBJECT_IN_SESSION = "surveyremindermailsubject";
    public static final String LOGIN_NAME = "loginName";
    public static final String HIGHEST_ROLE_ID_IN_SESSION = "highestrole";
    public static final String PAYMENT_NONCE = "payment_method_nonce";
    public static final String CURRENT_LICENSE_ID = "currentplan";
    public static final String UPGRADE_FLAG = "upgrade";
    public static final String COUNTRY_CODE = "countryCode";
    public static final String COUNTRY = "country";
    public static final String STATE = "state";
    public static final String CITY = "city";
    public static final String CRM_SOURCE = "crm_source";
    public static final String VERTICAL = "vertical";
    // JIRA - SS-536: Added for manual registration via invite
    public static final String BILLING_MODE_COLUMN = "billingMode";
    public static final String VERTICAL_COLUMN = "verticalsMaster";
    public static final String PAID_PLAN_UPGRADE_FLAG = "paidUpgrade";
    public static final String LINKEDIN_AUTH_URL = "authUrl";
    public static final String LINKEDIN_REQUEST_TOKEN = "linkedinRequestToken";
    public static final String SUCCESS_ATTRIBUTE = "success";
    public static final String PARENT_LOCK = "parentLock";
    public static final String USER_PROFILE = "profile";
    public static final String USER_PROFILE_LIST = "profileList";
    public static final String USER_PROFILE_MAP = "profileMap";
    public static final String USER_PROFILE_SETTINGS = "profileSettings";
    public static final String USER_ACCOUNT_SETTINGS = "accountSettings";
    public static final String ERROR = "error";
    public static final String MESSAGE = "message";
    public static final String EMAIL_TYPE = "emailtype";
    public static final String EMAIL_TYPE_WORK = "work";
    public static final String EMAIL_TYPE_PERSONAL = "personal";
    public static final String SOCIAL_AUTH_URL = "authUrl";
    public static final String SOCIAL_REQUEST_TOKEN = "requestToken";
    public static final String SOCIAL_FLOW = "socialFlow";
    public static final String BRANCHES_IN_SESSION = "branchesInSession";
    public static final String REGIONS_IN_SESSION = "regionsInSession";
    public static final String PROFILE_AGENT_VIEW = "Myself";
    public static final String FACEBOOK_SOCIAL_SITE = "facebook";
    public static final String TWITTER_SOCIAL_SITE = "twitter";
    public static final String LINKEDIN_SOCIAL_SITE = "linkedin";
    public static final String YELP_SOCIAL_SITE = "yelp";
    public static final String GOOGLE_SOCIAL_SITE = "google";
    public static final String TWITTER_BASE_URL = "www.twitter.com/";
    public static final String COMPANY_ID = "companyId";
    public static final String REGION_ID = "regionId";
    public static final String BRANCH_ID = "branchId";
    public static final String AGENT_ID = "agentId";
    public static final String FLOW_REGISTRATION = "registration";
    public static final String POPUP_FLAG_IN_SESSION = "popupStatus";
    public static final String ACTIVE_SESSIONS_FOUND = "activeSessionFound";

    /**
     * Batch Constants
     */
    public static final String CASE_NONE = "None";
    public static final String CASE_SETTLING = "Settling";
    public static final String CASE_SETTLED = "Settled";
    public static final String CASE_GENERAL = "General";
    public static final String CASE_RETRIES_EXCEEDED = "RetriesExceeded";
    public static final String CASE_KEY = "Case";
    public static final String LICENSE_DETAIL_OBJECT_KEY = "LicenseDetailObject";
    public static final String RETRIED_TRANSACTION_OBJECT_KEY = "RetriedTransactionObject";
    public static final String COMPANY_OBJECT_KEY = "CompanyObject";
    public static final String DISABLED_ACCOUNT_OBJECT_KEY = "DisabledAccountObject";

    /**
     * other constants
     */
    public static final long EPOCH_TIME_IN_MILLIS = 1000l;
    public static final String GUEST_USER_NAME = "GUEST";
    public static final String ADMIN_USER_NAME = "ADMIN";
    public static final int MAX_BRANCH_LIMIT_TEAM = 1;
    public static final int NO_LIMIT = -1;
    public static final int MAX_REGION_LIMIT_COMPANY = 1;
    public static final int INITIAL_INDEX = 0;
    public static final int PAYMENT_INCREMENT = 1;
    public static final int DEFAULT_BRANCH_REGION_ROWS = 10;
    public static final int MAX_LICENSE_DETAILS_RECORDS_PER_COMPANY = 1;
    public static final int MINIMUM_SIZE_OF_ARRAY = 1;
    public static final String PROFILE_LEVEL_COMPANY = "COMPANY";
    public static final String PROFILE_LEVEL_REGION = "REGION";
    public static final String PROFILE_LEVEL_BRANCH = "BRANCH";
    public static final String PROFILE_LEVEL_INDIVIDUAL = "INDIVIDUAL";
    public static final String USERS_MAP_KEY = "users";
    public static final String BRANCHES_MAP_KEY = "branches";
    public static final String REGIONS_MAP_KEY = "regions";
    public static final String REMINDER_MAIL_SUBJECT = "Quick Thank You from ";
    public static final String SURVEY_MAIL_SUBJECT = "Transaction with ";
    public static final String SURVEY_MAIL_SUBJECT_CUSTOMER = "Invitation to take survey";
    public static final String AGENT_PROFILE_FIXED_URL = "pages";
    public static final String BRANCH_PROFILE_FIXED_URL = "pages";
    public static final String REGION_PROFILE_FIXED_URL = "pages";
    public static final String COMPANY_PROFILE_FIXED_URL = "pages/company";
    public static final float DEFAULT_AUTOPOST_SCORE = 3.5f;
    public static final int DEFAULT_REMINDERMAIL_INTERVAL = 3;
    public static final String USER_SELECTION_TYPE_SINGLE = "single";
    public static final String USER_SELECTION_TYPE_MULTIPLE = "multiple";
    public static final String REVIEWS_SORT_CRITERIA_DATE = "date";
    public static final String REVIEWS_SORT_CRITERIA_FEATURE = "feature";
    public static final String REVIEWS_SORT_CRITERIA_DEFAULT = "default";
    public static final String TEMP_FOLDER = "Temp";
    public static final String LINKEDIN_URL_PART = "licdn";

    /**
     * Email templates config
     */
    public static final String SURVEY_REQUEST_MAIL_FILENAME = "EmailTemplates/SurveyInvitationMailBody.html";
    public static final String SURVEY_CUSTOMER_REQUEST_MAIL_FILENAME = "EmailTemplates/SurveyCustomerInvitationMailBody.html";
    public static final String SURVEY_REMINDER_MAIL_FILENAME = "EmailTemplates/SurveyReminderMailBody.html";

    /**
     * Logo related config
     */
    public static final String MAX_LOGO_SIZE_BYTES = "MAX_LOGO_SIZE_BYTES";
    public static final String MAX_LOGO_WIDTH_PIXELS = "MAX_LOGO_WIDTH_PIXELS";
    public static final String MAX_LOGO_HEIGHT_PIXELS = "MAX_LOGO_HEIGHT_PIXELS";
    public static final String LIST_LOGO_FORMATS = "LIST_LOGO_FORMATS";
    public static final String LOGO_HOME_DIRECTORY = "LOGO_HOME_DIRECTORY";
    public static final String LOGO_NAME = "logoName";
    public static final String IMAGE_DIR = "imageupload";
    public static final String IMAGE_NAME = "image.png";
    public static final String IMAGE_FORMAT_PNG = "png";

    /**
     * Amazon Details
     */
    public static final String AMAZON_ACCESS_KEY = "AMAZON_ACCESS_KEY";
    public static final String AMAZON_SECRET_KEY = "AMAZON_SECRET_KEY";
    public static final String AMAZON_ENDPOINT = "AMAZON_ENDPOINT";
    public static final String AMAZON_BUCKET = "AMAZON_BUCKET";
    public static final String AMAZON_ENV_PREFIX = "AMAZON_ENV_PREFIX";
    public static final String SYMBOL_HYPHEN = "-";
    public static final String SYMBOL_FULLSTOP = ".";

    // settings constants
    public static final String CRM_INFO_SOURCE_ENCOMPASS = "encompass";
    public static final String CRM_SOURCE_ENCOMPASS = "ENCOMPASS";
    public static final String CRM_SOURCE_DOTLOOP = "DOTLOOP";

    // mail content
    public static final String SURVEY_MAIL_BODY_CATEGORY = "SURVEY_MAIL_BODY_CATEGORY";
    public static final String SURVEY_REMINDER_MAIL_BODY_CATEGORY = "SURVEY_REMINDER_MAIL_BODY_CATEGORY";

    // regular expressions
    public static final String PASSWORD_REG_EX = "^(?=.*[a-zA-Z0-9])(?=.*[!@#$%&*()_+=|<>?{}~-]).{6,}$";
    public static final int PASSWORD_LENGTH = 6;
    public static final String PHONENUMBER_REGEX = "^((\\+)|(00)|(\\*)|())[0-9]{3,14}((\\#)|())$";
    public static final String ZIPCODE_REGEX = "\\d{5}(-\\d{4})?";
    public static final String COMPANY_NAME_REGEX = "^[a-zA-Z0-9 ]+$";
    public static final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public static final String FIRST_NAME_REGEX = "[a-zA-Z ]+";
    public static final String LAST_NAME_REGEX = "[a-zA-Z0-9 ]+";
    public static final String FINDAPRO_FIRST_NAME_REGEX = "^[a-zA-Z]{2,}$";
    public static final String FINDAPRO_LAST_NAME_REGEX = "^[a-zA-Z]{2,}$";

    /**
     * Solr document related constants
     */
    public static final String REGION_ID_SOLR = "regionId";
    public static final String COMPANY_ID_SOLR = "companyId";
    public static final String IS_DEFAULT_BY_SYSTEM_SOLR = "isDefaultBySystem";
    public static final String STATUS_SOLR = "status";
    public static final String REGION_NAME_SOLR = "regionName";
    public static final String ADDRESS1_SOLR = "address1";
    public static final String ADDRESS2_SOLR = "address2";
    public static final String BRANCH_ID_SOLR = "branchId";
    public static final String BRANCH_NAME_SOLR = "branchName";
    public static final String BRANCH_ADDRESS_SOLR = "address";
    public static final String USER_ID_SOLR = "userId";
    public static final String USER_FIRST_NAME_SOLR = "firstName";
    public static final String USER_LAST_NAME_SOLR = "lastName";
    public static final String USER_DISPLAY_NAME_SOLR = "displayName";
    public static final String USER_LOGIN_NAME_SOLR = "loginName";
    public static final String USER_EMAIL_ID_SOLR = "emailId";
    public static final String USER_IS_OWNER_SOLR = "isOwner";
    public static final String BRANCHES_SOLR = "branches";
    public static final String REGIONS_SOLR = "regions";
    public static final String ADDRESS1 = "address1";
    public static final String ADDRESS2 = "address2";
    public static final String IS_AGENT_SOLR = "isAgent";
    public static final String IS_BRANCH_ADMIN_SOLR = "isBranchAdmin";
    public static final String IS_REGION_ADMIN_SOLR = "isRegionAdmin";
    public static final boolean IS_AGENT_TRUE_SOLR = true;
    public static final boolean IS_AGENT_FALSE_SOLR = false;
    public static final String ABOUT_ME_SOLR = "aboutMe";
    public static final String PROFILE_NAME_SOLR = "profileName";
    public static final String PROFILE_URL_SOLR = "profileUrl";
    public static final String PROFILE_IMAGE_URL_SOLR = "profileImageUrl";
    public static final String REVIEW_COUNT_SOLR = "reviewCount";
    public static final String TITLE_SOLR = "title";

    /*
     * Payment detail constants
     */
    public static final String CARD_NUMBER = "cardNumber";
    public static final String CARD_TYPE = "cardType";
    public static final String CARD_HOLDER_NAME = "cardHolderName";
    public static final String ISSUING_BANK = "issuingBank";
    public static final String IMAGE_URL = "imageUrl";
    public static final String CLIENT_TOKEN = "clienttoken";
    public static final String PAYMENT_CHANGE_FLAG = "paymentChange";
    public static final int STATUS_PAYMENT_FAILED = 2;
    public static final String DISABLED_ACCOUNT_FLAG = "disabled";
    public static final int PAYMENT_RETRIES = 2;

    // Survey Constants
    public static final String QUESTION_MULTIPLE_CHOICE = "mcq";
    public static final String QUESTION_RATING = "range";
    public static final int QUESTION_RATING_VALUE_TRUE = 1;
    public static final int QUESTION_RATING_VALUE_FALSE = 0;
    public static final int SURVEY_STAGE_COMPLETE = -1;
    public static final String SURVEY_CUSTOMER_MOOD_SAD = "sad";

    /**
     * Error codes
     */
    public static final int ERROR_CODE_GENERAL = 100;
    public static final int ERROR_CODE_COMPANY_PROFILE_PRECONDITION_FAILURE = 101;
    public static final int ERROR_CODE_COMPANY_PROFILE_SERVICE_FAILURE = 102;
    public static final int ERROR_CODE_REGION_PROFILE_PRECONDITION_FAILURE = 103;
    public static final int ERROR_CODE_REGION_PROFILE_SERVICE_FAILURE = 104;
    public static final int ERROR_CODE_BRANCH_PROFILE_PRECONDITION_FAILURE = 105;
    public static final int ERROR_CODE_BRANCH_PROFILE_SERVICE_FAILURE = 106;
    public static final int ERROR_CODE_REGION_FETCH_PRECONDITION_FAILURE = 107;
    public static final int ERROR_CODE_REGION_FETCH_SERVICE_FAILURE = 108;
    public static final int ERROR_CODE_COMPANY_INDIVIDUALS_FETCH_PRECONDITION_FAILURE = 109;
    public static final int ERROR_CODE_COMPANY_INDIVIDUALS_FETCH_SERVICE_FAILURE = 110;
    public static final int ERROR_CODE_COMPANY_BRANCHES_FETCH_PRECONDITION_FAILURE = 111;
    public static final int ERROR_CODE_COMPANY_BRANCHES_FETCH_SERVICE_FAILURE = 112;
    public static final int ERROR_CODE_BRANCH_INDIVIDUALS_FETCH_PRECONDITION_FAILURE = 113;
    public static final int ERROR_CODE_BRANCH_INDIVIDUALS_FETCH_SERVICE_FAILURE = 114;
    public static final int ERROR_CODE_REGION_BRANCHES_FETCH_PRECONDITION_FAILURE = 115;
    public static final int ERROR_CODE_REGION_BRANCHES_FETCH_SERVICE_FAILURE = 116;
    public static final int ERROR_CODE_REGION_INDIVIDUALS_FETCH_PRECONDITION_FAILURE = 117;
    public static final int ERROR_CODE_REGION_INDIVIDUALS_FETCH_SERVICE_FAILURE = 118;
    public static final int ERROR_CODE_INDIVIDUAL_PROFILE_SERVICE_FAILURE = 119;
    public static final int ERROR_CODE_INDIVIDUAL_PROFILE_SERVICE_PRECONDITION_FAILURE = 120;
    public static final int ERROR_CODE_COMPANY_REVIEWS_FETCH_PRECONDITION_FAILURE = 121;
    public static final int ERROR_CODE_COMPANY_REVIEWS_FETCH_FAILURE = 122;
    public static final int ERROR_CODE_AVERAGE_RATING_FETCH_PRECONDITION_FAILURE = 123;
    public static final int ERROR_CODE_AVERAGE_RATING_FETCH_FAILURE = 124;
    public static final int ERROR_CODE_REVIEWS_COUNT_FETCH_FAILURE = 125;
    public static final int ERROR_CODE_REVIEWS_COUNT_FETCH_PRECONDITION_FAILURE = 126;
    public static final int ERROR_CODE_REGION_REVIEWS_FETCH_PRECONDITION_FAILURE = 127;
    public static final int ERROR_CODE_REGION_REVIEWS_FETCH_FAILURE = 128;
    public static final int ERROR_CODE_BRANCH_REVIEWS_FETCH_PRECONDITION_FAILURE = 129;
    public static final int ERROR_CODE_BRANCH_REVIEWS_FETCH_FAILURE = 130;
    public static final int ERROR_CODE_PRO_LIST_FETCH_PRECONDITION_FAILURE = 131;
    public static final int ERROR_CODE_PRO_LIST_FETCH_FAILURE = 132;
    public static final int ERROR_CODE_INDIVIDUAL_POSTS_FETCH_PRECONDITION_FAILURE = 133;
    public static final int ERROR_CODE_INDIVIDUAL_POSTS_FETCH_FAILURE = 134;
    public static final int ERROR_CODE_COMPANY_POSTS_FETCH_PRECONDITION_FAILURE = 135;
    public static final int ERROR_CODE_COMPANY_POSTS_FETCH_FAILURE = 136;
    public static final int ERROR_CODE_REGION_POSTS_FETCH_PRECONDITION_FAILURE = 137;
    public static final int ERROR_CODE_REGION_POSTS_FETCH_FAILURE = 138;
    public static final int ERROR_CODE_BRANCH_POSTS_FETCH_PRECONDITION_FAILURE = 139;
    public static final int ERROR_CODE_BRANCH_POSTS_FETCH_FAILURE = 140;

    /**
     * Service codes
     */
    public static final int SERVICE_CODE_GENERAL = 100;
    public static final int SERVICE_CODE_COMPANY_PROFILE = 101;
    public static final int SERVICE_CODE_REGION_PROFILE = 102;
    public static final int SERVICE_CODE_BRANCH_PROFILE = 103;
    public static final int SERVICE_CODE_FETCH_ALL_REGIONS = 104;

    /*
     * Mongo constants
     */
    public static final String COMPANY_SETTINGS_COLLECTION = "COMPANY_SETTINGS";
    public static final int SERVICE_CODE_FETCH_COMPANY_INDIVIDUALS = 105;
    public static final int SERVICE_CODE_FETCH_COMPANY_BRANCHES = 106;
    public static final int SERVICE_CODE_FETCH_BRANCH_INDIVIDUALS = 107;
    public static final int SERVICE_CODE_FETCH_REGION_BRANCHES = 108;
    public static final int SERVICE_CODE_FETCH_REGION_INDIVIDUALS = 109;
    public static final int SERVICE_CODE_INDIVIDUAL_PROFILE = 110;
    public static final int SERVICE_CODE_COMPANY_REVIEWS = 111;
    public static final int SERVICE_CODE_COMPANY_AVERAGE_RATINGS = 112;
    public static final int SERVICE_CODE_COMPANY_REVIEWS_COUNT = 113;
    public static final int SERVICE_CODE_REGION_AVERAGE_RATINGS = 114;
    public static final int SERVICE_CODE_REGION_REVIEWS = 115;
    public static final int SERVICE_CODE_REGION_REVIEWS_COUNT = 115;
    public static final int SERVICE_CODE_BRANCH_AVERAGE_RATINGS = 116;
    public static final int SERVICE_CODE_BRANCH_REVIEWS = 117;
    public static final int SERVICE_CODE_BRANCH_REVIEWS_COUNT = 118;
    public static final int SERVICE_CODE_PRO_LIST_FETCH = 119;
    public static final int SERVICE_CODE_INDIVIDUAL_AVERAGE_RATINGS = 120;
    public static final int SERVICE_CODE_INDIVIDUAL_REVIEWS_COUNT = 121;
    public static final int SERVICE_CODE_INDIVIDUAL_REVIEWS = 122;
    public static final int SERVICE_CODE_INDIVIDUAL_POSTS = 123;
    public static final int SERVICE_CODE_COMPANY_POSTS = 124;
    public static final int SERVICE_CODE_BRANCH_POSTS = 125;
    public static final int SERVICE_CODE_REGION_POSTS = 126;

    /*
     * Mongo column and collection constants
     */
    public static final String SOCIAL_MEDIA_TOKEN_MONGO_KEY = "socialMediaTokens";
    public static final String REGION_SETTINGS_COLLECTION = "REGION_SETTINGS";
    public static final String BRANCH_SETTINGS_COLLECTION = "BRANCH_SETTINGS";
    public static final String AGENT_SETTINGS_COLLECTION = "AGENT_SETTINGS";
    public static final String SOCIAL_POST_COLLECTION = "SOCIAL_POST";

    /*
     * Constants related to Dash board profile.
     */
    public static final int MAX_SURVEY_SCORE = 5;
    public static final int MAX_SENT_SURVEY_COUNT = 10;
    public static final int MAX_SOCIAL_POSTS = 10;
    public static final String DATE_FORMAT = "MM/dd/yyyy";

    /*
     * Constats for Find a pro
     */
    public static final int FIND_PRO_START_INDEX = 0;
    public static final int FIND_PRO_BATCH_SIZE = 10;

    // Braintree subscription types
    public static final int SUBSCRIPTION_WENT_PAST_DUE = 1;
    public static final int SUBSCRIPTION_CHARGED_UNSUCCESSFULLY = 2;
    public static final int SUBSCRIPTION_CHARGED_SUCCESSFULLY = 3;

    // Subscription price modification result constants
    public static final String SUBSCRIPTION_PRICE_CHANGED = "SUBSCRIPTION_PRICE_CHANGED";
    public static final String SUBSCRIPTION_OLD_PRICE = "SUBSCRIPTION_OLD_PRICE";
    public static final String SUBSCRIPTION_REVISED_PRICE = "SUBSCRIPTION_REVISED_PRICE";
    public static final String SUBSCRIPTION_REVISED_NUMOFUSERS = "SUBSCRIPTION_REVISED_NUMOFUSERS";

    // Billing Modes
    public static final String BILLING_MODE_AUTO = "A";
    public static final String BILLING_MODE_INVOICE = "I";
    public static final String INVOICE_BILLED_DEFULAT_SUBSCRIPTION_ID = "invoicebilling";

    // API constants
    public static final String API_KEY_FROM_URL = "api_key";

    // Email constants
    public static final String ELEMENTS_DELIMITER = "$$";
    public static final String HEADER_MARKER = "HEADER^^";
    public static final String RECIPIENT_MARKER = "RECIPIENT^^";
    public static final String LINK_MARKER = "LINK^^";
    public static final String URL_MARKER = "URL^^";
    public static final String NAME_MARKER = "NAME^^";
    public static final String FIRSTNAME_MARKER = "FIRSTNAME^^";
    public static final String LASTNAME_MARKER = "LASTNAME^^";
    public static final String RETRYDAYS_MARKER = "RETRYDAYS^^";
    public static final String RETRIES_MARKER = "RETRIES^^";
    public static final String AGENTNAME_MARKER = "AGENTNAME^^";
    public static final String AGENTPHONE_MARKER = "AGENTPHONE^^";
    public static final String AGENTTITLE_MARKER = "AGENTTITLE^^";
    public static final String COMPANYNAME_MARKER = "COMPANYNAME^^";
    public static final String LOGINNAME_MARKER = "LOGINNAME^^";
    public static final String PROFILENAME_MARKER = "PROFILENAME^^";
    public static final String SURVEYDETAIL_MARKER = "SURVEYDETAIL^^";
    public static final String RECIPIENT_NAME_MARKER = "RECIPIENTNAME^^";
    public static final String CUSTOMER_NAME_MARKER = "CUSTOMERNAME^^";
    public static final String CUSTOMER_RATING_MARKER = "CUSTOMERRATING^^";
    public static final String AGENTEMAIL_MARKER = "AGENTEMAIL^^";

    //Constants for user agent profile page
    public static final int USER_AGENT_NUMBER_REVIEWS = 100;
    public static final int USER_AGENT_NUMBER_POST = 100;

    //Default vertcial crm mapping id
    public static final long DEFAULT_VERTICAL_CRM_ID = -1;

    public static final int EXPIRE_AFTER_DAYS = 3;
    
    //Constants for survey request send type
    public static final String SURVEY_REQUEST_AGENT = "agent";
    public static final String SURVEY_REQUEST_ADMIN = "admin";
}
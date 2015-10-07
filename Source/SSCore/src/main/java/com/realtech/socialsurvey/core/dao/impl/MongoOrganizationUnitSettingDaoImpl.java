package com.realtech.socialsurvey.core.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.mongodb.BasicDBObject;
import com.realtech.socialsurvey.core.commons.CommonConstants;
import com.realtech.socialsurvey.core.dao.OrganizationUnitSettingsDao;
import com.realtech.socialsurvey.core.entities.AgentRankingReport;
import com.realtech.socialsurvey.core.entities.AgentSettings;
import com.realtech.socialsurvey.core.entities.FeedIngestionEntity;
import com.realtech.socialsurvey.core.entities.OrganizationUnitSettings;
import com.realtech.socialsurvey.core.entities.ProfileUrlEntity;
import com.realtech.socialsurvey.core.exception.InvalidInputException;
import com.realtech.socialsurvey.core.exception.NoRecordsFetchedException;

/**
 * Mongo implementation of settings
 */
@Repository
public class MongoOrganizationUnitSettingDaoImpl implements OrganizationUnitSettingsDao, InitializingBean {

	public static final String COMPANY_SETTINGS_COLLECTION = "COMPANY_SETTINGS";
	public static final String REGION_SETTINGS_COLLECTION = "REGION_SETTINGS";
	public static final String BRANCH_SETTINGS_COLLECTION = "BRANCH_SETTINGS";
	public static final String AGENT_SETTINGS_COLLECTION = "AGENT_SETTINGS";
	public static final String KEY_CRM_INFO = "crm_info";
	public static final String KEY_CRM_INFO_SOURCE = "crm_info.crm_source";
	public static final String KEY_IDEN = "iden";
	public static final String KEY_CRM_INFO_CLASS = "crm_info._class";
	public static final String KEY_MAIL_CONTENT = "mail_content";
	public static final String KEY_SURVEY_SETTINGS = "survey_settings";
	public static final String KEY_LOCATION_ENABLED = "isLocationEnabled";
	public static final String KEY_ACCOUNT_DISABLED = "isAccountDisabled";
	public static final String KEY_DEFAULT_BY_SYSTEM = "isDefaultBySystem";
	public static final String KEY_SEO_CONTENT_MODIFIED = "isSeoContentModified";
	public static final String KEY_CONTACT_DETAIL_SETTINGS = "contact_details";
	public static final String KEY_LOCK_SETTINGS = "lockSettings";
	public static final String KEY_LINKEDIN_PROFILEDATA = "linkedInProfileData";
	public static final String KEY_PROFILE_NAME = "profileName";
	public static final String KEY_UNIQUE_IDENTIFIER = "uniqueIdentifier";
	public static final String KEY_PROFILE_URL = "profileUrl";
	public static final String KEY_LOGO = "logo";
	public static final String KEY_PROFILE_IMAGE = "profileImageUrl";
	public static final String KEY_CONTACT_DETAILS = "contact_details";
	public static final String KEY_ASSOCIATION = "associations";
	public static final String KEY_EXPERTISE = "expertise";
	public static final String KEY_HOBBIES = "hobbies";
	public static final String KEY_ACHIEVEMENTS = "achievements";
	public static final String KEY_LICENCES = "licenses";
	public static final String KEY_SOCIAL_MEDIA_TOKENS = "socialMediaTokens";
	public static final String KEY_FACEBOOK_TOKEN_TO_POST = "facebookAccessTokenToPost";
	public static final String KEY_COMPANY_POSITIONS = "positions";
	public static final String KEY_IDENTIFIER = "iden";
	public static final String KEY_VERTICAL = "vertical";
	public static final String KEY_PROFILE_STAGES = "profileStages";
	public static final String KEY_MODIFIED_ON = "modifiedOn";
	public static final String KEY_DISCLAIMER = "disclaimer";
	public static final String KEY_FACEBOOK_SOCIAL_MEDIA_TOKEN = "socialMediaTokens.facebookToken";
	public static final String KEY_TWITTER_SOCIAL_MEDIA_TOKEN = "socialMediaTokens.twitterToken";
	public static final String KEY_GOOGLE_SOCIAL_MEDIA_TOKEN = "socialMediaTokens.googleToken";
	public static final String KEY_LINKEDIN_SOCIAL_MEDIA_TOKEN = "socialMediaTokens.linkedInToken";
	public static final String KEY_ZILLOW_SOCIAL_MEDIA_TOKEN = "socialMediaTokens.zillowToken";
	public static final String KEY_YELP_SOCIAL_MEDIA_TOKEN = "socialMediaTokens.yelpToken";
	public static final String KEY_LENDINGTREE_SOCIAL_MEDIA_TOKEN = "socialMediaTokens.lendingTreeToken";
	public static final String KEY_REALTOR_SOCIAL_MEDIA_TOKEN = "socialMediaTokens.realtorToken";
	public static final String KEY_CONTACT_NAME = "contact_details.name";
	public static final String KEY_POSTIONS = "positions";
	
	private static final Logger LOG = LoggerFactory.getLogger(MongoOrganizationUnitSettingDaoImpl.class);
	

	@Autowired
	private MongoTemplate mongoTemplate;

	@Value("${APPLICATION_BASE_URL}")
	private String applicationBaseUrl;

	@Override
	public void insertOrganizationUnitSettings(OrganizationUnitSettings organizationUnitSettings, String collectionName) {
		LOG.info("Creating " + collectionName + " document. Organiztion Unit id: " + organizationUnitSettings.getIden());
		LOG.debug("Inserting into " + collectionName + ". Object: " + organizationUnitSettings.toString());
		mongoTemplate.insert(organizationUnitSettings, collectionName);
		LOG.info("Inserted into " + collectionName);
	}

	@Override
	public void insertAgentSettings(AgentSettings agentSettings) {
		LOG.info("Inserting agent settings: " + agentSettings.toString());
		mongoTemplate.insert(agentSettings, AGENT_SETTINGS_COLLECTION);
		LOG.info("Inserted into agent settings");
	}

	@Override
	public OrganizationUnitSettings fetchOrganizationUnitSettingsById(long identifier, String collectionName) {
		LOG.info("Fetch organization unit settings from " + collectionName + " for id: " + identifier);
		Query query = new Query();
		query.addCriteria(Criteria.where(KEY_IDENTIFIER).is(identifier));
		query.fields().exclude(KEY_LINKEDIN_PROFILEDATA);
		OrganizationUnitSettings settings = mongoTemplate.findOne(query, OrganizationUnitSettings.class, collectionName);
		setCompleteUrlForSettings(settings, collectionName);
		return settings;
	}
	
	@Override
	public List<OrganizationUnitSettings> fetchOrganizationUnitSettingsForMultipleIds(Set<Long> identifiers, String collectionName) {
		LOG.info("Fetch organization unit settings from " + collectionName + " for multiple ids.");
		Query query = new Query();
		query.addCriteria(Criteria.where(KEY_IDENTIFIER).in(identifiers));
		query.fields().exclude(KEY_LINKEDIN_PROFILEDATA);
		List<OrganizationUnitSettings> settings = mongoTemplate.find(query, OrganizationUnitSettings.class, collectionName);
		return settings;
	}

	@Override
	public AgentSettings fetchAgentSettingsById(long identifier) {
		LOG.info("Fetch agent settings from for id: " + identifier);
		AgentSettings settings = mongoTemplate.findOne(new BasicQuery(new BasicDBObject(KEY_IDENTIFIER, identifier)), AgentSettings.class,
				AGENT_SETTINGS_COLLECTION);
		setCompleteUrlForSettings(settings, CommonConstants.AGENT_SETTINGS_COLLECTION);
		return settings;
	}
	
	@Override
	public List<AgentSettings> fetchMultipleAgentSettingsById(List<Long> identifiers) {
		LOG.info("Fetch multiple agent settings from list of Ids: " + identifiers);
		Query query = new Query();
		query.addCriteria(Criteria.where(KEY_IDENTIFIER).in(identifiers));
		query.fields().exclude(KEY_LINKEDIN_PROFILEDATA);
		List<AgentSettings> settingsList = mongoTemplate.find(query, AgentSettings.class,AGENT_SETTINGS_COLLECTION);
		for (AgentSettings settings : settingsList) {
			setCompleteUrlForSettings(settings, CommonConstants.AGENT_SETTINGS_COLLECTION);
		}
		return settingsList;
	}

	@Override
	public void updateParticularKeyOrganizationUnitSettings(String keyToUpdate, Object updatedRecord, OrganizationUnitSettings unitSettings,
			String collectionName) {
		LOG.info("Updating unit setting in " + collectionName + " with " + unitSettings + " for key: " + keyToUpdate + " wtih value: "
				+ updatedRecord);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(unitSettings.getId()));
		Update update = new Update().set(keyToUpdate, updatedRecord);
		LOG.debug("Updating the unit settings");
		mongoTemplate.updateFirst(query, update, OrganizationUnitSettings.class, collectionName);
		LOG.info("Updated the unit setting");
	}

	@Override
	public void updateParticularKeyAgentSettings(String keyToUpdate, Object updatedRecord, AgentSettings agentSettings) {
		LOG.info("Updating unit setting in AGENT_SETTINGS with " + agentSettings + " for key: " + keyToUpdate + " wtih value: " + updatedRecord);
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(agentSettings.getId()));
		Update update = new Update().set(keyToUpdate, updatedRecord);
		LOG.debug("Updating the unit settings");
		mongoTemplate.updateFirst(query, update, OrganizationUnitSettings.class, AGENT_SETTINGS_COLLECTION);
		LOG.info("Updated the unit setting");
	}

	/**
	 * Fetchs the list of names of logos being used.
	 * 
	 * @return
	 */
	@Override
	public List<String> fetchLogoList() {
		LOG.info("Fetching the list of logos being used");
		List<OrganizationUnitSettings> settingsList = mongoTemplate.findAll(OrganizationUnitSettings.class, COMPANY_SETTINGS_COLLECTION);
		List<String> logoList = new ArrayList<>();

		LOG.info("Preparing the list of logo names");
		for (OrganizationUnitSettings settings : settingsList) {
			String logoName = settings.getLogo();
			if (logoName != null && !logoName.isEmpty()) {
				logoList.add(logoName);
			}
		}
		LOG.info("Returning the list prepared!");
		return logoList;
	}

	/**
	 * Updates a particular key of organization unit settings based on criteria specified
	 */
	@Override
	public void updateKeyOrganizationUnitSettingsByCriteria(String keyToUpdate, Object updatedRecord, String criteriaKey, Object criteriaValue,
			String collectionName) {
		LOG.info("Method updateKeyOrganizationUnitSettingsByCriteria called in collection name :" + collectionName + " for keyToUpdate :"
				+ keyToUpdate + " criteria key :" + criteriaKey);
		Query query = new Query();
		query.addCriteria(Criteria.where(criteriaKey).is(criteriaValue));
		Update update = new Update().set(keyToUpdate, updatedRecord);
		LOG.debug("Updating unit settings based on criteria");
		mongoTemplate.updateMulti(query, update, OrganizationUnitSettings.class, collectionName);
		LOG.info("Successfully completed updation of unit settings");
	}

	/**
	 * Method to fetch organization settings based on profile name
	 */
	@Override
	public OrganizationUnitSettings fetchOrganizationUnitSettingsByProfileName(String profileName, String collectionName) {
		LOG.info("Method fetchOrganizationUnitSettingsByProfileName called for profileName:" + profileName + " and collectionName:" + collectionName);

		Query query = new Query();
		query.addCriteria(Criteria.where(KEY_PROFILE_NAME).is(profileName));
		query.fields().exclude(KEY_LINKEDIN_PROFILEDATA);
		OrganizationUnitSettings organizationUnitSettings = mongoTemplate.findOne(query, OrganizationUnitSettings.class, collectionName);
		setCompleteUrlForSettings(organizationUnitSettings, collectionName);
		LOG.info("Successfully executed method fetchOrganizationUnitSettingsByProfileName");
		return organizationUnitSettings;
	}

	/**
	 * Method to fetch organization settings based on profile url
	 */
	@Override
	public OrganizationUnitSettings fetchOrganizationUnitSettingsByProfileUrl(String profileUrl, String collectionName) {
		LOG.info("Method fetchOrganizationUnitSettingsByProfileUrl called for profileUrl:" + profileUrl + " and collectionName:" + collectionName);

		OrganizationUnitSettings organizationUnitSettings = mongoTemplate.findOne(new BasicQuery(new BasicDBObject(KEY_PROFILE_URL, profileUrl)),
				OrganizationUnitSettings.class, collectionName);
		setCompleteUrlForSettings(organizationUnitSettings, collectionName);
		LOG.info("Successfully executed method fetchOrganizationUnitSettingsByProfileUrl");
		return organizationUnitSettings;
	}

	// creates index on field 'iden'
	private void createIndexOnIden(String collectionName) {
		LOG.debug("Creating unique index on 'iden' for " + collectionName);
		mongoTemplate.indexOps(collectionName).ensureIndex(new Index().on(KEY_IDENTIFIER, Sort.Direction.ASC).unique());
		LOG.debug("Index created");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOG.info("Checking if collections are created in mongodb");
		if (!mongoTemplate.collectionExists(COMPANY_SETTINGS_COLLECTION)) {
			LOG.info("Creating " + COMPANY_SETTINGS_COLLECTION);
			mongoTemplate.createCollection(COMPANY_SETTINGS_COLLECTION);
			createIndexOnIden(COMPANY_SETTINGS_COLLECTION);
		}
		if (!mongoTemplate.collectionExists(REGION_SETTINGS_COLLECTION)) {
			LOG.info("Creating " + REGION_SETTINGS_COLLECTION);
			mongoTemplate.createCollection(REGION_SETTINGS_COLLECTION);
			createIndexOnIden(REGION_SETTINGS_COLLECTION);
		}
		if (!mongoTemplate.collectionExists(BRANCH_SETTINGS_COLLECTION)) {
			LOG.info("Creating " + BRANCH_SETTINGS_COLLECTION);
			mongoTemplate.createCollection(BRANCH_SETTINGS_COLLECTION);
			createIndexOnIden(BRANCH_SETTINGS_COLLECTION);
		}
		if (!mongoTemplate.collectionExists(AGENT_SETTINGS_COLLECTION)) {
			LOG.info("Creating " + AGENT_SETTINGS_COLLECTION);
			mongoTemplate.createCollection(AGENT_SETTINGS_COLLECTION);
			createIndexOnIden(AGENT_SETTINGS_COLLECTION);
		}
	}

	@Override
	public List<ProfileUrlEntity> fetchSEOOptimizedOrganizationUnitSettings(String collectionName, int skipCount, int numOfRecords) {
		LOG.info("Getting SEO related data for " + collectionName);
		List<ProfileUrlEntity> profileUrls = null;
		// only get profile name
		// Query query = new BasicQuery(new BasicDBObject(KEY_DEFAULT_BY_SYSTEM, false));
		Query query = new Query();
		query.addCriteria(Criteria.where(KEY_DEFAULT_BY_SYSTEM).is(false));
		query.fields().include(KEY_PROFILE_URL).include(KEY_MODIFIED_ON).exclude("_id");
		if (skipCount > 0) {
			query.skip(skipCount);
		}
		if (numOfRecords > 0) {
			query.limit(numOfRecords);
		}
		profileUrls = mongoTemplate.find(query, ProfileUrlEntity.class, collectionName);
		return profileUrls;
	}

	@Override
	public long fetchSEOOptimizedOrganizationUnitCount(String collectionName) {
		LOG.info("Getting SEO Optimized count for collection " + collectionName);
		Query query = new BasicQuery(new BasicDBObject(KEY_DEFAULT_BY_SYSTEM, false));
		long count = mongoTemplate.count(query, collectionName);
		LOG.info("Returning count " + count);
		return count;
	}

	@Override
	public void updateCompletedSurveyCountForAgent(long agentId, int incrementCount) {
		LOG.info("Method to update completed survey count for agent started.");
		Query query = new Query(Criteria.where("iden").is(agentId));
		Update update = new Update();
		update.inc(CommonConstants.REVIEW_COUNT_MONGO, incrementCount);
		mongoTemplate.updateFirst(query, update, AgentSettings.class, CommonConstants.AGENT_SETTINGS_COLLECTION);
		LOG.info("Method to update completed survey count for agent finished.");
	}

	@Override
	public List<FeedIngestionEntity> fetchSocialMediaTokens(String collectionName, int skipCount, int numOfRecords) {
		LOG.info("Fetching social media tokens from " + collectionName);
		List<FeedIngestionEntity> tokens = null;
		Query query = new Query();
		query.addCriteria(Criteria.where(KEY_SOCIAL_MEDIA_TOKENS).exists(true));
		query.fields().include(KEY_SOCIAL_MEDIA_TOKENS).include(KEY_IDENTIFIER).exclude("_id");
		if (skipCount > 0) {
			query.skip(skipCount);
		}
		if (numOfRecords > 0) {
			query.limit(numOfRecords);
		}
		tokens = mongoTemplate.find(query, FeedIngestionEntity.class, collectionName);
		LOG.info("Fetched " + (tokens != null ? tokens.size() : "none") + " items with social media tokens from " + collectionName);
		return tokens;
	}
	
	/*
	 * Method to delete Organization unit settings for list of idens.
	 */
	public void removeOganizationUnitSettings(List<Long> agentIds, String collectionName){
		LOG.info("Method removeOganizationUnitSettings() started.");
		Query query = new Query();
		query.addCriteria(Criteria.where("iden").in(agentIds));
		mongoTemplate.remove(query, collectionName);
		LOG.info("Method removeOganizationUnitSettings() finished.");
	}
	
	@Override
	public Map<Long, OrganizationUnitSettings> getSettingsMapWithLinkedinImageUrl(
			String collectionName, String matchUrl) {
		Query query = new Query();
		query.addCriteria(Criteria.where(CommonConstants.PROFILE_IMAGE_URL_SOLR).regex( matchUrl ));
		List<OrganizationUnitSettings> settings = mongoTemplate.find(query, OrganizationUnitSettings.class, collectionName);
		Map<Long, OrganizationUnitSettings> settingsMap = new HashMap<>();
		for(OrganizationUnitSettings setting : settings){
			settingsMap.put(setting.getIden(), setting);
		}
		return settingsMap;
	}
	

	@Override
	public void setAgentDetails(Map<Long, AgentRankingReport> agentsReport) {
		LOG.info("Method setAgentNames() started.");
		Set<Long> agentIds = agentsReport.keySet();
		List<AgentSettings> agentSettings = fetchMultipleAgentSettingsById(new ArrayList<Long>(agentIds));
		for (AgentSettings setting : agentSettings) {
			if (agentsReport.get(setting.getIden()) != null) {
				try {
					agentsReport.get(setting.getIden()).setAgentFirstName(setting.getContact_details().getFirstName());
					agentsReport.get(setting.getIden()).setAgentLastName(setting.getContact_details().getLastName());
					agentsReport.get(setting.getIden()).setRegistrationDate(setting.getCreatedOn());
				}
				catch (NullPointerException e) {
					LOG.error("Null Pointer exception caught in setAgentNames(). Nested exception is ", e);
					LOG.debug("Continuing...");
					continue;
				}
			}
		}
		LOG.info("Method setAgentNames() finished.");
	}
    
    @Override
    public OrganizationUnitSettings removeKeyInOrganizationSettings(OrganizationUnitSettings unitSettings, String keyToUpdate, String collectionName){
		LOG.info("Method removeKeyInOrganizationSettings() started.");
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(unitSettings.getId()));
		Update update = new Update().unset(keyToUpdate);
		LOG.debug("Updating the unit settings");
		mongoTemplate.updateFirst(query, update, OrganizationUnitSettings.class, collectionName);
		unitSettings = mongoTemplate.findOne(query, OrganizationUnitSettings.class, collectionName);
		setCompleteUrlForSettings(unitSettings, collectionName);
		LOG.info("Method removeKeyInOrganizationSettings() finished.");
		return unitSettings;
	}

    @Override
    public List<OrganizationUnitSettings> getCompanyListByVerticalName (String verticalName) {
    	LOG.debug( "Method getCompanyListByVerticalName() called for vertical name : " + verticalName);
    	
    	List<OrganizationUnitSettings> unitSettings = null;
    	Query query = new Query();
    	query.addCriteria(Criteria.where(KEY_VERTICAL).is(verticalName));
    	query.fields().include(KEY_LOGO).include(KEY_CONTACT_DETAILS).include(KEY_PROFILE_NAME).include(KEY_VERTICAL).exclude("_id");
    	
    	unitSettings = mongoTemplate.find(query, OrganizationUnitSettings.class, COMPANY_SETTINGS_COLLECTION);
    	
    	return unitSettings;
    }

    @Override
    public List<OrganizationUnitSettings> getCompanyList() {
    	LOG.debug( "Method getCompanyList() called");
    	
    	List<OrganizationUnitSettings> unitSettings = null;
    	Query query = new Query();
    	query.fields().include(KEY_CONTACT_DETAILS).include(KEY_PROFILE_NAME).include(KEY_VERTICAL).include(KEY_IDEN).exclude("_id");
    	query.with(new Sort(Sort.Direction.DESC, KEY_MODIFIED_ON));
    	
    	unitSettings = mongoTemplate.find(query, OrganizationUnitSettings.class, COMPANY_SETTINGS_COLLECTION);
    	
    	return unitSettings;
    }
    
    /**
     * Method to fetch the company setting list by set of company ids passed
     */
    @Override
    public List<OrganizationUnitSettings> getCompanyListByIds(Set<Long> companyIds) {
    	LOG.debug( "Method getCompanyList() called");
    	
    	List<OrganizationUnitSettings> unitSettings = null;
    	Query query = new Query();
    	query.addCriteria(Criteria.where(KEY_IDENTIFIER).in(companyIds));
    	query.fields().include(KEY_CONTACT_DETAILS).include(KEY_PROFILE_NAME).include(KEY_VERTICAL).include(KEY_IDEN).include(KEY_PROFILE_IMAGE).exclude("_id");
    	query.with(new Sort(Sort.Direction.DESC, KEY_MODIFIED_ON));
    	
    	unitSettings = mongoTemplate.find(query, OrganizationUnitSettings.class, COMPANY_SETTINGS_COLLECTION);
    	
    	return unitSettings;
    }
    
    
    @Override
    public List<OrganizationUnitSettings> getCompanyListByKey(String searchKey) {
		LOG.debug("Method getCompanyListByKey() called for key : " + searchKey);
    	
    	List<OrganizationUnitSettings> unitSettings = null;
    	Query query = new Query();
    	Pattern pattern = Pattern.compile(searchKey+".*");
    	query.addCriteria(Criteria.where(KEY_CONTACT_NAME).regex(pattern));
    	query.fields().include(KEY_CONTACT_DETAILS).include(KEY_VERTICAL).include(KEY_IDEN).exclude("_id");
    	query.with(new Sort(Sort.Direction.DESC, KEY_MODIFIED_ON));
    	
    	unitSettings = mongoTemplate.find(query, OrganizationUnitSettings.class, COMPANY_SETTINGS_COLLECTION);
    	
    	return unitSettings;
    }
    
	/*
	 * Method to set complete profile URL for each of the setting being fetched.
	 */
	private void setCompleteUrlForSettings(OrganizationUnitSettings settings, String collectionName) {
		if (settings != null && collectionName != null && !collectionName.isEmpty()){
			switch (collectionName) {
				case CommonConstants.BRANCH_SETTINGS_COLLECTION:
					settings.setCompleteProfileUrl(applicationBaseUrl + CommonConstants.BRANCH_PROFILE_FIXED_URL + settings.getProfileUrl());
					break;
				case CommonConstants.REGION_SETTINGS_COLLECTION:
					settings.setCompleteProfileUrl(applicationBaseUrl + CommonConstants.REGION_PROFILE_FIXED_URL + settings.getProfileUrl());
					break;
				case CommonConstants.COMPANY_SETTINGS_COLLECTION:
					settings.setCompleteProfileUrl(applicationBaseUrl + CommonConstants.COMPANY_PROFILE_FIXED_URL + settings.getProfileUrl());
					break;
				case CommonConstants.AGENT_SETTINGS_COLLECTION:
					settings.setCompleteProfileUrl(applicationBaseUrl + CommonConstants.AGENT_PROFILE_FIXED_URL + settings.getProfileUrl());
					break;
			}
		}
	}

    @Override
    public OrganizationUnitSettings fetchOrganizationUnitSettingsByUniqueIdentifier( String uniqueIdentifier,
        String collectionName )
    {
        Query query = new Query();
        query.addCriteria(Criteria.where(KEY_UNIQUE_IDENTIFIER).is(uniqueIdentifier));
        query.fields().exclude(KEY_LINKEDIN_PROFILEDATA);
        OrganizationUnitSettings organizationUnitSettings = mongoTemplate.findOne(query, OrganizationUnitSettings.class, collectionName);
        setCompleteUrlForSettings(organizationUnitSettings, collectionName);
        LOG.info("Successfully executed method fetchOrganizationUnitSettingsByProfileName");
        return organizationUnitSettings;
    }
    
    @Override
    public List<OrganizationUnitSettings> getOrganizationUnitListWithCRMSource(String source, String collectionName) throws InvalidInputException, NoRecordsFetchedException {
    	LOG.info("Getting Organization Unit Settings List from "+collectionName+" for crm source "+source);
    	if(collectionName == null || collectionName.isEmpty()){
    		LOG.info("Collection name is not present to fetch crm info list.");
    		throw new InvalidInputException("Collection name is not present to fetch crm info list.");
    	}
    	List<OrganizationUnitSettings> organizationUnitsSettingsList = null;
    	Query query = new Query();
    	if(source != null && !source.isEmpty()){
    		query.addCriteria(Criteria.where(KEY_CRM_INFO).exists(true).andOperator(Criteria.where(KEY_CRM_INFO_SOURCE).is(source)));
    	}
    	organizationUnitsSettingsList = mongoTemplate.find(query,OrganizationUnitSettings.class, collectionName);
    	if(organizationUnitsSettingsList == null || organizationUnitsSettingsList.isEmpty()){
    		LOG.info("No records found for crm source: "+source);
    		throw new NoRecordsFetchedException("No records found for crm source: "+source);
    	}
    	LOG.info("Successfully found unit settings for source "+source);
    	return organizationUnitsSettingsList;
    }
}
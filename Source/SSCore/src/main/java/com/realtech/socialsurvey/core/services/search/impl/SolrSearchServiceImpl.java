package com.realtech.socialsurvey.core.services.search.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.noggit.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.realtech.socialsurvey.core.commons.CommonConstants;
import com.realtech.socialsurvey.core.entities.Branch;
import com.realtech.socialsurvey.core.entities.Company;
import com.realtech.socialsurvey.core.entities.Region;
import com.realtech.socialsurvey.core.entities.User;
import com.realtech.socialsurvey.core.entities.UserProfile;
import com.realtech.socialsurvey.core.exception.InvalidInputException;
import com.realtech.socialsurvey.core.services.search.SolrSearchService;
import com.realtech.socialsurvey.core.services.search.exception.SolrException;
import com.realtech.socialsurvey.core.utils.SolrSearchUtils;

// JIRA:SS-62 BY RM 02
/**
 * Implementation class for solr search services
 */
@Component
public class SolrSearchServiceImpl implements SolrSearchService {

	private static final Logger LOG = LoggerFactory.getLogger(SolrSearchServiceImpl.class);

	@Value("${SOLR_REGION_URL}")
	private String solrRegionUrl;

	@Value("${SOLR_BRANCH_URL}")
	private String solrBranchUrl;

	@Value("${SOLR_USER_URL}")
	private String solrUserUrl;

	@Autowired
	private SolrSearchUtils solrSearchUtils;

	/**
	 * Method to perform search of regions from solr based on the input pattern and company
	 * 
	 * @param regionPattern
	 * @param company
	 * @param start
	 * @param rows
	 * @return
	 * @throws InvalidInputException
	 * @throws SolrException
	 */
	@Override
	public String searchRegions(String regionPattern, Company company, int start, int rows) throws InvalidInputException, SolrException {
		LOG.info("Method searchRegions called for regionPattern :" + regionPattern);
		if (regionPattern == null) {
			throw new InvalidInputException("Region pattern is null while searching for region");
		}
		if (company == null) {
			throw new InvalidInputException("company is null or empty while searching for region");
		}
		LOG.info("Method searchRegions called for regionPattern : " + regionPattern + " and company : " + company);
		String regionResult = null;
		QueryResponse response = null;
		try {
			regionPattern = regionPattern + "*";

			SolrServer solrServer = new HttpSolrServer(solrRegionUrl);
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery(CommonConstants.REGION_NAME_SOLR + ":" + regionPattern);
			solrQuery.addFilterQuery(CommonConstants.COMPANY_ID_SOLR + ":" + company.getCompanyId(), CommonConstants.STATUS_COLUMN + ":"
					+ CommonConstants.STATUS_ACTIVE);
			solrQuery.setStart(start);
			if (rows > 0) {
				solrQuery.setRows(rows);
			}

			LOG.debug("Querying solr for searching regions");
			response = solrServer.query(solrQuery);
			SolrDocumentList results = response.getResults();
			regionResult = JSONUtil.toJSON(results);
			LOG.debug("Region search result is : " + regionResult);

		}
		catch (SolrServerException e) {
			LOG.error("UnsupportedEncodingException while performing region search");
			throw new SolrException("Exception while performing search. Reason : " + e.getMessage(), e);
		}

		LOG.info("Method searchRegions finished for regionPattern :" + regionPattern + " returning : " + regionResult);
		return regionResult;
	}

	/**
	 * Method to perform search of branches from solr based on the input pattern and company
	 * 
	 * @param branchPattern
	 * @param company
	 * @param start
	 * @param rows
	 * @return
	 * @throws InvalidInputException
	 * @throws SolrException
	 */
	@Override
	public String searchBranches(String branchPattern, Company company, int start, int rows) throws InvalidInputException, SolrException {
		LOG.info("Method searchBranches called for branchPattern :" + branchPattern);
		if (branchPattern == null) {
			throw new InvalidInputException("Branch pattern is null while searching for branch");
		}
		if (company == null) {
			throw new InvalidInputException("Company is null while searching for branch");
		}
		LOG.info("Method searchBranches called for branchPattern : " + branchPattern + " and company : " + company);
		String branchResult = null;
		QueryResponse response = null;
		try {

			branchPattern = branchPattern + "*";

			SolrServer solrServer = new HttpSolrServer(solrBranchUrl);
			SolrQuery query = new SolrQuery();
			query.setQuery(CommonConstants.BRANCH_NAME_SOLR + ":" + branchPattern);
			query.addFilterQuery(CommonConstants.COMPANY_ID_SOLR + ":" + company.getCompanyId(), CommonConstants.STATUS_SOLR + ":"
					+ CommonConstants.STATUS_ACTIVE);
			query.setStart(start);
			
			if (rows > 0) {
				query.setRows(rows);
			}

			LOG.debug("Querying solr for searching branches");
			response = solrServer.query(query);
			SolrDocumentList documentList = response.getResults();
			branchResult = JSONUtil.toJSON(documentList);

			LOG.debug("Results obtained from solr :" + branchResult);
		}
		catch (SolrServerException e) {
			LOG.error("SolrServerException while performing branch search");
			throw new SolrException("Exception while performing search. Reason : " + e.getMessage(), e);
		}
		LOG.info("Method searchBranches finished for branchPattern :" + branchPattern);
		return branchResult;
	}

	/**
	 * Method to add region into solr
	 */
	@Override
	public void addOrUpdateRegionToSolr(Region region) throws SolrException {
		LOG.info("Method to add or update region to solr called for region : " + region);
		SolrServer solrServer;
		try {

			solrServer = new HttpSolrServer(solrRegionUrl);
			SolrInputDocument document = getSolrDocumentFromRegion(region);
			UpdateResponse response = solrServer.add(document);
			LOG.debug("response is while adding/updating region is : " + response);
			solrServer.commit();
		}
		catch (MalformedURLException e) {
			LOG.error("Exception while adding/updating regions to solr. Reason : " + e.getMessage(), e);
			throw new SolrException("Exception while adding/updating regions to solr. Reason : " + e.getMessage(), e);
		}
		catch (SolrServerException | IOException e) {
			LOG.error("Exception while adding/updating regions to solr. Reason : " + e.getMessage(), e);
			throw new SolrException("Exception while adding/updating regions to solr. Reason : " + e.getMessage(), e);
		}
		LOG.info("Method to add or update region to solr finshed for region : " + region);

	}

	/**
	 * Method to add branch into solr
	 */
	@Override
	public void addOrUpdateBranchToSolr(Branch branch) throws SolrException {
		LOG.info("Method to add/update branch to solr called for branch : " + branch);
		SolrServer solrServer;
		try {
			solrServer = new HttpSolrServer(solrBranchUrl);
			SolrInputDocument document = getSolrDocumentFromBranch(branch);

			UpdateResponse response = solrServer.add(document);
			LOG.debug("response while adding/updating branch is : " + response);
			solrServer.commit();
		}
		catch (MalformedURLException e) {
			LOG.error("Exception while adding/updating branch to solr. Reason : " + e.getMessage(), e);
			throw new SolrException("Exception while adding/updating branch to solr. Reason : " + e.getMessage(), e);
		}
		catch (SolrServerException | IOException e) {
			LOG.error("Exception while adding/updating branch to solr. Reason : " + e.getMessage(), e);
			throw new SolrException("Exception while adding/updating branch to solr. Reason : " + e.getMessage(), e);
		}
		LOG.info("Method to add/update branch to solr finshed for branch : " + branch);
	}

	/**
	 * Method to get solr input document from branch
	 * 
	 * @param branch
	 * @return
	 */
	private SolrInputDocument getSolrDocumentFromBranch(Branch branch) {
		LOG.debug("Method getSolrDocumentFromBranch called for branch " + branch);

		SolrInputDocument document = new SolrInputDocument();
		document.addField(CommonConstants.REGION_ID_SOLR, branch.getRegion().getRegionId());
		document.addField(CommonConstants.REGION_NAME_SOLR, branch.getRegion().getRegion());
		document.addField(CommonConstants.BRANCH_ID_SOLR, branch.getBranchId());
		document.addField(CommonConstants.BRANCH_NAME_SOLR, branch.getBranch());
		document.addField(CommonConstants.COMPANY_ID_SOLR, branch.getCompany().getCompanyId());
		document.addField(CommonConstants.IS_DEFAULT_BY_SYSTEM_SOLR, branch.getIsDefaultBySystem());
		document.addField(CommonConstants.STATUS_SOLR, branch.getStatus());

		LOG.debug("Method getSolrDocumentFromBranch finished for branch " + branch);
		return document;
	}

	/**
	 * Method to get solr document from a region
	 * 
	 * @param region
	 * @return
	 */
	private SolrInputDocument getSolrDocumentFromRegion(Region region) {
		LOG.debug("Method getSolrDocumentFromRegion called for region " + region);

		SolrInputDocument document = new SolrInputDocument();
		document.addField(CommonConstants.REGION_ID_SOLR, region.getRegionId());
		document.addField(CommonConstants.REGION_NAME_SOLR, region.getRegion());
		document.addField(CommonConstants.COMPANY_ID_SOLR, region.getCompany().getCompanyId());
		document.addField(CommonConstants.IS_DEFAULT_BY_SYSTEM_SOLR, region.getIsDefaultBySystem());
		document.addField(CommonConstants.STATUS_SOLR, region.getStatus());

		LOG.debug("Method getSolrDocumentFromRegion finished for region " + region);
		return document;
	}

	/**
	 * Method to perform search of Users from solr based on the input pattern for user and company.
	 * 
	 * @throws InvalidInputException
	 * @throws SolrException
	 * @throws MalformedURLException
	 */
	@Override
	public String searchUsersByLoginNameAndCompany(String loginNamePattern, Company company) throws InvalidInputException, SolrException,
			MalformedURLException {
		LOG.info("Method searchUsers called for userNamePattern :" + loginNamePattern);
		if (loginNamePattern == null || loginNamePattern.isEmpty()) {
			throw new InvalidInputException("Username pattern is null or empty while searching for Users");
		}
		if (company == null) {
			throw new InvalidInputException("company is null or empty while searching for users");
		}
		LOG.info("Method searchUsers() called for userNamePattern : " + loginNamePattern + " and company : " + company);
		String usersResult = null;
		QueryResponse response = null;
		try {
			loginNamePattern = loginNamePattern + "*";

			SolrServer solrServer = new HttpSolrServer(solrUserUrl);
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery(CommonConstants.USER_LOGIN_NAME_SOLR+":" + loginNamePattern);
			solrQuery.addFilterQuery(CommonConstants.COMPANY_ID_SOLR+":" + company.getCompanyId(), CommonConstants.STATUS_SOLR+":" + CommonConstants.STATUS_ACTIVE);

			LOG.debug("Querying solr for searching users");
			response = solrServer.query(solrQuery);
			SolrDocumentList results = response.getResults();
			usersResult = JSONUtil.toJSON(results);
			LOG.debug("User search result is : " + usersResult);

		}
		catch (SolrServerException e) {
			LOG.error("UnsupportedEncodingException while performing User search");
			throw new SolrException("Exception while performing search for user. Reason : " + e.getMessage(), e);
		}

		LOG.info("Method searchUsers finished for username pattern :" + loginNamePattern + " returning : " + usersResult);
		return usersResult;
	}

	/**
	 * Method to perform search of Users from solr based on the input pattern for user and company.
	 * 
	 * @throws InvalidInputException
	 * @throws SolrException
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	@Override
	public String searchUsersByLoginNameOrName(String pattern, long companyId) throws InvalidInputException, SolrException, MalformedURLException
			{
		LOG.info("Method searchUsersByLoginNameOrName called for pattern :" + pattern);
		if (pattern == null) {
			throw new InvalidInputException("Pattern is null or empty while searching for Users");
		}
		LOG.info("Method searchUsersByLoginNameOrName() called for parameter : " + pattern);
		String usersResult = null;
		QueryResponse response = null;
		pattern = pattern+"*";
		try {
			SolrServer solrServer = new HttpSolrServer(solrUserUrl);
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery("displayName:" + pattern + " OR "+CommonConstants.USER_FIRST_NAME_SOLR+":" + pattern + " OR "+CommonConstants.USER_LAST_NAME_SOLR+":" + pattern + " OR "+CommonConstants.USER_LOGIN_NAME_SOLR+":" + pattern);
			solrQuery.addFilterQuery("companyId:" + companyId);
			solrQuery.addFilterQuery(CommonConstants.STATUS_SOLR+":" + CommonConstants.STATUS_ACTIVE + " OR "+CommonConstants.STATUS_SOLR+":" + CommonConstants.STATUS_NOT_VERIFIED + " OR "+CommonConstants.STATUS_SOLR+":"
					+ CommonConstants.STATUS_TEMPORARILY_INACTIVE);
			LOG.debug("Querying solr for searching users");
			response = solrServer.query(solrQuery);
			SolrDocumentList results = response.getResults();
			usersResult = JSONUtil.toJSON(results);
			LOG.debug("User search result is : " + usersResult);
		}
		catch (SolrServerException e) {
			LOG.error("SolrServerException while performing User search");
			throw new SolrException("Exception while performing search for user. Reason : " + e.getMessage(), e);
		}

		LOG.info("Method searchUsersByLoginNameOrName finished for pattern :" + pattern + " returning : " + usersResult);
		return usersResult;
	}
	
	@Override
	public List<SolrDocument> searchUsersByFirstOrLastName(String patternFirst, String patternLast) throws InvalidInputException, SolrException, MalformedURLException {
		LOG.info("Method searchUsersByFirstOrLastName() called for pattern :" + patternFirst + ", " + patternLast);
		if (patternFirst == null && patternLast == null) {
			throw new InvalidInputException("Pattern is null or empty while searching for Users");
		}
		
		List<SolrDocument> users = new ArrayList<SolrDocument>();
		QueryResponse response = null;
		patternFirst = patternFirst + "*";
		patternLast = patternLast + "*";
		try {
			SolrQuery solrQuery = new SolrQuery();
			String[] fields = { CommonConstants.USER_FIRST_NAME_SOLR, CommonConstants.USER_LAST_NAME_SOLR, CommonConstants.USER_DISPLAY_NAME_SOLR,
					CommonConstants.USER_EMAIL_ID_SOLR };
			solrQuery.setFields(fields);
			solrQuery.setQuery(CommonConstants.USER_FIRST_NAME_SOLR + patternFirst + " OR " + CommonConstants.USER_LAST_NAME_SOLR + ":" + patternLast);
			solrQuery.addFilterQuery(CommonConstants.STATUS_SOLR + ":" + CommonConstants.STATUS_ACTIVE + " OR " + CommonConstants.STATUS_SOLR + ":"
					+ CommonConstants.STATUS_NOT_VERIFIED + " OR " + CommonConstants.STATUS_SOLR + ":" + CommonConstants.STATUS_TEMPORARILY_INACTIVE);

			LOG.debug("Querying solr for searching users");
			SolrServer solrServer = new HttpSolrServer(solrUserUrl);
			response = solrServer.query(solrQuery);
			SolrDocumentList results = response.getResults();
			for (SolrDocument solrDocument : results) {
				users.add(solrDocument);
			}
			LOG.debug("User search result size is : " + users.size());
		}
		catch (SolrServerException e) {
			LOG.error("SolrServerException while performing User search");
			throw new SolrException("Exception while performing search for user. Reason : " + e.getMessage(), e);
		}
		LOG.info("Method searchUsersByFirstOrLastName() called for parameter : " + patternFirst + ", " + patternLast + " returning : " + users);
		return users;
	}

	/**
	 * Method to perform search of Users from solr based on the input pattern for user and company.
	 * 
	 * @throws InvalidInputException
	 * @throws SolrException
	 * @throws MalformedURLException
	 */
	@Override
	public String searchUsersByCompany(long companyId, int startIndex, int noOfRows) throws InvalidInputException, SolrException,
			MalformedURLException {
		if (companyId < 0) {
			throw new InvalidInputException("Pattern is null or empty while searching for Users");
		}
		LOG.info("Method searchUsersByCompanyId() called for company id : " + companyId);
		String usersResult = null;
		QueryResponse response = null;
		try {
			SolrServer solrServer = new HttpSolrServer(solrUserUrl);
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery(CommonConstants.STATUS_SOLR+":" + CommonConstants.STATUS_ACTIVE + " OR "+CommonConstants.STATUS_SOLR+":" + CommonConstants.STATUS_NOT_VERIFIED + " OR "+CommonConstants.STATUS_SOLR+":"
					+ CommonConstants.STATUS_TEMPORARILY_INACTIVE);
			solrQuery.addFilterQuery(CommonConstants.COMPANY_ID_SOLR+":" + companyId);
			solrQuery.setStart(startIndex);
			solrQuery.setRows(noOfRows);
			LOG.debug("Querying solr for searching users");
			response = solrServer.query(solrQuery);
			SolrDocumentList results = response.getResults();
			usersResult = JSONUtil.toJSON(results);
			LOG.debug("User search result is : " + usersResult);
		}
		catch (SolrServerException e) {
			LOG.error("SolrServerException while performing User search");
			throw new SolrException("Exception while performing search for user. Reason : " + e.getMessage(), e);
		}

		LOG.info("Method searchUsersByCompanyId() finished for company id : " + companyId);
		return usersResult;
	}

	/**
	 * Method to add User into solr
	 */
	@Override
	public void addUserToSolr(User user) throws SolrException {
		LOG.info("Method to add user to solr called for user : " + user.getFirstName());
		SolrServer solrServer;
		UpdateResponse response = null;
		try {
			solrServer = new HttpSolrServer(solrUserUrl);
			SolrInputDocument document = new SolrInputDocument();
			document.addField(CommonConstants.USER_ID_SOLR, user.getUserId());
			document.addField(CommonConstants.USER_FIRST_NAME_SOLR, user.getFirstName());
			document.addField(CommonConstants.USER_LAST_NAME_SOLR, user.getLastName());
			document.addField(CommonConstants.USER_EMAIL_ID_SOLR, user.getEmailId());
			document.addField(CommonConstants.USER_LOGIN_NAME_COLUMN, user.getEmailId());
			document.addField(CommonConstants.USER_IS_OWNER_SOLR, user.getIsOwner());
			document.addField(CommonConstants.USER_DISPLAY_NAME_SOLR, user.getFirstName()+" "+user.getLastName());
			if (user.getCompany() != null)
				document.addField(CommonConstants.COMPANY_ID_SOLR, user.getCompany().getCompanyId());
			document.addField(CommonConstants.STATUS_SOLR, user.getStatus());
			List<Long> branches = new ArrayList<>();
			List<Long> regions = new ArrayList<>();
			if (user.getUserProfiles() != null)
				for (UserProfile userProfile : user.getUserProfiles()) {
					if (userProfile.getRegionId() != 0)
						regions.add(userProfile.getRegionId());
					if (userProfile.getBranchId() != 0)
						branches.add(userProfile.getBranchId());
				}
			document.addField(CommonConstants.BRANCHES_SOLR, branches);
			document.addField(CommonConstants.REGIONS_SOLR, regions);
			document.addField(CommonConstants.IS_AGENT_SOLR, user.isAgent());
			LOG.debug("response while adding region is {}." + response);
			solrServer.add(document);
			solrServer.commit();
		}
		catch (MalformedURLException e) {
			LOG.error("Exception while adding regions to solr. Reason : " + e.getMessage(), e);
			throw new SolrException("Exception while adding regions to solr. Reason : " + e.getMessage(), e);
		}
		catch (SolrServerException | IOException e) {
			LOG.error("Exception while adding regions to solr. Reason : " + e.getMessage(), e);
			throw new SolrException("Exception while adding regions to solr. Reason : " + e.getMessage(), e);
		}
		LOG.info("Method to add region to solr finshed for region : " + user);
	}

	/*
	 * Method to remove a user from Solr
	 */
	@Override
	public void removeUserFromSolr(long userIdToRemove) throws SolrException {
		LOG.info("Method removeUserFromSolr() to remove user id {} from solr started.", userIdToRemove);
		try {
			SolrServer solrServer = new HttpSolrServer(solrUserUrl);
			solrServer.deleteById(String.valueOf(userIdToRemove));
			solrServer.commit();
		}
		catch (SolrServerException | IOException e) {
			LOG.error("Exception while removing user from solr. Reason : " + e.getMessage(), e);
			throw new SolrException("Exception while removing user from solr. Reason : " + e.getMessage(), e);
		}
		LOG.info("Method removeUserFromSolr() to remove user id {} from solr finished successfully.", userIdToRemove);
	}
}

package com.realtech.socialsurvey.core.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.realtech.socialsurvey.core.entities.AgentSettings;
import com.realtech.socialsurvey.core.entities.OrganizationUnitSettings;
import com.realtech.socialsurvey.core.entities.SocialMediaTokens;
import com.realtech.socialsurvey.core.entities.User;
import com.realtech.socialsurvey.core.exception.NoRecordsFetchedException;
import com.realtech.socialsurvey.core.services.organizationmanagement.OrganizationManagementService;
import com.realtech.socialsurvey.core.services.organizationmanagement.ProfileManagementService;
import com.realtech.socialsurvey.core.services.organizationmanagement.ProfileNotFoundException;
import com.realtech.socialsurvey.core.services.organizationmanagement.UserManagementService;
import org.apache.commons.lang.WordUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.realtech.socialsurvey.core.exception.InvalidInputException;

@Component
public class EmailFormatHelper {
	private static final Logger LOG = LoggerFactory.getLogger(EmailFormatHelper.class);

	@Autowired
	private UserManagementService userManagementService;

	@Autowired
	private OrganizationManagementService organizationManagementService;

	@Autowired
	private ProfileManagementService profileManagementService;
	
	private static final String PARAM_PATTERN_REGEX = "\\[(.*?)\\]";
	private static final String PARAM_PATTERN = "%s";
	private static final String PARAM_OPEN = "[";
	private static final String PARAM_CLOSE = "]";

	public String buildAgentSignature(String agentName, String agentPhone, String agentTitle, String companyName) {
		LOG.info("Formatting Individual Signature for email");
		StringBuilder agentDetail = new StringBuilder();
		/*if (agentName != null && !agentName.isEmpty()) {
            agentDetail.append(agentName).append("<br />");
        }*/
		if (agentPhone != null && !agentPhone.isEmpty()) {
			agentDetail.append(agentPhone).append("<br />");
		}
		if (agentTitle != null && !agentTitle.isEmpty()) {
			agentDetail.append(agentTitle).append("<br />");
		}
		if (companyName != null && !companyName.isEmpty()) {
			agentDetail.append(companyName).append("<br />");
		}
		return agentDetail.toString();
	}

	public String replaceEmailBodyWithParams(String mailBody, List<String> paramOrder) {
		LOG.info("Replacing Default String with Email Params");
		if (paramOrder != null && !paramOrder.isEmpty()) {
			for (String replacementArg : paramOrder) {
				mailBody = mailBody.replaceFirst(PARAM_PATTERN, PARAM_OPEN + replacementArg + PARAM_CLOSE);
			}
		}
		return mailBody;
	}

	public String replaceEmailBodyParamsWithDefaultValue(String mailBody, List<String> paramOrder) {
		LOG.info("Replacing Email Params with Default String");
		Pattern pattern = Pattern.compile(PARAM_PATTERN_REGEX);
		Matcher matcher = pattern.matcher(mailBody);
		while (matcher.find()) {
			paramOrder.add(matcher.group(1));
		}
		mailBody = mailBody.replaceAll(PARAM_PATTERN_REGEX, PARAM_PATTERN);
		return mailBody;
	}
	
	/**
	 * Converts email html format to txt format
	 * @param htmlFormat
	 */
	public String getEmailTextFormat(String htmlFormat){
		LOG.debug("Converting html to text format");
		String textFormat = null;
		if(htmlFormat != null && !htmlFormat.isEmpty()){
			Document document = Jsoup.parse(htmlFormat);
			textFormat = document.body().text();
		}
		return textFormat;
	}
	
	public String getCustomerDisplayNameForEmail(String custFirstName, String custLastName) throws InvalidInputException {
	    LOG.debug( "method getCustomerDisplayNameForEmail started for first name : " + custFirstName + " and last name : " + custLastName );
		 String customerName = custFirstName;
		 if(custFirstName == null || custFirstName.isEmpty()){
	            throw new InvalidInputException("Invalid parameter: passed parameter custFirstName is null or empty");
	        }
        if(custLastName != null && !custLastName.isEmpty()){
        	customerName += " " + custLastName;
        }
        
        String[] custNameArray = customerName.split(" ");
        String custDisplayName = custNameArray[0];
        if(custNameArray.length > 1){
        	if(custNameArray[1] != null && custNameArray[1].length() >= 1){
        		custDisplayName += " " + custNameArray[1].substring(0, 1) + ".";
        	}
        }
        return WordUtils.capitalize(custDisplayName);
	}


	public String replaceLegends( boolean isSubject, String content, String baseUrl, String logoUrl, String link,
		String custFirstName, String custLastName, String agentName, String agentSignature, String recipientMailId,
		String senderEmail, String companyName, String initiatedDate, String currentYear, String fullAddress, String links,
		String agentProfileName, String companyDisclaimer, String agentDisclaimer, String agentLicense )
		throws InvalidInputException
	{
        LOG.info( "Method to replace legends with values called, replaceLegends() started");
        if ( content == null || content.isEmpty() ) {
            LOG.error( "Content passed in replaceLegends is null or empty" );
            throw new InvalidInputException( "Content passed in replaceLegends is null or empty" );
        }

        String customerName = getCustomerDisplayNameForEmail( custFirstName, custLastName );

        content = content.replaceAll( "\\[BaseUrl\\]", "" + baseUrl );
        content = content.replaceAll( "\\[AppBaseUrl\\]", "" + baseUrl );
        content = content.replaceAll( "\\[LogoUrl\\]", "" + logoUrl );
        content = content.replaceAll( "\\[Link\\]", "" + link );
        content = content.replaceAll( "\\[Links\\]", "" + links );
        content = content.replaceAll( "\\[Name\\]", "" + customerName );
        if ( !isSubject ) {
            content = content.replaceFirst( "\\[FirstName\\]", WordUtils.capitalize( custFirstName ) );
        }

        content = content.replaceAll( "\\[FirstName\\]", "" + custFirstName );
        content = content.replaceAll( "\\[AgentName\\]", "" + agentName );
		content = content.replace( "[AgentSignature]", "" + agentSignature );
        content = content.replaceAll( "\\[RecipientEmail\\]", "" + recipientMailId );
        content = content.replaceAll( "\\[SenderEmail\\]", "" + senderEmail );
        content = content.replaceAll( "\\[CompanyName\\]", "" + companyName );
        content = content.replaceAll( "\\[InitiatedDate\\]", "" + initiatedDate );
        content = content.replaceAll( "\\[CurrentYear\\]", "" + currentYear );
        content = content.replaceAll( "\\[FullAddress\\]", "" + fullAddress );
        content = content.replaceAll( "\\[AgentProfileName\\]", "" + agentProfileName );

		//JIRA SS-473 begin
		content = content.replace( "[CompanyDisclaimer]", companyDisclaimer );
		content = content.replace( "[AgentDisclaimer]", agentDisclaimer );
		content = content.replace( "[AgentLicense]", agentLicense );
		//JIRA SS-473 end
		String company_facebook_link = null;
		String company_twitter_link = null;
		String company_linkedin_link = null;
		String company_google_plus_link = null;
		String company_google_review_link = null;
		String company_yelp_link = null;
		String company_zillow_link = null;
		String company_lending_tree_link = null;
		String company_realtor_com_link = null;

		String facebook_link = null;
		String twitter_link = null;
		String linkedin_link = null;
		String google_plus_link = null;
		String google_review_link = null;
		String yelp_link = null;
		String zillow_link = null;
		String lending_tree_link = null;
		String realtor_com_link = null;

		//JIRA SS-626 begin
		//TODO: Fetch agentSettings from emailId
		//TODO: Fetch companySettings.
		//TODO: Get links according to the hierarchy
		try {
			User user = userManagementService.getUserByEmailAddress( senderEmail );
			if ( user == null ) {
				throw new NoRecordsFetchedException( "No user found" );
			}
			AgentSettings agentSettings = userManagementService.getUserSettings( user.getUserId() );
			if ( agentSettings == null ) {
				throw new NoRecordsFetchedException( "No agent setting found" );
			}
			if ( user.getCompany() == null ) {
				throw new NoRecordsFetchedException( "No company found for user Id: " + user.getUserId() );
			}
			OrganizationUnitSettings companySettings = organizationManagementService
				.getCompanySettings( user.getCompany().getCompanyId() );
			if ( companySettings == null ) {
				throw new NoRecordsFetchedException(
					"No company setting found for company Id : " + user.getCompany().getCompanyId() );
			}
			if ( agentSettings.getProfileName() == null || agentSettings.getProfileName().isEmpty() ) {
				throw new NoRecordsFetchedException( "No profileName found for userID : " + user.getUserId() );
			}
			OrganizationUnitSettings profileSettings = profileManagementService
				.getIndividualSettingsByProfileName( agentSettings.getProfileName() );

			SocialMediaTokens socialMediaTokens = profileSettings.getSocialMediaTokens();
			//Set aggregated values
			if ( socialMediaTokens != null ) {
				if ( socialMediaTokens.getFacebookToken() != null )
					facebook_link = socialMediaTokens.getFacebookToken().getFacebookPageLink();
				if ( socialMediaTokens.getTwitterToken() != null )
					twitter_link = socialMediaTokens.getTwitterToken().getTwitterPageLink();
				if ( socialMediaTokens.getLinkedInToken() != null )
					linkedin_link = socialMediaTokens.getLinkedInToken().getLinkedInPageLink();
				if ( socialMediaTokens.getGoogleToken() != null )
					google_plus_link = socialMediaTokens.getGoogleToken().getProfileLink();
				if ( socialMediaTokens.getGoogleBusinessToken() != null )
					google_review_link = socialMediaTokens.getGoogleBusinessToken().getGoogleBusinessLink();
				if ( socialMediaTokens.getYelpToken() != null )
					yelp_link = socialMediaTokens.getYelpToken().getYelpPageLink();
				if ( socialMediaTokens.getZillowToken() != null )
					zillow_link = socialMediaTokens.getZillowToken().getZillowProfileLink();
				if ( socialMediaTokens.getLendingTreeToken() != null )
					lending_tree_link = socialMediaTokens.getLendingTreeToken().getLendingTreeProfileLink();
				if ( socialMediaTokens.getRealtorToken() != null )
					realtor_com_link = socialMediaTokens.getRealtorToken().getRealtorProfileLink();
			}

			socialMediaTokens = companySettings.getSocialMediaTokens();
			//Set company values
			if ( socialMediaTokens != null ) {
				if ( socialMediaTokens.getFacebookToken() != null )
					company_facebook_link = socialMediaTokens.getFacebookToken().getFacebookPageLink();
				if ( socialMediaTokens.getTwitterToken() != null )
					company_twitter_link = socialMediaTokens.getTwitterToken().getTwitterPageLink();
				if ( socialMediaTokens.getLinkedInToken() != null )
					company_linkedin_link = socialMediaTokens.getLinkedInToken().getLinkedInPageLink();
				if ( socialMediaTokens.getGoogleToken() != null )
					company_google_plus_link = socialMediaTokens.getGoogleToken().getProfileLink();
				if ( socialMediaTokens.getGoogleBusinessToken() != null )
					company_google_review_link = socialMediaTokens.getGoogleBusinessToken().getGoogleBusinessLink();
				if ( socialMediaTokens.getYelpToken() != null )
					company_yelp_link = socialMediaTokens.getYelpToken().getYelpPageLink();
				if ( socialMediaTokens.getZillowToken() != null )
					company_zillow_link = socialMediaTokens.getZillowToken().getZillowProfileLink();
				if ( socialMediaTokens.getLendingTreeToken() != null )
					company_lending_tree_link = socialMediaTokens.getLendingTreeToken().getLendingTreeProfileLink();
				if ( socialMediaTokens.getRealtorToken() != null )
					company_realtor_com_link = socialMediaTokens.getRealtorToken().getRealtorProfileLink();
			}
		} catch ( NoRecordsFetchedException e ) {
			LOG.error( "No user found with email address : " + senderEmail );
		} catch ( ProfileNotFoundException e ) {
			LOG.error( "An error occurred while fetching the profile. Reason : ", e );
		}

		content = content.replace( "[facebook_link]", processUrl(twitter_link) );
		content = content.replace( "[twitter_link]", processUrl(twitter_link) );
		content = content.replace( "[linkedin_link]", processUrl(linkedin_link) );
		content = content.replace( "[google_plus_link]", processUrl(google_plus_link) );
		content = content.replace( "[google_review_link]", processUrl(google_review_link) );
		content = content.replace( "[yelp_link]", processUrl(yelp_link) );
		content = content.replace( "[zillow_link]", processUrl(zillow_link) );
		content = content.replace( "[lending_tree_link]", processUrl(lending_tree_link) );
		content = content.replace( "[realtor_com_link]", processUrl(realtor_com_link) );
		content = content.replace( "[company_facebook_link]", processUrl(company_facebook_link) );
		content = content.replace( "[company_twitter_link]", processUrl(company_twitter_link) );
		content = content.replace( "[company_linkedin_link]", processUrl(company_linkedin_link) );
		content = content.replace( "[company_google_plus_link]", processUrl(company_google_plus_link) );
		content = content.replace( "[company_google_review_link]", processUrl(company_google_review_link) );
		content = content.replace( "[company_yelp_link]", processUrl(company_yelp_link) );
		content = content.replace( "[company_zillow_link]", processUrl(company_zillow_link) );
		content = content.replace( "[company_lending_tree_link]", processUrl(company_lending_tree_link) );
		content = content.replace( "[company_realtor_com_link]", processUrl(company_realtor_com_link) );



		//JIRA SS-626 end
        content = content.replaceAll( "null", "" );
        LOG.info( "Method to replace legends with values called, replaceLegends() ended");
        return content;
    }

	String processUrl(String url){
		if ( url == null )
			return "";
		return url;
	}
}
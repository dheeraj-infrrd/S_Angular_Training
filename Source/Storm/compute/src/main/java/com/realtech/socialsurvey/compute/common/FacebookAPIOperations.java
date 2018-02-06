package com.realtech.socialsurvey.compute.common;

import java.io.IOException;
import java.util.Optional;

import com.realtech.socialsurvey.compute.services.api.APIIntegrationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.realtech.socialsurvey.compute.entities.response.FacebookResponse;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Facebook-API Operations
 * @author manish
 *
 */
public class FacebookAPIOperations
{
    private static final Logger LOG = LoggerFactory.getLogger( FacebookAPIOperations.class );
    private static FacebookAPIOperations apiOperations;
    
    private static String FIELDS ="story,message,created_time,updated_time,full_picture,picture,message_tags,from";
    private static String LIMIT = "100";

    private FacebookAPIOperations()
    {}


    public static synchronized FacebookAPIOperations getInstance()
    {
        if ( apiOperations == null ) {
            apiOperations = new FacebookAPIOperations();
        }
        return apiOperations;
    }
    
    /**
     * Fetch feeds for page id with accessToken
     * @param pageId - Facebook page id
     * @param accessToken 
     * @param since - Unix timestamp
     * @param after - after cursor
     * @param before - before cursor
     * @return
     */
    public FacebookResponse fetchFeeds( String pageId , String accessToken, String since, String until, String pagingToken)
    {
        Call<FacebookResponse> requestCall = RetrofitApiBuilder.apiBuilderInstance()
            .getFacebookAPIIntergrationService().fetchFeeds( pageId, accessToken, since, until, LIMIT, pagingToken, FIELDS );
        
        try {
            Response<FacebookResponse> response = requestCall.execute();
            RetrofitApiBuilder.apiBuilderInstance().validateResponse( response );
            if ( LOG.isTraceEnabled() ) {
                LOG.trace( "response {}", response.body() );
            }
            return response.body();
        } catch ( IOException | APIIntegrationException e ) {
            LOG.error( "IOException/ APIIntegrationException caught", e );
            return null;
        }
    }
}

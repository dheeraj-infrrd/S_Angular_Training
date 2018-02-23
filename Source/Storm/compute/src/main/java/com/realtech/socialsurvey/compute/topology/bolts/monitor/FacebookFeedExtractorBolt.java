package com.realtech.socialsurvey.compute.topology.bolts.monitor;

import java.util.List;

import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.realtech.socialsurvey.compute.entities.FacebookToken;
import com.realtech.socialsurvey.compute.entities.SocialMediaTokenResponse;
import com.realtech.socialsurvey.compute.entities.TwitterToken;
import com.realtech.socialsurvey.compute.entities.response.FacebookFeedData;
import com.realtech.socialsurvey.compute.entities.response.SocialResponseObject;
import com.realtech.socialsurvey.compute.enums.ProfileType;
import com.realtech.socialsurvey.compute.enums.SocialFeedType;
import com.realtech.socialsurvey.compute.feeds.FacebookFeedProcessor;
import com.realtech.socialsurvey.compute.feeds.impl.FacebookFeedProcessorImpl;
import com.realtech.socialsurvey.compute.topology.bolts.BaseComputeBolt;
import com.realtech.socialsurvey.compute.utils.UrlHelper;


/**
 * @author manish
 *
 */
public class FacebookFeedExtractorBolt extends BaseComputeBolt
{

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger( FacebookFeedExtractorBolt.class );

    private FacebookFeedProcessor facebookFeedProcessor = new FacebookFeedProcessorImpl();


    private boolean isRateLimitExceeded()
    {
        // TODO ckech for ratelimiting for facebook api (based on user-id, page-id, )
        return false;
    }


    @Override
    public void execute( Tuple input )
    {
        try {
            SocialMediaTokenResponse mediaToken = (SocialMediaTokenResponse) input.getValueByField( "mediaToken" );
            Long companyId = mediaToken.getCompanyId();

            // Check rate limiting for company
            if ( isRateLimitExceeded( /* pass media token*/ ) ) {
                LOG.warn( "Rate limit exceeded" );
            }

            List<FacebookFeedData> feeds = facebookFeedProcessor.fetchFeeds( companyId, mediaToken );

            String lastFetchedKey = getLastFetchedKey( mediaToken );

            LOG.debug( "Total tweet fetched : {}", feeds.size() );
            for ( FacebookFeedData facebookFeedData : feeds ) {
                SocialResponseObject<FacebookFeedData> responseWrapper = createSocialResponseObject( mediaToken,
                    facebookFeedData );
                String responseWrapperString = new Gson().toJson( responseWrapper );

                _collector.emit( new Values( Long.toString( companyId ), responseWrapperString, lastFetchedKey ) );
                LOG.debug( "Emitted successfully {}", responseWrapper );
            }

        }
        // End loop for companies
        catch ( Exception e ) {
            LOG.error( "Error while fetching post from facebook.", e );
        }
    }


    /**
     * Method for creating lastfetched key
     * @param mediaToken
     * @return
     */
    private String getLastFetchedKey( SocialMediaTokenResponse mediaToken )
    {
        String lastFetchedKey = "";
        if ( mediaToken.getSocialMediaTokens() != null && mediaToken.getSocialMediaTokens().getFacebookToken() != null ) {
            FacebookToken token = mediaToken.getSocialMediaTokens().getFacebookToken();
            String pageId = UrlHelper.getFacebookPageIdFromURL( token.getFacebookPageLink() );
            lastFetchedKey = mediaToken.getProfileType().toString() + "_" + mediaToken.getIden() + "_" + pageId;
        }
        return lastFetchedKey;
    }


    /**
     * Create SocialResponseObject with common fields
     * @param mediaToken
     * @param facebookFeedData
     * @return
     */
    private SocialResponseObject<FacebookFeedData> createSocialResponseObject( SocialMediaTokenResponse mediaToken,
        FacebookFeedData facebookFeedData )
    {
        SocialResponseObject<FacebookFeedData> responseWrapper = new SocialResponseObject<>( mediaToken.getCompanyId(),
            SocialFeedType.FACEBOOK, facebookFeedData.getMessage(), facebookFeedData, 1 );

        if ( mediaToken.getProfileType() != null ) {
            responseWrapper.setProfileType( mediaToken.getProfileType() );
            if ( mediaToken.getProfileType() == ProfileType.COMPANY ) {
                responseWrapper.setCompanyId( mediaToken.getIden() );
            } else if ( mediaToken.getProfileType() == ProfileType.REGION ) {
                responseWrapper.setRegionId( mediaToken.getIden() );
            } else if ( mediaToken.getProfileType() == ProfileType.BRANCH ) {
                responseWrapper.setBranchId( mediaToken.getIden() );
            } else if ( mediaToken.getProfileType() == ProfileType.AGENT ) {
                responseWrapper.setAgentId( mediaToken.getIden() );
            }
        }

        if ( facebookFeedData.getMessage() != null ) {
            responseWrapper.setHash( responseWrapper.getText().hashCode() );
        }

        responseWrapper.setPostId( facebookFeedData.getId() );
        responseWrapper.setId( facebookFeedData.getId() );

        if ( facebookFeedData.getUpdatedTime() > 0 ) {
            responseWrapper.setUpdatedTime( facebookFeedData.getUpdatedTime() * 1000 );
        }

        if ( facebookFeedData.getCreatedTime() > 0 ) {
            responseWrapper.setCreatedTime( facebookFeedData.getCreatedTime() * 1000 );
        }

        return responseWrapper;
    }


    @Override
    public void declareOutputFields( OutputFieldsDeclarer declarer )
    {
        declarer.declare( new Fields( "companyId", "post", "lastFetchedKey" ) );
    }


    public FacebookFeedProcessor getFacebookFeedProcessor()
    {
        return facebookFeedProcessor;
    }


    public void setFacebookFeedProcessor( FacebookFeedProcessor facebookFeedProcessor )
    {
        this.facebookFeedProcessor = facebookFeedProcessor;
    }
}

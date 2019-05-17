package com.realtech.socialsurvey.core.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.realtech.socialsurvey.core.commons.CommonConstants;


@Component
public class CommonUtils
{
	private static final Logger LOG = LoggerFactory.getLogger( CommonUtils.class );

    public String getAgentNameForHiddenAgentCompany( String firstName, String lastName )
    {

        String agentName = firstName;
        if ( lastName != null && !lastName.isEmpty() ) {
            agentName = firstName + " " + lastName.substring( 0, 1 );
        }

        return agentName;
    }


    public static String formatDate( Date date, String format )
    {
        if ( date == null ) {
            return "";
        } else if ( format == null || format.isEmpty() ) {
            return date.toString();
        } else {
            return new SimpleDateFormat( format ).format( date );
        }
    }
    
    /**
     * Get the long value of time n days ago
     * @param noOfDays
     * @return
     */
    public static long lastNdaysTimestamp(int noOfDays) {
        Date today = new Date();
        Date daysAgo = new DateTime(today).minusDays(noOfDays).withTimeAtStartOfDay().toDate();
        return daysAgo.getTime();
    }
    
    /**
     * Get the number of milliseconds
     * @param days
     * @return
     */
    public static Long daysToMilliseconds(int days){
        Long result = Long.valueOf(days * 24 * 60 * 60 * 1000);
        return result;
    }
    
    /**
     * This method keeps only alpha numeric characters, _ and - in the fileName 
     * and replaces all spaces with _ .
     * @param fileName
     * @return
     */
    public static String generateCleanFileName(String fileName) {
        fileName = fileName.replaceAll("[^a-zA-Z0-9 _-]", "");
        fileName = fileName.replaceAll( " ", "_" );
        return fileName;
    }
    
    public static Timestamp getEpochDefaultTime() {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
    	try {
    		
			return new Timestamp(sdf.parse( CommonConstants.EPOCH_REMINDER_TIME ).getTime());
		}
		catch ( ParseException pe ) {
			
			LOG.error( " ParseException in CommonUtils : getEpochDefaultTime", pe );
			return new Timestamp( System.currentTimeMillis() ); 
		}
    }
   
}

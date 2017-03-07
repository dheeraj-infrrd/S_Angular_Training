package com.realtech.socialsurvey.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class CommonUtils
{

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
}
package com.realtech.socialsurvey.core.services.organizationmanagement;

import java.io.IOException;
import java.util.List;
import com.realtech.socialsurvey.core.entities.SurveyDetails;
import com.realtech.socialsurvey.core.entities.User;
import com.realtech.socialsurvey.core.entities.UserSettings;


// JIRA SS-137 BY RM05:BOC
/**
 * Interface with methods declared to show dash board of user.
 */
public interface DashboardService {

	public long getAllSurveyCountForPastNdays(String columnName, long columnValue, int numberOfDays);

	public long getCompletedSurveyCountForPastNdays(String columnName, long columnValue, int numberOfDays);

	public long getClickedSurveyCountForPastNdays(String columnName, long columnValue, int numberOfDays);

	public long getSocialPostsForPastNdays(String columnName, long columnValue, int numberOfDays);

	public double getSurveyScore(String columnName, long columnValue, int numberOfDays);

	public int getProfileCompletionPercentage(User user, String columnName, long columnValue, UserSettings userSettings);
	
	public int getBadges(int surveyScore, int surveyCount, int socialPosts, int profileCompleteness);
	
	public void downloadCompleteSurveyData(List<SurveyDetails> surveyDetails, String fileLocation) throws IOException;

	public void downloadIncompleteSurveyData(List<SurveyDetails> surveyDetails, String fileLocation) throws IOException;

}
// JIRA SS-137 BY RM05:EOC

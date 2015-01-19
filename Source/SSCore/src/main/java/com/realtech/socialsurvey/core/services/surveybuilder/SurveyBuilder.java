package com.realtech.socialsurvey.core.services.surveybuilder;

import java.util.List;
import com.realtech.socialsurvey.core.entities.Company;
import com.realtech.socialsurvey.core.entities.Survey;
import com.realtech.socialsurvey.core.entities.SurveyQuestion;
import com.realtech.socialsurvey.core.entities.SurveyQuestionDetails;
import com.realtech.socialsurvey.core.entities.User;
import com.realtech.socialsurvey.core.exception.InvalidInputException;

public interface SurveyBuilder {

	public void createNewSurvey(User user, List<SurveyQuestionDetails> surveyQuestions) throws InvalidInputException;

	public void addSurveyToCompany(Survey survey, Company company, User user) throws InvalidInputException;

	public void addQuestionsToExistingSurvey(User user, Survey survey, List<SurveyQuestionDetails> surveyQuestions) throws InvalidInputException;

	public void deactivateExistingSurveyMappings(User user, SurveyQuestion surveyQuestion) throws InvalidInputException;

	public List<SurveyQuestionDetails> getAllActiveQuestionsOfSurvey(User user) throws InvalidInputException;

}
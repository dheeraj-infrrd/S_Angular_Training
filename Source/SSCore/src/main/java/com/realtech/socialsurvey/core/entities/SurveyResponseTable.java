package com.realtech.socialsurvey.core.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "survey_response")
public class SurveyResponseTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SURVEY_RESPONSE_ID")
	private String surveyStatsReportId;
	
	@Column(name = "SURVEY_DETAILS_ID")
	private String surveyDetailsId;
	
	@Column(name = "ANSWER")
	private String answer;
	
	@Column(name = " QUESTION")
	private String question;
	
	@Column(name = "QUESTION_TYPE")
	private String questionType;

	public String getSurveyStatsReportId() {
		return surveyStatsReportId;
	}

	public void setSurveyStatsReportId(String surveyStatsReportId) {
		this.surveyStatsReportId = surveyStatsReportId;
	}

	public String getSurveyDetailsId() {
		return surveyDetailsId;
	}

	public void setSurveyDetailsId(String surveyDetailsId) {
		this.surveyDetailsId = surveyDetailsId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	@Override
	public String toString() {
		return "SurveyResponseTable [surveyStatsReportId=" + surveyStatsReportId + ", surveyDetailsId="
				+ surveyDetailsId + ", answer=" + answer + ", question=" + question + ", questionType=" + questionType
				+ "]";
	}

}

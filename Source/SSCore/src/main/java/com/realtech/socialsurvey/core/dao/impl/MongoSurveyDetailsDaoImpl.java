package com.realtech.socialsurvey.core.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.realtech.socialsurvey.core.commons.CommonConstants;
import com.realtech.socialsurvey.core.dao.SurveyDetailsDao;
import com.realtech.socialsurvey.core.entities.SurveyDetails;
import com.realtech.socialsurvey.core.entities.SurveyResponse;

/*
 * Provides list of operations to be performed on SurveyDetails collection of mongo. SurveyDetails
 * collection contains list of surveys taken by customers. It also contains answers provided by
 * customers for questions specific to an agent.
 */
@Repository
public class MongoSurveyDetailsDaoImpl implements SurveyDetailsDao {

	private static final Logger LOG = LoggerFactory.getLogger(MongoSurveyDetailsDaoImpl.class);

	public static final String SURVEY_DETAILS_COLLECTION = "SURVEY_DETAILS";

	@Autowired
	private MongoTemplate mongoTemplate;

	/*
	 * Method to fetch survey details on the basis of agentId and customer email.
	 */
	@Override
	public SurveyDetails getSurveyByAgentIdAndCustomerEmail(long agentId, String customerEmail) {
		LOG.info("Method getSurveyByAgentIdAndCustomerEmail() to insert details of survey started.");
		Query query = new Query(Criteria.where(CommonConstants.AGENT_ID_COLUMN).is(agentId));
		query.addCriteria(Criteria.where(CommonConstants.CUSTOMER_EMAIL_COLUMN).is(customerEmail));
		List<SurveyDetails> surveys = mongoTemplate.find(query, SurveyDetails.class, SURVEY_DETAILS_COLLECTION);
		if (surveys == null || surveys.size() == 0)
			return null;
		LOG.info("Method insertSurveyDetails() to insert details of survey finished.");
		return surveys.get(CommonConstants.INITIAL_INDEX);
	}

	/*
	 * Method to insert survey details into the SURVEY_DETAILS collection.
	 */
	@Override
	public void insertSurveyDetails(SurveyDetails surveyDetails) {
		LOG.info("Method insertSurveyDetails() to insert details of survey started.");
		mongoTemplate.insert(surveyDetails, SURVEY_DETAILS_COLLECTION);
		LOG.info("Method insertSurveyDetails() to insert details of survey finished.");
	}

	/*
	 * Method to update email id by appending timestamp in documents from SurveyDetails collection
	 * by agent id and customer's email-id.
	 */
	@Override
	public void updateEmailForExistingFeedback(long agentId, String customerEmail) {
		LOG.info("Method updateEmailForExistingFeedback() to insert details of survey started.");
		Query query = new Query();
		query.addCriteria(Criteria.where(CommonConstants.AGENT_ID_COLUMN).is(agentId));
		query.addCriteria(Criteria.where(CommonConstants.CUSTOMER_EMAIL_COLUMN).is(customerEmail));
		Update update = new Update();
		update.set(CommonConstants.CUSTOMER_EMAIL_COLUMN, customerEmail + "#" + new Timestamp(System.currentTimeMillis()));
		update.set(CommonConstants.MODIFIED_ON_COLUMN, new Date());
		mongoTemplate.updateMulti(query, update, SURVEY_DETAILS_COLLECTION);
		LOG.info("Method updateEmailForExistingFeedback() to insert details of survey finished.");
	}

	/*
	 * Method to update questions for survey in SURVEY_DETAILS collection.
	 */
	@Override
	public void updateCustomerResponse(long agentId, String customerEmail, SurveyResponse surveyResponse, int stage) {
		LOG.info("Method updateCustomerResponse() to update response provided by customer started.");
		Query query = new Query();
		query.addCriteria(Criteria.where(CommonConstants.AGENT_ID_COLUMN).is(agentId));
		query.addCriteria(Criteria.where(CommonConstants.CUSTOMER_EMAIL_COLUMN).is(customerEmail));
		Update update = new Update();
		update.set("stage", stage);
		update.set(CommonConstants.MODIFIED_ON_COLUMN, new Date());
		update.pull("surveyResponse", new BasicDBObject("question", surveyResponse.getQuestion()));
		mongoTemplate.updateMulti(query, update, SURVEY_DETAILS_COLLECTION);
		mongoTemplate.updateMulti(query, new Update().push("surveyResponse", surveyResponse), SURVEY_DETAILS_COLLECTION);
		LOG.info("Method updateCustomerResponse() to update response provided by customer finished.");
	}

	/*
	 * Method to update answer and response for gateway question of survey in SURVEY_DETAILS
	 * collection.
	 */
	@Override
	public void updateGatewayAnswer(long agentId, String customerEmail, String mood, String review, boolean isAbusive) {
		LOG.info("Method updateGatewayAnswer() to update review provided by customer started.");
		Query query = new Query();
		query.addCriteria(Criteria.where(CommonConstants.AGENT_ID_COLUMN).is(agentId));
		query.addCriteria(Criteria.where(CommonConstants.CUSTOMER_EMAIL_COLUMN).is(customerEmail));
		Update update = new Update();
		update.set(CommonConstants.STAGE_COLUMN, CommonConstants.SURVEY_STAGE_COMPLETE);
		update.set(CommonConstants.MOOD_COLUMN, mood);
		update.set("review", review);
		update.set("isAbusive", isAbusive);
		update.set(CommonConstants.MODIFIED_ON_COLUMN, new Date());
		mongoTemplate.updateMulti(query, update, SURVEY_DETAILS_COLLECTION);
		LOG.info("Method updateGatewayAnswer() to update review provided by customer finished.");
	}

	/*
	 * Method to calculate and update final score based upon rating questions.
	 */
	@Override
	public void updateFinalScore(long agentId, String customerEmail) {
		LOG.info("Method to calculate and update final score based upon rating questions started.");
		Query query = new Query();
		List<String> ratingType = new ArrayList<>();
		ratingType.add("sb-range-smiles");
		ratingType.add("sb-range-scale");
		ratingType.add("sb-range-star");
		query.addCriteria(Criteria.where(CommonConstants.AGENT_ID_COLUMN).is(agentId));
		query.addCriteria(Criteria.where(CommonConstants.CUSTOMER_EMAIL_COLUMN).is(customerEmail));
		query.addCriteria(Criteria.where("surveyResponse.questionType").in(ratingType));
		List<SurveyResponse> surveyResponse = mongoTemplate.find(query, SurveyDetails.class, SURVEY_DETAILS_COLLECTION)
				.get(CommonConstants.INITIAL_INDEX).getSurveyResponse();
		double noOfResponse = 0;
		double answer = 0;
		for (SurveyResponse response : surveyResponse) {
			if (response.getQuestionType().equals(ratingType.get(CommonConstants.INITIAL_INDEX))
					|| response.getQuestionType().equals(ratingType.get(1)) || response.getQuestionType().equals(ratingType.get(2))) {
				if (response.getAnswer() != null && !response.getAnswer().isEmpty()) {
					answer += Integer.parseInt(response.getAnswer());
					noOfResponse++;
				}
			}
		}
		Update update = new Update();
		update.set(CommonConstants.SCORE_COLUMN, answer / noOfResponse);
		update.set(CommonConstants.MODIFIED_ON_COLUMN, new Date());
		mongoTemplate.updateMulti(query, update, SURVEY_DETAILS_COLLECTION);
		LOG.info("Method to calculate and update final score based upon rating questions finished.");
	}

	@Override
	public void updateSurveyAsClicked(long agentId, String customerEmail) {
		LOG.info("Method updateSurveyAsClicked() to mark survey as clicked started.");
		Query query = new Query();
		query.addCriteria(Criteria.where(CommonConstants.AGENT_ID_COLUMN).is(agentId));
		query.addCriteria(Criteria.where(CommonConstants.CUSTOMER_EMAIL_COLUMN).is(customerEmail));
		Update update = new Update();
		update.set(CommonConstants.SURVEY_CLICKED_COLUMN, true);
		update.set(CommonConstants.MODIFIED_ON_COLUMN, new Date());
		mongoTemplate.updateMulti(query, update, SURVEY_DETAILS_COLLECTION);
		LOG.info("Method updateSurveyAsClicked() to mark survey as clicked finished.");
	}

	// JIRA SS-137 and 158 BY RM-05 : BOC

	// -----Methods to get aggregated data from SURVEY_DETAILS collection starting-----

	// This method returns all the surveys that have been sent to or started by customers so far.
	// If columnName field is passed null value it returns count of all the survey.
	// columnName field can contain either of "agentId/branchId/regionId/companyId".
	// columnValue field can contain respective values for the columnName.

	@Override
	public long getSentSurveyCount(String columnName, long columnValue, int noOfDays) {
		LOG.info("Method to get count of total number of surveys sent so far, getSentSurveyCount() started.");
		Date startDate = getNdaysBackDate(noOfDays);

		Query query = new Query();
		if (columnName != null) {
			query = new Query(Criteria.where(columnName).is(columnValue));
		}
		query.addCriteria(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).gte(startDate));
		LOG.info("Method to get count of total number of surveys sent so far, getSentSurveyCount() finished.");
		return mongoTemplate.count(query, SURVEY_DETAILS_COLLECTION);
	}

	// This method returns all the surveys that have been clicked by customers so far.
	// If columnName field is passed null value it returns count of all the survey.
	// "columnName" field can contain either of "agentId/branchId/regionId/companyId".
	// "columnValue" field can contain respective values for the columnName.

	@Override
	public long getClickedSurveyCount(String columnName, long columnValue, int noOfDays) {
		LOG.info("Method to get count of total number of surveys clicked so far, getClickedSurveyCount() started.");
		Date endDate = Calendar.getInstance().getTime();
		Date startDate = getNdaysBackDate(noOfDays);
		Query query = new Query(Criteria.where(CommonConstants.SURVEY_CLICKED_COLUMN).is(true));
		query.addCriteria(Criteria.where("surveyResponse").size(0));
		if (columnName != null) {
			query.addCriteria(Criteria.where(columnName).is(columnValue));
		}
		query.addCriteria(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).gte(startDate).lte(endDate));
		LOG.info("Method to get count of total number of surveys clicked so far, getClickedSurveyCount() finished.");
		return mongoTemplate.count(query, SURVEY_DETAILS_COLLECTION);
	}

	// This method returns all the surveys that have been completed by customers so far.
	// If columnName field is passed null value it returns count of all the survey.
	// "columnName" field can contain either of "agentId/branchId/regionId/companyId".
	// "columnValue" field can contain respective values for the columnName.

	@Override
	public long getCompletedSurveyCount(String columnName, long columnValue, int noOfDays) {
		LOG.info("Method to get count of total number of surveys completed so far, getCompletedSurveyCount() started.");
		Date endDate = Calendar.getInstance().getTime();
		Date startDate = getNdaysBackDate(noOfDays);
		Query query = new Query(Criteria.where(CommonConstants.STAGE_COLUMN).is(CommonConstants.SURVEY_STAGE_COMPLETE));
		if (columnName != null) {
			query.addCriteria(Criteria.where(columnName).is(columnValue));
		}
		query.addCriteria(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).gte(startDate).lte(endDate));
		LOG.info("Method to get count of total number of surveys completed so far, getCompletedSurveyCount() finished.");
		return mongoTemplate.count(query, SURVEY_DETAILS_COLLECTION);
	}

	// This method returns all the surveys that are not yet completed by customers.
	// If columnName field is passed null value it returns count of all the survey.
	// "columnName" field can contain either of "agentId/branchId/regionId/companyId".
	// "columnValue" field can contain respective values for the columnName.

	@Override
	public long getIncompleteSurveyCount(String columnName, long columnValue, int noOfDays) {
		LOG.info("Method to get count of surveys which are not yet completed, getIncompleteSurveyCount() started.");
		Date endDate = Calendar.getInstance().getTime();
		Date startDate = getNdaysBackDate(noOfDays);
		Query query = new Query(Criteria.where(CommonConstants.STAGE_COLUMN).ne(CommonConstants.SURVEY_STAGE_COMPLETE));
		if (columnName != null) {
			query.addCriteria(Criteria.where(columnName).is(columnValue));
		}
		query.addCriteria(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).gte(startDate).lte(endDate));
		LOG.info("Method to get count of surveys which are not yet completed, getIncompleteSurveyCount() finished.");
		return mongoTemplate.count(query, SURVEY_DETAILS_COLLECTION);
	}

	// This method returns a map of customers count based upon their mood.
	// Map contains Mood --> Customers count mapping for each mood for a given
	// agent/branch/region/company.

	@Override
	public Map<String, Long> getCountOfCustomersByMood(String columnName, long columnValue) {
		LOG.info("Method to get customers according to their mood, getCountOfCustomersByMood() started.");
		TypedAggregation<SurveyDetails> aggregation;
		if (columnName == null) {
			aggregation = new TypedAggregation<SurveyDetails>(SurveyDetails.class, Aggregation.group(CommonConstants.MOOD_COLUMN).count().as("count"));
		}
		else {
			aggregation = new TypedAggregation<SurveyDetails>(SurveyDetails.class, Aggregation.match(Criteria.where(columnName).is(columnValue)),
					Aggregation.group(CommonConstants.MOOD_COLUMN).count().as("count"));
		}

		AggregationResults<SurveyDetails> result = mongoTemplate.aggregate(aggregation, SURVEY_DETAILS_COLLECTION, SurveyDetails.class);
		Map<String, Long> moodSplit = new HashMap<>();
		if (result != null) {
			@SuppressWarnings("unchecked") List<BasicDBObject> moodCount = (List<BasicDBObject>) result.getRawResults().get("result");
			for (BasicDBObject o : moodCount) {
				moodSplit.put(o.get("_id").toString(), Long.parseLong(o.get("count").toString()));
			}
		}
		LOG.info("Method to get customers according to their mood, getCountOfCustomersByMood() finished.");
		return moodSplit;
	}

	// This method returns the customers' count based upon the number of reminder mails sent to
	// them.

	@Override
	public Map<String, Long> getCountOfCustomersByReminderMails(String columnName, long columnValue) {
		LOG.info("Method to get customers according to the number of reminder emails sent, getCountOfCustomersByReminderMails() started.");
		TypedAggregation<SurveyDetails> aggregation;
		if (columnName == null) {
			aggregation = new TypedAggregation<SurveyDetails>(SurveyDetails.class, Aggregation.group(CommonConstants.REMINDER_COUNT_COLUMN).count()
					.as("count"));
		}
		else {
			aggregation = new TypedAggregation<SurveyDetails>(SurveyDetails.class, Aggregation.match(Criteria.where(columnName).is(columnValue)),
					Aggregation.group(CommonConstants.REMINDER_COUNT_COLUMN).count().as("count"));
		}

		AggregationResults<SurveyDetails> result = mongoTemplate.aggregate(aggregation, SURVEY_DETAILS_COLLECTION, SurveyDetails.class);
		Map<String, Long> reminderCountSplit = new HashMap<>();
		if (result != null) {
			@SuppressWarnings("unchecked") List<BasicDBObject> reminderCount = (List<BasicDBObject>) result.getRawResults().get("result");
			for (BasicDBObject reminder : reminderCount) {
				reminderCountSplit.put(reminder.get("_id").toString(), Long.parseLong(reminder.get("count").toString()));
			}
		}
		LOG.info("Method to get customers according to the number of reminder emails sent, getCountOfCustomersByReminderMails() finished.");
		return reminderCountSplit;
	}

	// This method returns the customers' count based upon their current stage i.e. number of
	// questions they have answered so far.

	@Override
	public Map<String, Long> getCountOfCustomersByStage(String columnName, long columnValue) {
		LOG.info("Method to get customers according to stage of survey, getCountOfCustomersByStage() started.");
		TypedAggregation<SurveyDetails> aggregation;
		if (columnName == null) {
			aggregation = new TypedAggregation<SurveyDetails>(SurveyDetails.class, Aggregation.group(CommonConstants.STAGE_COLUMN).count()
					.as("count"));
		}
		else {
			aggregation = new TypedAggregation<SurveyDetails>(SurveyDetails.class, Aggregation.match(Criteria.where(columnName).is(columnValue)),
					Aggregation.group(CommonConstants.STAGE_COLUMN).count().as("count"));
		}
		AggregationResults<SurveyDetails> result = mongoTemplate.aggregate(aggregation, SURVEY_DETAILS_COLLECTION, SurveyDetails.class);
		Map<String, Long> stageCountSplit = new HashMap<>();
		if (result != null) {
			@SuppressWarnings("unchecked") List<BasicDBObject> stageCount = (List<BasicDBObject>) result.getRawResults().get("result");
			for (BasicDBObject stage : stageCount) {
				stageCountSplit.put(stage.get("_id").toString(), Long.parseLong(stage.get("count").toString()));
			}
		}
		LOG.info("Method to get customers according to stage of survey, getCountOfCustomersByStage() finished.");
		return stageCountSplit;
	}

	// Method to return number of surveys taken in a given month of a particular year.

	@Override
	public long getTotalSurveyCountByMonth(int year, int month) {
		LOG.info("Method to get count of total number of surveys taken in a given month and year, getTotalSurveyCountByMonth() started.");
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		Date startDate = calendar.getTime();
		// Returns max value for date in the month set in Calendar instance.
		calendar.set(year, month, calendar.getActualMaximum(5));
		Date endDate = calendar.getTime();
		Query query = new Query(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).gte(startDate));
		query.addCriteria(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).lte(endDate));
		LOG.info("Method to get count of total number of surveys taken in a given month and year, getTotalSurveyCountByMonth() finished.");
		return mongoTemplate.count(query, SURVEY_DETAILS_COLLECTION);
	}

	@Override
	@SuppressWarnings("unchecked")
	public double getRatingForPastNdays(String columnName, long columnValue, int noOfDays) {
		LOG.info("Method getRatingOfAgentForPastNdays(), to calculate rating of agent started.");
		Date startDate = getNdaysBackDate(noOfDays);
		Date endDate = Calendar.getInstance().getTime();
		Query query = new Query(Criteria
				.where(columnName)
				.is(columnValue)
				.andOperator(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).lte(endDate),
						Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).gte(startDate)));
		TypedAggregation<SurveyDetails> aggregation = new TypedAggregation<SurveyDetails>(
				SurveyDetails.class, //
				Aggregation.match(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).lte(endDate)), Aggregation.match(Criteria.where(
						CommonConstants.MODIFIED_ON_COLUMN).gte(startDate)), Aggregation.match(Criteria.where(columnName).is(columnValue)),
				Aggregation.group(columnName).sum(CommonConstants.SCORE_COLUMN).as("total_score") //
		);

		AggregationResults<SurveyDetails> result = mongoTemplate.aggregate(aggregation, SURVEY_DETAILS_COLLECTION, SurveyDetails.class);
		long a = mongoTemplate.count(query, SURVEY_DETAILS_COLLECTION);
		double rating = 0;
		if (result != null && a > 0) {
			List<DBObject> basicDBObject = (List<DBObject>) result.getRawResults().get("result");
			rating = (double) basicDBObject.get(0).get("total_score") / a;
		}
		LOG.info("Method getRatingOfAgentForPastNdays(), to calculate rating of agent finished.");
		return rating;
	}

	// January is denoted with 0.
	public double getRatingByMonth(String columnName, long columnValue, int year, int month) {
		LOG.info("Method getRatingOfAgentByMonth(), to calculate rating of agent started.");
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		Date startDate = calendar.getTime();
		// Returns max value for date in the month set in Calendar instance.
		calendar.set(year, month, calendar.getActualMaximum(5));
		Date endDate = calendar.getTime();
		Query query = new Query(Criteria
				.where(columnName)
				.is(columnValue)
				.andOperator(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).lte(endDate),
						Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).gte(startDate)));
		long count = mongoTemplate.count(query, SURVEY_DETAILS_COLLECTION);
		if (count < 3) {
			LOG.info(columnName + " " + columnValue + " does not qualify for calculation of rating. Returning...");
			return -1;
		}
		TypedAggregation<SurveyDetails> aggregation = new TypedAggregation<SurveyDetails>(SurveyDetails.class, Aggregation.match(Criteria.where(
				CommonConstants.MODIFIED_ON_COLUMN).lte(endDate)), Aggregation.match(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN)
				.gte(startDate)), Aggregation.match(Criteria.where(columnName).is(columnValue)), Aggregation.group(columnName)
				.sum(CommonConstants.SCORE_COLUMN).as("total_score"));

		AggregationResults<SurveyDetails> result = mongoTemplate.aggregate(aggregation, SURVEY_DETAILS_COLLECTION, SurveyDetails.class);
		double rating = 0;
		if (result != null) {
			rating = ((long) result.getRawResults().get("total_score")) / count;
		}
		LOG.info("Method getRatingOfAgentByMonth(), to calculate rating of agent finished.");
		return rating;
	}

	// Method to get count of posts shared by customers on various social networking sites for
	// "agent/branch/region/company/all".
	// Returns posts count on that site.

	@Override
	public long getSocialPostsCount(String columnName, long columnValue, int numberOfDays) {
		LOG.info("Method to count number of social posts by customers, getSocialPostsCount() started.");
		Date endDate = Calendar.getInstance().getTime();
		Date startDate = getNdaysBackDate(numberOfDays);
		Query query = new Query(Criteria.where("sharedOn").exists(true));
		if (columnName != null) {
			query.addCriteria(Criteria.where(columnName).is(columnValue));
		}
		query.addCriteria(Criteria.where(CommonConstants.MODIFIED_ON_COLUMN).gte(startDate).lte(endDate));
		LOG.info("Method to count number of social posts by customers, getSocialPostsCount() finished.");
		return mongoTemplate.count(query, SURVEY_DETAILS_COLLECTION);
	}

	// Method to get count of surveys initiated by customers and agents separately.
	// Columns can only be from : {agentId/branchId/regionId}

	@Override
	public Map<String, Long> getCountOfSurveyInitiators(String columnName, long columnValue) {
		LOG.info("Method to count number of surveys initiators, getCountOfSurveyInitiators() started.");
		TypedAggregation<SurveyDetails> aggregation;
		if (columnName == null) {
			aggregation = new TypedAggregation<SurveyDetails>(SurveyDetails.class, Aggregation.group(CommonConstants.INITIATED_BY_COLUMN).count()
					.as("count"));
		}
		else {
			aggregation = new TypedAggregation<SurveyDetails>(SurveyDetails.class, Aggregation.match(Criteria.where(columnName).is(columnValue)),
					Aggregation.group(CommonConstants.INITIATED_BY_COLUMN).count().as("count"));
		}
		AggregationResults<SurveyDetails> result = mongoTemplate.aggregate(aggregation, SURVEY_DETAILS_COLLECTION, SurveyDetails.class);
		Map<String, Long> initiatorCountSplit = new HashMap<>();
		if (result != null) {
			@SuppressWarnings("unchecked") List<BasicDBObject> initiatorCount = (List<BasicDBObject>) result.getRawResults().get("result");
			for (BasicDBObject post : initiatorCount) {
				initiatorCountSplit.put(post.get("_id").toString(), Long.parseLong(post.get("count").toString()));
			}
		}
		LOG.info("Method to count number of surveys initiators, getCountOfSurveyInitiators() finished.");
		return initiatorCountSplit;
	}

	/*
	 * Returns a list of feedbacks provided by customers. First sorted on score then on date (both
	 * descending). ColumnName can be "agentId/branchId/regionId/companyId". ColumnValue should be
	 * value for respective column. limitScore is the max score under which reviews have to be shown
	 */

	@Override
	public List<SurveyDetails> getFeedbacks(String columnName, long columnValue, int start, int rows, double startScore, double limitScore) {
		LOG.info("Method to fetch all the feedbacks from SURVEY_DETAILS collection, getFeedbacks() started.");
		Query query = new Query();
		if (columnName != null) {
			query.addCriteria(Criteria.where(columnName).is(columnValue));
		}
		if (startScore > 0 && limitScore > 0) {
			query.addCriteria(new Criteria().andOperator(Criteria.where(CommonConstants.SCORE_COLUMN).gte(startScore),
					Criteria.where(CommonConstants.SCORE_COLUMN).lte(limitScore)));
		}
		if (start > -1) {
			query.skip(start);
		}
		if (rows > -1) {
			query.limit(rows);
		}
		query.with(new Sort(Sort.Direction.DESC, CommonConstants.UPDATED_ON));
		query.with(new Sort(Sort.Direction.DESC, CommonConstants.SCORE_COLUMN));
		List<SurveyDetails> surveysWithReviews = mongoTemplate.find(query, SurveyDetails.class, SURVEY_DETAILS_COLLECTION);

		LOG.info("Method to fetch all the feedbacks from SURVEY_DETAILS collection, getFeedbacks() finished.");
		return surveysWithReviews;
	}

	@Override
	public long getFeedBacksCount(String columnName, long columnValue, double startScore, double limitScore) {
		LOG.info("Method getFeedBacksCount started for columnName:" + columnName + " columnValue:" + columnValue + " startScore:" + startScore
				+ " limitScore:" + limitScore);
		Query query = new Query();
		if (columnName != null) {
			query.addCriteria(Criteria.where(columnName).is(columnValue));
		}
		if (startScore > 0 && limitScore > 0) {
			query.addCriteria(new Criteria().andOperator(Criteria.where(CommonConstants.SCORE_COLUMN).gte(startScore),
					Criteria.where(CommonConstants.SCORE_COLUMN).lt(limitScore)));
		}
		long feedBackCount = mongoTemplate.count(query, SURVEY_DETAILS_COLLECTION);
		LOG.info("Method getFeedBacksCount executed successfully");
		return feedBackCount;
	}
	
	/*
	 * Returns a list of survey which are not yet competed by customers.Sorted on date (
	 * descending). ColumnName can be "agentId/branchId/regionId/companyId". ColumnValue should be
	 * value for respective column. limitScore is the max score under which reviews have to be shown
	 */

	@Override
	public List<SurveyDetails> getIncompleteSurvey(String columnName, long columnValue, int start, int rows, double startScore, double limitScore) {
		LOG.info("Method to fetch all the incomplete survey from SURVEY_DETAILS collection, getIncompleteSurvey() started.");
		Query query = new Query();
		if (columnName != null) {
			query.addCriteria(Criteria.where(columnName).is(columnValue));
		}
		if (startScore > 0 && limitScore > 0) {
			query.addCriteria(new Criteria().andOperator(Criteria.where(CommonConstants.SCORE_COLUMN).gte(startScore),
					Criteria.where(CommonConstants.SCORE_COLUMN).lte(limitScore)));
		}
		query.addCriteria(Criteria.where(CommonConstants.STAGE_COLUMN).ne(CommonConstants.SURVEY_STAGE_COMPLETE));
		if (start > -1) {
			query.skip(start);
		}
		if (rows > -1) {
			query.limit(rows);
		}
		query.with(new Sort(Sort.Direction.DESC, CommonConstants.UPDATED_ON));
		List<SurveyDetails> surveysWithReviews = mongoTemplate.find(query, SurveyDetails.class, SURVEY_DETAILS_COLLECTION);

		LOG.info("Method to fetch all the incoplete survey from SURVEY_DETAILS collection, getIncompleteSurvey() finished.");
		return surveysWithReviews;
	}


	private Date getNdaysBackDate(int noOfDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(5, noOfDays * (-1));
		Date startDate = calendar.getTime();
		if (noOfDays == -1) {
			calendar.setTimeInMillis(0);
			startDate = calendar.getTime();
		}
		return startDate;
	}

	// JIRA SS-137 and 158 : EOC
}
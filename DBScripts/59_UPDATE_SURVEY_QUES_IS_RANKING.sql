ALTER TABLE `ss_user`.`SURVEY_QUESTIONS_MAPPING` 
ADD COLUMN `IS_USER_RANKING_QUESTION` INT(1) NOT NULL AFTER `IS_RATING_QUESTION`;
UPDATE ss_user.SURVEY_QUESTIONS_MAPPING set IS_USER_RANKING_QUESTION=1 where IS_RATING_QUESTION=1;

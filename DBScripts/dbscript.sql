-- MySQL Script generated by MySQL Workbench
-- Tue Nov 25 18:25:52 2014
-- Model: New Model    Version: 1.0
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ss_user
-- -----------------------------------------------------
-- Social Survey User Database. Holds the details created by users of the system.
CREATE SCHEMA IF NOT EXISTS `ss_user` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `ss_user` ;

-- -----------------------------------------------------
-- Table `ss_user`.`COMPANY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`COMPANY` (
  `COMPANY_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `COMPANY` VARCHAR(250) NOT NULL COMMENT 'Company name',
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`COMPANY_ID`))
ENGINE = InnoDB
COMMENT = 'Holds the company meta data';


-- -----------------------------------------------------
-- Table `ss_user`.`USERS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`USERS` (
  `USER_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` INT UNSIGNED NOT NULL,
  `LOGIN_NAME` VARCHAR(45) NOT NULL,
  `LOGIN_PASSWORD` VARCHAR(75) NOT NULL,
  `EMAIL_ID` VARCHAR(250) NULL,
  `DISPLAY_NAME` VARCHAR(75) NULL,
  `SOURCE` VARCHAR(2) NOT NULL COMMENT 'Source of record',
  `SOURCE_USER_ID` INT NULL,
  `IS_ATLEAST_ONE_USERPROFILE_COMPLETE` INT(1) NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `LAST_LOGIN` TIMESTAMP NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  INDEX `fk_USERS_COMPANY_idx` (`COMPANY_ID` ASC),
  CONSTRAINT `fk_USERS_COMPANY`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Holds the user details. A user can have multiple profiles as mapped with user profile table';


-- -----------------------------------------------------
-- Table `ss_user`.`PROFILES_MASTER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`PROFILES_MASTER` (
  `PROFILE_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `PROFILE` VARCHAR(45) NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`PROFILE_ID`))
ENGINE = InnoDB
COMMENT = 'Available profiles in the application.';


-- -----------------------------------------------------
-- Table `ss_user`.`REGION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`REGION` (
  `REGION_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` INT UNSIGNED NOT NULL,
  `REGION` VARCHAR(250) NOT NULL COMMENT 'Region name',
  `IS_DEFAULT_BY_SYSTEM` INT(1) NOT NULL COMMENT 'In case, the company does not have this profile, a default will be created by the system',
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`REGION_ID`),
  INDEX `fk_REGION_COMPANY1_idx` (`COMPANY_ID` ASC),
  CONSTRAINT `fk_REGION_COMPANY1`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Region details of a company. In case, if the admin decides there is no region for the company, then a default row will be added.';


-- -----------------------------------------------------
-- Table `ss_user`.`BRANCH`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`BRANCH` (
  `BRANCH_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` INT UNSIGNED NOT NULL,
  `REGION_ID` INT UNSIGNED NOT NULL,
  `BRANCH` VARCHAR(250) NOT NULL,
  `IS_DEFAULT_BY_SYSTEM` INT(1) NOT NULL COMMENT 'In case, the company does not have this profile, a default will be created by the system',
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`BRANCH_ID`),
  INDEX `fk_BRANCH_REGION1_idx` (`REGION_ID` ASC),
  INDEX `fk_BRANCH_COMPANY1_idx` (`COMPANY_ID` ASC),
  CONSTRAINT `fk_BRANCH_REGION1`
    FOREIGN KEY (`REGION_ID`)
    REFERENCES `ss_user`.`REGION` (`REGION_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BRANCH_COMPANY1`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Branch details under a region. In case, there are no branches under a region, a default row will be added.';


-- -----------------------------------------------------
-- Table `ss_user`.`USER_PROFILE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`USER_PROFILE` (
  `USER_PROFILE_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `USER_ID` INT UNSIGNED NOT NULL,
  `COMPANY_ID` INT UNSIGNED NOT NULL,
  `EMAIL_ID` VARCHAR(250) NOT NULL,
  `REGION_ID` INT NOT NULL,
  `BRANCH_ID` INT NOT NULL,
  `AGENT_ID` INT NOT NULL,
  `PROFILES_MASTER_ID` INT UNSIGNED NOT NULL,
  `USER_PROFILE_TYPE` VARCHAR(3) NULL COMMENT 'Mostly used to differentiate agents. In case, if the agent is a loan officer or realtor',
  `PROFILE_COMPLETION_STAGE` VARCHAR(45) NULL,
  `IS_PROFILE_COMPLETE` INT(1) NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_PROFILE_ID`),
  INDEX `fk_USER_PROFILE_PROFILES_MASTER1_idx` (`PROFILES_MASTER_ID` ASC),
  INDEX `fk_USER_PROFILE_COMPANY1_idx` (`COMPANY_ID` ASC),
  INDEX `fk_USER_PROFILE_USERS1_idx` (`USER_ID` ASC),
  CONSTRAINT `fk_USER_PROFILE_PROFILES_MASTER1`
    FOREIGN KEY (`PROFILES_MASTER_ID`)
    REFERENCES `ss_user`.`PROFILES_MASTER` (`PROFILE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_PROFILE_COMPANY1`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_PROFILE_USERS1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `ss_user`.`USERS` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Holds the details of profile for the company. A row is entered for an association with an organisation level.';


-- -----------------------------------------------------
-- Table `ss_user`.`ORGANIZATION_LEVEL_SETTINGS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`ORGANIZATION_LEVEL_SETTINGS` (
  `ORGANIZATION_LEVEL_SETTINGS_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` INT UNSIGNED NOT NULL,
  `REGION_ID` INT NOT NULL,
  `BRANCH_ID` INT NOT NULL,
  `AGENT_ID` INT NOT NULL,
  `SETTING_KEY` VARCHAR(45) NOT NULL,
  `SETTING_VALUE` VARCHAR(500) NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL COMMENT 'More details need to be added',
  PRIMARY KEY (`ORGANIZATION_LEVEL_SETTINGS_ID`),
  INDEX `fk_ORGANIZATION_PROFILE_COMPANY1_idx` (`COMPANY_ID` ASC),
  CONSTRAINT `fk_ORGANIZATION_PROFILE_COMPANY1`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Holds the settings for organisation level. The level could be company, region or branch';


-- -----------------------------------------------------
-- Table `ss_user`.`ACCOUNTS_MASTER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`ACCOUNTS_MASTER` (
  `ACCOUNTS_MASTER_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NAME` VARCHAR(45) NOT NULL,
  `MAX_USERS_ALLOWED` INT NOT NULL,
  `MAX_TIME_VALIDITY_ALLOWED_IN_DAYS` INT NOT NULL,
  `AMOUNT` FLOAT NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ACCOUNTS_MASTER_ID`))
ENGINE = InnoDB
COMMENT = 'Holds the details of accounts possible in the application.';


-- -----------------------------------------------------
-- Table `ss_user`.`LICENSE_DETAILS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`LICENSE_DETAILS` (
  `LICENSE_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ACCOUNTS_MASTER_ID` INT UNSIGNED NOT NULL,
  `COMPANY_ID` INT UNSIGNED NOT NULL,
  `LICENSE_START_DATE` TIMESTAMP NOT NULL,
  `LICENSE_END_DATE` TIMESTAMP NOT NULL,
  `PAYMENT_MODE` CHAR(1) NOT NULL COMMENT '\'M\' for manual, \'A\' for auto',
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`LICENSE_ID`),
  INDEX `fk_LICENCE_DETAILS_ACCOUNTS_MASTER1_idx` (`ACCOUNTS_MASTER_ID` ASC),
  INDEX `fk_LICENCE_DETAILS_COMPANY1_idx` (`COMPANY_ID` ASC),
  CONSTRAINT `fk_LICENCE_DETAILS_ACCOUNTS_MASTER1`
    FOREIGN KEY (`ACCOUNTS_MASTER_ID`)
    REFERENCES `ss_user`.`ACCOUNTS_MASTER` (`ACCOUNTS_MASTER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LICENCE_DETAILS_COMPANY1`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Holds the license details for a company';


-- -----------------------------------------------------
-- Table `ss_user`.`SURVEY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`SURVEY` (
  `SURVEY_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` INT UNSIGNED NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SURVEY_ID`),
  INDEX `fk_SURVEY_COMPANY1_idx` (`COMPANY_ID` ASC),
  CONSTRAINT `fk_SURVEY_COMPANY1`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ss_user`.`SURVEY_QUESTIONS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`SURVEY_QUESTIONS` (
  `SURVEY_QUESTIONS_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` INT UNSIGNED NOT NULL,
  `SURVEY_ID` INT UNSIGNED NOT NULL,
  `SURVEY_QUESTIONS_CODE` VARCHAR(10) NOT NULL COMMENT 'Pre defined code for survey questions. Determines the type of questions',
  `SURVEY_QUESTION` VARCHAR(500) NOT NULL,
  `ORDER` INT NOT NULL,
  `IS_RATING_QUESTION` INT(1) NOT NULL COMMENT '0 for no, 1 for yes',
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SURVEY_QUESTIONS_ID`),
  INDEX `fk_SURVEY_QUESTIONS_COMPANY1_idx` (`COMPANY_ID` ASC),
  INDEX `fk_SURVEY_QUESTIONS_SURVEY1_idx` (`SURVEY_ID` ASC),
  CONSTRAINT `fk_SURVEY_QUESTIONS_COMPANY1`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SURVEY_QUESTIONS_SURVEY1`
    FOREIGN KEY (`SURVEY_ID`)
    REFERENCES `ss_user`.`SURVEY` (`SURVEY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Holds the survey questions for a survey';


-- -----------------------------------------------------
-- Table `ss_user`.`SURVEY_QUESTIONS_ANSWER_OPTIONS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`SURVEY_QUESTIONS_ANSWER_OPTIONS` (
  `SURVEY_QUESTIONS_ANSWER_OPTIONS_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `SURVEY_QUESTIONS_ID` INT UNSIGNED NOT NULL,
  `ANSWER` VARCHAR(250) NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SURVEY_QUESTIONS_ANSWER_OPTIONS_ID`),
  INDEX `fk_SURVEY_QUESTIONS_ANSWER_OPTIONS_SURVEY_QUESTIONS1_idx` (`SURVEY_QUESTIONS_ID` ASC),
  CONSTRAINT `fk_SURVEY_QUESTIONS_ANSWER_OPTIONS_SURVEY_QUESTIONS1`
    FOREIGN KEY (`SURVEY_QUESTIONS_ID`)
    REFERENCES `ss_user`.`SURVEY_QUESTIONS` (`SURVEY_QUESTIONS_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ss_user`.`COMPANY_INVITATION_LICENSE_KEY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`COMPANY_INVITATION_LICENSE_KEY` (
  `COMPANY_INVITATION_LICENSE_KEY_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ACCOUNTS_MASTER_ID` INT UNSIGNED NOT NULL,
  `LICENSE_KEY` VARCHAR(100) NULL COMMENT 'License key against which registration will be validated.',
  `VALID_UNTIL` TIMESTAMP NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`COMPANY_INVITATION_LICENSE_KEY_ID`),
  UNIQUE INDEX `LICENSE_KEY_UNIQUE` (`LICENSE_KEY` ASC),
  INDEX `fk_COMPANY_INVITATION_LICENSE_KEY_ACCOUNTS_MASTER1_idx` (`ACCOUNTS_MASTER_ID` ASC),
  CONSTRAINT `fk_COMPANY_INVITATION_LICENSE_KEY_ACCOUNTS_MASTER1`
    FOREIGN KEY (`ACCOUNTS_MASTER_ID`)
    REFERENCES `ss_user`.`ACCOUNTS_MASTER` (`ACCOUNTS_MASTER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'This table will store the license key if the invitation is sent by SAAS control panel. The account created using this key, will not be tracked for payment from the application. The license table will set the payment mode to \'M\' for accounts created by this key.';


-- -----------------------------------------------------
-- Table `ss_user`.`USER_INVITE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ss_user`.`USER_INVITE` (
  `USER_INVITE_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` INT UNSIGNED NOT NULL,
  `PROFILE_MASTERS_ID` INT UNSIGNED NOT NULL,
  `INVITATION_KEY` VARCHAR(500) NULL,
  `INVITATION_TIME` TIMESTAMP NOT NULL,
  `INVITATION_EMAIL_ID` VARCHAR(250) NOT NULL,
  `INVITATION_PARAMETERS` VARCHAR(500) NULL,
  `INVITATION_VALID_UNTIL` TIMESTAMP NOT NULL,
  `INVITATION_SENT_BY` INT UNSIGNED NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`USER_INVITE_ID`),
  INDEX `fk_USER_INVITE_COMPANY1_idx` (`COMPANY_ID` ASC),
  INDEX `fk_USER_INVITE_PROFILES_MASTER1_idx` (`PROFILE_MASTERS_ID` ASC),
  CONSTRAINT `fk_USER_INVITE_COMPANY1`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_INVITE_PROFILES_MASTER1`
    FOREIGN KEY (`PROFILE_MASTERS_ID`)
    REFERENCES `ss_user`.`PROFILES_MASTER` (`PROFILE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Holds the user invitation record';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- ALTER SCRIPTS: 27th Nov 2014
ALTER TABLE ss_user.COMPANY ADD COLUMN REGISTRATION_STAGE VARCHAR(45) AFTER COMPANY, ADD COLUMN IS_REGISTRATION_COMPLETE INT(1) NOT NULL AFTER REGISTRATION_STAGE;

ALTER TABLE ss_user.LICENSE_DETAILS ADD COLUMN NEXT_RETRY_TIME TIMESTAMP NOT NULL AFTER PAYMENT_MODE, ADD COLUMN PAYMENT_RETRIES INT(2) NOT NULL AFTER NEXT_RETRY_TIME;
-- ALTER SCRIPTS: 28 Nov 2014
ALTER TABLE `ss_user`.`USERS` CHANGE COLUMN `LOGIN_PASSWORD` `LOGIN_PASSWORD` VARCHAR(200) NOT NULL ;

-- ALTER SCRIPTS: 01 Dec 2014: For Survey Builder

ALTER TABLE ss_user.SURVEY ADD COLUMN SURVEY_NAME VARCHAR(100) AFTER COMPANY_ID;
ALTER TABLE ss_user.SURVEY_QUESTIONS DROP FOREIGN KEY fk_SURVEY_QUESTIONS_COMPANY1;
ALTER TABLE ss_user.SURVEY_QUESTIONS DROP FOREIGN KEY fk_SURVEY_QUESTIONS_SURVEY1;
ALTER TABLE ss_user.SURVEY_QUESTIONS DROP COLUMN COMPANY_ID;
ALTER TABLE ss_user.SURVEY_QUESTIONS DROP COLUMN SURVEY_ID;
ALTER TABLE ss_user.SURVEY_QUESTIONS DROP COLUMN `ORDER`;
ALTER TABLE ss_user.SURVEY_QUESTIONS_ANSWER_OPTIONS ADD COLUMN ANSWER_ORDER INT(2) AFTER ANSWER;
ALTER TABLE ss_user.SURVEY_QUESTIONS DROP COLUMN IS_RATING_QUESTION;

CREATE TABLE IF NOT EXISTS `ss_user`.`SURVEY_QUESTIONS_MAPPING` (
  `SURVEY_QUESTIONS_MAPPING_ID` INT UNSIGNED NOT NULL,
  `SURVEY_ID` INT UNSIGNED NOT NULL,
  `SURVEY_QUESTIONS_ID` INT UNSIGNED NOT NULL,
  `QUESTION_ORDER` INT(2) NOT NULL,
  `IS_RATING_QUESTION` INT(1) NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`SURVEY_QUESTIONS_MAPPING_ID`),
  INDEX `fk_SURVEY_QUESTIONS_MAPPING_SURVEY1_idx` (`SURVEY_ID` ASC),
  INDEX `fk_SURVEY_QUESTIONS_MAPPING_SURVEY_QUESTIONS1_idx` (`SURVEY_QUESTIONS_ID` ASC),
  CONSTRAINT `fk_SURVEY_QUESTIONS_MAPPING_SURVEY1`
    FOREIGN KEY (`SURVEY_ID`)
    REFERENCES `ss_user`.`SURVEY` (`SURVEY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SURVEY_QUESTIONS_MAPPING_SURVEY_QUESTIONS1`
    FOREIGN KEY (`SURVEY_QUESTIONS_ID`)
    REFERENCES `ss_user`.`SURVEY_QUESTIONS` (`SURVEY_QUESTIONS_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- ALTER SCRIPTS: 09 Dec 2014 : For License Details 

ALTER TABLE ss_user.LICENSE_DETAILS ADD COLUMN SUBSCRIPTION_ID VARCHAR(20) AFTER LICENSE_ID;
ALTER TABLE ss_user.LICENSE_DETAILS ADD COLUMN SUBSCRIPTION_ID_SOURCE VARCHAR(20) AFTER STATUS;

CREATE TABLE IF NOT EXISTS `ss_user`.`RETRIED_TRANSACTIONS` (
  `RETRY_ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `TRANSACTION_ID` VARCHAR(20) NOT NULL,
  `LICENSE_ID` INT(10) UNSIGNED NOT NULL,
  `PAYMENT_TOKEN` VARCHAR(20) NOT NULL,
  `AMOUNT` FLOAT NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`RETRY_ID`),
  INDEX `fk_RETRIED_TRANSACTIONS_LICENSE_DETAILS1_idx` (`LICENSE_ID` ASC),
  CONSTRAINT `fk_RETRIED_TRANSACTIONS_LICENSE_DETAILS1`
    FOREIGN KEY (`LICENSE_ID`)
    REFERENCES `ss_user`.`LICENSE_DETAILS` (`LICENSE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Holds the retried payment transaction details for a company';

-- ALTER SCRIPTS: 16 Dec 2014 : Adding IS_OWNER column to USERS

ALTER TABLE ss_user.USERS ADD COLUMN IS_OWNER INT(1) DEFAULT 0 AFTER IS_ATLEAST_ONE_USERPROFILE_COMPLETE;

-- ALTER SCRIPTS: 02 JAN 2015 : Changed LOGIN_PASSWORD to nullable, removed DISPLAY_NAME and added FIRST_NAME, LAST_NAME instead, made LOGIN_NAME UNIQUE KEY.

ALTER TABLE `ss_user`.`USERS` 
CHANGE COLUMN `LOGIN_PASSWORD` `LOGIN_PASSWORD` VARCHAR(200) NULL DEFAULT NULL ,
CHANGE COLUMN `DISPLAY_NAME` `FIRST_NAME` VARCHAR(45) NULL DEFAULT NULL ,
ADD COLUMN `LAST_NAME` VARCHAR(45) NULL DEFAULT NULL AFTER `FIRST_NAME`,
ADD UNIQUE INDEX `LOGIN_NAME_UNIQUE` (`LOGIN_NAME` ASC);

-- ALTER SCRIPTS: 23 Dec 2014 : Adding IS_SUBSCRIPTION_DUE column to LICENSE_DETAILS

ALTER TABLE ss_user.LICENSE_DETAILS ADD COLUMN IS_SUBSCRIPTION_DUE INT(1) DEFAULT 0 AFTER PAYMENT_RETRIES;

ALTER TABLE `ss_user`.`USERS` 
DROP INDEX `LOGIN_NAME_UNIQUE` ,
ADD UNIQUE INDEX `LOGIN_NAME_UNIQUE` (`LOGIN_NAME` ASC, `COMPANY_ID` ASC);


-- ALTER SCRIPTS: 06 Jan 2015 : Adding DISABLED_ACCOUNTS table to ss_user

CREATE TABLE IF NOT EXISTS `ss_user`.`DISABLED_ACCOUNTS` (
  `ID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` INT(10) UNSIGNED NOT NULL,
  `LICENSE_ID` INT(10) UNSIGNED NOT NULL,
  `DISABLE_DATE` TIMESTAMP NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  `MODIFIED_ON` TIMESTAMP NOT NULL,
  `MODIFIED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_DISABLED_ACCOUNTS_COMPANY1_idx` (`COMPANY_ID` ASC),
  INDEX `fk_DISABLED_ACCOUNTS_LICENSE_DETAILS1_idx` (`LICENSE_ID` ASC),
  CONSTRAINT `fk_DISABLED_ACCOUNTS_COMPANY1`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DISABLED_ACCOUNTS_LICENSE_DETAILS1`
    FOREIGN KEY (`LICENSE_ID`)
    REFERENCES `ss_user`.`LICENSE_DETAILS` (`LICENSE_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Holds the disabled account details for a company';

-- CREATE SCRIPTS: 09 Jan 2015 : Adding REMOVED_USER table to ss_user

CREATE TABLE `ss_user`.`REMOVED_USER` (
  `REMOVED_USER_ID` INT unsigned NOT NULL,
  `USER_ID` INT UNSIGNED NOT NULL,
  `COMPANY_ID` INT UNSIGNED NOT NULL,
  `CREATED_ON` TIMESTAMP NOT NULL,
  `CREATED_BY` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`REMOVED_USER_ID`),
  INDEX `fk_REMOVED_USER_1_idx` (`USER_ID` ASC),
  INDEX `fk_REMOVED_USER_2_idx` (`COMPANY_ID` ASC),
  CONSTRAINT `fk_REMOVED_USER_1`
    FOREIGN KEY (`USER_ID`)
    REFERENCES `ss_user`.`USERS` (`USER_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_REMOVED_USER_2`
    FOREIGN KEY (`COMPANY_ID`)
    REFERENCES `ss_user`.`COMPANY` (`COMPANY_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Holds the information of users which have been removed from the company.';

ALTER TABLE `ss_user`.`removed_user` 
CHANGE COLUMN `REMOVED_USER_ID` `REMOVED_USER_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT ;
CREATE TABLE `company_user_report` (
  `company_user_table_id` varchar(36) NOT NULL,
  `company_id` int(10) DEFAULT NULL,
  `user_id` int(11) unsigned DEFAULT NULL,
  `first_name` varchar(250) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` text,
  `social_survey_access_level` text,
  `office_branch_assignment` text,
  `region_assignment` text,
  `office_admin` text,
  `region_admin` text,
  `ss_invite_sent_date` datetime DEFAULT NULL,
  `email_verified` varchar(45) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `profile_complete` text,
  `disclaimer` text,
  `address` text,
  `socially_connected` varchar(45) DEFAULT NULL,
  `fb_data_connection` varchar(45) DEFAULT NULL,
  `fb_connection_status` varchar(45) DEFAULT NULL,
  `last_post_date_fb` datetime DEFAULT NULL,
  `twitter_data_connection` varchar(45) DEFAULT NULL,
  `twitter_connection_status` varchar(45) DEFAULT NULL,
  `last_post_date_twitter` datetime DEFAULT NULL,
  `linkedin_data_connection` varchar(45) DEFAULT NULL,
  `linkedin_connection_status` varchar(45) DEFAULT NULL,
  `last_post_date_linkedin` datetime DEFAULT NULL,
  `google_plus_url` text,
  `zillow_url` text,
  `yelp_url` text,
  `realtor_url` text,
  `gb_url` text,
  `lendingtree_url` text,
  `email_verified_date` datetime DEFAULT NULL,
  `adoption_completed_date` datetime DEFAULT NULL,
  `last_survey_sent_date` datetime DEFAULT NULL,
  `last_survey_posted_date` datetime DEFAULT NULL,
  `ss_profile` text,
  `total_reviews` int(11) DEFAULT NULL,
  `ss_reviews` int(11) DEFAULT NULL,
  `zillow_reviews` int(11) DEFAULT NULL,
  `abusive_reviews` int(11) DEFAULT NULL,
  `3rd_party_reviews` int(11) DEFAULT NULL,
  PRIMARY KEY (`company_user_table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
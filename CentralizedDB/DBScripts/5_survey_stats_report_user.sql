CREATE DATABASE  IF NOT EXISTS `ss_report_demo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ss_report_demo`;
drop table if exists `survey_stats_report_user`;
CREATE TABLE `survey_stats_report_user` (
  `survey_stats_report_id` varchar(45) NOT NULL,
  `Id` text,
  `user_id` int(10) unsigned NOT NULL,
  `user_name` varchar(250) DEFAULT NULL,
  `trx_month` varchar(10) DEFAULT NULL,
  `trx_rcvd` int(11) DEFAULT '0',
  `pending` int(11) DEFAULT '0',
  `duplicates` int(11) DEFAULT '0',
  `corrupted` int(11) DEFAULT '0',
  `abusive` int(11) DEFAULT '0',
  `old_records` int(11) DEFAULT '0',
  `ignored` int(11) DEFAULT '0',
  `mismatched` int(11) DEFAULT '0',
  `sent_count` int(11) DEFAULT '0',
  `clicked_count` int(11) DEFAULT '0',
  `completed` int(11) DEFAULT '0',
  `partially_completed` int(11) DEFAULT '0',
  `complete_percentage` float DEFAULT '0',
  `delta` int(11) DEFAULT '0',
  `created_date` datetime DEFAULT NULL,
  `year` int(11) NOT NULL,
  `month` int(11) NOT NULL,
  `avg_rating` float DEFAULT NULL,
  `detractors` int(11) DEFAULT NULL,
  `passives` int(11) DEFAULT NULL,
  `promoters` int(11) DEFAULT NULL,
  `incomplete` int(11) DEFAULT NULL,
  PRIMARY KEY (`year`,`month`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
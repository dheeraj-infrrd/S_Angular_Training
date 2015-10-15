ALTER TABLE `ss_user`.`COLLECTION_DOTLOOP_PROFILEMAPPING` 
   ADD COLUMN `CREATED_BY` VARCHAR(255) NULL AFTER `AGENT_ID`, 
   ADD COLUMN `CREATED_ON` TIMESTAMP NULL AFTER `CREATED_BY`, 
   ADD COLUMN `MODIFIED_BY` VARCHAR(255) NULL AFTER `CREATED_ON`, 
   ADD COLUMN `MODIFIED_ON` TIMESTAMP NULL AFTER `MODIFIED_BY`;


UPDATE `ss_user`.`COLLECTION_DOTLOOP_PROFILEMAPPING` SET CREATED_ON = NOW() WHERE CREATED_ON IS NULL;
UPDATE `ss_user`.`COLLECTION_DOTLOOP_PROFILEMAPPING` SET MODIFIED_ON = NOW() WHERE MODIFIED_ON IS NULL;
UPDATE `ss_user`.`COLLECTION_DOTLOOP_PROFILEMAPPING` SET CREATED_BY = "Admin" WHERE CREATED_BY IS NULL;
UPDATE `ss_user`.`COLLECTION_DOTLOOP_PROFILEMAPPING` SET MODIFIED_BY = "Admin" WHERE MODIFIED_BY IS NULL;
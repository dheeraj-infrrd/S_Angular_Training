CREATE TABLE `ss_user`.`ENCOMPASS_SDK_VERSION` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `SDK_VERSION` VARCHAR(45) NOT NULL,
  `HOST_NAME` VARCHAR(200) NOT NULL,
  `STATUS` INT(1) NOT NULL,
  `CREATED_ON` TIMESTAMP NULL,
  `CREATED_BY` VARCHAR(45) NULL,
  `MODIFIED_ON` TIMESTAMP NULL,
  `MODIFIED_BY` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`));

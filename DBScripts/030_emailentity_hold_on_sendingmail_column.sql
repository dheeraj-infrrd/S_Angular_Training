USE `ss_user`;

ALTER TABLE `ss_user`.EMAIL_ENTITY ADD COLUMN HOLD_SENDING_EMAIL INT(1) NOT NULL AFTER EMAIL_OBJECT;
DROP DATABASE IF EXISTS bankagents;
CREATE DATABASE IF NOT EXISTS bankagents;
USE bankagents;

CREATE TABLE IF NOT EXISTS account (
	id int(16) NOT NULL AUTO_INCREMENT,
	username varchar(128) NOT NULL,
	password varchar(128) NOT NULL,
	firstName varchar(128) NOT NULL,
	lastName varchar(128) NOT NULL,
	phoneNumber varchar(128) NOT NULL,
	balance long NOT NULL,
	debt long NOT NULL,
	PRIMARY KEY (id)
);

/*B-createPaymentTable*/

/*B-createCreditTable*/

/*B-createTransactionTable*/


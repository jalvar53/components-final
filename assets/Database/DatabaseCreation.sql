DROP DATABASE IF EXISTS bankagents;
CREATE DATABASE IF NOT EXISTS bankagents;
USE bankagents;

CREATE TABLE IF NOT EXISTS credit (
	id int(16) NOT NULL AUTO_INCREMENT,
	amount long NOT NULL,
	state varchar(128) NOT NULL,
	userId int(16) NOT NULL,
	PRIMARY KEY (id)
);

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

CREATE TABLE IF NOT EXISTS payment (
  id int(16) NOT NULL AUTO_INCREMENT,
  payerId int(16) NOT NULL,
  amount varchar(128) NOT NULL,
  payment_date timestamp NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS transaction (
	id int(16) NOT NULL AUTO_INCREMENT,
	receiverId int(16) NOT NULL,
	senderId int(16) NOT NULL,
	amount long NOT NULL,
    transaction_date date,
	PRIMARY KEY (id)
);

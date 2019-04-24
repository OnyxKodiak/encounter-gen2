CREATE DATABASE IF NOT EXISTS `encounter-gen`;
use `encounter-gen`;
CREATE TABLE IF NOT EXISTS creatures (
	id int NOT NULL AUTO_INCREMENT,
	name TINYTEXT NOT NULL,
	`type` TINYTEXT,
	cr FLOAT(2,2) NOT NULL,
	str int NOT NULL,
	dex int NOT NULL,
	con int NOT NULL,
	intl int NOT NULL,
	wis int NOT NULL,
	cha int NOT NULL,
	ac int NOT NULL,
	hp int NOT NULL,
	size TINYTEXT NOT NULL,
	description TINYTEXT,
	userid int NOT NULL,
	createdate date DEFAULT NOW(),
	updated date DEFAULT NOW(),
	shared BOOLEAN,
	CHECK (cr>= 0.25),
	PRIMARY KEY (id),
	FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS interests (
	id int NOT NULL AUTO_INCREMENT,
	name TINYTEXT NOT NULL,
	description TINYTEXT,
	environment TINYTEXT,
	userid int NOT NULL,
	createdate date DEFAULT NOW(),
	updated date DEFAULT NOW(),
	shared BOOLEAN,
	PRIMARY KEY (id),
	FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS  treasures (
	id int NOT NULL AUTO_INCREMENT,
	name TINYTEXT NOT NULL,
	`type` TINYTEXT,
	value int NOT NULL,
	description TINYTEXT,
	userid int NOT NULL,
	createdate date DEFAULT NOW(),
	updated date DEFAULT NOW(),
	shared BOOLEAN,
	PRIMARY KEY (id),
	FOREIGN KEY (userid) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS  users (
	id int NOT NULL AUTO_INCREMENT,
	name TINYTEXT NOT NULL,
	password TINYTEXT NOT NULL,
	createdate date DEFAULT NOW(),
	PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS  encounters(
	partylevel int NOT NULL,
	environment TINYTEXT,
	partysize int NOT NULL,
	difficulty int NOT NULL,
	numencounters int NOT NULL,
	frequency int NOT NULL,
	userid int UNIQUE NOT NULL,
	userselect BOOLEAN,
	loot BOOLEAN,
	nummobs int NOT NULL,
	PRIMARY KEY (userid),
	FOREIGN KEY (userid) REFERENCES users(id)
);

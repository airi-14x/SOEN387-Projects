CREATE TABLE `book`(
	`id` int(11) NOT NULL,
    `title` varchar(64) DEFAULT NULL,
    `description` varchar(256) DEFAULT NULL,
    `isbn` varchar(64) DEFAULT NULL,
    `last_name` varchar(64) DEFAULT NULL,
    `first_name` varchar(64) DEFAULT NULL,
    `publisher_company` varchar(64) DEFAULT NULL,
    `address` varchar(64) DEFAULT NULL
)
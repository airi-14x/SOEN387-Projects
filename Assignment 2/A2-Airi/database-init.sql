CREATE TABLE `book`(
	`id` VARCHAR(11) NOT NULL,
    `title` VARCHAR(64) DEFAULT NULL,
    `description` VARCHAR(256) DEFAULT NULL,
    `isbn` VARCHAR(64) DEFAULT NULL,
    `last_name` VARCHAR(64) DEFAULT NULL,
    `first_name` VARCHAR(64) DEFAULT NULL,
    `publisher_company` VARCHAR(64) DEFAULT NULL,
    `address` VARCHAR(64) DEFAULT NULL,
    `image_mime` VARCHAR(256) DEFAULT NULL,
    `image_data` BLOB
);

CREATE PROCEDURE updateidCounter (
id VARCHAR(11)
)
BEGIN
	SELECT id, title FROM book
    WHERE this.id = id;
END;
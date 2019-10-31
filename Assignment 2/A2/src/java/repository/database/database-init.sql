CREATE TABLE `book`(
	`id` INT  NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(64) DEFAULT NULL,
    `description` VARCHAR(256) DEFAULT NULL,
    `isbn` VARCHAR(64) DEFAULT NULL,
    `last_name` VARCHAR(64) DEFAULT NULL,
    `first_name` VARCHAR(64) DEFAULT NULL,
    `publisher_company` VARCHAR(64) DEFAULT NULL,
    `address` VARCHAR(64) DEFAULT NULL,
    `image_mime` VARCHAR(256) DEFAULT NULL,
    `image_data` BLOB,
    PRIMARY KEY(`id`)
)AUTO_INCREMENT=1;

-- Implement AUTO_INCREMENT with PROCEDURE if time exists
DELIMITER //
CREATE PROCEDURE autoIncrementId (p_id INT, p_isbn VARCHAR(11))
BEGIN
	SELECT id, isbn
    FROM book
    WHERE id = p_id AND isbn = p_isbn;
END

CREATE PROCEDURE `new_procedure` ()
BEGIN
	SELECT id, isbn
    FROM book
END
DELIMITER ;


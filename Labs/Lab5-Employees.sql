CREATE TABLE `employees`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `last_name` varchar(64) DEFAULT NULL,
    `first_name` varchar(64) DEFAULT NULL,
    `email` varchar(64) DEFAULT NULL,
    `department` varchar(64) DEFAULT NULL,
    `salary` DECIMAL(10,2) DEFAULT NULL,
    PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET = latin1;

INSERT INTO `employees`(`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (1, "Mary", "Daks", "mary.daks@gmail.com", "Development", "54000");
INSERT INTO `employees`(`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (2, "Alan", "Tunner", "alan.tunner@gmail.com", "Research", "40000");
INSERT INTO `employees`(`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (3, "Mary", "Daks", "mary.daks@gmail.com", "Development", "54200");
INSERT INTO `employees`(`id`,`last_name`,`first_name`,`email`, `department`, `salary`) VALUES (4, "Alan", "Tunner", "alan.tunner@gmail.com", "Research", "43000");
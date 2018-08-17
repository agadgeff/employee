-- CREATE SCHEMA IF NOT EXISTS solsticeu
--   DEFAULT CHARACTER SET utf8
--   COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `employee_number` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `office` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_employee_number` (`employee_number`)
) CHARSET=utf8


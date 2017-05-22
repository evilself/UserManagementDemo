-- MySQL
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema westernacher
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `westernacher` DEFAULT CHARACTER SET utf8 ;
USE `westernacher` ;

-- -----------------------------------------------------
-- Table `westernacher`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `westernacher`.`users` ;

CREATE TABLE IF NOT EXISTS `westernacher`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'ID -  primary key',
  `first_name` VARCHAR(30) NOT NULL COMMENT 'First Name',
  `last_name` VARCHAR(30) NOT NULL COMMENT 'Last Name',
  `email` VARCHAR(100) NOT NULL COMMENT 'Email',
  `dob` DATE NOT NULL COMMENT 'Date of Birth',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `id_UNIQUE` (`user_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Holds users';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

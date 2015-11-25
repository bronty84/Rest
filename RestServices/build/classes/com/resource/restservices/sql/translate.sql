SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `translate` ;
CREATE SCHEMA IF NOT EXISTS `translate` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `translate` ;

-- -----------------------------------------------------
-- Table `translate`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `translate`.`user` ;

CREATE  TABLE IF NOT EXISTS `translate`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_language` VARCHAR(45) NOT NULL ,
  `gcm_token` VARCHAR(200) NULL ,
  `email` VARCHAR(100) NOT NULL ,
  `usename` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `telephone` INT UNSIGNED NULL ,
  `date_registration` DATETIME NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `translate`.`language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `translate`.`language` ;

CREATE  TABLE IF NOT EXISTS `translate`.`language` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `language` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `translate`.`user_has_language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `translate`.`user_has_language` ;

CREATE  TABLE IF NOT EXISTS `translate`.`user_has_language` (
  `user_id` INT NOT NULL ,
  `language_id` INT NOT NULL ,
  PRIMARY KEY (`user_id`, `language_id`) ,
  CONSTRAINT `fk_user_has_language_user`
    FOREIGN KEY (`user_id` )
    REFERENCES `translate`.`user` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_language_language1`
    FOREIGN KEY (`language_id` )
    REFERENCES `translate`.`language` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_user_has_language_language1_idx` ON `translate`.`user_has_language` (`language_id` ASC) ;

CREATE INDEX `fk_user_has_language_user_idx` ON `translate`.`user_has_language` (`user_id` ASC) ;

USE `translate` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

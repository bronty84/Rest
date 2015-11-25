SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `translate`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `translate`.`user` ;

CREATE  TABLE IF NOT EXISTS `translate`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `id_language` BIGINT NOT NULL ,
  `gcm_token` VARCHAR(200) NULL ,
  `email` VARCHAR(100) NOT NULL ,
  `username` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NOT NULL ,
  `telephone` BIGINT UNSIGNED NOT NULL ,
  `date_registration` DATE NULL ,
  PRIMARY KEY (`id`, `email`, `username`) )
ENGINE = InnoDB;

CREATE UNIQUE INDEX `username_UNIQUE` ON `translate`.`user` (`username` ASC) ;

CREATE UNIQUE INDEX `email_UNIQUE` ON `translate`.`user` (`email` ASC) ;


-- -----------------------------------------------------
-- Table `translate`.`language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `translate`.`language` ;

CREATE  TABLE IF NOT EXISTS `translate`.`language` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `language` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `translate`.`user_has_language`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `translate`.`user_has_language` ;

CREATE  TABLE IF NOT EXISTS `translate`.`user_has_language` (
  `user_id` BIGINT NOT NULL ,
  `language_id` BIGINT NOT NULL ,
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



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

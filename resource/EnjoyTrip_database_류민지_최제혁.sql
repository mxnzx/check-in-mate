-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `enjoytrip` ;

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `enjoytrip` ;

-- -----------------------------------------------------
-- Table `enjoytrip`.`sido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`gugun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`gugun` (
  `gugun_code` INT NOT NULL,
  `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `gugun_to_sido_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_info` (
  `content_id` INT NOT NULL,
  `content_type_id` INT NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `addr1` VARCHAR(100) NULL DEFAULT NULL,
  `addr2` VARCHAR(50) NULL DEFAULT NULL,
  `zipcode` VARCHAR(50) NULL DEFAULT NULL,
  `tel` VARCHAR(50) NULL DEFAULT NULL,
  `first_image` VARCHAR(200) NULL DEFAULT NULL,
  `first_image2` VARCHAR(200) NULL DEFAULT NULL,
  `readcount` INT NULL DEFAULT NULL,
  `sido_code` INT NULL DEFAULT NULL,
  `gugun_code` INT NULL DEFAULT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `mlevel` VARCHAR(2) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `attraction_to_content_type_id_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `enjoytrip`.`content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `enjoytrip`.`gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_description` (
  `content_id` INT NOT NULL,
  `homepage` VARCHAR(100) NULL DEFAULT NULL,
  `overview` VARCHAR(10000) NULL DEFAULT NULL,
  `telname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`member` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(16) NOT NULL,
  `email_id` VARCHAR(20) NULL DEFAULT NULL,
  `email_domain` VARCHAR(45) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `board_user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT '0',
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `board_to_members_user_id_fk` (`board_user_id` ASC) VISIBLE,
  CONSTRAINT `board_to_members_user_id_fk`
    FOREIGN KEY (`board_user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`board_comment` (
  `user_id` VARCHAR(16) NOT NULL,
  `article_no` INT NULL,
  `comment` VARCHAR(100) NULL,
  `parent_id` VARCHAR(16) NULL,
  PRIMARY KEY (`user_id`),
  INDEX `comments_to_board_article_no_fk_idx` (`article_no` ASC) VISIBLE,
  CONSTRAINT `comments_to_members_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comments_to_board_article_no_fk`
    FOREIGN KEY (`article_no`)
    REFERENCES `enjoytrip`.`board` (`article_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trip_plan_board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`trip_plan_board` (
  `trip_plan_id` INT NOT NULL AUTO_INCREMENT,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `trip_plan_title` VARCHAR(30) NOT NULL,
  `trip_plan_start_date` DATETIME NOT NULL,
  `trip_plan_end_date` DATETIME NOT NULL,
  `trip_plan_detail` VARCHAR(100) NOT NULL,
  `user_id` VARCHAR(16) NULL,
  PRIMARY KEY (`trip_plan_id`),
  INDEX `trip_plan_to_members_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `trip_plan_to_members_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`photo_spot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`photo_spot` (
  `photo_spot_id` INT NOT NULL AUTO_INCREMENT,
  `photo_spot_title` VARCHAR(30) NOT NULL,
  `photo_spot_hit` INT NULL,
  `photo_spot_addr` VARCHAR(100) NOT NULL,
  `photo_spot_detail` VARCHAR(100) NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` VARCHAR(16) NULL,
  `sido_code` INT NULL,
  `gugun_code` INT NULL,
  `latitude` DECIMAL(20,17) NULL,
  `longtitude` DECIMAL(20,17) NULL,
  `photo_spotcol` VARCHAR(45) NULL,
  `image_big` VARCHAR(200) NULL,
  `image_small` VARCHAR(200) NULL,
  `mlevel` VARCHAR(2) NULL,
  INDEX `photo_spot_to_gugun_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
  INDEX `photo_spot_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `photo_spot_to_members_user_id_fk_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`photo_spot_id`),
  CONSTRAINT `photo_spot_to_members_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `photo_spot_to_sido_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip`.`sido` (`sido_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `photo_spot_to_gugun_gugun_code_fk`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `enjoytrip`.`gugun` (`gugun_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trip_plan_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`trip_plan_detail` (
  `trip_plan_id` INT NOT NULL,
  `trip_no` VARCHAR(45) NOT NULL,
  `content_id` INT NOT NULL,
  `comment` VARCHAR(45) NULL,
  `seq` INT NULL,
  `trip_plan_start_date` DATETIME NULL,
  `trip_plan_end_date` DATETIME NULL,
  PRIMARY KEY (`trip_plan_id`, `trip_no`, `content_id`),
  CONSTRAINT `trip_plan_detail_to_trip_plan_board_trip_plan_id_fk`
    FOREIGN KEY (`trip_plan_id`)
    REFERENCES `enjoytrip`.`trip_plan_board` (`trip_plan_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `trip_plan_detail_to_attraction_info_content_id_fk`
    FOREIGN KEY (`trip_plan_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`notice` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `notice_user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT '0',
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `board_to_members_user_id_fk` (`notice_user_id` ASC) VISIBLE,
  CONSTRAINT `board_to_members_user_id_fk0`
    FOREIGN KEY (`notice_user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


-- member 테이블에 admin, ssafy12 계정 추가해놓기
INSERT INTO member (user_id, user_name, user_password, email_id, email_domain, join_date)
VALUES 
('admin', '관리자', '1234', 'admin','ssafy.com',now()),
('ssafy12', '김싸피', '1234', 'ssafy','ssafy.com', now());


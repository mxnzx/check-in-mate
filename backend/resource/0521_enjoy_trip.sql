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
DROP TABLE IF EXISTS `enjoytrip`.`sido` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`gugun`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`gugun` ;

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
DROP TABLE IF EXISTS `enjoytrip`.`attraction_info` ;

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
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
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
DROP TABLE IF EXISTS `enjoytrip`.`attraction_description` ;

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
DROP TABLE IF EXISTS `enjoytrip`.`member` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`member` (
  `user_id` VARCHAR(16) NOT NULL,
  `user_name` VARCHAR(20) NOT NULL,
  `user_password` VARCHAR(16) NOT NULL,
  `email_id` VARCHAR(20) NULL DEFAULT NULL,
  `email_domain` VARCHAR(45) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `token` VARCHAR(1000) NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`join_mate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`join_mate` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`join_mate` (
  `join_mate_article_no` INT NOT NULL AUTO_INCREMENT,
  `join_mate_user_id` VARCHAR(16) NULL DEFAULT NULL,
  `join_mate_title` VARCHAR(100) NULL DEFAULT NULL,
  `join_mate_content` VARCHAR(2000) NULL DEFAULT NULL,
  `join_mate_hit` INT NULL DEFAULT '0',
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `join_mate_category` VARCHAR(45) NULL,
  `join_mate_place` VARCHAR(45) NULL,
  `join_mate_date` VARCHAR(100) NULL,
  `join_mate_people_num` INT NULL,
  PRIMARY KEY (`join_mate_article_no`),
  INDEX `board_to_members_user_id_fk` (`join_mate_user_id` ASC) VISIBLE,
  CONSTRAINT `board_to_members_user_id_fk`
    FOREIGN KEY (`join_mate_user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`join_mate_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`join_mate_comment` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`join_mate_comment` (
  `comment_user_id` VARCHAR(16) NOT NULL,
  `comment_article_no` INT NULL DEFAULT NULL,
  `comment_content` VARCHAR(100) NULL DEFAULT NULL,
  `parent_id` VARCHAR(16) NULL DEFAULT NULL,
  `comment_no` INT NOT NULL AUTO_INCREMENT,
  `register_time` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`comment_no`),
  INDEX `comments_to_board_article_no_fk_idx` (`comment_article_no` ASC) INVISIBLE,
  INDEX `comments_to_members_user_id_fk` (`comment_user_id` ASC) VISIBLE,
  CONSTRAINT `comments_to_board_article_no_fk`
    FOREIGN KEY (`comment_article_no`)
    REFERENCES `enjoytrip`.`join_mate` (`join_mate_article_no`),
  CONSTRAINT `comments_to_members_user_id_fk`
    FOREIGN KEY (`comment_user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`notice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`notice` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`notice` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `notice_user_id` VARCHAR(16) NULL DEFAULT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT '0',
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `board_to_members_user_id_fk` (`notice_user_id` ASC) VISIBLE,
  CONSTRAINT `notice_to_member_user_id_fk0`
    FOREIGN KEY (`notice_user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`hotplace`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`hotplace` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`hotplace` (
  `hotplace_article_no` INT NOT NULL AUTO_INCREMENT,
  `hotplace_title` VARCHAR(30) NOT NULL,
  `hotplace_hit` INT NULL DEFAULT NULL,
  `hotplace_content` VARCHAR(1000) NULL DEFAULT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `hotplace_user_id` VARCHAR(16) NULL DEFAULT NULL,
  `hotplace_img_big` VARCHAR(200) NULL DEFAULT NULL,
  `hotplace_img_small` VARCHAR(200) NULL,
  PRIMARY KEY (`hotplace_article_no`),
  INDEX `hotplace_to_members_user_id_fk_idx` (`hotplace_user_id` ASC) VISIBLE,
  CONSTRAINT `hotplace_to_members_user_id_fk`
    FOREIGN KEY (`hotplace_user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci
ROW_FORMAT = DEFAULT;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trip_plan_board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`trip_plan_board` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`trip_plan_board` (
  `trip_plan_article_no` INT NOT NULL AUTO_INCREMENT,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `trip_plan_title` VARCHAR(30) NOT NULL,
  `trip_plan_user_id` VARCHAR(16) NULL DEFAULT NULL,
  `trip_plan_hit` INT NULL,
  `trip_plan_img` VARCHAR(200) NULL,
  PRIMARY KEY (`trip_plan_article_no`),
  INDEX `trip_plan_to_members_user_id_fk_idx` (`trip_plan_user_id` ASC) VISIBLE,
  CONSTRAINT `trip_plan_to_members_user_id_fk`
    FOREIGN KEY (`trip_plan_user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trip_plan_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`trip_plan_detail` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`trip_plan_detail` (
  `trip_plan_article_no` INT NOT NULL,
  `trip_plan_day` INT NOT NULL,
  `trip_plan_content_id` INT NOT NULL,
  `seq` INT NULL DEFAULT NULL,
  PRIMARY KEY (`trip_plan_article_no`, `trip_plan_day`, `trip_plan_content_id`),
  CONSTRAINT `trip_plan_detail_to_attraction_info_content_id_fk`
    FOREIGN KEY (`trip_plan_article_no`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`),
  CONSTRAINT `trip_plan_detail_to_trip_plan_board_trip_plan_id_fk`
    FOREIGN KEY (`trip_plan_article_no`)
    REFERENCES `enjoytrip`.`trip_plan_board` (`trip_plan_article_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`scrap_trip_plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`scrap_trip_plan` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`scrap_trip_plan` (
  `user_id` VARCHAR(16) NOT NULL,
  `trip_plan_article_no` INT NOT NULL,
  PRIMARY KEY (`user_id`, `trip_plan_article_no`),
  INDEX `photo_spot_to_scrap_photo_spot_photo_spot_id_fk_idx` (`trip_plan_article_no` ASC) VISIBLE,
  CONSTRAINT `member_to_scrap_photo_spot_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `enjoytrip`.`member` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `photo_spot_to_scrap_photo_spot_photo_spot_id_fk`
    FOREIGN KEY (`trip_plan_article_no`)
    REFERENCES `enjoytrip`.`trip_plan_board` (`trip_plan_article_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`trip_plan_by_day`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`trip_plan_by_day` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`trip_plan_by_day` (
  `trip_plan_article_no` INT NOT NULL,
  `trip_plan_day` INT NOT NULL,
  `trip_plan_content` VARCHAR(300) NULL,
  PRIMARY KEY (`trip_plan_article_no`, `trip_plan_day`),
  CONSTRAINT `trip_plan_detail_to_trip_plan_by_day_fk`
    FOREIGN KEY (`trip_plan_article_no` , `trip_plan_day`)
    REFERENCES `enjoytrip`.`trip_plan_detail` (`trip_plan_article_no` , `trip_plan_day`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `enjoytrip`.`hotplace_file_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`hotplace_file_info` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`hotplace_file_info` (
  `hotplace_file_idx` INT NOT NULL AUTO_INCREMENT,
  `hotplace_file_article_no` INT NULL,
  `hotplace_file_save_folder` VARCHAR(500) NULL,
  `hotplace_file_original_file` VARCHAR(500) NULL,
  `hotplace_file_save_file` VARCHAR(500) NULL,
  PRIMARY KEY (`hotplace_file_idx`),
  INDEX `hotplace_file_info_to_hotplace_hotplace_article_no_fk_idx` (`hotplace_file_article_no` ASC) VISIBLE,
  CONSTRAINT `hotplace_file_info_to_hotplace_hotplace_article_no_fk`
    FOREIGN KEY (`hotplace_file_article_no`)
    REFERENCES `enjoytrip`.`hotplace` (`hotplace_article_no`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

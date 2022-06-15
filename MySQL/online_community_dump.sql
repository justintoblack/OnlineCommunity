CREATE DATABASE IF NOT EXISTS online_community;
USE online_community;

CREATE TABLE IF NOT EXISTS `user`
(
    `uid`      INT UNSIGNED                NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(30) CHARSET utf8mb4 NOT NULL UNIQUE,
    `password` VARCHAR(20)                 NOT NULL,
    `phone`    VARCHAR(30),
    `email`    VARCHAR(50)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `following`
(
    `uid`           INT UNSIGNED,
    `following_uid` INT UNSIGNED,
    primary key (`uid`, `following_uid`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `moment`
(
    `moment_id`     INT UNSIGNED                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `uid`           INT UNSIGNED                 NOT NULL,
    `content`       varchar(255) CHARSET utf8mb4 NOT NULL,
    `moment_time`   DATETIME                     NOT NULL DEFAULT NOW(),
    `is_active`     tinyint,
    `deleted_at`    DATETIME,
    `picture_count` INT,
    `like_count`    INT,
    `comment_count` INT,
    `repost_count`  INT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
ALTER TABLE `moment`
    ADD CONSTRAINT fk_moment_user FOREIGN KEY (`uid`) REFERENCES `user` (uid);

CREATE TABLE IF NOT EXISTS `picture`
(
    `moment_id` INT UNSIGNED NOT NULL,
    `idx`       INT          NOT NULL,
    `url`       VARCHAR(255),
    PRIMARY KEY (`moment_id`, `idx`),
    CONSTRAINT `fk_picture_moment` FOREIGN KEY (`moment_id`) REFERENCES `moment` (moment_id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `comment`
(
    `comment_id`   INT UNSIGNED                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `content`      varchar(255) CHARSET utf8mb4 NOT NULL,
    `moment_id`    INT UNSIGNED                 NOT NULL,
    `comment_uid`  INT UNSIGNED                 NOT NULL,
    `moment_uid`   INT UNSIGNED                 NOT NULL,
    `like_count`   INT UNSIGNED                 NOT NULL,
    `comment_time` DATETIME                     NOT NULL DEFAULT NOW()
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
ALTER TABLE `comment`
    ADD CONSTRAINT fk_comment_moment FOREIGN KEY (`moment_id`) REFERENCES `moment` (`moment_id`);
ALTER TABLE `comment`
    ADD CONSTRAINT fk_comment_user FOREIGN KEY (`comment_uid`) REFERENCES `user` (`uid`);

CREATE TABLE IF NOT EXISTS `like`
(
    `like_id`    INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `moment_id`  INT UNSIGNED NOT NULL,
    `like_uid`   INT UNSIGNED NOT NULL,
    `moment_uid` INT UNSIGNED NOT NULL,
    `like_time`  DATETIME     NOT NULL DEFAULT NOW()
) ENGINE = InnoDB;
ALTER TABLE `like`
    ADD CONSTRAINT fk_like_moment FOREIGN KEY (`moment_id`) REFERENCES `moment` (`moment_id`);
ALTER TABLE `like`
    ADD CONSTRAINT fk_like_user FOREIGN KEY (`like_uid`) REFERENCES `user` (`uid`);

CREATE TABLE IF NOT EXISTS `repost`
(
    `repost_id`   INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `moment_id`   INT UNSIGNED NOT NULL,
    `repost_uid`  INT UNSIGNED NOT NULL,
    `moment_uid`  INT UNSIGNED NOT NULL,
    `repost_time` DATETIME     NOT NULL DEFAULT NOW()
) ENGINE = InnoDB;
ALTER TABLE `repost`
    ADD CONSTRAINT fk_repost_moment FOREIGN KEY (`moment_id`) REFERENCES `moment` (`moment_id`);
ALTER TABLE `repost`
    ADD CONSTRAINT fk_repost_user FOREIGN KEY (`repost_uid`) REFERENCES `user` (`uid`);

CREATE TABLE IF NOT EXISTS `star`
(
    `star_id`   INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `moment_id` INT UNSIGNED NOT NULL,
    `star_uid`  INT UNSIGNED NOT NULL,
    `moment_uid`   INT UNSIGNED  NOT NULL,
    `star_time` DATETIME     NOT NULL DEFAULT NOW()
) ENGINE = InnoDB;
ALTER TABLE `star`
    ADD CONSTRAINT fk_star_moment FOREIGN KEY (`moment_id`) REFERENCES `moment` (`moment_id`);
ALTER TABLE `star`
    ADD CONSTRAINT fk_star_user FOREIGN KEY (`star_uid`) REFERENCES `user` (`uid`);

CREATE TABLE IF NOT EXISTS `user_info`
(
    `uid`          INT UNSIGNED                NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username`     VARCHAR(30) CHARSET utf8mb4 NOT NULL,
    `real_name`    VARCHAR(30) CHARSET utf8mb4,
    `avatar_url`   VARCHAR(255),
    `about`        VARCHAR(255) CHARSET utf8mb4,
    `phone`        VARCHAR(30),
    `email`        VARCHAR(50),
    `birthday`     DATE,
    `is_following` Boolean,
    `followings`    INT,
    `followers`    INT,
    `moment_count` INT,
    `like_count`   INT,
    `star_count`   INT
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
ALTER TABLE `user_info`
    ADD CONSTRAINT fk_userinfo_user FOREIGN KEY (`uid`) REFERENCES `user` (`uid`);

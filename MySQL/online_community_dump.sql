CREATE DATABASE IF NOT EXISTS online_community;
USE online_community;

CREATE TABLE IF NOT EXISTS `user`
(
    `uid`      INT UNSIGNED                NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(30) CHARSET utf8mb4 NOT NULL UNIQUE,
    `password` VARCHAR(20)                 NOT NULL
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `moment`
(
    `mid`           INT UNSIGNED                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `uid`           INT UNSIGNED                 NOT NULL,
    `mcontent`      varchar(255) CHARSET utf8mb4 NOT NULL,
    `mpicurl`       varchar(255),
    `mtime`         DATETIME                     NOT NULL,
    `mlikecount`    int,
    `mcommentcount` int,
    `mrepostcount`  int
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
ALTER TABLE `moment`
    ADD CONSTRAINT fk_moment_user FOREIGN KEY (`uid`) REFERENCES `user` (uid);

CREATE TABLE IF NOT EXISTS `comment`
(
    `cid`        INT UNSIGNED                 NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `ccontent`   varchar(255) CHARSET utf8mb4 NOT NULL,
    `mid`        INT UNSIGNED                 NOT NULL,
    `cuid`       INT UNSIGNED                 NOT NULL,
    `muid`       INT UNSIGNED                 NOT NULL,
    `clikecount` INT UNSIGNED                 NOT NULL,
    `ctime`      DATETIME                     NOT NULL DEFAULT NOW()
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
ALTER TABLE `comment`
    ADD CONSTRAINT fk_comment_moment FOREIGN KEY (`mid`) REFERENCES `moment` (`mid`);
ALTER TABLE `comment`
    ADD CONSTRAINT fk_comment_user FOREIGN KEY (`cuid`) REFERENCES `user` (`uid`);

CREATE TABLE IF NOT EXISTS `like`
(
    `lid`  INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `mid`  INT UNSIGNED NOT NULL,
    `luid` INT UNSIGNED NOT NULL,
    `muid` INT UNSIGNED NOT NULL
) ENGINE = InnoDB;
ALTER TABLE `like`
    ADD CONSTRAINT fk_like_moment FOREIGN KEY (`mid`) REFERENCES `moment` (`mid`);
ALTER TABLE `like`
    ADD CONSTRAINT fk_like_user FOREIGN KEY (`luid`) REFERENCES `user` (`uid`);

CREATE TABLE IF NOT EXISTS `repost`
(
    `rid`   INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `mid`   INT UNSIGNED NOT NULL,
    `ruid`  INT UNSIGNED NOT NULL,
    `muid`  INT UNSIGNED NOT NULL,
    `rtime` DATETIME     NOT NULL DEFAULT NOW()
) ENGINE = InnoDB;
ALTER TABLE `repost`
    ADD CONSTRAINT fk_repost_moment FOREIGN KEY (`mid`) REFERENCES `moment` (`mid`);
ALTER TABLE `repost`
    ADD CONSTRAINT fk_repost_user FOREIGN KEY (`ruid`) REFERENCES `user` (`uid`);

CREATE TABLE IF NOT EXISTS `star`
(
    `sid`   INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `mid`   INT UNSIGNED NOT NULL,
    `suid`  INT UNSIGNED NOT NULL,
    `stime` DATETIME     NOT NULL DEFAULT NOW()
) ENGINE = InnoDB;
ALTER TABLE `star`
    ADD CONSTRAINT fk_star_moment FOREIGN KEY (`mid`) REFERENCES `moment` (`mid`);
ALTER TABLE `star`
    ADD CONSTRAINT fk_star_user FOREIGN KEY (`suid`) REFERENCES `user` (`uid`);

CREATE TABLE IF NOT EXISTS `user_info`
(
    `uid`          INT UNSIGNED                NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username`     VARCHAR(30) CHARSET utf8mb4 NOT NULL,
    `urealname`    VARCHAR(30) CHARSET utf8mb4,
    `uavatarurl`   VARCHAR(255),
    `uabout`       VARCHAR(255) CHARSET utf8mb4,
    `ufollowing`   int,
    `ufollowers`   int,
    `umomentcount` int,
    `ulikecount`   int,
    `ustarcount`   int,
    `ubirthday`    DATE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
ALTER TABLE `user_info`
    ADD CONSTRAINT fk_userinfo_user FOREIGN KEY (`uid`) REFERENCES `user` (`uid`);

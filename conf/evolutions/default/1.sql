-- !Ups

CREATE TABLE `USER` (
    `USER_ID` INTEGER PRIMARY KEY AUTOINCREMENT,
    `UUID` CHAR(36) UNIQUE NOT NULL,
    `USERNAME` VARCHAR(255) UNIQUE NOT NULL,
    `PASSWORD` VARCHAR(255) NOT NULL
);

-- !Downs

DROP TABLE `USER`;
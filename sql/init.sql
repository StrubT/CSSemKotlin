-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2015 at 01:14 PM
-- Server version: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `addressbook`
--
CREATE DATABASE IF NOT EXISTS `addressbook`
	DEFAULT CHARACTER SET utf8
	COLLATE utf8_general_ci;

GRANT USAGE ON *.* TO 'addressbook'@'localhost'
IDENTIFIED BY PASSWORD '*73179D4216FF30F5D51CCC62D042CA4210113481';

GRANT ALL PRIVILEGES ON `addressbook`.* TO 'addressbook'@'localhost';

USE `addressbook`;

-- --------------------------------------------------------

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
CREATE TABLE IF NOT EXISTS `cities` (
	`id`         INT(11)     NOT NULL,
	`name`       VARCHAR(50) NOT NULL,
	`postalcode` VARCHAR(10) NOT NULL,
	`state`      INT(11) DEFAULT NULL,
	`country`    INT(11)     NOT NULL
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
CREATE TABLE IF NOT EXISTS `countries` (
	`id`           INT(11)     NOT NULL,
	`abbreviation` VARCHAR(10) NOT NULL,
	`name`         VARCHAR(50) NOT NULL
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
CREATE TABLE IF NOT EXISTS `people` (
	`id`          INT(11)      NOT NULL,
	`lastname`    VARCHAR(250) NOT NULL,
	`firstname`   VARCHAR(250) NOT NULL,
	`addrstreet`  VARCHAR(250) DEFAULT NULL,
	`addrcity`    INT(11)      DEFAULT NULL,
	`emailhome`   VARCHAR(75)  DEFAULT NULL,
	`emailwork`   VARCHAR(75)  DEFAULT NULL,
	`phonehome`   VARCHAR(25)  DEFAULT NULL,
	`phonemobile` VARCHAR(25)  DEFAULT NULL,
	`phonework`   VARCHAR(25)  DEFAULT NULL
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8;

-- --------------------------------------------------------

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
CREATE TABLE IF NOT EXISTS `states` (
	`id`           INT(11)     NOT NULL,
	`abbreviation` VARCHAR(10) NOT NULL,
	`name`         VARCHAR(50) NOT NULL,
	`country`      INT(11)     NOT NULL
)
	ENGINE = InnoDB
	DEFAULT CHARSET = utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cities`
--
ALTER TABLE `cities`
ADD PRIMARY KEY (`id`), ADD KEY `state` (`state`, `country`), ADD KEY `country` (`country`);

--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
ADD PRIMARY KEY (`id`);

--
-- Indexes for table `people`
--
ALTER TABLE `people`
ADD PRIMARY KEY (`id`), ADD KEY `addrcity` (`addrcity`);

--
-- Indexes for table `states`
--
ALTER TABLE `states`
ADD PRIMARY KEY (`id`), ADD KEY `country` (`country`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cities`
--
ALTER TABLE `cities`
MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `people`
--
ALTER TABLE `people`
MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `states`
--
ALTER TABLE `states`
MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `cities`
--
ALTER TABLE `cities`
ADD CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`state`) REFERENCES `states` (`id`),
ADD CONSTRAINT `cities_ibfk_2` FOREIGN KEY (`country`) REFERENCES `countries` (`id`);

--
-- Constraints for table `people`
--
ALTER TABLE `people`
ADD CONSTRAINT `people_ibfk_1` FOREIGN KEY (`addrcity`) REFERENCES `cities` (`id`);

--
-- Constraints for table `states`
--
ALTER TABLE `states`
ADD CONSTRAINT `states_ibfk_1` FOREIGN KEY (`country`) REFERENCES `countries` (`id`);

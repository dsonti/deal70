-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 01, 2016 at 04:13 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `deal70`
--

-- --------------------------------------------------------

--
-- Table structure for table `CATEGORY_DATA`
--

CREATE TABLE `CATEGORY_DATA` (
  `ID` int(10) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `PARENT_ID` int(10) DEFAULT NULL,
  `DESCRIPTION` varchar(1024) DEFAULT NULL,
  `IMG_NAME` varchar(255) DEFAULT NULL,
  `VIEW_COUNT` int(10) NOT NULL DEFAULT '0',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CATEGORY_DATA`
--

INSERT INTO `CATEGORY_DATA` (`ID`, `NAME`, `PARENT_ID`, `DESCRIPTION`, `IMG_NAME`, `VIEW_COUNT`, `CREATE_DATE`, `UPDATE_DATE`, `CREATED_BY`) VALUES
(8, 'Automotive', NULL, NULL, NULL, 0, '2015-12-30 16:53:02', '2015-12-30 16:53:02', 'itsras@gmail.com'),
(9, 'Auto Parts', 8, NULL, NULL, 0, '2015-12-30 16:54:47', '2015-12-30 16:54:47', 'itsras@gmail.com'),
(10, 'Vehicles', 8, NULL, NULL, 0, '2015-12-30 16:54:47', '2015-12-30 16:54:47', 'itsras@gmail.com'),
(11, 'Truck', 8, NULL, NULL, 0, '2015-12-30 16:54:47', '2015-12-30 16:54:47', 'itsras@gmail.com'),
(12, 'Car Repair', 8, NULL, NULL, 0, '2015-12-30 16:54:47', '2015-12-30 16:54:47', 'itsras@gmail.com'),
(13, 'Accessories', NULL, NULL, NULL, 0, '2015-12-30 16:55:36', '2015-12-30 16:55:36', 'itsras@gmail.com'),
(14, 'Bags', 13, NULL, NULL, 0, '2015-12-30 16:56:41', '2015-12-30 16:56:41', 'itsras@gmail.com'),
(15, 'Belt', 13, NULL, NULL, 0, '2015-12-30 16:56:41', '2015-12-30 16:56:41', 'itsras@gmail.com'),
(16, 'Clothing', 13, NULL, NULL, 0, '2015-12-30 16:56:41', '2015-12-30 16:56:41', 'itsras@gmail.com'),
(17, 'Watches', 13, NULL, NULL, 0, '2015-12-30 16:56:41', '2015-12-30 16:56:41', 'itsras@gmail.com'),
(18, 'Books', NULL, NULL, NULL, 0, '2015-12-30 16:57:20', '2015-12-30 16:57:20', 'itsras@gmail.com'),
(19, 'Art Books', 18, NULL, NULL, 0, '2015-12-30 16:58:24', '2015-12-30 16:58:24', 'itsras@gmail.com'),
(20, 'Audiobooks', 18, NULL, NULL, 0, '2015-12-30 16:58:24', '2015-12-30 16:58:24', 'itsras@gmail.com'),
(21, 'Comic Books', 18, NULL, NULL, 0, '2015-12-30 16:58:24', '2015-12-30 16:58:24', 'itsras@gmail.com'),
(22, 'E Books', 18, NULL, NULL, 0, '2015-12-30 16:58:24', '2015-12-30 16:58:24', 'itsras@gmail.com'),
(23, 'Electronics', NULL, NULL, NULL, 0, '2015-12-30 16:58:58', '2015-12-30 16:58:58', 'itsras@gmail.com'),
(24, 'Cameras', 23, NULL, NULL, 0, '2015-12-30 17:00:06', '2015-12-30 17:00:06', 'itsras@gmail.com'),
(25, 'Computers', 23, NULL, NULL, 0, '2015-12-30 17:00:06', '2015-12-30 17:00:06', 'itsras@gmail.com'),
(26, 'Gadgets', 23, NULL, NULL, 0, '2015-12-30 17:00:06', '2015-12-30 17:00:06', 'itsras@gmail.com'),
(27, 'Printers', 23, NULL, NULL, 0, '2015-12-30 17:00:06', '2015-12-30 17:00:06', 'itsras@gmail.com'),
(28, 'Food', NULL, NULL, NULL, 0, '2015-12-30 17:00:37', '2015-12-30 17:00:37', 'itsras@gmail.com'),
(29, 'Backery', 28, NULL, NULL, 0, '2015-12-30 17:01:36', '2015-12-30 17:01:36', 'itsras@gmail.com'),
(30, 'Food Delivery', 28, NULL, NULL, 0, '2015-12-30 17:01:36', '2015-12-30 17:01:36', 'itsras@gmail.com'),
(31, 'Coffee', 28, NULL, NULL, 0, '2015-12-30 17:01:36', '2015-12-30 17:01:36', 'itsras@gmail.com'),
(32, 'Restaurants', 28, NULL, NULL, 0, '2015-12-30 17:01:36', '2015-12-30 17:01:36', 'itsras@gmail.com'),
(33, 'Home and Garden', NULL, NULL, NULL, 0, '2015-12-30 17:02:06', '2015-12-30 17:02:06', 'itsras@gmail.com'),
(34, 'Appliances', 33, NULL, NULL, 0, '2015-12-30 17:03:27', '2015-12-30 17:03:27', 'itsras@gmail.com'),
(35, 'Bedding', 33, NULL, NULL, 0, '2015-12-30 17:03:27', '2015-12-30 17:03:27', 'itsras@gmail.com'),
(36, 'Construction', 33, NULL, NULL, 0, '2015-12-30 17:03:27', '2015-12-30 17:03:27', 'itsras@gmail.com'),
(37, 'Gardening', 33, NULL, NULL, 0, '2015-12-30 17:03:27', '2015-12-30 17:03:27', 'itsras@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `DEAL_DATA`
--

CREATE TABLE `DEAL_DATA` (
  `ID` int(10) NOT NULL,
  `STORE_ID` int(10) NOT NULL,
  `CATEGORY_ID` int(10) NOT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) NOT NULL,
  `DESCRIPTION1` varchar(255) NOT NULL,
  `DESCRIPTION2` varchar(1024) NOT NULL,
  `DEAL_CODE` varchar(255) DEFAULT NULL,
  `DEAL_TYPE` varchar(255) NOT NULL,
  `STEPS_TO_CONSUME` varchar(2048) NOT NULL,
  `VIEW_COUNT` int(10) NOT NULL DEFAULT '0',
  `IS_ACTIVE` tinyint(1) NOT NULL,
  `IMG_NAME` varchar(255) DEFAULT NULL,
  `DEAL_URL` varchar(512) NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `EXPIRY_DATE` timestamp(6) NULL DEFAULT NULL,
  `UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(255) NOT NULL,
  `PRICE` varchar(255) DEFAULT NULL,
  `DISCOUNT` varchar(255) DEFAULT NULL,
  `DISCOUNTED_PRICE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DEAL_DATA`
--

INSERT INTO `DEAL_DATA` (`ID`, `STORE_ID`, `CATEGORY_ID`, `LOCATION`, `TITLE`, `DESCRIPTION1`, `DESCRIPTION2`, `DEAL_CODE`, `DEAL_TYPE`, `STEPS_TO_CONSUME`, `VIEW_COUNT`, `IS_ACTIVE`, `IMG_NAME`, `DEAL_URL`, `CREATE_DATE`, `EXPIRY_DATE`, `UPDATE_DATE`, `CREATED_BY`, `PRICE`, `DISCOUNT`, `DISCOUNTED_PRICE`) VALUES
(2, 1, 11, 'Hyderabad', 'Up To 40% - 70% Off January Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.filpkart.com', '2016-01-01 03:11:29', '2015-12-30 18:30:00.000000', '2016-01-01 03:11:29', 'itsras@gmail.com', NULL, NULL, NULL),
(3, 2, 11, 'Mumbai', 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.amazon.in', '2016-01-01 03:12:04', '2015-12-30 18:30:00.000000', '2016-01-01 03:12:04', 'itsras@gmail.com', NULL, NULL, NULL),
(4, 3, 11, 'Hyderabad', 'Up To 35% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'HUIKDFGS', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.paytm.com', '2016-01-01 03:11:36', '2015-12-30 18:30:00.000000', '2016-01-01 03:11:36', 'itsras@gmail.com', NULL, NULL, NULL),
(5, 4, 11, 'Mumbai', 'Up To 42% Off April Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'HUIKDFGS', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.ebay.in', '2016-01-01 03:12:10', '2015-12-30 18:30:00.000000', '2016-01-01 03:12:10', 'itsras@gmail.com', NULL, NULL, NULL),
(6, 1, 20, 'Hyderabad', 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.filpkart.com', '2016-01-01 03:11:42', '2015-12-30 18:30:00.000000', '2016-01-01 03:11:42', 'itsras@gmail.com', NULL, NULL, NULL),
(7, 2, 20, 'Mumbai', 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.amazon.in', '2016-01-01 03:12:16', '2015-12-30 18:30:00.000000', '2016-01-01 03:12:16', 'itsras@gmail.com', NULL, NULL, NULL),
(8, 3, 20, 'Hyderabad', 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.paytm.com', '2016-01-01 03:11:50', '2015-12-30 18:30:00.000000', '2016-01-01 03:11:50', 'itsras@gmail.com', NULL, NULL, NULL),
(9, 4, 20, 'Mumbai', 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.ebay.in', '2016-01-01 03:12:23', '2015-12-30 18:30:00.000000', '2016-01-01 03:12:23', 'itsras@gmail.com', NULL, NULL, NULL),
(10, 1, 25, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', NULL, 'DEAL', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.filpkart.com', '2016-01-01 00:04:38', '2015-12-30 18:30:00.000000', '2016-01-01 00:04:38', 'itsras@gmail.com', NULL, NULL, NULL),
(11, 2, 25, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', NULL, 'DEAL', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.amazon.in', '2016-01-01 00:05:32', '2015-12-30 18:30:00.000000', '2016-01-01 00:05:32', 'itsras@gmail.com', NULL, NULL, NULL),
(12, 3, 25, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', NULL, 'DEAL', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.paytm.com', '2016-01-01 00:05:53', '2015-12-30 18:30:00.000000', '2016-01-01 00:05:53', 'itsras@gmail.com', NULL, NULL, NULL),
(13, 4, 25, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', NULL, 'DEAL', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, 'http://www.ebay.in', '2016-01-01 00:06:10', '2015-12-30 18:30:00.000000', '2016-01-01 00:06:10', 'itsras@gmail.com', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Stand-in structure for view `deal_data_vw`
--
CREATE TABLE `deal_data_vw` (
`ID` int(10)
,`STORE_ID` int(10)
,`CATEGORY_ID` int(10)
,`LOCATION` varchar(255)
,`TITLE` varchar(255)
,`DESCRIPTION1` varchar(255)
,`DESCRIPTION2` varchar(1024)
,`DEAL_CODE` varchar(255)
,`STEPS_TO_CONSUME` varchar(2048)
,`VIEW_COUNT` int(10)
,`IS_ACTIVE` tinyint(1)
,`DEAL_IMG_NAME` varchar(255)
,`DEAL_URL` varchar(512)
,`CREATE_DATE` timestamp
,`EXPIRY_DATE` timestamp(6)
,`UPDATE_DATE` timestamp
,`CREATED_BY` varchar(255)
,`PRICE` varchar(255)
,`DISCOUNT` varchar(255)
,`DISCOUNTED_PRICE` varchar(255)
,`STORE_NAME` varchar(255)
,`STORE_IMG_NAME` varchar(255)
,`STORE_URL` varchar(255)
,`CATEGORY_NAME` varchar(255)
,`PARENT_ID` int(10)
);

-- --------------------------------------------------------

--
-- Table structure for table `STORE_DATA`
--

CREATE TABLE `STORE_DATA` (
  `ID` int(10) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `IMG_NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(1024) DEFAULT NULL,
  `STORE_URL` varchar(255) NOT NULL,
  `IS_POPULAR` tinyint(1) DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `IS_ONLINE` tinyint(1) DEFAULT NULL,
  `VIEW_COUNT` int(10) NOT NULL DEFAULT '0',
  `GEO_LOCATION` varchar(255) DEFAULT NULL,
  `CREATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `UPDATE_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `STORE_DATA`
--

INSERT INTO `STORE_DATA` (`ID`, `NAME`, `IMG_NAME`, `DESCRIPTION`, `STORE_URL`, `IS_POPULAR`, `LOCATION`, `IS_ONLINE`, `VIEW_COUNT`, `GEO_LOCATION`, `CREATE_DATE`, `UPDATE_DATE`, `CREATED_BY`) VALUES
(1, 'Flipkart', 'flipkart.jpg', NULL, 'http://www.flipkart.com', 1, NULL, 1, 0, NULL, '2016-01-01 00:03:04', '2016-01-01 00:03:04', 'itsras@gmail.com'),
(2, 'Amazon', 'amazon.png', NULL, 'http://www.amazon.in', 1, NULL, 1, 0, NULL, '2015-12-30 17:12:13', '2015-12-30 17:12:13', 'itsras@gmail.com'),
(3, 'Paytm', 'paytm.jpg', NULL, 'http://www.paytm.com', 1, NULL, 1, 0, NULL, '2015-12-30 17:12:44', '2015-12-30 17:12:44', 'itsras@gmail.com'),
(4, 'Ebay', 'ebay.png', NULL, 'http://www.ebay.in', 1, NULL, 1, 0, NULL, '2015-12-30 17:13:21', '2015-12-30 17:13:21', 'itsras@gmail.com');

-- --------------------------------------------------------

--
-- Structure for view `deal_data_vw`
--
DROP TABLE IF EXISTS `deal_data_vw`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `deal_data_vw`  AS  select `DD`.`ID` AS `ID`,`DD`.`STORE_ID` AS `STORE_ID`,`DD`.`CATEGORY_ID` AS `CATEGORY_ID`,`DD`.`LOCATION` AS `LOCATION`,`DD`.`TITLE` AS `TITLE`,`DD`.`DESCRIPTION1` AS `DESCRIPTION1`,`DD`.`DESCRIPTION2` AS `DESCRIPTION2`,`DD`.`DEAL_CODE` AS `DEAL_CODE`,`DD`.`STEPS_TO_CONSUME` AS `STEPS_TO_CONSUME`,`DD`.`VIEW_COUNT` AS `VIEW_COUNT`,`DD`.`IS_ACTIVE` AS `IS_ACTIVE`,`DD`.`IMG_NAME` AS `DEAL_IMG_NAME`,`DD`.`DEAL_URL` AS `DEAL_URL`,`DD`.`CREATE_DATE` AS `CREATE_DATE`,`DD`.`EXPIRY_DATE` AS `EXPIRY_DATE`,`DD`.`UPDATE_DATE` AS `UPDATE_DATE`,`DD`.`CREATED_BY` AS `CREATED_BY`,`DD`.`PRICE` AS `PRICE`,`DD`.`DISCOUNT` AS `DISCOUNT`,`DD`.`DISCOUNTED_PRICE` AS `DISCOUNTED_PRICE`,`SD`.`NAME` AS `STORE_NAME`,`SD`.`IMG_NAME` AS `STORE_IMG_NAME`,`SD`.`STORE_URL` AS `STORE_URL`,`CD`.`NAME` AS `CATEGORY_NAME`,`CD`.`PARENT_ID` AS `PARENT_ID` from ((`deal_data` `DD` join `store_data` `SD`) join `category_data` `CD`) where ((1 = 1) and (`DD`.`STORE_ID` = `SD`.`ID`) and (`DD`.`CATEGORY_ID` = `CD`.`ID`)) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `CATEGORY_DATA`
--
ALTER TABLE `CATEGORY_DATA`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `DEAL_DATA`
--
ALTER TABLE `DEAL_DATA`
  ADD PRIMARY KEY (`ID`,`STORE_ID`,`CATEGORY_ID`),
  ADD KEY `DEAL_DATA_FKC1` (`STORE_ID`),
  ADD KEY `DEAL_DATA_FKC2` (`CATEGORY_ID`);

--
-- Indexes for table `STORE_DATA`
--
ALTER TABLE `STORE_DATA`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `CATEGORY_DATA`
--
ALTER TABLE `CATEGORY_DATA`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `DEAL_DATA`
--
ALTER TABLE `DEAL_DATA`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `STORE_DATA`
--
ALTER TABLE `STORE_DATA`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `DEAL_DATA`
--
ALTER TABLE `DEAL_DATA`
  ADD CONSTRAINT `DEAL_DATA_FKC1` FOREIGN KEY (`STORE_ID`) REFERENCES `STORE_DATA` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `DEAL_DATA_FKC2` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `CATEGORY_DATA` (`ID`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

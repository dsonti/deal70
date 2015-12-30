-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 30, 2015 at 06:31 PM
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
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `EXPIRY_DATE` timestamp(6) NULL DEFAULT NULL,
  `UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `DEAL_DATA`
--

INSERT INTO `DEAL_DATA` (`ID`, `STORE_ID`, `CATEGORY_ID`, `LOCATION`, `TITLE`, `DESCRIPTION1`, `DESCRIPTION2`, `DEAL_CODE`, `DEAL_TYPE`, `STEPS_TO_CONSUME`, `VIEW_COUNT`, `IS_ACTIVE`, `IMG_NAME`, `CREATE_DATE`, `EXPIRY_DATE`, `UPDATE_DATE`, `CREATED_BY`) VALUES
(2, 1, 11, NULL, 'Up To 40% - 70% Off January Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:23:05', '2015-12-30 18:30:00.000000', '2015-12-30 17:23:05', 'itsras@gmail.com'),
(3, 2, 11, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:23:53', '2015-12-30 18:30:00.000000', '2015-12-30 17:23:53', 'itsras@gmail.com'),
(4, 3, 11, NULL, 'Up To 35% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'HUIKDFGS', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:24:31', '2015-12-30 18:30:00.000000', '2015-12-30 17:24:31', 'itsras@gmail.com'),
(5, 4, 11, NULL, 'Up To 42% Off April Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'HUIKDFGS', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:24:57', '2015-12-30 18:30:00.000000', '2015-12-30 17:24:57', 'itsras@gmail.com'),
(6, 1, 20, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:26:50', '2015-12-30 18:30:00.000000', '2015-12-30 17:26:50', 'itsras@gmail.com'),
(7, 2, 20, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:26:50', '2015-12-30 18:30:00.000000', '2015-12-30 17:26:50', 'itsras@gmail.com'),
(8, 3, 20, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:26:50', '2015-12-30 18:30:00.000000', '2015-12-30 17:26:50', 'itsras@gmail.com'),
(9, 4, 20, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', 'ABCDEFG', 'CODE', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:26:50', '2015-12-30 18:30:00.000000', '2015-12-30 17:26:50', 'itsras@gmail.com'),
(10, 1, 25, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', NULL, 'DEAL', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:29:21', '2015-12-30 18:30:00.000000', '2015-12-30 17:29:21', 'itsras@gmail.com'),
(11, 2, 25, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', NULL, 'DEAL', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:29:21', '2015-12-30 18:30:00.000000', '2015-12-30 17:29:21', 'itsras@gmail.com'),
(12, 3, 25, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', NULL, 'DEAL', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:29:21', '2015-12-30 18:30:00.000000', '2015-12-30 17:29:21', 'itsras@gmail.com'),
(13, 4, 25, NULL, 'Up To 50% Off December Savings Event', 'Some exclusions apply. No code required. Limited time offer', 'Some exclusions apply. No code required. Limited time offer', NULL, 'DEAL', 'Some exclusions apply. No code required. Limited time offer', 0, 1, NULL, '2015-12-30 17:29:21', '2015-12-30 18:30:00.000000', '2015-12-30 17:29:21', 'itsras@gmail.com');

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
(1, 'Facebook', 'flipkart.jpg', NULL, 'http://www.facebook.com', 1, NULL, 1, 0, NULL, '2015-12-30 17:11:21', '2015-12-30 17:11:21', 'itsras@gmail.com'),
(2, 'Amazon', 'amazon.png', NULL, 'http://www.amazon.in', 1, NULL, 1, 0, NULL, '2015-12-30 17:12:13', '2015-12-30 17:12:13', 'itsras@gmail.com'),
(3, 'Paytm', 'paytm.jpg', NULL, 'http://www.paytm.com', 1, NULL, 1, 0, NULL, '2015-12-30 17:12:44', '2015-12-30 17:12:44', 'itsras@gmail.com'),
(4, 'Ebay', 'ebay.png', NULL, 'http://www.ebay.in', 1, NULL, 1, 0, NULL, '2015-12-30 17:13:21', '2015-12-30 17:13:21', 'itsras@gmail.com');

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

-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: aplaya
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `brgystaff`
--

DROP TABLE IF EXISTS `brgystaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brgystaff` (
  `staff_ID` int(11) NOT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `remarks` text,
  `resident_ID` int(11) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`staff_ID`),
  KEY `fk_resstaff_idx` (`resident_ID`),
  KEY `fk_staff_username_idx` (`username`),
  CONSTRAINT `fk_staff_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='all barangay workers except health workers';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brgystaff`
--

LOCK TABLES `brgystaff` WRITE;
/*!40000 ALTER TABLE `brgystaff` DISABLE KEYS */;
INSERT INTO `brgystaff` VALUES (1,'Treasurer',NULL,NULL,NULL,37,'popot'),(2,'Secretary',NULL,NULL,NULL,38,'Secretary'),(3,'Asstnt Tr',NULL,NULL,NULL,35,'atr'),(4,'Asstnt Sec',NULL,NULL,NULL,36,'asec'),(5,'CODER',NULL,NULL,NULL,2,'axis');
/*!40000 ALTER TABLE `brgystaff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificates`
--

DROP TABLE IF EXISTS `certificates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificates` (
  `certificate_ID` int(11) NOT NULL AUTO_INCREMENT,
  `applicant` int(11) DEFAULT NULL,
  `cert_type` varchar(45) DEFAULT NULL,
  `purpose` varchar(45) DEFAULT NULL,
  `recorded` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `signedBy` int(11) DEFAULT NULL,
  `dateIssued` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `issuedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`certificate_ID`),
  KEY `fk_certres_idx` (`applicant`),
  KEY `fk_certificates_brgystaff_id_idx` (`issuedby`),
  KEY `fk_certificates_officials_id_idx` (`signedBy`),
  CONSTRAINT `fk_certificates_brgystaff_id` FOREIGN KEY (`issuedby`) REFERENCES `brgystaff` (`staff_ID`) ON UPDATE CASCADE,
  CONSTRAINT `fk_certificates_officials_id` FOREIGN KEY (`signedBy`) REFERENCES `officials` (`official_ID`),
  CONSTRAINT `fk_certificates_person_id` FOREIGN KEY (`applicant`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='brgy certification certificates';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificates`
--

LOCK TABLES `certificates` WRITE;
/*!40000 ALTER TABLE `certificates` DISABLE KEYS */;
INSERT INTO `certificates` VALUES (1,2,'Residence','Employment','2019-10-03 23:00:29',1,'2019-10-03 23:00:29',1),(2,3,'Residence','Employment','2019-10-03 23:00:52',2,'2019-10-03 23:00:52',2),(3,4,'Residence','Bank','2019-10-03 23:01:23',1,'2019-10-03 23:01:23',2),(4,6,'Residence','Bank','2019-10-03 23:01:54',1,'2019-10-03 23:01:54',2),(5,7,'Residence','Employment','2019-10-03 23:01:54',1,'2019-10-03 23:01:54',2),(55,44,'Residence','Employment','2019-10-07 04:17:18',1,'2019-10-07 04:17:18',NULL),(121,50,'Residence','Employment','2019-10-08 09:09:26',1,'2019-10-08 09:09:26',NULL),(122,2,'Residency','Employment','2019-10-10 07:16:56',1,'2019-10-10 07:16:56',NULL),(123,9,'Residency','Employment','2019-10-10 13:25:11',3,'2019-10-10 13:25:11',NULL),(124,2,'Residency','Employment','2019-10-10 20:49:11',1,'2019-10-10 20:49:11',5);
/*!40000 ALTER TABLE `certificates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clearance`
--

DROP TABLE IF EXISTS `clearance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clearance` (
  `clearance_no` int(11) NOT NULL AUTO_INCREMENT,
  `applicant` int(11) DEFAULT NULL,
  `purpose` varchar(45) DEFAULT NULL,
  `OR_no` int(11) DEFAULT NULL,
  `ReceiptAmount` int(11) DEFAULT NULL,
  `recorded` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `issuedBy` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `findings` text,
  `dateIssued` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `remarks` varchar(45) DEFAULT NULL,
  `signedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`clearance_no`),
  KEY `resident_idx` (`applicant`),
  KEY `fk_clearance_brgystaff_id_idx` (`issuedBy`),
  KEY `fk_clearance_officials_id_idx` (`signedby`),
  CONSTRAINT `fk_clearance_brgystaff_id` FOREIGN KEY (`issuedBy`) REFERENCES `brgystaff` (`staff_ID`),
  CONSTRAINT `fk_clearance_officials_id` FOREIGN KEY (`signedby`) REFERENCES `officials` (`official_ID`),
  CONSTRAINT `fk_clearance_person_id` FOREIGN KEY (`applicant`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='clearance for residents';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clearance`
--

LOCK TABLES `clearance` WRITE;
/*!40000 ALTER TABLE `clearance` DISABLE KEYS */;
INSERT INTO `clearance` VALUES (1,2,'Employment',NULL,NULL,'2019-10-03 23:18:38',1,'ok','none','2019-10-03 23:18:38',NULL,1),(2,3,'Bank',NULL,NULL,'2019-10-03 23:23:33',1,'ok','none','2019-10-03 23:23:33',NULL,2),(3,4,'Employment',NULL,NULL,'2019-10-03 23:23:33',2,'ok','none','2019-10-03 23:23:33',NULL,1),(4,5,'Employment',NULL,NULL,'2019-10-03 23:23:33',3,'ok','none','2019-10-03 23:23:33',NULL,2),(5,6,'Employment',NULL,NULL,'2019-10-03 23:23:33',4,'ok','none','2019-10-03 23:23:33',NULL,2),(6,5,'Employment',NULL,NULL,'2019-10-10 13:17:59',5,'asd','asd','2019-10-10 13:17:59','asd',1),(7,7,'Employment',NULL,NULL,'2019-10-10 13:20:15',5,'sss','sss','2019-10-10 13:20:15','sssssss',1),(8,2,'Employment',NULL,NULL,'2019-10-10 20:48:52',5,'asd','asd','2019-10-10 20:48:52','should be issuedby 5',1);
/*!40000 ALTER TABLE `clearance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `family`
--

DROP TABLE IF EXISTS `family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `family` (
  `family#` int(11) NOT NULL,
  `member` int(11) DEFAULT NULL,
  PRIMARY KEY (`family#`),
  KEY `fk_fam_member_idx` (`member`),
  CONSTRAINT `fk_fam_member` FOREIGN KEY (`member`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='a household can comprise of 1 or more families';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `family`
--

LOCK TABLES `family` WRITE;
/*!40000 ALTER TABLE `family` DISABLE KEYS */;
/*!40000 ALTER TABLE `family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hh_members`
--

DROP TABLE IF EXISTS `hh_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hh_members` (
  `hh_id` int(11) NOT NULL,
  `members` int(11) NOT NULL,
  PRIMARY KEY (`hh_id`,`members`),
  UNIQUE KEY `members_UNIQUE` (`members`),
  KEY `fk_hhmem_idx` (`members`),
  CONSTRAINT `fk_hhid` FOREIGN KEY (`hh_id`) REFERENCES `household` (`house_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_hhmem` FOREIGN KEY (`members`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='this table contains the members(res_ID) of a household(hh#)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hh_members`
--

LOCK TABLES `hh_members` WRITE;
/*!40000 ALTER TABLE `hh_members` DISABLE KEYS */;
INSERT INTO `hh_members` VALUES (57,2),(61,3),(57,4),(57,5),(58,6),(58,7),(63,8),(78,9),(69,10),(70,11),(72,12),(73,14),(57,15),(62,17),(62,40),(78,61);
/*!40000 ALTER TABLE `hh_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `household`
--

DROP TABLE IF EXISTS `household`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `household` (
  `house_ID` int(11) NOT NULL AUTO_INCREMENT,
  `headoffamily` int(11) DEFAULT NULL,
  `hzone` int(11) DEFAULT NULL,
  PRIMARY KEY (`house_ID`),
  UNIQUE KEY `headoffamily_UNIQUE` (`headoffamily`),
  KEY `head_idx` (`headoffamily`),
  KEY `hhzz_idx` (`hzone`),
  CONSTRAINT `head` FOREIGN KEY (`headoffamily`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `hhzz` FOREIGN KEY (`hzone`) REFERENCES `zone` (`zid`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `household`
--

LOCK TABLES `household` WRITE;
/*!40000 ALTER TABLE `household` DISABLE KEYS */;
INSERT INTO `household` VALUES (57,2,1),(58,7,2),(61,3,8),(62,40,1),(63,8,2),(67,45,1),(69,10,3),(70,11,1),(72,12,1),(73,14,1),(78,61,6);
/*!40000 ALTER TABLE `household` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incident`
--

DROP TABLE IF EXISTS `incident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incident` (
  `blotterID` int(11) NOT NULL AUTO_INCREMENT,
  `narrative` text,
  `incident_when` datetime DEFAULT NULL,
  `complainant` int(11) NOT NULL,
  `respondent` int(11) DEFAULT NULL,
  `victim` int(11) DEFAULT NULL,
  `recorded` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `incident_place` text,
  `incident_type` varchar(45) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `recordedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`blotterID`),
  KEY `fk_incident_person_complainant_idx` (`complainant`),
  KEY `fk_incident_person_respondent_idx` (`respondent`),
  KEY `fk_incident_person_victim_idx` (`victim`),
  KEY `fk_incident_brgystaff_id_idx` (`recordedby`),
  CONSTRAINT `fk_incident_brgystaff_id` FOREIGN KEY (`recordedby`) REFERENCES `brgystaff` (`staff_ID`),
  CONSTRAINT `fk_incident_person_complainant` FOREIGN KEY (`complainant`) REFERENCES `person` (`id`),
  CONSTRAINT `fk_incident_person_respondent` FOREIGN KEY (`respondent`) REFERENCES `person` (`id`),
  CONSTRAINT `fk_incident_person_victim` FOREIGN KEY (`victim`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='incident report form';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incident`
--

LOCK TABLES `incident` WRITE;
/*!40000 ALTER TABLE `incident` DISABLE KEYS */;
INSERT INTO `incident` VALUES (1,'gi tigbas ang liog','2019-10-01 00:00:00',2,3,4,'2019-10-03 23:45:37','balay','violence','pending',2),(2,'ang iro niya nalibang sa dalan','2019-10-01 00:00:00',3,4,5,'2019-10-03 23:45:37','balay','violence','pending',2),(3,'gikawat akong manok','2019-10-01 00:00:00',5,6,7,'2019-10-03 23:45:37','balay','violence','pending',2),(4,'wala ga bayad sa utang','2019-10-01 00:00:00',7,8,9,'2019-10-03 23:45:37','balay','violence','pending',2),(5,'dle ga bayad sa rent','2019-10-01 00:00:00',2,8,9,'2019-10-03 23:45:37','balay','violence','pending',2),(6,'asdfasdfasdf','2019-10-02 00:00:00',2,3,4,'2019-10-06 00:00:40','aplaya','violence','solved',NULL),(7,'asdf','2019-09-30 00:00:00',3,4,7,'2019-10-07 01:18:45','asdf','aasdf','asdf',NULL),(8,'hsdskjadhjkahdksajdj','2019-10-02 00:00:00',5,2,4,'2019-10-08 09:25:13','errgdf','Violence','Pending',NULL),(9,'asdf','2019-10-03 00:00:00',51,15,5,'2019-10-10 12:00:32','adsf','asdf','asdf',NULL),(10,'nothing ','2019-10-11 00:00:00',2,4,6,'2019-10-10 21:40:52','nothing happened','libak','resolved',NULL);
/*!40000 ALTER TABLE `incident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logs`
--

DROP TABLE IF EXISTS `logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logs` (
  `log#` int(11) NOT NULL,
  `actor` int(11) DEFAULT NULL,
  `action` text,
  `TIMESTAMP` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `table` varchar(10) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`log#`),
  UNIQUE KEY `log#_UNIQUE` (`log#`),
  KEY `fk_logs_staff_idx` (`actor`),
  CONSTRAINT `fk_logs_staff` FOREIGN KEY (`actor`) REFERENCES `brgystaff` (`staff_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logs`
--

LOCK TABLES `logs` WRITE;
/*!40000 ALTER TABLE `logs` DISABLE KEYS */;
INSERT INTO `logs` VALUES (1,1,'new res [2]','2019-10-03 23:42:31',NULL,NULL),(2,1,'cert [1]','2019-10-03 23:42:31',NULL,NULL),(3,2,'permit [1]','2019-10-03 23:42:31',NULL,NULL),(4,2,'new res [3]','2019-10-03 23:42:31',NULL,NULL),(5,3,'new res [4]','2019-10-03 23:42:31',NULL,NULL);
/*!40000 ALTER TABLE `logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `officials`
--

DROP TABLE IF EXISTS `officials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `officials` (
  `official_ID` int(11) NOT NULL,
  `resident_ID` int(11) DEFAULT NULL,
  `position` varchar(45) DEFAULT NULL,
  `committee` varchar(45) DEFAULT NULL,
  `term_start` date DEFAULT NULL,
  `term_end` date DEFAULT NULL,
  `remarks` text,
  PRIMARY KEY (`official_ID`),
  KEY `official res_idx` (`resident_ID`),
  CONSTRAINT `official res` FOREIGN KEY (`resident_ID`) REFERENCES `person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='all elected officials capt to kagawad sanguniang brgy';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `officials`
--

LOCK TABLES `officials` WRITE;
/*!40000 ALTER TABLE `officials` DISABLE KEYS */;
INSERT INTO `officials` VALUES (1,39,'Captain',NULL,NULL,NULL,NULL),(2,40,'Kagawad1',NULL,NULL,NULL,NULL),(3,10,'Kagawad2',NULL,NULL,NULL,NULL),(4,11,'Kagawad3',NULL,NULL,NULL,NULL),(5,12,'Kagawad4',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `officials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permits`
--

DROP TABLE IF EXISTS `permits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permits` (
  `permit_no` int(11) NOT NULL AUTO_INCREMENT,
  `permitType` varchar(45) DEFAULT NULL,
  `applicant` int(11) DEFAULT NULL,
  `businessName` text,
  `businessAddress` text,
  `businessType` varchar(45) DEFAULT NULL,
  `OR#` int(11) DEFAULT NULL,
  `ReceiptAmount` int(11) DEFAULT NULL,
  `recorded` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `issuedBy` int(11) DEFAULT NULL,
  `expiry` date DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `dateIssued` date DEFAULT NULL,
  `purpose` varchar(45) DEFAULT NULL,
  `location` text,
  `signedby` int(11) DEFAULT NULL,
  `remarks` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`permit_no`),
  KEY `recorder_idx` (`issuedBy`),
  KEY `fk_permits_person_applicant_idx` (`applicant`),
  KEY `fk_permits_officials_idx` (`signedby`),
  CONSTRAINT `fk_permits_brgystaff_recorder` FOREIGN KEY (`issuedBy`) REFERENCES `brgystaff` (`staff_ID`),
  CONSTRAINT `fk_permits_officials` FOREIGN KEY (`signedby`) REFERENCES `officials` (`official_ID`),
  CONSTRAINT `fk_permits_person_applicant` FOREIGN KEY (`applicant`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='permits table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permits`
--

LOCK TABLES `permits` WRITE;
/*!40000 ALTER TABLE `permits` DISABLE KEYS */;
INSERT INTO `permits` VALUES (1,'Cutting',50,NULL,NULL,NULL,NULL,NULL,'2019-10-08 09:07:48',NULL,NULL,NULL,NULL,'None',NULL,1,''),(5,'Cutting',2,NULL,NULL,NULL,NULL,NULL,'2019-10-10 12:46:16',NULL,NULL,NULL,NULL,NULL,NULL,4,'asdfggg'),(6,'Cutting',2,NULL,NULL,NULL,NULL,NULL,'2019-10-10 12:46:43',NULL,NULL,NULL,NULL,NULL,NULL,1,'atqtqwe w'),(7,'Cutting',2,NULL,NULL,NULL,NULL,NULL,'2019-10-10 12:47:20',NULL,NULL,NULL,NULL,NULL,NULL,1,'awasfdasf'),(8,'Cutting',2,NULL,NULL,NULL,NULL,NULL,'2019-10-10 12:47:36',NULL,NULL,NULL,NULL,NULL,NULL,2,'asdadfasdf'),(9,'Cutting',2,NULL,NULL,NULL,NULL,NULL,'2019-10-10 12:47:59',NULL,NULL,NULL,NULL,NULL,NULL,1,'captain choses 001'),(10,'Cutting',2,NULL,NULL,NULL,NULL,NULL,'2019-10-10 20:47:27',5,NULL,NULL,NULL,NULL,NULL,1,'should be issued by 5'),(12,'Cutting',61,NULL,NULL,NULL,NULL,NULL,'2019-10-11 01:21:47',5,NULL,NULL,NULL,NULL,NULL,1,'testtt');
/*!40000 ALTER TABLE `permits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resident` tinyint(1) DEFAULT '1',
  `lname` varchar(25) NOT NULL,
  `fname` varchar(25) NOT NULL,
  `mname` varchar(25) DEFAULT NULL,
  `suffix` varchar(4) DEFAULT NULL,
  `birthdate` date NOT NULL,
  `sex` varchar(6) NOT NULL,
  `height` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `citizenship` varchar(45) DEFAULT NULL,
  `birthplace` varchar(45) DEFAULT NULL,
  `mstatus` varchar(45) NOT NULL,
  `religion` varchar(45) DEFAULT NULL,
  `occupation` varchar(45) DEFAULT NULL,
  `idenmarks` varchar(45) DEFAULT NULL,
  `bloodtype` varchar(2) DEFAULT NULL,
  `mother` varchar(45) DEFAULT NULL,
  `father` varchar(45) DEFAULT NULL,
  `pregnant` tinyint(1) DEFAULT NULL,
  `PWD` int(11) DEFAULT NULL,
  `remarks` text,
  `image` text,
  `SSS` varchar(45) DEFAULT NULL,
  `philhealthNo` varchar(45) DEFAULT NULL,
  `highesteducation` varchar(45) DEFAULT NULL,
  `family#` int(11) DEFAULT NULL,
  `soloparent` int(11) DEFAULT NULL,
  `household#` int(11) DEFAULT NULL,
  `alive` tinyint(1) NOT NULL DEFAULT '1',
  `defect` varchar(45) DEFAULT NULL,
  `duedate` date DEFAULT NULL,
  `alias` varchar(45) DEFAULT NULL,
  `house` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `address` text,
  PRIMARY KEY (`id`),
  KEY `fk_resfam_idx` (`family#`),
  CONSTRAINT `fk_resfam` FOREIGN KEY (`family#`) REFERENCES `family` (`family#`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=armscii8 COMMENT='contains all info about each resident in the barangay';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` VALUES (2,1,'Galaura','Alexis Lyndon','Guian',NULL,'1994-05-09','Male',168,76,'Filipino','Cagayan de Oro','Single','Roman Catholic','Student','mole on chin','B','Lalyn Guian Galaura','Andrew Villafuerte Galaura',0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,'2020','xyz st.',NULL),(3,1,'Cabalatungan','Ella Rose','Berizo',NULL,'1999-07-05','Female',155,50,'Nigerian','Nigeria','Widow','Muslim yaharmokalah','Student',NULL,'AB',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(4,1,'Galaura','Yael Quentin','Alburo','','2018-02-06','Male',70,11,'Filipino','Cagayan de Oro','Single','Roman Catholic','bossbaby',NULL,'B',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(5,1,'Alburo','Rodith','Catchavan','','1994-12-20','Female',155,50,'Filipino','cdo','Single','Roman Catholic','Translator',NULL,'B',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(6,1,'Galaura','Lalyn','Guian','','1966-03-29','Female',155,70,'Filipino','Aplaya, Jasaan','Maried','Roman Catholic','',NULL,'O',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(7,1,'Galaura','Andrew','Villafuerte','','1961-11-30','Male',168,60,'Filipino','Cagayan de Oro','Maried','Roman Catholic','',NULL,'B',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(8,1,'Herrero','Anthony Dylan','Alburo','','2019-10-01','Male',55,3,'Filipino','Cagayan de Oro','Single','Roman Catholic','bossbaby',NULL,'B',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(9,1,'Susada','Marj','b',NULL,'2019-10-01','Female',NULL,NULL,NULL,NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(10,1,'WIP','WIP','WIP','WIP','2000-01-01','Female',1,1,'Filipino','WIP','Single','WIP','WIP',NULL,'O',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(11,1,'ELLA PANGIT','WIP','WIP','WIP','2000-01-01','Female',1,1,'Filipino','WIP','Single','WIP','WIP',NULL,'O',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(12,1,'WIP','WIP','WIP','WIP','2000-01-01','Female',1,1,'Filipino','WIP','Single','WIP','WIP',NULL,'O',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(14,1,'Llenares','Jan Grace','Ombid','','2000-01-25','Female',150,55,'Filipino','Cagayan de Oro','Single','Born Again','Pokpok',NULL,'AB',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(15,1,'Galaura','Lorraine Anne','Guian','','2000-03-28','Female',168,60,'Filipino','cagayan de oro','Single','Roman Catholic','student',NULL,'B',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(16,1,'Galaura','Lorraine Anne','Guian','','2000-03-29','Female',168,60,'Filipino','cagayan de oro','Single','Roman Catholic','student',NULL,'B',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(17,1,'Galaura','Lei Katreena','Guian','','1996-03-09','Female',150,48,'Filipino','cdo','Single','Roman Catholic','student',NULL,'o',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(18,1,'Guian','Cherrybel','Bade','','1970-07-28','Female',168,65,'Filipino','jasaan','Single','Roman Catholic','Businesswoman','very tall','O',NULL,NULL,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,'','2020-01-01',NULL,NULL,NULL,NULL),(19,1,'TEST','TEST','','','2019-09-29','Male',11,23,'Filipino','TEST','Single','Roman Catholic','','TEST','o',NULL,NULL,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,'','2000-01-01',NULL,NULL,NULL,NULL),(20,1,'g','g',NULL,NULL,'2019-09-29','Male',NULL,NULL,'Filipino',NULL,'Single','Roman Catholic',NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(21,1,'a','a',NULL,NULL,'2019-07-28','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(23,1,'Alburo','Bajing','Cat',NULL,'2019-09-09','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(24,1,'a','a','a',NULL,'2019-10-09','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(25,1,'asdf','asdf',NULL,NULL,'2019-10-01','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(26,1,'eeee','eeee',NULL,NULL,'2019-09-09','Male',NULL,NULL,'Filipino',NULL,'Maried',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(27,1,'qwer','qwer',NULL,NULL,'2019-09-10','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(28,1,'alex','alex',NULL,NULL,'2019-09-30','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(29,1,'Brazil','Charlz ','bAYOT',NULL,'2019-09-26','Male',158,43,'Filipino','cdo','Single','Born Again','pokpok','bayot na putot','O',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,1,'retard','2019-12-20',NULL,NULL,NULL,NULL),(30,1,'hh','dfg',NULL,NULL,'2019-11-21','Male',NULL,NULL,'Filipino',NULL,'Maried',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(31,1,'Libago','Tomas Jubile',NULL,NULL,'2000-10-08','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(32,1,'Malik','Zayn','Javadd',NULL,'1993-01-12','Male',500,78,'British','Bradford, England','Maried','muslim','Celebrity','amazing hair','B',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(33,1,'Malik','Jan Grace','Llenares',NULL,'2000-01-25','Female',NULL,NULL,'Filipino',NULL,'Maried',NULL,NULL,NULL,NULL,NULL,NULL,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(34,1,'Baang','Sean','Columna',NULL,'2000-07-19','Male',NULL,NULL,'Filipino','Hospital','Single','Roman Catholic','Student','mole','O',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(35,1,'sumalpong','bayogyog','tweng','jr','2019-10-31','Male',123,434,'Filipino','CDO','Single','Roman Catholic','student','naa','AB',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(36,1,'Styles','Harry Edward','Cox',NULL,'1994-02-01','Male',187,80,'British','secret','Single','Roman Catholic','Singer, Songwriter, Actor, Producer','4 nipples','A',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(37,1,'Valcueba','Haydee','Bade',NULL,'1978-07-11','Female',160,60,'Filipino','Jasaan','Single','Roman Catholic','Barangay Treasurer',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(38,1,'Roa','Denden',NULL,NULL,'1980-01-01','Female',NULL,NULL,NULL,NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(39,1,'Pedrano','Mila','Bade',NULL,'1955-01-01','Female',NULL,NULL,NULL,NULL,'Married',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(40,1,'Guian','Racor','Bade',NULL,'1962-05-05','Male',NULL,NULL,NULL,NULL,'Married',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(41,1,'Doe','John','Smith',NULL,'2019-10-05','Male',1,2,'American','USA','Single','Roman Catholic','Blacksmith','i have nice abs','o',NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,1,NULL,NULL,'joe',NULL,NULL,NULL),(42,1,'Abad','Neil Anthony','Usman',NULL,'1999-11-03','Male',NULL,NULL,'Filipino','Tagoloan','Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(43,1,'Deliza','Arbie','Rara',NULL,'2000-11-21','Female',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(44,1,'Pagaran','Michael','Tabamo',NULL,'2000-05-08','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(45,1,'Fernandez','Lester',NULL,NULL,'2019-10-08','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(46,1,'Fernandez','Lester',NULL,'','2019-10-08','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,'',NULL,NULL,NULL,NULL,NULL),(47,1,'abc','zza',NULL,NULL,'2019-10-06','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(48,1,'BBB','A',NULL,NULL,'2019-10-05','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(49,1,'Galaura','Lalyn','Guian','','1966-03-28','Female',155,70,'Filipino','Aplaya, Jasaan','Maried','Roman Catholic','',NULL,'O',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,'',NULL,NULL,'2020','karsada',NULL),(50,1,'Alburo','April Grace','Catchavan',NULL,'1999-04-10','Female',NULL,NULL,'Filipino','Butuan City','Single',NULL,NULL,NULL,NULL,NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,'','',NULL),(51,1,'Alamon','Vianni Benjamin',NULL,NULL,'2019-10-10','Male',NULL,NULL,'Filipino',NULL,'Married',NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,1,NULL,NULL,NULL,'','',NULL),(52,1,'Pacana','Meg Ella Fatime','Abucejo',NULL,'1999-12-01','Female',12,NULL,'Filipino',NULL,'Single','Born Again',NULL,'birth mark forehead','o',NULL,NULL,1,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,'2020-07-06','lil meg','032','gumamela',NULL),(53,1,'asd','asd',NULL,NULL,'2019-10-03','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,'','',NULL),(54,1,'test','test',NULL,NULL,'2019-10-11','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,1,1,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,1,NULL,NULL,NULL,'','',NULL),(55,1,'one','two','etst',NULL,'2019-10-09','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,'','',NULL),(56,1,'Alburo','Jeffrey','Catchavan',NULL,'2005-06-06','Male',150,45,'Filipino','CDO','Single','Roman Catholic',NULL,'mararar','o',NULL,NULL,0,1,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,1,'mute',NULL,'jeprox','','',NULL),(57,0,'someguy','non res',NULL,NULL,'2019-10-03','Other',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,NULL,NULL,''),(58,1,'Galaura','Alexis Lyndon','Guian','','1994-05-09','M',NULL,NULL,NULL,'Cagayan de Oro','Single','Roman Catholic','Student','mole on chin','B',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,'',NULL,NULL,'2020','xyz st.',NULL),(59,1,'Galaura','Alexis Lyndon','Guian','','1994-05-09','Male',168,76,'Filipino','Cagayan de Oro','Single','Roman Catholic','Student','mole on chin','B',NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,'',NULL,'axis','2020','xyz st.',NULL),(60,1,'Valdez','Cyriel',NULL,NULL,'2019-10-10','Male',NULL,NULL,'Filipino',NULL,'Single',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,'','',NULL),(61,1,'lagrosas','jessie',NULL,NULL,'1990-12-23','Male',NULL,NULL,'Filipino',NULL,'Married',NULL,NULL,NULL,NULL,NULL,NULL,0,0,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,1,NULL,NULL,NULL,'','',NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `pos` int(11) NOT NULL,
  `position_name` varchar(45) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`pos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `request_ID` int(11) NOT NULL AUTO_INCREMENT,
  `date_requested` datetime DEFAULT CURRENT_TIMESTAMP,
  `requester` int(11) DEFAULT NULL,
  `request_details` text,
  `date_time_needed` datetime DEFAULT NULL,
  `priority` varchar(12) DEFAULT NULL,
  `request_location` varchar(40) DEFAULT NULL,
  `recordedby` int(11) DEFAULT NULL,
  `fullfilledby` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`request_ID`),
  KEY `fk_request_person_requester_idx` (`requester`),
  KEY `fk_request_brgystaff_id_idx` (`recordedby`),
  KEY `fk_request_official_fullfiler_idx` (`fullfilledby`),
  CONSTRAINT `fk_request_brgystaff_id` FOREIGN KEY (`recordedby`) REFERENCES `brgystaff` (`staff_ID`),
  CONSTRAINT `fk_request_official_fullfiler` FOREIGN KEY (`fullfilledby`) REFERENCES `officials` (`official_ID`),
  CONSTRAINT `fk_request_person_requester` FOREIGN KEY (`requester`) REFERENCES `person` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'2019-10-04 07:28:27',2,'SUGA','2020-01-01 00:00:00','low','sa balay',2,1,NULL),(2,'2019-10-04 07:28:27',3,'LIPYO IMBURNAL','2019-10-10 00:00:00','high','sa balay',2,1,NULL),(3,'2019-10-04 07:28:27',4,'funeral assistance','2019-10-10 00:00:00','high','sa balay',2,1,NULL),(4,'2019-10-04 07:28:27',5,'monies','2019-10-10 00:00:00','medium','sa balay',2,1,NULL),(5,'2019-10-04 07:28:27',6,'monies','2019-10-10 00:00:00','medium','sa balay',NULL,1,NULL),(6,'2019-10-07 12:19:24',44,'123qwe','2019-10-08 00:00:00','high','1',NULL,NULL,NULL),(7,'2019-10-07 12:24:59',2,'dfghjk','2019-10-08 00:00:00','high','1',NULL,NULL,NULL),(8,'2019-10-11 04:38:55',3,'asd','2019-10-08 00:00:00','sfdsf','adsf',NULL,NULL,NULL),(9,'2019-10-11 04:50:54',2,'hello ','2019-10-12 00:00:00','high','galaura residence',NULL,NULL,NULL);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(16) NOT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(10) DEFAULT 'staff',
  PRIMARY KEY (`username`),
  KEY `idx_users_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('asec','admin','2019-10-03 23:37:06','asec'),('atr','admin','2019-10-03 23:37:06','atr'),('axis','gwapo','2019-09-24 03:25:19','coder'),('ella','pangit','2019-09-29 02:17:20','coder'),('popot','gwapa','2019-10-03 23:37:06','treasurer'),('rodith','gwapa','2019-09-28 02:08:41','tester'),('Secretary','admin','2019-10-03 01:25:47','brgy staff'),('Yael','gwapo','2019-09-30 10:32:10','bossbaby');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zone`
--

DROP TABLE IF EXISTS `zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zone` (
  `zid` int(11) NOT NULL,
  `zpresident` int(11) DEFAULT NULL,
  `zalias` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zone`
--

LOCK TABLES `zone` WRITE;
/*!40000 ALTER TABLE `zone` DISABLE KEYS */;
INSERT INTO `zone` VALUES (1,NULL,'1a'),(2,NULL,'1b'),(3,NULL,'2'),(4,NULL,'2u'),(5,NULL,'3'),(6,NULL,'3u'),(7,NULL,'4'),(8,NULL,'5');
/*!40000 ALTER TABLE `zone` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-13  7:51:35

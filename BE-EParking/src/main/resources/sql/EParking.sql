-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: eparking
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `cardetail`
--

DROP TABLE IF EXISTS `cardetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cardetail` (
  `carID` int NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(10) NOT NULL,
  `licensePlate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`carID`),
  KEY `FK_CustomerCar` (`phoneNumber`),
  CONSTRAINT `FK_CustomerCar` FOREIGN KEY (`phoneNumber`) REFERENCES `user` (`phoneNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cardetail`
--

LOCK TABLES `cardetail` WRITE;
/*!40000 ALTER TABLE `cardetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `cardetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `date`
--

DROP TABLE IF EXISTS `date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `date` (
  `dateOfWeekID` int NOT NULL AUTO_INCREMENT,
  `dateOfWeek` varchar(255) NOT NULL,
  PRIMARY KEY (`dateOfWeekID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `date`
--

LOCK TABLES `date` WRITE;
/*!40000 ALTER TABLE `date` DISABLE KEYS */;
INSERT INTO `date` VALUES (1,'Monday'),(2,'Tuesday'),(3,'Wednesday'),(4,'Thursday'),(5,'Friday'),(6,'Saturday'),(7,'Sunday');
/*!40000 ALTER TABLE `date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `method`
--

DROP TABLE IF EXISTS `method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `method` (
  `methodID` int NOT NULL AUTO_INCREMENT,
  `methodName` varchar(255) NOT NULL,
  PRIMARY KEY (`methodID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `method`
--

LOCK TABLES `method` WRITE;
/*!40000 ALTER TABLE `method` DISABLE KEYS */;
INSERT INTO `method` VALUES (1,'Hours'),(2,'Slot');
/*!40000 ALTER TABLE `method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking` (
  `parkingID` int NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(10) NOT NULL,
  `methodID` int DEFAULT NULL,
  `parkingName` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `description` varchar(3000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `images` varchar(3000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `address` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `latitude` decimal(10,6) DEFAULT NULL,
  `longitude` decimal(10,6) DEFAULT NULL,
  `pricing` int DEFAULT NULL,
  `park` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`parkingID`),
  KEY `FK_SupplierParking` (`phoneNumber`),
  KEY `FK_MethodParking` (`methodID`),
  CONSTRAINT `FK_MethodParking` FOREIGN KEY (`methodID`) REFERENCES `method` (`methodID`),
  CONSTRAINT `FK_SupplierParking` FOREIGN KEY (`phoneNumber`) REFERENCES `user` (`phoneNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
INSERT INTO `parking` VALUES (1,'0946219139',1,'Romantic A','This is the good parking','https://lirp.cdn-website.com/md/unsplash/dms3rep/multi/opt/photo-1506521781263-d8422e82f27a-1920w.jpg','123 Example Street',37.123456,-122.123456,100000,8,1),(2,'0946219139',2,'Romantic B','This is the good parking 1','https://lirp.cdn-website.com/md/unsplash/dms3rep/multi/opt/photo-1506521781263-d8422e82f27a-1920w.jpg','xyz Example Street',37.123456,-122.123456,100000,8,1),(3,'0946219139',2,'Romantic C','This is the good parking 2','https://lirp.cdn-website.com/md/unsplash/dms3rep/multi/opt/photo-1506521781263-d8422e82f27a-1920w.jpg','abc Example Street',37.123456,-122.123456,50000,8,1);
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkingdate`
--

DROP TABLE IF EXISTS `parkingdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parkingdate` (
  `dateOfWeekID` int NOT NULL,
  `parkingID` int NOT NULL,
  `offerDate` double DEFAULT NULL,
  KEY `FK_ParkingDate` (`parkingID`),
  KEY `FK_DateParking` (`dateOfWeekID`),
  CONSTRAINT `FK_DateParking` FOREIGN KEY (`dateOfWeekID`) REFERENCES `date` (`dateOfWeekID`),
  CONSTRAINT `FK_ParkingDate` FOREIGN KEY (`parkingID`) REFERENCES `parking` (`parkingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkingdate`
--

LOCK TABLES `parkingdate` WRITE;
/*!40000 ALTER TABLE `parkingdate` DISABLE KEYS */;
INSERT INTO `parkingdate` VALUES (1,1,0.4),(2,1,0.5),(3,1,0.7);
/*!40000 ALTER TABLE `parkingdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkingspecialdate`
--

DROP TABLE IF EXISTS `parkingspecialdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parkingspecialdate` (
  `specialDateID` int NOT NULL,
  `parkingID` int NOT NULL,
  `offerSpecialDate` double DEFAULT NULL,
  KEY `FK_ParkingSpecialDate` (`parkingID`),
  KEY `FK_SpecialDateParking` (`specialDateID`),
  CONSTRAINT `FK_ParkingSpecialDate` FOREIGN KEY (`parkingID`) REFERENCES `parking` (`parkingID`),
  CONSTRAINT `FK_SpecialDateParking` FOREIGN KEY (`specialDateID`) REFERENCES `specialdate` (`specialDateID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkingspecialdate`
--

LOCK TABLES `parkingspecialdate` WRITE;
/*!40000 ALTER TABLE `parkingspecialdate` DISABLE KEYS */;
INSERT INTO `parkingspecialdate` VALUES (1,1,0.4),(2,1,0.5),(3,1,0.7);
/*!40000 ALTER TABLE `parkingspecialdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentdetail`
--

DROP TABLE IF EXISTS `paymentdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymentdetail` (
  `paymentID` int NOT NULL AUTO_INCREMENT,
  `paymentName` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `paymentDateTime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`paymentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentdetail`
--

LOCK TABLES `paymentdetail` WRITE;
/*!40000 ALTER TABLE `paymentdetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `reserveID` int NOT NULL AUTO_INCREMENT,
  `phoneNumber` varchar(10) NOT NULL,
  `parkingID` int NOT NULL,
  `statusID` int NOT NULL,
  `paymentID` int NOT NULL,
  `carID` int NOT NULL,
  `startDateTime` timestamp NULL DEFAULT NULL,
  `endDatetime` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`reserveID`),
  KEY `FK_CustomerReservation` (`phoneNumber`),
  KEY `FK_ParkingReservation` (`parkingID`),
  KEY `FK_StatusReservation` (`statusID`),
  KEY `FK_PaymentReservation` (`paymentID`),
  KEY `FK_CarReservation` (`carID`),
  CONSTRAINT `FK_CarReservation` FOREIGN KEY (`carID`) REFERENCES `cardetail` (`carID`),
  CONSTRAINT `FK_CustomerReservation` FOREIGN KEY (`phoneNumber`) REFERENCES `user` (`phoneNumber`),
  CONSTRAINT `FK_ParkingReservation` FOREIGN KEY (`parkingID`) REFERENCES `parking` (`parkingID`),
  CONSTRAINT `FK_PaymentReservation` FOREIGN KEY (`paymentID`) REFERENCES `paymentdetail` (`paymentID`),
  CONSTRAINT `FK_StatusReservation` FOREIGN KEY (`statusID`) REFERENCES `revervationstatus` (`statusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revervationstatus`
--

DROP TABLE IF EXISTS `revervationstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revervationstatus` (
  `statusID` int NOT NULL AUTO_INCREMENT,
  `statusName` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`statusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revervationstatus`
--

LOCK TABLES `revervationstatus` WRITE;
/*!40000 ALTER TABLE `revervationstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `revervationstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `roleID` int NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) NOT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_SUPPLIER'),(2,'ROLE_CUSTOMER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialdate`
--

DROP TABLE IF EXISTS `specialdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialdate` (
  `specialDateID` int NOT NULL AUTO_INCREMENT,
  `startSpecialDate` timestamp NULL DEFAULT NULL,
  `endSpecialDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`specialDateID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialdate`
--

LOCK TABLES `specialdate` WRITE;
/*!40000 ALTER TABLE `specialdate` DISABLE KEYS */;
INSERT INTO `specialdate` VALUES (1,'2023-05-01 03:00:00','2023-05-10 11:00:00'),(2,'2023-06-15 01:30:00','2023-06-15 10:00:00'),(3,'2023-08-31 17:00:00','2023-09-30 16:59:59');
/*!40000 ALTER TABLE `specialdate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `phoneNumber` varchar(10) NOT NULL,
  `password` varchar(200) DEFAULT NULL,
  `fullName` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `identifyCard` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`phoneNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('0123456789','$2a$10$CovsRO2OYukwwURjLe1uGOYI1/z7jpjiKswqZfOK2.egrU2HGSzEi','Admin Parking','admin@gmail.com','111111111111'),('0946219139','$2a$10$CovsRO2OYukwwURjLe1uGOYI1/z7jpjiKswqZfOK2.egrU2HGSzEi','Hoang Tam','hoangtammht@gmail.com','111111111111');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userrole` (
  `roleID` int NOT NULL,
  `phoneNumber` varchar(10) NOT NULL,
  KEY `FK_UserRole` (`phoneNumber`),
  KEY `FK_RoleUser` (`roleID`),
  CONSTRAINT `FK_RoleUser` FOREIGN KEY (`roleID`) REFERENCES `role` (`roleID`),
  CONSTRAINT `FK_UserRole` FOREIGN KEY (`phoneNumber`) REFERENCES `user` (`phoneNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,'0123456789'),(1,'0946219139'),(2,'0946219139');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-23 22:02:15

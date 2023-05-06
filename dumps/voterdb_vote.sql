-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: voterdb
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vote` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `timestamp` datetime(6) DEFAULT NULL,
  `candidate_id` bigint DEFAULT NULL,
  `election_id` bigint DEFAULT NULL,
  `lista_id` bigint DEFAULT NULL,
  `voter_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsntn7i4l97xxwx420gb6ldpvn` (`candidate_id`),
  KEY `FK2pxxmx5nvain2p5v2hpjb3une` (`election_id`),
  KEY `FK3thwm9js94f12lkrf3j1nd2jy` (`lista_id`),
  KEY `FKbgqu449eayg16pkhliv4o0ejh` (`voter_id`),
  CONSTRAINT `FK2pxxmx5nvain2p5v2hpjb3une` FOREIGN KEY (`election_id`) REFERENCES `election` (`id`),
  CONSTRAINT `FK3thwm9js94f12lkrf3j1nd2jy` FOREIGN KEY (`lista_id`) REFERENCES `lista` (`id`),
  CONSTRAINT `FKbgqu449eayg16pkhliv4o0ejh` FOREIGN KEY (`voter_id`) REFERENCES `voter` (`id`),
  CONSTRAINT `FKsntn7i4l97xxwx420gb6ldpvn` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote`
--

LOCK TABLES `vote` WRITE;
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
INSERT INTO `vote` VALUES (1,'2023-05-06 13:19:25.324590',2,1,NULL,1),(2,'2023-05-06 13:19:25.324590',1,1,NULL,2),(3,'2023-05-06 13:19:25.324590',1,1,NULL,3),(4,'2023-05-06 13:19:25.324590',2,1,NULL,4),(5,'2023-05-06 13:19:25.324590',3,2,NULL,5),(6,'2023-05-06 13:19:25.324590',4,2,NULL,6);
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-06 14:21:58

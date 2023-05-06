-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: resultdb
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
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `id` bigint NOT NULL,
  `vote_count` int NOT NULL,
  `candidate_id` bigint DEFAULT NULL,
  `election_id` bigint DEFAULT NULL,
  `list_id` int DEFAULT NULL,
  `polling_station_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtclxv6fv00y1x7g0i965lhpq` (`candidate_id`),
  KEY `FKb7bqf5s2jc3cc0j79m8d05id` (`election_id`),
  KEY `FKmca3y1ptth6lg85cyodvs0y1r` (`list_id`),
  KEY `FKqigggvg155f0lyksnw6wrbn2i` (`polling_station_id`),
  CONSTRAINT `FKb7bqf5s2jc3cc0j79m8d05id` FOREIGN KEY (`election_id`) REFERENCES `election` (`id`),
  CONSTRAINT `FKmca3y1ptth6lg85cyodvs0y1r` FOREIGN KEY (`list_id`) REFERENCES `list` (`id`),
  CONSTRAINT `FKqigggvg155f0lyksnw6wrbn2i` FOREIGN KEY (`polling_station_id`) REFERENCES `polling_station` (`id`),
  CONSTRAINT `FKtclxv6fv00y1x7g0i965lhpq` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (1,1000,NULL,1,NULL,2),(2,100,NULL,2,NULL,1),(3,100,NULL,2,NULL,2);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-06 14:21:57

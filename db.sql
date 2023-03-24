-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: warehouse-application
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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Oleje'),(2,'Smary'),(3,'Wrzeciona'),(4,'Silniki'),(5,'Materiały eksploatacyjne'),(6,'Uszczelki i O-Ringi'),(7,'Filtry'),(8,'Części zamienne'),(9,'Pozostałe');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internal_order`
--

DROP TABLE IF EXISTS `internal_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internal_order` (
  `internal_order_id` bigint NOT NULL AUTO_INCREMENT,
  `order_date` datetime(6) DEFAULT NULL,
  `pick_date` datetime(6) DEFAULT NULL,
  `pick_up_location` varchar(255) DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`internal_order_id`),
  KEY `FK7sinxx5ikbgbbxse99s4458tc` (`status_id`),
  KEY `FKfyns2ilyjolaup2pw4sxyarmy` (`user_id`),
  CONSTRAINT `FK7sinxx5ikbgbbxse99s4458tc` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`),
  CONSTRAINT `FKfyns2ilyjolaup2pw4sxyarmy` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internal_order`
--

LOCK TABLES `internal_order` WRITE;
/*!40000 ALTER TABLE `internal_order` DISABLE KEYS */;
INSERT INTO `internal_order` VALUES (1,'2023-03-23 19:59:32.986826',NULL,'',1,1),(2,'2023-03-23 20:00:20.674458',NULL,'aplikacja',1,1),(3,'2023-03-24 10:07:45.660480',NULL,'',1,1);
/*!40000 ALTER TABLE `internal_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `internal_order_item`
--

DROP TABLE IF EXISTS `internal_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `internal_order_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `internal_order_id` bigint DEFAULT NULL,
  `material_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpnw7ntfwh39u4etic3g8tlexl` (`internal_order_id`),
  KEY `FK6fhfd1qx4r7k8smrmr3kr0i90` (`material_id`),
  CONSTRAINT `FK6fhfd1qx4r7k8smrmr3kr0i90` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`),
  CONSTRAINT `FKpnw7ntfwh39u4etic3g8tlexl` FOREIGN KEY (`internal_order_id`) REFERENCES `internal_order` (`internal_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internal_order_item`
--

LOCK TABLES `internal_order_item` WRITE;
/*!40000 ALTER TABLE `internal_order_item` DISABLE KEYS */;
INSERT INTO `internal_order_item` VALUES (1,1,1,2),(2,1,1,1),(3,2,2,6),(4,1,2,8),(5,2,2,3),(6,3,NULL,2),(7,1,NULL,1),(8,3,3,2),(9,1,3,1);
/*!40000 ALTER TABLE `internal_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `material` (
  `material_id` bigint NOT NULL AUTO_INCREMENT,
  `material_created` datetime(6) DEFAULT NULL,
  `material_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `materialean` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `material_manufacturer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `material_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `material_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `material_price` double DEFAULT NULL,
  `material_quantity` int DEFAULT NULL,
  `material_safety_stock` bigint DEFAULT NULL,
  `material_status` bit(1) DEFAULT NULL,
  `unit_of_measure` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_polish_ci DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `vendor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`material_id`),
  KEY `FKsrmnoix37w2el3y0r59c0d4fg` (`category_id`),
  KEY `FKcl6eaeve4b9yqadmudqe3ep39` (`vendor_id`),
  CONSTRAINT `FKcl6eaeve4b9yqadmudqe3ep39` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`vendor_id`),
  CONSTRAINT `FKsrmnoix37w2el3y0r59c0d4fg` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (1,'2023-02-26 17:24:17.691790','120505','729965','BESM18MG1-PSC12B-SO4G','CzujnikBESM18MG1-PSC12B-SO4G120505','F02S100006',4292.18,24,2,_binary '','sztuka',7,4),(2,'2023-02-26 17:24:27.514523','zam.BESQ08ZC-PSC30B-S49G162585BES01U4Sn=3mm','2397620','BESQ08MG-PSC30B-S49G','CZUJNIKBESQ08MG-PSC30B-S49G=BESQ08ZC-PSC30B-S49G','F02S100007',3293.86,47,2,_binary '','sztuka',2,4),(3,'2023-02-26 17:24:35.492948','150857','5245353','SME-8-S-LED-24','CZUJNIKSME-8-S-LED-24150857Festo','F02S100012',1879.46,55,2,_binary '','sztuka',7,5),(4,'2023-02-26 17:24:41.968456','NULL','6570005','T600.014-604-1','OsLonaT600.014-604-1STAMA','F02S100021',2855.97,3,1,_binary '','sztuka',2,8),(5,'2023-02-26 17:24:48.848466','NULL','3487707','1362450/T600.010-606-2','KololancuchaT600.010-606-2','F02S100024',3565.73,7,2,_binary '','sztuka',8,8),(6,'2023-02-26 17:24:53.988121','Decyzjaozakupiewgestiikierownikapozuzyciu.','1665473','G151.605-010=1262972/G134.605-116','LANCUCHG151.605-010=1262972/G134.605-116','F02S100026',281.3,7,2,_binary '','sztuka',4,8),(7,'2023-02-26 17:24:59.445405','NULL','3086521','6SN1118-0DJ21-0AA0','MODUL6SN1118-0DJ21-0AA0','F02S100039',5475.77,2,1,_binary '','sztuka',4,2),(8,'2023-02-26 17:25:04.538421','TosamocoF02S106382STAMA','5569226','6SN1118-0DM31-0AA0','MODUL6SN1118-0DM31-0AA0','F02S100041',6530.34,9,1,_binary '','sztuka',7,2),(9,'2023-02-26 17:25:13.719099','tosamocoF02S100199','3780429','6SN1118-0DG23-0AA1','MODUL6SN1118-0DG23-0AA1','F02S100043',6751.99,2,2,_binary '','sztuka',2,2),(10,'2023-02-26 17:25:39.827357','F02S102327','2558357','6SN1123-1AA00-0EA2','MODUL6SN1123-1AA00-0EA2','F02S100044',5891.86,1,1,_binary '','sztuka',2,2),(11,'2023-02-26 17:25:45.601140','NULL','3044458','6SN1145-1BA02-0CA1','MODUL6SN1145-1BA02-0CA1','F02S100045',4924.04,1,1,_binary '','sztuka',9,2),(12,'2023-02-26 17:25:51.145678','15226A708Stamasilownikdrzwi15226','1484571','DGO-40-865-PPV-A-BStamadrzwi','NAPEDDGO-40-865-PPV-A-B15226A708','F02S100047',3084.9,2,2,_binary '','sztuka',5,5),(13,'2023-02-26 17:25:56.236591','NULL','1004716','1133193/T600.010-159-4','OskaT600.010-159-4','F02S100051',463.05,5,2,_binary '','sztuka',4,8),(14,'2023-02-26 17:26:00.861483','1143561','8780374','T600.014-684-1','KlapamagazynuT600.014-684-1STAMA','F02S100064',3097.17,2,2,_binary '','sztuka',3,8),(15,'2023-02-26 17:26:05.538611','NULL','7930832','T600.013-940-3','PlytkaT600.013-940-3','F02S100065',2601.39,2,1,_binary '','sztuka',5,8),(16,'2023-02-26 17:26:10.913704','NULL','4996575','T600.014-902-3','PLYTKAT600.014-902-3','F02S100066',5203.19,10,1,_binary '','sztuka',8,8),(17,'2023-02-27 00:32:36.103754','1155285','5042310','T600.014-920-2','POKRYWASILOWNIKAT600.014-920-2','F02S100067',3033.31,3,1,_binary '','sztuka',9,8),(18,'2023-02-27 00:32:42.624460','NULL','8730553','DG36','Przekaznikcisn.DG36','F02S100075',2366.51,7,2,_binary '','sztuka',2,7),(19,'2023-02-27 00:32:49.001817','NULL','3664068','DG34','PRZEKAZNIKDG34','F02S100076',5779.33,3,2,_binary '','sztuka',3,7),(20,'2023-02-27 00:33:00.566242','NULL','8495391','DG35','PRZEKAZNIKDG35','F02S100077',5254.39,2,2,_binary '','sztuka',1,7),(21,'2023-02-27 00:33:06.135274','rolkadrzwiSTAMA','9014067','T600.008-926-4','ROLKAPROWADZACAT600.008-926-4','F02S100086',2311.31,2,1,_binary '','sztuka',8,8),(22,'2023-02-27 00:33:12.179021','NULL','2205717','SCIAGACZ1154843/T600.013-078-3','SCIAGACZ1154843/T600.013-078-3','F02S100088',1105.71,1,1,_binary '','sztuka',2,8),(23,'2023-02-27 00:33:17.468365','NULL','7281059','T600.013-795-3','SCIAGACZT600.013-795-3STAMA','F02S100089',1712.08,1,1,_binary '','sztuka',2,8),(24,'2023-02-27 00:33:23.009998','1143551','609442','T600.013-826-3','SCIAGACZT600.013-826-3','F02S100090',3244.24,3,2,_binary '','sztuka',3,8),(25,'2023-02-27 00:33:27.796135','NULL','4547889','T600.014.257.3','SCIAGACZT600.014-257-3','F02S100091',4649.25,3,1,_binary '','sztuka',7,8),(26,'2023-02-27 00:33:34.107694','STAMA','1858844','1FT6062-6AF71-3EA2','SILNIK1FT6062-6AF71-3EA2','F02S100092',510.33,2,1,_binary '','sztuka',3,2),(27,'2023-02-27 00:34:08.617862','NULL','8882750','1FT6062-6AF71-3AA0','SILNIK1FT6062-6AF71-3AA0SM-49','F02S100093',3636.26,1,1,_binary '','sztuka',8,2),(28,'2023-02-27 00:34:14.738241','STAMA','1270358','1FT6062-6AF71-3EB0','Silnik1FT6062-6AF71-3EB0','F02S100095',2918.46,1,1,_binary '','sztuka',1,2),(29,'2023-02-27 00:34:20.137153','NULL','8583135','1FT6082-8AF71-3EA2','SILNIK1FT6082-8AF71-3EA2','F02S100097',1929.36,1,1,_binary '','sztuka',5,2),(30,'2023-02-27 00:34:25.716779','STAMA','8918865','1FT6084-1AF71-1EH1','SILNIK1FT6084-1AF71-1EH1','F02S100099',3811.64,3,1,_binary '','sztuka',4,2),(31,'2023-02-27 00:34:31.253642','STAMA','7518191','1FT6084-1AF71-3EG1','SILNIK1FT6084-1AF71-3EG1','F02S100100',10581.2,1,1,_binary '','sztuka',1,2),(32,'2023-02-27 00:34:36.385529','OSZSTAMA','2330251','1FT6086-1AF71-3EH1','SILNIK1FT6086-1AF71-3EH1','F02S100101',3957.36,1,1,_binary '','sztuka',8,2),(33,'2023-02-27 00:34:41.831391','OSZSTAMA','8358490','1FT6086-1AF71-1EH1','SILNIK1FT6086-1AF71-1EH1','F02S100102',1730,3,1,_binary '','sztuka',1,2),(34,'2023-03-11 21:24:49.320861','','','test','mat test','F02Stest',123,3,NULL,_binary '','sztuka',4,3),(35,'2023-03-17 18:59:19.689058','Uszczelka do silnika przed sprzęgłem na maszynę SM23','4654646665648646464','Hansa','Uszczelka do SIlnika na SM23','F02S137999',2.96,31,5,_binary '','sztuka',6,6),(36,'2023-03-17 19:19:09.100460','','','Bosh','Sprężarka T56','F02S137998',13789,1,NULL,_binary '','sztuka',8,10);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_item_id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `material_id` bigint DEFAULT NULL,
  `orders_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `FKmhcpybpshfxpnr9twu5488gbh` (`material_id`),
  KEY `FKea29bb770t1s99pp2ngvhgwnt` (`orders_id`),
  CONSTRAINT `FKea29bb770t1s99pp2ngvhgwnt` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`orders_id`),
  CONSTRAINT `FKmhcpybpshfxpnr9twu5488gbh` FOREIGN KEY (`material_id`) REFERENCES `material` (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (31,5,1,2),(32,10,2,2),(33,15,3,2),(34,5,1,3),(35,10,2,3),(36,15,3,3),(37,2,2,4),(38,2,1,4),(39,1,6,4),(40,3,2,5),(41,2,5,5),(42,4,8,5),(43,1,34,6),(44,1,34,7),(45,1,8,8),(46,1,6,8),(47,1,12,8),(48,1,12,9),(49,1,8,10),(50,1,13,10),(51,1,8,11),(52,1,13,11),(53,1,6,12),(54,1,8,13),(55,1,13,13),(56,1,3,14),(57,1,3,15),(58,1,6,16),(59,1,7,17),(60,1,6,17),(61,1,2,18),(62,1,3,18),(65,1,5,19),(66,2,3,19),(67,2,2,NULL),(68,1,4,NULL),(69,1,3,NULL),(70,2,2,20),(71,1,4,20),(72,1,3,20),(73,1,6,NULL),(74,1,9,NULL),(75,1,6,21),(76,1,9,21),(77,1,4,NULL),(78,1,3,NULL),(79,1,4,22),(80,1,3,22),(81,10,16,NULL),(82,10,16,23),(83,1,14,NULL),(84,2,17,NULL),(85,1,14,24),(86,2,17,24),(87,1,4,NULL),(88,1,3,NULL),(89,1,4,25),(90,1,3,25),(91,1,1,NULL),(92,1,1,26),(93,1,2,NULL),(94,1,19,NULL),(95,1,2,27),(96,1,19,27),(97,1,14,NULL),(98,1,15,NULL),(99,1,14,28),(100,1,15,28),(101,1,5,NULL),(102,1,6,NULL),(103,1,5,29),(104,1,6,29),(105,2,2,NULL),(106,2,1,NULL),(107,2,2,30),(108,2,1,30);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orders_id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `delivery_date` date DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `order_total` double DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`orders_id`),
  KEY `FKnoettwqr56yaai4i8nwxg4huo` (`status_id`),
  KEY `FKel9kyl84ego2otj2accfd8mr7` (`user_id`),
  CONSTRAINT `FKel9kyl84ego2otj2accfd8mr7` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKnoettwqr56yaai4i8nwxg4huo` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,'test','2023-03-16','2023-03-16','1f344584-e16f-4fa0-be1f-bc7ef9a6bc67',82591.4,1,1),(3,'test','2023-03-16','2023-03-16','5ad2a57d-b717-457f-b7ad-7f2596c34026',82591.4,2,1),(4,'test',NULL,'2023-03-16','29f8d91c-78b3-435e-a1d4-700fd9585f6e',15453.380000000001,3,1),(5,'','2023-03-21','2023-03-19','faa3df07-f2a4-49ea-9631-a23908ba45cc',43134.4,2,1),(6,'','2023-03-20','2023-03-19','688703e9-7b42-42e4-8e1b-708f2ce4301c',123,2,1),(7,'','2023-03-22','2023-03-19','21128574-91b1-4019-a9b4-ef354f368dde',123,2,1),(8,'','2023-03-21','2023-03-19','4499fb57-13da-4d9b-add9-358e13f445ae',9896.54,2,1),(9,'','2023-03-19','2023-03-19','5926db2d-d8a2-4793-ae84-948b344d53a2',3084.9,2,1),(10,'','2023-03-21','2023-03-19','6ecded00-18fa-4b54-ba09-fba382b0fc57',6993.39,2,3),(11,'komentarz do zamówienia','2023-03-21','2023-03-19','c4bcea2a-a2c4-4d5b-a16a-8aca46a5e4e7',6993.39,2,1),(12,'zamówienie in cognito','2023-03-20','2023-03-19','c7e4083f-9fff-4595-b926-13b99bbc8438',281.3,2,1),(13,'Nowe zamówienie','2023-03-21','2023-03-19','e5234a28-4ec3-4e6f-ace0-2f820de245ed',6993.39,2,1),(14,'','2023-03-20','2023-03-19','a3500a41-7187-4e62-af31-e085281cb219',1879.46,2,1),(15,'','2023-03-20','2023-03-19','c0e8c02f-c99b-44d8-be27-8585f3ee75a2',1879.46,2,1),(16,'','2023-03-19','2023-03-19','fd27a464-1b7d-4d49-a63a-4f0892aff192',281.3,2,1),(17,'','2023-03-21','2023-03-19','12014b9b-18da-4c50-9c22-848188b372ed',5757.070000000001,2,1),(18,'nowe zamówienie','2023-03-21','2023-03-19','8c8e7f2e-3196-4d82-ab58-1fc5d79a65e0',5173.32,2,1),(19,'','2023-03-21','2023-03-21','9505e98f-ab73-4121-a5e3-164a29b237aa',7324.65,2,1),(20,'','2023-03-22','2023-03-22','a542adca-e9bf-4284-ade0-681951ce2bd6',11323.150000000001,2,1),(21,'','2023-03-22','2023-03-22','b15e39ee-9193-4e43-870f-bf558388df3d',7033.29,2,1),(22,'','2023-03-22','2023-03-22','6b149e28-478c-493a-9f1a-a8968a55b447',4735.43,2,1),(23,'','2023-03-22','2023-03-22','ddf73901-c467-4bad-be7f-88307375a9d7',52031.899999999994,2,1),(24,'','2023-03-22','2023-03-22','910d7723-5d05-4166-94a9-0a5fc373d602',9163.79,2,1),(25,'',NULL,'2023-03-22','ee74c4a9-8531-4ab0-a097-d07b2093dc37',4735.43,3,1),(26,'',NULL,'2023-03-22','0931a02a-320d-41da-97c9-7091aeac940a',4292.18,3,1),(27,'',NULL,'2023-03-22','d805eb4d-5555-485f-83e7-c604f7c645c1',9073.19,3,1),(28,'nowe zamoówienie','2023-03-22','2023-03-22','acb1ee1d-ead2-4622-b3ba-2f1601ff40f2',5698.5599999999995,2,1),(29,'','2023-03-22','2023-03-22','18cd5a35-04ff-4784-8379-d617e862c5e9',3847.03,2,1),(30,'',NULL,'2023-03-24','cc990a49-ac41-4e2c-bcfe-cb3e6828ab01',15172.080000000002,1,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_equipment`
--

DROP TABLE IF EXISTS `personal_equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personal_equipment` (
  `personal_equipment_id` bigint NOT NULL AUTO_INCREMENT,
  `invoice` varchar(255) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `purchase_date` datetime(6) DEFAULT NULL,
  `purchase_price` double DEFAULT NULL,
  `serial_number` varchar(255) DEFAULT NULL,
  `warranty_date` datetime(6) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`personal_equipment_id`),
  KEY `FK3lceday4pg9viw4ky2o6vk75m` (`user_id`),
  CONSTRAINT `FK3lceday4pg9viw4ky2o6vk75m` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_equipment`
--

LOCK TABLES `personal_equipment` WRITE;
/*!40000 ALTER TABLE `personal_equipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `personal_equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES (1,'2023-03-16 11:57:09.771877','67991342-f713-4c49-bdf4-e7dc7ded430d'),(2,'2023-03-16 12:50:35.003913','3cb93485-4fae-4e90-a6a9-6063e53169c3'),(3,'2023-03-16 13:31:46.835462','4c083bb5-881f-4538-a0db-6aa25c99c866'),(7,'2023-03-19 08:50:26.879164','1af046f4-dbd4-42e9-bd9e-08fadf99dd02'),(8,'2023-03-19 08:51:41.095851','89aea2a8-e123-4840-b5eb-7b88297d7a87'),(9,'2023-03-19 09:23:25.907492','1c3d9c29-91d6-4894-b5c5-8a23c432eaab'),(10,'2023-03-19 09:23:55.648058','3a01e008-460a-4fd0-bc1a-90ae520bb94f'),(11,'2023-03-19 09:24:23.952255','2715ac0e-a3b1-4289-81d7-5d2d55baad1f'),(12,'2023-03-19 09:29:17.936290','29897222-f056-4407-80a5-f0ea8eb67025'),(13,'2023-03-19 09:32:22.716490','ff1eb872-7efa-4d94-820e-e8a467c68798'),(14,'2023-03-19 09:40:05.815367','ad00ef6d-056f-4f3e-a00a-029ca82ff852'),(15,'2023-03-19 09:54:23.700969','fe015030-34ec-49b2-820e-8d2037dd1ee8'),(16,'2023-03-19 09:54:41.837433','1ee0c719-b465-4e3b-b9d1-60635ec3a73a'),(17,'2023-03-19 10:59:43.505351','8cd614d7-1176-4df1-b17f-d282d91675e2'),(18,'2023-03-19 12:08:25.856604','8a232af0-9291-492b-a79c-6a0be992d640'),(19,'2023-03-19 12:13:21.245521','6a09102a-c39c-4202-b5b4-d998d8c3682a'),(21,'2023-03-19 12:18:56.640807','a4d37eba-3197-4827-8ab0-7084060b072e'),(22,'2023-03-19 12:23:27.670558','5a04f130-9dc9-4bd7-adc5-f140ede04182'),(23,'2023-03-19 12:27:37.753941','6acdddc9-0a0d-480d-b5ac-a38d318718c3'),(24,'2023-03-19 12:35:11.930443','31e82d46-fdfe-4ae9-abc4-d873df4422dd'),(25,'2023-03-19 12:37:51.238490','368d113d-948c-4750-9f1c-19d24e26d8cd'),(26,'2023-03-19 12:38:19.609304','d1596a3f-257b-430b-911c-61155f3e3507'),(27,'2023-03-19 12:38:41.414447','d9c0c0c8-b296-4f80-82a4-d0c2fdf57b1f'),(28,'2023-03-19 12:48:10.141644','28678eda-30a9-4544-a2aa-a80c83a29e16'),(29,'2023-03-19 12:54:19.013613','848430f1-41e4-4c17-b67b-a2d437bc448b'),(30,'2023-03-19 15:30:57.437810','1e8cc0dc-73a2-435c-84f0-313dce6292c6'),(31,'2023-03-19 15:31:19.457075','457c50ba-a217-49cd-8764-2c326a62222b'),(33,'2023-03-19 16:30:19.212763','3301b8f6-75a9-46a1-9c20-4b2f616c6d17'),(34,'2023-03-19 16:33:19.468757','69ca5355-1a1e-43fc-9eb2-b27d9ac380c9'),(35,'2023-03-19 16:48:16.668933','913224ed-1321-4faa-8be2-f48a67346825'),(36,'2023-03-19 16:59:18.888689','0c434bdf-c872-4bb9-84a2-643ffd5f83b1'),(37,'2023-03-19 17:08:03.034365','ce4d597f-0d56-4b4c-a6e2-4369d3b4e881'),(39,'2023-03-19 17:29:45.608314','b9178862-987c-4a14-a997-c79ed1101df9'),(40,'2023-03-19 17:30:30.411472','9e328d7f-bcb3-49bc-9b8a-2e3c97ef41e0'),(41,'2023-03-19 17:35:28.147181','c10ac28d-f3a4-484d-bf40-6993938a03be'),(42,'2023-03-19 17:45:26.306154','bef146af-eba3-4d97-be16-515eee5e278c'),(43,'2023-03-19 17:51:27.503232','df57fdcb-abfa-4a41-ac4c-1501c4e9cfdb'),(45,'2023-03-19 18:01:13.220024','a8921065-fab1-4ce1-a788-3e5f993eb548'),(46,'2023-03-19 18:09:09.630113','dbc0d15d-07b6-4cbe-8d95-e5af7044fed8'),(47,'2023-03-19 18:15:21.589889','ca02af4e-08ff-4426-899d-93116b1f56fa'),(48,'2023-03-19 18:22:27.940357','d54adb64-bdf0-44f4-9fe2-3333869126ac'),(51,'2023-03-19 19:23:37.745759','2a643d28-db88-4814-bf95-3bab45c75bf7'),(54,'2023-03-19 20:12:49.870540','a09209b7-3005-480a-a007-918cf78d644d'),(56,'2023-03-20 09:39:40.181636','a20ad5c9-e92d-4cd0-bbf1-fdacfe77b600'),(60,'2023-03-20 18:36:15.091305','84c527f8-cc42-48a1-b048-7739c65e5a9b'),(62,'2023-03-21 08:20:03.578325','5efcb5d2-993f-416d-8823-ce05336d1e52'),(74,'2023-03-22 10:01:53.517344','b7fc04ca-e36c-4d93-8413-a4b3f0c61a39'),(81,'2023-03-23 09:57:25.841425','1963b3be-6c4c-4d92-8d23-b372d86b77ef'),(84,'2023-03-23 18:57:48.491306','0950efd4-33ca-424b-8794-698a6062b89f'),(87,'2023-03-24 10:16:43.609953','a64be9a6-9596-42ef-be2a-769d552b935c'),(88,'2023-03-24 10:21:17.432585','4d77f3f6-02d6-4d46-8133-4a06ee1966dc'),(89,'2023-03-24 10:30:27.234854','98644f85-16f5-473e-b196-73a59bdb3891');
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Utworzono'),(2,'Dostarczono'),(3,'Anulowano'),(4,'Gotowe'),(5,'Odebrano'),(6,'Wydane');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime(6) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK79keudebybjlldk2o4i0nwqev` (`user_user_id`),
  CONSTRAINT `FK79keudebybjlldk2o4i0nwqev` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `created` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2023-02-26 17:19:14.951044','admin@mail.com',_binary '','Krzysztof','Chwedziak','$2a$10$lEKyUbTkFtWLDzXERPxMuOUg607w7GpClMX1VgY5UHeiNTjMsBw4G'),(2,'2023-02-26 21:19:47.204404','dsgqggweqr@wp.pl',_binary '\0','nbnn','rnrnrn','$2a$10$bFdWFxdRR4Lc9JiEm.cyfOyKgYw5NyV5pjzUpo13Eto1jhfFTBgjO'),(3,'2023-02-26 21:20:37.101380','test@test.pl',_binary '','test','test','$2a$10$wqQozbbuhjKJUpLfps/LkekdNj0tb1lzgehkxU95vSs4eIpJk.v5y'),(4,'2023-03-10 20:39:22.382128','nowy@mail.com',_binary '','Janusz','Kowalski','$2a$10$FJ3CbRNe4iQK5FsJQeNjL.aXumZpv4uWpRUMqAkT3hzjrVEflCoCy');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendor` (
  `vendor_id` bigint NOT NULL AUTO_INCREMENT,
  `vendor_address` varchar(255) DEFAULT NULL,
  `vendor_bank_account` varchar(255) DEFAULT NULL,
  `vendor_bank_name` varchar(255) DEFAULT NULL,
  `vendor_city` varchar(255) DEFAULT NULL,
  `vendor_country` varchar(255) DEFAULT NULL,
  `vendor_created` datetime(6) DEFAULT NULL,
  `vendor_email` varchar(255) DEFAULT NULL,
  `vendor_krs` varchar(255) DEFAULT NULL,
  `vendor_name` varchar(255) DEFAULT NULL,
  `vendor_nip` varchar(255) DEFAULT NULL,
  `vendor_phone` varchar(255) DEFAULT NULL,
  `vendor_postal_code` varchar(255) DEFAULT NULL,
  `vendor_regon` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vendor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES (1,' ul. Wiejska  12A  ',' PL12 1234 5678 9101 1112 3456 7890',' PKO Bank Polski',' Warszawa',' Polska','2023-02-26 15:00:00.000000',' firmaXYZ@email.com','123456',' REXROTH',' 123-456-78-90',' 555-555-555',' 12-345','123456789'),(2,' ul. Krakowska 15  ',' PL12 1234 5678 9101 1112 3456 7891',' mBank',' Kraków',' Polska','2023-02-26 15:00:01.000000',' firmaABC@email.com','123457',' SIEMENS',' 123-456-78-91',' 555-555-556',' 34-567','123456780'),(3,' ul. Kłodawska 1  ',' PL12 1234 5678 9101 1112 3456 7892',' ING Bank Śląski',' Gdańsk',' Polska','2023-02-26 15:00:02.000000',' firmaDEF@email.com','123458',' SMC',' 123-456-78-92',' 555-555-557',' 67-890','123456781'),(4,' ul. Nowa 20  ',' PL12 1234 5678 9101 1112 3456 7893',' Alior Bank',' Wrocław',' Polska','2023-02-26 15:00:03.000000',' firmaGHI@email.com','123459',' BALLUFF',' 123-456-78-93',' 555-555-558',' 56-789','123456782'),(5,' ul. Stara 25  ',' PL12 1234 5678 9101 1112 3456 7894',' Bank Millennium',' Poznań',' Polska','2023-02-26 15:00:04.000000',' firmaJKL@email.com','123450',' FESTO',' 123-456-78-94',' 555-555-559',' 78-901','123456783'),(6,' ul. Nowolipie 30  ',' PL12 1234 5678 9101 1112 3456 7895',' PKO Bank Polski',' Łódź',' Polska','2023-02-26 15:00:05.000000',' firmaMNO@email.com','123451',' HANSA-FLEX',' 123-456-78-95',' 555-555-560',' 90-123','123456784'),(7,' ul. Stare Miasto 35  ',' PL12 1234 5678 9101 1112 3456 7896',' mBank',' Katowice',' Polska','2023-02-26 15:00:06.000000',' firmaPQR@email.com','123452',' HAWE',' 123-456-78-96',' 555-555-561',' 23-456','123456785'),(8,' ul. Nowy Świat 40  ',' PL12 1234 5678 9101 1112 3456 7897',' Bank Zachodni WBK',' Kraków',' Polska','2023-02-26 15:00:07.000000',' firmaSTU@email.com','123453',' STAMA',' 123-456-78-97',' 555-555-562',' 34-567','123456786'),(9,' ul. Krakowska 45  ',' PL12 1234 5678 9101 1112 3456 1444',' Getin Noble Bank',' Szczecin',' Polska','2023-02-26 15:00:08.000000',' firmaVWX@email.com','123454',' AVENTICS',' 123-456-78-98',' 555-555-563',' 45-678','123456787'),(10,' ul. Wrocławska 50  ',' PL12 1234 5678 9101 1112 3456 7899',' Alior Bank',' Wrocław',' Polska','2023-02-26 15:00:09.000000',' firmaYZX@email.com','123455',' SW',' 123-456-78-99',' 555-555-564',' 56-789','123456788'),(11,' ul. Krakowiacka 16  ',' PL12 1234 5678 9101 1112 3400 7898',' PKO Bank',' Kraków',' Polska','2023-02-26 15:00:10.000000',' firmaZY2@email.com','123385',' SICK',' 123-456-79-01',' 555-555-565',' 66-790','123456714'),(12,'Kamieńskiego 555','PL31165541561651651651651654','mBank S.A','Wrocław','Wrocław','2023-03-18 16:43:34.447418','januszex@business.com','48489213','Januszex','16516849684651','+48690784147','50-500','48484');
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-24 11:38:46

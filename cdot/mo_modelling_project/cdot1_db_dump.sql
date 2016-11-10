-- MySQL dump 10.13  Distrib 5.6.28, for osx10.8 (x86_64)
--
-- Host: localhost    Database: cdot1
-- ------------------------------------------------------
-- Server version	5.6.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ANNOTATION`
--

DROP TABLE IF EXISTS `ANNOTATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ANNOTATION` (
  `AAID` bigint(20) NOT NULL,
  `DISCRIMINATOR` varchar(20) NOT NULL,
  `ENTITY` varchar(100) DEFAULT NULL,
  `MODTIME` bigint(20) DEFAULT NULL,
  `NOTES` varchar(250) DEFAULT NULL,
  `WHO` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`AAID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ANNOTATION`
--

LOCK TABLES `ANNOTATION` WRITE;
/*!40000 ALTER TABLE `ANNOTATION` DISABLE KEYS */;
/*!40000 ALTER TABLE `ANNOTATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Alert`
--

DROP TABLE IF EXISTS `Alert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Alert` (
  `ENTITY` varchar(100) NOT NULL,
  `DISCRIMINATOR` varchar(30) NOT NULL,
  `ID` int(11) DEFAULT NULL,
  `GROUPNAME` varchar(100) DEFAULT NULL,
  `CATEGORY` varchar(100) DEFAULT NULL,
  `SEVERITY` int(11) DEFAULT NULL,
  `PREVIOUSSEVERITY` int(11) DEFAULT NULL,
  `CREATETIME` bigint(20) DEFAULT NULL,
  `MODTIME` bigint(20) DEFAULT NULL,
  `MMESSAGE` varchar(250) DEFAULT NULL,
  `SOURCE` varchar(100) DEFAULT NULL,
  `WHO` varchar(100) DEFAULT NULL,
  `WEBNMS` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ENTITY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Alert`
--

LOCK TABLES `Alert` WRITE;
/*!40000 ALTER TABLE `Alert` DISABLE KEYS */;
INSERT INTO `Alert` VALUES ('172.24.14.0','Alert',67,NULL,'Topology',2,-1,1478254649103,1478254649103,'At least one node on this net is in failure state.','172.24.14.0',NULL,NULL),('172.24.14.105','Alert',736,'172.24.14.105','Topology',5,2,1478271154423,1478776183126,'Node clear.  No failures on this node.','172.24.14.105',NULL,NULL),('172.24.14.112','Alert',408,'172.24.14.112','Topology',2,-1,1478263595352,1478263595352,'Node failure.  This probably means one or more interfaces have failed.','172.24.14.112',NULL,NULL),('172.24.14.115','Alert',508,'172.24.14.115','Topology',2,-1,1478267392071,1478267392071,'Node failure.  This probably means one or more interfaces have failed.','172.24.14.115',NULL,NULL),('172.24.14.149','Alert',591,'172.24.14.149','Topology',2,5,1478263695390,1478271546443,'Node failure.  This probably means one or more interfaces have failed.','172.24.14.149',NULL,NULL),('172.24.14.152','Alert',790,'172.24.14.152','Topology',5,2,1478272043523,1478776493438,'Node clear.  No failures on this node.','172.24.14.152',NULL,NULL),('172.24.14.154','Alert',360,'172.24.14.154','Topology',2,-1,1478260424416,1478260424416,'Node failure.  This probably means one or more interfaces have failed.','172.24.14.154',NULL,NULL),('172.24.14.171','Alert',764,'172.24.14.171','Topology',5,2,1478260115008,1478776308216,'Node clear.  No failures on this node.','172.24.14.171',NULL,NULL),('172.24.14.178','Alert',768,'172.24.14.178','Topology',5,2,1478263735394,1478776328048,'Node clear.  No failures on this node.','172.24.14.178',NULL,NULL),('172.24.14.191','Alert',607,'172.24.14.191','Topology',2,-1,1478271724553,1478271724553,'Node failure.  This probably means one or more interfaces have failed.','172.24.14.191',NULL,NULL),('172.24.14.226','Alert',800,'172.24.14.226','Topology',5,2,1478264414960,1478776542992,'Node clear.  No failures on this node.','172.24.14.226',NULL,NULL),('172.24.14.238','Alert',802,'172.24.14.238','Topology',5,2,1478272076737,1478776563023,'Node clear.  No failures on this node.','172.24.14.238',NULL,NULL),('172.24.14.252','Alert',819,'172.24.14.252','Topology',5,2,1478260965176,1478776683258,'Node clear.  No failures on this node.','172.24.14.252',NULL,NULL),('172.24.14.28','Alert',692,'172.24.14.28','Topology',5,2,1478263420972,1478776013086,'Node clear.  No failures on this node.','172.24.14.28',NULL,NULL),('172.24.14.56','Alert',712,'172.24.14.56','Topology',5,2,1478256289937,1478776083098,'Node clear.  No failures on this node.','172.24.14.56',NULL,NULL),('172.24.14.67','Alert',784,'172.24.14.67','Topology',5,2,1478259978825,1478776423056,'Node clear.  No failures on this node.','172.24.14.67',NULL,NULL),('172.24.14.84','Alert',724,'172.24.14.84','Topology',5,2,1478267155567,1478776148032,'Node clear.  No failures on this node.','172.24.14.84',NULL,NULL),('172.24.14.92','Alert',728,'172.24.14.92','Topology',5,2,1478263566169,1478776158057,'Node clear.  No failures on this node.','172.24.14.92',NULL,NULL),('abdul-0436.csez.zohocorpin.com','Alert',778,'abdul-0436.csez.zohocorpin.com','Topology',5,2,1478271746575,1478776373177,'Node clear.  No failures on this node.','abdul-0436.csez.zohocorpin.com',NULL,NULL),('abdul-zt24.csez.zohocorpin.com','Alert',559,'abdul-zt24.csez.zohocorpin.com','Topology',2,5,1478263540925,1478271102771,'Node failure.  This probably means one or more interfaces have failed.','abdul-zt24.csez.zohocorpin.com',NULL,NULL),('abhi-3378.csez.zohocorpin.com','Alert',659,'abhi-3378.csez.zohocorpin.com','Topology',2,-1,1478277382344,1478277382344,'Node failure.  This probably means one or more interfaces have failed.','abhi-3378.csez.zohocorpin.com',NULL,NULL),('admp-test1.csez.zohocorpin.com','Alert',786,'admp-test1.csez.zohocorpin.com','Topology',5,2,1478267202879,1478776433143,'Node clear.  No failures on this node.','admp-test1.csez.zohocorpin.com',NULL,NULL),('ajay-1385.csez.zohocorpin.com','Alert',428,'ajay-1385.csez.zohocorpin.com','Topology',2,-1,1478263821712,1478263821712,'Node failure.  This probably means one or more interfaces have failed.','ajay-1385.csez.zohocorpin.com',NULL,NULL),('amarnath-0642.csez.zohocorpin.com','Alert',491,'amarnath-0642.csez.zohocorpin.com','Topology',2,5,1478256414919,1478267237033,'Node failure.  This probably means one or more interfaces have failed.','amarnath-0642.csez.zohocorpin.com',NULL,NULL),('amritha-3867.csez.zohocorpin.com','Alert',685,'amritha-3867.csez.zohocorpin.com','Topology',5,2,1478276150520,1478775988133,'Node clear.  No failures on this node.','amritha-3867.csez.zohocorpin.com',NULL,NULL),('android-6d556f7960b13da.csez.zohocorpin.com','Alert',639,'android-6d556f7960b13da.csez.zohocorpin.com','Topology',2,5,1478264439381,1478272087735,'Node failure.  This probably means one or more interfaces have failed.','android-6d556f7960b13da.csez.zohocorpin.com',NULL,NULL),('android-6da4f6e8432f2ea.csez.zohocorpin.com','Alert',505,'android-6da4f6e8432f2ea.csez.zohocorpin.com','Topology',2,-1,1478267363595,1478267363595,'Node failure.  This probably means one or more interfaces have failed.','android-6da4f6e8432f2ea.csez.zohocorpin.com',NULL,NULL),('android-8f4bc429763adb11.csez.zohocorpin.com','Alert',366,'android-8f4bc429763adb11.csez.zohocorpin.com','Topology',2,-1,1478260814552,1478260814552,'Node failure.  This probably means one or more interfaces have failed.','android-8f4bc429763adb11.csez.zohocorpin.com',NULL,NULL),('android-d9e08dc4ead46367.csez.zohocorpin.com','Alert',219,'android-d9e08dc4ead46367.csez.zohocorpin.com','Topology',2,-1,1478256374877,1478256374877,'Node failure.  This probably means one or more interfaces have failed.','android-d9e08dc4ead46367.csez.zohocorpin.com',NULL,NULL),('anu-4114.csez.zohocorpin.com','Alert',811,'anu-4114.csez.zohocorpin.com','Topology',5,2,1478268084386,1478776623185,'Node clear.  No failures on this node.','anu-4114.csez.zohocorpin.com',NULL,NULL),('aravind-0717.csez.zohocorpin.com','Alert',694,'aravind-0717.csez.zohocorpin.com','Topology',5,2,1478277393441,1478776023185,'Node clear.  No failures on this node.','aravind-0717.csez.zohocorpin.com',NULL,NULL),('aravinds6splus.csez.zohocorpin.com','Alert',794,'aravinds6splus.csez.zohocorpin.com','Topology',5,2,1478260590910,1478776513264,'Node clear.  No failures on this node.','aravinds6splus.csez.zohocorpin.com',NULL,NULL),('aravinth-3063.csez.zohocorpin.com','Alert',579,'aravinth-3063.csez.zohocorpin.com','Topology',2,-1,1478271513487,1478271513487,'Node failure.  This probably means one or more interfaces have failed.','aravinth-3063.csez.zohocorpin.com',NULL,NULL),('arivalagan-2168.csez.zohocorpin.com','Alert',792,'arivalagan-2168.csez.zohocorpin.com','Topology',5,2,1478272054504,1478776503094,'Node clear.  No failures on this node.','arivalagan-2168.csez.zohocorpin.com',NULL,NULL),('arun-2286.csez.zohocorpin.com','Alert',796,'arun-2286.csez.zohocorpin.com','Topology',5,2,1478264369904,1478776523136,'Node clear.  No failures on this node.','arun-2286.csez.zohocorpin.com',NULL,NULL),('arun-3857.csez.zohocorpin.com','Alert',804,'arun-3857.csez.zohocorpin.com','Topology',5,2,1478272076690,1478776583050,'Node clear.  No failures on this node.','arun-3857.csez.zohocorpin.com',NULL,NULL),('arunsubhash-0371.csez.zohocorpin.com','Alert',722,'arunsubhash-0371.csez.zohocorpin.com','Topology',5,2,1478271080451,1478776118026,'Node clear.  No failures on this node.','arunsubhash-0371.csez.zohocorpin.com',NULL,NULL),('ashish-4086.csez.zohocorpin.com','Alert',714,'ashish-4086.csez.zohocorpin.com','Topology',5,2,1478271069468,1478776088061,'Node clear.  No failures on this node.','ashish-4086.csez.zohocorpin.com',NULL,NULL),('aswath-pt773.csez.zohocorpin.com','Alert',742,'aswath-pt773.csez.zohocorpin.com','Topology',5,2,1478271198672,1478776223059,'Node clear.  No failures on this node.','aswath-pt773.csez.zohocorpin.com',NULL,NULL),('bala-2606.csez.zohocorpin.com','Alert',665,'bala-2606.csez.zohocorpin.com','Topology',2,-1,1478277393450,1478277393450,'Node failure.  This probably means one or more interfaces have failed.','bala-2606.csez.zohocorpin.com',NULL,NULL),('BE_172.24.14.34:.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0','Alert',29,NULL,'NMSManagement',1,-1,1478254205511,1478254205511,'Threshold exceeded :  Value: 100.0, Data: JVMPD_BE_16500_MonitorMemory : BE_172.24.14.34 : .1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0, Threshold Type: max Critical Threshold: 80.0 Critical Rearm Value: 70.0','BE_172.24.14.34',NULL,NULL),('BE_172.24.14.34:.1.3.6.1.4.1.42.2.145.3.163.1.1.3.1.0','Alert',28,NULL,'NMSManagement',1,-1,1478254205510,1478254205510,'Threshold exceeded :  Value: 133.0, Data: JVMPD_BE_16500_MonitorThread : BE_172.24.14.34 : .1.3.6.1.4.1.42.2.145.3.163.1.1.3.1.0, Threshold Type: max Critical Threshold: 125.0 Critical Rearm Value: 105.0','BE_172.24.14.34',NULL,NULL),('bharath-2679.csez.zohocorpin.com','Alert',681,'bharath-2679.csez.zohocorpin.com','Topology',5,2,1478276139464,1478775963102,'Node clear.  No failures on this node.','bharath-2679.csez.zohocorpin.com',NULL,NULL),('bhargavi-2458.csez.zohocorpin.com','Alert',601,'bhargavi-2458.csez.zohocorpin.com','Topology',2,-1,1478271713434,1478271713434,'Node failure.  This probably means one or more interfaces have failed.','bhargavi-2458.csez.zohocorpin.com',NULL,NULL),('boobala-0048.csez.zohocorpin.com','Alert',738,'boobala-0048.csez.zohocorpin.com','Topology',5,2,1478260005017,1478776198113,'Node clear.  No failures on this node.','boobala-0048.csez.zohocorpin.com',NULL,NULL),('chithu-0706.csez.zohocorpin.com','Alert',569,'chithu-0706.csez.zohocorpin.com','Topology',2,-1,1478271187653,1478271187653,'Node failure.  This probably means one or more interfaces have failed.','chithu-0706.csez.zohocorpin.com',NULL,NULL),('Client_172.24.14.34:.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0','Alert',453,NULL,'NMSManagement',1,-1,1478266395669,1478266395669,'Threshold exceeded :  Value: 85.0, Data: JVMPD_CLIENT_14500_MonitorMemory : Client_172.24.14.34 : .1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0, Threshold Type: max Critical Threshold: 80.0 Critical Rearm Value: 70.0','Client_172.24.14.34',NULL,NULL),('damodhar-1003.csez.zohocorpin.com','Alert',511,'damodhar-1003.csez.zohocorpin.com','Topology',2,-1,1478267406719,1478267406719,'Node failure.  This probably means one or more interfaces have failed.','damodhar-1003.csez.zohocorpin.com',NULL,NULL),('dheeraj-1090.csez.zohocorpin.com','Alert',807,'dheeraj-1090.csez.zohocorpin.com','Topology',5,2,1478264452788,1478776603126,'Node clear.  No failures on this node.','dheeraj-1090.csez.zohocorpin.com',NULL,NULL),('drevathy-0847.csez.zohocorpin.com','Alert',625,'drevathy-0847.csez.zohocorpin.com','Topology',2,-1,1478272032410,1478272032410,'Node failure.  This probably means one or more interfaces have failed.','drevathy-0847.csez.zohocorpin.com',NULL,NULL),('gokul-3303.csez.zohocorpin.com','Alert',720,'gokul-3303.csez.zohocorpin.com','Topology',5,2,1478271080429,1478776113069,'Node clear.  No failures on this node.','gokul-3303.csez.zohocorpin.com',NULL,NULL),('gramkumar-0817.csez.zohocorpin.com','Alert',593,'gramkumar-0817.csez.zohocorpin.com','Topology',2,5,1478260120270,1478271557583,'Node failure.  This probably means one or more interfaces have failed.','gramkumar-0817.csez.zohocorpin.com',NULL,NULL),('guhan-3315.csez.zohocorpin.com','Alert',673,'guhan-3315.csez.zohocorpin.com','Topology',5,2,1478259750165,1478775943243,'Node clear.  No failures on this node.','guhan-3315.csez.zohocorpin.com',NULL,NULL),('harini-zu360.csez.zohocorpin.com','Alert',782,'harini-zu360.csez.zohocorpin.com','Topology',5,2,1478271746598,1478776383182,'Node clear.  No failures on this node.','harini-zu360.csez.zohocorpin.com',NULL,NULL),('hemanth-3818.csez.zohocorpin.com','Alert',629,'hemanth-3818.csez.zohocorpin.com','Topology',2,5,1478260391146,1478272043571,'Node failure.  This probably means one or more interfaces have failed.','hemanth-3818.csez.zohocorpin.com',NULL,NULL),('IF-172.24.14.105','Alert',735,'172.24.14.105','Topology',5,2,1478271154160,1478776182933,'Interface clear.  ','IF-172.24.14.105',NULL,''),('IF-172.24.14.112','Alert',407,'172.24.14.112','Topology',2,-1,1478263595037,1478263595037,'Interface failure.  Status poll failed.','IF-172.24.14.112',NULL,''),('IF-172.24.14.115','Alert',506,'172.24.14.115','Topology',2,-1,1478267391811,1478267391811,'Interface failure.  Status poll failed.','IF-172.24.14.115',NULL,''),('IF-172.24.14.149','Alert',590,'172.24.14.149','Topology',2,5,1478263695131,1478271546299,'Interface failure.  Status poll failed.','IF-172.24.14.149',NULL,''),('IF-172.24.14.152','Alert',789,'172.24.14.152','Topology',5,2,1478272043212,1478776493194,'Interface clear.  ','IF-172.24.14.152',NULL,''),('IF-172.24.14.154','Alert',359,'172.24.14.154','Topology',2,-1,1478260424230,1478260424230,'Interface failure.  Status poll failed.','IF-172.24.14.154',NULL,''),('IF-172.24.14.171','Alert',763,'172.24.14.171','Topology',5,2,1478260114854,1478776308028,'Interface clear.  ','IF-172.24.14.171',NULL,''),('IF-172.24.14.178','Alert',767,'172.24.14.178','Topology',5,2,1478263735129,1478776327851,'Interface clear.  ','IF-172.24.14.178',NULL,''),('IF-172.24.14.191','Alert',605,'172.24.14.191','Topology',2,-1,1478271724213,1478271724213,'Interface failure.  Status poll failed.','IF-172.24.14.191',NULL,''),('IF-172.24.14.226','Alert',799,'172.24.14.226','Topology',5,2,1478264414651,1478776542856,'Interface clear.  ','IF-172.24.14.226',NULL,''),('IF-172.24.14.238','Alert',801,'172.24.14.238','Topology',5,2,1478272076371,1478776562862,'Interface clear.  ','IF-172.24.14.238',NULL,''),('IF-172.24.14.252','Alert',818,'172.24.14.252','Topology',5,2,1478260965033,1478776682955,'Interface clear.  ','IF-172.24.14.252',NULL,''),('IF-172.24.14.28','Alert',691,'172.24.14.28','Topology',5,2,1478263420694,1478776012864,'Interface clear.  ','IF-172.24.14.28',NULL,''),('IF-172.24.14.56','Alert',711,'172.24.14.56','Topology',5,2,1478256289710,1478776082876,'Interface clear.  ','IF-172.24.14.56',NULL,''),('IF-172.24.14.67','Alert',783,'172.24.14.67','Topology',5,2,1478259978583,1478776422885,'Interface clear.  ','IF-172.24.14.67',NULL,''),('IF-172.24.14.84','Alert',723,'172.24.14.84','Topology',5,2,1478267155312,1478776147851,'Interface clear.  ','IF-172.24.14.84',NULL,''),('IF-172.24.14.92','Alert',727,'172.24.14.92','Topology',5,2,1478263566036,1478776157934,'Interface clear.  ','IF-172.24.14.92',NULL,''),('IF-192.168.220.202','Alert',598,'rejoe-0162.csez.zohocorpin.com','Topology',2,5,1478265854909,1478271713171,'Interface failure.  Status poll failed.','IF-192.168.220.202',NULL,''),('IF-abdul-0436.csez.zohocorpin.com','Alert',777,'abdul-0436.csez.zohocorpin.com','Topology',5,2,1478271746277,1478776372912,'Interface clear.  ','IF-abdul-0436.csez.zohocorpin.com',NULL,''),('IF-abdul-zt24.csez.zohocorpin.com','Alert',556,'abdul-zt24.csez.zohocorpin.com','Topology',2,5,1478263540613,1478271102387,'Interface failure.  Status poll failed.','IF-abdul-zt24.csez.zohocorpin.com',NULL,''),('IF-abhi-3378.csez.zohocorpin.com','Alert',658,'abhi-3378.csez.zohocorpin.com','Topology',2,-1,1478277382203,1478277382203,'Interface failure.  Status poll failed.','IF-abhi-3378.csez.zohocorpin.com',NULL,''),('IF-admp-test1.csez.zohocorpin.com','Alert',785,'admp-test1.csez.zohocorpin.com','Topology',5,2,1478267202593,1478776432906,'Interface clear.  ','IF-admp-test1.csez.zohocorpin.com',NULL,''),('IF-ajay-1385.csez.zohocorpin.com','Alert',427,'ajay-1385.csez.zohocorpin.com','Topology',2,-1,1478263821499,1478263821499,'Interface failure.  Status poll failed.','IF-ajay-1385.csez.zohocorpin.com',NULL,''),('IF-amarnath-0642.csez.zohocorpin.com','Alert',490,'amarnath-0642.csez.zohocorpin.com','Topology',2,5,1478256414697,1478267236906,'Interface failure.  Status poll failed.','IF-amarnath-0642.csez.zohocorpin.com',NULL,''),('IF-amritha-3867.csez.zohocorpin.com','Alert',684,'amritha-3867.csez.zohocorpin.com','Topology',5,2,1478276150249,1478775987900,'Interface clear.  ','IF-amritha-3867.csez.zohocorpin.com',NULL,''),('IF-android-6d556f7960b13da.csez.zohocorpin.com','Alert',638,'android-6d556f7960b13da.csez.zohocorpin.com','Topology',2,5,1478264439064,1478272087425,'Interface failure.  Status poll failed.','IF-android-6d556f7960b13da.csez.zohocorpin.com',NULL,''),('IF-android-6da4f6e8432f2ea.csez.zohocorpin.com','Alert',504,'android-6da4f6e8432f2ea.csez.zohocorpin.com','Topology',2,-1,1478267363352,1478267363352,'Interface failure.  Status poll failed.','IF-android-6da4f6e8432f2ea.csez.zohocorpin.com',NULL,''),('IF-android-8f4bc429763adb11.csez.zohocorpin.com','Alert',365,'android-8f4bc429763adb11.csez.zohocorpin.com','Topology',2,-1,1478260814499,1478260814499,'Interface failure.  Status poll failed.','IF-android-8f4bc429763adb11.csez.zohocorpin.com',NULL,''),('IF-android-d9e08dc4ead46367.csez.zohocorpin.com','Alert',218,'android-d9e08dc4ead46367.csez.zohocorpin.com','Topology',2,-1,1478256374695,1478256374695,'Interface failure.  Status poll failed.','IF-android-d9e08dc4ead46367.csez.zohocorpin.com',NULL,''),('IF-anu-4114.csez.zohocorpin.com','Alert',810,'anu-4114.csez.zohocorpin.com','Topology',5,2,1478268084131,1478776622954,'Interface clear.  ','IF-anu-4114.csez.zohocorpin.com',NULL,''),('IF-aravind-0717.csez.zohocorpin.com','Alert',693,'aravind-0717.csez.zohocorpin.com','Topology',5,2,1478277393263,1478776022869,'Interface clear.  ','IF-aravind-0717.csez.zohocorpin.com',NULL,''),('IF-aravinds6splus.csez.zohocorpin.com','Alert',793,'aravinds6splus.csez.zohocorpin.com','Topology',5,2,1478260590623,1478776512973,'Interface clear.  ','IF-aravinds6splus.csez.zohocorpin.com',NULL,''),('IF-aravinth-3063.csez.zohocorpin.com','Alert',578,'aravinth-3063.csez.zohocorpin.com','Topology',2,-1,1478271513156,1478271513156,'Interface failure.  Status poll failed.','IF-aravinth-3063.csez.zohocorpin.com',NULL,''),('IF-arivalagan-2168.csez.zohocorpin.com','Alert',791,'arivalagan-2168.csez.zohocorpin.com','Topology',5,2,1478272054271,1478776502920,'Interface clear.  ','IF-arivalagan-2168.csez.zohocorpin.com',NULL,''),('IF-arun-2286.csez.zohocorpin.com','Alert',795,'arun-2286.csez.zohocorpin.com','Topology',5,2,1478264369590,1478776522891,'Interface clear.  ','IF-arun-2286.csez.zohocorpin.com',NULL,''),('IF-arun-3857.csez.zohocorpin.com','Alert',803,'arun-3857.csez.zohocorpin.com','Topology',5,2,1478272076354,1478776582864,'Interface clear.  ','IF-arun-3857.csez.zohocorpin.com',NULL,''),('IF-arunsubhash-0371.csez.zohocorpin.com','Alert',721,'arunsubhash-0371.csez.zohocorpin.com','Topology',5,2,1478271080289,1478776117838,'Interface clear.  ','IF-arunsubhash-0371.csez.zohocorpin.com',NULL,''),('IF-ashish-4086.csez.zohocorpin.com','Alert',713,'ashish-4086.csez.zohocorpin.com','Topology',5,2,1478271069196,1478776087866,'Interface clear.  ','IF-ashish-4086.csez.zohocorpin.com',NULL,''),('IF-aswath-pt773.csez.zohocorpin.com','Alert',741,'aswath-pt773.csez.zohocorpin.com','Topology',5,2,1478271198384,1478776222849,'Interface clear.  ','IF-aswath-pt773.csez.zohocorpin.com',NULL,''),('IF-bala-2606.csez.zohocorpin.com','Alert',661,'bala-2606.csez.zohocorpin.com','Topology',2,-1,1478277393277,1478277393277,'Interface failure.  Status poll failed.','IF-bala-2606.csez.zohocorpin.com',NULL,''),('IF-bharath-2679.csez.zohocorpin.com','Alert',680,'bharath-2679.csez.zohocorpin.com','Topology',5,2,1478276139183,1478775962923,'Interface clear.  ','IF-bharath-2679.csez.zohocorpin.com',NULL,''),('IF-bhargavi-2458.csez.zohocorpin.com','Alert',596,'bhargavi-2458.csez.zohocorpin.com','Topology',2,-1,1478271713163,1478271713163,'Interface failure.  Status poll failed.','IF-bhargavi-2458.csez.zohocorpin.com',NULL,''),('IF-boobala-0048.csez.zohocorpin.com','Alert',737,'boobala-0048.csez.zohocorpin.com','Topology',5,2,1478260004853,1478776197928,'Interface clear.  ','IF-boobala-0048.csez.zohocorpin.com',NULL,''),('IF-chithu-0706.csez.zohocorpin.com','Alert',568,'chithu-0706.csez.zohocorpin.com','Topology',2,-1,1478271187367,1478271187367,'Interface failure.  Status poll failed.','IF-chithu-0706.csez.zohocorpin.com',NULL,''),('IF-damodhar-1003.csez.zohocorpin.com','Alert',510,'damodhar-1003.csez.zohocorpin.com','Topology',2,-1,1478267406532,1478267406532,'Interface failure.  Status poll failed.','IF-damodhar-1003.csez.zohocorpin.com',NULL,''),('IF-dheeraj-1090.csez.zohocorpin.com','Alert',806,'dheeraj-1090.csez.zohocorpin.com','Topology',5,2,1478264452628,1478776602914,'Interface clear.  ','IF-dheeraj-1090.csez.zohocorpin.com',NULL,''),('IF-drevathy-0847.csez.zohocorpin.com','Alert',624,'drevathy-0847.csez.zohocorpin.com','Topology',2,-1,1478272032162,1478272032162,'Interface failure.  Status poll failed.','IF-drevathy-0847.csez.zohocorpin.com',NULL,''),('IF-gokul-3303.csez.zohocorpin.com','Alert',719,'gokul-3303.csez.zohocorpin.com','Topology',5,2,1478271080261,1478776112846,'Interface clear.  ','IF-gokul-3303.csez.zohocorpin.com',NULL,''),('IF-gramkumar-0817.csez.zohocorpin.com','Alert',592,'gramkumar-0817.csez.zohocorpin.com','Topology',2,5,1478260120089,1478271557395,'Interface failure.  Status poll failed.','IF-gramkumar-0817.csez.zohocorpin.com',NULL,''),('IF-guhan-3315.csez.zohocorpin.com','Alert',672,'guhan-3315.csez.zohocorpin.com','Topology',5,2,1478259749876,1478775943005,'Interface clear.  ','IF-guhan-3315.csez.zohocorpin.com',NULL,''),('IF-harini-zu360.csez.zohocorpin.com','Alert',781,'harini-zu360.csez.zohocorpin.com','Topology',5,2,1478271746328,1478776382947,'Interface clear.  ','IF-harini-zu360.csez.zohocorpin.com',NULL,''),('IF-hemanth-3818.csez.zohocorpin.com','Alert',626,'hemanth-3818.csez.zohocorpin.com','Topology',2,5,1478260390861,1478272043211,'Interface failure.  Status poll failed.','IF-hemanth-3818.csez.zohocorpin.com',NULL,''),('IF-iphone-6-arivu.csez.zohocorpin.com','Alert',617,'iphone-6-arivu.csez.zohocorpin.com','Topology',2,5,1478256569720,1478271757399,'Interface failure.  Status poll failed.','IF-iphone-6-arivu.csez.zohocorpin.com',NULL,''),('IF-iphone6plus565.csez.zohocorpin.com','Alert',534,'iphone6plus565.csez.zohocorpin.com','Topology',2,5,1478256179687,1478270603818,'Interface failure.  Status poll failed.','IF-iphone6plus565.csez.zohocorpin.com',NULL,''),('IF-iphone6usest185.csez.zohocorpin.com','Alert',532,'iphone6usest185.csez.zohocorpin.com','Topology',2,5,1478259774824,1478270587277,'Interface failure.  Status poll failed.','IF-iphone6usest185.csez.zohocorpin.com',NULL,''),('IF-janaki-3684.csez.zohocorpin.com','Alert',616,'janaki-3684.csez.zohocorpin.com','Topology',2,-1,1478271757329,1478271757329,'Interface failure.  Status poll failed.','IF-janaki-3684.csez.zohocorpin.com',NULL,''),('IF-jerome-3929.csez.zohocorpin.com','Alert',586,'jerome-3929.csez.zohocorpin.com','Topology',2,-1,1478271535281,1478271535281,'Interface failure.  Status poll failed.','IF-jerome-3929.csez.zohocorpin.com',NULL,''),('IF-jlatha-0972.csez.zohocorpin.com','Alert',484,'jlatha-0972.csez.zohocorpin.com','Topology',2,-1,1478267213643,1478267213643,'Interface failure.  Status poll failed.','IF-jlatha-0972.csez.zohocorpin.com',NULL,''),('IF-jsangeetha-0849.csez.zohocorpin.com','Alert',725,'jsangeetha-0849.csez.zohocorpin.com','Topology',5,2,1478267161009,1478776152843,'Interface clear.  ','IF-jsangeetha-0849.csez.zohocorpin.com',NULL,''),('IF-kalai-0041.csez.zohocorpin.com','Alert',797,'kalai-0041.csez.zohocorpin.com','Topology',5,2,1478260769718,1478776532853,'Interface clear.  ','IF-kalai-0041.csez.zohocorpin.com',NULL,''),('IF-kavitha-1061.csez.zohocorpin.com','Alert',747,'kavitha-1061.csez.zohocorpin.com','Topology',5,2,1478260044914,1478776237934,'Interface clear.  ','IF-kavitha-1061.csez.zohocorpin.com',NULL,''),('IF-ksenthil-0949.csez.zohocorpin.com','Alert',461,'ksenthil-0949.csez.zohocorpin.com','Topology',2,-1,1478267011357,1478267011357,'Interface failure.  Status poll failed.','IF-ksenthil-0949.csez.zohocorpin.com',NULL,''),('IF-loga-zu396.csez.zohocorpin.com','Alert',775,'loga-zu396.csez.zohocorpin.com','Topology',5,2,1478271735277,1478776367877,'Interface clear.  ','IF-loga-zu396.csez.zohocorpin.com',NULL,''),('IF-macbook-pro.csez.zohocorpin.com','Alert',560,'macbook-pro.csez.zohocorpin.com','Topology',2,5,1478259949764,1478271113430,'Interface failure.  Status poll failed.','IF-macbook-pro.csez.zohocorpin.com',NULL,''),('IF-magesh-1870.csez.zohocorpin.com','Alert',743,'magesh-1870.csez.zohocorpin.com','Topology',5,2,1478271209415,1478776227854,'Interface clear.  ','IF-magesh-1870.csez.zohocorpin.com',NULL,''),('IF-mali-0473.csez.zohocorpin.com','Alert',769,'mali-0473.csez.zohocorpin.com','Topology',5,2,1478260145944,1478776337934,'Interface clear.  ','IF-mali-0473.csez.zohocorpin.com',NULL,''),('IF-mani-0702.csez.zohocorpin.com','Alert',552,'mani-0702.csez.zohocorpin.com','Topology',2,5,1478256329700,1478271091322,'Interface failure.  Status poll failed.','IF-mani-0702.csez.zohocorpin.com',NULL,''),('IF-mani-0918.csez.zohocorpin.com','Alert',697,'mani-0918.csez.zohocorpin.com','Topology',5,2,1478263440025,1478776032958,'Interface clear.  ','IF-mani-0918.csez.zohocorpin.com',NULL,''),('IF-mani-2209.csez.zohocorpin.com','Alert',554,'mani-2209.csez.zohocorpin.com','Topology',2,-1,1478271102361,1478271102361,'Interface failure.  Status poll failed.','IF-mani-2209.csez.zohocorpin.com',NULL,''),('IF-mani-4546.csez.zohocorpin.com','Alert',678,'mani-4546.csez.zohocorpin.com','Topology',5,2,1478256164690,1478775957931,'Interface clear.  ','IF-mani-4546.csez.zohocorpin.com',NULL,''),('IF-manimaran-1378.csez.zohocorpin.com','Alert',715,'manimaran-1378.csez.zohocorpin.com','Topology',5,2,1478263509972,1478776102897,'Interface clear.  ','IF-manimaran-1378.csez.zohocorpin.com',NULL,''),('IF-marutha-3402.csez.zohocorpin.com','Alert',749,'marutha-3402.csez.zohocorpin.com','Topology',5,2,1478271513155,1478776247846,'Interface clear.  ','IF-marutha-3402.csez.zohocorpin.com',NULL,''),('IF-meena.csez.zohocorpin.com','Alert',787,'meena.csez.zohocorpin.com','Topology',5,2,1478267322928,1478776442893,'Interface clear.  ','IF-meena.csez.zohocorpin.com',NULL,''),('IF-mithun-0445.csez.zohocorpin.com','Alert',814,'mithun-0445.csez.zohocorpin.com','Topology',5,2,1478268155915,1478776662915,'Interface clear.  ','IF-mithun-0445.csez.zohocorpin.com',NULL,''),('IF-mohasin-2851.csez.zohocorpin.com','Alert',731,'mohasin-2851.csez.zohocorpin.com','Topology',5,2,1478263579910,1478776172846,'Interface clear.  ','IF-mohasin-2851.csez.zohocorpin.com',NULL,''),('IF-mukil-4261.csez.zohocorpin.com','Alert',699,'mukil-4261.csez.zohocorpin.com','Topology',5,2,1478278071180,1478776052962,'Interface clear.  ','IF-mukil-4261.csez.zohocorpin.com',NULL,''),('IF-muni-0051.csez.zohocorpin.com','Alert',729,'muni-0051.csez.zohocorpin.com','Topology',5,2,1478256369699,1478776162850,'Interface clear.  ','IF-muni-0051.csez.zohocorpin.com',NULL,''),('IF-naga-3924.csez.zohocorpin.com','Alert',544,'naga-3924.csez.zohocorpin.com','Topology',2,5,1478259899763,1478271069216,'Interface failure.  Status poll failed.','IF-naga-3924.csez.zohocorpin.com',NULL,''),('IF-nirmal-2552.csez.zohocorpin.com','Alert',761,'nirmal-2552.csez.zohocorpin.com','Topology',5,2,1478263711547,1478776302884,'Interface clear.  ','IF-nirmal-2552.csez.zohocorpin.com',NULL,''),('IF-nj.csez.zohocorpin.com','Alert',644,'nj.csez.zohocorpin.com','Topology',2,5,1478260931097,1478272177158,'Interface failure.  Status poll failed.','IF-nj.csez.zohocorpin.com',NULL,''),('IF-padma-4271.csez.zohocorpin.com','Alert',689,'padma-4271.csez.zohocorpin.com','Topology',5,2,1478270618672,1478776007846,'Interface clear.  ','IF-padma-4271.csez.zohocorpin.com',NULL,''),('IF-pandi-1824.csez.zohocorpin.com','Alert',701,'pandi-1824.csez.zohocorpin.com','Topology',5,2,1478267066427,1478776057954,'Interface clear.  ','IF-pandi-1824.csez.zohocorpin.com',NULL,''),('IF-partha-3525.csez.zohocorpin.com','Alert',739,'partha-3525.csez.zohocorpin.com','Topology',5,2,1478267220110,1478776212901,'Interface clear.  ','IF-partha-3525.csez.zohocorpin.com',NULL,''),('IF-pav.csez.zohocorpin.com','Alert',808,'pav.csez.zohocorpin.com','Topology',5,2,1478260883655,1478776612923,'Interface clear.  ','IF-pav.csez.zohocorpin.com',NULL,''),('IF-pavithra-3526.csez.zohocorpin.com','Alert',757,'pavithra-3526.csez.zohocorpin.com','Topology',5,2,1478271524205,1478776277937,'Interface clear.  ','IF-pavithra-3526.csez.zohocorpin.com',NULL,''),('IF-prameena-1188.csez.zohocorpin.com','Alert',773,'prameena-1188.csez.zohocorpin.com','Topology',5,2,1478263754878,1478776348000,'Interface clear.  ','IF-prameena-1188.csez.zohocorpin.com',NULL,''),('IF-preethi2siphone.csez.zohocorpin.com','Alert',353,'preethi2siphone.csez.zohocorpin.com','Topology',2,-1,1478260180279,1478260180279,'Interface failure.  Status poll failed.','IF-preethi2siphone.csez.zohocorpin.com',NULL,''),('IF-priya-zutk58.csez.zohocorpin.com','Alert',393,'priya-zutk58.csez.zohocorpin.com','Topology',2,5,1478256304697,1478263515791,'Interface failure.  Status poll failed.','IF-priya-zutk58.csez.zohocorpin.com',NULL,''),('IF-purushoth-3454.csez.zohocorpin.com','Alert',755,'purushoth-3454.csez.zohocorpin.com','Topology',5,2,1478260075534,1478776267853,'Interface clear.  ','IF-purushoth-3454.csez.zohocorpin.com',NULL,''),('IF-radhas-iphone-6.csez.zohocorpin.com','Alert',604,'radhas-iphone-6.csez.zohocorpin.com','Topology',2,5,1478256529735,1478271724210,'Interface failure.  Status poll failed.','IF-radhas-iphone-6.csez.zohocorpin.com',NULL,''),('IF-raj-3842.csez.zohocorpin.com','Alert',733,'raj-3842.csez.zohocorpin.com','Topology',5,2,1478259984770,1478776177874,'Interface clear.  ','IF-raj-3842.csez.zohocorpin.com',NULL,''),('IF-rajesh-2755.csez.zohocorpin.com','Alert',555,'rajesh-2755.csez.zohocorpin.com','Topology',2,5,1478259934832,1478271102368,'Interface failure.  Status poll failed.','IF-rajesh-2755.csez.zohocorpin.com',NULL,''),('IF-raji-0675.csez.zohocorpin.com','Alert',759,'raji-0675.csez.zohocorpin.com','Topology',5,2,1478263699875,1478776292881,'Interface clear.  ','IF-raji-0675.csez.zohocorpin.com',NULL,''),('IF-rakesh-3889.csez.zohocorpin.com','Alert',317,'rakesh-3889.csez.zohocorpin.com','Topology',2,-1,1478259785185,1478259785185,'Interface failure.  Status poll failed.','IF-rakesh-3889.csez.zohocorpin.com',NULL,''),('IF-ram-3371.csez.zohocorpin.com','Alert',816,'ram-3371.csez.zohocorpin.com','Topology',5,2,1478264554987,1478776672895,'Interface clear.  ','IF-ram-3371.csez.zohocorpin.com',NULL,''),('IF-rejoe-0162.csez.zohocorpin.com','Alert',599,'rejoe-0162.csez.zohocorpin.com','Topology',2,5,1478254991174,1478271713175,'Interface failure.  Status poll failed.','IF-rejoe-0162.csez.zohocorpin.com',NULL,''),('IF-sabarna-4236.csez.zohocorpin.com','Alert',812,'sabarna-4236.csez.zohocorpin.com','Topology',5,2,1478272098486,1478776632915,'Interface clear.  ','IF-sabarna-4236.csez.zohocorpin.com',NULL,''),('IF-sai-3130.csez.zohocorpin.com','Alert',705,'sai-3130.csez.zohocorpin.com','Topology',5,2,1478278082222,1478776067840,'Interface clear.  ','IF-sai-3130.csez.zohocorpin.com',NULL,''),('IF-sandeep-2.csez.zohocorpin.com','Alert',656,'sandeep-2.csez.zohocorpin.com','Topology',2,5,1478254648884,1478277371166,'Interface failure.  Status poll failed.','IF-sandeep-2.csez.zohocorpin.com',NULL,''),('IF-sandhya-3439.csez.zohocorpin.com','Alert',745,'sandhya-3439.csez.zohocorpin.com','Topology',5,2,1478267239967,1478776232877,'Interface clear.  ','IF-sandhya-3439.csez.zohocorpin.com',NULL,''),('IF-santhanapreethi.csez.zohocorpin.com','Alert',621,'santhanapreethi.csez.zohocorpin.com','Topology',2,-1,1478271768446,1478271768446,'Interface failure.  Status poll failed.','IF-santhanapreethi.csez.zohocorpin.com',NULL,''),('IF-sathish-1320.csez.zohocorpin.com','Alert',703,'sathish-1320.csez.zohocorpin.com','Topology',5,2,1478263471303,1478776062844,'Interface clear.  ','IF-sathish-1320.csez.zohocorpin.com',NULL,''),('IF-sathish-2784.csez.zohocorpin.com','Alert',779,'sathish-2784.csez.zohocorpin.com','Topology',5,2,1478260184965,1478776377856,'Interface clear.  ','IF-sathish-2784.csez.zohocorpin.com',NULL,''),('IF-shanmugam-2352.csez.zohocorpin.com','Alert',717,'shanmugam-2352.csez.zohocorpin.com','Topology',5,2,1478271069205,1478776107844,'Interface clear.  ','IF-shanmugam-2352.csez.zohocorpin.com',NULL,''),('IF-shyamallsiphone.csez.zohocorpin.com','Alert',225,'shyamallsiphone.csez.zohocorpin.com','Topology',2,-1,1478256449714,1478256449714,'Interface failure.  Status poll failed.','IF-shyamallsiphone.csez.zohocorpin.com',NULL,''),('IF-siddharth-4445.csez.zohocorpin.com','Alert',241,'siddharth-4445.csez.zohocorpin.com','Topology',2,-1,1478256594723,1478256594723,'Interface failure.  Status poll failed.','IF-siddharth-4445.csez.zohocorpin.com',NULL,''),('IF-siva-2171.csez.zohocorpin.com','Alert',480,'siva-2171.csez.zohocorpin.com','Topology',2,-1,1478267200306,1478267200306,'Interface failure.  Status poll failed.','IF-siva-2171.csez.zohocorpin.com',NULL,''),('IF-srivatsav-3642.csez.zohocorpin.com','Alert',771,'srivatsav-3642.csez.zohocorpin.com','Topology',5,2,1478271713167,1478776342939,'Interface clear.  ','IF-srivatsav-3642.csez.zohocorpin.com',NULL,''),('IF-subha-4506.csez.zohocorpin.com','Alert',682,'subha-4506.csez.zohocorpin.com','Topology',5,2,1478266990012,1478775982838,'Interface clear.  ','IF-subha-4506.csez.zohocorpin.com',NULL,''),('IF-sumanth-3304.csez.zohocorpin.com','Alert',686,'sumanth-3304.csez.zohocorpin.com','Topology',5,2,1478276161302,1478775997930,'Interface clear.  ','IF-sumanth-3304.csez.zohocorpin.com',NULL,''),('IF-sundari-1712.csez.zohocorpin.com','Alert',765,'sundari-1712.csez.zohocorpin.com','Topology',5,2,1478267325071,1478776317858,'Interface clear.  ','IF-sundari-1712.csez.zohocorpin.com',NULL,''),('IF-suresh-0665.csez.zohocorpin.com','Alert',587,'suresh-0665.csez.zohocorpin.com','Topology',2,-1,1478271535281,1478271535281,'Interface failure.  Status poll failed.','IF-suresh-0665.csez.zohocorpin.com',NULL,''),('IF-suresh-1545.csez.zohocorpin.com','Alert',620,'suresh-1545.csez.zohocorpin.com','Topology',2,-1,1478271768371,1478271768371,'Interface failure.  Status poll failed.','IF-suresh-1545.csez.zohocorpin.com',NULL,''),('IF-ullas-4059.csez.zohocorpin.com','Alert',645,'ullas-4059.csez.zohocorpin.com','Topology',2,5,1478260896923,1478272177158,'Interface failure.  Status poll failed.','IF-ullas-4059.csez.zohocorpin.com',NULL,''),('IF-vaisali-4071.csez.zohocorpin.com','Alert',753,'vaisali-4071.csez.zohocorpin.com','Topology',5,2,1478271513153,1478776262853,'Interface clear.  ','IF-vaisali-4071.csez.zohocorpin.com',NULL,''),('IF-varun-2246.csez.zohocorpin.com','Alert',805,'varun-2246.csez.zohocorpin.com','Topology',5,2,1478264438814,1478776592864,'Interface clear.  ','IF-varun-2246.csez.zohocorpin.com',NULL,''),('IF-varun-3902.csez.zohocorpin.com','Alert',676,'varun-3902.csez.zohocorpin.com','Topology',5,2,1478270563606,1478775952838,'Interface clear.  ','IF-varun-3902.csez.zohocorpin.com',NULL,''),('IF-velan-es0007.csez.zohocorpin.com','Alert',345,'velan-es0007.csez.zohocorpin.com','Topology',2,-1,1478260079830,1478260079830,'Interface failure.  Status poll failed.','IF-velan-es0007.csez.zohocorpin.com',NULL,''),('IF-venkat-0773.csez.zohocorpin.com','Alert',695,'venkat-0773.csez.zohocorpin.com','Topology',5,2,1478277393278,1478776027838,'Interface clear.  ','IF-venkat-0773.csez.zohocorpin.com',NULL,''),('IF-vijay-0596.csez.zohocorpin.com','Alert',751,'vijay-0596.csez.zohocorpin.com','Topology',5,2,1478260059837,1478776252851,'Interface clear.  ','IF-vijay-0596.csez.zohocorpin.com',NULL,''),('IF-vineesh-3479.csez.zohocorpin.com','Alert',674,'vineesh-3479.csez.zohocorpin.com','Topology',5,2,1478259754775,1478775947836,'Interface clear.  ','IF-vineesh-3479.csez.zohocorpin.com',NULL,''),('IF-vinodh-2312.csez.zohocorpin.com','Alert',707,'vinodh-2312.csez.zohocorpin.com','Topology',5,2,1478267080548,1478776072873,'Interface clear.  ','IF-vinodh-2312.csez.zohocorpin.com',NULL,''),('IF-yuvaraj-1472.csez.zohocorpin.com','Alert',709,'yuvaraj-1472.csez.zohocorpin.com','Topology',5,2,1478278082231,1478776077874,'Interface clear.  ','IF-yuvaraj-1472.csez.zohocorpin.com',NULL,''),('iphone-6-arivu.csez.zohocorpin.com','Alert',619,'iphone-6-arivu.csez.zohocorpin.com','Topology',2,5,1478256569959,1478271757612,'Node failure.  This probably means one or more interfaces have failed.','iphone-6-arivu.csez.zohocorpin.com',NULL,NULL),('iphone6plus565.csez.zohocorpin.com','Alert',535,'iphone6plus565.csez.zohocorpin.com','Topology',2,5,1478256179886,1478270604029,'Node failure.  This probably means one or more interfaces have failed.','iphone6plus565.csez.zohocorpin.com',NULL,NULL),('iphone6usest185.csez.zohocorpin.com','Alert',533,'iphone6usest185.csez.zohocorpin.com','Topology',2,5,1478259775036,1478270587594,'Node failure.  This probably means one or more interfaces have failed.','iphone6usest185.csez.zohocorpin.com',NULL,NULL),('janaki-3684.csez.zohocorpin.com','Alert',618,'janaki-3684.csez.zohocorpin.com','Topology',2,-1,1478271757480,1478271757480,'Node failure.  This probably means one or more interfaces have failed.','janaki-3684.csez.zohocorpin.com',NULL,NULL),('jerome-3929.csez.zohocorpin.com','Alert',589,'jerome-3929.csez.zohocorpin.com','Topology',2,-1,1478271535463,1478271535463,'Node failure.  This probably means one or more interfaces have failed.','jerome-3929.csez.zohocorpin.com',NULL,NULL),('jlatha-0972.csez.zohocorpin.com','Alert',485,'jlatha-0972.csez.zohocorpin.com','Topology',2,-1,1478267213906,1478267213906,'Node failure.  This probably means one or more interfaces have failed.','jlatha-0972.csez.zohocorpin.com',NULL,NULL),('jsangeetha-0849.csez.zohocorpin.com','Alert',726,'jsangeetha-0849.csez.zohocorpin.com','Topology',5,2,1478267161336,1478776152993,'Node clear.  No failures on this node.','jsangeetha-0849.csez.zohocorpin.com',NULL,NULL),('kalai-0041.csez.zohocorpin.com','Alert',798,'kalai-0041.csez.zohocorpin.com','Topology',5,2,1478260770042,1478776533152,'Node clear.  No failures on this node.','kalai-0041.csez.zohocorpin.com',NULL,NULL),('kavitha-1061.csez.zohocorpin.com','Alert',748,'kavitha-1061.csez.zohocorpin.com','Topology',5,2,1478260045138,1478776238189,'Node clear.  No failures on this node.','kavitha-1061.csez.zohocorpin.com',NULL,NULL),('KeyPerformanceIndicator:Average Fault Resolution Time hours','Alert',688,NULL,'KPI',1,-1,1478776007792,1478776007792,'Fault Resolution Time is more than 2 days. Value: 132.91933, Data: Average Fault Resolution Time : KeyPerformanceIndicator : Average Fault Resolution Time hours, Threshold Type: max Critical Threshold: 48.0 Critical Rearm Value: 40.0','KeyPerformanceIndicator',NULL,NULL),('KeyPerformanceIndicator:Network Availability %','Alert',388,NULL,'KPI',1,5,1478256575621,1478263475628,'Network Availability percentage is less than 70% Value: 68.852, Data: Network Availability : KeyPerformanceIndicator : Network Availability %, Threshold Type: min Critical Threshold: 70.0 Critical Rearm Value: 80.0','KeyPerformanceIndicator',NULL,NULL),('ksenthil-0949.csez.zohocorpin.com','Alert',462,'ksenthil-0949.csez.zohocorpin.com','Topology',2,-1,1478267011638,1478267011638,'Node failure.  This probably means one or more interfaces have failed.','ksenthil-0949.csez.zohocorpin.com',NULL,NULL),('loga-zu396.csez.zohocorpin.com','Alert',776,'loga-zu396.csez.zohocorpin.com','Topology',5,2,1478271735588,1478776368117,'Node clear.  No failures on this node.','loga-zu396.csez.zohocorpin.com',NULL,NULL),('macbook-pro.csez.zohocorpin.com','Alert',561,'macbook-pro.csez.zohocorpin.com','Topology',2,5,1478259950033,1478271113770,'Node failure.  This probably means one or more interfaces have failed.','macbook-pro.csez.zohocorpin.com',NULL,NULL),('magesh-1870.csez.zohocorpin.com','Alert',744,'magesh-1870.csez.zohocorpin.com','Topology',5,2,1478271209704,1478776228000,'Node clear.  No failures on this node.','magesh-1870.csez.zohocorpin.com',NULL,NULL),('mali-0473.csez.zohocorpin.com','Alert',770,'mali-0473.csez.zohocorpin.com','Topology',5,2,1478260146139,1478776338052,'Node clear.  No failures on this node.','mali-0473.csez.zohocorpin.com',NULL,NULL),('mani-0702.csez.zohocorpin.com','Alert',553,'mani-0702.csez.zohocorpin.com','Topology',2,5,1478256329804,1478271091646,'Node failure.  This probably means one or more interfaces have failed.','mani-0702.csez.zohocorpin.com',NULL,NULL),('mani-0918.csez.zohocorpin.com','Alert',698,'mani-0918.csez.zohocorpin.com','Topology',5,2,1478263440258,1478776033191,'Node clear.  No failures on this node.','mani-0918.csez.zohocorpin.com',NULL,NULL),('mani-2209.csez.zohocorpin.com','Alert',558,'mani-2209.csez.zohocorpin.com','Topology',2,-1,1478271102717,1478271102717,'Node failure.  This probably means one or more interfaces have failed.','mani-2209.csez.zohocorpin.com',NULL,NULL),('mani-4546.csez.zohocorpin.com','Alert',679,'mani-4546.csez.zohocorpin.com','Topology',5,2,1478256164960,1478775958148,'Node clear.  No failures on this node.','mani-4546.csez.zohocorpin.com',NULL,NULL),('manimaran-1378.csez.zohocorpin.com','Alert',716,'manimaran-1378.csez.zohocorpin.com','Topology',5,2,1478263510123,1478776103142,'Node clear.  No failures on this node.','manimaran-1378.csez.zohocorpin.com',NULL,NULL),('marutha-3402.csez.zohocorpin.com','Alert',750,'marutha-3402.csez.zohocorpin.com','Topology',5,2,1478271513503,1478776248162,'Node clear.  No failures on this node.','marutha-3402.csez.zohocorpin.com',NULL,NULL),('meena.csez.zohocorpin.com','Alert',788,'meena.csez.zohocorpin.com','Topology',5,2,1478267323186,1478776443116,'Node clear.  No failures on this node.','meena.csez.zohocorpin.com',NULL,NULL),('mithun-0445.csez.zohocorpin.com','Alert',815,'mithun-0445.csez.zohocorpin.com','Topology',5,2,1478268156193,1478776663138,'Node clear.  No failures on this node.','mithun-0445.csez.zohocorpin.com',NULL,NULL),('mohasin-2851.csez.zohocorpin.com','Alert',732,'mohasin-2851.csez.zohocorpin.com','Topology',5,2,1478263580056,1478776173097,'Node clear.  No failures on this node.','mohasin-2851.csez.zohocorpin.com',NULL,NULL),('mukil-4261.csez.zohocorpin.com','Alert',700,'mukil-4261.csez.zohocorpin.com','Topology',5,2,1478278071497,1478776053182,'Node clear.  No failures on this node.','mukil-4261.csez.zohocorpin.com',NULL,NULL),('muni-0051.csez.zohocorpin.com','Alert',730,'muni-0051.csez.zohocorpin.com','Topology',5,2,1478256369934,1478776163131,'Node clear.  No failures on this node.','muni-0051.csez.zohocorpin.com',NULL,NULL),('naga-3924.csez.zohocorpin.com','Alert',545,'naga-3924.csez.zohocorpin.com','Topology',2,5,1478259899970,1478271069415,'Node failure.  This probably means one or more interfaces have failed.','naga-3924.csez.zohocorpin.com',NULL,NULL),('nirmal-2552.csez.zohocorpin.com','Alert',762,'nirmal-2552.csez.zohocorpin.com','Topology',5,2,1478263711684,1478776303064,'Node clear.  No failures on this node.','nirmal-2552.csez.zohocorpin.com',NULL,NULL),('nj.csez.zohocorpin.com','Alert',647,'nj.csez.zohocorpin.com','Topology',2,5,1478260931403,1478272177408,'Node failure.  This probably means one or more interfaces have failed.','nj.csez.zohocorpin.com',NULL,NULL),('padma-4271.csez.zohocorpin.com','Alert',690,'padma-4271.csez.zohocorpin.com','Topology',5,2,1478270618847,1478776008022,'Node clear.  No failures on this node.','padma-4271.csez.zohocorpin.com',NULL,NULL),('pandi-1824.csez.zohocorpin.com','Alert',702,'pandi-1824.csez.zohocorpin.com','Topology',5,2,1478267066743,1478776058132,'Node clear.  No failures on this node.','pandi-1824.csez.zohocorpin.com',NULL,NULL),('partha-3525.csez.zohocorpin.com','Alert',740,'partha-3525.csez.zohocorpin.com','Topology',5,2,1478267220256,1478776213181,'Node clear.  No failures on this node.','partha-3525.csez.zohocorpin.com',NULL,NULL),('pav.csez.zohocorpin.com','Alert',809,'pav.csez.zohocorpin.com','Topology',5,2,1478260883916,1478776613235,'Node clear.  No failures on this node.','pav.csez.zohocorpin.com',NULL,NULL),('pavithra-3526.csez.zohocorpin.com','Alert',758,'pavithra-3526.csez.zohocorpin.com','Topology',5,2,1478271524563,1478776278123,'Node clear.  No failures on this node.','pavithra-3526.csez.zohocorpin.com',NULL,NULL),('prameena-1188.csez.zohocorpin.com','Alert',774,'prameena-1188.csez.zohocorpin.com','Topology',5,2,1478263755156,1478776348196,'Node clear.  No failures on this node.','prameena-1188.csez.zohocorpin.com',NULL,NULL),('preethi2siphone.csez.zohocorpin.com','Alert',354,'preethi2siphone.csez.zohocorpin.com','Topology',2,-1,1478260180439,1478260180439,'Node failure.  This probably means one or more interfaces have failed.','preethi2siphone.csez.zohocorpin.com',NULL,NULL),('priya-zutk58.csez.zohocorpin.com','Alert',394,'priya-zutk58.csez.zohocorpin.com','Topology',2,5,1478256305009,1478263516037,'Node failure.  This probably means one or more interfaces have failed.','priya-zutk58.csez.zohocorpin.com',NULL,NULL),('purushoth-3454.csez.zohocorpin.com','Alert',756,'purushoth-3454.csez.zohocorpin.com','Topology',5,2,1478260075729,1478776268080,'Node clear.  No failures on this node.','purushoth-3454.csez.zohocorpin.com',NULL,NULL),('radhas-iphone-6.csez.zohocorpin.com','Alert',606,'radhas-iphone-6.csez.zohocorpin.com','Topology',2,5,1478256529972,1478271724505,'Node failure.  This probably means one or more interfaces have failed.','radhas-iphone-6.csez.zohocorpin.com',NULL,NULL),('raj-3842.csez.zohocorpin.com','Alert',734,'raj-3842.csez.zohocorpin.com','Topology',5,2,1478259984938,1478776178061,'Node clear.  No failures on this node.','raj-3842.csez.zohocorpin.com',NULL,NULL),('rajesh-2755.csez.zohocorpin.com','Alert',557,'rajesh-2755.csez.zohocorpin.com','Topology',2,5,1478259934952,1478271102668,'Node failure.  This probably means one or more interfaces have failed.','rajesh-2755.csez.zohocorpin.com',NULL,NULL),('raji-0675.csez.zohocorpin.com','Alert',760,'raji-0675.csez.zohocorpin.com','Topology',5,2,1478263699999,1478776293103,'Node clear.  No failures on this node.','raji-0675.csez.zohocorpin.com',NULL,NULL),('rakesh-3889.csez.zohocorpin.com','Alert',318,'rakesh-3889.csez.zohocorpin.com','Topology',2,-1,1478259785469,1478259785469,'Node failure.  This probably means one or more interfaces have failed.','rakesh-3889.csez.zohocorpin.com',NULL,NULL),('ram-3371.csez.zohocorpin.com','Alert',817,'ram-3371.csez.zohocorpin.com','Topology',5,2,1478264555221,1478776673142,'Node clear.  No failures on this node.','ram-3371.csez.zohocorpin.com',NULL,NULL),('rejoe-0162.csez.zohocorpin.com','Alert',600,'rejoe-0162.csez.zohocorpin.com','Topology',2,5,1478254991431,1478271713425,'Node failure.  This probably means one or more interfaces have failed.','rejoe-0162.csez.zohocorpin.com',NULL,NULL),('sabarna-4236.csez.zohocorpin.com','Alert',813,'sabarna-4236.csez.zohocorpin.com','Topology',5,2,1478272098797,1478776633159,'Node clear.  No failures on this node.','sabarna-4236.csez.zohocorpin.com',NULL,NULL),('sai-3130.csez.zohocorpin.com','Alert',706,'sai-3130.csez.zohocorpin.com','Topology',5,2,1478278082491,1478776068075,'Node clear.  No failures on this node.','sai-3130.csez.zohocorpin.com',NULL,NULL),('sandeep-2.csez.zohocorpin.com','Alert',657,'sandeep-2.csez.zohocorpin.com','Topology',2,5,1478254649106,1478277371415,'Node failure.  This probably means one or more interfaces have failed.','sandeep-2.csez.zohocorpin.com',NULL,NULL),('sandhya-3439.csez.zohocorpin.com','Alert',746,'sandhya-3439.csez.zohocorpin.com','Topology',5,2,1478267240208,1478776233144,'Node clear.  No failures on this node.','sandhya-3439.csez.zohocorpin.com',NULL,NULL),('santhanapreethi.csez.zohocorpin.com','Alert',623,'santhanapreethi.csez.zohocorpin.com','Topology',2,-1,1478271768828,1478271768828,'Node failure.  This probably means one or more interfaces have failed.','santhanapreethi.csez.zohocorpin.com',NULL,NULL),('sathish-1320.csez.zohocorpin.com','Alert',704,'sathish-1320.csez.zohocorpin.com','Topology',5,2,1478263471566,1478776063088,'Node clear.  No failures on this node.','sathish-1320.csez.zohocorpin.com',NULL,NULL),('sathish-2784.csez.zohocorpin.com','Alert',780,'sathish-2784.csez.zohocorpin.com','Topology',5,2,1478260185168,1478776378008,'Node clear.  No failures on this node.','sathish-2784.csez.zohocorpin.com',NULL,NULL),('shanmugam-2352.csez.zohocorpin.com','Alert',718,'shanmugam-2352.csez.zohocorpin.com','Topology',5,2,1478271069525,1478776108005,'Node clear.  No failures on this node.','shanmugam-2352.csez.zohocorpin.com',NULL,NULL),('shyamallsiphone.csez.zohocorpin.com','Alert',226,'shyamallsiphone.csez.zohocorpin.com','Topology',2,-1,1478256449914,1478256449914,'Node failure.  This probably means one or more interfaces have failed.','shyamallsiphone.csez.zohocorpin.com',NULL,NULL),('siddharth-4445.csez.zohocorpin.com','Alert',242,'siddharth-4445.csez.zohocorpin.com','Topology',2,-1,1478256594957,1478256594957,'Node failure.  This probably means one or more interfaces have failed.','siddharth-4445.csez.zohocorpin.com',NULL,NULL),('siva-2171.csez.zohocorpin.com','Alert',481,'siva-2171.csez.zohocorpin.com','Topology',2,-1,1478267200511,1478267200511,'Node failure.  This probably means one or more interfaces have failed.','siva-2171.csez.zohocorpin.com',NULL,NULL),('srivatsav-3642.csez.zohocorpin.com','Alert',772,'srivatsav-3642.csez.zohocorpin.com','Topology',5,2,1478271713451,1478776343132,'Node clear.  No failures on this node.','srivatsav-3642.csez.zohocorpin.com',NULL,NULL),('subha-4506.csez.zohocorpin.com','Alert',683,'subha-4506.csez.zohocorpin.com','Topology',5,2,1478266990159,1478775983060,'Node clear.  No failures on this node.','subha-4506.csez.zohocorpin.com',NULL,NULL),('sumanth-3304.csez.zohocorpin.com','Alert',687,'sumanth-3304.csez.zohocorpin.com','Topology',5,2,1478276161593,1478775998118,'Node clear.  No failures on this node.','sumanth-3304.csez.zohocorpin.com',NULL,NULL),('sundari-1712.csez.zohocorpin.com','Alert',766,'sundari-1712.csez.zohocorpin.com','Topology',5,2,1478267325208,1478776318071,'Node clear.  No failures on this node.','sundari-1712.csez.zohocorpin.com',NULL,NULL),('suresh-0665.csez.zohocorpin.com','Alert',588,'suresh-0665.csez.zohocorpin.com','Topology',2,-1,1478271535452,1478271535452,'Node failure.  This probably means one or more interfaces have failed.','suresh-0665.csez.zohocorpin.com',NULL,NULL),('suresh-1545.csez.zohocorpin.com','Alert',622,'suresh-1545.csez.zohocorpin.com','Topology',2,-1,1478271768686,1478271768686,'Node failure.  This probably means one or more interfaces have failed.','suresh-1545.csez.zohocorpin.com',NULL,NULL),('ullas-4059.csez.zohocorpin.com','Alert',646,'ullas-4059.csez.zohocorpin.com','Topology',2,5,1478260897225,1478272177400,'Node failure.  This probably means one or more interfaces have failed.','ullas-4059.csez.zohocorpin.com',NULL,NULL),('vaisali-4071.csez.zohocorpin.com','Alert',754,'vaisali-4071.csez.zohocorpin.com','Topology',5,2,1478271513495,1478776263005,'Node clear.  No failures on this node.','vaisali-4071.csez.zohocorpin.com',NULL,NULL),('varun-2246.csez.zohocorpin.com','Alert',436,'varun-2246.csez.zohocorpin.com','Topology',5,2,1478264439039,1478266983715,'Node failure.  This probably means one or more interfaces have failed.','varun-2246.csez.zohocorpin.com',NULL,NULL),('varun-3902.csez.zohocorpin.com','Alert',677,'varun-3902.csez.zohocorpin.com','Topology',5,2,1478270563842,1478775953072,'Node clear.  No failures on this node.','varun-3902.csez.zohocorpin.com',NULL,NULL),('velan-es0007.csez.zohocorpin.com','Alert',346,'velan-es0007.csez.zohocorpin.com','Topology',2,-1,1478260080040,1478260080040,'Node failure.  This probably means one or more interfaces have failed.','velan-es0007.csez.zohocorpin.com',NULL,NULL),('venkat-0773.csez.zohocorpin.com','Alert',696,'venkat-0773.csez.zohocorpin.com','Topology',5,2,1478277393432,1478776028048,'Node clear.  No failures on this node.','venkat-0773.csez.zohocorpin.com',NULL,NULL),('vijay-0596.csez.zohocorpin.com','Alert',752,'vijay-0596.csez.zohocorpin.com','Topology',5,2,1478260060023,1478776253033,'Node clear.  No failures on this node.','vijay-0596.csez.zohocorpin.com',NULL,NULL),('vineesh-3479.csez.zohocorpin.com','Alert',675,'vineesh-3479.csez.zohocorpin.com','Topology',5,2,1478259755026,1478775948012,'Node clear.  No failures on this node.','vineesh-3479.csez.zohocorpin.com',NULL,NULL),('vinodh-2312.csez.zohocorpin.com','Alert',708,'vinodh-2312.csez.zohocorpin.com','Topology',5,2,1478267080757,1478776073043,'Node clear.  No failures on this node.','vinodh-2312.csez.zohocorpin.com',NULL,NULL),('yuvaraj-1472.csez.zohocorpin.com','Alert',710,'yuvaraj-1472.csez.zohocorpin.com','Topology',5,2,1478278082483,1478776078019,'Node clear.  No failures on this node.','yuvaraj-1472.csez.zohocorpin.com',NULL,NULL);
/*!40000 ALTER TABLE `Alert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AlertPolicyObject`
--

DROP TABLE IF EXISTS `AlertPolicyObject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AlertPolicyObject` (
  `NAME` varchar(80) NOT NULL,
  `ACTIONNAME` varchar(80) NOT NULL,
  `PROPKEY` varchar(80) NOT NULL,
  `PROPVALUE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`NAME`,`ACTIONNAME`,`PROPKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AlertPolicyObject`
--

LOCK TABLES `AlertPolicyObject` WRITE;
/*!40000 ALTER TABLE `AlertPolicyObject` DISABLE KEYS */;
/*!40000 ALTER TABLE `AlertPolicyObject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AttributeAudit`
--

DROP TABLE IF EXISTS `AttributeAudit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AttributeAudit` (
  `ID` int(11) DEFAULT NULL,
  `IDENTIFIER` varchar(100) DEFAULT NULL,
  `TIMEOFFINISH` varchar(30) DEFAULT NULL,
  `RETRIES` int(11) DEFAULT NULL,
  `STATUS` varchar(200) DEFAULT NULL,
  `OUTPUT` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AttributeAudit`
--

LOCK TABLES `AttributeAudit` WRITE;
/*!40000 ALTER TABLE `AttributeAudit` DISABLE KEYS */;
/*!40000 ALTER TABLE `AttributeAudit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AuthAudit`
--

DROP TABLE IF EXISTS `AuthAudit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AuthAudit` (
  `AUTHAUDITID` int(11) DEFAULT NULL,
  `USERNAME` varchar(30) DEFAULT NULL,
  `OPERATION` varchar(100) DEFAULT NULL,
  `AUDITTIME` varchar(30) DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `CATEGORY` varchar(25) DEFAULT NULL,
  `AUDITEDOBJ` varchar(100) DEFAULT NULL,
  KEY `AuthAudit0_ndx` (`AUTHAUDITID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AuthAudit`
--

LOCK TABLES `AuthAudit` WRITE;
/*!40000 ALTER TABLE `AuthAudit` DISABLE KEYS */;
INSERT INTO `AuthAudit` VALUES (1,'root','Authentication : 172.24.14.34','2016-11-04 19:02:38.016','SUCCESS','Authentication','NULL'),(2,'root','Authentication : localhost','2016-11-04 19:08:07.426','SUCCESS','Authentication','NULL'),(3,'root','Authentication : 172.24.14.34','2016-11-04 19:09:08.224','SUCCESS','Authentication','NULL'),(4,'root','Clear Alerts','2016-11-04 19:10:01.847','SUCCESS','Fault','ram-3371.csez.zohocorpin.com'),(5,'root','Clear Alerts','2016-11-04 19:10:05.327','SUCCESS','Fault','IF-ram-3371.csez.zohocorpin.com'),(6,'root','Clear Alerts','2016-11-04 19:10:07.565','SUCCESS','Fault','dheeraj-1090.csez.zohocorpin.com'),(7,'root','Client logged out : 172.24.14.34','2016-11-04 19:10:12.877','SUCCESS','DEFAULT','NULL'),(8,'root','Client logged out : 172.24.14.34','2016-11-04 19:10:15.062','SUCCESS','DEFAULT','NULL'),(9,'root','Authentication : 172.24.14.34','2016-11-04 19:11:00.244','SUCCESS','Authentication','NULL'),(10,'root','Authentication : 172.24.14.34','2016-11-04 19:12:26.421','SUCCESS','Authentication','NULL'),(11,'root','Clear Alerts','2016-11-04 19:12:58.656','SUCCESS','Fault','IF-dheeraj-1090.csez.zohocorpin.com'),(12,'root','Clear Alerts','2016-11-04 19:13:01.015','SUCCESS','Fault','android-6d556f7960b13da.csez.zohocorpin.com'),(13,'root','Clear Alerts','2016-11-04 19:13:02.512','SUCCESS','Fault','IF-android-6d556f7960b13da.csez.zohocorpin.com'),(14,'root','Clear Alerts','2016-11-04 19:13:03.713','SUCCESS','Fault','varun-2246.csez.zohocorpin.com'),(15,'root','Client logged out : 172.24.14.34','2016-11-04 19:13:11.164','SUCCESS','DEFAULT','NULL'),(16,'root','Client logged out : 172.24.14.34','2016-11-04 19:13:14.531','SUCCESS','DEFAULT','NULL');
/*!40000 ALTER TABLE `AuthAudit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `BEFailOver`
--

DROP TABLE IF EXISTS `BEFailOver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BEFailOver` (
  `HOSTADDRESS` varchar(50) NOT NULL,
  `NMSBEPORT` int(11) NOT NULL,
  `RMIREGISTRYPORT` int(11) DEFAULT NULL,
  `LASTCOUNT` bigint(20) DEFAULT NULL,
  `SERVERROLE` varchar(10) DEFAULT NULL,
  `STANDBYSERVERNAME` varchar(50) DEFAULT NULL,
  KEY `BEFailOver0_ndx` (`HOSTADDRESS`),
  KEY `BEFailOver1_ndx` (`NMSBEPORT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BEFailOver`
--

LOCK TABLES `BEFailOver` WRITE;
/*!40000 ALTER TABLE `BEFailOver` DISABLE KEYS */;
INSERT INTO `BEFailOver` VALUES ('172.24.14.34',2000,1099,46,'PRIMARY','NULL');
/*!40000 ALTER TABLE `BEFailOver` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CCTV`
--

DROP TABLE IF EXISTS `CCTV`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CCTV` (
  `ID` bigint(20) NOT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `TIMEINTERVAL` bigint(20) DEFAULT NULL,
  `DESCRIPTION` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`,`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CCTV`
--

LOCK TABLES `CCTV` WRITE;
/*!40000 ALTER TABLE `CCTV` DISABLE KEYS */;
INSERT INTO `CCTV` VALUES (1001,'root','Sample View',10,'Sample CCTV View With Default Dashboards');
/*!40000 ALTER TABLE `CCTV` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CCTVVIEWS`
--

DROP TABLE IF EXISTS `CCTVVIEWS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CCTVVIEWS` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `DASHBOARDID` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`,`DASHBOARDID`),
  KEY `CctvViews0_Indx` (`ID`),
  KEY `CctvViews1_Indx` (`DASHBOARDID`),
  CONSTRAINT `CCTV_ID` FOREIGN KEY (`ID`) REFERENCES `CCTV` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `DASHBOARD_ID` FOREIGN KEY (`DASHBOARDID`) REFERENCES `DASHBOARD` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CCTVVIEWS`
--

LOCK TABLES `CCTVVIEWS` WRITE;
/*!40000 ALTER TABLE `CCTVVIEWS` DISABLE KEYS */;
INSERT INTO `CCTVVIEWS` VALUES (1001,1001),(1001,1002);
/*!40000 ALTER TABLE `CCTVVIEWS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CHILDRENSTATUS`
--

DROP TABLE IF EXISTS `CHILDRENSTATUS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CHILDRENSTATUS` (
  `KEYSTRING` varchar(250) NOT NULL,
  `VALUESTRING` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`KEYSTRING`),
  KEY `CHILDRENSTATUS0_ndx` (`KEYSTRING`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CHILDRENSTATUS`
--

LOCK TABLES `CHILDRENSTATUS` WRITE;
/*!40000 ALTER TABLE `CHILDRENSTATUS` DISABLE KEYS */;
INSERT INTO `CHILDRENSTATUS` VALUES ('172.24.14.1','5'),('172.24.14.10','5'),('172.24.14.101','5'),('172.24.14.102','2'),('172.24.14.104','5'),('172.24.14.105','5'),('172.24.14.106','2'),('172.24.14.107','5'),('172.24.14.108','5'),('172.24.14.109','2'),('172.24.14.11','5'),('172.24.14.111','5'),('172.24.14.112','2'),('172.24.14.113','5'),('172.24.14.115','2'),('172.24.14.117','2'),('172.24.14.118','5'),('172.24.14.119','2'),('172.24.14.120','2'),('172.24.14.125','5'),('172.24.14.131','5'),('172.24.14.133','5'),('172.24.14.134','2'),('172.24.14.135','5'),('172.24.14.136','5'),('172.24.14.137','2'),('172.24.14.14','2'),('172.24.14.141','5'),('172.24.14.142','5'),('172.24.14.149','2'),('172.24.14.15','5'),('172.24.14.152','5'),('172.24.14.154','2'),('172.24.14.155','2'),('172.24.14.157','2'),('172.24.14.158','2'),('172.24.14.16','2'),('172.24.14.160','5'),('172.24.14.163','2'),('172.24.14.167','5'),('172.24.14.168','5'),('172.24.14.169','2'),('172.24.14.170','2'),('172.24.14.171','5'),('172.24.14.172','5'),('172.24.14.173','5'),('172.24.14.175','2'),('172.24.14.176','5'),('172.24.14.177','5'),('172.24.14.178','5'),('172.24.14.182','5'),('172.24.14.183','2'),('172.24.14.188','2'),('172.24.14.189','5'),('172.24.14.19','5'),('172.24.14.190','5'),('172.24.14.191','2'),('172.24.14.192','2'),('172.24.14.193','2'),('172.24.14.197','2'),('172.24.14.199','5'),('172.24.14.20','2'),('172.24.14.201','5'),('172.24.14.208','2'),('172.24.14.211','2'),('172.24.14.219','2'),('172.24.14.220','2'),('172.24.14.223','5'),('172.24.14.224','5'),('172.24.14.225','2'),('172.24.14.226','5'),('172.24.14.227','5'),('172.24.14.228','5'),('172.24.14.23','5'),('172.24.14.233','5'),('172.24.14.236','5'),('172.24.14.237','2'),('172.24.14.238','5'),('172.24.14.240','5'),('172.24.14.242','2'),('172.24.14.243','5'),('172.24.14.246','5'),('172.24.14.249','2'),('172.24.14.252','5'),('172.24.14.254','5'),('172.24.14.27','2'),('172.24.14.28','5'),('172.24.14.3','5'),('172.24.14.30','2'),('172.24.14.34','5'),('172.24.14.38','5'),('172.24.14.39','5'),('172.24.14.4','2'),('172.24.14.40','5'),('172.24.14.41','5'),('172.24.14.42','5'),('172.24.14.44','5'),('172.24.14.49','5'),('172.24.14.50','2'),('172.24.14.51','5'),('172.24.14.53','5'),('172.24.14.56','5'),('172.24.14.63','2'),('172.24.14.67','5'),('172.24.14.68','5'),('172.24.14.69','2'),('172.24.14.7','2'),('172.24.14.70','2'),('172.24.14.72','2'),('172.24.14.75','5'),('172.24.14.78','5'),('172.24.14.8','5'),('172.24.14.80','5'),('172.24.14.81','2'),('172.24.14.84','5'),('172.24.14.86','2'),('172.24.14.88','5'),('172.24.14.9','5'),('172.24.14.90','5'),('172.24.14.92','5'),('172.24.14.93','5'),('172.24.14.96','2'),('192.168.220.202','2');
/*!40000 ALTER TABLE `CHILDRENSTATUS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CORBANode`
--

DROP TABLE IF EXISTS `CORBANode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CORBANode` (
  `MOID` bigint(20) NOT NULL,
  `ORBCLASSNAME` varchar(100) DEFAULT NULL,
  `NAMESERVICEHOST` varchar(100) DEFAULT NULL,
  `NAMESERVICEPORT` int(11) DEFAULT NULL,
  `NAMEREF` varchar(100) DEFAULT NULL,
  `INTERFACENAME` varchar(100) DEFAULT NULL,
  `DICTIONARY` varchar(100) DEFAULT NULL,
  `STATUSPOLLCOMMAND` varchar(100) DEFAULT NULL,
  `STATUSPOLLIOR` text,
  `DATAPOLLIOR` text,
  PRIMARY KEY (`MOID`),
  KEY `FK427DD3C7435BA95` (`MOID`),
  CONSTRAINT `FK427DD3C7435BA95` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CORBANode`
--

LOCK TABLES `CORBANode` WRITE;
/*!40000 ALTER TABLE `CORBANode` DISABLE KEYS */;
/*!40000 ALTER TABLE `CORBANode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CRITERIAPROPERTIES`
--

DROP TABLE IF EXISTS `CRITERIAPROPERTIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CRITERIAPROPERTIES` (
  `NAME` varchar(100) NOT NULL,
  `PROPVAL` varchar(255) DEFAULT NULL,
  `PROPNAME` varchar(255) NOT NULL,
  PRIMARY KEY (`NAME`,`PROPNAME`),
  KEY `FK435EDDD2977A5201` (`NAME`),
  CONSTRAINT `FK435EDDD2977A5201` FOREIGN KEY (`NAME`) REFERENCES `MapDB` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CRITERIAPROPERTIES`
--

LOCK TABLES `CRITERIAPROPERTIES` WRITE;
/*!40000 ALTER TABLE `CRITERIAPROPERTIES` DISABLE KEYS */;
INSERT INTO `CRITERIAPROPERTIES` VALUES ('172.24.14.0Failed_Objects_Map.netmap','*172.24.14.0*','parentNet'),('172.24.14.0Failed_Objects_Map.netmap','critical,major','stringstatus'),('Failed_Objects_Map.netmap','false','isInterface'),('Failed_Objects_Map.netmap','major,critical','stringstatus');
/*!40000 ALTER TABLE `CRITERIAPROPERTIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ChassisDevice`
--

DROP TABLE IF EXISTS `ChassisDevice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ChassisDevice` (
  `MOID` bigint(20) NOT NULL,
  `CHASSISID` varchar(40) DEFAULT NULL,
  `REGION` varchar(100) DEFAULT NULL,
  `ADDTIME` bigint(20) DEFAULT NULL,
  `MACID` varchar(20) DEFAULT NULL,
  `MODELNUMBER` varchar(20) DEFAULT NULL,
  `SERIALNUMBER` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK27715BFCD435654B` (`MOID`),
  CONSTRAINT `FK27715BFCD435654B` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ChassisDevice`
--

LOCK TABLES `ChassisDevice` WRITE;
/*!40000 ALTER TABLE `ChassisDevice` DISABLE KEYS */;
INSERT INTO `ChassisDevice` VALUES (195,NULL,NULL,0,NULL,'172.24.14.67---0',NULL),(198,NULL,NULL,0,NULL,'admp-test1.csez.zoho',NULL),(201,NULL,NULL,0,NULL,'meena.csez.zohocorpi',NULL),(204,NULL,NULL,0,NULL,'preethi2siphone.csez',NULL),(207,NULL,NULL,0,NULL,'172.24.14.115---4',NULL),(210,NULL,NULL,0,NULL,'hemanth-3818.csez.zo',NULL),(213,NULL,NULL,0,NULL,'172.24.14.154---6',NULL),(216,NULL,NULL,0,NULL,'172.24.14.152---7',NULL),(219,NULL,NULL,0,NULL,'arivalagan-2168.csez',NULL),(222,NULL,NULL,0,NULL,'aravinds6splus.csez.',NULL),(225,NULL,NULL,0,NULL,'arun-2286.csez.zohoc',NULL),(228,NULL,NULL,0,NULL,'kalai-0041.csez.zoho',NULL),(231,NULL,NULL,0,NULL,'172.24.14.226---12',NULL),(234,NULL,NULL,0,NULL,'android-8f4bc429763a',NULL),(237,NULL,NULL,0,NULL,'172.24.14.238---14',NULL),(240,NULL,NULL,0,NULL,'android-6d556f7960b1',NULL),(243,NULL,NULL,0,NULL,'arun-3857.csez.zohoc',NULL),(246,NULL,NULL,0,NULL,'varun-2246.csez.zoho',NULL),(249,NULL,NULL,0,NULL,'dheeraj-1090.csez.zo',NULL),(252,NULL,NULL,0,NULL,'pav.csez.zohocorpin.',NULL),(255,NULL,NULL,0,NULL,'anu-4114.csez.zohoco',NULL),(258,NULL,NULL,0,NULL,'sabarna-4236.csez.zo',NULL),(261,NULL,NULL,0,NULL,'ullas-4059.csez.zoho',NULL),(264,NULL,NULL,0,NULL,'nj.csez.zohocorpin.c',NULL),(267,NULL,NULL,0,NULL,'mithun-0445.csez.zoh',NULL),(270,NULL,NULL,0,NULL,'ram-3371.csez.zohoco',NULL),(273,NULL,NULL,0,NULL,'172.24.14.252---26',NULL);
/*!40000 ALTER TABLE `ChassisDevice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ConfigAttributes`
--

DROP TABLE IF EXISTS `ConfigAttributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ConfigAttributes` (
  `AKEY` varchar(120) DEFAULT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `VALUE` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ConfigAttributes`
--

LOCK TABLES `ConfigAttributes` WRITE;
/*!40000 ALTER TABLE `ConfigAttributes` DISABLE KEYS */;
INSERT INTO `ConfigAttributes` VALUES ('root@AcmeCardConfiguration+0','messagePayLoadBlock','PRIORITY=2'),('root@AcmeCardConfiguration+0','targetID',''),('root@AcmeCardConfiguration+0','identifier','ED-EQPT'),('root@AcmeCardConfiguration+0','accessID','ST1-4'),('root@AcmeCardConfiguration+0','commandCode','ED-EQPT'),('root@AcmeCardConfiguration+0','generalBlock',''),('root@BroadCastStormControl+0','identifier','m'),('root@BroadCastStormControl+0','value',':'),('root@BroadCastStormControl+1','identifier','n'),('root@BroadCastStormControl+1','value',':'),('root@BroadCastStormControl+2','identifier','s'),('root@BroadCastStormControl+2','value',':'),('root@BroadCastStormControl+3','identifier','f'),('root@BroadCastStormControl+3','cmdArgs','$UserInput$community'),('root@BroadCastStormControl+3','value',':'),('root@BroadCastStormControl+4','identifier','a'),('root@BroadCastStormControl+4','cmdArgs','$UserInput$snmpManager'),('root@BroadCastStormControl+4','value',':'),('root@BroadCastStormControl+5','identifier','x'),('root@BroadCastStormControl+5','value',':'),('root@BroadCastStormControl+6','identifier','x'),('root@BroadCastStormControl+6','value',':'),('root@BroadCastStormControl+7','identifier','s'),('root@BroadCastStormControl+7','value',':'),('root@BroadCastStormControl+8','identifier','b'),('root@BroadCastStormControl+8','value',':'),('root@BroadCastStormControl+9','identifier','g'),('root@BroadCastStormControl+9','cmdArgs','e'),('root@BroadCastStormControl+9','value',':'),('root@BroadCastStormControl+10','identifier','t'),('root@BroadCastStormControl+10','cmdArgs','$UserInput$thresholdValue'),('root@BroadCastStormControl+10','value',':'),('root@BroadCastStormControl+11','identifier','x'),('root@BroadCastStormControl+11','value',':'),('root@BroadCastStormControl+12','identifier','x'),('root@BroadCastStormControl+12','value',':'),('root@BroadCastStormControl+13','identifier','x'),('root@BroadCastStormControl+13','cmdArgs','y'),('root@BroadCastStormControl+13','value',':'),('root@CiscoAccessListDeletion+0','identifier','enable'),('root@CiscoAccessListDeletion+0','value',':'),('root@CiscoAccessListDeletion+1','identifier','$UserInput$Password'),('root@CiscoAccessListDeletion+1','value','#'),('root@CiscoAccessListDeletion+2','identifier','config t'),('root@CiscoAccessListDeletion+2','value','#'),('root@CiscoAccessListDeletion+3','identifier','line vty 0 4'),('root@CiscoAccessListDeletion+3','value','#'),('root@CiscoAccessListDeletion+4','identifier','no access-class $UserInput$acclistvalue in'),('root@CiscoAccessListDeletion+4','value','#'),('root@CiscoAccessListDeletion+5','identifier','exit'),('root@CiscoAccessListDeletion+5','value','#'),('root@CiscoAccessListDeletion+6','identifier','no access-list $UserInput$acclistvalue'),('root@CiscoAccessListDeletion+6','value','#'),('root@CiscoAccessListDeletion+7','identifier','exit'),('root@CiscoAccessListDeletion+7','value','#'),('root@CiscoAccessListDeletion+8','identifier','wr'),('root@CiscoAccessListDeletion+8','value','#'),('root@CiscoAccessListGeneration+0','identifier','enable'),('root@CiscoAccessListGeneration+0','value',':'),('root@CiscoAccessListGeneration+1','identifier','$UserInput$Password'),('root@CiscoAccessListGeneration+1','value','#'),('root@CiscoAccessListGeneration+2','identifier','config t'),('root@CiscoAccessListGeneration+2','value','#'),('root@CiscoAccessListGeneration+3','identifier','access-list $UserInput$acclistvalue deny 0.0.0.0'),('root@CiscoAccessListGeneration+3','value','#'),('root@CiscoAccessListGeneration+4','identifier','access-list $UserInput$acclistvalue permit $UserInput$IPAddress1'),('root@CiscoAccessListGeneration+4','value','#'),('root@CiscoAccessListGeneration+5','identifier','access-list $UserInput$acclistvalue permit $UserInput$IPAddress2'),('root@CiscoAccessListGeneration+5','value','#'),('root@CiscoAccessListGeneration+6','identifier','line vty 0 4'),('root@CiscoAccessListGeneration+6','value','#'),('root@CiscoAccessListGeneration+7','identifier','access-class $UserInput$acclistvalue in'),('root@CiscoAccessListGeneration+7','value','#'),('root@CiscoAccessListGeneration+8','identifier','exit'),('root@CiscoAccessListGeneration+8','value','#'),('root@CiscoAccessListGeneration+9','identifier','exit'),('root@CiscoAccessListGeneration+9','value','#'),('root@CiscoAccessListGeneration+10','identifier','wr'),('root@CiscoAccessListGeneration+10','value','#'),('root@CiscoConfigBackup+0','identifier','enable'),('root@CiscoConfigBackup+0','value',':'),('root@CiscoConfigBackup+1','identifier','$UserInput$Password'),('root@CiscoConfigBackup+1','value','#'),('root@CiscoConfigBackup+2','identifier','copy running-config tftp'),('root@CiscoConfigBackup+2','value','?'),('root@CiscoConfigBackup+3','identifier','$UserInput$IPAddr'),('root@CiscoConfigBackup+3','value','?'),('root@CiscoConfigBackup+4','identifier','$UserInput$fileName'),('root@CiscoConfigBackup+4','value','?'),('root@CiscoConfigBackup+5','identifier','y'),('root@CiscoConfigBackup+5','value','#'),('root@FileTransfer+0','srcFileName','README.html'),('root@FileTransfer+0','identifier','put'),('root@FileTransfer+0','binaryMode','true'),('root@FileTransfer+0','destFileName','READTEST.html'),('root@NATAddition+0','identifier','enable'),('root@NATAddition+0','value',':'),('root@NATAddition+1','identifier','$UserInput$Password'),('root@NATAddition+1','value','#'),('root@NATAddition+2','identifier','config terminal'),('root@NATAddition+2','value','#'),('root@NATAddition+3','identifier','ip nat inside source list $UserInput$listno interface $UserInput$outgoingInterface'),('root@NATAddition+3','value','#'),('root@NATAddition+4','identifier','interface $UserInput$outgoingInterface'),('root@NATAddition+4','value','#'),('root@NATAddition+5','identifier','ip nat outside'),('root@NATAddition+5','value','#'),('root@NATAddition+6','identifier','exit'),('root@NATAddition+6','value','#'),('root@NATAddition+7','identifier','interface $UserInput$incomingInterface'),('root@NATAddition+7','value','#'),('root@NATAddition+8','identifier','ip nat inside'),('root@NATAddition+8','value','#'),('root@NATAddition+9','identifier','exit'),('root@NATAddition+9','value','#'),('root@NATAddition+10','identifier','access-list $UserInput$listno permit $UserInput$IPAddress1'),('root@NATAddition+10','value','#'),('root@NATAddition+11','identifier','access-list $UserInput$listno permit $UserInput$IPAddress2'),('root@NATAddition+11','value','#'),('root@NATAddition+12','identifier','exit'),('root@NATAddition+12','value','#'),('root@NATAddition+13','identifier','wr'),('root@NATAddition+13','value','#'),('root@NATRemoval+0','identifier','enable'),('root@NATRemoval+0','value',':'),('root@NATRemoval+1','identifier','$UserInput$Password'),('root@NATRemoval+1','value','#'),('root@NATRemoval+2','identifier','config terminal'),('root@NATRemoval+2','value','#'),('root@NATRemoval+3','identifier','no access-list $UserInput$listno'),('root@NATRemoval+3','value','#'),('root@NATRemoval+4','identifier','interface $UserInput$outgoingInterface'),('root@NATRemoval+4','value','#'),('root@NATRemoval+5','identifier','no ip nat outside'),('root@NATRemoval+5','value','#'),('root@NATRemoval+6','identifier','exit'),('root@NATRemoval+6','value','#'),('root@NATRemoval+7','identifier','interface $UserInput$incomingInterface'),('root@NATRemoval+7','value','#'),('root@NATRemoval+8','identifier','no ip nat inside'),('root@NATRemoval+8','value','#'),('root@NATRemoval+9','identifier','exit'),('root@NATRemoval+9','value','#'),('root@NATRemoval+10','identifier','no ip nat inside source list $UserInput$listno'),('root@NATRemoval+10','value','#'),('root@NATRemoval+11','identifier','exit'),('root@NATRemoval+11','value','#'),('root@NATRemoval+12','identifier','wr'),('root@NATRemoval+12','value','#'),('root@NetworkDiscovery+0','label','networkDiscoveryTable'),('root@NetworkDiscovery+0','index','$UserInput$networkDiscoveryIndex'),('root@NetworkDiscovery+0','identifier','.1.3.6.1.4.1.2162.4.10.1.9'),('root@NetworkDiscovery+0','type','table'),('root@NetworkDiscovery+1','label','netIPAddress'),('root@NetworkDiscovery+1','identifier','.1.3.6.1.4.1.2162.4.10.1.9.1.2'),('root@NetworkDiscovery+1','type','64'),('root@NetworkDiscovery+1','value','$UserInput$netIPAddress'),('root@NetworkDiscovery+2','label','netMask'),('root@NetworkDiscovery+2','identifier','.1.3.6.1.4.1.2162.4.10.1.9.1.3'),('root@NetworkDiscovery+2','type','64'),('root@NetworkDiscovery+2','value','$UserInput$netMask'),('root@NetworkDiscovery+3','label','startIPAddress'),('root@NetworkDiscovery+3','identifier','.1.3.6.1.4.1.2162.4.10.1.9.1.4'),('root@NetworkDiscovery+3','type','4'),('root@NetworkDiscovery+3','value','$UserInput$StartIPAddress'),('root@NetworkDiscovery+4','label','endIPAddress'),('root@NetworkDiscovery+4','identifier','.1.3.6.1.4.1.2162.4.10.1.9.1.5'),('root@NetworkDiscovery+4','type','4'),('root@NetworkDiscovery+4','value','$UserInput$EndIPAddress'),('root@NetworkDiscovery+5','label','doDiscovery'),('root@NetworkDiscovery+5','identifier','.1.3.6.1.4.1.2162.4.10.1.9.1.6'),('root@NetworkDiscovery+5','type','2'),('root@NetworkDiscovery+5','value','1'),('root@NetworkDiscovery+6','label','dhcp'),('root@NetworkDiscovery+6','identifier','.1.3.6.1.4.1.2162.4.10.1.9.1.7'),('root@NetworkDiscovery+6','type','2'),('root@NetworkDiscovery+6','value','2'),('root@RouteAdd+0','identifier','enable'),('root@RouteAdd+0','value',':'),('root@RouteAdd+1','identifier','$UserInput$Password'),('root@RouteAdd+1','value','#'),('root@RouteAdd+2','identifier','config terminal'),('root@RouteAdd+2','value','#'),('root@RouteAdd+3','identifier','ip route $UserInput$network $UserInput$mask $UserInput$interFace'),('root@RouteAdd+3','value','#'),('root@RouteAdd+4','identifier','exit'),('root@RouteAdd+4','value','#'),('root@RouteAdd+5','identifier','wr'),('root@RouteAdd+5','value','#'),('root@RouteDelete+0','identifier','enable'),('root@RouteDelete+0','value',':'),('root@RouteDelete+1','identifier','$UserInput$Password'),('root@RouteDelete+1','value','#'),('root@RouteDelete+2','identifier','config terminal'),('root@RouteDelete+2','value','#'),('root@RouteDelete+3','identifier','no ip route $UserInput$network $UserInput$mask'),('root@RouteDelete+3','value','#'),('root@RouteDelete+4','identifier','exit'),('root@RouteDelete+4','value','#'),('root@RouteDelete+5','identifier','wr'),('root@RouteDelete+5','value','#'),('root@SystemDateConfiguration+0','messagePayLoadBlock','2001-12-27,11-11-11'),('root@SystemDateConfiguration+0','targetID',''),('root@SystemDateConfiguration+0','identifier','ED-DAT'),('root@SystemDateConfiguration+0','accessID',''),('root@SystemDateConfiguration+0','commandCode','ED-DAT'),('root@SystemDateConfiguration+0','generalBlock',''),('root@SystemGroupConfiguration+0','label','sysContact'),('root@SystemGroupConfiguration+0','identifier','1.4.0'),('root@SystemGroupConfiguration+0','type','4'),('root@SystemGroupConfiguration+0','value','test1'),('root@SystemGroupConfiguration+1','label','sysLocation'),('root@SystemGroupConfiguration+1','identifier','1.6.0'),('root@SystemGroupConfiguration+1','type','4'),('root@SystemGroupConfiguration+1','value','test2'),('root@TrapForwarder+0','label','forwardingTable'),('root@TrapForwarder+0','index','$UserInput$ID'),('root@TrapForwarder+0','identifier','.1.3.6.1.4.1.2162.4.9.1'),('root@TrapForwarder+0','type','table'),('root@TrapForwarder+1','label','rowStatus'),('root@TrapForwarder+1','identifier','.1.3.6.1.4.1.2162.4.9.1.1.4'),('root@TrapForwarder+1','type','2'),('root@TrapForwarder+1','value','$UserInput$rowStatus'),('root@TrapForwarder+2','label','managerPort'),('root@TrapForwarder+2','identifier','.1.3.6.1.4.1.2162.4.9.1.1.3'),('root@TrapForwarder+2','type','2'),('root@TrapForwarder+2','value','$UserInput$managerPort'),('root@TrapForwarder+3','label','managerHost'),('root@TrapForwarder+3','identifier','.1.3.6.1.4.1.2162.4.9.1.1.2'),('root@TrapForwarder+3','type','4'),('root@TrapForwarder+3','value','$UserInput$managerHost');
/*!40000 ALTER TABLE `ConfigAttributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ConfigProvider`
--

DROP TABLE IF EXISTS `ConfigProvider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ConfigProvider` (
  `PROTOCOL` varchar(100) NOT NULL,
  `CONFIGPROVIDER` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ConfigProvider`
--

LOCK TABLES `ConfigProvider` WRITE;
/*!40000 ALTER TABLE `ConfigProvider` DISABLE KEYS */;
INSERT INTO `ConfigProvider` VALUES ('tl1','com.adventnet.management.config.tl1.TL1ConfigProvider'),('telnet','com.adventnet.management.config.telnet.TelnetConfigProvider'),('netconf','com.adventnet.management.config.netconf.NetconfConfigProvider'),('snmp','com.adventnet.management.config.snmp.SnmpConfigProvider'),('ftp','com.adventnet.management.config.ftp.FTPConfigProvider'),('tftp','com.adventnet.management.config.tftp.TftpConfigProvider'),('CORBA','com.adventnet.management.config.corba.CorbaConfigProvider');
/*!40000 ALTER TABLE `ConfigProvider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ConfigTaskDetails`
--

DROP TABLE IF EXISTS `ConfigTaskDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ConfigTaskDetails` (
  `AKEY` int(11) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `TASKNAME` varchar(50) DEFAULT NULL,
  `TYPE` varchar(2) DEFAULT NULL,
  `PARENTKEY` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ConfigTaskDetails`
--

LOCK TABLES `ConfigTaskDetails` WRITE;
/*!40000 ALTER TABLE `ConfigTaskDetails` DISABLE KEYS */;
INSERT INTO `ConfigTaskDetails` VALUES (0,'root','AcmeCardConfiguration','A',-1),(0,'root','BroadCastStormControl','A',-1),(1,'root','BroadCastStormControl','A',-1),(2,'root','BroadCastStormControl','A',-1),(3,'root','BroadCastStormControl','A',-1),(4,'root','BroadCastStormControl','A',-1),(5,'root','BroadCastStormControl','A',-1),(6,'root','BroadCastStormControl','A',-1),(7,'root','BroadCastStormControl','A',-1),(8,'root','BroadCastStormControl','A',-1),(9,'root','BroadCastStormControl','A',-1),(10,'root','BroadCastStormControl','A',-1),(11,'root','BroadCastStormControl','A',-1),(12,'root','BroadCastStormControl','A',-1),(13,'root','BroadCastStormControl','A',-1),(0,'root','CiscoAccessListDeletion','A',-1),(1,'root','CiscoAccessListDeletion','A',-1),(2,'root','CiscoAccessListDeletion','A',-1),(3,'root','CiscoAccessListDeletion','A',-1),(4,'root','CiscoAccessListDeletion','A',-1),(5,'root','CiscoAccessListDeletion','A',-1),(6,'root','CiscoAccessListDeletion','A',-1),(7,'root','CiscoAccessListDeletion','A',-1),(8,'root','CiscoAccessListDeletion','A',-1),(0,'root','CiscoAccessListGeneration','A',-1),(1,'root','CiscoAccessListGeneration','A',-1),(2,'root','CiscoAccessListGeneration','A',-1),(3,'root','CiscoAccessListGeneration','A',-1),(4,'root','CiscoAccessListGeneration','A',-1),(5,'root','CiscoAccessListGeneration','A',-1),(6,'root','CiscoAccessListGeneration','A',-1),(7,'root','CiscoAccessListGeneration','A',-1),(8,'root','CiscoAccessListGeneration','A',-1),(9,'root','CiscoAccessListGeneration','A',-1),(10,'root','CiscoAccessListGeneration','A',-1),(0,'root','CiscoConfigBackup','A',-1),(1,'root','CiscoConfigBackup','A',-1),(2,'root','CiscoConfigBackup','A',-1),(3,'root','CiscoConfigBackup','A',-1),(4,'root','CiscoConfigBackup','A',-1),(5,'root','CiscoConfigBackup','A',-1),(0,'root','FileTransfer','A',-1),(0,'root','NATAddition','A',-1),(1,'root','NATAddition','A',-1),(2,'root','NATAddition','A',-1),(3,'root','NATAddition','A',-1),(4,'root','NATAddition','A',-1),(5,'root','NATAddition','A',-1),(6,'root','NATAddition','A',-1),(7,'root','NATAddition','A',-1),(8,'root','NATAddition','A',-1),(9,'root','NATAddition','A',-1),(10,'root','NATAddition','A',-1),(11,'root','NATAddition','A',-1),(12,'root','NATAddition','A',-1),(13,'root','NATAddition','A',-1),(0,'root','NATRemoval','A',-1),(1,'root','NATRemoval','A',-1),(2,'root','NATRemoval','A',-1),(3,'root','NATRemoval','A',-1),(4,'root','NATRemoval','A',-1),(5,'root','NATRemoval','A',-1),(6,'root','NATRemoval','A',-1),(7,'root','NATRemoval','A',-1),(8,'root','NATRemoval','A',-1),(9,'root','NATRemoval','A',-1),(10,'root','NATRemoval','A',-1),(11,'root','NATRemoval','A',-1),(12,'root','NATRemoval','A',-1),(0,'root','NetworkDiscovery','T',-1),(1,'root','NetworkDiscovery','C',0),(2,'root','NetworkDiscovery','C',0),(3,'root','NetworkDiscovery','C',0),(4,'root','NetworkDiscovery','C',0),(5,'root','NetworkDiscovery','C',0),(6,'root','NetworkDiscovery','C',0),(0,'root','RouteAdd','A',-1),(1,'root','RouteAdd','A',-1),(2,'root','RouteAdd','A',-1),(3,'root','RouteAdd','A',-1),(4,'root','RouteAdd','A',-1),(5,'root','RouteAdd','A',-1),(0,'root','RouteDelete','A',-1),(1,'root','RouteDelete','A',-1),(2,'root','RouteDelete','A',-1),(3,'root','RouteDelete','A',-1),(4,'root','RouteDelete','A',-1),(5,'root','RouteDelete','A',-1),(0,'root','SystemDateConfiguration','A',-1),(0,'root','SystemGroupConfiguration','A',-1),(1,'root','SystemGroupConfiguration','A',-1),(0,'root','TrapForwarder','T',-1),(1,'root','TrapForwarder','C',0),(2,'root','TrapForwarder','C',0),(3,'root','TrapForwarder','C',0);
/*!40000 ALTER TABLE `ConfigTaskDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ConfigTasks`
--

DROP TABLE IF EXISTS `ConfigTasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ConfigTasks` (
  `USERNAME` varchar(50) DEFAULT NULL,
  `TASKNAME` varchar(50) DEFAULT NULL,
  `PROTOCOL` varchar(10) DEFAULT NULL,
  `VERSION` varchar(10) DEFAULT NULL,
  `SUBTASKS` varchar(200) DEFAULT NULL,
  `ROLLBACKNEEDED` varchar(5) DEFAULT NULL,
  `ROLLBACKTYPE` int(11) DEFAULT NULL,
  `ROLLBACKDOCUMENT` varchar(50) DEFAULT NULL,
  `MIBSTOBELOADED` varchar(100) DEFAULT NULL,
  `DESCRIPTION` text,
  `ISTEMPLATE` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ConfigTasks`
--

LOCK TABLES `ConfigTasks` WRITE;
/*!40000 ALTER TABLE `ConfigTasks` DISABLE KEYS */;
INSERT INTO `ConfigTasks` VALUES ('root','AcmeCardConfiguration','tl1','1.0',NULL,'false',-1,NULL,NULL,'This task is used for configuring the PRIORITY property of a card of AcmeTL1Concentrator.','false'),('root','BroadCastStormControl','telnet','1.0',NULL,'false',-1,NULL,NULL,'This task configures the Broadcast threshold value for Cisco Systems Catalyst 1900 switches  and generates traps which can be sent to any Trap Manager. The Trap generated is the trapBroadcastStorm(ST - 6 and GT 4 )','true'),('root','CiscoAccessListDeletion','telnet','1.0',NULL,'false',-1,NULL,NULL,'This task is used to remove the generated AccessList for the Cisco C2600 Router ie to remove the controlled access for telnet service','true'),('root','CiscoAccessListGeneration','telnet','1.0',NULL,'false',-1,NULL,NULL,'This task is used to control access to the Cisco C2600 Router for telnet service.','true'),('root','CiscoConfigBackup','telnet','1.0',NULL,'false',-1,NULL,NULL,'This task is used for taking the backup of configuration information for Cisco C2600 Series','true'),('root','FileTransfer','tftp','1.0',NULL,'false',-1,NULL,NULL,'This task is used for transferring files.','false'),('root','NATAddition','telnet','1.0',NULL,'false',-1,NULL,NULL,'This task adds an entry in Network Address Translation table and is specific to Cisco C2600 series.This will allow hosts with private IP Addresses to access to the internet. ','true'),('root','NATRemoval','telnet','1.0',NULL,'false',-1,NULL,NULL,'This task is used to remove an entry from Network Address Translation table.This task is specific to Cisco C2600 series','true'),('root','NetworkDiscovery','snmp','1.0',NULL,'false',-1,NULL,NULL,'This task is used for discovering nodes under a network using snmp protocol.','true'),('root','RouteAdd','telnet','1.0',NULL,'false',-1,NULL,NULL,'This task adds a route for a subnet and is specific to C2600 Cisco Router','true'),('root','RouteDelete','telnet','1.0',NULL,'false',-1,NULL,NULL,'This task deletes the added route for a subnet and is specific to Cisco C2600 series','true'),('root','SystemDateConfiguration','tl1','1.0',NULL,'false',-1,NULL,NULL,'This task is used for configuring the system date and time of a TL1 machine.','false'),('root','SystemGroupConfiguration','snmp','1.0',NULL,'false',-1,NULL,NULL,'This task is used for configuring the snmp attributes of a remote host.','false'),('root','TrapForwarder','snmp','1.0',NULL,'false',-1,NULL,NULL,'This task is used for forwarding traps received in one machine to another machine using snmp protocol.','true');
/*!40000 ALTER TABLE `ConfigTasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomMatchCriteria`
--

DROP TABLE IF EXISTS `CustomMatchCriteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CustomMatchCriteria` (
  `CRITERIAID` bigint(20) NOT NULL,
  `CRITCLASSNAME` varchar(200) NOT NULL,
  `DEVLISTTOCUSTOMCRITID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CRITERIAID`),
  KEY `FK89FED43325ACDCD2` (`DEVLISTTOCUSTOMCRITID`),
  CONSTRAINT `FK89FED43325ACDCD2` FOREIGN KEY (`DEVLISTTOCUSTOMCRITID`) REFERENCES `KPIDeviceList` (`DEVICELISTID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomMatchCriteria`
--

LOCK TABLES `CustomMatchCriteria` WRITE;
/*!40000 ALTER TABLE `CustomMatchCriteria` DISABLE KEYS */;
/*!40000 ALTER TABLE `CustomMatchCriteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomView`
--

DROP TABLE IF EXISTS `CustomView`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CustomView` (
  `MODULE` varchar(25) NOT NULL,
  `VIEWID` varchar(150) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `VIEWNAME` varchar(100) NOT NULL,
  `PARENT` varchar(50) NOT NULL,
  `CONSTRAIN` varchar(200) DEFAULT NULL,
  `SQLSTRING` text,
  PRIMARY KEY (`VIEWID`,`USERNAME`),
  KEY `CustomView0_ndx` (`MODULE`,`USERNAME`,`VIEWNAME`,`PARENT`),
  KEY `CustomView1_ndx` (`VIEWID`,`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomView`
--

LOCK TABLES `CustomView` WRITE;
/*!40000 ALTER TABLE `CustomView` DISABLE KEYS */;
INSERT INTO `CustomView` VALUES ('Alerts','Alerts','root','Alarms','All','','NULL'),('Alerts','Alerts.1042110065','root','Alarms0s','Alerts','6&7','NULL'),('Audit','ConfigAudit','root','Audit','Configuration','','NULL'),('Events','Events','root','Network Events','All','','NULL'),('Network Database','Network Database','root','Network Database','All','','NULL'),('Network Database','Network Database.CompleteView','root','webclient.topo.tree.completeview','Network Database','','NULL'),('Network Database','Network Database.Interfaces','root','Interfaces','Network Database','3','NULL'),('Network Database','Network Database.Networks','root','Networks','Network Database','1','NULL'),('Network Database','Network Database.Nodes','root','Nodes','Network Database','2','NULL'),('Network Database','Network Database.Routers','root','Routers','Network Database','5','NULL'),('Network Database','Network Database.Switches','root','Switches','Network Database','4','NULL'),('Stats Admin','Stats Admin','root','Configured Collection','All','','NULL');
/*!40000 ALTER TABLE `CustomView` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomViewColumns`
--

DROP TABLE IF EXISTS `CustomViewColumns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CustomViewColumns` (
  `MODULE` varchar(25) NOT NULL,
  `VIEWID` varchar(150) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `DISPLAYNAME` varchar(50) DEFAULT NULL,
  `COLUMNNAME` varchar(50) NOT NULL,
  `WIDTH` int(11) DEFAULT NULL,
  `COLUMNINDEX` int(11) DEFAULT NULL,
  PRIMARY KEY (`VIEWID`,`USERNAME`,`COLUMNNAME`),
  KEY `CustomViewColumns0_ndx` (`VIEWID`,`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomViewColumns`
--

LOCK TABLES `CustomViewColumns` WRITE;
/*!40000 ALTER TABLE `CustomViewColumns` DISABLE KEYS */;
INSERT INTO `CustomViewColumns` VALUES ('Alerts','Alerts','root','Failure Object','entity',135,1),('Alerts','Alerts','root','Alarm Group','groupName',150,2),('Alerts','Alerts','root','Alarm Message','message',275,5),('Alerts','Alerts','root','Date/Time','modTime',155,4),('Alerts','Alerts','root','Status','severity',55,0),('Alerts','Alerts','root','Owner','who',60,3),('Alerts','Alerts.1042110065','root','Failure Object','entity',75,1),('Alerts','Alerts.1042110065','root','Alarm Group','groupName',75,2),('Alerts','Alerts.1042110065','root','Alarm Message','message',75,5),('Alerts','Alerts.1042110065','root','Date/Time','modTime',75,4),('Alerts','Alerts.1042110065','root','Status','severity',75,0),('Alerts','Alerts.1042110065','root','Owner','who',75,3),('Audit','ConfigAudit','root','Device Name','deviceName',75,1),('Audit','ConfigAudit','root','Status','status',75,4),('Audit','ConfigAudit','root','Task Name','taskName',150,0),('Audit','ConfigAudit','root','Finish Time','timeOfFinish',75,3),('Audit','ConfigAudit','root','Start Time','timeOfStart',75,2),('Events','Events','root','Status','severity',55,0),('Events','Events','root','Source','source',135,1),('Events','Events','root','Message','text',275,3),('Events','Events','root','Date','time',155,2),('Network Database','Network Database','root','IPAddress','ipAddress',100,1),('Network Database','Network Database','root','IsSnmp','isSNMP',65,4),('Network Database','Network Database','root','Name','name',150,0),('Network Database','Network Database','root','NetMask','netmask',100,5),('Network Database','Network Database','root','Status','status',70,2),('Network Database','Network Database','root','Type','type',90,3),('Network Database','Network Database.CompleteView','root','IPAddress','ipAddress',75,1),('Network Database','Network Database.CompleteView','root','Name','name',75,0),('Network Database','Network Database.CompleteView','root','NetMask','netmask',75,4),('Network Database','Network Database.CompleteView','root','Status','status',75,2),('Network Database','Network Database.CompleteView','root','Type','type',75,3),('Network Database','Network Database.Interfaces','root','IPAddress','ipAddress',75,2),('Network Database','Network Database.Interfaces','root','IsSNMP','isSNMP',75,4),('Network Database','Network Database.Interfaces','root','Name','name',75,0),('Network Database','Network Database.Interfaces','root','NetMask','netmask',75,3),('Network Database','Network Database.Interfaces','root','Status','status',75,5),('Network Database','Network Database.Interfaces','root','Last Status ChangeTime','statusChangeTime',75,6),('Network Database','Network Database.Interfaces','root','Type','type',75,1),('Network Database','Network Database.Networks','root','Discover','discover',75,4),('Network Database','Network Database.Networks','root','IPAddress','ipAddress',75,2),('Network Database','Network Database.Networks','root','Name','name',75,0),('Network Database','Network Database.Networks','root','NetMask','netmask',75,3),('Network Database','Network Database.Networks','root','Status','status',75,5),('Network Database','Network Database.Networks','root','Last Status ChangeTime','statusChangeTime',75,6),('Network Database','Network Database.Networks','root','Type','type',75,1),('Network Database','Network Database.Nodes','root','IPAddress','ipAddress',75,2),('Network Database','Network Database.Nodes','root','IsSNMP','isSNMP',75,4),('Network Database','Network Database.Nodes','root','Name','name',75,0),('Network Database','Network Database.Nodes','root','NetMask','netmask',75,3),('Network Database','Network Database.Nodes','root','Status','status',75,5),('Network Database','Network Database.Nodes','root','Last Status ChangeTime','statusChangeTime',75,6),('Network Database','Network Database.Nodes','root','Type','type',75,1),('Network Database','Network Database.Routers','root','IPAddress','ipAddress',97,2),('Network Database','Network Database.Routers','root','Name','name',89,0),('Network Database','Network Database.Routers','root','NetMask','netmask',100,3),('Network Database','Network Database.Routers','root','Status','status',78,6),('Network Database','Network Database.Routers','root','Last Status Change Time','statusChangeTime',181,7),('Network Database','Network Database.Routers','root','System Description','sysDescr',166,5),('Network Database','Network Database.Routers','root','Type','type',67,1),('Network Database','Network Database.Routers','root','SNMP Version','version',91,4),('Network Database','Network Database.Switches','root','IPAddress','ipAddress',87,1),('Network Database','Network Database.Switches','root','Name','name',93,0),('Network Database','Network Database.Switches','root','NetMask','netmask',91,2),('Network Database','Network Database.Switches','root','Port Count','numPorts',122,5),('Network Database','Network Database.Switches','root','Status','status',99,6),('Network Database','Network Database.Switches','root','Last StatusChangeTime','statusChangeTime',119,7),('Network Database','Network Database.Switches','root','System Description','sysDescr',116,4),('Network Database','Network Database.Switches','root','SNMP Version','version',91,3),('Stats Admin','Stats Admin','root','Active','active',30,6),('Stats Admin','Stats Admin','root','Community','community',75,4),('Stats Admin','Stats Admin','root','DNS Name','dnsName',160,2),('Stats Admin','Stats Admin','root','Poll Id','id',40,1),('Stats Admin','Stats Admin','root','Multiple','isMultiplePolledData',30,7),('Stats Admin','Stats Admin','root','Statistic Name','name',160,0),('Stats Admin','Stats Admin','root','Data Identifier','oid',100,3),('Stats Admin','Stats Admin','root','Interval','period',60,5);
/*!40000 ALTER TABLE `CustomViewColumns` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CustomViewProps`
--

DROP TABLE IF EXISTS `CustomViewProps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CustomViewProps` (
  `MODULE` varchar(25) NOT NULL,
  `VIEWID` varchar(150) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `CRITERIAID` int(11) NOT NULL,
  `PROPKEY` varchar(100) DEFAULT NULL,
  `OPERATOR` varchar(15) DEFAULT NULL,
  `PROPVALUE1` varchar(100) DEFAULT NULL,
  `PROPVALUE2` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`VIEWID`,`USERNAME`,`CRITERIAID`),
  KEY `CustomViewProps0_ndx` (`VIEWID`,`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CustomViewProps`
--

LOCK TABLES `CustomViewProps` WRITE;
/*!40000 ALTER TABLE `CustomViewProps` DISABLE KEYS */;
INSERT INTO `CustomViewProps` VALUES ('Alerts','Alerts.1042110065','root',6,'groupViewMode','=','none','NULL'),('Alerts','Alerts.1042110065','root',7,'stringseverity','!=','Clear','NULL'),('Network Database','Network Database.Interfaces','root',3,'isInterface','=','true','NULL'),('Network Database','Network Database.Networks','root',1,'isNetwork','=','true','NULL'),('Network Database','Network Database.Nodes','root',2,'isNode','=','true','NULL'),('Network Database','Network Database.Routers','root',5,'isRouter','=','true','NULL'),('Network Database','Network Database.Switches','root',4,'type','LIKE','*Switch*','NULL');
/*!40000 ALTER TABLE `CustomViewProps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DASHBOARD`
--

DROP TABLE IF EXISTS `DASHBOARD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DASHBOARD` (
  `ID` bigint(20) NOT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `USERNAME` (`USERNAME`,`NAME`),
  KEY `Dashboard0_Indx` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DASHBOARD`
--

LOCK TABLES `DASHBOARD` WRITE;
/*!40000 ALTER TABLE `DASHBOARD` DISABLE KEYS */;
INSERT INTO `DASHBOARD` VALUES (1001,'root','Monitoring','Monitoring view'),(1002,'root','Overview','Overview');
/*!40000 ALTER TABLE `DASHBOARD` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DASHBOARDCOLUMNS`
--

DROP TABLE IF EXISTS `DASHBOARDCOLUMNS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DASHBOARDCOLUMNS` (
  `ID` bigint(20) NOT NULL DEFAULT '0',
  `COLUMNID` int(11) NOT NULL DEFAULT '0',
  `COLUMNWIDTH` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`,`COLUMNID`),
  KEY `DashboardColumns0_Indx` (`ID`),
  KEY `DashboardColumns1_Indx` (`COLUMNID`),
  CONSTRAINT `DASHBOARDCOLUMN_ID` FOREIGN KEY (`ID`) REFERENCES `DASHBOARD` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DASHBOARDCOLUMNS`
--

LOCK TABLES `DASHBOARDCOLUMNS` WRITE;
/*!40000 ALTER TABLE `DASHBOARDCOLUMNS` DISABLE KEYS */;
INSERT INTO `DASHBOARDCOLUMNS` VALUES (1001,1,50),(1001,2,50),(1002,1,30),(1002,2,40),(1002,3,30);
/*!40000 ALTER TABLE `DASHBOARDCOLUMNS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DASHBOARDPROPS`
--

DROP TABLE IF EXISTS `DASHBOARDPROPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DASHBOARDPROPS` (
  `ID` bigint(20) NOT NULL,
  `PROPERTIES` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `DashboardProps0_Indx` (`ID`),
  CONSTRAINT `DASHBOARDPROPS_ID` FOREIGN KEY (`ID`) REFERENCES `DASHBOARD` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DASHBOARDPROPS`
--

LOCK TABLES `DASHBOARDPROPS` WRITE;
/*!40000 ALTER TABLE `DASHBOARDPROPS` DISABLE KEYS */;
INSERT INTO `DASHBOARDPROPS` VALUES (1001,'{\"treenodeId\":\"\",\"imageUrl\":\"/webclient/common/images/spacer.gif\"}'),(1002,'{\"treenodeId\":\"\",\"imageUrl\":\"/webclient/common/images/spacer.gif\"}');
/*!40000 ALTER TABLE `DASHBOARDPROPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DBPOLICY`
--

DROP TABLE IF EXISTS `DBPOLICY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DBPOLICY` (
  `KEYSTRING` varchar(250) NOT NULL,
  `VALUESTRING` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`KEYSTRING`),
  KEY `dbpolicy0_ndx` (`KEYSTRING`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DBPOLICY`
--

LOCK TABLES `DBPOLICY` WRITE;
/*!40000 ALTER TABLE `DBPOLICY` DISABLE KEYS */;
INSERT INTO `DBPOLICY` VALUES ('TableCleanupPolicy1','StatsTableCleanup');
/*!40000 ALTER TABLE `DBPOLICY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DECIMALDATA11_10_2016`
--

DROP TABLE IF EXISTS `DECIMALDATA11_10_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DECIMALDATA11_10_2016` (
  `POLLID` bigint(20) NOT NULL,
  `INSTANCE` varchar(100) NOT NULL,
  `TTIME` bigint(20) NOT NULL,
  `VAL` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DECIMALDATA11_10_2016`
--

LOCK TABLES `DECIMALDATA11_10_2016` WRITE;
/*!40000 ALTER TABLE `DECIMALDATA11_10_2016` DISABLE KEYS */;
/*!40000 ALTER TABLE `DECIMALDATA11_10_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DECIMALDATA11_4_2016`
--

DROP TABLE IF EXISTS `DECIMALDATA11_4_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DECIMALDATA11_4_2016` (
  `POLLID` bigint(20) NOT NULL,
  `INSTANCE` varchar(100) NOT NULL,
  `TTIME` bigint(20) NOT NULL,
  `VAL` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DECIMALDATA11_4_2016`
--

LOCK TABLES `DECIMALDATA11_4_2016` WRITE;
/*!40000 ALTER TABLE `DECIMALDATA11_4_2016` DISABLE KEYS */;
INSERT INTO `DECIMALDATA11_4_2016` VALUES (21,'.3',1478262361100,0.0000),(21,'.3',1478266561099,0.0000),(21,'.8',1478262361100,0.0000),(21,'.8',1478266561099,0.0000),(22,'.3',1478258161098,0.0000),(22,'.3',1478259361101,0.0000),(22,'.3',1478260561097,0.0000),(22,'.3',1478261161101,0.0000),(22,'.3',1478261761099,0.0000),(22,'.3',1478262361100,0.0000),(22,'.3',1478262961102,0.0000),(22,'.3',1478263561103,0.0000),(22,'.3',1478264161100,0.0000),(22,'.3',1478264761099,0.0000),(22,'.3',1478265361098,0.0000),(22,'.3',1478265961100,0.0000),(22,'.3',1478266561099,0.0000),(22,'.3',1478267161099,0.0000),(22,'.3',1478267761100,0.0000),(22,'.3',1478268361098,0.0000),(22,'.3',1478269561100,0.0000),(22,'.3',1478270161097,0.0000),(22,'.8',1478258161098,0.0000),(22,'.8',1478259361101,0.0000),(22,'.8',1478260561097,0.0000),(22,'.8',1478261161101,0.0000),(22,'.8',1478261761099,0.0000),(22,'.8',1478262361100,0.0000),(22,'.8',1478262961102,0.0000),(22,'.8',1478263561103,0.0000),(22,'.8',1478264161100,0.0000),(22,'.8',1478264761099,0.0000),(22,'.8',1478265361098,0.0000),(22,'.8',1478265961100,0.0000),(22,'.8',1478266561099,0.0000),(22,'.8',1478267161099,0.0000),(22,'.8',1478267761100,0.0000),(22,'.8',1478268361098,0.0000),(22,'.8',1478269561100,0.0000),(22,'.8',1478270161097,0.0000),(23,'.3',1478262361100,0.0000),(23,'.3',1478266561099,0.0000),(23,'.8',1478262361100,0.0000),(23,'.8',1478266561099,0.0000),(24,'.3',1478262361100,0.0000),(24,'.3',1478266561099,0.0000),(24,'.8',1478262361100,0.0000),(24,'.8',1478266561099,0.0000),(25,'.3',1478262361100,0.0000),(25,'.3',1478266561099,0.0000),(25,'.8',1478262361100,0.0000),(25,'.8',1478266561099,0.0000),(26,'.3',1478262361100,0.0000),(26,'.3',1478266561099,0.0000),(26,'.8',1478262361100,0.0000),(26,'.8',1478266561099,0.0000);
/*!40000 ALTER TABLE `DECIMALDATA11_4_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DECIMALDATA11_5_2016`
--

DROP TABLE IF EXISTS `DECIMALDATA11_5_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DECIMALDATA11_5_2016` (
  `POLLID` bigint(20) NOT NULL,
  `INSTANCE` varchar(100) NOT NULL,
  `TTIME` bigint(20) NOT NULL,
  `VAL` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DECIMALDATA11_5_2016`
--

LOCK TABLES `DECIMALDATA11_5_2016` WRITE;
/*!40000 ALTER TABLE `DECIMALDATA11_5_2016` DISABLE KEYS */;
/*!40000 ALTER TABLE `DECIMALDATA11_5_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DEVICELIST_AGENTNAMES`
--

DROP TABLE IF EXISTS `DEVICELIST_AGENTNAMES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DEVICELIST_AGENTNAMES` (
  `DEVICELISTID` bigint(20) NOT NULL,
  `AGENTNAME` varchar(255) DEFAULT NULL,
  KEY `FKDD1A908EF8844ACD` (`DEVICELISTID`),
  CONSTRAINT `FKDD1A908EF8844ACD` FOREIGN KEY (`DEVICELISTID`) REFERENCES `KPIDeviceList` (`DEVICELISTID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DEVICELIST_AGENTNAMES`
--

LOCK TABLES `DEVICELIST_AGENTNAMES` WRITE;
/*!40000 ALTER TABLE `DEVICELIST_AGENTNAMES` DISABLE KEYS */;
INSERT INTO `DEVICELIST_AGENTNAMES` VALUES (2,'IF-rejoe-0162.csez.zohocorpin.com'),(2,'IF-192.168.220.202'),(2,'rejoe-0162.csez.zohocorpin.com');
/*!40000 ALTER TABLE `DEVICELIST_AGENTNAMES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DataCollectionAttributes`
--

DROP TABLE IF EXISTS `DataCollectionAttributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DataCollectionAttributes` (
  `NAME` varchar(50) DEFAULT NULL,
  `PROPKEY` varchar(50) DEFAULT NULL,
  `PROPNAME` varchar(30) DEFAULT NULL,
  `PROPVAL` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DataCollectionAttributes`
--

LOCK TABLES `DataCollectionAttributes` WRITE;
/*!40000 ALTER TABLE `DataCollectionAttributes` DISABLE KEYS */;
INSERT INTO `DataCollectionAttributes` VALUES ('Win_95','Win_951','name','INTERFACE_in_octets'),('Win_95','Win_951','type','interface'),('Win_95','Win_951','oid','2.2.1.10'),('Win_95','Win_952','name','INTERFACE_out_octets'),('Win_95','Win_952','type','interface'),('Win_95','Win_952','oid','2.2.1.16'),('Win_95','Win_953','name','NODE_sysOID'),('Win_95','Win_953','type','node'),('Win_95','Win_953','oid','1.2.0'),('Win_95','NULL','pollingPeriod','300'),('.1.3.6.1.4.1.43.1.8.13','.1.3.6.1.4.1.43.1.8.131','name','INTERFACE_in_discards'),('.1.3.6.1.4.1.43.1.8.13','.1.3.6.1.4.1.43.1.8.131','type','interface'),('.1.3.6.1.4.1.43.1.8.13','.1.3.6.1.4.1.43.1.8.131','oid','2.2.1.13'),('.1.3.6.1.4.1.43.1.8.13','.1.3.6.1.4.1.43.1.8.132','name','INTERFACE_out_discards'),('.1.3.6.1.4.1.43.1.8.13','.1.3.6.1.4.1.43.1.8.132','type','interface'),('.1.3.6.1.4.1.43.1.8.13','.1.3.6.1.4.1.43.1.8.132','oid','2.2.1.19'),('.1.3.6.1.4.1.43.1.8.13','NULL','pollingPeriod','600'),('snmpnode','snmpnode1','name','INTERFACE_out_octets'),('snmpnode','snmpnode1','type','interface'),('snmpnode','snmpnode1','oid','2.2.1.16.'),('snmpnode','snmpnode2','name','INTERFACE_in_octets'),('snmpnode','snmpnode2','type','interface'),('snmpnode','snmpnode2','oid','2.2.1.10.'),('snmpnode','NULL','pollingPeriod','600'),('WindowsCPUUtilization','WindowsCPUUtilization1','name','CPUUtilization'),('WindowsCPUUtilization','WindowsCPUUtilization1','Active','true'),('WindowsCPUUtilization','WindowsCPUUtilization1','type','multiple'),('WindowsCPUUtilization','WindowsCPUUtilization1','oid','.1.3.6.1.2.1.25.3.3.1.2'),('WindowsCPUUtilization','NULL','pollingPeriod','300'),('LinuxCPUUtilization','LinuxCPUUtilization1','name','CPUUtilization'),('LinuxCPUUtilization','LinuxCPUUtilization1','Active','true'),('LinuxCPUUtilization','LinuxCPUUtilization1','type','multiple'),('LinuxCPUUtilization','LinuxCPUUtilization1','oid','.1.3.6.1.4.1.2021.10.1.5'),('LinuxCPUUtilization','NULL','pollingPeriod','300'),('SolarisCPUUtilization','SolarisCPUUtilization1','name','CPUUtilization'),('SolarisCPUUtilization','SolarisCPUUtilization1','Active','true'),('SolarisCPUUtilization','SolarisCPUUtilization1','type','multiple'),('SolarisCPUUtilization','SolarisCPUUtilization1','oid','.1.3.6.1.4.1.2021.10.1.5'),('SolarisCPUUtilization','NULL','pollingPeriod','300'),('MemoryUtilization','MemoryUtilization1','name','MemoryUtilization'),('MemoryUtilization','MemoryUtilization1','type','multiple'),('MemoryUtilization','MemoryUtilization1','oid','.1.3.6.1.2.1.25.5.1.1.2'),('MemoryUtilization','MemoryUtilization2','name','hrStorageType_RAM'),('MemoryUtilization','MemoryUtilization2','type','multiple'),('MemoryUtilization','MemoryUtilization2','oid','.1.3.6.1.2.1.25.2.3.1.2'),('MemoryUtilization','MemoryUtilization3','name','hrStorageUsed_RAM'),('MemoryUtilization','MemoryUtilization3','type','multiple'),('MemoryUtilization','MemoryUtilization3','oid','.1.3.6.1.2.1.25.2.3.1.6'),('MemoryUtilization','MemoryUtilization4','name','hrStorageAllocationUnits_RAM'),('MemoryUtilization','MemoryUtilization4','type','multiple'),('MemoryUtilization','MemoryUtilization4','oid','.1.3.6.1.2.1.25.2.3.1.4'),('MemoryUtilization','MemoryUtilization5','name','hrStorageSize_RAM'),('MemoryUtilization','MemoryUtilization5','type','node'),('MemoryUtilization','MemoryUtilization5','oid','.1.3.6.1.2.1.25.2.2.0'),('MemoryUtilization','NULL','pollingPeriod','300'),('DevicesTraffic','DevicesTraffic1','name','Device_INTERFACE_out_octets'),('DevicesTraffic','DevicesTraffic1','type','multiple'),('DevicesTraffic','DevicesTraffic1','oid','.1.3.6.1.2.1.2.2.1.16*8/$DELTA_TIME'),('DevicesTraffic','DevicesTraffic2','name','Device_INTERFACE_in_octets'),('DevicesTraffic','DevicesTraffic2','type','multiple'),('DevicesTraffic','DevicesTraffic2','oid','.1.3.6.1.2.1.2.2.1.10*8/$DELTA_TIME'),('DevicesTraffic','NULL','pollingPeriod','600'),('ReceiveErrors','ReceiveErrors1','name','ReceiveErrors'),('ReceiveErrors','ReceiveErrors1','type','multiple'),('ReceiveErrors','ReceiveErrors1','oid','.1.3.6.1.2.1.2.2.1.14/$DELTA_TIME'),('ReceiveErrors','NULL','pollingPeriod','600'),('TransmitErrors','TransmitErrors1','name','TransmitErrors'),('TransmitErrors','TransmitErrors1','type','multiple'),('TransmitErrors','TransmitErrors1','oid','.1.3.6.1.2.1.2.2.1.20/$DELTA_TIME'),('TransmitErrors','NULL','pollingPeriod','600'),('InDiscards','InDiscards1','name','InDiscards'),('InDiscards','InDiscards1','type','multiple'),('InDiscards','InDiscards1','oid','.1.3.6.1.2.1.2.2.1.13/$DELTA_TIME'),('InDiscards','NULL','pollingPeriod','600'),('OutDiscards','OutDiscards1','name','OutDiscards'),('OutDiscards','OutDiscards1','type','multiple'),('OutDiscards','OutDiscards1','oid','.1.3.6.1.2.1.2.2.1.19/$DELTA_TIME'),('OutDiscards','NULL','pollingPeriod','600'),('InterfaceOutUtilization','InterfaceOutUtilization1','name','InterfaceOutUtilization'),('InterfaceOutUtilization','InterfaceOutUtilization1','type','multiple'),('InterfaceOutUtilization','InterfaceOutUtilization1','oid','(.1.3.6.1.2.1.2.2.1.16*8*100)/(.1.3.6.1.2.1.2.2.1.5*$DELTA_TIME)'),('InterfaceOutUtilization','NULL','pollingPeriod','600'),('InterfaceInUtilization','InterfaceInUtilization1','name','InterfaceInUtilization'),('InterfaceInUtilization','InterfaceInUtilization1','type','multiple'),('InterfaceInUtilization','InterfaceInUtilization1','oid','(.1.3.6.1.2.1.2.2.1.10*8*100)/(.1.3.6.1.2.1.2.2.1.5*$DELTA_TIME)'),('InterfaceInUtilization','NULL','pollingPeriod','600'),('RouterCPUUtilization','RouterCPUUtilization1','name','RouterCPUUtilization'),('RouterCPUUtilization','RouterCPUUtilization1','Active','true'),('RouterCPUUtilization','RouterCPUUtilization1','type','node'),('RouterCPUUtilization','RouterCPUUtilization1','oid','.1.3.6.1.4.1.9.2.1.58.0'),('RouterCPUUtilization','NULL','pollingPeriod','600'),('RouterMemoryUtilization','RouterMemoryUtilization1','name','RouterMemoryUtilization'),('RouterMemoryUtilization','RouterMemoryUtilization1','Active','true'),('RouterMemoryUtilization','RouterMemoryUtilization1','type','node'),('RouterMemoryUtilization','RouterMemoryUtilization1','oid','(.1.3.6.1.4.1.9.9.48.1.1.1.5.1*100)/(.1.3.6.1.4.1.9.9.48.1.1.1.5.1+.1.3.6.1.4.1.9.9.48.1.1.1.6.1)'),('RouterMemoryUtilization','NULL','pollingPeriod','600'),('RouterBufferFailures','RouterBufferFailures1','name','BufferNoMemFailures'),('RouterBufferFailures','RouterBufferFailures1','type','node'),('RouterBufferFailures','RouterBufferFailures1','Active','true'),('RouterBufferFailures','RouterBufferFailures1','oid','.1.3.6.1.4.1.9.2.1.47.0'),('RouterBufferFailures','RouterBufferFailures1','timeAvg','true'),('RouterBufferFailures','NULL','pollingPeriod','600'),('RouterSmallBuffHits','RouterSmallBuffHits1','name','SmallBufferHits'),('RouterSmallBuffHits','RouterSmallBuffHits1','type','node'),('RouterSmallBuffHits','RouterSmallBuffHits1','Active','true'),('RouterSmallBuffHits','RouterSmallBuffHits1','oid','(.1.3.6.1.4.1.9.2.1.18.0*100)/(.1.3.6.1.4.1.9.2.1.18.0+.1.3.6.1.4.1.9.2.1.26.0+.1.3.6.1.4.1.9.2.1.34.0+.1.3.6.1.4.1.9.2.1.42.0+.1.3.6.1.4.1.9.2.1.66.0)'),('RouterSmallBuffHits','RouterSmallBuffHits1','saveAbsolute','false'),('RouterSmallBuffHits','NULL','pollingPeriod','600'),('RouterMedBuffHits','RouterMedBuffHits1','name','MediumBufferHits'),('RouterMedBuffHits','RouterMedBuffHits1','type','node'),('RouterMedBuffHits','RouterMedBuffHits1','Active','true'),('RouterMedBuffHits','RouterMedBuffHits1','oid','(.1.3.6.1.4.1.9.2.1.26.0*100)/(.1.3.6.1.4.1.9.2.1.18.0+.1.3.6.1.4.1.9.2.1.26.0+.1.3.6.1.4.1.9.2.1.34.0+.1.3.6.1.4.1.9.2.1.42.0+.1.3.6.1.4.1.9.2.1.66.0)'),('RouterMedBuffHits','RouterMedBuffHits1','saveAbsolute','false'),('RouterMedBuffHits','NULL','pollingPeriod','600'),('RouterBigBuffHits','RouterBigBuffHits1','name','BigBufferHits'),('RouterBigBuffHits','RouterBigBuffHits1','type','node'),('RouterBigBuffHits','RouterBigBuffHits1','Active','true'),('RouterBigBuffHits','RouterBigBuffHits1','oid','(.1.3.6.1.4.1.9.2.1.34.0*100)/(.1.3.6.1.4.1.9.2.1.18.0+.1.3.6.1.4.1.9.2.1.26.0+.1.3.6.1.4.1.9.2.1.34.0+.1.3.6.1.4.1.9.2.1.42.0+.1.3.6.1.4.1.9.2.1.66.0)'),('RouterBigBuffHits','RouterBigBuffHits1','saveAbsolute','false'),('RouterBigBuffHits','NULL','pollingPeriod','600'),('RouterLarBuffHits','RouterLarBuffHits1','name','LargeBufferHits'),('RouterLarBuffHits','RouterLarBuffHits1','type','node'),('RouterLarBuffHits','RouterLarBuffHits1','Active','true'),('RouterLarBuffHits','RouterLarBuffHits1','oid','(.1.3.6.1.4.1.9.2.1.42.0*100)/(.1.3.6.1.4.1.9.2.1.18.0+.1.3.6.1.4.1.9.2.1.26.0+.1.3.6.1.4.1.9.2.1.34.0+.1.3.6.1.4.1.9.2.1.42.0+.1.3.6.1.4.1.9.2.1.66.0)'),('RouterLarBuffHits','RouterLarBuffHits1','saveAbsolute','false'),('RouterLarBuffHits','NULL','pollingPeriod','600'),('RouterHugeBuffHits','RouterHugeBuffHits1','name','HugeBufferHits'),('RouterHugeBuffHits','RouterHugeBuffHits1','type','node'),('RouterHugeBuffHits','RouterHugeBuffHits1','Active','true'),('RouterHugeBuffHits','RouterHugeBuffHits1','oid','(.1.3.6.1.4.1.9.2.1.66.0*100)/(.1.3.6.1.4.1.9.2.1.18.0+.1.3.6.1.4.1.9.2.1.26.0+.1.3.6.1.4.1.9.2.1.34.0+.1.3.6.1.4.1.9.2.1.42.0+.1.3.6.1.4.1.9.2.1.66.0)'),('RouterHugeBuffHits','RouterHugeBuffHits1','saveAbsolute','false'),('RouterHugeBuffHits','NULL','pollingPeriod','600'),('RouterSmallBuffMisses','RouterSmallBuffMisses1','name','SmallBufferMisses'),('RouterSmallBuffMisses','RouterSmallBuffMisses1','type','node'),('RouterSmallBuffMisses','RouterSmallBuffMisses1','Active','true'),('RouterSmallBuffMisses','RouterSmallBuffMisses1','oid','.1.3.6.1.4.1.9.2.1.19.0'),('RouterSmallBuffMisses','RouterSmallBuffMisses1','timeAvg','true'),('RouterSmallBuffMisses','RouterSmallBuffMisses1','saveAbsolute','false'),('RouterSmallBuffMisses','NULL','pollingPeriod','600'),('RouterMedBuffMisses','RouterMedBuffMisses1','name','MediumBufferMisses'),('RouterMedBuffMisses','RouterMedBuffMisses1','type','node'),('RouterMedBuffMisses','RouterMedBuffMisses1','Active','true'),('RouterMedBuffMisses','RouterMedBuffMisses1','oid','.1.3.6.1.4.1.9.2.1.27.0'),('RouterMedBuffMisses','RouterMedBuffMisses1','timeAvg','true'),('RouterMedBuffMisses','RouterMedBuffMisses1','saveAbsolute','false'),('RouterMedBuffMisses','NULL','pollingPeriod','600'),('RouterBigBuffMisses','RouterBigBuffMisses1','name','BigBufferMisses'),('RouterBigBuffMisses','RouterBigBuffMisses1','type','node'),('RouterBigBuffMisses','RouterBigBuffMisses1','Active','true'),('RouterBigBuffMisses','RouterBigBuffMisses1','oid','.1.3.6.1.4.1.9.2.1.35.0'),('RouterBigBuffMisses','RouterBigBuffMisses1','timeAvg','true'),('RouterBigBuffMisses','RouterBigBuffMisses1','saveAbsolute','false'),('RouterBigBuffMisses','NULL','pollingPeriod','600'),('RouterLarBuffMisses','RouterLarBuffMisses1','name','LargeBufferMisses'),('RouterLarBuffMisses','RouterLarBuffMisses1','type','node'),('RouterLarBuffMisses','RouterLarBuffMisses1','Active','true'),('RouterLarBuffMisses','RouterLarBuffMisses1','oid','.1.3.6.1.4.1.9.2.1.43.0'),('RouterLarBuffMisses','RouterLarBuffMisses1','timeAvg','true'),('RouterLarBuffMisses','RouterLarBuffMisses1','saveAbsolute','false'),('RouterLarBuffMisses','NULL','pollingPeriod','600'),('RouterHugeBuffMisses','RouterHugeBuffMisses1','name','HugeBufferMisses'),('RouterHugeBuffMisses','RouterHugeBuffMisses1','type','node'),('RouterHugeBuffMisses','RouterHugeBuffMisses1','Active','true'),('RouterHugeBuffMisses','RouterHugeBuffMisses1','oid','.1.3.6.1.4.1.9.2.1.67.0'),('RouterHugeBuffMisses','RouterHugeBuffMisses1','timeAvg','true'),('RouterHugeBuffMisses','RouterHugeBuffMisses1','saveAbsolute','false'),('RouterHugeBuffMisses','NULL','pollingPeriod','600'),('RouterTraffic','RouterTraffic1','name','Router_INTERFACE_out_octets'),('RouterTraffic','RouterTraffic1','type','multiple'),('RouterTraffic','RouterTraffic1','oid','.1.3.6.1.2.1.2.2.1.16*8/$DELTA_TIME'),('RouterTraffic','RouterTraffic2','name','Router_INTERFACE_in_octets'),('RouterTraffic','RouterTraffic2','type','multiple'),('RouterTraffic','RouterTraffic2','oid','.1.3.6.1.2.1.2.2.1.10*8/$DELTA_TIME'),('RouterTraffic','NULL','pollingPeriod','600'),('RouterReceiveErrors','RouterReceiveErrors1','name','RouterReceiveErrors'),('RouterReceiveErrors','RouterReceiveErrors1','type','multiple'),('RouterReceiveErrors','RouterReceiveErrors1','oid','.1.3.6.1.2.1.2.2.1.14/$DELTA_TIME'),('RouterReceiveErrors','NULL','pollingPeriod','600'),('RouterTransmitErrors','RouterTransmitErrors1','name','RouterTransmitErrors'),('RouterTransmitErrors','RouterTransmitErrors1','type','multiple'),('RouterTransmitErrors','RouterTransmitErrors1','oid','.1.3.6.1.2.1.2.2.1.20/$DELTA_TIME'),('RouterTransmitErrors','NULL','pollingPeriod','600'),('RouterInDiscards','RouterInDiscards1','name','RouterInDiscards'),('RouterInDiscards','RouterInDiscards1','type','multiple'),('RouterInDiscards','RouterInDiscards1','oid','.1.3.6.1.2.1.2.2.1.13/$DELTA_TIME'),('RouterInDiscards','NULL','pollingPeriod','600'),('RouterOutDiscards','RouterOutDiscards1','name','RouterOutDiscards'),('RouterOutDiscards','RouterOutDiscards1','type','multiple'),('RouterOutDiscards','RouterOutDiscards1','oid','.1.3.6.1.2.1.2.2.1.19/$DELTA_TIME'),('RouterOutDiscards','NULL','pollingPeriod','600'),('RouterInterfaceOutUtilization','RouterInterfaceOutUtilization1','name','RouterInterfaceOutUtilization'),('RouterInterfaceOutUtilization','RouterInterfaceOutUtilization1','type','multiple'),('RouterInterfaceOutUtilization','RouterInterfaceOutUtilization1','oid','(.1.3.6.1.2.1.2.2.1.16*8*100)/(.1.3.6.1.2.1.2.2.1.5*$DELTA_TIME)'),('RouterInterfaceOutUtilization','NULL','pollingPeriod','600'),('RouterInterfaceInUtilization','RouterInterfaceInUtilization1','name','RouterInterfaceInUtilization'),('RouterInterfaceInUtilization','RouterInterfaceInUtilization1','type','multiple'),('RouterInterfaceInUtilization','RouterInterfaceInUtilization1','oid','(.1.3.6.1.2.1.2.2.1.10*8*100)/(.1.3.6.1.2.1.2.2.1.5*$DELTA_TIME)'),('RouterInterfaceInUtilization','NULL','pollingPeriod','600'),('CiscoSwitchMemoryUtilization','CiscoSwitchMemoryUtilization1','name','SwitchMemoryUtilization'),('CiscoSwitchMemoryUtilization','CiscoSwitchMemoryUtilization1','Active','true'),('CiscoSwitchMemoryUtilization','CiscoSwitchMemoryUtilization1','type','node'),('CiscoSwitchMemoryUtilization','CiscoSwitchMemoryUtilization1','oid','(.1.3.6.1.4.1.9.9.48.1.1.1.5.1*100)/(.1.3.6.1.4.1.9.9.48.1.1.1.5.1+.1.3.6.1.4.1.9.9.48.1.1.1.6.1)'),('CiscoSwitchMemoryUtilization','NULL','pollingPeriod','600'),('Switch_Traffic','Switch_Traffic1','name','Switch_Out_octets'),('Switch_Traffic','Switch_Traffic1','type','multiple'),('Switch_Traffic','Switch_Traffic1','oid','.1.3.6.1.2.1.2.2.1.16*8/$DELTA_TIME'),('Switch_Traffic','Switch_Traffic2','name','Switch_In_octets'),('Switch_Traffic','Switch_Traffic2','type','multiple'),('Switch_Traffic','Switch_Traffic2','oid','.1.3.6.1.2.1.2.2.1.10*8/$DELTA_TIME'),('Switch_Traffic','NULL','pollingPeriod','600'),('Switch_ReceiveErrors','Switch_ReceiveErrors1','name','Switch_ReceiveErrors'),('Switch_ReceiveErrors','Switch_ReceiveErrors1','type','multiple'),('Switch_ReceiveErrors','Switch_ReceiveErrors1','oid','.1.3.6.1.2.1.2.2.1.14/$DELTA_TIME'),('Switch_ReceiveErrors','NULL','pollingPeriod','600'),('Switch_TransmitErrors','Switch_TransmitErrors1','name','Switch_TransmitErrors'),('Switch_TransmitErrors','Switch_TransmitErrors1','type','multiple'),('Switch_TransmitErrors','Switch_TransmitErrors1','oid','.1.3.6.1.2.1.2.2.1.20/$DELTA_TIME'),('Switch_TransmitErrors','NULL','pollingPeriod','600'),('Switch_InDiscards','Switch_InDiscards1','name','Switch_InDiscards'),('Switch_InDiscards','Switch_InDiscards1','type','multiple'),('Switch_InDiscards','Switch_InDiscards1','oid','.1.3.6.1.2.1.2.2.1.13/$DELTA_TIME'),('Switch_InDiscards','NULL','pollingPeriod','600'),('Switch_OutDiscards','Switch_OutDiscards1','name','Switch_OutDiscards'),('Switch_OutDiscards','Switch_OutDiscards1','type','multiple'),('Switch_OutDiscards','Switch_OutDiscards1','oid','.1.3.6.1.2.1.2.2.1.19/$DELTA_TIME'),('Switch_OutDiscards','NULL','pollingPeriod','600'),('Switch_InterfaceOutUtilization','Switch_InterfaceOutUtilization1','name','Switch_InterfaceOutUtilization'),('Switch_InterfaceOutUtilization','Switch_InterfaceOutUtilization1','type','multiple'),('Switch_InterfaceOutUtilization','Switch_InterfaceOutUtilization1','oid','(.1.3.6.1.2.1.2.2.1.16*8*100)/(.1.3.6.1.2.1.2.2.1.5*$DELTA_TIME)'),('Switch_InterfaceOutUtilization','NULL','pollingPeriod','600'),('Switch_InterfaceInUtilization','Switch_InterfaceInUtilization1','name','Switch_InterfaceInUtilization'),('Switch_InterfaceInUtilization','Switch_InterfaceInUtilization1','type','multiple'),('Switch_InterfaceInUtilization','Switch_InterfaceInUtilization1','oid','(.1.3.6.1.2.1.2.2.1.10*8*100)/(.1.3.6.1.2.1.2.2.1.5*$DELTA_TIME)'),('Switch_InterfaceInUtilization','NULL','pollingPeriod','600');
/*!40000 ALTER TABLE `DataCollectionAttributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DeviceAudit`
--

DROP TABLE IF EXISTS `DeviceAudit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DeviceAudit` (
  `ID` int(11) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `TASKNAME` varchar(50) DEFAULT NULL,
  `SUBTASKNAME` varchar(50) DEFAULT NULL,
  `DEVICENAME` varchar(50) DEFAULT NULL,
  `TIMEOFSTART` varchar(30) DEFAULT NULL,
  `TIMEOFFINISH` varchar(30) DEFAULT NULL,
  `STATUS` varchar(15) DEFAULT NULL,
  `ROLLBACKSTATUS` varchar(15) DEFAULT NULL,
  `EXECUTIONID` int(11) DEFAULT NULL,
  `DEVICELIST` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DeviceAudit`
--

LOCK TABLES `DeviceAudit` WRITE;
/*!40000 ALTER TABLE `DeviceAudit` DISABLE KEYS */;
/*!40000 ALTER TABLE `DeviceAudit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DeviceCompSchedule`
--

DROP TABLE IF EXISTS `DeviceCompSchedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DeviceCompSchedule` (
  `SCHEDULEID` bigint(20) NOT NULL,
  `SUBCOMPNAME` varchar(100) NOT NULL,
  `POLLINTERVAL` int(11) DEFAULT NULL,
  `deviceKpiId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`SCHEDULEID`),
  KEY `FK9E94845C901D212` (`deviceKpiId`),
  CONSTRAINT `FK9E94845C901D212` FOREIGN KEY (`deviceKpiId`) REFERENCES `KPIObject` (`KPIID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DeviceCompSchedule`
--

LOCK TABLES `DeviceCompSchedule` WRITE;
/*!40000 ALTER TABLE `DeviceCompSchedule` DISABLE KEYS */;
INSERT INTO `DeviceCompSchedule` VALUES (1,'Switch_TxPackets',60,2),(2,'Switch_TxPackets',60,3);
/*!40000 ALTER TABLE `DeviceCompSchedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DeviceDSPolledData`
--

DROP TABLE IF EXISTS `DeviceDSPolledData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DeviceDSPolledData` (
  `ID` bigint(20) NOT NULL,
  `KPIOBJNAME` varchar(100) DEFAULT NULL,
  `SUBCOMPNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK18865CCD44CED9A2` (`ID`),
  CONSTRAINT `FK18865CCD44CED9A2` FOREIGN KEY (`ID`) REFERENCES `PolledData` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DeviceDSPolledData`
--

LOCK TABLES `DeviceDSPolledData` WRITE;
/*!40000 ALTER TABLE `DeviceDSPolledData` DISABLE KEYS */;
/*!40000 ALTER TABLE `DeviceDSPolledData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DeviceList`
--

DROP TABLE IF EXISTS `DeviceList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DeviceList` (
  `USERNAME` varchar(50) DEFAULT NULL,
  `PROTOCOL` varchar(10) DEFAULT NULL,
  `DEVICELISTNAME` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DeviceList`
--

LOCK TABLES `DeviceList` WRITE;
/*!40000 ALTER TABLE `DeviceList` DISABLE KEYS */;
/*!40000 ALTER TABLE `DeviceList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DeviceListDetails`
--

DROP TABLE IF EXISTS `DeviceListDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DeviceListDetails` (
  `DKEY` varchar(200) DEFAULT NULL,
  `DEVICENAME` varchar(50) DEFAULT NULL,
  `PORT` varchar(5) DEFAULT NULL,
  `RETRIES` int(11) DEFAULT NULL,
  `TIMEOUT` int(11) DEFAULT NULL,
  `DEVICESEQUENCE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DeviceListDetails`
--

LOCK TABLES `DeviceListDetails` WRITE;
/*!40000 ALTER TABLE `DeviceListDetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `DeviceListDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DeviceUserProps`
--

DROP TABLE IF EXISTS `DeviceUserProps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DeviceUserProps` (
  `DKEY` varchar(200) DEFAULT NULL,
  `NAME` varchar(200) DEFAULT NULL,
  `VALUE` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DeviceUserProps`
--

LOCK TABLES `DeviceUserProps` WRITE;
/*!40000 ALTER TABLE `DeviceUserProps` DISABLE KEYS */;
/*!40000 ALTER TABLE `DeviceUserProps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ENGINES`
--

DROP TABLE IF EXISTS `ENGINES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ENGINES` (
  `ENGINENAMES` varchar(100) NOT NULL,
  PRIMARY KEY (`ENGINENAMES`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ENGINES`
--

LOCK TABLES `ENGINES` WRITE;
/*!40000 ALTER TABLE `ENGINES` DISABLE KEYS */;
/*!40000 ALTER TABLE `ENGINES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ENGINETABLE`
--

DROP TABLE IF EXISTS `ENGINETABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ENGINETABLE` (
  `DBKEY` varchar(57) NOT NULL,
  `HOST` varchar(50) DEFAULT NULL,
  `PORT` varchar(5) DEFAULT NULL,
  `ENGINENAME` varchar(50) DEFAULT NULL,
  `ENGINEID` varchar(64) DEFAULT NULL,
  `ENGINETIME` varchar(10) DEFAULT NULL,
  `ENGINEBOOTS` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`DBKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ENGINETABLE`
--

LOCK TABLES `ENGINETABLE` WRITE;
/*!40000 ALTER TABLE `ENGINETABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `ENGINETABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Event`
--

DROP TABLE IF EXISTS `Event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Event` (
  `ID` int(11) NOT NULL,
  `DISCRIMINATOR` varchar(30) NOT NULL,
  `TEXT` varchar(250) DEFAULT NULL,
  `CATEGORY` varchar(100) DEFAULT NULL,
  `NETWORK` varchar(100) DEFAULT NULL,
  `NODE` varchar(100) DEFAULT NULL,
  `ENTITY` varchar(100) DEFAULT NULL,
  `SEVERITY` int(11) DEFAULT NULL,
  `TTIME` bigint(20) DEFAULT NULL,
  `SOURCE` varchar(100) DEFAULT NULL,
  `WEBNMS` varchar(100) DEFAULT NULL,
  `GROUPNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Event`
--

LOCK TABLES `Event` WRITE;
/*!40000 ALTER TABLE `Event` DISABLE KEYS */;
INSERT INTO `Event` VALUES (1,'Event','Network Added to Database','Topology',NULL,'172.24.14.0','172.24.14.0',6,1478254025829,'172.24.14.0',NULL,NULL),(2,'Event','Interface Added to Database','Topology',NULL,'guhan-3315.csez.zohocorpin.com','IF-guhan-3315.csez.zohocorpin.com',6,1478254040243,'IF-guhan-3315.csez.zohocorpin.com',NULL,NULL),(3,'Event','Node Added to Database','Topology',NULL,'guhan-3315.csez.zohocorpin.com','guhan-3315.csez.zohocorpin.com',6,1478254040284,'guhan-3315.csez.zohocorpin.com',NULL,NULL),(4,'Event','Interface Added to Database','Topology',NULL,'vineesh-3479.csez.zohocorpin.com','IF-vineesh-3479.csez.zohocorpin.com',6,1478254053652,'IF-vineesh-3479.csez.zohocorpin.com',NULL,NULL),(5,'Event','Node Added to Database','Topology',NULL,'vineesh-3479.csez.zohocorpin.com','vineesh-3479.csez.zohocorpin.com',6,1478254053682,'vineesh-3479.csez.zohocorpin.com',NULL,NULL),(6,'Event','Interface Added to Database','Topology',NULL,'varun-3902.csez.zohocorpin.com','IF-varun-3902.csez.zohocorpin.com',6,1478254075939,'IF-varun-3902.csez.zohocorpin.com',NULL,NULL),(7,'Event','Node Added to Database','Topology',NULL,'varun-3902.csez.zohocorpin.com','varun-3902.csez.zohocorpin.com',6,1478254075964,'varun-3902.csez.zohocorpin.com',NULL,NULL),(8,'Event','Interface Added to Database','Topology',NULL,'mani-4546.csez.zohocorpin.com','IF-mani-4546.csez.zohocorpin.com',6,1478254076273,'IF-mani-4546.csez.zohocorpin.com',NULL,NULL),(9,'Event','Node Added to Database','Topology',NULL,'mani-4546.csez.zohocorpin.com','mani-4546.csez.zohocorpin.com',6,1478254076296,'mani-4546.csez.zohocorpin.com',NULL,NULL),(10,'Event','Interface Added to Database','Topology',NULL,'bharath-2679.csez.zohocorpin.com','IF-bharath-2679.csez.zohocorpin.com',6,1478254076509,'IF-bharath-2679.csez.zohocorpin.com',NULL,NULL),(11,'Event','Node Added to Database','Topology',NULL,'bharath-2679.csez.zohocorpin.com','bharath-2679.csez.zohocorpin.com',6,1478254076542,'bharath-2679.csez.zohocorpin.com',NULL,NULL),(12,'Event','Interface Added to Database','Topology',NULL,'iphone6usest185.csez.zohocorpin.com','IF-iphone6usest185.csez.zohocorpin.com',6,1478254087716,'IF-iphone6usest185.csez.zohocorpin.com',NULL,NULL),(13,'Event','Node Added to Database','Topology',NULL,'iphone6usest185.csez.zohocorpin.com','iphone6usest185.csez.zohocorpin.com',6,1478254087742,'iphone6usest185.csez.zohocorpin.com',NULL,NULL),(14,'Event','Interface Added to Database','Topology',NULL,'iphone6plus565.csez.zohocorpin.com','IF-iphone6plus565.csez.zohocorpin.com',6,1478254087944,'IF-iphone6plus565.csez.zohocorpin.com',NULL,NULL),(15,'Event','Node Added to Database','Topology',NULL,'iphone6plus565.csez.zohocorpin.com','iphone6plus565.csez.zohocorpin.com',6,1478254087971,'iphone6plus565.csez.zohocorpin.com',NULL,NULL),(16,'Event','Interface Added to Database','Topology',NULL,'rakesh-3889.csez.zohocorpin.com','IF-rakesh-3889.csez.zohocorpin.com',6,1478254110321,'IF-rakesh-3889.csez.zohocorpin.com',NULL,NULL),(17,'Event','Node Added to Database','Topology',NULL,'rakesh-3889.csez.zohocorpin.com','rakesh-3889.csez.zohocorpin.com',6,1478254110352,'rakesh-3889.csez.zohocorpin.com',NULL,NULL),(18,'Event','Interface Added to Database','Topology',NULL,'subha-4506.csez.zohocorpin.com','IF-subha-4506.csez.zohocorpin.com',6,1478254112904,'IF-subha-4506.csez.zohocorpin.com',NULL,NULL),(19,'Event','Node Added to Database','Topology',NULL,'subha-4506.csez.zohocorpin.com','subha-4506.csez.zohocorpin.com',6,1478254112929,'subha-4506.csez.zohocorpin.com',NULL,NULL),(20,'Event','Interface Added to Database','Topology',NULL,'amritha-3867.csez.zohocorpin.com','IF-amritha-3867.csez.zohocorpin.com',6,1478254113171,'IF-amritha-3867.csez.zohocorpin.com',NULL,NULL),(21,'Event','Node Added to Database','Topology',NULL,'amritha-3867.csez.zohocorpin.com','amritha-3867.csez.zohocorpin.com',6,1478254113196,'amritha-3867.csez.zohocorpin.com',NULL,NULL),(22,'Event','Interface Added to Database','Topology',NULL,'sandeep-2.csez.zohocorpin.com','IF-sandeep-2.csez.zohocorpin.com',6,1478254113421,'IF-sandeep-2.csez.zohocorpin.com',NULL,NULL),(23,'Event','Node Added to Database','Topology',NULL,'sandeep-2.csez.zohocorpin.com','sandeep-2.csez.zohocorpin.com',6,1478254113446,'sandeep-2.csez.zohocorpin.com',NULL,NULL),(24,'Event','Interface Added to Database','Topology',NULL,'sumanth-3304.csez.zohocorpin.com','IF-sumanth-3304.csez.zohocorpin.com',6,1478254113643,'IF-sumanth-3304.csez.zohocorpin.com',NULL,NULL),(25,'Event','Node Added to Database','Topology',NULL,'sumanth-3304.csez.zohocorpin.com','sumanth-3304.csez.zohocorpin.com',6,1478254113686,'sumanth-3304.csez.zohocorpin.com',NULL,NULL),(26,'Event','Interface Added to Database','Topology',NULL,'ksenthil-0949.csez.zohocorpin.com','IF-ksenthil-0949.csez.zohocorpin.com',6,1478254124868,'IF-ksenthil-0949.csez.zohocorpin.com',NULL,NULL),(27,'Event','Node Added to Database','Topology',NULL,'ksenthil-0949.csez.zohocorpin.com','ksenthil-0949.csez.zohocorpin.com',6,1478254124895,'ksenthil-0949.csez.zohocorpin.com',NULL,NULL),(28,'Event','Threshold exceeded :  Value: 133.0, Data: JVMPD_BE_16500_MonitorThread : BE_172.24.14.34 : .1.3.6.1.4.1.42.2.145.3.163.1.1.3.1.0, Threshold Type: max Critical Threshold: 125.0 Critical Rearm Value: 105.0','NMSManagement',NULL,'BE_172.24.14.34','BE_172.24.14.34:.1.3.6.1.4.1.42.2.145.3.163.1.1.3.1.0',1,1478254205510,'BE_172.24.14.34',NULL,NULL),(29,'Event','Threshold exceeded :  Value: 100.0, Data: JVMPD_BE_16500_MonitorMemory : BE_172.24.14.34 : .1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0, Threshold Type: max Critical Threshold: 80.0 Critical Rearm Value: 70.0','NMSManagement',NULL,'BE_172.24.14.34','BE_172.24.14.34:.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0',1,1478254205511,'BE_172.24.14.34',NULL,NULL),(30,'Event','Interface Added to Database','Topology',NULL,'padma-4271.csez.zohocorpin.com','IF-padma-4271.csez.zohocorpin.com',6,1478254214700,'IF-padma-4271.csez.zohocorpin.com',NULL,NULL),(31,'Event','Node Added to Database','Topology',NULL,'padma-4271.csez.zohocorpin.com','padma-4271.csez.zohocorpin.com',6,1478254214730,'padma-4271.csez.zohocorpin.com',NULL,NULL),(32,'Event','Interface Added to Database','Topology',NULL,'172.24.14.28','IF-172.24.14.28',6,1478254214849,'IF-172.24.14.28',NULL,NULL),(33,'Event','Node Added to Database','Topology',NULL,'172.24.14.28','172.24.14.28',6,1478254214871,'172.24.14.28',NULL,NULL),(34,'Event','Interface Added to Database','Topology',NULL,'abhi-3378.csez.zohocorpin.com','IF-abhi-3378.csez.zohocorpin.com',6,1478254226045,'IF-abhi-3378.csez.zohocorpin.com',NULL,NULL),(35,'Event','Node Added to Database','Topology',NULL,'abhi-3378.csez.zohocorpin.com','abhi-3378.csez.zohocorpin.com',6,1478254226068,'abhi-3378.csez.zohocorpin.com',NULL,NULL),(36,'Event','Interface Added to Database','Topology',NULL,'aravind-0717.csez.zohocorpin.com','IF-aravind-0717.csez.zohocorpin.com',6,1478254227221,'IF-aravind-0717.csez.zohocorpin.com',NULL,NULL),(37,'Event','Node Added to Database','Topology',NULL,'aravind-0717.csez.zohocorpin.com','aravind-0717.csez.zohocorpin.com',6,1478254227244,'aravind-0717.csez.zohocorpin.com',NULL,NULL),(38,'Event','Interface Added to Database','Topology',NULL,'venkat-0773.csez.zohocorpin.com','IF-venkat-0773.csez.zohocorpin.com',6,1478254249462,'IF-venkat-0773.csez.zohocorpin.com',NULL,NULL),(39,'Event','Node Added to Database','Topology',NULL,'venkat-0773.csez.zohocorpin.com','venkat-0773.csez.zohocorpin.com',6,1478254249484,'venkat-0773.csez.zohocorpin.com',NULL,NULL),(40,'Event','Interface Added to Database','Topology',NULL,'mani-0918.csez.zohocorpin.com','IF-mani-0918.csez.zohocorpin.com',6,1478254260651,'IF-mani-0918.csez.zohocorpin.com',NULL,NULL),(41,'Event','Node Added to Database','Topology',NULL,'mani-0918.csez.zohocorpin.com','mani-0918.csez.zohocorpin.com',6,1478254260673,'mani-0918.csez.zohocorpin.com',NULL,NULL),(42,'Event','Interface Added to Database','Topology',NULL,'bala-2606.csez.zohocorpin.com','IF-bala-2606.csez.zohocorpin.com',6,1478254260812,'IF-bala-2606.csez.zohocorpin.com',NULL,NULL),(43,'Event','Node Added to Database','Topology',NULL,'bala-2606.csez.zohocorpin.com','bala-2606.csez.zohocorpin.com',6,1478254260834,'bala-2606.csez.zohocorpin.com',NULL,NULL),(44,'Event','Network Added to Database','Topology',NULL,'192.168.220.0','192.168.220.0',6,1478254261054,'192.168.220.0',NULL,NULL),(45,'Event','Interface Added to Database','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','IF-rejoe-0162.csez.zohocorpin.com',6,1478254261079,'IF-rejoe-0162.csez.zohocorpin.com',NULL,NULL),(46,'Event','Interface Added to Database','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','IF-192.168.220.202',6,1478254261106,'IF-192.168.220.202',NULL,NULL),(47,'Event','Node Added to Database','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','rejoe-0162.csez.zohocorpin.com',6,1478254261165,'rejoe-0162.csez.zohocorpin.com',NULL,NULL),(48,'Event','Interface Added to Database','Topology',NULL,'mukil-4261.csez.zohocorpin.com','IF-mukil-4261.csez.zohocorpin.com',6,1478254261326,'IF-mukil-4261.csez.zohocorpin.com',NULL,NULL),(49,'Event','Node Added to Database','Topology',NULL,'mukil-4261.csez.zohocorpin.com','mukil-4261.csez.zohocorpin.com',6,1478254261348,'mukil-4261.csez.zohocorpin.com',NULL,NULL),(50,'Event','Interface Added to Database','Topology',NULL,'pandi-1824.csez.zohocorpin.com','IF-pandi-1824.csez.zohocorpin.com',6,1478254261518,'IF-pandi-1824.csez.zohocorpin.com',NULL,NULL),(51,'Event','Node Added to Database','Topology',NULL,'pandi-1824.csez.zohocorpin.com','pandi-1824.csez.zohocorpin.com',6,1478254261540,'pandi-1824.csez.zohocorpin.com',NULL,NULL),(52,'Event','Interface Added to Database','Topology',NULL,'sathish-1320.csez.zohocorpin.com','IF-sathish-1320.csez.zohocorpin.com',6,1478254261731,'IF-sathish-1320.csez.zohocorpin.com',NULL,NULL),(53,'Event','Node Added to Database','Topology',NULL,'sathish-1320.csez.zohocorpin.com','sathish-1320.csez.zohocorpin.com',6,1478254261753,'sathish-1320.csez.zohocorpin.com',NULL,NULL),(54,'Event','Interface Added to Database','Topology',NULL,'sai-3130.csez.zohocorpin.com','IF-sai-3130.csez.zohocorpin.com',6,1478254283949,'IF-sai-3130.csez.zohocorpin.com',NULL,NULL),(55,'Event','Node Added to Database','Topology',NULL,'sai-3130.csez.zohocorpin.com','sai-3130.csez.zohocorpin.com',6,1478254283971,'sai-3130.csez.zohocorpin.com',NULL,NULL),(56,'Event','Interface Added to Database','Topology',NULL,'vinodh-2312.csez.zohocorpin.com','IF-vinodh-2312.csez.zohocorpin.com',6,1478254307877,'IF-vinodh-2312.csez.zohocorpin.com',NULL,NULL),(57,'Event','Node Added to Database','Topology',NULL,'vinodh-2312.csez.zohocorpin.com','vinodh-2312.csez.zohocorpin.com',6,1478254307899,'vinodh-2312.csez.zohocorpin.com',NULL,NULL),(58,'Event','Interface Added to Database','Topology',NULL,'yuvaraj-1472.csez.zohocorpin.com','IF-yuvaraj-1472.csez.zohocorpin.com',6,1478254462375,'IF-yuvaraj-1472.csez.zohocorpin.com',NULL,NULL),(59,'Event','Node Added to Database','Topology',NULL,'yuvaraj-1472.csez.zohocorpin.com','yuvaraj-1472.csez.zohocorpin.com',6,1478254462422,'yuvaraj-1472.csez.zohocorpin.com',NULL,NULL),(60,'Event','Interface Added to Database','Topology',NULL,'172.24.14.56','IF-172.24.14.56',6,1478254606208,'IF-172.24.14.56',NULL,NULL),(61,'Event','Node Added to Database','Topology',NULL,'172.24.14.56','172.24.14.56',6,1478254606242,'172.24.14.56',NULL,NULL),(62,'Event','Interface Added to Database','Topology',NULL,'ashish-4086.csez.zohocorpin.com','IF-ashish-4086.csez.zohocorpin.com',6,1478254606356,'IF-ashish-4086.csez.zohocorpin.com',NULL,NULL),(63,'Event','Node Added to Database','Topology',NULL,'ashish-4086.csez.zohocorpin.com','ashish-4086.csez.zohocorpin.com',6,1478254606386,'ashish-4086.csez.zohocorpin.com',NULL,NULL),(64,'Event','Interface Added to Database','Topology',NULL,'naga-3924.csez.zohocorpin.com','IF-naga-3924.csez.zohocorpin.com',6,1478254617605,'IF-naga-3924.csez.zohocorpin.com',NULL,NULL),(65,'Event','Node Added to Database','Topology',NULL,'naga-3924.csez.zohocorpin.com','naga-3924.csez.zohocorpin.com',6,1478254617635,'naga-3924.csez.zohocorpin.com',NULL,NULL),(66,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sandeep-2.csez.zohocorpin.com','IF-sandeep-2.csez.zohocorpin.com',2,1478254648884,'IF-sandeep-2.csez.zohocorpin.com','','sandeep-2.csez.zohocorpin.com'),(67,'Event','At least one node on this net is in failure state.','Topology',NULL,'172.24.14.0','172.24.14.0',2,1478254649103,'172.24.14.0',NULL,NULL),(68,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sandeep-2.csez.zohocorpin.com','sandeep-2.csez.zohocorpin.com',2,1478254649106,'sandeep-2.csez.zohocorpin.com',NULL,'sandeep-2.csez.zohocorpin.com'),(69,'Event','Interface Added to Database','Topology',NULL,'priya-zutk58.csez.zohocorpin.com','IF-priya-zutk58.csez.zohocorpin.com',6,1478254707044,'IF-priya-zutk58.csez.zohocorpin.com',NULL,NULL),(70,'Event','Node Added to Database','Topology',NULL,'priya-zutk58.csez.zohocorpin.com','priya-zutk58.csez.zohocorpin.com',6,1478254707069,'priya-zutk58.csez.zohocorpin.com',NULL,NULL),(71,'Event','Interface Added to Database','Topology',NULL,'manimaran-1378.csez.zohocorpin.com','IF-manimaran-1378.csez.zohocorpin.com',6,1478254729375,'IF-manimaran-1378.csez.zohocorpin.com',NULL,NULL),(72,'Event','Node Added to Database','Topology',NULL,'manimaran-1378.csez.zohocorpin.com','manimaran-1378.csez.zohocorpin.com',6,1478254729405,'manimaran-1378.csez.zohocorpin.com',NULL,NULL),(73,'Event','Interface Added to Database','Topology',NULL,'shanmugam-2352.csez.zohocorpin.com','IF-shanmugam-2352.csez.zohocorpin.com',6,1478254729519,'IF-shanmugam-2352.csez.zohocorpin.com',NULL,NULL),(74,'Event','Node Added to Database','Topology',NULL,'shanmugam-2352.csez.zohocorpin.com','shanmugam-2352.csez.zohocorpin.com',6,1478254729544,'shanmugam-2352.csez.zohocorpin.com',NULL,NULL),(75,'Event','Interface Added to Database','Topology',NULL,'gokul-3303.csez.zohocorpin.com','IF-gokul-3303.csez.zohocorpin.com',6,1478254751733,'IF-gokul-3303.csez.zohocorpin.com',NULL,NULL),(76,'Event','Node Added to Database','Topology',NULL,'gokul-3303.csez.zohocorpin.com','gokul-3303.csez.zohocorpin.com',6,1478254751758,'gokul-3303.csez.zohocorpin.com',NULL,NULL),(77,'Event','Interface Added to Database','Topology',NULL,'arunsubhash-0371.csez.zohocorpin.com','IF-arunsubhash-0371.csez.zohocorpin.com',6,1478254751998,'IF-arunsubhash-0371.csez.zohocorpin.com',NULL,NULL),(78,'Event','Node Added to Database','Topology',NULL,'arunsubhash-0371.csez.zohocorpin.com','arunsubhash-0371.csez.zohocorpin.com',6,1478254752025,'arunsubhash-0371.csez.zohocorpin.com',NULL,NULL),(79,'Event','Interface Added to Database','Topology',NULL,'mani-0702.csez.zohocorpin.com','IF-mani-0702.csez.zohocorpin.com',6,1478254752147,'IF-mani-0702.csez.zohocorpin.com',NULL,NULL),(80,'Event','Node Added to Database','Topology',NULL,'mani-0702.csez.zohocorpin.com','mani-0702.csez.zohocorpin.com',6,1478254752180,'mani-0702.csez.zohocorpin.com',NULL,NULL),(81,'Event','Interface Added to Database','Topology',NULL,'rajesh-2755.csez.zohocorpin.com','IF-rajesh-2755.csez.zohocorpin.com',6,1478254752380,'IF-rajesh-2755.csez.zohocorpin.com',NULL,NULL),(82,'Event','Node Added to Database','Topology',NULL,'rajesh-2755.csez.zohocorpin.com','rajesh-2755.csez.zohocorpin.com',6,1478254752405,'rajesh-2755.csez.zohocorpin.com',NULL,NULL),(83,'Event','Interface Added to Database','Topology',NULL,'abdul-zt24.csez.zohocorpin.com','IF-abdul-zt24.csez.zohocorpin.com',6,1478254752604,'IF-abdul-zt24.csez.zohocorpin.com',NULL,NULL),(84,'Event','Node Added to Database','Topology',NULL,'abdul-zt24.csez.zohocorpin.com','abdul-zt24.csez.zohocorpin.com',6,1478254752630,'abdul-zt24.csez.zohocorpin.com',NULL,NULL),(85,'Event','Interface Added to Database','Topology',NULL,'mani-2209.csez.zohocorpin.com','IF-mani-2209.csez.zohocorpin.com',6,1478254830000,'IF-mani-2209.csez.zohocorpin.com',NULL,NULL),(86,'Event','Node Added to Database','Topology',NULL,'mani-2209.csez.zohocorpin.com','mani-2209.csez.zohocorpin.com',6,1478254830029,'mani-2209.csez.zohocorpin.com',NULL,NULL),(87,'Event','Interface Added to Database','Topology',NULL,'macbook-pro.csez.zohocorpin.com','IF-macbook-pro.csez.zohocorpin.com',6,1478254842303,'IF-macbook-pro.csez.zohocorpin.com',NULL,NULL),(88,'Event','Node Added to Database','Topology',NULL,'macbook-pro.csez.zohocorpin.com','macbook-pro.csez.zohocorpin.com',6,1478254842329,'macbook-pro.csez.zohocorpin.com',NULL,NULL),(89,'Event','Interface Added to Database','Topology',NULL,'172.24.14.84','IF-172.24.14.84',6,1478254842446,'IF-172.24.14.84',NULL,NULL),(90,'Event','Node Added to Database','Topology',NULL,'172.24.14.84','172.24.14.84',6,1478254842470,'172.24.14.84',NULL,NULL),(91,'Event','Interface Added to Database','Topology',NULL,'jsangeetha-0849.csez.zohocorpin.com','IF-jsangeetha-0849.csez.zohocorpin.com',6,1478254909973,'IF-jsangeetha-0849.csez.zohocorpin.com',NULL,NULL),(92,'Event','Node Added to Database','Topology',NULL,'jsangeetha-0849.csez.zohocorpin.com','jsangeetha-0849.csez.zohocorpin.com',6,1478254909997,'jsangeetha-0849.csez.zohocorpin.com',NULL,NULL),(93,'Event','Interface Added to Database','Topology',NULL,'172.24.14.92','IF-172.24.14.92',6,1478254977658,'IF-172.24.14.92',NULL,NULL),(94,'Event','Node Added to Database','Topology',NULL,'172.24.14.92','172.24.14.92',6,1478254977682,'172.24.14.92',NULL,NULL),(95,'Event','Interface Added to Database','Topology',NULL,'muni-0051.csez.zohocorpin.com','IF-muni-0051.csez.zohocorpin.com',6,1478254977962,'IF-muni-0051.csez.zohocorpin.com',NULL,NULL),(96,'Event','Node Added to Database','Topology',NULL,'muni-0051.csez.zohocorpin.com','muni-0051.csez.zohocorpin.com',6,1478254977984,'muni-0051.csez.zohocorpin.com',NULL,NULL),(97,'Event','Interface Added to Database','Topology',NULL,'android-d9e08dc4ead46367.csez.zohocorpin.com','IF-android-d9e08dc4ead46367.csez.zohocorpin.com',6,1478254979325,'IF-android-d9e08dc4ead46367.csez.zohocorpin.com',NULL,NULL),(98,'Event','Node Added to Database','Topology',NULL,'android-d9e08dc4ead46367.csez.zohocorpin.com','android-d9e08dc4ead46367.csez.zohocorpin.com',6,1478254979352,'android-d9e08dc4ead46367.csez.zohocorpin.com',NULL,NULL),(99,'Event','Interface Added to Database','Topology',NULL,'mohasin-2851.csez.zohocorpin.com','IF-mohasin-2851.csez.zohocorpin.com',6,1478254979627,'IF-mohasin-2851.csez.zohocorpin.com',NULL,NULL),(100,'Event','Node Added to Database','Topology',NULL,'mohasin-2851.csez.zohocorpin.com','mohasin-2851.csez.zohocorpin.com',6,1478254979652,'mohasin-2851.csez.zohocorpin.com',NULL,NULL),(101,'Event','Interface failure.  Status poll failed.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','IF-rejoe-0162.csez.zohocorpin.com',2,1478254991174,'IF-rejoe-0162.csez.zohocorpin.com','','rejoe-0162.csez.zohocorpin.com'),(102,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','rejoe-0162.csez.zohocorpin.com',2,1478254991431,'rejoe-0162.csez.zohocorpin.com',NULL,'rejoe-0162.csez.zohocorpin.com'),(103,'Event','Interface Added to Database','Topology',NULL,'raj-3842.csez.zohocorpin.com','IF-raj-3842.csez.zohocorpin.com',6,1478255003110,'IF-raj-3842.csez.zohocorpin.com',NULL,NULL),(104,'Event','Node Added to Database','Topology',NULL,'raj-3842.csez.zohocorpin.com','raj-3842.csez.zohocorpin.com',6,1478255003134,'raj-3842.csez.zohocorpin.com',NULL,NULL),(105,'Event','Interface Added to Database','Topology',NULL,'172.24.14.105','IF-172.24.14.105',6,1478255025360,'IF-172.24.14.105',NULL,NULL),(106,'Event','Node Added to Database','Topology',NULL,'172.24.14.105','172.24.14.105',6,1478255025382,'172.24.14.105',NULL,NULL),(107,'Event','Interface Added to Database','Topology',NULL,'172.24.14.112','IF-172.24.14.112',6,1478255025927,'IF-172.24.14.112',NULL,NULL),(108,'Event','Node Added to Database','Topology',NULL,'172.24.14.112','172.24.14.112',6,1478255025949,'172.24.14.112',NULL,NULL),(109,'Event','Interface Added to Database','Topology',NULL,'siva-2171.csez.zohocorpin.com','IF-siva-2171.csez.zohocorpin.com',6,1478255037231,'IF-siva-2171.csez.zohocorpin.com',NULL,NULL),(110,'Event','Node Added to Database','Topology',NULL,'siva-2171.csez.zohocorpin.com','siva-2171.csez.zohocorpin.com',6,1478255037252,'siva-2171.csez.zohocorpin.com',NULL,NULL),(111,'Event','Interface Added to Database','Topology',NULL,'boobala-0048.csez.zohocorpin.com','IF-boobala-0048.csez.zohocorpin.com',6,1478255037762,'IF-boobala-0048.csez.zohocorpin.com',NULL,NULL),(112,'Event','Node Added to Database','Topology',NULL,'boobala-0048.csez.zohocorpin.com','boobala-0048.csez.zohocorpin.com',6,1478255037784,'boobala-0048.csez.zohocorpin.com',NULL,NULL),(113,'Event','Interface Added to Database','Topology',NULL,'jlatha-0972.csez.zohocorpin.com','IF-jlatha-0972.csez.zohocorpin.com',6,1478255038030,'IF-jlatha-0972.csez.zohocorpin.com',NULL,NULL),(114,'Event','Node Added to Database','Topology',NULL,'jlatha-0972.csez.zohocorpin.com','jlatha-0972.csez.zohocorpin.com',6,1478255038054,'jlatha-0972.csez.zohocorpin.com',NULL,NULL),(115,'Event','Interface Added to Database','Topology',NULL,'amarnath-0642.csez.zohocorpin.com','IF-amarnath-0642.csez.zohocorpin.com',6,1478255038190,'IF-amarnath-0642.csez.zohocorpin.com',NULL,NULL),(116,'Event','Node Added to Database','Topology',NULL,'amarnath-0642.csez.zohocorpin.com','amarnath-0642.csez.zohocorpin.com',6,1478255038217,'amarnath-0642.csez.zohocorpin.com',NULL,NULL),(117,'Event','Interface Added to Database','Topology',NULL,'partha-3525.csez.zohocorpin.com','IF-partha-3525.csez.zohocorpin.com',6,1478255038408,'IF-partha-3525.csez.zohocorpin.com',NULL,NULL),(118,'Event','Node Added to Database','Topology',NULL,'partha-3525.csez.zohocorpin.com','partha-3525.csez.zohocorpin.com',6,1478255038430,'partha-3525.csez.zohocorpin.com',NULL,NULL),(119,'Event','Interface Added to Database','Topology',NULL,'chithu-0706.csez.zohocorpin.com','IF-chithu-0706.csez.zohocorpin.com',6,1478255038648,'IF-chithu-0706.csez.zohocorpin.com',NULL,NULL),(120,'Event','Node Added to Database','Topology',NULL,'chithu-0706.csez.zohocorpin.com','chithu-0706.csez.zohocorpin.com',6,1478255038668,'chithu-0706.csez.zohocorpin.com',NULL,NULL),(121,'Event','Interface Added to Database','Topology',NULL,'aswath-pt773.csez.zohocorpin.com','IF-aswath-pt773.csez.zohocorpin.com',6,1478255049894,'IF-aswath-pt773.csez.zohocorpin.com',NULL,NULL),(122,'Event','Node Added to Database','Topology',NULL,'aswath-pt773.csez.zohocorpin.com','aswath-pt773.csez.zohocorpin.com',6,1478255049920,'aswath-pt773.csez.zohocorpin.com',NULL,NULL),(123,'Event','Interface Added to Database','Topology',NULL,'magesh-1870.csez.zohocorpin.com','IF-magesh-1870.csez.zohocorpin.com',6,1478255072064,'IF-magesh-1870.csez.zohocorpin.com',NULL,NULL),(124,'Event','Node Added to Database','Topology',NULL,'magesh-1870.csez.zohocorpin.com','magesh-1870.csez.zohocorpin.com',6,1478255072085,'magesh-1870.csez.zohocorpin.com',NULL,NULL),(125,'Event','Interface Added to Database','Topology',NULL,'sandhya-3439.csez.zohocorpin.com','IF-sandhya-3439.csez.zohocorpin.com',6,1478255127513,'IF-sandhya-3439.csez.zohocorpin.com',NULL,NULL),(126,'Event','Node Added to Database','Topology',NULL,'sandhya-3439.csez.zohocorpin.com','sandhya-3439.csez.zohocorpin.com',6,1478255127537,'sandhya-3439.csez.zohocorpin.com',NULL,NULL),(127,'Event','Interface Added to Database','Topology',NULL,'kavitha-1061.csez.zohocorpin.com','IF-kavitha-1061.csez.zohocorpin.com',6,1478255138742,'IF-kavitha-1061.csez.zohocorpin.com',NULL,NULL),(128,'Event','Node Added to Database','Topology',NULL,'kavitha-1061.csez.zohocorpin.com','kavitha-1061.csez.zohocorpin.com',6,1478255138763,'kavitha-1061.csez.zohocorpin.com',NULL,NULL),(129,'Event','Interface Added to Database','Topology',NULL,'shyamallsiphone.csez.zohocorpin.com','IF-shyamallsiphone.csez.zohocorpin.com',6,1478255138906,'IF-shyamallsiphone.csez.zohocorpin.com',NULL,NULL),(130,'Event','Node Added to Database','Topology',NULL,'shyamallsiphone.csez.zohocorpin.com','shyamallsiphone.csez.zohocorpin.com',6,1478255138928,'shyamallsiphone.csez.zohocorpin.com',NULL,NULL),(131,'Event','Interface Added to Database','Topology',NULL,'marutha-3402.csez.zohocorpin.com','IF-marutha-3402.csez.zohocorpin.com',6,1478255150186,'IF-marutha-3402.csez.zohocorpin.com',NULL,NULL),(132,'Event','Node Added to Database','Topology',NULL,'marutha-3402.csez.zohocorpin.com','marutha-3402.csez.zohocorpin.com',6,1478255150209,'marutha-3402.csez.zohocorpin.com',NULL,NULL),(133,'Event','Interface Added to Database','Topology',NULL,'vijay-0596.csez.zohocorpin.com','IF-vijay-0596.csez.zohocorpin.com',6,1478255150319,'IF-vijay-0596.csez.zohocorpin.com',NULL,NULL),(134,'Event','Node Added to Database','Topology',NULL,'vijay-0596.csez.zohocorpin.com','vijay-0596.csez.zohocorpin.com',6,1478255150341,'vijay-0596.csez.zohocorpin.com',NULL,NULL),(135,'Event','Interface Added to Database','Topology',NULL,'aravinth-3063.csez.zohocorpin.com','IF-aravinth-3063.csez.zohocorpin.com',6,1478255163564,'IF-aravinth-3063.csez.zohocorpin.com',NULL,NULL),(136,'Event','Node Added to Database','Topology',NULL,'aravinth-3063.csez.zohocorpin.com','aravinth-3063.csez.zohocorpin.com',6,1478255163586,'aravinth-3063.csez.zohocorpin.com',NULL,NULL),(137,'Event','Interface Added to Database','Topology',NULL,'vaisali-4071.csez.zohocorpin.com','IF-vaisali-4071.csez.zohocorpin.com',6,1478255185784,'IF-vaisali-4071.csez.zohocorpin.com',NULL,NULL),(138,'Event','Node Added to Database','Topology',NULL,'vaisali-4071.csez.zohocorpin.com','vaisali-4071.csez.zohocorpin.com',6,1478255185809,'vaisali-4071.csez.zohocorpin.com',NULL,NULL),(139,'Event','Interface Added to Database','Topology',NULL,'purushoth-3454.csez.zohocorpin.com','IF-purushoth-3454.csez.zohocorpin.com',6,1478255208004,'IF-purushoth-3454.csez.zohocorpin.com',NULL,NULL),(140,'Event','Node Added to Database','Topology',NULL,'purushoth-3454.csez.zohocorpin.com','purushoth-3454.csez.zohocorpin.com',6,1478255208026,'purushoth-3454.csez.zohocorpin.com',NULL,NULL),(141,'Event','Interface Added to Database','Topology',NULL,'velan-es0007.csez.zohocorpin.com','IF-velan-es0007.csez.zohocorpin.com',6,1478255252449,'IF-velan-es0007.csez.zohocorpin.com',NULL,NULL),(142,'Event','Node Added to Database','Topology',NULL,'velan-es0007.csez.zohocorpin.com','velan-es0007.csez.zohocorpin.com',6,1478255252470,'velan-es0007.csez.zohocorpin.com',NULL,NULL),(143,'Event','Interface Added to Database','Topology',NULL,'pavithra-3526.csez.zohocorpin.com','IF-pavithra-3526.csez.zohocorpin.com',6,1478255274679,'IF-pavithra-3526.csez.zohocorpin.com',NULL,NULL),(144,'Event','Node Added to Database','Topology',NULL,'pavithra-3526.csez.zohocorpin.com','pavithra-3526.csez.zohocorpin.com',6,1478255274701,'pavithra-3526.csez.zohocorpin.com',NULL,NULL),(145,'Event','Interface Added to Database','Topology',NULL,'jerome-3929.csez.zohocorpin.com','IF-jerome-3929.csez.zohocorpin.com',6,1478255285872,'IF-jerome-3929.csez.zohocorpin.com',NULL,NULL),(146,'Event','Node Added to Database','Topology',NULL,'jerome-3929.csez.zohocorpin.com','jerome-3929.csez.zohocorpin.com',6,1478255285895,'jerome-3929.csez.zohocorpin.com',NULL,NULL),(147,'Event','Interface Added to Database','Topology',NULL,'172.24.14.149','IF-172.24.14.149',6,1478255286030,'IF-172.24.14.149',NULL,NULL),(148,'Event','Node Added to Database','Topology',NULL,'172.24.14.149','172.24.14.149',6,1478255286051,'172.24.14.149',NULL,NULL),(149,'Event','Interface Added to Database','Topology',NULL,'raji-0675.csez.zohocorpin.com','IF-raji-0675.csez.zohocorpin.com',6,1478255286158,'IF-raji-0675.csez.zohocorpin.com',NULL,NULL),(150,'Event','Node Added to Database','Topology',NULL,'raji-0675.csez.zohocorpin.com','raji-0675.csez.zohocorpin.com',6,1478255286179,'raji-0675.csez.zohocorpin.com',NULL,NULL),(151,'Event','Interface clear.  ','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','IF-rejoe-0162.csez.zohocorpin.com',5,1478255291222,'IF-rejoe-0162.csez.zohocorpin.com','','rejoe-0162.csez.zohocorpin.com'),(152,'Event','Node clear.  No failures on this node.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','rejoe-0162.csez.zohocorpin.com',5,1478255291505,'rejoe-0162.csez.zohocorpin.com',NULL,'rejoe-0162.csez.zohocorpin.com'),(153,'Event','Interface Added to Database','Topology',NULL,'suresh-0665.csez.zohocorpin.com','IF-suresh-0665.csez.zohocorpin.com',6,1478255341941,'IF-suresh-0665.csez.zohocorpin.com',NULL,NULL),(154,'Event','Node Added to Database','Topology',NULL,'suresh-0665.csez.zohocorpin.com','suresh-0665.csez.zohocorpin.com',6,1478255341969,'suresh-0665.csez.zohocorpin.com',NULL,NULL),(155,'Event','Interface Added to Database','Topology',NULL,'nirmal-2552.csez.zohocorpin.com','IF-nirmal-2552.csez.zohocorpin.com',6,1478255375939,'IF-nirmal-2552.csez.zohocorpin.com',NULL,NULL),(156,'Event','Node Added to Database','Topology',NULL,'nirmal-2552.csez.zohocorpin.com','nirmal-2552.csez.zohocorpin.com',6,1478255375966,'nirmal-2552.csez.zohocorpin.com',NULL,NULL),(157,'Event','Interface Added to Database','Topology',NULL,'172.24.14.171','IF-172.24.14.171',6,1478255376128,'IF-172.24.14.171',NULL,NULL),(158,'Event','Node Added to Database','Topology',NULL,'172.24.14.171','172.24.14.171',6,1478255376154,'172.24.14.171',NULL,NULL),(159,'Event','Interface Added to Database','Topology',NULL,'gramkumar-0817.csez.zohocorpin.com','IF-gramkumar-0817.csez.zohocorpin.com',6,1478255376320,'IF-gramkumar-0817.csez.zohocorpin.com',NULL,NULL),(160,'Event','Node Added to Database','Topology',NULL,'gramkumar-0817.csez.zohocorpin.com','gramkumar-0817.csez.zohocorpin.com',6,1478255376346,'gramkumar-0817.csez.zohocorpin.com',NULL,NULL),(161,'Event','Interface Added to Database','Topology',NULL,'sundari-1712.csez.zohocorpin.com','IF-sundari-1712.csez.zohocorpin.com',6,1478255398570,'IF-sundari-1712.csez.zohocorpin.com',NULL,NULL),(162,'Event','Node Added to Database','Topology',NULL,'sundari-1712.csez.zohocorpin.com','sundari-1712.csez.zohocorpin.com',6,1478255398592,'sundari-1712.csez.zohocorpin.com',NULL,NULL),(163,'Event','Interface Added to Database','Topology',NULL,'radhas-iphone-6.csez.zohocorpin.com','IF-radhas-iphone-6.csez.zohocorpin.com',6,1478255421650,'IF-radhas-iphone-6.csez.zohocorpin.com',NULL,NULL),(164,'Event','Node Added to Database','Topology',NULL,'radhas-iphone-6.csez.zohocorpin.com','radhas-iphone-6.csez.zohocorpin.com',6,1478255421677,'radhas-iphone-6.csez.zohocorpin.com',NULL,NULL),(165,'Event','Interface Added to Database','Topology',NULL,'172.24.14.178','IF-172.24.14.178',6,1478255421815,'IF-172.24.14.178',NULL,NULL),(166,'Event','Node Added to Database','Topology',NULL,'172.24.14.178','172.24.14.178',6,1478255421844,'172.24.14.178',NULL,NULL),(167,'Event','Interface Added to Database','Topology',NULL,'bhargavi-2458.csez.zohocorpin.com','IF-bhargavi-2458.csez.zohocorpin.com',6,1478255422050,'IF-bhargavi-2458.csez.zohocorpin.com',NULL,NULL),(168,'Event','Node Added to Database','Topology',NULL,'bhargavi-2458.csez.zohocorpin.com','bhargavi-2458.csez.zohocorpin.com',6,1478255422070,'bhargavi-2458.csez.zohocorpin.com',NULL,NULL),(169,'Event','Interface Added to Database','Topology',NULL,'mali-0473.csez.zohocorpin.com','IF-mali-0473.csez.zohocorpin.com',6,1478255444346,'IF-mali-0473.csez.zohocorpin.com',NULL,NULL),(170,'Event','Node Added to Database','Topology',NULL,'mali-0473.csez.zohocorpin.com','mali-0473.csez.zohocorpin.com',6,1478255444366,'mali-0473.csez.zohocorpin.com',NULL,NULL),(171,'Event','Interface Added to Database','Topology',NULL,'srivatsav-3642.csez.zohocorpin.com','IF-srivatsav-3642.csez.zohocorpin.com',6,1478255455535,'IF-srivatsav-3642.csez.zohocorpin.com',NULL,NULL),(172,'Event','Node Added to Database','Topology',NULL,'srivatsav-3642.csez.zohocorpin.com','srivatsav-3642.csez.zohocorpin.com',6,1478255455557,'srivatsav-3642.csez.zohocorpin.com',NULL,NULL),(173,'Event','Interface Added to Database','Topology',NULL,'prameena-1188.csez.zohocorpin.com','IF-prameena-1188.csez.zohocorpin.com',6,1478255468735,'IF-prameena-1188.csez.zohocorpin.com',NULL,NULL),(174,'Event','Node Added to Database','Topology',NULL,'prameena-1188.csez.zohocorpin.com','prameena-1188.csez.zohocorpin.com',6,1478255468765,'prameena-1188.csez.zohocorpin.com',NULL,NULL),(175,'Event','Interface Added to Database','Topology',NULL,'android-6da4f6e8432f2ea.csez.zohocorpin.com','IF-android-6da4f6e8432f2ea.csez.zohocorpin.com',6,1478255480022,'IF-android-6da4f6e8432f2ea.csez.zohocorpin.com',NULL,NULL),(176,'Event','Node Added to Database','Topology',NULL,'android-6da4f6e8432f2ea.csez.zohocorpin.com','android-6da4f6e8432f2ea.csez.zohocorpin.com',6,1478255480042,'android-6da4f6e8432f2ea.csez.zohocorpin.com',NULL,NULL),(177,'Event','Interface Added to Database','Topology',NULL,'172.24.14.191','IF-172.24.14.191',6,1478255491223,'IF-172.24.14.191',NULL,NULL),(178,'Event','Node Added to Database','Topology',NULL,'172.24.14.191','172.24.14.191',6,1478255491253,'172.24.14.191',NULL,NULL),(179,'Event','Interface Added to Database','Topology',NULL,'iphone-6-arivu.csez.zohocorpin.com','IF-iphone-6-arivu.csez.zohocorpin.com',6,1478255524560,'IF-iphone-6-arivu.csez.zohocorpin.com',NULL,NULL),(180,'Event','Node Added to Database','Topology',NULL,'iphone-6-arivu.csez.zohocorpin.com','iphone-6-arivu.csez.zohocorpin.com',6,1478255524582,'iphone-6-arivu.csez.zohocorpin.com',NULL,NULL),(181,'Event','Interface Added to Database','Topology',NULL,'loga-zu396.csez.zohocorpin.com','IF-loga-zu396.csez.zohocorpin.com',6,1478255537007,'IF-loga-zu396.csez.zohocorpin.com',NULL,NULL),(182,'Event','Node Added to Database','Topology',NULL,'loga-zu396.csez.zohocorpin.com','loga-zu396.csez.zohocorpin.com',6,1478255537038,'loga-zu396.csez.zohocorpin.com',NULL,NULL),(183,'Event','Interface Added to Database','Topology',NULL,'abdul-0436.csez.zohocorpin.com','IF-abdul-0436.csez.zohocorpin.com',6,1478255537148,'IF-abdul-0436.csez.zohocorpin.com',NULL,NULL),(184,'Event','Node Added to Database','Topology',NULL,'abdul-0436.csez.zohocorpin.com','abdul-0436.csez.zohocorpin.com',6,1478255537171,'abdul-0436.csez.zohocorpin.com',NULL,NULL),(185,'Event','Interface Added to Database','Topology',NULL,'sathish-2784.csez.zohocorpin.com','IF-sathish-2784.csez.zohocorpin.com',6,1478255537277,'IF-sathish-2784.csez.zohocorpin.com',NULL,NULL),(186,'Event','Node Added to Database','Topology',NULL,'sathish-2784.csez.zohocorpin.com','sathish-2784.csez.zohocorpin.com',6,1478255537320,'sathish-2784.csez.zohocorpin.com',NULL,NULL),(187,'Event','Interface Added to Database','Topology',NULL,'harini-zu360.csez.zohocorpin.com','IF-harini-zu360.csez.zohocorpin.com',6,1478255537422,'IF-harini-zu360.csez.zohocorpin.com',NULL,NULL),(188,'Event','Node Added to Database','Topology',NULL,'harini-zu360.csez.zohocorpin.com','harini-zu360.csez.zohocorpin.com',6,1478255537453,'harini-zu360.csez.zohocorpin.com',NULL,NULL),(189,'Event','Interface Added to Database','Topology',NULL,'siddharth-4445.csez.zohocorpin.com','IF-siddharth-4445.csez.zohocorpin.com',6,1478255537983,'IF-siddharth-4445.csez.zohocorpin.com',NULL,NULL),(190,'Event','Node Added to Database','Topology',NULL,'siddharth-4445.csez.zohocorpin.com','siddharth-4445.csez.zohocorpin.com',6,1478255538004,'siddharth-4445.csez.zohocorpin.com',NULL,NULL),(191,'Event','Interface Added to Database','Topology',NULL,'janaki-3684.csez.zohocorpin.com','IF-janaki-3684.csez.zohocorpin.com',6,1478255561243,'IF-janaki-3684.csez.zohocorpin.com',NULL,NULL),(192,'Event','Node Added to Database','Topology',NULL,'janaki-3684.csez.zohocorpin.com','janaki-3684.csez.zohocorpin.com',6,1478255561274,'janaki-3684.csez.zohocorpin.com',NULL,NULL),(193,'Event','Interface Added to Database','Topology',NULL,'damodhar-1003.csez.zohocorpin.com','IF-damodhar-1003.csez.zohocorpin.com',6,1478255583662,'IF-damodhar-1003.csez.zohocorpin.com',NULL,NULL),(194,'Event','Node Added to Database','Topology',NULL,'damodhar-1003.csez.zohocorpin.com','damodhar-1003.csez.zohocorpin.com',6,1478255583684,'damodhar-1003.csez.zohocorpin.com',NULL,NULL),(195,'Event','Interface Added to Database','Topology',NULL,'suresh-1545.csez.zohocorpin.com','IF-suresh-1545.csez.zohocorpin.com',6,1478255639061,'IF-suresh-1545.csez.zohocorpin.com',NULL,NULL),(196,'Event','Node Added to Database','Topology',NULL,'suresh-1545.csez.zohocorpin.com','suresh-1545.csez.zohocorpin.com',6,1478255639087,'suresh-1545.csez.zohocorpin.com',NULL,NULL),(197,'Event','Interface Added to Database','Topology',NULL,'santhanapreethi.csez.zohocorpin.com','IF-santhanapreethi.csez.zohocorpin.com',6,1478255727662,'IF-santhanapreethi.csez.zohocorpin.com',NULL,NULL),(198,'Event','Node Added to Database','Topology',NULL,'santhanapreethi.csez.zohocorpin.com','santhanapreethi.csez.zohocorpin.com',6,1478255727683,'santhanapreethi.csez.zohocorpin.com',NULL,NULL),(199,'Event','Interface Added to Database','Topology',NULL,'ajay-1385.csez.zohocorpin.com','IF-ajay-1385.csez.zohocorpin.com',6,1478255740459,'IF-ajay-1385.csez.zohocorpin.com',NULL,NULL),(200,'Event','Node Added to Database','Topology',NULL,'ajay-1385.csez.zohocorpin.com','ajay-1385.csez.zohocorpin.com',6,1478255740481,'ajay-1385.csez.zohocorpin.com',NULL,NULL),(201,'Event','Interface Added to Database','Topology',NULL,'drevathy-0847.csez.zohocorpin.com','IF-drevathy-0847.csez.zohocorpin.com',6,1478255751811,'IF-drevathy-0847.csez.zohocorpin.com',NULL,NULL),(202,'Event','Node Added to Database','Topology',NULL,'drevathy-0847.csez.zohocorpin.com','drevathy-0847.csez.zohocorpin.com',6,1478255751836,'drevathy-0847.csez.zohocorpin.com',NULL,NULL),(203,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mani-4546.csez.zohocorpin.com','IF-mani-4546.csez.zohocorpin.com',2,1478256164690,'IF-mani-4546.csez.zohocorpin.com','','mani-4546.csez.zohocorpin.com'),(204,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mani-4546.csez.zohocorpin.com','mani-4546.csez.zohocorpin.com',2,1478256164960,'mani-4546.csez.zohocorpin.com',NULL,'mani-4546.csez.zohocorpin.com'),(205,'Event','Interface failure.  Status poll failed.','Topology',NULL,'iphone6plus565.csez.zohocorpin.com','IF-iphone6plus565.csez.zohocorpin.com',2,1478256179687,'IF-iphone6plus565.csez.zohocorpin.com','','iphone6plus565.csez.zohocorpin.com'),(206,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'iphone6plus565.csez.zohocorpin.com','iphone6plus565.csez.zohocorpin.com',2,1478256179886,'iphone6plus565.csez.zohocorpin.com',NULL,'iphone6plus565.csez.zohocorpin.com'),(207,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.56','IF-172.24.14.56',2,1478256289710,'IF-172.24.14.56','','172.24.14.56'),(208,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.56','172.24.14.56',2,1478256289937,'172.24.14.56',NULL,'172.24.14.56'),(209,'Event','Interface failure.  Status poll failed.','Topology',NULL,'priya-zutk58.csez.zohocorpin.com','IF-priya-zutk58.csez.zohocorpin.com',2,1478256304697,'IF-priya-zutk58.csez.zohocorpin.com','','priya-zutk58.csez.zohocorpin.com'),(210,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'priya-zutk58.csez.zohocorpin.com','priya-zutk58.csez.zohocorpin.com',2,1478256305009,'priya-zutk58.csez.zohocorpin.com',NULL,'priya-zutk58.csez.zohocorpin.com'),(211,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mani-0702.csez.zohocorpin.com','IF-mani-0702.csez.zohocorpin.com',2,1478256329700,'IF-mani-0702.csez.zohocorpin.com','','mani-0702.csez.zohocorpin.com'),(212,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mani-0702.csez.zohocorpin.com','mani-0702.csez.zohocorpin.com',2,1478256329804,'mani-0702.csez.zohocorpin.com',NULL,'mani-0702.csez.zohocorpin.com'),(213,'Event','Interface Added to Database','Topology',NULL,'172.24.14.67','IF-172.24.14.67',6,1478256367506,'IF-172.24.14.67',NULL,NULL),(214,'Event',' Object Added to Database','Topology',NULL,'172.24.14.67xx0','172.24.14.67xx0',6,1478256367572,'172.24.14.67xx0',NULL,NULL),(215,'Event','Node Added to Database','Topology',NULL,'172.24.14.67','172.24.14.67',6,1478256367576,'172.24.14.67',NULL,NULL),(216,'Event','Interface failure.  Status poll failed.','Topology',NULL,'muni-0051.csez.zohocorpin.com','IF-muni-0051.csez.zohocorpin.com',2,1478256369699,'IF-muni-0051.csez.zohocorpin.com','','muni-0051.csez.zohocorpin.com'),(217,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'muni-0051.csez.zohocorpin.com','muni-0051.csez.zohocorpin.com',2,1478256369934,'muni-0051.csez.zohocorpin.com',NULL,'muni-0051.csez.zohocorpin.com'),(218,'Event','Interface failure.  Status poll failed.','Topology',NULL,'android-d9e08dc4ead46367.csez.zohocorpin.com','IF-android-d9e08dc4ead46367.csez.zohocorpin.com',2,1478256374695,'IF-android-d9e08dc4ead46367.csez.zohocorpin.com','','android-d9e08dc4ead46367.csez.zohocorpin.com'),(219,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'android-d9e08dc4ead46367.csez.zohocorpin.com','android-d9e08dc4ead46367.csez.zohocorpin.com',2,1478256374877,'android-d9e08dc4ead46367.csez.zohocorpin.com',NULL,'android-d9e08dc4ead46367.csez.zohocorpin.com'),(220,'Event','Interface Added to Database','Topology',NULL,'admp-test1.csez.zohocorpin.com','IF-admp-test1.csez.zohocorpin.com',6,1478256389871,'IF-admp-test1.csez.zohocorpin.com',NULL,NULL),(221,'Event',' Object Added to Database','Topology',NULL,'admp-test1.csez.zohocorpin.comxx1','admp-test1.csez.zohocorpin.comxx1',6,1478256389912,'admp-test1.csez.zohocorpin.comxx1',NULL,NULL),(222,'Event','Node Added to Database','Topology',NULL,'admp-test1.csez.zohocorpin.com','admp-test1.csez.zohocorpin.com',6,1478256389915,'admp-test1.csez.zohocorpin.com',NULL,NULL),(223,'Event','Interface failure.  Status poll failed.','Topology',NULL,'amarnath-0642.csez.zohocorpin.com','IF-amarnath-0642.csez.zohocorpin.com',2,1478256414697,'IF-amarnath-0642.csez.zohocorpin.com','','amarnath-0642.csez.zohocorpin.com'),(224,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'amarnath-0642.csez.zohocorpin.com','amarnath-0642.csez.zohocorpin.com',2,1478256414919,'amarnath-0642.csez.zohocorpin.com',NULL,'amarnath-0642.csez.zohocorpin.com'),(225,'Event','Interface failure.  Status poll failed.','Topology',NULL,'shyamallsiphone.csez.zohocorpin.com','IF-shyamallsiphone.csez.zohocorpin.com',2,1478256449714,'IF-shyamallsiphone.csez.zohocorpin.com','','shyamallsiphone.csez.zohocorpin.com'),(226,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'shyamallsiphone.csez.zohocorpin.com','shyamallsiphone.csez.zohocorpin.com',2,1478256449914,'shyamallsiphone.csez.zohocorpin.com',NULL,'shyamallsiphone.csez.zohocorpin.com'),(227,'Event','Interface Added to Database','Topology',NULL,'meena.csez.zohocorpin.com','IF-meena.csez.zohocorpin.com',6,1478256511621,'IF-meena.csez.zohocorpin.com',NULL,NULL),(228,'Event',' Object Added to Database','Topology',NULL,'meena.csez.zohocorpin.comxx2','meena.csez.zohocorpin.comxx2',6,1478256511661,'meena.csez.zohocorpin.comxx2',NULL,NULL),(229,'Event','Node Added to Database','Topology',NULL,'meena.csez.zohocorpin.com','meena.csez.zohocorpin.com',6,1478256511663,'meena.csez.zohocorpin.com',NULL,NULL),(230,'Event','Interface failure.  Status poll failed.','Topology',NULL,'radhas-iphone-6.csez.zohocorpin.com','IF-radhas-iphone-6.csez.zohocorpin.com',2,1478256529735,'IF-radhas-iphone-6.csez.zohocorpin.com','','radhas-iphone-6.csez.zohocorpin.com'),(231,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'radhas-iphone-6.csez.zohocorpin.com','radhas-iphone-6.csez.zohocorpin.com',2,1478256529972,'radhas-iphone-6.csez.zohocorpin.com',NULL,'radhas-iphone-6.csez.zohocorpin.com'),(232,'Event','Interface Added to Database','Topology',NULL,'preethi2siphone.csez.zohocorpin.com','IF-preethi2siphone.csez.zohocorpin.com',6,1478256569211,'IF-preethi2siphone.csez.zohocorpin.com',NULL,NULL),(233,'Event',' Object Added to Database','Topology',NULL,'preethi2siphone.csez.zohocorpin.comxx3','preethi2siphone.csez.zohocorpin.comxx3',6,1478256569254,'preethi2siphone.csez.zohocorpin.comxx3',NULL,NULL),(234,'Event','Node Added to Database','Topology',NULL,'preethi2siphone.csez.zohocorpin.com','preethi2siphone.csez.zohocorpin.com',6,1478256569256,'preethi2siphone.csez.zohocorpin.com',NULL,NULL),(235,'Event','Interface failure.  Status poll failed.','Topology',NULL,'iphone-6-arivu.csez.zohocorpin.com','IF-iphone-6-arivu.csez.zohocorpin.com',2,1478256569720,'IF-iphone-6-arivu.csez.zohocorpin.com','','iphone-6-arivu.csez.zohocorpin.com'),(236,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'iphone-6-arivu.csez.zohocorpin.com','iphone-6-arivu.csez.zohocorpin.com',2,1478256569959,'iphone-6-arivu.csez.zohocorpin.com',NULL,'iphone-6-arivu.csez.zohocorpin.com'),(237,'Event','Network Availability percentage is between 70% and 90% Value: 87.878, Data: Network Availability : KeyPerformanceIndicator : Network Availability %, Threshold Type: in, Min value: 70.0, Max value: 90.0','KPI',NULL,'KeyPerformanceIndicator','KeyPerformanceIndicator:Network Availability %',2,1478256575621,'KeyPerformanceIndicator',NULL,NULL),(238,'Event','Interface Added to Database','Topology',NULL,'172.24.14.115','IF-172.24.14.115',6,1478256580509,'IF-172.24.14.115',NULL,NULL),(239,'Event',' Object Added to Database','Topology',NULL,'172.24.14.115xx4','172.24.14.115xx4',6,1478256580551,'172.24.14.115xx4',NULL,NULL),(240,'Event','Node Added to Database','Topology',NULL,'172.24.14.115','172.24.14.115',6,1478256580553,'172.24.14.115',NULL,NULL),(241,'Event','Interface failure.  Status poll failed.','Topology',NULL,'siddharth-4445.csez.zohocorpin.com','IF-siddharth-4445.csez.zohocorpin.com',2,1478256594723,'IF-siddharth-4445.csez.zohocorpin.com','','siddharth-4445.csez.zohocorpin.com'),(242,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'siddharth-4445.csez.zohocorpin.com','siddharth-4445.csez.zohocorpin.com',2,1478256594957,'siddharth-4445.csez.zohocorpin.com',NULL,'siddharth-4445.csez.zohocorpin.com'),(243,'Event','Interface Added to Database','Topology',NULL,'hemanth-3818.csez.zohocorpin.com','IF-hemanth-3818.csez.zohocorpin.com',6,1478256779789,'IF-hemanth-3818.csez.zohocorpin.com',NULL,NULL),(244,'Event',' Object Added to Database','Topology',NULL,'hemanth-3818.csez.zohocorpin.comxx5','hemanth-3818.csez.zohocorpin.comxx5',6,1478256779839,'hemanth-3818.csez.zohocorpin.comxx5',NULL,NULL),(245,'Event','Node Added to Database','Topology',NULL,'hemanth-3818.csez.zohocorpin.com','hemanth-3818.csez.zohocorpin.com',6,1478256779842,'hemanth-3818.csez.zohocorpin.com',NULL,NULL),(246,'Event','Interface Added to Database','Topology',NULL,'172.24.14.154','IF-172.24.14.154',6,1478256813169,'IF-172.24.14.154',NULL,NULL),(247,'Event',' Object Added to Database','Topology',NULL,'172.24.14.154xx6','172.24.14.154xx6',6,1478256813229,'172.24.14.154xx6',NULL,NULL),(248,'Event','Node Added to Database','Topology',NULL,'172.24.14.154','172.24.14.154',6,1478256813233,'172.24.14.154',NULL,NULL),(249,'Event','Interface Added to Database','Topology',NULL,'172.24.14.152','IF-172.24.14.152',6,1478256813424,'IF-172.24.14.152',NULL,NULL),(250,'Event',' Object Added to Database','Topology',NULL,'172.24.14.152xx7','172.24.14.152xx7',6,1478256813463,'172.24.14.152xx7',NULL,NULL),(251,'Event','Node Added to Database','Topology',NULL,'172.24.14.152','172.24.14.152',6,1478256813467,'172.24.14.152',NULL,NULL),(252,'Event','Interface Added to Database','Topology',NULL,'arivalagan-2168.csez.zohocorpin.com','IF-arivalagan-2168.csez.zohocorpin.com',6,1478256879847,'IF-arivalagan-2168.csez.zohocorpin.com',NULL,NULL),(253,'Event',' Object Added to Database','Topology',NULL,'arivalagan-2168.csez.zohocorpin.comxx8','arivalagan-2168.csez.zohocorpin.comxx8',6,1478256879896,'arivalagan-2168.csez.zohocorpin.comxx8',NULL,NULL),(254,'Event','Node Added to Database','Topology',NULL,'arivalagan-2168.csez.zohocorpin.com','arivalagan-2168.csez.zohocorpin.com',6,1478256879901,'arivalagan-2168.csez.zohocorpin.com',NULL,NULL),(255,'Event','Interface Added to Database','Topology',NULL,'aravinds6splus.csez.zohocorpin.com','IF-aravinds6splus.csez.zohocorpin.com',6,1478256979560,'IF-aravinds6splus.csez.zohocorpin.com',NULL,NULL),(256,'Event',' Object Added to Database','Topology',NULL,'aravinds6splus.csez.zohocorpin.comxx9','aravinds6splus.csez.zohocorpin.comxx9',6,1478256979606,'aravinds6splus.csez.zohocorpin.comxx9',NULL,NULL),(257,'Event','Node Added to Database','Topology',NULL,'aravinds6splus.csez.zohocorpin.com','aravinds6splus.csez.zohocorpin.com',6,1478256979609,'aravinds6splus.csez.zohocorpin.com',NULL,NULL),(258,'Event','Interface Added to Database','Topology',NULL,'arun-2286.csez.zohocorpin.com','IF-arun-2286.csez.zohocorpin.com',6,1478257158457,'IF-arun-2286.csez.zohocorpin.com',NULL,NULL),(259,'Event',' Object Added to Database','Topology',NULL,'arun-2286.csez.zohocorpin.comxx10','arun-2286.csez.zohocorpin.comxx10',6,1478257158511,'arun-2286.csez.zohocorpin.comxx10',NULL,NULL),(260,'Event','Node Added to Database','Topology',NULL,'arun-2286.csez.zohocorpin.com','arun-2286.csez.zohocorpin.com',6,1478257158516,'arun-2286.csez.zohocorpin.com',NULL,NULL),(261,'Event','Interface Added to Database','Topology',NULL,'kalai-0041.csez.zohocorpin.com','IF-kalai-0041.csez.zohocorpin.com',6,1478257158671,'IF-kalai-0041.csez.zohocorpin.com',NULL,NULL),(262,'Event',' Object Added to Database','Topology',NULL,'kalai-0041.csez.zohocorpin.comxx11','kalai-0041.csez.zohocorpin.comxx11',6,1478257158712,'kalai-0041.csez.zohocorpin.comxx11',NULL,NULL),(263,'Event','Node Added to Database','Topology',NULL,'kalai-0041.csez.zohocorpin.com','kalai-0041.csez.zohocorpin.com',6,1478257158715,'kalai-0041.csez.zohocorpin.com',NULL,NULL),(264,'Event','Interface Added to Database','Topology',NULL,'172.24.14.226','IF-172.24.14.226',6,1478257203290,'IF-172.24.14.226',NULL,NULL),(265,'Event',' Object Added to Database','Topology',NULL,'172.24.14.226xx12','172.24.14.226xx12',6,1478257203327,'172.24.14.226xx12',NULL,NULL),(266,'Event','Node Added to Database','Topology',NULL,'172.24.14.226','172.24.14.226',6,1478257203330,'172.24.14.226',NULL,NULL),(267,'Event','Interface Added to Database','Topology',NULL,'android-8f4bc429763adb11.csez.zohocorpin.com','IF-android-8f4bc429763adb11.csez.zohocorpin.com',6,1478257203435,'IF-android-8f4bc429763adb11.csez.zohocorpin.com',NULL,NULL),(268,'Event',' Object Added to Database','Topology',NULL,'android-8f4bc429763adb11.csez.zohocorpin.comxx13','android-8f4bc429763adb11.csez.zohocorpin.comxx13',6,1478257203471,'android-8f4bc429763adb11.csez.zohocorpin.comxx13',NULL,NULL),(269,'Event','Node Added to Database','Topology',NULL,'android-8f4bc429763adb11.csez.zohocorpin.com','android-8f4bc429763adb11.csez.zohocorpin.com',6,1478257203474,'android-8f4bc429763adb11.csez.zohocorpin.com',NULL,NULL),(270,'Event','Interface Added to Database','Topology',NULL,'172.24.14.238','IF-172.24.14.238',6,1478257226444,'IF-172.24.14.238',NULL,NULL),(271,'Event',' Object Added to Database','Topology',NULL,'172.24.14.238xx14','172.24.14.238xx14',6,1478257226504,'172.24.14.238xx14',NULL,NULL),(272,'Event','Node Added to Database','Topology',NULL,'172.24.14.238','172.24.14.238',6,1478257226508,'172.24.14.238',NULL,NULL),(273,'Event','Interface Added to Database','Topology',NULL,'android-6d556f7960b13da.csez.zohocorpin.com','IF-android-6d556f7960b13da.csez.zohocorpin.com',6,1478257227187,'IF-android-6d556f7960b13da.csez.zohocorpin.com',NULL,NULL),(274,'Event',' Object Added to Database','Topology',NULL,'android-6d556f7960b13da.csez.zohocorpin.comxx15','android-6d556f7960b13da.csez.zohocorpin.comxx15',6,1478257227273,'android-6d556f7960b13da.csez.zohocorpin.comxx15',NULL,NULL),(275,'Event','Node Added to Database','Topology',NULL,'android-6d556f7960b13da.csez.zohocorpin.com','android-6d556f7960b13da.csez.zohocorpin.com',6,1478257227276,'android-6d556f7960b13da.csez.zohocorpin.com',NULL,NULL),(276,'Event','Interface Added to Database','Topology',NULL,'arun-3857.csez.zohocorpin.com','IF-arun-3857.csez.zohocorpin.com',6,1478257227458,'IF-arun-3857.csez.zohocorpin.com',NULL,NULL),(277,'Event',' Object Added to Database','Topology',NULL,'arun-3857.csez.zohocorpin.comxx16','arun-3857.csez.zohocorpin.comxx16',6,1478257227536,'arun-3857.csez.zohocorpin.comxx16',NULL,NULL),(278,'Event','Node Added to Database','Topology',NULL,'arun-3857.csez.zohocorpin.com','arun-3857.csez.zohocorpin.com',6,1478257227540,'arun-3857.csez.zohocorpin.com',NULL,NULL),(279,'Event','Interface Added to Database','Topology',NULL,'varun-2246.csez.zohocorpin.com','IF-varun-2246.csez.zohocorpin.com',6,1478257227663,'IF-varun-2246.csez.zohocorpin.com',NULL,NULL),(280,'Event',' Object Added to Database','Topology',NULL,'varun-2246.csez.zohocorpin.comxx17','varun-2246.csez.zohocorpin.comxx17',6,1478257227727,'varun-2246.csez.zohocorpin.comxx17',NULL,NULL),(281,'Event','Node Added to Database','Topology',NULL,'varun-2246.csez.zohocorpin.com','varun-2246.csez.zohocorpin.com',6,1478257227732,'varun-2246.csez.zohocorpin.com',NULL,NULL),(282,'Event','Interface Added to Database','Topology',NULL,'dheeraj-1090.csez.zohocorpin.com','IF-dheeraj-1090.csez.zohocorpin.com',6,1478257239225,'IF-dheeraj-1090.csez.zohocorpin.com',NULL,NULL),(283,'Event',' Object Added to Database','Topology',NULL,'dheeraj-1090.csez.zohocorpin.comxx18','dheeraj-1090.csez.zohocorpin.comxx18',6,1478257239281,'dheeraj-1090.csez.zohocorpin.comxx18',NULL,NULL),(284,'Event','Node Added to Database','Topology',NULL,'dheeraj-1090.csez.zohocorpin.com','dheeraj-1090.csez.zohocorpin.com',6,1478257239288,'dheeraj-1090.csez.zohocorpin.com',NULL,NULL),(285,'Event','Interface Added to Database','Topology',NULL,'pav.csez.zohocorpin.com','IF-pav.csez.zohocorpin.com',6,1478257272579,'IF-pav.csez.zohocorpin.com',NULL,NULL),(286,'Event',' Object Added to Database','Topology',NULL,'pav.csez.zohocorpin.comxx19','pav.csez.zohocorpin.comxx19',6,1478257272617,'pav.csez.zohocorpin.comxx19',NULL,NULL),(287,'Event','Node Added to Database','Topology',NULL,'pav.csez.zohocorpin.com','pav.csez.zohocorpin.com',6,1478257272620,'pav.csez.zohocorpin.com',NULL,NULL),(288,'Event','Interface Added to Database','Topology',NULL,'anu-4114.csez.zohocorpin.com','IF-anu-4114.csez.zohocorpin.com',6,1478257272757,'IF-anu-4114.csez.zohocorpin.com',NULL,NULL),(289,'Event',' Object Added to Database','Topology',NULL,'anu-4114.csez.zohocorpin.comxx20','anu-4114.csez.zohocorpin.comxx20',6,1478257272796,'anu-4114.csez.zohocorpin.comxx20',NULL,NULL),(290,'Event','Node Added to Database','Topology',NULL,'anu-4114.csez.zohocorpin.com','anu-4114.csez.zohocorpin.com',6,1478257272799,'anu-4114.csez.zohocorpin.com',NULL,NULL),(291,'Event','Interface Added to Database','Topology',NULL,'sabarna-4236.csez.zohocorpin.com','IF-sabarna-4236.csez.zohocorpin.com',6,1478257272934,'IF-sabarna-4236.csez.zohocorpin.com',NULL,NULL),(292,'Event',' Object Added to Database','Topology',NULL,'sabarna-4236.csez.zohocorpin.comxx21','sabarna-4236.csez.zohocorpin.comxx21',6,1478257272969,'sabarna-4236.csez.zohocorpin.comxx21',NULL,NULL),(293,'Event','Node Added to Database','Topology',NULL,'sabarna-4236.csez.zohocorpin.com','sabarna-4236.csez.zohocorpin.com',6,1478257272972,'sabarna-4236.csez.zohocorpin.com',NULL,NULL),(294,'Event','Interface Added to Database','Topology',NULL,'ullas-4059.csez.zohocorpin.com','IF-ullas-4059.csez.zohocorpin.com',6,1478257285855,'IF-ullas-4059.csez.zohocorpin.com',NULL,NULL),(295,'Event',' Object Added to Database','Topology',NULL,'ullas-4059.csez.zohocorpin.comxx22','ullas-4059.csez.zohocorpin.comxx22',6,1478257285904,'ullas-4059.csez.zohocorpin.comxx22',NULL,NULL),(296,'Event','Node Added to Database','Topology',NULL,'ullas-4059.csez.zohocorpin.com','ullas-4059.csez.zohocorpin.com',6,1478257285908,'ullas-4059.csez.zohocorpin.com',NULL,NULL),(297,'Event','Interface Added to Database','Topology',NULL,'nj.csez.zohocorpin.com','IF-nj.csez.zohocorpin.com',6,1478257320012,'IF-nj.csez.zohocorpin.com',NULL,NULL),(298,'Event',' Object Added to Database','Topology',NULL,'nj.csez.zohocorpin.comxx23','nj.csez.zohocorpin.comxx23',6,1478257320058,'nj.csez.zohocorpin.comxx23',NULL,NULL),(299,'Event','Node Added to Database','Topology',NULL,'nj.csez.zohocorpin.com','nj.csez.zohocorpin.com',6,1478257320061,'nj.csez.zohocorpin.com',NULL,NULL),(300,'Event','Interface Added to Database','Topology',NULL,'mithun-0445.csez.zohocorpin.com','IF-mithun-0445.csez.zohocorpin.com',6,1478257342267,'IF-mithun-0445.csez.zohocorpin.com',NULL,NULL),(301,'Event',' Object Added to Database','Topology',NULL,'mithun-0445.csez.zohocorpin.comxx24','mithun-0445.csez.zohocorpin.comxx24',6,1478257342315,'mithun-0445.csez.zohocorpin.comxx24',NULL,NULL),(302,'Event','Node Added to Database','Topology',NULL,'mithun-0445.csez.zohocorpin.com','mithun-0445.csez.zohocorpin.com',6,1478257342319,'mithun-0445.csez.zohocorpin.com',NULL,NULL),(303,'Event','Interface Added to Database','Topology',NULL,'ram-3371.csez.zohocorpin.com','IF-ram-3371.csez.zohocorpin.com',6,1478257342520,'IF-ram-3371.csez.zohocorpin.com',NULL,NULL),(304,'Event',' Object Added to Database','Topology',NULL,'ram-3371.csez.zohocorpin.comxx25','ram-3371.csez.zohocorpin.comxx25',6,1478257342558,'ram-3371.csez.zohocorpin.comxx25',NULL,NULL),(305,'Event','Node Added to Database','Topology',NULL,'ram-3371.csez.zohocorpin.com','ram-3371.csez.zohocorpin.com',6,1478257342560,'ram-3371.csez.zohocorpin.com',NULL,NULL),(306,'Event','Interface Added to Database','Topology',NULL,'172.24.14.252','IF-172.24.14.252',6,1478257353969,'IF-172.24.14.252',NULL,NULL),(307,'Event',' Object Added to Database','Topology',NULL,'172.24.14.252xx26','172.24.14.252xx26',6,1478257354004,'172.24.14.252xx26',NULL,NULL),(308,'Event','Node Added to Database','Topology',NULL,'172.24.14.252','172.24.14.252',6,1478257354006,'172.24.14.252',NULL,NULL),(309,'Event','Interface failure.  Status poll failed.','Topology',NULL,'guhan-3315.csez.zohocorpin.com','IF-guhan-3315.csez.zohocorpin.com',2,1478259749876,'IF-guhan-3315.csez.zohocorpin.com','','guhan-3315.csez.zohocorpin.com'),(310,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'guhan-3315.csez.zohocorpin.com','guhan-3315.csez.zohocorpin.com',2,1478259750165,'guhan-3315.csez.zohocorpin.com',NULL,'guhan-3315.csez.zohocorpin.com'),(311,'Event','Interface failure.  Status poll failed.','Topology',NULL,'vineesh-3479.csez.zohocorpin.com','IF-vineesh-3479.csez.zohocorpin.com',2,1478259754775,'IF-vineesh-3479.csez.zohocorpin.com','','vineesh-3479.csez.zohocorpin.com'),(312,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'vineesh-3479.csez.zohocorpin.com','vineesh-3479.csez.zohocorpin.com',2,1478259755026,'vineesh-3479.csez.zohocorpin.com',NULL,'vineesh-3479.csez.zohocorpin.com'),(313,'Event','Interface clear.  ','Topology',NULL,'mani-4546.csez.zohocorpin.com','IF-mani-4546.csez.zohocorpin.com',5,1478259765624,'IF-mani-4546.csez.zohocorpin.com','','mani-4546.csez.zohocorpin.com'),(314,'Event','Node clear.  No failures on this node.','Topology',NULL,'mani-4546.csez.zohocorpin.com','mani-4546.csez.zohocorpin.com',5,1478259765720,'mani-4546.csez.zohocorpin.com',NULL,'mani-4546.csez.zohocorpin.com'),(315,'Event','Interface failure.  Status poll failed.','Topology',NULL,'iphone6usest185.csez.zohocorpin.com','IF-iphone6usest185.csez.zohocorpin.com',2,1478259774824,'IF-iphone6usest185.csez.zohocorpin.com','','iphone6usest185.csez.zohocorpin.com'),(316,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'iphone6usest185.csez.zohocorpin.com','iphone6usest185.csez.zohocorpin.com',2,1478259775036,'iphone6usest185.csez.zohocorpin.com',NULL,'iphone6usest185.csez.zohocorpin.com'),(317,'Event','Interface failure.  Status poll failed.','Topology',NULL,'rakesh-3889.csez.zohocorpin.com','IF-rakesh-3889.csez.zohocorpin.com',2,1478259785185,'IF-rakesh-3889.csez.zohocorpin.com','','rakesh-3889.csez.zohocorpin.com'),(318,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'rakesh-3889.csez.zohocorpin.com','rakesh-3889.csez.zohocorpin.com',2,1478259785469,'rakesh-3889.csez.zohocorpin.com',NULL,'rakesh-3889.csez.zohocorpin.com'),(319,'Event','Interface clear.  ','Topology',NULL,'sandeep-2.csez.zohocorpin.com','IF-sandeep-2.csez.zohocorpin.com',5,1478259800224,'IF-sandeep-2.csez.zohocorpin.com','','sandeep-2.csez.zohocorpin.com'),(320,'Event','Node clear.  No failures on this node.','Topology',NULL,'sandeep-2.csez.zohocorpin.com','sandeep-2.csez.zohocorpin.com',5,1478259800538,'sandeep-2.csez.zohocorpin.com',NULL,'sandeep-2.csez.zohocorpin.com'),(321,'Event','Interface failure.  Status poll failed.','Topology',NULL,'naga-3924.csez.zohocorpin.com','IF-naga-3924.csez.zohocorpin.com',2,1478259899763,'IF-naga-3924.csez.zohocorpin.com','','naga-3924.csez.zohocorpin.com'),(322,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'naga-3924.csez.zohocorpin.com','naga-3924.csez.zohocorpin.com',2,1478259899970,'naga-3924.csez.zohocorpin.com',NULL,'naga-3924.csez.zohocorpin.com'),(323,'Event','Interface clear.  ','Topology',NULL,'priya-zutk58.csez.zohocorpin.com','IF-priya-zutk58.csez.zohocorpin.com',5,1478259904762,'IF-priya-zutk58.csez.zohocorpin.com','','priya-zutk58.csez.zohocorpin.com'),(324,'Event','Node clear.  No failures on this node.','Topology',NULL,'priya-zutk58.csez.zohocorpin.com','priya-zutk58.csez.zohocorpin.com',5,1478259904921,'priya-zutk58.csez.zohocorpin.com',NULL,'priya-zutk58.csez.zohocorpin.com'),(325,'Event','Interface clear.  ','Topology',NULL,'mani-0702.csez.zohocorpin.com','IF-mani-0702.csez.zohocorpin.com',5,1478259929762,'IF-mani-0702.csez.zohocorpin.com','','mani-0702.csez.zohocorpin.com'),(326,'Event','Node clear.  No failures on this node.','Topology',NULL,'mani-0702.csez.zohocorpin.com','mani-0702.csez.zohocorpin.com',5,1478259930002,'mani-0702.csez.zohocorpin.com',NULL,'mani-0702.csez.zohocorpin.com'),(327,'Event','Interface failure.  Status poll failed.','Topology',NULL,'rajesh-2755.csez.zohocorpin.com','IF-rajesh-2755.csez.zohocorpin.com',2,1478259934832,'IF-rajesh-2755.csez.zohocorpin.com','','rajesh-2755.csez.zohocorpin.com'),(328,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'rajesh-2755.csez.zohocorpin.com','rajesh-2755.csez.zohocorpin.com',2,1478259934952,'rajesh-2755.csez.zohocorpin.com',NULL,'rajesh-2755.csez.zohocorpin.com'),(329,'Event','Interface failure.  Status poll failed.','Topology',NULL,'macbook-pro.csez.zohocorpin.com','IF-macbook-pro.csez.zohocorpin.com',2,1478259949764,'IF-macbook-pro.csez.zohocorpin.com','','macbook-pro.csez.zohocorpin.com'),(330,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'macbook-pro.csez.zohocorpin.com','macbook-pro.csez.zohocorpin.com',2,1478259950033,'macbook-pro.csez.zohocorpin.com',NULL,'macbook-pro.csez.zohocorpin.com'),(331,'Event','Interface clear.  ','Topology',NULL,'muni-0051.csez.zohocorpin.com','IF-muni-0051.csez.zohocorpin.com',5,1478259971339,'IF-muni-0051.csez.zohocorpin.com','','muni-0051.csez.zohocorpin.com'),(332,'Event','Node clear.  No failures on this node.','Topology',NULL,'muni-0051.csez.zohocorpin.com','muni-0051.csez.zohocorpin.com',5,1478259971576,'muni-0051.csez.zohocorpin.com',NULL,'muni-0051.csez.zohocorpin.com'),(333,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.67','IF-172.24.14.67',2,1478259978583,'IF-172.24.14.67','','172.24.14.67'),(334,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.67','172.24.14.67',2,1478259978825,'172.24.14.67',NULL,'172.24.14.67'),(335,'Event','Interface failure.  Status poll failed.','Topology',NULL,'raj-3842.csez.zohocorpin.com','IF-raj-3842.csez.zohocorpin.com',2,1478259984770,'IF-raj-3842.csez.zohocorpin.com','','raj-3842.csez.zohocorpin.com'),(336,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'raj-3842.csez.zohocorpin.com','raj-3842.csez.zohocorpin.com',2,1478259984938,'raj-3842.csez.zohocorpin.com',NULL,'raj-3842.csez.zohocorpin.com'),(337,'Event','Interface failure.  Status poll failed.','Topology',NULL,'boobala-0048.csez.zohocorpin.com','IF-boobala-0048.csez.zohocorpin.com',2,1478260004853,'IF-boobala-0048.csez.zohocorpin.com','','boobala-0048.csez.zohocorpin.com'),(338,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'boobala-0048.csez.zohocorpin.com','boobala-0048.csez.zohocorpin.com',2,1478260005017,'boobala-0048.csez.zohocorpin.com',NULL,'boobala-0048.csez.zohocorpin.com'),(339,'Event','Interface failure.  Status poll failed.','Topology',NULL,'kavitha-1061.csez.zohocorpin.com','IF-kavitha-1061.csez.zohocorpin.com',2,1478260044914,'IF-kavitha-1061.csez.zohocorpin.com','','kavitha-1061.csez.zohocorpin.com'),(340,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'kavitha-1061.csez.zohocorpin.com','kavitha-1061.csez.zohocorpin.com',2,1478260045138,'kavitha-1061.csez.zohocorpin.com',NULL,'kavitha-1061.csez.zohocorpin.com'),(341,'Event','Interface failure.  Status poll failed.','Topology',NULL,'vijay-0596.csez.zohocorpin.com','IF-vijay-0596.csez.zohocorpin.com',2,1478260059837,'IF-vijay-0596.csez.zohocorpin.com','','vijay-0596.csez.zohocorpin.com'),(342,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'vijay-0596.csez.zohocorpin.com','vijay-0596.csez.zohocorpin.com',2,1478260060023,'vijay-0596.csez.zohocorpin.com',NULL,'vijay-0596.csez.zohocorpin.com'),(343,'Event','Interface failure.  Status poll failed.','Topology',NULL,'purushoth-3454.csez.zohocorpin.com','IF-purushoth-3454.csez.zohocorpin.com',2,1478260075534,'IF-purushoth-3454.csez.zohocorpin.com','','purushoth-3454.csez.zohocorpin.com'),(344,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'purushoth-3454.csez.zohocorpin.com','purushoth-3454.csez.zohocorpin.com',2,1478260075729,'purushoth-3454.csez.zohocorpin.com',NULL,'purushoth-3454.csez.zohocorpin.com'),(345,'Event','Interface failure.  Status poll failed.','Topology',NULL,'velan-es0007.csez.zohocorpin.com','IF-velan-es0007.csez.zohocorpin.com',2,1478260079830,'IF-velan-es0007.csez.zohocorpin.com','','velan-es0007.csez.zohocorpin.com'),(346,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'velan-es0007.csez.zohocorpin.com','velan-es0007.csez.zohocorpin.com',2,1478260080040,'velan-es0007.csez.zohocorpin.com',NULL,'velan-es0007.csez.zohocorpin.com'),(347,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.171','IF-172.24.14.171',2,1478260114854,'IF-172.24.14.171','','172.24.14.171'),(348,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.171','172.24.14.171',2,1478260115008,'172.24.14.171',NULL,'172.24.14.171'),(349,'Event','Interface failure.  Status poll failed.','Topology',NULL,'gramkumar-0817.csez.zohocorpin.com','IF-gramkumar-0817.csez.zohocorpin.com',2,1478260120089,'IF-gramkumar-0817.csez.zohocorpin.com','','gramkumar-0817.csez.zohocorpin.com'),(350,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'gramkumar-0817.csez.zohocorpin.com','gramkumar-0817.csez.zohocorpin.com',2,1478260120270,'gramkumar-0817.csez.zohocorpin.com',NULL,'gramkumar-0817.csez.zohocorpin.com'),(351,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mali-0473.csez.zohocorpin.com','IF-mali-0473.csez.zohocorpin.com',2,1478260145944,'IF-mali-0473.csez.zohocorpin.com','','mali-0473.csez.zohocorpin.com'),(352,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mali-0473.csez.zohocorpin.com','mali-0473.csez.zohocorpin.com',2,1478260146139,'mali-0473.csez.zohocorpin.com',NULL,'mali-0473.csez.zohocorpin.com'),(353,'Event','Interface failure.  Status poll failed.','Topology',NULL,'preethi2siphone.csez.zohocorpin.com','IF-preethi2siphone.csez.zohocorpin.com',2,1478260180279,'IF-preethi2siphone.csez.zohocorpin.com','','preethi2siphone.csez.zohocorpin.com'),(354,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'preethi2siphone.csez.zohocorpin.com','preethi2siphone.csez.zohocorpin.com',2,1478260180439,'preethi2siphone.csez.zohocorpin.com',NULL,'preethi2siphone.csez.zohocorpin.com'),(355,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sathish-2784.csez.zohocorpin.com','IF-sathish-2784.csez.zohocorpin.com',2,1478260184965,'IF-sathish-2784.csez.zohocorpin.com','','sathish-2784.csez.zohocorpin.com'),(356,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sathish-2784.csez.zohocorpin.com','sathish-2784.csez.zohocorpin.com',2,1478260185168,'sathish-2784.csez.zohocorpin.com',NULL,'sathish-2784.csez.zohocorpin.com'),(357,'Event','Interface failure.  Status poll failed.','Topology',NULL,'hemanth-3818.csez.zohocorpin.com','IF-hemanth-3818.csez.zohocorpin.com',2,1478260390861,'IF-hemanth-3818.csez.zohocorpin.com','','hemanth-3818.csez.zohocorpin.com'),(358,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'hemanth-3818.csez.zohocorpin.com','hemanth-3818.csez.zohocorpin.com',2,1478260391146,'hemanth-3818.csez.zohocorpin.com',NULL,'hemanth-3818.csez.zohocorpin.com'),(359,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.154','IF-172.24.14.154',2,1478260424230,'IF-172.24.14.154','','172.24.14.154'),(360,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.154','172.24.14.154',2,1478260424416,'172.24.14.154',NULL,'172.24.14.154'),(361,'Event','Interface failure.  Status poll failed.','Topology',NULL,'aravinds6splus.csez.zohocorpin.com','IF-aravinds6splus.csez.zohocorpin.com',2,1478260590623,'IF-aravinds6splus.csez.zohocorpin.com','','aravinds6splus.csez.zohocorpin.com'),(362,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'aravinds6splus.csez.zohocorpin.com','aravinds6splus.csez.zohocorpin.com',2,1478260590910,'aravinds6splus.csez.zohocorpin.com',NULL,'aravinds6splus.csez.zohocorpin.com'),(363,'Event','Interface failure.  Status poll failed.','Topology',NULL,'kalai-0041.csez.zohocorpin.com','IF-kalai-0041.csez.zohocorpin.com',2,1478260769718,'IF-kalai-0041.csez.zohocorpin.com','','kalai-0041.csez.zohocorpin.com'),(364,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'kalai-0041.csez.zohocorpin.com','kalai-0041.csez.zohocorpin.com',2,1478260770042,'kalai-0041.csez.zohocorpin.com',NULL,'kalai-0041.csez.zohocorpin.com'),(365,'Event','Interface failure.  Status poll failed.','Topology',NULL,'android-8f4bc429763adb11.csez.zohocorpin.com','IF-android-8f4bc429763adb11.csez.zohocorpin.com',2,1478260814499,'IF-android-8f4bc429763adb11.csez.zohocorpin.com','','android-8f4bc429763adb11.csez.zohocorpin.com'),(366,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'android-8f4bc429763adb11.csez.zohocorpin.com','android-8f4bc429763adb11.csez.zohocorpin.com',2,1478260814552,'android-8f4bc429763adb11.csez.zohocorpin.com',NULL,'android-8f4bc429763adb11.csez.zohocorpin.com'),(367,'Event','Interface failure.  Status poll failed.','Topology',NULL,'pav.csez.zohocorpin.com','IF-pav.csez.zohocorpin.com',2,1478260883655,'IF-pav.csez.zohocorpin.com','','pav.csez.zohocorpin.com'),(368,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'pav.csez.zohocorpin.com','pav.csez.zohocorpin.com',2,1478260883916,'pav.csez.zohocorpin.com',NULL,'pav.csez.zohocorpin.com'),(369,'Event','Interface failure.  Status poll failed.','Topology',NULL,'ullas-4059.csez.zohocorpin.com','IF-ullas-4059.csez.zohocorpin.com',2,1478260896923,'IF-ullas-4059.csez.zohocorpin.com','','ullas-4059.csez.zohocorpin.com'),(370,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'ullas-4059.csez.zohocorpin.com','ullas-4059.csez.zohocorpin.com',2,1478260897225,'ullas-4059.csez.zohocorpin.com',NULL,'ullas-4059.csez.zohocorpin.com'),(371,'Event','Interface failure.  Status poll failed.','Topology',NULL,'nj.csez.zohocorpin.com','IF-nj.csez.zohocorpin.com',2,1478260931097,'IF-nj.csez.zohocorpin.com','','nj.csez.zohocorpin.com'),(372,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'nj.csez.zohocorpin.com','nj.csez.zohocorpin.com',2,1478260931403,'nj.csez.zohocorpin.com',NULL,'nj.csez.zohocorpin.com'),(373,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.252','IF-172.24.14.252',2,1478260965033,'IF-172.24.14.252','','172.24.14.252'),(374,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.252','172.24.14.252',2,1478260965176,'172.24.14.252',NULL,'172.24.14.252'),(375,'Event','Interface clear.  ','Topology',NULL,'iphone6usest185.csez.zohocorpin.com','IF-iphone6usest185.csez.zohocorpin.com',5,1478263375009,'IF-iphone6usest185.csez.zohocorpin.com','','iphone6usest185.csez.zohocorpin.com'),(376,'Event','Node clear.  No failures on this node.','Topology',NULL,'iphone6usest185.csez.zohocorpin.com','iphone6usest185.csez.zohocorpin.com',5,1478263375157,'iphone6usest185.csez.zohocorpin.com',NULL,'iphone6usest185.csez.zohocorpin.com'),(377,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mani-4546.csez.zohocorpin.com','IF-mani-4546.csez.zohocorpin.com',2,1478263376704,'IF-mani-4546.csez.zohocorpin.com','','mani-4546.csez.zohocorpin.com'),(378,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mani-4546.csez.zohocorpin.com','mani-4546.csez.zohocorpin.com',2,1478263376891,'mani-4546.csez.zohocorpin.com',NULL,'mani-4546.csez.zohocorpin.com'),(379,'Event','Interface clear.  ','Topology',NULL,'iphone6plus565.csez.zohocorpin.com','IF-iphone6plus565.csez.zohocorpin.com',5,1478263391612,'IF-iphone6plus565.csez.zohocorpin.com','','iphone6plus565.csez.zohocorpin.com'),(380,'Event','Node clear.  No failures on this node.','Topology',NULL,'iphone6plus565.csez.zohocorpin.com','iphone6plus565.csez.zohocorpin.com',5,1478263391886,'iphone6plus565.csez.zohocorpin.com',NULL,'iphone6plus565.csez.zohocorpin.com'),(381,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.28','IF-172.24.14.28',2,1478263420694,'IF-172.24.14.28','','172.24.14.28'),(382,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.28','172.24.14.28',2,1478263420972,'172.24.14.28',NULL,'172.24.14.28'),(383,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mani-0918.csez.zohocorpin.com','IF-mani-0918.csez.zohocorpin.com',2,1478263440025,'IF-mani-0918.csez.zohocorpin.com','','mani-0918.csez.zohocorpin.com'),(384,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mani-0918.csez.zohocorpin.com','mani-0918.csez.zohocorpin.com',2,1478263440258,'mani-0918.csez.zohocorpin.com',NULL,'mani-0918.csez.zohocorpin.com'),(385,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sathish-1320.csez.zohocorpin.com','IF-sathish-1320.csez.zohocorpin.com',2,1478263471303,'IF-sathish-1320.csez.zohocorpin.com','','sathish-1320.csez.zohocorpin.com'),(386,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sathish-1320.csez.zohocorpin.com','sathish-1320.csez.zohocorpin.com',2,1478263471566,'sathish-1320.csez.zohocorpin.com',NULL,'sathish-1320.csez.zohocorpin.com'),(387,'Event','Network Availability percentage is greater than 90 Value: 68.852, Data: Network Availability : KeyPerformanceIndicator : Network Availability %, Threshold Type: in','KPI',NULL,'KeyPerformanceIndicator','KeyPerformanceIndicator:Network Availability %',5,1478263475627,'KeyPerformanceIndicator',NULL,NULL),(388,'Event','Network Availability percentage is less than 70% Value: 68.852, Data: Network Availability : KeyPerformanceIndicator : Network Availability %, Threshold Type: min Critical Threshold: 70.0 Critical Rearm Value: 80.0','KPI',NULL,'KeyPerformanceIndicator','KeyPerformanceIndicator:Network Availability %',1,1478263475628,'KeyPerformanceIndicator',NULL,NULL),(389,'Event','Interface clear.  ','Topology',NULL,'naga-3924.csez.zohocorpin.com','IF-naga-3924.csez.zohocorpin.com',5,1478263499921,'IF-naga-3924.csez.zohocorpin.com','','naga-3924.csez.zohocorpin.com'),(390,'Event','Node clear.  No failures on this node.','Topology',NULL,'naga-3924.csez.zohocorpin.com','naga-3924.csez.zohocorpin.com',5,1478263500118,'naga-3924.csez.zohocorpin.com',NULL,'naga-3924.csez.zohocorpin.com'),(391,'Event','Interface failure.  Status poll failed.','Topology',NULL,'manimaran-1378.csez.zohocorpin.com','IF-manimaran-1378.csez.zohocorpin.com',2,1478263509972,'IF-manimaran-1378.csez.zohocorpin.com','','manimaran-1378.csez.zohocorpin.com'),(392,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'manimaran-1378.csez.zohocorpin.com','manimaran-1378.csez.zohocorpin.com',2,1478263510123,'manimaran-1378.csez.zohocorpin.com',NULL,'manimaran-1378.csez.zohocorpin.com'),(393,'Event','Interface failure.  Status poll failed.','Topology',NULL,'priya-zutk58.csez.zohocorpin.com','IF-priya-zutk58.csez.zohocorpin.com',2,1478263515791,'IF-priya-zutk58.csez.zohocorpin.com','','priya-zutk58.csez.zohocorpin.com'),(394,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'priya-zutk58.csez.zohocorpin.com','priya-zutk58.csez.zohocorpin.com',2,1478263516037,'priya-zutk58.csez.zohocorpin.com',NULL,'priya-zutk58.csez.zohocorpin.com'),(395,'Event','Interface clear.  ','Topology',NULL,'rajesh-2755.csez.zohocorpin.com','IF-rajesh-2755.csez.zohocorpin.com',5,1478263534925,'IF-rajesh-2755.csez.zohocorpin.com','','rajesh-2755.csez.zohocorpin.com'),(396,'Event','Node clear.  No failures on this node.','Topology',NULL,'rajesh-2755.csez.zohocorpin.com','rajesh-2755.csez.zohocorpin.com',5,1478263535162,'rajesh-2755.csez.zohocorpin.com',NULL,'rajesh-2755.csez.zohocorpin.com'),(397,'Event','Interface failure.  Status poll failed.','Topology',NULL,'abdul-zt24.csez.zohocorpin.com','IF-abdul-zt24.csez.zohocorpin.com',2,1478263540613,'IF-abdul-zt24.csez.zohocorpin.com','','abdul-zt24.csez.zohocorpin.com'),(398,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'abdul-zt24.csez.zohocorpin.com','abdul-zt24.csez.zohocorpin.com',2,1478263540925,'abdul-zt24.csez.zohocorpin.com',NULL,'abdul-zt24.csez.zohocorpin.com'),(399,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.92','IF-172.24.14.92',2,1478263566036,'IF-172.24.14.92','','172.24.14.92'),(400,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.92','172.24.14.92',2,1478263566169,'172.24.14.92',NULL,'172.24.14.92'),(401,'Event','Interface clear.  ','Topology',NULL,'172.24.14.67','IF-172.24.14.67',5,1478263578938,'IF-172.24.14.67','','172.24.14.67'),(402,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.67','172.24.14.67',5,1478263579076,'172.24.14.67',NULL,'172.24.14.67'),(403,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mohasin-2851.csez.zohocorpin.com','IF-mohasin-2851.csez.zohocorpin.com',2,1478263579910,'IF-mohasin-2851.csez.zohocorpin.com','','mohasin-2851.csez.zohocorpin.com'),(404,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mohasin-2851.csez.zohocorpin.com','mohasin-2851.csez.zohocorpin.com',2,1478263580056,'mohasin-2851.csez.zohocorpin.com',NULL,'mohasin-2851.csez.zohocorpin.com'),(405,'Event','Interface clear.  ','Topology',NULL,'raj-3842.csez.zohocorpin.com','IF-raj-3842.csez.zohocorpin.com',5,1478263584836,'IF-raj-3842.csez.zohocorpin.com','','raj-3842.csez.zohocorpin.com'),(406,'Event','Node clear.  No failures on this node.','Topology',NULL,'raj-3842.csez.zohocorpin.com','raj-3842.csez.zohocorpin.com',5,1478263585087,'raj-3842.csez.zohocorpin.com',NULL,'raj-3842.csez.zohocorpin.com'),(407,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.112','IF-172.24.14.112',2,1478263595037,'IF-172.24.14.112','','172.24.14.112'),(408,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.112','172.24.14.112',2,1478263595352,'172.24.14.112',NULL,'172.24.14.112'),(409,'Event','Interface clear.  ','Topology',NULL,'amarnath-0642.csez.zohocorpin.com','IF-amarnath-0642.csez.zohocorpin.com',5,1478263625859,'IF-amarnath-0642.csez.zohocorpin.com','','amarnath-0642.csez.zohocorpin.com'),(410,'Event','Node clear.  No failures on this node.','Topology',NULL,'amarnath-0642.csez.zohocorpin.com','amarnath-0642.csez.zohocorpin.com',5,1478263626125,'amarnath-0642.csez.zohocorpin.com',NULL,'amarnath-0642.csez.zohocorpin.com'),(411,'Event','Interface clear.  ','Topology',NULL,'vijay-0596.csez.zohocorpin.com','IF-vijay-0596.csez.zohocorpin.com',5,1478263659901,'IF-vijay-0596.csez.zohocorpin.com','','vijay-0596.csez.zohocorpin.com'),(412,'Event','Node clear.  No failures on this node.','Topology',NULL,'vijay-0596.csez.zohocorpin.com','vijay-0596.csez.zohocorpin.com',5,1478263660103,'vijay-0596.csez.zohocorpin.com',NULL,'vijay-0596.csez.zohocorpin.com'),(413,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.149','IF-172.24.14.149',2,1478263695131,'IF-172.24.14.149','','172.24.14.149'),(414,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.149','172.24.14.149',2,1478263695390,'172.24.14.149',NULL,'172.24.14.149'),(415,'Event','Interface failure.  Status poll failed.','Topology',NULL,'raji-0675.csez.zohocorpin.com','IF-raji-0675.csez.zohocorpin.com',2,1478263699875,'IF-raji-0675.csez.zohocorpin.com','','raji-0675.csez.zohocorpin.com'),(416,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'raji-0675.csez.zohocorpin.com','raji-0675.csez.zohocorpin.com',2,1478263699999,'raji-0675.csez.zohocorpin.com',NULL,'raji-0675.csez.zohocorpin.com'),(417,'Event','Interface failure.  Status poll failed.','Topology',NULL,'nirmal-2552.csez.zohocorpin.com','IF-nirmal-2552.csez.zohocorpin.com',2,1478263711547,'IF-nirmal-2552.csez.zohocorpin.com','','nirmal-2552.csez.zohocorpin.com'),(418,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'nirmal-2552.csez.zohocorpin.com','nirmal-2552.csez.zohocorpin.com',2,1478263711684,'nirmal-2552.csez.zohocorpin.com',NULL,'nirmal-2552.csez.zohocorpin.com'),(419,'Event','Interface clear.  ','Topology',NULL,'gramkumar-0817.csez.zohocorpin.com','IF-gramkumar-0817.csez.zohocorpin.com',5,1478263720189,'IF-gramkumar-0817.csez.zohocorpin.com','','gramkumar-0817.csez.zohocorpin.com'),(420,'Event','Node clear.  No failures on this node.','Topology',NULL,'gramkumar-0817.csez.zohocorpin.com','gramkumar-0817.csez.zohocorpin.com',5,1478263720464,'gramkumar-0817.csez.zohocorpin.com',NULL,'gramkumar-0817.csez.zohocorpin.com'),(421,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.178','IF-172.24.14.178',2,1478263735129,'IF-172.24.14.178','','172.24.14.178'),(422,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.178','172.24.14.178',2,1478263735394,'172.24.14.178',NULL,'172.24.14.178'),(423,'Event','Interface failure.  Status poll failed.','Topology',NULL,'prameena-1188.csez.zohocorpin.com','IF-prameena-1188.csez.zohocorpin.com',2,1478263754878,'IF-prameena-1188.csez.zohocorpin.com','','prameena-1188.csez.zohocorpin.com'),(424,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'prameena-1188.csez.zohocorpin.com','prameena-1188.csez.zohocorpin.com',2,1478263755156,'prameena-1188.csez.zohocorpin.com',NULL,'prameena-1188.csez.zohocorpin.com'),(425,'Event','Interface clear.  ','Topology',NULL,'sathish-2784.csez.zohocorpin.com','IF-sathish-2784.csez.zohocorpin.com',5,1478263785827,'IF-sathish-2784.csez.zohocorpin.com','','sathish-2784.csez.zohocorpin.com'),(426,'Event','Node clear.  No failures on this node.','Topology',NULL,'sathish-2784.csez.zohocorpin.com','sathish-2784.csez.zohocorpin.com',5,1478263786084,'sathish-2784.csez.zohocorpin.com',NULL,'sathish-2784.csez.zohocorpin.com'),(427,'Event','Interface failure.  Status poll failed.','Topology',NULL,'ajay-1385.csez.zohocorpin.com','IF-ajay-1385.csez.zohocorpin.com',2,1478263821499,'IF-ajay-1385.csez.zohocorpin.com','','ajay-1385.csez.zohocorpin.com'),(428,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'ajay-1385.csez.zohocorpin.com','ajay-1385.csez.zohocorpin.com',2,1478263821712,'ajay-1385.csez.zohocorpin.com',NULL,'ajay-1385.csez.zohocorpin.com'),(429,'Event','Interface clear.  ','Topology',NULL,'aravinds6splus.csez.zohocorpin.com','IF-aravinds6splus.csez.zohocorpin.com',5,1478264190806,'IF-aravinds6splus.csez.zohocorpin.com','','aravinds6splus.csez.zohocorpin.com'),(430,'Event','Node clear.  No failures on this node.','Topology',NULL,'aravinds6splus.csez.zohocorpin.com','aravinds6splus.csez.zohocorpin.com',5,1478264191096,'aravinds6splus.csez.zohocorpin.com',NULL,'aravinds6splus.csez.zohocorpin.com'),(431,'Event','Interface failure.  Status poll failed.','Topology',NULL,'arun-2286.csez.zohocorpin.com','IF-arun-2286.csez.zohocorpin.com',2,1478264369590,'IF-arun-2286.csez.zohocorpin.com','','arun-2286.csez.zohocorpin.com'),(432,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'arun-2286.csez.zohocorpin.com','arun-2286.csez.zohocorpin.com',2,1478264369904,'arun-2286.csez.zohocorpin.com',NULL,'arun-2286.csez.zohocorpin.com'),(433,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.226','IF-172.24.14.226',2,1478264414651,'IF-172.24.14.226','','172.24.14.226'),(434,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.226','172.24.14.226',2,1478264414960,'172.24.14.226',NULL,'172.24.14.226'),(435,'Event','Interface failure.  Status poll failed.','Topology',NULL,'varun-2246.csez.zohocorpin.com','IF-varun-2246.csez.zohocorpin.com',2,1478264438814,'IF-varun-2246.csez.zohocorpin.com','','varun-2246.csez.zohocorpin.com'),(436,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'varun-2246.csez.zohocorpin.com','varun-2246.csez.zohocorpin.com',2,1478264439039,'varun-2246.csez.zohocorpin.com',NULL,'varun-2246.csez.zohocorpin.com'),(437,'Event','Interface failure.  Status poll failed.','Topology',NULL,'android-6d556f7960b13da.csez.zohocorpin.com','IF-android-6d556f7960b13da.csez.zohocorpin.com',2,1478264439064,'IF-android-6d556f7960b13da.csez.zohocorpin.com','','android-6d556f7960b13da.csez.zohocorpin.com'),(438,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'android-6d556f7960b13da.csez.zohocorpin.com','android-6d556f7960b13da.csez.zohocorpin.com',2,1478264439381,'android-6d556f7960b13da.csez.zohocorpin.com',NULL,'android-6d556f7960b13da.csez.zohocorpin.com'),(439,'Event','Interface failure.  Status poll failed.','Topology',NULL,'dheeraj-1090.csez.zohocorpin.com','IF-dheeraj-1090.csez.zohocorpin.com',2,1478264452628,'IF-dheeraj-1090.csez.zohocorpin.com','','dheeraj-1090.csez.zohocorpin.com'),(440,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'dheeraj-1090.csez.zohocorpin.com','dheeraj-1090.csez.zohocorpin.com',2,1478264452788,'dheeraj-1090.csez.zohocorpin.com',NULL,'dheeraj-1090.csez.zohocorpin.com'),(441,'Event','Interface clear.  ','Topology',NULL,'pav.csez.zohocorpin.com','IF-pav.csez.zohocorpin.com',5,1478264484654,'IF-pav.csez.zohocorpin.com','','pav.csez.zohocorpin.com'),(442,'Event','Node clear.  No failures on this node.','Topology',NULL,'pav.csez.zohocorpin.com','pav.csez.zohocorpin.com',5,1478264484910,'pav.csez.zohocorpin.com',NULL,'pav.csez.zohocorpin.com'),(443,'Event','Interface clear.  ','Topology',NULL,'ullas-4059.csez.zohocorpin.com','IF-ullas-4059.csez.zohocorpin.com',5,1478264496987,'IF-ullas-4059.csez.zohocorpin.com','','ullas-4059.csez.zohocorpin.com'),(444,'Event','Node clear.  No failures on this node.','Topology',NULL,'ullas-4059.csez.zohocorpin.com','ullas-4059.csez.zohocorpin.com',5,1478264497161,'ullas-4059.csez.zohocorpin.com',NULL,'ullas-4059.csez.zohocorpin.com'),(445,'Event','Interface failure.  Status poll failed.','Topology',NULL,'ram-3371.csez.zohocorpin.com','IF-ram-3371.csez.zohocorpin.com',2,1478264554987,'IF-ram-3371.csez.zohocorpin.com','','ram-3371.csez.zohocorpin.com'),(446,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'ram-3371.csez.zohocorpin.com','ram-3371.csez.zohocorpin.com',2,1478264555221,'ram-3371.csez.zohocorpin.com',NULL,'ram-3371.csez.zohocorpin.com'),(447,'Event','Interface failure.  Status poll failed.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','IF-192.168.220.202',2,1478265854909,'IF-192.168.220.202','','rejoe-0162.csez.zohocorpin.com'),(448,'Event','Interface failure.  Status poll failed.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','IF-rejoe-0162.csez.zohocorpin.com',2,1478265854959,'IF-rejoe-0162.csez.zohocorpin.com','','rejoe-0162.csez.zohocorpin.com'),(449,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','rejoe-0162.csez.zohocorpin.com',2,1478265855166,'rejoe-0162.csez.zohocorpin.com',NULL,'rejoe-0162.csez.zohocorpin.com'),(450,'Event','Interface clear.  ','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','IF-192.168.220.202',5,1478266155104,'IF-192.168.220.202','','rejoe-0162.csez.zohocorpin.com'),(451,'Event','Interface clear.  ','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','IF-rejoe-0162.csez.zohocorpin.com',5,1478266155353,'IF-rejoe-0162.csez.zohocorpin.com','','rejoe-0162.csez.zohocorpin.com'),(452,'Event','Node clear.  No failures on this node.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','rejoe-0162.csez.zohocorpin.com',5,1478266155566,'rejoe-0162.csez.zohocorpin.com',NULL,'rejoe-0162.csez.zohocorpin.com'),(453,'Event','Threshold exceeded :  Value: 85.0, Data: JVMPD_CLIENT_14500_MonitorMemory : Client_172.24.14.34 : .1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0, Threshold Type: max Critical Threshold: 80.0 Critical Rearm Value: 70.0','NMSManagement',NULL,'Client_172.24.14.34','Client_172.24.14.34:.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0',1,1478266395669,'Client_172.24.14.34',NULL,NULL),(454,'Event','Interface clear.  ','Topology',NULL,'guhan-3315.csez.zohocorpin.com','IF-guhan-3315.csez.zohocorpin.com',5,1478266961034,'IF-guhan-3315.csez.zohocorpin.com','','guhan-3315.csez.zohocorpin.com'),(455,'Event','Node clear.  No failures on this node.','Topology',NULL,'guhan-3315.csez.zohocorpin.com','guhan-3315.csez.zohocorpin.com',5,1478266961320,'guhan-3315.csez.zohocorpin.com',NULL,'guhan-3315.csez.zohocorpin.com'),(456,'Event','Interface clear.  ','Topology',NULL,'mani-4546.csez.zohocorpin.com','IF-mani-4546.csez.zohocorpin.com',5,1478266977058,'IF-mani-4546.csez.zohocorpin.com','','mani-4546.csez.zohocorpin.com'),(457,'Event','Node clear.  No failures on this node.','Topology',NULL,'mani-4546.csez.zohocorpin.com','mani-4546.csez.zohocorpin.com',5,1478266977312,'mani-4546.csez.zohocorpin.com',NULL,'mani-4546.csez.zohocorpin.com'),(458,'Event','Interface failure.  Status poll failed.','Topology',NULL,'subha-4506.csez.zohocorpin.com','IF-subha-4506.csez.zohocorpin.com',2,1478266990012,'IF-subha-4506.csez.zohocorpin.com','','subha-4506.csez.zohocorpin.com'),(459,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'subha-4506.csez.zohocorpin.com','subha-4506.csez.zohocorpin.com',2,1478266990159,'subha-4506.csez.zohocorpin.com',NULL,'subha-4506.csez.zohocorpin.com'),(460,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sandeep-2.csez.zohocorpin.com','IF-sandeep-2.csez.zohocorpin.com',2,1478267011330,'IF-sandeep-2.csez.zohocorpin.com','','sandeep-2.csez.zohocorpin.com'),(461,'Event','Interface failure.  Status poll failed.','Topology',NULL,'ksenthil-0949.csez.zohocorpin.com','IF-ksenthil-0949.csez.zohocorpin.com',2,1478267011357,'IF-ksenthil-0949.csez.zohocorpin.com','','ksenthil-0949.csez.zohocorpin.com'),(462,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'ksenthil-0949.csez.zohocorpin.com','ksenthil-0949.csez.zohocorpin.com',2,1478267011638,'ksenthil-0949.csez.zohocorpin.com',NULL,'ksenthil-0949.csez.zohocorpin.com'),(463,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sandeep-2.csez.zohocorpin.com','sandeep-2.csez.zohocorpin.com',2,1478267011650,'sandeep-2.csez.zohocorpin.com',NULL,'sandeep-2.csez.zohocorpin.com'),(464,'Event','Interface failure.  Status poll failed.','Topology',NULL,'pandi-1824.csez.zohocorpin.com','IF-pandi-1824.csez.zohocorpin.com',2,1478267066427,'IF-pandi-1824.csez.zohocorpin.com','','pandi-1824.csez.zohocorpin.com'),(465,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'pandi-1824.csez.zohocorpin.com','pandi-1824.csez.zohocorpin.com',2,1478267066743,'pandi-1824.csez.zohocorpin.com',NULL,'pandi-1824.csez.zohocorpin.com'),(466,'Event','Interface clear.  ','Topology',NULL,'sathish-1320.csez.zohocorpin.com','IF-sathish-1320.csez.zohocorpin.com',5,1478267071456,'IF-sathish-1320.csez.zohocorpin.com','','sathish-1320.csez.zohocorpin.com'),(467,'Event','Node clear.  No failures on this node.','Topology',NULL,'sathish-1320.csez.zohocorpin.com','sathish-1320.csez.zohocorpin.com',5,1478267071662,'sathish-1320.csez.zohocorpin.com',NULL,'sathish-1320.csez.zohocorpin.com'),(468,'Event','Interface failure.  Status poll failed.','Topology',NULL,'vinodh-2312.csez.zohocorpin.com','IF-vinodh-2312.csez.zohocorpin.com',2,1478267080548,'IF-vinodh-2312.csez.zohocorpin.com','','vinodh-2312.csez.zohocorpin.com'),(469,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'vinodh-2312.csez.zohocorpin.com','vinodh-2312.csez.zohocorpin.com',2,1478267080757,'vinodh-2312.csez.zohocorpin.com',NULL,'vinodh-2312.csez.zohocorpin.com'),(470,'Event','Interface clear.  ','Topology',NULL,'abdul-zt24.csez.zohocorpin.com','IF-abdul-zt24.csez.zohocorpin.com',5,1478267140684,'IF-abdul-zt24.csez.zohocorpin.com','','abdul-zt24.csez.zohocorpin.com'),(471,'Event','Node clear.  No failures on this node.','Topology',NULL,'abdul-zt24.csez.zohocorpin.com','abdul-zt24.csez.zohocorpin.com',5,1478267141009,'abdul-zt24.csez.zohocorpin.com',NULL,'abdul-zt24.csez.zohocorpin.com'),(472,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.84','IF-172.24.14.84',2,1478267155312,'IF-172.24.14.84','','172.24.14.84'),(473,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.84','172.24.14.84',2,1478267155567,'172.24.14.84',NULL,'172.24.14.84'),(474,'Event','Interface clear.  ','Topology',NULL,'macbook-pro.csez.zohocorpin.com','IF-macbook-pro.csez.zohocorpin.com',5,1478267160891,'IF-macbook-pro.csez.zohocorpin.com','','macbook-pro.csez.zohocorpin.com'),(475,'Event','Interface failure.  Status poll failed.','Topology',NULL,'jsangeetha-0849.csez.zohocorpin.com','IF-jsangeetha-0849.csez.zohocorpin.com',2,1478267161009,'IF-jsangeetha-0849.csez.zohocorpin.com','','jsangeetha-0849.csez.zohocorpin.com'),(476,'Event','Node clear.  No failures on this node.','Topology',NULL,'macbook-pro.csez.zohocorpin.com','macbook-pro.csez.zohocorpin.com',5,1478267161217,'macbook-pro.csez.zohocorpin.com',NULL,'macbook-pro.csez.zohocorpin.com'),(477,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'jsangeetha-0849.csez.zohocorpin.com','jsangeetha-0849.csez.zohocorpin.com',2,1478267161336,'jsangeetha-0849.csez.zohocorpin.com',NULL,'jsangeetha-0849.csez.zohocorpin.com'),(478,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.67','IF-172.24.14.67',2,1478267189981,'IF-172.24.14.67','','172.24.14.67'),(479,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.67','172.24.14.67',2,1478267190270,'172.24.14.67',NULL,'172.24.14.67'),(480,'Event','Interface failure.  Status poll failed.','Topology',NULL,'siva-2171.csez.zohocorpin.com','IF-siva-2171.csez.zohocorpin.com',2,1478267200306,'IF-siva-2171.csez.zohocorpin.com','','siva-2171.csez.zohocorpin.com'),(481,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'siva-2171.csez.zohocorpin.com','siva-2171.csez.zohocorpin.com',2,1478267200511,'siva-2171.csez.zohocorpin.com',NULL,'siva-2171.csez.zohocorpin.com'),(482,'Event','Interface failure.  Status poll failed.','Topology',NULL,'admp-test1.csez.zohocorpin.com','IF-admp-test1.csez.zohocorpin.com',2,1478267202593,'IF-admp-test1.csez.zohocorpin.com','','admp-test1.csez.zohocorpin.com'),(483,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'admp-test1.csez.zohocorpin.com','admp-test1.csez.zohocorpin.com',2,1478267202879,'admp-test1.csez.zohocorpin.com',NULL,'admp-test1.csez.zohocorpin.com'),(484,'Event','Interface failure.  Status poll failed.','Topology',NULL,'jlatha-0972.csez.zohocorpin.com','IF-jlatha-0972.csez.zohocorpin.com',2,1478267213643,'IF-jlatha-0972.csez.zohocorpin.com','','jlatha-0972.csez.zohocorpin.com'),(485,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'jlatha-0972.csez.zohocorpin.com','jlatha-0972.csez.zohocorpin.com',2,1478267213906,'jlatha-0972.csez.zohocorpin.com',NULL,'jlatha-0972.csez.zohocorpin.com'),(486,'Event','Interface clear.  ','Topology',NULL,'boobala-0048.csez.zohocorpin.com','IF-boobala-0048.csez.zohocorpin.com',5,1478267216129,'IF-boobala-0048.csez.zohocorpin.com','','boobala-0048.csez.zohocorpin.com'),(487,'Event','Node clear.  No failures on this node.','Topology',NULL,'boobala-0048.csez.zohocorpin.com','boobala-0048.csez.zohocorpin.com',5,1478267216257,'boobala-0048.csez.zohocorpin.com',NULL,'boobala-0048.csez.zohocorpin.com'),(488,'Event','Interface failure.  Status poll failed.','Topology',NULL,'partha-3525.csez.zohocorpin.com','IF-partha-3525.csez.zohocorpin.com',2,1478267220110,'IF-partha-3525.csez.zohocorpin.com','','partha-3525.csez.zohocorpin.com'),(489,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'partha-3525.csez.zohocorpin.com','partha-3525.csez.zohocorpin.com',2,1478267220256,'partha-3525.csez.zohocorpin.com',NULL,'partha-3525.csez.zohocorpin.com'),(490,'Event','Interface failure.  Status poll failed.','Topology',NULL,'amarnath-0642.csez.zohocorpin.com','IF-amarnath-0642.csez.zohocorpin.com',2,1478267236906,'IF-amarnath-0642.csez.zohocorpin.com','','amarnath-0642.csez.zohocorpin.com'),(491,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'amarnath-0642.csez.zohocorpin.com','amarnath-0642.csez.zohocorpin.com',2,1478267237033,'amarnath-0642.csez.zohocorpin.com',NULL,'amarnath-0642.csez.zohocorpin.com'),(492,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sandhya-3439.csez.zohocorpin.com','IF-sandhya-3439.csez.zohocorpin.com',2,1478267239967,'IF-sandhya-3439.csez.zohocorpin.com','','sandhya-3439.csez.zohocorpin.com'),(493,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sandhya-3439.csez.zohocorpin.com','sandhya-3439.csez.zohocorpin.com',2,1478267240208,'sandhya-3439.csez.zohocorpin.com',NULL,'sandhya-3439.csez.zohocorpin.com'),(494,'Event','Interface clear.  ','Topology',NULL,'172.24.14.149','IF-172.24.14.149',5,1478267295226,'IF-172.24.14.149','','172.24.14.149'),(495,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.149','172.24.14.149',5,1478267295490,'172.24.14.149',NULL,'172.24.14.149'),(496,'Event','Interface failure.  Status poll failed.','Topology',NULL,'meena.csez.zohocorpin.com','IF-meena.csez.zohocorpin.com',2,1478267322928,'IF-meena.csez.zohocorpin.com','','meena.csez.zohocorpin.com'),(497,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'meena.csez.zohocorpin.com','meena.csez.zohocorpin.com',2,1478267323186,'meena.csez.zohocorpin.com',NULL,'meena.csez.zohocorpin.com'),(498,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sundari-1712.csez.zohocorpin.com','IF-sundari-1712.csez.zohocorpin.com',2,1478267325071,'IF-sundari-1712.csez.zohocorpin.com','','sundari-1712.csez.zohocorpin.com'),(499,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sundari-1712.csez.zohocorpin.com','sundari-1712.csez.zohocorpin.com',2,1478267325208,'sundari-1712.csez.zohocorpin.com',NULL,'sundari-1712.csez.zohocorpin.com'),(500,'Event','Interface clear.  ','Topology',NULL,'172.24.14.171','IF-172.24.14.171',5,1478267327068,'IF-172.24.14.171','','172.24.14.171'),(501,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.171','172.24.14.171',5,1478267327230,'172.24.14.171',NULL,'172.24.14.171'),(502,'Event','Interface clear.  ','Topology',NULL,'radhas-iphone-6.csez.zohocorpin.com','IF-radhas-iphone-6.csez.zohocorpin.com',5,1478267352842,'IF-radhas-iphone-6.csez.zohocorpin.com','','radhas-iphone-6.csez.zohocorpin.com'),(503,'Event','Node clear.  No failures on this node.','Topology',NULL,'radhas-iphone-6.csez.zohocorpin.com','radhas-iphone-6.csez.zohocorpin.com',5,1478267353009,'radhas-iphone-6.csez.zohocorpin.com',NULL,'radhas-iphone-6.csez.zohocorpin.com'),(504,'Event','Interface failure.  Status poll failed.','Topology',NULL,'android-6da4f6e8432f2ea.csez.zohocorpin.com','IF-android-6da4f6e8432f2ea.csez.zohocorpin.com',2,1478267363352,'IF-android-6da4f6e8432f2ea.csez.zohocorpin.com','','android-6da4f6e8432f2ea.csez.zohocorpin.com'),(505,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'android-6da4f6e8432f2ea.csez.zohocorpin.com','android-6da4f6e8432f2ea.csez.zohocorpin.com',2,1478267363595,'android-6da4f6e8432f2ea.csez.zohocorpin.com',NULL,'android-6da4f6e8432f2ea.csez.zohocorpin.com'),(506,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.115','IF-172.24.14.115',2,1478267391811,'IF-172.24.14.115','','172.24.14.115'),(507,'Event','Interface clear.  ','Topology',NULL,'iphone-6-arivu.csez.zohocorpin.com','IF-iphone-6-arivu.csez.zohocorpin.com',5,1478267391961,'IF-iphone-6-arivu.csez.zohocorpin.com','','iphone-6-arivu.csez.zohocorpin.com'),(508,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.115','172.24.14.115',2,1478267392071,'172.24.14.115',NULL,'172.24.14.115'),(509,'Event','Node clear.  No failures on this node.','Topology',NULL,'iphone-6-arivu.csez.zohocorpin.com','iphone-6-arivu.csez.zohocorpin.com',5,1478267392187,'iphone-6-arivu.csez.zohocorpin.com',NULL,'iphone-6-arivu.csez.zohocorpin.com'),(510,'Event','Interface failure.  Status poll failed.','Topology',NULL,'damodhar-1003.csez.zohocorpin.com','IF-damodhar-1003.csez.zohocorpin.com',2,1478267406532,'IF-damodhar-1003.csez.zohocorpin.com','','damodhar-1003.csez.zohocorpin.com'),(511,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'damodhar-1003.csez.zohocorpin.com','damodhar-1003.csez.zohocorpin.com',2,1478267406719,'damodhar-1003.csez.zohocorpin.com',NULL,'damodhar-1003.csez.zohocorpin.com'),(512,'Event','Interface clear.  ','Topology',NULL,'hemanth-3818.csez.zohocorpin.com','IF-hemanth-3818.csez.zohocorpin.com',5,1478267602045,'IF-hemanth-3818.csez.zohocorpin.com','','hemanth-3818.csez.zohocorpin.com'),(513,'Event','Node clear.  No failures on this node.','Topology',NULL,'hemanth-3818.csez.zohocorpin.com','hemanth-3818.csez.zohocorpin.com',5,1478267602335,'hemanth-3818.csez.zohocorpin.com',NULL,'hemanth-3818.csez.zohocorpin.com'),(514,'Event','Interface failure.  Status poll failed.','Topology',NULL,'aravinds6splus.csez.zohocorpin.com','IF-aravinds6splus.csez.zohocorpin.com',2,1478267801855,'IF-aravinds6splus.csez.zohocorpin.com','','aravinds6splus.csez.zohocorpin.com'),(515,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'aravinds6splus.csez.zohocorpin.com','aravinds6splus.csez.zohocorpin.com',2,1478267802094,'aravinds6splus.csez.zohocorpin.com',NULL,'aravinds6splus.csez.zohocorpin.com'),(516,'Event','Interface clear.  ','Topology',NULL,'172.24.14.226','IF-172.24.14.226',5,1478268014750,'IF-172.24.14.226','','172.24.14.226'),(517,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.226','172.24.14.226',5,1478268014885,'172.24.14.226',NULL,'172.24.14.226'),(518,'Event','Interface failure.  Status poll failed.','Topology',NULL,'dheeraj-1090.csez.zohocorpin.com','IF-dheeraj-1090.csez.zohocorpin.com',2,1478268063691,'IF-dheeraj-1090.csez.zohocorpin.com','','dheeraj-1090.csez.zohocorpin.com'),(519,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'dheeraj-1090.csez.zohocorpin.com','dheeraj-1090.csez.zohocorpin.com',2,1478268063845,'dheeraj-1090.csez.zohocorpin.com',NULL,'dheeraj-1090.csez.zohocorpin.com'),(520,'Event','Interface failure.  Status poll failed.','Topology',NULL,'anu-4114.csez.zohocorpin.com','IF-anu-4114.csez.zohocorpin.com',2,1478268084131,'IF-anu-4114.csez.zohocorpin.com','','anu-4114.csez.zohocorpin.com'),(521,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'anu-4114.csez.zohocorpin.com','anu-4114.csez.zohocorpin.com',2,1478268084386,'anu-4114.csez.zohocorpin.com',NULL,'anu-4114.csez.zohocorpin.com'),(522,'Event','Interface clear.  ','Topology',NULL,'nj.csez.zohocorpin.com','IF-nj.csez.zohocorpin.com',5,1478268142232,'IF-nj.csez.zohocorpin.com','','nj.csez.zohocorpin.com'),(523,'Event','Node clear.  No failures on this node.','Topology',NULL,'nj.csez.zohocorpin.com','nj.csez.zohocorpin.com',5,1478268142528,'nj.csez.zohocorpin.com',NULL,'nj.csez.zohocorpin.com'),(524,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mithun-0445.csez.zohocorpin.com','IF-mithun-0445.csez.zohocorpin.com',2,1478268155915,'IF-mithun-0445.csez.zohocorpin.com','','mithun-0445.csez.zohocorpin.com'),(525,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mithun-0445.csez.zohocorpin.com','mithun-0445.csez.zohocorpin.com',2,1478268156193,'mithun-0445.csez.zohocorpin.com',NULL,'mithun-0445.csez.zohocorpin.com'),(526,'Event','Interface failure.  Status poll failed.','Topology',NULL,'ram-3371.csez.zohocorpin.com','IF-ram-3371.csez.zohocorpin.com',2,1478268166052,'IF-ram-3371.csez.zohocorpin.com','','ram-3371.csez.zohocorpin.com'),(527,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'ram-3371.csez.zohocorpin.com','ram-3371.csez.zohocorpin.com',2,1478268166182,'ram-3371.csez.zohocorpin.com',NULL,'ram-3371.csez.zohocorpin.com'),(528,'Event','Interface failure.  Status poll failed.','Topology',NULL,'varun-3902.csez.zohocorpin.com','IF-varun-3902.csez.zohocorpin.com',2,1478270563606,'IF-varun-3902.csez.zohocorpin.com','','varun-3902.csez.zohocorpin.com'),(529,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'varun-3902.csez.zohocorpin.com','varun-3902.csez.zohocorpin.com',2,1478270563842,'varun-3902.csez.zohocorpin.com',NULL,'varun-3902.csez.zohocorpin.com'),(530,'Event','Interface failure.  Status poll failed.','Topology',NULL,'guhan-3315.csez.zohocorpin.com','IF-guhan-3315.csez.zohocorpin.com',2,1478270572081,'IF-guhan-3315.csez.zohocorpin.com','','guhan-3315.csez.zohocorpin.com'),(531,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'guhan-3315.csez.zohocorpin.com','guhan-3315.csez.zohocorpin.com',2,1478270572337,'guhan-3315.csez.zohocorpin.com',NULL,'guhan-3315.csez.zohocorpin.com'),(532,'Event','Interface failure.  Status poll failed.','Topology',NULL,'iphone6usest185.csez.zohocorpin.com','IF-iphone6usest185.csez.zohocorpin.com',2,1478270587277,'IF-iphone6usest185.csez.zohocorpin.com','','iphone6usest185.csez.zohocorpin.com'),(533,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'iphone6usest185.csez.zohocorpin.com','iphone6usest185.csez.zohocorpin.com',2,1478270587594,'iphone6usest185.csez.zohocorpin.com',NULL,'iphone6usest185.csez.zohocorpin.com'),(534,'Event','Interface failure.  Status poll failed.','Topology',NULL,'iphone6plus565.csez.zohocorpin.com','IF-iphone6plus565.csez.zohocorpin.com',2,1478270603818,'IF-iphone6plus565.csez.zohocorpin.com','','iphone6plus565.csez.zohocorpin.com'),(535,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'iphone6plus565.csez.zohocorpin.com','iphone6plus565.csez.zohocorpin.com',2,1478270604029,'iphone6plus565.csez.zohocorpin.com',NULL,'iphone6plus565.csez.zohocorpin.com'),(536,'Event','Interface clear.  ','Topology',NULL,'sandeep-2.csez.zohocorpin.com','IF-sandeep-2.csez.zohocorpin.com',5,1478270611705,'IF-sandeep-2.csez.zohocorpin.com','','sandeep-2.csez.zohocorpin.com'),(537,'Event','Node clear.  No failures on this node.','Topology',NULL,'sandeep-2.csez.zohocorpin.com','sandeep-2.csez.zohocorpin.com',5,1478270611897,'sandeep-2.csez.zohocorpin.com',NULL,'sandeep-2.csez.zohocorpin.com'),(538,'Event','Interface failure.  Status poll failed.','Topology',NULL,'padma-4271.csez.zohocorpin.com','IF-padma-4271.csez.zohocorpin.com',2,1478270618672,'IF-padma-4271.csez.zohocorpin.com','','padma-4271.csez.zohocorpin.com'),(539,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'padma-4271.csez.zohocorpin.com','padma-4271.csez.zohocorpin.com',2,1478270618847,'padma-4271.csez.zohocorpin.com',NULL,'padma-4271.csez.zohocorpin.com'),(540,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sathish-1320.csez.zohocorpin.com','IF-sathish-1320.csez.zohocorpin.com',2,1478271058159,'IF-sathish-1320.csez.zohocorpin.com','','sathish-1320.csez.zohocorpin.com'),(541,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sathish-1320.csez.zohocorpin.com','sathish-1320.csez.zohocorpin.com',2,1478271058480,'sathish-1320.csez.zohocorpin.com',NULL,'sathish-1320.csez.zohocorpin.com'),(542,'Event','Interface failure.  Status poll failed.','Topology',NULL,'ashish-4086.csez.zohocorpin.com','IF-ashish-4086.csez.zohocorpin.com',2,1478271069196,'IF-ashish-4086.csez.zohocorpin.com','','ashish-4086.csez.zohocorpin.com'),(543,'Event','Interface failure.  Status poll failed.','Topology',NULL,'shanmugam-2352.csez.zohocorpin.com','IF-shanmugam-2352.csez.zohocorpin.com',2,1478271069205,'IF-shanmugam-2352.csez.zohocorpin.com','','shanmugam-2352.csez.zohocorpin.com'),(544,'Event','Interface failure.  Status poll failed.','Topology',NULL,'naga-3924.csez.zohocorpin.com','IF-naga-3924.csez.zohocorpin.com',2,1478271069216,'IF-naga-3924.csez.zohocorpin.com','','naga-3924.csez.zohocorpin.com'),(545,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'naga-3924.csez.zohocorpin.com','naga-3924.csez.zohocorpin.com',2,1478271069415,'naga-3924.csez.zohocorpin.com',NULL,'naga-3924.csez.zohocorpin.com'),(546,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'ashish-4086.csez.zohocorpin.com','ashish-4086.csez.zohocorpin.com',2,1478271069468,'ashish-4086.csez.zohocorpin.com',NULL,'ashish-4086.csez.zohocorpin.com'),(547,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'shanmugam-2352.csez.zohocorpin.com','shanmugam-2352.csez.zohocorpin.com',2,1478271069525,'shanmugam-2352.csez.zohocorpin.com',NULL,'shanmugam-2352.csez.zohocorpin.com'),(548,'Event','Interface failure.  Status poll failed.','Topology',NULL,'gokul-3303.csez.zohocorpin.com','IF-gokul-3303.csez.zohocorpin.com',2,1478271080261,'IF-gokul-3303.csez.zohocorpin.com','','gokul-3303.csez.zohocorpin.com'),(549,'Event','Interface failure.  Status poll failed.','Topology',NULL,'arunsubhash-0371.csez.zohocorpin.com','IF-arunsubhash-0371.csez.zohocorpin.com',2,1478271080289,'IF-arunsubhash-0371.csez.zohocorpin.com','','arunsubhash-0371.csez.zohocorpin.com'),(550,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'gokul-3303.csez.zohocorpin.com','gokul-3303.csez.zohocorpin.com',2,1478271080429,'gokul-3303.csez.zohocorpin.com',NULL,'gokul-3303.csez.zohocorpin.com'),(551,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'arunsubhash-0371.csez.zohocorpin.com','arunsubhash-0371.csez.zohocorpin.com',2,1478271080451,'arunsubhash-0371.csez.zohocorpin.com',NULL,'arunsubhash-0371.csez.zohocorpin.com'),(552,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mani-0702.csez.zohocorpin.com','IF-mani-0702.csez.zohocorpin.com',2,1478271091322,'IF-mani-0702.csez.zohocorpin.com','','mani-0702.csez.zohocorpin.com'),(553,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mani-0702.csez.zohocorpin.com','mani-0702.csez.zohocorpin.com',2,1478271091646,'mani-0702.csez.zohocorpin.com',NULL,'mani-0702.csez.zohocorpin.com'),(554,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mani-2209.csez.zohocorpin.com','IF-mani-2209.csez.zohocorpin.com',2,1478271102361,'IF-mani-2209.csez.zohocorpin.com','','mani-2209.csez.zohocorpin.com'),(555,'Event','Interface failure.  Status poll failed.','Topology',NULL,'rajesh-2755.csez.zohocorpin.com','IF-rajesh-2755.csez.zohocorpin.com',2,1478271102368,'IF-rajesh-2755.csez.zohocorpin.com','','rajesh-2755.csez.zohocorpin.com'),(556,'Event','Interface failure.  Status poll failed.','Topology',NULL,'abdul-zt24.csez.zohocorpin.com','IF-abdul-zt24.csez.zohocorpin.com',2,1478271102387,'IF-abdul-zt24.csez.zohocorpin.com','','abdul-zt24.csez.zohocorpin.com'),(557,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'rajesh-2755.csez.zohocorpin.com','rajesh-2755.csez.zohocorpin.com',2,1478271102668,'rajesh-2755.csez.zohocorpin.com',NULL,'rajesh-2755.csez.zohocorpin.com'),(558,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mani-2209.csez.zohocorpin.com','mani-2209.csez.zohocorpin.com',2,1478271102717,'mani-2209.csez.zohocorpin.com',NULL,'mani-2209.csez.zohocorpin.com'),(559,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'abdul-zt24.csez.zohocorpin.com','abdul-zt24.csez.zohocorpin.com',2,1478271102771,'abdul-zt24.csez.zohocorpin.com',NULL,'abdul-zt24.csez.zohocorpin.com'),(560,'Event','Interface failure.  Status poll failed.','Topology',NULL,'macbook-pro.csez.zohocorpin.com','IF-macbook-pro.csez.zohocorpin.com',2,1478271113430,'IF-macbook-pro.csez.zohocorpin.com','','macbook-pro.csez.zohocorpin.com'),(561,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'macbook-pro.csez.zohocorpin.com','macbook-pro.csez.zohocorpin.com',2,1478271113770,'macbook-pro.csez.zohocorpin.com',NULL,'macbook-pro.csez.zohocorpin.com'),(562,'Event','Interface failure.  Status poll failed.','Topology',NULL,'muni-0051.csez.zohocorpin.com','IF-muni-0051.csez.zohocorpin.com',2,1478271154159,'IF-muni-0051.csez.zohocorpin.com','','muni-0051.csez.zohocorpin.com'),(563,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.105','IF-172.24.14.105',2,1478271154160,'IF-172.24.14.105','','172.24.14.105'),(564,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'muni-0051.csez.zohocorpin.com','muni-0051.csez.zohocorpin.com',2,1478271154414,'muni-0051.csez.zohocorpin.com',NULL,'muni-0051.csez.zohocorpin.com'),(565,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.105','172.24.14.105',2,1478271154423,'172.24.14.105',NULL,'172.24.14.105'),(566,'Event','Interface failure.  Status poll failed.','Topology',NULL,'raj-3842.csez.zohocorpin.com','IF-raj-3842.csez.zohocorpin.com',2,1478271165219,'IF-raj-3842.csez.zohocorpin.com','','raj-3842.csez.zohocorpin.com'),(567,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'raj-3842.csez.zohocorpin.com','raj-3842.csez.zohocorpin.com',2,1478271165516,'raj-3842.csez.zohocorpin.com',NULL,'raj-3842.csez.zohocorpin.com'),(568,'Event','Interface failure.  Status poll failed.','Topology',NULL,'chithu-0706.csez.zohocorpin.com','IF-chithu-0706.csez.zohocorpin.com',2,1478271187367,'IF-chithu-0706.csez.zohocorpin.com','','chithu-0706.csez.zohocorpin.com'),(569,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'chithu-0706.csez.zohocorpin.com','chithu-0706.csez.zohocorpin.com',2,1478271187653,'chithu-0706.csez.zohocorpin.com',NULL,'chithu-0706.csez.zohocorpin.com'),(570,'Event','Interface failure.  Status poll failed.','Topology',NULL,'boobala-0048.csez.zohocorpin.com','IF-boobala-0048.csez.zohocorpin.com',2,1478271198384,'IF-boobala-0048.csez.zohocorpin.com','','boobala-0048.csez.zohocorpin.com'),(571,'Event','Interface failure.  Status poll failed.','Topology',NULL,'aswath-pt773.csez.zohocorpin.com','IF-aswath-pt773.csez.zohocorpin.com',2,1478271198384,'IF-aswath-pt773.csez.zohocorpin.com','','aswath-pt773.csez.zohocorpin.com'),(572,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'boobala-0048.csez.zohocorpin.com','boobala-0048.csez.zohocorpin.com',2,1478271198648,'boobala-0048.csez.zohocorpin.com',NULL,'boobala-0048.csez.zohocorpin.com'),(573,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'aswath-pt773.csez.zohocorpin.com','aswath-pt773.csez.zohocorpin.com',2,1478271198672,'aswath-pt773.csez.zohocorpin.com',NULL,'aswath-pt773.csez.zohocorpin.com'),(574,'Event','Interface failure.  Status poll failed.','Topology',NULL,'magesh-1870.csez.zohocorpin.com','IF-magesh-1870.csez.zohocorpin.com',2,1478271209415,'IF-magesh-1870.csez.zohocorpin.com','','magesh-1870.csez.zohocorpin.com'),(575,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'magesh-1870.csez.zohocorpin.com','magesh-1870.csez.zohocorpin.com',2,1478271209704,'magesh-1870.csez.zohocorpin.com',NULL,'magesh-1870.csez.zohocorpin.com'),(576,'Event','Interface failure.  Status poll failed.','Topology',NULL,'vaisali-4071.csez.zohocorpin.com','IF-vaisali-4071.csez.zohocorpin.com',2,1478271513153,'IF-vaisali-4071.csez.zohocorpin.com','','vaisali-4071.csez.zohocorpin.com'),(577,'Event','Interface failure.  Status poll failed.','Topology',NULL,'marutha-3402.csez.zohocorpin.com','IF-marutha-3402.csez.zohocorpin.com',2,1478271513155,'IF-marutha-3402.csez.zohocorpin.com','','marutha-3402.csez.zohocorpin.com'),(578,'Event','Interface failure.  Status poll failed.','Topology',NULL,'aravinth-3063.csez.zohocorpin.com','IF-aravinth-3063.csez.zohocorpin.com',2,1478271513156,'IF-aravinth-3063.csez.zohocorpin.com','','aravinth-3063.csez.zohocorpin.com'),(579,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'aravinth-3063.csez.zohocorpin.com','aravinth-3063.csez.zohocorpin.com',2,1478271513487,'aravinth-3063.csez.zohocorpin.com',NULL,'aravinth-3063.csez.zohocorpin.com'),(580,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'vaisali-4071.csez.zohocorpin.com','vaisali-4071.csez.zohocorpin.com',2,1478271513495,'vaisali-4071.csez.zohocorpin.com',NULL,'vaisali-4071.csez.zohocorpin.com'),(581,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'marutha-3402.csez.zohocorpin.com','marutha-3402.csez.zohocorpin.com',2,1478271513503,'marutha-3402.csez.zohocorpin.com',NULL,'marutha-3402.csez.zohocorpin.com'),(582,'Event','Interface failure.  Status poll failed.','Topology',NULL,'vijay-0596.csez.zohocorpin.com','IF-vijay-0596.csez.zohocorpin.com',2,1478271524200,'IF-vijay-0596.csez.zohocorpin.com','','vijay-0596.csez.zohocorpin.com'),(583,'Event','Interface failure.  Status poll failed.','Topology',NULL,'pavithra-3526.csez.zohocorpin.com','IF-pavithra-3526.csez.zohocorpin.com',2,1478271524205,'IF-pavithra-3526.csez.zohocorpin.com','','pavithra-3526.csez.zohocorpin.com'),(584,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'pavithra-3526.csez.zohocorpin.com','pavithra-3526.csez.zohocorpin.com',2,1478271524563,'pavithra-3526.csez.zohocorpin.com',NULL,'pavithra-3526.csez.zohocorpin.com'),(585,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'vijay-0596.csez.zohocorpin.com','vijay-0596.csez.zohocorpin.com',2,1478271524611,'vijay-0596.csez.zohocorpin.com',NULL,'vijay-0596.csez.zohocorpin.com'),(586,'Event','Interface failure.  Status poll failed.','Topology',NULL,'jerome-3929.csez.zohocorpin.com','IF-jerome-3929.csez.zohocorpin.com',2,1478271535281,'IF-jerome-3929.csez.zohocorpin.com','','jerome-3929.csez.zohocorpin.com'),(587,'Event','Interface failure.  Status poll failed.','Topology',NULL,'suresh-0665.csez.zohocorpin.com','IF-suresh-0665.csez.zohocorpin.com',2,1478271535281,'IF-suresh-0665.csez.zohocorpin.com','','suresh-0665.csez.zohocorpin.com'),(588,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'suresh-0665.csez.zohocorpin.com','suresh-0665.csez.zohocorpin.com',2,1478271535452,'suresh-0665.csez.zohocorpin.com',NULL,'suresh-0665.csez.zohocorpin.com'),(589,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'jerome-3929.csez.zohocorpin.com','jerome-3929.csez.zohocorpin.com',2,1478271535463,'jerome-3929.csez.zohocorpin.com',NULL,'jerome-3929.csez.zohocorpin.com'),(590,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.149','IF-172.24.14.149',2,1478271546299,'IF-172.24.14.149','','172.24.14.149'),(591,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.149','172.24.14.149',2,1478271546443,'172.24.14.149',NULL,'172.24.14.149'),(592,'Event','Interface failure.  Status poll failed.','Topology',NULL,'gramkumar-0817.csez.zohocorpin.com','IF-gramkumar-0817.csez.zohocorpin.com',2,1478271557395,'IF-gramkumar-0817.csez.zohocorpin.com','','gramkumar-0817.csez.zohocorpin.com'),(593,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'gramkumar-0817.csez.zohocorpin.com','gramkumar-0817.csez.zohocorpin.com',2,1478271557583,'gramkumar-0817.csez.zohocorpin.com',NULL,'gramkumar-0817.csez.zohocorpin.com'),(594,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.171','IF-172.24.14.171',2,1478271568433,'IF-172.24.14.171','','172.24.14.171'),(595,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.171','172.24.14.171',2,1478271568770,'172.24.14.171',NULL,'172.24.14.171'),(596,'Event','Interface failure.  Status poll failed.','Topology',NULL,'bhargavi-2458.csez.zohocorpin.com','IF-bhargavi-2458.csez.zohocorpin.com',2,1478271713163,'IF-bhargavi-2458.csez.zohocorpin.com','','bhargavi-2458.csez.zohocorpin.com'),(597,'Event','Interface failure.  Status poll failed.','Topology',NULL,'srivatsav-3642.csez.zohocorpin.com','IF-srivatsav-3642.csez.zohocorpin.com',2,1478271713167,'IF-srivatsav-3642.csez.zohocorpin.com','','srivatsav-3642.csez.zohocorpin.com'),(598,'Event','Interface failure.  Status poll failed.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','IF-192.168.220.202',2,1478271713171,'IF-192.168.220.202','','rejoe-0162.csez.zohocorpin.com'),(599,'Event','Interface failure.  Status poll failed.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','IF-rejoe-0162.csez.zohocorpin.com',2,1478271713175,'IF-rejoe-0162.csez.zohocorpin.com','','rejoe-0162.csez.zohocorpin.com'),(600,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','rejoe-0162.csez.zohocorpin.com',2,1478271713425,'rejoe-0162.csez.zohocorpin.com',NULL,'rejoe-0162.csez.zohocorpin.com'),(601,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'bhargavi-2458.csez.zohocorpin.com','bhargavi-2458.csez.zohocorpin.com',2,1478271713434,'bhargavi-2458.csez.zohocorpin.com',NULL,'bhargavi-2458.csez.zohocorpin.com'),(602,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'rejoe-0162.csez.zohocorpin.com','rejoe-0162.csez.zohocorpin.com',2,1478271713443,'rejoe-0162.csez.zohocorpin.com',NULL,'rejoe-0162.csez.zohocorpin.com'),(603,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'srivatsav-3642.csez.zohocorpin.com','srivatsav-3642.csez.zohocorpin.com',2,1478271713451,'srivatsav-3642.csez.zohocorpin.com',NULL,'srivatsav-3642.csez.zohocorpin.com'),(604,'Event','Interface failure.  Status poll failed.','Topology',NULL,'radhas-iphone-6.csez.zohocorpin.com','IF-radhas-iphone-6.csez.zohocorpin.com',2,1478271724210,'IF-radhas-iphone-6.csez.zohocorpin.com','','radhas-iphone-6.csez.zohocorpin.com'),(605,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.191','IF-172.24.14.191',2,1478271724213,'IF-172.24.14.191','','172.24.14.191'),(606,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'radhas-iphone-6.csez.zohocorpin.com','radhas-iphone-6.csez.zohocorpin.com',2,1478271724505,'radhas-iphone-6.csez.zohocorpin.com',NULL,'radhas-iphone-6.csez.zohocorpin.com'),(607,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.191','172.24.14.191',2,1478271724553,'172.24.14.191',NULL,'172.24.14.191'),(608,'Event','Interface failure.  Status poll failed.','Topology',NULL,'loga-zu396.csez.zohocorpin.com','IF-loga-zu396.csez.zohocorpin.com',2,1478271735277,'IF-loga-zu396.csez.zohocorpin.com','','loga-zu396.csez.zohocorpin.com'),(609,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'loga-zu396.csez.zohocorpin.com','loga-zu396.csez.zohocorpin.com',2,1478271735588,'loga-zu396.csez.zohocorpin.com',NULL,'loga-zu396.csez.zohocorpin.com'),(610,'Event','Interface failure.  Status poll failed.','Topology',NULL,'abdul-0436.csez.zohocorpin.com','IF-abdul-0436.csez.zohocorpin.com',2,1478271746277,'IF-abdul-0436.csez.zohocorpin.com','','abdul-0436.csez.zohocorpin.com'),(611,'Event','Interface failure.  Status poll failed.','Topology',NULL,'harini-zu360.csez.zohocorpin.com','IF-harini-zu360.csez.zohocorpin.com',2,1478271746328,'IF-harini-zu360.csez.zohocorpin.com','','harini-zu360.csez.zohocorpin.com'),(612,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sathish-2784.csez.zohocorpin.com','IF-sathish-2784.csez.zohocorpin.com',2,1478271746338,'IF-sathish-2784.csez.zohocorpin.com','','sathish-2784.csez.zohocorpin.com'),(613,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'abdul-0436.csez.zohocorpin.com','abdul-0436.csez.zohocorpin.com',2,1478271746575,'abdul-0436.csez.zohocorpin.com',NULL,'abdul-0436.csez.zohocorpin.com'),(614,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sathish-2784.csez.zohocorpin.com','sathish-2784.csez.zohocorpin.com',2,1478271746587,'sathish-2784.csez.zohocorpin.com',NULL,'sathish-2784.csez.zohocorpin.com'),(615,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'harini-zu360.csez.zohocorpin.com','harini-zu360.csez.zohocorpin.com',2,1478271746598,'harini-zu360.csez.zohocorpin.com',NULL,'harini-zu360.csez.zohocorpin.com'),(616,'Event','Interface failure.  Status poll failed.','Topology',NULL,'janaki-3684.csez.zohocorpin.com','IF-janaki-3684.csez.zohocorpin.com',2,1478271757329,'IF-janaki-3684.csez.zohocorpin.com','','janaki-3684.csez.zohocorpin.com'),(617,'Event','Interface failure.  Status poll failed.','Topology',NULL,'iphone-6-arivu.csez.zohocorpin.com','IF-iphone-6-arivu.csez.zohocorpin.com',2,1478271757399,'IF-iphone-6-arivu.csez.zohocorpin.com','','iphone-6-arivu.csez.zohocorpin.com'),(618,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'janaki-3684.csez.zohocorpin.com','janaki-3684.csez.zohocorpin.com',2,1478271757480,'janaki-3684.csez.zohocorpin.com',NULL,'janaki-3684.csez.zohocorpin.com'),(619,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'iphone-6-arivu.csez.zohocorpin.com','iphone-6-arivu.csez.zohocorpin.com',2,1478271757612,'iphone-6-arivu.csez.zohocorpin.com',NULL,'iphone-6-arivu.csez.zohocorpin.com'),(620,'Event','Interface failure.  Status poll failed.','Topology',NULL,'suresh-1545.csez.zohocorpin.com','IF-suresh-1545.csez.zohocorpin.com',2,1478271768371,'IF-suresh-1545.csez.zohocorpin.com','','suresh-1545.csez.zohocorpin.com'),(621,'Event','Interface failure.  Status poll failed.','Topology',NULL,'santhanapreethi.csez.zohocorpin.com','IF-santhanapreethi.csez.zohocorpin.com',2,1478271768446,'IF-santhanapreethi.csez.zohocorpin.com','','santhanapreethi.csez.zohocorpin.com'),(622,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'suresh-1545.csez.zohocorpin.com','suresh-1545.csez.zohocorpin.com',2,1478271768686,'suresh-1545.csez.zohocorpin.com',NULL,'suresh-1545.csez.zohocorpin.com'),(623,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'santhanapreethi.csez.zohocorpin.com','santhanapreethi.csez.zohocorpin.com',2,1478271768828,'santhanapreethi.csez.zohocorpin.com',NULL,'santhanapreethi.csez.zohocorpin.com'),(624,'Event','Interface failure.  Status poll failed.','Topology',NULL,'drevathy-0847.csez.zohocorpin.com','IF-drevathy-0847.csez.zohocorpin.com',2,1478272032162,'IF-drevathy-0847.csez.zohocorpin.com','','drevathy-0847.csez.zohocorpin.com'),(625,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'drevathy-0847.csez.zohocorpin.com','drevathy-0847.csez.zohocorpin.com',2,1478272032410,'drevathy-0847.csez.zohocorpin.com',NULL,'drevathy-0847.csez.zohocorpin.com'),(626,'Event','Interface failure.  Status poll failed.','Topology',NULL,'hemanth-3818.csez.zohocorpin.com','IF-hemanth-3818.csez.zohocorpin.com',2,1478272043211,'IF-hemanth-3818.csez.zohocorpin.com','','hemanth-3818.csez.zohocorpin.com'),(627,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.152','IF-172.24.14.152',2,1478272043212,'IF-172.24.14.152','','172.24.14.152'),(628,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.152','172.24.14.152',2,1478272043523,'172.24.14.152',NULL,'172.24.14.152'),(629,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'hemanth-3818.csez.zohocorpin.com','hemanth-3818.csez.zohocorpin.com',2,1478272043571,'hemanth-3818.csez.zohocorpin.com',NULL,'hemanth-3818.csez.zohocorpin.com'),(630,'Event','Interface failure.  Status poll failed.','Topology',NULL,'arivalagan-2168.csez.zohocorpin.com','IF-arivalagan-2168.csez.zohocorpin.com',2,1478272054271,'IF-arivalagan-2168.csez.zohocorpin.com','','arivalagan-2168.csez.zohocorpin.com'),(631,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'arivalagan-2168.csez.zohocorpin.com','arivalagan-2168.csez.zohocorpin.com',2,1478272054504,'arivalagan-2168.csez.zohocorpin.com',NULL,'arivalagan-2168.csez.zohocorpin.com'),(632,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.226','IF-172.24.14.226',2,1478272065339,'IF-172.24.14.226','','172.24.14.226'),(633,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.226','172.24.14.226',2,1478272065499,'172.24.14.226',NULL,'172.24.14.226'),(634,'Event','Interface failure.  Status poll failed.','Topology',NULL,'arun-3857.csez.zohocorpin.com','IF-arun-3857.csez.zohocorpin.com',2,1478272076354,'IF-arun-3857.csez.zohocorpin.com','','arun-3857.csez.zohocorpin.com'),(635,'Event','Interface failure.  Status poll failed.','Topology',NULL,'172.24.14.238','IF-172.24.14.238',2,1478272076371,'IF-172.24.14.238','','172.24.14.238'),(636,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'arun-3857.csez.zohocorpin.com','arun-3857.csez.zohocorpin.com',2,1478272076690,'arun-3857.csez.zohocorpin.com',NULL,'arun-3857.csez.zohocorpin.com'),(637,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'172.24.14.238','172.24.14.238',2,1478272076737,'172.24.14.238',NULL,'172.24.14.238'),(638,'Event','Interface failure.  Status poll failed.','Topology',NULL,'android-6d556f7960b13da.csez.zohocorpin.com','IF-android-6d556f7960b13da.csez.zohocorpin.com',2,1478272087425,'IF-android-6d556f7960b13da.csez.zohocorpin.com','','android-6d556f7960b13da.csez.zohocorpin.com'),(639,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'android-6d556f7960b13da.csez.zohocorpin.com','android-6d556f7960b13da.csez.zohocorpin.com',2,1478272087735,'android-6d556f7960b13da.csez.zohocorpin.com',NULL,'android-6d556f7960b13da.csez.zohocorpin.com'),(640,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sabarna-4236.csez.zohocorpin.com','IF-sabarna-4236.csez.zohocorpin.com',2,1478272098486,'IF-sabarna-4236.csez.zohocorpin.com','','sabarna-4236.csez.zohocorpin.com'),(641,'Event','Interface failure.  Status poll failed.','Topology',NULL,'pav.csez.zohocorpin.com','IF-pav.csez.zohocorpin.com',2,1478272098496,'IF-pav.csez.zohocorpin.com','','pav.csez.zohocorpin.com'),(642,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'pav.csez.zohocorpin.com','pav.csez.zohocorpin.com',2,1478272098751,'pav.csez.zohocorpin.com',NULL,'pav.csez.zohocorpin.com'),(643,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sabarna-4236.csez.zohocorpin.com','sabarna-4236.csez.zohocorpin.com',2,1478272098797,'sabarna-4236.csez.zohocorpin.com',NULL,'sabarna-4236.csez.zohocorpin.com'),(644,'Event','Interface failure.  Status poll failed.','Topology',NULL,'nj.csez.zohocorpin.com','IF-nj.csez.zohocorpin.com',2,1478272177158,'IF-nj.csez.zohocorpin.com','','nj.csez.zohocorpin.com'),(645,'Event','Interface failure.  Status poll failed.','Topology',NULL,'ullas-4059.csez.zohocorpin.com','IF-ullas-4059.csez.zohocorpin.com',2,1478272177158,'IF-ullas-4059.csez.zohocorpin.com','','ullas-4059.csez.zohocorpin.com'),(646,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'ullas-4059.csez.zohocorpin.com','ullas-4059.csez.zohocorpin.com',2,1478272177400,'ullas-4059.csez.zohocorpin.com',NULL,'ullas-4059.csez.zohocorpin.com'),(647,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'nj.csez.zohocorpin.com','nj.csez.zohocorpin.com',2,1478272177408,'nj.csez.zohocorpin.com',NULL,'nj.csez.zohocorpin.com'),(648,'Event','Interface failure.  Status poll failed.','Topology',NULL,'bharath-2679.csez.zohocorpin.com','IF-bharath-2679.csez.zohocorpin.com',2,1478276139183,'IF-bharath-2679.csez.zohocorpin.com','','bharath-2679.csez.zohocorpin.com'),(649,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'bharath-2679.csez.zohocorpin.com','bharath-2679.csez.zohocorpin.com',2,1478276139464,'bharath-2679.csez.zohocorpin.com',NULL,'bharath-2679.csez.zohocorpin.com'),(650,'Event','Interface failure.  Status poll failed.','Topology',NULL,'amritha-3867.csez.zohocorpin.com','IF-amritha-3867.csez.zohocorpin.com',2,1478276150249,'IF-amritha-3867.csez.zohocorpin.com','','amritha-3867.csez.zohocorpin.com'),(651,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mani-4546.csez.zohocorpin.com','IF-mani-4546.csez.zohocorpin.com',2,1478276150250,'IF-mani-4546.csez.zohocorpin.com','','mani-4546.csez.zohocorpin.com'),(652,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mani-4546.csez.zohocorpin.com','mani-4546.csez.zohocorpin.com',2,1478276150509,'mani-4546.csez.zohocorpin.com',NULL,'mani-4546.csez.zohocorpin.com'),(653,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'amritha-3867.csez.zohocorpin.com','amritha-3867.csez.zohocorpin.com',2,1478276150520,'amritha-3867.csez.zohocorpin.com',NULL,'amritha-3867.csez.zohocorpin.com'),(654,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sumanth-3304.csez.zohocorpin.com','IF-sumanth-3304.csez.zohocorpin.com',2,1478276161302,'IF-sumanth-3304.csez.zohocorpin.com','','sumanth-3304.csez.zohocorpin.com'),(655,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sumanth-3304.csez.zohocorpin.com','sumanth-3304.csez.zohocorpin.com',2,1478276161593,'sumanth-3304.csez.zohocorpin.com',NULL,'sumanth-3304.csez.zohocorpin.com'),(656,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sandeep-2.csez.zohocorpin.com','IF-sandeep-2.csez.zohocorpin.com',2,1478277371166,'IF-sandeep-2.csez.zohocorpin.com','','sandeep-2.csez.zohocorpin.com'),(657,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sandeep-2.csez.zohocorpin.com','sandeep-2.csez.zohocorpin.com',2,1478277371415,'sandeep-2.csez.zohocorpin.com',NULL,'sandeep-2.csez.zohocorpin.com'),(658,'Event','Interface failure.  Status poll failed.','Topology',NULL,'abhi-3378.csez.zohocorpin.com','IF-abhi-3378.csez.zohocorpin.com',2,1478277382203,'IF-abhi-3378.csez.zohocorpin.com','','abhi-3378.csez.zohocorpin.com'),(659,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'abhi-3378.csez.zohocorpin.com','abhi-3378.csez.zohocorpin.com',2,1478277382344,'abhi-3378.csez.zohocorpin.com',NULL,'abhi-3378.csez.zohocorpin.com'),(660,'Event','Interface failure.  Status poll failed.','Topology',NULL,'aravind-0717.csez.zohocorpin.com','IF-aravind-0717.csez.zohocorpin.com',2,1478277393263,'IF-aravind-0717.csez.zohocorpin.com','','aravind-0717.csez.zohocorpin.com'),(661,'Event','Interface failure.  Status poll failed.','Topology',NULL,'bala-2606.csez.zohocorpin.com','IF-bala-2606.csez.zohocorpin.com',2,1478277393277,'IF-bala-2606.csez.zohocorpin.com','','bala-2606.csez.zohocorpin.com'),(662,'Event','Interface failure.  Status poll failed.','Topology',NULL,'venkat-0773.csez.zohocorpin.com','IF-venkat-0773.csez.zohocorpin.com',2,1478277393278,'IF-venkat-0773.csez.zohocorpin.com','','venkat-0773.csez.zohocorpin.com'),(663,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'venkat-0773.csez.zohocorpin.com','venkat-0773.csez.zohocorpin.com',2,1478277393432,'venkat-0773.csez.zohocorpin.com',NULL,'venkat-0773.csez.zohocorpin.com'),(664,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'aravind-0717.csez.zohocorpin.com','aravind-0717.csez.zohocorpin.com',2,1478277393441,'aravind-0717.csez.zohocorpin.com',NULL,'aravind-0717.csez.zohocorpin.com'),(665,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'bala-2606.csez.zohocorpin.com','bala-2606.csez.zohocorpin.com',2,1478277393450,'bala-2606.csez.zohocorpin.com',NULL,'bala-2606.csez.zohocorpin.com'),(666,'Event','Interface failure.  Status poll failed.','Topology',NULL,'mukil-4261.csez.zohocorpin.com','IF-mukil-4261.csez.zohocorpin.com',2,1478278071180,'IF-mukil-4261.csez.zohocorpin.com','','mukil-4261.csez.zohocorpin.com'),(667,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'mukil-4261.csez.zohocorpin.com','mukil-4261.csez.zohocorpin.com',2,1478278071497,'mukil-4261.csez.zohocorpin.com',NULL,'mukil-4261.csez.zohocorpin.com'),(668,'Event','Interface failure.  Status poll failed.','Topology',NULL,'sai-3130.csez.zohocorpin.com','IF-sai-3130.csez.zohocorpin.com',2,1478278082222,'IF-sai-3130.csez.zohocorpin.com','','sai-3130.csez.zohocorpin.com'),(669,'Event','Interface failure.  Status poll failed.','Topology',NULL,'yuvaraj-1472.csez.zohocorpin.com','IF-yuvaraj-1472.csez.zohocorpin.com',2,1478278082231,'IF-yuvaraj-1472.csez.zohocorpin.com','','yuvaraj-1472.csez.zohocorpin.com'),(670,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'yuvaraj-1472.csez.zohocorpin.com','yuvaraj-1472.csez.zohocorpin.com',2,1478278082483,'yuvaraj-1472.csez.zohocorpin.com',NULL,'yuvaraj-1472.csez.zohocorpin.com'),(671,'Event','Node failure.  This probably means one or more interfaces have failed.','Topology',NULL,'sai-3130.csez.zohocorpin.com','sai-3130.csez.zohocorpin.com',2,1478278082491,'sai-3130.csez.zohocorpin.com',NULL,'sai-3130.csez.zohocorpin.com'),(672,'Event','Interface clear.  ','Topology',NULL,'guhan-3315.csez.zohocorpin.com','IF-guhan-3315.csez.zohocorpin.com',5,1478775943005,'IF-guhan-3315.csez.zohocorpin.com','','guhan-3315.csez.zohocorpin.com'),(673,'Event','Node clear.  No failures on this node.','Topology',NULL,'guhan-3315.csez.zohocorpin.com','guhan-3315.csez.zohocorpin.com',5,1478775943243,'guhan-3315.csez.zohocorpin.com',NULL,'guhan-3315.csez.zohocorpin.com'),(674,'Event','Interface clear.  ','Topology',NULL,'vineesh-3479.csez.zohocorpin.com','IF-vineesh-3479.csez.zohocorpin.com',5,1478775947836,'IF-vineesh-3479.csez.zohocorpin.com','','vineesh-3479.csez.zohocorpin.com'),(675,'Event','Node clear.  No failures on this node.','Topology',NULL,'vineesh-3479.csez.zohocorpin.com','vineesh-3479.csez.zohocorpin.com',5,1478775948012,'vineesh-3479.csez.zohocorpin.com',NULL,'vineesh-3479.csez.zohocorpin.com'),(676,'Event','Interface clear.  ','Topology',NULL,'varun-3902.csez.zohocorpin.com','IF-varun-3902.csez.zohocorpin.com',5,1478775952838,'IF-varun-3902.csez.zohocorpin.com','','varun-3902.csez.zohocorpin.com'),(677,'Event','Node clear.  No failures on this node.','Topology',NULL,'varun-3902.csez.zohocorpin.com','varun-3902.csez.zohocorpin.com',5,1478775953072,'varun-3902.csez.zohocorpin.com',NULL,'varun-3902.csez.zohocorpin.com'),(678,'Event','Interface clear.  ','Topology',NULL,'mani-4546.csez.zohocorpin.com','IF-mani-4546.csez.zohocorpin.com',5,1478775957931,'IF-mani-4546.csez.zohocorpin.com','','mani-4546.csez.zohocorpin.com'),(679,'Event','Node clear.  No failures on this node.','Topology',NULL,'mani-4546.csez.zohocorpin.com','mani-4546.csez.zohocorpin.com',5,1478775958148,'mani-4546.csez.zohocorpin.com',NULL,'mani-4546.csez.zohocorpin.com'),(680,'Event','Interface clear.  ','Topology',NULL,'bharath-2679.csez.zohocorpin.com','IF-bharath-2679.csez.zohocorpin.com',5,1478775962923,'IF-bharath-2679.csez.zohocorpin.com','','bharath-2679.csez.zohocorpin.com'),(681,'Event','Node clear.  No failures on this node.','Topology',NULL,'bharath-2679.csez.zohocorpin.com','bharath-2679.csez.zohocorpin.com',5,1478775963102,'bharath-2679.csez.zohocorpin.com',NULL,'bharath-2679.csez.zohocorpin.com'),(682,'Event','Interface clear.  ','Topology',NULL,'subha-4506.csez.zohocorpin.com','IF-subha-4506.csez.zohocorpin.com',5,1478775982838,'IF-subha-4506.csez.zohocorpin.com','','subha-4506.csez.zohocorpin.com'),(683,'Event','Node clear.  No failures on this node.','Topology',NULL,'subha-4506.csez.zohocorpin.com','subha-4506.csez.zohocorpin.com',5,1478775983060,'subha-4506.csez.zohocorpin.com',NULL,'subha-4506.csez.zohocorpin.com'),(684,'Event','Interface clear.  ','Topology',NULL,'amritha-3867.csez.zohocorpin.com','IF-amritha-3867.csez.zohocorpin.com',5,1478775987900,'IF-amritha-3867.csez.zohocorpin.com','','amritha-3867.csez.zohocorpin.com'),(685,'Event','Node clear.  No failures on this node.','Topology',NULL,'amritha-3867.csez.zohocorpin.com','amritha-3867.csez.zohocorpin.com',5,1478775988133,'amritha-3867.csez.zohocorpin.com',NULL,'amritha-3867.csez.zohocorpin.com'),(686,'Event','Interface clear.  ','Topology',NULL,'sumanth-3304.csez.zohocorpin.com','IF-sumanth-3304.csez.zohocorpin.com',5,1478775997930,'IF-sumanth-3304.csez.zohocorpin.com','','sumanth-3304.csez.zohocorpin.com'),(687,'Event','Node clear.  No failures on this node.','Topology',NULL,'sumanth-3304.csez.zohocorpin.com','sumanth-3304.csez.zohocorpin.com',5,1478775998118,'sumanth-3304.csez.zohocorpin.com',NULL,'sumanth-3304.csez.zohocorpin.com'),(688,'Event','Fault Resolution Time is more than 2 days. Value: 132.91933, Data: Average Fault Resolution Time : KeyPerformanceIndicator : Average Fault Resolution Time hours, Threshold Type: max Critical Threshold: 48.0 Critical Rearm Value: 40.0','KPI',NULL,'KeyPerformanceIndicator','KeyPerformanceIndicator:Average Fault Resolution Time hours',1,1478776007792,'KeyPerformanceIndicator',NULL,NULL),(689,'Event','Interface clear.  ','Topology',NULL,'padma-4271.csez.zohocorpin.com','IF-padma-4271.csez.zohocorpin.com',5,1478776007846,'IF-padma-4271.csez.zohocorpin.com','','padma-4271.csez.zohocorpin.com'),(690,'Event','Node clear.  No failures on this node.','Topology',NULL,'padma-4271.csez.zohocorpin.com','padma-4271.csez.zohocorpin.com',5,1478776008022,'padma-4271.csez.zohocorpin.com',NULL,'padma-4271.csez.zohocorpin.com'),(691,'Event','Interface clear.  ','Topology',NULL,'172.24.14.28','IF-172.24.14.28',5,1478776012864,'IF-172.24.14.28','','172.24.14.28'),(692,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.28','172.24.14.28',5,1478776013086,'172.24.14.28',NULL,'172.24.14.28'),(693,'Event','Interface clear.  ','Topology',NULL,'aravind-0717.csez.zohocorpin.com','IF-aravind-0717.csez.zohocorpin.com',5,1478776022869,'IF-aravind-0717.csez.zohocorpin.com','','aravind-0717.csez.zohocorpin.com'),(694,'Event','Node clear.  No failures on this node.','Topology',NULL,'aravind-0717.csez.zohocorpin.com','aravind-0717.csez.zohocorpin.com',5,1478776023185,'aravind-0717.csez.zohocorpin.com',NULL,'aravind-0717.csez.zohocorpin.com'),(695,'Event','Interface clear.  ','Topology',NULL,'venkat-0773.csez.zohocorpin.com','IF-venkat-0773.csez.zohocorpin.com',5,1478776027838,'IF-venkat-0773.csez.zohocorpin.com','','venkat-0773.csez.zohocorpin.com'),(696,'Event','Node clear.  No failures on this node.','Topology',NULL,'venkat-0773.csez.zohocorpin.com','venkat-0773.csez.zohocorpin.com',5,1478776028048,'venkat-0773.csez.zohocorpin.com',NULL,'venkat-0773.csez.zohocorpin.com'),(697,'Event','Interface clear.  ','Topology',NULL,'mani-0918.csez.zohocorpin.com','IF-mani-0918.csez.zohocorpin.com',5,1478776032958,'IF-mani-0918.csez.zohocorpin.com','','mani-0918.csez.zohocorpin.com'),(698,'Event','Node clear.  No failures on this node.','Topology',NULL,'mani-0918.csez.zohocorpin.com','mani-0918.csez.zohocorpin.com',5,1478776033191,'mani-0918.csez.zohocorpin.com',NULL,'mani-0918.csez.zohocorpin.com'),(699,'Event','Interface clear.  ','Topology',NULL,'mukil-4261.csez.zohocorpin.com','IF-mukil-4261.csez.zohocorpin.com',5,1478776052962,'IF-mukil-4261.csez.zohocorpin.com','','mukil-4261.csez.zohocorpin.com'),(700,'Event','Node clear.  No failures on this node.','Topology',NULL,'mukil-4261.csez.zohocorpin.com','mukil-4261.csez.zohocorpin.com',5,1478776053182,'mukil-4261.csez.zohocorpin.com',NULL,'mukil-4261.csez.zohocorpin.com'),(701,'Event','Interface clear.  ','Topology',NULL,'pandi-1824.csez.zohocorpin.com','IF-pandi-1824.csez.zohocorpin.com',5,1478776057954,'IF-pandi-1824.csez.zohocorpin.com','','pandi-1824.csez.zohocorpin.com'),(702,'Event','Node clear.  No failures on this node.','Topology',NULL,'pandi-1824.csez.zohocorpin.com','pandi-1824.csez.zohocorpin.com',5,1478776058132,'pandi-1824.csez.zohocorpin.com',NULL,'pandi-1824.csez.zohocorpin.com'),(703,'Event','Interface clear.  ','Topology',NULL,'sathish-1320.csez.zohocorpin.com','IF-sathish-1320.csez.zohocorpin.com',5,1478776062844,'IF-sathish-1320.csez.zohocorpin.com','','sathish-1320.csez.zohocorpin.com'),(704,'Event','Node clear.  No failures on this node.','Topology',NULL,'sathish-1320.csez.zohocorpin.com','sathish-1320.csez.zohocorpin.com',5,1478776063088,'sathish-1320.csez.zohocorpin.com',NULL,'sathish-1320.csez.zohocorpin.com'),(705,'Event','Interface clear.  ','Topology',NULL,'sai-3130.csez.zohocorpin.com','IF-sai-3130.csez.zohocorpin.com',5,1478776067840,'IF-sai-3130.csez.zohocorpin.com','','sai-3130.csez.zohocorpin.com'),(706,'Event','Node clear.  No failures on this node.','Topology',NULL,'sai-3130.csez.zohocorpin.com','sai-3130.csez.zohocorpin.com',5,1478776068075,'sai-3130.csez.zohocorpin.com',NULL,'sai-3130.csez.zohocorpin.com'),(707,'Event','Interface clear.  ','Topology',NULL,'vinodh-2312.csez.zohocorpin.com','IF-vinodh-2312.csez.zohocorpin.com',5,1478776072873,'IF-vinodh-2312.csez.zohocorpin.com','','vinodh-2312.csez.zohocorpin.com'),(708,'Event','Node clear.  No failures on this node.','Topology',NULL,'vinodh-2312.csez.zohocorpin.com','vinodh-2312.csez.zohocorpin.com',5,1478776073043,'vinodh-2312.csez.zohocorpin.com',NULL,'vinodh-2312.csez.zohocorpin.com'),(709,'Event','Interface clear.  ','Topology',NULL,'yuvaraj-1472.csez.zohocorpin.com','IF-yuvaraj-1472.csez.zohocorpin.com',5,1478776077874,'IF-yuvaraj-1472.csez.zohocorpin.com','','yuvaraj-1472.csez.zohocorpin.com'),(710,'Event','Node clear.  No failures on this node.','Topology',NULL,'yuvaraj-1472.csez.zohocorpin.com','yuvaraj-1472.csez.zohocorpin.com',5,1478776078019,'yuvaraj-1472.csez.zohocorpin.com',NULL,'yuvaraj-1472.csez.zohocorpin.com'),(711,'Event','Interface clear.  ','Topology',NULL,'172.24.14.56','IF-172.24.14.56',5,1478776082876,'IF-172.24.14.56','','172.24.14.56'),(712,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.56','172.24.14.56',5,1478776083098,'172.24.14.56',NULL,'172.24.14.56'),(713,'Event','Interface clear.  ','Topology',NULL,'ashish-4086.csez.zohocorpin.com','IF-ashish-4086.csez.zohocorpin.com',5,1478776087866,'IF-ashish-4086.csez.zohocorpin.com','','ashish-4086.csez.zohocorpin.com'),(714,'Event','Node clear.  No failures on this node.','Topology',NULL,'ashish-4086.csez.zohocorpin.com','ashish-4086.csez.zohocorpin.com',5,1478776088061,'ashish-4086.csez.zohocorpin.com',NULL,'ashish-4086.csez.zohocorpin.com'),(715,'Event','Interface clear.  ','Topology',NULL,'manimaran-1378.csez.zohocorpin.com','IF-manimaran-1378.csez.zohocorpin.com',5,1478776102897,'IF-manimaran-1378.csez.zohocorpin.com','','manimaran-1378.csez.zohocorpin.com'),(716,'Event','Node clear.  No failures on this node.','Topology',NULL,'manimaran-1378.csez.zohocorpin.com','manimaran-1378.csez.zohocorpin.com',5,1478776103142,'manimaran-1378.csez.zohocorpin.com',NULL,'manimaran-1378.csez.zohocorpin.com'),(717,'Event','Interface clear.  ','Topology',NULL,'shanmugam-2352.csez.zohocorpin.com','IF-shanmugam-2352.csez.zohocorpin.com',5,1478776107844,'IF-shanmugam-2352.csez.zohocorpin.com','','shanmugam-2352.csez.zohocorpin.com'),(718,'Event','Node clear.  No failures on this node.','Topology',NULL,'shanmugam-2352.csez.zohocorpin.com','shanmugam-2352.csez.zohocorpin.com',5,1478776108005,'shanmugam-2352.csez.zohocorpin.com',NULL,'shanmugam-2352.csez.zohocorpin.com'),(719,'Event','Interface clear.  ','Topology',NULL,'gokul-3303.csez.zohocorpin.com','IF-gokul-3303.csez.zohocorpin.com',5,1478776112846,'IF-gokul-3303.csez.zohocorpin.com','','gokul-3303.csez.zohocorpin.com'),(720,'Event','Node clear.  No failures on this node.','Topology',NULL,'gokul-3303.csez.zohocorpin.com','gokul-3303.csez.zohocorpin.com',5,1478776113069,'gokul-3303.csez.zohocorpin.com',NULL,'gokul-3303.csez.zohocorpin.com'),(721,'Event','Interface clear.  ','Topology',NULL,'arunsubhash-0371.csez.zohocorpin.com','IF-arunsubhash-0371.csez.zohocorpin.com',5,1478776117838,'IF-arunsubhash-0371.csez.zohocorpin.com','','arunsubhash-0371.csez.zohocorpin.com'),(722,'Event','Node clear.  No failures on this node.','Topology',NULL,'arunsubhash-0371.csez.zohocorpin.com','arunsubhash-0371.csez.zohocorpin.com',5,1478776118026,'arunsubhash-0371.csez.zohocorpin.com',NULL,'arunsubhash-0371.csez.zohocorpin.com'),(723,'Event','Interface clear.  ','Topology',NULL,'172.24.14.84','IF-172.24.14.84',5,1478776147851,'IF-172.24.14.84','','172.24.14.84'),(724,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.84','172.24.14.84',5,1478776148032,'172.24.14.84',NULL,'172.24.14.84'),(725,'Event','Interface clear.  ','Topology',NULL,'jsangeetha-0849.csez.zohocorpin.com','IF-jsangeetha-0849.csez.zohocorpin.com',5,1478776152843,'IF-jsangeetha-0849.csez.zohocorpin.com','','jsangeetha-0849.csez.zohocorpin.com'),(726,'Event','Node clear.  No failures on this node.','Topology',NULL,'jsangeetha-0849.csez.zohocorpin.com','jsangeetha-0849.csez.zohocorpin.com',5,1478776152993,'jsangeetha-0849.csez.zohocorpin.com',NULL,'jsangeetha-0849.csez.zohocorpin.com'),(727,'Event','Interface clear.  ','Topology',NULL,'172.24.14.92','IF-172.24.14.92',5,1478776157934,'IF-172.24.14.92','','172.24.14.92'),(728,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.92','172.24.14.92',5,1478776158057,'172.24.14.92',NULL,'172.24.14.92'),(729,'Event','Interface clear.  ','Topology',NULL,'muni-0051.csez.zohocorpin.com','IF-muni-0051.csez.zohocorpin.com',5,1478776162850,'IF-muni-0051.csez.zohocorpin.com','','muni-0051.csez.zohocorpin.com'),(730,'Event','Node clear.  No failures on this node.','Topology',NULL,'muni-0051.csez.zohocorpin.com','muni-0051.csez.zohocorpin.com',5,1478776163131,'muni-0051.csez.zohocorpin.com',NULL,'muni-0051.csez.zohocorpin.com'),(731,'Event','Interface clear.  ','Topology',NULL,'mohasin-2851.csez.zohocorpin.com','IF-mohasin-2851.csez.zohocorpin.com',5,1478776172846,'IF-mohasin-2851.csez.zohocorpin.com','','mohasin-2851.csez.zohocorpin.com'),(732,'Event','Node clear.  No failures on this node.','Topology',NULL,'mohasin-2851.csez.zohocorpin.com','mohasin-2851.csez.zohocorpin.com',5,1478776173097,'mohasin-2851.csez.zohocorpin.com',NULL,'mohasin-2851.csez.zohocorpin.com'),(733,'Event','Interface clear.  ','Topology',NULL,'raj-3842.csez.zohocorpin.com','IF-raj-3842.csez.zohocorpin.com',5,1478776177874,'IF-raj-3842.csez.zohocorpin.com','','raj-3842.csez.zohocorpin.com'),(734,'Event','Node clear.  No failures on this node.','Topology',NULL,'raj-3842.csez.zohocorpin.com','raj-3842.csez.zohocorpin.com',5,1478776178061,'raj-3842.csez.zohocorpin.com',NULL,'raj-3842.csez.zohocorpin.com'),(735,'Event','Interface clear.  ','Topology',NULL,'172.24.14.105','IF-172.24.14.105',5,1478776182933,'IF-172.24.14.105','','172.24.14.105'),(736,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.105','172.24.14.105',5,1478776183126,'172.24.14.105',NULL,'172.24.14.105'),(737,'Event','Interface clear.  ','Topology',NULL,'boobala-0048.csez.zohocorpin.com','IF-boobala-0048.csez.zohocorpin.com',5,1478776197928,'IF-boobala-0048.csez.zohocorpin.com','','boobala-0048.csez.zohocorpin.com'),(738,'Event','Node clear.  No failures on this node.','Topology',NULL,'boobala-0048.csez.zohocorpin.com','boobala-0048.csez.zohocorpin.com',5,1478776198113,'boobala-0048.csez.zohocorpin.com',NULL,'boobala-0048.csez.zohocorpin.com'),(739,'Event','Interface clear.  ','Topology',NULL,'partha-3525.csez.zohocorpin.com','IF-partha-3525.csez.zohocorpin.com',5,1478776212901,'IF-partha-3525.csez.zohocorpin.com','','partha-3525.csez.zohocorpin.com'),(740,'Event','Node clear.  No failures on this node.','Topology',NULL,'partha-3525.csez.zohocorpin.com','partha-3525.csez.zohocorpin.com',5,1478776213181,'partha-3525.csez.zohocorpin.com',NULL,'partha-3525.csez.zohocorpin.com'),(741,'Event','Interface clear.  ','Topology',NULL,'aswath-pt773.csez.zohocorpin.com','IF-aswath-pt773.csez.zohocorpin.com',5,1478776222849,'IF-aswath-pt773.csez.zohocorpin.com','','aswath-pt773.csez.zohocorpin.com'),(742,'Event','Node clear.  No failures on this node.','Topology',NULL,'aswath-pt773.csez.zohocorpin.com','aswath-pt773.csez.zohocorpin.com',5,1478776223059,'aswath-pt773.csez.zohocorpin.com',NULL,'aswath-pt773.csez.zohocorpin.com'),(743,'Event','Interface clear.  ','Topology',NULL,'magesh-1870.csez.zohocorpin.com','IF-magesh-1870.csez.zohocorpin.com',5,1478776227854,'IF-magesh-1870.csez.zohocorpin.com','','magesh-1870.csez.zohocorpin.com'),(744,'Event','Node clear.  No failures on this node.','Topology',NULL,'magesh-1870.csez.zohocorpin.com','magesh-1870.csez.zohocorpin.com',5,1478776228000,'magesh-1870.csez.zohocorpin.com',NULL,'magesh-1870.csez.zohocorpin.com'),(745,'Event','Interface clear.  ','Topology',NULL,'sandhya-3439.csez.zohocorpin.com','IF-sandhya-3439.csez.zohocorpin.com',5,1478776232877,'IF-sandhya-3439.csez.zohocorpin.com','','sandhya-3439.csez.zohocorpin.com'),(746,'Event','Node clear.  No failures on this node.','Topology',NULL,'sandhya-3439.csez.zohocorpin.com','sandhya-3439.csez.zohocorpin.com',5,1478776233144,'sandhya-3439.csez.zohocorpin.com',NULL,'sandhya-3439.csez.zohocorpin.com'),(747,'Event','Interface clear.  ','Topology',NULL,'kavitha-1061.csez.zohocorpin.com','IF-kavitha-1061.csez.zohocorpin.com',5,1478776237934,'IF-kavitha-1061.csez.zohocorpin.com','','kavitha-1061.csez.zohocorpin.com'),(748,'Event','Node clear.  No failures on this node.','Topology',NULL,'kavitha-1061.csez.zohocorpin.com','kavitha-1061.csez.zohocorpin.com',5,1478776238189,'kavitha-1061.csez.zohocorpin.com',NULL,'kavitha-1061.csez.zohocorpin.com'),(749,'Event','Interface clear.  ','Topology',NULL,'marutha-3402.csez.zohocorpin.com','IF-marutha-3402.csez.zohocorpin.com',5,1478776247846,'IF-marutha-3402.csez.zohocorpin.com','','marutha-3402.csez.zohocorpin.com'),(750,'Event','Node clear.  No failures on this node.','Topology',NULL,'marutha-3402.csez.zohocorpin.com','marutha-3402.csez.zohocorpin.com',5,1478776248162,'marutha-3402.csez.zohocorpin.com',NULL,'marutha-3402.csez.zohocorpin.com'),(751,'Event','Interface clear.  ','Topology',NULL,'vijay-0596.csez.zohocorpin.com','IF-vijay-0596.csez.zohocorpin.com',5,1478776252851,'IF-vijay-0596.csez.zohocorpin.com','','vijay-0596.csez.zohocorpin.com'),(752,'Event','Node clear.  No failures on this node.','Topology',NULL,'vijay-0596.csez.zohocorpin.com','vijay-0596.csez.zohocorpin.com',5,1478776253033,'vijay-0596.csez.zohocorpin.com',NULL,'vijay-0596.csez.zohocorpin.com'),(753,'Event','Interface clear.  ','Topology',NULL,'vaisali-4071.csez.zohocorpin.com','IF-vaisali-4071.csez.zohocorpin.com',5,1478776262853,'IF-vaisali-4071.csez.zohocorpin.com','','vaisali-4071.csez.zohocorpin.com'),(754,'Event','Node clear.  No failures on this node.','Topology',NULL,'vaisali-4071.csez.zohocorpin.com','vaisali-4071.csez.zohocorpin.com',5,1478776263005,'vaisali-4071.csez.zohocorpin.com',NULL,'vaisali-4071.csez.zohocorpin.com'),(755,'Event','Interface clear.  ','Topology',NULL,'purushoth-3454.csez.zohocorpin.com','IF-purushoth-3454.csez.zohocorpin.com',5,1478776267853,'IF-purushoth-3454.csez.zohocorpin.com','','purushoth-3454.csez.zohocorpin.com'),(756,'Event','Node clear.  No failures on this node.','Topology',NULL,'purushoth-3454.csez.zohocorpin.com','purushoth-3454.csez.zohocorpin.com',5,1478776268080,'purushoth-3454.csez.zohocorpin.com',NULL,'purushoth-3454.csez.zohocorpin.com'),(757,'Event','Interface clear.  ','Topology',NULL,'pavithra-3526.csez.zohocorpin.com','IF-pavithra-3526.csez.zohocorpin.com',5,1478776277937,'IF-pavithra-3526.csez.zohocorpin.com','','pavithra-3526.csez.zohocorpin.com'),(758,'Event','Node clear.  No failures on this node.','Topology',NULL,'pavithra-3526.csez.zohocorpin.com','pavithra-3526.csez.zohocorpin.com',5,1478776278123,'pavithra-3526.csez.zohocorpin.com',NULL,'pavithra-3526.csez.zohocorpin.com'),(759,'Event','Interface clear.  ','Topology',NULL,'raji-0675.csez.zohocorpin.com','IF-raji-0675.csez.zohocorpin.com',5,1478776292881,'IF-raji-0675.csez.zohocorpin.com','','raji-0675.csez.zohocorpin.com'),(760,'Event','Node clear.  No failures on this node.','Topology',NULL,'raji-0675.csez.zohocorpin.com','raji-0675.csez.zohocorpin.com',5,1478776293103,'raji-0675.csez.zohocorpin.com',NULL,'raji-0675.csez.zohocorpin.com'),(761,'Event','Interface clear.  ','Topology',NULL,'nirmal-2552.csez.zohocorpin.com','IF-nirmal-2552.csez.zohocorpin.com',5,1478776302884,'IF-nirmal-2552.csez.zohocorpin.com','','nirmal-2552.csez.zohocorpin.com'),(762,'Event','Node clear.  No failures on this node.','Topology',NULL,'nirmal-2552.csez.zohocorpin.com','nirmal-2552.csez.zohocorpin.com',5,1478776303064,'nirmal-2552.csez.zohocorpin.com',NULL,'nirmal-2552.csez.zohocorpin.com'),(763,'Event','Interface clear.  ','Topology',NULL,'172.24.14.171','IF-172.24.14.171',5,1478776308028,'IF-172.24.14.171','','172.24.14.171'),(764,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.171','172.24.14.171',5,1478776308216,'172.24.14.171',NULL,'172.24.14.171'),(765,'Event','Interface clear.  ','Topology',NULL,'sundari-1712.csez.zohocorpin.com','IF-sundari-1712.csez.zohocorpin.com',5,1478776317858,'IF-sundari-1712.csez.zohocorpin.com','','sundari-1712.csez.zohocorpin.com'),(766,'Event','Node clear.  No failures on this node.','Topology',NULL,'sundari-1712.csez.zohocorpin.com','sundari-1712.csez.zohocorpin.com',5,1478776318071,'sundari-1712.csez.zohocorpin.com',NULL,'sundari-1712.csez.zohocorpin.com'),(767,'Event','Interface clear.  ','Topology',NULL,'172.24.14.178','IF-172.24.14.178',5,1478776327851,'IF-172.24.14.178','','172.24.14.178'),(768,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.178','172.24.14.178',5,1478776328048,'172.24.14.178',NULL,'172.24.14.178'),(769,'Event','Interface clear.  ','Topology',NULL,'mali-0473.csez.zohocorpin.com','IF-mali-0473.csez.zohocorpin.com',5,1478776337934,'IF-mali-0473.csez.zohocorpin.com','','mali-0473.csez.zohocorpin.com'),(770,'Event','Node clear.  No failures on this node.','Topology',NULL,'mali-0473.csez.zohocorpin.com','mali-0473.csez.zohocorpin.com',5,1478776338052,'mali-0473.csez.zohocorpin.com',NULL,'mali-0473.csez.zohocorpin.com'),(771,'Event','Interface clear.  ','Topology',NULL,'srivatsav-3642.csez.zohocorpin.com','IF-srivatsav-3642.csez.zohocorpin.com',5,1478776342939,'IF-srivatsav-3642.csez.zohocorpin.com','','srivatsav-3642.csez.zohocorpin.com'),(772,'Event','Node clear.  No failures on this node.','Topology',NULL,'srivatsav-3642.csez.zohocorpin.com','srivatsav-3642.csez.zohocorpin.com',5,1478776343132,'srivatsav-3642.csez.zohocorpin.com',NULL,'srivatsav-3642.csez.zohocorpin.com'),(773,'Event','Interface clear.  ','Topology',NULL,'prameena-1188.csez.zohocorpin.com','IF-prameena-1188.csez.zohocorpin.com',5,1478776348000,'IF-prameena-1188.csez.zohocorpin.com','','prameena-1188.csez.zohocorpin.com'),(774,'Event','Node clear.  No failures on this node.','Topology',NULL,'prameena-1188.csez.zohocorpin.com','prameena-1188.csez.zohocorpin.com',5,1478776348196,'prameena-1188.csez.zohocorpin.com',NULL,'prameena-1188.csez.zohocorpin.com'),(775,'Event','Interface clear.  ','Topology',NULL,'loga-zu396.csez.zohocorpin.com','IF-loga-zu396.csez.zohocorpin.com',5,1478776367877,'IF-loga-zu396.csez.zohocorpin.com','','loga-zu396.csez.zohocorpin.com'),(776,'Event','Node clear.  No failures on this node.','Topology',NULL,'loga-zu396.csez.zohocorpin.com','loga-zu396.csez.zohocorpin.com',5,1478776368117,'loga-zu396.csez.zohocorpin.com',NULL,'loga-zu396.csez.zohocorpin.com'),(777,'Event','Interface clear.  ','Topology',NULL,'abdul-0436.csez.zohocorpin.com','IF-abdul-0436.csez.zohocorpin.com',5,1478776372912,'IF-abdul-0436.csez.zohocorpin.com','','abdul-0436.csez.zohocorpin.com'),(778,'Event','Node clear.  No failures on this node.','Topology',NULL,'abdul-0436.csez.zohocorpin.com','abdul-0436.csez.zohocorpin.com',5,1478776373177,'abdul-0436.csez.zohocorpin.com',NULL,'abdul-0436.csez.zohocorpin.com'),(779,'Event','Interface clear.  ','Topology',NULL,'sathish-2784.csez.zohocorpin.com','IF-sathish-2784.csez.zohocorpin.com',5,1478776377856,'IF-sathish-2784.csez.zohocorpin.com','','sathish-2784.csez.zohocorpin.com'),(780,'Event','Node clear.  No failures on this node.','Topology',NULL,'sathish-2784.csez.zohocorpin.com','sathish-2784.csez.zohocorpin.com',5,1478776378008,'sathish-2784.csez.zohocorpin.com',NULL,'sathish-2784.csez.zohocorpin.com'),(781,'Event','Interface clear.  ','Topology',NULL,'harini-zu360.csez.zohocorpin.com','IF-harini-zu360.csez.zohocorpin.com',5,1478776382947,'IF-harini-zu360.csez.zohocorpin.com','','harini-zu360.csez.zohocorpin.com'),(782,'Event','Node clear.  No failures on this node.','Topology',NULL,'harini-zu360.csez.zohocorpin.com','harini-zu360.csez.zohocorpin.com',5,1478776383182,'harini-zu360.csez.zohocorpin.com',NULL,'harini-zu360.csez.zohocorpin.com'),(783,'Event','Interface clear.  ','Topology',NULL,'172.24.14.67','IF-172.24.14.67',5,1478776422885,'IF-172.24.14.67','','172.24.14.67'),(784,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.67','172.24.14.67',5,1478776423056,'172.24.14.67',NULL,'172.24.14.67'),(785,'Event','Interface clear.  ','Topology',NULL,'admp-test1.csez.zohocorpin.com','IF-admp-test1.csez.zohocorpin.com',5,1478776432906,'IF-admp-test1.csez.zohocorpin.com','','admp-test1.csez.zohocorpin.com'),(786,'Event','Node clear.  No failures on this node.','Topology',NULL,'admp-test1.csez.zohocorpin.com','admp-test1.csez.zohocorpin.com',5,1478776433143,'admp-test1.csez.zohocorpin.com',NULL,'admp-test1.csez.zohocorpin.com'),(787,'Event','Interface clear.  ','Topology',NULL,'meena.csez.zohocorpin.com','IF-meena.csez.zohocorpin.com',5,1478776442893,'IF-meena.csez.zohocorpin.com','','meena.csez.zohocorpin.com'),(788,'Event','Node clear.  No failures on this node.','Topology',NULL,'meena.csez.zohocorpin.com','meena.csez.zohocorpin.com',5,1478776443116,'meena.csez.zohocorpin.com',NULL,'meena.csez.zohocorpin.com'),(789,'Event','Interface clear.  ','Topology',NULL,'172.24.14.152','IF-172.24.14.152',5,1478776493194,'IF-172.24.14.152','','172.24.14.152'),(790,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.152','172.24.14.152',5,1478776493438,'172.24.14.152',NULL,'172.24.14.152'),(791,'Event','Interface clear.  ','Topology',NULL,'arivalagan-2168.csez.zohocorpin.com','IF-arivalagan-2168.csez.zohocorpin.com',5,1478776502920,'IF-arivalagan-2168.csez.zohocorpin.com','','arivalagan-2168.csez.zohocorpin.com'),(792,'Event','Node clear.  No failures on this node.','Topology',NULL,'arivalagan-2168.csez.zohocorpin.com','arivalagan-2168.csez.zohocorpin.com',5,1478776503094,'arivalagan-2168.csez.zohocorpin.com',NULL,'arivalagan-2168.csez.zohocorpin.com'),(793,'Event','Interface clear.  ','Topology',NULL,'aravinds6splus.csez.zohocorpin.com','IF-aravinds6splus.csez.zohocorpin.com',5,1478776512973,'IF-aravinds6splus.csez.zohocorpin.com','','aravinds6splus.csez.zohocorpin.com'),(794,'Event','Node clear.  No failures on this node.','Topology',NULL,'aravinds6splus.csez.zohocorpin.com','aravinds6splus.csez.zohocorpin.com',5,1478776513264,'aravinds6splus.csez.zohocorpin.com',NULL,'aravinds6splus.csez.zohocorpin.com'),(795,'Event','Interface clear.  ','Topology',NULL,'arun-2286.csez.zohocorpin.com','IF-arun-2286.csez.zohocorpin.com',5,1478776522891,'IF-arun-2286.csez.zohocorpin.com','','arun-2286.csez.zohocorpin.com'),(796,'Event','Node clear.  No failures on this node.','Topology',NULL,'arun-2286.csez.zohocorpin.com','arun-2286.csez.zohocorpin.com',5,1478776523136,'arun-2286.csez.zohocorpin.com',NULL,'arun-2286.csez.zohocorpin.com'),(797,'Event','Interface clear.  ','Topology',NULL,'kalai-0041.csez.zohocorpin.com','IF-kalai-0041.csez.zohocorpin.com',5,1478776532853,'IF-kalai-0041.csez.zohocorpin.com','','kalai-0041.csez.zohocorpin.com'),(798,'Event','Node clear.  No failures on this node.','Topology',NULL,'kalai-0041.csez.zohocorpin.com','kalai-0041.csez.zohocorpin.com',5,1478776533152,'kalai-0041.csez.zohocorpin.com',NULL,'kalai-0041.csez.zohocorpin.com'),(799,'Event','Interface clear.  ','Topology',NULL,'172.24.14.226','IF-172.24.14.226',5,1478776542856,'IF-172.24.14.226','','172.24.14.226'),(800,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.226','172.24.14.226',5,1478776542992,'172.24.14.226',NULL,'172.24.14.226'),(801,'Event','Interface clear.  ','Topology',NULL,'172.24.14.238','IF-172.24.14.238',5,1478776562862,'IF-172.24.14.238','','172.24.14.238'),(802,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.238','172.24.14.238',5,1478776563023,'172.24.14.238',NULL,'172.24.14.238'),(803,'Event','Interface clear.  ','Topology',NULL,'arun-3857.csez.zohocorpin.com','IF-arun-3857.csez.zohocorpin.com',5,1478776582864,'IF-arun-3857.csez.zohocorpin.com','','arun-3857.csez.zohocorpin.com'),(804,'Event','Node clear.  No failures on this node.','Topology',NULL,'arun-3857.csez.zohocorpin.com','arun-3857.csez.zohocorpin.com',5,1478776583050,'arun-3857.csez.zohocorpin.com',NULL,'arun-3857.csez.zohocorpin.com'),(805,'Event','Interface clear.  ','Topology',NULL,'varun-2246.csez.zohocorpin.com','IF-varun-2246.csez.zohocorpin.com',5,1478776592864,'IF-varun-2246.csez.zohocorpin.com','','varun-2246.csez.zohocorpin.com'),(806,'Event','Interface clear.  ','Topology',NULL,'dheeraj-1090.csez.zohocorpin.com','IF-dheeraj-1090.csez.zohocorpin.com',5,1478776602914,'IF-dheeraj-1090.csez.zohocorpin.com','','dheeraj-1090.csez.zohocorpin.com'),(807,'Event','Node clear.  No failures on this node.','Topology',NULL,'dheeraj-1090.csez.zohocorpin.com','dheeraj-1090.csez.zohocorpin.com',5,1478776603126,'dheeraj-1090.csez.zohocorpin.com',NULL,'dheeraj-1090.csez.zohocorpin.com'),(808,'Event','Interface clear.  ','Topology',NULL,'pav.csez.zohocorpin.com','IF-pav.csez.zohocorpin.com',5,1478776612923,'IF-pav.csez.zohocorpin.com','','pav.csez.zohocorpin.com'),(809,'Event','Node clear.  No failures on this node.','Topology',NULL,'pav.csez.zohocorpin.com','pav.csez.zohocorpin.com',5,1478776613235,'pav.csez.zohocorpin.com',NULL,'pav.csez.zohocorpin.com'),(810,'Event','Interface clear.  ','Topology',NULL,'anu-4114.csez.zohocorpin.com','IF-anu-4114.csez.zohocorpin.com',5,1478776622954,'IF-anu-4114.csez.zohocorpin.com','','anu-4114.csez.zohocorpin.com'),(811,'Event','Node clear.  No failures on this node.','Topology',NULL,'anu-4114.csez.zohocorpin.com','anu-4114.csez.zohocorpin.com',5,1478776623185,'anu-4114.csez.zohocorpin.com',NULL,'anu-4114.csez.zohocorpin.com'),(812,'Event','Interface clear.  ','Topology',NULL,'sabarna-4236.csez.zohocorpin.com','IF-sabarna-4236.csez.zohocorpin.com',5,1478776632915,'IF-sabarna-4236.csez.zohocorpin.com','','sabarna-4236.csez.zohocorpin.com'),(813,'Event','Node clear.  No failures on this node.','Topology',NULL,'sabarna-4236.csez.zohocorpin.com','sabarna-4236.csez.zohocorpin.com',5,1478776633159,'sabarna-4236.csez.zohocorpin.com',NULL,'sabarna-4236.csez.zohocorpin.com'),(814,'Event','Interface clear.  ','Topology',NULL,'mithun-0445.csez.zohocorpin.com','IF-mithun-0445.csez.zohocorpin.com',5,1478776662915,'IF-mithun-0445.csez.zohocorpin.com','','mithun-0445.csez.zohocorpin.com'),(815,'Event','Node clear.  No failures on this node.','Topology',NULL,'mithun-0445.csez.zohocorpin.com','mithun-0445.csez.zohocorpin.com',5,1478776663138,'mithun-0445.csez.zohocorpin.com',NULL,'mithun-0445.csez.zohocorpin.com'),(816,'Event','Interface clear.  ','Topology',NULL,'ram-3371.csez.zohocorpin.com','IF-ram-3371.csez.zohocorpin.com',5,1478776672895,'IF-ram-3371.csez.zohocorpin.com','','ram-3371.csez.zohocorpin.com'),(817,'Event','Node clear.  No failures on this node.','Topology',NULL,'ram-3371.csez.zohocorpin.com','ram-3371.csez.zohocorpin.com',5,1478776673142,'ram-3371.csez.zohocorpin.com',NULL,'ram-3371.csez.zohocorpin.com'),(818,'Event','Interface clear.  ','Topology',NULL,'172.24.14.252','IF-172.24.14.252',5,1478776682955,'IF-172.24.14.252','','172.24.14.252'),(819,'Event','Node clear.  No failures on this node.','Topology',NULL,'172.24.14.252','172.24.14.252',5,1478776683258,'172.24.14.252',NULL,'172.24.14.252');
/*!40000 ALTER TABLE `Event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FAULTREPORTS_DAILY`
--

DROP TABLE IF EXISTS `FAULTREPORTS_DAILY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FAULTREPORTS_DAILY` (
  `TTIME` bigint(20) DEFAULT NULL,
  `CATEGORY` varchar(50) DEFAULT NULL,
  `SEVERITY` int(11) DEFAULT NULL,
  `SEVERITYCOUNT` int(11) DEFAULT NULL,
  `DAYS` int(11) DEFAULT NULL,
  `MONTH` int(11) DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `SOURCE` varchar(50) DEFAULT NULL,
  KEY `FAULTREPORTS_DAILY0_ndx` (`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FAULTREPORTS_DAILY`
--

LOCK TABLES `FAULTREPORTS_DAILY` WRITE;
/*!40000 ALTER TABLE `FAULTREPORTS_DAILY` DISABLE KEYS */;
INSERT INTO `FAULTREPORTS_DAILY` VALUES (1478284199000,'KPI',1,1,4,11,2016,'KeyPerformanceIndicator'),(1478284199000,'KPI',2,1,4,11,2016,'KeyPerformanceIndicator'),(1478284199000,'KPI',5,1,4,11,2016,'KeyPerformanceIndicator'),(1478284199000,'NMSManagement',1,2,4,11,2016,'BE_172.24.14.34'),(1478284199000,'NMSManagement',1,1,4,11,2016,'Client_172.24.14.34'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.0'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.105'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.112'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.115'),(1478284199000,'Topology',2,2,4,11,2016,'172.24.14.149'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.152'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.154'),(1478284199000,'Topology',2,2,4,11,2016,'172.24.14.171'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.178'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.191'),(1478284199000,'Topology',2,2,4,11,2016,'172.24.14.226'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.238'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.252'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.28'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.56'),(1478284199000,'Topology',2,2,4,11,2016,'172.24.14.67'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.84'),(1478284199000,'Topology',2,1,4,11,2016,'172.24.14.92'),(1478284199000,'Topology',2,1,4,11,2016,'abdul-0436.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'abdul-zt24.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'abhi-3378.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'admp-test1.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'ajay-1385.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'amarnath-0642.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'amritha-3867.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'android-6d556f7960b13da.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'android-6da4f6e8432f2ea.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'android-8f4bc429763adb11.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'android-d9e08dc4ead46367.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'anu-4114.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'aravind-0717.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'aravinds6splus.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'aravinth-3063.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'arivalagan-2168.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'arun-2286.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'arun-3857.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'arunsubhash-0371.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'ashish-4086.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'aswath-pt773.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'bala-2606.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'bharath-2679.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'bhargavi-2458.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'boobala-0048.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'chithu-0706.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'damodhar-1003.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'dheeraj-1090.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'drevathy-0847.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'gokul-3303.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'gramkumar-0817.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'guhan-3315.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'harini-zu360.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'hemanth-3818.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.105'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.112'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.115'),(1478284199000,'Topology',2,2,4,11,2016,'IF-172.24.14.149'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.152'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.154'),(1478284199000,'Topology',2,2,4,11,2016,'IF-172.24.14.171'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.178'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.191'),(1478284199000,'Topology',2,2,4,11,2016,'IF-172.24.14.226'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.238'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.252'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.28'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.56'),(1478284199000,'Topology',2,2,4,11,2016,'IF-172.24.14.67'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.84'),(1478284199000,'Topology',2,1,4,11,2016,'IF-172.24.14.92'),(1478284199000,'Topology',2,2,4,11,2016,'IF-192.168.220.202'),(1478284199000,'Topology',2,1,4,11,2016,'IF-abdul-0436.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-abdul-zt24.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-abhi-3378.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-admp-test1.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-ajay-1385.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-amarnath-0642.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-amritha-3867.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-android-6d556f7960b13da.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-android-6da4f6e8432f2ea.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-android-8f4bc429763adb11.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-android-d9e08dc4ead46367.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-anu-4114.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-aravind-0717.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-aravinds6splus.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-aravinth-3063.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-arivalagan-2168.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-arun-2286.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-arun-3857.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-arunsubhash-0371.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-ashish-4086.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-aswath-pt773.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-bala-2606.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-bharath-2679.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-bhargavi-2458.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-boobala-0048.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-chithu-0706.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-damodhar-1003.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-dheeraj-1090.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-drevathy-0847.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-gokul-3303.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-gramkumar-0817.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-guhan-3315.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-harini-zu360.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-hemanth-3818.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-iphone-6-arivu.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-iphone6plus565.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-iphone6usest185.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-janaki-3684.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-jerome-3929.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-jlatha-0972.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-jsangeetha-0849.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-kalai-0041.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-kavitha-1061.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-ksenthil-0949.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-loga-zu396.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-macbook-pro.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-magesh-1870.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-mali-0473.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-mani-0702.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-mani-0918.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-mani-2209.csez.zohocorpin.com'),(1478284199000,'Topology',2,3,4,11,2016,'IF-mani-4546.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-manimaran-1378.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-marutha-3402.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-meena.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-mithun-0445.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-mohasin-2851.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-mukil-4261.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-muni-0051.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-naga-3924.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-nirmal-2552.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-nj.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-padma-4271.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-pandi-1824.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-partha-3525.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-pav.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-pavithra-3526.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-prameena-1188.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-preethi2siphone.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-priya-zutk58.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-purushoth-3454.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-radhas-iphone-6.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-raj-3842.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-rajesh-2755.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-raji-0675.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-rakesh-3889.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-ram-3371.csez.zohocorpin.com'),(1478284199000,'Topology',2,3,4,11,2016,'IF-rejoe-0162.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-sabarna-4236.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-sai-3130.csez.zohocorpin.com'),(1478284199000,'Topology',2,3,4,11,2016,'IF-sandeep-2.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-sandhya-3439.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-santhanapreethi.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-sathish-1320.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-sathish-2784.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-shanmugam-2352.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-shyamallsiphone.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-siddharth-4445.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-siva-2171.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-srivatsav-3642.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-subha-4506.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-sumanth-3304.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-sundari-1712.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-suresh-0665.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-suresh-1545.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-ullas-4059.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-vaisali-4071.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-varun-2246.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-varun-3902.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-velan-es0007.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-venkat-0773.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'IF-vijay-0596.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-vineesh-3479.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-vinodh-2312.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'IF-yuvaraj-1472.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'iphone-6-arivu.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'iphone6plus565.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'iphone6usest185.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'janaki-3684.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'jerome-3929.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'jlatha-0972.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'jsangeetha-0849.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'kalai-0041.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'kavitha-1061.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'ksenthil-0949.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'loga-zu396.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'macbook-pro.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'magesh-1870.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'mali-0473.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'mani-0702.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'mani-0918.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'mani-2209.csez.zohocorpin.com'),(1478284199000,'Topology',2,3,4,11,2016,'mani-4546.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'manimaran-1378.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'marutha-3402.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'meena.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'mithun-0445.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'mohasin-2851.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'mukil-4261.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'muni-0051.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'naga-3924.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'nirmal-2552.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'nj.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'padma-4271.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'pandi-1824.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'partha-3525.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'pav.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'pavithra-3526.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'prameena-1188.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'preethi2siphone.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'priya-zutk58.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'purushoth-3454.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'radhas-iphone-6.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'raj-3842.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'rajesh-2755.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'raji-0675.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'rakesh-3889.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'ram-3371.csez.zohocorpin.com'),(1478284199000,'Topology',2,4,4,11,2016,'rejoe-0162.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'sabarna-4236.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'sai-3130.csez.zohocorpin.com'),(1478284199000,'Topology',2,3,4,11,2016,'sandeep-2.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'sandhya-3439.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'santhanapreethi.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'sathish-1320.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'sathish-2784.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'shanmugam-2352.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'shyamallsiphone.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'siddharth-4445.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'siva-2171.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'srivatsav-3642.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'subha-4506.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'sumanth-3304.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'sundari-1712.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'suresh-0665.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'suresh-1545.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'ullas-4059.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'vaisali-4071.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'varun-2246.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'varun-3902.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'velan-es0007.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'venkat-0773.csez.zohocorpin.com'),(1478284199000,'Topology',2,2,4,11,2016,'vijay-0596.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'vineesh-3479.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'vinodh-2312.csez.zohocorpin.com'),(1478284199000,'Topology',2,1,4,11,2016,'yuvaraj-1472.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'172.24.14.149'),(1478284199000,'Topology',5,1,4,11,2016,'172.24.14.171'),(1478284199000,'Topology',5,1,4,11,2016,'172.24.14.226'),(1478284199000,'Topology',5,1,4,11,2016,'172.24.14.67'),(1478284199000,'Topology',5,1,4,11,2016,'abdul-zt24.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'amarnath-0642.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'aravinds6splus.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'boobala-0048.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'gramkumar-0817.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'guhan-3315.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'hemanth-3818.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-172.24.14.149'),(1478284199000,'Topology',5,1,4,11,2016,'IF-172.24.14.171'),(1478284199000,'Topology',5,1,4,11,2016,'IF-172.24.14.226'),(1478284199000,'Topology',5,1,4,11,2016,'IF-172.24.14.67'),(1478284199000,'Topology',5,1,4,11,2016,'IF-192.168.220.202'),(1478284199000,'Topology',5,1,4,11,2016,'IF-abdul-zt24.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-amarnath-0642.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-aravinds6splus.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-boobala-0048.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-gramkumar-0817.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-guhan-3315.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-hemanth-3818.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-iphone-6-arivu.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-iphone6plus565.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-iphone6usest185.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-macbook-pro.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-mani-0702.csez.zohocorpin.com'),(1478284199000,'Topology',5,2,4,11,2016,'IF-mani-4546.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-muni-0051.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-naga-3924.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-nj.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-pav.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-priya-zutk58.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-radhas-iphone-6.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-raj-3842.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-rajesh-2755.csez.zohocorpin.com'),(1478284199000,'Topology',5,2,4,11,2016,'IF-rejoe-0162.csez.zohocorpin.com'),(1478284199000,'Topology',5,2,4,11,2016,'IF-sandeep-2.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-sathish-1320.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-sathish-2784.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-ullas-4059.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'IF-vijay-0596.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'iphone-6-arivu.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'iphone6plus565.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'iphone6usest185.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'macbook-pro.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'mani-0702.csez.zohocorpin.com'),(1478284199000,'Topology',5,2,4,11,2016,'mani-4546.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'muni-0051.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'naga-3924.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'nj.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'pav.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'priya-zutk58.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'radhas-iphone-6.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'raj-3842.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'rajesh-2755.csez.zohocorpin.com'),(1478284199000,'Topology',5,2,4,11,2016,'rejoe-0162.csez.zohocorpin.com'),(1478284199000,'Topology',5,2,4,11,2016,'sandeep-2.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'sathish-1320.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'sathish-2784.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'ullas-4059.csez.zohocorpin.com'),(1478284199000,'Topology',5,1,4,11,2016,'vijay-0596.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.0'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.105'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.112'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.115'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.115xx4'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.149'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.152'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.152xx7'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.154'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.154xx6'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.171'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.178'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.191'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.226'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.226xx12'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.238'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.238xx14'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.252'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.252xx26'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.28'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.56'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.67'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.67xx0'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.84'),(1478284199000,'Topology',6,1,4,11,2016,'172.24.14.92'),(1478284199000,'Topology',6,1,4,11,2016,'192.168.220.0'),(1478284199000,'Topology',6,1,4,11,2016,'abdul-0436.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'abdul-zt24.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'abhi-3378.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'admp-test1.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'admp-test1.csez.zohocorpin.comxx1'),(1478284199000,'Topology',6,1,4,11,2016,'ajay-1385.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'amarnath-0642.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'amritha-3867.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'android-6d556f7960b13da.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'android-6d556f7960b13da.csez.zohocorpin.comxx15'),(1478284199000,'Topology',6,1,4,11,2016,'android-6da4f6e8432f2ea.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'android-8f4bc429763adb11.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'android-8f4bc429763adb11.csez.zohocorpin.comxx13'),(1478284199000,'Topology',6,1,4,11,2016,'android-d9e08dc4ead46367.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'anu-4114.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'anu-4114.csez.zohocorpin.comxx20'),(1478284199000,'Topology',6,1,4,11,2016,'aravind-0717.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'aravinds6splus.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'aravinds6splus.csez.zohocorpin.comxx9'),(1478284199000,'Topology',6,1,4,11,2016,'aravinth-3063.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'arivalagan-2168.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'arivalagan-2168.csez.zohocorpin.comxx8'),(1478284199000,'Topology',6,1,4,11,2016,'arun-2286.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'arun-2286.csez.zohocorpin.comxx10'),(1478284199000,'Topology',6,1,4,11,2016,'arun-3857.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'arun-3857.csez.zohocorpin.comxx16'),(1478284199000,'Topology',6,1,4,11,2016,'arunsubhash-0371.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'ashish-4086.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'aswath-pt773.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'bala-2606.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'bharath-2679.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'bhargavi-2458.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'boobala-0048.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'chithu-0706.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'damodhar-1003.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'dheeraj-1090.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'dheeraj-1090.csez.zohocorpin.comxx18'),(1478284199000,'Topology',6,1,4,11,2016,'drevathy-0847.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'gokul-3303.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'gramkumar-0817.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'guhan-3315.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'harini-zu360.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'hemanth-3818.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'hemanth-3818.csez.zohocorpin.comxx5'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.105'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.112'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.115'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.149'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.152'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.154'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.171'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.178'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.191'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.226'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.238'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.252'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.28'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.56'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.67'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.84'),(1478284199000,'Topology',6,1,4,11,2016,'IF-172.24.14.92'),(1478284199000,'Topology',6,1,4,11,2016,'IF-192.168.220.202'),(1478284199000,'Topology',6,1,4,11,2016,'IF-abdul-0436.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-abdul-zt24.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-abhi-3378.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-admp-test1.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-ajay-1385.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-amarnath-0642.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-amritha-3867.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-android-6d556f7960b13da.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-android-6da4f6e8432f2ea.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-android-8f4bc429763adb11.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-android-d9e08dc4ead46367.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-anu-4114.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-aravind-0717.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-aravinds6splus.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-aravinth-3063.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-arivalagan-2168.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-arun-2286.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-arun-3857.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-arunsubhash-0371.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-ashish-4086.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-aswath-pt773.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-bala-2606.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-bharath-2679.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-bhargavi-2458.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-boobala-0048.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-chithu-0706.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-damodhar-1003.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-dheeraj-1090.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-drevathy-0847.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-gokul-3303.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-gramkumar-0817.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-guhan-3315.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-harini-zu360.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-hemanth-3818.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-iphone-6-arivu.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-iphone6plus565.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-iphone6usest185.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-janaki-3684.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-jerome-3929.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-jlatha-0972.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-jsangeetha-0849.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-kalai-0041.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-kavitha-1061.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-ksenthil-0949.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-loga-zu396.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-macbook-pro.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-magesh-1870.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-mali-0473.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-mani-0702.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-mani-0918.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-mani-2209.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-mani-4546.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-manimaran-1378.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-marutha-3402.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-meena.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-mithun-0445.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-mohasin-2851.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-mukil-4261.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-muni-0051.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-naga-3924.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-nirmal-2552.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-nj.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-padma-4271.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-pandi-1824.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-partha-3525.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-pav.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-pavithra-3526.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-prameena-1188.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-preethi2siphone.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-priya-zutk58.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-purushoth-3454.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-radhas-iphone-6.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-raj-3842.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-rajesh-2755.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-raji-0675.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-rakesh-3889.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-ram-3371.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-rejoe-0162.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-sabarna-4236.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-sai-3130.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-sandeep-2.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-sandhya-3439.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-santhanapreethi.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-sathish-1320.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-sathish-2784.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-shanmugam-2352.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-shyamallsiphone.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-siddharth-4445.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-siva-2171.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-srivatsav-3642.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-subha-4506.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-sumanth-3304.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-sundari-1712.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-suresh-0665.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-suresh-1545.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-ullas-4059.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-vaisali-4071.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-varun-2246.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-varun-3902.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-velan-es0007.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-venkat-0773.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-vijay-0596.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-vineesh-3479.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-vinodh-2312.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'IF-yuvaraj-1472.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'iphone-6-arivu.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'iphone6plus565.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'iphone6usest185.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'janaki-3684.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'jerome-3929.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'jlatha-0972.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'jsangeetha-0849.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'kalai-0041.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'kalai-0041.csez.zohocorpin.comxx11'),(1478284199000,'Topology',6,1,4,11,2016,'kavitha-1061.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'ksenthil-0949.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'loga-zu396.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'macbook-pro.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'magesh-1870.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'mali-0473.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'mani-0702.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'mani-0918.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'mani-2209.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'mani-4546.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'manimaran-1378.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'marutha-3402.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'meena.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'meena.csez.zohocorpin.comxx2'),(1478284199000,'Topology',6,1,4,11,2016,'mithun-0445.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'mithun-0445.csez.zohocorpin.comxx24'),(1478284199000,'Topology',6,1,4,11,2016,'mohasin-2851.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'mukil-4261.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'muni-0051.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'naga-3924.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'nirmal-2552.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'nj.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'nj.csez.zohocorpin.comxx23'),(1478284199000,'Topology',6,1,4,11,2016,'padma-4271.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'pandi-1824.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'partha-3525.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'pav.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'pav.csez.zohocorpin.comxx19'),(1478284199000,'Topology',6,1,4,11,2016,'pavithra-3526.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'prameena-1188.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'preethi2siphone.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'preethi2siphone.csez.zohocorpin.comxx3'),(1478284199000,'Topology',6,1,4,11,2016,'priya-zutk58.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'purushoth-3454.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'radhas-iphone-6.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'raj-3842.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'rajesh-2755.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'raji-0675.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'rakesh-3889.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'ram-3371.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'ram-3371.csez.zohocorpin.comxx25'),(1478284199000,'Topology',6,1,4,11,2016,'rejoe-0162.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'sabarna-4236.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'sabarna-4236.csez.zohocorpin.comxx21'),(1478284199000,'Topology',6,1,4,11,2016,'sai-3130.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'sandeep-2.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'sandhya-3439.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'santhanapreethi.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'sathish-1320.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'sathish-2784.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'shanmugam-2352.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'shyamallsiphone.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'siddharth-4445.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'siva-2171.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'srivatsav-3642.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'subha-4506.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'sumanth-3304.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'sundari-1712.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'suresh-0665.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'suresh-1545.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'ullas-4059.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'ullas-4059.csez.zohocorpin.comxx22'),(1478284199000,'Topology',6,1,4,11,2016,'vaisali-4071.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'varun-2246.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'varun-2246.csez.zohocorpin.comxx17'),(1478284199000,'Topology',6,1,4,11,2016,'varun-3902.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'velan-es0007.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'venkat-0773.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'vijay-0596.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'vineesh-3479.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'vinodh-2312.csez.zohocorpin.com'),(1478284199000,'Topology',6,1,4,11,2016,'yuvaraj-1472.csez.zohocorpin.com');
/*!40000 ALTER TABLE `FAULTREPORTS_DAILY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FAULTREPORTS_HOURLY`
--

DROP TABLE IF EXISTS `FAULTREPORTS_HOURLY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FAULTREPORTS_HOURLY` (
  `TTIME` bigint(20) DEFAULT NULL,
  `CATEGORY` varchar(50) DEFAULT NULL,
  `SEVERITY` int(11) DEFAULT NULL,
  `SEVERITYCOUNT` int(11) DEFAULT NULL,
  `HOURS` int(11) DEFAULT NULL,
  `SOURCE` varchar(50) DEFAULT NULL,
  KEY `FAULTREPORTS_HOURLY0_ndx` (`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FAULTREPORTS_HOURLY`
--

LOCK TABLES `FAULTREPORTS_HOURLY` WRITE;
/*!40000 ALTER TABLE `FAULTREPORTS_HOURLY` DISABLE KEYS */;
INSERT INTO `FAULTREPORTS_HOURLY` VALUES (1478777399000,'Topology',5,1,17,'IF-guhan-3315.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'guhan-3315.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-vineesh-3479.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'vineesh-3479.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-varun-3902.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'varun-3902.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-mani-4546.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'mani-4546.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-bharath-2679.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'bharath-2679.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-subha-4506.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'subha-4506.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-amritha-3867.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'amritha-3867.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-sumanth-3304.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'sumanth-3304.csez.zohocorpin.com'),(1478777399000,'KPI',1,1,17,'KeyPerformanceIndicator'),(1478777399000,'Topology',5,1,17,'IF-padma-4271.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'padma-4271.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.28'),(1478777399000,'Topology',5,1,17,'172.24.14.28'),(1478777399000,'Topology',5,1,17,'IF-aravind-0717.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'aravind-0717.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-venkat-0773.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'venkat-0773.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-mani-0918.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'mani-0918.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-mukil-4261.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'mukil-4261.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-pandi-1824.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'pandi-1824.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-sathish-1320.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'sathish-1320.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-sai-3130.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'sai-3130.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-vinodh-2312.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'vinodh-2312.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-yuvaraj-1472.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'yuvaraj-1472.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.56'),(1478777399000,'Topology',5,1,17,'172.24.14.56'),(1478777399000,'Topology',5,1,17,'IF-ashish-4086.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'ashish-4086.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-manimaran-1378.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'manimaran-1378.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-shanmugam-2352.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'shanmugam-2352.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-gokul-3303.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'gokul-3303.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-arunsubhash-0371.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'arunsubhash-0371.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.84'),(1478777399000,'Topology',5,1,17,'172.24.14.84'),(1478777399000,'Topology',5,1,17,'IF-jsangeetha-0849.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'jsangeetha-0849.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.92'),(1478777399000,'Topology',5,1,17,'172.24.14.92'),(1478777399000,'Topology',5,1,17,'IF-muni-0051.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'muni-0051.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-mohasin-2851.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'mohasin-2851.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-raj-3842.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'raj-3842.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.105'),(1478777399000,'Topology',5,1,17,'172.24.14.105'),(1478777399000,'Topology',5,1,17,'IF-boobala-0048.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'boobala-0048.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-partha-3525.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'partha-3525.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-aswath-pt773.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'aswath-pt773.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-magesh-1870.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'magesh-1870.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-sandhya-3439.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'sandhya-3439.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-kavitha-1061.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'kavitha-1061.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-marutha-3402.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'marutha-3402.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-vijay-0596.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'vijay-0596.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-vaisali-4071.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'vaisali-4071.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-purushoth-3454.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'purushoth-3454.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-pavithra-3526.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'pavithra-3526.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-raji-0675.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'raji-0675.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-nirmal-2552.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'nirmal-2552.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.171'),(1478777399000,'Topology',5,1,17,'172.24.14.171'),(1478777399000,'Topology',5,1,17,'IF-sundari-1712.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'sundari-1712.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.178'),(1478777399000,'Topology',5,1,17,'172.24.14.178'),(1478777399000,'Topology',5,1,17,'IF-mali-0473.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'mali-0473.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-srivatsav-3642.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'srivatsav-3642.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-prameena-1188.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'prameena-1188.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-loga-zu396.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'loga-zu396.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-abdul-0436.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'abdul-0436.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-sathish-2784.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'sathish-2784.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-harini-zu360.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'harini-zu360.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.67'),(1478777399000,'Topology',5,1,17,'172.24.14.67'),(1478777399000,'Topology',5,1,17,'IF-admp-test1.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'admp-test1.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-meena.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'meena.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.152'),(1478777399000,'Topology',5,1,17,'172.24.14.152'),(1478777399000,'Topology',5,1,17,'IF-arivalagan-2168.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'arivalagan-2168.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-aravinds6splus.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'aravinds6splus.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-arun-2286.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'arun-2286.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-kalai-0041.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'kalai-0041.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.226'),(1478777399000,'Topology',5,1,17,'172.24.14.226'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.238'),(1478777399000,'Topology',5,1,17,'172.24.14.238'),(1478777399000,'Topology',5,1,17,'IF-arun-3857.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'arun-3857.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-varun-2246.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-dheeraj-1090.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'dheeraj-1090.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-pav.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'pav.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-anu-4114.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'anu-4114.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-sabarna-4236.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'sabarna-4236.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-mithun-0445.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'mithun-0445.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-ram-3371.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'ram-3371.csez.zohocorpin.com'),(1478777399000,'Topology',5,1,17,'IF-172.24.14.252'),(1478777399000,'Topology',5,1,17,'172.24.14.252');
/*!40000 ALTER TABLE `FAULTREPORTS_HOURLY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FilterCommandAlertAction`
--

DROP TABLE IF EXISTS `FilterCommandAlertAction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FilterCommandAlertAction` (
  `ID` int(11) NOT NULL,
  `COMMANDPROPS` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FilterCommandAlertAction`
--

LOCK TABLES `FilterCommandAlertAction` WRITE;
/*!40000 ALTER TABLE `FilterCommandAlertAction` DISABLE KEYS */;
/*!40000 ALTER TABLE `FilterCommandAlertAction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FilterCommandEventAction`
--

DROP TABLE IF EXISTS `FilterCommandEventAction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FilterCommandEventAction` (
  `ID` int(11) NOT NULL,
  `COMMANDPROPS` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FilterCommandEventAction`
--

LOCK TABLES `FilterCommandEventAction` WRITE;
/*!40000 ALTER TABLE `FilterCommandEventAction` DISABLE KEYS */;
/*!40000 ALTER TABLE `FilterCommandEventAction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GMapSymbol`
--

DROP TABLE IF EXISTS `GMapSymbol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GMapSymbol` (
  `NAME` varchar(100) NOT NULL,
  `MAPNAME` varchar(100) NOT NULL,
  `LATITUDE` double DEFAULT NULL,
  `LONGITUDE` double DEFAULT NULL,
  PRIMARY KEY (`NAME`,`MAPNAME`),
  KEY `FK6E7E118DB80FB2C3` (`NAME`,`MAPNAME`),
  CONSTRAINT `FK6E7E118DB80FB2C3` FOREIGN KEY (`NAME`, `MAPNAME`) REFERENCES `MapSymbol` (`NAME`, `MAPNAME`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GMapSymbol`
--

LOCK TABLES `GMapSymbol` WRITE;
/*!40000 ALTER TABLE `GMapSymbol` DISABLE KEYS */;
/*!40000 ALTER TABLE `GMapSymbol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GroupTable`
--

DROP TABLE IF EXISTS `GroupTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GroupTable` (
  `NAME` varchar(255) NOT NULL,
  `MEMBERNAME` varchar(255) NOT NULL,
  PRIMARY KEY (`MEMBERNAME`,`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GroupTable`
--

LOCK TABLES `GroupTable` WRITE;
/*!40000 ALTER TABLE `GroupTable` DISABLE KEYS */;
/*!40000 ALTER TABLE `GroupTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `HOSTS`
--

DROP TABLE IF EXISTS `HOSTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HOSTS` (
  `HOSTNAMES` varchar(100) NOT NULL,
  PRIMARY KEY (`HOSTNAMES`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `HOSTS`
--

LOCK TABLES `HOSTS` WRITE;
/*!40000 ALTER TABLE `HOSTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `HOSTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IpAddress`
--

DROP TABLE IF EXISTS `IpAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IpAddress` (
  `MOID` bigint(20) NOT NULL,
  `PARENTNET` varchar(100) DEFAULT NULL,
  `PARENTNODE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FKD8D77CAD7825E164` (`MOID`),
  CONSTRAINT `FKD8D77CAD7825E164` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IpAddress`
--

LOCK TABLES `IpAddress` WRITE;
/*!40000 ALTER TABLE `IpAddress` DISABLE KEYS */;
INSERT INTO `IpAddress` VALUES (2,'172.24.14.0','guhan-3315.csez.zohocorpin.com'),(4,'172.24.14.0','vineesh-3479.csez.zohocorpin.com'),(6,'172.24.14.0','varun-3902.csez.zohocorpin.com'),(8,'172.24.14.0','mani-4546.csez.zohocorpin.com'),(10,'172.24.14.0','bharath-2679.csez.zohocorpin.com'),(12,'172.24.14.0','iphone6usest185.csez.zohocorpin.com'),(14,'172.24.14.0','iphone6plus565.csez.zohocorpin.com'),(16,'172.24.14.0','rakesh-3889.csez.zohocorpin.com'),(18,'172.24.14.0','subha-4506.csez.zohocorpin.com'),(20,'172.24.14.0','amritha-3867.csez.zohocorpin.com'),(22,'172.24.14.0','sandeep-2.csez.zohocorpin.com'),(24,'172.24.14.0','sumanth-3304.csez.zohocorpin.com'),(26,'172.24.14.0','ksenthil-0949.csez.zohocorpin.com'),(28,'172.24.14.0','padma-4271.csez.zohocorpin.com'),(30,'172.24.14.0','172.24.14.28'),(32,'172.24.14.0','abhi-3378.csez.zohocorpin.com'),(34,'172.24.14.0','aravind-0717.csez.zohocorpin.com'),(36,'172.24.14.0','venkat-0773.csez.zohocorpin.com'),(38,'172.24.14.0','mani-0918.csez.zohocorpin.com'),(40,'172.24.14.0','bala-2606.csez.zohocorpin.com'),(43,'172.24.14.0','rejoe-0162.csez.zohocorpin.com'),(44,'192.168.220.0','rejoe-0162.csez.zohocorpin.com'),(46,'172.24.14.0','mukil-4261.csez.zohocorpin.com'),(48,'172.24.14.0','pandi-1824.csez.zohocorpin.com'),(50,'172.24.14.0','sathish-1320.csez.zohocorpin.com'),(52,'172.24.14.0','sai-3130.csez.zohocorpin.com'),(54,'172.24.14.0','vinodh-2312.csez.zohocorpin.com'),(56,'172.24.14.0','yuvaraj-1472.csez.zohocorpin.com'),(58,'172.24.14.0','172.24.14.56'),(60,'172.24.14.0','ashish-4086.csez.zohocorpin.com'),(62,'172.24.14.0','naga-3924.csez.zohocorpin.com'),(64,'172.24.14.0','priya-zutk58.csez.zohocorpin.com'),(66,'172.24.14.0','manimaran-1378.csez.zohocorpin.com'),(68,'172.24.14.0','shanmugam-2352.csez.zohocorpin.com'),(70,'172.24.14.0','gokul-3303.csez.zohocorpin.com'),(72,'172.24.14.0','arunsubhash-0371.csez.zohocorpin.com'),(74,'172.24.14.0','mani-0702.csez.zohocorpin.com'),(76,'172.24.14.0','rajesh-2755.csez.zohocorpin.com'),(78,'172.24.14.0','abdul-zt24.csez.zohocorpin.com'),(80,'172.24.14.0','mani-2209.csez.zohocorpin.com'),(82,'172.24.14.0','macbook-pro.csez.zohocorpin.com'),(84,'172.24.14.0','172.24.14.84'),(86,'172.24.14.0','jsangeetha-0849.csez.zohocorpin.com'),(88,'172.24.14.0','172.24.14.92'),(90,'172.24.14.0','muni-0051.csez.zohocorpin.com'),(92,'172.24.14.0','android-d9e08dc4ead46367.csez.zohocorpin.com'),(94,'172.24.14.0','mohasin-2851.csez.zohocorpin.com'),(96,'172.24.14.0','raj-3842.csez.zohocorpin.com'),(98,'172.24.14.0','172.24.14.105'),(100,'172.24.14.0','172.24.14.112'),(102,'172.24.14.0','siva-2171.csez.zohocorpin.com'),(104,'172.24.14.0','boobala-0048.csez.zohocorpin.com'),(106,'172.24.14.0','jlatha-0972.csez.zohocorpin.com'),(108,'172.24.14.0','amarnath-0642.csez.zohocorpin.com'),(110,'172.24.14.0','partha-3525.csez.zohocorpin.com'),(112,'172.24.14.0','chithu-0706.csez.zohocorpin.com'),(114,'172.24.14.0','aswath-pt773.csez.zohocorpin.com'),(116,'172.24.14.0','magesh-1870.csez.zohocorpin.com'),(118,'172.24.14.0','sandhya-3439.csez.zohocorpin.com'),(120,'172.24.14.0','kavitha-1061.csez.zohocorpin.com'),(122,'172.24.14.0','shyamallsiphone.csez.zohocorpin.com'),(124,'172.24.14.0','marutha-3402.csez.zohocorpin.com'),(126,'172.24.14.0','vijay-0596.csez.zohocorpin.com'),(128,'172.24.14.0','aravinth-3063.csez.zohocorpin.com'),(130,'172.24.14.0','vaisali-4071.csez.zohocorpin.com'),(132,'172.24.14.0','purushoth-3454.csez.zohocorpin.com'),(134,'172.24.14.0','velan-es0007.csez.zohocorpin.com'),(136,'172.24.14.0','pavithra-3526.csez.zohocorpin.com'),(138,'172.24.14.0','jerome-3929.csez.zohocorpin.com'),(140,'172.24.14.0','172.24.14.149'),(142,'172.24.14.0','raji-0675.csez.zohocorpin.com'),(144,'172.24.14.0','suresh-0665.csez.zohocorpin.com'),(146,'172.24.14.0','nirmal-2552.csez.zohocorpin.com'),(148,'172.24.14.0','172.24.14.171'),(150,'172.24.14.0','gramkumar-0817.csez.zohocorpin.com'),(152,'172.24.14.0','sundari-1712.csez.zohocorpin.com'),(154,'172.24.14.0','radhas-iphone-6.csez.zohocorpin.com'),(156,'172.24.14.0','172.24.14.178'),(158,'172.24.14.0','bhargavi-2458.csez.zohocorpin.com'),(160,'172.24.14.0','mali-0473.csez.zohocorpin.com'),(162,'172.24.14.0','srivatsav-3642.csez.zohocorpin.com'),(164,'172.24.14.0','prameena-1188.csez.zohocorpin.com'),(166,'172.24.14.0','android-6da4f6e8432f2ea.csez.zohocorpin.com'),(168,'172.24.14.0','172.24.14.191'),(170,'172.24.14.0','iphone-6-arivu.csez.zohocorpin.com'),(172,'172.24.14.0','loga-zu396.csez.zohocorpin.com'),(174,'172.24.14.0','abdul-0436.csez.zohocorpin.com'),(176,'172.24.14.0','sathish-2784.csez.zohocorpin.com'),(178,'172.24.14.0','harini-zu360.csez.zohocorpin.com'),(180,'172.24.14.0','siddharth-4445.csez.zohocorpin.com'),(182,'172.24.14.0','janaki-3684.csez.zohocorpin.com'),(184,'172.24.14.0','damodhar-1003.csez.zohocorpin.com'),(186,'172.24.14.0','suresh-1545.csez.zohocorpin.com'),(188,'172.24.14.0','santhanapreethi.csez.zohocorpin.com'),(190,'172.24.14.0','ajay-1385.csez.zohocorpin.com'),(192,'172.24.14.0','drevathy-0847.csez.zohocorpin.com'),(194,'172.24.14.0','172.24.14.67'),(197,'172.24.14.0','admp-test1.csez.zohocorpin.com'),(200,'172.24.14.0','meena.csez.zohocorpin.com'),(203,'172.24.14.0','preethi2siphone.csez.zohocorpin.com'),(206,'172.24.14.0','172.24.14.115'),(209,'172.24.14.0','hemanth-3818.csez.zohocorpin.com'),(212,'172.24.14.0','172.24.14.154'),(215,'172.24.14.0','172.24.14.152'),(218,'172.24.14.0','arivalagan-2168.csez.zohocorpin.com'),(221,'172.24.14.0','aravinds6splus.csez.zohocorpin.com'),(224,'172.24.14.0','arun-2286.csez.zohocorpin.com'),(227,'172.24.14.0','kalai-0041.csez.zohocorpin.com'),(230,'172.24.14.0','172.24.14.226'),(233,'172.24.14.0','android-8f4bc429763adb11.csez.zohocorpin.com'),(236,'172.24.14.0','172.24.14.238'),(239,'172.24.14.0','android-6d556f7960b13da.csez.zohocorpin.com'),(242,'172.24.14.0','arun-3857.csez.zohocorpin.com'),(245,'172.24.14.0','varun-2246.csez.zohocorpin.com'),(248,'172.24.14.0','dheeraj-1090.csez.zohocorpin.com'),(251,'172.24.14.0','pav.csez.zohocorpin.com'),(254,'172.24.14.0','anu-4114.csez.zohocorpin.com'),(257,'172.24.14.0','sabarna-4236.csez.zohocorpin.com'),(260,'172.24.14.0','ullas-4059.csez.zohocorpin.com'),(263,'172.24.14.0','nj.csez.zohocorpin.com'),(266,'172.24.14.0','mithun-0445.csez.zohocorpin.com'),(269,'172.24.14.0','ram-3371.csez.zohocorpin.com'),(272,'172.24.14.0','172.24.14.252');
/*!40000 ALTER TABLE `IpAddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPICustomDS`
--

DROP TABLE IF EXISTS `KPICustomDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPICustomDS` (
  `SUBCOMPID` bigint(20) NOT NULL,
  `DSCLASSNAME` varchar(200) DEFAULT NULL,
  `DSUSERPROPS` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`SUBCOMPID`),
  KEY `FK65733F64B72CEF94` (`SUBCOMPID`),
  CONSTRAINT `FK65733F64B72CEF94` FOREIGN KEY (`SUBCOMPID`) REFERENCES `KPISubComputation` (`SUBCOMPID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPICustomDS`
--

LOCK TABLES `KPICustomDS` WRITE;
/*!40000 ALTER TABLE `KPICustomDS` DISABLE KEYS */;
/*!40000 ALTER TABLE `KPICustomDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPIDeviceDS`
--

DROP TABLE IF EXISTS `KPIDeviceDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPIDeviceDS` (
  `SUBCOMPID` bigint(20) NOT NULL,
  `DEVICELISTNAME` varchar(100) DEFAULT NULL,
  `PROTOCOL` varchar(200) DEFAULT NULL,
  `DEVICECOMPEXPR` varchar(200) DEFAULT NULL,
  `INSTAGGROVERTIME` varchar(100) DEFAULT NULL,
  `ROWSCOUNT` varchar(200) DEFAULT NULL,
  `RETRIES` bigint(20) DEFAULT NULL,
  `FETCHRANGE` varchar(200) DEFAULT NULL,
  `PDTYPE` varchar(100) DEFAULT NULL,
  `INSTAGGROVERMULTIPLEINSTANCES` varchar(100) DEFAULT NULL,
  `STATSDATATABLENAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SUBCOMPID`),
  KEY `FK836EAD09D5285D39` (`SUBCOMPID`),
  CONSTRAINT `FK836EAD09D5285D39` FOREIGN KEY (`SUBCOMPID`) REFERENCES `KPISubComputation` (`SUBCOMPID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPIDeviceDS`
--

LOCK TABLES `KPIDeviceDS` WRITE;
/*!40000 ALTER TABLE `KPIDeviceDS` DISABLE KEYS */;
INSERT INTO `KPIDeviceDS` VALUES (7,'SwitchList','SNMP','(.1.3.6.1.2.1.2.2.1.11+.1.3.6.1.2.1.2.2.1.12+.1.3.6.1.2.1.2.2.1.17+.1.3.6.1.2.1.2.2.1.18)/$DELTA_TIME','sum',NULL,3,'LATEST','MultiplePolledData','sum','STATSDATA%');
/*!40000 ALTER TABLE `KPIDeviceDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPIDeviceList`
--

DROP TABLE IF EXISTS `KPIDeviceList`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPIDeviceList` (
  `DEVICELISTID` bigint(20) NOT NULL,
  `DISCRIMINATOR` varchar(30) NOT NULL,
  `DEVICELISTNAME` varchar(100) NOT NULL,
  PRIMARY KEY (`DEVICELISTID`),
  UNIQUE KEY `DEVICELISTNAME` (`DEVICELISTNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPIDeviceList`
--

LOCK TABLES `KPIDeviceList` WRITE;
/*!40000 ALTER TABLE `KPIDeviceList` DISABLE KEYS */;
INSERT INTO `KPIDeviceList` VALUES (1,'KPIDeviceList','SwitchList'),(2,'KPIDeviceList','NodeList');
/*!40000 ALTER TABLE `KPIDeviceList` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPIObject`
--

DROP TABLE IF EXISTS `KPIObject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPIObject` (
  `KPIID` bigint(20) NOT NULL,
  `DISCRIMINATOR` varchar(30) NOT NULL,
  `KPINAME` varchar(150) NOT NULL,
  `KPIDESCRIPTION` varchar(250) DEFAULT NULL,
  `FORMULA` varchar(250) DEFAULT NULL,
  `KPIOWNER` varchar(100) DEFAULT NULL,
  `KPIUNIT` varchar(100) DEFAULT NULL,
  `KPICATEGORY` varchar(100) DEFAULT NULL,
  `KPITYPE` varchar(50) DEFAULT NULL,
  `SCHEDULETYPE` int(11) DEFAULT NULL,
  `SCHEDULEVALUES` varchar(200) DEFAULT NULL,
  `THRESHOLD` varchar(200) DEFAULT NULL,
  `ISACTIVE` bit(1) DEFAULT NULL,
  PRIMARY KEY (`KPIID`),
  UNIQUE KEY `KPINAME` (`KPINAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPIObject`
--

LOCK TABLES `KPIObject` WRITE;
/*!40000 ALTER TABLE `KPIObject` DISABLE KEYS */;
INSERT INTO `KPIObject` VALUES (1,'KPIObject','Network Availability','Percentage availability of all managed nodes in the network.','(((ManagedNodes)-(UnAvailableNodes))/(ManagedNodes))*100','root','%','KPI','PERCENTAGE',1,'300','Network-Avl-Crit,Network-Avl-Major',''),(2,'KPIObject','Network Pkt Errors','Percentage of packets droped in the network due to Errors with respect to the total number of packets transmitted.','(((Switch_TxErrors) + (Switch_RxErrors))/(Switch_TxPackets))*100','root','%','KPI','PERCENTAGE',1,'300','Network-Loss',''),(3,'KPIObject','Network Pkt Discards','Percentage of packets discarded in the network with respect to the total number of packets transmitted.','(((Switch_InDiscards) + (Switch_OutDiscards))/(Switch_TxPackets))*100','root','%','KPI','PERCENTAGE',1,'300','Network-Loss',''),(4,'KPIObject','Network Packet Loss','Average Packet Loss (both due to Errors and Discards) of all Network interfaces in the managed networks.','((Interface_TxErrors)+(Interface_RxErrors)+(Interface_InDiscards)+(Interface_OutDiscards))/4','root','packets','KPI','NUMBER',1,'300','',''),(5,'KPIObject','Average Fault Resolution Time','Average time that elapsed from an alert being raised to the alert being cleared represented in hours.','(Alarm_Resolution_Time)/3600','root','hours','KPI','NUMBER',1,'300','Fault-Res-Crit,Fault-Res-Major',''),(6,'KPIObject','Fault Resolution Time Variance','Average variance from mean Fault Resolution Time for all alerts measured in hours.            Higher variance indicates that actual Fault Resolution Time deviates from the mean resolution time in most instances.','(Alarm_Resolution_Variance)/3600','root','hours','KPI','NUMBER',1,'300','','');
/*!40000 ALTER TABLE `KPIObject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPIPollDS`
--

DROP TABLE IF EXISTS `KPIPollDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPIPollDS` (
  `SUBCOMPID` bigint(20) NOT NULL,
  `DEVICELISTNAME` varchar(100) DEFAULT NULL,
  `DEVICECOMPEXPR` varchar(200) DEFAULT NULL,
  `INSTAGGROVERTIME` varchar(100) DEFAULT NULL,
  `FETCHRANGE` varchar(200) DEFAULT NULL,
  `PDTYPE` varchar(100) DEFAULT NULL,
  `INSTAGGROVERMULTIPLEINSTANCES` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SUBCOMPID`),
  KEY `FK8AE409326AC762E2` (`SUBCOMPID`),
  CONSTRAINT `FK8AE409326AC762E2` FOREIGN KEY (`SUBCOMPID`) REFERENCES `KPISubComputation` (`SUBCOMPID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPIPollDS`
--

LOCK TABLES `KPIPollDS` WRITE;
/*!40000 ALTER TABLE `KPIPollDS` DISABLE KEYS */;
INSERT INTO `KPIPollDS` VALUES (3,'SwitchList','.1.3.6.1.2.1.2.2.1.20/$DELTA_TIME','sum','LATEST','MultiplePolledData','avg'),(4,'SwitchList','.1.3.6.1.2.1.2.2.1.14/$DELTA_TIME','sum','LATEST','MultiplePolledData','avg'),(5,'SwitchList','.1.3.6.1.2.1.2.2.1.13/$DELTA_TIME','sum','LATEST','MultiplePolledData','avg'),(6,'SwitchList','.1.3.6.1.2.1.2.2.1.19/$DELTA_TIME','sum','LATEST','MultiplePolledData','avg'),(8,'NodeList','.1.3.6.1.2.1.2.2.1.20/$DELTA_TIME','sum','LATEST','MultiplePolledData','avg'),(9,'NodeList','.1.3.6.1.2.1.2.2.1.14/$DELTA_TIME','sum','LATEST','MultiplePolledData','avg'),(10,'NodeList','.1.3.6.1.2.1.2.2.1.13/$DELTA_TIME','sum','LATEST','MultiplePolledData','avg'),(11,'NodeList','.1.3.6.1.2.1.2.2.1.19/$DELTA_TIME','sum','LATEST','MultiplePolledData','avg');
/*!40000 ALTER TABLE `KPIPollDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPIPolledData`
--

DROP TABLE IF EXISTS `KPIPolledData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPIPolledData` (
  `ID` bigint(20) NOT NULL,
  `KPIOBJNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK33D8000CD723312D` (`ID`),
  CONSTRAINT `FK33D8000CD723312D` FOREIGN KEY (`ID`) REFERENCES `PolledData` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPIPolledData`
--

LOCK TABLES `KPIPolledData` WRITE;
/*!40000 ALTER TABLE `KPIPolledData` DISABLE KEYS */;
INSERT INTO `KPIPolledData` VALUES (1,'Network Availability'),(2,'Network Pkt Errors'),(3,'Network Pkt Discards'),(4,'Network Packet Loss'),(5,'Average Fault Resolution Time'),(6,'Fault Resolution Time Variance');
/*!40000 ALTER TABLE `KPIPolledData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPISqlDS`
--

DROP TABLE IF EXISTS `KPISqlDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPISqlDS` (
  `SUBCOMPID` bigint(20) NOT NULL,
  `DBCONNECTSTRING` varchar(200) DEFAULT NULL,
  `DBUSERNAME` varchar(100) DEFAULT NULL,
  `DBPASSWORD` varchar(100) DEFAULT NULL,
  `QUERY` varchar(200) DEFAULT NULL,
  `SQLCOMPEXPR` varchar(200) DEFAULT NULL,
  `QUERYRETURNTYPE` varchar(50) DEFAULT NULL,
  `ROWSCOUNT` int(11) DEFAULT NULL,
  PRIMARY KEY (`SUBCOMPID`),
  KEY `FK784309B9B4F03C9D` (`SUBCOMPID`),
  CONSTRAINT `FK784309B9B4F03C9D` FOREIGN KEY (`SUBCOMPID`) REFERENCES `KPISubComputation` (`SUBCOMPID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPISqlDS`
--

LOCK TABLES `KPISqlDS` WRITE;
/*!40000 ALTER TABLE `KPISqlDS` DISABLE KEYS */;
INSERT INTO `KPISqlDS` VALUES (1,NULL,NULL,NULL,'select count(*) from ManagedObject where managed=true and moid in (SELECT moid FROM TopoObject where isnode=true)','(query*1)','int',1),(2,NULL,NULL,NULL,'select count(*) from ManagedObject where status < 3 and managed=true and moid in (SELECT moid FROM TopoObject where isnode=true)','(1*query)','int',1),(12,NULL,NULL,NULL,'select (modtime-createtime) from Alert where severity=5','(query/1000)','long',1000),(13,NULL,NULL,NULL,'select (modtime-createtime) from Alert where severity=5','(query/1000)','long',1000);
/*!40000 ALTER TABLE `KPISqlDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPISubComputation`
--

DROP TABLE IF EXISTS `KPISubComputation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPISubComputation` (
  `SUBCOMPID` bigint(20) NOT NULL,
  `DISCRIMINATOR` varchar(30) NOT NULL,
  `SUBCOMPNAME` varchar(100) NOT NULL,
  `DATASOURCETYPE` varchar(100) DEFAULT NULL,
  `VALUEAGGREGATION` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SUBCOMPID`),
  UNIQUE KEY `SUBCOMPNAME` (`SUBCOMPNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPISubComputation`
--

LOCK TABLES `KPISubComputation` WRITE;
/*!40000 ALTER TABLE `KPISubComputation` DISABLE KEYS */;
INSERT INTO `KPISubComputation` VALUES (1,'KPISqlDS','ManagedNodes','SQL','avg'),(2,'KPISqlDS','UnAvailableNodes','SQL','avg'),(3,'KPIPollDS','Switch_TxErrors','POLL','avg'),(4,'KPIPollDS','Switch_RxErrors','POLL','avg'),(5,'KPIPollDS','Switch_InDiscards','POLL','avg'),(6,'KPIPollDS','Switch_OutDiscards','POLL','avg'),(7,'KPIDeviceDS','Switch_TxPackets','DEVICE','avg'),(8,'KPIPollDS','Interface_TxErrors','POLL','avg'),(9,'KPIPollDS','Interface_RxErrors','POLL','avg'),(10,'KPIPollDS','Interface_InDiscards','POLL','avg'),(11,'KPIPollDS','Interface_OutDiscards','POLL','avg'),(12,'KPISqlDS','Alarm_Resolution_Time','SQL','avg'),(13,'KPISqlDS','Alarm_Resolution_Variance','SQL','variance');
/*!40000 ALTER TABLE `KPISubComputation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPITOSUBCOMPUTATION`
--

DROP TABLE IF EXISTS `KPITOSUBCOMPUTATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPITOSUBCOMPUTATION` (
  `subCompId` bigint(20) NOT NULL,
  `KPIID` bigint(20) NOT NULL,
  PRIMARY KEY (`KPIID`,`subCompId`),
  KEY `FK62B67A46633F77BB` (`subCompId`),
  KEY `FK62B67A466A696AA8` (`KPIID`),
  CONSTRAINT `FK62B67A46633F77BB` FOREIGN KEY (`subCompId`) REFERENCES `KPISubComputation` (`SUBCOMPID`),
  CONSTRAINT `FK62B67A466A696AA8` FOREIGN KEY (`KPIID`) REFERENCES `KPIObject` (`KPIID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPITOSUBCOMPUTATION`
--

LOCK TABLES `KPITOSUBCOMPUTATION` WRITE;
/*!40000 ALTER TABLE `KPITOSUBCOMPUTATION` DISABLE KEYS */;
INSERT INTO `KPITOSUBCOMPUTATION` VALUES (1,1),(2,1),(3,2),(4,2),(5,3),(6,3),(7,2),(7,3),(8,4),(9,4),(10,4),(11,4),(12,5),(13,6);
/*!40000 ALTER TABLE `KPITOSUBCOMPUTATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPI_STATSDATA11_10_2016`
--

DROP TABLE IF EXISTS `KPI_STATSDATA11_10_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPI_STATSDATA11_10_2016` (
  `POLLID` bigint(20) NOT NULL DEFAULT '0',
  `INSTANCE` varchar(100) NOT NULL DEFAULT '',
  `TTIME` bigint(20) NOT NULL DEFAULT '0',
  `VAL` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPI_STATSDATA11_10_2016`
--

LOCK TABLES `KPI_STATSDATA11_10_2016` WRITE;
/*!40000 ALTER TABLE `KPI_STATSDATA11_10_2016` DISABLE KEYS */;
INSERT INTO `KPI_STATSDATA11_10_2016` VALUES (1,'-1',1478775707798,0.8190),(1,'-1',1478776007779,7.3770),(1,'-1',1478776307776,37.7040),(1,'-1',1478776607777,55.7370),(1,'-1',1478776907785,60.6550),(1,'-1',1478777207786,60.6550),(1,'-1',1478777507776,60.6550),(1,'-1',1478777807789,60.6550),(1,'-1',1478778107772,60.6550),(1,'-1',1478778407777,60.6550),(5,'-1',1478775707795,0.7069),(5,'-1',1478776007790,132.9193),(5,'-1',1478776307790,139.7352),(5,'-1',1478776607793,140.3872),(5,'-1',1478776907798,140.5103),(5,'-1',1478777207787,140.5103),(5,'-1',1478777507790,140.5103),(5,'-1',1478777807791,140.5103),(5,'-1',1478778107787,140.5103),(5,'-1',1478778407788,140.5103),(6,'-1',1478775707799,0.0000),(6,'-1',1478776007790,33.1191),(6,'-1',1478776307789,14.7604),(6,'-1',1478776607793,12.1326),(6,'-1',1478776907798,11.6422),(6,'-1',1478777207789,11.6422),(6,'-1',1478777507789,11.6422),(6,'-1',1478777807793,11.6422),(6,'-1',1478778107787,11.6422),(6,'-1',1478778407788,11.6422);
/*!40000 ALTER TABLE `KPI_STATSDATA11_10_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPI_STATSDATA11_4_2016`
--

DROP TABLE IF EXISTS `KPI_STATSDATA11_4_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPI_STATSDATA11_4_2016` (
  `POLLID` bigint(20) NOT NULL DEFAULT '0',
  `INSTANCE` varchar(100) NOT NULL DEFAULT '',
  `TTIME` bigint(20) NOT NULL DEFAULT '0',
  `VAL` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPI_STATSDATA11_4_2016`
--

LOCK TABLES `KPI_STATSDATA11_4_2016` WRITE;
/*!40000 ALTER TABLE `KPI_STATSDATA11_4_2016` DISABLE KEYS */;
INSERT INTO `KPI_STATSDATA11_4_2016` VALUES (1,'-1',1478254175625,100.0000),(1,'-1',1478254475628,100.0000),(1,'-1',1478254775619,97.3680),(1,'-1',1478255075610,96.4910),(1,'-1',1478255375617,98.5910),(1,'-1',1478255675628,98.9130),(1,'-1',1478255975619,98.9470),(1,'-1',1478256275611,96.8420),(1,'-1',1478256575619,87.8780),(1,'-1',1478256875617,87.3780),(1,'-1',1478257175629,87.8500),(1,'-1',1478257475619,89.3440),(1,'-1',1478257775614,89.3440),(1,'-1',1478258075611,89.3440),(1,'-1',1478258375626,89.3440),(1,'-1',1478258675623,89.3440),(1,'-1',1478258975609,89.3440),(1,'-1',1478259275613,89.3440),(1,'-1',1478259575620,89.3440),(1,'-1',1478259875610,87.7040),(1,'-1',1478260175612,79.5080),(1,'-1',1478260475619,76.2290),(1,'-1',1478260775613,74.5900),(1,'-1',1478261075618,70.4910),(1,'-1',1478261375622,70.4910),(1,'-1',1478261675619,70.4910),(1,'-1',1478261975630,70.4910),(1,'-1',1478262275621,70.4910),(1,'-1',1478262575614,70.4910),(1,'-1',1478262875614,70.4910),(1,'-1',1478263175626,70.4910),(1,'-1',1478263475623,68.8520),(1,'-1',1478263775625,65.5730),(1,'-1',1478264075624,65.5730),(1,'-1',1478264375626,65.5730),(1,'-1',1478264675622,63.1140),(1,'-1',1478264975620,63.1140),(1,'-1',1478265275622,63.1140),(1,'-1',1478265575609,63.1140),(1,'-1',1478265875613,62.2950),(1,'-1',1478266175616,63.1140),(1,'-1',1478266475622,63.1140),(1,'-1',1478266775624,63.1140),(1,'-1',1478267075628,65.5730),(1,'-1',1478267375628,59.8360),(1,'-1',1478267675638,59.8360),(1,'-1',1478267975629,59.0160),(1,'-1',1478268275621,57.3770),(1,'-1',1478268575613,57.3770),(1,'-1',1478268875624,57.3770),(1,'-1',1478269175623,57.3770),(1,'-1',1478269475618,57.3770),(1,'-1',1478269775621,57.3770),(1,'-1',1478270075619,57.3770),(1,'-1',1478270375616,57.3770),(1,'-1',1478270675626,54.0980),(1,'-1',1478271073302,50.8190),(1,'-1',1478271527152,35.2450),(1,'-1',1478272046152,18.0320),(1,'-1',1478273143288,10.6550),(1,'-1',1478274077135,10.6550),(1,'-1',1478276142143,9.8360),(1,'-1',1478277385154,5.7370),(1,'-1',1478278085120,0.8190),(1,'-1',1478278386182,0.8190),(1,'-1',1478278686169,0.8190),(1,'-1',1478278986183,0.8190),(1,'-1',1478279286185,0.8190),(1,'-1',1478279586175,0.8190),(1,'-1',1478279886184,0.8190),(1,'-1',1478280186176,0.8190),(1,'-1',1478280486184,0.8190),(1,'-1',1478280786183,0.8190),(1,'-1',1478281086175,0.8190),(1,'-1',1478281386176,0.8190),(1,'-1',1478281686177,0.8190),(1,'-1',1478281986174,0.8190),(1,'-1',1478282286181,0.8190),(1,'-1',1478282586186,0.8190),(1,'-1',1478282886177,0.8190),(1,'-1',1478283186176,0.8190),(1,'-1',1478283486177,0.8190),(1,'-1',1478283786182,0.8190),(1,'-1',1478284086173,0.8190),(4,'-1',1478262575646,0.0000),(4,'-1',1478266775646,0.0000),(5,'-1',1478255375617,0.0834),(5,'-1',1478255675627,0.0834),(5,'-1',1478255975631,0.0834),(5,'-1',1478256275624,0.0834),(5,'-1',1478256575623,0.0834),(5,'-1',1478256875631,0.0834),(5,'-1',1478257175626,0.0834),(5,'-1',1478257475620,0.0834),(5,'-1',1478257775627,0.0834),(5,'-1',1478258075624,0.0834),(5,'-1',1478258375624,0.0834),(5,'-1',1478258675622,0.0834),(5,'-1',1478258975621,0.0834),(5,'-1',1478259275622,0.0834),(5,'-1',1478259575619,0.0834),(5,'-1',1478259875621,0.8382),(5,'-1',1478260175626,0.9192),(5,'-1',1478260475618,0.9192),(5,'-1',1478260775626,0.9192),(5,'-1',1478261075630,0.9192),(5,'-1',1478261375621,0.9192),(5,'-1',1478261675627,0.9192),(5,'-1',1478261975628,0.9192),(5,'-1',1478262275620,0.9192),(5,'-1',1478262575624,0.9192),(5,'-1',1478262875629,0.9192),(5,'-1',1478263175624,0.9192),(5,'-1',1478263475622,1.0740),(5,'-1',1478263775623,1.1170),(5,'-1',1478264075623,1.1087),(5,'-1',1478264375625,1.1015),(5,'-1',1478264675621,1.0895),(5,'-1',1478264975620,1.0895),(5,'-1',1478265275633,1.0895),(5,'-1',1478265575619,1.0895),(5,'-1',1478265875625,1.1524),(5,'-1',1478266175626,1.2332),(5,'-1',1478266475621,1.2332),(5,'-1',1478266775624,1.2332),(5,'-1',1478267075628,1.2400),(5,'-1',1478267375626,1.3530),(5,'-1',1478267675636,1.4325),(5,'-1',1478267975625,1.4479),(5,'-1',1478268275620,1.5086),(5,'-1',1478268575622,1.5086),(5,'-1',1478268875623,1.5086),(5,'-1',1478269175623,1.5086),(5,'-1',1478269475618,1.5086),(5,'-1',1478269775621,1.5086),(5,'-1',1478270075619,1.5086),(5,'-1',1478270375624,1.5086),(5,'-1',1478270675626,1.6027),(5,'-1',1478271073325,1.6529),(5,'-1',1478271527171,1.8539),(5,'-1',1478272046180,1.8001),(5,'-1',1478273143307,3.1164),(5,'-1',1478274077163,3.1164),(5,'-1',1478276142173,3.1164),(5,'-1',1478277385162,0.7069),(5,'-1',1478278085119,0.7069),(5,'-1',1478278386180,0.7069),(5,'-1',1478278686182,0.7069),(5,'-1',1478278986182,0.7069),(5,'-1',1478279286184,0.7069),(5,'-1',1478279586185,0.7069),(5,'-1',1478279886181,0.7069),(5,'-1',1478280186184,0.7069),(5,'-1',1478280486183,0.7069),(5,'-1',1478280786182,0.7069),(5,'-1',1478281086186,0.7069),(5,'-1',1478281386185,0.7069),(5,'-1',1478281686186,0.7069),(5,'-1',1478281986183,0.7069),(5,'-1',1478282286178,0.7069),(5,'-1',1478282586184,0.7069),(5,'-1',1478282886186,0.7069),(5,'-1',1478283186186,0.7069),(5,'-1',1478283486188,0.7069),(5,'-1',1478283786190,0.7069),(5,'-1',1478284086185,0.7069),(6,'-1',1478255375628,0.0000),(6,'-1',1478255675629,0.0000),(6,'-1',1478255975631,0.0000),(6,'-1',1478256275624,0.0000),(6,'-1',1478256575623,0.0000),(6,'-1',1478256875626,0.0000),(6,'-1',1478257175630,0.0000),(6,'-1',1478257475621,0.0000),(6,'-1',1478257775626,0.0000),(6,'-1',1478258075624,0.0000),(6,'-1',1478258375627,0.0000),(6,'-1',1478258675623,0.0000),(6,'-1',1478258975621,0.0000),(6,'-1',1478259275622,0.0000),(6,'-1',1478259575621,0.0000),(6,'-1',1478259875621,0.5620),(6,'-1',1478260175625,0.4055),(6,'-1',1478260475620,0.4055),(6,'-1',1478260775626,0.4055),(6,'-1',1478261075631,0.4055),(6,'-1',1478261375623,0.4055),(6,'-1',1478261675627,0.4055),(6,'-1',1478261975631,0.4055),(6,'-1',1478262275622,0.4055),(6,'-1',1478262575624,0.4055),(6,'-1',1478262875629,0.4055),(6,'-1',1478263175627,0.4055),(6,'-1',1478263475624,0.5338),(6,'-1',1478263775625,0.4691),(6,'-1',1478264075625,0.4530),(6,'-1',1478264375628,0.4385),(6,'-1',1478264675625,0.4132),(6,'-1',1478264975621,0.4132),(6,'-1',1478265275633,0.4132),(6,'-1',1478265575619,0.4132),(6,'-1',1478265875625,0.3379),(6,'-1',1478266175626,0.5895),(6,'-1',1478266475625,0.5895),(6,'-1',1478266775626,0.5895),(6,'-1',1478267075630,0.6890),(6,'-1',1478267375628,0.7433),(6,'-1',1478267675638,0.7854),(6,'-1',1478267975631,0.7950),(6,'-1',1478268275623,0.7753),(6,'-1',1478268575622,0.7753),(6,'-1',1478268875625,0.7753),(6,'-1',1478269175625,0.7753),(6,'-1',1478269475620,0.7753),(6,'-1',1478269775623,0.7753),(6,'-1',1478270075621,0.7753),(6,'-1',1478270375624,0.7753),(6,'-1',1478270675628,0.9689),(6,'-1',1478271073336,0.9921),(6,'-1',1478271527182,1.1229),(6,'-1',1478272046189,1.2723),(6,'-1',1478273143318,1.3641),(6,'-1',1478274077164,1.3641),(6,'-1',1478276142172,1.3641),(6,'-1',1478277385172,0.0000),(6,'-1',1478278085127,0.0000),(6,'-1',1478278386183,0.0000),(6,'-1',1478278686182,0.0000),(6,'-1',1478278986192,0.0000),(6,'-1',1478279286185,0.0000),(6,'-1',1478279586185,0.0000),(6,'-1',1478279886184,0.0000),(6,'-1',1478280186183,0.0000),(6,'-1',1478280486185,0.0000),(6,'-1',1478280786190,0.0000),(6,'-1',1478281086186,0.0000),(6,'-1',1478281386184,0.0000),(6,'-1',1478281686186,0.0000),(6,'-1',1478281986183,0.0000),(6,'-1',1478282286182,0.0000),(6,'-1',1478282586187,0.0000),(6,'-1',1478282886186,0.0000),(6,'-1',1478283186186,0.0000),(6,'-1',1478283486188,0.0000),(6,'-1',1478283786190,0.0000),(6,'-1',1478284086184,0.0000);
/*!40000 ALTER TABLE `KPI_STATSDATA11_4_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KPI_STATSDATA11_5_2016`
--

DROP TABLE IF EXISTS `KPI_STATSDATA11_5_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KPI_STATSDATA11_5_2016` (
  `POLLID` bigint(20) NOT NULL DEFAULT '0',
  `INSTANCE` varchar(100) NOT NULL DEFAULT '',
  `TTIME` bigint(20) NOT NULL DEFAULT '0',
  `VAL` decimal(19,4) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KPI_STATSDATA11_5_2016`
--

LOCK TABLES `KPI_STATSDATA11_5_2016` WRITE;
/*!40000 ALTER TABLE `KPI_STATSDATA11_5_2016` DISABLE KEYS */;
INSERT INTO `KPI_STATSDATA11_5_2016` VALUES (1,'-1',1478284386183,0.8190),(1,'-1',1478284686181,0.8190),(1,'-1',1478284986175,0.8190),(1,'-1',1478285286179,0.8190),(1,'-1',1478285586175,0.8190),(1,'-1',1478285886190,0.8190),(1,'-1',1478286186182,0.8190),(1,'-1',1478286486188,0.8190),(1,'-1',1478286786173,0.8190),(1,'-1',1478287086182,0.8190),(1,'-1',1478287386185,0.8190),(1,'-1',1478287686174,0.8190),(1,'-1',1478287986181,0.8190),(1,'-1',1478288286181,0.8190),(1,'-1',1478288586178,0.8190),(1,'-1',1478288886174,0.8190),(1,'-1',1478289186176,0.8190),(1,'-1',1478289486179,0.8190),(1,'-1',1478289786179,0.8190),(1,'-1',1478290086186,0.8190),(1,'-1',1478290386180,0.8190),(1,'-1',1478290686187,0.8190),(1,'-1',1478290986181,0.8190),(1,'-1',1478291657122,0.8190),(1,'-1',1478292264146,0.8190),(1,'-1',1478292870149,0.8190),(1,'-1',1478293462141,0.8190),(1,'-1',1478294055140,0.8190),(1,'-1',1478294648148,0.8190),(1,'-1',1478295240145,0.8190),(1,'-1',1478295833157,0.8190),(1,'-1',1478296427273,0.8190),(1,'-1',1478297020300,0.8190),(1,'-1',1478297613137,0.8190),(1,'-1',1478298207153,0.8190),(1,'-1',1478298801137,0.8190),(1,'-1',1478299394157,0.8190),(1,'-1',1478299987154,0.8190),(1,'-1',1478300581300,0.8190),(1,'-1',1478301173143,0.8190),(1,'-1',1478301766162,0.8190),(1,'-1',1478302506451,0.8190),(1,'-1',1478303104149,0.8190),(1,'-1',1478303697274,0.8190),(1,'-1',1478304290137,0.8190),(1,'-1',1478304883146,0.8190),(1,'-1',1478305475164,0.8190),(1,'-1',1478306068159,0.8190),(1,'-1',1478306662152,0.8190),(1,'-1',1478307255153,0.8190),(1,'-1',1478307849144,0.8190),(1,'-1',1478308442151,0.8190),(1,'-1',1478309066155,0.8190),(1,'-1',1478309659147,0.8190),(1,'-1',1478310254152,0.8190),(1,'-1',1478310846156,0.8190),(1,'-1',1478311439149,0.8190),(1,'-1',1478312032149,0.8190),(1,'-1',1478312625158,0.8190),(1,'-1',1478313218151,0.8190),(1,'-1',1478313811153,0.8190),(1,'-1',1478314404144,0.8190),(1,'-1',1478314997140,0.8190),(1,'-1',1478315590163,0.8190),(1,'-1',1478316183149,0.8190),(1,'-1',1478316775148,0.8190),(1,'-1',1478317368148,0.8190),(1,'-1',1478317960144,0.8190),(1,'-1',1478318553151,0.8190),(1,'-1',1478319145144,0.8190),(1,'-1',1478319739153,0.8190),(1,'-1',1478320331147,0.8190),(1,'-1',1478320924151,0.8190),(1,'-1',1478321225192,0.8190),(5,'-1',1478284386188,0.7069),(5,'-1',1478284686180,0.7069),(5,'-1',1478284986184,0.7069),(5,'-1',1478285286177,0.7069),(5,'-1',1478285586184,0.7069),(5,'-1',1478285886188,0.7069),(5,'-1',1478286186180,0.7069),(5,'-1',1478286486186,0.7069),(5,'-1',1478286786187,0.7069),(5,'-1',1478287086181,0.7069),(5,'-1',1478287386184,0.7069),(5,'-1',1478287686186,0.7069),(5,'-1',1478287986180,0.7069),(5,'-1',1478288286180,0.7069),(5,'-1',1478288586177,0.7069),(5,'-1',1478288886187,0.7069),(5,'-1',1478289186188,0.7069),(5,'-1',1478289486178,0.7069),(5,'-1',1478289786179,0.7069),(5,'-1',1478290086184,0.7069),(5,'-1',1478290386178,0.7069),(5,'-1',1478290686185,0.7069),(5,'-1',1478290986178,0.7069),(5,'-1',1478291657132,0.7069),(5,'-1',1478292264145,0.7069),(5,'-1',1478292870155,0.7069),(5,'-1',1478293462165,0.7069),(5,'-1',1478294055164,0.7069),(5,'-1',1478294648157,0.7069),(5,'-1',1478295240172,0.7069),(5,'-1',1478295833164,0.7069),(5,'-1',1478296427308,0.7069),(5,'-1',1478297020319,0.7069),(5,'-1',1478297613160,0.7069),(5,'-1',1478298207165,0.7069),(5,'-1',1478298801157,0.7069),(5,'-1',1478299394175,0.7069),(5,'-1',1478299987173,0.7069),(5,'-1',1478300581308,0.7069),(5,'-1',1478301173159,0.7069),(5,'-1',1478301766167,0.7069),(5,'-1',1478302506454,0.7069),(5,'-1',1478303104173,0.7069),(5,'-1',1478303697298,0.7069),(5,'-1',1478304290155,0.7069),(5,'-1',1478304883155,0.7069),(5,'-1',1478305475170,0.7069),(5,'-1',1478306068166,0.7069),(5,'-1',1478306662164,0.7069),(5,'-1',1478307255177,0.7069),(5,'-1',1478307849154,0.7069),(5,'-1',1478308442170,0.7069),(5,'-1',1478309066162,0.7069),(5,'-1',1478309659154,0.7069),(5,'-1',1478310254161,0.7069),(5,'-1',1478310846167,0.7069),(5,'-1',1478311439164,0.7069),(5,'-1',1478312032161,0.7069),(5,'-1',1478312625165,0.7069),(5,'-1',1478313218161,0.7069),(5,'-1',1478313811160,0.7069),(5,'-1',1478314404164,0.7069),(5,'-1',1478314997150,0.7069),(5,'-1',1478315590171,0.7069),(5,'-1',1478316183169,0.7069),(5,'-1',1478316775167,0.7069),(5,'-1',1478317368157,0.7069),(5,'-1',1478317960162,0.7069),(5,'-1',1478318553160,0.7069),(5,'-1',1478319145162,0.7069),(5,'-1',1478319739161,0.7069),(5,'-1',1478320331165,0.7069),(5,'-1',1478320924169,0.7069),(5,'-1',1478321225200,0.7069),(6,'-1',1478284386188,0.0000),(6,'-1',1478284686182,0.0000),(6,'-1',1478284986184,0.0000),(6,'-1',1478285286180,0.0000),(6,'-1',1478285586184,0.0000),(6,'-1',1478285886191,0.0000),(6,'-1',1478286186182,0.0000),(6,'-1',1478286486189,0.0000),(6,'-1',1478286786187,0.0000),(6,'-1',1478287086183,0.0000),(6,'-1',1478287386186,0.0000),(6,'-1',1478287686186,0.0000),(6,'-1',1478287986182,0.0000),(6,'-1',1478288286182,0.0000),(6,'-1',1478288586179,0.0000),(6,'-1',1478288886187,0.0000),(6,'-1',1478289186188,0.0000),(6,'-1',1478289486180,0.0000),(6,'-1',1478289786187,0.0000),(6,'-1',1478290086187,0.0000),(6,'-1',1478290386181,0.0000),(6,'-1',1478290686188,0.0000),(6,'-1',1478290986182,0.0000),(6,'-1',1478291657132,0.0000),(6,'-1',1478292264146,0.0000),(6,'-1',1478292870166,0.0000),(6,'-1',1478293462165,0.0000),(6,'-1',1478294055164,0.0000),(6,'-1',1478294648171,0.0000),(6,'-1',1478295240172,0.0000),(6,'-1',1478295833178,0.0000),(6,'-1',1478296427308,0.0000),(6,'-1',1478297020319,0.0000),(6,'-1',1478297613170,0.0000),(6,'-1',1478298207174,0.0000),(6,'-1',1478298801157,0.0000),(6,'-1',1478299394175,0.0000),(6,'-1',1478299987173,0.0000),(6,'-1',1478300581308,0.0000),(6,'-1',1478301173159,0.0000),(6,'-1',1478301766167,0.0000),(6,'-1',1478302506457,0.0000),(6,'-1',1478303104174,0.0000),(6,'-1',1478303697298,0.0000),(6,'-1',1478304290155,0.0000),(6,'-1',1478304883169,0.0000),(6,'-1',1478305475185,0.0000),(6,'-1',1478306068177,0.0000),(6,'-1',1478306662175,0.0000),(6,'-1',1478307255177,0.0000),(6,'-1',1478307849166,0.0000),(6,'-1',1478308442170,0.0000),(6,'-1',1478309066162,0.0000),(6,'-1',1478309659163,0.0000),(6,'-1',1478310254177,0.0000),(6,'-1',1478310846179,0.0000),(6,'-1',1478311439179,0.0000),(6,'-1',1478312032170,0.0000),(6,'-1',1478312625179,0.0000),(6,'-1',1478313218170,0.0000),(6,'-1',1478313811161,0.0000),(6,'-1',1478314404164,0.0000),(6,'-1',1478314997161,0.0000),(6,'-1',1478315590181,0.0000),(6,'-1',1478316183168,0.0000),(6,'-1',1478316775168,0.0000),(6,'-1',1478317368157,0.0000),(6,'-1',1478317960162,0.0000),(6,'-1',1478318553169,0.0000),(6,'-1',1478319145162,0.0000),(6,'-1',1478319739172,0.0000),(6,'-1',1478320331166,0.0000),(6,'-1',1478320924169,0.0000),(6,'-1',1478321225200,0.0000);
/*!40000 ALTER TABLE `KPI_STATSDATA11_5_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MAPPEDPROPERTIES`
--

DROP TABLE IF EXISTS `MAPPEDPROPERTIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MAPPEDPROPERTIES` (
  `NAME` varchar(100) NOT NULL,
  `PROPVAL` varchar(255) DEFAULT NULL,
  `PROPNAME` varchar(255) NOT NULL,
  PRIMARY KEY (`NAME`,`PROPNAME`),
  KEY `FK4D2F47A6977A5201` (`NAME`),
  CONSTRAINT `FK4D2F47A6977A5201` FOREIGN KEY (`NAME`) REFERENCES `MapDB` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAPPEDPROPERTIES`
--

LOCK TABLES `MAPPEDPROPERTIES` WRITE;
/*!40000 ALTER TABLE `MAPPEDPROPERTIES` DISABLE KEYS */;
INSERT INTO `MAPPEDPROPERTIES` VALUES ('172.24.14.0.netmap','Finished','discoveryStatus'),('172.24.14.0.netmap','true','flash'),('172.24.14.0.netmap','line','htmlMapLinkRenderer'),('172.24.14.0.netmap','symbol_label','htmlMapSymbRenderer'),('172.24.14.0.netmap','com.adventnet.nms.mapui.EthernetMapLinkRenderer','javaMapLinkRenderer'),('172.24.14.0.netmap','com.adventnet.nms.mapui.EthernetMapSymbolRenderer','javaMapSymbRenderer'),('172.24.14.0Failed_Objects_Map.netmap','symbol_label','htmlMapSymbRenderer'),('172.24.14.0Failed_Objects_Map.netmap','com.adventnet.nms.mapui.MapSymbolRendererImpl_4','javaMapSymbRenderer'),('Failed_Objects_Map.netmap','symbol_label','htmlMapSymbRenderer'),('Failed_Objects_Map.netmap','com.adventnet.nms.mapui.MapSymbolRendererImpl_4','javaMapSymbRenderer'),('ipnet.netmap','true','highlightLinks'),('ipnet.netmap','curved','htmlMapLinkRenderer'),('ipnet.netmap','symbol_label','htmlMapSymbRenderer'),('ipnet.netmap','com.adventnet.nms.mapui.MapLinkRendererImpl_3','javaMapLinkRenderer'),('ipnet.netmap','com.adventnet.nms.mapui.MapSymbolRendererImpl_5','javaMapSymbRenderer');
/*!40000 ALTER TABLE `MAPPEDPROPERTIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MAPUSERPROPS`
--

DROP TABLE IF EXISTS `MAPUSERPROPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MAPUSERPROPS` (
  `NAME` varchar(100) NOT NULL,
  `MAPNAME` varchar(100) NOT NULL,
  `PROPVAL` varchar(255) DEFAULT NULL,
  `PROPNAME` varchar(255) NOT NULL,
  PRIMARY KEY (`NAME`,`MAPNAME`,`PROPNAME`),
  KEY `FK30B70EA9AF738122` (`NAME`,`MAPNAME`),
  CONSTRAINT `FK30B70EA9AF738122` FOREIGN KEY (`NAME`, `MAPNAME`) REFERENCES `MapSymbol` (`NAME`, `MAPNAME`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAPUSERPROPS`
--

LOCK TABLES `MAPUSERPROPS` WRITE;
/*!40000 ALTER TABLE `MAPUSERPROPS` DISABLE KEYS */;
INSERT INTO `MAPUSERPROPS` VALUES ('172.24.14.0','Failed_Objects_Map.netmap','{\"netmask\":\"255.255.255.0\",\"ipAddress\":\"172.24.14.0\",\"discoveryStatus\":\"FINISHED\",\"status\":2}','tooltip'),('172.24.14.0','ipnet.netmap','{\"netmask\":\"255.255.255.0\",\"ipAddress\":\"172.24.14.0\",\"discoveryStatus\":\"IN_PROGRESS\",\"status\":5}','tooltip'),('172.24.14.105','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.112','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.115','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.149','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.152','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.154','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.171','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.178','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.191','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.226','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.238','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.252','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.28','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.56','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.67','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.84','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('172.24.14.92','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('192.168.220.0','ipnet.netmap','{\"netmask\":\"255.255.252.0\",\"ipAddress\":\"192.168.220.0\",\"discoveryStatus\":\"DISCOVERY_DISABLED\"}','tooltip'),('abdul-0436.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('abdul-zt24.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('abhi-3378.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('admp-test1.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('ajay-1385.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('amarnath-0642.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('amritha-3867.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('android-6d556f7960b13da.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('android-6da4f6e8432f2ea.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('android-8f4bc429763adb11.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('android-d9e08dc4ead46367.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('anu-4114.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('aravind-0717.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('aravinds6splus.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('aravinth-3063.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('arivalagan-2168.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('arun-2286.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('arun-3857.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('arunsubhash-0371.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('ashish-4086.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('aswath-pt773.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('bala-2606.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('bharath-2679.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('bhargavi-2458.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('boobala-0048.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('chithu-0706.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('damodhar-1003.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('dheeraj-1090.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('drevathy-0847.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('gokul-3303.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('gramkumar-0817.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('guhan-3315.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('harini-zu360.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('hemanth-3818.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('iphone-6-arivu.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('iphone6plus565.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('iphone6usest185.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('janaki-3684.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('jerome-3929.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('jlatha-0972.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('jsangeetha-0849.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('kalai-0041.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('kavitha-1061.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('ksenthil-0949.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('loga-zu396.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('macbook-pro.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('magesh-1870.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('mali-0473.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('mani-0702.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('mani-0918.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('mani-2209.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('mani-4546.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('manimaran-1378.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('marutha-3402.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('meena.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('mithun-0445.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('mohasin-2851.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('mukil-4261.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('muni-0051.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('naga-3924.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('nirmal-2552.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('nj.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('padma-4271.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('pandi-1824.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('partha-3525.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('pav.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('pavithra-3526.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('prameena-1188.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('preethi2siphone.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('priya-zutk58.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('purushoth-3454.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('radhas-iphone-6.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('raj-3842.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('rajesh-2755.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('raji-0675.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('rakesh-3889.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('ram-3371.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('rejoe-0162.csez.zohocorpin.com','172.24.14.0.netmap','{\"sysOID\":\".1.3.6.1.4.1.311.1.1.3.1.1\",\"sysName\":\"rejoe-0162.csez.zohocorpin.com\",\"isRouter\":false,\"status\":5}','tooltip'),('sabarna-4236.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('sai-3130.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('sandeep-2.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('sandhya-3439.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('santhanapreethi.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('sathish-1320.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('sathish-2784.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('shanmugam-2352.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('shyamallsiphone.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('siddharth-4445.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('siva-2171.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('srivatsav-3642.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('subha-4506.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('sumanth-3304.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('sundari-1712.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('suresh-0665.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('suresh-1545.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('ullas-4059.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('vaisali-4071.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('varun-2246.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('varun-3902.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('velan-es0007.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('venkat-0773.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('vijay-0596.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('vineesh-3479.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('vinodh-2312.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip'),('yuvaraj-1472.csez.zohocorpin.com','172.24.14.0.netmap','{\"isRouter\":false,\"status\":5}','tooltip');
/*!40000 ALTER TABLE `MAPUSERPROPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MOMatchCriteria`
--

DROP TABLE IF EXISTS `MOMatchCriteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MOMatchCriteria` (
  `CRITERIAID` bigint(20) NOT NULL,
  `MATCHPROP` varchar(100) DEFAULT NULL,
  `MATCHTYPE` varchar(200) DEFAULT NULL,
  `MATCHCONDITION` varchar(200) DEFAULT NULL,
  `VALUE` varchar(200) DEFAULT NULL,
  `DEVLISTTOMOCRITID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CRITERIAID`),
  KEY `FK631E5C2D09CDD43` (`DEVLISTTOMOCRITID`),
  CONSTRAINT `FK631E5C2D09CDD43` FOREIGN KEY (`DEVLISTTOMOCRITID`) REFERENCES `KPIDeviceList` (`DEVICELISTID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MOMatchCriteria`
--

LOCK TABLES `MOMatchCriteria` WRITE;
/*!40000 ALTER TABLE `MOMatchCriteria` DISABLE KEYS */;
INSERT INTO `MOMatchCriteria` VALUES (1,'isSNMP','STRING','equals','true',1),(2,'type','STRING','equals','Switch',1),(3,'isSNMP','STRING','equals','true',2);
/*!40000 ALTER TABLE `MOMatchCriteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ManagedGroupObject`
--

DROP TABLE IF EXISTS `ManagedGroupObject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ManagedGroupObject` (
  `MOID` bigint(20) NOT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK2D38159FD43F4EA2` (`MOID`),
  CONSTRAINT `FK2D38159FD43F4EA2` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ManagedGroupObject`
--

LOCK TABLES `ManagedGroupObject` WRITE;
/*!40000 ALTER TABLE `ManagedGroupObject` DISABLE KEYS */;
/*!40000 ALTER TABLE `ManagedGroupObject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ManagedObject`
--

DROP TABLE IF EXISTS `ManagedObject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ManagedObject` (
  `MOID` bigint(20) NOT NULL,
  `DISCRIMINATOR` varchar(30) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `CLASSNAME` varchar(100) DEFAULT NULL,
  `DISPLAYNAME` varchar(100) NOT NULL,
  `FAILURECOUNT` int(11) DEFAULT NULL,
  `FAILURETHRESHOLD` int(11) DEFAULT NULL,
  `ISCONTAINER` bit(1) DEFAULT NULL,
  `ISGROUP` bit(1) DEFAULT NULL,
  `MANAGED` bit(1) DEFAULT NULL,
  `PARENTKEY` varchar(100) NOT NULL,
  `POLLINTERVAL` int(11) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `STATUSCHANGETIME` bigint(20) DEFAULT NULL,
  `STATUSPOLLENABLED` bit(1) DEFAULT NULL,
  `STATUSUPDATETIME` bigint(20) DEFAULT NULL,
  `TESTER` varchar(100) DEFAULT NULL,
  `TYPE` varchar(100) DEFAULT NULL,
  `UCLASS` varchar(100) DEFAULT NULL,
  `WEBNMS` varchar(100) DEFAULT NULL,
  `PARENTID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  UNIQUE KEY `NAME` (`NAME`),
  KEY `FKB855B9E41BE4C5D` (`PARENTID`),
  CONSTRAINT `FKB855B9E41BE4C5D` FOREIGN KEY (`PARENTID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ManagedObject`
--

LOCK TABLES `ManagedObject` WRITE;
/*!40000 ALTER TABLE `ManagedObject` DISABLE KEYS */;
INSERT INTO `ManagedObject` VALUES (1,'Network','172.24.14.0','Network','172.24.14.0',0,1,'','\0','','NULL',1800,2,1478254649328,'\0',1478776683254,'max','Network',NULL,NULL,NULL),(2,'IpAddress','IF-guhan-3315.csez.zohocorpin.com','IpAddress','IF-guhan-3315.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775943229,'',1478775943229,'ping','Interface',NULL,NULL,NULL),(3,'Node','guhan-3315.csez.zohocorpin.com','Node','guhan-3315.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775943459,'\0',1478775943459,'max','Node',NULL,NULL,NULL),(4,'IpAddress','IF-vineesh-3479.csez.zohocorpin.com','IpAddress','IF-vineesh-3479.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775948001,'',1478775948001,'ping','Interface',NULL,NULL,NULL),(5,'Node','vineesh-3479.csez.zohocorpin.com','Node','vineesh-3479.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775948231,'\0',1478775948231,'max','Node',NULL,NULL,NULL),(6,'IpAddress','IF-varun-3902.csez.zohocorpin.com','IpAddress','IF-varun-3902.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775953062,'',1478775953062,'ping','Interface',NULL,NULL,NULL),(7,'Node','varun-3902.csez.zohocorpin.com','Node','varun-3902.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775953292,'\0',1478775953292,'max','Node',NULL,NULL,NULL),(8,'IpAddress','IF-mani-4546.csez.zohocorpin.com','IpAddress','IF-mani-4546.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775958135,'',1478775958135,'ping','Interface',NULL,NULL,NULL),(9,'Node','mani-4546.csez.zohocorpin.com','Node','mani-4546.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775958367,'\0',1478775958367,'max','Node',NULL,NULL,NULL),(10,'IpAddress','IF-bharath-2679.csez.zohocorpin.com','IpAddress','IF-bharath-2679.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775963092,'',1478775963092,'ping','Interface',NULL,NULL,NULL),(11,'Node','bharath-2679.csez.zohocorpin.com','Node','bharath-2679.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775963324,'\0',1478775963324,'max','Node',NULL,NULL,NULL),(12,'IpAddress','IF-iphone6usest185.csez.zohocorpin.com','IpAddress','IF-iphone6usest185.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478270587585,'',1478775978827,'ping','Interface',NULL,NULL,NULL),(13,'Node','iphone6usest185.csez.zohocorpin.com','Node','iphone6usest185.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478270587817,'\0',1478270587817,'max','Node',NULL,NULL,NULL),(14,'IpAddress','IF-iphone6plus565.csez.zohocorpin.com','IpAddress','IF-iphone6plus565.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478270604022,'',1478775983822,'ping','Interface',NULL,NULL,NULL),(15,'Node','iphone6plus565.csez.zohocorpin.com','Node','iphone6plus565.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478270604240,'\0',1478270604240,'max','Node',NULL,NULL,NULL),(16,'IpAddress','IF-rakesh-3889.csez.zohocorpin.com','IpAddress','IF-rakesh-3889.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478259785460,'',1478775988831,'ping','Interface',NULL,NULL,NULL),(17,'Node','rakesh-3889.csez.zohocorpin.com','Node','rakesh-3889.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478259785691,'\0',1478259785691,'max','Node',NULL,NULL,NULL),(18,'IpAddress','IF-subha-4506.csez.zohocorpin.com','IpAddress','IF-subha-4506.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775983050,'',1478775983050,'ping','Interface',NULL,NULL,NULL),(19,'Node','subha-4506.csez.zohocorpin.com','Node','subha-4506.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775983283,'\0',1478775983283,'max','Node',NULL,NULL,NULL),(20,'IpAddress','IF-amritha-3867.csez.zohocorpin.com','IpAddress','IF-amritha-3867.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775988122,'',1478775988122,'ping','Interface',NULL,NULL,NULL),(21,'Node','amritha-3867.csez.zohocorpin.com','Node','amritha-3867.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775988247,'\0',1478775988247,'max','Node',NULL,NULL,NULL),(22,'IpAddress','IF-sandeep-2.csez.zohocorpin.com','IpAddress','IF-sandeep-2.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478277371408,'',1478776003810,'ping','Interface',NULL,NULL,NULL),(23,'Node','sandeep-2.csez.zohocorpin.com','Node','sandeep-2.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478277371737,'\0',1478277371737,'max','Node',NULL,NULL,NULL),(24,'IpAddress','IF-sumanth-3304.csez.zohocorpin.com','IpAddress','IF-sumanth-3304.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775998109,'',1478775998109,'ping','Interface',NULL,NULL,NULL),(25,'Node','sumanth-3304.csez.zohocorpin.com','Node','sumanth-3304.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478775998346,'\0',1478775998346,'max','Node',NULL,NULL,NULL),(26,'IpAddress','IF-ksenthil-0949.csez.zohocorpin.com','IpAddress','IF-ksenthil-0949.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267011627,'',1478776013835,'ping','Interface',NULL,NULL,NULL),(27,'Node','ksenthil-0949.csez.zohocorpin.com','Node','ksenthil-0949.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267011875,'\0',1478267011875,'max','Node',NULL,NULL,NULL),(28,'IpAddress','IF-padma-4271.csez.zohocorpin.com','IpAddress','IF-padma-4271.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776008013,'',1478776008013,'ping','Interface',NULL,NULL,NULL),(29,'Node','padma-4271.csez.zohocorpin.com','Node','padma-4271.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776008239,'\0',1478776008239,'max','Node',NULL,NULL,NULL),(30,'IpAddress','IF-172.24.14.28','IpAddress','IF-172.24.14.28',0,1,'\0','\0','','NULL',3600,5,1478776013075,'',1478776013075,'ping','Interface',NULL,NULL,NULL),(31,'Node','172.24.14.28','Node','172.24.14.28',0,1,'\0','\0','','NULL',3600,5,1478776013305,'\0',1478776013305,'max','Node',NULL,NULL,NULL),(32,'IpAddress','IF-abhi-3378.csez.zohocorpin.com','IpAddress','IF-abhi-3378.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478277382319,'',1478776028830,'ping','Interface',NULL,NULL,NULL),(33,'Node','abhi-3378.csez.zohocorpin.com','Node','abhi-3378.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478277382577,'\0',1478277382577,'max','Node',NULL,NULL,NULL),(34,'IpAddress','IF-aravind-0717.csez.zohocorpin.com','IpAddress','IF-aravind-0717.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776023174,'',1478776023174,'ping','Interface',NULL,NULL,NULL),(35,'Node','aravind-0717.csez.zohocorpin.com','Node','aravind-0717.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776023301,'\0',1478776023301,'max','Node',NULL,NULL,NULL),(36,'IpAddress','IF-venkat-0773.csez.zohocorpin.com','IpAddress','IF-venkat-0773.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776028039,'',1478776028039,'ping','Interface',NULL,NULL,NULL),(37,'Node','venkat-0773.csez.zohocorpin.com','Node','venkat-0773.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776028257,'\0',1478776028257,'max','Node',NULL,NULL,NULL),(38,'IpAddress','IF-mani-0918.csez.zohocorpin.com','IpAddress','IF-mani-0918.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776033181,'',1478776033181,'ping','Interface',NULL,NULL,NULL),(39,'Node','mani-0918.csez.zohocorpin.com','Node','mani-0918.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776033414,'\0',1478776033414,'max','Node',NULL,NULL,NULL),(40,'IpAddress','IF-bala-2606.csez.zohocorpin.com','IpAddress','IF-bala-2606.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478277393445,'',1478776048820,'ping','Interface',NULL,NULL,NULL),(41,'Node','bala-2606.csez.zohocorpin.com','Node','bala-2606.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478277393661,'\0',1478277393661,'max','Node',NULL,NULL,NULL),(42,'Network','192.168.220.0','Network','192.168.220.0',0,1,'','\0','\0','NULL',1800,7,1478254261046,'\0',1478254261046,'max','Network',NULL,NULL,NULL),(43,'SnmpInterface','IF-rejoe-0162.csez.zohocorpin.com','SnmpInterface','IF-rejoe-0162.csez.zohocorpin.com',0,1,'\0','\0','','NULL',300,2,1478271713438,'',1478778159122,'snmpping','Interface',NULL,NULL,NULL),(44,'SnmpInterface','IF-192.168.220.202','SnmpInterface','IF-192.168.220.202',0,1,'\0','\0','','NULL',300,2,1478271713416,'',1478778164089,'snmpping','Interface',NULL,NULL,NULL),(45,'SnmpNode','rejoe-0162.csez.zohocorpin.com','SnmpNode','rejoe-0162.csez.zohocorpin.com',0,1,'\0','\0','','NULL',300,2,1478271713586,'\0',1478271713586,'max','WindowsNT',NULL,NULL,NULL),(46,'IpAddress','IF-mukil-4261.csez.zohocorpin.com','IpAddress','IF-mukil-4261.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776053174,'',1478776053174,'ping','Interface',NULL,NULL,NULL),(47,'Node','mukil-4261.csez.zohocorpin.com','Node','mukil-4261.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776053393,'\0',1478776053393,'max','Node',NULL,NULL,NULL),(48,'IpAddress','IF-pandi-1824.csez.zohocorpin.com','IpAddress','IF-pandi-1824.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776058120,'',1478776058120,'ping','Interface',NULL,NULL,NULL),(49,'Node','pandi-1824.csez.zohocorpin.com','Node','pandi-1824.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776058352,'\0',1478776058352,'max','Node',NULL,NULL,NULL),(50,'IpAddress','IF-sathish-1320.csez.zohocorpin.com','IpAddress','IF-sathish-1320.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776063078,'',1478776063078,'ping','Interface',NULL,NULL,NULL),(51,'Node','sathish-1320.csez.zohocorpin.com','Node','sathish-1320.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776063206,'\0',1478776063206,'max','Node',NULL,NULL,NULL),(52,'IpAddress','IF-sai-3130.csez.zohocorpin.com','IpAddress','IF-sai-3130.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776068064,'',1478776068064,'ping','Interface',NULL,NULL,NULL),(53,'Node','sai-3130.csez.zohocorpin.com','Node','sai-3130.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776068191,'\0',1478776068191,'max','Node',NULL,NULL,NULL),(54,'IpAddress','IF-vinodh-2312.csez.zohocorpin.com','IpAddress','IF-vinodh-2312.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776073034,'',1478776073034,'ping','Interface',NULL,NULL,NULL),(55,'Node','vinodh-2312.csez.zohocorpin.com','Node','vinodh-2312.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776073153,'\0',1478776073153,'max','Node',NULL,NULL,NULL),(56,'IpAddress','IF-yuvaraj-1472.csez.zohocorpin.com','IpAddress','IF-yuvaraj-1472.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776078008,'',1478776078008,'ping','Interface',NULL,NULL,NULL),(57,'Node','yuvaraj-1472.csez.zohocorpin.com','Node','yuvaraj-1472.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776078239,'\0',1478776078239,'max','Node',NULL,NULL,NULL),(58,'IpAddress','IF-172.24.14.56','IpAddress','IF-172.24.14.56',0,1,'\0','\0','','NULL',3600,5,1478776083088,'',1478776083088,'ping','Interface',NULL,NULL,NULL),(59,'Node','172.24.14.56','Node','172.24.14.56',0,1,'\0','\0','','NULL',3600,5,1478776083215,'\0',1478776083215,'max','Node',NULL,NULL,NULL),(60,'IpAddress','IF-ashish-4086.csez.zohocorpin.com','IpAddress','IF-ashish-4086.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776088050,'',1478776088050,'ping','Interface',NULL,NULL,NULL),(61,'Node','ashish-4086.csez.zohocorpin.com','Node','ashish-4086.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776088281,'\0',1478776088281,'max','Node',NULL,NULL,NULL),(62,'IpAddress','IF-naga-3924.csez.zohocorpin.com','IpAddress','IF-naga-3924.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271069385,'',1478776103797,'ping','Interface',NULL,NULL,NULL),(63,'Node','naga-3924.csez.zohocorpin.com','Node','naga-3924.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271069752,'\0',1478271069752,'max','Node',NULL,NULL,NULL),(64,'IpAddress','IF-priya-zutk58.csez.zohocorpin.com','IpAddress','IF-priya-zutk58.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478263516026,'',1478776108822,'ping','Interface',NULL,NULL,NULL),(65,'Node','priya-zutk58.csez.zohocorpin.com','Node','priya-zutk58.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478263516254,'\0',1478263516254,'max','Node',NULL,NULL,NULL),(66,'IpAddress','IF-manimaran-1378.csez.zohocorpin.com','IpAddress','IF-manimaran-1378.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776103132,'',1478776103132,'ping','Interface',NULL,NULL,NULL),(67,'Node','manimaran-1378.csez.zohocorpin.com','Node','manimaran-1378.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776103351,'\0',1478776103351,'max','Node',NULL,NULL,NULL),(68,'IpAddress','IF-shanmugam-2352.csez.zohocorpin.com','IpAddress','IF-shanmugam-2352.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776107997,'',1478776107997,'ping','Interface',NULL,NULL,NULL),(69,'Node','shanmugam-2352.csez.zohocorpin.com','Node','shanmugam-2352.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776108216,'\0',1478776108216,'max','Node',NULL,NULL,NULL),(70,'IpAddress','IF-gokul-3303.csez.zohocorpin.com','IpAddress','IF-gokul-3303.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776113059,'',1478776113059,'ping','Interface',NULL,NULL,NULL),(71,'Node','gokul-3303.csez.zohocorpin.com','Node','gokul-3303.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776113290,'\0',1478776113290,'max','Node',NULL,NULL,NULL),(72,'IpAddress','IF-arunsubhash-0371.csez.zohocorpin.com','IpAddress','IF-arunsubhash-0371.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776118016,'',1478776118016,'ping','Interface',NULL,NULL,NULL),(73,'Node','arunsubhash-0371.csez.zohocorpin.com','Node','arunsubhash-0371.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776118240,'\0',1478776118240,'max','Node',NULL,NULL,NULL),(74,'IpAddress','IF-mani-0702.csez.zohocorpin.com','IpAddress','IF-mani-0702.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271091626,'',1478776133789,'ping','Interface',NULL,NULL,NULL),(75,'Node','mani-0702.csez.zohocorpin.com','Node','mani-0702.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271091866,'\0',1478271091866,'max','Node',NULL,NULL,NULL),(76,'IpAddress','IF-rajesh-2755.csez.zohocorpin.com','IpAddress','IF-rajesh-2755.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271102638,'',1478776138840,'ping','Interface',NULL,NULL,NULL),(77,'Node','rajesh-2755.csez.zohocorpin.com','Node','rajesh-2755.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271102999,'\0',1478271102999,'max','Node',NULL,NULL,NULL),(78,'IpAddress','IF-abdul-zt24.csez.zohocorpin.com','IpAddress','IF-abdul-zt24.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271102739,'',1478776143845,'ping','Interface',NULL,NULL,NULL),(79,'Node','abdul-zt24.csez.zohocorpin.com','Node','abdul-zt24.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271103269,'\0',1478271103269,'max','Node',NULL,NULL,NULL),(80,'IpAddress','IF-mani-2209.csez.zohocorpin.com','IpAddress','IF-mani-2209.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271102687,'',1478776148824,'ping','Interface',NULL,NULL,NULL),(81,'Node','mani-2209.csez.zohocorpin.com','Node','mani-2209.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271103086,'\0',1478271103086,'max','Node',NULL,NULL,NULL),(82,'IpAddress','IF-macbook-pro.csez.zohocorpin.com','IpAddress','IF-macbook-pro.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271113739,'',1478776153822,'ping','Interface',NULL,NULL,NULL),(83,'Node','macbook-pro.csez.zohocorpin.com','Node','macbook-pro.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271114011,'\0',1478271114011,'max','Node',NULL,NULL,NULL),(84,'IpAddress','IF-172.24.14.84','IpAddress','IF-172.24.14.84',0,1,'\0','\0','','NULL',3600,5,1478776148021,'',1478776148021,'ping','Interface',NULL,NULL,NULL),(85,'Node','172.24.14.84','Node','172.24.14.84',0,1,'\0','\0','','NULL',3600,5,1478776148146,'\0',1478776148146,'max','Node',NULL,NULL,NULL),(86,'IpAddress','IF-jsangeetha-0849.csez.zohocorpin.com','IpAddress','IF-jsangeetha-0849.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776152985,'',1478776152985,'ping','Interface',NULL,NULL,NULL),(87,'Node','jsangeetha-0849.csez.zohocorpin.com','Node','jsangeetha-0849.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776153108,'\0',1478776153108,'max','Node',NULL,NULL,NULL),(88,'IpAddress','IF-172.24.14.92','IpAddress','IF-172.24.14.92',0,1,'\0','\0','','NULL',3600,5,1478776158047,'',1478776158047,'ping','Interface',NULL,NULL,NULL),(89,'Node','172.24.14.92','Node','172.24.14.92',0,1,'\0','\0','','NULL',3600,5,1478776158279,'\0',1478776158279,'max','Node',NULL,NULL,NULL),(90,'IpAddress','IF-muni-0051.csez.zohocorpin.com','IpAddress','IF-muni-0051.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776163121,'',1478776163121,'ping','Interface',NULL,NULL,NULL),(91,'Node','muni-0051.csez.zohocorpin.com','Node','muni-0051.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776163352,'\0',1478776163352,'max','Node',NULL,NULL,NULL),(92,'IpAddress','IF-android-d9e08dc4ead46367.csez.zohocorpin.com','IpAddress','IF-android-d9e08dc4ead46367.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478256374869,'',1478776178831,'ping','Interface',NULL,NULL,NULL),(93,'Node','android-d9e08dc4ead46367.csez.zohocorpin.com','Node','android-d9e08dc4ead46367.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478256375097,'\0',1478256375097,'max','Node',NULL,NULL,NULL),(94,'IpAddress','IF-mohasin-2851.csez.zohocorpin.com','IpAddress','IF-mohasin-2851.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776173087,'',1478776173087,'ping','Interface',NULL,NULL,NULL),(95,'Node','mohasin-2851.csez.zohocorpin.com','Node','mohasin-2851.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776173211,'\0',1478776173211,'max','Node',NULL,NULL,NULL),(96,'IpAddress','IF-raj-3842.csez.zohocorpin.com','IpAddress','IF-raj-3842.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776178051,'',1478776178051,'ping','Interface',NULL,NULL,NULL),(97,'Node','raj-3842.csez.zohocorpin.com','Node','raj-3842.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776178288,'\0',1478776178288,'max','Node',NULL,NULL,NULL),(98,'IpAddress','IF-172.24.14.105','IpAddress','IF-172.24.14.105',0,1,'\0','\0','','NULL',3600,5,1478776183117,'',1478776183117,'ping','Interface',NULL,NULL,NULL),(99,'Node','172.24.14.105','Node','172.24.14.105',0,1,'\0','\0','','NULL',3600,5,1478776183340,'\0',1478776183340,'max','Node',NULL,NULL,NULL),(100,'IpAddress','IF-172.24.14.112','IpAddress','IF-172.24.14.112',0,1,'\0','\0','','NULL',3600,2,1478263595345,'',1478776198828,'ping','Interface',NULL,NULL,NULL),(101,'Node','172.24.14.112','Node','172.24.14.112',0,1,'\0','\0','','NULL',3600,2,1478263595566,'\0',1478263595566,'max','Node',NULL,NULL,NULL),(102,'IpAddress','IF-siva-2171.csez.zohocorpin.com','IpAddress','IF-siva-2171.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267200502,'',1478776203804,'ping','Interface',NULL,NULL,NULL),(103,'Node','siva-2171.csez.zohocorpin.com','Node','siva-2171.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267200732,'\0',1478267200732,'max','Node',NULL,NULL,NULL),(104,'IpAddress','IF-boobala-0048.csez.zohocorpin.com','IpAddress','IF-boobala-0048.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776198102,'',1478776198102,'ping','Interface',NULL,NULL,NULL),(105,'Node','boobala-0048.csez.zohocorpin.com','Node','boobala-0048.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776198336,'\0',1478776198336,'max','Node',NULL,NULL,NULL),(106,'IpAddress','IF-jlatha-0972.csez.zohocorpin.com','IpAddress','IF-jlatha-0972.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267213900,'',1478776213849,'ping','Interface',NULL,NULL,NULL),(107,'Node','jlatha-0972.csez.zohocorpin.com','Node','jlatha-0972.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267214123,'\0',1478267214123,'max','Node',NULL,NULL,NULL),(108,'IpAddress','IF-amarnath-0642.csez.zohocorpin.com','IpAddress','IF-amarnath-0642.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267237023,'',1478776218816,'ping','Interface',NULL,NULL,NULL),(109,'Node','amarnath-0642.csez.zohocorpin.com','Node','amarnath-0642.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267237243,'\0',1478267237243,'max','Node',NULL,NULL,NULL),(110,'IpAddress','IF-partha-3525.csez.zohocorpin.com','IpAddress','IF-partha-3525.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776213171,'',1478776213171,'ping','Interface',NULL,NULL,NULL),(111,'Node','partha-3525.csez.zohocorpin.com','Node','partha-3525.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776213391,'\0',1478776213391,'max','Node',NULL,NULL,NULL),(112,'IpAddress','IF-chithu-0706.csez.zohocorpin.com','IpAddress','IF-chithu-0706.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271187642,'',1478776228834,'ping','Interface',NULL,NULL,NULL),(113,'Node','chithu-0706.csez.zohocorpin.com','Node','chithu-0706.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271187870,'\0',1478271187870,'max','Node',NULL,NULL,NULL),(114,'IpAddress','IF-aswath-pt773.csez.zohocorpin.com','IpAddress','IF-aswath-pt773.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776223051,'',1478776223051,'ping','Interface',NULL,NULL,NULL),(115,'Node','aswath-pt773.csez.zohocorpin.com','Node','aswath-pt773.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776223272,'\0',1478776223272,'max','Node',NULL,NULL,NULL),(116,'IpAddress','IF-magesh-1870.csez.zohocorpin.com','IpAddress','IF-magesh-1870.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776227992,'',1478776227992,'ping','Interface',NULL,NULL,NULL),(117,'Node','magesh-1870.csez.zohocorpin.com','Node','magesh-1870.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776228107,'\0',1478776228107,'max','Node',NULL,NULL,NULL),(118,'IpAddress','IF-sandhya-3439.csez.zohocorpin.com','IpAddress','IF-sandhya-3439.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776233134,'',1478776233134,'ping','Interface',NULL,NULL,NULL),(119,'Node','sandhya-3439.csez.zohocorpin.com','Node','sandhya-3439.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776233354,'\0',1478776233354,'max','Node',NULL,NULL,NULL),(120,'IpAddress','IF-kavitha-1061.csez.zohocorpin.com','IpAddress','IF-kavitha-1061.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776238179,'',1478776238179,'ping','Interface',NULL,NULL,NULL),(121,'Node','kavitha-1061.csez.zohocorpin.com','Node','kavitha-1061.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776238411,'\0',1478776238411,'max','Node',NULL,NULL,NULL),(122,'IpAddress','IF-shyamallsiphone.csez.zohocorpin.com','IpAddress','IF-shyamallsiphone.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478256449901,'',1478776253831,'ping','Interface',NULL,NULL,NULL),(123,'Node','shyamallsiphone.csez.zohocorpin.com','Node','shyamallsiphone.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478256450133,'\0',1478256450133,'max','Node',NULL,NULL,NULL),(124,'IpAddress','IF-marutha-3402.csez.zohocorpin.com','IpAddress','IF-marutha-3402.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776248152,'',1478776248152,'ping','Interface',NULL,NULL,NULL),(125,'Node','marutha-3402.csez.zohocorpin.com','Node','marutha-3402.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776248279,'\0',1478776248279,'max','Node',NULL,NULL,NULL),(126,'IpAddress','IF-vijay-0596.csez.zohocorpin.com','IpAddress','IF-vijay-0596.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776253023,'',1478776253023,'ping','Interface',NULL,NULL,NULL),(127,'Node','vijay-0596.csez.zohocorpin.com','Node','vijay-0596.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776253146,'\0',1478776253146,'max','Node',NULL,NULL,NULL),(128,'IpAddress','IF-aravinth-3063.csez.zohocorpin.com','IpAddress','IF-aravinth-3063.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271513480,'',1478776268802,'ping','Interface',NULL,NULL,NULL),(129,'Node','aravinth-3063.csez.zohocorpin.com','Node','aravinth-3063.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271513745,'\0',1478271513745,'max','Node',NULL,NULL,NULL),(130,'IpAddress','IF-vaisali-4071.csez.zohocorpin.com','IpAddress','IF-vaisali-4071.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776262995,'',1478776262995,'ping','Interface',NULL,NULL,NULL),(131,'Node','vaisali-4071.csez.zohocorpin.com','Node','vaisali-4071.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776263123,'\0',1478776263123,'max','Node',NULL,NULL,NULL),(132,'IpAddress','IF-purushoth-3454.csez.zohocorpin.com','IpAddress','IF-purushoth-3454.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776268069,'',1478776268069,'ping','Interface',NULL,NULL,NULL),(133,'Node','purushoth-3454.csez.zohocorpin.com','Node','purushoth-3454.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776268290,'\0',1478776268290,'max','Node',NULL,NULL,NULL),(134,'IpAddress','IF-velan-es0007.csez.zohocorpin.com','IpAddress','IF-velan-es0007.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478260080031,'',1478776283830,'ping','Interface',NULL,NULL,NULL),(135,'Node','velan-es0007.csez.zohocorpin.com','Node','velan-es0007.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478260080253,'\0',1478260080253,'max','Node',NULL,NULL,NULL),(136,'IpAddress','IF-pavithra-3526.csez.zohocorpin.com','IpAddress','IF-pavithra-3526.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776278114,'',1478776278114,'ping','Interface',NULL,NULL,NULL),(137,'Node','pavithra-3526.csez.zohocorpin.com','Node','pavithra-3526.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776278229,'\0',1478776278229,'max','Node',NULL,NULL,NULL),(138,'IpAddress','IF-jerome-3929.csez.zohocorpin.com','IpAddress','IF-jerome-3929.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271535457,'',1478776293838,'ping','Interface',NULL,NULL,NULL),(139,'Node','jerome-3929.csez.zohocorpin.com','Node','jerome-3929.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271535588,'\0',1478271535588,'max','Node',NULL,NULL,NULL),(140,'IpAddress','IF-172.24.14.149','IpAddress','IF-172.24.14.149',0,1,'\0','\0','','NULL',3600,2,1478271546432,'',1478776298837,'ping','Interface',NULL,NULL,NULL),(141,'Node','172.24.14.149','Node','172.24.14.149',0,1,'\0','\0','','NULL',3600,2,1478271546667,'\0',1478271546667,'max','Node',NULL,NULL,NULL),(142,'IpAddress','IF-raji-0675.csez.zohocorpin.com','IpAddress','IF-raji-0675.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776293093,'',1478776293093,'ping','Interface',NULL,NULL,NULL),(143,'Node','raji-0675.csez.zohocorpin.com','Node','raji-0675.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776293314,'\0',1478776293314,'max','Node',NULL,NULL,NULL),(144,'IpAddress','IF-suresh-0665.csez.zohocorpin.com','IpAddress','IF-suresh-0665.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271535438,'',1478776308833,'ping','Interface',NULL,NULL,NULL),(145,'Node','suresh-0665.csez.zohocorpin.com','Node','suresh-0665.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271535570,'\0',1478271535570,'max','Node',NULL,NULL,NULL),(146,'IpAddress','IF-nirmal-2552.csez.zohocorpin.com','IpAddress','IF-nirmal-2552.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776303055,'',1478776303055,'ping','Interface',NULL,NULL,NULL),(147,'Node','nirmal-2552.csez.zohocorpin.com','Node','nirmal-2552.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776303276,'\0',1478776303276,'max','Node',NULL,NULL,NULL),(148,'IpAddress','IF-172.24.14.171','IpAddress','IF-172.24.14.171',0,1,'\0','\0','','NULL',3600,5,1478776308204,'',1478776308204,'ping','Interface',NULL,NULL,NULL),(149,'Node','172.24.14.171','Node','172.24.14.171',0,1,'\0','\0','','NULL',3600,5,1478776308436,'\0',1478776308436,'max','Node',NULL,NULL,NULL),(150,'IpAddress','IF-gramkumar-0817.csez.zohocorpin.com','IpAddress','IF-gramkumar-0817.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271557568,'',1478776323837,'ping','Interface',NULL,NULL,NULL),(151,'Node','gramkumar-0817.csez.zohocorpin.com','Node','gramkumar-0817.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271557803,'\0',1478271557803,'max','Node',NULL,NULL,NULL),(152,'IpAddress','IF-sundari-1712.csez.zohocorpin.com','IpAddress','IF-sundari-1712.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776318065,'',1478776318065,'ping','Interface',NULL,NULL,NULL),(153,'Node','sundari-1712.csez.zohocorpin.com','Node','sundari-1712.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776318288,'\0',1478776318288,'max','Node',NULL,NULL,NULL),(154,'IpAddress','IF-radhas-iphone-6.csez.zohocorpin.com','IpAddress','IF-radhas-iphone-6.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271724478,'',1478776333833,'ping','Interface',NULL,NULL,NULL),(155,'Node','radhas-iphone-6.csez.zohocorpin.com','Node','radhas-iphone-6.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271724779,'\0',1478271724779,'max','Node',NULL,NULL,NULL),(156,'IpAddress','IF-172.24.14.178','IpAddress','IF-172.24.14.178',0,1,'\0','\0','','NULL',3600,5,1478776328039,'',1478776328039,'ping','Interface',NULL,NULL,NULL),(157,'Node','172.24.14.178','Node','172.24.14.178',0,1,'\0','\0','','NULL',3600,5,1478776328272,'\0',1478776328272,'max','Node',NULL,NULL,NULL),(158,'IpAddress','IF-bhargavi-2458.csez.zohocorpin.com','IpAddress','IF-bhargavi-2458.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271713429,'',1478776343811,'ping','Interface',NULL,NULL,NULL),(159,'Node','bhargavi-2458.csez.zohocorpin.com','Node','bhargavi-2458.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271713573,'\0',1478271713573,'max','Node',NULL,NULL,NULL),(160,'IpAddress','IF-mali-0473.csez.zohocorpin.com','IpAddress','IF-mali-0473.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776338042,'',1478776338042,'ping','Interface',NULL,NULL,NULL),(161,'Node','mali-0473.csez.zohocorpin.com','Node','mali-0473.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776338276,'\0',1478776338276,'max','Node',NULL,NULL,NULL),(162,'IpAddress','IF-srivatsav-3642.csez.zohocorpin.com','IpAddress','IF-srivatsav-3642.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776343122,'',1478776343122,'ping','Interface',NULL,NULL,NULL),(163,'Node','srivatsav-3642.csez.zohocorpin.com','Node','srivatsav-3642.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776343342,'\0',1478776343342,'max','Node',NULL,NULL,NULL),(164,'IpAddress','IF-prameena-1188.csez.zohocorpin.com','IpAddress','IF-prameena-1188.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776348186,'',1478776348186,'ping','Interface',NULL,NULL,NULL),(165,'Node','prameena-1188.csez.zohocorpin.com','Node','prameena-1188.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776348414,'\0',1478776348414,'max','Node',NULL,NULL,NULL),(166,'IpAddress','IF-android-6da4f6e8432f2ea.csez.zohocorpin.com','IpAddress','IF-android-6da4f6e8432f2ea.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267363587,'',1478776363835,'ping','Interface',NULL,NULL,NULL),(167,'Node','android-6da4f6e8432f2ea.csez.zohocorpin.com','Node','android-6da4f6e8432f2ea.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267363808,'\0',1478267363808,'max','Node',NULL,NULL,NULL),(168,'IpAddress','IF-172.24.14.191','IpAddress','IF-172.24.14.191',0,1,'\0','\0','','NULL',3600,2,1478271724529,'',1478776368852,'ping','Interface',NULL,NULL,NULL),(169,'Node','172.24.14.191','Node','172.24.14.191',0,1,'\0','\0','','NULL',3600,2,1478271724849,'\0',1478271724849,'max','Node',NULL,NULL,NULL),(170,'IpAddress','IF-iphone-6-arivu.csez.zohocorpin.com','IpAddress','IF-iphone-6-arivu.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271757599,'',1478776373825,'ping','Interface',NULL,NULL,NULL),(171,'Node','iphone-6-arivu.csez.zohocorpin.com','Node','iphone-6-arivu.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271757867,'\0',1478271757867,'max','Node',NULL,NULL,NULL),(172,'IpAddress','IF-loga-zu396.csez.zohocorpin.com','IpAddress','IF-loga-zu396.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776368106,'',1478776368106,'ping','Interface',NULL,NULL,NULL),(173,'Node','loga-zu396.csez.zohocorpin.com','Node','loga-zu396.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776368337,'\0',1478776368337,'max','Node',NULL,NULL,NULL),(174,'IpAddress','IF-abdul-0436.csez.zohocorpin.com','IpAddress','IF-abdul-0436.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776373168,'',1478776373168,'ping','Interface',NULL,NULL,NULL),(175,'Node','abdul-0436.csez.zohocorpin.com','Node','abdul-0436.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776373493,'\0',1478776373493,'max','Node',NULL,NULL,NULL),(176,'IpAddress','IF-sathish-2784.csez.zohocorpin.com','IpAddress','IF-sathish-2784.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776378001,'',1478776378001,'ping','Interface',NULL,NULL,NULL),(177,'Node','sathish-2784.csez.zohocorpin.com','Node','sathish-2784.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776378224,'\0',1478776378224,'max','Node',NULL,NULL,NULL),(178,'IpAddress','IF-harini-zu360.csez.zohocorpin.com','IpAddress','IF-harini-zu360.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776383174,'',1478776383174,'ping','Interface',NULL,NULL,NULL),(179,'Node','harini-zu360.csez.zohocorpin.com','Node','harini-zu360.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776383396,'\0',1478776383396,'max','Node',NULL,NULL,NULL),(180,'IpAddress','IF-siddharth-4445.csez.zohocorpin.com','IpAddress','IF-siddharth-4445.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478256594948,'',1478776398821,'ping','Interface',NULL,NULL,NULL),(181,'Node','siddharth-4445.csez.zohocorpin.com','Node','siddharth-4445.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478256595174,'\0',1478256595174,'max','Node',NULL,NULL,NULL),(182,'IpAddress','IF-janaki-3684.csez.zohocorpin.com','IpAddress','IF-janaki-3684.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271757468,'',1478776403837,'ping','Interface',NULL,NULL,NULL),(183,'Node','janaki-3684.csez.zohocorpin.com','Node','janaki-3684.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271757728,'\0',1478271757728,'max','Node',NULL,NULL,NULL),(184,'IpAddress','IF-damodhar-1003.csez.zohocorpin.com','IpAddress','IF-damodhar-1003.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267406711,'',1478776408845,'ping','Interface',NULL,NULL,NULL),(185,'Node','damodhar-1003.csez.zohocorpin.com','Node','damodhar-1003.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478267406945,'\0',1478267406945,'max','Node',NULL,NULL,NULL),(186,'IpAddress','IF-suresh-1545.csez.zohocorpin.com','IpAddress','IF-suresh-1545.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271768667,'',1478776413851,'ping','Interface',NULL,NULL,NULL),(187,'Node','suresh-1545.csez.zohocorpin.com','Node','suresh-1545.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271768839,'\0',1478271768839,'max','Node',NULL,NULL,NULL),(188,'IpAddress','IF-santhanapreethi.csez.zohocorpin.com','IpAddress','IF-santhanapreethi.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271768811,'',1478776418860,'ping','Interface',NULL,NULL,NULL),(189,'Node','santhanapreethi.csez.zohocorpin.com','Node','santhanapreethi.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478271769093,'\0',1478271769093,'max','Node',NULL,NULL,NULL),(190,'IpAddress','IF-ajay-1385.csez.zohocorpin.com','IpAddress','IF-ajay-1385.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478263821704,'',1478776423825,'ping','Interface',NULL,NULL,NULL),(191,'Node','ajay-1385.csez.zohocorpin.com','Node','ajay-1385.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478263821929,'\0',1478263821929,'max','Node',NULL,NULL,NULL),(192,'IpAddress','IF-drevathy-0847.csez.zohocorpin.com','IpAddress','IF-drevathy-0847.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478272032398,'',1478776428811,'ping','Interface',NULL,NULL,NULL),(193,'Node','drevathy-0847.csez.zohocorpin.com','Node','drevathy-0847.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478272032623,'\0',1478272032623,'max','Node',NULL,NULL,NULL),(194,'IpAddress','IF-172.24.14.67','IpAddress','IF-172.24.14.67',0,1,'\0','\0','','NULL',3600,5,1478776423048,'',1478776423048,'ping','Interface',NULL,NULL,NULL),(195,'ChassisDevice','172.24.14.67xx0','ChassisDevice','172.24.14.67xx0',0,1,'\0','\0','','NULL',1800,5,1478256367536,'',1478778227813,'ping','',NULL,NULL,NULL),(196,'Node','172.24.14.67','Node','172.24.14.67',0,1,'\0','\0','','NULL',3600,5,1478776423272,'\0',1478776423272,'max','Node',NULL,NULL,NULL),(197,'IpAddress','IF-admp-test1.csez.zohocorpin.com','IpAddress','IF-admp-test1.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776433133,'',1478776433133,'ping','Interface',NULL,NULL,NULL),(198,'ChassisDevice','admp-test1.csez.zohocorpin.comxx1','ChassisDevice','admp-test1.csez.zohocorpin.comxx1',0,1,'\0','\0','','NULL',1800,5,1478256389892,'',1478778237809,'ping','',NULL,NULL,NULL),(199,'Node','admp-test1.csez.zohocorpin.com','Node','admp-test1.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776433354,'\0',1478776433354,'max','Node',NULL,NULL,NULL),(200,'IpAddress','IF-meena.csez.zohocorpin.com','IpAddress','IF-meena.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776443106,'',1478776443106,'ping','Interface',NULL,NULL,NULL),(201,'ChassisDevice','meena.csez.zohocorpin.comxx2','ChassisDevice','meena.csez.zohocorpin.comxx2',0,1,'\0','\0','','NULL',1800,5,1478256511643,'',1478778247805,'ping','',NULL,NULL,NULL),(202,'Node','meena.csez.zohocorpin.com','Node','meena.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776443327,'\0',1478776443327,'max','Node',NULL,NULL,NULL),(203,'IpAddress','IF-preethi2siphone.csez.zohocorpin.com','IpAddress','IF-preethi2siphone.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478260180433,'',1478776463833,'ping','Interface',NULL,NULL,NULL),(204,'ChassisDevice','preethi2siphone.csez.zohocorpin.comxx3','ChassisDevice','preethi2siphone.csez.zohocorpin.comxx3',0,1,'\0','\0','','NULL',1800,5,1478256569234,'',1478778257809,'ping','',NULL,NULL,NULL),(205,'Node','preethi2siphone.csez.zohocorpin.com','Node','preethi2siphone.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478260180548,'\0',1478260180548,'max','Node',NULL,NULL,NULL),(206,'IpAddress','IF-172.24.14.115','IpAddress','IF-172.24.14.115',0,1,'\0','\0','','NULL',3600,2,1478267392062,'',1478776473812,'ping','Interface',NULL,NULL,NULL),(207,'ChassisDevice','172.24.14.115xx4','ChassisDevice','172.24.14.115xx4',0,1,'\0','\0','','NULL',1800,5,1478256580531,'',1478778267806,'ping','',NULL,NULL,NULL),(208,'Node','172.24.14.115','Node','172.24.14.115',0,1,'\0','\0','','NULL',3600,2,1478267392296,'\0',1478267392296,'max','Node',NULL,NULL,NULL),(209,'IpAddress','IF-hemanth-3818.csez.zohocorpin.com','IpAddress','IF-hemanth-3818.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478272043543,'',1478776483860,'ping','Interface',NULL,NULL,NULL),(210,'ChassisDevice','hemanth-3818.csez.zohocorpin.comxx5','ChassisDevice','hemanth-3818.csez.zohocorpin.comxx5',0,1,'\0','\0','','NULL',1800,5,1478256779817,'',1478778277799,'ping','',NULL,NULL,NULL),(211,'Node','hemanth-3818.csez.zohocorpin.com','Node','hemanth-3818.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478272043803,'\0',1478272043803,'max','Node',NULL,NULL,NULL),(212,'IpAddress','IF-172.24.14.154','IpAddress','IF-172.24.14.154',0,1,'\0','\0','','NULL',3600,2,1478260424410,'',1478776493822,'ping','Interface',NULL,NULL,NULL),(213,'ChassisDevice','172.24.14.154xx6','ChassisDevice','172.24.14.154xx6',0,1,'\0','\0','','NULL',1800,5,1478256813205,'',1478778287806,'ping','',NULL,NULL,NULL),(214,'Node','172.24.14.154','Node','172.24.14.154',0,1,'\0','\0','','NULL',3600,2,1478260424526,'\0',1478260424526,'max','Node',NULL,NULL,NULL),(215,'IpAddress','IF-172.24.14.152','IpAddress','IF-172.24.14.152',0,1,'\0','\0','','NULL',3600,5,1478776493429,'',1478776493429,'ping','Interface',NULL,NULL,NULL),(216,'ChassisDevice','172.24.14.152xx7','ChassisDevice','172.24.14.152xx7',0,1,'\0','\0','','NULL',1800,5,1478256813444,'',1478778297805,'ping','',NULL,NULL,NULL),(217,'Node','172.24.14.152','Node','172.24.14.152',0,1,'\0','\0','','NULL',3600,5,1478776493654,'\0',1478776493654,'max','Node',NULL,NULL,NULL),(218,'IpAddress','IF-arivalagan-2168.csez.zohocorpin.com','IpAddress','IF-arivalagan-2168.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776503084,'',1478776503084,'ping','Interface',NULL,NULL,NULL),(219,'ChassisDevice','arivalagan-2168.csez.zohocorpin.comxx8','ChassisDevice','arivalagan-2168.csez.zohocorpin.comxx8',0,1,'\0','\0','','NULL',1800,5,1478256879868,'',1478778307805,'ping','',NULL,NULL,NULL),(220,'Node','arivalagan-2168.csez.zohocorpin.com','Node','arivalagan-2168.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776503316,'\0',1478776503316,'max','Node',NULL,NULL,NULL),(221,'IpAddress','IF-aravinds6splus.csez.zohocorpin.com','IpAddress','IF-aravinds6splus.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776513254,'',1478776513254,'ping','Interface',NULL,NULL,NULL),(222,'ChassisDevice','aravinds6splus.csez.zohocorpin.comxx9','ChassisDevice','aravinds6splus.csez.zohocorpin.comxx9',0,1,'\0','\0','','NULL',1800,5,1478256979586,'',1478778317807,'ping','',NULL,NULL,NULL),(223,'Node','aravinds6splus.csez.zohocorpin.com','Node','aravinds6splus.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776513486,'\0',1478776513486,'max','Node',NULL,NULL,NULL),(224,'IpAddress','IF-arun-2286.csez.zohocorpin.com','IpAddress','IF-arun-2286.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776523126,'',1478776523126,'ping','Interface',NULL,NULL,NULL),(225,'ChassisDevice','arun-2286.csez.zohocorpin.comxx10','ChassisDevice','arun-2286.csez.zohocorpin.comxx10',0,1,'\0','\0','','NULL',1800,5,1478257158481,'',1478778327809,'ping','',NULL,NULL,NULL),(226,'Node','arun-2286.csez.zohocorpin.com','Node','arun-2286.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776523353,'\0',1478776523353,'max','Node',NULL,NULL,NULL),(227,'IpAddress','IF-kalai-0041.csez.zohocorpin.com','IpAddress','IF-kalai-0041.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776533143,'',1478776533143,'ping','Interface',NULL,NULL,NULL),(228,'ChassisDevice','kalai-0041.csez.zohocorpin.comxx11','ChassisDevice','kalai-0041.csez.zohocorpin.comxx11',0,1,'\0','\0','','NULL',1800,5,1478257158690,'',1478778337807,'ping','',NULL,NULL,NULL),(229,'Node','kalai-0041.csez.zohocorpin.com','Node','kalai-0041.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776533469,'\0',1478776533469,'max','Node',NULL,NULL,NULL),(230,'IpAddress','IF-172.24.14.226','IpAddress','IF-172.24.14.226',0,1,'\0','\0','','NULL',3600,5,1478776542986,'',1478776542986,'ping','Interface',NULL,NULL,NULL),(231,'ChassisDevice','172.24.14.226xx12','ChassisDevice','172.24.14.226xx12',0,1,'\0','\0','','NULL',1800,5,1478257203309,'',1478778347803,'ping','',NULL,NULL,NULL),(232,'Node','172.24.14.226','Node','172.24.14.226',0,1,'\0','\0','','NULL',3600,5,1478776543210,'\0',1478776543210,'max','Node',NULL,NULL,NULL),(233,'IpAddress','IF-android-8f4bc429763adb11.csez.zohocorpin.com','IpAddress','IF-android-8f4bc429763adb11.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478260814545,'',1478776563846,'ping','Interface',NULL,NULL,NULL),(234,'ChassisDevice','android-8f4bc429763adb11.csez.zohocorpin.comxx13','ChassisDevice','android-8f4bc429763adb11.csez.zohocorpin.comxx13',0,1,'\0','\0','','NULL',1800,5,1478257203453,'',1478778357809,'ping','',NULL,NULL,NULL),(235,'Node','android-8f4bc429763adb11.csez.zohocorpin.com','Node','android-8f4bc429763adb11.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478260814663,'\0',1478260814663,'max','Node',NULL,NULL,NULL),(236,'IpAddress','IF-172.24.14.238','IpAddress','IF-172.24.14.238',0,1,'\0','\0','','NULL',3600,5,1478776563015,'',1478776563015,'ping','Interface',NULL,NULL,NULL),(237,'ChassisDevice','172.24.14.238xx14','ChassisDevice','172.24.14.238xx14',0,1,'\0','\0','','NULL',1800,5,1478257226469,'',1478778367818,'ping','',NULL,NULL,NULL),(238,'Node','172.24.14.238','Node','172.24.14.238',0,1,'\0','\0','','NULL',3600,5,1478776563235,'\0',1478776563235,'max','Node',NULL,NULL,NULL),(239,'IpAddress','IF-android-6d556f7960b13da.csez.zohocorpin.com','IpAddress','IF-android-6d556f7960b13da.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478272087701,'',1478776583844,'ping','Interface',NULL,NULL,NULL),(240,'ChassisDevice','android-6d556f7960b13da.csez.zohocorpin.comxx15','ChassisDevice','android-6d556f7960b13da.csez.zohocorpin.comxx15',0,1,'\0','\0','','NULL',1800,5,1478257227220,'',1478778377815,'ping','',NULL,NULL,NULL),(241,'Node','android-6d556f7960b13da.csez.zohocorpin.com','Node','android-6d556f7960b13da.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478272087974,'\0',1478272087974,'max','Node',NULL,NULL,NULL),(242,'IpAddress','IF-arun-3857.csez.zohocorpin.com','IpAddress','IF-arun-3857.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776583042,'',1478776583042,'ping','Interface',NULL,NULL,NULL),(243,'ChassisDevice','arun-3857.csez.zohocorpin.comxx16','ChassisDevice','arun-3857.csez.zohocorpin.comxx16',0,1,'\0','\0','','NULL',1800,5,1478257227494,'',1478778387805,'ping','',NULL,NULL,NULL),(244,'Node','arun-3857.csez.zohocorpin.com','Node','arun-3857.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776583262,'\0',1478776583262,'max','Node',NULL,NULL,NULL),(245,'IpAddress','IF-varun-2246.csez.zohocorpin.com','IpAddress','IF-varun-2246.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776593027,'',1478776593027,'ping','Interface',NULL,NULL,NULL),(246,'ChassisDevice','varun-2246.csez.zohocorpin.comxx17','ChassisDevice','varun-2246.csez.zohocorpin.comxx17',0,1,'\0','\0','','NULL',1800,5,1478257227688,'',1478778397818,'ping','',NULL,NULL,NULL),(247,'Node','varun-2246.csez.zohocorpin.com','Node','varun-2246.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478266983777,'\0',1478776593035,'max','Node',NULL,NULL,NULL),(248,'IpAddress','IF-dheeraj-1090.csez.zohocorpin.com','IpAddress','IF-dheeraj-1090.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776603116,'',1478776603116,'ping','Interface',NULL,NULL,NULL),(249,'ChassisDevice','dheeraj-1090.csez.zohocorpin.comxx18','ChassisDevice','dheeraj-1090.csez.zohocorpin.comxx18',0,1,'\0','\0','','NULL',1800,5,1478257239251,'',1478778407809,'ping','',NULL,NULL,NULL),(250,'Node','dheeraj-1090.csez.zohocorpin.com','Node','dheeraj-1090.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776603243,'\0',1478776603243,'max','Node',NULL,NULL,NULL),(251,'IpAddress','IF-pav.csez.zohocorpin.com','IpAddress','IF-pav.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776613226,'',1478776613226,'ping','Interface',NULL,NULL,NULL),(252,'ChassisDevice','pav.csez.zohocorpin.comxx19','ChassisDevice','pav.csez.zohocorpin.comxx19',0,1,'\0','\0','','NULL',1800,5,1478257272597,'',1478778417810,'ping','',NULL,NULL,NULL),(253,'Node','pav.csez.zohocorpin.com','Node','pav.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776613453,'\0',1478776613453,'max','Node',NULL,NULL,NULL),(254,'IpAddress','IF-anu-4114.csez.zohocorpin.com','IpAddress','IF-anu-4114.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776623177,'',1478776623177,'ping','Interface',NULL,NULL,NULL),(255,'ChassisDevice','anu-4114.csez.zohocorpin.comxx20','ChassisDevice','anu-4114.csez.zohocorpin.comxx20',0,1,'\0','\0','','NULL',1800,5,1478257272775,'',1478778427818,'ping','',NULL,NULL,NULL),(256,'Node','anu-4114.csez.zohocorpin.com','Node','anu-4114.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776623398,'\0',1478776623398,'max','Node',NULL,NULL,NULL),(257,'IpAddress','IF-sabarna-4236.csez.zohocorpin.com','IpAddress','IF-sabarna-4236.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776633150,'',1478776633150,'ping','Interface',NULL,NULL,NULL),(258,'ChassisDevice','sabarna-4236.csez.zohocorpin.comxx21','ChassisDevice','sabarna-4236.csez.zohocorpin.comxx21',0,1,'\0','\0','','NULL',1800,5,1478257272952,'',1478778437814,'ping','',NULL,NULL,NULL),(259,'Node','sabarna-4236.csez.zohocorpin.com','Node','sabarna-4236.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776633371,'\0',1478776633371,'max','Node',NULL,NULL,NULL),(260,'IpAddress','IF-ullas-4059.csez.zohocorpin.com','IpAddress','IF-ullas-4059.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478272177392,'',1478776653841,'ping','Interface',NULL,NULL,NULL),(261,'ChassisDevice','ullas-4059.csez.zohocorpin.comxx22','ChassisDevice','ullas-4059.csez.zohocorpin.comxx22',0,1,'\0','\0','','NULL',1800,5,1478257285882,'',1478776647802,'ping','',NULL,NULL,NULL),(262,'Node','ullas-4059.csez.zohocorpin.com','Node','ullas-4059.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478272177626,'\0',1478272177626,'max','Node',NULL,NULL,NULL),(263,'IpAddress','IF-nj.csez.zohocorpin.com','IpAddress','IF-nj.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478272177403,'',1478776663822,'ping','Interface',NULL,NULL,NULL),(264,'ChassisDevice','nj.csez.zohocorpin.comxx23','ChassisDevice','nj.csez.zohocorpin.comxx23',0,1,'\0','\0','','NULL',1800,5,1478257320036,'',1478776657807,'ping','',NULL,NULL,NULL),(265,'Node','nj.csez.zohocorpin.com','Node','nj.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,2,1478272177758,'\0',1478272177758,'max','Node',NULL,NULL,NULL),(266,'IpAddress','IF-mithun-0445.csez.zohocorpin.com','IpAddress','IF-mithun-0445.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776663127,'',1478776663127,'ping','Interface',NULL,NULL,NULL),(267,'ChassisDevice','mithun-0445.csez.zohocorpin.comxx24','ChassisDevice','mithun-0445.csez.zohocorpin.comxx24',0,1,'\0','\0','','NULL',1800,5,1478257342294,'',1478776667810,'ping','',NULL,NULL,NULL),(268,'Node','mithun-0445.csez.zohocorpin.com','Node','mithun-0445.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776663247,'\0',1478776663247,'max','Node',NULL,NULL,NULL),(269,'IpAddress','IF-ram-3371.csez.zohocorpin.com','IpAddress','IF-ram-3371.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776673131,'',1478776673131,'ping','Interface',NULL,NULL,NULL),(270,'ChassisDevice','ram-3371.csez.zohocorpin.comxx25','ChassisDevice','ram-3371.csez.zohocorpin.comxx25',0,1,'\0','\0','','NULL',1800,5,1478257342539,'',1478776677800,'ping','',NULL,NULL,NULL),(271,'Node','ram-3371.csez.zohocorpin.com','Node','ram-3371.csez.zohocorpin.com',0,1,'\0','\0','','NULL',3600,5,1478776673362,'\0',1478776673362,'max','Node',NULL,NULL,NULL),(272,'IpAddress','IF-172.24.14.252','IpAddress','IF-172.24.14.252',0,1,'\0','\0','','NULL',3600,5,1478776683248,'',1478776683248,'ping','Interface',NULL,NULL,NULL),(273,'ChassisDevice','172.24.14.252xx26','ChassisDevice','172.24.14.252xx26',0,1,'\0','\0','','NULL',1800,5,1478257353986,'',1478776687808,'ping','',NULL,NULL,NULL),(274,'Node','172.24.14.252','Node','172.24.14.252',0,1,'\0','\0','','NULL',3600,5,1478776683572,'\0',1478776683572,'max','Node',NULL,NULL,NULL);
/*!40000 ALTER TABLE `ManagedObject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MapContainer`
--

DROP TABLE IF EXISTS `MapContainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MapContainer` (
  `NAME` varchar(100) NOT NULL,
  `MAPNAME` varchar(100) NOT NULL,
  `CURRENTTOPOLOGY` varchar(100) DEFAULT NULL,
  `TOPOLOGY` varchar(100) DEFAULT NULL,
  `CONTAINMENT` bit(1) DEFAULT NULL,
  PRIMARY KEY (`NAME`,`MAPNAME`),
  KEY `FKFA2B62A5334AE2DB` (`NAME`,`MAPNAME`),
  CONSTRAINT `FKFA2B62A5334AE2DB` FOREIGN KEY (`NAME`, `MAPNAME`) REFERENCES `MapSymbol` (`NAME`, `MAPNAME`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MapContainer`
--

LOCK TABLES `MapContainer` WRITE;
/*!40000 ALTER TABLE `MapContainer` DISABLE KEYS */;
/*!40000 ALTER TABLE `MapContainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MapDB`
--

DROP TABLE IF EXISTS `MapDB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MapDB` (
  `NAME` varchar(100) NOT NULL,
  `PARENT` varchar(100) DEFAULT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  `MENUNAME` varchar(100) DEFAULT NULL,
  `IMAGENAME` varchar(100) DEFAULT NULL,
  `TREEICONFILENAME` varchar(100) DEFAULT NULL,
  `AUTOPLACEMENT` bit(1) DEFAULT NULL,
  `MAPSYMBOLRENDERER` varchar(150) DEFAULT NULL,
  `MAPLINKRENDERER` varchar(150) DEFAULT NULL,
  `HELPDOC` varchar(100) DEFAULT NULL,
  `TOPOLOGY` varchar(100) DEFAULT NULL,
  `CURRENTTOPOLOGY` varchar(100) DEFAULT NULL,
  `WEBNMS` varchar(100) DEFAULT NULL,
  `GROUPNAME` varchar(100) DEFAULT NULL,
  `MAPLISTENER` varchar(100) DEFAULT NULL,
  `ANCHORED` bit(1) DEFAULT NULL,
  `TYPE` varchar(100) DEFAULT NULL,
  `TABPANELS` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MapDB`
--

LOCK TABLES `MapDB` WRITE;
/*!40000 ALTER TABLE `MapDB` DISABLE KEYS */;
INSERT INTO `MapDB` VALUES ('172.24.14.0.netmap',NULL,'172.24.14.0',NULL,NULL,'images/ipn.png','','{\"html\":\"symbol_label\",\"java\":\"com.adventnet.nms.mapui.EthernetMapSymbolRenderer\"}','{\"html\":\"line\",\"java\":\"com.adventnet.nms.mapui.EthernetMapLinkRenderer\"}','default_impl/map_filters/README.html','$grid(width=55;height=55;gapX=120;gapY=130;scroll=vertical)','grid',NULL,NULL,NULL,'\0','DefaultMap',''),('172.24.14.0Failed_Objects_Map.netmap','172.24.14.0.netmap','Failed Systems','failedobjectsmenu',NULL,'images/burst.png','','{\"html\":\"symbol_label\",\"java\":\"com.adventnet.nms.mapui.MapSymbolRendererImpl_4\"}',NULL,'help/user_guide/app_ui/maps/map_details/appui_map_details.html','grid(width=60;height=60;gapX=140;gapY=140),star,ring,flow','grid',NULL,NULL,NULL,'\0','CustomMap',''),('Failed_Objects_Map.netmap',NULL,'Failed Systems','failedobjectsmenu',NULL,'images/burst.png','','{\"html\"=\"symbol_label\",\"java\"=\"com.adventnet.nms.mapui.MapSymbolRendererImpl_4\"}',NULL,'help/user_guide/app_ui/maps/map_details/appui_map_details_symbol.html','grid(width=5;height=7;gapX=13;gapY=15;scroll=vertical;isPercentage=true),star,ring,flow','grid',NULL,NULL,NULL,'\0','CustomMap',''),('ipnet.netmap',NULL,'ipnet',NULL,'worldmap_2.png','images/ipnet_small.png','','{\"html\":\"symbol_label\",\"java\":\"com.adventnet.nms.mapui.MapSymbolRendererImpl_5\"}','{\"html\":\"curved\",\"java\":\"com.adventnet.nms.mapui.MapLinkRendererImpl_3\"}','default_impl/map_filters/README.html','grid,star,ring,flow,3Dimension(linkOverSymbol=true)','3Dimension',NULL,NULL,NULL,'\0','DefaultMap','');
/*!40000 ALTER TABLE `MapDB` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MapGroup`
--

DROP TABLE IF EXISTS `MapGroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MapGroup` (
  `NAME` varchar(100) NOT NULL,
  `MAPNAME` varchar(100) NOT NULL,
  PRIMARY KEY (`NAME`,`MAPNAME`),
  KEY `FKD33BEA36F4BC0D9` (`NAME`,`MAPNAME`),
  CONSTRAINT `FKD33BEA36F4BC0D9` FOREIGN KEY (`NAME`, `MAPNAME`) REFERENCES `MapSymbol` (`NAME`, `MAPNAME`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MapGroup`
--

LOCK TABLES `MapGroup` WRITE;
/*!40000 ALTER TABLE `MapGroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `MapGroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MapLink`
--

DROP TABLE IF EXISTS `MapLink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MapLink` (
  `NAME` varchar(100) NOT NULL,
  `MAPNAME` varchar(100) NOT NULL,
  `SOURCE` varchar(100) DEFAULT NULL,
  `DEST` varchar(100) DEFAULT NULL,
  `THICKNESS` int(11) DEFAULT NULL,
  `LINKTYPE` int(11) DEFAULT NULL,
  `NX` int(11) DEFAULT NULL,
  `NY` int(11) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME`,`MAPNAME`),
  KEY `FK951453561311A184` (`NAME`,`MAPNAME`),
  CONSTRAINT `FK951453561311A184` FOREIGN KEY (`NAME`, `MAPNAME`) REFERENCES `MapSymbol` (`NAME`, `MAPNAME`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MapLink`
--

LOCK TABLES `MapLink` WRITE;
/*!40000 ALTER TABLE `MapLink` DISABLE KEYS */;
/*!40000 ALTER TABLE `MapLink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MapSymbol`
--

DROP TABLE IF EXISTS `MapSymbol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MapSymbol` (
  `NAME` varchar(100) NOT NULL,
  `MAPNAME` varchar(100) NOT NULL,
  `DISCRIMINATOR` varchar(30) NOT NULL,
  `OBJNAME` varchar(100) DEFAULT NULL,
  `LABEL` varchar(100) DEFAULT NULL,
  `ICONNAME` varchar(100) DEFAULT NULL,
  `MENUNAME` varchar(100) DEFAULT NULL,
  `WIDTH` int(11) DEFAULT NULL,
  `HEIGHT` int(11) DEFAULT NULL,
  `X` int(11) DEFAULT NULL,
  `Y` int(11) DEFAULT NULL,
  `OBJTYPE` int(11) DEFAULT NULL,
  `WEBNMS` varchar(100) DEFAULT NULL,
  `GROUPNAME` varchar(100) DEFAULT NULL,
  `PARENTNAME` varchar(100) DEFAULT NULL,
  `ANCHORED` bit(1) DEFAULT NULL,
  `MAPWIDTH` int(11) DEFAULT NULL,
  `MAPHEIGHT` int(11) DEFAULT NULL,
  PRIMARY KEY (`NAME`,`MAPNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MapSymbol`
--

LOCK TABLES `MapSymbol` WRITE;
/*!40000 ALTER TABLE `MapSymbol` DISABLE KEYS */;
INSERT INTO `MapSymbol` VALUES ('172.24.14.0','Failed_Objects_Map.netmap','MapSymbol','172.24.14.0','172.24.14.0',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.0','ipnet.netmap','MapSymbol','172.24.14.0','172.24.14.0','ip.png',NULL,-1,-1,-1,-1,2,'','',NULL,'\0',0,0),('172.24.14.105','172.24.14.0.netmap','MapSymbol','172.24.14.105','172.24.14.105',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.112','172.24.14.0.netmap','MapSymbol','172.24.14.112','172.24.14.112',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.112','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','172.24.14.112','172.24.14.112',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.112','Failed_Objects_Map.netmap','MapSymbol','172.24.14.112','172.24.14.112',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.115','172.24.14.0.netmap','MapSymbol','172.24.14.115','172.24.14.115',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.115','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','172.24.14.115','172.24.14.115',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.115','Failed_Objects_Map.netmap','MapSymbol','172.24.14.115','172.24.14.115',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.149','172.24.14.0.netmap','MapSymbol','172.24.14.149','172.24.14.149',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.149','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','172.24.14.149','172.24.14.149',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.149','Failed_Objects_Map.netmap','MapSymbol','172.24.14.149','172.24.14.149',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.152','172.24.14.0.netmap','MapSymbol','172.24.14.152','172.24.14.152',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.154','172.24.14.0.netmap','MapSymbol','172.24.14.154','172.24.14.154',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.154','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','172.24.14.154','172.24.14.154',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.154','Failed_Objects_Map.netmap','MapSymbol','172.24.14.154','172.24.14.154',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.171','172.24.14.0.netmap','MapSymbol','172.24.14.171','172.24.14.171',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.178','172.24.14.0.netmap','MapSymbol','172.24.14.178','172.24.14.178',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.191','172.24.14.0.netmap','MapSymbol','172.24.14.191','172.24.14.191',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.191','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','172.24.14.191','172.24.14.191',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.191','Failed_Objects_Map.netmap','MapSymbol','172.24.14.191','172.24.14.191',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.226','172.24.14.0.netmap','MapSymbol','172.24.14.226','172.24.14.226',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.238','172.24.14.0.netmap','MapSymbol','172.24.14.238','172.24.14.238',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.252','172.24.14.0.netmap','MapSymbol','172.24.14.252','172.24.14.252',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.28','172.24.14.0.netmap','MapSymbol','172.24.14.28','172.24.14.28',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.56','172.24.14.0.netmap','MapSymbol','172.24.14.56','172.24.14.56',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.67','172.24.14.0.netmap','MapSymbol','172.24.14.67','172.24.14.67',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.84','172.24.14.0.netmap','MapSymbol','172.24.14.84','172.24.14.84',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('172.24.14.92','172.24.14.0.netmap','MapSymbol','172.24.14.92','172.24.14.92',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('192.168.220.0','ipnet.netmap','MapSymbol','192.168.220.0','192.168.220.0','ip.png',NULL,-1,-1,-1,-1,2,'','',NULL,'\0',0,0),('abdul-0436.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','abdul-0436.csez.zohocorpin.com','abdul-0436.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('abdul-zt24.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','abdul-zt24.csez.zohocorpin.com','abdul-zt24.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('abdul-zt24.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','abdul-zt24.csez.zohocorpin.com','abdul-zt24.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('abdul-zt24.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','abdul-zt24.csez.zohocorpin.com','abdul-zt24.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('abhi-3378.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','abhi-3378.csez.zohocorpin.com','abhi-3378.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('abhi-3378.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','abhi-3378.csez.zohocorpin.com','abhi-3378.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('abhi-3378.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','abhi-3378.csez.zohocorpin.com','abhi-3378.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('admp-test1.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','admp-test1.csez.zohocorpin.com','admp-test1.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ajay-1385.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','ajay-1385.csez.zohocorpin.com','ajay-1385.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ajay-1385.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','ajay-1385.csez.zohocorpin.com','ajay-1385.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ajay-1385.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','ajay-1385.csez.zohocorpin.com','ajay-1385.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('amarnath-0642.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','amarnath-0642.csez.zohocorpin.com','amarnath-0642.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('amarnath-0642.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','amarnath-0642.csez.zohocorpin.com','amarnath-0642.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('amarnath-0642.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','amarnath-0642.csez.zohocorpin.com','amarnath-0642.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('amritha-3867.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','amritha-3867.csez.zohocorpin.com','amritha-3867.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-6d556f7960b13da.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','android-6d556f7960b13da.csez.zohocorpin.com','android-6d556f7960b13da.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-6d556f7960b13da.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','android-6d556f7960b13da.csez.zohocorpin.com','android-6d556f7960b13da.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-6d556f7960b13da.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','android-6d556f7960b13da.csez.zohocorpin.com','android-6d556f7960b13da.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-6da4f6e8432f2ea.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','android-6da4f6e8432f2ea.csez.zohocorpin.com','android-6da4f6e8432f2ea.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-6da4f6e8432f2ea.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','android-6da4f6e8432f2ea.csez.zohocorpin.com','android-6da4f6e8432f2ea.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-6da4f6e8432f2ea.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','android-6da4f6e8432f2ea.csez.zohocorpin.com','android-6da4f6e8432f2ea.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-8f4bc429763adb11.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','android-8f4bc429763adb11.csez.zohocorpin.com','android-8f4bc429763adb11.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-8f4bc429763adb11.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','android-8f4bc429763adb11.csez.zohocorpin.com','android-8f4bc429763adb11.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-8f4bc429763adb11.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','android-8f4bc429763adb11.csez.zohocorpin.com','android-8f4bc429763adb11.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-d9e08dc4ead46367.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','android-d9e08dc4ead46367.csez.zohocorpin.com','android-d9e08dc4ead46367.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-d9e08dc4ead46367.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','android-d9e08dc4ead46367.csez.zohocorpin.com','android-d9e08dc4ead46367.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('android-d9e08dc4ead46367.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','android-d9e08dc4ead46367.csez.zohocorpin.com','android-d9e08dc4ead46367.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('anu-4114.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','anu-4114.csez.zohocorpin.com','anu-4114.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('aravind-0717.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','aravind-0717.csez.zohocorpin.com','aravind-0717.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('aravinds6splus.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','aravinds6splus.csez.zohocorpin.com','aravinds6splus.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('aravinth-3063.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','aravinth-3063.csez.zohocorpin.com','aravinth-3063.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('aravinth-3063.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','aravinth-3063.csez.zohocorpin.com','aravinth-3063.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('aravinth-3063.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','aravinth-3063.csez.zohocorpin.com','aravinth-3063.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('arivalagan-2168.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','arivalagan-2168.csez.zohocorpin.com','arivalagan-2168.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('arun-2286.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','arun-2286.csez.zohocorpin.com','arun-2286.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('arun-3857.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','arun-3857.csez.zohocorpin.com','arun-3857.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('arunsubhash-0371.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','arunsubhash-0371.csez.zohocorpin.com','arunsubhash-0371.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ashish-4086.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','ashish-4086.csez.zohocorpin.com','ashish-4086.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('aswath-pt773.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','aswath-pt773.csez.zohocorpin.com','aswath-pt773.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('bala-2606.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','bala-2606.csez.zohocorpin.com','bala-2606.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('bala-2606.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','bala-2606.csez.zohocorpin.com','bala-2606.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('bala-2606.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','bala-2606.csez.zohocorpin.com','bala-2606.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('bharath-2679.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','bharath-2679.csez.zohocorpin.com','bharath-2679.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('bhargavi-2458.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','bhargavi-2458.csez.zohocorpin.com','bhargavi-2458.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('bhargavi-2458.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','bhargavi-2458.csez.zohocorpin.com','bhargavi-2458.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('bhargavi-2458.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','bhargavi-2458.csez.zohocorpin.com','bhargavi-2458.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('boobala-0048.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','boobala-0048.csez.zohocorpin.com','boobala-0048.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('chithu-0706.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','chithu-0706.csez.zohocorpin.com','chithu-0706.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('chithu-0706.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','chithu-0706.csez.zohocorpin.com','chithu-0706.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('chithu-0706.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','chithu-0706.csez.zohocorpin.com','chithu-0706.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('damodhar-1003.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','damodhar-1003.csez.zohocorpin.com','damodhar-1003.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('damodhar-1003.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','damodhar-1003.csez.zohocorpin.com','damodhar-1003.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('damodhar-1003.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','damodhar-1003.csez.zohocorpin.com','damodhar-1003.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('dheeraj-1090.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','dheeraj-1090.csez.zohocorpin.com','dheeraj-1090.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('drevathy-0847.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','drevathy-0847.csez.zohocorpin.com','drevathy-0847.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('drevathy-0847.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','drevathy-0847.csez.zohocorpin.com','drevathy-0847.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('drevathy-0847.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','drevathy-0847.csez.zohocorpin.com','drevathy-0847.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('gokul-3303.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','gokul-3303.csez.zohocorpin.com','gokul-3303.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('gramkumar-0817.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','gramkumar-0817.csez.zohocorpin.com','gramkumar-0817.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('gramkumar-0817.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','gramkumar-0817.csez.zohocorpin.com','gramkumar-0817.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('gramkumar-0817.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','gramkumar-0817.csez.zohocorpin.com','gramkumar-0817.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('guhan-3315.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','guhan-3315.csez.zohocorpin.com','guhan-3315.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('harini-zu360.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','harini-zu360.csez.zohocorpin.com','harini-zu360.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('hemanth-3818.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','hemanth-3818.csez.zohocorpin.com','hemanth-3818.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('hemanth-3818.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','hemanth-3818.csez.zohocorpin.com','hemanth-3818.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('hemanth-3818.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','hemanth-3818.csez.zohocorpin.com','hemanth-3818.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('iphone-6-arivu.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','iphone-6-arivu.csez.zohocorpin.com','iphone-6-arivu.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('iphone-6-arivu.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','iphone-6-arivu.csez.zohocorpin.com','iphone-6-arivu.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('iphone-6-arivu.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','iphone-6-arivu.csez.zohocorpin.com','iphone-6-arivu.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('iphone6plus565.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','iphone6plus565.csez.zohocorpin.com','iphone6plus565.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('iphone6plus565.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','iphone6plus565.csez.zohocorpin.com','iphone6plus565.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('iphone6plus565.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','iphone6plus565.csez.zohocorpin.com','iphone6plus565.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('iphone6usest185.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','iphone6usest185.csez.zohocorpin.com','iphone6usest185.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('iphone6usest185.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','iphone6usest185.csez.zohocorpin.com','iphone6usest185.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('iphone6usest185.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','iphone6usest185.csez.zohocorpin.com','iphone6usest185.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('janaki-3684.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','janaki-3684.csez.zohocorpin.com','janaki-3684.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('janaki-3684.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','janaki-3684.csez.zohocorpin.com','janaki-3684.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('janaki-3684.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','janaki-3684.csez.zohocorpin.com','janaki-3684.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('jerome-3929.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','jerome-3929.csez.zohocorpin.com','jerome-3929.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('jerome-3929.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','jerome-3929.csez.zohocorpin.com','jerome-3929.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('jerome-3929.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','jerome-3929.csez.zohocorpin.com','jerome-3929.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('jlatha-0972.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','jlatha-0972.csez.zohocorpin.com','jlatha-0972.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('jlatha-0972.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','jlatha-0972.csez.zohocorpin.com','jlatha-0972.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('jlatha-0972.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','jlatha-0972.csez.zohocorpin.com','jlatha-0972.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('jsangeetha-0849.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','jsangeetha-0849.csez.zohocorpin.com','jsangeetha-0849.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('kalai-0041.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','kalai-0041.csez.zohocorpin.com','kalai-0041.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('kavitha-1061.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','kavitha-1061.csez.zohocorpin.com','kavitha-1061.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ksenthil-0949.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','ksenthil-0949.csez.zohocorpin.com','ksenthil-0949.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ksenthil-0949.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','ksenthil-0949.csez.zohocorpin.com','ksenthil-0949.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ksenthil-0949.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','ksenthil-0949.csez.zohocorpin.com','ksenthil-0949.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('loga-zu396.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','loga-zu396.csez.zohocorpin.com','loga-zu396.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('macbook-pro.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','macbook-pro.csez.zohocorpin.com','macbook-pro.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('macbook-pro.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','macbook-pro.csez.zohocorpin.com','macbook-pro.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('macbook-pro.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','macbook-pro.csez.zohocorpin.com','macbook-pro.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('magesh-1870.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','magesh-1870.csez.zohocorpin.com','magesh-1870.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mali-0473.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','mali-0473.csez.zohocorpin.com','mali-0473.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mani-0702.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','mani-0702.csez.zohocorpin.com','mani-0702.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mani-0702.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','mani-0702.csez.zohocorpin.com','mani-0702.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mani-0702.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','mani-0702.csez.zohocorpin.com','mani-0702.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mani-0918.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','mani-0918.csez.zohocorpin.com','mani-0918.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mani-2209.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','mani-2209.csez.zohocorpin.com','mani-2209.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mani-2209.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','mani-2209.csez.zohocorpin.com','mani-2209.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mani-2209.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','mani-2209.csez.zohocorpin.com','mani-2209.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mani-4546.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','mani-4546.csez.zohocorpin.com','mani-4546.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('manimaran-1378.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','manimaran-1378.csez.zohocorpin.com','manimaran-1378.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('marutha-3402.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','marutha-3402.csez.zohocorpin.com','marutha-3402.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('meena.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','meena.csez.zohocorpin.com','meena.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mithun-0445.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','mithun-0445.csez.zohocorpin.com','mithun-0445.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mohasin-2851.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','mohasin-2851.csez.zohocorpin.com','mohasin-2851.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('mukil-4261.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','mukil-4261.csez.zohocorpin.com','mukil-4261.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('muni-0051.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','muni-0051.csez.zohocorpin.com','muni-0051.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('naga-3924.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','naga-3924.csez.zohocorpin.com','naga-3924.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('naga-3924.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','naga-3924.csez.zohocorpin.com','naga-3924.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('naga-3924.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','naga-3924.csez.zohocorpin.com','naga-3924.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('nirmal-2552.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','nirmal-2552.csez.zohocorpin.com','nirmal-2552.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('nj.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','nj.csez.zohocorpin.com','nj.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('nj.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','nj.csez.zohocorpin.com','nj.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('nj.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','nj.csez.zohocorpin.com','nj.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('padma-4271.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','padma-4271.csez.zohocorpin.com','padma-4271.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('pandi-1824.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','pandi-1824.csez.zohocorpin.com','pandi-1824.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('partha-3525.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','partha-3525.csez.zohocorpin.com','partha-3525.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('pav.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','pav.csez.zohocorpin.com','pav.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('pavithra-3526.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','pavithra-3526.csez.zohocorpin.com','pavithra-3526.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('prameena-1188.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','prameena-1188.csez.zohocorpin.com','prameena-1188.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('preethi2siphone.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','preethi2siphone.csez.zohocorpin.com','preethi2siphone.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('preethi2siphone.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','preethi2siphone.csez.zohocorpin.com','preethi2siphone.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('preethi2siphone.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','preethi2siphone.csez.zohocorpin.com','preethi2siphone.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('priya-zutk58.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','priya-zutk58.csez.zohocorpin.com','priya-zutk58.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('priya-zutk58.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','priya-zutk58.csez.zohocorpin.com','priya-zutk58.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('priya-zutk58.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','priya-zutk58.csez.zohocorpin.com','priya-zutk58.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('purushoth-3454.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','purushoth-3454.csez.zohocorpin.com','purushoth-3454.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('radhas-iphone-6.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','radhas-iphone-6.csez.zohocorpin.com','radhas-iphone-6.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('radhas-iphone-6.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','radhas-iphone-6.csez.zohocorpin.com','radhas-iphone-6.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('radhas-iphone-6.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','radhas-iphone-6.csez.zohocorpin.com','radhas-iphone-6.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('raj-3842.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','raj-3842.csez.zohocorpin.com','raj-3842.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('rajesh-2755.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','rajesh-2755.csez.zohocorpin.com','rajesh-2755.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('rajesh-2755.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','rajesh-2755.csez.zohocorpin.com','rajesh-2755.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('rajesh-2755.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','rajesh-2755.csez.zohocorpin.com','rajesh-2755.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('raji-0675.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','raji-0675.csez.zohocorpin.com','raji-0675.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('rakesh-3889.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','rakesh-3889.csez.zohocorpin.com','rakesh-3889.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('rakesh-3889.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','rakesh-3889.csez.zohocorpin.com','rakesh-3889.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('rakesh-3889.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','rakesh-3889.csez.zohocorpin.com','rakesh-3889.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ram-3371.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','ram-3371.csez.zohocorpin.com','ram-3371.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('rejoe-0162.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','rejoe-0162.csez.zohocorpin.com','rejoe-0162.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('rejoe-0162.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','rejoe-0162.csez.zohocorpin.com','rejoe-0162.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('rejoe-0162.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','rejoe-0162.csez.zohocorpin.com','rejoe-0162.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('sabarna-4236.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','sabarna-4236.csez.zohocorpin.com','sabarna-4236.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('sai-3130.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','sai-3130.csez.zohocorpin.com','sai-3130.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('sandeep-2.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','sandeep-2.csez.zohocorpin.com','sandeep-2.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('sandeep-2.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','sandeep-2.csez.zohocorpin.com','sandeep-2.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('sandeep-2.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','sandeep-2.csez.zohocorpin.com','sandeep-2.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('sandhya-3439.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','sandhya-3439.csez.zohocorpin.com','sandhya-3439.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('santhanapreethi.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','santhanapreethi.csez.zohocorpin.com','santhanapreethi.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('santhanapreethi.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','santhanapreethi.csez.zohocorpin.com','santhanapreethi.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('santhanapreethi.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','santhanapreethi.csez.zohocorpin.com','santhanapreethi.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('sathish-1320.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','sathish-1320.csez.zohocorpin.com','sathish-1320.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('sathish-2784.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','sathish-2784.csez.zohocorpin.com','sathish-2784.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('shanmugam-2352.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','shanmugam-2352.csez.zohocorpin.com','shanmugam-2352.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('shyamallsiphone.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','shyamallsiphone.csez.zohocorpin.com','shyamallsiphone.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('shyamallsiphone.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','shyamallsiphone.csez.zohocorpin.com','shyamallsiphone.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('shyamallsiphone.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','shyamallsiphone.csez.zohocorpin.com','shyamallsiphone.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('siddharth-4445.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','siddharth-4445.csez.zohocorpin.com','siddharth-4445.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('siddharth-4445.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','siddharth-4445.csez.zohocorpin.com','siddharth-4445.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('siddharth-4445.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','siddharth-4445.csez.zohocorpin.com','siddharth-4445.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('siva-2171.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','siva-2171.csez.zohocorpin.com','siva-2171.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('siva-2171.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','siva-2171.csez.zohocorpin.com','siva-2171.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('siva-2171.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','siva-2171.csez.zohocorpin.com','siva-2171.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('srivatsav-3642.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','srivatsav-3642.csez.zohocorpin.com','srivatsav-3642.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('subha-4506.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','subha-4506.csez.zohocorpin.com','subha-4506.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('sumanth-3304.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','sumanth-3304.csez.zohocorpin.com','sumanth-3304.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('sundari-1712.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','sundari-1712.csez.zohocorpin.com','sundari-1712.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('suresh-0665.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','suresh-0665.csez.zohocorpin.com','suresh-0665.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('suresh-0665.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','suresh-0665.csez.zohocorpin.com','suresh-0665.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('suresh-0665.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','suresh-0665.csez.zohocorpin.com','suresh-0665.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('suresh-1545.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','suresh-1545.csez.zohocorpin.com','suresh-1545.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('suresh-1545.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','suresh-1545.csez.zohocorpin.com','suresh-1545.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('suresh-1545.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','suresh-1545.csez.zohocorpin.com','suresh-1545.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ullas-4059.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','ullas-4059.csez.zohocorpin.com','ullas-4059.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ullas-4059.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','ullas-4059.csez.zohocorpin.com','ullas-4059.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('ullas-4059.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','ullas-4059.csez.zohocorpin.com','ullas-4059.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('vaisali-4071.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','vaisali-4071.csez.zohocorpin.com','vaisali-4071.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('varun-2246.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','varun-2246.csez.zohocorpin.com','varun-2246.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('varun-3902.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','varun-3902.csez.zohocorpin.com','varun-3902.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('velan-es0007.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','velan-es0007.csez.zohocorpin.com','velan-es0007.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('velan-es0007.csez.zohocorpin.com','172.24.14.0Failed_Objects_Map.netmap','MapSymbol','velan-es0007.csez.zohocorpin.com','velan-es0007.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('velan-es0007.csez.zohocorpin.com','Failed_Objects_Map.netmap','MapSymbol','velan-es0007.csez.zohocorpin.com','velan-es0007.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('venkat-0773.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','venkat-0773.csez.zohocorpin.com','venkat-0773.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('vijay-0596.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','vijay-0596.csez.zohocorpin.com','vijay-0596.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('vineesh-3479.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','vineesh-3479.csez.zohocorpin.com','vineesh-3479.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('vinodh-2312.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','vinodh-2312.csez.zohocorpin.com','vinodh-2312.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0),('yuvaraj-1472.csez.zohocorpin.com','172.24.14.0.netmap','MapSymbol','yuvaraj-1472.csez.zohocorpin.com','yuvaraj-1472.csez.zohocorpin.com',NULL,NULL,-1,-1,-1,-1,-1,'','',NULL,'\0',0,0);
/*!40000 ALTER TABLE `MapSymbol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MonitorNmsParameter`
--

DROP TABLE IF EXISTS `MonitorNmsParameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MonitorNmsParameter` (
  `PARAMETERNAME` varchar(200) NOT NULL,
  `CURRENTTIME` bigint(20) NOT NULL,
  `PARAMETERVALUE` bigint(20) DEFAULT NULL,
  `TINTERVAL` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`PARAMETERNAME`,`CURRENTTIME`),
  KEY `MonitorNmsParameter0_ndx` (`PARAMETERNAME`),
  KEY `MonitorNmsParameter1_ndx` (`CURRENTTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MonitorNmsParameter`
--

LOCK TABLES `MonitorNmsParameter` WRITE;
/*!40000 ALTER TABLE `MonitorNmsParameter` DISABLE KEYS */;
INSERT INTO `MonitorNmsParameter` VALUES ('AlertRate',1478254657153,0,300),('AlertRate',1478254957166,0,300),('AlertRate',1478255257180,0,300),('AlertRate',1478255557186,0,300),('AlertRate',1478256207888,0,300),('AlertRate',1478256507903,0,300),('AlertRate',1478256807908,0,300),('AlertRate',1478257107916,0,300),('AlertRate',1478257407927,0,300),('AlertRate',1478257707928,0,300),('AlertRate',1478258007933,0,300),('AlertRate',1478258307935,0,300),('AlertRate',1478258607946,0,300),('AlertRate',1478258907955,0,300),('AlertRate',1478259207958,0,300),('AlertRate',1478259507968,0,300),('AlertRate',1478259807969,0,300),('AlertRate',1478260107972,0,300),('AlertRate',1478260407974,0,300),('AlertRate',1478260707986,0,300),('AlertRate',1478261007988,0,300),('AlertRate',1478261307995,0,300),('AlertRate',1478261608002,0,300),('AlertRate',1478261908006,0,300),('AlertRate',1478262208011,0,300),('AlertRate',1478262508017,0,300),('AlertRate',1478262808025,0,300),('AlertRate',1478263108036,0,300),('AlertRate',1478263408038,0,300),('AlertRate',1478263708039,0,300),('AlertRate',1478264008041,0,300),('AlertRate',1478264308044,0,300),('AlertRate',1478264608045,0,300),('AlertRate',1478264908047,0,300),('AlertRate',1478265208060,0,300),('AlertRate',1478265508062,0,300),('AlertRate',1478265808064,0,300),('AlertRate',1478266108075,0,300),('AlertRate',1478266408086,0,300),('AlertRate',1478266708094,0,300),('AlertRate',1478267008106,0,300),('AlertRate',1478267308108,0,300),('AlertRate',1478267608120,0,300),('AlertRate',1478267908123,0,300),('AlertRate',1478268208125,0,300),('AlertRate',1478268508127,0,300),('AlertRate',1478268808132,0,300),('AlertRate',1478269108137,0,300),('AlertRate',1478269408138,0,300),('AlertRate',1478269708146,0,300),('AlertRate',1478270008159,0,300),('AlertRate',1478270308161,0,300),('AlertRate',1478270608163,0,300),('AlertRate',1478271057011,0,300),('AlertRate',1478271512015,0,300),('AlertRate',1478272031015,0,300),('AlertRate',1478273127013,0,300),('AlertRate',1478274062012,0,300),('AlertRate',1478276127011,0,300),('AlertRate',1478277370018,0,300),('AlertRate',1478278070017,0,300),('AlertRate',1478278371175,0,300),('AlertRate',1478278671182,0,300),('AlertRate',1478278971191,0,300),('AlertRate',1478279271196,0,300),('AlertRate',1478279571206,0,300),('AlertRate',1478279871219,0,300),('AlertRate',1478280171227,0,300),('AlertRate',1478280471235,0,300),('AlertRate',1478280771237,0,300),('AlertRate',1478281071250,0,300),('AlertRate',1478281371265,0,300),('AlertRate',1478281671273,0,300),('AlertRate',1478281971278,0,300),('AlertRate',1478282271286,0,300),('AlertRate',1478282571288,0,300),('AlertRate',1478282871296,0,300),('AlertRate',1478283171308,0,300),('AlertRate',1478283471312,0,300),('AlertRate',1478283771314,0,300),('AlertRate',1478284071322,0,300),('AlertRate',1478284371326,0,300),('AlertRate',1478284671331,0,300),('AlertRate',1478284971344,0,300),('AlertRate',1478285271354,0,300),('AlertRate',1478285571360,0,300),('AlertRate',1478285871362,0,300),('AlertRate',1478286171364,0,300),('AlertRate',1478286471365,0,300),('AlertRate',1478286771372,0,300),('AlertRate',1478287071380,0,300),('AlertRate',1478287371392,0,300),('AlertRate',1478287671398,0,300),('AlertRate',1478287971409,0,300),('AlertRate',1478288271411,0,300),('AlertRate',1478288571424,0,300),('AlertRate',1478288871426,0,300),('AlertRate',1478289171428,0,300),('AlertRate',1478289471430,0,300),('AlertRate',1478289771443,0,300),('AlertRate',1478290071450,0,300),('AlertRate',1478290371458,0,300),('AlertRate',1478290671462,0,300),('AlertRate',1478290971476,0,300),('AlertRate',1478291642010,0,300),('AlertRate',1478292249009,0,300),('AlertRate',1478292855010,0,300),('AlertRate',1478293447011,0,300),('AlertRate',1478294040011,0,300),('AlertRate',1478294633011,0,300),('AlertRate',1478295225012,0,300),('AlertRate',1478295818012,0,300),('AlertRate',1478296411011,0,300),('AlertRate',1478297004018,0,300),('AlertRate',1478297598015,0,300),('AlertRate',1478298192017,0,300),('AlertRate',1478298786019,0,300),('AlertRate',1478299379017,0,300),('AlertRate',1478299972017,0,300),('AlertRate',1478300565018,0,300),('AlertRate',1478301158018,0,300),('AlertRate',1478301751018,0,300),('AlertRate',1478302491677,0,300),('AlertRate',1478303089021,0,300),('AlertRate',1478303682014,0,300),('AlertRate',1478304275014,0,300),('AlertRate',1478304868015,0,300),('AlertRate',1478305460015,0,300),('AlertRate',1478306053015,0,300),('AlertRate',1478306647020,0,300),('AlertRate',1478307240016,0,300),('AlertRate',1478307834016,0,300),('AlertRate',1478308427018,0,300),('AlertRate',1478309051020,0,300),('AlertRate',1478309644017,0,300),('AlertRate',1478310239020,0,300),('AlertRate',1478310831018,0,300),('AlertRate',1478311424018,0,300),('AlertRate',1478312017015,0,300),('AlertRate',1478312610017,0,300),('AlertRate',1478313203018,0,300),('AlertRate',1478313796019,0,300),('AlertRate',1478314389017,0,300),('AlertRate',1478314982017,0,300),('AlertRate',1478315575015,0,300),('AlertRate',1478316168013,0,300),('AlertRate',1478316760012,0,300),('AlertRate',1478317353011,0,300),('AlertRate',1478317945012,0,300),('AlertRate',1478318538013,0,300),('AlertRate',1478319130012,0,300),('AlertRate',1478319724012,0,300),('AlertRate',1478320316011,0,300),('AlertRate',1478320909011,0,300),('AlertRate',1478321210163,0,300),('AlertRate',1478776012012,0,300),('AlertRate',1478776312037,0,300),('AlertRate',1478776612049,0,300),('AlertRate',1478776912060,0,300),('AlertRate',1478777212070,0,300),('AlertRate',1478777512079,0,300),('AlertRate',1478777812081,0,300),('AlertRate',1478778112083,0,300),('AlertRate',1478778412096,0,300),('DataCollectionRate',1478254657160,0,300),('DataCollectionRate',1478254957166,0,300),('DataCollectionRate',1478255257180,0,300),('DataCollectionRate',1478255557193,0,300),('DataCollectionRate',1478256207883,0,300),('DataCollectionRate',1478256507889,0,300),('DataCollectionRate',1478256807901,0,300),('DataCollectionRate',1478257107903,0,300),('DataCollectionRate',1478257407904,0,300),('DataCollectionRate',1478257707904,0,300),('DataCollectionRate',1478258007908,0,300),('DataCollectionRate',1478258307913,0,300),('DataCollectionRate',1478258607915,0,300),('DataCollectionRate',1478258907922,0,300),('DataCollectionRate',1478259207934,0,300),('DataCollectionRate',1478259507944,0,300),('DataCollectionRate',1478259807947,0,300),('DataCollectionRate',1478260107960,0,300),('DataCollectionRate',1478260407968,0,300),('DataCollectionRate',1478260707981,0,300),('DataCollectionRate',1478261007988,0,300),('DataCollectionRate',1478261307995,0,300),('DataCollectionRate',1478261608001,0,300),('DataCollectionRate',1478261908006,0,300),('DataCollectionRate',1478262208011,0,300),('DataCollectionRate',1478262508017,0,300),('DataCollectionRate',1478262808025,0,300),('DataCollectionRate',1478263108036,0,300),('DataCollectionRate',1478263408038,0,300),('DataCollectionRate',1478263708039,0,300),('DataCollectionRate',1478264008041,0,300),('DataCollectionRate',1478264308044,0,300),('DataCollectionRate',1478264608045,0,300),('DataCollectionRate',1478264908047,0,300),('DataCollectionRate',1478265208058,0,300),('DataCollectionRate',1478265508062,0,300),('DataCollectionRate',1478265808064,0,300),('DataCollectionRate',1478266108075,0,300),('DataCollectionRate',1478266408077,0,300),('DataCollectionRate',1478266708082,0,300),('DataCollectionRate',1478267008082,0,300),('DataCollectionRate',1478267308085,0,300),('DataCollectionRate',1478267608098,0,300),('DataCollectionRate',1478267908101,0,300),('DataCollectionRate',1478268208113,0,300),('DataCollectionRate',1478268508127,0,300),('DataCollectionRate',1478268808132,0,300),('DataCollectionRate',1478269108136,0,300),('DataCollectionRate',1478269408144,0,300),('DataCollectionRate',1478269708151,0,300),('DataCollectionRate',1478270008159,0,300),('DataCollectionRate',1478270308167,0,300),('DataCollectionRate',1478270608174,0,300),('DataCollectionRate',1478271057011,0,300),('DataCollectionRate',1478271512014,0,300),('DataCollectionRate',1478272031014,0,300),('DataCollectionRate',1478273127013,0,300),('DataCollectionRate',1478274062011,0,300),('DataCollectionRate',1478276127011,0,300),('DataCollectionRate',1478277370018,0,300),('DataCollectionRate',1478278070016,0,300),('DataCollectionRate',1478278371175,0,300),('DataCollectionRate',1478278671180,0,300),('DataCollectionRate',1478278971191,0,300),('DataCollectionRate',1478279271196,0,300),('DataCollectionRate',1478279571209,0,300),('DataCollectionRate',1478279871219,0,300),('DataCollectionRate',1478280171227,0,300),('DataCollectionRate',1478280471231,0,300),('DataCollectionRate',1478280771237,0,300),('DataCollectionRate',1478281071250,0,300),('DataCollectionRate',1478281371254,0,300),('DataCollectionRate',1478281671262,0,300),('DataCollectionRate',1478281971267,0,300),('DataCollectionRate',1478282271275,0,300),('DataCollectionRate',1478282571288,0,300),('DataCollectionRate',1478282871296,0,300),('DataCollectionRate',1478283171307,0,300),('DataCollectionRate',1478283471321,0,300),('DataCollectionRate',1478283771322,0,300),('DataCollectionRate',1478284071334,0,300),('DataCollectionRate',1478284371338,0,300),('DataCollectionRate',1478284671343,0,300),('DataCollectionRate',1478284971344,0,300),('DataCollectionRate',1478285271346,0,300),('DataCollectionRate',1478285571348,0,300),('DataCollectionRate',1478285871349,0,300),('DataCollectionRate',1478286171364,0,300),('DataCollectionRate',1478286471365,0,300),('DataCollectionRate',1478286771372,0,300),('DataCollectionRate',1478287071380,0,300),('DataCollectionRate',1478287371382,0,300),('DataCollectionRate',1478287671396,0,300),('DataCollectionRate',1478287971405,0,300),('DataCollectionRate',1478288271411,0,300),('DataCollectionRate',1478288571424,0,300),('DataCollectionRate',1478288871432,0,300),('DataCollectionRate',1478289171440,0,300),('DataCollectionRate',1478289471452,0,300),('DataCollectionRate',1478289771460,0,300),('DataCollectionRate',1478290071468,0,300),('DataCollectionRate',1478290371470,0,300),('DataCollectionRate',1478290671474,0,300),('DataCollectionRate',1478290971476,0,300),('DataCollectionRate',1478291642003,0,300),('DataCollectionRate',1478292249008,0,300),('DataCollectionRate',1478292855010,0,300),('DataCollectionRate',1478293447010,0,300),('DataCollectionRate',1478294040011,0,300),('DataCollectionRate',1478294633011,0,300),('DataCollectionRate',1478295225012,0,300),('DataCollectionRate',1478295818012,0,300),('DataCollectionRate',1478296411011,0,300),('DataCollectionRate',1478297004017,0,300),('DataCollectionRate',1478297598014,0,300),('DataCollectionRate',1478298192017,0,300),('DataCollectionRate',1478298786019,0,300),('DataCollectionRate',1478299379017,0,300),('DataCollectionRate',1478299972017,0,300),('DataCollectionRate',1478300565018,0,300),('DataCollectionRate',1478301158017,0,300),('DataCollectionRate',1478301751017,0,300),('DataCollectionRate',1478302491677,0,300),('DataCollectionRate',1478303089021,0,300),('DataCollectionRate',1478303682013,0,300),('DataCollectionRate',1478304275014,0,300),('DataCollectionRate',1478304868015,0,300),('DataCollectionRate',1478305460015,0,300),('DataCollectionRate',1478306053015,0,300),('DataCollectionRate',1478306647020,0,300),('DataCollectionRate',1478307240016,0,300),('DataCollectionRate',1478307834015,0,300),('DataCollectionRate',1478308427018,0,300),('DataCollectionRate',1478309051020,0,300),('DataCollectionRate',1478309644017,0,300),('DataCollectionRate',1478310239019,0,300),('DataCollectionRate',1478310831018,0,300),('DataCollectionRate',1478311424018,0,300),('DataCollectionRate',1478312017015,0,300),('DataCollectionRate',1478312610017,0,300),('DataCollectionRate',1478313203018,0,300),('DataCollectionRate',1478313796019,0,300),('DataCollectionRate',1478314389017,0,300),('DataCollectionRate',1478314982017,0,300),('DataCollectionRate',1478315575015,0,300),('DataCollectionRate',1478316168012,0,300),('DataCollectionRate',1478316760012,0,300),('DataCollectionRate',1478317353011,0,300),('DataCollectionRate',1478317945012,0,300),('DataCollectionRate',1478318538012,0,300),('DataCollectionRate',1478319130011,0,300),('DataCollectionRate',1478319724011,0,300),('DataCollectionRate',1478320316011,0,300),('DataCollectionRate',1478320909011,0,300),('DataCollectionRate',1478321210158,0,300),('DataCollectionRate',1478776012012,0,300),('DataCollectionRate',1478776312037,0,300),('DataCollectionRate',1478776612050,0,300),('DataCollectionRate',1478776912060,0,300),('DataCollectionRate',1478777212065,0,300),('DataCollectionRate',1478777512074,0,300),('DataCollectionRate',1478777812081,0,300),('DataCollectionRate',1478778112083,0,300),('DataCollectionRate',1478778412096,0,300),('EventRate',1478254657153,0,300),('EventRate',1478254957166,0,300),('EventRate',1478255257180,0,300),('EventRate',1478255557186,0,300),('EventRate',1478256207883,0,300),('EventRate',1478256507889,0,300),('EventRate',1478256807897,0,300),('EventRate',1478257107903,0,300),('EventRate',1478257407913,0,300),('EventRate',1478257707916,0,300),('EventRate',1478258007922,0,300),('EventRate',1478258307922,0,300),('EventRate',1478258607931,0,300),('EventRate',1478258907932,0,300),('EventRate',1478259207934,0,300),('EventRate',1478259507944,0,300),('EventRate',1478259807951,0,300),('EventRate',1478260107959,0,300),('EventRate',1478260407966,0,300),('EventRate',1478260707973,0,300),('EventRate',1478261007977,0,300),('EventRate',1478261307985,0,300),('EventRate',1478261607992,0,300),('EventRate',1478261907994,0,300),('EventRate',1478262208000,0,300),('EventRate',1478262508003,0,300),('EventRate',1478262808006,0,300),('EventRate',1478263108014,0,300),('EventRate',1478263408026,0,300),('EventRate',1478263708033,0,300),('EventRate',1478264008041,0,300),('EventRate',1478264308043,0,300),('EventRate',1478264608045,0,300),('EventRate',1478264908056,0,300),('EventRate',1478265208059,0,300),('EventRate',1478265508062,0,300),('EventRate',1478265808064,0,300),('EventRate',1478266108075,0,300),('EventRate',1478266408088,0,300),('EventRate',1478266708094,0,300),('EventRate',1478267008098,0,300),('EventRate',1478267308108,0,300),('EventRate',1478267608110,0,300),('EventRate',1478267908112,0,300),('EventRate',1478268208118,0,300),('EventRate',1478268508127,0,300),('EventRate',1478268808132,0,300),('EventRate',1478269108137,0,300),('EventRate',1478269408144,0,300),('EventRate',1478269708146,0,300),('EventRate',1478270008159,0,300),('EventRate',1478270308167,0,300),('EventRate',1478270608174,0,300),('EventRate',1478271057011,0,300),('EventRate',1478271512014,0,300),('EventRate',1478272031014,0,300),('EventRate',1478273127013,0,300),('EventRate',1478274062012,0,300),('EventRate',1478276127011,0,300),('EventRate',1478277370018,0,300),('EventRate',1478278070017,0,300),('EventRate',1478278371187,0,300),('EventRate',1478278671193,0,300),('EventRate',1478278971202,0,300),('EventRate',1478279271204,0,300),('EventRate',1478279571206,0,300),('EventRate',1478279871213,0,300),('EventRate',1478280171216,0,300),('EventRate',1478280471227,0,300),('EventRate',1478280771237,0,300),('EventRate',1478281071250,0,300),('EventRate',1478281371265,0,300),('EventRate',1478281671273,0,300),('EventRate',1478281971278,0,300),('EventRate',1478282271286,0,300),('EventRate',1478282571288,0,300),('EventRate',1478282871296,0,300),('EventRate',1478283171301,0,300),('EventRate',1478283471304,0,300),('EventRate',1478283771314,0,300),('EventRate',1478284071322,0,300),('EventRate',1478284371326,0,300),('EventRate',1478284671331,0,300),('EventRate',1478284971344,0,300),('EventRate',1478285271345,0,300),('EventRate',1478285571348,0,300),('EventRate',1478285871349,0,300),('EventRate',1478286171364,0,300),('EventRate',1478286471365,0,300),('EventRate',1478286771372,0,300),('EventRate',1478287071380,0,300),('EventRate',1478287371392,0,300),('EventRate',1478287671395,0,300),('EventRate',1478287971409,0,300),('EventRate',1478288271411,0,300),('EventRate',1478288571421,0,300),('EventRate',1478288871426,0,300),('EventRate',1478289171428,0,300),('EventRate',1478289471430,0,300),('EventRate',1478289771443,0,300),('EventRate',1478290071450,0,300),('EventRate',1478290371458,0,300),('EventRate',1478290671464,0,300),('EventRate',1478290971476,0,300),('EventRate',1478291642010,0,300),('EventRate',1478292249009,0,300),('EventRate',1478292855010,0,300),('EventRate',1478293447011,0,300),('EventRate',1478294040011,0,300),('EventRate',1478294633011,0,300),('EventRate',1478295225012,0,300),('EventRate',1478295818012,0,300),('EventRate',1478296411011,0,300),('EventRate',1478297004017,0,300),('EventRate',1478297598015,0,300),('EventRate',1478298192017,0,300),('EventRate',1478298786019,0,300),('EventRate',1478299379017,0,300),('EventRate',1478299972017,0,300),('EventRate',1478300565018,0,300),('EventRate',1478301158018,0,300),('EventRate',1478301751018,0,300),('EventRate',1478302491677,0,300),('EventRate',1478303089021,0,300),('EventRate',1478303682013,0,300),('EventRate',1478304275014,0,300),('EventRate',1478304868015,0,300),('EventRate',1478305460015,0,300),('EventRate',1478306053015,0,300),('EventRate',1478306647020,0,300),('EventRate',1478307240016,0,300),('EventRate',1478307834016,0,300),('EventRate',1478308427018,0,300),('EventRate',1478309051020,0,300),('EventRate',1478309644017,0,300),('EventRate',1478310239020,0,300),('EventRate',1478310831018,0,300),('EventRate',1478311424018,0,300),('EventRate',1478312017015,0,300),('EventRate',1478312610017,0,300),('EventRate',1478313203018,0,300),('EventRate',1478313796019,0,300),('EventRate',1478314389017,0,300),('EventRate',1478314982017,0,300),('EventRate',1478315575016,0,300),('EventRate',1478316168013,0,300),('EventRate',1478316760012,0,300),('EventRate',1478317353011,0,300),('EventRate',1478317945012,0,300),('EventRate',1478318538013,0,300),('EventRate',1478319130011,0,300),('EventRate',1478319724012,0,300),('EventRate',1478320316011,0,300),('EventRate',1478320909011,0,300),('EventRate',1478321210163,0,300),('EventRate',1478776012012,0,300),('EventRate',1478776312037,0,300),('EventRate',1478776612049,0,300),('EventRate',1478776912060,0,300),('EventRate',1478777212070,0,300),('EventRate',1478777512079,0,300),('EventRate',1478777812081,0,300),('EventRate',1478778112083,0,300),('EventRate',1478778412096,0,300),('StatusPollingRate',1478254657153,0,300),('StatusPollingRate',1478254957166,0,300),('StatusPollingRate',1478255257180,0,300),('StatusPollingRate',1478255557186,0,300),('StatusPollingRate',1478256207883,0,300),('StatusPollingRate',1478256507897,0,300),('StatusPollingRate',1478256807901,0,300),('StatusPollingRate',1478257107903,0,300),('StatusPollingRate',1478257407904,0,300),('StatusPollingRate',1478257707905,0,300),('StatusPollingRate',1478258007910,0,300),('StatusPollingRate',1478258307910,0,300),('StatusPollingRate',1478258607915,0,300),('StatusPollingRate',1478258907926,0,300),('StatusPollingRate',1478259207934,0,300),('StatusPollingRate',1478259507944,0,300),('StatusPollingRate',1478259807949,0,300),('StatusPollingRate',1478260107960,0,300),('StatusPollingRate',1478260407961,0,300),('StatusPollingRate',1478260707973,0,300),('StatusPollingRate',1478261007977,0,300),('StatusPollingRate',1478261307982,0,300),('StatusPollingRate',1478261607988,0,300),('StatusPollingRate',1478261907994,0,300),('StatusPollingRate',1478262208003,0,300),('StatusPollingRate',1478262508017,0,300),('StatusPollingRate',1478262808025,0,300),('StatusPollingRate',1478263108036,0,300),('StatusPollingRate',1478263408038,0,300),('StatusPollingRate',1478263708043,0,300),('StatusPollingRate',1478264008052,0,300),('StatusPollingRate',1478264308055,0,300),('StatusPollingRate',1478264608057,0,300),('StatusPollingRate',1478264908066,0,300),('StatusPollingRate',1478265208072,0,300),('StatusPollingRate',1478265508074,0,300),('StatusPollingRate',1478265808085,0,300),('StatusPollingRate',1478266108086,0,300),('StatusPollingRate',1478266408091,0,300),('StatusPollingRate',1478266708094,0,300),('StatusPollingRate',1478267008103,0,300),('StatusPollingRate',1478267308108,0,300),('StatusPollingRate',1478267608110,0,300),('StatusPollingRate',1478267908112,0,300),('StatusPollingRate',1478268208118,0,300),('StatusPollingRate',1478268508127,0,300),('StatusPollingRate',1478268808132,0,300),('StatusPollingRate',1478269108141,0,300),('StatusPollingRate',1478269408144,0,300),('StatusPollingRate',1478269708145,0,300),('StatusPollingRate',1478270008159,0,300),('StatusPollingRate',1478270308161,0,300),('StatusPollingRate',1478270608168,0,300),('StatusPollingRate',1478271057011,0,300),('StatusPollingRate',1478271512014,0,300),('StatusPollingRate',1478272031014,0,300),('StatusPollingRate',1478273127013,0,300),('StatusPollingRate',1478274062011,0,300),('StatusPollingRate',1478276127011,0,300),('StatusPollingRate',1478277370018,0,300),('StatusPollingRate',1478278070016,0,300),('StatusPollingRate',1478278371187,0,300),('StatusPollingRate',1478278671191,0,300),('StatusPollingRate',1478278971202,0,300),('StatusPollingRate',1478279271208,0,300),('StatusPollingRate',1478279571218,0,300),('StatusPollingRate',1478279871219,0,300),('StatusPollingRate',1478280171227,0,300),('StatusPollingRate',1478280471238,0,300),('StatusPollingRate',1478280771248,0,300),('StatusPollingRate',1478281071250,0,300),('StatusPollingRate',1478281371265,0,300),('StatusPollingRate',1478281671270,0,300),('StatusPollingRate',1478281971278,0,300),('StatusPollingRate',1478282271286,0,300),('StatusPollingRate',1478282571288,0,300),('StatusPollingRate',1478282871296,0,300),('StatusPollingRate',1478283171301,0,300),('StatusPollingRate',1478283471308,0,300),('StatusPollingRate',1478283771314,0,300),('StatusPollingRate',1478284071316,0,300),('StatusPollingRate',1478284371326,0,300),('StatusPollingRate',1478284671331,0,300),('StatusPollingRate',1478284971344,0,300),('StatusPollingRate',1478285271355,0,300),('StatusPollingRate',1478285571360,0,300),('StatusPollingRate',1478285871362,0,300),('StatusPollingRate',1478286171364,0,300),('StatusPollingRate',1478286471365,0,300),('StatusPollingRate',1478286771374,0,300),('StatusPollingRate',1478287071380,0,300),('StatusPollingRate',1478287371382,0,300),('StatusPollingRate',1478287671396,0,300),('StatusPollingRate',1478287971407,0,300),('StatusPollingRate',1478288271411,0,300),('StatusPollingRate',1478288571422,0,300),('StatusPollingRate',1478288871436,0,300),('StatusPollingRate',1478289171440,0,300),('StatusPollingRate',1478289471451,0,300),('StatusPollingRate',1478289771455,0,300),('StatusPollingRate',1478290071462,0,300),('StatusPollingRate',1478290371470,0,300),('StatusPollingRate',1478290671474,0,300),('StatusPollingRate',1478290971476,0,300),('StatusPollingRate',1478291642003,0,300),('StatusPollingRate',1478292249008,0,300),('StatusPollingRate',1478292855010,0,300),('StatusPollingRate',1478293447010,0,300),('StatusPollingRate',1478294040011,0,300),('StatusPollingRate',1478294633011,0,300),('StatusPollingRate',1478295225012,0,300),('StatusPollingRate',1478295818012,0,300),('StatusPollingRate',1478296411011,0,300),('StatusPollingRate',1478297004017,0,300),('StatusPollingRate',1478297598014,0,300),('StatusPollingRate',1478298192017,0,300),('StatusPollingRate',1478298786019,0,300),('StatusPollingRate',1478299379017,0,300),('StatusPollingRate',1478299972017,0,300),('StatusPollingRate',1478300565018,0,300),('StatusPollingRate',1478301158018,0,300),('StatusPollingRate',1478301751017,0,300),('StatusPollingRate',1478302491677,0,300),('StatusPollingRate',1478303089021,0,300),('StatusPollingRate',1478303682013,0,300),('StatusPollingRate',1478304275014,0,300),('StatusPollingRate',1478304868015,0,300),('StatusPollingRate',1478305460015,0,300),('StatusPollingRate',1478306053015,0,300),('StatusPollingRate',1478306647020,0,300),('StatusPollingRate',1478307240016,0,300),('StatusPollingRate',1478307834015,0,300),('StatusPollingRate',1478308427018,0,300),('StatusPollingRate',1478309051020,0,300),('StatusPollingRate',1478309644017,0,300),('StatusPollingRate',1478310239019,0,300),('StatusPollingRate',1478310831018,0,300),('StatusPollingRate',1478311424018,0,300),('StatusPollingRate',1478312017015,0,300),('StatusPollingRate',1478312610017,0,300),('StatusPollingRate',1478313203018,0,300),('StatusPollingRate',1478313796019,0,300),('StatusPollingRate',1478314389017,0,300),('StatusPollingRate',1478314982017,0,300),('StatusPollingRate',1478315575015,0,300),('StatusPollingRate',1478316168012,0,300),('StatusPollingRate',1478316760012,0,300),('StatusPollingRate',1478317353011,0,300),('StatusPollingRate',1478317945012,0,300),('StatusPollingRate',1478318538013,0,300),('StatusPollingRate',1478319130011,0,300),('StatusPollingRate',1478319724011,0,300),('StatusPollingRate',1478320316011,0,300),('StatusPollingRate',1478320909011,0,300),('StatusPollingRate',1478321210163,0,300),('StatusPollingRate',1478776012012,0,300),('StatusPollingRate',1478776312037,0,300),('StatusPollingRate',1478776612049,0,300),('StatusPollingRate',1478776912060,0,300),('StatusPollingRate',1478777212065,0,300),('StatusPollingRate',1478777512074,0,300),('StatusPollingRate',1478777812081,0,300),('StatusPollingRate',1478778112083,0,300),('StatusPollingRate',1478778412096,0,300),('TrapRate',1478254657156,0,300),('TrapRate',1478254957166,0,300),('TrapRate',1478255257180,0,300),('TrapRate',1478255557193,0,300),('TrapRate',1478256207883,0,300),('TrapRate',1478256507897,0,300),('TrapRate',1478256807901,0,300),('TrapRate',1478257107903,0,300),('TrapRate',1478257407904,0,300),('TrapRate',1478257707904,0,300),('TrapRate',1478258007908,0,300),('TrapRate',1478258307913,0,300),('TrapRate',1478258607919,0,300),('TrapRate',1478258907922,0,300),('TrapRate',1478259207934,0,300),('TrapRate',1478259507944,0,300),('TrapRate',1478259807952,0,300),('TrapRate',1478260107955,0,300),('TrapRate',1478260407968,0,300),('TrapRate',1478260707974,0,300),('TrapRate',1478261007977,0,300),('TrapRate',1478261307982,0,300),('TrapRate',1478261607993,0,300),('TrapRate',1478261907994,0,300),('TrapRate',1478262208003,0,300),('TrapRate',1478262508017,0,300),('TrapRate',1478262808025,0,300),('TrapRate',1478263108036,0,300),('TrapRate',1478263408038,0,300),('TrapRate',1478263708042,0,300),('TrapRate',1478264008051,0,300),('TrapRate',1478264308055,0,300),('TrapRate',1478264608057,0,300),('TrapRate',1478264908062,0,300),('TrapRate',1478265208063,0,300),('TrapRate',1478265508074,0,300),('TrapRate',1478265808076,0,300),('TrapRate',1478266108085,0,300),('TrapRate',1478266408086,0,300),('TrapRate',1478266708094,0,300),('TrapRate',1478267008103,0,300),('TrapRate',1478267308109,0,300),('TrapRate',1478267608110,0,300),('TrapRate',1478267908123,0,300),('TrapRate',1478268208130,0,300),('TrapRate',1478268508138,0,300),('TrapRate',1478268808144,0,300),('TrapRate',1478269108148,0,300),('TrapRate',1478269408155,0,300),('TrapRate',1478269708157,0,300),('TrapRate',1478270008159,0,300),('TrapRate',1478270308161,0,300),('TrapRate',1478270608163,0,300),('TrapRate',1478271057011,0,300),('TrapRate',1478271512014,0,300),('TrapRate',1478272031014,0,300),('TrapRate',1478273127013,0,300),('TrapRate',1478274062012,0,300),('TrapRate',1478276127011,0,300),('TrapRate',1478277370018,0,300),('TrapRate',1478278070016,0,300),('TrapRate',1478278371175,0,300),('TrapRate',1478278671180,0,300),('TrapRate',1478278971191,0,300),('TrapRate',1478279271196,0,300),('TrapRate',1478279571208,0,300),('TrapRate',1478279871219,0,300),('TrapRate',1478280171227,0,300),('TrapRate',1478280471234,0,300),('TrapRate',1478280771237,0,300),('TrapRate',1478281071250,0,300),('TrapRate',1478281371254,0,300),('TrapRate',1478281671262,0,300),('TrapRate',1478281971267,0,300),('TrapRate',1478282271275,0,300),('TrapRate',1478282571288,0,300),('TrapRate',1478282871296,0,300),('TrapRate',1478283171308,0,300),('TrapRate',1478283471308,0,300),('TrapRate',1478283771311,0,300),('TrapRate',1478284071322,0,300),('TrapRate',1478284371326,0,300),('TrapRate',1478284671331,0,300),('TrapRate',1478284971333,0,300),('TrapRate',1478285271346,0,300),('TrapRate',1478285571348,0,300),('TrapRate',1478285871349,0,300),('TrapRate',1478286171352,0,300),('TrapRate',1478286471365,0,300),('TrapRate',1478286771372,0,300),('TrapRate',1478287071380,0,300),('TrapRate',1478287371382,0,300),('TrapRate',1478287671395,0,300),('TrapRate',1478287971407,0,300),('TrapRate',1478288271412,0,300),('TrapRate',1478288571422,0,300),('TrapRate',1478288871426,0,300),('TrapRate',1478289171428,0,300),('TrapRate',1478289471430,0,300),('TrapRate',1478289771443,0,300),('TrapRate',1478290071450,0,300),('TrapRate',1478290371458,0,300),('TrapRate',1478290671463,0,300),('TrapRate',1478290971476,0,300),('TrapRate',1478291642003,0,300),('TrapRate',1478292249008,0,300),('TrapRate',1478292855010,0,300),('TrapRate',1478293447011,0,300),('TrapRate',1478294040011,0,300),('TrapRate',1478294633011,0,300),('TrapRate',1478295225012,0,300),('TrapRate',1478295818012,0,300),('TrapRate',1478296411011,0,300),('TrapRate',1478297004017,0,300),('TrapRate',1478297598015,0,300),('TrapRate',1478298192017,0,300),('TrapRate',1478298786019,0,300),('TrapRate',1478299379017,0,300),('TrapRate',1478299972017,0,300),('TrapRate',1478300565018,0,300),('TrapRate',1478301158018,0,300),('TrapRate',1478301751018,0,300),('TrapRate',1478302491677,0,300),('TrapRate',1478303089021,0,300),('TrapRate',1478303682013,0,300),('TrapRate',1478304275014,0,300),('TrapRate',1478304868015,0,300),('TrapRate',1478305460015,0,300),('TrapRate',1478306053015,0,300),('TrapRate',1478306647020,0,300),('TrapRate',1478307240016,0,300),('TrapRate',1478307834015,0,300),('TrapRate',1478308427018,0,300),('TrapRate',1478309051020,0,300),('TrapRate',1478309644017,0,300),('TrapRate',1478310239020,0,300),('TrapRate',1478310831018,0,300),('TrapRate',1478311424018,0,300),('TrapRate',1478312017015,0,300),('TrapRate',1478312610017,0,300),('TrapRate',1478313203018,0,300),('TrapRate',1478313796019,0,300),('TrapRate',1478314389017,0,300),('TrapRate',1478314982017,0,300),('TrapRate',1478315575015,0,300),('TrapRate',1478316168013,0,300),('TrapRate',1478316760012,0,300),('TrapRate',1478317353011,0,300),('TrapRate',1478317945012,0,300),('TrapRate',1478318538013,0,300),('TrapRate',1478319130011,0,300),('TrapRate',1478319724012,0,300),('TrapRate',1478320316011,0,300),('TrapRate',1478320909011,0,300),('TrapRate',1478321210158,0,300),('TrapRate',1478776012012,0,300),('TrapRate',1478776312037,0,300),('TrapRate',1478776612050,0,300),('TrapRate',1478776912060,0,300),('TrapRate',1478777212065,0,300),('TrapRate',1478777512070,0,300),('TrapRate',1478777812076,0,300),('TrapRate',1478778112083,0,300),('TrapRate',1478778412085,0,300);
/*!40000 ALTER TABLE `MonitorNmsParameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MultipleDeviceDSPolledData`
--

DROP TABLE IF EXISTS `MultipleDeviceDSPolledData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MultipleDeviceDSPolledData` (
  `ID` bigint(20) NOT NULL,
  `KPIOBJNAME` varchar(100) DEFAULT NULL,
  `SUBCOMPNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK8C5D3AFD567F5DD2` (`ID`),
  CONSTRAINT `FK8C5D3AFD567F5DD2` FOREIGN KEY (`ID`) REFERENCES `PolledData` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MultipleDeviceDSPolledData`
--

LOCK TABLES `MultipleDeviceDSPolledData` WRITE;
/*!40000 ALTER TABLE `MultipleDeviceDSPolledData` DISABLE KEYS */;
/*!40000 ALTER TABLE `MultipleDeviceDSPolledData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NMS_STATUS_MONITOR11_10_2016`
--

DROP TABLE IF EXISTS `NMS_STATUS_MONITOR11_10_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NMS_STATUS_MONITOR11_10_2016` (
  `POLLID` bigint(20) DEFAULT NULL,
  `INSTANCE` varchar(100) DEFAULT NULL,
  `TTIME` bigint(20) DEFAULT NULL,
  `VAL` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NMS_STATUS_MONITOR11_10_2016`
--

LOCK TABLES `NMS_STATUS_MONITOR11_10_2016` WRITE;
/*!40000 ALTER TABLE `NMS_STATUS_MONITOR11_10_2016` DISABLE KEYS */;
INSERT INTO `NMS_STATUS_MONITOR11_10_2016` VALUES (7,'-1',1478775711994,123),(8,'-1',1478775727000,100),(7,'-1',1478775741999,132),(8,'-1',1478775741999,100),(7,'-1',1478775772000,131),(8,'-1',1478775772000,100),(7,'-1',1478775801999,123),(8,'-1',1478775801999,100),(7,'-1',1478775831999,123),(8,'-1',1478775831999,100),(7,'-1',1478775862000,123),(8,'-1',1478775862000,100),(7,'-1',1478775892000,123),(8,'-1',1478775892000,100),(7,'-1',1478775922001,123),(8,'-1',1478775922001,100),(7,'-1',1478775952001,124),(8,'-1',1478775952001,100),(7,'-1',1478775982000,130),(8,'-1',1478775982000,100),(7,'-1',1478776012001,129),(7,'-1',1478776027001,129),(8,'-1',1478776027001,100),(7,'-1',1478776057001,126),(8,'-1',1478776057001,100),(7,'-1',1478776087003,125),(8,'-1',1478776087003,100),(7,'-1',1478776117003,126),(8,'-1',1478776117003,100),(7,'-1',1478776147002,130),(8,'-1',1478776147002,100),(7,'-1',1478776177001,128),(8,'-1',1478776177001,100),(7,'-1',1478776207002,130),(8,'-1',1478776207002,100),(7,'-1',1478776237002,128),(8,'-1',1478776237002,100),(7,'-1',1478776267001,130),(8,'-1',1478776267001,100),(8,'-1',1478776297002,100),(7,'-1',1478776311997,128),(8,'-1',1478776311997,100),(7,'-1',1478776342000,129),(8,'-1',1478776342000,100),(7,'-1',1478776371998,128),(8,'-1',1478776371998,100),(7,'-1',1478776401998,130),(8,'-1',1478776401998,100),(7,'-1',1478776431997,126),(8,'-1',1478776431997,100),(7,'-1',1478776461999,128),(8,'-1',1478776461999,100),(7,'-1',1478776491999,129),(8,'-1',1478776491999,100),(7,'-1',1478776521999,127),(8,'-1',1478776521999,100),(7,'-1',1478776551998,125),(8,'-1',1478776551998,100),(7,'-1',1478776581995,127),(8,'-1',1478776581995,100),(7,'-1',1478776611997,126),(7,'-1',1478776627000,126),(8,'-1',1478776627000,100),(7,'-1',1478776657000,128),(8,'-1',1478776657000,100),(7,'-1',1478776687000,125),(8,'-1',1478776687000,100),(7,'-1',1478776717001,125),(8,'-1',1478776717001,100),(7,'-1',1478776747002,123),(8,'-1',1478776747002,100),(7,'-1',1478776777003,123),(8,'-1',1478776777003,100),(7,'-1',1478776807000,123),(8,'-1',1478776807000,100),(7,'-1',1478776837000,123),(8,'-1',1478776837000,100),(7,'-1',1478776867000,123),(8,'-1',1478776867000,100),(8,'-1',1478776897001,100),(7,'-1',1478776911998,124),(8,'-1',1478776911998,100),(7,'-1',1478776941998,124),(8,'-1',1478776941998,100),(7,'-1',1478776971998,123),(8,'-1',1478776971998,100),(7,'-1',1478777001995,123),(8,'-1',1478777001995,100),(7,'-1',1478777031995,123),(8,'-1',1478777031995,100),(7,'-1',1478777061996,123),(8,'-1',1478777061996,100),(7,'-1',1478777091996,123),(8,'-1',1478777091996,100),(7,'-1',1478777121996,123),(8,'-1',1478777121996,100),(7,'-1',1478777151996,124),(8,'-1',1478777151996,100),(7,'-1',1478777181998,123),(8,'-1',1478777181998,100),(7,'-1',1478777211996,124),(7,'-1',1478777227001,124),(8,'-1',1478777227001,100),(7,'-1',1478777257002,124),(8,'-1',1478777257002,100),(7,'-1',1478777287000,123),(8,'-1',1478777287000,100),(7,'-1',1478777317003,123),(8,'-1',1478777317003,100),(7,'-1',1478777347004,123),(8,'-1',1478777347004,100),(7,'-1',1478777376999,123),(8,'-1',1478777376999,100),(7,'-1',1478777407001,123),(8,'-1',1478777407001,100),(7,'-1',1478777437001,123),(8,'-1',1478777437001,100),(7,'-1',1478777467003,123),(8,'-1',1478777467003,100),(8,'-1',1478777497003,100),(7,'-1',1478777511999,124),(8,'-1',1478777511999,100),(7,'-1',1478777541998,124),(8,'-1',1478777541998,100),(7,'-1',1478777571999,123),(8,'-1',1478777571999,100),(7,'-1',1478777601997,123),(8,'-1',1478777601997,100),(7,'-1',1478777631996,123),(8,'-1',1478777631996,100),(7,'-1',1478777661999,123),(8,'-1',1478777661999,100),(7,'-1',1478777691997,123),(8,'-1',1478777691997,100),(7,'-1',1478777721998,123),(8,'-1',1478777721998,100),(7,'-1',1478777751999,123),(8,'-1',1478777751999,100),(7,'-1',1478777782001,123),(8,'-1',1478777782001,100),(7,'-1',1478777811996,124),(7,'-1',1478777827000,124),(8,'-1',1478777827000,100),(7,'-1',1478777857002,124),(8,'-1',1478777857002,100),(7,'-1',1478777887000,123),(8,'-1',1478777887000,100),(7,'-1',1478777917002,123),(8,'-1',1478777917002,100),(7,'-1',1478777947001,123),(8,'-1',1478777947001,100),(7,'-1',1478777977004,123),(8,'-1',1478777977004,100),(7,'-1',1478778007001,123),(8,'-1',1478778007001,100),(7,'-1',1478778037002,123),(8,'-1',1478778037002,100),(7,'-1',1478778067002,123),(8,'-1',1478778067002,100),(8,'-1',1478778097001,100),(7,'-1',1478778111997,124),(8,'-1',1478778111997,100),(7,'-1',1478778142000,124),(8,'-1',1478778142000,100),(7,'-1',1478778171996,123),(8,'-1',1478778171996,100),(7,'-1',1478778201999,123),(8,'-1',1478778201999,100),(7,'-1',1478778231998,123),(8,'-1',1478778231998,100),(7,'-1',1478778261998,123),(8,'-1',1478778261998,100),(7,'-1',1478778291996,123),(8,'-1',1478778291996,100),(7,'-1',1478778321995,123),(8,'-1',1478778321995,100),(7,'-1',1478778351997,123),(8,'-1',1478778351997,100),(7,'-1',1478778381997,123),(8,'-1',1478778381997,100),(7,'-1',1478778411998,124),(7,'-1',1478778427003,124),(8,'-1',1478778427003,100);
/*!40000 ALTER TABLE `NMS_STATUS_MONITOR11_10_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NMS_STATUS_MONITOR11_4_2016`
--

DROP TABLE IF EXISTS `NMS_STATUS_MONITOR11_4_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NMS_STATUS_MONITOR11_4_2016` (
  `POLLID` bigint(20) DEFAULT NULL,
  `INSTANCE` varchar(100) DEFAULT NULL,
  `TTIME` bigint(20) DEFAULT NULL,
  `VAL` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NMS_STATUS_MONITOR11_4_2016`
--

LOCK TABLES `NMS_STATUS_MONITOR11_4_2016` WRITE;
/*!40000 ALTER TABLE `NMS_STATUS_MONITOR11_4_2016` DISABLE KEYS */;
INSERT INTO `NMS_STATUS_MONITOR11_4_2016` VALUES (7,'-1',1478254175503,132),(8,'-1',1478254175503,100),(7,'-1',1478254205503,133),(8,'-1',1478254205503,100),(7,'-1',1478254235503,134),(8,'-1',1478254235503,100),(7,'-1',1478254265505,134),(8,'-1',1478254265505,100),(7,'-1',1478254295502,134),(8,'-1',1478254295502,100),(7,'-1',1478254357141,141),(8,'-1',1478254372146,100),(7,'-1',1478254387145,142),(8,'-1',1478254387145,100),(7,'-1',1478254417145,142),(8,'-1',1478254417145,100),(7,'-1',1478254447145,134),(8,'-1',1478254447145,100),(7,'-1',1478254477147,134),(7,'-1',1478254492148,134),(8,'-1',1478254492148,100),(7,'-1',1478254522146,134),(8,'-1',1478254522146,100),(7,'-1',1478254552148,134),(8,'-1',1478254552148,100),(7,'-1',1478254582149,134),(8,'-1',1478254582149,100),(7,'-1',1478254612148,135),(8,'-1',1478254612148,100),(7,'-1',1478254642147,138),(8,'-1',1478254642147,100),(7,'-1',1478254672150,137),(8,'-1',1478254672150,100),(7,'-1',1478254702149,137),(8,'-1',1478254702149,100),(7,'-1',1478254732146,135),(8,'-1',1478254732146,100),(7,'-1',1478254762149,135),(8,'-1',1478254762149,100),(7,'-1',1478254792151,134),(8,'-1',1478254792151,100),(7,'-1',1478254822147,134),(8,'-1',1478254822147,100),(7,'-1',1478254852148,134),(8,'-1',1478254852148,100),(7,'-1',1478254882151,134),(8,'-1',1478254882151,100),(7,'-1',1478254912148,134),(8,'-1',1478254912148,100),(7,'-1',1478254942150,135),(8,'-1',1478254942150,100),(7,'-1',1478254972146,135),(8,'-1',1478254972146,100),(7,'-1',1478255002151,135),(8,'-1',1478255002151,100),(7,'-1',1478255032151,134),(8,'-1',1478255032151,100),(7,'-1',1478255062150,134),(8,'-1',1478255062150,100),(7,'-1',1478255092151,134),(8,'-1',1478255092151,100),(7,'-1',1478255122148,134),(8,'-1',1478255122148,100),(7,'-1',1478255152145,134),(8,'-1',1478255152145,100),(7,'-1',1478255182149,134),(8,'-1',1478255182149,100),(7,'-1',1478255212146,134),(8,'-1',1478255212146,100),(7,'-1',1478255242146,135),(8,'-1',1478255242146,100),(7,'-1',1478255272150,135),(8,'-1',1478255272150,100),(7,'-1',1478255302145,135),(8,'-1',1478255302145,100),(7,'-1',1478255332148,134),(8,'-1',1478255332148,100),(7,'-1',1478255362146,134),(8,'-1',1478255362146,100),(7,'-1',1478255392145,134),(8,'-1',1478255392145,100),(7,'-1',1478255422146,134),(8,'-1',1478255422146,100),(7,'-1',1478255452150,134),(8,'-1',1478255452150,100),(7,'-1',1478255482149,134),(8,'-1',1478255482149,100),(7,'-1',1478255512152,134),(8,'-1',1478255512152,100),(7,'-1',1478255542150,135),(8,'-1',1478255542150,100),(7,'-1',1478255572148,135),(8,'-1',1478255572148,100),(7,'-1',1478255602147,135),(8,'-1',1478255602147,100),(7,'-1',1478255632147,134),(8,'-1',1478255632147,100),(7,'-1',1478255662145,134),(8,'-1',1478255662145,100),(7,'-1',1478255692149,134),(8,'-1',1478255692149,100),(7,'-1',1478255722147,134),(8,'-1',1478255722147,100),(7,'-1',1478255752149,134),(8,'-1',1478255752149,100),(7,'-1',1478255907863,141),(8,'-1',1478255922865,100),(7,'-1',1478255937869,142),(8,'-1',1478255937869,100),(7,'-1',1478255967867,142),(8,'-1',1478255967867,100),(7,'-1',1478255997866,134),(8,'-1',1478255997866,100),(7,'-1',1478256027864,134),(8,'-1',1478256027864,100),(7,'-1',1478256057865,134),(8,'-1',1478256057865,100),(7,'-1',1478256087864,134),(8,'-1',1478256087864,100),(7,'-1',1478256117864,134),(8,'-1',1478256117864,100),(7,'-1',1478256147870,135),(8,'-1',1478256147870,100),(7,'-1',1478256177866,138),(8,'-1',1478256177866,100),(7,'-1',1478256207864,137),(8,'-1',1478256207864,100),(7,'-1',1478256237863,137),(8,'-1',1478256237863,100),(7,'-1',1478256267864,135),(8,'-1',1478256267864,100),(7,'-1',1478256297864,138),(8,'-1',1478256297864,100),(7,'-1',1478256327864,138),(8,'-1',1478256327864,100),(7,'-1',1478256357865,136),(8,'-1',1478256357865,100),(7,'-1',1478256387864,137),(8,'-1',1478256387864,100),(7,'-1',1478256417866,137),(8,'-1',1478256417866,100),(7,'-1',1478256447865,138),(8,'-1',1478256447865,100),(7,'-1',1478256477871,136),(8,'-1',1478256477871,100),(7,'-1',1478256507866,137),(8,'-1',1478256507866,100),(7,'-1',1478256537865,137),(8,'-1',1478256537865,100),(7,'-1',1478256567866,138),(8,'-1',1478256567866,100),(7,'-1',1478256597864,136),(8,'-1',1478256597864,100),(7,'-1',1478256627869,136),(8,'-1',1478256627869,100),(7,'-1',1478256657863,135),(8,'-1',1478256657863,100),(7,'-1',1478256687866,134),(8,'-1',1478256687866,100),(7,'-1',1478256717868,134),(8,'-1',1478256717868,100),(7,'-1',1478256747864,134),(8,'-1',1478256747864,100),(7,'-1',1478256777866,134),(8,'-1',1478256777866,100),(7,'-1',1478256807868,135),(8,'-1',1478256807868,100),(7,'-1',1478256837865,135),(8,'-1',1478256837865,100),(7,'-1',1478256867866,134),(8,'-1',1478256867866,100),(7,'-1',1478256897864,134),(8,'-1',1478256897864,100),(7,'-1',1478256927868,134),(8,'-1',1478256927868,100),(7,'-1',1478256957864,134),(8,'-1',1478256957864,100),(7,'-1',1478256987868,134),(8,'-1',1478256987868,100),(7,'-1',1478257017864,134),(8,'-1',1478257017864,100),(7,'-1',1478257047868,134),(8,'-1',1478257047868,100),(7,'-1',1478257077865,134),(8,'-1',1478257077865,100),(7,'-1',1478257107863,135),(8,'-1',1478257107863,100),(7,'-1',1478257137870,135),(8,'-1',1478257137870,100),(7,'-1',1478257167866,134),(8,'-1',1478257167866,100),(7,'-1',1478257197865,134),(8,'-1',1478257197865,100),(7,'-1',1478257227865,134),(8,'-1',1478257227865,100),(7,'-1',1478257257864,134),(8,'-1',1478257257864,100),(7,'-1',1478257287868,134),(8,'-1',1478257287868,100),(7,'-1',1478257317864,134),(8,'-1',1478257317864,100),(7,'-1',1478257347865,134),(8,'-1',1478257347865,100),(7,'-1',1478257377869,132),(8,'-1',1478257377869,100),(7,'-1',1478257407868,133),(8,'-1',1478257407868,100),(7,'-1',1478257437866,132),(8,'-1',1478257437866,100),(7,'-1',1478257467865,131),(8,'-1',1478257467865,100),(7,'-1',1478257497866,131),(8,'-1',1478257497866,100),(7,'-1',1478257527866,131),(8,'-1',1478257527866,100),(7,'-1',1478257557863,131),(8,'-1',1478257557863,100),(7,'-1',1478257587869,131),(8,'-1',1478257587869,100),(7,'-1',1478257617866,131),(8,'-1',1478257617866,100),(7,'-1',1478257647865,131),(8,'-1',1478257647865,100),(7,'-1',1478257677869,131),(8,'-1',1478257677869,100),(7,'-1',1478257707863,132),(8,'-1',1478257707863,100),(7,'-1',1478257737867,132),(8,'-1',1478257737867,100),(7,'-1',1478257767867,131),(8,'-1',1478257767867,100),(7,'-1',1478257797868,131),(8,'-1',1478257797868,100),(7,'-1',1478257827867,131),(8,'-1',1478257827867,100),(7,'-1',1478257857867,131),(8,'-1',1478257857867,100),(7,'-1',1478257887867,131),(8,'-1',1478257887867,100),(7,'-1',1478257917865,131),(8,'-1',1478257917865,100),(7,'-1',1478257947864,131),(8,'-1',1478257947864,100),(7,'-1',1478257977870,131),(8,'-1',1478257977870,100),(7,'-1',1478258007863,132),(8,'-1',1478258007863,100),(7,'-1',1478258037869,132),(8,'-1',1478258037869,100),(7,'-1',1478258067864,131),(8,'-1',1478258067864,100),(7,'-1',1478258097869,131),(8,'-1',1478258097869,100),(7,'-1',1478258127868,131),(8,'-1',1478258127868,100),(7,'-1',1478258157863,131),(8,'-1',1478258157863,100),(7,'-1',1478258187864,131),(8,'-1',1478258187864,100),(7,'-1',1478258217869,131),(8,'-1',1478258217869,100),(7,'-1',1478258247864,131),(8,'-1',1478258247864,100),(7,'-1',1478258277865,131),(8,'-1',1478258277865,100),(7,'-1',1478258307869,132),(8,'-1',1478258307869,100),(7,'-1',1478258337864,132),(8,'-1',1478258337864,100),(7,'-1',1478258367864,131),(8,'-1',1478258367864,100),(7,'-1',1478258397864,131),(8,'-1',1478258397864,100),(7,'-1',1478258427868,131),(8,'-1',1478258427868,100),(7,'-1',1478258457866,131),(8,'-1',1478258457866,100),(7,'-1',1478258487866,131),(8,'-1',1478258487866,100),(7,'-1',1478258517865,131),(8,'-1',1478258517865,100),(7,'-1',1478258547867,131),(8,'-1',1478258547867,100),(7,'-1',1478258577865,131),(8,'-1',1478258577865,100),(7,'-1',1478258607864,132),(8,'-1',1478258607864,100),(7,'-1',1478258637864,132),(8,'-1',1478258637864,100),(7,'-1',1478258667865,131),(8,'-1',1478258667865,100),(7,'-1',1478258697867,131),(8,'-1',1478258697867,100),(7,'-1',1478258727866,131),(8,'-1',1478258727866,100),(7,'-1',1478258757864,131),(8,'-1',1478258757864,100),(7,'-1',1478258787867,131),(8,'-1',1478258787867,100),(7,'-1',1478258817865,131),(8,'-1',1478258817865,100),(7,'-1',1478258847868,131),(8,'-1',1478258847868,100),(7,'-1',1478258877864,131),(8,'-1',1478258877864,100),(7,'-1',1478258907866,132),(8,'-1',1478258907866,100),(7,'-1',1478258937867,132),(8,'-1',1478258937867,100),(7,'-1',1478258967867,131),(8,'-1',1478258967867,100),(7,'-1',1478258997866,131),(8,'-1',1478258997866,100),(7,'-1',1478259027868,131),(8,'-1',1478259027868,100),(7,'-1',1478259057866,131),(8,'-1',1478259057866,100),(7,'-1',1478259087868,131),(8,'-1',1478259087868,100),(7,'-1',1478259117868,131),(8,'-1',1478259117868,100),(7,'-1',1478259147866,131),(8,'-1',1478259147866,100),(7,'-1',1478259177863,131),(8,'-1',1478259177863,100),(7,'-1',1478259207867,132),(8,'-1',1478259207867,100),(7,'-1',1478259237865,132),(8,'-1',1478259237865,100),(7,'-1',1478259267869,131),(8,'-1',1478259267869,100),(7,'-1',1478259297863,131),(8,'-1',1478259297863,100),(7,'-1',1478259327869,131),(8,'-1',1478259327869,100),(7,'-1',1478259357868,131),(8,'-1',1478259357868,100),(7,'-1',1478259387866,131),(8,'-1',1478259387866,100),(7,'-1',1478259417864,131),(8,'-1',1478259417864,100),(7,'-1',1478259447864,131),(8,'-1',1478259447864,100),(7,'-1',1478259477864,131),(8,'-1',1478259477864,100),(7,'-1',1478259507864,132),(8,'-1',1478259507864,100),(7,'-1',1478259537865,132),(8,'-1',1478259537865,100),(7,'-1',1478259567864,131),(8,'-1',1478259567864,100),(7,'-1',1478259597864,131),(8,'-1',1478259597864,100),(7,'-1',1478259627867,131),(8,'-1',1478259627867,100),(7,'-1',1478259657864,131),(8,'-1',1478259657864,100),(7,'-1',1478259687866,131),(8,'-1',1478259687866,100),(7,'-1',1478259717865,131),(8,'-1',1478259717865,100),(7,'-1',1478259747864,137),(8,'-1',1478259747864,100),(7,'-1',1478259777865,136),(8,'-1',1478259777865,100),(7,'-1',1478259807863,135),(8,'-1',1478259807863,100),(7,'-1',1478259837865,135),(8,'-1',1478259837865,100),(7,'-1',1478259867865,132),(8,'-1',1478259867865,100),(7,'-1',1478259897863,137),(8,'-1',1478259897863,100),(7,'-1',1478259927866,136),(8,'-1',1478259927866,100),(7,'-1',1478259957866,134),(8,'-1',1478259957866,100),(7,'-1',1478259987866,134),(8,'-1',1478259987866,100),(7,'-1',1478260017868,136),(8,'-1',1478260017868,100),(7,'-1',1478260047867,133),(8,'-1',1478260047867,100),(7,'-1',1478260077869,136),(8,'-1',1478260077869,100),(7,'-1',1478260107865,137),(8,'-1',1478260107865,100),(7,'-1',1478260137868,139),(8,'-1',1478260137868,100),(7,'-1',1478260167867,134),(8,'-1',1478260167867,100),(7,'-1',1478260197868,136),(8,'-1',1478260197868,100),(7,'-1',1478260227868,134),(8,'-1',1478260227868,100),(7,'-1',1478260257868,133),(8,'-1',1478260257868,100),(7,'-1',1478260287868,131),(8,'-1',1478260287868,100),(7,'-1',1478260317864,131),(8,'-1',1478260317864,100),(7,'-1',1478260347864,131),(8,'-1',1478260347864,100),(7,'-1',1478260377863,131),(8,'-1',1478260377863,100),(7,'-1',1478260407870,133),(8,'-1',1478260407870,100),(7,'-1',1478260437864,134),(8,'-1',1478260437864,100),(7,'-1',1478260467869,133),(8,'-1',1478260467869,100),(7,'-1',1478260497869,132),(8,'-1',1478260497869,100),(7,'-1',1478260527865,132),(8,'-1',1478260527865,100),(7,'-1',1478260557864,131),(8,'-1',1478260557864,100),(7,'-1',1478260587868,134),(8,'-1',1478260587868,100),(7,'-1',1478260617864,132),(8,'-1',1478260617864,100),(7,'-1',1478260647866,132),(8,'-1',1478260647866,100),(7,'-1',1478260677865,131),(8,'-1',1478260677865,100),(7,'-1',1478260707865,132),(8,'-1',1478260707865,100),(7,'-1',1478260737863,132),(8,'-1',1478260737863,100),(7,'-1',1478260767866,134),(8,'-1',1478260767866,100),(7,'-1',1478260797865,132),(8,'-1',1478260797865,100),(7,'-1',1478260827866,135),(8,'-1',1478260827866,100),(7,'-1',1478260857866,133),(8,'-1',1478260857866,100),(7,'-1',1478260887867,135),(8,'-1',1478260887867,100),(7,'-1',1478260917867,133),(8,'-1',1478260917867,100),(7,'-1',1478260947867,133),(8,'-1',1478260947867,100),(7,'-1',1478260977867,133),(8,'-1',1478260977867,100),(7,'-1',1478261007867,133),(8,'-1',1478261007867,100),(7,'-1',1478261037867,132),(8,'-1',1478261037867,100),(7,'-1',1478261067867,131),(8,'-1',1478261067867,100),(7,'-1',1478261097868,131),(8,'-1',1478261097868,100),(7,'-1',1478261127869,131),(8,'-1',1478261127869,100),(7,'-1',1478261157868,131),(8,'-1',1478261157868,100),(7,'-1',1478261187869,131),(8,'-1',1478261187869,100),(7,'-1',1478261217863,131),(8,'-1',1478261217863,100),(7,'-1',1478261247866,131),(8,'-1',1478261247866,100),(7,'-1',1478261277869,131),(8,'-1',1478261277869,100),(7,'-1',1478261307869,132),(8,'-1',1478261307869,100),(7,'-1',1478261337868,132),(8,'-1',1478261337868,100),(7,'-1',1478261367869,131),(8,'-1',1478261367869,100),(7,'-1',1478261397865,131),(8,'-1',1478261397865,100),(7,'-1',1478261427867,131),(8,'-1',1478261427867,100),(7,'-1',1478261457866,131),(8,'-1',1478261457866,100),(7,'-1',1478261487865,131),(8,'-1',1478261487865,100),(7,'-1',1478261517867,131),(8,'-1',1478261517867,100),(7,'-1',1478261547864,131),(8,'-1',1478261547864,100),(7,'-1',1478261577864,131),(8,'-1',1478261577864,100),(7,'-1',1478261607865,132),(8,'-1',1478261607865,100),(7,'-1',1478261637865,132),(8,'-1',1478261637865,100),(7,'-1',1478261667864,131),(8,'-1',1478261667864,100),(7,'-1',1478261697869,131),(8,'-1',1478261697869,100),(7,'-1',1478261727865,131),(8,'-1',1478261727865,100),(7,'-1',1478261757868,131),(8,'-1',1478261757868,100),(7,'-1',1478261787867,131),(8,'-1',1478261787867,100),(7,'-1',1478261817867,131),(8,'-1',1478261817867,100),(7,'-1',1478261847868,131),(8,'-1',1478261847868,100),(7,'-1',1478261877868,131),(8,'-1',1478261877868,100),(7,'-1',1478261907867,132),(8,'-1',1478261907867,100),(7,'-1',1478261937866,132),(8,'-1',1478261937866,100),(7,'-1',1478261967867,131),(8,'-1',1478261967867,100),(7,'-1',1478261997870,131),(8,'-1',1478261997870,100),(7,'-1',1478262027863,131),(8,'-1',1478262027863,100),(7,'-1',1478262057863,131),(8,'-1',1478262057863,100),(7,'-1',1478262087864,131),(8,'-1',1478262087864,100),(7,'-1',1478262117868,131),(8,'-1',1478262117868,100),(7,'-1',1478262147869,131),(8,'-1',1478262147869,100),(7,'-1',1478262177868,131),(8,'-1',1478262177868,100),(7,'-1',1478262207868,132),(8,'-1',1478262207868,100),(7,'-1',1478262237864,132),(8,'-1',1478262237864,100),(7,'-1',1478262267869,131),(8,'-1',1478262267869,100),(7,'-1',1478262297866,131),(8,'-1',1478262297866,100),(7,'-1',1478262327868,131),(8,'-1',1478262327868,100),(7,'-1',1478262357864,131),(8,'-1',1478262357864,100),(7,'-1',1478262387863,131),(8,'-1',1478262387863,100),(7,'-1',1478262417868,131),(8,'-1',1478262417868,100),(7,'-1',1478262447865,131),(8,'-1',1478262447865,100),(7,'-1',1478262477866,131),(8,'-1',1478262477866,100),(7,'-1',1478262507866,132),(8,'-1',1478262507866,100),(7,'-1',1478262537868,132),(8,'-1',1478262537868,100),(7,'-1',1478262567866,131),(8,'-1',1478262567866,100),(7,'-1',1478262597868,131),(8,'-1',1478262597868,100),(7,'-1',1478262627865,131),(8,'-1',1478262627865,100),(7,'-1',1478262657864,131),(8,'-1',1478262657864,100),(7,'-1',1478262687868,131),(8,'-1',1478262687868,100),(7,'-1',1478262717870,131),(8,'-1',1478262717870,100),(7,'-1',1478262747866,131),(8,'-1',1478262747866,100),(7,'-1',1478262777865,131),(8,'-1',1478262777865,100),(7,'-1',1478262807863,132),(8,'-1',1478262807863,100),(7,'-1',1478262837865,132),(8,'-1',1478262837865,100),(7,'-1',1478262867866,131),(8,'-1',1478262867866,100),(7,'-1',1478262897867,131),(8,'-1',1478262897867,100),(7,'-1',1478262927867,131),(8,'-1',1478262927867,100),(7,'-1',1478262957865,131),(8,'-1',1478262957865,100),(7,'-1',1478262987868,131),(8,'-1',1478262987868,100),(7,'-1',1478263017870,131),(8,'-1',1478263017870,100),(7,'-1',1478263047864,131),(8,'-1',1478263047864,100),(7,'-1',1478263077866,131),(8,'-1',1478263077866,100),(7,'-1',1478263107866,132),(8,'-1',1478263107866,100),(7,'-1',1478263137868,132),(8,'-1',1478263137868,100),(7,'-1',1478263167866,131),(8,'-1',1478263167866,100),(7,'-1',1478263197863,131),(8,'-1',1478263197863,100),(7,'-1',1478263227871,131),(8,'-1',1478263227871,100),(7,'-1',1478263257870,131),(8,'-1',1478263257870,100),(7,'-1',1478263287870,131),(8,'-1',1478263287870,100),(7,'-1',1478263317864,131),(8,'-1',1478263317864,100),(7,'-1',1478263347863,131),(8,'-1',1478263347863,100),(7,'-1',1478263377867,134),(8,'-1',1478263377867,100),(7,'-1',1478263407864,135),(8,'-1',1478263407864,100),(7,'-1',1478263437867,136),(8,'-1',1478263437867,100),(7,'-1',1478263467867,135),(8,'-1',1478263467867,100),(7,'-1',1478263497866,133),(8,'-1',1478263497866,100),(7,'-1',1478263527863,134),(8,'-1',1478263527863,100),(7,'-1',1478263557864,138),(8,'-1',1478263557864,100),(7,'-1',1478263587865,137),(8,'-1',1478263587865,100),(7,'-1',1478263617864,134),(8,'-1',1478263617864,100),(7,'-1',1478263647864,136),(8,'-1',1478263647864,100),(7,'-1',1478263677869,135),(8,'-1',1478263677869,100),(7,'-1',1478263707868,137),(8,'-1',1478263707868,100),(7,'-1',1478263737864,135),(8,'-1',1478263737864,100),(7,'-1',1478263767865,134),(8,'-1',1478263767865,100),(7,'-1',1478263797866,134),(8,'-1',1478263797866,100),(7,'-1',1478263827867,134),(8,'-1',1478263827867,100),(7,'-1',1478263857863,134),(8,'-1',1478263857863,100),(7,'-1',1478263887867,131),(8,'-1',1478263887867,100),(7,'-1',1478263917867,131),(8,'-1',1478263917867,100),(7,'-1',1478263947869,131),(8,'-1',1478263947869,100),(7,'-1',1478263977869,131),(8,'-1',1478263977869,100),(7,'-1',1478264007867,133),(8,'-1',1478264007867,100),(7,'-1',1478264037868,133),(8,'-1',1478264037868,100),(7,'-1',1478264067865,132),(8,'-1',1478264067865,100),(7,'-1',1478264097868,132),(8,'-1',1478264097868,100),(7,'-1',1478264127864,132),(8,'-1',1478264127864,100),(7,'-1',1478264157865,131),(8,'-1',1478264157865,100),(7,'-1',1478264187864,131),(8,'-1',1478264187864,100),(7,'-1',1478264217867,132),(8,'-1',1478264217867,100),(7,'-1',1478264247864,132),(8,'-1',1478264247864,100),(7,'-1',1478264277866,131),(8,'-1',1478264277866,100),(7,'-1',1478264307867,132),(8,'-1',1478264307867,100),(7,'-1',1478264337863,132),(8,'-1',1478264337863,100),(7,'-1',1478264367863,134),(8,'-1',1478264367863,100),(7,'-1',1478264397867,132),(8,'-1',1478264397867,100),(7,'-1',1478264427865,135),(8,'-1',1478264427865,100),(7,'-1',1478264457866,133),(8,'-1',1478264457866,100),(7,'-1',1478264487864,133),(8,'-1',1478264487864,100),(7,'-1',1478264517865,132),(8,'-1',1478264517865,100),(7,'-1',1478264547864,135),(8,'-1',1478264547864,100),(7,'-1',1478264577866,133),(8,'-1',1478264577866,100),(7,'-1',1478264607865,133),(8,'-1',1478264607865,100),(7,'-1',1478264637869,132),(8,'-1',1478264637869,100),(7,'-1',1478264667866,131),(8,'-1',1478264667866,100),(7,'-1',1478264697866,131),(8,'-1',1478264697866,100),(7,'-1',1478264727867,131),(8,'-1',1478264727867,100),(7,'-1',1478264757864,131),(8,'-1',1478264757864,100),(7,'-1',1478264787866,131),(8,'-1',1478264787866,100),(7,'-1',1478264817865,131),(8,'-1',1478264817865,100),(7,'-1',1478264847866,131),(8,'-1',1478264847866,100),(7,'-1',1478264877868,131),(8,'-1',1478264877868,100),(7,'-1',1478264907867,132),(8,'-1',1478264907867,100),(7,'-1',1478264937867,132),(8,'-1',1478264937867,100),(7,'-1',1478264967864,131),(8,'-1',1478264967864,100),(7,'-1',1478264997867,131),(8,'-1',1478264997867,100),(7,'-1',1478265027868,131),(8,'-1',1478265027868,100),(7,'-1',1478265057864,131),(8,'-1',1478265057864,100),(7,'-1',1478265087867,131),(8,'-1',1478265087867,100),(7,'-1',1478265117868,131),(8,'-1',1478265117868,100),(7,'-1',1478265147868,131),(8,'-1',1478265147868,100),(7,'-1',1478265177863,131),(8,'-1',1478265177863,100),(7,'-1',1478265207869,132),(8,'-1',1478265207869,100),(7,'-1',1478265237865,132),(8,'-1',1478265237865,100),(7,'-1',1478265267864,131),(8,'-1',1478265267864,100),(7,'-1',1478265297865,131),(8,'-1',1478265297865,100),(7,'-1',1478265327864,131),(8,'-1',1478265327864,100),(7,'-1',1478265357867,131),(8,'-1',1478265357867,100),(7,'-1',1478265387865,132),(8,'-1',1478265387865,100),(7,'-1',1478265417866,131),(8,'-1',1478265417866,100),(7,'-1',1478265447868,131),(8,'-1',1478265447868,100),(7,'-1',1478265477865,131),(8,'-1',1478265477865,100),(7,'-1',1478265507865,132),(8,'-1',1478265507865,100),(7,'-1',1478265537866,132),(8,'-1',1478265537866,100),(7,'-1',1478265567867,131),(8,'-1',1478265567867,100),(7,'-1',1478265597867,131),(8,'-1',1478265597867,100),(7,'-1',1478265627866,131),(8,'-1',1478265627866,100),(7,'-1',1478265657863,131),(8,'-1',1478265657863,100),(7,'-1',1478265687865,131),(8,'-1',1478265687865,100),(7,'-1',1478265717867,131),(8,'-1',1478265717867,100),(7,'-1',1478265747867,131),(8,'-1',1478265747867,100),(7,'-1',1478265777866,131),(8,'-1',1478265777866,100),(7,'-1',1478265807866,132),(8,'-1',1478265807866,100),(7,'-1',1478265837865,132),(8,'-1',1478265837865,100),(7,'-1',1478265867866,131),(8,'-1',1478265867866,100),(7,'-1',1478265897864,131),(8,'-1',1478265897864,100),(7,'-1',1478265927868,131),(8,'-1',1478265927868,100),(7,'-1',1478265957866,131),(8,'-1',1478265957866,100),(7,'-1',1478265987863,131),(8,'-1',1478265987863,100),(7,'-1',1478266017867,131),(8,'-1',1478266017867,100),(7,'-1',1478266047864,131),(8,'-1',1478266047864,100),(7,'-1',1478266077867,131),(8,'-1',1478266077867,100),(7,'-1',1478266107866,132),(8,'-1',1478266107866,100),(7,'-1',1478266137864,132),(8,'-1',1478266137864,100),(7,'-1',1478266167867,132),(8,'-1',1478266167867,100),(7,'-1',1478266197867,131),(8,'-1',1478266197867,100),(7,'-1',1478266227864,131),(8,'-1',1478266227864,100),(7,'-1',1478266257866,131),(8,'-1',1478266257866,100),(7,'-1',1478266287865,131),(8,'-1',1478266287865,100),(7,'-1',1478266317864,131),(8,'-1',1478266317864,100),(7,'-1',1478266347865,131),(8,'-1',1478266347865,100),(27,'-1',1478266365648,32),(28,'-1',1478266380654,84),(7,'-1',1478266380656,178),(8,'-1',1478266380656,100),(27,'-1',1478266395661,28),(28,'-1',1478266395661,85),(7,'-1',1478266410656,161),(7,'-1',1478266425658,161),(8,'-1',1478266425658,100),(27,'-1',1478266425663,28),(28,'-1',1478266425663,87),(7,'-1',1478266455659,161),(8,'-1',1478266455659,100),(27,'-1',1478266455665,28),(28,'-1',1478266455665,87),(8,'-1',1478266485660,100),(27,'-1',1478266485660,28),(27,'-1',1478266500668,28),(7,'-1',1478266500668,160),(28,'-1',1478266500668,88),(8,'-1',1478266500668,100),(27,'-1',1478266530669,28),(7,'-1',1478266530669,160),(8,'-1',1478266530669,100),(28,'-1',1478266530669,88),(7,'-1',1478266560669,160),(27,'-1',1478266560669,30),(8,'-1',1478266560669,100),(28,'-1',1478266560669,95),(27,'-1',1478266590669,28),(7,'-1',1478266590670,160),(8,'-1',1478266590670,100),(28,'-1',1478266590669,100),(7,'-1',1478266620670,160),(27,'-1',1478266620669,28),(8,'-1',1478266620670,100),(28,'-1',1478266620669,89),(27,'-1',1478266650671,28),(7,'-1',1478266650671,155),(8,'-1',1478266650671,100),(28,'-1',1478266650671,100),(27,'-1',1478266680668,28),(7,'-1',1478266680668,155),(28,'-1',1478266680668,91),(8,'-1',1478266680668,100),(7,'-1',1478266710671,156),(27,'-1',1478266710670,28),(28,'-1',1478266710670,91),(8,'-1',1478266710671,100),(27,'-1',1478266740668,28),(7,'-1',1478266740668,156),(28,'-1',1478266740668,91),(8,'-1',1478266740668,100),(27,'-1',1478266770665,28),(28,'-1',1478266770665,100),(7,'-1',1478266770671,184),(8,'-1',1478266770671,100),(27,'-1',1478266800666,28),(28,'-1',1478266800666,100),(7,'-1',1478266800672,184),(8,'-1',1478266800672,100),(7,'-1',1478266830667,146),(8,'-1',1478266830667,100),(7,'-1',1478266860671,147),(8,'-1',1478266860671,100),(7,'-1',1478266890671,174),(8,'-1',1478266890671,100),(7,'-1',1478266920669,174),(8,'-1',1478266920669,100),(7,'-1',1478266950671,160),(8,'-1',1478266950671,100),(7,'-1',1478266980672,189),(8,'-1',1478266980672,100),(7,'-1',1478267010671,150),(8,'-1',1478267010671,100),(7,'-1',1478267040671,148),(8,'-1',1478267040671,100),(7,'-1',1478267070669,145),(8,'-1',1478267070669,100),(7,'-1',1478267100673,143),(8,'-1',1478267100673,100),(7,'-1',1478267130671,146),(8,'-1',1478267130671,100),(7,'-1',1478267160669,146),(8,'-1',1478267160669,100),(7,'-1',1478267190671,148),(8,'-1',1478267190671,100),(7,'-1',1478267220670,144),(8,'-1',1478267220670,100),(7,'-1',1478267250668,144),(8,'-1',1478267250668,100),(7,'-1',1478267280669,145),(8,'-1',1478267280669,100),(7,'-1',1478267310668,146),(8,'-1',1478267310668,100),(7,'-1',1478267340672,146),(8,'-1',1478267340672,100),(7,'-1',1478267370667,143),(8,'-1',1478267370667,100),(7,'-1',1478267400669,147),(8,'-1',1478267400669,100),(7,'-1',1478267430672,145),(8,'-1',1478267430672,100),(7,'-1',1478267460669,142),(8,'-1',1478267460669,100),(7,'-1',1478267490667,141),(8,'-1',1478267490667,100),(7,'-1',1478267520669,140),(8,'-1',1478267520669,100),(7,'-1',1478267550670,140),(8,'-1',1478267550670,100),(7,'-1',1478267580670,140),(8,'-1',1478267580670,100),(7,'-1',1478267610671,142),(8,'-1',1478267610671,100),(7,'-1',1478267640667,144),(8,'-1',1478267640667,100),(7,'-1',1478267670670,141),(8,'-1',1478267670670,100),(7,'-1',1478267700670,141),(8,'-1',1478267700670,100),(7,'-1',1478267730671,141),(8,'-1',1478267730671,100),(7,'-1',1478267760671,140),(8,'-1',1478267760671,100),(7,'-1',1478267790671,140),(8,'-1',1478267790671,100),(7,'-1',1478267820673,141),(8,'-1',1478267820673,100),(7,'-1',1478267850671,141),(8,'-1',1478267850671,100),(7,'-1',1478267880673,140),(8,'-1',1478267880673,100),(7,'-1',1478267910670,141),(8,'-1',1478267910670,100),(7,'-1',1478267940670,141),(8,'-1',1478267940670,100),(7,'-1',1478267970672,143),(8,'-1',1478267970672,100),(7,'-1',1478268000672,141),(8,'-1',1478268000672,100),(7,'-1',1478268030673,145),(8,'-1',1478268030673,100),(7,'-1',1478268060672,145),(8,'-1',1478268060672,100),(7,'-1',1478268090667,142),(8,'-1',1478268090667,100),(7,'-1',1478268120667,142),(8,'-1',1478268120667,100),(7,'-1',1478268150669,143),(8,'-1',1478268150669,100),(7,'-1',1478268180669,144),(8,'-1',1478268180669,100),(7,'-1',1478268210667,143),(8,'-1',1478268210667,100),(7,'-1',1478268240672,142),(8,'-1',1478268240672,100),(7,'-1',1478268270668,140),(8,'-1',1478268270668,100),(7,'-1',1478268300669,140),(8,'-1',1478268300669,100),(7,'-1',1478268330669,140),(8,'-1',1478268330669,100),(7,'-1',1478268360668,140),(8,'-1',1478268360668,100),(7,'-1',1478268390669,140),(8,'-1',1478268390669,100),(7,'-1',1478268420669,140),(8,'-1',1478268420669,100),(7,'-1',1478268450670,140),(8,'-1',1478268450670,100),(7,'-1',1478268480669,140),(8,'-1',1478268480669,100),(7,'-1',1478268510671,141),(8,'-1',1478268510671,100),(7,'-1',1478268540670,141),(8,'-1',1478268540670,100),(7,'-1',1478268570667,140),(8,'-1',1478268570667,100),(7,'-1',1478268600670,140),(8,'-1',1478268600670,100),(7,'-1',1478268630673,140),(8,'-1',1478268630673,100),(7,'-1',1478268660667,140),(8,'-1',1478268660667,100),(7,'-1',1478268690669,140),(8,'-1',1478268690669,100),(7,'-1',1478268720670,140),(8,'-1',1478268720670,100),(7,'-1',1478268750668,140),(8,'-1',1478268750668,100),(7,'-1',1478268780671,140),(8,'-1',1478268780671,100),(7,'-1',1478268810671,141),(8,'-1',1478268810671,100),(7,'-1',1478268840672,141),(8,'-1',1478268840672,100),(7,'-1',1478268870668,140),(8,'-1',1478268870668,100),(7,'-1',1478268900672,140),(8,'-1',1478268900672,100),(7,'-1',1478268930674,140),(8,'-1',1478268930674,100),(7,'-1',1478268960672,140),(8,'-1',1478268960672,100),(7,'-1',1478268990667,140),(8,'-1',1478268990667,100),(7,'-1',1478269020668,140),(8,'-1',1478269020668,100),(7,'-1',1478269050668,140),(8,'-1',1478269050668,100),(7,'-1',1478269080670,140),(8,'-1',1478269080670,100),(7,'-1',1478269110667,141),(8,'-1',1478269110667,100),(7,'-1',1478269140672,141),(8,'-1',1478269140672,100),(7,'-1',1478269170667,140),(8,'-1',1478269170667,100),(7,'-1',1478269200669,140),(8,'-1',1478269200669,100),(7,'-1',1478269230668,141),(8,'-1',1478269230668,100),(7,'-1',1478269260668,140),(8,'-1',1478269260668,100),(7,'-1',1478269290670,140),(8,'-1',1478269290670,100),(7,'-1',1478269320670,140),(8,'-1',1478269320670,100),(7,'-1',1478269350669,140),(8,'-1',1478269350669,100),(7,'-1',1478269380668,140),(8,'-1',1478269380668,100),(7,'-1',1478269410671,141),(8,'-1',1478269410671,100),(7,'-1',1478269440669,141),(8,'-1',1478269440669,100),(7,'-1',1478269470669,140),(8,'-1',1478269470669,100),(7,'-1',1478269500673,140),(8,'-1',1478269500673,100),(7,'-1',1478269530671,140),(8,'-1',1478269530671,100),(7,'-1',1478269560670,140),(8,'-1',1478269560670,100),(7,'-1',1478269590671,140),(8,'-1',1478269590671,100),(7,'-1',1478269620671,140),(8,'-1',1478269620671,100),(7,'-1',1478269650672,140),(8,'-1',1478269650672,100),(7,'-1',1478269680669,140),(8,'-1',1478269680669,100),(7,'-1',1478269710671,141),(8,'-1',1478269710671,100),(7,'-1',1478269740672,141),(8,'-1',1478269740672,100),(7,'-1',1478269770669,140),(8,'-1',1478269770669,100),(7,'-1',1478269800672,140),(8,'-1',1478269800672,100),(7,'-1',1478269830671,140),(8,'-1',1478269830671,100),(7,'-1',1478269860672,140),(8,'-1',1478269860672,100),(7,'-1',1478269890668,140),(8,'-1',1478269890668,100),(7,'-1',1478269920673,140),(8,'-1',1478269920673,100),(7,'-1',1478269950667,140),(8,'-1',1478269950667,100),(7,'-1',1478269980673,140),(8,'-1',1478269980673,100),(7,'-1',1478270010668,141),(8,'-1',1478270010668,100),(7,'-1',1478270040671,141),(8,'-1',1478270040671,100),(7,'-1',1478270070667,140),(8,'-1',1478270070667,100),(7,'-1',1478270100669,140),(8,'-1',1478270100669,100),(7,'-1',1478270130669,140),(8,'-1',1478270130669,100),(7,'-1',1478270160668,140),(8,'-1',1478270160668,100),(7,'-1',1478270190670,141),(8,'-1',1478270190670,100),(7,'-1',1478270220668,140),(8,'-1',1478270220668,100),(7,'-1',1478270250669,140),(8,'-1',1478270250669,100),(7,'-1',1478270280668,140),(8,'-1',1478270280668,100),(7,'-1',1478270310670,141),(8,'-1',1478270310670,100),(7,'-1',1478270340674,141),(8,'-1',1478270340674,100),(7,'-1',1478270370668,140),(8,'-1',1478270370668,100),(7,'-1',1478270400669,140),(8,'-1',1478270400669,100),(7,'-1',1478270430669,140),(8,'-1',1478270430669,100),(7,'-1',1478270460670,140),(8,'-1',1478270460670,100),(7,'-1',1478270490670,140),(8,'-1',1478270490670,100),(7,'-1',1478270520671,140),(8,'-1',1478270520671,100),(7,'-1',1478270550672,140),(8,'-1',1478270550672,100),(7,'-1',1478270580669,147),(8,'-1',1478270580669,100),(7,'-1',1478270610670,148),(8,'-1',1478270610670,100),(7,'-1',1478270640672,146),(8,'-1',1478270640672,100),(7,'-1',1478270670671,145),(8,'-1',1478270670671,100);
/*!40000 ALTER TABLE `NMS_STATUS_MONITOR11_4_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NMS_STATUS_MONITOR11_5_2016`
--

DROP TABLE IF EXISTS `NMS_STATUS_MONITOR11_5_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NMS_STATUS_MONITOR11_5_2016` (
  `POLLID` bigint(20) DEFAULT NULL,
  `INSTANCE` varchar(100) DEFAULT NULL,
  `TTIME` bigint(20) DEFAULT NULL,
  `VAL` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NMS_STATUS_MONITOR11_5_2016`
--

LOCK TABLES `NMS_STATUS_MONITOR11_5_2016` WRITE;
/*!40000 ALTER TABLE `NMS_STATUS_MONITOR11_5_2016` DISABLE KEYS */;
/*!40000 ALTER TABLE `NMS_STATUS_MONITOR11_5_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NamedViewToAuthorizedViewTable`
--

DROP TABLE IF EXISTS `NamedViewToAuthorizedViewTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NamedViewToAuthorizedViewTable` (
  `NAMEDVIEWNAME` varchar(50) NOT NULL,
  `AUTHORIZEDVIEWNAME` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NamedViewToAuthorizedViewTable`
--

LOCK TABLES `NamedViewToAuthorizedViewTable` WRITE;
/*!40000 ALTER TABLE `NamedViewToAuthorizedViewTable` DISABLE KEYS */;
INSERT INTO `NamedViewToAuthorizedViewTable` VALUES ('Events','NULL'),('Alerts','NULL'),('Network Database','NULL'),('Maps','NULL'),('Stats Admin','NULL'),('Provisioning','NULL');
/*!40000 ALTER TABLE `NamedViewToAuthorizedViewTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Network`
--

DROP TABLE IF EXISTS `Network`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Network` (
  `MOID` bigint(20) NOT NULL,
  `DISCOVER` bit(1) DEFAULT NULL,
  `DISCOVERYSTATUS` int(11) DEFAULT NULL,
  `PARENTNETMASK` varchar(100) DEFAULT NULL,
  `PARENTNETWORK` varchar(100) NOT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FKD119F20E5044AD45` (`MOID`),
  CONSTRAINT `FKD119F20E5044AD45` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Network`
--

LOCK TABLES `Network` WRITE;
/*!40000 ALTER TABLE `Network` DISABLE KEYS */;
INSERT INTO `Network` VALUES (1,'',3,'','NULL'),(42,'\0',4,'','NULL');
/*!40000 ALTER TABLE `Network` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NetworkInventory`
--

DROP TABLE IF EXISTS `NetworkInventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NetworkInventory` (
  `LASTMODIFIEDTIME` varchar(25) DEFAULT NULL,
  `MODEL` varchar(50) DEFAULT NULL,
  `TYPE` varchar(50) DEFAULT NULL,
  `CONTACT` varchar(50) DEFAULT NULL,
  `CLASSNAME` varchar(50) DEFAULT NULL,
  `MANAGEDOBJECTNAME` varchar(50) DEFAULT NULL,
  `OPERATIONALSTATUS` varchar(50) DEFAULT NULL,
  `NAME` varchar(50) NOT NULL,
  `LOCATION` varchar(50) DEFAULT NULL,
  `ADMINSTATUS` varchar(50) DEFAULT NULL,
  `VENDOR` varchar(50) DEFAULT NULL,
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`NAME`),
  KEY `NetworkInventory0_ndx` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NetworkInventory`
--

LOCK TABLES `NetworkInventory` WRITE;
/*!40000 ALTER TABLE `NetworkInventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `NetworkInventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Node`
--

DROP TABLE IF EXISTS `Node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Node` (
  `MOID` bigint(20) NOT NULL,
  `ISROUTER` bit(1) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK2522223D370DA5` (`MOID`),
  CONSTRAINT `FK2522223D370DA5` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Node`
--

LOCK TABLES `Node` WRITE;
/*!40000 ALTER TABLE `Node` DISABLE KEYS */;
INSERT INTO `Node` VALUES (3,'\0'),(5,'\0'),(7,'\0'),(9,'\0'),(11,'\0'),(13,'\0'),(15,'\0'),(17,'\0'),(19,'\0'),(21,'\0'),(23,'\0'),(25,'\0'),(27,'\0'),(29,'\0'),(31,'\0'),(33,'\0'),(35,'\0'),(37,'\0'),(39,'\0'),(41,'\0'),(45,'\0'),(47,'\0'),(49,'\0'),(51,'\0'),(53,'\0'),(55,'\0'),(57,'\0'),(59,'\0'),(61,'\0'),(63,'\0'),(65,'\0'),(67,'\0'),(69,'\0'),(71,'\0'),(73,'\0'),(75,'\0'),(77,'\0'),(79,'\0'),(81,'\0'),(83,'\0'),(85,'\0'),(87,'\0'),(89,'\0'),(91,'\0'),(93,'\0'),(95,'\0'),(97,'\0'),(99,'\0'),(101,'\0'),(103,'\0'),(105,'\0'),(107,'\0'),(109,'\0'),(111,'\0'),(113,'\0'),(115,'\0'),(117,'\0'),(119,'\0'),(121,'\0'),(123,'\0'),(125,'\0'),(127,'\0'),(129,'\0'),(131,'\0'),(133,'\0'),(135,'\0'),(137,'\0'),(139,'\0'),(141,'\0'),(143,'\0'),(145,'\0'),(147,'\0'),(149,'\0'),(151,'\0'),(153,'\0'),(155,'\0'),(157,'\0'),(159,'\0'),(161,'\0'),(163,'\0'),(165,'\0'),(167,'\0'),(169,'\0'),(171,'\0'),(173,'\0'),(175,'\0'),(177,'\0'),(179,'\0'),(181,'\0'),(183,'\0'),(185,'\0'),(187,'\0'),(189,'\0'),(191,'\0'),(193,'\0'),(196,'\0'),(199,'\0'),(202,'\0'),(205,'\0'),(208,'\0'),(211,'\0'),(214,'\0'),(217,'\0'),(220,'\0'),(223,'\0'),(226,'\0'),(229,'\0'),(232,'\0'),(235,'\0'),(238,'\0'),(241,'\0'),(244,'\0'),(247,'\0'),(250,'\0'),(253,'\0'),(256,'\0'),(259,'\0'),(262,'\0'),(265,'\0'),(268,'\0'),(271,'\0'),(274,'\0');
/*!40000 ALTER TABLE `Node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `NotificationLog`
--

DROP TABLE IF EXISTS `NotificationLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `NotificationLog` (
  `NOTIINDEX` int(11) NOT NULL,
  `LOGTIME` bigint(20) DEFAULT NULL,
  `NOVARBINDS` int(11) DEFAULT NULL,
  `NOTIOID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`NOTIINDEX`),
  KEY `NotificationLog0_ndx` (`NOTIINDEX`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `NotificationLog`
--

LOCK TABLES `NotificationLog` WRITE;
/*!40000 ALTER TABLE `NotificationLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `NotificationLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OBJECTSTOLINK`
--

DROP TABLE IF EXISTS `OBJECTSTOLINK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OBJECTSTOLINK` (
  `KEYSTRING` varchar(250) NOT NULL,
  `VALUESTRING` varchar(250) DEFAULT NULL,
  KEY `OBJECTSTOLINK0_ndx` (`KEYSTRING`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OBJECTSTOLINK`
--

LOCK TABLES `OBJECTSTOLINK` WRITE;
/*!40000 ALTER TABLE `OBJECTSTOLINK` DISABLE KEYS */;
/*!40000 ALTER TABLE `OBJECTSTOLINK` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ObjectSchedulerRUNNABLE`
--

DROP TABLE IF EXISTS `ObjectSchedulerRUNNABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ObjectSchedulerRUNNABLE` (
  `MOID` bigint(20) NOT NULL,
  `TIMEVAL` bigint(20) NOT NULL,
  `CLASSNAME` varchar(150) NOT NULL,
  PRIMARY KEY (`MOID`),
  KEY `ObjectSchedulerRUNNABLE0_ndx` (`TIMEVAL`),
  KEY `ObjectSchedulerRUNNABLE1_ndx` (`MOID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ObjectSchedulerRUNNABLE`
--

LOCK TABLES `ObjectSchedulerRUNNABLE` WRITE;
/*!40000 ALTER TABLE `ObjectSchedulerRUNNABLE` DISABLE KEYS */;
INSERT INTO `ObjectSchedulerRUNNABLE` VALUES (2,1478779543005,'IpAddress'),(4,1478779547836,'IpAddress'),(6,1478779552838,'IpAddress'),(8,1478779557931,'IpAddress'),(10,1478779562923,'IpAddress'),(12,1478779578829,'IpAddress'),(14,1478779583824,'IpAddress'),(16,1478779588832,'IpAddress'),(18,1478779582838,'IpAddress'),(20,1478779587900,'IpAddress'),(22,1478779603812,'IpAddress'),(24,1478779597930,'IpAddress'),(26,1478779613836,'IpAddress'),(28,1478779607846,'IpAddress'),(30,1478779612864,'IpAddress'),(32,1478779628831,'IpAddress'),(34,1478779622869,'IpAddress'),(36,1478779627838,'IpAddress'),(38,1478779632958,'IpAddress'),(40,1478779648821,'IpAddress'),(43,1478778459123,'SnmpInterface'),(44,1478778464090,'SnmpInterface'),(46,1478779652962,'IpAddress'),(48,1478779657954,'IpAddress'),(50,1478779662844,'IpAddress'),(52,1478779667840,'IpAddress'),(54,1478779672873,'IpAddress'),(56,1478779677874,'IpAddress'),(58,1478779682876,'IpAddress'),(60,1478779687866,'IpAddress'),(62,1478779703798,'IpAddress'),(64,1478779708823,'IpAddress'),(66,1478779702897,'IpAddress'),(68,1478779707844,'IpAddress'),(70,1478779712846,'IpAddress'),(72,1478779717838,'IpAddress'),(74,1478779733792,'IpAddress'),(76,1478779738842,'IpAddress'),(78,1478779743847,'IpAddress'),(80,1478779748825,'IpAddress'),(82,1478779753823,'IpAddress'),(84,1478779747851,'IpAddress'),(86,1478779752843,'IpAddress'),(88,1478779757934,'IpAddress'),(90,1478779762850,'IpAddress'),(92,1478779778832,'IpAddress'),(94,1478779772846,'IpAddress'),(96,1478779777874,'IpAddress'),(98,1478779782933,'IpAddress'),(100,1478779798829,'IpAddress'),(102,1478779803807,'IpAddress'),(104,1478779797928,'IpAddress'),(106,1478779813850,'IpAddress'),(108,1478779818817,'IpAddress'),(110,1478779812901,'IpAddress'),(112,1478779828836,'IpAddress'),(114,1478779822849,'IpAddress'),(116,1478779827854,'IpAddress'),(118,1478779832877,'IpAddress'),(120,1478779837934,'IpAddress'),(122,1478779853832,'IpAddress'),(124,1478779847846,'IpAddress'),(126,1478779852851,'IpAddress'),(128,1478779868804,'IpAddress'),(130,1478779862853,'IpAddress'),(132,1478779867853,'IpAddress'),(134,1478779883832,'IpAddress'),(136,1478779877937,'IpAddress'),(138,1478779893839,'IpAddress'),(140,1478779898838,'IpAddress'),(142,1478779892882,'IpAddress'),(144,1478779908835,'IpAddress'),(146,1478779902884,'IpAddress'),(148,1478779908028,'IpAddress'),(150,1478779923838,'IpAddress'),(152,1478779917858,'IpAddress'),(154,1478779933834,'IpAddress'),(156,1478779927851,'IpAddress'),(158,1478779943812,'IpAddress'),(160,1478779937934,'IpAddress'),(162,1478779942939,'IpAddress'),(164,1478779948000,'IpAddress'),(166,1478779963837,'IpAddress'),(168,1478779968853,'IpAddress'),(170,1478779973826,'IpAddress'),(172,1478779967877,'IpAddress'),(174,1478779972912,'IpAddress'),(176,1478779977856,'IpAddress'),(178,1478779982947,'IpAddress'),(180,1478779998823,'IpAddress'),(182,1478780003838,'IpAddress'),(184,1478780008846,'IpAddress'),(186,1478780013852,'IpAddress'),(188,1478780018861,'IpAddress'),(190,1478780023826,'IpAddress'),(192,1478780028812,'IpAddress'),(194,1478780022885,'IpAddress'),(195,1478780027814,'ChassisDevice'),(197,1478780032906,'IpAddress'),(198,1478780037809,'ChassisDevice'),(200,1478780042893,'IpAddress'),(201,1478780047805,'ChassisDevice'),(203,1478780063833,'IpAddress'),(204,1478780057809,'ChassisDevice'),(206,1478780073813,'IpAddress'),(207,1478780067806,'ChassisDevice'),(209,1478780083861,'IpAddress'),(210,1478780077799,'ChassisDevice'),(212,1478780093824,'IpAddress'),(213,1478780087806,'ChassisDevice'),(215,1478780093194,'IpAddress'),(216,1478780097805,'ChassisDevice'),(218,1478780102920,'IpAddress'),(219,1478780107805,'ChassisDevice'),(221,1478780112973,'IpAddress'),(222,1478780117808,'ChassisDevice'),(224,1478780122891,'IpAddress'),(225,1478780127810,'ChassisDevice'),(227,1478780132853,'IpAddress'),(228,1478780137807,'ChassisDevice'),(230,1478780142856,'IpAddress'),(231,1478780147804,'ChassisDevice'),(233,1478780163847,'IpAddress'),(234,1478780157810,'ChassisDevice'),(236,1478780162862,'IpAddress'),(237,1478780167818,'ChassisDevice'),(239,1478780183846,'IpAddress'),(240,1478780177816,'ChassisDevice'),(242,1478780182864,'IpAddress'),(243,1478780187806,'ChassisDevice'),(245,1478780192864,'IpAddress'),(246,1478780197818,'ChassisDevice'),(248,1478780202914,'IpAddress'),(249,1478780207810,'ChassisDevice'),(251,1478780212923,'IpAddress'),(252,1478780217810,'ChassisDevice'),(254,1478780222954,'IpAddress'),(255,1478780227818,'ChassisDevice'),(257,1478780232915,'IpAddress'),(258,1478780237814,'ChassisDevice'),(260,1478780253842,'IpAddress'),(261,1478778447803,'ChassisDevice'),(263,1478780263823,'IpAddress'),(264,1478778457808,'ChassisDevice'),(266,1478780262915,'IpAddress'),(267,1478778467810,'ChassisDevice'),(269,1478780272895,'IpAddress'),(270,1478778477800,'ChassisDevice'),(272,1478780282955,'IpAddress'),(273,1478778487809,'ChassisDevice');
/*!40000 ALTER TABLE `ObjectSchedulerRUNNABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ObjectTypes`
--

DROP TABLE IF EXISTS `ObjectTypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ObjectTypes` (
  `OBJKEY` varchar(100) NOT NULL,
  `CLASSNAME` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ObjectTypes`
--

LOCK TABLES `ObjectTypes` WRITE;
/*!40000 ALTER TABLE `ObjectTypes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ObjectTypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OperationsTable`
--

DROP TABLE IF EXISTS `OperationsTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OperationsTable` (
  `USERNAME` varchar(50) NOT NULL,
  `OPERATIONID` bigint(20) NOT NULL,
  `TEMPLATENAME` varchar(50) DEFAULT NULL,
  `SCHEDULEDTIME` varchar(50) DEFAULT NULL,
  `STAGE` varchar(50) DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`USERNAME`,`OPERATIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OperationsTable`
--

LOCK TABLES `OperationsTable` WRITE;
/*!40000 ALTER TABLE `OperationsTable` DISABLE KEYS */;
/*!40000 ALTER TABLE `OperationsTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OperationsTreeTable`
--

DROP TABLE IF EXISTS `OperationsTreeTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OperationsTreeTable` (
  `PARENTOPERATION` varchar(50) NOT NULL,
  `CHILDOPERATION` varchar(50) NOT NULL,
  `LEAFNODE` int(11) DEFAULT NULL,
  PRIMARY KEY (`PARENTOPERATION`,`CHILDOPERATION`),
  KEY `OperationsTreeTable0_ndx` (`PARENTOPERATION`),
  KEY `OperationsTreeTable1_ndx` (`CHILDOPERATION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OperationsTreeTable`
--

LOCK TABLES `OperationsTreeTable` WRITE;
/*!40000 ALTER TABLE `OperationsTreeTable` DISABLE KEYS */;
INSERT INTO `OperationsTreeTable` VALUES ('Administrative Operation','Add Group',0),('Administrative Operation','Add Operation',0),('Administrative Operation','Banner Configuration',0),('Administrative Operation','Clear Discovery',0),('Administrative Operation','Configure Log Levels',0),('Administrative Operation','Create Scope For Group',0),('Administrative Operation','Group Operations',1),('Administrative Operation','Modify Group Scope Relation',0),('Administrative Operation','MTAP Administration',0),('Administrative Operation','Operation Settings',1),('Administrative Operation','Remove Group',0),('Administrative Operation','Remove Operation',0),('Administrative Operation','Resume NMS',0),('Administrative Operation','Runtime Administration',0),('Administrative Operation','Scope Settings',1),('Administrative Operation','Security Administration',0),('Administrative Operation','Services',1),('Administrative Operation','Set Group Permission',0),('Administrative Operation','Shutdown Web NMS Server',0),('Administrative Operation','Start Backup',0),('Administrative Operation','System Administration',0),('Administrative Operation','Terminate Client',0),('Alert Filters','Get Alert Filters',0),('Alert Filters','Set Alert Filters',0),('Alert User Operations','Alert Pickup',0),('Alert User Operations','Clear Alerts',0),('Alert User Operations','Get Alert Annotation',0),('Alert User Operations','Get Alert Details',0),('Alert User Operations','Get Alert History',0),('Alert User Operations','Print Alert View',0),('Alert User Operations','Save Alerts To File',0),('Alert User Operations','Set Alert Annotation',0),('Alerts','Alert Filters',1),('Alerts','Alert Pickup',0),('Alerts','Alert User Operations',1),('Alerts','Clear Alerts',0),('Alerts','Delete Alerts',0),('Alerts','Get Alert Annotation',0),('Alerts','Get Alert Details',0),('Alerts','Get Alert Filters',0),('Alerts','Get Alert History',0),('Alerts','Print Alert View',0),('Alerts','Save Alerts To File',0),('Alerts','Set Alert Annotation',0),('Alerts','Set Alert Filters',0),('Configuration','Create Task',0),('Configuration','Execute Task',0),('Event Filters And Parsers','Get Event Filters',0),('Event Filters And Parsers','Get Event Parsers',0),('Event Filters And Parsers','Set Event Filters',0),('Event Filters And Parsers','Set Event Parsers',0),('Event User Operations','Print Event View',0),('Event User Operations','Save Events To File',0),('Events','Event Filters And Parsers',1),('Events','Event User Operations',1),('Events','Get Event Filters',0),('Events','Get Event Parsers',0),('Events','Print Event View',0),('Events','Save Events To File',0),('Events','Set Event Filters',0),('Events','Set Event Parsers',0),('Group Operations','Add Group',0),('Group Operations','Remove Group',0),('Group Operations','Set Group Permission',0),('Maps','Map Editing Operations',0),('Modify Object','Manage And Unmanage Objects',0),('Modify Object','Start And Stop Discovery',0),('Operation Settings','Add Operation',0),('Operation Settings','Remove Operation',0),('Operation Tree Root','Administrative Operation',1),('Operation Tree Root','Alerts',1),('Operation Tree Root','Configuration',1),('Operation Tree Root','Events',1),('Operation Tree Root','Maps',1),('Operation Tree Root','Policy',1),('Operation Tree Root','Poll Filters',1),('Operation Tree Root','Polling Object',1),('Operation Tree Root','Polling Units',1),('Operation Tree Root','Provisioning',1),('Operation Tree Root','Threshold Object',1),('Operation Tree Root','Topology',1),('Operation Tree Root','Trap Parsers And Filters',1),('Operation Tree Root','User Administration',1),('Policy','Add Policy',0),('Policy','Delete Policy',0),('Policy','Update Policy',0),('Poll Filters','Get Poll Filters',0),('Poll Filters','Reload Poll Filters',0),('Poll Filters','Update Poll Filters',0),('Polling Object','Add Polling Object',0),('Polling Object','Change Polling Object Status',0),('Polling Object','Delete Polling Object',0),('Polling Object','Get Polling Objects',0),('Polling Object','Modify Polling Object',0),('Polling Units','Add Polling Units',0),('Polling Units','Get Polling Unit',0),('Polling Units','Modify Polling Units',0),('Polling Units','Remove Polling Units',0),('Provisioning','View TemplateResult',0),('Scope Settings','Create Scope For Group',0),('Scope Settings','Modify Group Scope Relation',0),('Security Administration','Add Group',0),('Security Administration','Add Operation',0),('Security Administration','Create Scope For Group',0),('Security Administration','Group Operations',1),('Security Administration','Modify Group Scope Relation',0),('Security Administration','Operation Settings',1),('Security Administration','Remove Group',0),('Security Administration','Remove Operation',0),('Security Administration','Scope Settings',1),('Security Administration','Set Group Permission',0),('Services','Clear Discovery',0),('Services','Resume NMS',0),('Services','Start Backup',0),('Threshold Object','Add Threshold Object',0),('Threshold Object','Delete Threshold Object',0),('Threshold Object','Get Threshold Objects',0),('Threshold Object','Modify Threshold Object',0),('Topology','Add Network',0),('Topology','Add Node',0),('Topology','Delete Object',0),('Topology','Manage And Unmanage Objects',0),('Topology','Modify Object',1),('Topology','Refresh Node',0),('Topology','Start And Stop Discovery',0),('Trap Parsers And Filters','Get Trap Filters',0),('Trap Parsers And Filters','Get Trap Parsers',0),('Trap Parsers And Filters','Reload Trap Filters',0),('Trap Parsers And Filters','Set Trap Filters',0),('Trap Parsers And Filters','Set Trap Parsers',0),('User Administration','Add Users',0),('User Administration','Assign User To Group',0),('User Administration','Change Password',0),('User Administration','Change Self Password',0),('User Administration','Clear Audit Trails',0),('User Administration','Get List of Users',0),('User Administration','Remove User From Group',0),('User Administration','Remove Users',0),('User Administration','Set User Permission',0),('User Administration','Set User Profile',0),('User Administration','User Configuration',0);
/*!40000 ALTER TABLE `OperationsTreeTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `POLICYUSERPROPS`
--

DROP TABLE IF EXISTS `POLICYUSERPROPS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `POLICYUSERPROPS` (
  `NAME` varchar(100) NOT NULL,
  `OWNERNAME` varchar(25) NOT NULL,
  `PROPNAME` varchar(150) NOT NULL,
  `PROPVAL` varchar(150) DEFAULT NULL,
  KEY `POLICYUSERPROPS0_ndx` (`NAME`),
  KEY `POLICYUSERPROPS1_ndx` (`OWNERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `POLICYUSERPROPS`
--

LOCK TABLES `POLICYUSERPROPS` WRITE;
/*!40000 ALTER TABLE `POLICYUSERPROPS` DISABLE KEYS */;
INSERT INTO `POLICYUSERPROPS` VALUES ('TableCleanupPolicy1','NULL','Delete data after (days)','7'),('TableCleanupPolicy1','NULL','Table Name','STATSDATA%'),('TableCleanupPolicy1','NULL','Cleanup Hour (0-23)','0');
/*!40000 ALTER TABLE `POLICYUSERPROPS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PORTS`
--

DROP TABLE IF EXISTS `PORTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PORTS` (
  `PORTS` varchar(100) NOT NULL,
  PRIMARY KEY (`PORTS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PORTS`
--

LOCK TABLES `PORTS` WRITE;
/*!40000 ALTER TABLE `PORTS` DISABLE KEYS */;
/*!40000 ALTER TABLE `PORTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PanelTree`
--

DROP TABLE IF EXISTS `PanelTree`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PanelTree` (
  `NODEID` varchar(150) NOT NULL,
  `NODETYPE` varchar(100) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PARENT` varchar(100) DEFAULT NULL,
  `PREVIOUSNODE` varchar(100) DEFAULT NULL,
  `MODULENAME` varchar(30) DEFAULT NULL,
  `PANELPROPS` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`NODEID`,`USERNAME`),
  KEY `PREVIOUSNODE0_ndx` (`PREVIOUSNODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PanelTree`
--

LOCK TABLES `PanelTree` WRITE;
/*!40000 ALTER TABLE `PanelTree` DISABLE KEYS */;
INSERT INTO `PanelTree` VALUES ('172.24.14.0.netmap','MAP','All','Maps','Failed_Objects_Map.netmap','MapDB','{\"TARGET\":\"center\",\"NODE-FORALL\":\"true\",\"ICON-FILE\":\"images/ipn.png\",\"HTML-TABLE\":\"Network Maps\",\"TREE-NAME\":\"172.24.14.0\",\"WEB-TREE-ICON\":\"ip-address.png\",\"MO-NAME\":\"172.24.14.0\",\"PANEL-KEY\":\"MapApplet\",\"URL\":\"/map/MapView.do\",\"BACKGROUND-COLOR\":\"255,255,255\"}'),('172.24.14.0.netmap','MAP','root','Maps','Failed_Objects_Map.netmap','MapDB','{\"TARGET\":\"center\",\"NODE-FORALL\":\"true\",\"ICON-FILE\":\"images/ipn.png\",\"HTML-TABLE\":\"Network Maps\",\"TREE-NAME\":\"172.24.14.0\",\"WEB-TREE-ICON\":\"ip-address.png\",\"MO-NAME\":\"172.24.14.0\",\"PANEL-KEY\":\"MapApplet\",\"URL\":\"/map/MapView.do\",\"BACKGROUND-COLOR\":\"255,255,255\"}'),('172.24.14.0Failed_Objects_Map.netmap','MAP','All','172.24.14.0.netmap','START','MapDB','{\"TARGET\":\"center\",\"NODE-FORALL\":\"true\",\"ICON-FILE\":\"images/burst.png\",\"TREE-NAME\":\"Failed Systems\",\"WEB-TREE-ICON\":\"failed-systems.png\",\"URL\":\"/map/MapView.do\",\"PANEL-KEY\":\"MapApplet\",\"BACKGROUND-COLOR\":\"255,255,255\"}'),('172.24.14.0Failed_Objects_Map.netmap','MAP','root','172.24.14.0.netmap','START','MapDB','{\"TARGET\":\"center\",\"NODE-FORALL\":\"true\",\"ICON-FILE\":\"images/burst.png\",\"TREE-NAME\":\"Failed Systems\",\"WEB-TREE-ICON\":\"failed-systems.png\",\"PANEL-KEY\":\"MapApplet\",\"URL\":\"/map/MapView.do\",\"BACKGROUND-COLOR\":\"255,255,255\"}'),('ActivityList','LEVEL-1','root','Provisioning','Templates','Default','{\"TARGET\":\"center\",\"TABLE-POPUP-MENU\":\"ActivityList\",\"ICON-FILE\":\"images/operationsTreeIcon.png\",\"TREE-NAME\":\"ActivityList\",\"PANEL-NAME\":\"com.adventnet.nms.provisioning.ui.ProvisioningOperationsPanel\",\"Client\":\"Java\",\"TREE-POPUP-MENU\":\"provisioningoperationsmenu.xml\",\"PANEL-KEY\":\"ProvisioningOperationsPanel\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/screen.png\",\"MENU-FILE-NAME\":\"provisioningoperationsmenu.xml\"}'),('addnewnetwork','LEVEL-3','root','networkadmin','newnode','Default','{\"TARGET\":\"center\",\"MODULENAME\":\"AddNewNetwork\",\"ACTION-ON-NO-PRIVILEGE\":\"HIDE\",\"TREE-NAME\":\"webclient.admin.networkadmin.addnetwork.treenode\",\"OPERATION\":\"Add Network\",\"NODETYPE\":\"LEVEL-3\",\"USERNAME\":\"root\",\"Client\":\"HTML\",\"NODEID\":\"addnewnetwork\",\"URL\":\"/topo/addNetwork.do\"}'),('admin','DEVICE','root','WebNMS-Panels','TL1','Default','{\"TARGET\":\"center\",\"INIT-ON-STARTUP\":\"false\",\"TAB\":\"true\",\"TREE-NAME\":\"webclient.common.tree.adminnode.name\",\"DEFAULT-CLOSE-OPERATION\":\"DISPOSE_ON_CLOSE\",\"FRAME-TITLE\":\"Admin\",\"Client\":\"HTML\",\"URL\":\"/admin/CompleteAdminPanel.do\"}'),('admincomplete','LEVEL-1','root','admin','START','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.common.tree.adminnode.completeview\",\"OPERATION\":\"System Administration\",\"URL\":\"/admin/CompleteAdminPanel.do\"}'),('adminoperation','LEVEL-2','root','admincomplete','networkadmin','Default','{\"TARGET\":\"center\",\"ACTION-ON-NO-PRIVILEGE\":\"HIDE\",\"TREE-NAME\":\"webclient.admin.systemadmin.operationtitle\",\"OPERATION\":\"System Administration\",\"Client\":\"HTML\",\"URL\":\"/admin/SysAdmin.do\"}'),('AdminTool','DEVICE','root','WebNMS-Panels','Network Database','Default','{\"TARGET\":\"center\",\"INIT-ON-STARTUP\":\"false\",\"ICON-FILE\":\"images/admintooltreeicon.png\",\"TREE-NAME\":\"Administration Tools\",\"IMAGE-NAME\":\"images/admintoolsmain.gif\",\"DEFAULT-CLOSE-OPERATION\":\"DISPOSE_ON_CLOSE\",\"PANEL-NAME\":\"com.adventnet.nms.util.ImagePanel\",\"Client\":\"All\",\"PANEL-KEY\":\"AdminTool\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/admintoolsmain.gif\"}'),('AdventNet','DEVICE-DATA','root','null','START','Default','{\"TARGET\":\"center\",\"ICON-FILE\":\"./images/cdot.jpg\",\"Client\":\"All\",\"TREE-NAME\":\"C-DOT IEMS\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/screen.png\"}'),('Alerts','LEVEL-1','root','Fault','Events','Alerts','{\"TARGET\":\"center\",\"TABLE-POPUP-MENU\":\"View\",\"ICON-FILE\":\"images/alarm.png\",\"TREE-NAME\":\"Alarms\",\"Client\":\"All\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\",\"PANEL-KEY\":\"AlertApplet\",\"URL\":\"/fault/AlarmView.do\",\"MENU-FILE-NAME\":\"alertsmenu.xml\"}'),('Alerts.1042110065','LEVEL-2','root','Alerts','START','Alerts','{\"TARGET\":\"center\",\"TABLE-POPUP-MENU\":\"View\",\"ICON-FILE\":\"images/alarm.png\",\"TREE-NAME\":\"Alarms0s\",\"FREEZE_UPDATE_OPTION\":\"true\",\"NODE-INDEX\":\"\",\"DEVICE-REF\":\"Alerts\",\"URL\":\"/fault/AlarmView.do\",\"PANEL-KEY\":\"Alerts.1042110065\",\"MENU-FILE-NAME\":\"alertsmenu.xml\",\"MODULE-NAME\":\"Alerts\",\"PANEL-NAME\":\"com.adventnet.nms.alertui.AlertApplet\",\"VIEW-LENGTH\":\"25\",\"ALERT-DETAILS-FORM\":\"\",\"SHOW-TREENODE-PROPERTIES\":\"true\",\"Client\":\"All\",\"FRAME-TITLE\":\"\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\"}'),('backup','LEVEL-3','root','adminoperation','shutdown','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.services.backup.treename\",\"URL\":\"/admin/Backup.do\"}'),('backupresync','LEVEL-3','root','adminoperation','backup','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.resyncne.withbackup.treename\",\"URL\":\"/admin/ReSyncNEWithBackup.do\"}'),('Batch Configuration','DEVICE','root','Configuration','START','Default','{\"TARGET\":\"center\",\"TABLE-POPUP-MENU\":\"View\",\"ICON-FILE\":\"images/batchconfigtreeicon.png\",\"TREE-NAME\":\"Batch Configuration\",\"PANEL-NAME\":\"com.adventnet.nms.config.ConfigPanel\",\"Client\":\"Java\",\"TREE-POPUP-MENU\":\"configmenu.xml\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/screen.png\",\"MENU-FILE-NAME\":\"configmenu.xml\"}'),('bedetail','LEVEL-3','root','serverdetails','START','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.serverstatus.serverdetails\",\"URL\":\"/admin/AdminServerStatus.do?toPerform=serverDetails\"}'),('clientdetail','LEVEL-3','root','serverdetails','fedetail','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.serverstatus.clientdetails\",\"URL\":\"/admin/AdminServerStatus.do?toPerform=clientDetails\"}'),('ConfigAudit','DEVICE','root','Configuration','Batch Configuration','Audit','{\"PARENT-ID\":\"Configuration\",\"TARGET\":\"center\",\"TABLE-POPUP-MENU\":\"View\",\"ICON-FILE\":\"images/audit.png\",\"TREE-NAME\":\"Audit\",\"PANEL-NAME\":\"com.adventnet.nms.config.ConfigAuditBrowser\",\"VIEW-LENGTH\":\"20\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\",\"PANEL-KEY\":\"ConfigAuditBrowser\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/screen.png\",\"MENU-FILE-NAME\":\"configauditmenu.xml\"}'),('Configuration','DEVICE','root','WebNMS-Panels','Fault','Default','{\"TARGET\":\"center\",\"INIT-ON-STARTUP\":\"false\",\"ICON-FILE\":\"images/configmgmttreeicon.png\",\"TREE-NAME\":\"Configuration\",\"IMAGE-NAME\":\"images/configmain.gif\",\"DEFAULT-CLOSE-OPERATION\":\"DISPOSE_ON_CLOSE\",\"PANEL-NAME\":\"com.adventnet.nms.util.ImagePanel\",\"FRAME-TITLE\":\"Configuration Management\",\"Client\":\"Java\",\"PANEL-KEY\":\"Configuration\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/configmain.gif\"}'),('disccriteriaconfig','LEVEL-3','root','runtimeadmin','discparameterconfig','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.runtimeadmin.disccriteria.link\",\"URL\":\"/admin/CriteriaBasedDiscovery.do\"}'),('discparameterconfig','LEVEL-3','root','runtimeadmin','START','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.runtimeadmin.discpara.link\",\"URL\":\"/admin/ConfigDiscParam.do\"}'),('discstatus','LEVEL-3','root','moduledetails','START','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.modulestatus.disc.title\",\"URL\":\"/admin/DiscModuleStatus.do\"}'),('Events','LEVEL-1','root','Fault','START','Events','{\"TARGET\":\"center\",\"TABLE-POPUP-MENU\":\"View\",\"ICON-FILE\":\"images/event.png\",\"TREE-NAME\":\"Network Events\",\"Client\":\"All\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\",\"PANEL-KEY\":\"EventBrowser\",\"URL\":\"/fault/NetworkEvent.do\",\"MENU-FILE-NAME\":\"eventsmenu.xml\"}'),('ExampleLayout.netmap','LEVEL-1','root','Maps','172.24.14.0.netmap','Default','{\"TARGET\":\"center\",\"ICON-FILE\":\"webclient/common/images/treedot.gif\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.map.example.layout\",\"URL\":\"/map/ExampleLayout.do?layout=ring\"}'),('Failed_Objects_Map.netmap','MAP','All','Maps','ipnet.netmap','MapDB','{\"TARGET\":\"center\",\"NODE-FORALL\":\"true\",\"ICON-FILE\":\"images/burst.png\",\"TREE-NAME\":\"Failed Systems\",\"WEB-TREE-ICON\":\"failed-systems.png\",\"URL\":\"/map/MapView.do\",\"PANEL-KEY\":\"MapApplet\",\"BACKGROUND-COLOR\":\"255,255,255\"}'),('Failed_Objects_Map.netmap','MAP','root','Maps','ipnet.netmap','MapDB','{\"TARGET\":\"center\",\"NODE-FORALL\":\"true\",\"ICON-FILE\":\"images/burst.png\",\"TREE-NAME\":\"Failed Systems\",\"WEB-TREE-ICON\":\"failed-systems.png\",\"PANEL-KEY\":\"MapApplet\",\"URL\":\"/map/MapView.do\",\"BACKGROUND-COLOR\":\"255,255,255\"}'),('Fault','DEVICE','root','WebNMS-Panels','Maps','Default','{\"TARGET\":\"center\",\"INIT-ON-STARTUP\":\"false\",\"ICON-FILE\":\"images/faulttreeicon.png\",\"TAB\":\"true\",\"TREE-NAME\":\"Fault Management\",\"IMAGE-NAME\":\"images/faultmain.gif\",\"DEFAULT-CLOSE-OPERATION\":\"DISPOSE_ON_CLOSE\",\"PANEL-NAME\":\"com.adventnet.nms.util.ImagePanel\",\"Client\":\"All\",\"PANEL-KEY\":\"Fault\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/faultmain.gif\"}'),('faultstatus','LEVEL-3','root','moduledetails','discstatus','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.modulestatus.fault.title\",\"URL\":\"/admin/FaultModuleStatus.do\"}'),('fedetail','LEVEL-3','root','serverdetails','portdetail','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.serverstatus.fedetails\",\"URL\":\"/admin/AdminServerStatus.do?toPerform=feDetails\"}'),('ipnet.netmap','MAP','All','Maps','START','MapDB','{\"TARGET\":\"center\",\"NODE-FORALL\":\"true\",\"ICON-FILE\":\"images/ipnet_small.png\",\"HTML-TABLE\":\"Infrastructure View\",\"TREE-NAME\":\"ipnet\",\"WEB-TREE-ICON\":\"ipnet.png\",\"URL\":\"/map/MapView.do\",\"PANEL-KEY\":\"MapApplet\"}'),('ipnet.netmap','MAP','root','Maps','START','MapDB','{\"TARGET\":\"center\",\"NODE-FORALL\":\"true\",\"ICON-FILE\":\"images/ipnet_small.png\",\"HTML-TABLE\":\"Infrastructure View\",\"TREE-NAME\":\"ipnet\",\"WEB-TREE-ICON\":\"ipnet.png\",\"PANEL-KEY\":\"MapApplet\",\"URL\":\"/map/MapView.do\"}'),('logconfig','LEVEL-3','root','runtimeadmin','trapconfig','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.runtimeadmin.logging.link\",\"URL\":\"/admin/loggingAction.do\"}'),('Maps','DEVICE','root','WebNMS-Panels','START','Default','{\"TARGET\":\"center\",\"ICON-FILE\":\"images/maps.png\",\"TAB\":\"true\",\"TREE-NAME\":\"Network Maps\",\"Client\":\"All\",\"PANEL-KEY\":\"MapApplet\",\"URL\":\"/map/MapView.do\",\"MENU-FILE-NAME\":\"mapmenu.xml\"}'),('Mib Manager','LEVEL-1','root','Snmp','START','Default','{\"TARGET\":\"center\",\"ICON-FILE\":\"images/origin.png\",\"TREE-NAME\":\"Mib Manager\",\"PANEL-NAME\":\"com.adventnet.mibBrowser.MibBrowser\",\"Client\":\"All\",\"URL\":\"/MibBrowser.do\",\"MENU-FILE-NAME\":\"mibmenu.xml\"}'),('modifyprofile','LEVEL-3','root','useradmin','newuser','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.adminpanel.modifyuserprofile\",\"OPERATION\":\"Change Password\",\"URL\":\"/admin/InvokeUserToModify.do\"}'),('moduledetails','LEVEL-2','root','admincomplete','serverdetails','Default','{\"TARGET\":\"center\",\"ACTION-ON-NO-PRIVILEGE\":\"HIDE\",\"TREE-NAME\":\"webclient.admin.systemadmin.modulestatus.title\",\"OPERATION\":\"System Administration\",\"Client\":\"HTML\",\"URL\":\"/admin/ModuleDetails.do\"}'),('Network Database','DEVICE','root','WebNMS-Panels','Performance','Network Database','{\"TARGET\":\"center\",\"ICON-FILE\":\"images/ntwkdbtreeicon.png\",\"TAB\":\"true\",\"TREE-NAME\":\"Network Database\",\"Client\":\"All\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\",\"PANEL-KEY\":\"NmsListView\",\"URL\":\"/topo/NetworkInventory.do\",\"MENU-FILE-NAME\":\"dbmenu.xml\"}'),('Network Database.CompleteView','LEVEL-1','root','Network Database','START','Network Database','{\"TARGET\":\"center\",\"MODULENAME\":\"Network Database\",\"TABLE-POPUP-MENU\":\"View\",\"ICON-FILE\":\"/webclient/topo/images/completeview.gif\",\"TREE-NAME\":\"webclient.topo.tree.completeview\",\"NODE-INDEX\":\"0\",\"DEVICE-REF\":\"Network Database\",\"URL\":\"/topo/NetworkInventory.do\",\"NODEID\":\"Network Database.CompleteView\",\"PANEL-KEY\":\"NmsListView\",\"MENU-FILE-NAME\":\"dbmenu.xml\",\"VIEW-LENGTH\":\"25\",\"USERNAME\":\"root\",\"NODETYPE\":\"LEVEL-1\",\"SHOW-TREENODE-PROPERTIES\":\"true\",\"Client\":\"HTML\",\"FRAME-TITLE\":\"\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\"}'),('Network Database.Interfaces','LEVEL-1','root','Network Database','Network Database.Nodes','Network Database','{\"TARGET\":\"center\",\"ICON-FILE\":\"images/redDot.png\",\"TREE-NAME\":\"Interfaces\",\"VIEW-LENGTH\":\"25\",\"DEVICE-REF\":\"Network Database\",\"Client\":\"All\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\",\"PANEL-KEY\":\"NmsListView\",\"URL\":\"/topo/NetworkInventory.do\",\"MENU-FILE-NAME\":\"dbmenu.xml\"}'),('Network Database.Networks','LEVEL-1','root','Network Database','Network Database.CompleteView','Network Database','{\"TARGET\":\"center\",\"ICON-FILE\":\"images/networktreeicon.png\",\"TREE-NAME\":\"Networks\",\"VIEW-LENGTH\":\"25\",\"DEVICE-REF\":\"Network Database\",\"Client\":\"All\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\",\"PANEL-KEY\":\"NmsListView\",\"URL\":\"/topo/NetworkInventory.do\",\"MENU-FILE-NAME\":\"dbmenu.xml\"}'),('Network Database.Nodes','LEVEL-1','root','Network Database','Network Database.Networks','Network Database','{\"TARGET\":\"center\",\"ICON-FILE\":\"images/nodetreeicon.png\",\"TREE-NAME\":\"Nodes\",\"VIEW-LENGTH\":\"25\",\"DEVICE-REF\":\"Network Database\",\"Client\":\"All\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\",\"PANEL-KEY\":\"NmsListView\",\"URL\":\"/topo/NetworkInventory.do\",\"MENU-FILE-NAME\":\"dbmenu.xml\"}'),('Network Database.Routers','LEVEL-1','root','Network Database','Network Database.Switches','Network Database','{\"TARGET\":\"center\",\"TABLE-POPUP-MENU\":\"View\",\"ICON-FILE\":\"images/routermap.png\",\"TREE-NAME\":\"Routers\",\"VIEW-LENGTH\":\"25\",\"DEVICE-REF\":\"Network Database\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\",\"CLIENT\":\"All\",\"URL\":\"/topo/NetworkInventory.do\",\"PANEL-KEY\":\"NmsListView\",\"MENU-FILE-NAME\":\"dbmenu.xml\"}'),('Network Database.Switches','LEVEL-1','root','Network Database','Network Database.Interfaces','Network Database','{\"TARGET\":\"center\",\"TABLE-POPUP-MENU\":\"View\",\"ICON-FILE\":\"images/switch1.png\",\"TREE-NAME\":\"Switches\",\"VIEW-LENGTH\":\"25\",\"DEVICE-REF\":\"Network Database\",\"Client\":\"All\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\",\"URL\":\"/topo/NetworkInventory.do\",\"PANEL-KEY\":\"NmsListView\",\"MENU-FILE-NAME\":\"dbmenu.xml\"}'),('networkadmin','LEVEL-2','root','admincomplete','useradmin','Default','{\"TARGET\":\"center\",\"MODULENAME\":\"NetworkAdmin\",\"ACTION-ON-NO-PRIVILEGE\":\"HIDE\",\"TREE-NAME\":\"webclient.admin.networkadmin.treenode\",\"OPERATION\":\"Topology\",\"NODETYPE\":\"LEVEL-2\",\"USERNAME\":\"root\",\"Client\":\"HTML\",\"NODEID\":\"networkadmin\",\"URL\":\"/topo/NetworkAdminPanel.do\"}'),('newnode','LEVEL-3','root','networkadmin','START','Default','{\"TARGET\":\"center\",\"MODULENAME\":\"AddNewNode\",\"ACTION-ON-NO-PRIVILEGE\":\"HIDE\",\"TREE-NAME\":\"webclient.admin.networkadmin.addnode.treenode\",\"OPERATION\":\"Add Node\",\"NODETYPE\":\"LEVEL-3\",\"USERNAME\":\"root\",\"Client\":\"HTML\",\"NODEID\":\"newnode\",\"URL\":\"/topo/addNode.do\"}'),('newuser','LEVEL-3','root','useradmin','START','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.adminpanel.adduser\",\"OPERATION\":\"Add Users\",\"URL\":\"/admin/InvokeAddUserForm.do\"}'),('Performance','DEVICE','root','WebNMS-Panels','Provisioning','Default','{\"TARGET\":\"center\",\"INIT-ON-STARTUP\":\"false\",\"ICON-FILE\":\"images/perfmgmttreeicon.png\",\"TAB\":\"true\",\"TREE-NAME\":\"Performance\",\"IMAGE-NAME\":\"images/perfmgmtmain.gif\",\"DEFAULT-CLOSE-OPERATION\":\"DISPOSE_ON_CLOSE\",\"PANEL-NAME\":\"com.adventnet.nms.util.ImagePanel\",\"Client\":\"All\",\"PANEL-KEY\":\"Performance\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/perfmgmtmain.gif\"}'),('perfstatus','LEVEL-3','root','moduledetails','faultstatus','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.modulestatus.perf.title\",\"URL\":\"/admin/PerformanceModule.do\"}'),('Policy','LEVEL-1','root','AdminTool','START','Policy','{\"TARGET\":\"center\",\"ICON-FILE\":\"images/policy.png\",\"TABLE-COLUMNS\":\"Name=name=200;Groupname=groupName=170;Status=status=100;Period=period=80;\",\"TREE-NAME\":\"Policies\",\"PANEL-NAME\":\"com.adventnet.nms.policyui.NmsPolicyPanel\",\"Client\":\"All\",\"URL\":\"jsp/GetPolicy.jsp\",\"MENU-FILE-NAME\":\"policymenu.xml\"}'),('portdetail','LEVEL-3','root','serverdetails','schedulerdetail','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.serverstatus.portdetails\",\"URL\":\"/admin/AdminServerStatus.do?toPerform=portDetails\"}'),('Provisioning','DEVICE','root','WebNMS-Panels','Configuration','Default','{\"TARGET\":\"center\",\"INIT-ON-STARTUP\":\"false\",\"ICON-FILE\":\"images/provisioningTreeIcon.png\",\"TREE-NAME\":\"Provisioning\",\"IMAGE-NAME\":\"images/provisioningMain.gif\",\"DEFAULT-CLOSE-OPERATION\":\"DISPOSE_ON_CLOSE\",\"PANEL-NAME\":\"com.adventnet.nms.util.ImagePanel\",\"FRAME-TITLE\":\"Provisioning Framework\",\"Client\":\"Java\",\"PANEL-KEY\":\"Provisioning\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/provisioningMain.gif\"}'),('refreshnode','LEVEL-3','root','networkadmin','addnewnetwork','Default','{\"TARGET\":\"center\",\"MODULENAME\":\"RefreshNode\",\"ACTION-ON-NO-PRIVILEGE\":\"HIDE\",\"TREE-NAME\":\"webclient.admin.networkadmin.refreshnode.treenode\",\"OPERATION\":\"Refresh Node\",\"NODETYPE\":\"LEVEL-3\",\"USERNAME\":\"root\",\"Client\":\"HTML\",\"NODEID\":\"refreshnode\",\"URL\":\"/topo/refreshNode.do\"}'),('removeuser','LEVEL-3','root','useradmin','modifyprofile','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.adminpanel.removeuser\",\"OPERATION\":\"Remove Users\",\"URL\":\"/admin/InvokeRemoveUserForm.do\"}'),('resync','LEVEL-3','root','adminoperation','backupresync','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.resyncne.withoutbackup\",\"URL\":\"/admin/ReSyncNE.do\"}'),('runtimeadmin','LEVEL-2','root','admincomplete','adminoperation','Default','{\"TARGET\":\"center\",\"ACTION-ON-NO-PRIVILEGE\":\"HIDE\",\"TREE-NAME\":\"webclient.admin.runtime.title\",\"OPERATION\":\"Runtime Administration\",\"Client\":\"HTML\",\"URL\":\"/admin/RuntimeAdmin.do\"}'),('schedulerdetail','LEVEL-3','root','serverdetails','bedetail','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.serverstatus.schedulerdetails\",\"URL\":\"/admin/AdminServerStatus.do?toPerform=schedulerDetails\"}'),('serverdetails','LEVEL-2','root','admincomplete','runtimeadmin','Default','{\"TARGET\":\"center\",\"ACTION-ON-NO-PRIVILEGE\":\"HIDE\",\"TREE-NAME\":\"webclient.admin.systemadmin.serverstatus.details\",\"OPERATION\":\"System Administration\",\"Client\":\"HTML\",\"URL\":\"/admin/ServerDetails.do\"}'),('serverlogs','LEVEL-3','root','serverdetails','clientdetail','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.serverlogs.title\",\"URL\":\"/admin/ShowServerLogs.do?directory=logs\"}'),('shutdown','LEVEL-3','root','adminoperation','START','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.shutdown.treename\",\"URL\":\"/admin/ShutDown.do\"}'),('Snmp','DEVICE','root','WebNMS-Panels','AdminTool','Default','{\"TARGET\":\"center\",\"INIT-ON-STARTUP\":\"false\",\"ICON-FILE\":\"images/snmptoolstreeicon.png\",\"TAB\":\"true\",\"TREE-NAME\":\"SNMP Tools\",\"IMAGE-NAME\":\"images/snmptoolsmain.gif\",\"DEFAULT-CLOSE-OPERATION\":\"DISPOSE_ON_CLOSE\",\"PANEL-NAME\":\"com.adventnet.nms.util.ImagePanel\",\"Client\":\"All\",\"PANEL-KEY\":\"Snmp\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/snmptoolsmain.gif\"}'),('Stats Admin','LEVEL-1','root','Performance','START','Stats Admin','{\"TARGET\":\"center\",\"TABLE-POPUP-MENU\":\"View\",\"ICON-FILE\":\"images/configcollecttreeicon.png\",\"TREE-NAME\":\"Configured Collection\",\"Client\":\"Java\",\"TREE-POPUP-MENU\":\"Custom Views,frameoptions.xml,TreeOperations.xml\",\"PANEL-KEY\":\"StatsAdminPanel\",\"URL\":\"jsp/Report.jsp\",\"MENU-FILE-NAME\":\"pollmenu.xml\"}'),('System Administration','LEVEL-1','root','AdminTool','Policy','Default','{\"TARGET\":\"center\",\"ICON-FILE\":\"images/sys.png\",\"Client\":\"HTML\",\"TREE-NAME\":\"System Administration\",\"URL\":\"jsp/SysAdminFrontPage.jsp\"}'),('Templates','LEVEL-1','root','Provisioning','START','Default','{\"TARGET\":\"center\",\"TABLE-POPUP-MENU\":\"Templates\",\"ICON-FILE\":\"images/templatesTreeIcon.png\",\"TREE-NAME\":\"Templates\",\"PANEL-NAME\":\"com.adventnet.nms.provisioning.ui.ProvisioningTemplatesPanel\",\"Client\":\"Java\",\"TREE-POPUP-MENU\":\"provisioningmenu.xml\",\"PANEL-KEY\":\"ProvisioningTemplatesPanel\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/screen.png\",\"MENU-FILE-NAME\":\"provisioningmenu.xml\"}'),('TL1','DEVICE','root','WebNMS-Panels','Snmp','Default','{\"TARGET\":\"center\",\"INIT-ON-STARTUP\":\"false\",\"ICON-FILE\":\"images/tl1treeicon.png\",\"TREE-NAME\":\"TL1 Tools\",\"IMAGE-NAME\":\"images/tl1toolsmain.gif\",\"DEFAULT-CLOSE-OPERATION\":\"DISPOSE_ON_CLOSE\",\"PANEL-NAME\":\"com.adventnet.nms.util.ImagePanel\",\"Client\":\"Java\",\"PANEL-KEY\":\"TL1\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/tl1toolsmain.gif\"}'),('TL1 Browser','LEVEL-1','root','TL1','START','Default','{\"TARGET\":\"center\",\"INIT-ON-STARTUP\":\"false\",\"ICON-FILE\":\"images/webcrafttreeicon.png\",\"TREE-NAME\":\"Web Craft Interface\",\"DEFAULT-CLOSE-OPERATION\":\"DISPOSE_ON_CLOSE\",\"PANEL-NAME\":\"com.adventnet.nms.tl1.TL1NMSBrowser\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/screen.png\",\"CLIENT\":\"Java\",\"MENU-FILE-NAME\":\"tl1browsermenu.xml\"}'),('trapconfig','LEVEL-3','root','runtimeadmin','disccriteriaconfig','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.runtimeadmin.trapparser.link\",\"URL\":\"/admin/TrapParser.do\"}'),('useradmin','LEVEL-2','root','admincomplete','START','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.adminpanel.useradmin\",\"OPERATION\":\"User Administration\",\"URL\":\"/admin/UserAdminPanel.do\"}'),('userlist','LEVEL-3','root','useradmin','removeuser','Default','{\"TARGET\":\"center\",\"Client\":\"HTML\",\"TREE-NAME\":\"webclient.admin.systemadmin.useradmin.title\",\"OPERATION\":\"Get List of Users\",\"URL\":\"/admin/UserAdmin.do\"}'),('USM Table','LEVEL-1','root','Snmp','Mib Manager','Default','{\"TARGET\":\"center\",\"INIT-ON-STARTUP\":\"false\",\"ICON-FILE\":\"images/usmtable.png\",\"TREE-NAME\":\"SNMP V3 Security\",\"PANEL-NAME\":\"com.cdot.iems.utils.USMTable\",\"Client\":\"Java\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/screen.png\"}'),('WebNMS-Panels','DEVICE-GROUP','root','AdventNet','START','Default','{\"TARGET\":\"center\",\"ICON-FILE\":\"images/panel.png\",\"Client\":\"All\",\"TREE-NAME\":\"Applications\",\"URL\":\"jsp/ShowImage.jsp?imageName=../images/screen.png\"}');
/*!40000 ALTER TABLE `PanelTree` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PendingDevices`
--

DROP TABLE IF EXISTS `PendingDevices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PendingDevices` (
  `DKEY` varchar(200) DEFAULT NULL,
  `NAME` varchar(200) DEFAULT NULL,
  `VALUE` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PendingDevices`
--

LOCK TABLES `PendingDevices` WRITE;
/*!40000 ALTER TABLE `PendingDevices` DISABLE KEYS */;
/*!40000 ALTER TABLE `PendingDevices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PendingTasks`
--

DROP TABLE IF EXISTS `PendingTasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PendingTasks` (
  `USERNAME` varchar(50) DEFAULT NULL,
  `TASKNAME` varchar(50) DEFAULT NULL,
  `SUBTASKNAME` varchar(50) DEFAULT NULL,
  `DEVICENAME` varchar(50) DEFAULT NULL,
  `STATUS` varchar(20) DEFAULT NULL,
  `PORT` varchar(5) DEFAULT NULL,
  `RETRIES` int(11) DEFAULT NULL,
  `TIMEOUT` int(11) DEFAULT NULL,
  `AINDEX` int(11) DEFAULT NULL,
  `EXECUTIONID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PendingTasks`
--

LOCK TABLES `PendingTasks` WRITE;
/*!40000 ALTER TABLE `PendingTasks` DISABLE KEYS */;
/*!40000 ALTER TABLE `PendingTasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PolicyActionCondition`
--

DROP TABLE IF EXISTS `PolicyActionCondition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PolicyActionCondition` (
  `NAME` varchar(100) NOT NULL,
  `OBJKEY` varchar(100) NOT NULL,
  `POLICYACTION` varchar(100) DEFAULT NULL,
  `POLICYCONDITION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`NAME`,`OBJKEY`),
  KEY `PolicyActionCondition0_ndx` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PolicyActionCondition`
--

LOCK TABLES `PolicyActionCondition` WRITE;
/*!40000 ALTER TABLE `PolicyActionCondition` DISABLE KEYS */;
/*!40000 ALTER TABLE `PolicyActionCondition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PolicyObject`
--

DROP TABLE IF EXISTS `PolicyObject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PolicyObject` (
  `NAME` varchar(100) NOT NULL,
  `GROUPNAME` varchar(100) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `PERIOD` int(11) DEFAULT NULL,
  `POLICYOBJECTCUSTOMIZER` varchar(50) DEFAULT NULL,
  `HELPURL` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`NAME`),
  KEY `PolicyObject0_ndx` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PolicyObject`
--

LOCK TABLES `PolicyObject` WRITE;
/*!40000 ALTER TABLE `PolicyObject` DISABLE KEYS */;
INSERT INTO `PolicyObject` VALUES ('TableCleanupPolicy1','statspolicies',33,3600,'','NULL');
/*!40000 ALTER TABLE `PolicyObject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PolicyScheduleTime`
--

DROP TABLE IF EXISTS `PolicyScheduleTime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PolicyScheduleTime` (
  `TIMEKEY` varchar(250) NOT NULL,
  `NAME` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PolicyScheduleTime`
--

LOCK TABLES `PolicyScheduleTime` WRITE;
/*!40000 ALTER TABLE `PolicyScheduleTime` DISABLE KEYS */;
/*!40000 ALTER TABLE `PolicyScheduleTime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PollIDToKeyMap`
--

DROP TABLE IF EXISTS `PollIDToKeyMap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PollIDToKeyMap` (
  `NAME` varchar(50) DEFAULT NULL,
  `ID` bigint(20) DEFAULT NULL,
  `AGENT` varchar(50) DEFAULT NULL,
  `OID` varchar(200) DEFAULT NULL,
  `STATSDATATABLENAME` varchar(100) DEFAULT NULL,
  `OWNERNAME` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PollIDToKeyMap`
--

LOCK TABLES `PollIDToKeyMap` WRITE;
/*!40000 ALTER TABLE `PollIDToKeyMap` DISABLE KEYS */;
/*!40000 ALTER TABLE `PollIDToKeyMap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PolledData`
--

DROP TABLE IF EXISTS `PolledData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PolledData` (
  `ID` bigint(20) NOT NULL,
  `DISCRIMINATOR` varchar(30) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `AGENT` varchar(50) NOT NULL,
  `OID` varchar(200) NOT NULL,
  `COMMUNITY` varchar(100) NOT NULL,
  `PERIOD` int(11) NOT NULL,
  `ACTIVE` bit(1) DEFAULT NULL,
  `LOGDIRECTLY` bit(1) DEFAULT NULL,
  `LOGFILE` varchar(100) DEFAULT NULL,
  `SAVEDATA` bit(1) DEFAULT NULL,
  `THRESHOLD` bit(1) DEFAULT NULL,
  `ISMULTIPLEPOLLEDDATA` bit(1) DEFAULT NULL,
  `PREVIOUSSEVERITY` int(11) DEFAULT NULL,
  `NUMERICTYPE` int(11) DEFAULT NULL,
  `SAVEABSOLUTES` bit(1) DEFAULT NULL,
  `TIMEAVG` bit(1) DEFAULT NULL,
  `PORT` int(11) DEFAULT NULL,
  `WEBNMS` varchar(100) DEFAULT NULL,
  `GROUPNAME` varchar(100) DEFAULT NULL,
  `LASTCOUNTERVALUE` bigint(20) DEFAULT NULL,
  `LASTTIMEVALUE` bigint(20) DEFAULT NULL,
  `TIMEVAL` bigint(20) NOT NULL,
  `POLICYNAME` varchar(100) DEFAULT NULL,
  `THRESHOLDLIST` varchar(100) DEFAULT NULL,
  `DNSNAME` varchar(100) DEFAULT NULL,
  `SUFFIX` varchar(20) DEFAULT NULL,
  `STATSDATATABLENAME` varchar(100) DEFAULT NULL,
  `POLLERNAME` varchar(200) DEFAULT NULL,
  `FAILURECOUNT` int(11) DEFAULT NULL,
  `FAILURETHRESHOLD` int(11) DEFAULT NULL,
  `PARENTOBJ` varchar(100) DEFAULT NULL,
  `PROTOCOL` varchar(50) DEFAULT NULL,
  `SAVEPOLLCOUNT` int(11) DEFAULT NULL,
  `CURRENTSAVECOUNT` int(11) DEFAULT NULL,
  `SAVEONTHRESHOLD` bit(1) DEFAULT NULL,
  `SNMPVERSION` varchar(10) DEFAULT NULL,
  `USERNAME` varchar(30) DEFAULT NULL,
  `CONTEXTNAME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME` (`NAME`,`AGENT`,`OID`),
  KEY `PARENTOBJ_ndx` (`PARENTOBJ`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PolledData`
--

LOCK TABLES `PolledData` WRITE;
/*!40000 ALTER TABLE `PolledData` DISABLE KEYS */;
INSERT INTO `PolledData` VALUES (1,'KPIPolledData','Network Availability','KeyPerformanceIndicator','Network Availability %','public',300,'','\0',NULL,'','','\0',1,3,'\0','\0',161,'','default',0,1478778407777,1478778707666,'','Network-Avl-Crit,Network-Avl-Major','KeyPerformanceIndicator','','KPI_STATSDATA%','',0,1,'','KPI',1,0,'\0','v1',NULL,NULL),(2,'KPIPolledData','Network Pkt Errors','KeyPerformanceIndicator','Network Pkt Errors %','public',300,'','\0',NULL,'','','\0',5,3,'\0','\0',161,'','default',0,0,1478778707666,'','Network-Loss','KeyPerformanceIndicator','','KPI_STATSDATA%','',0,1,'','KPI',1,0,'\0','v1',NULL,NULL),(3,'KPIPolledData','Network Pkt Discards','KeyPerformanceIndicator','Network Pkt Discards %','public',300,'','\0',NULL,'','','\0',5,3,'\0','\0',161,'','default',0,0,1478778707666,'','Network-Loss','KeyPerformanceIndicator','','KPI_STATSDATA%','',0,1,'','KPI',1,0,'\0','v1',NULL,NULL),(4,'KPIPolledData','Network Packet Loss','KeyPerformanceIndicator','Network Packet Loss packets','public',300,'','\0',NULL,'','\0','\0',5,3,'\0','\0',161,'','default',0,1478266775646,1478778707666,'',NULL,'KeyPerformanceIndicator','','KPI_STATSDATA%','',0,1,'','KPI',1,0,'\0','v1',NULL,NULL),(5,'KPIPolledData','Average Fault Resolution Time','KeyPerformanceIndicator','Average Fault Resolution Time hours','public',300,'','\0',NULL,'','','\0',1,3,'\0','\0',161,'','default',0,1478778407788,1478778707666,'','Fault-Res-Crit,Fault-Res-Major','KeyPerformanceIndicator','','KPI_STATSDATA%','',0,1,'','KPI',1,0,'\0','v1',NULL,NULL),(6,'KPIPolledData','Fault Resolution Time Variance','KeyPerformanceIndicator','Fault Resolution Time Variance hours','public',300,'','\0',NULL,'','\0','\0',5,3,'\0','\0',161,'','default',0,1478778407788,1478778707666,'',NULL,'KeyPerformanceIndicator','','KPI_STATSDATA%','',0,1,'','KPI',1,0,'\0','v1',NULL,NULL),(7,'PolledData','JVMPD_BE_16500_MonitorThread','BE_172.24.14.34','.1.3.6.1.4.1.42.2.145.3.163.1.1.3.1.0','public',30,'','\0',NULL,'','','\0',1,1,'\0','\0',16500,'','default',124,1478778427003,1478778471995,'','bethreadcount','172.24.14.34','','NMS_STATUS_MONITOR%','',0,2,'','SNMP',1,0,'\0','v2',NULL,NULL),(8,'PolledData','JVMPD_BE_16500_MonitorMemory','BE_172.24.14.34','.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0','public',30,'','\0',NULL,'','','\0',1,1,'','\0',16500,'','default',156755680,1478778427003,1478778456999,'','resourceusage','172.24.14.34','','NMS_STATUS_MONITOR%','',0,2,'','SNMP',1,0,'\0','v2',NULL,NULL),(9,'PolledData','INTERFACE_out_octets','rejoe-0162.csez.zohocorpin.com','2.2.1.16.8','public',600,'','\0',NULL,'','\0','\0',5,1,'\0','\0',161,'','default',0,1478778092659,1478778692659,'snmpnode',NULL,'rejoe-0162.csez.zohocorpin.com','8','STATSDATA%','',0,1,'IF-rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(10,'PolledData','INTERFACE_in_octets','rejoe-0162.csez.zohocorpin.com','2.2.1.10.8','public',600,'','\0',NULL,'','\0','\0',5,1,'\0','\0',161,'','default',0,1478778092659,1478778692661,'snmpnode',NULL,'rejoe-0162.csez.zohocorpin.com','8','STATSDATA%','',0,1,'IF-rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(11,'PolledData','INTERFACE_out_octets','192.168.220.202','2.2.1.16.3','public',600,'','\0',NULL,'','\0','\0',5,1,'\0','\0',161,'','default',1734845574,1478270161126,1478778692685,'snmpnode',NULL,'192.168.220.202','3','STATSDATA%','',0,1,'IF-192.168.220.202','SNMP',1,0,'\0','v1',NULL,NULL),(12,'PolledData','INTERFACE_in_octets','192.168.220.202','2.2.1.10.3','public',600,'','\0',NULL,'','\0','\0',5,1,'\0','\0',161,'','default',555544034,1478270161126,1478778692686,'snmpnode',NULL,'192.168.220.202','3','STATSDATA%','',0,1,'IF-192.168.220.202','SNMP',1,0,'\0','v1',NULL,NULL),(13,'MultiplePolledData','CPUUtilization','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.25.3.3.1.2','public',300,'','\0','','','\0','',5,-1,'\0','\0',161,'','default',0,1478270461190,1478778707667,'WindowsCPUUtilization',NULL,'172.24.14.117','','STATSDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(14,'MultiplePolledData','MemoryUtilization','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.25.5.1.1.2','public',300,'','\0','','','\0','',5,1,'\0','\0',161,'','default',0,1478270161192,1478778707668,'MemoryUtilization',NULL,'172.24.14.117','','STATSDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(15,'MultiplePolledData','hrStorageType_RAM','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.25.2.3.1.2','public',300,'','\0','','','\0','',5,-1,'\0','\0',161,'','default',0,1478270161192,1478778707669,'MemoryUtilization',NULL,'172.24.14.117','','STATSDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(16,'MultiplePolledData','hrStorageUsed_RAM','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.25.2.3.1.6','public',300,'','\0','','','\0','',5,-1,'\0','\0',161,'','default',0,1478270161192,1478778707671,'MemoryUtilization',NULL,'172.24.14.117','','STATSDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(17,'MultiplePolledData','hrStorageAllocationUnits_RAM','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.25.2.3.1.4','public',300,'','\0','','','\0','',5,-1,'\0','\0',161,'','default',0,1478270161192,1478778707672,'MemoryUtilization',NULL,'172.24.14.117','','STATSDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(18,'PolledData','hrStorageSize_RAM','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.25.2.2.0','public',300,'','\0',NULL,'','\0','\0',5,1,'\0','\0',161,'','default',16662992,1478270161192,1478778707673,'MemoryUtilization',NULL,'172.24.14.117','','STATSDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(19,'MultiplePolledData','Device_INTERFACE_out_octets','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.2.2.1.16*8/$DELTA_TIME','public',600,'','\0','','','\0','',5,1,'\0','\0',161,'','default',0,1478266561099,1478778692755,'DevicesTraffic',NULL,'172.24.14.117','','STATSDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(20,'MultiplePolledData','Device_INTERFACE_in_octets','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.2.2.1.10*8/$DELTA_TIME','public',600,'','\0','','','\0','',5,1,'\0','\0',161,'','default',0,1478266561099,1478778692756,'DevicesTraffic',NULL,'172.24.14.117','','STATSDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(21,'MultiplePolledData','ReceiveErrors','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.2.2.1.14/$DELTA_TIME','public',600,'','\0','','','\0','',5,3,'\0','\0',161,'','default',0,1478266561099,1478778692757,'ReceiveErrors',NULL,'172.24.14.117','','DECIMALDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(22,'MultiplePolledData','TransmitErrors','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.2.2.1.20/$DELTA_TIME','public',600,'','\0','','','\0','',5,3,'\0','\0',161,'','default',0,1478270161097,1478778692759,'TransmitErrors',NULL,'172.24.14.117','','DECIMALDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(23,'MultiplePolledData','InDiscards','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.2.2.1.13/$DELTA_TIME','public',600,'','\0','','','\0','',5,3,'\0','\0',161,'','default',0,1478266561099,1478778692760,'InDiscards',NULL,'172.24.14.117','','DECIMALDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(24,'MultiplePolledData','OutDiscards','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.2.1.2.2.1.19/$DELTA_TIME','public',600,'','\0','','','\0','',5,3,'\0','\0',161,'','default',0,1478266561099,1478778692761,'OutDiscards',NULL,'172.24.14.117','','DECIMALDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(25,'MultiplePolledData','InterfaceOutUtilization','rejoe-0162.csez.zohocorpin.com','(.1.3.6.1.2.1.2.2.1.16*8*100)/(.1.3.6.1.2.1.2.2.1.5*$DELTA_TIME)','public',600,'','\0','','','\0','',5,-1,'\0','\0',161,'','default',0,0,1478778692763,'InterfaceOutUtilization',NULL,'172.24.14.117','','STATSDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(26,'MultiplePolledData','InterfaceInUtilization','rejoe-0162.csez.zohocorpin.com','(.1.3.6.1.2.1.2.2.1.10*8*100)/(.1.3.6.1.2.1.2.2.1.5*$DELTA_TIME)','public',600,'','\0','','','\0','',5,-1,'\0','\0',161,'','default',0,0,1478778692764,'InterfaceInUtilization',NULL,'172.24.14.117','','STATSDATA%','',0,1,'rejoe-0162.csez.zohocorpin.com','SNMP',1,0,'\0','v2',NULL,NULL),(27,'PolledData','JVMPD_CLIENT_14500_MonitorThread','Client_172.24.14.34','.1.3.6.1.4.1.42.2.145.3.163.1.1.3.1.0','public',30,'\0','\0',NULL,'','','\0',5,1,'\0','\0',14500,'','default',28,1478266800666,1478266845660,'','clientthreadcount','172.24.14.34','','NMS_STATUS_MONITOR%','',0,2,'','SNMP',1,0,'\0','v2',NULL,NULL),(28,'PolledData','JVMPD_CLIENT_14500_MonitorMemory','Client_172.24.14.34','.1.3.6.1.4.1.42.2.145.3.163.1.1.2.11.0+.1.3.6.1.4.1.42.2.145.3.163.1.1.2.21.0','public',30,'\0','\0',NULL,'','','\0',1,1,'','\0',14500,'','default',132142432,1478266800666,1478266830665,'','resourceusage','172.24.14.34','','NMS_STATUS_MONITOR%','',0,2,'','SNMP',1,0,'\0','v2',NULL,NULL);
/*!40000 ALTER TABLE `PolledData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PollingAttributes`
--

DROP TABLE IF EXISTS `PollingAttributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PollingAttributes` (
  `NAME` varchar(50) DEFAULT NULL,
  `TYPE` varchar(10) DEFAULT NULL,
  `PROPERTY` varchar(20) DEFAULT NULL,
  `POLLCONDITION` varchar(50) DEFAULT NULL,
  `VALUE` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PollingAttributes`
--

LOCK TABLES `PollingAttributes` WRITE;
/*!40000 ALTER TABLE `PollingAttributes` DISABLE KEYS */;
INSERT INTO `PollingAttributes` VALUES ('Win_95','STRING','sysOID','startswith','.1.3.6.1.4.1.311.1.1.3.2'),('.1.3.6.1.4.1.43.1.8.13','STRING','sysOID','startswith','.1.3.6.1.4.1.43.1.8.13'),('snmpnode','STRING','isSNMP','equals','true'),('WindowsCPUUtilization','STRING','type','startsWith','Windows'),('LinuxCPUUtilization','STRING','type','startsWith','Linux'),('SolarisCPUUtilization','STRING','type','startsWith','Solaris'),('MemoryUtilization','STRING','isSNMP','equals','true'),('DevicesTraffic','STRING','isSNMP','equals','true'),('DevicesTraffic','STRING','isRouter','equals','false'),('DevicesTraffic','STRING','type','notequals','Switch'),('ReceiveErrors','STRING','isSNMP','equals','true'),('ReceiveErrors','STRING','isRouter','equals','false'),('ReceiveErrors','STRING','type','notequals','Switch'),('TransmitErrors','STRING','isSNMP','equals','true'),('TransmitErrors','STRING','isRouter','equals','false'),('TransmitErrors','STRING','type','notequals','Switch'),('InDiscards','STRING','isSNMP','equals','true'),('InDiscards','STRING','isRouter','equals','false'),('InDiscards','STRING','type','notequals','Switch'),('OutDiscards','STRING','isSNMP','equals','true'),('OutDiscards','STRING','isRouter','equals','false'),('OutDiscards','STRING','type','notequals','Switch'),('InterfaceOutUtilization','STRING','isSNMP','equals','true'),('InterfaceOutUtilization','STRING','isRouter','equals','false'),('InterfaceOutUtilization','STRING','type','notequals','Switch'),('InterfaceInUtilization','STRING','isSNMP','equals','true'),('InterfaceInUtilization','STRING','isRouter','equals','false'),('InterfaceInUtilization','STRING','type','notequals','Switch'),('RouterCPUUtilization','STRING','isRouter','equals','true'),('RouterCPUUtilization','STRING','type','equals','CISCO'),('RouterMemoryUtilization','STRING','isRouter','equals','true'),('RouterBufferFailures','STRING','isRouter','equals','true'),('RouterSmallBuffHits','STRING','isRouter','equals','true'),('RouterMedBuffHits','STRING','isRouter','equals','true'),('RouterBigBuffHits','STRING','isRouter','equals','true'),('RouterLarBuffHits','STRING','isRouter','equals','true'),('RouterHugeBuffHits','STRING','isRouter','equals','true'),('RouterSmallBuffMisses','STRING','isRouter','equals','true'),('RouterMedBuffMisses','STRING','isRouter','equals','true'),('RouterBigBuffMisses','STRING','isRouter','equals','true'),('RouterLarBuffMisses','STRING','isRouter','equals','true'),('RouterHugeBuffMisses','STRING','isRouter','equals','true'),('RouterTraffic','STRING','isRouter','equals','true'),('RouterTraffic','STRING','isSNMP','equals','true'),('RouterReceiveErrors','STRING','isRouter','equals','true'),('RouterReceiveErrors','STRING','isSNMP','equals','true'),('RouterTransmitErrors','STRING','isRouter','equals','true'),('RouterTransmitErrors','STRING','isSNMP','equals','true'),('RouterInDiscards','STRING','isRouter','equals','true'),('RouterInDiscards','STRING','isSNMP','equals','true'),('RouterOutDiscards','STRING','isRouter','equals','true'),('RouterOutDiscards','STRING','isSNMP','equals','true'),('RouterInterfaceOutUtilization','STRING','isRouter','equals','true'),('RouterInterfaceOutUtilization','STRING','isSNMP','equals','true'),('RouterInterfaceInUtilization','STRING','isRouter','equals','true'),('RouterInterfaceInUtilization','STRING','isSNMP','equals','true'),('CiscoSwitchMemoryUtilization','STRING','type','equals','Switch'),('Switch_Traffic','STRING','type','equals','Switch'),('Switch_Traffic','STRING','isSNMP','equals','true'),('Switch_ReceiveErrors','STRING','type','equals','Switch'),('Switch_ReceiveErrors','STRING','isSNMP','equals','true'),('Switch_TransmitErrors','STRING','type','equals','Switch'),('Switch_TransmitErrors','STRING','isSNMP','equals','true'),('Switch_InDiscards','STRING','type','equals','Switch'),('Switch_InDiscards','STRING','isSNMP','equals','true'),('Switch_OutDiscards','STRING','type','equals','Switch'),('Switch_OutDiscards','STRING','isSNMP','equals','true'),('Switch_InterfaceOutUtilization','STRING','type','equals','Switch'),('Switch_InterfaceOutUtilization','STRING','isSNMP','equals','true'),('Switch_InterfaceInUtilization','STRING','type','equals','Switch'),('Switch_InterfaceInUtilization','STRING','isSNMP','equals','true');
/*!40000 ALTER TABLE `PollingAttributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PollingObjects`
--

DROP TABLE IF EXISTS `PollingObjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PollingObjects` (
  `NAME` varchar(50) NOT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PollingObjects`
--

LOCK TABLES `PollingObjects` WRITE;
/*!40000 ALTER TABLE `PollingObjects` DISABLE KEYS */;
INSERT INTO `PollingObjects` VALUES ('.1.3.6.1.4.1.43.1.8.13','true'),('CiscoSwitchMemoryUtilization','true'),('DevicesTraffic','true'),('InDiscards','true'),('InterfaceInUtilization','true'),('InterfaceOutUtilization','true'),('LinuxCPUUtilization','true'),('MemoryUtilization','true'),('OutDiscards','true'),('ReceiveErrors','true'),('RouterBigBuffHits','true'),('RouterBigBuffMisses','true'),('RouterBufferFailures','true'),('RouterCPUUtilization','true'),('RouterHugeBuffHits','true'),('RouterHugeBuffMisses','true'),('RouterInDiscards','true'),('RouterInterfaceInUtilization','true'),('RouterInterfaceOutUtilization','true'),('RouterLarBuffHits','true'),('RouterLarBuffMisses','true'),('RouterMedBuffHits','true'),('RouterMedBuffMisses','true'),('RouterMemoryUtilization','true'),('RouterOutDiscards','true'),('RouterReceiveErrors','true'),('RouterSmallBuffHits','true'),('RouterSmallBuffMisses','true'),('RouterTraffic','true'),('RouterTransmitErrors','true'),('snmpnode','true'),('SolarisCPUUtilization','true'),('Switch_InDiscards','true'),('Switch_InterfaceInUtilization','true'),('Switch_InterfaceOutUtilization','true'),('Switch_OutDiscards','true'),('Switch_ReceiveErrors','true'),('Switch_Traffic','true'),('Switch_TransmitErrors','true'),('TransmitErrors','true'),('WindowsCPUUtilization','true'),('Win_95','true');
/*!40000 ALTER TABLE `PollingObjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PortObject`
--

DROP TABLE IF EXISTS `PortObject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PortObject` (
  `MOID` bigint(20) NOT NULL,
  `PORTIFINDEX` int(11) DEFAULT NULL,
  `PORTIFDESCR` varchar(100) DEFAULT NULL,
  `PORTSTATE` varchar(100) DEFAULT NULL,
  `PORTIFSPEED` int(11) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK679DDF409E30C459` (`MOID`),
  CONSTRAINT `FK679DDF409E30C459` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PortObject`
--

LOCK TABLES `PortObject` WRITE;
/*!40000 ALTER TABLE `PortObject` DISABLE KEYS */;
/*!40000 ALTER TABLE `PortObject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Printer`
--

DROP TABLE IF EXISTS `Printer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Printer` (
  `MOID` bigint(20) NOT NULL,
  `DEVICESTATUS` int(11) DEFAULT NULL,
  `PRINTERSTATUS` int(11) DEFAULT NULL,
  `PRINTERDETECTEDERRSTATUS` int(11) DEFAULT NULL,
  `CONSOLEDISPBUFFERTEXT` varchar(100) DEFAULT NULL,
  `CONSOLELIGHTSTRING` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK50765FFA21D93CDB` (`MOID`),
  CONSTRAINT `FK50765FFA21D93CDB` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Printer`
--

LOCK TABLES `Printer` WRITE;
/*!40000 ALTER TABLE `Printer` DISABLE KEYS */;
/*!40000 ALTER TABLE `Printer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Providers`
--

DROP TABLE IF EXISTS `Providers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Providers` (
  `PROVIDERNAME` varchar(15) NOT NULL,
  `VALUE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PROVIDERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Providers`
--

LOCK TABLES `Providers` WRITE;
/*!40000 ALTER TABLE `Providers` DISABLE KEYS */;
INSERT INTO `Providers` VALUES ('CORBA','com.adventnet.nms.poll.corba.CorbaPollProvider'),('KPI','com.adventnet.nms.kpi.KPIProtocolProvider'),('SNMP','com.adventnet.nms.poll.SnmpProtocolProvider'),('TL1','com.adventnet.nms.poll.tl1.TL1DataCollectorAsync');
/*!40000 ALTER TABLE `Providers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProvisionResult`
--

DROP TABLE IF EXISTS `ProvisionResult`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProvisionResult` (
  `PRID` bigint(20) NOT NULL,
  `TYPE` varchar(50) DEFAULT NULL,
  `TEMPLATENAME` varchar(100) DEFAULT NULL,
  `SCHEDULEDTIME` bigint(20) DEFAULT NULL,
  `STARTTIME` bigint(20) DEFAULT NULL,
  `DURATION` int(11) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `STATUS` varchar(50) DEFAULT NULL,
  `DETAILS` varchar(250) DEFAULT NULL,
  `PROGRESS` int(11) DEFAULT NULL,
  PRIMARY KEY (`PRID`),
  KEY `ProvisionResult0_Indx` (`PRID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProvisionResult`
--

LOCK TABLES `ProvisionResult` WRITE;
/*!40000 ALTER TABLE `ProvisionResult` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProvisionResult` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProvisioningVariant`
--

DROP TABLE IF EXISTS `ProvisioningVariant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProvisioningVariant` (
  `DESCRIPTION` varchar(50) DEFAULT NULL,
  `VARIANTID` varchar(50) NOT NULL,
  PRIMARY KEY (`VARIANTID`),
  KEY `ProvisioningVariant0_ndx` (`VARIANTID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProvisioningVariant`
--

LOCK TABLES `ProvisioningVariant` WRITE;
/*!40000 ALTER TABLE `ProvisioningVariant` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProvisioningVariant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ProvisioningVariantProps`
--

DROP TABLE IF EXISTS `ProvisioningVariantProps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ProvisioningVariantProps` (
  `VARIANTID` varchar(50) NOT NULL,
  `PROPNAME` varchar(50) DEFAULT NULL,
  `PROPVAL` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ProvisioningVariantProps`
--

LOCK TABLES `ProvisioningVariantProps` WRITE;
/*!40000 ALTER TABLE `ProvisioningVariantProps` DISABLE KEYS */;
/*!40000 ALTER TABLE `ProvisioningVariantProps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPORTS_DAILY`
--

DROP TABLE IF EXISTS `REPORTS_DAILY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REPORTS_DAILY` (
  `POLLID` bigint(20) DEFAULT NULL,
  `INSTANCE` varchar(100) DEFAULT NULL,
  `TTIME` bigint(20) DEFAULT NULL,
  `VAL` bigint(20) DEFAULT NULL,
  `MINVALUE` bigint(20) DEFAULT NULL,
  `MAXVALUE` bigint(20) DEFAULT NULL,
  `TINTERVAL` bigint(20) DEFAULT NULL,
  KEY `REPORTS_DAILY0_ndx` (`POLLID`),
  KEY `REPORTS_DAILY2_ndx` (`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPORTS_DAILY`
--

LOCK TABLES `REPORTS_DAILY` WRITE;
/*!40000 ALTER TABLE `REPORTS_DAILY` DISABLE KEYS */;
/*!40000 ALTER TABLE `REPORTS_DAILY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REPORTS_HOURLY`
--

DROP TABLE IF EXISTS `REPORTS_HOURLY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REPORTS_HOURLY` (
  `POLLID` bigint(20) DEFAULT NULL,
  `INSTANCE` varchar(100) DEFAULT NULL,
  `TTIME` bigint(20) DEFAULT NULL,
  `VAL` bigint(20) DEFAULT NULL,
  `MINVALUE` bigint(20) DEFAULT NULL,
  `MAXVALUE` bigint(20) DEFAULT NULL,
  `TINTERVAL` bigint(20) DEFAULT NULL,
  KEY `REPORTS_HOURLY0_ndx` (`POLLID`),
  KEY `REPORTS_HOURLY2_ndx` (`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REPORTS_HOURLY`
--

LOCK TABLES `REPORTS_HOURLY` WRITE;
/*!40000 ALTER TABLE `REPORTS_HOURLY` DISABLE KEYS */;
/*!40000 ALTER TABLE `REPORTS_HOURLY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reports`
--

DROP TABLE IF EXISTS `Reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reports` (
  `CLASSNAME` varchar(100) NOT NULL,
  `DAILY` varchar(10) DEFAULT NULL,
  `WEEKLY` varchar(10) DEFAULT NULL,
  `HOUR` varchar(100) DEFAULT NULL,
  `MONTHDAYS` varchar(50) DEFAULT NULL,
  `WEEKDAYS` varchar(50) DEFAULT NULL,
  `TIMEVAL` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CLASSNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reports`
--

LOCK TABLES `Reports` WRITE;
/*!40000 ALTER TABLE `Reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STATSAGGREGATIONDAILY`
--

DROP TABLE IF EXISTS `STATSAGGREGATIONDAILY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STATSAGGREGATIONDAILY` (
  `POLLID` bigint(20) NOT NULL,
  `INSTANCE` varchar(100) NOT NULL,
  `TTIME` bigint(20) NOT NULL,
  `VAL` decimal(19,4) DEFAULT NULL,
  `MMINVALUE` decimal(19,4) DEFAULT NULL,
  `MMAXVALUE` decimal(19,4) DEFAULT NULL,
  `TINTERVAL` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`),
  KEY `STATSAGGREGATIONDAILY0_ndx` (`POLLID`),
  KEY `STATSAGGREGATIONDAILY2_ndx` (`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STATSAGGREGATIONDAILY`
--

LOCK TABLES `STATSAGGREGATIONDAILY` WRITE;
/*!40000 ALTER TABLE `STATSAGGREGATIONDAILY` DISABLE KEYS */;
INSERT INTO `STATSAGGREGATIONDAILY` VALUES (1,'-1',1478266733867,54.1989,0.8190,100.0000,86400000),(4,'-1',1478264675646,0.0000,0.0000,0.0000,86400000),(5,'-1',1478266808874,1.0077,0.0834,3.1164,86400000),(6,'-1',1478266808878,0.4193,0.0000,1.3641,86400000),(9,'-1',1478264461099,73805905.0000,52550299.0000,95061511.0000,86400000),(10,'-1',1478264461099,99036085.5000,92427470.0000,105644701.0000,86400000),(11,'-1',1478261221125,2455086.9267,199247.0000,4712722.0000,86400000),(12,'-1',1478261221125,8351765.7733,3586323.0000,16152592.0000,86400000),(14,'RAMUSAGE',1478261221189,33.6333,26.0000,38.0000,86400000),(18,'-1',1478261221189,16662992.0000,16662992.0000,16662992.0000,86400000),(19,'.3',1478264461099,11.5000,0.0000,23.0000,86400000),(19,'.8',1478264461099,48.5000,0.0000,97.0000,86400000),(20,'.3',1478264461099,46.0000,0.0000,92.0000,86400000),(20,'.8',1478264461099,85.5000,0.0000,171.0000,86400000),(21,'.3',1478264461099,0.0000,0.0000,0.0000,86400000),(21,'.8',1478264461099,0.0000,0.0000,0.0000,86400000),(22,'.3',1478262886099,0.0000,0.0000,0.0000,86400000),(22,'.8',1478262886099,0.0000,0.0000,0.0000,86400000),(23,'.3',1478264461099,0.0000,0.0000,0.0000,86400000),(23,'.8',1478264461099,0.0000,0.0000,0.0000,86400000),(24,'.3',1478264461099,0.0000,0.0000,0.0000,86400000),(24,'.8',1478264461099,0.0000,0.0000,0.0000,86400000),(25,'.3',1478264461099,0.0000,0.0000,0.0000,86400000),(25,'.8',1478264461099,0.0000,0.0000,0.0000,86400000),(26,'.3',1478264461099,0.0000,0.0000,0.0000,86400000),(26,'.8',1478264461099,0.0000,0.0000,0.0000,86400000);
/*!40000 ALTER TABLE `STATSAGGREGATIONDAILY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STATSAGGREGATIONHOURLY`
--

DROP TABLE IF EXISTS `STATSAGGREGATIONHOURLY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STATSAGGREGATIONHOURLY` (
  `POLLID` bigint(20) NOT NULL,
  `INSTANCE` varchar(100) NOT NULL,
  `TTIME` bigint(20) NOT NULL,
  `VAL` decimal(19,4) DEFAULT NULL,
  `MMINVALUE` decimal(19,4) DEFAULT NULL,
  `MMAXVALUE` decimal(19,4) DEFAULT NULL,
  `TINTERVAL` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`),
  KEY `STATSAGGREGATIONHOURLY0_ndx` (`POLLID`),
  KEY `STATSAGGREGATIONHOURLY2_ndx` (`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STATSAGGREGATIONHOURLY`
--

LOCK TABLES `STATSAGGREGATIONHOURLY` WRITE;
/*!40000 ALTER TABLE `STATSAGGREGATIONHOURLY` DISABLE KEYS */;
INSERT INTO `STATSAGGREGATIONHOURLY` VALUES (1,'-1',1478254775619,98.4900,96.4910,100.0000,3600000),(1,'-1',1478257325618,91.1560,87.3780,98.9470,3600000),(1,'-1',1478260925617,76.6388,70.4910,89.3440,3600000),(1,'-1',1478264525620,65.3682,62.2950,70.4910,3600000),(1,'-1',1478268125624,59.5626,57.3770,65.5730,3600000),(1,'-1',1478271693104,33.7698,10.6550,54.0980,3600000),(1,'-1',1478276763648,7.7865,5.7370,9.8360,3600000),(1,'-1',1478279736091,0.8190,0.8190,0.8190,3600000),(1,'-1',1478282886178,0.8190,0.8190,0.8190,3600000),(1,'-1',1478286936180,0.8190,0.8190,0.8190,3600000),(1,'-1',1478290667295,0.8190,0.8190,0.8190,3600000),(1,'-1',1478294944334,0.8190,0.8190,0.8190,3600000),(1,'-1',1478299097339,0.8190,0.8190,0.8190,3600000),(1,'-1',1478303072834,0.8190,0.8190,0.8190,3600000),(1,'-1',1478306662152,0.8190,0.8190,0.8190,3600000),(1,'-1',1478310549484,0.8190,0.8190,0.8190,3600000),(1,'-1',1478314700650,0.8190,0.8190,0.8190,3600000),(1,'-1',1478318849481,0.8190,0.8190,0.8190,3600000),(1,'-1',1478776457783,37.1578,0.8190,60.6550,3600000),(4,'-1',1478262575646,0.0000,0.0000,0.0000,3600000),(4,'-1',1478266775646,0.0000,0.0000,0.0000,3600000),(5,'-1',1478255375617,0.0834,0.0834,0.0834,3600000),(5,'-1',1478257325625,0.0834,0.0834,0.0834,3600000),(5,'-1',1478260925623,0.7732,0.0834,0.9192,3600000),(5,'-1',1478264525624,1.0819,0.9192,1.2332,3600000),(5,'-1',1478268125623,1.4160,1.2332,1.5086,3600000),(5,'-1',1478271693121,2.0052,1.6027,3.1164,3600000),(5,'-1',1478276763667,1.9117,0.7069,3.1164,3600000),(5,'-1',1478279736094,0.7069,0.7069,0.7069,3600000),(5,'-1',1478282886185,0.7069,0.7069,0.7069,3600000),(5,'-1',1478286936182,0.7069,0.7069,0.7069,3600000),(5,'-1',1478290667294,0.7069,0.7069,0.7069,3600000),(5,'-1',1478294944355,0.7069,0.7069,0.7069,3600000),(5,'-1',1478299097356,0.7069,0.7069,0.7069,3600000),(5,'-1',1478303072849,0.7069,0.7069,0.7069,3600000),(5,'-1',1478306662165,0.7069,0.7069,0.7069,3600000),(5,'-1',1478310549494,0.7069,0.7069,0.7069,3600000),(5,'-1',1478314700662,0.7069,0.7069,0.7069,3600000),(5,'-1',1478318849494,0.7069,0.7069,0.7069,3600000),(5,'-1',1478776457792,115.7949,0.7069,140.5103,3600000),(6,'-1',1478255375628,0.0000,0.0000,0.0000,3600000),(6,'-1',1478257325625,0.0000,0.0000,0.0000,3600000),(6,'-1',1478260925624,0.3510,0.0000,0.5620,3600000),(6,'-1',1478264525625,0.4405,0.3379,0.5895,3600000),(6,'-1',1478268125626,0.7370,0.5895,0.7950,3600000),(6,'-1',1478271693130,1.1441,0.9689,1.3641,3600000),(6,'-1',1478276763672,0.6821,0.0000,1.3641,3600000),(6,'-1',1478279736097,0.0000,0.0000,0.0000,3600000),(6,'-1',1478282886185,0.0000,0.0000,0.0000,3600000),(6,'-1',1478286936184,0.0000,0.0000,0.0000,3600000),(6,'-1',1478290667297,0.0000,0.0000,0.0000,3600000),(6,'-1',1478294944359,0.0000,0.0000,0.0000,3600000),(6,'-1',1478299097359,0.0000,0.0000,0.0000,3600000),(6,'-1',1478303072850,0.0000,0.0000,0.0000,3600000),(6,'-1',1478306662174,0.0000,0.0000,0.0000,3600000),(6,'-1',1478310549505,0.0000,0.0000,0.0000,3600000),(6,'-1',1478314700667,0.0000,0.0000,0.0000,3600000),(6,'-1',1478318849498,0.0000,0.0000,0.0000,3600000),(6,'-1',1478776457793,13.8828,0.0000,33.1191,3600000),(9,'-1',1478262361100,95061511.0000,95061511.0000,95061511.0000,3600000),(9,'-1',1478266561099,52550299.0000,52550299.0000,52550299.0000,3600000),(9,'-1',1478776292661,1221091426.6667,0.0000,3663274280.0000,3600000),(10,'-1',1478262361100,105644701.0000,105644701.0000,105644701.0000,3600000),(10,'-1',1478266561099,92427470.0000,92427470.0000,92427470.0000,3600000),(10,'-1',1478776292661,1162204707.0000,0.0000,3486614121.0000,3600000),(11,'-1',1478255161125,2690462.0000,2690462.0000,2690462.0000,3600000),(11,'-1',1478257561125,2738570.8000,1272248.0000,4712722.0000,3600000),(11,'-1',1478260861126,2983256.3333,1969709.0000,3869447.0000,3600000),(11,'-1',1478264461126,2076993.5000,199247.0000,3817430.0000,3600000),(11,'-1',1478268061126,1786152.0000,255885.0000,3766509.0000,3600000),(12,'-1',1478255161125,7705396.0000,7705396.0000,7705396.0000,3600000),(12,'-1',1478257561125,8973378.2000,6337243.0000,16152592.0000,3600000),(12,'-1',1478260861126,9033647.3333,7001610.0000,11889017.0000,3600000),(12,'-1',1478264461126,7651066.5000,3586323.0000,13535704.0000,3600000),(12,'-1',1478268061126,8395340.8333,3606572.0000,14099607.0000,3600000),(14,'RAMUSAGE',1478255161188,33.0000,33.0000,33.0000,3600000),(14,'RAMUSAGE',1478257561190,34.0000,34.0000,34.0000,3600000),(14,'RAMUSAGE',1478260711189,35.2500,34.0000,36.0000,3600000),(14,'RAMUSAGE',1478264461191,34.1667,26.0000,38.0000,3600000),(14,'RAMUSAGE',1478268211187,31.7500,27.0000,34.0000,3600000),(18,'-1',1478255161188,16662992.0000,16662992.0000,16662992.0000,3600000),(18,'-1',1478257561190,16662992.0000,16662992.0000,16662992.0000,3600000),(18,'-1',1478260711189,16662992.0000,16662992.0000,16662992.0000,3600000),(18,'-1',1478264461191,16662992.0000,16662992.0000,16662992.0000,3600000),(18,'-1',1478268211187,16662992.0000,16662992.0000,16662992.0000,3600000),(19,'.3',1478262361100,0.0000,0.0000,0.0000,3600000),(19,'.3',1478266561099,23.0000,23.0000,23.0000,3600000),(19,'.8',1478262361100,0.0000,0.0000,0.0000,3600000),(19,'.8',1478266561099,97.0000,97.0000,97.0000,3600000),(20,'.3',1478262361100,0.0000,0.0000,0.0000,3600000),(20,'.3',1478266561099,92.0000,92.0000,92.0000,3600000),(20,'.8',1478262361100,0.0000,0.0000,0.0000,3600000),(20,'.8',1478266561099,171.0000,171.0000,171.0000,3600000),(21,'.3',1478262361100,0.0000,0.0000,0.0000,3600000),(21,'.3',1478266561099,0.0000,0.0000,0.0000,3600000),(21,'.8',1478262361100,0.0000,0.0000,0.0000,3600000),(21,'.8',1478266561099,0.0000,0.0000,0.0000,3600000),(22,'.3',1478258161098,0.0000,0.0000,0.0000,3600000),(22,'.3',1478261041099,0.0000,0.0000,0.0000,3600000),(22,'.3',1478264461100,0.0000,0.0000,0.0000,3600000),(22,'.3',1478267881099,0.0000,0.0000,0.0000,3600000),(22,'.8',1478258161098,0.0000,0.0000,0.0000,3600000),(22,'.8',1478261041099,0.0000,0.0000,0.0000,3600000),(22,'.8',1478264461100,0.0000,0.0000,0.0000,3600000),(22,'.8',1478267881099,0.0000,0.0000,0.0000,3600000),(23,'.3',1478262361100,0.0000,0.0000,0.0000,3600000),(23,'.3',1478266561099,0.0000,0.0000,0.0000,3600000),(23,'.8',1478262361100,0.0000,0.0000,0.0000,3600000),(23,'.8',1478266561099,0.0000,0.0000,0.0000,3600000),(24,'.3',1478262361100,0.0000,0.0000,0.0000,3600000),(24,'.3',1478266561099,0.0000,0.0000,0.0000,3600000),(24,'.8',1478262361100,0.0000,0.0000,0.0000,3600000),(24,'.8',1478266561099,0.0000,0.0000,0.0000,3600000),(25,'.3',1478262361100,0.0000,0.0000,0.0000,3600000),(25,'.3',1478266561099,0.0000,0.0000,0.0000,3600000),(25,'.8',1478262361100,0.0000,0.0000,0.0000,3600000),(25,'.8',1478266561099,0.0000,0.0000,0.0000,3600000),(26,'.3',1478262361100,0.0000,0.0000,0.0000,3600000),(26,'.3',1478266561099,0.0000,0.0000,0.0000,3600000),(26,'.8',1478262361100,0.0000,0.0000,0.0000,3600000),(26,'.8',1478266561099,0.0000,0.0000,0.0000,3600000);
/*!40000 ALTER TABLE `STATSAGGREGATIONHOURLY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STATSDATA11_10_2016`
--

DROP TABLE IF EXISTS `STATSDATA11_10_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STATSDATA11_10_2016` (
  `POLLID` bigint(20) NOT NULL,
  `INSTANCE` varchar(100) NOT NULL,
  `TTIME` bigint(20) NOT NULL,
  `VAL` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STATSDATA11_10_2016`
--

LOCK TABLES `STATSDATA11_10_2016` WRITE;
/*!40000 ALTER TABLE `STATSDATA11_10_2016` DISABLE KEYS */;
INSERT INTO `STATSDATA11_10_2016` VALUES (9,'-1',1478775692661,3663274280),(9,'-1',1478776292660,0),(9,'-1',1478776892663,0),(9,'-1',1478777492660,0),(9,'-1',1478778092659,0),(10,'-1',1478775692661,3486614121),(10,'-1',1478776292660,0),(10,'-1',1478776892663,0),(10,'-1',1478777492660,0),(10,'-1',1478778092659,0);
/*!40000 ALTER TABLE `STATSDATA11_10_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STATSDATA11_4_2016`
--

DROP TABLE IF EXISTS `STATSDATA11_4_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STATSDATA11_4_2016` (
  `POLLID` bigint(20) NOT NULL,
  `INSTANCE` varchar(100) NOT NULL,
  `TTIME` bigint(20) NOT NULL,
  `VAL` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STATSDATA11_4_2016`
--

LOCK TABLES `STATSDATA11_4_2016` WRITE;
/*!40000 ALTER TABLE `STATSDATA11_4_2016` DISABLE KEYS */;
INSERT INTO `STATSDATA11_4_2016` VALUES (9,'-1',1478262361100,95061511),(9,'-1',1478266561099,52550299),(10,'-1',1478262361100,105644701),(10,'-1',1478266561099,92427470),(11,'-1',1478255161125,2690462),(11,'-1',1478256361126,4712722),(11,'-1',1478256961123,2382868),(11,'-1',1478257561126,1272248),(11,'-1',1478258161127,2756835),(11,'-1',1478258761124,2568181),(11,'-1',1478259361125,1969709),(11,'-1',1478259961128,3613255),(11,'-1',1478260561126,2690217),(11,'-1',1478261161124,2726136),(11,'-1',1478261761127,3869447),(11,'-1',1478262361127,3030774),(11,'-1',1478262961123,3180027),(11,'-1',1478263561127,3817430),(11,'-1',1478264161128,2842670),(11,'-1',1478264761128,2148605),(11,'-1',1478265361126,273982),(11,'-1',1478265961127,199247),(11,'-1',1478266561126,255885),(11,'-1',1478267161124,354729),(11,'-1',1478267761128,967472),(11,'-1',1478268361127,3766509),(11,'-1',1478268961128,3210479),(11,'-1',1478269561123,2161838),(11,'-1',1478270161126,3429605),(12,'-1',1478255161125,7705396),(12,'-1',1478256361126,16152592),(12,'-1',1478256961123,7240874),(12,'-1',1478257561126,6337243),(12,'-1',1478258161127,7600862),(12,'-1',1478258761124,7535320),(12,'-1',1478259361125,7001610),(12,'-1',1478259961128,8756814),(12,'-1',1478260561126,7375042),(12,'-1',1478261161124,11889017),(12,'-1',1478261761127,9597918),(12,'-1',1478262361127,9581483),(12,'-1',1478262961123,13535704),(12,'-1',1478263561127,8481493),(12,'-1',1478264161128,8887365),(12,'-1',1478264761128,7310968),(12,'-1',1478265361126,4104546),(12,'-1',1478265961127,3586323),(12,'-1',1478266561126,3606572),(12,'-1',1478267161124,3641735),(12,'-1',1478267761128,8928726),(12,'-1',1478268361127,14099607),(12,'-1',1478268961128,10755417),(12,'-1',1478269561123,9339988),(12,'-1',1478270161126,10042708),(14,'RAMUSAGE',1478255161188,33),(14,'RAMUSAGE',1478257561190,34),(14,'RAMUSAGE',1478259361188,35),(14,'RAMUSAGE',1478260561190,34),(14,'RAMUSAGE',1478261161189,36),(14,'RAMUSAGE',1478261761191,36),(14,'RAMUSAGE',1478262961191,38),(14,'RAMUSAGE',1478263561192,37),(14,'RAMUSAGE',1478264161190,38),(14,'RAMUSAGE',1478264761192,37),(14,'RAMUSAGE',1478265361192,29),(14,'RAMUSAGE',1478265961189,26),(14,'RAMUSAGE',1478267161187,27),(14,'RAMUSAGE',1478267761188,32),(14,'RAMUSAGE',1478268361187,34),(14,'RAMUSAGE',1478269561188,34),(14,'RAMUSAGE',1478270161192,36),(18,'-1',1478255161188,16662992),(18,'-1',1478257561190,16662992),(18,'-1',1478259361188,16662992),(18,'-1',1478260561190,16662992),(18,'-1',1478261161189,16662992),(18,'-1',1478261761191,16662992),(18,'-1',1478262961191,16662992),(18,'-1',1478263561192,16662992),(18,'-1',1478264161190,16662992),(18,'-1',1478264761192,16662992),(18,'-1',1478265361192,16662992),(18,'-1',1478265961189,16662992),(18,'-1',1478267161187,16662992),(18,'-1',1478267761188,16662992),(18,'-1',1478268361187,16662992),(18,'-1',1478269561188,16662992),(18,'-1',1478270161192,16662992),(19,'.3',1478262361100,0),(19,'.3',1478266561099,23),(19,'.8',1478262361100,0),(19,'.8',1478266561099,97),(20,'.3',1478262361100,0),(20,'.3',1478266561099,92),(20,'.8',1478262361100,0),(20,'.8',1478266561099,171);
/*!40000 ALTER TABLE `STATSDATA11_4_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STATSDATA11_5_2016`
--

DROP TABLE IF EXISTS `STATSDATA11_5_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STATSDATA11_5_2016` (
  `POLLID` bigint(20) NOT NULL,
  `INSTANCE` varchar(100) NOT NULL,
  `TTIME` bigint(20) NOT NULL,
  `VAL` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`POLLID`,`INSTANCE`,`TTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STATSDATA11_5_2016`
--

LOCK TABLES `STATSDATA11_5_2016` WRITE;
/*!40000 ALTER TABLE `STATSDATA11_5_2016` DISABLE KEYS */;
/*!40000 ALTER TABLE `STATSDATA11_5_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STRINGDATA11_10_2016`
--

DROP TABLE IF EXISTS `STRINGDATA11_10_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STRINGDATA11_10_2016` (
  `POLLID` bigint(20) DEFAULT NULL,
  `INSTANCE` varchar(100) DEFAULT NULL,
  `TTIME` bigint(20) DEFAULT NULL,
  `VALUE` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STRINGDATA11_10_2016`
--

LOCK TABLES `STRINGDATA11_10_2016` WRITE;
/*!40000 ALTER TABLE `STRINGDATA11_10_2016` DISABLE KEYS */;
/*!40000 ALTER TABLE `STRINGDATA11_10_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STRINGDATA11_4_2016`
--

DROP TABLE IF EXISTS `STRINGDATA11_4_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STRINGDATA11_4_2016` (
  `POLLID` bigint(20) DEFAULT NULL,
  `INSTANCE` varchar(100) DEFAULT NULL,
  `TTIME` bigint(20) DEFAULT NULL,
  `VALUE` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STRINGDATA11_4_2016`
--

LOCK TABLES `STRINGDATA11_4_2016` WRITE;
/*!40000 ALTER TABLE `STRINGDATA11_4_2016` DISABLE KEYS */;
/*!40000 ALTER TABLE `STRINGDATA11_4_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STRINGDATA11_5_2016`
--

DROP TABLE IF EXISTS `STRINGDATA11_5_2016`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STRINGDATA11_5_2016` (
  `POLLID` bigint(20) DEFAULT NULL,
  `INSTANCE` varchar(100) DEFAULT NULL,
  `TTIME` bigint(20) DEFAULT NULL,
  `VALUE` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STRINGDATA11_5_2016`
--

LOCK TABLES `STRINGDATA11_5_2016` WRITE;
/*!40000 ALTER TABLE `STRINGDATA11_5_2016` DISABLE KEYS */;
/*!40000 ALTER TABLE `STRINGDATA11_5_2016` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SendEmailAlertAction`
--

DROP TABLE IF EXISTS `SendEmailAlertAction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SendEmailAlertAction` (
  `ID` int(11) NOT NULL,
  `EMAILPROPS` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SendEmailAlertAction`
--

LOCK TABLES `SendEmailAlertAction` WRITE;
/*!40000 ALTER TABLE `SendEmailAlertAction` DISABLE KEYS */;
/*!40000 ALTER TABLE `SendEmailAlertAction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SendEmailEventAction`
--

DROP TABLE IF EXISTS `SendEmailEventAction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SendEmailEventAction` (
  `ID` int(11) NOT NULL,
  `EMAILPROPS` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SendEmailEventAction`
--

LOCK TABLES `SendEmailEventAction` WRITE;
/*!40000 ALTER TABLE `SendEmailEventAction` DISABLE KEYS */;
/*!40000 ALTER TABLE `SendEmailEventAction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SnmpInterface`
--

DROP TABLE IF EXISTS `SnmpInterface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SnmpInterface` (
  `MOID` bigint(20) NOT NULL,
  `HOSTNETMASK` varchar(100) DEFAULT NULL,
  `IFDESCR` varchar(200) DEFAULT NULL,
  `IFINDEX` int(11) DEFAULT NULL,
  `IFSPEED` int(11) DEFAULT NULL,
  `PHYSADDR` varchar(100) DEFAULT NULL,
  `PHYSMEDIA` varchar(100) DEFAULT NULL,
  `SYSOID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK7DB9517B6E19E932` (`MOID`),
  CONSTRAINT `FK7DB9517B6E19E932` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SnmpInterface`
--

LOCK TABLES `SnmpInterface` WRITE;
/*!40000 ALTER TABLE `SnmpInterface` DISABLE KEYS */;
INSERT INTO `SnmpInterface` VALUES (43,'255.255.0.0','Intel(R) Dual Band Wireless-AC 7265 #2',8,866700000,'94 65 9c 5a 2d 69','71','.1.3.6.1.4.1.311.1.1.3.1.1'),(44,'255.255.252.0','Intel(R) Ethernet Connection (3) I218-LM',3,1000000000,'34 e6 d7 86 e2 ab','6','.1.3.6.1.4.1.311.1.1.3.1.1');
/*!40000 ALTER TABLE `SnmpInterface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SnmpNode`
--

DROP TABLE IF EXISTS `SnmpNode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SnmpNode` (
  `MOID` bigint(20) NOT NULL,
  `HOSTNETMASK` varchar(100) DEFAULT NULL,
  `SYSDESCR` varchar(200) DEFAULT NULL,
  `SYSNAME` varchar(100) DEFAULT NULL,
  `SYSOID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK293EA880896A8103` (`MOID`),
  CONSTRAINT `FK293EA880896A8103` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SnmpNode`
--

LOCK TABLES `SnmpNode` WRITE;
/*!40000 ALTER TABLE `SnmpNode` DISABLE KEYS */;
INSERT INTO `SnmpNode` VALUES (45,'255.255.0.0','Hardware: Intel64 Family 6 Model 61 Stepping 4 AT/AT COMPATIBLE - Software: Windows Version 6.3 (Build 9600 Multiprocessor Free)','rejoe-0162.csez.zohocorpin.com','.1.3.6.1.4.1.311.1.1.3.1.1');
/*!40000 ALTER TABLE `SnmpNode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StageIdVsConfigId`
--

DROP TABLE IF EXISTS `StageIdVsConfigId`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StageIdVsConfigId` (
  `PRID` bigint(20) NOT NULL,
  `STAGEID` varchar(100) NOT NULL,
  `CONFIGID` int(11) NOT NULL,
  PRIMARY KEY (`PRID`,`STAGEID`,`CONFIGID`),
  KEY `StageIdVsConfigId0_Indx` (`PRID`),
  KEY `StageIdVsConfigId1_Indx` (`STAGEID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StageIdVsConfigId`
--

LOCK TABLES `StageIdVsConfigId` WRITE;
/*!40000 ALTER TABLE `StageIdVsConfigId` DISABLE KEYS */;
/*!40000 ALTER TABLE `StageIdVsConfigId` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StatsTables`
--

DROP TABLE IF EXISTS `StatsTables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StatsTables` (
  `TableName` varchar(100) NOT NULL,
  `CreateSchema` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TableName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StatsTables`
--

LOCK TABLES `StatsTables` WRITE;
/*!40000 ALTER TABLE `StatsTables` DISABLE KEYS */;
INSERT INTO `StatsTables` VALUES ('KPI_STATSDATA%','create table <> (POLLID BIGINT,INSTANCE varchar(100),TTIME BIGINT,VAL Numeric(19,4),PRIMARY KEY (POLLID,INSTANCE,TTIME))'),('NMS_STATUS_MONITOR%','create table <> (POLLID BIGINT,INSTANCE varchar(100),TTIME BIGINT,VAL BIGINT)');
/*!40000 ALTER TABLE `StatsTables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SwitchObject`
--

DROP TABLE IF EXISTS `SwitchObject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SwitchObject` (
  `MOID` bigint(20) NOT NULL,
  `ROOTPORT` int(11) DEFAULT NULL,
  `ROOTCOST` int(11) DEFAULT NULL,
  `NUMPORTS` int(11) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK4C0B63B3695F01CC` (`MOID`),
  CONSTRAINT `FK4C0B63B3695F01CC` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SwitchObject`
--

LOCK TABLES `SwitchObject` WRITE;
/*!40000 ALTER TABLE `SwitchObject` DISABLE KEYS */;
/*!40000 ALTER TABLE `SwitchObject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TL1Interface`
--

DROP TABLE IF EXISTS `TL1Interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TL1Interface` (
  `MOID` bigint(20) NOT NULL,
  `STATPOLLCOMMAND` varchar(254) DEFAULT NULL,
  `CONNECTIONHANDLER` varchar(150) DEFAULT NULL,
  `DICTIONARY` varchar(100) DEFAULT NULL,
  `SESSIONID` varchar(100) DEFAULT NULL,
  `TL1PORT` int(11) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK11A58880F6B1DA18` (`MOID`),
  CONSTRAINT `FK11A58880F6B1DA18` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TL1Interface`
--

LOCK TABLES `TL1Interface` WRITE;
/*!40000 ALTER TABLE `TL1Interface` DISABLE KEYS */;
/*!40000 ALTER TABLE `TL1Interface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TL1Node`
--

DROP TABLE IF EXISTS `TL1Node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TL1Node` (
  `MOID` bigint(20) NOT NULL,
  `LOGINCOMMAND` varchar(254) DEFAULT NULL,
  `INITCOMMAND` varchar(254) DEFAULT NULL,
  `CONNECTIONHANDLER` varchar(150) DEFAULT NULL,
  `INFOCOMMAND` varchar(254) DEFAULT NULL,
  `DICTIONARY` varchar(100) DEFAULT NULL,
  `SESSIONID` varchar(100) DEFAULT NULL,
  `TL1PORT` int(11) DEFAULT NULL,
  `NOTIFYID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FKE013625BB76D185D` (`MOID`),
  CONSTRAINT `FKE013625BB76D185D` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TL1Node`
--

LOCK TABLES `TL1Node` WRITE;
/*!40000 ALTER TABLE `TL1Node` DISABLE KEYS */;
/*!40000 ALTER TABLE `TL1Node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TaskAudit`
--

DROP TABLE IF EXISTS `TaskAudit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TaskAudit` (
  `EXECUTIONID` int(11) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `TASKNAME` varchar(50) DEFAULT NULL,
  `DEVICELIST` text,
  `DATASOURCE` varchar(50) DEFAULT NULL,
  `EXECUTEDTIME` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TaskAudit`
--

LOCK TABLES `TaskAudit` WRITE;
/*!40000 ALTER TABLE `TaskAudit` DISABLE KEYS */;
/*!40000 ALTER TABLE `TaskAudit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TaskToDeviceListMap`
--

DROP TABLE IF EXISTS `TaskToDeviceListMap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TaskToDeviceListMap` (
  `USERNAME` varchar(50) DEFAULT NULL,
  `TASKNAME` varchar(50) DEFAULT NULL,
  `DEVICELISTNAMES` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TaskToDeviceListMap`
--

LOCK TABLES `TaskToDeviceListMap` WRITE;
/*!40000 ALTER TABLE `TaskToDeviceListMap` DISABLE KEYS */;
/*!40000 ALTER TABLE `TaskToDeviceListMap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ThresholdObjects`
--

DROP TABLE IF EXISTS `ThresholdObjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ThresholdObjects` (
  `NAME` varchar(20) NOT NULL,
  `KIND` varchar(15) DEFAULT NULL,
  `SEVERITY` int(11) DEFAULT NULL,
  `TRIGGERSEVERITY` int(11) DEFAULT NULL,
  `RESETSEVERITY` int(11) DEFAULT NULL,
  `THRESHOLDTYPE` varchar(5) DEFAULT NULL,
  `CATEGORY` varchar(20) DEFAULT NULL,
  `THRESHOLDVALUE` decimal(23,4) DEFAULT NULL,
  `REARMVALUE` decimal(23,4) DEFAULT NULL,
  `MMESSAGE` varchar(100) DEFAULT NULL,
  `CLEARMESSAGE` varchar(100) DEFAULT NULL,
  `SENDCLEAR` varchar(10) DEFAULT NULL,
  `ALLOWED` varchar(50) DEFAULT NULL,
  `DISALLOWED` varchar(50) DEFAULT NULL,
  `OID` varchar(50) DEFAULT NULL,
  `OIDTYPE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ThresholdObjects`
--

LOCK TABLES `ThresholdObjects` WRITE;
/*!40000 ALTER TABLE `ThresholdObjects` DISABLE KEYS */;
INSERT INTO `ThresholdObjects` VALUES ('bethreadcount','long',1,-1,5,'max','NMSManagement',125.0000,105.0000,'Threshold exceeded : ','Threshold reset : ','true','NULL','NULL','',''),('clientthreadcount','long',1,-1,5,'max','NMSManagement',35.0000,25.0000,'Threshold exceeded : ','Threshold reset : ','true','NULL','NULL','',''),('Fault-Res-Crit','long',1,-1,5,'max','KPI',48.0000,40.0000,'Fault Resolution Time is more than 2 days.','Fault Resolution Time within 2 days.','false','NULL','NULL','',''),('Fault-Res-Major','range',2,-1,5,'in','KPI',48.0000,24.0000,'Fault Resolution Time is 1-2 days.','Fault Resolution Time within 1 day.','true','NULL','NULL','',''),('faultmgmtthreshold','long',2,-1,5,'max','NMSManagement',150.0000,100.0000,'Threshold exceeded : ','Threshold reset : ','false','NULL','NULL','',''),('fethreadcount','long',1,-1,5,'max','NMSManagement',60.0000,45.0000,'Threshold exceeded : ','Threshold reset : ','true','NULL','NULL','',''),('Network-Avl-Crit','long',1,-1,5,'min','KPI',70.0000,80.0000,'Network Availability percentage is less than 70%','Network Availability percentage greater than 70','false','NULL','NULL','',''),('Network-Avl-Major','range',2,-1,5,'in','KPI',90.0000,70.0000,'Network Availability percentage is between 70% and 90%','Network Availability percentage is greater than 90','true','NULL','NULL','',''),('Network-Loss','long',3,-1,5,'max','KPI',7.0000,5.0000,'Network losses above threshold','Network losses within threshold.','true','NULL','NULL','',''),('ratethreshold','long',2,-1,5,'min','NMSManagement',30.0000,50.0000,'Threshold exceeded : ','Threshold reset : ','false','NULL','NULL','',''),('resourceusage','long',1,-1,5,'max','NMSManagement',80.0000,70.0000,'Threshold exceeded : ','Threshold reset : ','true','NULL','NULL','','');
/*!40000 ALTER TABLE `ThresholdObjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TopoObject`
--

DROP TABLE IF EXISTS `TopoObject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TopoObject` (
  `MOID` bigint(20) NOT NULL,
  `BASEMIBS` varchar(100) DEFAULT NULL,
  `COMMUNITY` varchar(100) DEFAULT NULL,
  `CONTEXTNAME` varchar(100) DEFAULT NULL,
  `IPADDRESS` varchar(100) DEFAULT NULL,
  `ISDHCP` bit(1) DEFAULT NULL,
  `ISINTERFACE` bit(1) DEFAULT NULL,
  `ISNETWORK` bit(1) DEFAULT NULL,
  `ISNODE` bit(1) DEFAULT NULL,
  `ISSNMP` bit(1) DEFAULT NULL,
  `NETMASK` varchar(100) DEFAULT NULL,
  `SNMPPORT` int(11) DEFAULT NULL,
  `USERNAME` varchar(100) DEFAULT NULL,
  `VERSION` varchar(100) DEFAULT NULL,
  `WRITECOMMUNITY` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK608221B9A4FF80BC` (`MOID`),
  CONSTRAINT `FK608221B9A4FF80BC` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TopoObject`
--

LOCK TABLES `TopoObject` WRITE;
/*!40000 ALTER TABLE `TopoObject` DISABLE KEYS */;
INSERT INTO `TopoObject` VALUES (1,'','public','','172.24.14.0','\0','\0','','\0','\0','255.255.255.0',161,'',' ','public'),(2,'','public','','172.24.14.1','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(3,'','public','','172.24.14.1','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(4,'','public','','172.24.14.9','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(5,'','public','','172.24.14.9','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(6,'','public','','172.24.14.3','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(7,'','public','','172.24.14.3','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(8,'','public','','172.24.14.11','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(9,'','public','','172.24.14.11','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(10,'','public','','172.24.14.19','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(11,'','public','','172.24.14.19','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(12,'','public','','172.24.14.14','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(13,'','public','','172.24.14.14','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(14,'','public','','172.24.14.7','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(15,'','public','','172.24.14.7','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(16,'','public','','172.24.14.4','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(17,'','public','','172.24.14.4','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(18,'','public','','172.24.14.15','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(19,'','public','','172.24.14.15','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(20,'','public','','172.24.14.8','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(21,'','public','','172.24.14.8','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(22,'','public','','172.24.14.16','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(23,'','public','','172.24.14.16','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(24,'','public','','172.24.14.10','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(25,'','public','','172.24.14.10','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(26,'','public','','172.24.14.20','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(27,'','public','','172.24.14.20','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(28,'','public','','172.24.14.39','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(29,'','public','','172.24.14.39','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(30,'','public','','172.24.14.28','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(31,'','public','','172.24.14.28','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(32,'','public','','172.24.14.27','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(33,'','public','','172.24.14.27','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(34,'','public','','172.24.14.23','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(35,'','public','','172.24.14.23','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(36,'','public','','172.24.14.34','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(37,'','public','','172.24.14.34','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(38,'','public','','172.24.14.38','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(39,'','public','','172.24.14.38','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(40,'','public','','172.24.14.30','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(41,'','public','','172.24.14.30','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(42,'','public','','192.168.220.0','\0','\0','','\0','\0','255.255.252.0',161,'',' ','public'),(43,'RFC1213-MIB','public','','172.24.14.117','\0','','\0','\0','','255.255.255.0',161,'','v2','public'),(44,'RFC-1213','public','','192.168.220.202','\0','','\0','\0','','255.255.252.0',161,'',' ','public'),(45,'RFC1213-MIB','public','','172.24.14.117','\0','\0','\0','','','255.255.255.0',161,'','v2','public'),(46,'','public','','172.24.14.40','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(47,'','public','','172.24.14.40','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(48,'','public','','172.24.14.44','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(49,'','public','','172.24.14.44','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(50,'','public','','172.24.14.42','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(51,'','public','','172.24.14.42','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(52,'','public','','172.24.14.53','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(53,'','public','','172.24.14.53','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(54,'','public','','172.24.14.51','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(55,'','public','','172.24.14.51','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(56,'','public','','172.24.14.41','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(57,'','public','','172.24.14.41','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(58,'','public','','172.24.14.56','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(59,'','public','','172.24.14.56','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(60,'','public','','172.24.14.49','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(61,'','public','','172.24.14.49','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(62,'','public','','172.24.14.50','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(63,'','public','','172.24.14.50','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(64,'','public','','172.24.14.63','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(65,'','public','','172.24.14.63','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(66,'','public','','172.24.14.75','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(67,'','public','','172.24.14.75','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(68,'','public','','172.24.14.78','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(69,'','public','','172.24.14.78','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(70,'','public','','172.24.14.80','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(71,'','public','','172.24.14.80','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(72,'','public','','172.24.14.68','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(73,'','public','','172.24.14.68','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(74,'','public','','172.24.14.81','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(75,'','public','','172.24.14.81','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(76,'','public','','172.24.14.69','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(77,'','public','','172.24.14.69','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(78,'','public','','172.24.14.72','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(79,'','public','','172.24.14.72','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(80,'','public','','172.24.14.70','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(81,'','public','','172.24.14.70','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(82,'','public','','172.24.14.86','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(83,'','public','','172.24.14.86','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(84,'','public','','172.24.14.84','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(85,'','public','','172.24.14.84','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(86,'','public','','172.24.14.101','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(87,'','public','','172.24.14.101','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(88,'','public','','172.24.14.92','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(89,'','public','','172.24.14.92','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(90,'','public','','172.24.14.90','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(91,'','public','','172.24.14.90','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(92,'','public','','172.24.14.96','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(93,'','public','','172.24.14.96','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(94,'','public','','172.24.14.88','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(95,'','public','','172.24.14.88','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(96,'','public','','172.24.14.111','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(97,'','public','','172.24.14.111','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(98,'','public','','172.24.14.105','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(99,'','public','','172.24.14.105','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(100,'','public','','172.24.14.112','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(101,'','public','','172.24.14.112','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(102,'','public','','172.24.14.106','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(103,'','public','','172.24.14.106','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(104,'','public','','172.24.14.113','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(105,'','public','','172.24.14.113','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(106,'','public','','172.24.14.109','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(107,'','public','','172.24.14.109','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(108,'','public','','172.24.14.102','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(109,'','public','','172.24.14.102','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(110,'','public','','172.24.14.118','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(111,'','public','','172.24.14.118','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(112,'','public','','172.24.14.120','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(113,'','public','','172.24.14.120','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(114,'','public','','172.24.14.104','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(115,'','public','','172.24.14.104','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(116,'','public','','172.24.14.108','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(117,'','public','','172.24.14.108','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(118,'','public','','172.24.14.136','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(119,'','public','','172.24.14.136','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(120,'','public','','172.24.14.125','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(121,'','public','','172.24.14.125','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(122,'','public','','172.24.14.134','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(123,'','public','','172.24.14.134','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(124,'','public','','172.24.14.133','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(125,'','public','','172.24.14.133','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(126,'','public','','172.24.14.141','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(127,'','public','','172.24.14.141','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(128,'','public','','172.24.14.137','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(129,'','public','','172.24.14.137','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(130,'','public','','172.24.14.131','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(131,'','public','','172.24.14.131','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(132,'','public','','172.24.14.135','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(133,'','public','','172.24.14.135','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(134,'','public','','172.24.14.158','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(135,'','public','','172.24.14.158','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(136,'','public','','172.24.14.160','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(137,'','public','','172.24.14.160','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(138,'','public','','172.24.14.157','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(139,'','public','','172.24.14.157','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(140,'','public','','172.24.14.149','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(141,'','public','','172.24.14.149','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(142,'','public','','172.24.14.142','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(143,'','public','','172.24.14.142','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(144,'','public','','172.24.14.155','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(145,'','public','','172.24.14.155','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(146,'','public','','172.24.14.167','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(147,'','public','','172.24.14.167','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(148,'','public','','172.24.14.171','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(149,'','public','','172.24.14.171','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(150,'','public','','172.24.14.175','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(151,'','public','','172.24.14.175','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(152,'','public','','172.24.14.173','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(153,'','public','','172.24.14.173','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(154,'','public','','172.24.14.170','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(155,'','public','','172.24.14.170','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(156,'','public','','172.24.14.178','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(157,'','public','','172.24.14.178','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(158,'','public','','172.24.14.169','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(159,'','public','','172.24.14.169','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(160,'','public','','172.24.14.177','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(161,'','public','','172.24.14.177','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(162,'','public','','172.24.14.176','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(163,'','public','','172.24.14.176','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(164,'','public','','172.24.14.168','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(165,'','public','','172.24.14.168','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(166,'','public','','172.24.14.183','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(167,'','public','','172.24.14.183','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(168,'','public','','172.24.14.191','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(169,'','public','','172.24.14.191','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(170,'','public','','172.24.14.193','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(171,'','public','','172.24.14.193','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(172,'','public','','172.24.14.182','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(173,'','public','','172.24.14.182','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(174,'','public','','172.24.14.190','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(175,'','public','','172.24.14.190','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(176,'','public','','172.24.14.201','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(177,'','public','','172.24.14.201','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(178,'','public','','172.24.14.189','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(179,'','public','','172.24.14.189','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(180,'','public','','172.24.14.197','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(181,'','public','','172.24.14.197','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(182,'','public','','172.24.14.188','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(183,'','public','','172.24.14.188','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(184,'','public','','172.24.14.192','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(185,'','public','','172.24.14.192','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(186,'','public','','172.24.14.220','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(187,'','public','','172.24.14.220','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(188,'','public','','172.24.14.211','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(189,'','public','','172.24.14.211','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(190,'','public','','172.24.14.219','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(191,'','public','','172.24.14.219','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(192,'','public','','172.24.14.208','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(193,'','public','','172.24.14.208','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(194,'','public','','172.24.14.67','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(195,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(196,'','public','','172.24.14.67','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(197,'','public','','172.24.14.93','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(198,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(199,'','public','','172.24.14.93','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(200,'','public','','172.24.14.107','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(201,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(202,'','public','','172.24.14.107','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(203,'','public','','172.24.14.119','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(204,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(205,'','public','','172.24.14.119','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(206,'','public','','172.24.14.115','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(207,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(208,'','public','','172.24.14.115','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(209,'','public','','172.24.14.163','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(210,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(211,'','public','','172.24.14.163','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(212,'','public','','172.24.14.154','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(213,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(214,'','public','','172.24.14.154','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(215,'','public','','172.24.14.152','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(216,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(217,'','public','','172.24.14.152','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(218,'','public','','172.24.14.172','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(219,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(220,'','public','','172.24.14.172','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(221,'','public','','172.24.14.199','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(222,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(223,'','public','','172.24.14.199','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(224,'','public','','172.24.14.223','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(225,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(226,'','public','','172.24.14.223','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(227,'','public','','172.24.14.224','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(228,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(229,'','public','','172.24.14.224','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(230,'','public','','172.24.14.226','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(231,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(232,'','public','','172.24.14.226','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(233,'','public','','172.24.14.225','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(234,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(235,'','public','','172.24.14.225','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(236,'','public','','172.24.14.238','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(237,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(238,'','public','','172.24.14.238','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(239,'','public','','172.24.14.242','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(240,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(241,'','public','','172.24.14.242','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(242,'','public','','172.24.14.228','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(243,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(244,'','public','','172.24.14.228','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(245,'','public','','172.24.14.236','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(246,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(247,'','public','','172.24.14.236','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(248,'','public','','172.24.14.233','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(249,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(250,'','public','','172.24.14.233','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(251,'','public','','172.24.14.227','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(252,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(253,'','public','','172.24.14.227','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(254,'','public','','172.24.14.243','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(255,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(256,'','public','','172.24.14.243','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(257,'','public','','172.24.14.240','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(258,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(259,'','public','','172.24.14.240','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(260,'','public','','172.24.14.237','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(261,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(262,'','public','','172.24.14.237','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(263,'','public','','172.24.14.249','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(264,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(265,'','public','','172.24.14.249','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(266,'','public','','172.24.14.246','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(267,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(268,'','public','','172.24.14.246','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(269,'','public','','172.24.14.254','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(270,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(271,'','public','','172.24.14.254','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public'),(272,'','public','','172.24.14.252','\0','','\0','\0','\0','255.255.255.0',161,'',' ','public'),(273,'','public','','0.0.0.0','\0','\0','\0','\0','\0','255.255.255.0',161,'',' ','public'),(274,'','public','','172.24.14.252','\0','\0','\0','','\0','255.255.255.0',161,'',' ','public');
/*!40000 ALTER TABLE `TopoObject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrapDisabledMO`
--

DROP TABLE IF EXISTS `TrapDisabledMO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrapDisabledMO` (
  `Name` varchar(250) NOT NULL,
  `Type` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrapDisabledMO`
--

LOCK TABLES `TrapDisabledMO` WRITE;
/*!40000 ALTER TABLE `TrapDisabledMO` DISABLE KEYS */;
/*!40000 ALTER TABLE `TrapDisabledMO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UIDataIdVsPRId`
--

DROP TABLE IF EXISTS `UIDataIdVsPRId`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UIDataIdVsPRId` (
  `UIDATAID` bigint(20) NOT NULL,
  `PRID` bigint(20) NOT NULL,
  PRIMARY KEY (`UIDATAID`,`PRID`),
  KEY `UIDataIdVsPRId0_Indx` (`PRID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UIDataIdVsPRId`
--

LOCK TABLES `UIDataIdVsPRId` WRITE;
/*!40000 ALTER TABLE `UIDataIdVsPRId` DISABLE KEYS */;
/*!40000 ALTER TABLE `UIDataIdVsPRId` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `USERNAMES` varchar(100) NOT NULL,
  PRIMARY KEY (`USERNAMES`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERS`
--

LOCK TABLES `USERS` WRITE;
/*!40000 ALTER TABLE `USERS` DISABLE KEYS */;
/*!40000 ALTER TABLE `USERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USERTABLE`
--

DROP TABLE IF EXISTS `USERTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERTABLE` (
  `DBKEY` varchar(105) NOT NULL,
  `HOST` varchar(50) DEFAULT NULL,
  `PORT` varchar(5) DEFAULT NULL,
  `ENGINENAME` varchar(50) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `AUTHPROTOCOL` varchar(10) DEFAULT NULL,
  `AUTHPASSWORD` varchar(50) DEFAULT NULL,
  `PRIVPROTOCOL` varchar(10) DEFAULT NULL,
  `PRIVPASSWORD` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`DBKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USERTABLE`
--

LOCK TABLES `USERTABLE` WRITE;
/*!40000 ALTER TABLE `USERTABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `USERTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USMTABLE`
--

DROP TABLE IF EXISTS `USMTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USMTABLE` (
  `DBKEY` varchar(116) NOT NULL,
  `HOST` varchar(50) DEFAULT NULL,
  `PORT` varchar(5) DEFAULT NULL,
  `ENGINENAME` varchar(50) DEFAULT NULL,
  `ENGINEID` varchar(64) DEFAULT NULL,
  `USERNAME` varchar(50) DEFAULT NULL,
  `SECURITYLEVEL` varchar(5) DEFAULT NULL,
  `SECURITYNAME` varchar(50) DEFAULT NULL,
  `AUTHPROTOCOL` varchar(10) DEFAULT NULL,
  `AUTHPASSWORD` varchar(255) DEFAULT NULL,
  `AUTHKEY` varchar(255) DEFAULT NULL,
  `PRIVPROTOCOL` varchar(10) DEFAULT NULL,
  `PRIVPASSWORD` varchar(255) DEFAULT NULL,
  `PRIVKEY` varchar(255) DEFAULT NULL,
  `ENGINETIME` varchar(10) DEFAULT NULL,
  `ENGINEBOOTS` varchar(10) DEFAULT NULL,
  `LATESTRCVDENGTIME` varchar(10) DEFAULT NULL,
  `USMLOCALTIME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`DBKEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USMTABLE`
--

LOCK TABLES `USMTABLE` WRITE;
/*!40000 ALTER TABLE `USMTABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `USMTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserConfTable`
--

DROP TABLE IF EXISTS `UserConfTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserConfTable` (
  `USERNAME` varchar(50) NOT NULL,
  `OWNERNAME` varchar(50) NOT NULL,
  `STATUS` varchar(20) NOT NULL,
  `PASSWORDEXPIRYTIME` bigint(20) DEFAULT NULL,
  `USEREXPIRYTIME` bigint(20) DEFAULT NULL,
  `PASSWORDAGE` int(11) DEFAULT NULL,
  `USERAGE` int(11) DEFAULT NULL,
  `DESCRIPTIVENAME` varchar(100) NOT NULL,
  PRIMARY KEY (`USERNAME`,`OWNERNAME`),
  KEY `UserConfTable0_ndx` (`STATUS`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserConfTable`
--

LOCK TABLES `UserConfTable` WRITE;
/*!40000 ALTER TABLE `UserConfTable` DISABLE KEYS */;
INSERT INTO `UserConfTable` VALUES ('guest','NULL','enabled',0,0,0,0,'guest'),('root','NULL','enabled',0,0,0,0,'root');
/*!40000 ALTER TABLE `UserConfTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserGroupTable`
--

DROP TABLE IF EXISTS `UserGroupTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserGroupTable` (
  `USERNAME` varchar(50) NOT NULL,
  `GROUPNAME` varchar(100) NOT NULL,
  `OWNERNAME` varchar(50) NOT NULL,
  PRIMARY KEY (`USERNAME`,`GROUPNAME`,`OWNERNAME`),
  KEY `UserGroupTable0_ndx` (`USERNAME`,`GROUPNAME`),
  KEY `UserGroupTable1_ndx` (`USERNAME`),
  KEY `UserGroupTable2_ndx` (`GROUPNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserGroupTable`
--

LOCK TABLES `UserGroupTable` WRITE;
/*!40000 ALTER TABLE `UserGroupTable` DISABLE KEYS */;
INSERT INTO `UserGroupTable` VALUES ('guest','Users','NULL'),('root','Admin','NULL'),('root','Users','NULL');
/*!40000 ALTER TABLE `UserGroupTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserInputData`
--

DROP TABLE IF EXISTS `UserInputData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserInputData` (
  `UIDATAID` bigint(20) NOT NULL,
  `TEMPLATENAME` varchar(100) NOT NULL,
  `USERINPUTKEY` varchar(80) NOT NULL,
  `USERINPUTVALUE` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`UIDATAID`,`TEMPLATENAME`,`USERINPUTKEY`),
  KEY `UserInputData0_Indx` (`UIDATAID`),
  KEY `UserInputData1_Indx` (`TEMPLATENAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserInputData`
--

LOCK TABLES `UserInputData` WRITE;
/*!40000 ALTER TABLE `UserInputData` DISABLE KEYS */;
/*!40000 ALTER TABLE `UserInputData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserPasswordTable`
--

DROP TABLE IF EXISTS `UserPasswordTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserPasswordTable` (
  `USERNAME` varchar(50) NOT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `OWNERNAME` varchar(50) NOT NULL,
  PRIMARY KEY (`USERNAME`,`OWNERNAME`),
  KEY `UserPasswordTable0_ndx` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserPasswordTable`
--

LOCK TABLES `UserPasswordTable` WRITE;
/*!40000 ALTER TABLE `UserPasswordTable` DISABLE KEYS */;
INSERT INTO `UserPasswordTable` VALUES ('guest','e8c89O1f','NULL'),('root','b96u1ae9J','NULL');
/*!40000 ALTER TABLE `UserPasswordTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VACMACCESSTABLE`
--

DROP TABLE IF EXISTS `VACMACCESSTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VACMACCESSTABLE` (
  `GROUPNAME` varchar(255) NOT NULL,
  `CONTEXTPREFIX` varchar(65) NOT NULL,
  `SECURITYMODEL` varchar(10) NOT NULL,
  `SECURITYLEVEL` varchar(5) NOT NULL,
  `CONTEXTMATCH` varchar(5) DEFAULT NULL,
  `READVIEWNAME` varchar(65) DEFAULT NULL,
  `WRITEVIEWNAME` varchar(65) DEFAULT NULL,
  `NOTIFYVIEWNAME` varchar(65) DEFAULT NULL,
  `STORAGETYPE` varchar(5) DEFAULT NULL,
  `ROWSTATUS` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`GROUPNAME`,`CONTEXTPREFIX`,`SECURITYMODEL`,`SECURITYLEVEL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VACMACCESSTABLE`
--

LOCK TABLES `VACMACCESSTABLE` WRITE;
/*!40000 ALTER TABLE `VACMACCESSTABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `VACMACCESSTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VACMCONTEXTTABLE`
--

DROP TABLE IF EXISTS `VACMCONTEXTTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VACMCONTEXTTABLE` (
  `CONTEXTNAME` varchar(65) NOT NULL,
  PRIMARY KEY (`CONTEXTNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VACMCONTEXTTABLE`
--

LOCK TABLES `VACMCONTEXTTABLE` WRITE;
/*!40000 ALTER TABLE `VACMCONTEXTTABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `VACMCONTEXTTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VACMSECURITYTOGROUPTABLE`
--

DROP TABLE IF EXISTS `VACMSECURITYTOGROUPTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VACMSECURITYTOGROUPTABLE` (
  `SECURITYMODEL` varchar(10) NOT NULL,
  `SECURITYNAME` varchar(65) NOT NULL,
  `GROUPNAME` varchar(35) DEFAULT NULL,
  `STORAGETYPE` varchar(5) DEFAULT NULL,
  `GROUPSTATUS` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`SECURITYMODEL`,`SECURITYNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VACMSECURITYTOGROUPTABLE`
--

LOCK TABLES `VACMSECURITYTOGROUPTABLE` WRITE;
/*!40000 ALTER TABLE `VACMSECURITYTOGROUPTABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `VACMSECURITYTOGROUPTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VACMVIEWTREEFAMILYTABLE`
--

DROP TABLE IF EXISTS `VACMVIEWTREEFAMILYTABLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VACMVIEWTREEFAMILYTABLE` (
  `VIEWNAME` varchar(65) DEFAULT NULL,
  `SUBTREE` varchar(255) DEFAULT NULL,
  `MASK` varchar(35) DEFAULT NULL,
  `TYPE` varchar(5) DEFAULT NULL,
  `STORAGETYPE` varchar(5) DEFAULT NULL,
  `STATUS` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VACMVIEWTREEFAMILYTABLE`
--

LOCK TABLES `VACMVIEWTREEFAMILYTABLE` WRITE;
/*!40000 ALTER TABLE `VACMVIEWTREEFAMILYTABLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `VACMVIEWTREEFAMILYTABLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VarBindLog`
--

DROP TABLE IF EXISTS `VarBindLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VarBindLog` (
  `NOTIINDEX` int(11) NOT NULL,
  `VARINDEX` int(11) NOT NULL,
  `VARTYPE` int(11) DEFAULT NULL,
  `VARVALUE` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`NOTIINDEX`,`VARINDEX`),
  KEY `VarBindLog0_ndx` (`NOTIINDEX`),
  KEY `VarBindLog1_ndx` (`VARINDEX`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VarBindLog`
--

LOCK TABLES `VarBindLog` WRITE;
/*!40000 ALTER TABLE `VarBindLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `VarBindLog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ViewPropertiesTable`
--

DROP TABLE IF EXISTS `ViewPropertiesTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ViewPropertiesTable` (
  `VIEWNAME` varchar(50) NOT NULL,
  `PROPERTYNAME` varchar(50) NOT NULL,
  `PROPERTYVALUE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`VIEWNAME`,`PROPERTYNAME`),
  KEY `ViewPropertiesTable0_ndx` (`VIEWNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ViewPropertiesTable`
--

LOCK TABLES `ViewPropertiesTable` WRITE;
/*!40000 ALTER TABLE `ViewPropertiesTable` DISABLE KEYS */;
INSERT INTO `ViewPropertiesTable` VALUES ('Admin Alerts Scope','entity','*add*'),('Admin Alerts Scope','source','*add*'),('printerView','netmask','255.255.255.0'),('printerView','network','192.168.1.0'),('printerView','type','printer');
/*!40000 ALTER TABLE `ViewPropertiesTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ViewToOperationsTable`
--

DROP TABLE IF EXISTS `ViewToOperationsTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ViewToOperationsTable` (
  `VIEWNAME` varchar(50) NOT NULL,
  `OPERATIONSNAME` varchar(50) NOT NULL,
  `OPERATIONSTYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`VIEWNAME`,`OPERATIONSNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ViewToOperationsTable`
--

LOCK TABLES `ViewToOperationsTable` WRITE;
/*!40000 ALTER TABLE `ViewToOperationsTable` DISABLE KEYS */;
INSERT INTO `ViewToOperationsTable` VALUES ('admin','Administrative Operation',1),('Admin Alerts Scope','Alerts',1),('authView','Alerts',1),('authView','Event Filters And Parsers',1),('authView','Events',1),('authView','Map Editing Operations',1),('authView','Poll Filters',1),('authView','Polling Object',1),('authView','Polling Units',1),('authView','Threshold Object',1),('authView','Trap Parsers And Filters',1),('configView','Configuration',1),('default Admin View','Provisioning',1),('operatorView','Alert User Operations',1),('operatorView','Event User Operations',1),('operatorView','Map Editing Operations',0),('operatorView','Runtime Administration',0),('operatorView','Security Administration',0),('operatorView','System Administration',0),('operatorView','Topology',0),('policy','Policy',1),('topo','Topology',1),('userConfig','User Administration',1);
/*!40000 ALTER TABLE `ViewToOperationsTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ViewsToGroupTable`
--

DROP TABLE IF EXISTS `ViewsToGroupTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ViewsToGroupTable` (
  `VIEWNAME` varchar(50) NOT NULL,
  `GROUPNAME` varchar(50) NOT NULL,
  `OWNERNAME` varchar(50) NOT NULL,
  PRIMARY KEY (`VIEWNAME`,`GROUPNAME`,`OWNERNAME`),
  KEY `ViewsToGroupTable0_ndx` (`VIEWNAME`,`GROUPNAME`),
  KEY `ViewsToGroupTable1_ndx` (`GROUPNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ViewsToGroupTable`
--

LOCK TABLES `ViewsToGroupTable` WRITE;
/*!40000 ALTER TABLE `ViewsToGroupTable` DISABLE KEYS */;
INSERT INTO `ViewsToGroupTable` VALUES ('admin','Admin','NULL'),('Admin Alerts Scope','Admin','NULL'),('authView','Admin','NULL'),('configView','Admin','NULL'),('default Admin View','Admin','NULL'),('policy','Admin','NULL'),('topo','Admin','NULL'),('userConfig','Admin','NULL'),('operatorView','Users','NULL'),('printerView','Users','NULL');
/*!40000 ALTER TABLE `ViewsToGroupTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WIDGET`
--

DROP TABLE IF EXISTS `WIDGET`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WIDGET` (
  `ID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `DESCRIPTION` varchar(150) DEFAULT NULL,
  `DATASOURCEID` bigint(20) NOT NULL,
  `CRITERIAID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `Widget0_Indx` (`ID`),
  KEY `Widget1_Indx` (`DATASOURCEID`),
  KEY `Widget2_Indx` (`CRITERIAID`),
  CONSTRAINT `WIDGET_CRITERIAID` FOREIGN KEY (`CRITERIAID`) REFERENCES `WIDGETCRITERIA` (`CRITERIAID`) ON DELETE CASCADE,
  CONSTRAINT `WIDGET_DATASOURCEID` FOREIGN KEY (`DATASOURCEID`) REFERENCES `WIDGETDATASOURCE` (`DATASOURCEID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WIDGET`
--

LOCK TABLES `WIDGET` WRITE;
/*!40000 ALTER TABLE `WIDGET` DISABLE KEYS */;
INSERT INTO `WIDGET` VALUES (10001,'Maps','Network Map View',11,0),(10002,'Inventory View','Inventory snap shot view',12,0),(10004,'Traffic Today','Traffic details',14,2),(10006,'Recent Alarms','Recent Alarms',15,1),(10008,'Alarms Bar Chart','Alarms Bar Chart',16,0),(10009,'Alarms Pie Chart','Alarms Pie Chart',17,11),(10011,'BE Memory Utilization','Memory Utilization Graph',19,3),(10012,'CPUUtilization','CPU Utilization Graph',20,3),(10013,'Thread Count','Thread Count Graph',21,3),(10014,'Data Collection Rate','Various Monitoring Graphs',22,4),(10015,'Event Rate','Various Monitoring Graphs',22,5),(10016,'Alert Rate','Various Monitoring Graphs',22,6),(10017,'Trap Rate','Various Monitoring Graphs',22,7),(10018,'Status Polling Rate','Various Monitoring Graphs',22,8),(10019,'Top N CPU Utilization','Top N CPU Utilization',23,2),(10020,'Top N Memory Utilization','Top N Memory',23,9),(10021,'Top N Tx Traffic','Top N Tx Traffic',14,2),(10022,'Top N Rx Traffic','Top N Rx Traffic',14,10);
/*!40000 ALTER TABLE `WIDGET` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WIDGETASSOCIATION`
--

DROP TABLE IF EXISTS `WIDGETASSOCIATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WIDGETASSOCIATION` (
  `ASSOCIATIONID` bigint(20) NOT NULL,
  `DASHBOARDID` bigint(20) NOT NULL,
  `WIDGETID` bigint(20) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `CRITERIA` varchar(250) DEFAULT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ASSOCIATIONID`),
  KEY `WidgetAssociation0_Indx` (`ASSOCIATIONID`),
  KEY `WIDGETASSOCIATION_DASHBOARDID` (`DASHBOARDID`),
  KEY `WIDGETASSOCIATION_WIDGETID` (`WIDGETID`),
  CONSTRAINT `WIDGETASSOCIATION_DASHBOARDID` FOREIGN KEY (`DASHBOARDID`) REFERENCES `DASHBOARD` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `WIDGETASSOCIATION_WIDGETID` FOREIGN KEY (`WIDGETID`) REFERENCES `WIDGET` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WIDGETASSOCIATION`
--

LOCK TABLES `WIDGETASSOCIATION` WRITE;
/*!40000 ALTER TABLE `WIDGETASSOCIATION` DISABLE KEYS */;
INSERT INTO `WIDGETASSOCIATION` VALUES (1001,1002,10009,'PieChart View',NULL,'PieChart view'),(1002,1002,10011,'BE Memory Utilization',NULL,'JVM Memory Graph'),(1003,1002,10002,'Inventory view',NULL,'Inventory view'),(1004,1002,10015,'Event Rate',NULL,'Event Rate'),(1005,1002,10006,'Recent Alarms',NULL,'Top N Recent Alarms'),(1006,1002,10020,'Top N Memory Utilization',NULL,'Top N Memory Utilization'),(1007,1002,10021,'Top N Traffic',NULL,'Top N Traffic'),(1008,1002,10013,'BE Thread Count',NULL,'JVM Thread Count Graph'),(1009,1002,10014,'Data Collection Rate',NULL,'DataCollection Rate'),(1010,1001,10009,'Alarm Pie chart',NULL,'PieChart View'),(1011,1001,10006,'Recent Alarms',NULL,'Top N Recent Alarms'),(1012,1001,10011,'BE Memory Utilization',NULL,'JVM Memory Graph'),(1013,1001,10002,'Inventory view',NULL,'Inventory View'),(1014,1001,10019,'Top N CPU Utilization',NULL,'Top N CPU Utilization'),(1015,1001,10020,'Top N Memory Utilization',NULL,'Top N Memory Utilization'),(1016,1001,10021,'Top N Tx Traffic',NULL,'Top N Tx Traffic'),(1017,1001,10022,'Top N Rx Traffic',NULL,'Top N Rx Traffic');
/*!40000 ALTER TABLE `WIDGETASSOCIATION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WIDGETCRITERIA`
--

DROP TABLE IF EXISTS `WIDGETCRITERIA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WIDGETCRITERIA` (
  `CRITERIAID` bigint(20) NOT NULL,
  `CRITERIA` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`CRITERIAID`),
  KEY `WidgetCriteria0_Indx` (`CRITERIAID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WIDGETCRITERIA`
--

LOCK TABLES `WIDGETCRITERIA` WRITE;
/*!40000 ALTER TABLE `WIDGETCRITERIA` DISABLE KEYS */;
INSERT INTO `WIDGETCRITERIA` VALUES (0,'{\"refreshInterval\":\"0\"}'),(1,'{\"severity\":\"!5\",\"count\":\"6\",\"refreshInterval\":\"10\",\"entity\":\"!IF-*\"}'),(2,'{\"count\":\"10\",\"period\":\"Today\",\"refreshInterval\":\"0\"}'),(3,'{\"refreshInterval\":\"10\",\"graphType\":\"Line\"}'),(4,'{\"graphType\":\"Area\",\"refreshInterval\":\"30\",\"dataToShow\":\"DataCollectionRate\"}'),(5,'{\"graphType\":\"Area\",\"refreshInterval\":\"30\",\"dataToShow\":\"EventRate\"}'),(6,'{\"graphType\":\"Area\",\"refreshInterval\":\"30\",\"dataToShow\":\"AlertRate\"}'),(7,'{\"graphType\":\"Area\",\"refreshInterval\":\"30\",\"dataToShow\":\"TrapRate\"}'),(8,'{\"graphType\":\"Area\",\"refreshInterval\":\"30\",\"dataToShow\":\"StatusPollingRate\"}'),(9,'{\"count\":\"10\",\"moType\":\"Device\",\"period\":\"Today\",\"pdName\":\"MemoryUtilization\",\"refreshInterval\":\"0\"}'),(10,'{\"count\":\"15\",\"period\":\"Today\",\"pdName\":\"Device_INTERFACE_in_octets\",\"refreshInterval\":\"0\"}'),(11,'{\"refreshInterval\":\"10\"}');
/*!40000 ALTER TABLE `WIDGETCRITERIA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WIDGETDATASOURCE`
--

DROP TABLE IF EXISTS `WIDGETDATASOURCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WIDGETDATASOURCE` (
  `DATASOURCEID` bigint(20) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `CLASSNAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`DATASOURCEID`),
  KEY `WidgetDataSource0_Indx` (`DATASOURCEID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WIDGETDATASOURCE`
--

LOCK TABLES `WIDGETDATASOURCE` WRITE;
/*!40000 ALTER TABLE `WIDGETDATASOURCE` DISABLE KEYS */;
INSERT INTO `WIDGETDATASOURCE` VALUES (11,'MapView','com.adventnet.nms.webclient.home.widgets.MapTreeViewDS','Top N Devices'),(12,'Inventory Datasource','com.adventnet.nms.webclient.home.widgets.InventoryViewDS','Inventory view'),(14,'Reports','com.adventnet.nms.webclient.home.widgets.TrafficDS','Top N Interfaces'),(15,'Recent Alarms','com.adventnet.nms.webclient.home.widgets.RecentAlarmsDS','Recent Alarms'),(16,'BarChart','com.adventnet.nms.webclient.home.widgets.BarChartDS','Alarms Pie chart'),(17,'PieChart','com.adventnet.nms.webclient.home.widgets.PieChartDS','Alarms Pie chart'),(19,'Memory Utilization Chart','com.adventnet.nms.webclient.home.widgets.MemoryUtilizationGraphDS','Memory Utilization Chart'),(20,'CPU Utilization Chart','com.adventnet.nms.webclient.home.widgets.CPUUtilizationGraphDS','CPU Utilization Chart'),(21,'Thread Count Chart','com.adventnet.nms.webclient.home.widgets.ThreadCountGraphDS','Thread Count Chart'),(22,'Graphs','com.adventnet.nms.webclient.home.widgets.MonitoringGraphsDS','Graphs'),(23,'TopNCPUUtilization','com.adventnet.nms.webclient.home.widgets.CPUReportDS','Top N CPU Utilization');
/*!40000 ALTER TABLE `WIDGETDATASOURCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WIDGETLEVEL`
--

DROP TABLE IF EXISTS `WIDGETLEVEL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WIDGETLEVEL` (
  `ASSOCIATIONID` bigint(20) NOT NULL,
  `COLUMNID` int(11) DEFAULT NULL,
  `WIDGETLEVEL` int(11) DEFAULT NULL,
  PRIMARY KEY (`ASSOCIATIONID`),
  KEY `WidgetLevel0_Indx` (`ASSOCIATIONID`),
  CONSTRAINT `WIDGETLEVEL_ID` FOREIGN KEY (`ASSOCIATIONID`) REFERENCES `WIDGETASSOCIATION` (`ASSOCIATIONID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WIDGETLEVEL`
--

LOCK TABLES `WIDGETLEVEL` WRITE;
/*!40000 ALTER TABLE `WIDGETLEVEL` DISABLE KEYS */;
INSERT INTO `WIDGETLEVEL` VALUES (1001,1,1),(1002,2,1),(1003,3,1),(1004,1,2),(1005,2,2),(1006,3,2),(1007,1,3),(1008,2,3),(1009,3,3),(1010,1,1),(1011,2,1),(1012,1,2),(1013,2,2),(1014,1,3),(1015,2,3),(1016,1,4),(1017,2,4);
/*!40000 ALTER TABLE `WIDGETLEVEL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `XMLNode_Port`
--

DROP TABLE IF EXISTS `XMLNode_Port`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `XMLNode_Port` (
  `MOID` bigint(20) NOT NULL,
  `PORTIP` varchar(15) DEFAULT NULL,
  `PORTOPSTATUS` varchar(10) DEFAULT NULL,
  `REGIONCODE` varchar(10) DEFAULT NULL,
  `ADMINSTATUS` varchar(20) DEFAULT NULL,
  `PURPOSE` varchar(300) DEFAULT NULL,
  `MACADDRESS` varchar(50) DEFAULT NULL,
  `MTU` int(11) DEFAULT NULL,
  `PORTGROUP` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`MOID`),
  KEY `FK86CDDA873A6CAB62` (`MOID`),
  CONSTRAINT `FK86CDDA873A6CAB62` FOREIGN KEY (`MOID`) REFERENCES `ManagedObject` (`MOID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `XMLNode_Port`
--

LOCK TABLES `XMLNode_Port` WRITE;
/*!40000 ALTER TABLE `XMLNode_Port` DISABLE KEYS */;
/*!40000 ALTER TABLE `XMLNode_Port` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smsprofiles`
--

DROP TABLE IF EXISTS `smsprofiles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `smsprofiles` (
  `profilename` varchar(20) NOT NULL,
  `recipients` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smsprofiles`
--

LOCK TABLES `smsprofiles` WRITE;
/*!40000 ALTER TABLE `smsprofiles` DISABLE KEYS */;
/*!40000 ALTER TABLE `smsprofiles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smsserver_out`
--

DROP TABLE IF EXISTS `smsserver_out`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `smsserver_out` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(1) NOT NULL DEFAULT 'O',
  `recipient` varchar(16) NOT NULL,
  `text` varchar(1000) NOT NULL,
  `wap_url` varchar(100) DEFAULT NULL,
  `wap_expiry_date` varchar(30) DEFAULT NULL,
  `wap_signal` varchar(1) DEFAULT NULL,
  `create_date` varchar(30) NOT NULL,
  `originator` varchar(16) NOT NULL DEFAULT ' ',
  `encoding` varchar(1) NOT NULL DEFAULT '7',
  `status_report` int(1) NOT NULL DEFAULT '0',
  `flash_sms` int(1) NOT NULL DEFAULT '0',
  `src_port` int(6) NOT NULL DEFAULT '-1',
  `dst_port` int(6) NOT NULL DEFAULT '-1',
  `sent_date` varchar(30) DEFAULT NULL,
  `ref_no` varchar(64) DEFAULT NULL,
  `priority` int(5) NOT NULL DEFAULT '0',
  `status` varchar(1) NOT NULL DEFAULT 'U',
  `errors` int(2) NOT NULL DEFAULT '0',
  `gateway_id` varchar(64) NOT NULL DEFAULT '*',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smsserver_out`
--

LOCK TABLES `smsserver_out` WRITE;
/*!40000 ALTER TABLE `smsserver_out` DISABLE KEYS */;
/*!40000 ALTER TABLE `smsserver_out` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-10 17:17:22

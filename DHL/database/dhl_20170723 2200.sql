-- MySQL Administrator dump 1.4
-- Author: Prashant Padmanabhan 
-- ------------------------------------------------------
-- Server version	5.0.19-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema dhl
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ dhl;
USE dhl;

--
-- Table structure for table `dhl`.`order_details`
--

DROP TABLE IF EXISTS `order_details`;
CREATE TABLE `order_details` (
  `order_no` varchar(30) NOT NULL default '',
  `other_details` varchar(1000) NOT NULL default '',
  `order_status` varchar(30) NOT NULL default '',
  PRIMARY KEY  (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dhl`.`order_details`
--

/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` (`order_no`,`other_details`,`order_status`) VALUES 
 ('DHL1111','Mrs Pushkala, coimbatore, India','NOT STARTED'),
 ('DHL1234','Mr Prashant, goregaon, mumbai, India','DELIVERED'),
 ('DHL5678','Mrs Lavanya, coimbatore, India','SHIPPED');
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;


--
-- Table structure for table `dhl`.`prash_response`
--

DROP TABLE IF EXISTS `prash_response`;
CREATE TABLE `prash_response` (
  `query_text` varchar(1000) NOT NULL default '',
  `response_text` varchar(1000) NOT NULL default '',
  PRIMARY KEY  (`query_text`)
) ENGINE=MEMORY DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dhl`.`prash_response`
--

/*!40000 ALTER TABLE `prash_response` DISABLE KEYS */;
INSERT INTO `prash_response` (`query_text`,`response_text`) VALUES 
 ('good morning afternoon evening night','Good Day, How May I help you '),
 ('hello hi','Hello, I am Prash, You can place,cancel,amend,enquire about your order. How May I help you '),
 ('Bye see you thank you thanks','Thank you for contacting me, Please feel free to contact me again'),
 ('price pay payment cost','0 to 5 kg - Rs10, 5 to 20 kg - Rs20, 20-50 kg - Rs50, 50+ kg -Rs100, Within India- Rs40, To US - Rs100, to Europe - Rs50, Within asia - rs30, to Africa - Rs75. Weight price + location + 10% taxes will be your total amount'),
 ('enquire ask question doubt status order no number','Please give your enquire Order number in the format -  Order no: <your number> (It will start with DHL)'),
 ('Default','I am sorry, I am unable to process the request, Please contact our customer care number 9819045436 or email us at prashantp1985@gmail.com'),
 ('cancel','The cancel feature will be available in future'),
 ('place put send give courier parcel','The place order feature will be available in future'),
 ('amend edit alter','Please give your amend Order number in the format - Order no: <your number> (It will start with DHL)');
/*!40000 ALTER TABLE `prash_response` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

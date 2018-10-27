/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.21 : Database - db_movie
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_movie` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_movie`;

/*Table structure for table `cinema` */

DROP TABLE IF EXISTS `cinema`;

CREATE TABLE `cinema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `addr` varchar(256) NOT NULL,
  `city` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cinema` */

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(256) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

/*Table structure for table `movie` */

DROP TABLE IF EXISTS `movie`;

CREATE TABLE `movie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `actors` varchar(256) NOT NULL,
  `duration` double NOT NULL,
  `type` varchar(32) NOT NULL,
  `release_time` date NOT NULL,
  `score` int(2) DEFAULT NULL,
  `summary` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `movie` */

insert  into `movie`(`id`,`name`,`actors`,`duration`,`type`,`release_time`,`score`,`summary`) values (1,'追龙','刘德华，甄子丹',128,'动作','2018-10-10',NULL,NULL),(2,'大师兄','郑子丹，陈乔恩',120,'动作','2017-10-01',NULL,NULL),(3,'拆弹专家','刘德华，宋佳',120,'动作','2017-01-01',NULL,NULL),(4,'杀破狼','洪金宝，甄子丹',118,'动作','2017-01-06',NULL,NULL),(5,'捉妖记','井柏然',110,'玄幻','2017-01-01',NULL,NULL),(6,'不期而遇','张亮，张雨绮',110,'爱情','2016-09-09',NULL,NULL);

/*Table structure for table `movie_hall` */

DROP TABLE IF EXISTS `movie_hall`;

CREATE TABLE `movie_hall` (
  `id` int(11) NOT NULL,
  `name` varchar(16) NOT NULL,
  `hall_id` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `movie_hall` */

/*Table structure for table `movie_ticket` */

DROP TABLE IF EXISTS `movie_ticket`;

CREATE TABLE `movie_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seat_id` int(11) NOT NULL,
  `play_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `pay_price` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `movie_ticket` */

/*Table structure for table `play` */

DROP TABLE IF EXISTS `play`;

CREATE TABLE `play` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` int(11) NOT NULL,
  `play_time` datetime NOT NULL,
  `price` int(10) NOT NULL,
  `hall_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `play` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `sex` tinyint(4) NOT NULL DEFAULT '1',
  `tel` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

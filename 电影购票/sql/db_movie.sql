/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 8.0.12 : Database - db_movie
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `cinema` */

insert  into `cinema`(`id`,`name`,`addr`,`city`) values 
(1,'万达','深圳南山区','深圳'),
(2,'百脑汇','深圳市龙华区','深圳'),
(3,'上影','南山区深南大道10000号','深圳');

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(256) NOT NULL,
  `movie_id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`id`,`content`,`movie_id`,`timestamp`,`user_id`) values 
(1,'打斗场面很精彩，不错的电影',4,'2018-10-28 13:55:42',1);

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

insert  into `movie`(`id`,`name`,`actors`,`duration`,`type`,`release_time`,`score`,`summary`) values 
(1,'追龙','刘德华，甄子丹',128,'动作','2018-10-10',NULL,NULL),
(2,'大师兄','郑子丹，陈乔恩',120,'动作','2017-10-01',NULL,NULL),
(3,'拆弹专家','刘德华，宋佳',120,'动作','2017-01-01',NULL,NULL),
(4,'杀破狼','洪金宝，甄子丹',118,'动作','2017-01-06',NULL,NULL),
(5,'捉妖记','井柏然',110,'玄幻','2017-01-01',NULL,NULL),
(6,'不期而遇','张亮，张雨绮',110,'爱情','2016-09-09',NULL,NULL);

/*Table structure for table `movie_hall` */

DROP TABLE IF EXISTS `movie_hall`;

CREATE TABLE `movie_hall` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `cinema_id` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `movie_hall` */

insert  into `movie_hall`(`id`,`name`,`cinema_id`) values 
(1,'1号影厅',1),
(2,'2號廳',2);

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `movie_ticket` */

insert  into `movie_ticket`(`id`,`seat_id`,`play_id`,`user_id`,`pay_price`,`status`) values 
(1,25,1,1,45,0),
(2,55,1,1,45,0);

/*Table structure for table `play` */

DROP TABLE IF EXISTS `play`;

CREATE TABLE `play` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `movie_id` int(11) NOT NULL,
  `play_time` varchar(128) NOT NULL,
  `price` int(10) NOT NULL,
  `hall_id` int(11) NOT NULL,
  `cinema_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `play` */

insert  into `play`(`id`,`movie_id`,`play_time`,`price`,`hall_id`,`cinema_id`) values 
(1,4,'[15:00-17:00]',45,1,1),
(2,2,'[18:00-20:00]',40,1,1),
(3,4,'[18:00-20:00]',55,2,2);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  `sex` tinyint(4) NOT NULL DEFAULT '1',
  `tel` varchar(11) NOT NULL,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`sex`,`tel`,`balance`) values 
(1,'test','123456',0,'13800138000',173),
(2,'test2','1111',0,'13551355111',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

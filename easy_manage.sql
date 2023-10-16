-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: mybatis
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Current Database: `mybatis`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `mybatis` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mybatis`;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_time` datetime DEFAULT NULL COMMENT '日志操作时间',
  `description` varchar(300) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (13,'2023-06-30 19:23:59','14 号部门的名称被修改为 法务部'),(14,'2023-06-30 19:24:25','删除了 1 个员工'),(15,'2023-07-02 11:40:53','删除了 1 个员工'),(16,'2023-07-03 19:40:48','名为 杰克 的员工信息发生了变更'),(17,'2023-07-03 21:14:12','执行了删除部门的操作，此次删除的是 9 号部门'),(18,'2023-07-04 15:48:22','名为 汤姆 的员工信息发生了变更'),(19,'2023-07-04 15:49:02','名为 薇薇 的员工信息发生了变更'),(20,'2023-07-04 15:49:15','名为 约翰 的员工信息发生了变更');
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_dept`
--

DROP TABLE IF EXISTS `tb_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_dept` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(30) NOT NULL COMMENT '部门名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_dept`
--

LOCK TABLES `tb_dept` WRITE;
/*!40000 ALTER TABLE `tb_dept` DISABLE KEYS */;
INSERT INTO `tb_dept` VALUES (1,'人力资源部','2023-05-30 09:39:02','2023-05-30 09:39:02'),(2,'财务部','2023-05-31 22:05:38','2023-05-31 22:05:38'),(3,'销售部','2023-05-31 22:05:38','2023-05-31 22:05:38'),(4,'后勤部','2023-05-31 22:05:38','2023-05-31 22:05:38'),(5,'市场部','2023-05-31 22:05:38','2023-05-31 22:05:38'),(6,'生产部','2023-06-30 19:24:07','2023-06-30 19:24:07'),(7,'采购部','2023-07-01 14:53:53','2023-07-01 14:53:53');
/*!40000 ALTER TABLE `tb_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_emp`
--

DROP TABLE IF EXISTS `tb_emp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_emp` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT '123456' COMMENT '密码',
  `name` varchar(30) NOT NULL COMMENT '姓名',
  `gender` tinyint(3) unsigned NOT NULL COMMENT '性别, 说明: 1 男, 2 女',
  `image` varchar(300) DEFAULT NULL COMMENT '图像',
  `job` tinyint(3) unsigned DEFAULT NULL COMMENT '职位, 说明: 1 班主任,2 讲师, 3 学工主管, 4 教研主管',
  `entrydate` date DEFAULT NULL COMMENT '入职时间',
  `dept_id` int(10) unsigned DEFAULT NULL COMMENT '部门ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_emp`
--

LOCK TABLES `tb_emp` WRITE;
/*!40000 ALTER TABLE `tb_emp` DISABLE KEYS */;
INSERT INTO `tb_emp` VALUES (1,'zhangwuji','123456','张无忌',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/6bb6bc35-88d0-4c33-8be8-b0d81308a627.jpeg',2,'2015-01-01',1,'2023-05-30 09:39:02','2023-06-01 20:54:11'),(2,'weiyixiao','123456','韦一笑',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/9a923ade-693b-48e7-b966-0dfa00eb3ed9.jpeg',2,'2007-01-01',2,'2023-05-30 09:39:02','2023-06-01 20:54:20'),(3,'zhouzhiruo','123456','周芷若',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/91e39bc8-9481-42bb-a5d3-9b1d11c70acc.jpg',1,'2014-11-09',3,'2023-05-30 09:39:02','2023-06-01 20:59:55'),(4,'muChen','123456','牧尘',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/91c19a4a-c936-4769-9661-657eee87e282.jpeg',3,'2004-01-01',4,'2022-10-27 16:35:33','2023-06-30 18:49:58'),(5,'bobo','123456','波波',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/0edc4e29-bdad-4234-9426-11d87622fcf8.jpeg',1,'2023-06-08',5,'2023-06-01 11:53:46','2023-06-30 19:12:08'),(6,'wangYu','123456','忘语',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/a2255c78-5367-4c9a-ae59-fce8b05febd7.jpeg',4,'2007-06-06',6,'2023-06-01 13:46:35','2023-06-01 20:53:13'),(7,'cloudRipple','123456','云漪',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2023-06-30',7,'2023-07-01 09:30:34','2023-07-01 09:30:34'),(73,'Jack','123456','杰克',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/81b50f4f-ba04-4903-b41a-5370ad575bd2.jpg',1,'2018-05-07',1,'2023-07-01 15:50:26','2023-07-03 19:40:48'),(74,'Tom','123456','汤姆',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/59de151a-29e9-4ee4-9210-772f52e2e0fe.jpeg',4,'2018-05-26',2,'2023-07-01 15:50:26','2023-07-04 15:48:22'),(75,'Robin','123456','罗宾·',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2018-07-21',2,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(76,'John','123456','约翰',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/58b3887f-275f-4676-b229-fae8d1bb0826.jpeg',1,'2018-07-21',3,'2023-07-01 15:50:26','2023-07-04 15:49:14'),(77,'Rose','123456','罗丝',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',4,'2018-07-21',4,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(78,'Alice','123456','爱丽丝',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2019-04-21',5,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(79,'Angela','123456','安吉拉',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',4,'2019-04-21',1,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(80,'Bonnie','123456','邦妮',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2019-06-05',2,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(81,'Cathy','123456','凯茜',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2019-06-05',3,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(82,'Sam','123456','山姆',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2019-11-08',5,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(83,'Porter','123456','波特',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',2,'2019-11-08',5,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(84,'Bridges','123456','布里吉斯',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2019-11-08',5,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(85,'carver','123456','卡文',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2020-05-02',6,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(86,'brett','123456','布雷特',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',3,'2020-05-02',6,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(87,'Dany','123456','丹妮',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2020-08-20',4,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(88,'Emily','123456','艾米丽',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2020-08-20',7,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(89,'Hellen','123456','海伦',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',2,'2020-08-20',2,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(90,'Gina','123456','吉娜',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2021-02-12',3,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(91,'Isabel','123456','伊莎贝尔',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2021-02-12',7,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(92,'Alan','123456','艾伦',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',2,'2021-09-08',3,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(93,'Barry','123456','巴里',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',3,'2021-09-08',4,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(94,'Eden','123456','伊登',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2022-01-17',5,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(95,'Felix','123456','菲力克斯',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',4,'2022-06-21',6,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(96,'Wade','123456','维德',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/391f78b6-8167-488b-9c67-2a46e6e9865d.jpg',1,'2022-06-21',7,'2023-07-01 15:50:26','2023-07-01 15:50:26'),(97,'weiwei','123456','薇薇',2,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/34840766-5364-4496-9d02-5a93bc3dedc5.jpeg',2,'2023-07-01',1,'2023-07-01 23:57:04','2023-07-04 15:49:02'),(98,'qwert','123456','汤姆',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/81a8a79f-5972-4c79-9c7d-34bc00b53edd.jpeg',3,'2023-07-04',1,'2023-07-05 08:42:18','2023-07-05 08:42:18'),(99,'smile','123456','微笑',1,'https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/4228446e-8248-4c95-a594-20c30248b498.jpeg',2,'2023-07-04',1,'2023-07-05 08:43:13','2023-07-05 08:43:13');
/*!40000 ALTER TABLE `tb_emp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nickname` varchar(255) DEFAULT '' COMMENT '昵称',
  `username` varchar(255) NOT NULL COMMENT '账号',
  `password` varchar(255) NOT NULL DEFAULT '123456' COMMENT '密码',
  `role` varchar(50) DEFAULT '' COMMENT '用户角色',
  `gender` char(4) DEFAULT '' COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `phone` varchar(50) DEFAULT '' COMMENT '电话',
  `address` varchar(255) DEFAULT '' COMMENT '地址',
  `email` varchar(255) DEFAULT '' COMMENT '邮箱',
  `avatar` varchar(300) DEFAULT '' COMMENT '头像',
  `register_date` datetime DEFAULT NULL COMMENT '注册日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'云漪','cloudRipple','083e878c26d20f43ae1c01a5d66006dd','管理员','女',100,'13211846651','苏州市','1493440094@qq.com','https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/cd411fd2-d032-451e-939e-256c4624fdfe.jpeg','2023-07-03 16:08:48'),(7,'历飞雨','weiwei','997f41ac165e7df393d8f5a2ad019cbb','学生','男',22,'12345678910','南京市','18642874@qq.com','https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/9513981d-94be-47ef-be85-d0d16e65f8f2.jpeg','2023-07-03 22:00:53'),(8,'肯','king','e10adc3949ba59abbe56e057f20f883e','普通用户','男',NULL,'','','','https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/0b52f8e9-5d81-4a62-b4bf-b248aff63d3d.jpeg','2023-07-03 22:30:34'),(9,'天南第一散修','韩立','e10adc3949ba59abbe56e057f20f883e','普通用户','男',33,'18163786314','乱星海','hanli3678@qq.com','https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/30407378-60e4-4015-995a-5f54ead132ea.jpeg','2023-07-04 15:39:41'),(10,'杰可','jack','e10adc3949ba59abbe56e057f20f883e','学生','男',NULL,'','','','https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/93e0c623-6e30-4105-bd2c-06d27bdd3f10.jpeg','2023-07-05 08:40:20'),(11,'ll','lll','e10adc3949ba59abbe56e057f20f883e','老师','男',NULL,'','','','https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/29b0a15d-7285-4d1a-990f-f3a03c610510.jpeg','2023-07-05 09:18:09'),(13,'韩老魔','hanli','e10adc3949ba59abbe56e057f20f883e','学生','男',NULL,'','','','https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/afc2bb01-16a8-44f4-8942-c445ed07eb19.jpeg','2023-07-05 20:10:47'),(15,'腾薇薇','wei','e10adc3949ba59abbe56e057f20f883e','老师','女',NULL,'','','','https://bobo-tlias.oss-cn-hangzhou.aliyuncs.com/389ddae6-4abf-4b71-9ad3-ab12f5479421.jpeg','2023-07-05 20:41:49'),(16,'','ripple','e10adc3949ba59abbe56e057f20f883e','','',NULL,'','','','','2023-10-13 23:32:13');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-16 21:49:27

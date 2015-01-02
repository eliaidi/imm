/*
SQLyog v10.2 
MySQL - 5.5.15 : Database - imm
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`imm` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `imm`;

/*Table structure for table `t_answer_info` */

DROP TABLE IF EXISTS `t_answer_info`;

CREATE TABLE `t_answer_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(256) DEFAULT NULL COMMENT '回答者ip',
  `name` varchar(64) DEFAULT NULL COMMENT '回答者姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问卷结果';

/*Data for the table `t_answer_info` */

/*Table structure for table `t_answer_rec` */

DROP TABLE IF EXISTS `t_answer_rec`;

CREATE TABLE `t_answer_rec` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer_id` int(11) NOT NULL COMMENT '所属回答者',
  `subject_id` int(11) NOT NULL COMMENT '题目ID',
  `subject_option_id` int(11) DEFAULT NULL COMMENT '选项ID',
  `question_id` int(11) NOT NULL COMMENT '所属问卷ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_answer_rec` */

/*Table structure for table `t_dictionary_info` */

DROP TABLE IF EXISTS `t_dictionary_info`;

CREATE TABLE `t_dictionary_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dictionary_key` varchar(64) NOT NULL COMMENT '数据字典键值',
  `dictionary_value` varchar(256) DEFAULT NULL COMMENT '数据字典数值',
  `dictionary_name` varchar(256) DEFAULT NULL COMMENT '数据字典名称',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='字典表';

/*Data for the table `t_dictionary_info` */

insert  into `t_dictionary_info`(`id`,`dictionary_key`,`dictionary_value`,`dictionary_name`,`description`) values (15,'CUSTOMER_TYPE','1','潜在客户','客户类型'),(16,'CUSTOMER_TYPE','2','休眠客户','客户类型'),(17,'CUSTOMER_TYPE','3','已购车客户','客户类型'),(19,'CUSTOMER_TYPE','4','总部下发客户','客户类型'),(20,'CONUTRY','1','中国','国家'),(21,'PROVINCE','1','北京','省份'),(22,'PROVINCE','2','上海','省份'),(23,'CITY','1','长沙','城市'),(24,'CITY','2','南京','城市'),(25,'CITY_CODE','1','10010','区号'),(26,'CITY_CODE','2','10086','区号'),(27,'SEX','1','男',NULL),(28,'SEX','2','女',NULL),(29,'STAGE','1','预定','销售阶段'),(30,'STAGE','2','已交订金','销售阶段'),(31,'STAGE','3','已交全额','销售阶段'),(32,'STAGE','4','已提车','销售阶段'),(33,'STAGE','5','回馈服务体验','销售阶段'),(34,'STAGE','6','交易结束','销售阶段'),(35,'MARKET_TYPE','1','个人销售机会','交易类型'),(36,'MARKET_TYPE','2','推荐销售机会','交易类型'),(37,'MARKET_TYPE','3','总部下发销售机会','交易类型'),(38,'ISLOCK','1','是','是否锁定'),(39,'ISLOCK','0','否','是否锁定');

/*Table structure for table `t_menu_info` */

DROP TABLE IF EXISTS `t_menu_info`;

CREATE TABLE `t_menu_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL COMMENT '菜单名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='菜单表';

/*Data for the table `t_menu_info` */

insert  into `t_menu_info`(`id`,`name`) values (1,'基本管理'),(5,'系统管理');

/*Table structure for table `t_question_info` */

DROP TABLE IF EXISTS `t_question_info`;

CREATE TABLE `t_question_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL COMMENT '问卷名称',
  `create_time` varchar(64) NOT NULL,
  `create_by` varchar(64) DEFAULT NULL,
  `update_time` varchar(64) DEFAULT NULL,
  `update_by` varchar(64) DEFAULT NULL,
  `is_delete` int(1) DEFAULT '0' COMMENT '0-未删除 ，1-归档',
  `start_date` varchar(64) DEFAULT NULL COMMENT '开启时间',
  `end_date` varchar(64) DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问卷表';

/*Data for the table `t_question_info` */

/*Table structure for table `t_role_info` */

DROP TABLE IF EXISTS `t_role_info`;

CREATE TABLE `t_role_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色表';

/*Data for the table `t_role_info` */

insert  into `t_role_info`(`id`,`name`) values (1,'管理员');

/*Table structure for table `t_role_submenu_rec` */

DROP TABLE IF EXISTS `t_role_submenu_rec`;

CREATE TABLE `t_role_submenu_rec` (
  `submenu_id` int(11) NOT NULL COMMENT '子菜单ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`submenu_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与子菜单关联表';

/*Data for the table `t_role_submenu_rec` */

insert  into `t_role_submenu_rec`(`submenu_id`,`role_id`) values (1,1),(17,1),(18,1);

/*Table structure for table `t_rss_info` */

DROP TABLE IF EXISTS `t_rss_info`;

CREATE TABLE `t_rss_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(256) DEFAULT NULL COMMENT 'RSS标题',
  `url` varchar(2000) DEFAULT NULL COMMENT 'RSS的URL地址',
  `order_num` int(10) DEFAULT NULL COMMENT '排序',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `user_id` int(11) DEFAULT NULL COMMENT '创建该RSS的管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_rss_info` */

insert  into `t_rss_info`(`id`,`title`,`url`,`order_num`,`description`,`user_id`) values (1,'网易新闻头条','http://news.163.com/special/00011K6L/rss_newstop.xml',1,'12',1),(2,'百度新闻','http://news.baidu.com/n?cmd=1&class=civilnews&tn=rss',2,'',1),(3,'腾讯QQRSS','http://cq.qq.com/CQxinwen/09cqnews/rss_newb.xml',3,'啊啊',1),(4,'新华网-国际新闻','http://www.xinhuanet.com/world/news_world.xml',5,'嗖嗖嗖',1),(5,'新浪','http://rss.sina.com.cn/news/marquee/ddt.xml',4,'11',1);

/*Table structure for table `t_subject_info` */

DROP TABLE IF EXISTS `t_subject_info`;

CREATE TABLE `t_subject_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `description` text COMMENT '题目描述',
  `type` varchar(64) NOT NULL COMMENT '类型(checkbox,radio,input,textarea)',
  `question_id` int(11) NOT NULL COMMENT '所属问卷',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题目表';

/*Data for the table `t_subject_info` */

/*Table structure for table `t_subject_option_info` */

DROP TABLE IF EXISTS `t_subject_option_info`;

CREATE TABLE `t_subject_option_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text COMMENT '选项描述',
  `num` int(11) DEFAULT NULL COMMENT '序号',
  `subject_id` int(11) NOT NULL COMMENT '所属题目',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='题目选项表';

/*Data for the table `t_subject_option_info` */

/*Table structure for table `t_submenu_info` */

DROP TABLE IF EXISTS `t_submenu_info`;

CREATE TABLE `t_submenu_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL COMMENT '子菜单名',
  `menu_id` int(11) NOT NULL COMMENT '父菜单ID',
  `path` varchar(256) DEFAULT NULL COMMENT '子菜单关联的页面',
  `order_num` int(11) DEFAULT NULL,
  `code` varchar(256) DEFAULT NULL COMMENT '编码-客户端用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='子菜单表';

/*Data for the table `t_submenu_info` */

insert  into `t_submenu_info`(`id`,`name`,`menu_id`,`path`,`order_num`,`code`) values (1,'RSS管理',1,'/main/goRssManager.do',1,NULL),(17,'登录账户管理',5,'/main/goUserManager.do',1,NULL),(18,'角色管理',5,'/main/goRoleManager.do',2,NULL);

/*Table structure for table `t_user_info` */

DROP TABLE IF EXISTS `t_user_info`;

CREATE TABLE `t_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(256) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL COMMENT '实际姓名',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `create_time` varchar(64) DEFAULT NULL COMMENT '创建时间',
  `update_time` varchar(64) DEFAULT NULL COMMENT '修改时间',
  `isLock` int(1) DEFAULT '0' COMMENT '0-不锁定 1-锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `t_user_info` */

insert  into `t_user_info`(`id`,`username`,`password`,`name`,`role_id`,`create_time`,`update_time`,`isLock`) values (1,'admin','admin123','程旭媛',1,NULL,'2014-04-11',0),(3,'admin123','admin123','adasd',2,'2014-04-18',NULL,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

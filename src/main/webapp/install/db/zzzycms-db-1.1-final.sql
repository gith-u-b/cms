
DROP TABLE IF EXISTS `cms_channel`;
CREATE TABLE `cms_channel` (
  `channel_id` int(11) NOT NULL DEFAULT '0',
  `channel_name` varchar(255) DEFAULT NULL,
  `template_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `channel_path` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `has_content` int(11) DEFAULT '0',
  `is_display` int(11) DEFAULT '1',
  `is_static` int(255) DEFAULT NULL,
  `is_state` varchar(255) DEFAULT NULL,
  `page` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `title_img` varchar(255) DEFAULT NULL,
  `content_img` varchar(255) DEFAULT NULL,
  `title_img_height` int(11) DEFAULT NULL,
  `title_img_width` int(11) DEFAULT NULL,
  `content_img_width` int(11) DEFAULT NULL,
  `content_img_height` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `keywords` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`channel_id`),
  UNIQUE KEY `channel_name` (`channel_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `cms_click`;
CREATE TABLE `cms_click` (
  `click_id` int(11) NOT NULL AUTO_INCREMENT,
  `content_id` int(11) DEFAULT NULL,
  `click_time` datetime DEFAULT NULL,
  PRIMARY KEY (`click_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `cms_comment`;
CREATE TABLE `cms_comment` (
  `com_id` int(11) NOT NULL DEFAULT '0',
  `user_name` varchar(255) DEFAULT NULL,
  `reply_user_id` int(11) DEFAULT NULL,
  `content_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `reply_time` datetime DEFAULT NULL,
  `is_recommend` int(11) DEFAULT NULL,
  `is_checked` int(11) DEFAULT NULL,
  `com_content` varchar(255) DEFAULT NULL,
  `reply_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`com_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `cms_content`;
CREATE TABLE `cms_content` (
  `content_id` int(11) NOT NULL DEFAULT '0',
  `title` varchar(255) DEFAULT NULL,
  `short_title` varchar(255) DEFAULT NULL,
  `summary` varchar(255) DEFAULT NULL,
  `channel_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `has_title_img` int(11) DEFAULT NULL,
  `is_recommend` int(11) DEFAULT NULL,
  `is_static` int(11) DEFAULT '0',
  `is_commend` int(11) DEFAULT '0',
  `is_display` int(11) DEFAULT '1',
  `status` int(255) DEFAULT '0',
  `author` varchar(255) DEFAULT NULL,
  `origin` varchar(255) DEFAULT NULL,
  `origin_url` varchar(255) DEFAULT NULL,
  `content` text,
  `replease_time` datetime DEFAULT NULL,
  `title_color` varchar(255) DEFAULT NULL,
  `is_bold` int(11) DEFAULT NULL,
  `title_img` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `template_id` int(11) DEFAULT NULL,
  `content_img` varchar(255) DEFAULT NULL,
  `is_created` int(11) DEFAULT NULL,
  PRIMARY KEY (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `cms_content_check`;
CREATE TABLE `cms_content_check` (
  `content_check_id` int(11) NOT NULL DEFAULT '0',
  `content_id` int(11) DEFAULT NULL,
  `check_option` varchar(255) DEFAULT NULL,
  `is_rejected` int(11) DEFAULT NULL,
  `reviewer` varchar(255) DEFAULT NULL,
  `check_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`content_check_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;








DROP TABLE IF EXISTS `cms_content_type`;
CREATE TABLE `cms_content_type` (
  `type_id` int(11) NOT NULL DEFAULT '0',
  `type_name` varchar(255) DEFAULT NULL,
  `img_width` int(11) DEFAULT NULL,
  `img_height` int(11) DEFAULT NULL,
  `has_image` int(11) DEFAULT NULL,
  `is_disabled` int(11) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `cms_duplicate`;
CREATE TABLE `cms_duplicate` (
  `duplicate_id` int(11) NOT NULL DEFAULT '0',
  `duplicate_url` varchar(255) DEFAULT NULL,
  `duplicate_table` varchar(255) DEFAULT NULL,
  `duplicate_user_name` varchar(255) DEFAULT NULL,
  `duplicate_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`duplicate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `cms_exception_log`;
CREATE TABLE `cms_exception_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_time` datetime DEFAULT NULL,
  `log_level` varchar(255) DEFAULT NULL,
  `log_site` varchar(255) DEFAULT NULL,
  `log_msg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `cms_file`;
CREATE TABLE `cms_file` (
  `file_id` int(11) NOT NULL DEFAULT '0',
  `file_name` varchar(255) DEFAULT NULL,
  `file_url` varchar(255) DEFAULT NULL,
  `file_upload_time` date DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `file_is_disable` int(11) DEFAULT NULL,
  `content_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `cms_group`;
CREATE TABLE `cms_group` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `group_name` (`group_name`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `cms_login_failure`;
CREATE TABLE `cms_login_failure` (
  `login_id` int(11) NOT NULL,
  `login_username` varchar(255) DEFAULT NULL,
  `login_password` varchar(255) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `cms_login_success`;
CREATE TABLE `cms_login_success` (
  `login_id` int(11) NOT NULL,
  `login_user` int(11) DEFAULT NULL,
  `login_datetime` datetime DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






DROP TABLE IF EXISTS `cms_operate_log`;
CREATE TABLE `cms_operate_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  `log_url` varchar(255) DEFAULT NULL,
  `log_methode` varchar(255) DEFAULT NULL,
  `log_msg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7829 DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `cms_power`;
CREATE TABLE `cms_power` (
  `power_id` int(11) NOT NULL DEFAULT '0',
  `power_name` varchar(255) DEFAULT NULL,
  `power_url` varchar(255) DEFAULT NULL,
  `power_pid` int(11) DEFAULT NULL,
  `power_state` varchar(255) DEFAULT NULL,
  `power_iconcls` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`power_id`),
  UNIQUE KEY `power_name` (`power_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `cms_role`;
CREATE TABLE `cms_role` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `role_name` varchar(255) DEFAULT NULL,
  `role_describe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `cms_role_power`;
CREATE TABLE `cms_role_power` (
  `role_id` int(11) DEFAULT NULL,
  `power_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `cms_template`;
CREATE TABLE `cms_template` (
  `template_id` int(11) NOT NULL DEFAULT '0',
  `template_name` varchar(255) DEFAULT NULL,
  `template_type_id` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  `title_img_width` int(11) DEFAULT NULL,
  `title_img_height` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `has_content` int(11) DEFAULT NULL,
  `is_disabled` int(11) DEFAULT NULL,
  `template_path` varchar(255) DEFAULT NULL,
  `template_pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`template_id`),
  UNIQUE KEY `template_name` (`template_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




DROP TABLE IF EXISTS `cms_template_type`;
CREATE TABLE `cms_template_type` (
  `type_id` int(11) NOT NULL DEFAULT '0',
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_name` (`type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





DROP TABLE IF EXISTS `cms_time_task`;
CREATE TABLE `cms_time_task` (
  `task_id` int(11) NOT NULL,
  `task_type` int(11) DEFAULT NULL,
  `task_name` varchar(255) DEFAULT NULL,
  `task_cycle` varchar(255) DEFAULT NULL,
  `task_cron` varchar(255) DEFAULT NULL,
  `task_state` int(11) DEFAULT NULL,
  `task_descripe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`task_id`),
  UNIQUE KEY `task_name` (`task_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;








DROP TABLE IF EXISTS `cms_time_task_type`;
CREATE TABLE `cms_time_task_type` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  `type_trigger` varchar(255) DEFAULT NULL,
  `type_job` varchar(255) DEFAULT NULL,
  `type_descripe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`),
  UNIQUE KEY `type_name` (`type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






DROP TABLE IF EXISTS `cms_user`;
CREATE TABLE `cms_user` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `register_ip` varchar(255) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `last_login_ip` varchar(255) DEFAULT NULL,
  `login_count` int(11) DEFAULT '0',
  `rank` int(11) DEFAULT NULL,
  `upload_total` int(11) DEFAULT NULL,
  `upload_size` int(11) DEFAULT NULL,
  `upload_time` datetime DEFAULT NULL,
  `is_admin` int(11) DEFAULT NULL,
  `is_disabled` int(11) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE
IF
	EXISTS `common_role`;
CREATE TABLE `common_role` (
	`id` INT ( 12 ) NOT NULL auto_increment,
	`role` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '角色',
	`did` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '部门id',
	`identity` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '身份',
	`power` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '权限',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '角色表';

DROP TABLE
IF
	EXISTS `common_user`;
CREATE TABLE `common_user` (
	`id` INT ( 12 ) NOT NULL auto_increment,
	`openid` VARCHAR ( 120 ) NOT NULL DEFAULT '' COMMENT 'openid',
	`name` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '姓名',
	`icon` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '头像',
	`grade` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '年级',
	`phone` BIGINT ( 12 ) NOT NULL DEFAULT 0 COMMENT '手机号',
	`password` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '密码',
	`rid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '角色id',
	`addtime` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '添加时间',
	`status` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '状态',
	`type` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '类型',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '用户表';

DROP TABLE
IF
	EXISTS `common_student`;
CREATE TABLE `common_student` (
	`id` INT ( 12 ) NOT NULL auto_increment,
	`name` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '姓名',
	`studentId` VARCHAR  (36) NOT NULL DEFAULT  '' COMMENT '学号',
	`icon` VARCHAR ( 100 ) NOT NULL DEFAULT '' COMMENT '头像',
	`phone` BIGINT ( 12 ) NOT NULL DEFAULT 0 COMMENT '手机号',
	`birthday` INT ( 8 ) NOT NULL DEFAULT 0 COMMENT '生日',
	`sex` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '性别',
	`pid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '父亲id',
	`tid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '老师id',
	`question` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '问题数',
	`answer` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '答案数',
	`addtime` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '添加时间',
	`status` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '状态',
	`grad` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '年级',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '学生表';

DROP TABLE
IF
	EXISTS `common_class`;
CREATE TABLE `common_class` (
	`id` INT ( 12 ) NOT NULL auto_increment,
	`schoolName` BIGINT ( 12 ) NOT NULL DEFAULT 0 COMMENT '学校名',
	`grade` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '年级',
	`className` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '班级名',
	`tOrSid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '老师(学生)id',
	`type` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '类型',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '班级表';

DROP TABLE
IF
	EXISTS `common_division`;
CREATE TABLE `common_division` (
	`id` INT ( 12 ) NOT NULL auto_increment,
	`name` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '部门名',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '部门表';

DROP TABLE
IF
	EXISTS `common_category`;
CREATE TABLE `common_category` (
	`id` INT ( 12 ) NOT NULL auto_increment,
	`name` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '分类',
	`type` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '类型',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '分类(身份题目等等)表';

DROP TABLE
IF
 EXISTS `common_question`;
CREATE TABLE `common_question` (
 `id` INT ( 12 ) NOT NULL auto_increment,
 `cid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '分类',
 `name` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '题目名',
 `question` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '题目',
 `detail` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '题目详情',
 `uid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '用户',
 `sid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '学生id',
 `grad` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '年级',
 `star` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '收藏数',
 `answer` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '答案数',
 `addtime` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '添加时间',
 `status` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '状态',
 PRIMARY KEY ( `id` ),
 UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '题目表';

DROP TABLE
IF
	EXISTS `common_repository`;
CREATE TABLE `common_repository` (
	`id` INT ( 12 ) NOT NULL auto_increment,
	`cid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '分类',
	`name` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '知识库名',
	`detail` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '知识库详情',
	`uid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '用户',
	`star` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '收藏数',
	`addtime` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '添加时间',
	`status` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '状态',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '知识库表';

DROP TABLE
IF
	EXISTS `common_answer`;
CREATE TABLE `common_answer` (
	`id` INT ( 12 ) NOT NULL auto_increment,
	`qid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '题目',
	`name` VARCHAR ( 36 ) NOT NULL DEFAULT '' COMMENT '回答',
	`answer` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '答案',
	`detail` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '答案详情',
	`uid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '用户',
	`addtime` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '添加时间',
	`like` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '点赞数',
	`unlike` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '差评数',
	`comment` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '评论数',
	`status` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '状态',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '答案表';

DROP TABLE
IF
	EXISTS `common_like`;
CREATE TABLE `common_like` (
	`id` INT ( 12 ) NOT NULL auto_increment,
	`uid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '用户',
	`tid` INT NOT NULL DEFAULT 0 COMMENT '目标id',
	`type` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '类型',
	`addtime` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '添加时间',
	PRIMARY KEY ( `id` ),
	UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '点赞收藏表';

DROP TABLE
IF
	EXISTS `common_comment`;
CREATE TABLE `common_comment` (
	`id` INT ( 12 ) NOT NULL auto_increment,
	`uid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '用户',
	`tid` INT NOT NULL DEFAULT 0 COMMENT '目标id',
	`comment` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '评论内容',
	`like` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '点赞数',
	`addtime` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '添加时间',
	PRIMARY KEY ( `id` ),
UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '评论表';

DROP TABLE
IF
	EXISTS `common_report`;
CREATE TABLE `common_report` (
 `id` INT ( 12 ) NOT NULL auto_increment,
 `title` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '题目',
 `description` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '题目简介',
 `detail` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '题目详情',
 `score` VARCHAR ( 50 ) NOT NULL DEFAULT '' COMMENT '评分',
 `judge` VARCHAR ( 500 ) NOT NULL DEFAULT '' COMMENT '教师评价',
 `stage` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '时间段',
 `uid` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '用户',
 `stuid` INT ( 12 ) NOT NULL DEFAULT  0 COMMENT '学生id',
 `addtime` INT ( 12 ) NOT NULL DEFAULT 0 COMMENT '添加时间',
 `type` TINYINT ( 4 ) NOT NULL DEFAULT 0 COMMENT '分类 0是家长版 1是教师版',
 PRIMARY KEY ( `id` ),
 UNIQUE KEY `id` ( `id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8 COMMENT = '报告表';
/*创建用户表*/
CREATE TABLE If Not Exists `sys_user` (
	`id`  varchar(36) NOT NULL ,
	`uname`  varchar(255) NOT NULL COMMENT '账号' ,
	`nickname`  varchar(255) NULL COMMENT '昵称' ,
	`password`  varchar(255) NOT NULL COMMENT '密码' ,
	`enable` int NULL DEFAULT 0 COMMENT '是否启用',
	`email`  varchar(255) DEFAULT '' NULL COMMENT '邮箱' ,
	`phone`  varchar(255) DEFAULT '' NULL COMMENT '手机号' ,
	`create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
	PRIMARY KEY (`id`)
);

/*插入默认数据*/
INSERT INTO sys_user(id,uname,nickname,password)
VALUE('e2460c47-dd91-478f-a096-087147ef3159','admin','超级管理员','e10adc3949ba59abbe56e057f20f883e')
ON DUPLICATE KEY UPDATE id= 'e2460c47-dd91-478f-a096-087147ef3159',uname='admin';


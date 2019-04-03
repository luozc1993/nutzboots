--创建用户表
/*key*/
CREATE TABLE If Not Exists `sys_role` (
	`id`  varchar(36) NOT NULL ,
	`name`  varchar(255) NOT NULL COMMENT '名称' ,
	`remarks`  varchar(255) DEFAULT '' NULL COMMENT '备注' ,
	`create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
	PRIMARY KEY (`id`)
);


/*创建角色表*/
CREATE TABLE If Not Exists `sys_role` (
	`id`  varchar(36) NOT NULL ,
	`name`  varchar(255) NOT NULL COMMENT '名称' ,
	`remarks`  varchar(255) DEFAULT '' NULL COMMENT '备注' ,
	`create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
	PRIMARY KEY (`id`)
);
/*插入角色默认数据*/
INSERT INTO sys_role(id,name,remarks)
VALUE('f59a4c93-c153-49a9-bfb7-5ef53dff7077','超级管理员','超级管理员')
ON DUPLICATE KEY UPDATE id= 'f59a4c93-c153-49a9-bfb7-5ef53dff7077',name='超级管理员';



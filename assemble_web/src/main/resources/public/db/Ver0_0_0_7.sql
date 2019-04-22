/*创建数据管理表*/
CREATE TABLE If Not Exists `sys_data` (
  `id`  varchar(36) NOT NULL ,
  `data_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '数据名称' ,
  `table_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '表名' ,
  `parent_id`  varchar(36) NOT NULL DEFAULT '' COMMENT '上级id' ,
  `create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`)
);


/*插入报表添加*/
INSERT INTO sys_data(id,data_name,table_name,parent_id)
VALUE('0cf13ad7-302b-4c3f-98a2-1b88a598f868','报表添加','sys_report','0')
ON DUPLICATE KEY UPDATE id= '0cf13ad7-302b-4c3f-98a2-1b88a598f868';

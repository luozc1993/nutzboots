/*创建数据管理表*/
CREATE TABLE If Not Exists `sys_data` (
  `id`  varchar(36) NOT NULL ,
  `name`  varchar(100) NOT NULL DEFAULT '' COMMENT '数据名称' ,
  `table`  varchar(100) NOT NULL DEFAULT '' COMMENT '表名' ,
  `parent_id`  varchar(36) NOT NULL DEFAULT '' COMMENT '上级id' ,
  `create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`)
);

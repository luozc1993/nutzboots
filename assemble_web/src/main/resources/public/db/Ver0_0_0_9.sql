/*创建数据管理表*/
CREATE TABLE If Not Exists `sys_report_form` (
  `id`  varchar(36) NOT NULL ,
  `pid`  varchar(36) NOT NULL ,
  `field`  varchar(100) NOT NULL DEFAULT '' COMMENT '字段标识' ,
  `title`  varchar(100) NOT NULL DEFAULT '' COMMENT '字段名称' ,
  `minWidth`  varchar(100) NOT NULL DEFAULT '100' COMMENT '最小宽度默认100' ,
  `fixed`  varchar(100) NOT NULL DEFAULT '' COMMENT '固定列 left、right' ,
  `hide`  varchar(100) NOT NULL DEFAULT '' COMMENT '是否隐藏 true false' ,
  `create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`)
);

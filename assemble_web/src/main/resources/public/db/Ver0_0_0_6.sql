/*创建数据表表*/
CREATE TABLE If Not Exists `sys_table` (
  `id`  varchar(36) NOT NULL ,
  `name`  varchar(100) NOT NULL DEFAULT '' COMMENT '表名',
  `remarks`  varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
);

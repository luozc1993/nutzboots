/*创建菜单表*/
CREATE TABLE If Not Exists `sys_menu` (
  `id`  varchar(36) NOT NULL ,
  `name`  varchar(100) NOT NULL DEFAULT '' COMMENT '菜单名称' ,
  `url`  varchar(255) NOT NULL DEFAULT '' COMMENT '地址' ,
  `parent_id`  varchar(36) NOT NULL DEFAULT '' COMMENT '上级id' ,
  `icon`  varchar(255) NULL DEFAULT '' COMMENT '菜单图标' ,
  `is_menu`  int NULL DEFAULT 0 COMMENT '是否为菜单' ,
  `sort`  int NULL DEFAULT 0 COMMENT '排序' ,
  `create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`)
);

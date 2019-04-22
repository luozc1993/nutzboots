/*创建数据管理表*/
CREATE TABLE If Not Exists `sys_report_btn` (
  `id`  varchar(36) NOT NULL ,
  `title`  varchar(100) NOT NULL ,
  `pid`  varchar(36) NOT NULL ,
  `data_id`  varchar(36) NOT NULL ,
  `type`  varchar(100) NOT NULL DEFAULT 'data' COMMENT '按钮类型  data数据 report报表 flow流程 fun存储过程' ,
  `jump`  varchar(10) NOT NULL DEFAULT '0' COMMENT '跳转方式 0弹窗 1框架标签 2浏览器标签' ,
  `create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`)
);

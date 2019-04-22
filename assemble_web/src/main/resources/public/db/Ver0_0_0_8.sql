/*创建数据管理表*/
CREATE TABLE If Not Exists `sys_report` (
  `id`  varchar(36) NOT NULL ,
  `report_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '报表名称' ,
  `table_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '表名' ,
  `create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`)
);

/*插入报表管理*/
INSERT INTO sys_report(id,report_name,table_name)
VALUE('88abe9f2-3aab-4c4a-b5aa-653249e3ac25','报表管理','sys_report')
ON DUPLICATE KEY UPDATE id= '88abe9f2-3aab-4c4a-b5aa-653249e3ac25';

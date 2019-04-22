/*创建函数管理表*/
CREATE TABLE If Not Exists `sys_function` (
  `id`  varchar(36) NOT NULL ,
  `name`  varchar(100) NOT NULL DEFAULT '' COMMENT '按钮名称' ,
  `function_name`  varchar(100) NOT NULL DEFAULT '' COMMENT '函数名称' ,
  `function_key`  varchar(1000) NOT NULL DEFAULT '' COMMENT '函数数据' ,
  `create_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `update_time`  datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  PRIMARY KEY (`id`)
);

/*插入报表管理*/
INSERT INTO sys_function(id,name,function_name,function_key)
VALUE('e09938df-bebe-4e57-8013-5d1b4bb1095c','删除系统报表','del_sys_report','id')
ON DUPLICATE KEY UPDATE id= '88abe9f2-3aab-4c4a-b5aa-653249e3ac25';
/*删除报表存储过程*/
CREATE PROCEDURE del_sys_report(IN `report_id` varchar(100))
BEGIN
	DELETE FROM sys_report WHERE id=report_id;
END
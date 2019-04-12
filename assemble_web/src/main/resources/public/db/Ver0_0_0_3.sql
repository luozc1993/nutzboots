/*创建角色用户关联表*/
CREATE TABLE If Not Exists `sys_role_user` (
  `id`  varchar(36) NOT NULL,
	`rid`  varchar(36) NOT NULL COMMENT '角色id' ,
	`uid`  varchar(36) NOT NULL COMMENT '用户id',
	PRIMARY KEY (`id`)
);

/*删除临时存储过程temporary*/
DROP PROCEDURE IF EXISTS temporary;
/*创建添加外键临时存储过程*/
create procedure temporary()
begin
    declare c_no int;
		SELECT COUNT(*) into c_no FROM `information_schema`.`KEY_COLUMN_USAGE` where constraint_name='sys_role_user_rid_fk1';
		IF c_no = 0
    THEN
        ALTER TABLE `sys_role_user` ADD CONSTRAINT `sys_role_user_rid_fk1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
    END IF;
end;
/*执行存储过程*/
call temporary();
/*删除临时存储过程temporary*/
DROP PROCEDURE IF EXISTS temporary;




/*删除临时存储过程temporary*/
DROP PROCEDURE IF EXISTS temporary;
/*创建添加外键临时存储过程*/
create procedure temporary()
begin
    declare c_no int;
		SELECT COUNT(*) into c_no FROM `information_schema`.`KEY_COLUMN_USAGE` where constraint_name='sys_role_user_uid_fk1';
		IF c_no = 0
    THEN
        ALTER TABLE `sys_role_user` ADD CONSTRAINT `sys_role_user_uid_fk1` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
    END IF;
end;
/*执行存储过程*/
call temporary();
/*删除临时存储过程temporary*/
DROP PROCEDURE IF EXISTS temporary;




/*插入超级管理员和角色关系*/
INSERT INTO sys_role_user(id,rid,uid)
VALUE('b3ce3cec-a590-4918-a400-4773718e5d30','f59a4c93-c153-49a9-bfb7-5ef53dff7077','e2460c47-dd91-478f-a096-087147ef3159')
ON DUPLICATE KEY UPDATE id= 'b3ce3cec-a590-4918-a400-4773718e5d30';
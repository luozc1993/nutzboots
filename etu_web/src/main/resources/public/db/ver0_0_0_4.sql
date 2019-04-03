--创建用户表
/*key*/
CREATE TABLE If Not Exists `sys_role_user` (
	`rid`  varchar(36) NOT NULL COMMENT '角色id' ,
	`uid`  varchar(36) NOT NULL COMMENT '用户id'
);
ALTER TABLE `sys_role_user` ADD CONSTRAINT `sys_role_user_rid_fk1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE `sys_role_user` ADD CONSTRAINT `sys_role_user_uid_fk1` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
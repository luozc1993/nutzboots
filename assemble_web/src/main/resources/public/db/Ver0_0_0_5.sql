/*创建角色菜单关联表*/
CREATE TABLE If Not Exists `sys_role_menu` (
  `id`  varchar(36) NOT NULL,
	`rid`  varchar(36) NOT NULL COMMENT '角色id' ,
	`mid`  varchar(36) NOT NULL COMMENT '菜单id',
	PRIMARY KEY (`id`)
);

/*删除临时存储过程temporary*/
DROP PROCEDURE IF EXISTS temporary;
/*创建添加外键临时存储过程*/
create procedure temporary()
begin
    declare c_no int;
		SELECT COUNT(*) into c_no FROM `information_schema`.`KEY_COLUMN_USAGE` where constraint_name='sys_role_menu_rid_fk1';
		IF c_no = 0
    THEN
        ALTER TABLE `sys_role_menu` ADD CONSTRAINT `sys_role_menu_rid_fk1` FOREIGN KEY (`rid`) REFERENCES `sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
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
		SELECT COUNT(*) into c_no FROM `information_schema`.`KEY_COLUMN_USAGE` where constraint_name='sys_role_menu_mid_fk1';
		IF c_no = 0
    THEN
        ALTER TABLE `sys_role_menu` ADD CONSTRAINT `sys_role_menu_mid_fk1` FOREIGN KEY (`mid`) REFERENCES `sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
    END IF;
end;
/*执行存储过程*/
call temporary();
/*删除临时存储过程temporary*/
DROP PROCEDURE IF EXISTS temporary;

/*插入超级管理员和系统一目录关系*/
INSERT INTO sys_role_menu(id,rid,mid)
VALUE('66598d3c-fd53-4819-b164-2b011a8e668e','f59a4c93-c153-49a9-bfb7-5ef53dff7077','5847f351-b800-44cd-8a0b-298cda2f916f')
ON DUPLICATE KEY UPDATE id= '66598d3c-fd53-4819-b164-2b011a8e668e';

/*插入超级管理员和用户管理目录关系*/
INSERT INTO sys_role_menu(id,rid,mid)
VALUE('6901e46e-c649-4588-b398-bad3f9334079','f59a4c93-c153-49a9-bfb7-5ef53dff7077','7e956aab-7827-47e3-a69d-f2c0fbfb66e8')
ON DUPLICATE KEY UPDATE id= '6901e46e-c649-4588-b398-bad3f9334079';

/*插入超级管理员和用户列表菜单关系*/
INSERT INTO sys_role_menu(id,rid,mid)
VALUE('16fe4f60-2528-4bb7-8009-1ed931c63f5a','f59a4c93-c153-49a9-bfb7-5ef53dff7077','4e7db1a3-ea23-46c8-b6b3-71267c6f9c18')
ON DUPLICATE KEY UPDATE id= '16fe4f60-2528-4bb7-8009-1ed931c63f5a';


/*插入超级管理员和角色管理菜单关系*/
INSERT INTO sys_role_menu(id,rid,mid)
VALUE('f2ad3401-7bb4-45a4-bdfa-e632804e6d59','f59a4c93-c153-49a9-bfb7-5ef53dff7077','4d470d73-5eed-47f7-805c-b5f86a27ca8d')
ON DUPLICATE KEY UPDATE id= 'f2ad3401-7bb4-45a4-bdfa-e632804e6d59';


/*插入超级管理员和菜单管理菜单关系*/
INSERT INTO sys_role_menu(id,rid,mid)
VALUE('3652d54f-81ce-4ed0-b2e0-13778975d66f','f59a4c93-c153-49a9-bfb7-5ef53dff7077','ce1e42cb-283d-4021-a277-c7d1f8eae27a')
ON DUPLICATE KEY UPDATE id= '3652d54f-81ce-4ed0-b2e0-13778975d66f';

/*插入超级管理员和系统设置关系*/
INSERT INTO sys_role_menu(id,rid,mid)
VALUE('86f3b27d-bde8-47c1-b57a-204c7923c689','f59a4c93-c153-49a9-bfb7-5ef53dff7077','edf20c53-374e-4489-bed7-592c67943c88')
ON DUPLICATE KEY UPDATE id= '86f3b27d-bde8-47c1-b57a-204c7923c689';

/*插入超级管理员和报表管理关系*/
INSERT INTO sys_role_menu(id,rid,mid)
VALUE('d7562b9c-f78a-4c4c-be5c-c1cea4b67739','f59a4c93-c153-49a9-bfb7-5ef53dff7077','00813153-0c16-47d0-8d84-ce173affc7ac')
ON DUPLICATE KEY UPDATE id= 'd7562b9c-f78a-4c4c-be5c-c1cea4b67739';
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

/*添加默认系统菜单*/
INSERT INTO sys_menu(id,name,url,parent_id,icon)
VALUE('7e956aab-7827-47e3-a69d-f2c0fbfb66e8','用户管理','','0','layui-icon-home')
ON DUPLICATE KEY UPDATE id= '7e956aab-7827-47e3-a69d-f2c0fbfb66e8';
/*添加默认用户管理*/
INSERT INTO sys_menu(id,name,url,parent_id,icon,is_menu,sort)
VALUE('4e7db1a3-ea23-46c8-b6b3-71267c6f9c18','用户管理','/page/sys/user/user_list.html','7e956aab-7827-47e3-a69d-f2c0fbfb66e8','layui-icon-home',1,1)
ON DUPLICATE KEY UPDATE id= '4e7db1a3-ea23-46c8-b6b3-71267c6f9c18';
/*添加默认角色管理*/
INSERT INTO sys_menu(id,name,url,parent_id,icon,is_menu,sort)
VALUE('4d470d73-5eed-47f7-805c-b5f86a27ca8d','角色管理','/page/sys/user/role_list.html','7e956aab-7827-47e3-a69d-f2c0fbfb66e8','layui-icon-home',1,2)
ON DUPLICATE KEY UPDATE id= '4d470d73-5eed-47f7-805c-b5f86a27ca8d';
/*添加默认菜单管理*/
INSERT INTO sys_menu(id,name,url,parent_id,icon,is_menu,sort)
VALUE('ce1e42cb-283d-4021-a277-c7d1f8eae27a','菜单管理','/page/sys/user/menu_list.html','7e956aab-7827-47e3-a69d-f2c0fbfb66e8','layui-icon-home',1,3)
ON DUPLICATE KEY UPDATE id= 'ce1e42cb-283d-4021-a277-c7d1f8eae27a';


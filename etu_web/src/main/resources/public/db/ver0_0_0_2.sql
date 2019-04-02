--插入用户表默认数据
/*key*/
INSERT INTO sys_user(id,uname,nickname,password)
VALUE('e2460c47-dd91-478f-a096-087147ef3159','admin','超级管理员','e10adc3949ba59abbe56e057f20f883e')
ON DUPLICATE KEY UPDATE id= 'e2460c47-dd91-478f-a096-087147ef3159',uname='admin';


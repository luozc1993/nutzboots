package cn.luozc.unit_app;

import cn.luozc.unit_app.sys.modules.model.*;
import cn.luozc.unit_app.utils.MD5Util;
import org.nutz.boot.NbApp;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.Modules;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * Created by wizzer on 2018/3/17.
 */
@IocBean(create = "init", depose = "depose")
public class SysMainLauncher {
    private static final Log log = Logs.get();

    @Inject
    private Dao dao;

    public static void main(String[] args) throws Exception {
        new NbApp().run();
    }

    public void init() {
        //通过POJO类创建表结构
        try {
            Daos.createTablesInPackage(dao, "cn.luozc.unit_app.sys", false);
            //通过POJO类修改表结构
            //Daos.migration(dao, "cn.wizzer.app.cms", true, false);
        } catch (Exception e) {
        }

        if(0==dao.count(SysUser.class)){
            //添加超级管理员
            SysUser sysUser = dao.insert(new SysUser("admin", MD5Util.getMD5Str("123456"),"超级管理员"));
            //添加超级管理员组
            SysRole sysRole = dao.insert(new SysRole("超级管理员组"));
            //添加用户角色关联表
            dao.insert(new SysRoleUser(sysRole.getId(),sysUser.getId()));
            //添加系统模块
            SysMenu sysMenu = dao.insert(new SysMenu("系统一", "0", "", "layui-icon-home", 0, "", ""));
            dao.insert(new SysRoleMenu(sysRole.getId(),sysMenu.getId()));
            String parentId = sysMenu.getId();
            //添加用户管理目录
            sysMenu = dao.insert(new SysMenu("用户管理", parentId, "", "layui-icon-home", 0, "", ""));
            dao.insert(new SysRoleMenu(sysRole.getId(),sysMenu.getId()));
            //添加用户列表菜单
            sysMenu = dao.insert(new SysMenu("用户列表", sysMenu.getId(), "/page/sys/user/user_list.html", "layui-icon-home", 0, "url", ""));
            dao.insert(new SysRoleMenu(sysRole.getId(),sysMenu.getId()));
            //添加角色管理菜单
            sysMenu = dao.insert(new SysMenu("角色管理", sysMenu.getParentId(), "/page/sys/user/role_list.html", "layui-icon-home", 1, "url", ""));
            dao.insert(new SysRoleMenu(sysRole.getId(),sysMenu.getId()));
            //添加菜单管理菜单
            sysMenu = dao.insert(new SysMenu("菜单管理", sysMenu.getParentId(), "/page/sys/user/menu_list.html", "layui-icon-home", 2, "url", ""));
            dao.insert(new SysRoleMenu(sysRole.getId(),sysMenu.getId()));
            //添加公司管理菜单
            sysMenu = dao.insert(new SysMenu("公司管理", sysMenu.getParentId(), "/page/sys/user/company_list.html", "layui-icon-home", 2, "url", ""));
            dao.insert(new SysRoleMenu(sysRole.getId(),sysMenu.getId()));
            //添加系统设置目录
            sysMenu = dao.insert(new SysMenu("系统设置", parentId, "", "layui-icon-home", 1, "", ""));
            dao.insert(new SysRoleMenu(sysRole.getId(),sysMenu.getId()));
            //添加报表管理菜单
            sysMenu = dao.insert(new SysMenu("报表管理", sysMenu.getId(), "/page/sys/report/report_list.html", "layui-icon-home", 0, "url", ""));
            dao.insert(new SysRoleMenu(sysRole.getId(),sysMenu.getId()));
            //添加数据管理菜单
            sysMenu = dao.insert(new SysMenu("数据管理", sysMenu.getParentId(), "/page/sys/data/data_list.html", "layui-icon-home", 1, "url", ""));
            dao.insert(new SysRoleMenu(sysRole.getId(),sysMenu.getId()));
            //添加函数管理菜单
            sysMenu = dao.insert(new SysMenu("函数管理", sysMenu.getParentId(), "/page/sys/function/function_list.html", "layui-icon-home", 1, "url", ""));
            dao.insert(new SysRoleMenu(sysRole.getId(),sysMenu.getId()));

        }

    }

    public void depose() {

    }
}

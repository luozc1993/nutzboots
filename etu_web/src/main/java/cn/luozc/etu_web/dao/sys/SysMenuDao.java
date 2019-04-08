package cn.luozc.etu_web.dao.sys;

import cn.luozc.etu_web.bean.sys.SysMenu;
import cn.luozc.etu_web.bean.sys.SysRole;
import cn.luozc.etu_web.bean.sys.SysUser;
import cn.luozc.etu_web.dao.BaseDao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class SysMenuDao extends BaseDao<SysMenu> {
    /**
     * 获取菜单关联角色关联表信息
     * @param sysMenu 菜单对象
     * @return 菜单对象
     */
    public SysMenu getUserRoleLinks(SysMenu sysMenu){
        return dao.fetchLinks(sysMenu,"sysRoleMenus");
    }

    /**
     * 删除用户关联的所有角色关联表
     * @param sysMenu 用户对象
     */
    public void delUserRoleBySysMenu(SysMenu sysMenu){
        dao.deleteLinks(sysMenu, "sysRoleMenus");

    }

    /**
     * 添加用户角色
     * @param sysMenu 用户对象
     */
    public void addMenuRole(SysMenu sysMenu){
        dao.insertLinks(sysMenu,"sysRoleMenus");
    }

}

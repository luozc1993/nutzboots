package cn.luozc.assemble_web.dao.sys;

import cn.luozc.assemble_web.bean.sys.SysMenu;
import cn.luozc.assemble_web.dao.BaseDao;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class SysMenuDao extends BaseDao<SysMenu> {
    /**
     * 获取菜单关联角色关联表信息
     * @param sysMenu 菜单对象
     * @return 菜单对象
     */
    public SysMenu getMenuRoleLinks(SysMenu sysMenu){
        return dao.fetchLinks(sysMenu,"sysRoleMenus");
    }

    /**
     * 获取菜单关联角色关联表信息
     * @param sysMenu 菜单对象
     * @return 菜单对象
     */
    public SysMenu getMenuParentLinks(SysMenu sysMenu){
        return dao.fetchLinks(sysMenu,"parent");
    }

    /**
     * 删除用户关联的所有角色关联表
     * @param sysMenu 用户对象
     */
    public void delMenuRoleBySysMenu(SysMenu sysMenu){
        dao.deleteLinks(sysMenu, "sysRoleMenus");

    }

    /**
     * 添加用户角色
     * @param sysMenu 用户对象
     */
    public void addMenuRole(SysMenu sysMenu){
        dao.insertLinks(sysMenu,"sysRoleMenus");
    }


    /**
     * 通过父id获取子id
     * @param parentId
     * @return
     */
    public List<SysMenu> getSysMenuByParentId(String parentId){
        return dao.query(SysMenu.class, Cnd.where("parent_id","=",parentId));
    }

}

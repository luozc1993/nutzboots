package cn.luozc.assemble_web.dao.sys;

import cn.luozc.assemble_web.bean.sys.SysRole;
import cn.luozc.assemble_web.dao.BaseDao;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class SysRoleDao extends BaseDao<SysRole> {

    /**
     * 获取用户关联角色关联表信息
     * @param sysRole 角色对象
     * @return 用户对象
     */
    public SysRole getUserRoleLinks(SysRole sysRole){
        return dao.fetchLinks(sysRole,"sysRoleUsers");
    }

    /**
     * 获取用户关联角色关联表信息
     * @param sysRole 角色对象
     * @return 用户对象
     */
    public SysRole getSysMenuLinks(SysRole sysRole){
        return dao.fetchLinks(sysRole,"menus",Cnd.orderBy().asc("sort"));
    }



}

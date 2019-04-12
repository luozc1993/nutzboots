package cn.luozc.assemble_web.dao.sys;

import cn.luozc.assemble_web.bean.sys.SysUser;
import cn.luozc.assemble_web.dao.BaseDao;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class SysUserDao extends BaseDao<SysUser> {


    /**
     * 通过账号获取用户信息
     * @param uname 账号
     * @return  用户对象
     */
    public SysUser getSysUserByUname(String uname){
        return dao.fetch(SysUser.class, Cnd.where("uname","=",uname));
    }

    /**
     * 通过手机号获取用户信息
     * @param phone 手机号
     * @return  用户对象
     */
    public SysUser getSysUserByPhone(String phone){
        return dao.fetch(SysUser.class, Cnd.where("phone","=",phone));
    }

    /**
     * 通过手机号获取不是当前用户id的用户信息
     * @param phone 手机号
     * @param id    用户id
     * @return  用户对象
     */
    public SysUser getSysUserByPhoneNotId(String phone,String id){
        return dao.fetch(SysUser.class,Cnd.where("phone","=",phone).and("id","<>",id));
    }

    /**
     * 获取用户关联角色
     * @param sysUser 用户信息
     * @return      用户信息
     */
    public SysUser getRolesLinks(SysUser sysUser){
        return dao.fetchLinks(sysUser,"roles");
    }

    /**
     * 获取用户关联角色关联表信息
     * @param sysUser 用户对象
     * @return 用户对象
     */
    public SysUser getUserRoleLinks(SysUser sysUser){
        return dao.fetchLinks(sysUser,"sysRoleUsers");
    }

    /**
     * 删除用户关联的所有角色关联表
     * @param sysUser 用户对象
     */
    public void delUserRoleBySysUser(SysUser sysUser){
        dao.deleteLinks(sysUser, "sysRoleUsers");

    }

    /**
     * 添加用户角色
     * @param sysUser 用户对象
     */
    public void addUserRole(SysUser sysUser){
        dao.insertLinks(sysUser,"sysRoleUsers");
    }
}

package cn.luozc.etu_web.dao.sys;

import cn.luozc.etu_web.bean.sys.SysUser;
import cn.luozc.etu_web.dao.BaseDao;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class UserDao extends BaseDao<SysUser> {



    public SysUser getSysUserByUname(String uname){
        return dao.fetch(SysUser.class, Cnd.where("uname","=",uname));
    }

    public SysUser getSysUserByPhone(String phone){
        return dao.fetch(SysUser.class, Cnd.where("phone","=",phone));
    }

    public SysUser getSysUserByPhoneNotId(String phone,String id){
        return dao.fetch(SysUser.class,Cnd.where("phone","=",phone).and("id","<>",id));
    }
}

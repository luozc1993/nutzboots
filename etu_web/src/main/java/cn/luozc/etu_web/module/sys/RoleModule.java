package cn.luozc.etu_web.module.sys;

import cn.luozc.etu_web.bean.sys.SysRole;
import cn.luozc.etu_web.bean.sys.SysUser;
import cn.luozc.etu_web.service.sys.RoleService;
import cn.luozc.etu_web.service.sys.UserService;
import cn.luozc.etu_web.util.JsonData;
import cn.luozc.etu_web.util.LayuiTableResult;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/sys/role")
@Ok("json:full")
@IocBean
public class RoleModule {

    @Inject
    private RoleService roleService;

    @At("/list")
    public LayuiTableResult list(int page,int limit,String value){
        return roleService.getList(page,limit,value);
    }

    @At("/add")
    public Object add(SysRole sysRole){
        if(StringUtils.isEmpty(sysRole.getId())){
            return roleService.add(sysRole);
        }else{
            return roleService.edit(sysRole);
        }
    }

    @At
    public JsonData del(String id){
        return roleService.del(id);
    }

}

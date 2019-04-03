package cn.luozc.etu_web.module.sys;

import cn.luozc.etu_web.bean.sys.SysUser;
import cn.luozc.etu_web.service.sys.UserService;
import cn.luozc.etu_web.util.JsonData;
import cn.luozc.etu_web.util.LayuiTableResult;
import cn.luozc.etu_web.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import java.util.Date;
import java.util.UUID;

@At("/sys/user")
@Ok("json:full")
@IocBean
public class UserModule {

    @Inject
    private UserService userService;

    @At("/list")
    public LayuiTableResult list(int page,int limit){
        return userService.getList(page,limit,"");
    }
    @At("/add")
    public Object add(SysUser sysUser){
        if(StringUtils.isEmpty(sysUser.getId())){
            return userService.add(sysUser);
        }else{
            return userService.edit(sysUser);
        }
    }



}

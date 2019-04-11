package cn.luozc.etu_web.module.sys;

import cn.luozc.etu_web.bean.sys.SysRoleUser;
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

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@At("/sys/user")
@Ok("json:full")
@IocBean
public class UserModule {

    @Inject
    private UserService userService;

    @At("/list")
    public LayuiTableResult list(int page,int limit,String value){
        return userService.getList(page,limit,value);
    }

    @At("/add")
    public Object add(SysUser sysUser,String roleId){
        JsonData jsonData = null;
        if(StringUtils.isEmpty(sysUser.getId())){
            jsonData = userService.add(sysUser,roleId);
        }else{
            jsonData = userService.edit(sysUser,roleId);
        }



        return jsonData;
    }

    @At
    public JsonData del(String id){
        return userService.del(id);
    }

    @At
    public JsonData enable(String id,int enable){
        return userService.enable(enable,id);
    }


    @At("/login")
    public JsonData login(String uname, String password, HttpSession session){
        return userService.login(uname,password,session);
    }

    @At
    @Ok(">>:/login.html")
    public void logout(HttpSession session){
        session.invalidate();
    }

}

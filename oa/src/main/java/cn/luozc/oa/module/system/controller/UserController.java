package cn.luozc.oa.module.system.controller;

import cn.luozc.oa.commom.SysLog;
import cn.luozc.oa.commom.utils.JsonData;
import cn.luozc.oa.commom.utils.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.filter.CrossOriginFilter;

import javax.servlet.http.HttpServletRequest;

@At("/system/user")
@Ok("json:full")
@IocBean
public class UserController {


    @ApiOperation(value = "登录", notes = "登录", httpMethod="GET", response=Long.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @Ok("json:full")
    @SysLog // 自定义注解,后面会介绍
    public JsonData login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sign = JwtTokenUtil.sign(username, "123");
        if(sign==null){
            return JsonData.fail("登录失败");
        }
        return JsonData.success(sign);
    }
}

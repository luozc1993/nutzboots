package cn.luozc.oa.module.system.controller;

import cn.luozc.oa.commom.SysLog;
import cn.luozc.oa.commom.utils.JsonData;
import cn.luozc.oa.commom.utils.TokenUtil;
import cn.luozc.oa.module.system.model.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CrossOriginFilter;
import org.nutz.plugins.slog.annotation.Slog;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "用户操作")
@At("/system/user")
@Ok("json:full")
@IocBean
public class UserController {


    @ApiOperation(value = "登录", notes = "登录", httpMethod="GET", response=JsonData.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @Slog(tag="新增yongh", after="用户")
    public JsonData login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sign = TokenUtil.sign(username, "123");
        if(sign==null){
            return JsonData.fail("登录失败");
        }
        return JsonData.success(new NutMap().addv("token",sign));
    }

    @ApiOperation(value = "创建用户", notes = "创建用户", httpMethod="POST", response=JsonData.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @SysLog("创建用户")
    @POST
    public JsonData createUser(SysUser user){

        return JsonData.success(user);
    }
}

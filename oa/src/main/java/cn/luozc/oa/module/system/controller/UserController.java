package cn.luozc.oa.module.system.controller;

import cn.luozc.oa.commom.SysLog;
import cn.luozc.oa.commom.utils.JsonData;
import cn.luozc.oa.commom.utils.MD5Util;
import cn.luozc.oa.commom.utils.TokenUtil;
import cn.luozc.oa.module.system.model.SysUser;
import cn.luozc.oa.module.system.model.SysUserRole;
import cn.luozc.oa.module.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nutz.ioc.loader.annotation.Inject;
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

    @Inject private SysUserService sysUserService;


    @ApiOperation(value = "登录", notes = "登录", httpMethod="GET", response=JsonData.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @Slog(tag="登录", after="登录${req.getParameter('username')}" +
            "+")
    public JsonData login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sign = TokenUtil.sign(username, "123");
        if(sign==null){
            return JsonData.fail("登录失败");
        }
        return JsonData.success(new NutMap().addv("token",sign));
    }

    /**
     *  创建用户
     * @param user      用户信息
     * @param roleId    关联的角色id
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "创建用户", httpMethod="POST", response=JsonData.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @Slog(tag="创建用户", after="uid为【${re.data.user.opUser}】的用户创建了一个新用户【${re.data.user.username}】")
    @POST
    public JsonData createUser(SysUser user,String roleId){
        //默认密码123456
        user.setPassword(MD5Util.getMD5Str("123456"));
        //添加用户
        user = sysUserService.insert(user);
        //添加角色映射
        sysUserService.insert(new SysUserRole(user.getId(),roleId));
        return JsonData.success(new NutMap().addv("user",user));
    }
}

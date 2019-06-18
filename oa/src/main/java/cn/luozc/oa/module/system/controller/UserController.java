package cn.luozc.oa.module.system.controller;

import cn.luozc.oa.commom.utils.JsonData;
import cn.luozc.oa.commom.utils.MD5Util;
import cn.luozc.oa.commom.utils.TokenUtil;
import cn.luozc.oa.module.system.model.SysUser;
import cn.luozc.oa.module.system.model.SysUserRole;
import cn.luozc.oa.module.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.nutz.dao.Cnd;
import org.nutz.dao.entity.Record;
import org.nutz.dao.util.cri.SimpleCriteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
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


    /**
     * 用户登录
     * @param username      账号
     * @param password      密码
     * @return              JsonData
     */
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod="GET", response=JsonData.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @Slog(tag="登录", after="${req.getParameter('username')}进行登录：${re.msg}" )
    public JsonData login(String username,String password){
        String sign = TokenUtil.sign(username, "123");
        if(sign==null){
            return JsonData.fail("登录失败");
        }
        return JsonData.success(new NutMap().addv("token",sign),"登录成功");
    }

    /**
     *  创建用户
     * @param user      用户信息
     * @param roleId    关联的角色id
     * @return          JsonData
     */
    @ApiOperation(value = "创建用户", notes = "创建用户", httpMethod="POST", response=JsonData.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @Slog(tag="创建用户", after="创建了一个新用户【${re.data.user.username}】")
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


    /**
     *  修改用户
     * @param user      用户信息
     * @param roleId    关联的角色id
     * @return          JsonData
     */
    @ApiOperation(value = "修改用户", notes = "修改用户", httpMethod="POST", response=JsonData.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @Slog(tag="修改用户", after="修改用户")
    @POST
    public JsonData updateUser(SysUser user,String roleId){
        //添加用户
        sysUserService.update(user,"username|nickName|sex");
        //添加角色映射
        SysUserRole sysUserRole = sysUserService.dao().fetch(SysUserRole.class, Cnd.where("uid", "=", user.getId()));
        if(sysUserRole==null){
            sysUserService.insert(new SysUserRole(user.getId(),roleId));
        }else if(roleId!=null&&!roleId.equals(sysUserRole.getRid())){
            sysUserRole.setRid(roleId);
            sysUserService.update(sysUserRole);
        }
        return JsonData.success(new NutMap().addv("user",user));
    }

    /**
     *  用户列表
     * @param username      账号
     * @param nickName      昵称
     * @param sex           性别
     * @return              JsonData
     */
    @ApiOperation(value = "用户列表", notes = "用户列表", httpMethod="GET", response=JsonData.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @Slog(tag="查看用户列表", after="查看了用户列表")
    @GET
    public Object userList(String username,String nickName,String sex){
        Cnd cnd = null;
        cnd = sysUserService.getCnd("username","like",username, cnd);
        cnd = sysUserService.getCnd("nickName","like",nickName, cnd);
        cnd = sysUserService.getCnd("sex","=",sex, cnd);
        return sysUserService.layuiListPage(1,10,cnd);
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "删除用户", httpMethod="GET", response=JsonData.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @Slog(tag="删除用户", after="删除用户")
    @GET
    public JsonData deleteUser(String userId){
        int delete = sysUserService.delete(userId);
        if(delete>0){
            sysUserService.dao().clear("sys_user_role",Cnd.where("uid","=",userId));
        }
        return JsonData.success("","删除成功");
    }

    /**
     * 修改用户状态
     * @param userId
     * @return
     */
    @ApiOperation(value = "修改用户状态", notes = "修改用户状态", httpMethod="GET", response=JsonData.class)
    @At
    @Filters(@By(type= CrossOriginFilter.class))
    @Slog(tag="修改用户状态", after="修改用户状态")
    @GET
    public JsonData updateUserState(String userId,int state){
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setState(state);
        int state1 = sysUserService.update(sysUser, "state");
        return JsonData.success(state1,"删除成功");
    }

}

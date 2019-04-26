package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.*;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_app.utils.MD5Util;
import cn.luozc.unit_framework.page.Pagination;
import org.apache.commons.lang.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@IocBean
@At("/sys/user")
@Ok("json:full")
public class SysUserController {

    @Inject private SysUserService sysUserService;
    @Inject private SysRoleUserService sysRoleUserService;


    @At
    public JsonData enable(String id){
        SysUser sysUser = sysUserService.fetch(id);
        if(sysUser.isEnable()){
            sysUser.setEnable(false);
        }else{
            sysUser.setEnable(true);
        }
        sysUserService.update(sysUser);
        return JsonData.success();

    }


    /**
     * 删除用户
     * @param id    角色id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        sysRoleUserService.clear(Cnd.where("uid","=",id));
        sysUserService.delete(id);
        return JsonData.success();
    }


    /**
     * 修改用户信息
     * @param sysUser       用户信息
     * @param roleId        角色id
     * @return              JsonData
     */
    @At
    @Aop(TransAop.READ_COMMITTED)
    public JsonData edit(SysUser sysUser,String roleId){

        //添加信息
        int update = sysUserService.update(sysUser);

        if(update==0){return JsonData.fail("添加失败"); }

        //角色关联添加
        List<SysRoleUser> rus = getSysRoleUsers(sysUser, roleId);
        sysRoleUserService.clear(Cnd.where("uid", "=", sysUser.getId()));
        sysRoleUserService.insert(rus);
        return JsonData.success(sysUser);
    }

    /**
     * 获取添加的关联角色信息
     * @param sysUser       用户信息
     * @param roleId        角色id
     * @return              JsonData
     */
    private List<SysRoleUser> getSysRoleUsers(SysUser sysUser, String roleId) {
        List<SysRoleUser> rus = new ArrayList<>();
        if (roleId != null) {
            String[] roleIdArr = roleId.split(",");
            for (String rid : roleIdArr) {
                rus.add(new SysRoleUser(rid, sysUser.getId()));
            }
        }
        return rus;
    }

    /**
     * 添加用户
     * @param sysUser
     * @param roleId
     * @return
     */
    @At
    @Aop(TransAop.READ_COMMITTED)
    public JsonData add(SysUser sysUser,String roleId){
        if(sysUserService.count(Cnd.where("uname","=",sysUser.getUname()))>0){
            return JsonData.fail("账号已被注册");
        }
        if(sysUserService.count(Cnd.where("phone","=",sysUser.getPhone()))>0){
            return JsonData.fail("手机号已被注册");
        }
        //默认密码
        sysUser.setPassword(MD5Util.getMD5Str("123456"));
        //添加信息
        sysUser = sysUserService.insert(sysUser);
        if(sysUser==null){
            return JsonData.fail("添加失败");
        }
        //角色关联添加
        List<SysRoleUser> rus = getSysRoleUsers(sysUser, roleId);
        sysRoleUserService.insert(rus);
        return JsonData.success(sysUser);
    }

    /**
     * 分页获取用户列表
     * @param page      页数
     * @param limit     每页显示数量
     * @param value     查询条件
     * @return          LayuiTableResult
     */
    @At
    public LayuiTableResult list(int page, int limit, String value){
        Criteria criteria = sysUserService.getVagueCriteria(value, "uname,nickname,phone");
        Pagination listPage = sysUserService.listPageLinks(page, limit,criteria,"roles");
        int count = sysUserService.count(criteria);
        return LayuiTableResult.result(0,"",count,listPage.getList());
    }


    /**
     * 登录
     * @param uname     账号
     * @param password  密码
     * @param session   会话
     * @return          JsonData
     */
    @At
    public JsonData login(String uname, String password, HttpSession session){
        //通过账号密码查询用户信息
        SysUser sysUser = sysUserService.fetch(Cnd.where("uname", "=", uname).and("password", "=", MD5Util.getMD5Str(password)));
        if(sysUser==null){return JsonData.fail("账号或密码错误");}
        //获取用户关联的角色信息
        sysUser = sysUserService.fetchLinks(sysUser, "roles");
        List<String> rids = new ArrayList<>();
        for (SysRole sysRole:sysUser.getRoles()) {
            rids.add(sysRole.getId());
        }
        //将信息存入session中
        session.setAttribute("user",sysUser);
        session.setAttribute("userId",sysUser.getId());
        session.setAttribute("rids", StringUtils.join(rids,","));
        return JsonData.success("登录成功");
    }


}

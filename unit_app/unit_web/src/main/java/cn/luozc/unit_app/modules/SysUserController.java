package cn.luozc.unit_app.modules;

import cn.luozc.unit_app.sys.modules.service.SysRole;
import cn.luozc.unit_app.sys.modules.service.SysUser;
import cn.luozc.unit_app.sys.modules.service.SysUserService;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
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

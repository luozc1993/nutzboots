package cn.luozc.etu_web.module.sys;

import cn.luozc.etu_web.bean.sys.SysMenu;
import cn.luozc.etu_web.bean.sys.SysRole;
import cn.luozc.etu_web.service.sys.MenuService;
import cn.luozc.etu_web.service.sys.RoleService;
import cn.luozc.etu_web.util.JsonData;
import cn.luozc.etu_web.util.LayuiTableResult;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import java.util.UUID;

@At("/sys/menu")
@Ok("json:full")
@IocBean
public class MenuModule {

    @Inject
    private MenuService menuService;

    @At("/list")
    public LayuiTableResult list(int page,int limit,String value){
        return menuService.getList(page,limit,value);
    }

    @At("/add")
    public JsonData add(SysMenu sysMenu){
        sysMenu.setId(UUID.randomUUID().toString());
        return menuService.add(sysMenu);
    }

}

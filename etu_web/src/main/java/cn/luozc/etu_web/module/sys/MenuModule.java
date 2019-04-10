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
    public JsonData add(SysMenu sysMenu,String roleId){
        if(StringUtils.isEmpty(sysMenu.getId())){
            return menuService.add(sysMenu,roleId);
        }else{
            return menuService.edit(sysMenu,roleId);
        }
    }

    @At("/del")
    public JsonData del(String id){
        if(StringUtils.isNotEmpty(id)){
            return menuService.del(id);
        }
        return JsonData.fail("删除失败");
    }

    @At("/listTree")
    public JsonData list(){
        return menuService.getList("a17d8b28-d70e-4a7a-979e-7c6912ce3ace");
    }

}

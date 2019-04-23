package cn.luozc.assemble_web.module.sys;

import cn.luozc.assemble_web.bean.sys.SysMenu;
import cn.luozc.assemble_web.service.sys.MenuService;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpSession;

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
    public JsonData list(HttpSession session){
        Object roles = session.getAttribute("rids");
        String s = roles==null? "":(String)roles;

        return menuService.getList(s);
    }

    @At
    @Ok("beetl:/${obj.type}/${obj.fid}.html")
    public JSONObject page(String mid){
        SysMenu menu = menuService.getMenuById(mid);
        JSONObject json = new JSONObject();
        json.put("mid",mid);
        json.put("type",menu.getType());
        json.put("fid",menu.getFid());
        return json;
    }

}

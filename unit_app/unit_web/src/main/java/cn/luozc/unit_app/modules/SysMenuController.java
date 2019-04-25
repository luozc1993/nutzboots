package cn.luozc.unit_app.modules;

import cn.luozc.unit_app.sys.modules.service.SysMenu;
import cn.luozc.unit_app.sys.modules.service.SysMenuService;
import cn.luozc.unit_app.sys.modules.service.SysRole;
import cn.luozc.unit_app.sys.modules.service.SysRoleService;
import cn.luozc.unit_app.utils.JsonData;
import net.sf.json.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@IocBean
@At("/sys/menu")
@Ok("json:full")
public class SysMenuController {

    @Inject private SysRoleService sysRoleService;
    @Inject private SysMenuService sysMenuService;

    @At
    public JsonData indexMenu(HttpSession session){
        //获取用户角色
        Object obj = session.getAttribute("rids");
        String rids = obj==null? "":(String)obj;
        //通过用户角色获取用户菜单
        List<SysMenu> sysMenus = new ArrayList<>();
        String[] arr = rids.split(",");
        for (String rid:arr) {
            SysRole sysRole = new SysRole();
            sysRole.setId(rid);
            sysRole = sysMenuService.fetchLinks(sysRole, "menus", Cnd.orderBy().asc("sort"));
            for (SysMenu sysMenu:sysRole.getMenus()) {
                if(!sysMenus.contains(sysMenu)){
                    sysMenus.add(sysMenu);
                }
            }
        }
        //加工角色
        List<JSONObject> result = new ArrayList<>();
        for (int i = 0; i < sysMenus.size(); i++) {
            SysMenu menu = sysMenus.get(i);
            if("0".equals(menu.getParentId())){
                String id = menu.getId();
                JSONObject json = JSONObject.fromObject(menu);
                List<JSONObject> menus = menuTree(sysMenus,id);
                if(menus.size()>0){
                    json.put("child",menus);
                }
                result.add(json);

            }
        }

        return JsonData.success(result);
    }

    /**
     * 递归菜单
     * @param menus     所有菜单
     * @param parentId  上级id
     * @return          List<JSONObject>
     */
    private List<JSONObject> menuTree(List<SysMenu> menus,String parentId){
        List<JSONObject> result = new ArrayList<>();
        for (int i = 0; i < menus.size(); i++) {
            SysMenu menu = menus.get(i);
            if(parentId.equals(menu.getParentId())){
                JSONObject json = JSONObject.fromObject(menu);
                List<JSONObject> sysMenus = menuTree(menus, menu.getId());
                if(sysMenus.size()>0){
                    json.put("child",sysMenus);
                }
                result.add(json);
            }
        }

        return result;
    }

}

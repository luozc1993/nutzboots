package cn.luozc.unit_app.modules;

import cn.luozc.unit_app.sys.modules.service.*;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_framework.page.Pagination;
import net.sf.json.JSONObject;
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
import java.util.UUID;

@IocBean
@At("/sys/menu")
@Ok("json:full")
public class SysMenuController {

    @Inject private SysRoleService sysRoleService;
    @Inject private SysMenuService sysMenuService;
    @Inject private SysRoleMenuService sysRoleMenuService;

    /**
     * 删除用户
     * @param id    角色id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        sysRoleMenuService.clear(Cnd.where("mid","=",id));
        sysMenuService.delete(id);
        return JsonData.success();
    }

    @At
    @Aop(TransAop.READ_COMMITTED)
    public JsonData edit(SysMenu sysMenu,String roleId){

        //添加信息
        int update = sysMenuService.update(sysMenu);

        if(update==0){return JsonData.fail("添加失败"); }

        List<SysRoleMenu> srms = getSysRoleMenus(sysMenu, roleId);
        sysRoleMenuService.clear(Cnd.where("mid", "=", sysMenu.getId()));
        sysRoleMenuService.insert(srms);
        return JsonData.success(sysMenu);
    }

    private List<SysRoleMenu> getSysRoleMenus(SysMenu sysMenu, String roleId) {
        List<SysRoleMenu> srms = new ArrayList<>();
        if (roleId != null) {
            String[] roleIdArr = roleId.split(",");
            for (String rid : roleIdArr) {
                srms.add(new SysRoleMenu(rid, sysMenu.getId()));
            }
        }
        return srms;
    }

    /**
     * 添加角色
     * @param sysMenu       角色信息
     * @return              JsonData
     */
    @At
    public JsonData add(SysMenu sysMenu,String roleId){
        if(StringUtils.isEmpty(sysMenu.getParentId())){
            sysMenu.setParentId("0");
        }
        String id = UUID.randomUUID().toString();
        sysMenu.setId(id);
        if(StringUtils.isNotEmpty(sysMenu.getType())&&!"url".equals(sysMenu.getType())){
            sysMenu.setUrl("/"+sysMenu.getType()+"/"+id+".html");
        }
        sysMenu = sysMenuService.insert(sysMenu);
        List<SysRoleMenu> srms = getSysRoleMenus(sysMenu, roleId);
        sysRoleMenuService.insert(srms);
        return JsonData.success();
    }

    /**
     * 获取菜单列表
     * @param page      页数
     * @param limit     每页显示数量
     * @param value     搜索框值
     * @return          LayuiTableResult
     */
    @At
    public LayuiTableResult list(int page, int limit, String value){
        Criteria criteria = sysMenuService.getVagueCriteria(value, "name");
        Pagination listPage = sysMenuService.listPageLinks(page, limit,criteria,"^(roles|parent)$");
        return LayuiTableResult.result(0,"",sysMenuService.count(criteria),listPage.getList());
    }

    /**
     * 首页菜单
     * @param session
     * @return
     */
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

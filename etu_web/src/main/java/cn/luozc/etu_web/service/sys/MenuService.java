package cn.luozc.etu_web.service.sys;

import cn.luozc.etu_web.bean.sys.SysMenu;
import cn.luozc.etu_web.bean.sys.SysRoleMenu;
import cn.luozc.etu_web.dao.sys.SysMenuDao;
import cn.luozc.etu_web.util.JsonData;
import cn.luozc.etu_web.util.LayuiTableResult;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@IocBean
public class MenuService {

    @Inject
    private SysMenuDao sysMenuDao;

    /**
     * 获取用户列表数据 模糊分页查询
     * @param pageNumber
     * @param pageSize
     * @param value
     * @return
     */
    public LayuiTableResult getList(int pageNumber,int pageSize,String value){
        List<String> list = new ArrayList<>();
        list.add("name");
        list.add("remarks");
        List<SysMenu> menus = sysMenuDao.getListSortAsc(pageNumber, pageSize, value, list);
        for (int i = 0; i < menus.size(); i++) {
            menus.get(i).setSysRoleMenus(sysMenuDao.getUserRoleLinks(menus.get(i)).getSysRoleMenus());
        }
        return LayuiTableResult.result(0,"",sysMenuDao.size(value,list),menus);
    }


    public JsonData add(SysMenu sysMenu,String roleIds){
        sysMenu.setId(UUID.randomUUID().toString());
        if(StringUtils.isEmpty(sysMenu.getParentId())){
            sysMenu.setParentId("0");
        }
        SysMenu add = sysMenuDao.add(sysMenu);
        if(add!=null){
            addUserRole(roleIds,sysMenu);
        }
        return JsonData.success();
    }

    private void addUserRole(String roleIds, SysMenu sysMenu) {
        if(StringUtils.isNotEmpty(roleIds)){
            String[] roleIdArr = roleIds.split(",");
            List<SysRoleMenu> sysRoleMenus = new ArrayList<>();
            for (String rid:roleIdArr) {
                sysRoleMenus.add(new SysRoleMenu(UUID.randomUUID().toString(),rid,sysMenu.getId()));
            }
            sysMenu = sysMenuDao.getUserRoleLinks(sysMenu);
            sysMenuDao.delUserRoleBySysMenu(sysMenu);
            sysMenu.setSysRoleMenus(sysRoleMenus);
            sysMenuDao.addMenuRole(sysMenu);

        }
    }

}

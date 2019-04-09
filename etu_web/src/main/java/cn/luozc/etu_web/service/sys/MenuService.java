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
            addMenuRole(roleIds,sysMenu);
        }
        return JsonData.success();
    }

    private void addMenuRole(String roleIds, SysMenu sysMenu) {
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


    /**
     * 删除数据
     * @param id
     * @return
     */
    public JsonData del(String id){
        SysMenu sysMenu = new SysMenu();
        sysMenu.setId(id);
        sysMenu = sysMenuDao.getUserRoleLinks(sysMenu);
        return JsonData.success(sysMenuDao.delete(sysMenu),"删除成功");
    }

    /**
     * 修改用户信息
     * @param sysMenu   用户信息
     * @return          插入的数据
     */
    public JsonData edit(SysMenu sysMenu,String roleId){
        if(sysMenu.getId().equals(sysMenu.getParentId())){
            return JsonData.fail("上级菜单不能是当前菜单");
        }

        int update = sysMenuDao.update(sysMenu);
        if(update==0){
            return JsonData.fail("修改失败");
        }
        //添加用户角色
        addMenuRole(roleId,sysMenu);
        return JsonData.success(sysMenu,"修改成功");
    }
}

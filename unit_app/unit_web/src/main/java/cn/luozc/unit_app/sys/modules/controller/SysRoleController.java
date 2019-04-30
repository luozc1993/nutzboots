package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.*;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_framework.page.Pagination;
import com.alibaba.dubbo.config.annotation.Reference;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean
@At("/sys/role")
@Ok("json:full")
public class SysRoleController {

    @Inject
    @Reference
    private SysRoleService sysRoleService;
    @Inject
    @Reference
    private SysRoleUserService sysRoleUserService;
    /**
     * 删除角色
     * @param id    角色id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        sysRoleUserService.clear(Cnd.where("rid","=",id));
        sysRoleService.delete(id);
        return JsonData.success();
    }

    /**
     * 编辑角色
     * @param sysRole       角色信息
     * @return              JsonData
     */
    @At
    public JsonData edit(SysRole sysRole){
        sysRoleService.update(sysRole);
        return JsonData.success();
    }
    /**
     * 添加角色
     * @param sysRole       角色信息
     * @return              JsonData
     */
    @At
    public JsonData add(SysRole sysRole){
        sysRoleService.insert(sysRole);
        return JsonData.success();
    }

    /**
     * 获取角色列表
     * @param page      页数
     * @param limit     每页显示数量
     * @param value     搜索框值
     * @return          LayuiTableResult
     */
    @At
    public LayuiTableResult list(int page, int limit, String value){
        Criteria criteria = sysRoleService.getVagueCriteria(value, "name");
        Pagination listPage = sysRoleService.listPage(page, limit,criteria);
        return LayuiTableResult.result(0,"",sysRoleService.count(criteria),listPage.getList());
    }






}

package cn.luozc.assemble_web.service.sys;

import cn.luozc.assemble_web.bean.sys.SysRole;
import cn.luozc.assemble_web.dao.sys.SysRoleDao;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@IocBean
public class RoleService {

    @Inject
    private SysRoleDao sysRoleDao;

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
        return LayuiTableResult.result(0,"",sysRoleDao.size(value,list),sysRoleDao.getList(pageNumber,pageSize,value,list));
    }

    /**
     * 添加用户信息
     * @param sysRole   角色信息
     * @return          插入的数据
     */
    public JsonData add(SysRole sysRole){
        sysRole.setId(UUID.randomUUID().toString());
        return JsonData.success(sysRoleDao.add(sysRole),"添加成功");
    }

    /**
     * 编辑用户信息
     * @param sysRole   角色信息
     * @return          插入的数据
     */
    public JsonData edit(SysRole sysRole){
        return JsonData.success(sysRoleDao.update(sysRole),"保存成功");
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    public JsonData del(String id){
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        sysRole = sysRoleDao.getUserRoleLinks(sysRole);
        return JsonData.success(sysRoleDao.delete(sysRole),"删除成功");
    }




}

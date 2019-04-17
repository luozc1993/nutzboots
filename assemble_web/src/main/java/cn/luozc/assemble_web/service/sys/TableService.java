package cn.luozc.assemble_web.service.sys;

import cn.luozc.assemble_web.bean.sys.SysRole;
import cn.luozc.assemble_web.bean.sys.SysTable;
import cn.luozc.assemble_web.dao.sys.SysRoleDao;
import cn.luozc.assemble_web.dao.sys.SysTableDao;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import org.nutz.dao.Chain;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@IocBean
public class TableService {

    @Inject
    private SysTableDao sysTableDao;

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
        return LayuiTableResult.result(0,"",sysTableDao.size("sys_table",value,list),sysTableDao.getList("sys_table",pageNumber,pageSize,value,list));
    }

    /**
     * 添加信息
     * @param sysTable   信息
     * @return          插入的数据
     */
    public JsonData add(SysTable sysTable){
        Chain chain = Chain.from(sysTable);
        chain.add("id", UUID.randomUUID().toString());
        sysTableDao.add("sys_table",chain);
        return JsonData.success("","添加成功");
    }

    /**
     * 编辑信息
     * @param sysTable   信息
     * @return          插入的数据
     */
    public JsonData edit(SysTable sysTable){
        Chain chain = Chain.make("name",sysTable.getName());
        chain.add("remarks",sysTable.getRemarks());
        return JsonData.success(sysTableDao.update("sys_table",chain,sysTable.getId()),"保存成功");
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    public JsonData del(String id){
        SysTable sysTable = new SysTable();
        sysTable.setId(id);
        return JsonData.success(sysTableDao.delete("sys_table",id),"删除成功");
    }




}

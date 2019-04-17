package cn.luozc.assemble_web.module.sys;

import cn.luozc.assemble_web.bean.sys.SysRole;
import cn.luozc.assemble_web.bean.sys.SysTable;
import cn.luozc.assemble_web.service.sys.RoleService;
import cn.luozc.assemble_web.service.sys.TableService;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import org.apache.commons.lang3.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/sys/table")
@Ok("json:full")
@IocBean
public class TableModule {

    @Inject
    private TableService tableService;

    @At("/list")
    public LayuiTableResult list(int page,int limit,String value){



        return tableService.getList(page,limit,value);
    }

    @At("/add")
    public Object add(SysTable sysTable){
        if(StringUtils.isEmpty(sysTable.getId())){
            return tableService.add(sysTable);
        }else{
            return tableService.edit(sysTable);
        }
    }

    @At
    public JsonData del(String id){
        return tableService.del(id);
    }

}

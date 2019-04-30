package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.SysFunction;
import cn.luozc.unit_app.sys.modules.model.SysFunction;
import cn.luozc.unit_app.sys.modules.model.SysFunctionService;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_framework.page.Pagination;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONObject;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@At("/sys/function")
@Ok("json:full")
@IocBean
public class SysFunctionController  {
    @Inject
    @Reference
    private SysFunctionService sysFunctionService;

    @At
    public JsonData call(String id, String data){
        SysFunction sysFunction = sysFunctionService.fetch(id);
        JSONObject json = JSONObject.fromObject(data);
        Record call = sysFunctionService.call(sysFunction.getFunctionName(), json);
        return JsonData.success(call);
    }


    /**
     * 删除数据
     * @param id    id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        int delete = sysFunctionService.delete(id);
        if(delete>0){
            return JsonData.success("删除成功");
        }
        return JsonData.fail("删除失败");
    }

    /**
     * 编辑
     * @param sysFunction       信息
     * @return              JsonData
     */
    @At
    public JsonData edit(SysFunction sysFunction){
        int update = sysFunctionService.update(sysFunction);
        if(update>0){
            return JsonData.success("修改成功");
        }
        return JsonData.fail("修改失败");
    }
    /**
     * 添加数据
     * @param sysFunction       信息
     * @return              JsonData
     */
    @At
    public JsonData add(SysFunction sysFunction){
        SysFunction insert = sysFunctionService.insert(sysFunction);
        if(insert!=null){
            return JsonData.success("添加成功");
        }
        return JsonData.fail("添加失败");
    }

    /**
     * 获取列表
     * @param page      页数
     * @param limit     每页显示数量
     * @param value     搜索框值
     * @return          LayuiTableResult
     */
    @At
    public LayuiTableResult list(int page, int limit, String value){
        Criteria criteria = sysFunctionService.getVagueCriteria(value, "name");
        Pagination listPage = sysFunctionService.listPage(page, limit,criteria);
        return LayuiTableResult.result(0,"",sysFunctionService.count(criteria),listPage.getList());
    }

}

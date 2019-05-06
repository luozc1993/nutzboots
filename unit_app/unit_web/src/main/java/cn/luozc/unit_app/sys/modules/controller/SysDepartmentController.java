package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.SysDepartment;
import cn.luozc.unit_app.sys.modules.model.SysDepartmentService;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_framework.page.Pagination;
import com.alibaba.dubbo.config.annotation.Reference;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean
@At("/sys/department")
@Ok("json:full")
public class SysDepartmentController {

    @Inject
    @Reference
    private SysDepartmentService sysDepartmentService;

    @At
    @Ok("beetl:system.department.html")
    public void listPage(){

    }

    /**
     * 删除数据
     * @param id    id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        int delete = sysDepartmentService.delete(id);
        if(delete>0){
            return JsonData.success("删除成功");
        }
        return JsonData.fail("删除失败");
    }

    /**
     * 编辑
     * @param data       信息
     * @return              JsonData
     */
    @At
    public JsonData edit(SysDepartment data){
        int update = sysDepartmentService.update(data);
        if(update>0){
            return JsonData.success("修改成功");
        }
        return JsonData.fail("修改失败");
    }
    /**
     * 添加数据
     * @param data       信息
     * @return              JsonData
     */
    @At
    public JsonData add(SysDepartment data){
        SysDepartment insert = sysDepartmentService.insert(data);
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
        Criteria criteria = sysDepartmentService.getVagueCriteria(value, "companyName");
        Pagination listPage = sysDepartmentService.listPage(page, limit,criteria);
        return LayuiTableResult.result(0,"",sysDepartmentService.count(criteria),listPage.getList());
    }







}

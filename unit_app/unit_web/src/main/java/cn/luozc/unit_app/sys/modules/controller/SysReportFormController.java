package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.*;
import cn.luozc.unit_app.sys.modules.model.SysReportForm;
import cn.luozc.unit_app.sys.modules.model.SysReportFormService;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_framework.page.Pagination;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import java.util.List;

@IocBean
@At("/sys/report_form")
@Ok("json:full")
public class SysReportFormController {

    @Inject private SysReportFormService sysReportFormService;
    @Inject private SysReportService sysReportService;



    /**
     * 删除数据
     * @param id    id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        int delete = sysReportFormService.delete(id);
        if(delete>0){
            return JsonData.success("删除成功");
        }
        return JsonData.fail("删除失败");
    }

    /**
     * 编辑
     * @param sysReportForm       信息
     * @return              JsonData
     */
    @At
    public JsonData edit(SysReportForm sysReportForm){
        int update = sysReportFormService.update(sysReportForm);
        if(update>0){
            return JsonData.success("修改成功");
        }
        return JsonData.fail("修改失败");
    }
    /**
     * 添加数据
     * @param sysReportForm       信息
     * @return              JsonData
     */
    @At
    public JsonData add(SysReportForm sysReportForm){
        SysReportForm insert = sysReportFormService.insert(sysReportForm);
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
    public LayuiTableResult list(int page, int limit, String value,String reportId){
        Criteria criteria = sysReportFormService.getVagueCriteria(value, "title");
        criteria.where().and("report_id","=",reportId);
        criteria.getOrderBy().asc("order_by");
        Pagination listPage = sysReportFormService.listPage(page, limit,criteria);
        return LayuiTableResult.result(0,"",sysReportFormService.count(criteria),listPage.getList());
    }

    /**
     * 获取列表
     * @param page      页数
     * @param limit     每页显示数量
     * @param value     搜索框值
     * @return          LayuiTableResult
     */
    @At
    public LayuiTableResult listTableFeilds(int page, int limit, String value,String reportId){
        SysReport sysReport = sysReportService.fetch(reportId);
        List<Record> list = sysReportFormService.list(Sqls.create("select column_name, column_comment from information_schema.columns where table_schema ='unit' and table_name = '" + sysReport.getTableName() + "'"));
        return LayuiTableResult.result(0,"",list.size(),list);
    }






}

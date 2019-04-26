package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.*;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_framework.page.Pagination;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@IocBean
@At("/sys/report")
@Ok("json:full")
public class SysReportController {

    @Inject private SysReportService sysReportService;



    @At("/report_list")
    public LayuiTableResult report_list(int page,int limit,String fid,String value){
        SysReport sysReport = sysReportService.fetch(fid);
        if(sysReport==null){
            return LayuiTableResult.result(1,"数据错误",0,new ArrayList<>());
        }
        Criteria criteria = sysReportService.getVagueCriteria(value, "report_name");
        int count = sysReportService.count(sysReport.getTableName(), criteria);
        Pagination pagination = sysReportService.listPage(page, limit, sysReport.getTableName(), criteria);
        return LayuiTableResult.result(0,"",count,pagination.getList());
    }




    /**
     * 删除数据
     * @param id    id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        int delete = sysReportService.delete(id);
        if(delete>0){
            return JsonData.success("删除成功");
        }
        return JsonData.fail("删除失败");
    }

    /**
     * 编辑
     * @param sysReport       信息
     * @return              JsonData
     */
    @At
    public JsonData edit(SysReport sysReport){
        int update = sysReportService.update(sysReport);
        if(update>0){
            return JsonData.success("修改成功");
        }
        return JsonData.fail("修改失败");
    }
    /**
     * 添加数据
     * @param sysReport       信息
     * @return              JsonData
     */
    @At
    public JsonData add(SysReport sysReport){
        SysReport insert = sysReportService.insert(sysReport);
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
        Criteria criteria = sysReportService.getVagueCriteria(value, "name");
        Pagination listPage = sysReportService.listPage(page, limit,criteria);
        return LayuiTableResult.result(0,"",sysReportService.count(criteria),listPage.getList());
    }






}

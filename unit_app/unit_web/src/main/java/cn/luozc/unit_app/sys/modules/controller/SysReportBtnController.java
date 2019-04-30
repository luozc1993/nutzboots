package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.*;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_framework.page.Pagination;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONObject;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean
@At("/sys/report_btn")
@Ok("json:full")
public class SysReportBtnController {

    @Inject
    @Reference
    private SysReportBtnService sysReportBtnService;



    /**
     * 删除数据
     * @param id    id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        int delete = sysReportBtnService.delete(id);
        if(delete>0){
            return JsonData.success("删除成功");
        }
        return JsonData.fail("删除失败");
    }

    /**
     * 编辑
     * @param sysReportBtn       信息
     * @return              JsonData
     */
    @At
    public JsonData edit(SysReportBtn sysReportBtn){
        int update = sysReportBtnService.update(sysReportBtn);
        if(update>0){
            return JsonData.success("修改成功");
        }
        return JsonData.fail("修改失败");
    }
    /**
     * 添加数据
     * @param sysReportBtn       信息
     * @return              JsonData
     */
    @At
    public JsonData add(SysReportBtn sysReportBtn){
        SysReportBtn insert = sysReportBtnService.insert(sysReportBtn);
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
        Criteria criteria = sysReportBtnService.getVagueCriteria(value, "title");
        criteria.where().and("report_id","=",reportId);
        Pagination listPage = sysReportBtnService.listPage(page, limit,criteria);
        return LayuiTableResult.result(0,"",sysReportBtnService.count(criteria),listPage.getList());
    }






}

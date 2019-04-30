package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.*;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_framework.page.Pagination;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@IocBean
@At("/sys/company")
@Ok("json:full")
public class SysCompanyController {

    @Inject
    @Reference
    private SysCompanyService sysCompanyService;


    /**
     * 删除数据
     * @param id    id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        int delete = sysCompanyService.delete(id);
        if(delete>0){
            return JsonData.success("删除成功");
        }
        return JsonData.fail("删除失败");
    }

    /**
     * 编辑
     * @param sysCompany       信息
     * @return              JsonData
     */
    @At
    public JsonData edit(SysCompany sysCompany){
        int update = sysCompanyService.update(sysCompany);
        if(update>0){
            return JsonData.success("修改成功");
        }
        return JsonData.fail("修改失败");
    }
    /**
     * 添加数据
     * @param sysCompany       信息
     * @return              JsonData
     */
    @At
    public JsonData add(SysCompany sysCompany){
        SysCompany insert = sysCompanyService.insert(sysCompany);
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
        Criteria criteria = sysCompanyService.getVagueCriteria(value, "companyName");
        Pagination listPage = sysCompanyService.listPage(page, limit,criteria);
        return LayuiTableResult.result(0,"",sysCompanyService.count(criteria),listPage.getList());
    }







}

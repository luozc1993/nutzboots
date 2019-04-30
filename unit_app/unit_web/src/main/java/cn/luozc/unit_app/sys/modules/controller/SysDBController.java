package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.*;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_app.utils.MD5Util;
import cn.luozc.unit_framework.base.service.BaseService;
import cn.luozc.unit_framework.page.Pagination;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.commons.lang.StringUtils;
import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@IocBean
@At("/sys/db")
@Ok("json:full")
public class SysDBController {

    @Inject
    @Reference
    private BaseService baseService;

    /**
     * 获取列表
     * @param page      页数
     * @param limit     每页显示数量
     * @param value     搜索框值
     * @return          LayuiTableResult
     */
    @At
    public LayuiTableResult listTableNames(int page, int limit, String value,String reportId){
        List<Record> list = baseService.list(Sqls.create("select table_name,table_comment from information_schema.tables where table_schema='unit'"));
        return LayuiTableResult.result(0,"",list.size(),list);
    }

}

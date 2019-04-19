package cn.luozc.assemble_web.module.sys;

import cn.luozc.assemble_web.bean.sys.SysMenu;
import cn.luozc.assemble_web.bean.sys.SysReport;
import cn.luozc.assemble_web.module.BaseModule;
import cn.luozc.assemble_web.service.BaseService;
import cn.luozc.assemble_web.service.sys.MenuService;
import cn.luozc.assemble_web.service.sys.ReportService;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@At("/sys/report")
@Ok("json:full")
@IocBean
public class ReportModule extends BaseModule {
    @Inject private BaseService baseService;
    @Inject private MenuService menuService;
    @Inject private ReportService reportService;
    @Inject private Dao dao;
    @At("/list")
    public LayuiTableResult list(HttpServletRequest request){
        int page = Integer.valueOf(request.getParameter("page"));
        int limit = Integer.valueOf(request.getParameter("limit"));
        String mid = request.getParameter("mid");
        String value = request.getParameter("value");
        List<String> searchField = new ArrayList<>();
        searchField.add("name");
        searchField.add("ramarks");
        SysMenu menu = menuService.getMenuById(mid);
        if(menu==null|| StringUtils.isEmpty(menu.getPid())){
            return LayuiTableResult.result(1,"数据错误",0,new ArrayList<>());
        }

        SysReport sysReport = reportService.getSysReportById(menu.getPid());
        if(sysReport==null){
            return LayuiTableResult.result(1,"数据错误",0,new ArrayList<>());
        }
        return baseService.getList(sysReport.getTable(),page,limit,value,searchField);
    }

    @At
    public JsonData fields(String mid) {
        SysMenu menu = menuService.getMenuById(mid);
        reportService.getSysReportFormsByPid(menu.getPid());
        return null;
    }



    @At
    @Ok("th:/report/${obj.mid}/list.html")
    public NutMap page(String mid){
        NutMap map = new NutMap();
        map.put("mid",mid);
        return map;
    }
    @At
    @Ok("th:/report/${obj.mid}/${obj.bid}.html")
    public NutMap botton(String mid,String bid){
        NutMap map = new NutMap();
        map.put("mid",mid);
        map.put("bid",bid);
        return map;
    }
}

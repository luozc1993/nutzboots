package cn.luozc.assemble_web.module;

import cn.luozc.assemble_web.bean.sys.SysData;
import cn.luozc.assemble_web.bean.sys.SysMenu;
import cn.luozc.assemble_web.service.BaseService;
import cn.luozc.assemble_web.service.sys.DataService;
import cn.luozc.assemble_web.service.sys.MenuService;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.At;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class BaseModule {

    @Inject private BaseService baseService;
    @Inject private MenuService menuService;
    @Inject private DataService dataService;



    @At("/add")
    public Object add(HttpServletRequest request){
        String pid = request.getParameter("pid");
        String type = request.getParameter("type");
        String table = "";
        if("data".equals(type)){
            SysData sysData = dataService.getSysDataById(pid);
            table = sysData.getTableName();
        }

        String data = request.getParameter("data");
        JSONObject json = JSONObject.fromObject(data);
        String id = json.get("id")==null?"":json.getString("id");
        if(StringUtils.isEmpty(id)){
            json.put("id", UUID.randomUUID().toString());
            return baseService.add(table,json);
        }else{
            return baseService.edit(table,json,id);
        }
    }



    @At("/del")
    public JsonData del(HttpServletRequest request){
        String id = request.getParameter("id");
        return baseService.del("sys_table",id);
    }

    protected JSONObject getRequestData(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getParameterNames();
        String str = "id";
        List<String> list =Arrays.asList(str.split(","));
        JSONObject json = new JSONObject();
        while (headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            if(!list.contains(key)){
                json.put(key,request.getParameter(key));
            }

        }
        return json;
    }


}

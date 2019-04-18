package cn.luozc.assemble_web.module;

import cn.luozc.assemble_web.service.BaseService;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.mvc.annotation.At;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class BaseModule {

    @Inject private BaseService baseService;


    @At("/list")
    public LayuiTableResult list(HttpServletRequest request){
        int page = Integer.valueOf(request.getParameter("page"));
        int limit = Integer.valueOf(request.getParameter("limit"));
        String value = request.getParameter("value");
        List<String> searchField = new ArrayList<>();
        searchField.add("name");
        searchField.add("ramarks");
        return baseService.getList("sys_table",page,limit,value,searchField);
    }

    @At("/add")
    public Object add(HttpServletRequest request){
        String id = request.getParameter("id");
        JSONObject json = getRequestData(request);
        if(StringUtils.isEmpty(id)){
            json.put("id", UUID.randomUUID().toString());
            return baseService.add("sys_table",json);
        }else{
            return baseService.edit("sys_table",json,id);
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

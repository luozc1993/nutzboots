package cn.luozc.unit_app.sys.modules.controller;

import cn.luozc.unit_app.sys.modules.model.SysData;
import cn.luozc.unit_app.sys.modules.model.SysDataService;
import cn.luozc.unit_app.sys.modules.model.SysReport;
import cn.luozc.unit_app.utils.JsonData;
import cn.luozc.unit_app.utils.LayuiTableResult;
import cn.luozc.unit_framework.page.Pagination;
import com.alibaba.dubbo.config.annotation.Reference;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.sql.Criteria;
import org.nutz.filepool.UU32FilePool;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import java.util.UUID;

@At("/sys/data")
@Ok("json:full")
@IocBean
public class SysDataController  {

    @Inject
    @Reference
    private SysDataService sysDataService;

    @At
    @Ok("beetl:/data/${obj.did}.html")
    public JSONObject page(String mid,String did){
        JSONObject json = new JSONObject();
        json.put("did",did);
        return json;
    }

    @At
    public JsonData data_add(String pid,String type,String data){
        String table = "";
        if("data".equals(type)){
            SysData sysData = sysDataService.fetch(pid);
            table = sysData.getTableName();
        }

        JSONObject json = JSONObject.fromObject(data);
        String id = json.get("id")==null?"":json.getString("id");
        if(StringUtils.isEmpty(id)){
            id = UUID.randomUUID().toString().replaceAll("-","");
            json.put("id",id);
            sysDataService.insert(table, Chain.from(json));
        }else{
            sysDataService.update(table, Chain.from(json), Cnd.where("id", "=", id));
        }
        return JsonData.success();
    }

    /**
     * 删除数据
     * @param id    id
     * @return      JsonData
     */
    @At
    public JsonData del(String id){
        int delete = sysDataService.delete(id);
        if(delete>0){
            return JsonData.success("删除成功");
        }
        return JsonData.fail("删除失败");
    }

    /**
     * 编辑
     * @param sysData       信息
     * @return              JsonData
     */
    @At
    public JsonData edit(SysData sysData){
        int update = sysDataService.update(sysData);
        if(update>0){
            return JsonData.success("修改成功");
        }
        return JsonData.fail("修改失败");
    }
    /**
     * 添加数据
     * @param sysData       信息
     * @return              JsonData
     */
    @At
    public JsonData add(SysData sysData){
        SysData insert = sysDataService.insert(sysData);
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
        Criteria criteria = sysDataService.getVagueCriteria(value, "name");
        Pagination listPage = sysDataService.listPage(page, limit,criteria);
        return LayuiTableResult.result(0,"",sysDataService.count(criteria),listPage.getList());
    }
    

}

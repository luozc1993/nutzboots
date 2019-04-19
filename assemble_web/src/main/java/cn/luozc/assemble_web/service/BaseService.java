package cn.luozc.assemble_web.service;

import cn.luozc.assemble_web.dao.Base2Dao;
import cn.luozc.assemble_web.util.JsonData;
import cn.luozc.assemble_web.util.LayuiTableResult;
import net.sf.json.JSONObject;
import org.nutz.dao.Chain;
import org.nutz.dao.entity.Record;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

@IocBean
public class BaseService {
    @Inject private Base2Dao base2Dao;


    /**
     * 获取用户列表数据 模糊分页查询
     * @param pageNumber
     * @param pageSize
     * @param value
     * @return
     */
    public LayuiTableResult getList(String table, int pageNumber, int pageSize, String value, List<String> searchField){
        return LayuiTableResult.result(0,"",base2Dao.size(table,value,searchField),base2Dao.getList(table,pageNumber,pageSize,value,searchField));
    }

    /**
     * 编辑信息
     * @param data   信息
     * @return          插入的数据
     */
    public JsonData edit(String table, JSONObject data, String id){
        try {
            if(base2Dao.update(table, Chain.from(data), id)<=0){
                return JsonData.fail("编辑失败");
            }
        }catch (Exception e){
            return JsonData.fail("编辑失败");
        }
        return JsonData.success();
    }

    /**
     * 添加信息
     * @param data   信息
     * @return          插入的数据
     */
    public JsonData add(String table,JSONObject data){
        try {
            base2Dao.add(table, Chain.from(data));
        }catch (Exception e){
            return JsonData.fail(String.format("修改{}表失败,data=>{}"));
        }
        return JsonData.success();
    }

    /**
     * 删除数据
     * @param id
     * @return
     */
    public JsonData del(String table,String id){
        return JsonData.success(base2Dao.delete(table,id),"删除成功");
    }

    /**
     * 通过id获取数据
     */
    public Record getDataById(String table,String id){
       return base2Dao.getDataById(table,id);
    }
}

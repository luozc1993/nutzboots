package cn.luozc.assemble_web.dao;

import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Record;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@IocBean
public class Base2Dao {
    @Inject public Dao dao;

    /**
     * 模糊分页查询
     * @param pageNumber    页数
     * @param pageSize      没有人显示数量
     * @param value         模糊查询值
     * @return  返回实体对象列表
     */
    public List<Record> getList(String table, int pageNumber, int pageSize, String value, List<String> feilds){

        return dao.query(table, getVagueCriteria(value,feilds),  getPager(pageNumber, pageSize));
    }


    /**
     * 添加数据
     * @param t 实体类
     * @return  插入的数据
     */
    public void add(String table,Chain t){
        dao.insert(table,t);
    }

    /**
     * 通过id获取数据
     * @param id    数据id
     * @return  对象
     */
    public Record getDataById(String table,String id){
        return dao.fetch(table,Cnd.where("id","=",id));
    }

    /**
     * 修改数据
     */
    public int update(String table,Chain chain,String id){
        return dao.update(table,chain,Cnd.where("id","=",id));
    }

    /**
     * 删除数据
     */
    public int delete(String table ,String id){
        return dao.clear(table,Cnd.where("id","=",id));
    }

    /**
     * 获取总数据量
     * @return  int
     */
    public int size(String table,String value,List<String> feilds){
        if(StringUtils.isEmpty(value)){
            return dao.count(table);
        }
        return dao.count(table,getVagueCriteria(value,feilds));
    }

    /**
     * 获取分页
     * @param pageNumber
     * @param pageSize
     * @return
     */
    private Pager getPager(int pageNumber,int pageSize){
        Pager pager = new Pager();
        pager.setPageNumber(pageNumber);
        pager.setPageSize(pageSize);
        return pager;
    }

    /**
     * 获取模糊查询参数
     * @param value
     * @return
     */
    private  Criteria getVagueCriteria(String value,List<String> feilds) {
        Criteria cri = Cnd.cri();
        if(StringUtils.isNotEmpty(value)){
            for (String feild:feilds) {
                // 封装条件并塞入条件集合
                SqlExpressionGroup expression = Cnd.exps(feild, "like", "%"+value+"%");
                cri.where().or(expression);
            }
        }
        return cri;
    }


    public Object call(String funName, JSONObject data){
        Iterator keys = data.keys();
        List<String> list = new ArrayList<>();
        while (keys.hasNext()){
            String key = (String) keys.next();
            list.add("'"+data.getString(key)+"'");

        }
        Sql sql = Sqls.create("Call "+funName+"("+ StringUtils.join(list,",") +")");
        sql.setCallback(Sqls.callback.records());
        dao.execute(sql);
        Record record = sql.getOutParams();
        return record;
    }
}

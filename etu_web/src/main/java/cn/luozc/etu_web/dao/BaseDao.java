package cn.luozc.etu_web.dao;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.ioc.loader.annotation.Inject;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<T> {
    private Class<T> bean  = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Inject public Dao dao;

    /**
     * 模糊分页查询
     * @param pageNumber    页数
     * @param pageSize      没有人显示数量
     * @param value         模糊查询值
     * @return  返回实体对象列表
     */
    public List<T> getList(int pageNumber,int pageSize,String value,List<String> feilds){
        return dao.query(bean, getVagueCriteria(value,feilds),  getPager(pageNumber, pageSize));
    }
    /**
     * 模糊分页查询
     * @param pageNumber    页数
     * @param pageSize      没有人显示数量
     * @param value         模糊查询值
     * @return  返回实体对象列表
     */
    public List<T> getListSortAsc(int pageNumber,int pageSize,String value,List<String> feilds){
        return dao.query(bean, getVagueCriteria(value,feilds).getOrderBy().asc("sort"),  getPager(pageNumber, pageSize));
    }
    /**
     * 模糊分页查询
     * @param pageNumber    页数
     * @param pageSize      没有人显示数量
     * @param value         模糊查询值
     * @return  返回实体对象列表
     */
    public List<T> getListByRoleIdSortAsc(int pageNumber,int pageSize,String value,List<String> feilds){
        return dao.query(bean, getVagueCriteria(value,feilds).getOrderBy().asc("sort"),  getPager(pageNumber, pageSize));
    }

    /**
     * 添加数据
     * @param t 实体类
     * @return  插入的数据
     */
    public T add(T t){
        return dao.insert(t);
    }

    /**
     * 通过id获取数据
     * @param id    数据id
     * @return  对象
     */
    public T getDataById(String id){
        return dao.fetch(bean,Cnd.where("id","=",id));
    }

    /**
     * 修改数据
     * @param t 要修改的数据
     * @return  返回修改的条数
     */
    public int update(T t){
        return dao.update(t);
    }

    /**
     * 删除数据
     * @param t    需要删除的对象
     * @return  删除的条数
     */
    public int delete(T t){
        return dao.deleteWith(t,"");
    }

    /**
     * 获取总数据量
     * @return  int
     */
    public int size(String value,List<String> feilds){
        if(StringUtils.isEmpty(value)){
            return dao.count(bean);
        }
        return dao.count(bean,getVagueCriteria(value,feilds));
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

}

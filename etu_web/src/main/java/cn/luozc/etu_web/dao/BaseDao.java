package cn.luozc.etu_web.dao;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.util.cri.SqlExpressionGroup;
import org.nutz.el.Operator;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.Lang;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
public class BaseDao<T> {
    private Class<T> bean  = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Inject private Dao dao;


    public List<T> getList(){
        Criteria criteria = getCriteria("");
        Pager pager = new Pager();
        pager.setPageNumber(2);
        pager.setPageSize(10);
        return dao.query(bean,criteria,pager);
    }

    /**
     * 获取总数据量
     * @return
     */
    public int size(Criteria criteria){
        if(criteria==null){
            return dao.count(bean);
        }
        return dao.count(bean,criteria);
    }

    /**
     * 获取模糊查询参数
     * @param value
     * @return
     */
    public  Criteria getCriteria(String value) {
        Criteria cri = Cnd.cri();
        for (Field field : bean.getDeclaredFields()) {
            if("String".equals(field.getType().getSimpleName())){
                // 获取数据库字段名称
                String columnName =  field.getName();
                // 封装条件并塞入条件集合
                SqlExpressionGroup expression = Cnd.exps(columnName, "like", "%"+value+"%");
                cri.where().or(expression);
            }
        }
        return cri;
    }

}

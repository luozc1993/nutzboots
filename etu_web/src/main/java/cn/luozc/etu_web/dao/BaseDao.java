package cn.luozc.etu_web.dao;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Column;
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
        System.err.println(criteria);
        return dao.query(bean,criteria);
    }

    /**
     * 获取总数据量
     * @return
     */
    public int size(){
        return dao.count(bean);
    }

    public  Criteria getCriteria(String value) {
        Criteria cri = Cnd.cri();
        Field[] fields = bean.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            // 判断值为不为空
            try {
                if (!Lang.isEmpty(field.getName())) {
                    if (field.toString().length() == 0) {
                        continue;
                    }
                    // 获取nutz字段注解
                    Column column = field.getAnnotation(Column.class);
                    if (column == null) {
                        continue;
                    }
                    // 获取数据库字段名称
                    String columnName = (column.value() == "" || column.value().length() == 0) ? field.getName()
                            : column.value();

                    // 封装条件并塞入条件集合
                    SqlExpressionGroup expression = Cnd.exps(columnName, "like", "%"+value+"%");
                    cri.where().or(expression);
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return cri;
    }

}

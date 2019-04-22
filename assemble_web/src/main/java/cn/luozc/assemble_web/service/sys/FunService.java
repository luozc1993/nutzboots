package cn.luozc.assemble_web.service.sys;

import cn.luozc.assemble_web.bean.sys.SysFunction;
import cn.luozc.assemble_web.dao.Base2Dao;
import cn.luozc.assemble_web.dao.BaseDao;
import net.sf.json.JSONObject;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class FunService {

    @Inject
    private Dao dao;
    @Inject
    private Base2Dao base2Dao;

    public SysFunction getSysFunctionById(String id){
        return dao.fetch(SysFunction.class,id);
    }

    public Object call(String id,String data){
        SysFunction sysFunction = getSysFunctionById(id);
        JSONObject json = JSONObject.fromObject(data);
        base2Dao.call(sysFunction.getFunctionName(),json);
        return null;
    }


}

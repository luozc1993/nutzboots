package cn.luozc.assemble_web.service.sys;

import cn.luozc.assemble_web.bean.sys.SysData;
import cn.luozc.assemble_web.dao.sys.SysDataDao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean
public class DataService {

    @Inject
    private SysDataDao sysDataDao;



    public SysData getSyDataById(String id){
        return sysDataDao.getDataById(id);
    }

}

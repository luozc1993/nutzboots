package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysData;
import cn.luozc.unit_app.sys.modules.model.SysDataService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import com.alibaba.dubbo.config.annotation.Service;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysDataService.class)
public class SysDataServiceImpl extends BaseServiceImpl<SysData> implements SysDataService {
    public SysDataServiceImpl(Dao dao) {
        super(dao);
    }
}

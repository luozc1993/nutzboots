package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysCompany;
import cn.luozc.unit_app.sys.modules.model.SysCompanyService;
import cn.luozc.unit_app.sys.modules.model.SysReport;
import cn.luozc.unit_app.sys.modules.model.SysReportService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import com.alibaba.dubbo.config.annotation.Service;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysCompanyService.class)
public class SysConpanyServiceImpl extends BaseServiceImpl<SysCompany> implements SysCompanyService {
    public SysConpanyServiceImpl(Dao dao) {
        super(dao);
    }
}

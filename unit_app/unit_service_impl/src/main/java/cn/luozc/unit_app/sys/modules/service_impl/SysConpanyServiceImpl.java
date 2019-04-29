package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysCompany;
import cn.luozc.unit_app.sys.modules.model.SysCompanyService;
import cn.luozc.unit_app.sys.modules.model.SysReport;
import cn.luozc.unit_app.sys.modules.model.SysReportService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.springframework.stereotype.Service;

@IocBean(args = {"refer:dao"})
@Service
public class SysConpanyServiceImpl extends BaseServiceImpl<SysCompany> implements SysCompanyService {
    public SysConpanyServiceImpl(Dao dao) {
        super(dao);
    }
}

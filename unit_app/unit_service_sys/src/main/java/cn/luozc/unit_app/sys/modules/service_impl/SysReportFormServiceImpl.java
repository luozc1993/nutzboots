package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysReportBtn;
import cn.luozc.unit_app.sys.modules.model.SysReportBtnService;
import cn.luozc.unit_app.sys.modules.model.SysReportForm;
import cn.luozc.unit_app.sys.modules.model.SysReportFormService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import com.alibaba.dubbo.config.annotation.Service;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysReportFormService.class)
public class SysReportFormServiceImpl extends BaseServiceImpl<SysReportForm> implements SysReportFormService {
    public SysReportFormServiceImpl(Dao dao) {
        super(dao);
    }
}

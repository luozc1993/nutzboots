package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysReportBtn;
import cn.luozc.unit_app.sys.modules.model.SysReportBtnService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import com.alibaba.dubbo.config.annotation.Service;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysReportBtnService.class)
public class SysReportBtnServiceImpl extends BaseServiceImpl<SysReportBtn> implements SysReportBtnService {
    public SysReportBtnServiceImpl(Dao dao) {
        super(dao);
    }
}

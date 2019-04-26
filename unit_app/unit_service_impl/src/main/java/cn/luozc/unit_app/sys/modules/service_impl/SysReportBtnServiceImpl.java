package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysReportBtn;
import cn.luozc.unit_app.sys.modules.model.SysReportBtnService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.springframework.stereotype.Service;

@IocBean(args = {"refer:dao"})
@Service
public class SysReportBtnServiceImpl extends BaseServiceImpl<SysReportBtn> implements SysReportBtnService {
    public SysReportBtnServiceImpl(Dao dao) {
        super(dao);
    }
}

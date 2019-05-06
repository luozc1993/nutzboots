package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysCompany;
import cn.luozc.unit_app.sys.modules.model.SysCompanyService;
import cn.luozc.unit_app.sys.modules.model.SysDepartment;
import cn.luozc.unit_app.sys.modules.model.SysDepartmentService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysDepartmentService.class)
public class SysDepartmentServiceImpl extends BaseServiceImpl<SysDepartment> implements SysDepartmentService {
    public SysDepartmentServiceImpl(Dao dao) {
        super(dao);
    }
}

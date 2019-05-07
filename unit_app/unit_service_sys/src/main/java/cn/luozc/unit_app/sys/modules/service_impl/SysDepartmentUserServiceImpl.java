package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysDepartment;
import cn.luozc.unit_app.sys.modules.model.SysDepartmentService;
import cn.luozc.unit_app.sys.modules.model.SysDepartmentUser;
import cn.luozc.unit_app.sys.modules.model.SysDepartmentUserService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass= SysDepartmentUserService.class)
public class SysDepartmentUserServiceImpl extends BaseServiceImpl<SysDepartmentUser> implements SysDepartmentUserService {
    public SysDepartmentUserServiceImpl(Dao dao) {
        super(dao);
    }
}

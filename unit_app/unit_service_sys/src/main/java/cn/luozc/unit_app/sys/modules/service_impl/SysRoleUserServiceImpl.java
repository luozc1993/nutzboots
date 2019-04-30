package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.model.SysRoleUser;
import cn.luozc.unit_app.sys.modules.model.SysRoleUserService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import com.alibaba.dubbo.config.annotation.Service;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

@IocBean(args = {"refer:dao"})
@Service(interfaceClass=SysRoleUserService.class)
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUser> implements SysRoleUserService {
    public SysRoleUserServiceImpl(Dao dao) {
        super(dao);
    }
}

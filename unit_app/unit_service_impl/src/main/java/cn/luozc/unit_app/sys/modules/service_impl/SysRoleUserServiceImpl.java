package cn.luozc.unit_app.sys.modules.service_impl;

import cn.luozc.unit_app.sys.modules.service.SysRole;
import cn.luozc.unit_app.sys.modules.service.SysRoleService;
import cn.luozc.unit_app.sys.modules.service.SysRoleUser;
import cn.luozc.unit_app.sys.modules.service.SysRoleUserService;
import cn.luozc.unit_framework.base.service.BaseServiceImpl;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;
import org.springframework.stereotype.Service;

@IocBean(args = {"refer:dao"})
@Service
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUser> implements SysRoleUserService {
    public SysRoleUserServiceImpl(Dao dao) {
        super(dao);
    }
}
